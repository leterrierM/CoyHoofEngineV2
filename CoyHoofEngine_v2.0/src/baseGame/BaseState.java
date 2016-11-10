package baseGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class BaseState extends GameState {
	
	private int mouseX;
	private int mouseY;
	private boolean isDragging;
	private boolean isPressed;
	private boolean isClicking;
	private int wheelOffset;
	private String str = "";
	
	
	public BaseState(){

	}
	
	public synchronized void update(double deltaTime) {
		//System.out.println("RD is best pony");
	}
	
	public synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor( new Color( 0xFFFFFF - getBackground().getRGB() ) );
		
		g.drawString( "( " + mouseX + ", " + mouseY + " ), Drag=" + isDragging, mouseX, mouseY + wheelOffset);
		
		if(isPressed){
			g.drawString( "Stahp pressin' mah !!", mouseX, mouseY - 10 + wheelOffset);
		}
		
		if(isClicking){
			g.drawString( "Click !", mouseX, mouseY - 20 + wheelOffset);
			isClicking = false;
		}
		
		g.drawString( str, 0, 100 );
		
		g.drawString("ID : " + Integer.toString(getID()), 0, 20);
		g.drawString("Showing Default gameSate", 0, 30);
	}

	@Override
	public String toString() {
		return "BaseState2 [ ID=" + this.getID() + " ]";
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode() + "=====");
		switch ( e.getKeyCode() ){
		case 8: str = str.substring( 0, Math.max(str.length()-1,0) );
				System.out.println(e.getKeyChar());
				break;
		default: str += e.getKeyChar(); 
				 break;
		}
		System.out.println("str : "+str);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		isClicking = true;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		isPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isDragging = false;
		isPressed = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		isDragging = true;
		mouseX=e.getX();
		mouseY=e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX=e.getX();
		mouseY=e.getY();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println(e);		
		wheelOffset += e.getScrollAmount()*(int)e.getPreciseWheelRotation();
	}
	
}
