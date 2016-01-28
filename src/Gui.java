import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends JFrame {
	protected static char PLAYER = 'x';
	public Gui()
	{	
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,3));
		for(int i=0;i<9;i++){
			Tic b = new Tic(i);
			panel.add(b);
		}
				
		this.setTitle("Tic-Tac-Toe");
		this.setSize(300, 300);
		this.getContentPane().add(panel);
		this.setVisible(true);
	}
}


