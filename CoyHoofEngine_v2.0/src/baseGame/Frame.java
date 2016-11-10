package baseGame;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

public class Frame extends JFrame implements KeyListener,MouseListener,MouseMotionListener,MouseWheelListener{
	
	private static final long serialVersionUID = 1L;
	
	GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice device = environment.getDefaultScreenDevice();

	public Frame(String TITTLE, Dimension dimension){
		super(TITTLE);
		
		this.setSize(dimension);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.setFocusable(true);
		
		setFullScreen();
		
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
		
		this.requestFocus();
	}

	public Dimension setFullScreen(){
		this.dispose();
		this.setUndecorated(true);
		this.setVisible(true);
		device.setFullScreenWindow(this);
		this.setLocationRelativeTo(null);
		return this.getSize();
	}
	
	public Dimension setWindowed(){
		this.dispose();
		this.setUndecorated(false);
		this.setVisible(true);
		device.setFullScreenWindow(null);
		this.setLocationRelativeTo(null);
		return this.getSize();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		GameStates.mouseWheelMoved(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		GameStates.mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		GameStates.mouseMoved(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		GameStates.mouseClicked(e);		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		GameStates.mouseEntered(e);		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		GameStates.mouseExited(e);		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GameStates.mousePressed(e);		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		GameStates.mouseReleased(e);		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		GameStates.keyPressed(e);		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		GameStates.keyReleased(e);		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		GameStates.keyTyped(e);		
	}
	
}
