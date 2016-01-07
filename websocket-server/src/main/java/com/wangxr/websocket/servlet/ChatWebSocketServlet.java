package com.wangxr.websocket.servlet;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

import com.wangxr.websocket.bean.ChatMessageInbound;
import com.wangxr.websocket.util.WsSendMessageUtil;




/**
 * 通过注解配置servlet
 * @author wangxueren
 *
 */
@WebServlet("/api/zm/push/web-socket")
public class ChatWebSocketServlet extends WebSocketServlet {
	 private static final long serialVersionUID = 1L;
	    private static final String GUEST_PREFIX = "Guest";
	    private WsSendMessageUtil test = new WsSendMessageUtil();
	    //用于自动生成连接ID
	    private final AtomicInteger connectionIds = new AtomicInteger(0);
	    //用于保存所有连接Inbound的集合
	    private static final Map<String,ChatMessageInbound> connections =
	            new HashMap<String,ChatMessageInbound>();
	    public static Map<String,ChatMessageInbound> getConnections() {
			return connections;
		}
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    		throws ServletException, IOException {
	    	try{
	    		super.doGet(req, resp);
	    		
	    	}catch(Exception e){
	    	}
	    }
	    /**
	     * 连接登录认证
	     */
	    @SuppressWarnings("deprecation")
		@Override
	    protected boolean verifyOrigin(String origin) {
	    		return super.verifyOrigin(origin);
	    }
		//生成新的连接Inbound，WebSocketServlet必须实现该方法
		@Override
	    protected StreamInbound createWebSocketInbound(String subProtocol,
	            HttpServletRequest request) {
	    	int userId = connectionIds.getAndIncrement();
	    	return new ChatMessageInbound(connections, userId+"");//暂时以用户Id作为Websocket的连接ID
	    }

}
