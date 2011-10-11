package org.polishcalendar.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourcePasswordField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;

public class OrganisationRegistration {

	Canvas buildOrganisationRegistration() {
		return buildForm();

	}

	Canvas buildForm() {

		DataSource dataSource = new DataSource();

		DataSourceTextField organisationName = new DataSourceTextField("name",
				"Organisation Name", 50, true);
		DataSourceTextField emailField = new DataSourceTextField("email",
				"Email", 100, true);
		DataSourceTextField loginField = new DataSourceTextField("login",
				"Login", 100, true);

		RegExpValidator emailValidator = new RegExpValidator();
		emailValidator.setErrorMessage("Invalid email address");
		emailValidator
				.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");

		emailField.setValidators(emailValidator);

		DataSourcePasswordField passwordField = new DataSourcePasswordField(
				"password", "Password", 20, true);

		dataSource.setFields(organisationName, emailField, loginField,
				passwordField);

		final DynamicForm form = new DynamicForm();
		form.setDataSource(dataSource);
		form.setUseAllDataSourceFields(true);

		HeaderItem header = new HeaderItem();
		header.setDefaultValue("Organisation Registration Form");

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

		CheckboxItem acceptItem = new CheckboxItem();
		acceptItem.setName("acceptTerms");
		acceptItem.setTitle("I accept the terms of use.");
		acceptItem.setRequired(true);
		acceptItem.setWidth(150);

		ButtonItem validateItem = new ButtonItem();
		validateItem.setTitle("Register");
		validateItem.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (form.validate(false))
					PolishCalendarDev.replaceOutmostContent((new LoginPage())
							.buildLoginPage());
			}
		});

		ButtonItem backTologinPage = new ButtonItem("Back");
		backTologinPage.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				PolishCalendarDev.replaceOutmostContent((new LoginPage())
						.buildLoginPage());
			}
		});

		form.setFields(header, passwordItem, passwordItem2, acceptItem,
				validateItem, backTologinPage);

		return form;

	}

}
