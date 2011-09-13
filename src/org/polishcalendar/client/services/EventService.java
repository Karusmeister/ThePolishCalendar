package org.polishcalendar.client.services;

import org.polishcalendar.shared.Event;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath ("event")
public interface EventService extends RemoteService {

	Event addEvent(Event e);
	Event deleteEvent(Event e);
}
