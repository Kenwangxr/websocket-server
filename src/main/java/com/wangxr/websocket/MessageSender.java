package com.wangxr.websocket;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.wangxr.websocket.bean.Message;
import com.wangxr.websocket.util.WsSendMessageUtil;

/**
 *
 * @author wangxueren
 * @Date 2016年1月6日 下午3:22:05
 * @email wangxr_it@sina.com
 *
 */

public class MessageSender {
	private Logger logger = Logger.getLogger(MessageSender.class);
	private Message msg;
	public MessageSender(Message msg){
		this.msg = msg;
	}
	public boolean send(){
		//WsSendMessageUtil.sendMessage(msg.getReceiver(), msg.getText());
		logger.debug(msg.toString());
		return true;
	}

}
