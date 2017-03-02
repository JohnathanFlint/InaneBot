package chat.controller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileController
{
	public static void saveFile(ChatbotController baseController, String fileName, String contents)
	{
		try 
		{
			File saveFile;
			if(fileName.length() > 5)
			{
				saveFile = new File(fileName + "text");
			}
			else
			{
				saveFile = new File("you don goofed.txt");
			}
			
			FileWriter saveFileWriter = new FileWriter(saveFile);
			saveFileWriter.write(contents);
			saveFileWriter.close();
		}
		catch(IOException error)
		{
			baseController.handleErrors(error);
		}
	}
	
	public static String readFile(ChatbotController baseController, String fileName)
	{
		
	}
}
