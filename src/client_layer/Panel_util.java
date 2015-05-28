
package client_layer;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class Panel_util implements ActionListener {
    
    Client client;
    
    JPanel cards;
    final static String RYZYKO = "Dodaj ryzyko";
    final static String LISTA_RYZYK = "Lista ryzyk";
    final static String OSOBA = "Dodaj osobe";
    final static String LISTA_OSOB = "Lista osob";
    final static String KLIENT = "Dodaj klienta";
    final static String PROJEKT = "Dodaj projekt";
    final static String LISTA_PROJEKTOW = "Lista projektów";
    final static String DODAJ_DO_PROJEKTU = "Dodaj Klienta do Projektu";
    
    
        public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;

        menuBar = new JMenuBar();

        menu = new JMenu("Menu");
        menuBar.add(menu);
   
        menuItem = new JMenuItem(KLIENT);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem(OSOBA);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem(PROJEKT);
        menuItem.addActionListener(this);
        menu.add(menuItem); 
        
        menuItem = new JMenuItem(RYZYKO);
        menuItem.addActionListener(this);
        menu.add(menuItem);
         
        menuItem = new JMenuItem(LISTA_OSOB);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem(LISTA_PROJEKTOW);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem(LISTA_RYZYK);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem(DODAJ_DO_PROJEKTU);
        menuItem.addActionListener(this);
        menu.add(menuItem);
       
       
        return menuBar;
    }
        
    public Container createContentPane() {

        DodajKlienta_form card0 = new DodajKlienta_form();
        DodajRyzyko_form card1 = new DodajRyzyko_form();
        ListaRyzyk_form card2 = new ListaRyzyk_form();
        DodajOsobe_form card3 = new DodajOsobe_form(client);
        ListaOsob_form card4 = new ListaOsob_form(client);
        Dodaj_projekt_form card5 = new Dodaj_projekt_form();
        Lista_projektow_form card6 = new Lista_projektow_form();
        Dodaj_klienta_do_projektu_form card7 = new Dodaj_klienta_do_projektu_form();
        
        cards = new JPanel(new CardLayout());
        cards.add(card0, KLIENT);
        cards.add(card1, RYZYKO);
        cards.add(card2, LISTA_RYZYK);
        cards.add(card3, OSOBA);
        cards.add(card4, LISTA_OSOB);
        cards.add(card5, PROJEKT);
        cards.add(card6, LISTA_PROJEKTOW);
        cards.add(card7, DODAJ_DO_PROJEKTU);
        
        JPanel panel = new JPanel();

        panel.add(cards, BorderLayout.CENTER);
        card2.init();
        return panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        JMenuItem source = (JMenuItem) (e.getSource());
        CardLayout cl = (CardLayout) (cards.getLayout());
        switch (source.getText()) {
            case KLIENT:
                ((DodajKlienta_form) cards.getComponent(0)).init();
                cl.show(cards, KLIENT);
                break;
            case OSOBA:
                cl.show(cards, OSOBA);
                break;
            case RYZYKO:
                ((DodajRyzyko_form) cards.getComponent(1)).init();
                cl.show(cards, RYZYKO);
                break;
            case LISTA_OSOB:
                cl.show(cards, LISTA_OSOB);
                break;
            case LISTA_RYZYK:
                ((ListaRyzyk_form) cards.getComponent(2)).init();
                cl.show(cards, LISTA_RYZYK);
                break;
            case PROJEKT:
                ((Dodaj_projekt_form) cards.getComponent(5)).init();
                cl.show(cards, PROJEKT);
                break;
            case LISTA_PROJEKTOW:
                cl.show(cards, LISTA_PROJEKTOW);
                break;
            case DODAJ_DO_PROJEKTU:
                ((Dodaj_klienta_do_projektu_form) cards.getComponent(7)).init();
                cl.show(cards, DODAJ_DO_PROJEKTU);
                break;
        }
    }


    public void createAndShowGUI(Client client) {
        this.client = client;
       JFrame frame = new JFrame("Project Portfolio");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setMinimumSize(new Dimension(400,450));
     
        Panel_util demo = new Panel_util();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());
        
        frame.setVisible(true);
    }
}
