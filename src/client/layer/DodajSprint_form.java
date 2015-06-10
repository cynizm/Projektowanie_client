package client.layer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JComboBox;

public class DodajSprint_form extends JPanel implements ActionListener {

        JLabel lprojekty = new JLabel("Kierownik projektu:");
        JComboBox projekty= new JComboBox();
        
        JLabel lnr_sprintu = new JLabel("Numer sprintu:");
        JTextField nr_sprintu = new JTextField(30);
        
	JLabel ldata_rozp = new JLabel("Data rozpoczęcia:");
	JTextField data_rozp = new JTextField(30);
        
	JLabel ldata_zak = new JLabel("Data zakończenia:");
	JTextField data_zak = new JTextField(30);
        
	JLabel lstatus = new JLabel("Status sprintu:");
	JComboBox<String> status = new JComboBox<>();
	
	JButton dodaj_sprint = new JButton("Dodaj sprint");  

	
        
    DodajSprint_form() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
                add(lprojekty);
                projekty=new JComboBox(Client.getFasada().pobierzTabliceKierownikow());
                add(projekty);
                projekty.setName("Projekty");
                
		add(lnr_sprintu);
		add(nr_sprintu);
		nr_sprintu.setName("Nr sprintu");
                
		add(ldata_rozp);
		add(data_rozp);
		data_rozp.setName("Data rozpoczecia");
                
		add(ldata_zak);
		add(data_zak);
		data_zak.setName("Data zakonczenia");
                
		add(lstatus);
		for (String r : Client.getFasada().modelStatusSprintu())
                status.addItem(r);
                add(status);
		status.setName("Status");
                
                dodaj_sprint.addActionListener(this);
                add(dodaj_sprint);
                
    }
    
    public void init() {
            Utility.initComboBox(projekty, Client.getFasada().pobierzTabliceKierownikow());
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {                
		String[] sp1 = form_content();
                
		if (sp1 == null) {
			return;
		}
                if(projekty.getItemCount() != 0)
                {
                    if(status!= null && nr_sprintu!=null)
                    {
                        int rezultat = Client.getFasada().addSprint( sp1, (String)projekty.getSelectedItem() );
                        
                        if (rezultat == 0)
                            JOptionPane.showMessageDialog(this, "Dodano pomyślnie");
                        else
                            JOptionPane.showMessageDialog(this, "Podany sprint zostal juz dodany do projektu!");
                        
                    }
                    else if (status== null)
                    {
                        JOptionPane.showMessageDialog(this, "Wpisz status!");
                    }
                    else if (nr_sprintu== null)
                    {
                        JOptionPane.showMessageDialog(this, "Wpisz numer sprintu!");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Brak projektu!");
                }
                    
                
	}

	public String[] form_content() {
		if (!content_validate(nr_sprintu)) {
			return new String[0];
		}
                if (!content_validate(data_rozp)) {
			return new String[0];
		}
                if (!content_validate(data_zak)) {
			return new String[0];
		}
               
		String data[] = {(String) nr_sprintu.getText(), (String)status.getSelectedItem() };
		return data;
	}
        
	//metoda do refaktoryzacji
	public boolean content_validate(JTextField tf) {
		String str = tf.getText();
		if ("".equals(str)) {
			JOptionPane.showMessageDialog(this, "Wypełnij wszystkie pola");
			return false;
		}
                else if ("Nr sprintu".equals(tf.getName())) {
                        if(!str.matches("^[0-9]+$")) {
                            JOptionPane.showMessageDialog(this, "DataAktualizacji: nieprawidlowy format danych!\nPodaj liczbę!");
                            return false;
                        }
		}
                else if ("Data rozpoczecia".equals(tf.getName())) {
                        if(!str.matches("^[0-9]{4}-[01][0-9]-[0-3][0-9]$")) {
                            JOptionPane.showMessageDialog(this, "Data rozpoczecia: nieprawidlowy format danych!\nPodaj dane w formacie rrrr-mm-dd.");
                            return false;
                        }
		}
                else if ("Data zakonczenia".equals(tf.getName())) {
                        if(!str.matches("^[0-9]{4}-[01][0-9]-[0-3][0-9]$")) {
                            JOptionPane.showMessageDialog(this, "Data zakonczenia: nieprawidlowy format danych!\nPodaj dane w formacie rrrr-mm-dd.");
                            return false;
                        }
		}
                else {
			return true;
		}
                return true;
	}

	
}

