package ua.khpi.service;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;

import ua.khpi.constants.Constants;

@ApplicationScoped
public class JMSReceiver {
	@Inject
	private JMSContext context;

	@Resource(mappedName = Constants.QUEUE)
	private Queue queue;

	public String receive() {
		JMSConsumer consumer = context.createConsumer(queue);
		return consumer.receiveBody(String.class);
	}
}
