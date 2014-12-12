package kr.co.sunnyvale.jms;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.springframework.stereotype.Component;

@Component("messageListenerExample")
public class MessageListenerExample implements MessageListener {
	int count = 0;
	long startTime = 0;
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			ObjectMessage msg = (ObjectMessage) message;
			if(startTime == 0){
				startTime = System.currentTimeMillis();
			}
			if(count % 999 == 0){
				System.out.println(System.currentTimeMillis() - startTime);
			}
			count++;
		}
	}
}