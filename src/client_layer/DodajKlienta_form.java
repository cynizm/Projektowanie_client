
package client_layer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class DodajKlienta_form extends javax.swing.JPanel  {

    public DodajKlienta_form() {   
        initComponents();       
    }
     
    public void init()
    {
        Utility.initComboBox(listaComboBox, Client.getFasada().modelKlienci());
    }
   
    public String[] form_title() {
        if (content_validate(kod_pocz1TF, 1, "Kod pocztowy (czesc 1)") == null) {
            return null;
        }
        if (content_validate(kod_pocz2TF, 2, "Kod pocztowy (czesc 2)") == null) {
            return null;
        }
        if (content_validate(miejscowoscTF, 4, "Miejscowosc") == null) {
            return null;
        }
        if (content_validate(nazwaTF, 5, "Nazwa firmy") == null) {
            return null;
        }
        if (content_validate(nipTF, 3, "NIP") == null) {
            return null;
        }
        if (content_validate(nr_domuTF, 6, "Nr domu") == null) {
            return null;
        }
        if (content_validate(nr_lokaluTF, 6, "Nr lokalu") == null) {
            return null;
        }
        if (content_validate(ulicaTF, 4, "Ulica") == null) {
            return null;
        }  
        
        String data[] = {"0", (String) nazwaTF.getText(),
            (String) nipTF.getText(), (String) ulicaTF.getText(),
            (String) nr_domuTF.getText(), (String) nr_lokaluTF.getText(),
            (String) miejscowoscTF.getText(), (String) kod_pocz1TF.getText() + (String) kod_pocz2TF.getText()};
        return data;   
    }
    
    public String content_validate(JTextField val, int opcja, String nazwa_pola)
    {
        String s = val.getText();
        String wzorzec="";        

        switch (opcja)
        {
            case 1:                     // pierwsza czesc kodu pocztowego
                wzorzec = "[0-9]{2}";
                break;
            case 2:                     // druga czesc kodu pocztowego
                wzorzec = "[0-9]{3}";
                break;
            case 3:                     // nip
                wzorzec = "[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}";
                break;
            case 4:                     // ulica i miejscowosc
                wzorzec = "[ .\\p{Digit}\\p{L}]+";
                break;
            case 5:                     // nazwa firmy
                wzorzec = "[\\p{L}\\p{ASCII}]+";
                break;
            case 6:                     // nr domu i lokalu
                wzorzec = "[\\w]+";
        }

        if (s.equals("")) {
            JOptionPane.showMessageDialog(this, "Nie wszystkie pola zostaly wypelnione");
            return null;
        } 
        else if (! s.matches(wzorzec)){
            JOptionPane.showMessageDialog(this, nazwa_pola + ": nieprawidlowy format danych!");
            return null;
        }
        else {
            return s;
        } 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nazwaTF = new javax.swing.JTextField();
        nipTF = new javax.swing.JTextField();
        ulicaTF = new javax.swing.JTextField();
        nr_domuTF = new javax.swing.JTextField();
        nr_lokaluTF = new javax.swing.JTextField();
        kod_pocz1TF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        kod_pocz2TF = new javax.swing.JTextField();
        miejscowoscTF = new javax.swing.JTextField();
        dodajButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        listaComboBox = new javax.swing.JComboBox();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setText("Nazwa firmy");

        jLabel2.setText("NIP");

        jLabel3.setText("Ulica");

        jLabel4.setText("Nr domu");

        jLabel5.setText("Nr lokalu");

        jLabel6.setText("Kod pocztowy");

        jLabel7.setText("Miejscowość");

        jLabel8.setText("-");

        dodajButton.setText("Dodaj klienta");
        dodajButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajButtonActionPerformed(evt);
            }
        });

        jLabel9.setText("Klienci");
        
        
//        String[] tablica = Client.getFasada().modelKlienci(); 
//            
//            if(tablica != null)
//            {
//                listaComboBox.setModel(new DefaultComboBoxModel(tablica));
//            }
        Utility.initComboBox(listaComboBox, Client.getFasada().modelKlienci());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dodajButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nazwaTF, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(nipTF)
                            .addComponent(ulicaTF)
                            .addComponent(nr_domuTF)
                            .addComponent(nr_lokaluTF)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(kod_pocz1TF)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(kod_pocz2TF, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(miejscowoscTF, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(63, 63, 63)
                        .addComponent(listaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nazwaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nipTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ulicaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nr_domuTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(nr_lokaluTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(kod_pocz1TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(kod_pocz2TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(miejscowoscTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(dodajButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(listaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
    }// </editor-fold>                        

    private void dodajButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String[] data = form_title();
        if (data == null) {
            return;
        }
        
            String[] tablica = Client.getFasada().dodajKlienta(data); 
            
            if(tablica != null)
            {
                listaComboBox.setModel(new DefaultComboBoxModel(tablica));
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Klient juz istnieje");
            }
       
    }                                           

                   
    private javax.swing.JButton dodajButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField kod_pocz1TF;
    private javax.swing.JTextField kod_pocz2TF;
    private javax.swing.JComboBox listaComboBox;
    private javax.swing.JTextField miejscowoscTF;
    private javax.swing.JTextField nazwaTF;
    private javax.swing.JTextField nipTF;
    private javax.swing.JTextField nr_domuTF;
    private javax.swing.JTextField nr_lokaluTF;
    private javax.swing.JTextField ulicaTF;
    // End of variables declaration                   
}
