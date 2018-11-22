package com.imooc.rabbit.consumer;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.imooc.rabbit.entity.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Queue;
@Component
public class OrderReciver {

	@RabbitListener
	(bindings=@QueueBinding(
			value = @Queue(value = "order-queue" , durable="true"),
			exchange = @Exchange(name="order-exchange" ,durable="true",type="topic"),
			key = "order.*"
			))
	@RabbitHandler
	public void onOrderMessage(@Payload Order order,
			@Headers Map<String,Object> headers,
			Channel channel) throws Exception{
		//消费者操作
		System.out.println("_________________收到消息，开始消费===");
		System.out.println("订单号:"+ order.getId());

		Long deliveryTag=	(Long) headers.get(AmqpHeaders.DELIVERY_TAG);
		//手动签收数据
		channel.basicAck(deliveryTag, false);


	}


}
