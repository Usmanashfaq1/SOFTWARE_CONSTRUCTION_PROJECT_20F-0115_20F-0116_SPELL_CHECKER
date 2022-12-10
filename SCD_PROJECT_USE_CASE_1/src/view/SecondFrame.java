package view;

import tranferObjects.*;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class SecondFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	public JTable getTable() {
		return table;
	}

	private JTextField textField;

	public JTextField gettTextField() {
		return textField;
	}

	private int indexRowSelected = -1;

	public int getIndexRowSelected() {
		return indexRowSelected;
	}

	public void setIndexRowSelected(int indexRowSelected) {
		this.indexRowSelected = indexRowSelected;
	}

	public String getTextField() {
		return textField.getText().toString() + " ";
	}

	private JButton btnNewButton;
	private JButton btnNewButton_1;

	public JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public TableModel getTableModel() {
		return table.getModel();
	}
	public void setTableData(ArrayList<TransferObject> alTO)
	{
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Word ID", "Frequency", "Word" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}
		});
		DefaultTableModel model = null;
		for (TransferObject t : alTO) {

			 model = (DefaultTableModel) table.getModel();
			model.addRow(new Object[] { t.getWordId(), t.getFrequency(), t.getWord() });
		}
		table.setModel(model);
		scrollPane.setViewportView(table);

	}
	public SecondFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		 scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 68, 341, 185);
		contentPane.add(scrollPane);

		table = new JTable();

		
		table.addMouseListener(new MouseAdapter() {

		});
		//scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("لفظ شامل کریں۔");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel.setBounds(251, 2, 83, 28);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(145, 7, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		btnNewButton = new JButton("اپ ڈیٹ کریں۔");
		btnNewButton.setBackground(new Color(255, 255, 128));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(27, 6, 102, 21);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("حذف کریں۔");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBackground(new Color(255, 60, 60));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton_1.setBounds(143, 37, 85, 21);
		contentPane.add(btnNewButton_1);
		
	}


}
