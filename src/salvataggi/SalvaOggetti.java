/**
 * 
 */
package salvataggi;

import java.io.*;
import javax.swing.JFileChooser;

import strutture.raccolta;

/**
 * Classe che permette di serializzare 
 * e deserializzare un rancio
 * @author Luca Di Leo
 *
 */
public class SalvaOggetti {
	private File f; //file finale su cui scrivere
	private raccolta r; //rancio su cui serializzare/deserializzare
	private String filePath;
	/**
	 * Costruttore della classe che inizializza il rancio
	 * e crea il file
	 * @param nomeFile stringa che contiene il nome del file
	 * @param r	rancio
	 */
	public SalvaOggetti(raccolta r) {
		this.r=r;
		filePath=null;
		JFileChooser fileChooser = new JFileChooser();

      /*  // Imposta la cartella predefinita sul desktop
        String userHome = System.getProperty("user.home");
        File defaultDirectory = new File(userHome, "Desktop");
        fileChooser.setCurrentDirectory(defaultDirectory);
//*/
		 // Trova la pennetta USB inserita
        File[] roots = File.listRoots();
        File usbDrive = null;

        for (File root : roots) {
            if (isUSBDrive(root)) {
                usbDrive = root;
                break;
            }
        }

        // Imposta la cartella predefinita sulla pennetta USB
        if (usbDrive != null) {
            fileChooser.setCurrentDirectory(usbDrive);
        }
		// Mostra la finestra di dialogo di salvataggio e ricevi la risposta dell'utente
		int userResponse = fileChooser.showSaveDialog(null);

		// Verifica se l'utente ha selezionato "Salva"
		if (userResponse != JFileChooser.APPROVE_OPTION) {
			return;
			}
		// Ottieni il percorso completo del file selezionato
		filePath = fileChooser.getSelectedFile().getAbsolutePath();
				
		f=new File(filePath);	
	}
	/**
	 * Metodo che dice se il file esiste o no
	 * @return true se il file esiste, falso altrimenti
	 */
	public boolean ilFileEsiste() {
		return f.exists();
	}
	/**
	 * Metodo per serializzare
	 */
	public void serializzazione() {
		if ( filePath == null || filePath.isEmpty() || filePath.isBlank() )
			return;
		try {
		ObjectOutputStream os= new ObjectOutputStream(new FileOutputStream(f));
		os.writeObject(r);
		os.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Ops, qualcosa è andato storto "+e);
			e.printStackTrace();
		}
	}
	/**
	 * Metodo per deserializzare
	 * @return il rancio ottenuto
	 */
	public raccolta deserializzazione() {
		if (filePath == null || filePath.isEmpty() || filePath.isBlank() || !ilFileEsiste())
			//se il file non esiste ritorno un rancio vuoto
			return r;
		try {
		ObjectInputStream in= new ObjectInputStream(new FileInputStream(f));
		r= (raccolta) in.readObject(); 	
		in.close();
		return r;
		}
		catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Ops, qualcosa è andato storto "+e);
			e.printStackTrace();
		}
		return r;
	}
	
	// Verifica se un'unità è una pennetta USB
    private static boolean isUSBDrive(File drive) {
        File[] files = drive.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().equalsIgnoreCase("autorun.inf")) {
                    return true;
                }
            }
        }
        return false;
    }
}

