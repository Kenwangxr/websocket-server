package com.wangxr.websocket.util;



import java.io.IOException;
import java.util.Map;

import com.wangxr.websocket.bean.ChatMessageInbound;
import com.wangxr.websocket.servlet.ChatWebSocketServlet;




public class WsSendMessageUtil {
	public static boolean sendMessage(String userId,String message) throws IOException{
		Map<String,ChatMessageInbound> connections = ChatWebSocketServlet.getConnections();
		ChatMessageInbound connection = connections.get(userId);
		if(connection!=null){
			connection.sendMessage(message);
			return true;
		}else{
			return false;
		}
	}
	{
		/*new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(5*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Map<String,ChatMessageInbound> connections = ChatWebSocketServlet.getConnections();
					Set<String> set  = connections.keySet();
			    	ChatMessageInbound connection;
			    	for(String userId:set){//遍历所有连接，向每个连接发送消息，消息体里有各自的appid即连接ID
			    		connection = connections.get(userId);
			    		int size = connections.size();
			    		String message = "Websocket Helloworld--连接数："+size+";连接ID="+userId;
			    		try {
							sendMessage(userId, message);
						} catch (IOException e) {
							e.printStackTrace();
						}
			    	}
				}
				
			}
		}).start();*/
	}

}
