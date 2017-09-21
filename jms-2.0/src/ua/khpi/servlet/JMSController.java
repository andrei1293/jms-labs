package ua.khpi.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.khpi.constants.Constants;
import ua.khpi.service.JMSReceiver;
import ua.khpi.service.JMSSender;

public class JMSController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private JMSSender sender;

	@Inject
	private JMSReceiver receiver;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String serviceName = request.getParameter(Constants.SERVICE);

		if (serviceName == null) {
			request.setAttribute(Constants.ERROR, "Service name can't be empty!");
		} else {
			if (serviceName.equals(Constants.SENDER)) {
				String text = request.getParameter(Constants.TEXT);

				if (text == null || text.isEmpty()) {
					request.setAttribute(Constants.ERROR, "Text can't be empty!");
				} else {
					sender.send(text);
				}
			} else if (serviceName.equals(Constants.RECEIVER)) {
				String text = receiver.receive();

				request.setAttribute(Constants.TEXT, text);
			} else {
				request.setAttribute(Constants.ERROR, "Invalid service name!");
			}
		}

		request.getRequestDispatcher(Constants.HOME).forward(request, response);
	}
}
