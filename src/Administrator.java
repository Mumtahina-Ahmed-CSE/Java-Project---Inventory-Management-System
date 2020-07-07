import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;

public class Administrator extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private loginFileOperation loginfileOperation;
	ArrayList<loginInfo> allLoginData=new ArrayList<>();
	/**
	 * Create the panel.
	 */
	public Administrator() {
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBackground(Color.BLACK);
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUserName.setBounds(244, 115, 144, 32);
		add(lblUserName);
		
		textField = new JTextField();
		textField.setBounds(399, 115, 202, 32);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPassword.setBounds(244, 202, 107, 32);
		add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(399, 208, 202, 32);
		add(textField_1);
		textField_1.setColumns(10);
		loginfileOperation = new loginFileOperation("login123.txt");
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Name = textField.getText().toString();
				String Pass = textField_1.getText().toString();
				textField.setText("");
				textField_1.setText("");
				saveLoginData(Name,Pass);
				//JOptionPane.showMessageDialog(null, "Please restart the application");
			}
		});
		btnSignUp.setBounds(534, 301, 89, 23);
		add(btnSignUp);

	}
	private void saveLoginData(String name1,String password1)
	{
		loginInfo info=new loginInfo(name1,password1);
		allLoginData.add(info);
		loginfileOperation.addlginInfo(info);
	}
}
