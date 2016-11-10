package baseGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class BaseState2 extends GameState {
	
	public BaseState2(){
			this.setBackground(Color.CYAN);
	}
	
	public synchronized void update(double deltaTime) {
	}
	
	public synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor( new Color( 0xFFFFFF - getBackground().getRGB() ) );
		g.drawString("ID : " + Integer.toString(getID()), 0, 20);
		g.drawString("Showing BaseState2", 0, 30);
	}
	
	@Override
	public String toString() {
		return "BaseState2 [ ID=" + this.getID() + " ]";
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
	}
	
}
