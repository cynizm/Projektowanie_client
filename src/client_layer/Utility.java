package client_layer;

import javax.swing.JComboBox;

public class Utility {
    
    public static void initComboBox(JComboBox comboBox, Object [] objects)
    {
        comboBox.removeAllItems();
        for (Object object : objects) 
        {
            comboBox.addItem((String) object);
        }
    }
}
