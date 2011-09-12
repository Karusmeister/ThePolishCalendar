package org.polishcalendar.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.Canvas;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PolishCalendarDev implements EntryPoint {
	
	
	public void onModuleLoad() {
		
		//ShortcutWindow shortcutWindow = new ShortcutWindow(); 
		//Canvas scwindow_main_panel = shortcutWindow.buildShortcutPanel();
		
		LoginPage loginPage = new LoginPage();
		Canvas content = loginPage.buildLoginPage();
		
		//SearchPage search_page = new SearchPage();
		//Canvas content = search_page.build();
		
		//CalendarPage caldendar_page = new CalendarPage();
		//Canvas content = caldendar_page.build();
		
		
		content.setWidth(1000);
		content.setHeight(800);
		RootPanel rootPanel = RootPanel.get("testWindow");
		rootPanel.add(content);
	}
}
