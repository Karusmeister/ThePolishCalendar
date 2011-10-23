package org.polishcalendar.ds;

import java.util.List;

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
	
	private static UserDataSource userDS = null;
	
	public static UserDataSource getUserDS() {
		if (userDS == null) {
			userDS = new UserDataSource("userDS");
		}
		return userDS;
	}
	
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
	protected void executeRemove(final String requestId, final DSRequest request,
			final DSResponse response) {
		
		// retriving record
		JavaScriptObject js_data = request.getData();
		ListGridRecord event_record = new ListGridRecord(js_data);
		UserDTO user = new UserDTO();
		copyValues(event_record, user);
		
		// creating service
		UserServiceAsync service = GWT.create (UserService.class);
		service.removeUser(user, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
                response.setStatus (RPCResponse.STATUS_FAILURE);
                processResponse (requestId, response);
			}

			@Override
			public void onSuccess(Void result) {
                ListGridRecord[] list = new ListGridRecord[1];
                response.setData (list);
                processResponse (requestId, response);
			}	
		});
	}

	@Override
	protected void executeUpdate(final String requestId, final DSRequest request,
			final DSResponse response) {
		
		// retriving record
        ListGridRecord user_record = DSUtils.getEditedRecord (request);
		UserDTO user = new UserDTO();
		copyValues(user_record, user);
		
		// creating service
		UserServiceAsync service = GWT.create (UserService.class);
		service.updateUser(user, new AsyncCallback<UserDTO>() {

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
	protected void executeFetch(final String requestId, final DSRequest request,
			final DSResponse response) {
		
		// creating service
		UserServiceAsync service = GWT.create (UserService.class);
		service.fetchUser(new AsyncCallback<List<UserDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
                response.setStatus (RPCResponse.STATUS_FAILURE);
                processResponse (requestId, response);
			}

			@Override
			public void onSuccess(List<UserDTO> result) {
                ListGridRecord[] list = new ListGridRecord[result.size ()];
                for (int i = 0; i < list.length; i++) {
                    ListGridRecord record = new ListGridRecord ();
                    copyValues (result.get (i), record);
                    list[i] = record;
                }
                response.setData (list);
                processResponse (requestId, response);
			}	
		});
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
