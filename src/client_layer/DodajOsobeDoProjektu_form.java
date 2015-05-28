
package client_layer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class DodajOsobeDoProjektu_form extends javax.swing.JFrame implements ActionListener  {

    JTable table;
    MyTableModel model;
    String kierownik;
    String nazwa;
    JLabel lkierownik;
    JLabel lnazwa; 
    int row;
    JButton dodaj_osobe = new JButton("Dodaj osobę");

    public DodajOsobeDoProjektu_form(String kierownik_projektu, String nazwa_projektu) {
        kierownik = kierownik_projektu;
        nazwa = nazwa_projektu;
        lnazwa = new JLabel("Nazwa projektu: " + nazwa); 
        lkierownik = new JLabel("Kierownik: " + kierownik);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(700, 400);
        setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        setTitle("Dodaj osobę do projektu");
        add(lnazwa);
        add(lkierownik);
        model = new MyTableModel();
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new RowListener());
        model.setData(Client.getFasada().modelTablicaZDanymiOsob());
        add(new JScrollPane(table));
        dodaj_osobe.addActionListener(this);
        add(dodaj_osobe);
        setVisible(true); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int rzad = table.getSelectedRow();
        if (rzad==-1)
            JOptionPane.showMessageDialog(null, "Nie wybrano osoby!");
        else {
            String temp = Client.getFasada().dodajOsobeDoProjektu(kierownik,(String)table.getModel().getValueAt(rzad, 2));
            JOptionPane.showMessageDialog(null, temp);
            if ("Dodano osobę do projektu.".equals(temp))
                this.dispose();
        }
    }
    
    private class RowListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting())
                return;
            row = table.getSelectionModel().getLeadSelectionIndex();
        }
    }
    
    class MyTableModel extends AbstractTableModel {

        private final String[] columnNames = {
            "Imię", "Nazwisko", "E-mail", "Projekt", "Rola"
        };
        
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