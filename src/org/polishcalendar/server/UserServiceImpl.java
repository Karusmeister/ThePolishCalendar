package org.polishcalendar.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.hibernate.Query;
import org.hibernate.Session;
import org.polishcalendar.client.services.UserService;
import org.polishcalendar.server.persistence.User;
import org.polishcalendar.server.util.HibernateUtil;
import org.polishcalendar.server.util.MappingUtils;
import org.polishcalendar.shared.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl extends RemoteServiceServlet implements 
							 UserService {

	private static final long serialVersionUID = 4854895607913385725L;
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public UserDTO addUser(UserDTO user_dto) {
		logger.debug("Executing user_dto add");
		logger.debug(user_dto.toString());
		
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        
		// Creating user object
		User user_record = new User();
		MappingUtils.copyValues(user_record, user_dto);
		Calendar today = Calendar.getInstance(); 
		user_record.setJoinedDate(today.getTime());
        
        session.save(user_record);
        session.getTransaction().commit();
        
        user_dto.setId(user_record.getId());
		return user_dto;
	}

	@Override
	public void removeUser(UserDTO user_dto) {
		logger.debug("Executing user remove");
		logger.debug(user_dto.toString());
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User user_record = (User)session.load(User.class, user_dto.getId());
		if (user_record != null) {
			session.delete(user_record);
		}
        session.getTransaction().commit();
	}

	@Override
	public UserDTO updateUser(UserDTO user_dto) {
		logger.debug("Executing user remove");
		logger.debug(user_dto.toString());
		
		// Fetching User object
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User user_record = (User)session.load(User.class, user_dto.getId());
		
		if (user_record != null) {
			// Updating detached object
			MappingUtils.copyValues(user_record , user_dto);
			session.update(user_record);
		}
		
		session.getTransaction().commit();
		return user_dto;
	}

	@Override
	public List<UserDTO> fetchUser() {
		logger.debug("Executing user fetch");
		List<UserDTO> output = new ArrayList<UserDTO>();
		
		// Fetching Event object
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query q = session.createQuery("from User");
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>)q.list();
		
		for (User user_record : users) {
			UserDTO user_dto = new UserDTO();
			MappingUtils.copyValues(user_dto, user_record);
			output.add(user_dto);
		}
		
		session.getTransaction().commit();
		
		return output;
	}

}
