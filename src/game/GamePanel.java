package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import GameState.GameStateManager;

public class GamePanel extends JPanel implements Runnable, KeyListener
{
	private GameStateManager gameStateManager;
	private long start;
	private long elapsed;
	private long waitTime;
	private Thread gameThread;
	private boolean isGameRunning;
	private int FPS;
	private long targetTime;
	private BufferedImage backgroundImage;
	private Graphics2D graphics;
	
	public GamePanel()
	{
		super();
		gameStateManager = new GameStateManager();
		
		isGameRunning = true;
		FPS = 60;
		targetTime = 1000 / FPS;
		backgroundImage = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
		graphics = (Graphics2D) graphics;
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify()
	{
		super.addNotify();
		if(gameThread == null)
		{
			gameThread = new Thread(this);
			addKeyListener(this);
			gameThread.start();
		}
	}
	private void update()
	{
		gameStateManager.updateGameState();
	}
	private void draw()
	{
		gameStateManager.draw(graphics);
	}
	private void drawToScreen()
	{
		Graphics backgroundGraphics = getGraphics();
		backgroundGraphics.drawImage(backgroundImage, 0, 0, null);
		backgroundGraphics.dispose();
	}
	
	public void run()
	{
		//Game Loop
		while(isGameRunning)
		{
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			waitTime = targetTime - elapsed / 1000000;
			
			catchExeption();
		}
	}
	
	private void catchExeption()
	{
		try
		{
			Thread.sleep(waitTime);
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void keyTyped(KeyEvent keyTyped)
	{
		
	}
	public void keyPressed(KeyEvent keyPressed)
	{
		gameStateManager.keyPressed(keyPressed.getKeyCode());
	}
	public void keyReleased(KeyEvent keyReleased)
	{
		gameStateManager.keyPressed(keyReleased.getKeyCode());
	}
}
