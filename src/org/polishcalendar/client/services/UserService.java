package org.polishcalendar.client.services;

import java.util.List;

import org.polishcalendar.shared.UserDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath ("user")
public interface UserService extends RemoteService {

	UserDTO addUser(UserDTO user);
	void removeUser(UserDTO user);
	UserDTO updateUser(UserDTO user);
	List<UserDTO> fetchUser();
}
