package kr.co.sunnyvale.jms;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class MessageSender2 {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext_sender.xml");
		JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");		
		MessageCreator messageCreateor = new MessageCreator() {
			public ObjectMessage createMessage(Session session)
					throws JMSException {
				ObjectMessage message = session.createObjectMessage();
				message.setObject("My first Message");
				return message;
			}
		};		
		
		for(int i = 0; i < 100; i++){
			jmsTemplate.send(messageCreateor);
		}
		System.out.println("MESSAGE SENT TO myMessageQueue");
	}
}

