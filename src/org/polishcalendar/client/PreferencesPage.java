package org.polishcalendar.client;

import org.polishcalendar.client.MockData.PreferenceRecord;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.DateChooser;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.DataChangedEvent;  
import com.smartgwt.client.widgets.events.DataChangedHandler;  
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.BlurbItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class PreferencesPage {
	
	// preferences pickup
	private ListGrid social_select = null;
	private ListGrid cultural_select = null;
	private ListGrid political_select = null;
	private ListGrid other_select = null;

	public Layout build() {
		VLayout output = new VLayout();
		
		Layout top_panel = buildTopPanel();
		top_panel.setWidth100();
		top_panel.setHeight("10%");
		output.addMember(top_panel);
		
		Layout main_panel = buildMainPanel();
		main_panel.setWidth100();
		main_panel.setHeight("*");
		output.addMember(main_panel);
		
		return output;
	}
	
	private Layout buildTopPanel() {
		return (new TopPanel()).build();
	}
	
	private Layout buildMainPanel() {
		VLayout output = new VLayout();
		
		// Building main tabs component
        final TabSet left_tabs = new TabSet();  
        left_tabs.setTabBarPosition(Side.LEFT); 
        left_tabs.setHeight100();
        left_tabs.setWidth100();
        left_tabs.setTabBarThickness(80);
        
        Tab interests_tab = new Tab("Interests");
        interests_tab.setPane(createInterestsContent());
        left_tabs.addTab(interests_tab);
        
        Tab location_tab = new Tab("Location");
        location_tab.setPane(createLocationContent());
        left_tabs.addTab(location_tab);
        
        Tab time_tab = new Tab("Time");
        time_tab.setPane(createTimeContent());
        left_tabs.addTab(time_tab);
        
        output.addMember(left_tabs);
        return output;
	}
	
	private Layout buildBottomPanel() {
		
		// TODO stub

		return null;
	}
	
	// creates interests tbb
	private Layout createInterestsContent() {
		VLayout main_layout = new VLayout();
		main_layout.setMembersMargin(10);
		
		// creating preference pick-up
        final SectionStack preferences_stack = new SectionStack();  
        preferences_stack.setVisibilityMode(VisibilityMode.MULTIPLE);  
        preferences_stack.setWidth100();
        preferences_stack.setOverflow(Overflow.VISIBLE);
        preferences_stack.setHeight("90%");
        preferences_stack.setMembersMargin(10);
        preferences_stack.setAnimateSections(true);
        main_layout.addMember(preferences_stack);
        
    	social_select = createPreferencesGrid(MockData.getSocialPreferences());
    	cultural_select = createPreferencesGrid(MockData.getCulturalPreferences());
    	political_select = createPreferencesGrid(MockData.getPoliticalPreferences());
    	other_select = createPreferencesGrid(MockData.getOtherPreferences());
    	
        SectionStackSection section1 = new SectionStackSection("Social");  
        section1.setExpanded(true);  
        section1.setCanCollapse(true);  
        section1.addItem(social_select);  
        preferences_stack.addSection(section1);
        
        SectionStackSection section2 = new SectionStackSection("Cultural");  
        section2.setExpanded(true);  
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
        
		// creating bottom buttons
		Layout bottom_widget = createBottomButtons();
		bottom_widget.setHeight("5%");
		main_layout.addMember(bottom_widget);
		
		return main_layout;
	}
	
	// creates location tab
	private Layout createLocationContent() {
		VLayout output = new VLayout();
		output.setMembersMargin(10);
		
		// Creating explanation
		HTMLFlow description = new HTMLFlow();
		description.setStyleName("exampleTextBlock");
		description.setContents("<h3>Your locations of interests</h3>");
		description.setAlign(Alignment.CENTER);
		output.addMember(description);
		
		// creating locations grid
		final ListGrid locations_grid = new ListGrid();
		locations_grid.setShowAllRecords(true);
		locations_grid.setCanRemoveRecords(true); 
		
        ListGridField location_field = new ListGridField("location");  
        locations_grid.setFields(location_field);
        locations_grid.setData(MockData.getLocationRecords()); 
        locations_grid.setShowEdges(false);
        locations_grid.setShowHeader(false);
        locations_grid.setShowHeaderContextMenu(false);
        output.addMember(locations_grid);
        
        // creating search box
        final DynamicForm form = new DynamicForm();  
        final TextItem location_item = new TextItem("location_search");
        location_item.setTitle("Location");
        location_item.setHint("<nobr>Search for a given location of interests</nobr>");
        form.setFields(location_item);
        output.addMember(form);
        
		Button add_button = new Button("Add Location");
		add_button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String location = location_item.getValueAsString();
				if (location != null && !location.isEmpty()) {
					locations_grid.addData(new MockData.LocationRecord(location));
				}
			}
		});
		output.addMember(add_button);
		
		// creating bottom buttons
		Layout bottom_widget = createBottomButtons();
		bottom_widget.setHeight("5%");
		output.addMember(bottom_widget);
		
		return output;
	}
	
	// creates time tab
	private Layout createTimeContent() {
		VLayout output = new VLayout();
		
		// Building main tabs component
        final TabSet time_tabs = new TabSet();  
        time_tabs.setTabBarPosition(Side.TOP); 
        time_tabs.setHeight100();
        time_tabs.setWidth100();
        
        Tab general_tab = new Tab("General");
        general_tab.setPane(createGeneralTimeContent());
        time_tabs.addTab(general_tab);
        
        Tab specific_tab = new Tab("Specific");
        specific_tab.setPane(createSpecificTimeContent());
        time_tabs.addTab(specific_tab);
        
        output.addMember(time_tabs);
        return output;
	}
	
	// creates content of time general tab
	private Layout createGeneralTimeContent() {
		VLayout output = new VLayout();
		output.setMembersMargin(20);
		
		// Creating hours form
		final DynamicForm hours = new DynamicForm();
        
        HeaderItem hours_header = new HeaderItem();  
        hours_header.setDefaultValue("Set hours of interest");  
  
        TextItem hour_from = new TextItem("hour_from");
        hour_from.setTitle("From");
        
        TextItem hour_to = new TextItem("hour_to");
        hour_to.setTitle("To");
        
        hours.setFields(hours_header, hour_from, hour_to);
        output.addMember(hours);
        
        // creating spacer
        LayoutSpacer spacer = new LayoutSpacer();
        spacer.setHeight("10%");
        output.addMember(spacer);
        
        // Creating days form
		final DynamicForm days = new DynamicForm();
        
        HeaderItem days_header = new HeaderItem();  
        days_header.setDefaultValue("Pick days of interests");  
  
        CheckboxItem monday_i = new CheckboxItem();  
        monday_i.setName("monday");  
        monday_i.setTitle("Monday");
        
        CheckboxItem tuesday_i = new CheckboxItem();  
        tuesday_i.setName("tuesday");  
        tuesday_i.setTitle("Tuesday");
        
        CheckboxItem wednesday_i = new CheckboxItem();  
        wednesday_i.setName("wednesday");  
        wednesday_i.setTitle("Wednesday");
        
        CheckboxItem thursday_i = new CheckboxItem();  
        thursday_i.setName("thursday");  
        thursday_i.setTitle("Thursday");
        
        CheckboxItem friday_i = new CheckboxItem();  
        friday_i.setName("friday");  
        friday_i.setTitle("Friday");
        
        CheckboxItem saturday_i = new CheckboxItem();  
        saturday_i.setName("saturday");  
        saturday_i.setTitle("Saturday");
        
        CheckboxItem sunday_i = new CheckboxItem();  
        sunday_i.setName("sunday");  
        sunday_i.setTitle("Sunday");
        
        days.setFields(days_header, monday_i, tuesday_i, wednesday_i, thursday_i, friday_i, saturday_i, sunday_i);
        output.addMember(days);
        
        // creating spacer
        LayoutSpacer spacer2 = new LayoutSpacer();
        spacer2.setHeight("20%");
        output.addMember(spacer2);
        
		// creating bottom buttons
		Layout bottom_widget = createBottomButtons();
		bottom_widget.setHeight("5%");
		output.addMember(bottom_widget);
        
		return output;
	}
	
	// creates content of time specific tab
	private Layout createSpecificTimeContent() {
		VLayout output = new VLayout();
		output.setMembersMargin(10);

		// Creating explanation1
		HTMLFlow description1 = new HTMLFlow();
		description1.setContents("<h3>Select single from and to dates of interest</h3>");
		description1.setAlign(Alignment.CENTER);
		output.addMember(description1);
		
        // creating spacer
        LayoutSpacer spacer = new LayoutSpacer();
        spacer.setHeight("5%");
        output.addMember(spacer);
		
		// Creating From and To calendars
		HLayout calendars_layout = new HLayout();
		calendars_layout.setMembersMargin(20);
		
        // creating spacer
        LayoutSpacer cal_spacer1 = new LayoutSpacer();
        cal_spacer1.setWidth("5%");
        calendars_layout.addMember(cal_spacer1);
		
		// creating from calendar
		VLayout from_layout = new VLayout();
		from_layout.setMembersMargin(5);
		
		HTMLFlow from_txt = new HTMLFlow();
		from_txt.setContents("FROM");
		from_txt.setAlign(Alignment.CENTER);
		from_layout.addMember(from_txt);
		
        DynamicForm from_date = new DynamicForm();   
        final BlurbItem from_blurb = new BlurbItem();  
        from_date.setItems(from_blurb);
        final DateChooser from_chooser = new DateChooser();    
        from_chooser.addDataChangedHandler(new DataChangedHandler() {  
            @Override  
            public void onDataChanged(DataChangedEvent event) {  
            	from_blurb.setValue("Selected from date : " + from_chooser.getData());
            }  
        });
        from_layout.addMember(from_chooser);
        from_layout.addMember(from_date);
        
        calendars_layout.addMember(from_layout);

        // creating spacer
        LayoutSpacer cal_spacer2 = new LayoutSpacer();
        cal_spacer2.setWidth("25%");
        calendars_layout.addMember(cal_spacer2);
        
        // creating to calendar
		VLayout to_layout = new VLayout();
		to_layout.setMembersMargin(5);
		
		HTMLFlow to_txt = new HTMLFlow();
		to_txt.setContents("TO");
		to_txt.setAlign(Alignment.CENTER);
		to_layout.addMember(to_txt);
		
        DynamicForm to_date = new DynamicForm();   
        final BlurbItem to_blurb = new BlurbItem();  
        to_date.setItems(to_blurb);
        final DateChooser to_chooser = new DateChooser();    
        to_chooser.addDataChangedHandler(new DataChangedHandler() {  
            @Override  
            public void onDataChanged(DataChangedEvent event) {  
            	to_blurb.setValue("Selected to date : " + to_chooser.getData());
            }  
        });
        to_layout.addMember(to_chooser);
        to_layout.addMember(to_date);
        
        calendars_layout.addMember(to_layout);
        
        // creating spacer
        LayoutSpacer cal_spacer3 = new LayoutSpacer();
        cal_spacer3.setWidth("5%");
        calendars_layout.addMember(cal_spacer3);
        
        //calendars_layout.setHeight("30%");
        output.addMember(calendars_layout);
        
		// creating bottom buttons
		Layout bottom_widget = createBottomButtons();
		bottom_widget.setHeight("5%");
		output.addMember(bottom_widget);
		
		return output;
	}
	
	// creates grid with given preference records
	private ListGrid createPreferencesGrid(PreferenceRecord[] records) {
		
		ListGrid preferences_grid = new ListGrid();
		preferences_grid.setShowAllRecords(false);
		preferences_grid.setSelectionType(SelectionStyle.SIMPLE);  
		preferences_grid.setSelectionAppearance(SelectionAppearance.CHECKBOX); 
  
        ListGridField nameField = new ListGridField("name");  
        preferences_grid.setFields(nameField);
        preferences_grid.setData(records); 
        preferences_grid.setShowEdges(false);
        preferences_grid.setShowHeader(false);
        preferences_grid.setShowHeaderContextMenu(false);
        
        return preferences_grid;
	}
	
	private Layout createBottomButtons() {
		HLayout button_layout = new HLayout();
		button_layout.setMembersMargin(5);
		
		Button save_button = new Button("Save Preferences");
		save_button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO stub
			}
		});
		
		Button discard_button = new Button("Discard");
		discard_button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		button_layout.addMember(save_button);
		button_layout.addMember(discard_button);
		
		return button_layout;
	}
}
