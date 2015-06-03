package client_layer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class Lista_sprintow_form extends JPanel implements ActionListener {

    JTable table;
    JTable tabelaStanow;
    int row;
    MyTableModel model, modelStanow;
    JComboBox bProjekt;
    JLabel lstany = new JLabel("Stany:");
    //JComboBox stany= new JComboBox();
    int licz;
    public Lista_sprintow_form() {
        super();
        row = -1;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        licz=0;
        JLabel lProjekt = new JLabel("Kierownik");
        add(lProjekt);
        bProjekt = new JComboBox();
        add(bProjekt);
        
        model = new MyTableModel();
        table = new JTable(model);
       
        //table.getColumn("Nazwa projektu").setMinWidth(175);
        //table.getColumn("Kierownik projektu").setMinWidth(175);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new RowListener());
        table_content();
        add(new JScrollPane(table));
        
        add(lstany);
        String[] nazwyKolumn = { "Nr dnia", "N", "A", "I", "T", "Z","Data"};
        modelStanow = new MyTableModel();
        modelStanow.setColumnNames(nazwyKolumn);
        tabelaStanow = new JTable(modelStanow);
        
        tabelaStanow.setPreferredScrollableViewportSize(new Dimension(300, 100));
        tabelaStanow.setFillsViewportHeight(true);        
        tabelaStanow_content();
        add(new JScrollPane(tabelaStanow));
    }
    
    public void init() {
            Utility.initComboBox(bProjekt,Client.getFasada().pobierzTabliceKierownikow());

            table_content();
            tabelaStanow_content();    
    }
    
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (licz % 2 == 0) {
                table_content();
            }
            table.repaint();
            tabelaStanow_content();
            tabelaStanow.repaint();
            licz++;
            table.repaint();
         }

    
    void table_content() {
        Object[][] projekty = Client.getFasada().modelSprinty((String)bProjekt.getSelectedItem());
        model.setData(projekty);
    }
    
    void tabelaStanow_content() {
        if (table.getSelectionModel() != null && row >=0)
        {
            String[] dataSprint = new String[2];
            dataSprint[0] = (String)table.getValueAt(row, 0);   // nr sprintu
            dataSprint[1] = (String)table.getValueAt(row, 3);   // status sprintu
                
            Object[][] stany = Client.getFasada().modelStanySprintu(dataSprint, (String)bProjekt.getSelectedItem());
            modelStanow.setData(stany);
        }
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
            tabelaStanow_content();
            tabelaStanow.repaint();
        }
    }
    
    
     class MyTableModel extends AbstractTableModel {

        private String[] columnNames = {
            "Numer sprintu",
            "Data rozpoczecia",
            "Data zakonczenia",
            "Status",
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
            if (data == null)
                return 0;
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
        
        public void setColumnNames (String[] columnNames)
        {
            this.columnNames = columnNames;
        }
    }
}
