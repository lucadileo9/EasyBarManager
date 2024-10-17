package strutture;

import java.awt.Frame;

import grafica.MyFrame;
import grafica.MyPanel;

public class MainTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		raccolta r = new raccolta();
		r.aggiungiGiorno(2020, 1, 15, 1250, 150, 20, 50, 90);
		r.aggiungiGiorno(2020, 2, 2, 980, 120, 50, 40, 70);
		r.aggiungiGiorno(2020, 3, 20, 1800, 220, 30, 90, 130);
		r.aggiungiGiorno(2020, 4, 5, 2100, 190, 45, 80, 110);
		r.aggiungiGiorno(2020, 5, 12, 1500, 180, 25, 55, 80);
		r.aggiungiGiorno(2020, 6, 25, 2800, 260, 40, 70, 140);
		r.aggiungiGiorno(2020, 7, 8, 3200, 270, 50, 100, 190);
		r.aggiungiGiorno(2020, 8, 14, 2650, 120, 35, 75, 130);
		r.aggiungiGiorno(2020, 9, 29, 1800, 160, 45, 85, 120);
		r.aggiungiGiorno(2020, 10, 17, 1400, 150, 30, 60, 100);
		r.aggiungiGiorno(2021, 1, 7, 1850, 130, 25, 55, 90);
		r.aggiungiGiorno(2021, 2, 19, 2100, 140, 30, 65, 110);
		r.aggiungiGiorno(2021, 3, 8, 1450, 130, 20, 45, 85);
		r.aggiungiGiorno(2021, 4, 21, 2400, 170, 35, 75, 130);
		r.aggiungiGiorno(2021, 5, 3, 1320, 115, 22, 50, 80);
		r.aggiungiGiorno(2021, 6, 11, 1750, 125, 28, 60, 100);
		r.aggiungiGiorno(2021, 7, 27, 2950, 150, 30, 70, 140);
		r.aggiungiGiorno(2021, 8, 5, 2750, 145, 26, 58, 120);
		r.aggiungiGiorno(2022, 1, 9, 1620, 135, 23, 52, 95);
		r.aggiungiGiorno(2022, 2, 13, 1980, 140, 24, 54, 100);
		r.aggiungiGiorno(2022, 3, 30, 2050, 150, 26, 58, 110);
		r.aggiungiGiorno(2022, 4, 18, 1450, 130, 22, 50, 90);
		r.aggiungiGiorno(2022, 5, 22, 3120, 160, 30, 70, 130);
		r.aggiungiGiorno(2022, 6, 7, 2800, 155, 28, 65, 120);
		r.aggiungiGiorno(2022, 7, 15, 1350, 125, 21, 48, 85);
		r.aggiungiGiorno(2022, 8, 28, 1920, 140, 25, 56, 100);
		//mese corrente
		r.aggiungiGiorno(2023, 9, 1, 2500, 120, 30, 60, 80);
		r.aggiungiGiorno(2023, 9, 2, 1800, 150, 20, 45, 120);
		r.aggiungiGiorno(2023, 9, 3, 3000, 130, 15, 70, 90);
		r.aggiungiGiorno(2023, 9, 4, 2100, 110, 40, 55, 70);
		r.aggiungiGiorno(2023, 9, 5, 2700, 140, 25, 65, 110);
		r.aggiungiGiorno(2023, 9, 6, 1500, 160, 18, 50, 75);
		r.aggiungiGiorno(2023, 9, 7, 2200, 120, 30, 58, 95);
		r.aggiungiGiorno(2023, 9, 8, 2800, 130, 22, 75, 105);
		r.aggiungiGiorno(2023, 9, 9, 1900, 145, 35, 62, 85);
		r.aggiungiGiorno(2023, 9, 10, 2600, 125, 28, 68, 115);
		r.aggiungiGiorno(2023, 9, 20, 2300, 135, 38, 80, 125);
		r.aggiungiGiorno(2023, 9, 30, 1700, 155, 31, 52, 80);

		//mese scorso
		r.aggiungiGiorno(2023, 8, 1, 2500, 95, 33, 72, 180);
		r.aggiungiGiorno(2023, 8, 2, 1800, 120, 20, 64, 150);
		r.aggiungiGiorno(2023, 8, 3, 2100, 88, 28, 49, 220);
		r.aggiungiGiorno(2023, 8, 4, 2900, 105, 35, 78, 130);
		r.aggiungiGiorno(2023, 8, 5, 1600, 75, 22, 42, 270);
		r.aggiungiGiorno(2023, 8, 6, 2200, 100, 31, 68, 160);
		r.aggiungiGiorno(2023, 8, 7, 2800, 110, 30, 55, 190);
		r.aggiungiGiorno(2023, 8, 8, 2400, 92, 27, 60, 210);
		r.aggiungiGiorno(2023, 8, 9, 2700, 115, 29, 73, 140);
		r.aggiungiGiorno(2023, 8, 10, 1900, 80, 24, 47, 250);
		r.aggiungiGiorno(2023, 8, 20, 2600, 98, 32, 69, 170);
		r.aggiungiGiorno(2023, 8, 30, 2100, 105, 26, 62, 200);
//		System.out.println(r.massimo());
//		System.out.println(r.ricerca("1333",0));
//		System.out.println(r.elementAt(r.ricerca("1333",0)));
		

		MyFrame f = new MyFrame("Raccolta");
		MyPanel p= new MyPanel(r);
		f.add(p);
		f.setVisible(true);
        f.setExtendedState(Frame.MAXIMIZED_BOTH);//per rendelo a schermo intero
//*/
	}

}
