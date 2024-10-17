package grafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import pannelli.*;
import pannelli.aggiungi.*;
import pannelli.dati.AvgPanel;
import pannelli.visualizza.*;
import salvataggi.EsportaTesto;
import salvataggi.SalvaOggetti;
import strutture.MyDateFormatSymbols;
import strutture.giorno;
import strutture.raccolta;

/**
 * Classe che implementa il panello contenente la tabella, il totale,
 * il menu e i vari bottoni
 * @author Luca Di Leo
 *
 */
public class MyPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	/**
	 * rancio da attaccare alla tabella
	 */
	private raccolta r; 
	/**
	 * Bottoni
	 */
	private JButton rimuovi, ricercaBott, prossimo, frecciaAvanti, frecciaIndietro; 
	/**
	 * Testo
	 */
	private JTextField ricercaTesto, meseAnno;
	/**
	 * Tabella dei dati
	 */
	private MyTableModel dataModel; 
	/**
	 * Tabella grafica
	 */
	private JTable t;
	/**
	 * ScrollPane per poter scorrere la tabella
	 * Ancora non riesco ad inserirlo
	 */
	private JScrollPane scrollPane;
	/**
	 * Primo Menu
	 */
	private JMenuItem anno, mese, settimana, giorno, periodo, visualizzaTutto;
	/**
	 * Secondo Menu
	 */
	private JMenuItem aggiungi;
	/**
	 * Primo Menu
	 */
	private JMenuItem salva,carica, esportaTxt;
	
	private JMenu massimi;
	private JMenuItem medie;
	private JMenuItem maxAmm, maxCaffe, maxCh, maxPos, maxRic, maxDiff;
	/**
	 * Variare per la ricerca degli elementi
	 */
	private int i=0; 
	private SimpleDateFormat dateFormat;
	 /**
	  * Costruttore che copia il rancio, inizalizza tutte le componenti, aggiungendo i vari
	  * ascoltatori, per poi aggiungerli al pannello 
	  * @param r rancio
	  */
	public MyPanel(raccolta r) {
		super();
		this.r = r;

		//inizializzo la tabella
		dataModel=new MyTableModel(r, this);
		t=new JTable(dataModel);
		scrollPane= new JScrollPane(t);
		
		t.getColumnModel().getColumn(0).setCellRenderer(new MyDateCellRenderer());
		//inizializzo i bottoni, i testi e aggiungo gli ascoltatori
		rimuovi= new JButton("Rimuovi");
		ricercaTesto= new JTextField("", 15); ricercaTesto.setEditable(true);
		ricercaBott= new JButton("Ricerca");
		prossimo= new JButton("Prossimo"); prossimo.setEnabled(false);
		
		// Creiamo un formato personalizzato per la rappresentazione desiderata
        dateFormat = new SimpleDateFormat("MMMM yyyy", new MyDateFormatSymbols());
        // Utilizziamo il formato per formattare la data in una stringa
		meseAnno= new JTextField(dateFormat.format(r.getDataInizio()));
		meseAnno.setEditable(false);
		meseAnno.setHorizontalAlignment(JTextField.CENTER);
		
		frecciaDX();
		frecciaSX();

		frecciaAvanti.addActionListener(this);
		frecciaIndietro.addActionListener(this);
		
		rimuovi.addActionListener(this);
		ricercaBott.addActionListener(this);
		prossimo.addActionListener(this);

//////Menu per visulizzare///////////////////////////////////////////////////
		anno= new JMenuItem("Anno");
		mese= new JMenuItem("Mese"); 
		giorno= new JMenuItem("Giorno"); 
		settimana= new JMenuItem("Settimana"); 
		periodo= new JMenuItem ("Periodo");
		visualizzaTutto= new JMenuItem ("Visualizza tutto");

		anno.addActionListener(this);
		mese.addActionListener(this);
		settimana.addActionListener(this);
		giorno.addActionListener(this);
		periodo.addActionListener(this);
		visualizzaTutto.addActionListener(this);

		JMenu visualizza= new JMenu ("Visulizza");
			
		visualizza.add(anno);
		visualizza.add(mese);
		visualizza.add(settimana);
		visualizza.add(giorno);
		visualizza.add(periodo);
		visualizza.add(visualizzaTutto);

		aggiungi= new JMenuItem ("Aggiungi");
		aggiungi.addActionListener(this);

/////////////////////////////////////////////////////////////////////////////
		
		massimi= new JMenu ("Massimi");
			maxAmm= new JMenuItem ("Incasso");
			maxCaffe= new JMenuItem ("Caffè");
			maxCh= new JMenuItem ("Chiusure");
			maxPos= new JMenuItem ("Pos");
			maxRic= new JMenuItem ("Ricariche");
			maxDiff= new JMenuItem ("Differenza");
			
			maxAmm.addActionListener(this);
			maxCaffe.addActionListener(this);
			maxCh.addActionListener(this);
			maxPos.addActionListener(this);
			maxRic.addActionListener(this);
			maxDiff.addActionListener(this);

			massimi.add(maxAmm);
			massimi.add(maxCaffe);
			massimi.add(maxCh);
			massimi.add(maxPos);
			massimi.add(maxRic);
			massimi.add(maxDiff);

		
		medie= new JMenuItem ("Media");
		medie.addActionListener(this);
				
		JMenu dati= new JMenu("Dati");
		dati.add(massimi);
		dati.add(medie);

//////////Menu Altro/////////////////////////////////////////////////
		
		esportaTxt= new JMenuItem ("Esporta");
		salva= new JMenuItem ("Salva");
		carica= new JMenuItem ("Carica");

		esportaTxt.addActionListener(this);
		salva.addActionListener(this);
		carica.addActionListener(this);


		JMenu altro= new JMenu("Altro");
		altro.add(esportaTxt);
		altro.add(salva);
		altro.add(carica);

/////////////////////////////////////////////////////////////////////////////

////////Menu finale//////////////////////////////////////////////////////////
		
		JMenuBar fine= new JMenuBar();
		fine.add(visualizza);
		fine.add(dati);
		fine.add(aggiungi);
		fine.add(altro);
				
////////Costruzione pannello finale//////////////////////////////////////////
		
		//nord		
		JPanel nordNord = new JPanel ();
		nordNord.setLayout(new BorderLayout());
		nordNord.add(meseAnno,BorderLayout.NORTH);
		nordNord.add(t.getTableHeader(), BorderLayout.CENTER);
		nordNord.add(scrollPane,BorderLayout.SOUTH);
				

		JPanel nordSud = new JPanel ();
		nordSud.setLayout(new BorderLayout());
		nordSud.add(frecciaAvanti,BorderLayout.EAST);
		nordSud.add(frecciaIndietro,BorderLayout.WEST);
		
		JPanel nord = new JPanel ();
		nord.setLayout(new BorderLayout());
		nord.add(nordNord,BorderLayout.NORTH);
		nord.add(nordSud,BorderLayout.CENTER);

		//centro		
		JPanel centroNord = new JPanel ();
		centroNord.add(rimuovi);
				
		JPanel centroCentro= new JPanel();
		centroCentro.add(ricercaTesto);
		centroCentro.add(ricercaBott);
		centroCentro.add(prossimo);
		
				
		JPanel centro = new JPanel ();
		centro.setLayout(new BorderLayout());
		centro.add(centroNord,BorderLayout.NORTH);
		centro.add(centroCentro,BorderLayout.CENTER);

			
		//finale
		this.setLayout(new BorderLayout());
		this.add(nord,BorderLayout.NORTH);
		this.add(centro,BorderLayout.CENTER);
		this.add(fine,BorderLayout.SOUTH);	
	}	
	

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object scelta= e.getSource();
		EsportaTesto app;

		if(scelta==rimuovi) {
			//rimuovo la voce selezionata
			ArrayList<giorno> appoggio= r.getVisibili();
			int[] indici= t.getSelectedRows();
			int max=t.getRowCount() -1;
			for(int i=0; i< indici.length; i++) {
 				if(indici[i] >=  max)
					{
					this.aggiorna();
					return;
					}
				r.rimuoviGiorno(appoggio.get(indici[i]));
			}
			//notifico il cambiamento della tabella e modifico il totale
			this.aggiorna();

		}
		else if(scelta==ricercaBott) {
			t.clearSelection(); //deseleziono tutto			
			
			i=r.ricerca(ricercaTesto.getText(), 0);
			//ottengo l'indice della voce trovata
			
			//controllo se la ricerca mi ha resituito un valore non valido
			if(i==-1) {
				ricercaTesto.setText("Elemento non presente");
				//se così non fosse avverto l'utente
				prossimo.setEnabled(false);
				return;
			}
			//se invece l'elemento è presente lo evidenzio
			t.addRowSelectionInterval(i, i);
			//aumento l'indice qualora l'utente volesse cercare le prossime voci
			i++;
			prossimo.setEnabled(true); //rendo cliccare il tasto prossimo
		}
		
		else if(scelta==prossimo) {
			/*Controllo sia prima che dopo se l'indice è accettare, perchè l'utente 
			 malizioso potrebbe portare il tutto ad errore
			 */
			if(i==-1) {
				ricercaTesto.setText("Elemento non presente");
				prossimo.setEnabled(false);
				return;
			}
			//ricerco l'espressione immessa continuando da i, per trovare appunto le prossime voci
			i=r.ricerca(ricercaTesto.getText(), i);
			//controllo se la ricerca mi ha resituito un valore non valido
			if(i==-1) {
				ricercaTesto.setText("Elemento non presente");
				prossimo.setEnabled(false);
				return;
			}
			//se invece l'elemento è presente lo evidenzio
			t.addRowSelectionInterval(i, i);
			//aumento l'indice qualora l'utente vlesse cercare le prossime voci
			i++;
			
		}
		else if(scelta==visualizzaTutto) {
	//per visualizzare l'intero rancio, settando le date a quelle di default 
			r.setDataInizio(r.elementAt(0).getData());
			r.setDataFine(r.elementAt(r.superSize()-1).getData());
			this.aggiorna();
		}
		else if(scelta==maxAmm) {
		//	t.clearSelection(); //deseleziono tutto		
			int j=0;
			j=r.massimoAmm();
			t.addRowSelectionInterval(j, j);
		//	this.aggiorna();
		}
		else if(scelta==maxAmm) {
			//	t.clearSelection(); //deseleziono tutto		
				int j=0;
				j=r.massimoAmm();
				t.addRowSelectionInterval(j, j);
			//	this.aggiorna();
				}
		else if(scelta==maxCaffe) {
			//	t.clearSelection(); //deseleziono tutto		
				int j=0;
				j=r.massimoCaf();
				t.addRowSelectionInterval(j, j);
			//	this.aggiorna();
				}
		else if(scelta==maxCh) {
			//	t.clearSelection(); //deseleziono tutto		
				int j=0;
				j=r.massimoChiusure();
				t.addRowSelectionInterval(j, j);
			//	this.aggiorna();
				}
		else if(scelta==maxPos) {
			//	t.clearSelection(); //deseleziono tutto		
				int j=0;
				j=r.massimoPos();
				t.addRowSelectionInterval(j, j);
			//	this.aggiorna();
				}
		else if(scelta==maxRic) {
			//	t.clearSelection(); //deseleziono tutto		
				int j=0;
				j=r.massimoRic();
				t.addRowSelectionInterval(j, j);
			//	this.aggiorna();
				}
		else if(scelta==maxDiff) {
			//	t.clearSelection(); //deseleziono tutto		
				int j=0;
				j=r.massimoDiff();
				t.addRowSelectionInterval(j, j);
			//	this.aggiorna();
				}
		else if(scelta== salva) {
			SalvaOggetti o = new SalvaOggetti (r);
			o.serializzazione();
		}
		else if (scelta== carica) {
			SalvaOggetti s = new SalvaOggetti (r);
			//deserializzo ottenendo il raccolta usando un raccolta di appoggio
			raccolta appoggio=s.deserializzazione();
			//copio il raccolta ottenuto nel raccolta di partenza
			r.setTutto(appoggio.getTutto());
			//e resetto le date per essere sicuro di visualizzare l'intero raccolta
			r.setDataInizio(appoggio.elementAt(0).getData());
			r.setDataFine(appoggio.elementAt(r.superSize()-1).getData());
			
			this.aggiorna();
		}
		else if (scelta== frecciaIndietro) {
		//	System.out.println("Sono nell'if indietro");
			r.meseInDietro();
			this.aggiorna();
		}
		else if (scelta== frecciaAvanti) {
			//System.out.println("Sono nell'if avanti");
			r.meseInAvanti();
			this.aggiorna();
		}
		else if (scelta==esportaTxt) {
			app= new EsportaTesto (r);
			app.esporta();
		}
		else {
		JFrame f = new JFrame(e.getActionCommand());
		BasePanel p = null;
		if(scelta == anno) {
			p= new AnnoPanel (r, this, f);
		}
		if(scelta == mese) {
			p= new MesePanel (r, this,f);
		}
		if(scelta == settimana) {
			 p= new SettimanaPanel (r, this,f);
		}
		if(scelta == giorno) {
			p= new GiornoPanel (r, this,f);
		}
		if(scelta == periodo) {
			p= new PeriodoPanel (r, this,f);
		}
		if(scelta == aggiungi) {
			p= new AggiungiPanel (r, this,f);

		}
		if (scelta==medie) {
			p= new AvgPanel(r);
		}
		f.add(p);
		f.pack();
		f.setVisible(true);
		}
	}
	
	/**
	 * Funzione che aggiorna il pannello 
	 */
	public void aggiorna() {
		//aggiornamento della tabella
		meseAnno.setText(dateFormat.format(r.getDataInizio()));
		t.setValueAt(null,0,0);
		//aggiornamento della pannello vero e proprio
		this.revalidate();
		this.repaint();
	}
	
	public void frecciaDX() {
		ImageIcon arrowIcon = new ImageIcon("C:\\Users\\lucad\\eclipse-workspace\\bar2.0\\src\\arrow_right\""); // Inserisci il percorso dell'immagine della freccia
		int buttonSize = 20; // Imposta la dimensione desiderata del pulsante
		// Ridimensiona l'immagine dell'icona
		Image scaledImage = arrowIcon.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH);
		ImageIcon scaledArrowIcon = new ImageIcon(scaledImage);
		frecciaAvanti = new JButton(scaledArrowIcon);
		frecciaAvanti.setBorderPainted(false);
		frecciaAvanti.setContentAreaFilled(false);
		frecciaAvanti.setFocusPainted(false);
		frecciaAvanti.setOpaque(false);
		frecciaAvanti.addMouseListener(new java.awt.event.MouseAdapter() {
	         
			public void mousePressed(java.awt.event.MouseEvent evt) {
	        	 frecciaAvanti.setBackground(Color.GRAY); // Cambia il colore del pulsante quando viene premuto
	         }

	         public void mouseReleased(java.awt.event.MouseEvent evt) {
	        	 frecciaAvanti.setBackground(null); // Ripristina il colore del pulsante quando il tasto viene rilasciato
	         }
});

		
	}
	
	public void frecciaSX() {
		ImageIcon arrowIcon = new ImageIcon("C:\\Users\\Luca\\Desktop\\arrowSX.png"); // Inserisci il percorso dell'immagine della freccia
		int buttonSize = 20; // Imposta la dimensione desiderata del pulsante
		// Ridimensiona l'immagine dell'icona
		Image scaledImage = arrowIcon.getImage().getScaledInstance(buttonSize, buttonSize, Image.SCALE_SMOOTH);
		ImageIcon scaledArrowIcon = new ImageIcon(scaledImage);
		frecciaIndietro = new JButton(scaledArrowIcon);
		frecciaIndietro.setBorderPainted(false);
		frecciaIndietro.setContentAreaFilled(false);
		frecciaIndietro.setFocusPainted(false);
		frecciaIndietro.setOpaque(false);
		frecciaIndietro.addMouseListener(new java.awt.event.MouseAdapter() {
	         
			public void mousePressed(java.awt.event.MouseEvent evt) {
	        	 frecciaIndietro.setBackground(Color.GRAY); // Cambia il colore del pulsante quando viene premuto
	         }

	         public void mouseReleased(java.awt.event.MouseEvent evt) {
	        	 frecciaIndietro.setBackground(null); // Ripristina il colore del pulsante quando il tasto viene rilasciato
	         }
	     });

	}
	
}