package org.polishcalendar.client;

import org.polishcalendar.client.MockData.OrganizationRecord;
import org.polishcalendar.client.MockData.PreferenceRecord;

import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class SearchPage {
	
	private ListGrid organizations_select = null;
	private ListGrid preferences_select = null;

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
		
		// creating organizations select list
		organizations_select = createOrganizationsSelect();
		organizations_select.setWidth100();
		organizations_select.setHeight("35%");
		main_layout.addMember(organizations_select);
		
		// creating preferences select list
		preferences_select = createPreferencesSelect();
		preferences_select.setWidth100();
		preferences_select.setHeight("35%");
		main_layout.addMember(preferences_select);
		
		// creating bottom buttons
		Canvas bottom_widget = createBottomButtons();
		main_layout.addMember(bottom_widget);
		
		return main_layout;
	}
	
	private Canvas buildBottomPanel() {
		
		// TODO stub
		
		return null;
	}
	
	private ListGrid createOrganizationsSelect() {
		
        final ListGrid organizations_grid = new ListGrid();  
        organizations_grid.setShowAllRecords(false);  
        organizations_grid.setSelectionType(SelectionStyle.SIMPLE);  
        organizations_grid.setSelectionAppearance(SelectionAppearance.CHECKBOX); 
  
        ListGridField nameField = new ListGridField("name", "Organization");  
        organizations_grid.setFields(nameField);
        OrganizationRecord[] records = MockData.getOrganizations();
        organizations_grid.setData(records); 
        
        return organizations_grid;
	}
	
	private ListGrid createPreferencesSelect() {
		
		ListGrid preferences_grid = new ListGrid();
		preferences_grid.setShowAllRecords(false);
		preferences_grid.setSelectionType(SelectionStyle.SIMPLE);  
		preferences_grid.setSelectionAppearance(SelectionAppearance.CHECKBOX); 
  
        ListGridField nameField = new ListGridField("name", "Type");  
        preferences_grid.setFields(nameField);
        PreferenceRecord[] records = MockData.getPreferences();
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
		
		Button default_button = new Button ("Load Default");
		default_button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (organizations_select != null) {
					organizations_select.deselectAllRecords();
					OrganizationRecord[] records = MockData.getOrganizations();
					for (OrganizationRecord record : records) {
						boolean selected = record.getFollow();
						if (selected) {
							int index = record.getIndex();
							organizations_select.selectRecord(index);
						}
					}
				}
				if (preferences_select != null) {
					preferences_select.deselectAllRecords();
					PreferenceRecord[] records = MockData.getPreferences();
					for (PreferenceRecord record : records) {
						boolean selected = record.getSelected();
						if (selected) {
							int index = record.getIndex();
							preferences_select.selectRecord(index);
						}
					}					
				}
			}
		});
		
		Button search_button = new Button("Search");
		search_button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
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
		button_layout.addMember(default_button);
		button_layout.addMember(search_button);
		button_layout.addMember(back_button);
		
		return button_layout;
	}
}
