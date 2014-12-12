package kr.co.sunnyvale.jms;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MessageProcessorServer {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
		
	}

}
