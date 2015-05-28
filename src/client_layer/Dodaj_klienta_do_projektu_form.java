package client_layer;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Dodaj_klienta_do_projektu_form extends JPanel implements ActionListener {
    
    JLabel lProjekty = new JLabel("Projekty");
    JComboBox cbProjekty = new JComboBox();
    
    JLabel lKlienci = new JLabel("Klienci");
    JComboBox cbKlienci = new JComboBox();
 
    JButton bDodajKlientaDoProjektu = new JButton("Dodaj klienta do projektu");
    
     Dodaj_klienta_do_projektu_form() 
     {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(lProjekty);
        add(cbProjekty);
        cbProjekty.setName("Projekty");

        add(lKlienci);
        add(cbKlienci);
        cbKlienci.setName("Klienci");
     
        add(bDodajKlientaDoProjektu);
        bDodajKlientaDoProjektu.addActionListener(this);
        
    }
    
    public void init() {
        cbProjekty.removeAllItems();
        for(Object r : Client.getFasada().pobierzTabliceProjektow())
            cbProjekty.addItem(r.toString());
        cbKlienci.removeAllItems();
        for(Object r : Client.getFasada().pobierzTabliceKlientow())
            cbKlienci.addItem(r.toString());
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(cbProjekty.getItemCount()!=0)
            if (cbKlienci.getItemCount()!=0){
                String klient = cbKlienci.getSelectedItem().toString();
                String projekt = cbProjekty.getSelectedItem().toString();
                if(evt.getSource().equals(bDodajKlientaDoProjektu) && klient != null && projekt != null){
                    int result = Client.getFasada().przypiszKlientaDoProjektu(klient, projekt);
                    if (result == 0) 
                        JOptionPane.showMessageDialog(this, "Dodano klienta do projektu!");
                    else if (result == 1) 
                        JOptionPane.showMessageDialog(this, "Wystapil blad!");
                }
            }
            else
                JOptionPane.showMessageDialog(this, "Brak klienta");
        else
            JOptionPane.showMessageDialog(this, "Brak projektu");
    }
    
    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
    }

    
}
