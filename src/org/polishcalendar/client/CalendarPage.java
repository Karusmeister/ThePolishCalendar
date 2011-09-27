package org.polishcalendar.client;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
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
		main_layout.setMembersMargin(15);
		
		//
		// Creating vertical panel on the right
		//
		VLayout left_panel = new VLayout();
		left_panel.setWidth("30%");
		left_panel.setHeight100();
		left_panel.setMembersMargin(15);
		
		// Creating short_cut window
		//ShortcutWindow sc_window = new ShortcutWindow();
		//Canvas scwindow_main_panel = sc_window.buildShortcutPanel();
		Layout search_box_l = createSearchBox();
		search_box_l.setWidth100();
		search_box_l.setHeight("30%");
		left_panel.addMember(search_box_l);
		
		// Creating organizations buttons
		Layout general_buttons = createGeneralButtons();
		//general_buttons.setHeight("15%");
		left_panel.addMember(general_buttons);
		
		Layout org_buttons = createOrgButtons();
		left_panel.addMember(org_buttons);
		
		// Creating filter buttons
		Layout filter_buttons = createFilterButtons();
		filter_buttons.setWidth100();
		//filter_buttons.setHeight("*");
		left_panel.addMember(filter_buttons);
		
		// Creating calendar
		VLayout cal_layout = new VLayout(); 
		cal_layout.setMembersMargin(10);
		
		calendar = new Calendar();
		calendar.setAddDropValues(true);
		calendar.setWidth100();
		calendar.setHeight("90%");
		calendar.setData(MockData.getCalendarData());
		cal_layout.addMember(calendar);
		
		HLayout cal_buttons_l = new HLayout();
		cal_buttons_l.setMembersMargin(10);
		Button export_b = new Button("Export Calendar View");
		export_b.setWidth(150);
		cal_buttons_l.addMember(export_b);
		cal_layout.addMember(cal_buttons_l);
		
		main_layout.addMember(left_panel);
		main_layout.addMember(cal_layout);
		
		return main_layout;
	}
	
	
	private Canvas buildBottomPanel() {
		
		// TODO stub
		
		return null;
	}
	
	
	private Layout createFilterButtons() {
		
		VLayout buttons_layout = new VLayout();
		buttons_layout.setMembersMargin(5);
		
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
	
	
	private Layout createSearchBox() {
		// TODO stub
		VLayout output = new VLayout();
		output.setShowEdges(true);
		
		HTMLFlow description = new HTMLFlow();
		description.setStyleName("mainSearchBox");
		description.setContents("<h3>Search Box Goes Here</h3>");
		description.setAlign(Alignment.CENTER);
		output.addMember(description);		
		return output;
	}
	
	
	private Layout createGeneralButtons() {
		VLayout output = new VLayout();
		output.setMembersMargin(5);
		
		LayoutSpacer spacer = new LayoutSpacer();
		spacer.setHeight("40%");
		output.addMember(spacer);
		
		Button orgs_b = new Button("My Organizations");
		orgs_b.setWidth100();
		output.addMember(orgs_b);
		
		Button pref_b = new Button("My Preferences");
		pref_b.setWidth100();
		pref_b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) { 
				PreferencesPage search_page = new PreferencesPage();
				Canvas content = search_page.build();
				PolishCalendarDev.replaceOutmostContent(content);
			}
		});
		output.addMember(pref_b);
		
		return output;
	}
	
	
	private Layout createOrgButtons() {
		VLayout output = new VLayout();
		output.setMembersMargin(5);
		
		Button manage_events_b = new Button("Manage Events");
		manage_events_b.setWidth100();
		output.addMember(manage_events_b);
		
		Button new_events_b =  new Button("Create New Events");
		new_events_b.setWidth100();
		output.addMember(new_events_b);
		
		Button import_b = new Button("Import Existing Calendar");
		import_b.setWidth100();
		output.addMember(import_b);
		
		return output;
	}
}
