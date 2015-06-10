package client.layer;

import business_layer.Fasada;

public class Client {

    private static final Fasada fasada = new Fasada();

    static public Fasada getFasada() {
        return fasada;
    }

    public static void main(String[] args) {
        Client client = new Client();
	Panel_util panel = new Panel_util();
	java.awt.EventQueue.invokeLater(() -> 
            panel.createAndShowGUI(client));
    }
}
