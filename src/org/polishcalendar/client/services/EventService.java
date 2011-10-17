package org.polishcalendar.client.services;


import org.polishcalendar.shared.EventDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath ("event")
public interface EventService extends RemoteService {

	EventDTO addEvent(EventDTO e);
	EventDTO deleteEvent(EventDTO e);
	EventDTO updateEvent(EventDTO e);
	EventDTO fetchEvent(EventDTO e);
}
