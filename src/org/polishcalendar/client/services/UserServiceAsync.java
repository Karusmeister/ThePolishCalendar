package org.polishcalendar.client.services;

import org.polishcalendar.shared.UserDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

	void addUser(UserDTO user, AsyncCallback<UserDTO> callback);

	void removeUser(UserDTO user, AsyncCallback<UserDTO> callback);

	void updateUser(UserDTO user, AsyncCallback<UserDTO> callback);

	void fetchUser(UserDTO user, AsyncCallback<UserDTO> callback);

}
