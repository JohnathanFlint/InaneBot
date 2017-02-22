package chat.view;

import javax.swing.JFrame;

import chat.controller.ChatbotController;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;


public class ChatFrame extends JFrame
{
	private ChatbotController baseController;
	private ChatPanel appPanel;
	
	public ChatFrame (ChatbotController baseController)
	{
		super();
		this.baseController = baseController;
		appPanel = new ChatPanel(baseController);
		
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(appPanel);
		this.setTitle("Chatbot");
		GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = screen.getDisplayMode().getWidth();
		int height = screen.getDisplayMode().getHeight();
		int windowWidth = width - width/2;
		int windowHeight = height - height/2;
		int xPos = (width - windowWidth)/2;
		int yPos = (height - windowHeight)/2;
		this.setLocation(xPos, yPos);
		this.setSize(new Dimension(windowWidth, windowHeight));
		this.setResizable(false);
		this.setVisible(true);
	}

	public Object getBaseController()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
