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
		Button logout_button = new Button("Logout");
		Button search_button = new Button("Preferences");
		Button settings_button = new Button("Account Settings");
		Button about_button = new Button("About us");
		Button contant_button = new Button("Contact us");
		
		// Creating appearance 
		// Each button would stretch for stretch% of available width
		String stretch = "20%";
		logout_button.setWidth(stretch);  
		logout_button.setShowRollOver(true);  
		logout_button.setShowDisabled(true);  
		logout_button.setShowDown(true);  
		
		search_button.setWidth(stretch);  
		search_button.setShowRollOver(true);  
		search_button.setShowDisabled(true);  
		search_button.setShowDown(true);
		
		settings_button.setWidth(stretch);  
		settings_button.setShowRollOver(true);  
		settings_button.setShowDisabled(true);  
		settings_button.setShowDown(true);  
		
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
		search_button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) { 
				PreferencesPage search_page = new PreferencesPage();
				Canvas content = search_page.build();
				PolishCalendarDev.replaceOutmostContent(content);
			}
		});
		
		upper_toolbar.addMember(search_button);
		upper_toolbar.addMember(settings_button);
		upper_toolbar.addMember(about_button);
		upper_toolbar.addMember(contant_button);
		upper_toolbar.addMember(logout_button);
		
		return upper_toolbar;
	}
}
