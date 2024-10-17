package grafica;

import javax.swing.*;

/**
 * Classe che implementa un frame con le giuste 
 * dimensioni e a cui "attaccarci" un bilancio
 * @author Luca Di Leo
 *
 */
public class MyFrame extends JFrame
{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Inizializza il bilancio, setta il titolo del frame
	 * e stabilisce le dimensioni del frame
	 * @param titolo titolo del frame
	 */
	public MyFrame(String titolo){ 
		super(titolo);

		this.setSize(getMaximumSize());
		this.setLocationRelativeTo(null);//per centrare il frame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}	
}