package grafica;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.table.AbstractTableModel;

import strutture.raccolta;
import strutture.giorno;

/**
 * Classe che implementa la tabella a cui è associato un rancio
 * @author Luca Di Leo
 * 
 */
public class MyTableModel extends AbstractTableModel{
	 private static final long serialVersionUID = 1L;
	 /**
	  * rancio
	  */
	 private raccolta r;
	 /**
	  * Pannello da modificare
	  */
	 private MyPanel p;
	 /**
	  * Intestazione
	  */
	 private final String[] intestazione = {"Data", "Incasso", "Chiusura", 
			 "Pos", "Ricariche", "Differenza","Caffè"}; 
	/**
	 * Costruttore che copia il rancio e il totale
	 * @param r rancio
	 * @param totale totale
	 * @param p pannello
	 * 
	 */
	 public MyTableModel (raccolta r, MyPanel p) {
		 this.p=p;
		 this.r=r;
	 }
	 
	 @Override
	public boolean isCellEditable (int row, int column) {
		 if(row >= r.size() /* Se sono nella riga del totale*/
			|| (column == intestazione.length -2) /*Oppure sono nella colonna della differnza*/
			|| (column == 0)) /*Oppure sono nella colonna della data*/
			 return false;
		 return true; }
	 
	 @Override
	public void setValueAt(Object value, int row, int col) {
		 if(value==null) {//usato per un semplice aggiornamento della tabella
			 fireTableDataChanged();
			 p.revalidate();
			 p.repaint();
			 return;			 
		 }
		ArrayList<giorno> app=r.getVisibili();
		giorno v = (giorno)app.get(row);
		if(col == 1 )//controllo anche che il valore immesso sia effettivamente composto da numeri  
			v.setAmmontare(Integer.parseInt(value.toString()));
		if( col==2)
			v.setChiusura(Integer.parseInt(value.toString()));
		if( col==3)
			v.setPos(Integer.parseInt(value.toString()));
		if( col==4)
			v.setRicariche(Integer.parseInt(value.toString()));
		if(col==6)
			v.setCaffeFatti(Integer.parseInt(value.toString()));
		 // notifica il cambiamento
		fireTableDataChanged();
		 p.revalidate();
		 p.repaint();
	}
	 
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		 return r.size() +1;//per la riga del totale
	 }
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return intestazione.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Collections.sort(r.getTutto());
		/* 
		 	creo un array di appoggio, all'interno del quale verrano copiati
			tutti gli elementi che dovranno essere inseriti nella tabella, ossia
			tutti gli elementi che si trovano nel range di data visualizzare		
		 */
		ArrayList<giorno> copia = r.getVisibili(); 
		
		//controllo che effettivamente ci sia qualche elemento disponire
		if(copia.isEmpty()) {
			return null;
		}
		if (rowIndex >= copia.size()) {
				switch (columnIndex) {
	            case 0:
	            	return "Totale";
	            case 1:
	                return r.totaleAmm() ;
	            case 2:
	                return r.totaleChiusure() ;
	            case 3:
	                return r.totalePos() ;
	            case 4:
	                return r.totaleRic() ;
	            case 5:
	            	return r.totaleDiff() ;
	            case 6:
	                return r.totaleCaf() ;

	            default:
	                break;
				}
			}
		else {
	//costruisco la mia tabella usando l'array contenente solo le voci valide	
		giorno appoggio = (giorno) copia.get(rowIndex);
		switch (columnIndex)
		{
			case 0: return appoggio.getData();
			case 1: return appoggio.getAmmontare();
			case 2: return appoggio.getChiusura();
			case 3: return appoggio.getPos();
			case 4: return appoggio.getRicariche();
			case 5: return appoggio.getDifferenza();
			case 6: return appoggio.getCaffeFatti();

			default: ;
		}
	  }
		return null;
	} 
	/**
	 * Per ottenere il l'intestazione della tabella
	 */
	public String getColumnName(int col) {
		return intestazione[col];
	}
}
