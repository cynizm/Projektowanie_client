
package client.layer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class ListaRyzyk_form extends JPanel implements ActionListener {

	JTable table;
	int row;
	MyTableModel model;
	JComboBox bProjekt;
        int licz;
	public ListaRyzyk_form() {
		super();

                this.licz = 1;
                
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel lProjekt = new JLabel("Kierownik");
		add(lProjekt);
		bProjekt = new JComboBox(Client.getFasada().pobierzTabliceProjektow());
		add(bProjekt);

		model = new MyTableModel();
		table = new JTable(model);

		table.getColumn("Data wystapienia").setMinWidth(185);
		table.getColumn("Data zakonczenia").setMinWidth(175);
		table.getColumn("Prwd wystapienia").setMinWidth(20);
		table.setPreferredScrollableViewportSize(new Dimension(700, 100));
		table.setFillsViewportHeight(true);
		table.getSelectionModel().addListSelectionListener(new RowListener());

		add(new JScrollPane(table));
	}

        public void init() {
            
            Utility.initComboBox(bProjekt, Client.getFasada().pobierzTabliceKierownikow());
            table_content();
        }

	@Override
	public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            if (licz % 2 == 0) {
                table_content();  
            }
            licz++;
            table.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
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

	void table_content() {
		Object[][] titles = Client.getFasada().modelRisks((String)bProjekt.getSelectedItem());
                
		model.setData(titles);
                
	}

	class MyTableModel extends AbstractTableModel {

		private final String[] columnNames = {"Nazwa",
			"Opis",
			"Prwd wystapienia",
			"Koszt",
			"Data wystapienia",
			"Data zakonczenia",
			"Aktywne"};

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
