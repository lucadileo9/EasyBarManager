package pannelli.visualizza;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import grafica.MyPanel;
import pannelli.BasePanel;
import strutture.raccolta;

/**
 * 
 * Classe che implementa il pannello che si presenterà cercando di 
 * visualizzare il raccolta di un giorno
 * @author Luca Di Leo
 *
 */
public class GiornoPanel extends BasePanel{

	private static final long serialVersionUID = 1L;
	/**
	 * Il costruttore non fa altro che chiamare il costruttore 
	 * del padre inserendoci dentro la frase adatta, e passandoci
	 * i vari parametri
	 * @param r raccolta
	 * @param p pannello da modificare
	 */
	public GiornoPanel(raccolta r, MyPanel p, JFrame f ) {
		super(r, "Inserire giorno(formato gg/mm/yyyy)", p,f);
		// TODO Auto-generated constructor stub
	}
	/**
	 * L'ascoltatore cambia la data di inizio e di fine del raccolta
	 * così da visualizzare solo quel periodo
	 * L'utente inserisce solo la data che sarà settata come data di 
	 * inizio che di fine per evidenziare il giorno voluto
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String s =testo.getText();
		if(!controlloData(3,s)) {
			errore(0);
			return;
		}
		String [] d=s.split("/");
		int g= Integer.parseInt(d[0]);
		int m= Integer.parseInt(d[1]);
		int a= Integer.parseInt(d[2]);

		r.setDataInizio(a, m, g);
		r.setDataFine(a, m, g);
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
