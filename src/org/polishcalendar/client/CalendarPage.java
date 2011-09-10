package org.polishcalendar.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class CalendarPage {

	public Canvas build() {
		
		Canvas top_panel = buildTopPanel();
		Canvas bottom_panel = buildBottomPanel();
		Canvas main_panel = buildMainPanel();
		
		// layouting 
		VLayout main_layout = new VLayout();
		main_layout.setMembersMargin(10);
		
		top_panel.setWidth100();
		top_panel.setHeight("10%");
		main_layout.addMember(top_panel);
		
		main_panel.setWidth100();
		main_panel.setHeight("*");
		main_layout.addMember(main_panel);
		
		return main_layout;
	}
	
	
	private Canvas buildTopPanel() {
		
		HLayout upper_toolbar = new HLayout();
		Button logout_button = new Button("Logout");
		Button search_button = new Button("Search Events");
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
				RootPanel rootPanel = RootPanel.get("testWindow");
				rootPanel.clear();
				SearchPage search_page = new SearchPage();
				Canvas content = search_page.build();
				rootPanel.add(content);
			}
		});
		
		upper_toolbar.addMember(search_button);
		upper_toolbar.addMember(settings_button);
		upper_toolbar.addMember(about_button);
		upper_toolbar.addMember(contant_button);
		upper_toolbar.addMember(logout_button);
		
		return upper_toolbar;
	}
	
	
	private Canvas buildMainPanel() {
		
		HLayout main_layout = new HLayout();
		main_layout.setMembersMargin(5);
		
		// Creating short_cut window
		ShortcutWindow sc_window = new ShortcutWindow();
		Canvas scwindow_main_panel = sc_window.buildShortcutPanel();
		scwindow_main_panel.setWidth("30%");
		scwindow_main_panel.setHeight("40%");
		
		// Creating calendar
		Calendar calendar = new Calendar();
		calendar.setAddDropValues(true);
		calendar.setWidth("*");
		calendar.setHeight("90%");
		
		main_layout.addMember(scwindow_main_panel);
		main_layout.addMember(calendar);
		
		return main_layout;
	}
	
	
	private Canvas buildBottomPanel() {
		
		// TODO stub
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
