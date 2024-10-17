package pannelli.aggiungi;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import grafica.MyPanel;
import pannelli.BasePanel;
import strutture.raccolta;

/**
 * Classe che implementa il pannello che si presenterà cercando di 
 * inserire un'uscita
 * @author Luca Di Leo
 */
public class AggiungiPanel extends BasePanel{
	
	private static final long serialVersionUID = 1L;
	/**
	 * Porzioni in cui inserire i dati
	 */
	protected JTextField data, ammontare, caffeFatti, chiusura, pos, ricariche;
	/**
	 * Dati dell'uscita da inserire
	 */
	protected int g,m,a,amm, cf, ch, ps, ric;
	
	/**
	 * Il costruttore non fa altro che chiamare il costruttore 
	 * del padre inserendoci dentro la frase adatta, e passandoci
	 * i vari parametri.
	 * @param bil bilancio
	 * @param p pannello da modificare
	 */
	public AggiungiPanel (raccolta r, MyPanel p, JFrame f) {
		super(r, "Inserire anno(formato yyyy)", p,f);	
		super.removeAll(); //rimuovo tutti i componenti, poichè il pannello sarà 
		// molto diverso rispetto a quello base
		//codice per ottenere la data di oggi nel formato voluto
		SimpleDateFormat dateFormat = (SimpleDateFormat)SimpleDateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);			
		dateFormat.applyPattern("dd/MM/yyyy");
		data= new JTextField(dateFormat.format(new Date()), 20);
		data.selectAll();
		data.addKeyListener(this);
		
		ammontare= new JTextField("Inserire incasso", 20);
		ammontare.addFocusListener(this);
		ammontare.addKeyListener(this);
		
		chiusura= new JTextField("Chiusura", 20);
		chiusura.addFocusListener(this);
		chiusura.addKeyListener(this);

		caffeFatti= new JTextField("Caffè", 20);
		caffeFatti.addFocusListener(this);
		caffeFatti.addKeyListener(this);
		
		pos= new JTextField("Pos", 20);
		pos.addFocusListener(this);
		pos.addKeyListener(this);

		ricariche= new JTextField("Ricariche", 20);
		ricariche.addFocusListener(this);
		ricariche.addKeyListener(this);
		
		//nord
		JPanel nord = new JPanel ();
		nord.add(data);
		nord.add(ammontare);

		//centro
		JPanel centro = new JPanel ();
		centro.add(chiusura);
		centro.add(pos);
		
		//sud
		JPanel sudNord=new JPanel ();
		sudNord.add(ricariche);
		sudNord.add(caffeFatti);
		JPanel sudSud=new JPanel ();
		sudSud.add(invio);
		
		JPanel sud =new JPanel ();
		sud.setLayout(new BorderLayout());
		sud.add(sudNord, BorderLayout.NORTH);
		sud.add(sudSud, BorderLayout.SOUTH);

		//finale
		this.setLayout(new BorderLayout());
		this.add(nord,BorderLayout.NORTH);
		this.add(centro,BorderLayout.CENTER);
		this.add(sud,BorderLayout.SOUTH);
	}
	@Override
	/**
	 * Metodo che sovrascrive il metodo errore, andando ad inserire vari messaggi di
	 * errore a seconda dell'input(err), 
	 * @param err numero dell'errore
	 */
	public void errore(int err) {
		switch (err) {
	    case 0:
			data.setText("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	    	break;
	    case 1:
			ammontare.setText("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	    	break;
	    case 2:
	    	chiusura.setText("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	        break;
	    case 3:
	    	pos.setText("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	        break;
	    case 4:
	    	ricariche.setText("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	        break;
	    case 5:
	    	caffeFatti.setText("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	        break;
	    default:
	        // Caso predefinito, se necessario
	}
		}	
	
	/**
	 * Metodo che controlla che la stringa immessa come ammontare sia
	 * formata solo da numeri e che quindi sia converitbile
	 * @param p stringa da controllare
	 * @return vero se è formata solo da numeri, falso altrimenti
	 */
	public boolean controlloNumero(String p) {
		if(p.isEmpty())
			return false;
		return p.chars().allMatch(Character::isDigit);
	}
	/**
	 * Metodo che permette di estrarre i dati dal pannello
	 * @return true se l'estrazione è andata a buon fine, false altrimenti
	 */
	public boolean estraiDati() {
		
		String s =data.getText();
		if(!controlloData(3,s)) {
			errore(0);
			return false; 
		}
		String [] d=s.split("/");
		g= Integer.parseInt(d[0]);
		m= Integer.parseInt(d[1]);
		a= Integer.parseInt(d[2]);

		String p =ammontare.getText();
		if(!controlloNumero(p)) {
			errore(1);
			return false;
		}
		amm=Integer.parseInt(p);

		
		p=chiusura.getText();
		if(!controlloNumero(p)) {
			errore(2);
			return false;
		}
		ch=Integer.parseInt(p);

		p=pos.getText();
		if(!controlloNumero(p)) {
			errore(3);
			return false;
		}
		ps=Integer.parseInt(p);

		p=ricariche.getText();
		if(!controlloNumero(p)) {
			errore(4);
			return false;
		}
		ric=Integer.parseInt(p);
		
		p=caffeFatti.getText();
		if(!controlloNumero(p)) {
			errore(5);
			return false;
		}
		cf=Integer.parseInt(p);

		return true;
	}
	 
	@Override
	/**
	 * Metodo che non fa altro che estrarre i dati, aggiungere l'uscita
	 * e aggiornare il panellp
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(estraiDati())
			r.aggiungiGiorno(a, m, g, amm, ch , ps,ric,cf);
        p.aggiorna();
        f.dispose();
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		JTextField source = (JTextField) e.getSource();
        source.selectAll();

	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Simula il clic sul pulsante 'Custom Button' quando viene premuto 'Invio'
            invio.doClick();
        }
	}
}
