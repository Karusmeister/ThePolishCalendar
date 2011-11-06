package org.polishcalendar.ds;

import java.util.List;

import org.polishcalendar.client.services.EventService;
import org.polishcalendar.client.services.EventServiceAsync;
import org.polishcalendar.server.persistence.Event;
import org.polishcalendar.shared.EventDTO;
import org.polishcalendar.translators.Builder;
import org.polishcalendar.translators.ClassDescriptor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class EventDataSource extends GwtRpcDataSource {

	private Builder<Event, GwtRpcDataSource> _builder;
	
	public EventDataSource(String id, Builder<Event,GwtRpcDataSource> builder){
		setID(id);
		_builder = builder;
		_builder.build(this);
	}

	@Override
	protected void executeAdd(final String requestId, final DSRequest request,
			final DSResponse response) {
		
		// retriving record
		JavaScriptObject js_data = request.getData();
		ListGridRecord event_record = new ListGridRecord(js_data);
		EventDTO event = new EventDTO();
		copyValues(event_record, event);
		
		// creating service
		EventServiceAsync service = GWT.create (EventService.class);
		service.addEvent(event, new AsyncCallback<EventDTO>() {

			@Override
			public void onFailure(Throwable caught) {
                response.setStatus (RPCResponse.STATUS_FAILURE);
                processResponse (requestId, response);
			}

			@Override
			public void onSuccess(EventDTO result) {
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
		EventServiceAsync service = GWT.create (EventService.class);
		service.fetchEvent(new AsyncCallback<List<EventDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
                response.setStatus (RPCResponse.STATUS_FAILURE);
                processResponse (requestId, response);
			}

			@Override
			public void onSuccess(List<EventDTO> result) {
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

	@Override
	protected void executeRemove(final String requestId, final DSRequest request,
			final DSResponse response) {
		// retriving record
		JavaScriptObject js_data = request.getData();
		ListGridRecord event_record = new ListGridRecord(js_data);
		EventDTO event = new EventDTO();
		copyValues(event_record, event);
		
		// creating service
		EventServiceAsync service = GWT.create (EventService.class);
		service.deleteEvent(event, new AsyncCallback<Void>() {

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
        ListGridRecord event_record = DSUtils.getEditedRecord (request);
		EventDTO event = new EventDTO();
		copyValues(event_record, event);
		
		// creating service
		EventServiceAsync service = GWT.create (EventService.class);
		service.updateEvent(event, new AsyncCallback<EventDTO>() {

			@Override
			public void onFailure(Throwable caught) {
                response.setStatus (RPCResponse.STATUS_FAILURE);
                processResponse (requestId, response);
			}

			@Override
			public void onSuccess(EventDTO result) {
                ListGridRecord[] list = new ListGridRecord[1];
                ListGridRecord newRec = new ListGridRecord ();
                copyValues (result, newRec);
                list[0] = newRec;
                response.setData (list);
                processResponse (requestId, response);
			}		
		});
	}
	
	/* Copy values between ListGridRecord for view and EventDTO. */
	public void copyValues(Record from, EventDTO to) {
		to.setName(from.getAttributeAsString("Name"));
		to.setId(from.getAttributeAsInt("Id"));
		to.setLocation(from.getAttributeAsString("Location"));
		to.setAttendeesNumber(from.getAttributeAsInt("AttendeesNumber"));
		to.setStartDate(from.getAttributeAsDate("StartDate"));
		to.setOrganizationName(from.getAttributeAsString("OrganizationName"));
	}
	
	public void copyValues(EventDTO from, Record to) {
		to.setAttribute("Id", from.getId());
		to.setAttribute("Name", from.getName());
		to.setAttribute("Location", from.getLocation());
		to.setAttribute("AttendeesNumber", from.getAttendeesNumber());
		to.setAttribute("StartDate", from.getStartDate());
		to.setAttribute("OrganizationName", from.getOrganizationName());
	}
}
