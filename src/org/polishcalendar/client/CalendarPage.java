package org.polishcalendar.client;

import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class CalendarPage {
	
	private Calendar calendar;

	public Canvas build() {
		
		Canvas top_panel = buildTopPanel();
		Canvas bottom_panel = buildBottomPanel();
		Canvas main_panel = buildMainPanel();
		
		// layouting 
		VLayout output = new VLayout();
		output.setMembersMargin(10);
		
		top_panel.setWidth100();
		top_panel.setHeight("10%");
		output.addMember(top_panel);
		
		main_panel.setWidth100();
		main_panel.setHeight("*");
		output.addMember(main_panel);
		
		return output;
	}
	
	
	private Canvas buildTopPanel() {
		return (new TopPanel()).build();
	}
	
	
	private Canvas buildMainPanel() {
		
		HLayout main_layout = new HLayout();
		main_layout.setMembersMargin(5);
		
		//
		// Creating vertical panel on the right
		//
		VLayout left_panel = new VLayout();
		left_panel.setWidth("30%");
		left_panel.setHeight100();
		left_panel.setMembersMargin(5);
		
		// Creating short_cut window
		ShortcutWindow sc_window = new ShortcutWindow();
		Canvas scwindow_main_panel = sc_window.buildShortcutPanel();
		scwindow_main_panel.setWidth100();
		scwindow_main_panel.setHeight("20%");
		left_panel.addMember(scwindow_main_panel);
		
		// Creating filter buttons
		Canvas buttons = createFilterButtons();
		buttons.setWidth100();
		buttons.setHeight("30%");
		left_panel.addMember(buttons);
		
		// Creating calendar
		calendar = new Calendar();
		calendar.setAddDropValues(true);
		calendar.setWidth("*");
		calendar.setHeight("90%");
		calendar.setData(MockData.getCalendarData());
		
		main_layout.addMember(left_panel);
		main_layout.addMember(calendar);
		
		return main_layout;
	}
	
	
	private Canvas buildBottomPanel() {
		
		// TODO stub
		
		return null;
	}
	
	
	private Canvas createFilterButtons() {
		
		VLayout buttons_layout = new VLayout();
		buttons_layout.setMembersMargin(5);
		buttons_layout.setLayoutMargin(10);
		
		Button all_events = new Button("Show All Events");
		all_events.setWidth100();
		buttons_layout.addMember(all_events);
		all_events.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) { 
				calendar.setData(MockData.getCalendarData());
			}
		});
		
		Button attending_events = new Button("Show Events I am Attending");
		attending_events.setWidth100();
		buttons_layout.addMember(attending_events);
		attending_events.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) { 
				calendar.setData(MockData.getAttendingEvents());
			}
		});
		
		Button preference_events = new Button("Show Events Matching My Preferences");
		preference_events.setWidth100();
		buttons_layout.addMember(preference_events);
		preference_events.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) { 
				calendar.setData(MockData.getPreferenceEvents());
			}
		});
		
		return buttons_layout;
	}
}
