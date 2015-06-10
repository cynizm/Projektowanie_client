package client.layer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class ListaOsob_form extends JPanel implements ActionListener {

	Client client;
	JTable table;
	MyTableModel model;
	int row;
	JLabel lemail = new JLabel("E-mail");
	JTextField email = new JTextField(10);
	JButton szukaj_osobe = new JButton("Szukaj");
	ListaOsob_form thisClass = this;

	public ListaOsob_form(Client client) {
		super();
		//this.client = client;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel lnumber = new JLabel("Lista dodanych osób");

		add(lnumber);
		model = new MyTableModel();
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500, 100));
		table.setFillsViewportHeight(true);
		table.getSelectionModel().addListSelectionListener(new RowListener());
		table_content();
		add(new JScrollPane(table));
		add(lemail);
		add(email);
		szukaj_osobe.addActionListener(thisClass);
		add(szukaj_osobe);
	}

	public void init() {
		table_content();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		table_content();
	}

	private void table_content() {
		Object[][] osoby = Client.getFasada().modelTablicaZDanymiOsob();
		model.setData(osoby);
	}

	public String content_validate(JTextField val) {
		String s = val.getText();
		if (s.equals("") || s.length() > 30) {
			JOptionPane.showMessageDialog(this, "Nie wprowadzono danych.\nNie można wykonać operacji.");
			return null;
		} else {
			val.setText(s);
			return s;
		}
	}

	public String[] validateForm() {
		if (content_validate(email) == null) {
			return null;
		}
		String data[] = {
			"0",
			(String) email.getText()
		};
		return data;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String[] data = validateForm();
		if (data == null) {
			return;
		}
                String dane = Client.getFasada().szukajOsobe(data);
		if (dane != null) {
			JOptionPane.showMessageDialog(this, "Osoba o podanym adresie e-mail istnieje w systemie.\n" + Client.getFasada().szukajOsobe(data));
		} else {
			JOptionPane.showMessageDialog(this, "Brak szukanej osoby.");
		}
	}

	private class RowListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				return;
			}
			row = table.getSelectionModel().getLeadSelectionIndex();
		}

	}

	class MyTableModel extends AbstractTableModel {

		private final String[] columnNames = {
			"Imię",
			"Nazwisko",
			"E-mail",
			"Rola"};
		private Object[][] data;

		public void setData(Object[][] val) {
			data = val;
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			return data.length;
		}

		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}

		@Override
		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		@Override
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			return col < 0;
		}

		@Override
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
	}
}
