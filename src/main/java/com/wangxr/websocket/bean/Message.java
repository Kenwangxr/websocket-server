package com.wangxr.websocket.bean;
/**
 *
 * @author wangxueren
 * @Date 2016年1月6日 下午3:09:20
 * @email wangxr_it@sina.com
 *
 */

public class Message {
	private String id;
	private String sender;
	private  String receiver;
	private String text;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", sender=" + sender + ", receiver="
				+ receiver + ", text=" + text + "]";
	}

}
