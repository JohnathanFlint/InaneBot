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
		baseFrame = new ChatFrame
	}
	
	public void start()
	{
		String pResponse = display.collectPictureResponse("What do you want to talk about!?!", null);
		String response = display.collectResponse("What do you want to talk about!?!");
		
		while(inaneBot.lengthChecker(response))
		{
			display.displayMessage(useChatbotCheckers(response));
			response = display.collectResponse("Oh, you want to talk about " + response + "? Tell me more...");
		}
		
		
	}
	
	private String useChatbotCheckers(String input)
	{
		String checkedInput = "I have no idea what you mean about..." + input;
		
		if(inaneBot.memeChecker(input))
		{
			checkedInput += "\nYou like memes!\n";
		}
		
		if(inaneBot.contentChecker(input))
		{
			checkedInput += "\nYou know my secret topic!\n";
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
			checkedInput = "\n actually don't use twitter.";
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
		return checkedInput;
	}
}
