import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JMenu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

public class InventoryManagement {

	private JFrame frame;
	static logout l = new logout();

	static SearchPanel rrr = new SearchPanel();
	static Administrator admin = new Administrator();
	static Login1 login = new Login1();
	
	ArrayList<loginInfo> allLoginData;
	static loginFileOperation loginfileOperation1 = new loginFileOperation("login123.txt");
	boolean t;
	boolean p = true;
	static InventoryToolsPanel inventoryToolsPanel = new InventoryToolsPanel();
	static SearchPanel searchPanel = new SearchPanel();
	static OverView overviewpanel = new OverView();

	CardLayout appLayout;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryManagement window = new InventoryManagement(args);

					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InventoryManagement(String[] args) {
		initialize(args);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] args) {
		frame = new JFrame();
		frame.setTitle("Inventory Management");
		frame.setBounds(0, 0, 1368, 689);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		inventoryToolsPanel.setBackground(Color.GRAY);
		frame.getContentPane().add(inventoryToolsPanel, "name");
		frame.getContentPane().add(searchPanel, "name_2");
		frame.getContentPane().add(overviewpanel, "name_3");

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		

		JMenu mnAdministrator = new JMenu("Administrator");
		mnAdministrator.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				appLayout = (CardLayout) frame.getContentPane().getLayout();
				appLayout.show(frame.getContentPane(), "Admin");

			}
		});
		menuBar.add(mnAdministrator);

		JMenu mnLogIn = new JMenu("Login");
		mnLogIn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				loginfileOperation1 = new loginFileOperation("login123.txt");
				System.out.println("Log in called!");

				appLayout = (CardLayout) frame.getContentPane().getLayout();
				appLayout.show(frame.getContentPane(), "login");

			}
		});
		menuBar.add(mnLogIn);

		

		allLoginData = loginfileOperation1.getLogin();

		JMenu mnOperation = new JMenu("InventoryToolsPanel");
		mnOperation.setEnabled(false);
		mnOperation.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				loginfileOperation1 = new loginFileOperation("login123.txt");
				allLoginData = loginfileOperation1.getLogin();
				System.out.println(allLoginData.size() + "");
				for (loginInfo lg : allLoginData) {

					if (lg.getName().equals(Login1.b()) && lg.getName().equals(Login1.c())) {

						JOptionPane.showMessageDialog(null, "You are sucessfully logged in");
						t = true;
						p = true;
						break;
					}

				}
				if (t == false)
					JOptionPane.showMessageDialog(null, "Please log in to get access");
				if (t == true && p == true) {
					mnOperation.setEnabled(true);
					appLayout = (CardLayout) frame.getContentPane().getLayout();
					appLayout.show(frame.getContentPane(), "InventoryToolsPanel");
				}
			}
		});
		menuBar.add(mnOperation);

		JMenu mnQA = new JMenu("Search");
		mnQA.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				appLayout = (CardLayout) frame.getContentPane().getLayout();
				appLayout.show(frame.getContentPane(), "SearchPanel");

			}
		});
		menuBar.add(mnQA);

		JMenu overview = new JMenu("Overview");
		overview.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				appLayout = (CardLayout) frame.getContentPane().getLayout();
				appLayout.show(frame.getContentPane(), "Overview");

			}
		});
		menuBar.add(overview);
		

		JMenu mnLogout = new JMenu("Logout");
		mnLogout.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				p = false;
				mnOperation.setEnabled(false);
				Login1.borrowed = null;
				Login1.borrowed1 = null;
				appLayout = (CardLayout) frame.getContentPane().getLayout();
				appLayout.show(frame.getContentPane(), "logout");

			}
		});
		menuBar.add(mnLogout);


		JPanel l = new JPanel();
		frame.getContentPane().add(admin, "Admin");
		frame.getContentPane().add(login, "login");
		frame.getContentPane().add(inventoryToolsPanel, "InventoryToolsPanel");
		frame.getContentPane().add(searchPanel, "SearchPanel");
		frame.getContentPane().add(overviewpanel, "Overview");
		frame.getContentPane().add(l, "logout");
		menuBar.setSelected(mnAdministrator);

	}

}
