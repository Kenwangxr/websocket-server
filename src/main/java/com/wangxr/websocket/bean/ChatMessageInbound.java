package com.wangxr.websocket.bean;



import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

/**
 * 相当与socket对象
 * @author wangxueren
 *
 */
public class ChatMessageInbound extends MessageInbound {
	private String userId;
	private static final String GUEST_PREFIX = "Guest";
    private final String nickname;
    private final Map<String,ChatMessageInbound> connections ;//全站的所有websocket连接对象,这里引用它就是为了添加和删除连接
   public ChatMessageInbound(){
	   super();
	   onClose(2);
	   nickname = "";
	   connections = null;
   }
    public ChatMessageInbound(final Map<String,ChatMessageInbound> connections,String userId) {
    	this.userId = userId;
    	this.connections = connections;
        this.nickname = GUEST_PREFIX + userId;
    }
    
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	//创建新的连接时触发
    @Override
    protected void onOpen(WsOutbound outbound) {
    	if(connections!=null){
    		connections.put(this.userId,this);
    		String message = String.format("* %s %s",
    				nickname, "has joined.");
    		//websocket连接新建时向redis中注册该用户的websocket连接信息
    		
    		connections.put(this.userId, this);
    		
    		//sendMessage(message);
    		
    	}
    }
    //关闭连接时触发
    @Override
    protected void onClose(int status) {
    	if(connections!=null){
    		connections.remove(this.userId);
    		String message = String.format("* %s %s",
    				nickname, "has disconnected.");
    		// sendMessage(message);
    		//websocket连接断开时删除该用户缓存的websocket连接信息
    		connections.remove(this.userId);
    	}
    }
    //接收此链接发送的二进制消息时触发
    @Override
    protected void onBinaryMessage(ByteBuffer message) throws IOException {
        throw new UnsupportedOperationException(
                "Binary message not supported.");
        
    }
    //接收此链接发送的文本消息时触发
    @Override
    protected void onTextMessage(CharBuffer message) throws IOException {
        // Never trust the client
        String filteredMessage = String.format("%s: %s",
                nickname, message.toString());
        sendAll(filteredMessage);
    }
    private void sendAll(String message){
    	Iterator<Entry<String, ChatMessageInbound>> i = this.connections.entrySet().iterator();
    	while(i.hasNext()){
    		Entry<String, ChatMessageInbound> entry = i.next();
    		entry.getValue().sendMessage(message);
    	}
    }
    //该方法向指定连接发送文本信息
    public void sendMessage(String message) {
    		 try {
               CharBuffer buffer = CharBuffer.wrap(message);
               this.getWsOutbound().writeTextMessage(buffer);
           } catch (IOException ignore) {
           }
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((connections == null) ? 0 : connections.hashCode());
		result = prime * result
				+ ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
}
