package org.polishcalendar.ds;

import org.polishcalendar.client.services.OrganizationService;
import org.polishcalendar.client.services.OrganizationServiceAsync;
import org.polishcalendar.shared.OrganizationDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class OrganizationDataSource extends GwtRpcDataSource {
	
	private static OrganizationDataSource ds_singleton = null;
	
	public static OrganizationDataSource getOrganizationDS () {
		if (ds_singleton == null) {
			ds_singleton = new OrganizationDataSource("orgDS");
		}
		return ds_singleton;
	}
	
	
	private OrganizationDataSource(String id) {
		setID(id);
		
		DataSourceIntegerField index_f = new DataSourceIntegerField("id");
		index_f.setPrimaryKey(true);
		index_f.setHidden(true);
		
		DataSourceTextField name_f = new DataSourceTextField("name");
		name_f.setRequired(true);
		
		DataSourceIntegerField followersNumber_f 
			= new DataSourceIntegerField("followersNumber");
		followersNumber_f.setRequired(true);
		
		DataSourceTextField description_f 
			= new DataSourceTextField("description");
		description_f.setRequired(true);
		
		setFields(index_f , name_f , followersNumber_f , description_f);
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
		OrganizationDTO organization = new OrganizationDTO();
		copyValues(event_record, organization);
		
		// creating service
		OrganizationServiceAsync service = GWT.create (OrganizationService.class);
		service.addOrganization(organization, new AsyncCallback<OrganizationDTO>() {

			@Override
			public void onFailure(Throwable caught) {
                response.setStatus (RPCResponse.STATUS_FAILURE);
                processResponse (requestId, response);
			}

			@Override
			public void onSuccess(OrganizationDTO result) {
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
	
	/* Copy values between ListGridRecord for view and EventDTO. */
	private static void copyValues(ListGridRecord from, OrganizationDTO to) {
		to.setName(from.getAttributeAsString("name"));
		to.setId(from.getAttributeAsInt("id"));
		to.setFollowersNumber(from.getAttributeAsInt("followersNumber"));
		to.setDescription(from.getAttributeAsString("description"));
	}
	
	private static void copyValues(OrganizationDTO from, ListGridRecord to) {
		to.setAttribute("id", from.getId());
		to.setAttribute("name", from.getName());
		to.setAttribute("followersNumber", from.getFollowersNumber());
		to.setAttribute("description", from.getDescription());
	}

}
