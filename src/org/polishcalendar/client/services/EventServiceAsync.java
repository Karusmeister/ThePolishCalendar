package org.polishcalendar.client.services;


import java.util.List;

import org.polishcalendar.shared.EventDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EventServiceAsync {

	void addEvent(EventDTO e, AsyncCallback<EventDTO> callback);

	void updateEvent(EventDTO e, AsyncCallback<EventDTO> callback);

	void deleteEvent(EventDTO e, AsyncCallback<Void> callback);

	void fetchEvent(AsyncCallback<List<EventDTO>> callback);

}
