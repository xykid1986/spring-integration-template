package com.xiao.test;

import java.io.IOException;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.support.MessageBuilder;

public class TestTraffic {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring/context.xml");
		MessageChannel toRabbit = ctx.getBean("toRabbit",MessageChannel.class);
		PollableChannel fromRabbit = ctx.getBean("fromRabbit",PollableChannel.class);
		for(int i=0;i<10;i++)
			toRabbit.send(MessageBuilder.withPayload(new Book("Two cities"+i,"Xiao Yi")).build());
		while(true){
			@SuppressWarnings("unchecked")
			Message<Book> get = (Message<Book>) fromRabbit.receive();
			//Message get = fromRabbit.receive();
			//Object obj = toObject((byte[])get.getPayload());
			//System.out.print((Book)obj);
			System.out.println(get.getPayload());
		}
		
	}
}
