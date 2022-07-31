import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;


import javax.swing.ButtonGroup;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.io.Console;
import java.awt.event.ActionEvent;

public class TorneoDeTenis extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TorneoDeTenis frame = new TorneoDeTenis();
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
	public TorneoDeTenis() {
		
		setResizable(false);
		setTitle("Torneo de Tenis");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 330);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Jugador 1");
		lblNewLabel.setBounds(20, 55, 66, 14);
		contentPane.add(lblNewLabel);
		
		JTextField tfJugador1 = new JTextField();
		tfJugador1.setBounds(96, 55, 180, 20);
		contentPane.add(tfJugador1);
		tfJugador1.setColumns(10);
		
		JLabel lblJugador = new JLabel("Jugador 2");
		lblJugador.setBounds(20, 108, 66, 14);
		contentPane.add(lblJugador);
		
		JTextField tfJugador2 = new JTextField();
		tfJugador2.setColumns(10);
		tfJugador2.setBounds(96, 105, 180, 20);
		contentPane.add(tfJugador2);
		
		JLabel lblTorneo = new JLabel("Torneo");
		lblTorneo.setBounds(20, 158, 66, 14);
		contentPane.add(lblTorneo);
		
		JTextField tfTorneo = new JTextField();
		tfTorneo.setColumns(10);
		tfTorneo.setBounds(96, 155, 180, 20);
		contentPane.add(tfTorneo);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad de sets");
		lblNewLabel_1.setBounds(20, 210, 103, 14);
		contentPane.add(lblNewLabel_1);
		
		JRadioButton radio5 = new JRadioButton("5");
		radio5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonGroup_1.add(radio5);
		radio5.setSelected(true);

		radio5.setBounds(133, 206, 66, 23);
		contentPane.add(radio5);
		
		JRadioButton radio3 = new JRadioButton("3");
		radio3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonGroup_1.add(radio3);

		radio3.setBounds(201, 206, 66, 23);
		contentPane.add(radio3);
		
		JLabel lblNewLabel_2 = new JLabel("Probabilidad de ganar (%)");
		lblNewLabel_2.setBounds(415, 23, 151, 14);
		contentPane.add(lblNewLabel_2);
		
		JSpinner probabilidad1 = new JSpinner();
		probabilidad1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		probabilidad1.setModel(new SpinnerNumberModel(1, 0, 100, 1));
		probabilidad1.setBounds(466, 55, 91, 20);
		contentPane.add(probabilidad1);
		
		JSpinner probabilidad2 = new JSpinner();
		probabilidad2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		probabilidad2.setModel(new SpinnerNumberModel(1, 0, 100, 1));
		probabilidad2.setBounds(466, 105, 91, 20);
		contentPane.add(probabilidad2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Saque");
		lblNewLabel_2_1.setBounds(323, 23, 66, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JRadioButton saque1 = new JRadioButton("");
		saque1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonGroup.add(saque1);
		saque1.setSelected(true);

		saque1.setBounds(330, 50, 21, 23);
		contentPane.add(saque1);
		
		JRadioButton saque2 = new JRadioButton("");
		saque2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonGroup.add(saque2);

		saque2.setBounds(330, 105, 21, 23);
		contentPane.add(saque2);
		
		JButton btnNewButton = new JButton("Comenzar Partido");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String jugador1=tfJugador1.getText().toString();
				String jugador2=tfJugador2.getText().toString();
				String torneo=tfTorneo.getText().toString();
				String saque=jugador1;
				int sets=3;
				 //Validaciones
				if(jugador1.equals("")||jugador2.equals("")) {
					JOptionPane.showMessageDialog(null, "Debe ingresar los nombres de los jugadores.", "Error",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(torneo.equals("")) {
					JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del torneo.", "Error",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				int prob1=Integer.parseInt(probabilidad1.getValue().toString());
				int prob2=Integer.parseInt(probabilidad2.getValue().toString());
				if((prob1+prob2)!=100) {
					JOptionPane.showMessageDialog(null, "Las probabilidades deben sumar 100", "Error",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				//Game
				if(saque1.isSelected()) {
					saque=jugador1;
				}
				if(saque2.isSelected()) {
					saque=jugador2;
				}
				
				if(radio5.isSelected()) {
					sets=5;
				}
				if(radio3.isSelected()) {
					sets=3;
				}	
				Partido part=new Partido();
				part.jugador1=jugador1;
				part.jugador2=jugador2;
				part.prob1=prob1;
				part.prob2=prob2;
				part.saque=saque;
				part.sets=sets;
				part.torneo=torneo;
				dispose();
				Tablero tablero=new Tablero(part);
				tablero.setVisible(true);

			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBounds(10, 261, 167, 23);
		contentPane.add(btnNewButton);
		
	}

}
