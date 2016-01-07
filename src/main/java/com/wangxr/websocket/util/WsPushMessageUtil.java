package com.wangxr.websocket.util;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wangxr.websocket.bean.ChatMessageInbound;
import com.wangxr.websocket.servlet.ChatWebSocketServlet;


/**
 * 
 * @author wangxueren
 *
 */
public class WsPushMessageUtil {
	public static   Logger logger = Logger.getLogger(WsPushMessageUtil.class);
	
    /**
     * 消息发送单例入口，后期这里需要改造成线程发送
     * @return 返回消息发送对象 PushMessageUtil
     */
	public static final WsPushMessageUtil getInstance() {		
		return PushMessageUtilHolder.INSTANCE;  
	}  
    
    /**
     * 获取消息发送对象 PushMessageUtil
     */
    private static class PushMessageUtilHolder {  
    	private static final WsPushMessageUtil INSTANCE = new WsPushMessageUtil();  
	}  
    
	
	
	public boolean pushMessage(String toUserId,String msgJsonString){
		boolean flag = false;
		try {
			flag = sendMessage(toUserId, msgJsonString);
			logger.debug("发送消息："+flag);

		} catch (IOException e) {
		//	e.printStackTrace();
			logger.debug("PUSH网络连接异常--"+e.getMessage());
		}
		return flag;
		
	}

	private boolean sendMessage(String userId,String message) throws IOException{
		Map<String,ChatMessageInbound> connections = ChatWebSocketServlet.getConnections();
		ChatMessageInbound connection = connections.get(userId);
		if(connection!=null){
			connection.sendMessage(message);
			return true;
		}else{
			return false;
		}
	}
	
	
	public boolean isOnline(String userId){
		return ChatWebSocketServlet.getConnections().containsKey(userId);
	}
	
}
