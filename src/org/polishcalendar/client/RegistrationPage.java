package org.polishcalendar.client;


import java.util.Date;

import org.polishcalendar.ds.UserDataSource;

import com.smartgwt.client.data.DataSource;  
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;  
import com.smartgwt.client.widgets.form.DynamicForm;  
import com.smartgwt.client.widgets.form.fields.ButtonItem;  
import com.smartgwt.client.widgets.form.fields.CheckboxItem;  
import com.smartgwt.client.widgets.form.fields.HeaderItem;  
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;  
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;  
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;  
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;  
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;

public class RegistrationPage {
	
	private DynamicForm form;
	
	public Canvas buildRegistrationPage() {
		
 		return buildRegistrationForm();
	}

	
	public Canvas buildRegistrationForm()
	{
        DataSource dataSource = UserDataSource.getUserDS();

        form = new DynamicForm();  
        form.setDataSource(dataSource);  
        form.setUseAllDataSourceFields(true);
  
        HeaderItem header = new HeaderItem();  
        header.setDefaultValue("Registration Form");  
  
        PasswordItem passwordItem = new PasswordItem();  
        passwordItem.setName("password");  
  
        PasswordItem passwordItem2 = new PasswordItem();  
        passwordItem2.setName("password2");  
        passwordItem2.setTitle("Password Again");  
        passwordItem2.setRequired(true);  
        passwordItem2.setLength(20);  
  
        MatchesFieldValidator matchesValidator = new MatchesFieldValidator();  
        matchesValidator.setOtherField("password");  
        matchesValidator.setErrorMessage("Passwords do not match");          
        passwordItem2.setValidators(matchesValidator);
        
        // Creating secret fields in order to bypass DS limitations
        // GWT is fucked up so you have to hide them explicitly
        HiddenItem date_item = new HiddenItem();
        date_item.setName("joinedDate");
        date_item.setValue(new Date());
        
        HiddenItem type_item = new HiddenItem();
        type_item.setName("type");
        type_item.setValue("user");
        
        // This will be ignored but is required for avoiding null pntr exception
        HiddenItem id_item = new HiddenItem();
        id_item.setName("id");
        id_item.setValue(-1);
  
        CheckboxItem acceptItem = new CheckboxItem();  
        acceptItem.setName("acceptTerms");  
        acceptItem.setTitle("I accept the terms of use.");  
        acceptItem.setRequired(true);  
        acceptItem.setWidth(150); 
        
        ButtonItem validateItem = new ButtonItem();  
        validateItem.setTitle("Next");  
        validateItem.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {  
               if (form.validate(true)) { 
   				   form.getDataSource().addData(form.getValuesAsRecord());
            	   PolishCalendarDev.replaceOutmostContent(createPreferencesSetUp());
               }
            }  
        });  
        
        
        ButtonItem registerOrg= new ButtonItem();
        registerOrg.setTitle("Register Organisation");
        registerOrg.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				PolishCalendarDev.replaceOutmostContent((new OrganisationRegistration()).buildOrganisationRegistration());	
			}
		});
        
        
        ButtonItem backTologinPage = new ButtonItem("Back");
        backTologinPage.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				PolishCalendarDev.replaceOutmostContent((new LoginPage()).buildLoginPage());			
			}
		});
  
 
        form.setFields(header, passwordItem, passwordItem2, acceptItem, id_item, 
        		date_item , type_item , validateItem);
               
              
        return form;  
    } 
	
	
	private Layout createPreferencesSetUp() {
		
		Layout output = new VLayout();
		
		output.addMember((new PreferencesPage()).buildMainPanel());
		
		Button submit = new Button("Submit");
		submit.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			@Override
			public void onClick(
					com.smartgwt.client.widgets.events.ClickEvent event) {
				CalendarPage caldendar_page = new CalendarPage();
				Canvas content = caldendar_page.build();
				PolishCalendarDev.replaceOutmostContent(content);
			}
		});
		
		output.addMember(submit);
		
		return output;
	}
	
	
}
