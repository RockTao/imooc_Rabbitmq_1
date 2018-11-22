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
public class RabbitReciver {

	
//	spring.rabbitmq.listener.order.queue.name=order-queue
//			spring.rabbitmq.listener.order.queue.durable=true
//			spring.rabbitmq.listener.order.exchange.name=order-exchange
//			spring.rabbitmq.listener.order.exchange.durable=true
//			spring.rabbitmq.listener.order.exchange.type=topic
//			spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions=true
//			spring.rabbitmq.listener.order.key=order.*
	
	@RabbitListener
	(bindings=@QueueBinding(
			value = @Queue(value = "${spring.rabbitmq.listener.order.queue.name}" , 
			durable="${spring.rabbitmq.listener.order.queue.durable}"),
			exchange = @Exchange(name="${spring.rabbitmq.listener.order.exchange.name}" ,
			durable="${spring.rabbitmq.listener.order.exchange.durable}",
			type="${spring.rabbitmq.listener.order.exchange.type}"
			,ignoreDeclarationExceptions="${spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions}"
			),
			key = "${spring.rabbitmq.listener.order.key}"
			))
	@RabbitHandler
	public void onOrderMessage(@Payload Order order,
			@Headers Map<String,Object> headers,
			Channel channel) throws Exception{
		//消费者操作
		System.out.println("----------------------------------");
		System.out.println("消费端Order:"+ order.getId());

		Long deliveryTag=	(Long) headers.get(AmqpHeaders.DELIVERY_TAG);
		//手动签收数据
		channel.basicAck(deliveryTag, false);


	}


}
