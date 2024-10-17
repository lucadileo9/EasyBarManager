package pannelli.visualizza;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import grafica.MyPanel;
import pannelli.BasePanel;
import strutture.raccolta;

/**
 *	Classe che implementa il pannello che si presenterà cercando di 
 * visualizzare il raccolta di un anno
 * @author Luca Di Leo
 *
 */
public class AnnoPanel extends BasePanel{
	private static final long serialVersionUID = 1L;
	/**
	 * Il costruttore non fa altro che chiamare il costruttore 
	 * del padre inserendoci dentro la frase adatta, e passandoci
	 * i vari parametri
	 * @param r raccolta
	 * @param p pannello da modificare
	 */
	public AnnoPanel(raccolta r, MyPanel p, JFrame f) {
		super(r, "Inserire anno(formato yyyy)", p, f);	
			}
	/**
	 * L'ascoltatore cambia la data di inizio e di fine del raccolta
	 * così da visualizzare solo quel periodo
	 * L'utente inserisce solo l'anno e poi setto la data di fine all'anno
	 * successivo per visulizzare il periodo voluto
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s=testo.getText();
		
		if(!controlloData(0,s)) {
		//se la data non è corretta segnalo l'errore e fermo tutto
			errore(0);
			return;
		}
		//altrimenti estraggo la data e modifico
		int i =Integer.parseInt(s);
		r.setDataInizio(i, 1, 1);
		r.setDataFine(i+1, 1, 1);
		//aggiorno il pannello
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
