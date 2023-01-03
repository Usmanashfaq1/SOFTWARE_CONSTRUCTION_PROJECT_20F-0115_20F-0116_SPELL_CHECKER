package presentationLayer;
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
	private JButton readDataButton;
	public JButton getBtnNewButton() {
		return readDataButton;
	}

	public JButton getBtnNewButton_1() {
		return deleteAndUpdateButton;
	}

	public JButton getBtnNewButton_2() {
		return suggestWordButton;
	}

	private JButton deleteAndUpdateButton;
	private JButton suggestWordButton;

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
		
		 readDataButton = new JButton("Read Data from XML");
		 readDataButton.setForeground(new Color(255, 255, 255));
		 readDataButton.setBackground(new Color(100, 149, 237));
		readDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		readDataButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		readDataButton.setBounds(166, 68, 293, 42);
		contentPane.add(readDataButton);
		
		 deleteAndUpdateButton = new JButton("Delete or Update Word");
		 deleteAndUpdateButton.setForeground(new Color(255, 255, 255));
		 deleteAndUpdateButton.setBackground(Color.RED);
		deleteAndUpdateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		deleteAndUpdateButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		deleteAndUpdateButton.setBounds(166, 139, 293, 42);
		contentPane.add(deleteAndUpdateButton);
		
		 suggestWordButton = new JButton("Suggest word according to Database");
		 suggestWordButton.setForeground(new Color(255, 255, 255));
		 suggestWordButton.setBackground(Color.MAGENTA);
		suggestWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		suggestWordButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		suggestWordButton.setBounds(166, 208, 293, 42);
		contentPane.add(suggestWordButton);
		
		JLabel welcomeLabel = new JLabel("Welcome to Urdu Spell Checker");
		welcomeLabel.setBackground(new Color(0, 191, 255));
		welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		welcomeLabel.setBounds(174, 11, 382, 47);
		contentPane.add(welcomeLabel);
		
		JPanel secondBottomPanel = new JPanel();
		secondBottomPanel.setBackground(new Color(0, 0, 255));
		secondBottomPanel.setBounds(42, 315, 514, 10);
		contentPane.add(secondBottomPanel);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.YELLOW);
		topPanel.setBounds(42, 11, 514, 10);
		contentPane.add(topPanel);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(0, 255, 0));
		leftPanel.setBounds(42, 11, 10, 314);
		contentPane.add(leftPanel);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(255, 0, 0));
		rightPanel.setBounds(551, 11, 10, 314);
		contentPane.add(rightPanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(0, 255, 255));
		bottomPanel.setBounds(42, 47, 514, 10);
		contentPane.add(bottomPanel);
	}
}