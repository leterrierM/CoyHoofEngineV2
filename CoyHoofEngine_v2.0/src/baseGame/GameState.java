package baseGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.JPanel;

public abstract class GameState extends JPanel{

	public static int IDcpt = -1;
	public int ID;
	
	public GameState(){
		this.ID = GameState.IDcpt;
		IDcpt++;
		
		setBackground(Color.BLACK);
		
	}

	public synchronized void update(double deltaTime) {
	}

	public synchronized void render() {
		repaint();
	}
	
	@Override
	public synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	@Override
	protected void paintChildren(Graphics g){
	    super.paintChildren(g);
	    //String hex = Integer.toHexString(getBackground().getRGB()).substring(2).toUpperCase();
	    //int col = 0x111111;
	    //System.out.println(col);
	    //hex = String.format("06X", (0xFFFFFF - col));
	    //g.setColor( getBackground().getRGB());
	    //g.setColor( new Color( Integer.valueOf( hex.substring( 1, 3 ), 16 ), Integer.valueOf( hex.substring( 3, 5 ), 16 ), Integer.valueOf( hex.substring( 5, 7 ), 16 ) ) );
		g.setColor( new Color( 0xFFFFFF - getBackground().getRGB() ) );
	    if(GameStates.isShowFPS()){
			g.drawString("FPS : " + Long.toString( GameStates.getFps() ), 0, 10);
		}
	    
	}

	public int getID() {
		return this.ID;
	}
	
	public void toggleFullscreen(){
		if(!GameStates.isFullscreen()){
			GameStates.getFrame().setFullScreen();
		} else {
			GameStates.getFrame().setWindowed();
		}
		GameStates.setFullscreen(!GameStates.isFullscreen());
	}


	public abstract void keyPressed(KeyEvent e);

	public abstract void keyReleased(KeyEvent e);

	public abstract void keyTyped(KeyEvent e);

	public abstract void mouseClicked(MouseEvent e);

	public abstract void mouseEntered(MouseEvent e);

	public abstract void mouseExited(MouseEvent e);

	public abstract void mousePressed(MouseEvent e);

	public abstract void mouseReleased(MouseEvent e);

	public abstract void mouseDragged(MouseEvent e);

	public abstract void mouseMoved(MouseEvent e);

	public abstract void mouseWheelMoved(MouseWheelEvent e);
	
	@Override
	public String toString() {
		return "GameState [ ID=" + this.getID() + " ]";
	}
	
}
