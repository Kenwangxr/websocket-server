package com.wangxr.websocket.queue;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.commons.lang.math.RandomUtils;

import com.wangxr.websocket.MessageSenderService;
import com.wangxr.websocket.bean.Message;

/**
 *
 * @author wangxueren
 * @Date 2016年1月6日 下午2:58:38
 * @email wangxr_it@sina.com
 *
 */

public class MessageSenderQueueTest {
	private static final LinkedBlockingDeque<Message> queue = new LinkedBlockingDeque<Message>();
	public boolean offer(Message msg){
		return queue.offer(msg);
	}
	private static final Random r = new Random(1);
	public static void main(String[] args) {
		MessageSenderQueueTest sender  = new MessageSenderQueueTest();
		for(int i=0;i<10000;i++){
			Message msg = new Message();
			msg.setId(RandomUtils.nextLong(r)+"");
			msg.setReceiver("receiver"+i);
			msg.setSender("sender"+i);
			msg.setText("text"+i);
			sender.offer(msg);
		}
		for(int i=0;i<100;i++){
			new Thread(new MessageSenderService(queue)).start();
		}
		
	}
	
	

}
