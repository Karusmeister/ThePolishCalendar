package org.polishcalendar.client;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.data.DataSource;  
import com.smartgwt.client.data.fields.DataSourcePasswordField;  
import com.smartgwt.client.data.fields.DataSourceTextField;  
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;  
import com.smartgwt.client.widgets.form.DynamicForm;  
import com.smartgwt.client.widgets.form.fields.ButtonItem;  
import com.smartgwt.client.widgets.form.fields.CheckboxItem;  
import com.smartgwt.client.widgets.form.fields.HeaderItem;  
import com.smartgwt.client.widgets.form.fields.PasswordItem;  
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;  
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;  
import com.smartgwt.client.widgets.form.validator.CustomValidator;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;  
import com.smartgwt.client.widgets.form.validator.RegExpValidator;  
import com.smartgwt.client.widgets.form.validator.Validator;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class LoginPage {

	public Canvas buildLoginPage() {
		
		Canvas top_panel = buildTopPanel();
		Canvas bottom_panel = buildBottomPanel();
		Canvas main_panel = buildMainPanel();
		
		VLayout main_layout = new VLayout();
		top_panel.setWidth(600);
		top_panel.setHeight("10%");
		main_layout.addMember(top_panel);
		main_panel.setWidth(600);
		main_panel.setHeight("*");
		main_layout.addMember(main_panel);
		
		return main_layout;
	}
	
	private Canvas buildTopPanel() {
		
		HLayout main_layout = new HLayout();
		main_layout.setMembersMargin(10);
		
		Button login_button = new Button("Login");
		Button reg_button = new Button("Register");
		Button about_button = new Button("About us");
		Button contant_button = new Button("Contact us");
		
		// Each button would stretch for 25% of available width
		login_button.setWidth("25%");  
		login_button.setShowRollOver(true);  
		login_button.setShowDisabled(true);  
		login_button.setShowDown(true);  
		
		reg_button.setWidth("25%");  
		reg_button.setShowRollOver(true);  
		reg_button.setShowDisabled(true);  
		reg_button.setShowDown(true);  
		
		about_button.setWidth("25%");  
		about_button.setShowRollOver(true);  
		about_button.setShowDisabled(true);  
		about_button.setShowDown(true);  
		
		contant_button.setWidth("25%");  
		contant_button.setShowRollOver(true);  
		contant_button.setShowDisabled(true);  
		contant_button.setShowDown(true);  
		
		main_layout.addMember(login_button);
		main_layout.addMember(reg_button);
		main_layout.addMember(about_button);
		main_layout.addMember(contant_button);
		
		return main_layout;
	}
	
	
	private Canvas buildMainPanel() {
  
		// TODO: IMPROVE VALIDATION! Validation runs on the client side!
		// Need to change add underlying DateSource objects to perform
		// validations on them and thus on the server side!
		
        // Building the view
        final DynamicForm form = new DynamicForm();  
        
        HeaderItem header = new HeaderItem();  
        header.setDefaultValue("Login Form");  
  
        TextItem loginItem = new TextItem("login");
        loginItem.setTitle("Login");
        
        PasswordItem passwordItem = new PasswordItem("password");
        passwordItem.setTitle("Password");
        // Creating the validatior
        CustomValidator validator = new CustomValidator() {
			@Override
			protected boolean condition(Object stored_password) {
				String input = getFormItem().getAttribute("password");
				return (input != null && input.equals("krzys"));
			}
        };
        validator.setErrorMessage("Password is not valid");
        passwordItem.setValidators(validator);
        
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
        
        form.setFields(header, loginItem, passwordItem, loginButton);
              
        return form;  
    }  
	
	private Canvas buildBottomPanel() {
		return null;
	}
}
