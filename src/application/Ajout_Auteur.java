package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ajout_Auteur extends JFrame {

	private JPanel contentPane;
	static JTextField tfNom;
	static JTextField tfPrenom;
	static JTextField tfNationalite;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajout_Auteur frame = new Ajout_Auteur();
					frame.setVisible(true);
					Dao.connection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ajout_Auteur() {
		setTitle("Ajout auteur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom : ");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNom.setBounds(35, 28, 56, 16);
		contentPane.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom : ");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrenom.setBounds(35, 77, 87, 16);
		contentPane.add(lblPrenom);
		
		JLabel lblNationalit = new JLabel("Nationalit\u00E9 : ");
		lblNationalit.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNationalit.setBounds(35, 125, 87, 16);
		contentPane.add(lblNationalit);
		
		tfNom = new JTextField();
		tfNom.setBounds(134, 26, 169, 22);
		contentPane.add(tfNom);
		tfNom.setColumns(10);
		
		tfPrenom = new JTextField();
		tfPrenom.setColumns(10);
		tfPrenom.setBounds(134, 75, 169, 22);
		contentPane.add(tfPrenom);
		
		tfNationalite = new JTextField();
		tfNationalite.setColumns(10);
		tfNationalite.setBounds(134, 123, 169, 22);
		contentPane.add(tfNationalite);
		
		JButton btnajouter = new JButton("<html><u>A</u>jouter</html>");
		btnajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dao.ajouterAuteur2();
				Dao.Auteurs.clear();
				DefaultListModel<String> DLM = new DefaultListModel<String>();
				Dao.selectAuteurs2();
				for (int i=0;i<Dao.Auteurs.size();i++) {
					DLM.addElement(Dao.Auteurs.get(i));
				}
				Interface.listAuteurs.setModel(DLM);
				Dao.fermetureConnection();
				dispose();
			}
		});
		btnajouter.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnajouter.setBounds(28, 177, 169, 25);
		contentPane.add(btnajouter);
		
		JButton btnquiter = new JButton("<html><u>A</u>nnuler</html>");
		btnquiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dao.fermetureConnection();
				dispose();
			}
		});
		btnquiter.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnquiter.setBounds(235, 177, 169, 25);
		contentPane.add(btnquiter);
	}
}
