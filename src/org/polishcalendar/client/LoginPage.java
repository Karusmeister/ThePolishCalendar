package org.polishcalendar.client;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;  
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;  
import com.smartgwt.client.widgets.form.fields.HeaderItem;  
import com.smartgwt.client.widgets.form.fields.PasswordItem;  
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class LoginPage {

	public Canvas buildLoginPage() {
		
		Canvas top_panel = buildTopPanel();
		Canvas bottom_panel = buildBottomPanel();
		Canvas main_panel = buildMainPanel();
		
		VLayout output = new VLayout();
		top_panel.setWidth100();
		top_panel.setHeight("10%");
		output.addMember(top_panel);
		main_panel.setWidth("20%");
		//main_panel.setHeight("20%");
		main_panel.setAlign(Alignment.CENTER);
		output.addMember(main_panel);
		
		return output;
	}
	
	private Canvas buildTopPanel() {
		
		HLayout main_layout = new HLayout();
		main_layout.setMembersMargin(10);
		
		Button reg_button = new Button("Register");
		Button contact_button = new Button("Contact us");
		
		// Each button would stretch for stretch% of available width
		String stretch = "25%";
		//reg_button.setWidth(stretch);  
		reg_button.setShowRollOver(true);  
		reg_button.setShowDisabled(true);  
		reg_button.setShowDown(true);  
		
		//contact_button.setWidth(stretch);  
		contact_button.setShowRollOver(true);  
		contact_button.setShowDisabled(true);  
		contact_button.setShowDown(true);  
		
		main_layout.addMember(reg_button);
		main_layout.addMember(contact_button);
		
		return main_layout;
	}
	
	
	private Canvas buildMainPanel() {
  
		// TODO: IMPROVE VALIDATION! Validation runs on the client side!
		// Need to change add underlying DateSource objects to perform
		// validations on them and thus on the server side!
		
        // Building the view
		VLayout main_layout = new VLayout();
		main_layout.setMembersMargin(15);
		main_layout.setLayoutMargin(10);
		
		// Building login form
        final DynamicForm form = new DynamicForm();  
        
        HeaderItem header = new HeaderItem();  
        header.setDefaultValue("Login Form");  
  
        TextItem loginItem = new TextItem("login");
        loginItem.setTitle("Login");
        
        final PasswordItem passwordItem = new PasswordItem("password");
        passwordItem.setTitle("Password");
        
        form.setFields(header, loginItem, passwordItem);
        main_layout.addMember(form);
        
        // Building login and register buttons
		HLayout buttons_layout = new HLayout();
		buttons_layout.setMembersMargin(10);
		
		Button login_button = new Button("Log in");
		login_button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String input_password = passwordItem.getValueAsString();
				if (input_password != null && 
						input_password.equals(MockData.getValidUser().getPassword())) {
					CalendarPage calendar_page = new CalendarPage();
					Canvas content = calendar_page.build();
					PolishCalendarDev.replaceOutmostContent(content);
				}
				else {
					Window.alert("Incorrect password!");
				}
			}
		});
		
		Button register_button = new Button("Register");
		register_button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RegistrationPage regPage = new RegistrationPage();
				Canvas regContent = regPage.buildRegistrationPage();
				PolishCalendarDev.replaceOutmostContent(regContent);
			}
			
		});
		
		buttons_layout.addMember(login_button);
		buttons_layout.addMember(register_button);
		
		main_layout.addMember(buttons_layout);
        
        /*
        ButtonItem loginButton = new ButtonItem("log");
        loginButton.setWidth("150");  
        loginItem.setTitle("Login");  
        loginItem.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
                boolean valid = form.validate(false);
                if (!valid) {
                	form.clearValues();
                }
                else {
                	Window.alert("Password ok");
                }
            }  
        });  
        */
              
        return main_layout;  
    }  
	
	private Canvas buildBottomPanel() {
		return null;
	}
}
