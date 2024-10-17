package strutture;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class raccolta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<giorno> tutto;
	/**
	 * Data d'inizio da cui iniziare a vedere le operazioni
	 */
	private Date dataInizio ;
	/**
	 * Data finale fino a cui vedere le operazioni
	 */
	private Date dataFine;
	
	public raccolta() {
		tutto = new ArrayList<giorno>();
		this.setDataInizio(0, 0, 0);
		this.setDataFine(0, 0, 0);
	}
	
	public raccolta(ArrayList<giorno> v) {
		setTutto(v);
		this.setDataInizio(0, 0, 0);
		this.setDataFine(0, 0, 0);
	}
	
	public ArrayList<giorno> getTutto() {
		return tutto;
	}
	/**
	 * setter
	 * @param tutto the tutto to set
	 */
	public void setTutto(ArrayList<giorno> tutto) {
		this.tutto = tutto;
	}
	
	public Date getDataInizio() {
		return dataInizio;
	}
	public Date getDataFine() {
		return dataFine;
	}
	
	public void setDataInizio(Date d) {
		this.dataInizio=d;
	}
	public void setDataInizio(int a, int m, int g) {
		Calendar calendar = Calendar.getInstance();
		if(a==0 && m==0 && g==0) {
			calendar.set(Calendar.DAY_OF_MONTH, 1); //setto il giorno al primo del mese
			// Setto al primo millisecondo della giornata
			calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        calendar.set(Calendar.MILLISECOND, 0);
			this.dataInizio= calendar.getTime();
			return;
		}
		calendar.set(a,m-1,g,0,0,0);
	//setto anche ore, minuti, e secondi, così da ottenere praticamente
	// l'inizio del giorno e visualizzare TUTTE le voci cronoligicamente successive
		this.dataInizio = calendar.getTime();
	}
	
	public void setDataFine(Date d) {
		this.dataFine=d;
	}
	public void setDataFine(int a, int m, int g) {
		Calendar calendar = Calendar.getInstance();
		if(a==0 && m==0 && g==0) {
	        //Setto il giorno all'ultimoi del mese
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			// Setto all'ultimo millisecondo della giornata
	        calendar.set(Calendar.HOUR_OF_DAY, 23);
	        calendar.set(Calendar.MINUTE, 59);
	        calendar.set(Calendar.SECOND, 59);
	        calendar.set(Calendar.MILLISECOND, 999);
	        this.dataFine= calendar.getTime();
			return;
		}
		calendar.set(a,m-1,g,23,59,59);
		//setto anche ore, minuti, e secondi, così da ottenere praticamente
		// la fine del giorno e visualizzare TUTTE le voci cronoligicamente precedenti
		this.dataFine= calendar.getTime();
	}
	
	public void meseInAvanti() {
//		System.out.println("Sono nella funzione avanti");
        // Creazione di un oggetto Calendar
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTime(dataFine);
        // Sposta la data avanti di un mese
        calendar.add(Calendar.MONTH, 1);
        // Ora calendar contiene la data avanzata di un mese
        dataFine = calendar.getTime();
        
        calendar.setTime(dataInizio);
        // Sposta la data avanti di un mese
        calendar.add(Calendar.MONTH, 1);
        // Ora calendar contiene la data avanzata di un mese
        dataInizio = calendar.getTime();

	}
	
	public void meseInDietro() {
        
//	System.out.println("Sono nella funzione indietro");
        // Creazione di un oggetto Calendar
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(dataFine);
        // Sposta la data avanti di un mese
        calendar.add(Calendar.MONTH, -1);
        // Ora calendar contiene la data avanzata di un mese
        dataFine = calendar.getTime();
        
        calendar.setTime(dataInizio);
        // Sposta la data avanti di un mese
        calendar.add(Calendar.MONTH, -1);
        // Ora calendar contiene la data avanzata di un mese
        dataInizio = calendar.getTime();

	}
	
	public void aggiungiGiorno(int anno, int mese, int giorno, int a,
	int cf, int gv, int p, int ric){
				tutto.add(new giorno(anno, mese, giorno, a, cf, gv, p,ric));
		}
		
	public void rimuoviGiorno(giorno v) {
		tutto.remove(v);
	}
	
	public giorno elementAt(int index) {
		// TODO Auto-generated method stub
		return tutto.get(index);
	}
	
	public int size() {
		ArrayList<giorno> app=this.getVisibili();
		if(app == null)
			return 0;
		return app.size();
	}
	public int superSize() {
		return tutto.size();
	}	
	public ArrayList<giorno> getVisibili(){
		if(this.getTutto().isEmpty())
			return new ArrayList<giorno>();
	
		ArrayList<giorno> app=new ArrayList<giorno>();
		for(giorno v: this.getTutto())
		{
			if ((v.getData().compareTo(this.getDataInizio()) >= 0 ) &&
				(v.getData().compareTo(this.getDataFine()) <= 0))
			{
				app.add(v);	
			}
		}
		return app;
	}
	
	@Override
	public String toString() {
		//innanzitutto se il bilancio è vuoto mi fermo e ritorno un messaggio
		if(tutto.isEmpty() )
			return "Bilancio vuoto";
		
		Collections.sort(tutto);
		//meccanismo che dovrebbe permettermi di vedere solo le op. relative alla data
		StringBuffer stringa = new StringBuffer() ;
		ArrayList<giorno> app=this.getVisibili();
		for(giorno v:app) {
			stringa.append(v+ " ");
			}
		return stringa.toString();
	}
	

	public int ricerca(String s, int i) {
		if (s.isEmpty())
			return -1;
		ArrayList<giorno> app=this.getVisibili();			
		for(;i<app.size(); i++) 
		{ //app.get(i).getDescrizione().contains(s) qualora si volesse fare la ricerca solo sulla descrizione
			if (	(app.get(i).toString()).contains(s) ) // controllo se contiene la stringa cercata
				{ return i;}
		}
			return -1;
	}
	
	
///////////////////Ammontare////////////////////////////////////////////////////////////////////////////////////////	
	public int totaleAmm() {
		if (tutto.isEmpty())
			return -1;
		ArrayList<giorno> app=this.getVisibili();
		int tot=0;
		for(giorno v:app) {
			tot+=v.getAmmontare();
		}
		return tot;
	}
	public int mediaAmm() {
		if (tutto.isEmpty())
			return -1;
		return this.totaleAmm()/this.size();
	}
	public int massimoAmm() {
		ArrayList<giorno> app=this.getVisibili();
		int indiceParziale=0;
		for(int i=0;i<app.size(); i++) 
		{ //app.get(i).getDescrizione().contains(s) qualora si volesse fare la ricerca solo sulla descrizione
			if (	app.get(i).getAmmontare() > app.get(indiceParziale).getAmmontare()) // controllo se contiene la stringa cercata
				{ indiceParziale = i;}
		}
		return indiceParziale;
	}
	///////////////////Caffe////////////////////////////////////////////////////////////////////////////////////////	
	public int totaleCaf() {
		if (tutto.isEmpty())
			return -1;
		ArrayList<giorno> app=this.getVisibili();
		int tot=0;
		for(giorno v:app) {
			tot+=v.getCaffeFatti();
			}
		return tot;
	}
	public int mediaCaf() {
		if (tutto.isEmpty())
			return -1;
		return this.totaleCaf()/this.size();
	}
	public int massimoCaf() {
	ArrayList<giorno> app=this.getVisibili();
	int indiceParziale=0;
	for(int i=0;i<app.size(); i++) 
	{ //app.get(i).getDescrizione().contains(s) qualora si volesse fare la ricerca solo sulla descrizione
	if (	app.get(i).getCaffeFatti() > app.get(indiceParziale).getCaffeFatti()) // controllo se contiene la stringa cercata
		{ indiceParziale = i;}
	}
	return indiceParziale;	
	}
	
	///////////////////Chiusure/////////////////////////////////////////////////////////////////////	
	public int totaleChiusure() {
		if (tutto.isEmpty())
			return -1;
		ArrayList<giorno> app=this.getVisibili();
		int tot=0;
		for(giorno v:app) {
			tot+=v.getChiusura();
			}
		return tot;
	}
	public int mediaChiusure() {
		if (tutto.isEmpty())
			return -1;
		return this.totaleChiusure()/this.size();
	}
	public int massimoChiusure() {
	ArrayList<giorno> app=this.getVisibili();
	int indiceParziale=0;
	for(int i=0;i<app.size(); i++) 
	{ //app.get(i).getDescrizione().contains(s) qualora si volesse fare la ricerca solo sulla descrizione
		if (	app.get(i).getChiusura() > app.get(indiceParziale).getChiusura()) // controllo se contiene la stringa cercata
			{ indiceParziale = i;}
	}
	return indiceParziale;
}
	///////////////////Pos/////////////////////////////////////////////////////////////////////	
	public int totalePos() {
		if (tutto.isEmpty())
			return -1;
		ArrayList<giorno> app=this.getVisibili();
		int tot=0;
		for(giorno v:app) {
			tot+=v.getPos();
			}
		return tot;
	}
	public int mediaPos() {
		if (tutto.isEmpty())
			return -1;
		return this.totalePos()/this.size();
	}
	public int massimoPos() {
	ArrayList<giorno> app=this.getVisibili();
	int indiceParziale=0;
	for(int i=0;i<app.size(); i++) 
	{ //app.get(i).getDescrizione().contains(s) qualora si volesse fare la ricerca solo sulla descrizione
	if (	app.get(i).getPos() > app.get(indiceParziale).getPos()) // controllo se contiene la stringa cercata
		{ indiceParziale = i;}
	}
	return indiceParziale;
	}
	
	///////////////////Ricariche/////////////////////////////////////////////////////////////////////	
	public int totaleRic() {
		if (tutto.isEmpty())
			return -1;
		ArrayList<giorno> app=this.getVisibili();
		int tot=0;
		for(giorno v:app) {
			tot+=v.getRicariche();
			}
		return tot;
	}
	public int mediaRic() {
		if (tutto.isEmpty())
			return -1;
		return this.totaleRic()/this.size();
	}
	public int massimoRic() {
	ArrayList<giorno> app=this.getVisibili();
	int indiceParziale=0;
	for(int i=0;i<app.size(); i++) 
	{ //app.get(i).getDescrizione().contains(s) qualora si volesse fare la ricerca solo sulla descrizione
	if (	app.get(i).getRicariche() > app.get(indiceParziale).getRicariche()) // controllo se contiene la stringa cercata
		{ indiceParziale = i;}
	}
	return indiceParziale;
	}
	
///////////////////Differenza/////////////////////////////////////////////////////////////////////	
	public int totaleDiff() {
	if (tutto.isEmpty())
		return -1;
	ArrayList<giorno> app=this.getVisibili();
	int tot=0;
	for(giorno v:app) {
		tot+=v.getDifferenza();
		}
	return tot;
	}
	public int mediaDiff() {
	if (tutto.isEmpty())
		return -1;
	return this.totaleDiff()/this.size();
	}
	public int massimoDiff() {
	ArrayList<giorno> app=this.getVisibili();
	int indiceParziale=0;
	for(int i=0;i<app.size(); i++) 
	{ //app.get(i).getDescrizione().contains(s) qualora si volesse fare la ricerca solo sulla descrizione
		if (	app.get(i).getDifferenza() > app.get(indiceParziale).getDifferenza()) // controllo se contiene la stringa cercata
				{ indiceParziale = i;}
	}
	return indiceParziale;
	}
}
