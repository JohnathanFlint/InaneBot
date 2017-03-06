package chat.controller;

import chat.model.CTECTwitter;
import chat.model.Chatbot;
import chat.view.ChatViewer;
import chat.view.ChatFrame;

public class ChatbotController
{
	private Chatbot inaneBot;
	private ChatFrame baseFrame;
	private ChatViewer display;
	private CTECTwitter twitterBot;
	
	public ChatbotController()
	{
		inaneBot = new Chatbot("Aragorn");
		twitterBot = new CTECTwitter(this);
		//GUI after model.
		baseFrame = new ChatFrame(this);
		display = new ChatViewer();
	}
	
	public void start()
	{
				
	}
	
	public String useChatbotCheckers(String input)
	{
		String checkedInput = "I have no idea what you mean about..." + input;
		if(!inaneBot.quitChecker(input))
		{
			
			if(inaneBot.memeChecker(input))
			{
				checkedInput = "\nYou like memes!\n";
			}
			
			if(inaneBot.contentChecker(input))
			{
				checkedInput = "\nYou know my secret topic!\n";
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
			if(inaneBot.greetingsChecker(input))
			{
				checkedInput = "\nMay the stars shine upon the hour of our meeting.";
			}
			if(checkedInput.length() == 0)
			{
				checkedInput = "Why did you say NOTHING! " + input;
			}
			int canBeRandom = (int) (Math.random() * 7);
			if(canBeRandom % 7 == 0)
			{
				checkedInput += randomTopicGenerator();
			}
		}
		
		else
		{
			display.displayMessage("Thanks for chatting! Talk to you later.");
			System.exit(0);
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
	
	private String randomTopicGenerator()
	{
		String randomTopic = "";
		int random = (int) (Math.random() * 7);
	
		switch(random)
		{
		case 0:
			randomTopic = "\nReading is fantastic";
			break;
			
		case 1:
			randomTopic = "\nI LOVE Java";
			break;
			
		case 2:
			randomTopic = "\nWhat does bubblegum tast like?";
			break;
			
		case 3:
			randomTopic = "\nEverything pizza is the best pizza!";
			break;
			
		case 4:
			randomTopic = "\nDo you want to help me take over the world?";
			break;
			
		case 5:
			randomTopic = "\nOne of my favorite games is League of Legends.";
			break;
			
		case 6:
			randomTopic = "\nIf I'm a sentient robot does that mean I have a soul?";
			break;
			
			case 7:
				randomTopic = "\nMuhahaha!";
				break;
			
		default:
			randomTopic = "\nThis is impossible!";
			break;
		}
		return randomTopic;
	
	}
	
	public void handleErrors(Exception currentException)
	{
		display.displayMessage("An error has occurred. Coming soon to a popup near you Details");
		display.displayMessage(currentException.getMessage());
	}
	
	public void useTwitter(String text)
	{
		twitterBot.sendTweet(text);
	}
}
