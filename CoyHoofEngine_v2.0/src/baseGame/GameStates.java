package baseGame;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class GameStates {
	
	// State variables
	private static ArrayList<GameState> gameStates;
	private static GameState baseGameState;
	private static int currentState;
	
	// Frame variables
	private static Frame frame;
	private final String TITLE;
	
	// GameLoop variables externe au jeu
	private boolean gameRunning;
	private boolean gameLeaving;
	private static boolean showFPS;
	private static boolean fullscreen;
	
	// calcul FPS 
	private long lastLoopTime;
	private int targetFPS;
	private long optimalTime;
	private long lastFpsTime;
	private long fps;
	
	// Stock des valeurs des FPS
	private final int fpsSaveSize;
	private long[] fpsList;
	private int cpt;
	private static long fpsMax;
	private static long fpsAverage;
	private static long fpsMin;
	
	public GameStates(String TITLE, int PreferredWidth, int preferredHeight){
		this.gameStates = new ArrayList<GameState>();
		baseGameState = new BaseState();
		currentState = -1; // Cas special pour rendre le pannel par defaut
		
		this.TITLE = TITLE;
		frame = new Frame(TITLE, new Dimension(PreferredWidth,preferredHeight));
		
		gameRunning = true;
		gameLeaving = false;
		showFPS = true;
		fullscreen = true;
		
		lastLoopTime = System.nanoTime();
		targetFPS = 60;
		optimalTime = 1000000000 / targetFPS;
		lastFpsTime = 0;
		fps = 0;
		
		fpsSaveSize = 10;
		fpsList = new long[fpsSaveSize];
		for(int i = 0; i < fpsList.length; i++){
			fpsList[i] = targetFPS;
		}
		cpt = 0;
		fpsMax = Long.MIN_VALUE;
		fpsAverage = 0;
		fpsMin = Long.MAX_VALUE;
		
		frame.setContentPane(baseGameState);
		
		initStatesList();
		
		gameLoop();
		
	}
	
	public static void main(String[] args) {
		new GameStates("PONY", 1280, 720);
	}
	
	public void gameLoop(){
		
		while (gameRunning)
		{
			long time = System.nanoTime();
			long updateLength = time - lastLoopTime;
			lastLoopTime = time;
			
			double deltaTime = updateLength / ((double)optimalTime);
			
			lastFpsTime += updateLength;
			fps++;
			
			if (lastFpsTime >= 1000000000)
			{
				fpsList[cpt++] = fps;
				if(cpt%fpsSaveSize == 0){
					cpt = 0;
				}
				
				fpsMax = Long.MIN_VALUE;
				fpsAverage = 0;
				fpsMin = Long.MAX_VALUE;
				for(long i : fpsList){
					fpsAverage += i;
					if(i < fpsMin){
						fpsMin = i;
					}
					if(i > fpsMax){
						fpsMax = i;
					}
				}
				fpsAverage = fpsAverage/fpsSaveSize; 
				
				//System.out.println(fpsAverage);
				
				lastFpsTime = 0;
				fps = 0;
			}
			
			this.update(deltaTime);
			
			this.render();
			
			//System.out.println(this.toString());
			
			try {
				Thread.sleep( Math.max( (lastLoopTime-System.nanoTime() + optimalTime)/1000000 ,0) );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void render() {
		if(currentState >= 0)
			gameStates.get(currentState).render();
		else
			baseGameState.render();
	}

	private void update(double deltaTime) {
		if(currentState >= 0)
			gameStates.get(currentState).update(deltaTime);
		else
			baseGameState.update(deltaTime);
	}

	public void addState(GameState gs){
		this.gameStates.add(gs);
	}
	
	public void initStatesList(){
		addState( new BaseState2() );
		addState( new CopyOfBaseState2() );
	}
	
	public void enterState(int id){
		currentState = id;
		if(currentState >= 0)
			frame.setContentPane(gameStates.get(currentState));
		else
			frame.setContentPane(baseGameState);

	}
	
	public void setTargetFrameRate(int targetFPS){
		this.targetFPS = targetFPS;
		this.optimalTime = 1000000000 / targetFPS;
	}
	
	public void setShowFPS(boolean b){
		showFPS = b;
	}
	
	public static long getFps() {
		return fpsAverage;
	}

	public static long getFpsMax() {
		return fpsMax;
	}

	public static long getFpsMin() {
		return fpsMin;
	}

	public static boolean isShowFPS() {
		return showFPS;
	}

	public static boolean isFullscreen() {
		return fullscreen;
	}

	public static void setFullscreen(boolean fullscreen) {
		GameStates.fullscreen = fullscreen;
	}

	public static Frame getFrame() {
		return frame;
	}

	
	public static void keyPressed(KeyEvent e) {
		if(currentState >= 0)
			gameStates.get(currentState).keyPressed(e);
		else
			baseGameState.keyPressed(e);
	}

	public static void keyReleased(KeyEvent e) {
		if(currentState >= 0)
			gameStates.get(currentState).keyReleased(e);
		else
			baseGameState.keyReleased(e);		
	}

	public static void keyTyped(KeyEvent e) {
		if(currentState >= 0)
			gameStates.get(currentState).keyTyped(e);
		else
			baseGameState.keyTyped(e);		
	}

	public static void mouseClicked(MouseEvent e) {
		if(currentState >= 0)
			gameStates.get(currentState).mouseClicked(e);
		else
			baseGameState.mouseClicked(e);		
	}

	public static void mouseEntered(MouseEvent e) {
		if(currentState >= 0)
			gameStates.get(currentState).mouseEntered(e);
		else
			baseGameState.mouseEntered(e);		
	}

	public static void mouseExited(MouseEvent e) {
		if(currentState >= 0)
			gameStates.get(currentState).mouseExited(e);
		else
			baseGameState.mouseExited(e);
	}

	public static void mousePressed(MouseEvent e) {
		if(currentState >= 0)
			gameStates.get(currentState).mousePressed(e);
		else
			baseGameState.mousePressed(e);		
	}

	public static void mouseReleased(MouseEvent e) {
		if(currentState >= 0)
			gameStates.get(currentState).mouseReleased(e);
		else
			baseGameState.mouseReleased(e);		
	}

	public static void mouseDragged(MouseEvent e) {
		if(currentState >= 0)
			gameStates.get(currentState).mouseDragged(e);
		else
			baseGameState.mouseDragged(e);
	}

	public static void mouseMoved(MouseEvent e) {
		if(currentState >= 0)
			gameStates.get(currentState).mouseMoved(e);
		else
			baseGameState.mouseMoved(e);	
	}

	public static void mouseWheelMoved(MouseWheelEvent e) {
		if(currentState >= 0)
			gameStates.get(currentState).mouseWheelMoved(e);
		else
			baseGameState.mouseWheelMoved(e);		
	}
	
	@Override
	public String toString() {
		return "GameStates [ TITLE= " + TITLE + ", currentState= " + currentState+ ", gameStates= " + gameStates + " ]";
	}
	
}
