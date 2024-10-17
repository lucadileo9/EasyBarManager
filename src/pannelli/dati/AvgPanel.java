package pannelli.dati;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import pannelli.BasePanel;
import strutture.raccolta;

public class AvgPanel extends BasePanel{

	private static final long serialVersionUID = 1L;

	protected JLabel avgAmm, avgCaffe, avgCh, avgPos, avgRic, avgDiff;
	
	public AvgPanel(raccolta r) {
		super(r, "");
		super.removeAll(); //rimuovo tutti i componenti, poichè il pannello sarà 

		avgAmm= new JLabel(" Media incasso: "+ r.mediaAmm()+ " €");
		avgCh= new JLabel(" Media chiusure: "+ r.mediaChiusure()+ " €");
		avgCaffe= new JLabel(" Media caffe fatti: "+ r.mediaCaf()+ " ");
		avgPos= new JLabel(" Media incasso tramite pos: "+ r.mediaPos()+ " €");
		avgRic= new JLabel(" Media  incasso tramite ricariche: "+ r.mediaRic()+ " €   ");
		avgDiff= new JLabel(" Media differenze: "+ r.mediaDiff()+ " €   ");
		// TODO Auto-generated constructor stub
		
		Font timesNewRomanFont = new Font("Times New Roman", Font.PLAIN, 20);
		avgAmm.setFont(timesNewRomanFont);
		avgCaffe.setFont(timesNewRomanFont);
		avgCh.setFont(timesNewRomanFont);
		avgPos.setFont(timesNewRomanFont);
		avgRic.setFont(timesNewRomanFont);
		avgDiff.setFont(timesNewRomanFont);

		//nord
		JPanel nord = new JPanel ();
		nord.setLayout(new BorderLayout());
		nord.add(avgAmm,BorderLayout.NORTH);
		nord.add(avgCh,BorderLayout.SOUTH);

		//centro
		JPanel centro = new JPanel ();
		centro.setLayout(new BorderLayout());
		centro.add(avgPos, BorderLayout.NORTH);
		centro.add(avgRic, BorderLayout.SOUTH);
		
		//sud
		JPanel sud = new JPanel ();
		sud.setLayout(new BorderLayout());
		sud.add(avgDiff, BorderLayout.NORTH);
		sud.add(avgCaffe, BorderLayout.SOUTH);
		
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
