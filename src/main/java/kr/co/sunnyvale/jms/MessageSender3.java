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

public class MessageSender3 {
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
		
		Runnable c = new CallableImpl2(jmsTemplate,messageCreateor);
		ExecutorService executor = Executors.newFixedThreadPool(100);
		long alltime = 0;
		for(int i = 0; i < 10000; i++){
			executor.submit(c);
//			Long time = 0L;
//			try {
//				time = future.get();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			alltime += time;
		}
		System.out.println(alltime / 10000.0);
   	    executor.shutdown();
		System.out.println("MESSAGE SENT TO myMessageQueue");
	}
}

class CallableImpl2 implements Runnable {
	JmsTemplate jmsTemplate = null;
	MessageCreator messageCreateor = null;
	public CallableImpl2(JmsTemplate jmsTemplate, MessageCreator messageCreateor){
		this.jmsTemplate = jmsTemplate;
		this.messageCreateor = messageCreateor;
	}
	 
    public void run() {
    	jmsTemplate.send(messageCreateor);
    }
}