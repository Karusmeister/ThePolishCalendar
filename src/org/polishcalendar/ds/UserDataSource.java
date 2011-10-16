package org.polishcalendar.ds;

import org.polishcalendar.client.services.UserService;
import org.polishcalendar.client.services.UserServiceAsync;
import org.polishcalendar.shared.UserDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class UserDataSource extends GwtRpcDataSource {
	
	private UserDataSource userDS = null;
	
	public UserDataSource getUserDS() {
		if (userDS == null) {
			userDS = new UserDataSource("userDS");
		}
		return userDS;
	}
	/*
	private String id;
	private String name;
	private Date joinedDate;
	private String type;
	*/
	private UserDataSource (String id) {
		setID(id);
		
		DataSourceIntegerField id_f = new DataSourceIntegerField("id");
		id_f.setPrimaryKey(true);
		id_f.setHidden(true);
		
		DataSourceTextField name_f = new DataSourceTextField("name");
		name_f.setRequired(true);
		
		DataSourceDateField joinedDate_f = new DataSourceDateField("joinedDate");
		joinedDate_f.setRequired(true);
		
		DataSourceTextField type_f = new DataSourceTextField("type");
		type_f.setRequired(true);
		
		setFields(id_f , name_f , joinedDate_f , type_f);
	}

	@Override
	protected void executeRemove(String requestId, DSRequest request,
			DSResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void executeUpdate(String requestId, DSRequest request,
			DSResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void executeAdd(final String requestId, final DSRequest request,
			final DSResponse response) {
		
		// retriving record
		JavaScriptObject js_data = request.getData();
		ListGridRecord event_record = new ListGridRecord(js_data);
		UserDTO user = new UserDTO();
		copyValues(event_record, user);
		
		// creating service
		UserServiceAsync service = GWT.create (UserService.class);
		service.addUser(user, new AsyncCallback<UserDTO>() {

			@Override
			public void onFailure(Throwable caught) {
                response.setStatus (RPCResponse.STATUS_FAILURE);
                processResponse (requestId, response);
			}

			@Override
			public void onSuccess(UserDTO result) {
                ListGridRecord[] list = new ListGridRecord[1];
                ListGridRecord newRec = new ListGridRecord ();
                copyValues (result, newRec);
                list[0] = newRec;
                response.setData (list);
                processResponse (requestId, response);
			}
		});
		
	}

	@Override
	protected void executeFetch(String requestId, DSRequest request,
			DSResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	
	private void copyValues(UserDTO from, ListGridRecord to) {
		to.setAttribute("id", from.getId());
		to.setAttribute("name", from.getName());
		to.setAttribute("joinedDate", from.getJoinedDate());
		to.setAttribute("type", from.getType());
	}
	
	private void copyValues(ListGridRecord from, UserDTO to) {
		to.setName(from.getAttributeAsString("name"));
		to.setId(from.getAttributeAsInt("id"));
		to.setJoinedDate(from.getAttributeAsDate("joinedDate"));
		to.setType(from.getAttributeAsString("type"));
	}

}
