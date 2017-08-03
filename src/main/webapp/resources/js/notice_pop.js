//var userCode=$.cookie('cookie_username'); // 读取 cookie 
var userCode;
var userName;
//如果用户禁用了则发送请求获取
if(!userCode){
	getSession();
}
//刷新保证session不过期
shuaxin();
var host = window.location.host;
var websocket = null;
//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
	if(userCode){
		websocket = new WebSocket("ws://"+host+"/"+global_param.context_name+"/websocket?id="+userCode);
	}	
}
else {
	alert('当前浏览器 Not support websocket');
}
//连接发生错误的回调方法
websocket.onerror = function () {
	setMessageInnerHTML("WebSocket连接发生错误");
};
//连接成功建立的回调方法
websocket.onopen = function () {
	//setMessageInnerHTML("WebSocket连接成功");
};
//接收到消息的回调方法
websocket.onmessage = function (event) {
	setMessageInnerHTML(event.data);
};
//连接关闭的回调方法
websocket.onclose = function () {
	//setMessageInnerHTML("WebSocket连接关闭");
	try{
		notification.close(); 	
	}catch(e){
		
	}
};
//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
	closeWebSocket();
};
//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
    //调用发送通知消息
    op(innerHTML,userName);
};
//关闭WebSocket连接
function closeWebSocket() {
	websocket.close();
};
//发送消息
function send() {
	var message = document.getElementById('text').value;
	websocket.send(message);
};

//弹窗消息
function pop_init(title,content) {  
    //取当前浏览器窗口大小  
    var windowWidth=$(document).width();  
    var windowHeight=$(document).height();  
    //弹窗的大小  
    var weight=320;  
    var height=240;  
    $("body").append(  
    "<div id='pop_div'style='display:none;position:absolute;border:1px solid #e0e0e0;z-index:99;width:"+weight+"px;height:"+height+"px;top:"+(windowHeight-height-10)+"px;left:"+(windowWidth-weight-10)+"px'>"+  
        "<div style='line-height:32px;background:#f6f0f3;border-bottom:1px solid #e0e0e0;font-size:14px;padding:0 0 0 10px;'>" +  
            "<div style='float:left;'><b>"+title+"</b></div><div style='float:right;cursor:pointer;'><b onclick='pop_close()'>×</b></div>" +  
            "<div style='clear:both'></div>"+  
        "</div>" +  
        "<div id='content'>" +  
             content+  
        "</div>"+  
    "</div>"  
    );  
}

function pop_close(){  
    $('#pop_div').fadeOut(400);  
}
function pop_clear(){  
    $('#pop_div').remove();  
}

var notification;
//右下角显示通知信息
if (window.Notification) {
    function popNotice(message,code) {
        if (Notification.permission == "granted") {
             notification = new Notification("Hi，"+code+"：", {
            	//dir[String]:auto, Not 显示信息的方向，通常可以取：auto, ltr, or rtl
            	//sound[String]： 设置音频的地址。例如： /audio/notification-sound.mp3
            	//data[Any]: 用来附带在 Not 里面的信息。我们一般可以在 notificationclick 事件中，对回调参数进行调
            	//vibrate: [200, 100, 200],//用来设置振动的范围。格式为:[振动,暂停,振动,暂停...]。具体取值单位为 ms 设备震动硬件需要的振动模式app
            	requireInteraction:true,//必须等待与用户互动
                body: message,//显示的主体信息
                tag: "sptx",//用来标识每个 Not。方便后续对 Not 进行相关管理。
                renotify: true,//当重复的 Not 触发时，标识是否禁用振动和声音,默认为 false
                icon: '../../images/zx.png'//显示的 Icon 图片路径。
            });
            notification.onclick = function() {
                notification.close();    
            };
        }    
    };
    //发送消息操作
    function op(message,code) {
    	//granted表示用户允许通知
        if (Notification.permission == "granted") {
            popNotice(message,code);
        } else if (Notification.permission != "denied") {//denied表示用户嫌弃你
            Notification.requestPermission(function (permission) {
              popNotice(message,code);
            });
        }
    };
} else {
    alert('浏览器不支持Notification');    
}

var time=0;
function shuaxin(){
	//执行
	time=setInterval(getSession,1200000);	
}
//获取session
function  getSession(){
	 var json = globalUtil.syncGetJson("/sysmanage/getcurrentuserinfo.do", null);//
	   if(json.userCode!=undefined&&json.userCode!=""){
	   	 userCode=json.userCode;
	   	 userName=json.realname;
	   } 
}

