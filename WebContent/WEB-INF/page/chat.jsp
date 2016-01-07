<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
	<link rel="stylesheet" type="text/css" href="">
     <style type="text/css">
          .div_{
               border-color: blue;
              width: 300px;
              height: 300px;
              border: 1px solid #F00;
             
          }
     </style>
	<script type="text/javascript" src="\js\websocket.js"></script>
	<body>
          <div id="login_container">
              <input type="text"></input><input onclick="sendMsg()" id="submit" type="button">send</input>
              <div id="text-div" class="div_"></div>
          </div>
	</body>
	<script>
	 
          function sendMsg(){
        	  var input = document.getElementsByTagName('input')[0];
               var text = input.value;
               input.value="";
               if(text){
            	  Chat.sendMessage(text);
               }else{
            	   alert("请输入内容！");
               }
          }
		Chat.initialize("djdjdjdjd","localhost:8005");
           Chat.socket.onmessage =function(messageEvent){
                  var text_div = document.getElementById('text-div');
                  var textNode = document.createTextNode(messageEvent.data);
                  var div = document.createElement("div");
                  div.appendChild(textNode);
                  text_div.appendChild(div);
           }
		  
	</script>
</html>



   