package org.polishcalendar.client;


import org.polishcalendar.client.util.AppConstants;

import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Canvas;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PolishCalendarDev implements EntryPoint {
	
	public static Canvas outmost_element = null;
	
	public void onModuleLoad() {
		
		DBTestPanel shortcutWindow = new DBTestPanel(); 
		Canvas content = shortcutWindow.build();
		
		//LoginPage loginPage = new LoginPage();
		//Canvas content = loginPage.buildLoginPage();
		
		//SearchPage search_page = new SearchPage();
		//Canvas content = search_page.build();
		
		//CalendarPage caldendar_page = new CalendarPage();
		//Canvas content = caldendar_page.build();
		
		//AccountSettingsPage settingsPage = new AccountSettingsPage();
		//Canvas content = settingsPage.build();
		
		content.setWidth100();
		content.setHeight100();
		content.setOverflow(Overflow.VISIBLE);
		outmost_element = new Canvas();
		outmost_element.setWidth(AppConstants.MAIN_PAGE_WIDTH);
		outmost_element.setHeight(AppConstants.MAIN_PAGE_HEIGHT);
		outmost_element.addChild(content);
		outmost_element.setOverflow(Overflow.VISIBLE);
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
