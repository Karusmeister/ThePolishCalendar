package org.polishcalendar.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerInitServlet extends HttpServlet {
	
	private static final long serialVersionUID = -383583706077845672L;
	
	static final Logger logger = Logger.getLogger(LoggerInitServlet.class);

	public void init() throws ServletException {
		System.out.println("Log4JInitServlet init() starting.");
		String log4jfile = getInitParameter("log4j-properties");
		System.out.println("log4jfile: " + log4jfile);
		if (log4jfile != null) {
			String propertiesFilename = getServletContext().getRealPath(
					log4jfile);
			PropertyConfigurator.configure(propertiesFilename);
			logger.info("logger configured.");
		} else {
			System.out.println("Error setting up logger.");
		}
		System.out.println("Log4JInitServlet init() done.");
	}

}
