import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Login1 extends JPanel {
	private JTextField textField;
	
	static loginFileOperation loginfileOperation1 = new loginFileOperation("login123.txt");
	
	ArrayList<loginInfo> allLoginData;
	 int c=0;
	 static Login1 login = new Login1();
	 static String borrowed=null;
	 static String borrowed1=null;
	/**
	 * Create the panel.
	 */
	 private static int a;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public Login1() {
		setBackground(Color.ORANGE);
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(574, 98, 158, 39);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(401, 173, 174, 38);
		add(lblNewLabel);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Name = textField.getText().toString();
				String Pass = textField_1.getText().toString();
				loginfileOperation1 = new loginFileOperation("login123.txt");
				allLoginData = loginfileOperation1.getLogin();
				boolean s=false;
				for (loginInfo lg : allLoginData) {
				if (lg.getName().equals(Name) && lg.getName().equals(Pass)) {

					JOptionPane.showMessageDialog(null, "You are sucessfully logged in");
					s=true;
				}}
				if(s==false) JOptionPane.showMessageDialog(null, "Wrong username or password");

			    borrowed=Name;
			    borrowed1=Pass;
			//	Login1 w=new Login1();
			    c();
				b();
				textField.setText("");
				textField_1.setText("");
				
			}
		});
		btnLogIn.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogIn.setBounds(496, 269, 122, 39);
		add(btnLogIn);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(574, 177, 158, 39);
		add(textField_1);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblUserName.setBounds(401, 99, 146, 38);
		add(lblUserName);

	}
	public static String c()
	{
		return borrowed1;
		
	}
	public static String b()
	{
		return borrowed;
		
	}
}
