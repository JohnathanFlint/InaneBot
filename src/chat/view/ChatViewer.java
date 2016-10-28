package chat.view;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ChatViewer 
{
	private String windowMessage;
	private ImageIcon chatIcon;
	
	public ChatViewer()
	{
		windowMessage = "This message is brought to you by the chatbot!";
		chatIcon = new ImageIcon(getClass().getResource("images/chatbot.png"));
	}
	
	public String collectResponse(String question)
	
	{
		String response = "";
		
		response = JOptionPane.showInputDialog(null, question);		
		
		return response;
	}
	
	public void displayMessage(String message)
	{
		JOptionPane.showMessageDialog(null,  message);
	}
	
	public int collectUserOption(String question)
	{
		int response = 0;
		
		response = JOptionPane.showConfirmDialog(null, response);
		
		return response;
	}
	
public String collectPictureResponse(String question, String path)
	
	{
		String userInput = "";
		if(!path.equals(null))
		{
			chatIcon = new ImageIcon(getClass().getResource(path));
		}
		
		userInput = JOptionPane.showInputDialog(null, question, windowMessage, JOptionPane.INFORMATION_MESSAGE, chatIcon, null, "Type here please!").toString();		
		
		return userInput;
	}

}
