package chat.model;

import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import java.util.List;
import java.util.Scanner;

import chat.controller.ChatbotController;
import twitter4j.Twitter;

import java.text.DecimalFormat;
import java.util.ArrayList;

import twitter4j.GeoLocation;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;

public class CTECTwitter
{
	private ChatbotController baseController;
	private Twitter chatbotTwitter;
	private List<Status> searchedTweets;
	private List<String> tweetedWords;
	
	public CTECTwitter(ChatbotController baseController)
	{
		this.baseController = baseController;
		searchedTweets = new ArrayList<Status>();
		this.tweetedWords = new ArrayList<String>();
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
	
	private String [] createIgnoredWordArray()
	{
		 String [] boringWords;
		 
		 int wordCount = 0;
		 Scanner wordScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));
		 while(wordScanner.hasNextLine())
		 {
			 wordScanner.nextLine();
			 wordCount++;
		 }
		 
		 boringWords = new String [wordCount]; 
		 wordScanner.close();
		 
		 wordScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));
		 for(int index = 0; index < boringWords.length; index ++)
		 {
			 boringWords[index] = wordScanner.nextLine();
		 }
		 
		 wordScanner.close();
		 
		return boringWords;
	}
	
	private void collectTweets(String username)
	{
		searchedTweets.clear();
		tweetedWords.clear();
		
		Paging statusPage = new Paging(1, 100);
		int page = 1;
		
		while(page <= 10)
		{
			statusPage.setPage(page);
			try
			{
				searchedTweets.addAll(chatbotTwitter.getUserTimeline(username, statusPage));
			}
			catch(TwitterException searchTweetError)
			{
				baseController.handleErrors(searchTweetError);;
			}
			
			page++;
		}
	}
	
	public String getMostCommonWord(String user)
	{
		String results = "";

		collectTweets(user);
		statusesToWords();
		
		removeAllBoringWords();
		removeEmptyText();
		
		results += "There are " + tweetedWords.size() + " words in the tweets from " + user;
		return results;
	}
	
	private void removeEmptyText()
	{
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			if(tweetedWords.get(index).trim().equals(""))
			{
				tweetedWords.remove(index);
				index--;
			}
		}
	}
	
	private void removeAllBoringWords()
	{
		String [] boringWords = createIgnoredWordArray();
		
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			for(int boringIndex = 0; boringIndex < boringWords.length; boringIndex++)
			{
				if(tweetedWords.get(index).equalsIgnoreCase(boringWords[boringIndex]))
				{
					tweetedWords.remove(index);
					index--;
					boringIndex = boringWords.length;
				}
			}
		}
	}
	
	private void statusesToWords()

	{
		for(Status currentStatus : searchedTweets)
		{
			String tweetText = currentStatus.getText();
			String [] tweetWords = tweetText.split(" ");
			
			for(int index =0; index < tweetWords.length; index++)
			{
				tweetedWords.add(removePunctuation(tweetWords[index]));
			}
		}
	}
	
	private String calculatePopularWordAndCount()
	{
		String information = "";
		String mostPopular = "";
		int popularIndex = 0;
		int popularCount = 0;
		
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			int currentPopularity = 0;
			for(int searched = index + 1; searched < tweetedWords.size(); searched++)
			{
				if(tweetedWords.get(index).equalsIgnoreCase(tweetedWords.get(searched)) && !tweetedWords.get(index).equals(mostPopular))
				{
					currentPopularity++;
				}
			}
			
			if(currentPopularity > popularCount)
			{
				popularIndex = index;
				popularCount = currentPopularity;
				mostPopular = tweetedWords.get(index);
			}
		}
		
		information = "The most popular word is: " + mostPopular + ", and it occured " + popularCount + "times out of " 
				+ tweetedWords.size() + ", AKA " + (DecimalFormat.getPercentInstance().format(((double) popularCount)/tweetedWords.size())); 
		
		return information;
	}
	
	private String removePunctuation(String currentString)
	{
		String punctuation = ".,'?!:;\"(){}^[]<>-";
		
		String scrubbedString = "";
		for(int i = 0; i< currentString.length(); i++)
		{
			if(punctuation.indexOf(currentString.charAt(i)) == -1)
			{
				scrubbedString += currentString.charAt(i);
			}
		}
		
		return scrubbedString;
	}
	
	private void removeMntions()
	{
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			if(tweetedWords.get(index).substring(0,1).equals("@"))
			{
				tweetedWords.remove(index);
				index--;
			}
		}
	}
	
	public int euroQuery(String searchName)
	{
		int euroResults = 0;
		
		Query euroQuery = new Query("Brandon Sanderson");
		//find out what count is for
		euroQuery.setCount(100);
		//set this geoCode for europe
		euroQuery.setGeoCode(new GeoLocation(54.5260, 15.2551), 2300, Query.KILOMETERS);
		//find publish date of first sanderson book to now.
		euroQuery.setSince("2005-04-21");
		
		try
		{
			QueryResult result = chatbotTwitter.search(euroQuery);
			euroResults += result.getCount();
			
		}
		catch(TwitterException error)
		{
			error.printStackTrace();
		}
		
		return euroResults;
	}
	
	public int USQuery(String searchName)
	{
		int USResults = 0;
		
		Query USQuery = new Query(searchName);
		USQuery.setCount(100);
		USQuery.setGeoCode(new GeoLocation(39.8282, 98.5795), 1400, Query.MILES);
		USQuery.setSince("2005-04-21");
		
		try
		{
			QueryResult result = chatbotTwitter.search(USQuery);
			USResults = result.getCount();
		}
		catch(TwitterException error)
		{
			error.printStackTrace();
		}
		
		return USResults;
	}
	
	public String investigateSanderson()
	{
		String results = "";
		String outcome = "";
		int UsTotal = 0;
		int euroTotal = 0;
		
		int euroAuthor = euroQuery("Brandon Sanderson");
		int euroCosmere = euroQuery("Cosmere");
		int euroArchive = euroQuery("Stormlight Archive");
		int euroMisborn = euroQuery("Mistborn");
		int euroAlcatraz = euroQuery("Alcatraz Versus the Evil Librarians");
		
//		int  USAuthor = USQuery("Brandon Sanderson");
//		int USArchive = USQuery("Stormlight Archive");
//		int USCosmere = USQuery("Cosmere");
//		int USMisborn = USQuery("Mistborn");
//		int USAlcatraz = USQuery("Alcatraz Versus the Evil Librarians");
		
		int USResults = 0; //(USAuthor + USArchive + USCosmere + USMisborn + USAlcatraz);	
		int euroResults = (euroAuthor + euroCosmere + euroArchive +euroMisborn + euroAlcatraz);
		
		results += "The United States results are: " + USResults +  "The European results are: "  + euroResults;
		
		if(euroResults > USResults)
		{
			outcome += results + "Brandon Sanderson is more popular in Europe by: " + (euroResults - USResults);
		}
		else if(USResults > euroResults)
		{
			outcome += results + "Brandon Sanderson is more popular in the United States by: " + (USResults - euroResults);

		}
		
		return outcome;
	}
}
