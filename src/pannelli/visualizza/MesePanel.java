package pannelli.visualizza;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import grafica.MyPanel;
import pannelli.BasePanel;
import strutture.raccolta;

/**
 * Classe che implementa il pannello che si presenterà cercando di 
 * visualizzare il raccolta di un mese
 * @author Luca Di Leo
 *	
 */
public class MesePanel extends BasePanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Il construttore non fa altro che chiamare il costruttore 
	 * del padre inserendoci dentro la frase adatta
	 * @param r raccolta
	 * @param p pannello da modificare
	 */
	public MesePanel(raccolta r, MyPanel p, JFrame f) {
		super(r, "Inserire mese(formato mm/yyyy)", p,f );
		// TODO Auto-generated constructor stub
	}
	/**
	 * L'ascoltatore cambia la data di inizio e di fine del raccolta
	 * così da visualizzare solo quel periodo
	 * L'utente inserisce solo la data di inzio e poi viene aggiunto 
	 * 1 mese per evidenziare il periodo voluto
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String s =testo.getText();
		if(!controlloData(1,s)) {
			errore(0);
			return;
		}
		String [] d=s.split("/");
		int m= Integer.parseInt(d[0]);
		int a= Integer.parseInt(d[1]);

		r.setDataInizio(a, m, 1);
		r.setDataFine(a, m+1, 0);
		
		p.aggiorna();
		f.dispose();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Simula il clic sul pulsante 'Custom Button' quando viene premuto 'Invio'
            invio.doClick();
        }
	}
}
