package org.polishcalendar.client.services;

import java.util.List;

import org.polishcalendar.shared.OrganizationDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface OrganizationServiceAsync {

	void addOrganization(OrganizationDTO organization,
			AsyncCallback<OrganizationDTO> callback);

	void removeOrganization(OrganizationDTO organization,
			AsyncCallback<Void> callback);

	void updateOrganization(OrganizationDTO organization,
			AsyncCallback<OrganizationDTO> callback);

	void fetchOrganization(AsyncCallback<List<OrganizationDTO>> callback);

}
