package view;
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


public class View extends JFrame 
{
	public JPanel contentPane;
	
	public static JTextField word_input;
	public JLabel suggest = new JLabel("word");
	public JPanel getContentPane() 
	{
		return contentPane;
	}

	public static JTextField getWord_input() 
	{
		return word_input;
	}

	public static JTextArea getTextArea() {
		return textArea;
	}

//	public JLabel getLblNewLabel_1() {
//		return lblNewLabel_1;
//	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}
	
	public JButton getBtnNewButton() 
	{
		return  btnNewButton;
	}
	


	public static JTextArea textArea = new JTextArea();
	JLabel lblNewLabel = new JLabel("TEXT:");
	JButton btnNewButton = new JButton("CHECK");
	public JTable table;
	
	

	/**
	 * Launch the application.
	 */
//	//main start
//		public static void main(String[] args) 
//		{
//			EventQueue.invokeLater(new Runnable() {
//				public void run() 
//				{
//					try {
//						View frame = new View();
//						frame.setVisible(true);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			});
//		}
//	// main end

	/**
	 * Create the frame.
	 */
		
		
		
		
	public View()  //constructor
	{
		
		//action listner
		btnNewButton.addActionListener(new ActionListener() 
		{
		 	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				
			}
		 });
		
		
		//
		
		setBackground(new Color(102, 51, 153));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\usman\\Downloads\\Best-Milky-Way-photo-Tenerife.jpg"));// file icon
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
		
		
		JLabel input_label = new JLabel("Enter Text");
		input_label.setFont(new Font("Arial", Font.PLAIN, 17));
		input_label.setBounds(37, 43, 77, 14);
		panel.add(input_label);
		
		word_input = new JTextField();
		word_input.setBounds(152, 40, 191, 20);
		panel.add(word_input);
		word_input.setColumns(10);
		
		
		btnNewButton.setBackground(new Color(128, 255, 255));
		btnNewButton.setForeground(new Color(0, 255, 0));
		
		
		btnNewButton.setBounds(208, 294, 135, 23);
		panel.add(btnNewButton);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		
		
		lblNewLabel.setBounds(297, 71, 58, 38);
		panel.add(lblNewLabel);
		textArea.setFont(new Font("Arial", Font.PLAIN, 18));
		
		
		textArea.setBounds(355, 73, 237, 74);
		panel.add(textArea);
		
		JLabel lblNewLabel_2 = new JLabel("اردو سپیل چیکر");
		lblNewLabel_2.setForeground(new Color(102, 51, 255));
		lblNewLabel_2.setBackground(new Color(204, 255, 153));
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_2.setBounds(230, 11, 113, 23);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("do you mean?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_1.setBounds(355, 158, 104, 14);
		panel.add(lblNewLabel_1);
		suggest.setFont(new Font("Arial", Font.PLAIN, 17));
		
		
		suggest.setBounds(355, 183, 58, 14);
		panel.add(suggest);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 90, 274, 195);
		panel.add(scrollPane);
		
		table = new JTable();

		table.setFont(new Font("Arial", Font.PLAIN, 18));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Suggestions"
			}
		));
		scrollPane.setViewportView(table);
		
		//
	}
}
