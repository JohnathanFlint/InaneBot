package chat.view;

import javax.swing.*;
import javax.swing.JPanel;
import chat.controller.ChatbotController;
import chat.controller.FileController;

import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatPanel extends JPanel
{
	private ChatbotController baseController;
	private SpringLayout baseLayout;
	private JTextArea chatDisplay;
	private JTextField chatField;
	private JButton chatButton;
	private JLabel pictureLabel;
	private JScrollPane scroll;
	private String conversation;
	private JButton openFile;
	private JButton saveText;
	private JButton postTwit;
	private JButton searchTwit;
	
	
	public ChatPanel(ChatbotController baseController)
	{
		super();
		this.baseController = baseController;
		
		baseLayout = new SpringLayout();
		chatDisplay = new JTextArea(5, 25);
		chatField = new JTextField(25);
		baseLayout.putConstraint(SpringLayout.WEST, chatField, 79, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatField, -45, SpringLayout.SOUTH, this);
		chatButton = new JButton("Chat with bot");
		pictureLabel = new JLabel(new ImageIcon(getClass().getResource("images/chatbot.png")));
		scroll = new JScrollPane (chatDisplay);		
		baseLayout.putConstraint(SpringLayout.WEST, scroll, 79, SpringLayout.WEST, this);
		conversation = "";
		openFile = new JButton("Open File");
		saveText = new JButton("Save Text");
		baseLayout.putConstraint(SpringLayout.EAST, saveText, -250, SpringLayout.EAST, this);
		postTwit = new JButton("Post Tweet");
		baseLayout.putConstraint(SpringLayout.NORTH, postTwit, 50, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, postTwit, 0, SpringLayout.WEST, this);
		
		searchTwit = new JButton("Search Twitter");		
		baseLayout.putConstraint(SpringLayout.EAST, searchTwit, 5, SpringLayout.EAST, this);
		
		setupChatDisplay();		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupChatDisplay()
	{
		chatDisplay.setEditable(false);
		chatDisplay.setEnabled(false);
		chatDisplay.setWrapStyleWord(true);
		chatDisplay.setLineWrap(true);	
		scroll.setViewportView(chatDisplay);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.GREEN);
		this.add(chatButton);
		this.add(chatField);
		this.add(scroll);
		this.add(openFile);
		this.add(saveText);
		this.add(postTwit);
		this.add(searchTwit);
		this.add(pictureLabel);

		
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, chatButton, 79, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 261, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, scroll, -6, SpringLayout.NORTH, chatField);

	}
	
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String personWords = chatField.getText();
				String chatbotResponse = baseController.useChatbotCheckers(personWords);
				
				conversation += ("You said: " + personWords +"\n"+ "Chatbot says: " + chatbotResponse);
				chatDisplay.setText(conversation);
				//chatDisplay.setDisabledTextColor(new Color(1,1,1));
			}
		});
		
		saveText.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String fileName = chatField.getText();
				
				FileController.saveFile(baseController, fileName, chatDisplay.getText());
			}
				});
		
		openFile.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent click)
					{
						String fileName = chatField.getText();
						String saved = FileController.readFile(baseController,  fileName + ".txt");
						chatDisplay.setText(saved);
						
					}
				});
		
		chatField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent enter)
			{
				String personWords = chatField.getText();
				String chatbotResponse = baseController.useChatbotCheckers(personWords);
				String currentText = chatDisplay.getText();
				
				conversation += ("You said: " + personWords +"\n"+ "Chatbot says: " + chatbotResponse + "\n" + currentText);
				chatDisplay.setCaretPosition(0);
				
				chatField.setText("");
			}
		});
		
		postTwit.addActionListener(new ActionListener()
		{
				public void actionPerformed(ActionEvent click)
				{
					baseController.useTwitter(chatField.getText());
				}
		});
		
		searchTwit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String results = baseController.searchTwitterUser(chatField.getText());
				chatDisplay.setText(results + chatDisplay.getText());
			}
		});
		
		searchTwit.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent longCLick)
					{
						String results = baseController.investigateSanderson();
						 chatDisplay.setText(results);
					}
					
				});
	}
}
