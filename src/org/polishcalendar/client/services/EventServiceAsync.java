package org.polishcalendar.client.services;


import org.polishcalendar.shared.EventDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EventServiceAsync {

	void addEvent(EventDTO e, AsyncCallback<EventDTO> callback);

	void deleteEvent(EventDTO e, AsyncCallback<EventDTO> callback);

}
