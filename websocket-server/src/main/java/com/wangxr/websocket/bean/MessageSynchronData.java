package com.wangxr.websocket.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * @author liujt
 * 多进程消息同步对象
 * 
 */
public class MessageSynchronData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1224185599717200350L;

	private String channel;//来源
	
	private List<String> userIds;//用户id集合
	
	private Map<String, Object> content;//消息内容
	private String groupId;//群id

	public List<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

	
	public Map<String, Object> getContent() {
		return content;
	}

	public void setContent(Map<String, Object> content) {
		this.content = content;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	
}
