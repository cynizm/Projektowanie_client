
package client.layer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DodajOsobe_form extends JPanel implements ActionListener {

    Client client;
    JLabel limie = new JLabel("Imię");
    JTextField imie = new JTextField(10);
    JLabel lnazwisko = new JLabel("Nazwisko");
    JTextField nazwisko = new JTextField(10);
    JLabel lemail = new JLabel("E-mail");
    JTextField email = new JTextField(10);
    JLabel lrola = new JLabel("Rola");
    JComboBox<String> rola = new JComboBox<>();
    JButton dodaj_osobe = new JButton("Dodaj osobę");
    DodajOsobe_form thisClass = this;

    DodajOsobe_form(Client client) {
        this.client = client;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(limie);
        add(imie);
        add(lnazwisko);
        add(nazwisko);
        add(lemail);
        add(email);
        add(lrola);
        Utility.initComboBox(rola, Client.getFasada().modelRole());
        
        add(rola);
        dodaj_osobe.addActionListener(thisClass);
        add(dodaj_osobe);
    }

    
    public String content_validate(JTextField val) {
        String s = val.getText();
        if ("".equals(s)|| s.length()>30) {
            JOptionPane.showMessageDialog(this, "Nie wprowadzono wszystkich danych.\nNie można wykonać operacji.");
            return null;
        } else {
            val.setText(s);
            return s;
        }
    }
    
    public String[] validateForm() {
        if (content_validate(imie) == null) {
            return new String[0];
        }
        if (content_validate(nazwisko) == null) {
            return new String[0];
        }
        if (content_validate(email) == null) {
            return new String[0];
        } else {
            if(!email.getText().matches("[0-9a-zA-Z_-]{1,}@[0-9a-zA-Z_-]{1,}.[0-9a-zA-Z_-]{1,}"))
            {
                JOptionPane.showMessageDialog(this, "Niepoprawny adres e-mail.");
                return new String[0];
            }
        }

        String data[] = {
            "1", 
            (String) imie.getText(), 
            (String) nazwisko.getText(),
            (String) email.getText(),
            (String) rola.getSelectedItem()
        };
        return data;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String[] data = validateForm();    
        if (data == null) {
            return;
        }
        
            if (Client.getFasada().dodajOsobe(data) == null)
                JOptionPane.showMessageDialog(this, "Osoba o podanym adresie e-mail już istnieje w systemie.\nNie można dodać.");
            else
                JOptionPane.showMessageDialog(this, "Dodano pomyślnie!");
        
    }

}
