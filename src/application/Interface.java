package application;
import java.awt.Button;
import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ListSelectionModel;

public class Interface extends JFrame {

	
	private JPanel contentPane;
	static JTextField tfIsbn;
	static JTextField tfTitre;
	static JTextField tfEditeur;
	static JTextField tfAnnee;
	static JTextField choix;
	static Choice choiceAuteur;
	static JList<String> listAuteurs;



	/**
	 * Launch the application.
//	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
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
	public Interface() {
		setTitle("biblioth\u00E8que");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnNew = new JMenu("New");
		mnFile.add(mnNew);
		
		JMenuItem mntmAuteur = new JMenuItem("Auteur");
		mnNew.add(mntmAuteur);
		
		JMenuItem mntmLivre = new JMenuItem("Livre");
		mnNew.add(mntmLivre);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
//////////////////////////////////////////////////////////////////Auteurs	
		
		
		JLabel lblAuteurs = new JLabel("Auteurs :");
		lblAuteurs.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAuteurs.setBounds(115, 13, 110, 16);
		contentPane.add(lblAuteurs);
			
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 69, 259, 180);
		contentPane.add(scrollPane);
		
		listAuteurs = new JList<String>();
		listAuteurs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listAuteurs);
		listAuteurs.setVisibleRowCount(20);
		Dao.selectAuteurs2();
		for (int i=0;i<Dao.Auteurs.size();i++) {
			listAuteurs.setListData(Dao.Auteurs);
		}
		
		
//////////////////////////////////////////////////////////////////livres	
		
		
		JLabel lblLivres = new JLabel("Livres :");
		lblLivres.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLivres.setBounds(440, 13, 101, 16);
		contentPane.add(lblLivres);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(341, 69, 268, 180);
		contentPane.add(scrollPane_1);
		
		
		JList<String> listLivres = new JList<String>();
		listLivres.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(listLivres);
		listLivres.setVisibleRowCount(20);
		
		
//////////////////////////////////////////////////////////////////		
		
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(97, 281, 444, 6);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(97, 339, 444, 6);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(97, 399, 444, 6);
		contentPane.add(separator_3);
		
		JLabel lblSupprimerLeLivre = new JLabel("Supprimer le livre selectionn\u00E9 : ");
		lblSupprimerLeLivre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSupprimerLeLivre.setBounds(97, 300, 268, 16);
		contentPane.add(lblSupprimerLeLivre);
		
		JButton btnsupprimer = new JButton("<html><u>S</u>upprimer</html>");
		btnsupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 JOptionPane jop = new JOptionPane();    	
			      int option = jop.showConfirmDialog(null, 
			        "Voulez-vous vraiment supprimer ce livre?", 
			        "Suppression", 
			        JOptionPane.YES_NO_OPTION, 
			        JOptionPane.QUESTION_MESSAGE);

			      if(option == JOptionPane.OK_OPTION){
				
				Dao.supprimerLivre2(listLivres.getSelectedValue());
				Dao.Livres.clear();
				DefaultListModel<String> DLM = new DefaultListModel<String>();
				Dao.selectLivresParAuteur2(listAuteurs .getSelectedValue());
				for (int i=0;i<Dao.Livres.size();i++) {
					DLM.addElement(Dao.Livres.get(i));
				}
				listLivres.setModel(DLM);
			      }
				
			}
		});
		btnsupprimer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnsupprimer.setBounds(377, 297, 135, 25);
		contentPane.add(btnsupprimer);
		
		JLabel lblajouterAuteur = new JLabel("Ajouter auteur");
		lblajouterAuteur.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblajouterAuteur.setBounds(97, 358, 268, 16);
		contentPane.add(lblajouterAuteur);
		
		JLabel lblIsbn = new JLabel("ISBN :");
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIsbn.setBounds(97, 418, 91, 16);
		contentPane.add(lblIsbn);
		
		JLabel lblTitre = new JLabel("Titre : ");
		lblTitre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitre.setBounds(97, 464, 91, 16);
		contentPane.add(lblTitre);
		
		JLabel lblEditeur = new JLabel("Editeur :");
		lblEditeur.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEditeur.setBounds(97, 509, 91, 16);
		contentPane.add(lblEditeur);
		
		JLabel lblAnne = new JLabel("Ann\u00E9e :");
		lblAnne.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAnne.setBounds(97, 548, 91, 16);
		contentPane.add(lblAnne);
		
		tfIsbn = new JTextField();
		tfIsbn.setBounds(200, 415, 170, 22);
		tfIsbn.setColumns(10);
		contentPane.add(tfIsbn);
		
		
		tfTitre = new JTextField();
		tfTitre.setBounds(200, 462, 170, 22);
		tfTitre.setColumns(10);
		contentPane.add(tfTitre);
		
		tfEditeur = new JTextField();
		tfEditeur.setBounds(200, 507, 170, 22);
		tfEditeur.setColumns(10);
		contentPane.add(tfEditeur);
		
		tfAnnee = new JTextField();
		tfAnnee.setBounds(200, 546, 90, 22);
		tfAnnee.setColumns(10);
		contentPane.add(tfAnnee);
		
		JButton btnajouterLivre = new JButton("<html><u>A</u>jouter</html>");
		btnajouterLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dao.ajouterLivreDunAuteur2();
				
				
			}
		});
		btnajouterLivre.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnajouterLivre.setBounds(377, 461, 135, 25);
		contentPane.add(btnajouterLivre);
		
		choiceAuteur = new Choice();
		choiceAuteur.setBounds(377, 416, 135, 22);
		contentPane.add(choiceAuteur);
		Dao.selectAuteurs2();
		for (int i=0;i<Dao.Auteurs.size();i++) {
			choiceAuteur.add(Dao.Auteurs.get(i));
		}
		
		JButton bntQuitter = new JButton("<html><u>Q</u>uitter</html>");
		bntQuitter.setFont(new Font("Tahoma", Font.BOLD, 13));
		bntQuitter.setBounds(377, 545, 135, 25);
		contentPane.add(bntQuitter);
		
		Button button = new Button("Refresh");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dao.Livres.clear();
				DefaultListModel<String> DLM = new DefaultListModel<String>();
				Dao.selectLivresParAuteur2(listAuteurs .getSelectedValue());
				for (int i=0;i<Dao.Livres.size();i++) {
					DLM.addElement(Dao.Livres.get(i));
				}
				listLivres.setModel(DLM);
				
			}
		});
		
		JButton btnajouterAuteur = new JButton("<html><u>A</u>jouter</html>");
		btnajouterAuteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Ajout_Auteur frame2 = new Ajout_Auteur();
				frame2.setVisible(true);

			}
		});
		btnajouterAuteur.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnajouterAuteur.setBounds(377, 358, 135, 25);
		contentPane.add(btnajouterAuteur);
		
		
		button.setFont(new Font("Dialog", Font.PLAIN, 10));
		button.setBounds(293, 142, 42, 40);
		contentPane.add(button);

		bntQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dao.fermetureConnection();
				System.exit(0);
			}
		});
		
		
	}
}
