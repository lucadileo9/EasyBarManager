package grafica;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import strutture.MyDateFormatSymbols;
public class MyDateCellRenderer extends DefaultTableCellRenderer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd",new MyDateFormatSymbols());
    private static final Color coloreFestivo = Color.RED; // Colore per le date festive
    private static final Color coloreFeriale = Color.DARK_GRAY; // Colore per le date non festive

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      
    	// Converto il valore della cella in una data
        if (value instanceof Date) {
            Date data = (Date) value;
            
            // Verifica se la data è festiva (qui dovresti implementare la logica per rilevare le date festive)
                        // Imposta il colore del testo in base alla condizione
            if (isFestivita(data)) {
                setForeground(coloreFestivo);
            } 
            else { 
                setForeground(coloreFeriale);
            }
            
            // Formatta la data come stringa e imposta il testo della cella
            setText(dateFormat.format(data));
        } else {
        	setForeground(coloreFeriale);
        	setText("Totale:"); // Se il valore non è una data, imposta il testo vuoto
        }

        return this;
    }

    
    

public boolean isFestivita(Date data) {
	List<String> festivita = new ArrayList<>();

    // Aggiungi le date delle festività (senza specificare l'anno)
    festivita.add("01/01"); // Capodanno
    festivita.add("06/01"); // Epifania
    festivita.add("01/01"); // Capodanno
    festivita.add("06/01"); // Epifania
    festivita.add("01/05"); // Festa dei Lavoratori
    festivita.add("02/06"); // Festa della Repubblica
    festivita.add("15/08"); // Assunzione di Maria
    festivita.add("01/11"); // Tutti i Santi
    festivita.add("08/12"); // Immacolata Concezione
    festivita.add("25/12"); // Natale
    festivita.add("26/12"); // Santo Stefano
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE"); // formato col nome
	String stringa = dateFormat.format(data); //prendo solo il nome del giorno della data
    if (stringa.equalsIgnoreCase("domenica")) //se il nome è uguale a domenica
    	return true; //è una festività e ritorno true
    else
    {
    	dateFormat  = new SimpleDateFormat("dd/MM"); //altrimenti creo il formato giorno e mese della data
        stringa = dateFormat.format(data); // prendo giorno e mese dalla data
        return festivita.contains(stringa); // vedo se è contenuto nell'array delle festività
    }
}
}

