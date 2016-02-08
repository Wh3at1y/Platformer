package game;

import javax.swing.JFrame;

public class GameFrame extends JFrame
{
	private GamePanel basePanel;
	
	public GameFrame()
	{
		basePanel = new GamePanel();
		
		setupFrame();
	}
	
	private void setupFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setContentPane(basePanel);
		setSize(640,480);
		setTitle("2D Platformer");
		setVisible(true);
	}
}
