package presentationLayer;

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

	private JTextField wordTextField;

	public JTextField gettTextField() {
		return wordTextField;
	}

	private int indexRowSelected = -1;

	public int getIndexRowSelected() {
		return indexRowSelected;
	}

	public void setIndexRowSelected(int indexRowSelected) {
		this.indexRowSelected = indexRowSelected;
	}

	public String getTextField() {
		return wordTextField.getText().toString() + " ";
	}

	private JButton updateButton;
	private JButton deleteButton;

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public JButton getUpdateButton() {
		return updateButton;
	}

	public TableModel getTableModel() {
		return table.getModel();
	}
	public void setTableData(ArrayList<TransferObject> transferObjectArrayList)
	{
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Word ID", "Frequency", "Word" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}
		});
		DefaultTableModel model = null;
		for (TransferObject instanceTransferObject : transferObjectArrayList) {

			 model = (DefaultTableModel) table.getModel();
			model.addRow(new Object[] { instanceTransferObject.getWordId(), instanceTransferObject.getFrequency(), instanceTransferObject.getWord() });
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

		JLabel selectWordLabel = new JLabel("Select Word");
		selectWordLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		selectWordLabel.setBounds(251, 2, 83, 28);
		contentPane.add(selectWordLabel);

		wordTextField = new JTextField();
		wordTextField.setBounds(145, 7, 96, 19);
		contentPane.add(wordTextField);
		wordTextField.setColumns(10);

		updateButton = new JButton("Update");
		updateButton.setBackground(new Color(255, 255, 128));
		updateButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		updateButton.setBounds(27, 6, 102, 21);
		contentPane.add(updateButton);

		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deleteButton.setBackground(new Color(255, 60, 60));
		deleteButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		deleteButton.setBounds(143, 37, 85, 21);
		contentPane.add(deleteButton);
		
	}


}
