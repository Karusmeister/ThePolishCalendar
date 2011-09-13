package org.polishcalendar.client.services;

import org.polishcalendar.shared.Event;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EventServiceAsync {

	void addEvent(Event e, AsyncCallback<Event> callback);

	void deleteEvent(Event e, AsyncCallback<Event> callback);

}
