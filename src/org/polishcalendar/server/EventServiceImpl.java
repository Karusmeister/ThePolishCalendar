package org.polishcalendar.server;

import net.sf.gilead.core.PersistentBeanManager;
import net.sf.gilead.core.store.stateless.StatelessProxyStore;
import net.sf.gilead.gwt.GwtConfigurationHelper;
import net.sf.gilead.gwt.PersistentRemoteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.Session;
import org.polishcalendar.client.services.EventService;
import org.polishcalendar.shared.Event;
import org.polishcalendar.util.HibernateUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EventServiceImpl extends PersistentRemoteService implements
		EventService {

	private static final long serialVersionUID = 2084965182039303410L;
	private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
	
	public EventServiceImpl() {
		
		// Initializing gilead for gwt - hubernate integration
		net.sf.gilead.core.hibernate.HibernateUtil gileadHibernateUtil = 
			new net.sf.gilead.core.hibernate.HibernateUtil();
		gileadHibernateUtil.setSessionFactory(HibernateUtil.getSessionFactory());

		PersistentBeanManager persistentBeanManager 
			= GwtConfigurationHelper.initGwtStatelessBeanManager(gileadHibernateUtil);

		setBeanManager(persistentBeanManager);
	}

	@Override
	public Event addEvent(Event e) {
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
	public Event deleteEvent(Event e) {
		System.out.println("Delete event called on server side!");
		System.out.println(e.toString());
		return e;
	}

}
