package org.polishcalendar.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourcePasswordField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;


public class AccountSettingsPage {
	
	// Current section content
	private Canvas section_content = null;

	// No need to rebuild those elements
	//private Layout personal_info = null;
	//private Layout email_settings = null;
	//private Layout password_settings = null;
	//private Layout language_settings = null;
	private Layout settings_buttons = null;
	
	public Canvas build() {
		Layout output = new VLayout();
		
		Layout top_panel = new TopPanel().build();
		top_panel.setWidth100();
		top_panel.setHeight("10%");
		output.addMember(top_panel);
		
		Layout main_panel = buildMainPanel();
		main_panel.setWidth100();
		main_panel.setHeight("*");
		output.addMember(main_panel);
		
		return output;
	}
	
	/* Building main panels */
	
	private Layout buildMainPanel() {
		
		// Building outermost panel
		VLayout outermost_layout = new VLayout();
		outermost_layout.setMembersMargin(10);
		Label title = new Label ("<font size='6px'>Account Settings</font>");
		title.setAlign(Alignment.CENTER);
		title.setHeight("10%");
		outermost_layout.addMember(title);
		
		HLayout main_content = new HLayout();
		main_content.setHeight("*");
		outermost_layout.addMember(main_content);
		
		// Building main content of the page, consisting of lhs
		// selection buttons and rhs selected panel
		Layout lhs_buttons = buildLhsButtons();
		lhs_buttons.setWidth("25%");
		lhs_buttons.setHeight100();
		lhs_buttons.setMembersMargin(15);
		lhs_buttons.setLayoutMargin(5);
		lhs_buttons.setShowEdges(true);
		main_content.addMember(lhs_buttons);
		
		section_content = new VLayout();
		section_content.setWidth("75%");
		section_content.setHeight100();
		section_content.setShowEdges(true);
		main_content.addMember(section_content);
		
		// Initial section content
		selectRHS(buildPersonalInfoSettings());
		
		return outermost_layout;
	}
	
	private Layout buildTopPanel() {
		return (new TopPanel()).build();
	}
	
	private Layout buildBottomPanel() {
		// TODO stub
		return null;
	}
	
	
	/* Aux methods */
	
	private Layout buildLhsButtons() {
		VLayout output = new VLayout();
		
		Button personal_info_b = new Button("Personal Information");
		personal_info_b.setWidth100();
		personal_info_b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				selectRHS(buildPersonalInfoSettings());
			}
		});
		output.addMember(personal_info_b);
		
		Button email_settings_b = new Button("Email Settings");
		email_settings_b.setWidth100();
		email_settings_b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				selectRHS(buildEmailSettings());
			}
		});
		output.addMember(email_settings_b);
		
		Button password_settings_b = new Button("Password Settings");
		password_settings_b.setWidth100();
		password_settings_b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				selectRHS(buildPasswordSettings());
			}
		});
		output.addMember(password_settings_b);
		
		Button language_settings_b = new Button("Language Settings");
		language_settings_b.setWidth100();
		language_settings_b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				selectRHS(buildLanguageSettings());
			}
		});
		output.addMember(language_settings_b);
		
		return output;
	}
	
	
	/* Building settings sections */
	
	private Layout buildPersonalInfoSettings() {
		VLayout personal_info = new VLayout();
		personal_info.setMembersMargin(20);

		// Building header
		Layout header_layout = buildHeaderLayout("Personal Info Settings");
		header_layout.setHeight("5%");
		personal_info.addMember(header_layout);

		// Building personal info form
		VLayout form_layout = new VLayout();
		final DynamicForm form = new DynamicForm();
		TextItem nameItem = new TextItem("name");
		nameItem.setTitle("Name");

		TextItem surnameItem = new TextItem("surname");
		surnameItem.setTitle("Surname");

		TextItem locationItem = new TextItem("location");
		locationItem.setTitle("Location");

		TextItem occupationItem = new TextItem("occupation");
		occupationItem.setTitle("Occupation");

		form.setFields(nameItem, surnameItem, locationItem, occupationItem);
		form.setHeight100();

		form_layout.addMember(form);
		form_layout.setHeight("20%");
		personal_info.addMember(form_layout);

		personal_info.addMember(buildSettingsButtons());

		return personal_info;
	}
	
	private Layout buildEmailSettings() {
		VLayout output = new VLayout();
		output.setMembersMargin(20);
		
		// Creating header 
		// Creating header 
		Layout header_layout = buildHeaderLayout("Email settings");
		header_layout.setHeight("5%");
		output.addMember(header_layout);
		
		// creating email settings
		VLayout email_layout = new VLayout();
		final DynamicForm form = new DynamicForm(); 
        final TextItem email_item = new TextItem("email");
        email_item.setTitle("Your current email");
        email_item.disable();
        email_item.setValue("gothren18@gmail.com");
		form.setFields(email_item);
		form.setWidth100();
		email_layout.addMember(form);
		
		final Button change_label = new Button("change");
		change_label.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				email_item.enable();
			}
		});
		email_layout.addMember(change_label);
        
		email_layout.setMembersMargin(5);
		email_layout.setHeight("5%");
		output.addMember(email_layout);
		
		// creating notifications grid
		VLayout grid_layout = new VLayout();
		
        final ListGrid notifications_grid = new ListGrid();  
        notifications_grid.setWidth100();  
        notifications_grid.setHeight100();  
        notifications_grid.setShowAllRecords(true);    
        notifications_grid.setEditEvent(ListGridEditEvent.CLICK);
   
        ListGridField name_field = new ListGridField("name", "Notification type");  
        name_field.setCanEdit(false);
        
        ListGridField selection_field = new ListGridField("selected", "Selected");  
        selection_field.setType(ListGridFieldType.BOOLEAN);  
        selection_field.setCanEdit(true);
        
        ListGridField how_field = new ListGridField("how", "How would you like to be notified");  
        SelectItem notifications_select = new SelectItem();  
        notifications_select.setValueMap("Email", "Message Box");  
        how_field.setEditorType(notifications_select); 
        how_field.setCanEdit(true);
  
        notifications_grid.setFields(new ListGridField[] {name_field, how_field, selection_field});  
        notifications_grid.setData(MockData.getNotificationPreferences()); 
        
        grid_layout.addMember(notifications_grid);
        grid_layout.setHeight("60%");
        output.addMember(grid_layout);
        
		output.addMember(buildSettingsButtons());
        
		return output;
	}
	
	private Layout buildPasswordSettings() {
		VLayout output = new VLayout();
		output.setMembersMargin(20);
		
		// Creating header 
		Layout header_layout = buildHeaderLayout("Password settings");
		header_layout.setHeight("5%");
		output.addMember(header_layout);
		
		// Building datasource
		DataSource data_source = new DataSource();
		DataSourcePasswordField old_password_field 
			= new DataSourcePasswordField("oldpassword", "Old password", 20, true);
		DataSourcePasswordField new_password_field 
			= new DataSourcePasswordField("newpassword", "New password", 20, true); 
		data_source.setFields(old_password_field , new_password_field);
		
		// Building form
		VLayout form_layout = new VLayout();
        final DynamicForm form = new DynamicForm();  
        form.setDataSource(data_source);  
        form.setUseAllDataSourceFields(true);
        
        PasswordItem old_password_item = new PasswordItem();  
        old_password_item.setName("oldpassword");
        
        PasswordItem new_password_item = new PasswordItem();  
        new_password_item.setName("newpassword");  
  
        PasswordItem new_password_item2 = new PasswordItem();  
        new_password_item2.setName("newpassword2");  
        new_password_item2.setTitle("Re-enter new password");  
        new_password_item2.setRequired(true);  
        new_password_item2.setLength(20);
		
        MatchesFieldValidator matchesValidator = new MatchesFieldValidator();  
        matchesValidator.setOtherField("newpassword");  
        matchesValidator.setErrorMessage("Passwords do not match");          
        new_password_item2.setValidators(matchesValidator);
        
        form.setFields(old_password_item, new_password_item, new_password_item2);
        form.setWidth100();
        form.setHeight100();
        
        form_layout.addMember(form);
        form_layout.setHeight("20%");
        output.addMember(form_layout);
        
		output.addMember(buildSettingsButtons());
        
		return output;
	}
	
	private Layout buildLanguageSettings() {
		VLayout language_settings = new VLayout();
		language_settings.setMembersMargin(20);

		// Creating header
		Layout header_layout = buildHeaderLayout("Language settings");
		header_layout.setHeight("5%");
		language_settings.addMember(header_layout);

		// Creating form
		VLayout form_layout = new VLayout();
		final DynamicForm form = new DynamicForm();
		ComboBoxItem cbItem = new ComboBoxItem();
		cbItem.setTitle("Select language:");
		cbItem.setType("comboBox");
		cbItem.setValueMap("Polish", "English");
		form.setFields(cbItem);

		form_layout.addMember(form);
		form_layout.setHeight("10%");
		language_settings.addMember(form_layout);

		language_settings.addMember(buildSettingsButtons());
		return language_settings;
	}
	
	private Layout buildSettingsButtons() {
		if (settings_buttons == null) {
			settings_buttons = new HLayout();
			settings_buttons.setMembersMargin(5);
			
			Button save = new Button("Save");
			settings_buttons.addMember(save);
			
			Button discard = new Button("Discard");
			settings_buttons.addMember(discard);
		}
		
		return settings_buttons;
	}
	
	private void selectRHS(Layout rhs_content) {
		for (Canvas child: section_content.getChildren()) {
			child.hide();
		}
		if (!section_content.contains(rhs_content)) {
			rhs_content.setWidth100();
			rhs_content.setHeight100();
			section_content.addChild(rhs_content);
		}
		rhs_content.show();
	}
	
	private Layout buildHeaderLayout(String content) {
		VLayout header_layout = new VLayout();
		Label title = new Label ("<font size='5px'>"+content+"</font>");
		title.setAlign(Alignment.CENTER);
		title.setHeight100();
		title.setWidth100();
		
		header_layout.addMember(title);
		header_layout.setShowEdges(true);
		
		return header_layout;
	}
}
