package org.polishcalendar.client;

import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;

public class TopPanel {

	public Layout build () {
		HLayout upper_toolbar = new HLayout();
		Button home_page = new Button("Home Page");
		Button logout_button = new Button("Logout");
		Button preference_button = new Button("Preferences");
		Button settings_button = new Button("Account Settings");
		Button about_button = new Button("About us");
		Button contant_button = new Button("Contact us");
		
		// Creating appearance 
		// Each button would stretch for stretch% of available width
		String stretch = "20%";
		home_page.setWidth(stretch);  
		home_page.setShowRollOver(true);  
		home_page.setShowDisabled(true);  
		home_page.setShowDown(true);  
		
		logout_button.setWidth(stretch);  
		logout_button.setShowRollOver(true);  
		logout_button.setShowDisabled(true);  
		logout_button.setShowDown(true);  
		
		settings_button.setWidth(stretch);  
		settings_button.setShowRollOver(true);  
		settings_button.setShowDisabled(true);  
		settings_button.setShowDown(true);
		
		preference_button.setWidth(stretch);  
		preference_button.setShowRollOver(true);  
		preference_button.setShowDisabled(true);  
		preference_button.setShowDown(true);  
		
		about_button.setWidth(stretch);  
		about_button.setShowRollOver(true);  
		about_button.setShowDisabled(true);  
		about_button.setShowDown(true);  
		
		contant_button.setWidth(stretch);  
		contant_button.setShowRollOver(true);  
		contant_button.setShowDisabled(true);  
		contant_button.setShowDown(true); 
		
		// Creating handlers
		// Setting all listeners
		home_page.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) { 
				CalendarPage caldendar_page = new CalendarPage();
				Canvas content = caldendar_page.build();
				PolishCalendarDev.replaceOutmostContent(content);
			}
		});
		
		logout_button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) { 
				LoginPage loginPage = new LoginPage();
				Canvas content = loginPage.buildLoginPage();
				PolishCalendarDev.replaceOutmostContent(content);
			}
		});
		
		preference_button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) { 
				PreferencesPage search_page = new PreferencesPage();
				Canvas content = search_page.build();
				PolishCalendarDev.replaceOutmostContent(content);
			}
		});
		
		settings_button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) { 
				AccountSettingsPage settingsPage = new AccountSettingsPage();
				Canvas content = settingsPage.build();
				PolishCalendarDev.replaceOutmostContent(content);
			}
		});
		
		upper_toolbar.addMember(home_page);
		//upper_toolbar.addMember(preference_button);
		upper_toolbar.addMember(settings_button);
		upper_toolbar.addMember(about_button);
		upper_toolbar.addMember(contant_button);
		upper_toolbar.addMember(logout_button);
		
		return upper_toolbar;
	}
}
