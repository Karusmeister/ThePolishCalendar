package org.polishcalendar.client;

import com.google.gwt.user.client.ui.DockPanel;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.layout.HLayout;

public class CalendarPage {

	public DockPanel build() {
		
		DockPanel mainPanel = new DockPanel();
		
		HLayout upperToolbar = new HLayout();
		
		IButton logoutButton = new IButton("Logout");
	//	logoutButton.setAlign( Alignment.RIGHT);
		IButton settingsButton = new IButton("Account Settings");
	//	settingsButton.setAlign(Alignment.RIGHT);
		
		ComboBoxItem searchBox = new ComboBoxItem();
		
		upperToolbar.addMember(settingsButton);
		upperToolbar.addMember(settingsButton);
		upperToolbar.addMember(logoutButton);	
		
		Calendar calendar = new Calendar();
		calendar.setAddDropValues(true);
		calendar.setWidth("70%");
		calendar.setHeight("80%");
		
		ShortcutWindow sc_window = new ShortcutWindow();
		Canvas scwindow_main_panel = sc_window.buildShortcutPanel();
		
		mainPanel.add(upperToolbar,DockPanel.NORTH);
		mainPanel.add(calendar, DockPanel.EAST);
		mainPanel.add(scwindow_main_panel, DockPanel.EAST);
		
		return mainPanel;
	}
}
