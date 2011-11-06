package org.polishcalendar.client.services;

import java.util.List;

import org.polishcalendar.shared.UserDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

	void addUser(UserDTO user, AsyncCallback<UserDTO> callback);

	void fetchUsers(AsyncCallback<List<UserDTO>> callback);

	void removeUser(UserDTO user, AsyncCallback<Void> callback);

	void updateUser(UserDTO user, AsyncCallback<UserDTO> callback);

	void checkUserPassword(String email, String password,
			AsyncCallback<Boolean> callback);

}
