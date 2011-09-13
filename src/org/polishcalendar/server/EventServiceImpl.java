package org.polishcalendar.server;

import org.polishcalendar.client.services.EventService;
import org.polishcalendar.shared.Event;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EventServiceImpl extends RemoteServiceServlet implements
		EventService {

	private static final long serialVersionUID = 2084965182039303410L;

	@Override
	public Event addEvent(Event e) {
		System.out.println("Add event called on the server side!");
		System.out.println(e.toString());
		return e;
	}

	@Override
	public Event deleteEvent(Event e) {
		System.out.println("Delete event called on server side!");
		System.out.println(e.toString());
		return e;
	}

}
