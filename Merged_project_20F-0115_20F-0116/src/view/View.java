package view;

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
	private JTextField textField;
	private JButton btnNewButton;

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
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

		JLabel lblNewLabel = new JLabel("مقام شامل کریں");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(37, 128, 90, 28);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(137, 134, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		btnNewButton = new JButton("براؤز کریں۔");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBackground(new Color(128, 255, 255));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 16));

		btnNewButton.setBounds(264, 132, 90, 21);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("براہ کرم ڈائریکٹری منتخب کریں۔");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(89, 78, 203, 28);
		contentPane.add(lblNewLabel_1);
	}

}