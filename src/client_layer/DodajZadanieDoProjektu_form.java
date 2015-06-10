
package client_layer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DodajZadanieDoProjektu_form extends javax.swing.JFrame implements ActionListener {
    
    JLabel lidentyfikator = new JLabel("Identyfikator:");
    JTextField identyfikator = new JTextField(10);
    JLabel lnazwa = new JLabel("Nazwa:");
    JTextField nazwa = new JTextField(10);
    JLabel lstatus = new JLabel("Status:");
    JComboBox<String> status = new JComboBox<>();
    JLabel lszacczas = new JLabel("Szacowany czas:");
    JTextField szacczas = new JTextField(10);
    JLabel lczaszak = new JLabel("Czas do zakonczenia:");
    JTextField czaszak = new JTextField(10);
    JLabel lczasrealiz = new JLabel("Czas realizacji:");
    JTextField czasrealiz = new JTextField(10);    
    JButton dodaj_zadanie = new JButton("Dodaj zadanie");
    String kierownik;
    
    DodajZadanieDoProjektu_form(String kierownik_projektu) {       
        kierownik = kierownik_projektu;
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(400, 300);
        setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        setTitle("Dodaj zadanie do projektu");
        add(lidentyfikator);
        add(identyfikator);
        add(lnazwa);
        add(nazwa);
        add(lstatus);
        for (String r : Client.getFasada().modelStatusyZadan())
            status.addItem(r);
        add(status);
        add(lszacczas);
        add(szacczas);
        add(lczaszak);
        add(czaszak);
        add(lczasrealiz);
        add(czasrealiz);
        dodaj_zadanie.addActionListener(this);
        add(dodaj_zadanie);
        setVisible(true); 
    }
    
    public String[] validateForm() {
        String data[] = {
            identyfikator.getText(), 
            nazwa.getText(), 
            (String) status.getSelectedItem(),
            szacczas.getText(), 
            czaszak.getText(),
            czasrealiz.getText(),
            ""
        };
        return data;
    }

    @Override
    public void actionPerformed(ActionEvent evt) { 
        String temp = Client.getFasada().dodajZadanieDoProjektu(kierownik,validateForm());
        JOptionPane.showMessageDialog(null, temp);
        if ("Dodano zadanie do projektu.".equals(temp))
                this.dispose();
    }
}
