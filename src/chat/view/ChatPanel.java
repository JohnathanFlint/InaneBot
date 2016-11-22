package chat.view;

import javax.swing.*;
import javax.swing.JPanel;
import chat.controller.ChatbotController;
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
	
	
	public ChatPanel(ChatbotController baseController)
	{
		super();
		this.baseController = baseController;
		
		baseLayout = new SpringLayout();
		chatDisplay = new JTextArea(5, 25);
		chatField = new JTextField(25);
		chatButton = new JButton("Chat with bot");
		pictureLabel = new JLabel(new ImageIcon(getClass().getResource("images/chatbot.png")));
		scroll = new JScrollPane (chatDisplay);		
		conversation = "";
		
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
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.GREEN);
		this.add(chatButton);
		this.add(chatField);
		this.add(scroll);
		this.add(pictureLabel);
		
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, chatButton, 79, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, scroll, 0, SpringLayout.WEST, chatButton);
		baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 261, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatField, 0, SpringLayout.WEST, chatButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatField, -6, SpringLayout.NORTH, chatButton);
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
		
		chatField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent enter)
			{
				String personWords = chatField.getText();
				String chatbotResponse = baseController.useChatbotCheckers(personWords);
				
				conversation += ("You said: " + personWords +"\n"+ "Chatbot says: " + chatbotResponse);
				chatDisplay.setText(conversation);
				
				chatField.setText("");
			}
		});
	}
}
