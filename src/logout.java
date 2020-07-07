import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;

public class logout extends JPanel {

	/**
	 * Create the panel.
	 */
	public logout() {
		setBackground(Color.ORANGE);
		setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(225, 5, 0, 0);
		add(label);
		
		JLabel lblNewLabel = new JLabel("Thanks for Using !");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 50));
		lblNewLabel.setBounds(10, 99, 440, 75);
		add(lblNewLabel);

	}

}
