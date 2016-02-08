package GameState;

import java.util.ArrayList;

public class GameStateManager
{
	private MenuState menuState;
	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public GameStateManager()
	{
		menuState = new MenuState(this);
		gameStates = new ArrayList<GameState>();
		currentState = 0;
		gameStates.add(menuState);
	}
	
	private void setState(int gameState)
	{
		currentState = gameState;
		gameStates.get(currentState).init();
	}
	
	public void updateGameState()
	{
		gameStates.get(currentState).update();
	}
	
	public void draw(java.awt.Graphics2D graphics)
	{
		gameStates.get(currentState).draw(graphics);
	}
	
	public void keyPressed(int key)
	{
		gameStates.get(currentState).keyPresssed(key);
	}
	public void keyReleaed(int key)
	{
		gameStates.get(currentState).keyReleased(key);
	}
}
