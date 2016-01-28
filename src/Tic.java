import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Tic extends JPanel{
	private static final long serialVersionUID = 1L;
	
	protected static Long l;
	protected static int list[] = {3, 5, 7, 11, 13, 17, 19, 23, 29};
	private static Border BlackBorder = null;
	private static Border RedBorder = null;
	public int id;
	private char x;
	Tic(int i){
		super();
		
		//create an identifier
		id = list[i];
		
		x = ' ';
		if(Tic.BlackBorder == null)
			BlackBorder = BorderFactory.createLineBorder(Color.BLACK);
		if(Tic.RedBorder == null)
			RedBorder = BorderFactory.createLineBorder(Color.RED);
		
		if(Tic.l == null){
			Tic.l = new Long(1);
			for(int j=0;j<9;j++)
				Tic.l = Tic.l * list[j];
		}
		
		this.setBorder(BlackBorder);
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("", SwingConstants.CENTER);
		label.setFont(new Font("Arial" , Font.BOLD, 68));
		this.add(label);
		Tic temp = this;
		this.addMouseListener(new MouseListener() {				
			@Override
			public void mouseExited(MouseEvent e) {
				temp.setBorder(BlackBorder);	
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				temp.setBorder(RedBorder);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(temp.isAvailable()){
					temp.x = Gui.PLAYER;
					label.setText(String.valueOf(temp.x));
					Gui.PLAYER = Gui.PLAYER == 'x' ? 'o':'x';
					temp.check();
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		
		
	}
	
	private void check(){
		Long sumx=new Long(1),sumy=new Long(1);
		for(int i = 0;i<9;i++){
			Tic temp = (Tic)(this.getParent().getComponents()[i]);
			if(temp.getLetter() == 'x'){
				sumx = sumx * temp.id;
			}
			if(temp.getLetter() == 'o'){
				sumy = sumy * temp.id;
			}					
		}
		if(this.checkWin(sumx)){
	        JOptionPane.showMessageDialog(null, "Player X wins!", "Winner" , JOptionPane.INFORMATION_MESSAGE);
			for(int i=0;i<this.getParent().getComponentCount();i++)
				((Tic)this.getParent().getComponent(i)).clear();
		}
		if(this.checkWin(sumy)){
	        JOptionPane.showMessageDialog(null, "Player O wins!", "Winner" , JOptionPane.INFORMATION_MESSAGE);
	        for(int i=0;i<this.getParent().getComponentCount();i++)
				((Tic)this.getParent().getComponent(i)).clear();
		}
		Long l = new Long(sumx*sumy);
		if(l.equals(new Long(Tic.l))){
	        JOptionPane.showMessageDialog(null, "Tie!", ":( Tie" , JOptionPane.INFORMATION_MESSAGE);
	        for(int i=0;i<this.getParent().getComponentCount();i++)
				((Tic)this.getParent().getComponent(i)).clear();
		}
		
	}
	private boolean checkWin(long sumx){
		int R1 = Tic.list[0]*Tic.list[1]*Tic.list[2];
		int R2 = Tic.list[3]*Tic.list[4]*Tic.list[5];
		int R3 = Tic.list[6]*Tic.list[7]*Tic.list[8];
		int C1 = Tic.list[0]*Tic.list[3]*Tic.list[6];
		int C2 = Tic.list[1]*Tic.list[4]*Tic.list[7];
		int C3 = Tic.list[2]*Tic.list[5]*Tic.list[8];
		int D1 = Tic.list[0]*Tic.list[4]*Tic.list[8];
		int D2 = Tic.list[2]*Tic.list[4]*Tic.list[6];
		System.out.println("Sum:" + sumx);
		System.out.println("R1:" + sumx);
		return  sumx%R1==0 || sumx%R2==0 || sumx%R3==0 ||
				sumx%C1==0 || sumx%C2==0 || sumx%C3==0 ||
				sumx%D1==0 || sumx%D2==0;
	}
	
	public boolean isAvailable(){
		return x == ' ';
	}
	public void setLetter(char x){
		this.x = x;
	}
	public char getLetter(){
		return x;
	}
	public void clear(){
		this.x = ' ';
		((JLabel)(this.getComponent(0))).setText("");
	}
}
