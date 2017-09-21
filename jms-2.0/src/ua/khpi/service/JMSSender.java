package ua.khpi.service;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

import ua.khpi.constants.Constants;

@ApplicationScoped
public class JMSSender {
	@Inject
	private JMSContext context;

	@Resource(mappedName = Constants.QUEUE)
    private Queue queue;

	public void send(String text) {
		context.createProducer().send(queue, text);
	}
}
