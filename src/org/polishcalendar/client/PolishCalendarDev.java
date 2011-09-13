package org.polishcalendar.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.Canvas;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PolishCalendarDev implements EntryPoint {
	
	public static Canvas outmost_element = null;
	
	public void onModuleLoad() {
		
		//ShortcutWindow shortcutWindow = new ShortcutWindow(); 
		//Canvas scwindow_main_panel = shortcutWindow.buildShortcutPanel();
		
		LoginPage loginPage = new LoginPage();
		Canvas login_page = loginPage.buildLoginPage();
		login_page.setWidth100();
		login_page.setHeight100();
		
		//SearchPage search_page = new SearchPage();
		//Canvas content = search_page.build();
		
		//CalendarPage caldendar_page = new CalendarPage();
		//Canvas content = caldendar_page.build();
		
		outmost_element = new Canvas();
		outmost_element.setWidth(1000);
		outmost_element.setHeight(800);
		outmost_element.addChild(login_page);
		outmost_element.draw();
	}
	
	
	// Replaces the content of outmost element by input canvas
	public static void replaceOutmostContent (Canvas content) {
		content.setWidth100();
		content.setHeight100();
		for (Canvas child: PolishCalendarDev.outmost_element.getChildren()) {
			child.destroy();
		}
		PolishCalendarDev.outmost_element.addChild(content);
	}
	
}
