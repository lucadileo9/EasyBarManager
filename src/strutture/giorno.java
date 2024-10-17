/**
 * 
 */
package strutture;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
/**
 * @author Luca
 *
 */
public class giorno implements Comparable<giorno>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Data
	 */
	private Date data; 
	/**
	 * Ammontare del guadagno 
	 */
	private int ammontare;
	/**
	 * Caffe fatti in un giorno
	 */
	private int caffeFatti;
	
	/**
	 * Pagamenti effettuati dai clienti col pos
	 */
	private int pos;
	/**
	 * Soldi incasssati da ricariche telefoniche e postepay
	 */
	private int ricariche;
	/**
	 * Soldi segnati dalla cassa
	 */
	 private int chiusura;

	 public giorno(int anno, int mese, int giorno, int a,
			int c, int p, int ric,int cf) {
		setData(anno, mese, giorno);
		setAmmontare(a);
		setChiusura(c);
		setPos(p);
		setRicariche(ric);
		setCaffeFatti(cf);

	}
	
	public Date getData() {	
		return data; 
	}
	public void setData(int anno, int mese, int giorno) {
		/*if (anno==0 && mese==0 && giorno==0)
		{ //valori per inserire la data di default, cioè oggi
			this.data = new Date();
			return;
		}*/
		
		//codice per immettere una data qualsiasi
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
		calendar.set(anno, mese-1, giorno);
		this.data = calendar.getTime();
	}
	
	/**
	 * getter
	 * @return the ammontare
	 */
	public int getAmmontare() {
		return ammontare;
	}
	/**
	 * setter
	 * @param a the ammontare to set
	 */
	public void setAmmontare(int a) {
		this.ammontare=a;
	}
	/**
	 * getter
	 * @return Chiusura
	 */
	public int getChiusura() {
		return chiusura;
	}
	/**
	 * setter
	 * @param a the Chiusura to set
	 */
	public void setChiusura(int a) {
		this.chiusura=a;
	}
	public int getCaffeFatti() {
		return caffeFatti;
	}
	
	public void setCaffeFatti(int a) {
		this.caffeFatti=a;
	}

	public int getPos() {
		return pos;
	}
	public void setPos(int a) {
		this.pos=a;
	}
	
	public int getRicariche() {
		return ricariche;
	}
	public void setRicariche(int a) {
		this.ricariche=a;
	}
	/**
	 * getter
	 * @return the Differenza
	 */
	public int getDifferenza() {
		return this.ammontare+this.pos-this.ricariche-this.chiusura;
	}
	
	
	/**
	 * ritorna la data in formato stringa per stamparla
	 * @return the data
	 */
	public String printData() {	
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd", new MyDateFormatSymbols());
		return dateFormat.format(data);
	}
	
	@Override
	public String toString() { 
		return printData()+" "+getAmmontare()+" "+getChiusura()+" "+getCaffeFatti()+" "+getPos()+" "+getRicariche();
	}

	@Override
	public int compareTo(giorno o) {
		// TODO Auto-generated method stub
		return this.data.compareTo(o.data);
	}
}

