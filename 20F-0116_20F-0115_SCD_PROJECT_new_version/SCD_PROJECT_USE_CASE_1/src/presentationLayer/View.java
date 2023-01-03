package presentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

/**
 * 
 * @author maste
 *
 */
public class View extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField directoryTextField;
	private JButton browseButton;

	public JTextField getDirectoryTextField() {
		return directoryTextField;
	}

	public void setTextField(JTextField textField) {
		this.directoryTextField = textField;
	}

	public JButton getBrowseButton() {
		return browseButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.browseButton = btnNewButton;
	}

	/**
	 * 
	 * @param args
	 */

	public View() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\maste\\Documents\\hi.png"));
		setFont(new Font("Times New Roman", Font.BOLD, 20));
		setTitle("اردو سپیل چیکر");
		setResizable(false);
		setForeground(new Color(192, 192, 192));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 254);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel enterDirectoryLabel = new JLabel("Enter Location");
		enterDirectoryLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		enterDirectoryLabel.setBounds(10, 128, 117, 28);
		contentPane.add(enterDirectoryLabel);

		directoryTextField = new JTextField();
		directoryTextField.setBounds(137, 134, 96, 19);
		contentPane.add(directoryTextField);
		directoryTextField.setColumns(10);

		browseButton = new JButton("Browse");
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		browseButton.setBackground(new Color(128, 255, 255));
		browseButton.setFont(new Font("Times New Roman", Font.BOLD, 16));

		browseButton.setBounds(264, 132, 90, 21);
		contentPane.add(browseButton);

		JLabel chooseDirectoryLabel = new JLabel("Choose Directory");
		chooseDirectoryLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		chooseDirectoryLabel.setBounds(121, 77, 203, 28);
		contentPane.add(chooseDirectoryLabel);
	}

}