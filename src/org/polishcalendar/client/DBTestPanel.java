package org.polishcalendar.client;


import org.polishcalendar.ds.EventDataSource;
import org.polishcalendar.ds.OrganizationDataSource;
import org.polishcalendar.ds.UserDataSource;

import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler; 
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout; 
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;

public class DBTestPanel {
	
	public Canvas build() {
		
		VLayout output = new VLayout();	
		
		Button organizations_b = new Button("Test Organizations");
		output.addMember(organizations_b);
		
		Button my_events_b = new Button("Test Events");
		output.addMember(my_events_b);
		
		Button users_b = new Button("Test Users");
		output.addMember(users_b);
		
		// Setting all listeners
		organizations_b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
		        DataSource org_ds = OrganizationDataSource.getOrganizationDS();
				Canvas content = buildWindowContent(org_ds);
				buildWindow(content).show();
			}
		});
		
		my_events_b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
		        DataSource events_ds = EventDataSource.getEventDS();
				Canvas content = buildWindowContent(events_ds);
				buildWindow(content).show();
			}
		});
		
		users_b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
		        DataSource user_ds = UserDataSource.getUserDS();
				Canvas content = buildWindowContent(user_ds);
				buildWindow(content).show();
			}
		});
		
		return output;
	}
	
	private Window buildWindow (Canvas content) {
		final Window output_window = new Window();
		
		// Setting popup
		output_window.setWidth(700);  
		output_window.setHeight(500);  
		output_window.setShowMinimizeButton(false);  
		output_window.setIsModal(true);  
		output_window.setShowModalMask(true);  
		output_window.centerInPage();  
		output_window.addCloseClickHandler(new CloseClickHandler() {  
            public void onCloseClick(CloseClientEvent event) {
            	output_window.destroy();  
            }  
        }); 
		
		// Setting popup's  content
		output_window.addItem(content);  
		return output_window;
	}
	
	
	private Canvas buildWindowContent(final DataSource ds) {
		
        VLayout output = new VLayout();  
        
        final ListGrid main_grid = new ListGrid();  
        main_grid.setWidth100();  
        main_grid.setHeight100();  
        main_grid.setShowAllRecords(true); 
        main_grid.setCanEdit(true);
        main_grid.setCanRemoveRecords(true);
        
        Button new_record = new Button("New Record");
        new_record.addClickHandler(new ClickHandler() {
        	public void onClick(ClickEvent event) {
        		int id = main_grid.getRecords().length + 1;
        		ListGridRecord record = new ListGridRecord();
        		record.setAttribute("id", id);
        		main_grid.startEditingNew(record);
        	}
        });
        
        Button save = new Button("Save");
        save.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				main_grid.saveAllEdits();
			}
		});
        
        Button auto_update = new Button("Auto Update");
        auto_update.addClickHandler(new ClickHandler() {
        	@Override
        	public void onClick(ClickEvent event) {
        		ListGridRecord record = main_grid.getRecord(1);
        		record.setAttribute("name", "TESTUJE KURWA");
        		ds.updateData(record);
        	}
        });
        
        Button fetch = new Button("Fetch Data");
        fetch.addClickHandler(new ClickHandler() {
        	@Override
        	public void onClick(ClickEvent event) {
        		ds.fetchData(null, new DSCallback() {

					@Override
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						main_grid.setData(response.getData());
					}
        			
        		});
        	}
        });
  
        main_grid.setDataSource(ds);
        
        HLayout buttons = new HLayout();
        buttons.addMember(new_record);
        buttons.addMember(save);
        buttons.addMember(auto_update);
        buttons.addMember(fetch);
        
        output.addMember(main_grid);
        output.addMember(buttons);
		return output;
	}
}
