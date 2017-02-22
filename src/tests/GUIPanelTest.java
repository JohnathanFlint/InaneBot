/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import chat.controller.ChatbotController;
import chat.view.ChatPanel;


/**
 * @author CodyH
 * @version 0.x Nov 12, 2015
 *
 */
public class GUIPanelTest
{

	private ChatPanel testPanel;
	private ChatbotController testController;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		testController = new ChatbotController();
		testPanel = new ChatPanel(testController);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		testController = null;
		testPanel = null;
	}

	/**
	 * Test method for {@link gui.view.GUIPanel#GUIPanel(gui.controller.GUIAppController)}.
	 */
	@Test
	public void testGUIPanel()
	{
		assertTrue("Incorrect layout type", testPanel.getLayout() instanceof javax.swing.SpringLayout);
		assertTrue("Minimum components not met", testPanel.getComponents().length >= 2);
	}

}
