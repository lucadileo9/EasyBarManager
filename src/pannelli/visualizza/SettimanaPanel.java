package pannelli.visualizza;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import grafica.MyPanel;
import pannelli.BasePanel;
import strutture.raccolta;

/**
 *	Classe che implementa il pannello che si presenterà cercando di 
 *	visualizzare il raccolta di una settimana
 * @author Luca Di Leo
 *
 */
public class SettimanaPanel extends BasePanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	/**
	 * Il costruttore non fa altro che chiamare il costruttore 
	 * del padre inserendoci dentro la frase adatta, e passandoci
	 * i vari parametri
	 * @param r raccolta
	 * @param p pannello da modificare
	 */
	public SettimanaPanel(raccolta r, MyPanel p, JFrame f) {
		super(r,"Inserire settimana(formato gg/mm/yyyy)", p , f);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * L'ascoltatore cambia la data di inizio e di fine del raccolta
	 * così da visualizzare solo quel periodo
	 * L'utente inserisce solo la data di inzio e poi ci aggiungo 
	 * 7 giorni per evidenziare la settimana
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String s =testo.getText();
		if(!controlloData(2,s)) {
			errore(0);
			return;
		}
		String [] d=s.split("/");
		int g= Integer.parseInt(d[0]);
		int m= Integer.parseInt(d[1]);
		int a= Integer.parseInt(d[2]);
		
		r.setDataInizio(a, m, g);
		r.setDataFine(a, m, g+7);	

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
