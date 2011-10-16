package org.polishcalendar.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.Session;
import org.polishcalendar.client.services.EventService;
import org.polishcalendar.server.util.HibernateUtil;
import org.polishcalendar.shared.EventDTO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EventServiceImpl extends RemoteServiceServlet implements
		EventService {

	private static final long serialVersionUID = 2084965182039303410L;
	private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
	
	public EventServiceImpl() {}

	@Override
	public EventDTO addEvent(EventDTO e) {
		logger.debug("Adding event called on server side! {}");
		logger.warn(e.toString());
		/*
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(e);
        session.getTransaction().commit();
        */
		return e;
	}

	@Override
	public EventDTO deleteEvent(EventDTO e) {
		System.out.println("Delete event called on server side!");
		System.out.println(e.toString());
		return e;
	}

}
