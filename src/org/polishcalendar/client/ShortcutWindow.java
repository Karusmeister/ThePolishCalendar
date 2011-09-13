package org.polishcalendar.client;

import java.util.Date;

import org.polishcalendar.client.MockData.EventRecord;
import org.polishcalendar.client.MockData.OrganizationRecord;
import org.polishcalendar.ds.EventDataSource;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler; 
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler; 
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;  
import com.smartgwt.client.widgets.layout.VLayout; 
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;

public class ShortcutWindow {
	
	// Hack - set to true when organizations popup window is being destroyed, so that
	// SelectionEvent is ignored.
	private boolean organizations_destoyed = false;
	
	public Canvas buildShortcutPanel() {
		
		VLayout scwindow_main_panel = new VLayout();
		HLayout user_panel = new HLayout();
		VLayout info_panel = new VLayout();
		Img user_image = new Img ("huszcza.jpg");
		Button account_settings  = new Button("Account Settings");
		Button organizations_b = new Button("Organizations");
		Button my_preferences_b = new Button("Preferences");
		Button my_events_b = new Button("My Events");
		
		
		// Setting main panel
		scwindow_main_panel.setMembersMargin(5);
		scwindow_main_panel.setLayoutMargin(10);
		scwindow_main_panel.setShowEdges(true);
		
		scwindow_main_panel.addMember(user_panel);
		organizations_b.setWidth100();
		scwindow_main_panel.addMember(organizations_b);
		my_events_b.setWidth100();
		scwindow_main_panel.addMember(my_events_b);
		my_preferences_b.setWidth100();
		scwindow_main_panel.addMember(my_preferences_b);
		
		// Setting user panel
		user_panel.setWidth100();
		user_image.setWidth("50%");
		user_image.setHeight100();
		user_panel.addMember(user_image);
		info_panel.setWidth("50%");
		user_panel.addMember(info_panel);
		
		// Setting infor panel
		Label user_name = new Label("Krzysztof Huszcza");
		user_name.setAlign(Alignment.CENTER);
		user_name.setWidth100();
		info_panel.addMember(user_name);
		account_settings.setWidth100();
		info_panel.addMember(account_settings);
		
		// Setting all listeners
		organizations_b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final Window org_popup = new Window();
				
				// Setting popup
				org_popup.setWidth(400);  
				org_popup.setHeight(600);  
				org_popup.setTitle("Organizations followed");  
				org_popup.setShowMinimizeButton(false);  
				org_popup.setIsModal(true);  
				org_popup.setShowModalMask(true);  
				org_popup.centerInPage();  
				org_popup.addCloseClickHandler(new CloseClickHandler() {  
                    public void onCloseClick(CloseClientEvent event) {
                    	organizations_destoyed = true;
                    	org_popup.destroy();  
                    }  
                }); 
				
				// Setting popup's  content
				Canvas content = buildOrganizationPopup();
				org_popup.addItem(content);  
				org_popup.show();  
			}
		});
		
		my_events_b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final Window events_popup = new Window();
				
				// Setting popup
				events_popup.setWidth(400);  
				events_popup.setHeight(600);  
				events_popup.setTitle("Registered events");  
				events_popup.setShowMinimizeButton(false);  
				events_popup.setIsModal(true);  
				events_popup.setShowModalMask(true);  
				events_popup.centerInPage();  
				events_popup.addCloseClickHandler(new CloseClickHandler() {  
                    public void onCloseClick(CloseClientEvent event) {
                    	events_popup.destroy();  
                    }  
                }); 
				
				// Setting popup's  content
				Canvas content = buildEventsPopup();
				events_popup.addItem(content);  
				events_popup.show();  
			}
		});
		
		return scwindow_main_panel;
	}
	
	
	private Canvas buildOrganizationPopup() {
		
        Canvas canvas = new Canvas();  
        
        final ListGrid organizations_grid = new ListGrid();  
        organizations_grid.setWidth100();  
        organizations_grid.setHeight100();  
        organizations_grid.setShowAllRecords(true);  
        organizations_grid.setSelectionType(SelectionStyle.SIMPLE);  
        organizations_grid.setSelectionAppearance(SelectionAppearance.CHECKBOX); 
  
        ListGridField nameField = new ListGridField("name", "Organization");  
        organizations_grid.setFields(nameField);
        OrganizationRecord[] records = MockData.getOrganizations();
        organizations_grid.setData(records);
        
        organizations_grid.addSelectionChangedHandler(new SelectionChangedHandler() {  
            public void onSelectionChanged(SelectionEvent event) {  
            	OrganizationRecord record 
            		= (OrganizationRecord) event.getRecord();
            	if (record != null && !organizations_destoyed) {
            		System.out.println("selecting " + record.getName());
            		record.setFollow(event.getState());
            	}
            }  
        }); 
        
        organizations_destoyed = false;
        for (OrganizationRecord record : records) {
        	if (record.getFollow()) {
        		System.out.println("Preselecting: " + record);
        		organizations_grid.selectRecord(record);
        	}
        }
        
        canvas.addChild(organizations_grid);
		
		return canvas;
	}
	
	private Canvas buildEventsPopup() {
		VLayout layout = new VLayout();
		
        final ListGrid events_grid = new ListGrid();  
        events_grid.setWidth100();  
        //events_grid.setHeight100();  
        
        // test button
        Button test = new Button("Test");
        test.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				DataSource events_ds = EventDataSource.getEventDS();
				Date common_date = new Date();
				EventRecord record = new EventRecord("Test" , "Test" , common_date , 5);
				events_ds.addData(record);
			}
		});
  
        //ListGridField nameField = new ListGridField("name", "Event");  
        //ListGridField organizationField = new ListGridField("organized_by", "Organized By");  
        //ListGridField dateField = new ListGridField("date", "Date");  
        //events_grid.setFields(nameField, organizationField, dateField);
        DataSource events_ds = EventDataSource.getEventDS();
        events_grid.setDataSource(events_ds);
        events_grid.setAutoFetchData(true);
        
        EventRecord[] records = (EventRecord[]) MockData.getEvents();
        for (EventRecord record : records) {
        	events_ds.addData(record);
        }
        //events_grid.setData(records);
        
        layout.addMember(events_grid);
        layout.addMember(test);
        
		return layout;
	}
}
