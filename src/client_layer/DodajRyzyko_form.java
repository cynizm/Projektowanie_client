package client_layer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DodajRyzyko_form extends JPanel implements ActionListener {

	JLabel lProjekt = new JLabel("Kierownik");
	JComboBox bProjekt = new JComboBox();
	JLabel lNazwa = new JLabel("Nazwa");
	JTextField Nazwa = new JTextField(30);
	JLabel lOpis = new JLabel("Opis");
	JTextField Opis = new JTextField(30);
	JLabel lPrwdWystapienia = new JLabel("Prwd Wystapienia");
	JTextField PrwdWystapienia = new JTextField(30);
	JLabel lKosztWystapienia = new JLabel("Koszt Wystapienia");
	JTextField KosztWystapienia = new JTextField(30);
	JButton add_ryzyko = new JButton("Dodaj ryzyko");

	DodajRyzyko_form() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(lProjekt);
		bProjekt = new JComboBox(Client.getFasada().pobierzTabliceKierownikow());
		add(bProjekt);
                //bProjekt.
		add(lNazwa);
		add(Nazwa);
		Nazwa.setName("Nazwa");
		add(lOpis);
		add(Opis);
		Opis.setName("Opis");
		add(lPrwdWystapienia);
		add(PrwdWystapienia);
		PrwdWystapienia.setName("PrwdWystapienia");
		add(lKosztWystapienia);
		add(KosztWystapienia);
		KosztWystapienia.setName("KosztWystapienia");
		add_ryzyko.addActionListener(this);
		add(add_ryzyko);
	}

        public void init() {
            Utility.initComboBox(bProjekt, Client.getFasada().pobierzTabliceKierownikow());
        }
     

	@Override
	public void actionPerformed(ActionEvent evt) {
		String[] data = form_content();
		if (data == null) {
			return;
		}
                if(bProjekt.getItemCount() != 0)
                {
                    int result = Client.getFasada().addRisk(bProjekt.getSelectedItem().toString(), data);
                    if (result == 3) {
                            JOptionPane.showMessageDialog(this, "Ryzyko już istnieje w projekcie!");
                    } else if (result == 2) {
                            JOptionPane.showMessageDialog(this, "Kierownik nie ma przypisanego projektu!");
                    } else if (result == 1) {
                            JOptionPane.showMessageDialog(this, "Podana osoba nie jest kierownikiem!");
                    }
                    else if(result ==0)
                    {
                        JOptionPane.showMessageDialog(this, "Dodano ryzyko!");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Brak kierownika!");
                }
	}

	public String[] form_content() {
		if (!content_validate(Nazwa)) {
			return null;
		}
		if (!content_validate(PrwdWystapienia)) {
			return null;
		}
		if (!content_validate(KosztWystapienia)) {
			return null;
		}
		if (!content_validate(Opis)) {
			return null;
		}
		String data[] = {(String) Opis.getText(), (String) Nazwa.getText(), (String) PrwdWystapienia.getText(), (String) KosztWystapienia.getText()};
		return data;
	}

	//metoda do refaktoryzacji
	public boolean content_validate(JTextField tf) {
		String str = tf.getText();
		if (str.equals("")) {
			JOptionPane.showMessageDialog(this, "Wymagana wartość");
			return false;
		} else if ("Nazwa".equals(tf.getName())) {
			str = str.replaceAll(" ", "_");
			Nazwa.setText(str);
			return true;
		} else if ("Opis".equals(tf.getName())) {
			return true;
		} else if ("PrwdWystapienia".equals(tf.getName())) {
			if (!isFloatValid(tf.getText())) {
				JOptionPane.showMessageDialog(this, "Prawdopodobienstwo powinno być liczbą zmiennoprzecinkową mniejszą od 1 i większą od 0");
				return false;
			}
			return true;

		} else if ("KosztWystapienia".equals(tf.getName())) {

                        try
                            {
                                String test = tf.getText();
                              Float.parseFloat(test);
                            }
                        catch(NumberFormatException e)
                            {
                              JOptionPane.showMessageDialog(this, "Koszt wystapienia nie jest liczba!");
                              return false;
                            }
			return true;
		} else {
			return false;
		}
	}

	private boolean isFloatValid(String text) {
		Pattern pattern = Pattern.compile("^(1\\.0*$)|(0\\.\\d+$)|(1$)");
		Matcher match = pattern.matcher(text);
		return match.find();
	}
}
