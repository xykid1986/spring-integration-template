package com.xiao.test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.support.MessageBuilder;

import com.xiao.domain.Book;

public class AMQPGatewayTest {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/amqp-gateway-context.xml");
		MessageChannel toRabbit = ctx.getBean("toRabbit",MessageChannel.class);
		PollableChannel outRe = ctx.getBean("outboundReply",PollableChannel.class);
		for(int i=0;i<10;i++){
			toRabbit.send(MessageBuilder.withPayload(new Book("Two cities"+i,"Xiao Yi")).build());
			Message<?> outReMessage = outRe.receive();
			if(outReMessage!=null)
				System.out.println("outBound:"+outReMessage.getPayload());
		}
	}

}
