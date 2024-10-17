package pannelli.visualizza;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import grafica.MyPanel;
import pannelli.BasePanel;
import strutture.raccolta;

/**
 * Classe che implementa il pannello che si presenterà cercando di 
 * visualizzare il raccolta di un periodo preciso
 * @author Luca Di Leo
 */
public class PeriodoPanel extends BasePanel{

	private static final long serialVersionUID = 1L;
	/**
	 * Seconda data
	 */
	private JTextField secondaData;
	/**
	 * Il costruttore non fa altro che chiamare il costruttore 
	 * del padre inserendoci dentro la frase adatta, oltre che 
	 * aggiungere un'altra sezione di testo per inserire la
	 * seconda data
	 * @param r raccolta da modificare
	 * @param p pannello da modificare
	 */
	public PeriodoPanel(raccolta r, MyPanel p, JFrame f) {
		//chiamata al costruttore del padre
		super(r,"Inserire 1° giorno(formato gg/mm/yyyy)", p, f);
		//nuovo componente
		secondaData= new JTextField("Inserisci 2° giorno",20);

		JPanel nord= new JPanel();
		nord.setLayout(new BorderLayout());
		nord.add(testo, BorderLayout.NORTH);
		nord.add(secondaData, BorderLayout.CENTER);
		
		JPanel centro= new JPanel();
		centro.setLayout(new BorderLayout());
		centro.add(invio, BorderLayout.NORTH);

		this.setLayout(new BorderLayout());
		this.add(nord,BorderLayout.NORTH);
		this.add(centro,BorderLayout.CENTER);
		// TODO Auto-generated constructor stub
	}
	/**
	 * L'ascoltatore cambia la data di inizio e di fine del raccolta
	 * così da visualizzare solo quel periodo
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

		s =secondaData.getText();
		if(!controlloData(3,s)) {
			errore(0);
			return;
		}
		
		r.setDataInizio(a, m, g);
		d=s.split("/");
		g= Integer.parseInt(d[0]);
		m= Integer.parseInt(d[1]);
		a= Integer.parseInt(d[2]);
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
