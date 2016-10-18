package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatViewer;

public class ChatbotController
{
	private Chatbot inaneBot;
	private ChatViewer display;
	
	public ChatbotController()
	{
		inaneBot = new Chatbot("Aragorn");
		display = new ChatViewer();
	}
	
	public void start()
	{
		String response = "words";
		
		while(inaneBot.lengthChecker(response))
		{
			
			response = display.collectResponse("What do you want to talk about!?!");
		}
		
		
	}
}
