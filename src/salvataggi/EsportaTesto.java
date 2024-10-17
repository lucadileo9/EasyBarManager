package salvataggi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import strutture.raccolta;
import strutture.giorno;

/**
 * Classe che permette di esportare il rancio in
 * formato testo
 * @author Luca Di Leo
 * 
 */
public class EsportaTesto {
	/**
	 * File finale su cui scriveremo
	 */
	protected File f; 
	/**
	 * raccolta su cui serializzeremo/deserializzeremo
	 */
	protected raccolta r; 
	/**
	 * File writer che mi permetterà di scrivere su file
	 */
	protected FileWriter out; 
	/**
	 * Stringa in cui inserire il percorso del file
	 */
	protected String filePath;


	/**
	 * Costruttore che permette di inizializzare il file, 
	 * allocare il FileWriter col relativo file
	 * @param nomeFile nome del file
	 * @param r rancio da esportare
	 *  
	 *  
	 *  FileNameExtensionFilter filter = new FileNameExtensionFilter("File di testo (.txt)", "txt");
fileChooser.setFileFilter(filter);

	 */
	public EsportaTesto(raccolta r)  {
		this.r=r;
		filePath=null;
		JFileChooser fileChooser = new JFileChooser();

		 // Imposta la cartella predefinita sul desktop
        String userHome = System.getProperty("user.home");
        File defaultDirectory = new File(userHome, "Desktop");
        fileChooser.setCurrentDirectory(defaultDirectory);

        // Mostra la finestra di dialogo di salvataggio e ricevi la risposta dell'utente
		int userResponse = fileChooser.showSaveDialog(null);

		// Verifica se l'utente ha selezionato "Salva"
		if (userResponse != JFileChooser.APPROVE_OPTION) {
			return;
		}
		
		// Ottieni il percorso completo del file selezionato
		filePath = fileChooser.getSelectedFile().getAbsolutePath();
				
		// Assicurati che l'estensione sia sempre ".txt"
	    if (!filePath.toLowerCase().endsWith(".txt")) {
	        filePath += ".txt"; // Aggiungi l'estensione ".txt" se non è già presente
	    }
		f=new File(filePath);	
		try {  out = new FileWriter (f);		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore nella creazione del FileWriter");
			e.printStackTrace();
		} 
	}
	
	public EsportaTesto(raccolta r, boolean empty)  {
		this.r=r;
		String filePath=null;
		JFileChooser fileChooser = new JFileChooser();

		// Per settare come directory di default Questo Pc
        File defaultDirectory = new File("::{20D04FE0-3AEA-1069-A2D8-08002B30309D}");
        fileChooser.setCurrentDirectory(defaultDirectory);

		// Mostra la finestra di dialogo di salvataggio e ricevi la risposta dell'utente
		int userResponse = fileChooser.showSaveDialog(null);

		// Verifica se l'utente ha selezionato "Salva"
		if (userResponse != JFileChooser.APPROVE_OPTION) {
			return;
		}

		// Ottieni il percorso completo del file selezionato
		filePath = fileChooser.getSelectedFile().getAbsolutePath();

		// Assicurati che l'estensione sia sempre ".txt"
	    if (!filePath.toLowerCase().endsWith(".comma")) {
	        filePath += ".comma"; // Aggiungi l'estensione ".txt" se non è già presente
	    }
		f=new File(filePath);	
		try {  out = new FileWriter (f);		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore nella creazione del FileWriter");
			e.printStackTrace();
		} 
	}

	/**
	 * Metodo che permette di esportare il testo
	 * trovandoci nella classe esportaTesto il rancio verrà
	 * esportato utilizzando come separatore il tab
	 */
	public void esporta() {
		if (filePath == null || filePath.isEmpty() || filePath.isBlank())
			return;
		
		String buffer= "";
		buffer= buffer.concat("    Data  \t"+ " Inc"+ "\t"+ " Chius"+ "  "
				+ " Pos"+ "\t"+ " Ric"+ "\t"+" Dif"+ "\t"+ " Caffè fatti"+"\n");
	//Ottengo le voci che effettivamente voglio esportare
		ArrayList<giorno> app=r.getVisibili();	
	//Ottengo la stringa contenente l'intero rancio
		for(int i=0; i<r.size(); i++) {
			buffer= buffer.concat(app.get(i).printData()+ "\t|");
			buffer=buffer.concat(app.get(i).getAmmontare()+ "\t|");
			buffer=buffer.concat(app.get(i).getChiusura()+ "\t|");
			buffer=buffer.concat(app.get(i).getPos()+ "\t|");
			buffer=buffer.concat(app.get(i).getRicariche()+ "\t|");
			buffer=buffer.concat(app.get(i).getDifferenza()+ "\t|");
			buffer=buffer.concat(app.get(i).getCaffeFatti()+ "\t");
			buffer=buffer.concat("\n");
		}
		buffer=buffer.concat("Totale:\t\t|");
		buffer=buffer.concat(Integer.toString(r.totaleAmm())+ "\t|"+
				Integer.toString(r.totaleChiusure())+ "\t|"+
				Integer.toString(r.totalePos())+ "\t|"+
				Integer.toString(r.totaleRic())+ "\t|"+
				Integer.toString(r.totaleDiff())+ "\t|"+
				Integer.toString(r.totaleCaf())+ "\t"
				);
		
			try {	out.write(buffer);		} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Errore nella scrittura");
				e.printStackTrace();
			}
		try {	out.close();	} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore nella chiusura");
			e.printStackTrace();
			}
		}
	}