package org.polishcalendar.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.polishcalendar.client.services.UserService;
import org.polishcalendar.shared.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl extends RemoteServiceServlet implements 
							 UserService {

	private static final long serialVersionUID = 4854895607913385725L;
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public UserDTO addUser(UserDTO user) {
		logger.debug(user.toString());
		return user;
	}

	@Override
	public UserDTO removeUser(UserDTO user) {
		logger.debug(user.toString());
		return user;
	}

}
