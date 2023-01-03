package presentationLayer;

import java.util.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewTypos extends JFrame {
	public JPanel contentPane;

	public static JTextField inputWord;
	public JLabel suggest = new JLabel("word");

	public JPanel getContentPane() {
		return contentPane;
	}

	public static JTextField getWordInput() {
		return inputWord;
	}

	public static JTextArea getTextArea() {
		return textArea;
	}

//	public JLabel getLblNewLabel_1() {
//		return lblNewLabel_1;
//	}

	public JLabel getLblNewLabel() {
		return text;
	}

	public JButton getCheckButton() {
		return checkButton;
	}

	public static JTextArea textArea = new JTextArea();
	JLabel text = new JLabel("TEXT:");
	JButton checkButton = new JButton("CHECK");
	public JTable table;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	public ViewTypos() // constructor
	{

		// action listner
		checkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		//

		setBackground(new Color(102, 51, 153));
		setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\usman\\Downloads\\Best-Milky-Way-photo-Tenerife.jpg"));// file
																														// icon
		setTitle("Spell Checker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(175, 238, 238));
		panel.setBounds(10, 11, 602, 328);
		contentPane.add(panel);

		panel.setLayout(null);

		JLabel inputTextString = new JLabel("Enter Text");
		inputTextString.setFont(new Font("Arial", Font.PLAIN, 17));
		inputTextString.setBounds(37, 43, 77, 14);
		panel.add(inputTextString);

		inputWord = new JTextField();
		inputWord.setBounds(152, 40, 191, 20);
		panel.add(inputWord);
		inputWord.setColumns(10);

		checkButton.setBackground(new Color(128, 255, 255));
		checkButton.setForeground(new Color(0, 255, 0));

		checkButton.setBounds(208, 294, 135, 23);
		panel.add(checkButton);
		text.setFont(new Font("Arial", Font.PLAIN, 15));

		text.setBounds(297, 71, 58, 38);
		panel.add(text);
		textArea.setFont(new Font("Arial", Font.PLAIN, 18));

		textArea.setBounds(355, 73, 237, 74);
		panel.add(textArea);

		JLabel title = new JLabel("اردو سپیل چیکر");
		title.setForeground(new Color(102, 51, 255));
		title.setBackground(new Color(204, 255, 153));
		title.setFont(new Font("SansSerif", Font.BOLD, 18));
		title.setBounds(230, 11, 113, 23);
		panel.add(title);

		JLabel meanLabel = new JLabel("do you mean?");
		meanLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		meanLabel.setBounds(355, 158, 104, 14);
		panel.add(meanLabel);
		suggest.setFont(new Font("Arial", Font.PLAIN, 17));

		suggest.setBounds(355, 183, 58, 14);
		panel.add(suggest);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 90, 274, 195);
		panel.add(scrollPane);

		table = new JTable();

		table.setFont(new Font("Arial", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Suggestions" }));
		scrollPane.setViewportView(table);

		//
	}
}
