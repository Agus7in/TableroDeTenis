import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.ScrollPane;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Console;
import java.awt.event.ActionEvent;

public class Tablero extends JFrame {
	
	private JPanel contentPane;
	private JTable table;
	final DefaultTableModel modelo = new DefaultTableModel();
	private int puntos1=0;
	private int puntos2=0;
	private int games1=0;
	private int games2=0;
	private int sets1=0;
	private int sets2=0;
	private int columnaDeGame=2;
	private boolean banderaInicio=false;
	private boolean banderaTieBreak=false;
	
	private static int op;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	
	public Tablero(Partido partido) {                  
		
		setTitle("Partido del torneo "+partido.torneo);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 230);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 456, 100);
		contentPane.add(scrollPane);
		
		table = new JTable();
		final Object[] fila = new Object[3];
		modelo.setColumnIdentifiers(new Object[] { "Jugador", "Puntos", "Set 1"});
		fila[0]=partido.jugador1;
		fila[1]=0;
		fila[2]=0;
		modelo.addRow(fila);
		fila[0]=partido.jugador2;
		fila[1]=0;
		fila[2]=0;
		modelo.addRow(fila);

		if(partido.getSets()==3) {
			modelo.addColumn("Set 2"); 
			modelo.addColumn("Set 3"); 
		}
		if(partido.getSets()==5) {
			modelo.addColumn("Set 2"); 
			modelo.addColumn("Set 3");
			modelo.addColumn("Set 4"); 
			modelo.addColumn("Set 5"); 
		}	
		table.setModel(modelo);
		
		scrollPane.setViewportView(table);

		JButton btnIniciar = new JButton("Iniciar Game");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				banderaInicio=true;
				JOptionPane.showMessageDialog(null, "Saca: "+partido.saque, "Saque",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnIniciar.setBounds(10, 131, 130, 23);
		contentPane.add(btnIniciar);
		
		
		JButton btnJugarPunto = new JButton("Jugar Punto");
		btnJugarPunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!banderaInicio) {
					JOptionPane.showMessageDialog(null, "Debe iniciar un nuevo game", "Error",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				int aleatorio=(int) Math.floor(Math.random()*100);
				///***Tie break***///
				if(banderaTieBreak) {
					if(aleatorio<=partido.prob1) {
						System.out.println("Punto para "+partido.jugador1);
						if(puntos1<7) {
							puntos1++;
							table.setValueAt(puntos1,0,1);
							return;
						}
						if(puntos1>=7) {
							if(puntos2<=puntos1-2) {
								JOptionPane.showMessageDialog(null, "Ganador del game: "+partido.jugador1, "Game",
										JOptionPane.INFORMATION_MESSAGE);
								partido.ganadorGame=partido.jugador1;
								ganoGame(partido);
								ganoSet(partido);
								return;
							}
						}
					}else {
						System.out.println("Punto para "+partido.jugador2);
						if(puntos2<7) {
							puntos2++;
							table.setValueAt(puntos2,0,1);
							return;
						}
						if(puntos2>=7) {
							if(puntos1<=puntos2-2) {
								JOptionPane.showMessageDialog(null, "Ganador del game: "+partido.jugador2, "Game",
										JOptionPane.INFORMATION_MESSAGE);
								partido.ganadorGame=partido.jugador2;
								ganoGame(partido);
								ganoSet(partido);
								return;
							}
						}
					}
				}	
				///***FIN Tie break***///
				if(aleatorio<=partido.prob1) {
					System.out.println("Punto para "+partido.jugador1);
					if(puntos1<30) {
						puntos1+=15;
						table.setValueAt(puntos1,0,1);
						return;
					}
					if(puntos1==30) {
						puntos1+=10;
						table.setValueAt(puntos1,0,1);
						return;
					}
					if(puntos1==40) {
						if(puntos2!=40 && puntos2!=50) {
							JOptionPane.showMessageDialog(null, "Ganador del game: "+partido.jugador1, "Game",
									JOptionPane.INFORMATION_MESSAGE);
							partido.ganadorGame=partido.jugador1;
							ganoGame(partido);
							ganoSet(partido);
							return;
						}
						if(puntos2==50) {
							puntos2-=10;
							table.setValueAt(puntos2,1,1);
							return;
						}
						puntos1+=10;
						table.setValueAt("Ad",0,1);
						return;
					}
					if(puntos1==50) {
						if(puntos2!=50) {
							JOptionPane.showMessageDialog(null, "Ganador del game: "+partido.jugador1, "Game",
									JOptionPane.INFORMATION_MESSAGE);
							partido.ganadorGame=partido.jugador1;
							ganoGame(partido);
							ganoSet(partido);		
							return;
						}	
					}
					if(puntos2==50) {
						puntos2-=10;
						table.setValueAt(puntos2,1,1);
						return;
					}		
				}else {
					System.out.println("Punto para "+partido.jugador2);
					if(puntos2<30) {
						puntos2+=15;
						table.setValueAt(puntos2,1,1);
						return;
					}
					if(puntos2==30) {
						puntos2+=10;
						table.setValueAt(puntos2,1,1);
						return;
					}
					if(puntos2==40) {
						if(puntos1!=40 && puntos1!=50) {
							JOptionPane.showMessageDialog(null, "Ganador del game: "+partido.jugador2, "Game",
									JOptionPane.INFORMATION_MESSAGE);
							partido.ganadorGame=partido.jugador2;
							ganoGame(partido);
							ganoSet(partido);
							return;
						}
						if(puntos1==50) {
							puntos1-=10;
							table.setValueAt(puntos1,0,1);
							return;
						}
						puntos2+=10;
						table.setValueAt("Ad",1,1);
						return;
					}
					if(puntos2==50) {
						if(puntos1!=50) {
							JOptionPane.showMessageDialog(null, "Ganador del game: "+partido.jugador2, "Game",
									JOptionPane.INFORMATION_MESSAGE);
							partido.ganadorGame=partido.jugador2;
							ganoGame(partido);
							ganoSet(partido);
							return;
						}				
					}
					if(puntos1==50) {
						puntos1-=10;
						table.setValueAt(puntos1,1,1);
						return;
					}
				}
			}
		});
		btnJugarPunto.setBounds(187, 131, 130, 23);
		contentPane.add(btnJugarPunto);		
	}
	public void limpiarConsola() {
		System.out.flush();
	}
	public void ganoGame(Partido partido) {
		banderaInicio=false;
		puntos1=0;
		puntos2=0;
		table.setValueAt(puntos1,0,1);
		table.setValueAt(puntos2,1,1);
		
		/*** CAMBIO DE SACADOR ***/
		if(partido.saque==partido.jugador1) {
			partido.saque=partido.jugador2;	
		}else {
			partido.saque=partido.jugador1;
		}
		
		/*** SUMAR LOS GAMES A UN SET ***/
		if(partido.ganadorGame==partido.jugador1) {
			games1++;
			table.setValueAt(games1,0,columnaDeGame);
			ganoSet(partido);
			return;
		}
		if(partido.ganadorGame==partido.jugador2) {
			games2++;
			table.setValueAt(games2,1,columnaDeGame);
    		ganoSet(partido);
			return;
		}
	}
	public void ganoSet(Partido partido) {
		if(games1==6 && games1==games2) {
			JOptionPane.showMessageDialog(null, "Tie Break", "Set",
					JOptionPane.INFORMATION_MESSAGE);
		}
		if(games1>=6) {
			if(games1>=games2+2) {
				partido.ganadorSet=partido.jugador1;
				games1=0;
				games2=0;
				sets1++;
				JOptionPane.showMessageDialog(null, "Ganador del set: "+partido.ganadorSet, "Set",
						JOptionPane.INFORMATION_MESSAGE);
				
				if(ganoPartido(partido,sets1,sets2)) {
					if(op==0){
						dispose();
						partido.ganadorGame="";
						partido.ganadorSet="";
						puntos1=0;
					    puntos2=0;
						games1=0;
						games2=0;
						sets1=0;
						sets2=0;
						Tablero tablero2=new Tablero(partido);
						tablero2.setVisible(true);
					}
					if(op==1) {
						dispose();
						TorneoDeTenis tdt=new TorneoDeTenis();
						tdt.setVisible(true);
					}
					return;
				}
				columnaDeGame++;
				table.setValueAt(0,0,columnaDeGame);
				table.setValueAt(0,1,columnaDeGame);
			}
			if(games1==7 && games2==6) {
				partido.ganadorSet=partido.jugador1;
				games1=0;
				games2=0;
				sets1++;
				JOptionPane.showMessageDialog(null, "Ganador del set: "+partido.ganadorSet, "Set",
						JOptionPane.INFORMATION_MESSAGE);
				
				if(ganoPartido(partido,sets1,sets2)) {
					if(op==0){
						dispose();
						partido.ganadorGame="";
						partido.ganadorSet="";
						puntos1=0;
					    puntos2=0;
						games1=0;
						games2=0;
						sets1=0;
						sets2=0;
						Tablero tablero2=new Tablero(partido);
						tablero2.setVisible(true);
					}
					if(op==1) {
						dispose();
						TorneoDeTenis tdt=new TorneoDeTenis();
						tdt.setVisible(true);
					}
					return;
				}
				
				columnaDeGame++;
				table.setValueAt(0,0,columnaDeGame);
				table.setValueAt(0,1,columnaDeGame);
			}
		}
		if(games2>=6) {
			if(games2>=games1+2) {
				partido.ganadorSet=partido.jugador2;
				games1=0;
				games2=0;
				sets2++;

				JOptionPane.showMessageDialog(null, "Ganador del set: "+partido.ganadorSet, "Set",
						JOptionPane.INFORMATION_MESSAGE);
				
				if(ganoPartido(partido,sets1,sets2)) {
					if(op==0){
						dispose();
						partido.ganadorGame="";
						partido.ganadorSet="";
						puntos1=0;
					    puntos2=0;
						games1=0;
						games2=0;
						sets1=0;
						sets2=0;
						Tablero tablero2=new Tablero(partido);
						tablero2.setVisible(true);
					}
					if(op==1) {
						dispose();
						TorneoDeTenis tdt=new TorneoDeTenis();
						tdt.setVisible(true);
					}
					return;
				}
				
				columnaDeGame++;
				table.setValueAt(0,0,columnaDeGame);
				table.setValueAt(0,1,columnaDeGame);
			}
			if(games2==7 && games1==6) {
				partido.ganadorSet=partido.jugador2;
				games1=0;
				games2=0;
				sets2++;
				JOptionPane.showMessageDialog(null, "Ganador del set: "+partido.ganadorSet, "Set",
						JOptionPane.INFORMATION_MESSAGE);
				
				if(ganoPartido(partido,sets1,sets2)) {
					if(op==0){
						dispose();
						partido.ganadorGame="";
						partido.ganadorSet="";
						puntos1=0;
					    puntos2=0;
						games1=0;
						games2=0;
						sets1=0;
						sets2=0;
						Tablero tablero2=new Tablero(partido);
						tablero2.setVisible(true);
					}
					if(op==1) {
						dispose();
						TorneoDeTenis tdt=new TorneoDeTenis();
						tdt.setVisible(true);
					}
					return;
				}
				
				columnaDeGame++;
				table.setValueAt(0,0,columnaDeGame);
				table.setValueAt(0,1,columnaDeGame);
			}
		}
		return;
	}
	/***********************************ANTES
	public boolean ganoPartido(Partido partido, int setsGanados1, int setsGanados2) {
		if(partido.sets==3) {
			if(setsGanados1==2) {
				op = JOptionPane.showConfirmDialog(contentPane, "Ganador del partido: "+partido.jugador1 +"\nTorneo: "
						+partido.torneo+"\n\n ¿Desea reiniciar el partido? ");
				
				return true;
			}
			if(setsGanados2==2) {
				op = JOptionPane.showConfirmDialog(contentPane, "Ganador del partido: "+partido.jugador2 +"\nTorneo: "
						+partido.torneo+"\n\n ¿Desea reiniciar el partido? ");
				return true;
			}
		}
		if(partido.sets==5) {
			if(setsGanados1==3) {
				op = JOptionPane.showConfirmDialog(contentPane, "Ganador del partido: "+partido.jugador1 +"\nTorneo: "
						+partido.torneo+"\n ¿Desea reiniciar el partido? ");
				return true;
			}
			if(setsGanados2==3) {
				op = JOptionPane.showConfirmDialog(contentPane, "Ganador del partido "+partido.jugador2 +"\n"+"\nTorneo: "
						+partido.torneo+"\n ¿Desea jugar la revancha? ","Fin del Partido", JOptionPane.YES_NO_OPTION);
				return true;
			}
		}
		return false;
	}
	*************************************/
	public boolean ganoPartido(Partido partido, int setsGanados1, int setsGanados2) {
		if(partido.sets==3) {
			if(setsGanados1==2) {
				op = JOptionPane.showConfirmDialog(contentPane, "Ganador del partido: "+partido.jugador1 +"\nTorneo: "
						+partido.torneo+"\n ¿ Desea jugar la revancha ? ","Fin del Partido", JOptionPane.YES_NO_OPTION);
				
				return true;
			}
			if(setsGanados2==2) {
				op = JOptionPane.showConfirmDialog(contentPane, "Ganador del partido: "+partido.jugador2 +"\nTorneo: "
						+partido.torneo+"\n ¿ Desea jugar la revancha ? ","Fin del Partido", JOptionPane.YES_NO_OPTION);
				return true;
			}
		}
		if(partido.sets==5) {
			if(setsGanados1==3) {
				op = JOptionPane.showConfirmDialog(contentPane, "Ganador del partido: "+partido.jugador1 +"\nTorneo: "
						+partido.torneo+"\n ¿ Desea jugar la revancha ? ","Fin del Partido", JOptionPane.YES_NO_OPTION);
				return true;
			}
			if(setsGanados2==3) {
				op = JOptionPane.showConfirmDialog(contentPane, "Ganador del partido "+partido.jugador2 +"\n"+"\nTorneo: "
						+partido.torneo+"\n ¿ Desea jugar la revancha ? ","Fin del Partido", JOptionPane.YES_NO_OPTION);
				return true;
			}
		}
		return false;
	}
}
