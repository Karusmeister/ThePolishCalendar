package org.polishcalendar.client;

import org.polishcalendar.client.util.AppConstants;
import org.polishcalendar.ds.LocPrefDS;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.calendar.Calendar;
import com.smartgwt.client.widgets.calendar.events.BackgroundClickEvent;
import com.smartgwt.client.widgets.calendar.events.BackgroundClickHandler;
import com.smartgwt.client.widgets.calendar.events.CalendarEventClick;
import com.smartgwt.client.widgets.calendar.events.EventClickHandler;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.ValuesManager; 
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.TimeItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class CalendarPage {
	
	private Calendar calendar;

	public Canvas build() {
		
		Canvas top_panel = buildTopPanel();
		Canvas bottom_panel = buildBottomPanel();
		Canvas main_panel = buildMainPanel();
		main_panel.setPadding(10);
		
		// layouting 
		VLayout output = new VLayout();
		output.setMembersMargin(10);
		
		top_panel.setWidth100();
		top_panel.setHeight("10%");
		output.addMember(top_panel);
		
		main_panel.setWidth100();
		main_panel.setHeight("*");
		output.addMember(main_panel);
		
		bottom_panel.setWidth100();
		bottom_panel.setHeight("10%");
		output.addMember(bottom_panel);
		
		return output;
	}
	
	public Canvas buildOrganisationCalendar(){
	
		VLayout output = new VLayout();
		
		Canvas top_panel = buildTopPanel();
		// Canvas bottom_panel = buildBottomPanel();
		
		Canvas main_panel = buildMainOrganisationPanel();
		
		output.addMember(top_panel);
		output.addMember(main_panel);

		return output;
	}
	
	private Canvas buildMainOrganisationPanel(){
		
		HLayout output = new HLayout();
		
		// Creating calendar
		VLayout cal_layout = new VLayout(); 
		cal_layout.setMembersMargin(10);
		
		calendar = new Calendar();
		// canEditEvents false cause that disables standart creator. Gonna
		// use self made creator
		calendar.setCanEditEvents(false);
		calendar.setCanCreateEvents(false);
		calendar.setAddDropValues(true);
		calendar.setWidth100();
		calendar.setHeight("90%");
		calendar.setData(MockData.getCalendarData());
		// no pop up for user
		
		calendar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final Window creator_popup = buildNewEventPopup();
				creator_popup.show();
			}
		});
	
		

		cal_layout.addMember(calendar);
		
		return output;
		
	}
	
	private Canvas buildTopPanel() {
		return (new TopPanel()).build();
	}
	
	
	private Canvas buildMainPanel() {
		
		HLayout main_layout = new HLayout();
		main_layout.setMembersMargin(15);
		
		//
		// Creating vertical panel on the left
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
		// user specification of calendar
		calendar.setCanEditEvents(false);
		calendar.setCanCreateEvents(false);
		calendar.setAddDropValues(true);
		calendar.setWidth100();
		calendar.setHeight("90%");
		calendar.setData(MockData.getCalendarData());
		// no pop up for user
		
		/*calendar.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final Window creator_popup = buildNewEventPopup();
				creator_popup.show();  
			}
		});
		*/

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
		
		return (new BottomPanel().build());	
	}
	
	/* Components */
	private Layout createFilterButtons() {
		
		VLayout buttons_layout = new VLayout();
		buttons_layout.setMembersMargin(5);
		
		Button all_events = new Button("Show All Events");
		all_events.setWidth("50%");
		buttons_layout.addMember(all_events);
		all_events.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) { 
				calendar.setData(MockData.getCalendarData());
			}
		});
		
		Button attending_events = new Button("Show Events I am Attending");
		attending_events.setWidth("50%");
		buttons_layout.addMember(attending_events);
		attending_events.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) { 
				calendar.setData(MockData.getAttendingEvents());
			}
		});
		
		Button preference_events = new Button("Show Events Matching My Prefs");
		preference_events.setWidth("50%");
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
		orgs_b.setWidth("50%");
		orgs_b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final Window orgs_popup = buildOrgPopup();
				orgs_popup.show();  
			}
		});
		output.addMember(orgs_b);
		
		Button pref_b = new Button("My Preferences");
		pref_b.setWidth("50%");
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
		manage_events_b.setWidth("50%");
		output.addMember(manage_events_b);
		
		Button new_events_b =  new Button("Create New Events");
		new_events_b.setWidth("50%");
		new_events_b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final Window creator_popup = buildNewEventPopup();
				creator_popup.show();  
			}
		});
		//output.addMember(new_events_b);
		
		Button import_b = new Button("Import Existing Calendar");
		import_b.setWidth("50%");
		output.addMember(import_b);
		
		return output;
	}
	
	/* Popups */
	private Window buildOrgPopup() {
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
			= buildOrgPopupLayout(AppConstants.ORGS_POPUP_WIDTH,
					AppConstants.ORGS_POPUP_HEIGHT);
		orgs_popup.addItem(content);  
		return orgs_popup;  
	}
	
	private Window buildNewEventPopup() {
		final Window creator_popup = new Window();
		
		// Setting popup
		creator_popup.setWidth(AppConstants.EVCREATOR_POPUP_WIDTH);  
		creator_popup.setHeight(AppConstants.EVCREATOR_POPUP_HEIGHT);  
		creator_popup.setTitle("Create event");  
		creator_popup.setShowMinimizeButton(false);  
		creator_popup.setIsModal(true);  
		creator_popup.setShowModalMask(true);  
		creator_popup.centerInPage();  
		creator_popup.addCloseClickHandler(new CloseClickHandler() {  
            public void onCloseClick(CloseClientEvent event) {
            	creator_popup.destroy();  
            }  
        }); 
		
		// Setting popup's  content
		Layout content = buildEventCreator();
		creator_popup.addItem(content); 
		return creator_popup;
	}
	
	private Layout buildOrgPopupLayout(int parent_width, int parent_height) {
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
	
	
	private Layout buildEventCreator () {
		VLayout output = new VLayout();
		
		final TabSet creator_tabs = new TabSet();
		
		Tab general_tab = new Tab();
		general_tab.setTitle("General");
		
		final Tab categories_tab = new Tab();
		categories_tab.setTitle("Categories");
		categories_tab.setDisabled(true);
		
		creator_tabs.setTabs(general_tab , categories_tab);
		
		// Creating first tab content
		VLayout general_l = new VLayout();
		general_l.setMembersMargin(10);
		
		// Creating explanation
		HTMLFlow general_description = new HTMLFlow();
		general_description.setStyleName("exampleTextBlock");
		general_description.setContents("<h3>Please enter the general information about your event");
		general_description.setAlign(Alignment.CENTER);
		general_l.addMember(general_description);
		
		LayoutSpacer spacer = new LayoutSpacer();
		spacer.setHeight("15%");
		general_l.addMember(spacer);
		
		final DynamicForm form1 = new DynamicForm();
		form1.setID("form1");
		form1.setCellPadding(5);
		
		TextItem name_field = new TextItem("name_f" , "Name");
		name_field.setRequired(true);
		
		SelectItem location_item = new SelectItem("location_f");
        ListGridField search_field = new ListGridField("search_loc"); 
        ListGrid search_grid_props = new ListGrid();  
        search_grid_props.setShowFilterEditor(true);
		location_item.setOptionDataSource(LocPrefDS.getInstance());
        location_item.setTitle("Location");
        location_item.setHint("<nobr>Search for a given location of interests</nobr>");
        location_item.setDisplayField("search_loc"); 
        location_item.setPickListWidth(300); 
        location_item.setPickListFields(search_field);  
        location_item.setPickListProperties(search_grid_props); 
        location_item.setRequired(true);
        
        TextAreaItem descritpion = new TextAreaItem("description_f" , "Description");
        descritpion.setRequired(true);
        
        DateItem date = new DateItem("when_f" , "When");
        date.setRequired(true);
        
        TimeItem start_time = new TimeItem("stime_f" , "Start Time");
        start_time.setRequired(true);
		
        TimeItem end_time = new TimeItem("etime_f" , "End Time");
        
        form1.setFields(name_field, location_item , descritpion, date, start_time , end_time);
        general_l.addMember(form1);
        general_l.setAlign(Alignment.CENTER);
        
		LayoutSpacer spacer2 = new LayoutSpacer();
		spacer2.setHeight("30%");
		general_l.addMember(spacer2);
        
        // Creating bottom buttons
        HLayout buttons_l = new HLayout();
        
        Button next_b = new Button("Next");
        next_b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				form1.validate();
				if (!form1.hasErrors()) {
					categories_tab.setDisabled(false);
					creator_tabs.selectTab(1);
				}
			}
		});
        buttons_l.addMember(next_b);
        
        Button clear_b = new Button("Clear");
        buttons_l.addMember(clear_b);
        general_l.addMember(buttons_l);
       
        general_tab.setPane(general_l);
        
        // Creating categories content
        VLayout cat_content = new VLayout();
        cat_content.setMembersMargin(5);
        
		// Creating explanation
		HTMLFlow metadata_description = new HTMLFlow();
		metadata_description.setStyleName("exampleTextBlock");
		metadata_description.setContents("<h3>Plase tick those categories that best describe your event");
		metadata_description.setAlign(Alignment.CENTER);
		cat_content.addMember(metadata_description);
        
		// Creating main content
        Layout metadata_l = (new PreferencesPage()).createPreferenceStack() ;
        cat_content.addMember(metadata_l);
        
        // Creating submit button
        Button submit_b = new Button("Submit");
        cat_content.addMember(submit_b);
        
        categories_tab.setPane(cat_content);
        
        output.addMember(creator_tabs);
		return output;
	}
}
