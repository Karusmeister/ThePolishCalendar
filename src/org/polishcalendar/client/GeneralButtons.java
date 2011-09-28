package org.polishcalendar.client;

import org.polishcalendar.client.util.AppConstants;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class GeneralButtons {

	public Layout buildGeneralButtons () {
		VLayout output = new VLayout();
		output.setMembersMargin(5);
		
		LayoutSpacer spacer = new LayoutSpacer();
		spacer.setHeight("40%");
		output.addMember(spacer);
		
		Button orgs_b = new Button("My Organizations");
		orgs_b.setWidth100();
		orgs_b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final Window orgs_popup = new Window();
				
				// Setting popup
				orgs_popup.setWidth(AppConstants.ORGS_POPUP_WIDTH);  
				orgs_popup.setHeight(AppConstants.ORGS_POPUP_HEIGHT);  
				orgs_popup.setTitle("Organizations you follow");  
				orgs_popup.setShowMinimizeButton(false);  
				orgs_popup.setIsModal(true);  
				orgs_popup.setShowModalMask(true);  
				orgs_popup.centerInPage();  
				orgs_popup.addCloseClickHandler(new CloseClickHandler() {  
                    public void onCloseClick(CloseClientEvent event) {
                    	orgs_popup.destroy();  
                    }  
                }); 
				
				// Setting popup's  content
				Layout content 
					= buildOrganizationPopup(AppConstants.ORGS_POPUP_WIDTH,
							AppConstants.ORGS_POPUP_HEIGHT);
				orgs_popup.addItem(content);  
				orgs_popup.show();  
			}
		});
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
	
	private Layout buildOrganizationPopup(int parent_width, int parent_height) {
		VLayout output = new VLayout();
		int cell_height = parent_height/5;
		
		// creating organizations grid
		// Image field : 10% of parent width
		double img_f_perc = 0.12;
		// Name field : 20% of parent width
		double name_f_perc = 0.2;
		// Description field : 50% of parent width
		double desc_f_perc = 0.5;
		// Link field : 20% of parent width
		double link_f_perc = 0.15;
		
		ListGrid orgs_grid = new ListGrid();
		orgs_grid.setWidth100();  
		orgs_grid.setHeight100();  
		orgs_grid.setShowAllRecords(true);  
		orgs_grid.setCanEdit(false);
		orgs_grid.setCellHeight(cell_height);
		orgs_grid.setWrapCells(true); 
		orgs_grid.setData(MockData.getOrganizations());
		
		ListGridField image_f = new ListGridField("image" , "Thumbnail");
		image_f.setType(ListGridFieldType.IMAGE);
		// set width to 10% of all width
		int imgf_width = (int)(img_f_perc * parent_width);
		image_f.setWidth(imgf_width);
		image_f.setAlign(Alignment.CENTER);
		image_f.setImageURLPrefix("orgs_thumbnails/");
		image_f.setImageURLSuffix(".jpg");
		image_f.setCellAlign(Alignment.CENTER);
		// set width and height of thumbnail to 80% of its container width and height
		image_f.setImageWidth((int) (0.8 * imgf_width));
		image_f.setImageHeight((int) (0.8 * cell_height));
		
		ListGridField name_f = new ListGridField("name" , "Name");
		name_f.setAlign(Alignment.CENTER);
		name_f.setCellAlign(Alignment.CENTER);
	    name_f.setWidth((int) (name_f_perc * parent_width));
		
		ListGridField description_f = new ListGridField("description" , "Description");
		description_f.setAlign(Alignment.CENTER);
		description_f.setWidth((int) (desc_f_perc * parent_width));
		
		ListGridField link_f = new ListGridField("link" , "Profile");
		link_f.setType(ListGridFieldType.LINK);
		link_f.setLinkText("click to view the profile");
		link_f.setAlign(Alignment.CENTER);
		link_f.setCellAlign(Alignment.CENTER);
		link_f.setWidth((int) (link_f_perc * parent_width));
		
		orgs_grid.setFields(image_f , name_f , description_f , link_f);
		
		output.addMember(orgs_grid);
		
		return output;
	}
}
