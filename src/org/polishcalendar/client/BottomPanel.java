package org.polishcalendar.client;

import com.smartgwt.client.util.Page;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;

public class BottomPanel {

	public Layout build() {
		VLayout output = new VLayout();
		HTMLFlow bottom = new HTMLFlow();
		bottom.setWidth(Page.getScreenWidth());
		bottom.setHeight("15%");
		String content = "<hr> "
				+ "<b> To bedzie nasza stopka w ktorej nie wiem co umiescic (jeszcze) </b>.<hr>";
		bottom.setContents(content);

		output.addMember(bottom);
		return output;
	}

}
