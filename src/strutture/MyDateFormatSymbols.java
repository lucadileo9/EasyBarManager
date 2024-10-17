package strutture;

import java.text.DateFormatSymbols;

public class MyDateFormatSymbols extends DateFormatSymbols {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
    public String[] getMonths() {
        String[] months = new String[] {
            "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno",
            "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"
        };
        return months;
    }
	
	@Override
    public String[] getShortWeekdays() {
        String[] weekdays = new String[] {
            " ", "Dom\t","Lun\t", "Mar\t", "Mer\t", "Gio\t",
            "Ven\t", "Sab\t"
        };
        return weekdays;
    }
}