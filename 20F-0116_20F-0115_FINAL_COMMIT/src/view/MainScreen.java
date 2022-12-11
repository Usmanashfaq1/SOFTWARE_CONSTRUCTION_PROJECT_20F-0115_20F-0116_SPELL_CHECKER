package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}

	public JButton getBtnNewButton_2() {
		return btnNewButton_2;
	}

	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public MainScreen() {
		setTitle("URDU SPELL CHECKER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 btnNewButton = new JButton("XML فائل سے ڈیٹا بیس میں درآمد کریں۔");
		 btnNewButton.setForeground(new Color(255, 255, 255));
		 btnNewButton.setBackground(new Color(100, 149, 237));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton.setBounds(166, 68, 293, 42);
		contentPane.add(btnNewButton);
		
		 btnNewButton_1 = new JButton("ٹیبل دیکھیں اور لفظ کو حذف یا اپ ڈیٹ کریں۔");
		 btnNewButton_1.setForeground(new Color(255, 255, 255));
		 btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_1.setBounds(166, 139, 293, 42);
		contentPane.add(btnNewButton_1);
		
		 btnNewButton_2 = new JButton("ڈیٹا بیس کے لحاظ سے لفظ کے لیے تجویز دکھائیں۔");
		 btnNewButton_2.setForeground(new Color(255, 255, 255));
		 btnNewButton_2.setBackground(Color.MAGENTA);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnNewButton_2.setBounds(166, 208, 293, 42);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("ہمارے اردو سپیل چیکر میں خوش آمدید جناب");
		lblNewLabel.setBackground(new Color(0, 191, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(174, 11, 382, 47);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 255));
		panel.setBounds(42, 315, 514, 10);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.YELLOW);
		panel_1.setBounds(42, 11, 514, 10);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 255, 0));
		panel_2.setBounds(42, 11, 10, 314);
		contentPane.add(panel_2);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(255, 0, 0));
		panel_2_1.setBounds(551, 11, 10, 314);
		contentPane.add(panel_2_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 255, 255));
		panel_3.setBounds(42, 47, 514, 10);
		contentPane.add(panel_3);
	}
}