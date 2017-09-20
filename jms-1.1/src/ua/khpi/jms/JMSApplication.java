package ua.khpi.jms;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSApplication implements AutoCloseable {

	private ActiveMQConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private Destination destination;

	private String url = "tcp://localhost:61616/";
	private String queue = "test.in";

	public void init() throws JMSException {
		connectionFactory = new ActiveMQConnectionFactory(url);

		connection = connectionFactory.createConnection();
		connection.start();

		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destination = session.createQueue(queue);
	}

	public void send(String text) throws JMSException {
		MessageProducer messageProducer = session.createProducer(destination);

		TextMessage textMessage = session.createTextMessage(text);
		messageProducer.send(textMessage);
	}

	public String receive() throws JMSException {
		MessageConsumer messageConsumer = session.createConsumer(destination);

		TextMessage textMessage = (TextMessage) messageConsumer.receive();
		return textMessage.getText();
	}

	@Override
	public void close() throws JMSException {
		session.close();
		connection.close();
	}

	public static void main(String[] args) throws Exception {
		try (JMSApplication app = new JMSApplication()) {
			app.init();
			app.send("Test message");
			
			System.out.println(app.receive());
		}
	}
}
