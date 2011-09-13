package org.polishcalendar.client;

import org.polishcalendar.client.MockData.OrganizationRecord;
import org.polishcalendar.client.MockData.PreferenceRecord;

import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class PreferencesPage {
	
	// preferences pickup
	private ListGrid social_select = null;
	private ListGrid cultural_select = null;
	private ListGrid political_select = null;
	private ListGrid other_select = null;
	private ListGrid location_select = null;
	private ListGrid time_select = null;

	public Canvas build() {
		
		//TODO build all panels
		Canvas main_panel = buildMainPanel();
		main_panel.setWidth100();
		main_panel.setHeight100();
		return main_panel;
	}
	
	private Canvas buildTopPanel() {
		
		// TODO stub
		
		return null;
	}
	
	private Canvas buildMainPanel() {
		
		VLayout main_layout = new VLayout();
		
		// creating preference pick-up
        final SectionStack preferences_stack = new SectionStack();  
        preferences_stack.setVisibilityMode(VisibilityMode.MULTIPLE);  
        preferences_stack.setWidth100();
        preferences_stack.setOverflow(Overflow.VISIBLE);
        main_layout.addMember(preferences_stack);
        
    	social_select = createPreferencesGrid(MockData.getSocialPreferences());
    	cultural_select = createPreferencesGrid(MockData.getCulturalPreferences());
    	political_select = createPreferencesGrid(MockData.getPoliticalPreferences());
    	other_select = createPreferencesGrid(MockData.getOtherPreferences());
    	location_select = createPreferencesGrid(MockData.getLocationPreferences());
    	time_select = createPreferencesGrid(MockData.getTimePreferences());
    	
        SectionStackSection section1 = new SectionStackSection("Social");  
        section1.setExpanded(false);  
        section1.setCanCollapse(true);  
        section1.addItem(social_select);  
        preferences_stack.addSection(section1);
        
        SectionStackSection section2 = new SectionStackSection("Cultural");  
        section2.setExpanded(false);  
        section2.setCanCollapse(true);  
        section2.addItem(cultural_select);  
        preferences_stack.addSection(section2);
        
        SectionStackSection section3 = new SectionStackSection("Political");  
        section3.setExpanded(false);  
        section3.setCanCollapse(true);  
        section3.addItem(political_select);  
        preferences_stack.addSection(section3);
        
        SectionStackSection section4 = new SectionStackSection("Other");  
        section4.setExpanded(false);  
        section4.setCanCollapse(true);  
        section4.addItem(other_select);  
        preferences_stack.addSection(section4);
        
        SectionStackSection section5 = new SectionStackSection("Location");  
        section5.setExpanded(false);  
        section5.setCanCollapse(true);  
        section5.addItem(location_select);  
        preferences_stack.addSection(section5);
        
        SectionStackSection section6 = new SectionStackSection("Time");  
        section6.setExpanded(false);  
        section6.setCanCollapse(true);  
        section6.addItem(time_select);  
        preferences_stack.addSection(section6);
        
		// creating bottom buttons
		Canvas bottom_widget = createBottomButtons();
		main_layout.addMember(bottom_widget);
		
		return main_layout;
	}
	
	private Canvas buildBottomPanel() {
		
		// TODO stub
		
		return null;
	}
	
	// creates grid with given preference records
	private ListGrid createPreferencesGrid(PreferenceRecord[] records) {
		
		ListGrid preferences_grid = new ListGrid();
		preferences_grid.setShowAllRecords(false);
		preferences_grid.setSelectionType(SelectionStyle.SIMPLE);  
		preferences_grid.setSelectionAppearance(SelectionAppearance.CHECKBOX); 
  
        ListGridField nameField = new ListGridField("name" , " ");  
        preferences_grid.setFields(nameField);
        preferences_grid.setData(records); 
        
        return preferences_grid;
	}
	
	private Canvas createBottomButtons() {
		HLayout button_layout = new HLayout();
		
		Button save_button = new Button("Save Preferences");
		save_button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO stub
			}
		});
		
		Button back_button = new Button("Go Back");
		back_button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RootPanel rootPanel = RootPanel.get("testWindow");
				rootPanel.clear();
				CalendarPage caldendar_page = new CalendarPage();
				Canvas content = caldendar_page.build();
				content.setWidth(1000);
				content.setHeight(800);
				rootPanel.add(content);
			}
		});
		
		button_layout.addMember(save_button);
		button_layout.addMember(back_button);
		
		return button_layout;
	}
}
