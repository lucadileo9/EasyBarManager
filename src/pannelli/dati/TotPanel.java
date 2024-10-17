package pannelli.dati;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import pannelli.BasePanel;
import strutture.raccolta;

public class TotPanel extends BasePanel{

	private static final long serialVersionUID = 1L;

	protected JLabel totAmm, totCaffe, totCh, totPos, totRic, totDiff;
	
	public TotPanel(raccolta r) {
		super(r, "");
		super.removeAll(); //rimuovo tutti i componenti, poichè il pannello sarà 

		totAmm= new JLabel(" Totale incasso: "+ r.totaleAmm()+ " €");
		totCaffe= new JLabel(" Totale caffe fatti: "+ r.totaleCaf()+ " ");
		totCh= new JLabel(" Totale chiusure: "+ r.totaleChiusure()+ " €");
		totPos= new JLabel(" Totale incasso tramite pos: "+ r.totalePos()+ " €");
		totRic= new JLabel(" Totale incasso tramite ricariche: "+ r.totaleRic()+ " €   ");
		totDiff= new JLabel(" Totale differenze: "+ r.totaleDiff()+ " €   ");
		// TODO Auto-generated constructor stub
		
		Font timesNewRomanFont = new Font("Times New Roman", Font.PLAIN, 20);
		totAmm.setFont(timesNewRomanFont);
		totCaffe.setFont(timesNewRomanFont);
		totCh.setFont(timesNewRomanFont);
		totPos.setFont(timesNewRomanFont);
		totRic.setFont(timesNewRomanFont);
		totDiff.setFont(timesNewRomanFont);

		//nord
		JPanel nord = new JPanel ();
		nord.setLayout(new BorderLayout());
		nord.add(totAmm,BorderLayout.NORTH);
		nord.add(totCh,BorderLayout.SOUTH);

		//centro
		JPanel centro = new JPanel ();
		centro.setLayout(new BorderLayout());
		centro.add(totPos,BorderLayout.NORTH);
		centro.add(totRic, BorderLayout.SOUTH);

		//sud
		JPanel sud = new JPanel ();
		sud.setLayout(new BorderLayout());
		sud.add(totDiff,BorderLayout.NORTH);
		sud.add(totCaffe, BorderLayout.SOUTH);
		
		this.setLayout(new BorderLayout());
		this.add(nord,BorderLayout.NORTH);
		this.add(centro,BorderLayout.CENTER);
		this.add(sud,BorderLayout.SOUTH);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
