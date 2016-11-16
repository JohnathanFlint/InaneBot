package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatViewer;
import chat.view.ChatFrame;

public class ChatbotController
{
	private Chatbot inaneBot;
	private ChatFrame baseFrame;
	
	public ChatbotController()
	{
		inaneBot = new Chatbot("Aragorn");
		baseFrame = new ChatFrame(this);
		
	}
	
	public void start()
	{
		
		
	}
	
	public String useChatbotCheckers(String input)
	{
		String checkedInput = "I have no idea what you mean about..." + input;
		
		if(inaneBot.memeChecker(input))
		{
			checkedInput = "\nYou like memes!\n";
		}
		
		if(inaneBot.contentChecker(input))
		{
			checkedInput = "\nYou know my secret topic!\n";
		}
		if(inaneBot.quitChecker(input))
		{
			System.exit(0);
		}
		if(inaneBot.politicalTopicChecker(input))
		{
			checkedInput = "\nPolotics are not allowed here!";
		}
		if(inaneBot.twitterChecker(input))
		{
			checkedInput = "\nI actually don't use twitter.";
		}
		if(inaneBot.keyboardMashChecker(input))
		{
			checkedInput = "\nNO MASHING!!! :-(";
		}
		if(inaneBot.inputHTMLChecker(input))
		{
			checkedInput = "\nDo you know html too?";
		}
		if(checkedInput.length() == 0)
		{
			checkedInput = "Why did you say NOTHING! " + input;
		}
			
		if(inaneBot.greetingsChecker(input))
		{
			checkedInput = "\nMay the stars shine upon the hour of our meeting.";
		}
		
		return checkedInput;
	
	}
	
	public ChatFrame getBaseFrame()
	{
		return baseFrame;
	}
	
	public Chatbot getChatbot()
	{
		return inaneBot;
	}
}
