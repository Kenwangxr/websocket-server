package com.wangxr.websocket;

import java.util.Queue;

import javax.management.Query;

import com.wangxr.websocket.bean.Message;

/**
 *
 * @author wangxueren
 * @Date 2016年1月6日 下午3:16:04
 * @email wangxr_it@sina.com
 *
 */

public class MessageSenderService implements Runnable{
	private Queue<Message> queue;
	public MessageSenderService(Queue<Message> queue){
		this.queue = queue;
	}
	@Override
	public void run() {
		while(true){
			if(queue.isEmpty()){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				Message msg = queue.poll();
				new MessageSender(msg).send();
			}
		}
		
	}

}
