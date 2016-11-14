package chat.view;

import javax.swing.JFrame;
import chat.controller.ChatbotController;
import java.awt.Dimension;

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
		this.setVisible(true);
	}
}
