package chat.model;

import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import java.util.List;
import chat.controller.ChatbotController;
import twitter4j.Twitter;
import java.util.ArrayList;
import twitter4j.Status;

public class CTECTwitter
{
	private ChatbotController baseController;
	private Twitter chatbotTwitter;
	private List<Status> searchedTweets;
	private List<String> ignoredWords;
	
	public CTECTwitter(ChatbotController baseController)
	{
		this.baseController = baseController;
		searchedTweets = new ArrayList<Status>();
		ignoredWords = new ArrayList<String>();
		this.chatbotTwitter = TwitterFactory.getSingleton();
	}
	
	public void sendTweet(String textToTweet)
	{
		try
		{
			chatbotTwitter.updateStatus("I Mr. Salochin just tweeted from my Java Chatbot program 2017! #APCSRocks @CTECNow Thanks @cscheerleader & @codyhenrichsen! @ChatbotCTEC");
		}
		catch(TwitterException tweetError)
		{
			baseController.handleErrors(tweetError);
		}
		catch(Exception otherError)
		{
			baseController.handleErrors(otherError);;
		}
	}
	
	private void createIgnoredWordList()
	{
		
	}
	
	private void collectTweets(String username)
	{
		
	}
	
	public String getMostCommonWord()
	{
		return null;
	}
}
