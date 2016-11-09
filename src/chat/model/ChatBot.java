package chat.model;

import java.util.ArrayList;

/**
 * Base version of the 2015 Chatbot class. Only stub methods are provided.
 * Students will complete methods as part of the project.
 * 
 * @author Cody Henrichsen
 * @version 1.0 10/14/15
 */
public class Chatbot
{
	private ArrayList<String> memesList;
	private ArrayList<String> politicalTopicList;
	private String userName;
	private String content;
	private ArrayList<String> mashList;

	/**
	 * Creates an instance of the Chatbot with the supplied username.
	 * 
	 * @param userName
	 *            The username for the chatbot.
	 */
	public Chatbot(String userName)
	{
		memesList = new ArrayList<String>();
		politicalTopicList = new ArrayList<String>();
		mashList = new ArrayList<String>();

		content = ("Content");
		this.userName = userName;
		buildMemesList();
		buildPoliticalTopicsList();
		buildMashList();
	}

	private void buildMemesList()
	{
		memesList.add("john cena");
		memesList.add("harambe");
		memesList.add("doge");
		memesList.add("cute animals");
		memesList.add("cute animals");
		memesList.add("grumpy cat");
		memesList.add("dat boi");
		memesList.add("willy wonka");
		memesList.add("pepe");
		memesList.add("trump's hair");
		memesList.add("tacos");
		memesList.add("geran");
		memesList.add("meme hole");
		memesList.add("the destroyer of salt");
		memesList.add("lag");
		memesList.add("godzilla");
		memesList.add("nooo");
		memesList.add("fruit snacks");

	}

	private void buildPoliticalTopicsList()
	{
		politicalTopicList.add("Democrat");
		politicalTopicList.add("Republican");
		politicalTopicList.add("11/8/16");
		politicalTopicList.add("liberal");
		politicalTopicList.add("conservative");
		politicalTopicList.add("Clinton");
		politicalTopicList.add("Trump");
		politicalTopicList.add("Kaine");
		politicalTopicList.add("Pence");
		politicalTopicList.add("Stein");
		politicalTopicList.add("Johnson");
		politicalTopicList.add("election");
		politicalTopicList.add("gun control");
		politicalTopicList.add("debates");
		politicalTopicList.add("debt");
		politicalTopicList.add("taxes");
		politicalTopicList.add("emails");
		politicalTopicList.add("doomed");
		politicalTopicList.add("Hillary");
	}

	private void buildMashList()
	{
		mashList.add("sdf");
		mashList.add("dfg");
		mashList.add("cvb");
		mashList.add(",./");
	}

	/**
	 * Checks the length of the supplied string. Returns false if the supplied
	 * String is empty or null, otherwise returns true.
	 * 
	 * @param currentInput
	 * @return A true or false based on the length of the supplied String.
	 */
	public boolean lengthChecker(String currentInput)
	{
		boolean hasLength = false;

		if (currentInput != null && !currentInput.equals(""))
		{
			hasLength = true;
		}

		return hasLength;
	}

	/**
	 * Checks if the supplied String matches the content area for this Chatbot
	 * instance.
	 * 
	 * @param currentInput
	 *            The supplied String to be checked.
	 * @return Whether it matches the content area.
	 */
	public boolean contentChecker(String currentInput)
	{
		boolean hasContent = false;
		String tempInput = currentInput.toLowerCase();

		if (tempInput.toLowerCase().contains(content.toLowerCase()))
		{
			hasContent = true;
		}
		return hasContent;
	}

	/**
	 * Checks if supplied String matches ANY of the topics in the
	 * politicalTopicsList. Returns true if it does find a match and false if it
	 * does not match.
	 * 
	 * @param currentInput
	 *            The supplied String to be checked.
	 * @return Whether the String is contained in the ArrayList.
	 */
	public boolean politicalTopicChecker(String currentInput)
	{
		for (int pos = 0; pos < politicalTopicList.size(); pos++)
		{
			if (currentInput.equals(politicalTopicList.get(pos)))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks to see that the supplied String value is in the current memesList
	 * variable.
	 * 
	 * @param currentInput
	 *            The supplied String to be checked.
	 * @return Whether the supplied String is a recognized meme.
	 */
	public boolean memeChecker(String currentInput)
	{
		for (int pos = 0; pos < memesList.size(); pos++)
		{
			if (currentInput.equalsIgnoreCase(memesList.get(pos)))
			{
				return true;
			}

		}
		return false;

	}

	/**
	 * Returns the username of this Chatbot instance.
	 * 
	 * @return The username of the Chatbot.
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Returns the content area for this Chatbot instance.
	 * 
	 * @return The content area for this Chatbot instance.
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * Getter method for the memesList object.
	 * 
	 * @return The reference to the meme list.
	 */
	public ArrayList<String> getMemesList()
	{
		return memesList;
	}

	/**
	 * Getter method for the politicalTopicList object.
	 * 
	 * @return The reference to the political topic list.
	 */
	public ArrayList<String> getPoliticalTopicList()
	{
		return politicalTopicList;
	}

	/**
	 * Updates the content area for this Chatbot instance.
	 * 
	 * @param content
	 *            The updated value for the content area.
	 */
	public void setContent(String content)
	{
		this.content = content;

	}

	public boolean twitterChecker(String string)
	{
		boolean isTweet = false;

		if (string.startsWith("#") || string.startsWith("@"))
		{
			isTweet = true;
		}
		return isTweet;
	}

	public boolean quitChecker(String string)
	{
		if (string.equalsIgnoreCase("quit"))
		{
			return true;
		}
		return false;
	}

	public boolean keyboardMashChecker(String string)
	{
		for (int pos = 0; pos < mashList.size(); pos++)
		{
			if (string.contains(mashList.get(pos)))
			{
				return true;
			}
		}
		return false;
	}

	public boolean inputHTMLChecker(String input)
	{
		boolean isHTML = false;

		if (input.startsWith("<") && input.endsWith(">"))
		{
			if (input.length() == 3 && input.contains("p"))
			{
				isHTML = true;
			}
			
			if (input.length() >= 2)
			{

				if (input.contains("</") && input.substring(1, 2).equalsIgnoreCase(input.substring(input.length() - 2,input.length() - 1)))
				{
					if (input.contains("HREF"))
					{
						if (input.contains("="))
						{
							isHTML = true;
						}
					}
					
					else
					{
						isHTML = true;
					}
				}
			}		
		}

		return isHTML;
	}

}
