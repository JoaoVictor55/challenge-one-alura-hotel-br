package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import controller.FormaDePagamentoController;
import controller.HospedeController;
import controller.NacionalidadeController;
import controller.ReservaController;
import domain.formaPagamento.FormaPagamento;
import domain.hospede.Hospede;
import domain.nacionalidade.Nacionalidade;
import domain.reserva.Reserva;
import domain.reserva.ReservaDetalhes;


import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	
	private ReservaController reservaController;
	private HospedeController hospedeController;
	private Integer reservaMudou = -1;
	private Integer hospedeMudou = -1;
	
	private Reserva novaReserva; 
	private Hospede novoHospede;
	
	private FormaDePagamentoController formaPagamentoController;
	private NacionalidadeController nacionalidadeController;
	
	private Integer idBuscado;
	private String sobrenomeBuscado;
	int xMouse, yMouse;

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

	/**
	 * Create the frame.
	 */
	public Buscar() {
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
		
		//List<Hospede> hospedes = new ArrayList<>();
				
		idBuscado = null;
		sobrenomeBuscado = "";
		
		reservaController = new ReservaController();
		hospedeController = new HospedeController();
		
		nacionalidadeController = new NacionalidadeController();
		formaPagamentoController = new FormaDePagamentoController();
		
		List<Nacionalidade> nacionalidades = nacionalidadeController.listarNacionalidades();
		List<FormaPagamento> formaPagamento = formaPagamentoController.listarFormasDePagamento();
		novaReserva = new Reserva(null, null, null, null,null);
		novoHospede = new Hospede();
		
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
		tbReservas.setModel(new DefaultTableModel() {
			
	        @Override
	        public boolean isCellEditable(int row, int column)
	        {
	            // coluna 4 é não editável nem a 0
	            return column != 4 && column != 0;
	        }
		});
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
				
		tbReservas.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
			
				//System.out.println(tbReservas.getSelectedColumn()+" "+tbReservas.getSelectedRow());
				
			
				if(e.getClickCount() == 2 && tbReservas.getSelectedColumn() == 4) {
					
					Object [] nomes = formaPagamento.stream().map(FormaPagamento::getNome).toArray();
					
					String antigo = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
					
					String novo = (String) JOptionPane.showInputDialog(null, "Choose retard!", "CHoose, disgraça",JOptionPane.QUESTION_MESSAGE,
							null,nomes, nomes[0]);
					
					//modelo.setValueAt((novo == null ? antigo : novo), tbReservas.getSelectedRow(), 4);
					
					if(novo != null) {
						
						for(FormaPagamento f : formaPagamento) {
							
							if(novo == f.getNome()) {
								novaReserva.setFormaPagamento(f);
								break;
							}
						}
						
						modelo.setValueAt(novo, tbReservas.getSelectedRow(), 4);
						reservaMudou = Integer.parseInt( (String) modelo.getValueAt(tbReservas.getSelectedRow(), 0));
					}else {
						
						modelo.setValueAt(antigo, tbReservas.getSelectedRow(), 4);
					}
					
				}
			}
		
		});
		
		modelo.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				
				//novaReserva.setDataReserva(null);
				Integer rowEditor = tbReservas.getEditingRow();
				Integer columnEditor = tbReservas.getEditingColumn();
				boolean dataAtualizada = false;
				
				if(columnEditor == -1 || rowEditor == -1) {
					return;
				}
				
				switch(columnEditor) {
				
				//data checkin
				case 1:
					
					try {
						
						novaReserva.setDataReserva(new SimpleDateFormat("yyyy-MM-dd").parse((String)modelo.getValueAt(rowEditor, columnEditor)));
						dataAtualizada = true;
						
						
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(panel, "Data inválida", "Erro", JOptionPane.ERROR_MESSAGE);
					}
					
				break;
				
				case 2:
					try {
						
						novaReserva.setDataSaida(new SimpleDateFormat("yyyy-MM-dd").parse((String)modelo.getValueAt(rowEditor, columnEditor)));
						dataAtualizada = true;
						
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(panel, "Data inválida", "Erro", JOptionPane.ERROR_MESSAGE);
					}
				break;
				
				
				}
				
				if(dataAtualizada) {
					novaReserva.atualizarValor();
					reservaMudou = Integer.parseInt((String)modelo.getValueAt(tbReservas.getSelectedRow(), 0));
				}
				
			}
		});
		
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbHospedes.setModel(new DefaultTableModel() {
			
	        @Override
	        public boolean isCellEditable(int row, int column)
	        {
	            // coluna 4 é não editável nem a 0
	            return column != 4 && column != 0;
	        }
			
		});
		modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Sobrenome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Numero de Reserva");
		
		tbHospedes.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getClickCount() == 2 && tbHospedes.getSelectedColumn() == 4) {
					
					Object [] hospedesNomes = nacionalidades.stream().map(Nacionalidade::getNome).toArray();
					
					String antigo = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 4);
					
					String novo = (String) JOptionPane.showInputDialog(null, "Choose retard!", "CHoose, disgraça",JOptionPane.QUESTION_MESSAGE,
							null,hospedesNomes, hospedesNomes[0]);
					
					if(novo != null) {
						
						for(Nacionalidade n : nacionalidades) {
							
							if(novo == n.getNome()) {
								
								novoHospede.setNacionalidade(n);
								break;
							}
						}
						
						modeloHospedes.setValueAt(novo, tbHospedes.getSelectedRow(), 4);
						hospedeMudou = (Integer) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 0);
					}else {
						
						modeloHospedes.setValueAt(antigo, tbHospedes.getSelectedRow(), 4);
					}

				}
			}
			
		});
		
		modeloHospedes.addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				
				Integer rowEditor = tbHospedes.getEditingRow();
				Integer columnEditor = tbHospedes.getEditingColumn();
				
				switch(columnEditor) {
					
				case 1:
						
						novoHospede.setNome((String)modeloHospedes.getValueAt(rowEditor, columnEditor));
						hospedeMudou = Integer.parseInt((String)modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 0));
					break;
				
				case 2:
					novoHospede.setNome((String)modeloHospedes.getValueAt(rowEditor, columnEditor));
					hospedeMudou = Integer.parseInt((String)modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 0));
					break;
				
				case 3:
					novoHospede.setDataNascimento((String)modeloHospedes.getValueAt(rowEditor, columnEditor));
					hospedeMudou = Integer.parseInt((String)modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 0));
					break;
					
				case 5:
					novoHospede.setTelefone((String)modeloHospedes.getValueAt(rowEditor, columnEditor));
					hospedeMudou = Integer.parseInt((String)modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 0));
					break;
				
				}
				
				
			}
		});
		
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Huéspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
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
				
				if(!printarHospedeReserva()) {
					
					JOptionPane.showMessageDialog(contentPane, "hospede ou reserva não encontradas");
				}
								
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
			public void mouseClicked(MouseEvent e)  {

				if(reservaMudou != -1) {
					
					Date buffer = novaReserva.getDataReserva();
					java.sql.Date dataReserva = null;
					java.sql.Date dataSaida = null;
					
					if(buffer != null) {
						
						dataReserva  = new java.sql.Date(buffer.getTime());
						System.out.println(buffer+" - "+dataReserva);
						
					}
					 
						
					
					buffer = novaReserva.getDataSaida();
					if(buffer != null) {
						
						
						dataSaida = new java.sql.Date(buffer.getTime());
						System.out.println(buffer+" - "+dataSaida);
						
					}
						
					
					reservaController.atualizar(reservaMudou,dataReserva, dataSaida, novaReserva.getValor(), novaReserva.getHospede(), novaReserva.getFormaPagamento());
					
				}
				
				if(hospedeMudou != -1) {
					System.out.println(hospedeMudou);
					hospedeController.atualizar(hospedeMudou, novoHospede.getNome(), novoHospede.getSobrenome(), novoHospede.getDataNascimento(), novoHospede.getNacionalidade(), novoHospede.getTelefone());
				}
				
				modelo.setRowCount(0);
				modeloHospedes.setRowCount(0);
				printarHospedeReserva();
				
				//Object [] nomes = new Object[nacionalidades.size()];
				
				//Hospede selecionado
							
				/*
				Integer linha = tbReservas.getSelectedRow();
				
				if(linha == -1) {
					linha = tbHospedes.getSelectedRow();
					
					if(linha == -1) {
						return ; //o usuário não selecionou nada
					}
					
					
					
				}

			*/	
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
			public void mouseClicked(MouseEvent e)  {
				
				Integer linha = tbHospedes.getSelectedRow();
				
				if(linha == -1) {
					
					if(linha == -1) {
						
						return ;
					}
					
					linha = tbReservas.getSelectedRow();
					
					Integer reservaId = Integer.parseInt(tbReservas.getModel().getValueAt(linha, 0).toString());
					
					reservaController.deletar(reservaId);
					modelo.setRowCount(0);
					
					if (!printarHospedeReserva()) {
						
						JOptionPane.showMessageDialog(contentPane, "Não há mais reservas com essa identificação");
					}
					
		
					
				}
				else {
				Integer hospedeId = Integer.parseInt(tbHospedes.getModel().getValueAt(linha, 0).toString());

				hospedeController.deletarHospede(hospedeId);
				modeloHospedes.setRowCount(0);
				modelo.setRowCount(0);
				
				if(!printarHospedeReserva()) {
					
					JOptionPane.showMessageDialog(contentPane, "Não há mais hospedes com esse sobrenome");
				}
				
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
	    
	private Boolean printarHospedeReserva() {
		
		try {
			
			
			Integer id = Integer.parseInt(txtBuscar.getText());
			
			try {
				
				
				Reserva resultado = reservaController.listarPorId(id);
				Hospede hospede = resultado.getHospede();
							
				modelo.addRow(new String[] {resultado.getId().toString(),
						resultado.getDataReserva().toString(), resultado.getDataSaida().toString(),
						resultado.getValor().toString(), resultado.getFormaPagamento().getNome()});
				
				
				modeloHospedes.addRow(new String[] {hospede.getIndice().toString(), hospede.getNome(),
						hospede.getSobrenome(), hospede.getDataNascimento().toString(),
						hospede.getNacionalidade().getNome(), hospede.getTelefone(),
						resultado.getId().toString()});
				
			}
			catch(NullPointerException e) {
				//JOptionPane.showMessageDialog(contentPane, "Reserva não encontrada");
				return false; 
			}

			
		}catch(NumberFormatException foo) {
			
			List<Reserva> resultado = reservaController.listarPorSobreNome(txtBuscar.getText());
			
			for(Reserva res : resultado) {
				
				modelo.addRow(new String[] {res.getId().toString(),
						res.getDataReserva().toString(), res.getDataSaida().toString(),
						res.getValor().toString(), res.getFormaPagamento().getNome()});	
		
				Hospede hospede = res.getHospede();
				
				modeloHospedes.addRow(new String[] {hospede.getIndice().toString(), hospede.getNome(),
						hospede.getSobrenome(), hospede.getDataNascimento().toString(),
						hospede.getNacionalidade().getNome(), hospede.getTelefone(),
						res.getId().toString()});
			}
		
		return !resultado.isEmpty();
	}
		
	return true;
		
		
	    
}
	}
	
