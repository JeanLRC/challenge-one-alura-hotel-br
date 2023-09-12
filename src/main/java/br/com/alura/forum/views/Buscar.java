package main.java.br.com.alura.forum.views;

import main.java.br.com.alura.forum.controller.HospedeController;
import main.java.br.com.alura.forum.controller.ReservaController;
import main.java.br.com.alura.forum.model.Hospede;
import main.java.br.com.alura.forum.model.Reserva;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

    int xMouse, yMouse;
    String reserva;
    String hospedes;
    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHospedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloHospedes;
    private JLabel labelAtras;
    private JLabel labelExit;
    private HospedeController hospedeController;
    private ReservaController reservaController;

    /**
     * Create the frame.
     */
    public Buscar() {
        this.reservaController = new ReservaController();
        this.hospedeController = new HospedeController();
        setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);


        JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
        lblTitulo.setForeground(new Color(12, 138, 199));
        lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblTitulo.setBounds(331, 62, 280, 42);
        contentPane.add(lblTitulo);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);

        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Data Check In");
        modelo.addColumn("Data Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");
        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);
        preencherTabelaReservas();


        tbHospedes = new JTable();
        tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
        modeloHospedes.addColumn("Numero de Hóspede");
        modeloHospedes.addColumn("Nome");
        modeloHospedes.addColumn("Sobrenome");
        modeloHospedes.addColumn("Data de Nascimento");
        modeloHospedes.addColumn("Nacionalidade");
        modeloHospedes.addColumn("Telefone");
        modeloHospedes.addColumn("Numero de Reserva");
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
        panel.addTab("Huéspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);
        preencherTabelaHospedes();

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                limparTabela();
                preencherTabelaReservasId();
                preencherTabelaHospedesId();
            }
        });
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                atualizarReservas();
                limparTabela();
                preencherTabelaReservas();
                preencherTabelaHospedes();
            }
        });
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        JPanel btnDeletar = new JPanel();
        btnDeletar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaReservas = tbReservas.getSelectedRow();
                int filaHospedes = tbHospedes.getSelectedRow();

                if (filaReservas >= 0) {
                    reserva = tbReservas.getValueAt(filaReservas, 0).toString();
                    int confirmar = JOptionPane.showConfirmDialog(null, "Deseja excluir os dados?");
                    if (confirmar == JOptionPane.YES_OPTION) {
                        String valor = tbReservas.getValueAt(filaReservas, 0).toString();
                        reservaController.deletar(Long.valueOf(valor));
                        JOptionPane.showMessageDialog(contentPane, "Registro Excluído");
                        limparTabela();
                        preencherTabelaReservas();
                        preencherTabelaHospedes();
                    }
                }
                if (filaHospedes >= 0) {
                    hospedes = tbHospedes.getValueAt(filaHospedes, 0).toString();
                    int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir os dados?");
                    if (confirmar == JOptionPane.YES_OPTION) {
                        String valor = tbHospedes.getValueAt(filaHospedes, 0).toString();
                        hospedeController.deletar(Long.valueOf(valor));
                        JOptionPane.showMessageDialog(contentPane, "Registro Excluído");
                        limparTabela();
                        preencherTabelaHospedes();
                        preencherTabelaReservas();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Erro: fileira não selecionada, por favor realize uma busca e selecione uma fileira para excluir");
                }
            }
        });
        btnDeletar.setLayout(null);
        btnDeletar.setBackground(new Color(12, 138, 199));
        btnDeletar.setBounds(767, 508, 122, 35);
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnDeletar);

        JLabel lblExcluir = new JLabel("DELETAR");
        lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
        lblExcluir.setForeground(Color.WHITE);
        lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblExcluir.setBounds(0, 0, 122, 35);
        btnDeletar.add(lblExcluir);
        setResizable(false);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Buscar frame = new Buscar();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

    private List<Reserva> buscarReservas() {
        return this.reservaController.buscar();
    }

    private List<Reserva> buscarReservasId() {
        return this.reservaController.buscarId(txtBuscar.getText());
    }

    private List<Hospede> buscarHospedes() {
        return this.hospedeController.buscar();
    }

    private List<Hospede> buscarHospedesId() {
        return this.hospedeController.buscarId(txtBuscar.getText());
    }

    private void preencherTabelaReservas() {
        List<Reserva> reservasList = buscarReservas();
        try {
            for (Reserva reserva : reservasList) {
                modelo.addRow(new Object[]{reserva.getId(), reserva.getDataEntrada(), reserva.getDataSaida(), reserva.getValor(), reserva.getFormaDePagamento()});
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void preencherTabelaReservasId() {
        List<Reserva> reservaList = buscarReservasId();
        try {
            for (Reserva reserva : reservaList) {
                modelo.addRow(new Object[]{reserva.getId(), reserva.getDataEntrada(), reserva.getDataSaida(), reserva.getValor(), reserva.getFormaDePagamento()});
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void preencherTabelaHospedes() {
        List<Hospede> hospedeList = buscarHospedes();
        try {
            for (Hospede hospede : hospedeList) {
                modeloHospedes.addRow(new Object[]{hospede.getId(), hospede.getNome(), hospede.getSobrenome(), hospede.getDataNascimento(), hospede.getNacionalidade(), hospede.getTelefone(), hospede.getIdReserva()});
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void preencherTabelaHospedesId() {
        List<Hospede> hospedeList = buscarHospedesId();
        try {
            for (Hospede hospede : hospedeList) {
                modeloHospedes.addRow(new Object[]{hospede.getId(), hospede.getNome(), hospede.getSobrenome(), hospede.getDataNascimento(), hospede.getNacionalidade(), hospede.getTelefone(), hospede.getIdReserva()});
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void atualizarReservas() {
        Optional<Object> valorOpcional = Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()));
        valorOpcional.ifPresent(fila -> {
            Date dataE = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
            Date dataS = Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(),2).toString());
            String valor = (String) modelo.getValueAt(tbReservas.getSelectedRow(),3);
            String formaPagamento = (String) modelo.getValueAt(tbReservas.getSelectedRow(),4);
            Long id = Long.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(),0).toString());
            this.reservaController.atualizar(dataE, dataS, valor, formaPagamento, id);
            JOptionPane.showMessageDialog(this, String.format("Registro modificado com sucesso!"));
        });
        valorOpcional.orElseGet(() -> {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um registro");
            return null;
        });
    }

    private void atualizarHospede() {
        Optional<Object> valorOpcional = Optional.ofNullable(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn()));
        valorOpcional.ifPresent(filaHospede -> {
            
            String nome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(),1);
            String sobrenome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(),2);
            Date dataNascimento = Date.valueOf(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(),3).toString());
            String nacionalidade = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(),4);
            String telefone = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(),5);
            Long idReserva = Long.valueOf(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(),6).toString());
            Long id = Long.valueOf(modeloHospedes.getValueAt(tbHospedes.getSelectedRow(),0).toString());
            this.hospedeController.atualizar(nome, sobrenome,dataNascimento,nacionalidade,telefone,idReserva, id);
            JOptionPane.showMessageDialog(this, String.format("Registro modificado com sucesso"));
        });
        valorOpcional.orElseGet(() -> {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um Registro");
            return null;
        });
    }

    private void limparTabela() {
        ((DefaultTableModel) tbHospedes.getModel()).setRowCount(0);
        ((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
    }
}

