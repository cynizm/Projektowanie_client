package client.layer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

public class Lista_projektow_form extends JPanel implements ActionListener {

    JTable table;
    int row;
    MyTableModel model;
    
    public Lista_projektow_form() {
        super();
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel lProjekt = new JLabel("Projekt");
        add(lProjekt);
        
        model = new MyTableModel();
        table = new JTable(model);
       
        table.getColumn("Nazwa projektu").setMinWidth(175);
        table.getColumn("Kierownik projektu").setMinWidth(175);
        table.getColumn("Klient projektu").setMinWidth(175);
        table.setPreferredScrollableViewportSize(new Dimension(700, 100));
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new RowListener());
        table_content();
        add(new JScrollPane(table));
        
        JButton button1 = new JButton("Przypisz osobę do projektu");
        button1.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e)
            {   
                int rzad = table.getSelectedRow();     
                if(rzad==-1){
                    JOptionPane.showMessageDialog(null, "Nie wybrano projektu!");
                }else{
                    DodajOsobeDoProjektu_form d1 = new DodajOsobeDoProjektu_form(
                        (String)table.getModel().getValueAt(rzad, 1),
                        (String)table.getModel().getValueAt(rzad, 0)
                    );
                    d1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    d1.setVisible(true);
                }
            }
        }); 
        JButton button2 = new JButton("Dodaj zadanie do projektu");
        button2.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e)
            {   
                int rzad = table.getSelectedRow();     
                if (rzad==-1)
                    JOptionPane.showMessageDialog(null, "Nie wybrano projektu!");
                else {
                    DodajZadanieDoProjektu_form d1 = new DodajZadanieDoProjektu_form((String)table.getModel().getValueAt(rzad, 1));
                    d1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    d1.setVisible(true);
                }
            }
        });
        JButton button3 = new JButton("Pokaż listę osób przypisanych do projektu");
        
        button3.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e)
            {   
                int rzad = table.getSelectedRow();     
                if(rzad==-1){
                    JOptionPane.showMessageDialog(null, "Nie wybrano projektu");
                }else{
                PokazZespol_form zespol = new PokazZespol_form(
                        (String)table.getModel().getValueAt(rzad, 1),
                        (String)table.getModel().getValueAt(rzad, 0)
                );
                zespol.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                zespol.setVisible(true);
                }
            }
        });
        
        JButton button4 = new JButton("Pokaż listę zadań dodanych do projektu");
        
        button4.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e)
            {   
                int rzad = table.getSelectedRow();     
                if (rzad==-1)
                    JOptionPane.showMessageDialog(null, "Nie wybrano projektu!");
                else {
                    PokazZadania_form zadania = new PokazZadania_form(
                            (String)table.getModel().getValueAt(rzad, 1),
                            (String)table.getModel().getValueAt(rzad, 0)
                    );
                    zadania.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    zadania.setVisible(true);
                }
            }
        });
        
        add(button1);
        add(button2);
        add(button3);
        add(button4);

    }
    
    public void init() {
        table_content();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        table_content();
    }
    
    void table_content() {
        Object[][] projekty = Client.getFasada().modelProjekty();
        model.setData(projekty);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        // pusta metoda
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
            "Nazwa projektu",
            "Kierownik projektu",
            "Klient projektu",
            "Data rozpoczecia",
            "Data zakonczenia",
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
