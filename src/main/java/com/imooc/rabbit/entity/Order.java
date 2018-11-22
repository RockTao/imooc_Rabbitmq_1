package com.imooc.rabbit.entity;

import java.io.Serializable;
//必须实现序列化接口
public class Order implements Serializable {

	private String id;
	private String name;
	private String messageId;//存储消息发送的唯一标识
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id ==null? null: id.trim();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public Order(String id, String name, String messageId) {
		super();
		this.id = id;
		this.name = name;
		this.messageId = messageId;
	}
	public Order() {
		super();
	}
	
	
}

