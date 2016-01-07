 		if(!window.chat){
 			Chat = {};
 			window.chat = Chat;
 			Chat.socket = null;
 			Chat.connect = (function(host) {
 				if ('WebSocket' in window) {
 					Chat.socket = new WebSocket(host);
 				} else if ('MozWebSocket' in window) {
 					Chat.socket = new MozWebSocket(host);
 				} else {
 					console.log('Error: WebSocket is not supported by this browser.');
 					return;
 				}
 				//建立连接触发事件
 				Chat.socket.onopen = function () {
 					console.log('Info: WebSocket connection opened.');
 				};
 				//接收消息触发事件
 				Chat.socket.onmessage = function (messageEvent) {
 					console.log("接收到："+eval("("+messageEvent.data+")").content);
 				};
 				//关闭连接触发事件
 	            Chat.socket.onclose = function () {
 	                console.log('Info: WebSocket closed.');
 	                Chat.socket = null;
 	            };
 				
 			});
 			//初始化聊天对象方法，注意URL中的项目名称和Servlet名称
 			Chat.initialize = function(userId,url) {
 				if(Chat.socket)return;//已经连接则返回
 				 if (window.location.protocol == 'http:') {
 					Chat.connect('ws://' +url+'/api/zm/push/web-socket?userId='+userId);
 				 } else  {
 					 Chat.connect('wss://' +url + '/ChatWebSocketServlet?userId='+userId);
 				 }
 			};
 			//发送聊天信息方法
 			Chat.sendMessage = (function(message) {
 				Chat.socket.send(message);
 			});
 		}
