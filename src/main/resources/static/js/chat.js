window.JIM = new JMessage({debug : true});
var appkey = "1da85b41a92e505c2651bb6b";
var timestamp = (new Date()).valueOf();
var secret = "635789d73fcf30a70bf7269f";
var random_str = "7db047a67a9d7293850ac69d14cc82bf";
var signature = hex_md5('appkey='+appkey+'&timestamp='+timestamp+'&random_str='+random_str+'&key='+secret);
console.log( "appkey"+appkey,
    "random_str"+  random_str,
    "signature"+  signature,
    "timestamp"+  timestamp);
function init() {
    JIM.init({
        "appkey":appkey,
        "random_str":  random_str,
        "signature":  signature,
        "timestamp":  timestamp,
        "flag": 0

    }).onSuccess(function(data) {
        console.log('success成功:' + JSON.stringify(data));
    }).onFail(function(data) {
        console.log('error失败:' + JSON.stringify(data))
    });
}
init();

function register(){
    var username = $('#id_username').val();
    var password = $('#id_password').val();
    var nickname = $('#id_nickname').val();
    var id_confirm_password = $('#id_confirm_password').val();
    if(password != id_confirm_password){
        alert("两次输入密码不一致");
        return;
    }

    console.log(username+":"+password+":"+nickname);
    var md5Password = hex_md5(password)
    JIM.register({
        'username' : username,
        'password': md5Password,
        'nickname' : nickname
    }).onSuccess(function(data) {
        //alert("注册成功");
        console.log('success:' + JSON.stringify(data));
        $("#register_div").hide();
    }).onFail(function(data) {
        //alert("注册失败");
        console.log('error:' + JSON.stringify(data))
    });
}

function login(username,password) {
    alert(username+":"+password);
    JIM.login({
        'username' : username,
        'password': password
    }).onSuccess(function(data) {
        console.log('success:' + JSON.stringify(data));
        getFriendList();
        JIM.onMsgReceive(function(data) {
            let messages = data.messages;
            for(message in messages){
                var content = messages[message].content;
                var from_id = content.from_id; //发送者
                var msg_body = content.msg_body; //数据
                var text = msg_body.text; //发送文本
                $(".lite-chatbox").append("<div class='cleft cmsg'>"+
                    "<img class='headIcon radius' ondragstart='return false;'  oncontextmenu='return false;' src='./img/A.jpg' />"+
                    "<span class='name'>"+from_id+"</span>"+
                    "<span class='content'>"+text+"</span>"+
                    "</div>");
            }


            data = JSON.stringify(data);
            console.log('1msg_receive:' + data);

        });
        JIM.onEventNotification(function(data) {
            console.log('event_receive: ' + JSON.stringify(data));
        });

        JIM.onSyncConversation(function(data) { //离线消息同步监听
            var data = data[0];
            let msgs = data.msgs;
            let msg_ids = new Array();
            for(msg in msgs){
                var need_receipt =  msgs[msg].need_receipt;
                var msg_id = msgs[msg].msg_id;
                msg_ids[msg] = msg_id;
                var content = msgs[msg].content;
                var from_id = content.from_id; //发送者
                var msg_body = content.msg_body; //数据
                var text = msg_body.text; //发送文本
                $(".lite-chatbox").append("<div class='cleft cmsg'>"+
                    "<img class='headIcon radius' ondragstart='return false;'  oncontextmenu='return false;' src='./img/A.jpg' />"+
                    "<span class='name'>"+from_id+"</span>"+
                    "<span class='content'>"+text+"</span>"+
                    "</div>");


            }
            addSingleReceiptReport(username,msg_ids);


            content = JSON.stringify(content);

            console.log( data);
        });

        JIM.onUserInfUpdate(function(data) {
            console.log('onUserInfUpdate : ' + JSON.stringify(data));
        });

        JIM.onSyncEvent(function(data) {
            console.log('onSyncEvent : ' + JSON.stringify(data));
        });

        JIM.onMsgReceiptChange(function(data){
            console.log('onMsgReceiptChange : ' + JSON.stringify(data));

        });

        JIM.onSyncMsgReceipt(function(data){
            console.log('onSyncMsgReceipt : ' + JSON.stringify(data));

        });

        JIM.onMutiUnreadMsgUpdate(function(data){
            console.log('onConversationUpdate : ' + JSON.stringify(data));

        });

        JIM.onTransMsgRec(function(data){
            console.log('onTransMsgRec : ' + JSON.stringify(data));
        });

        JIM.onRoomMsg (function(data){
            console.log('onRoomMsg  : ' + JSON.stringify(data));
        });

    }).onFail(function(data) {
        alert("登陆失败");
        console.log('error:' + JSON.stringify(data));
    }).onTimeout(function(data) {
        console.log('timeout:' + JSON.stringify(data));
    });
}

function sendSingleMsg() {
    var SendName = $('input[name="SendName"]').val();
    var content = $('input[name="content"]').val();
    var content1 = content;
    alert(SendName+":"+content);
    var name_span = $(".name_span").val();
    JIM.sendSingleMsg({
        'target_username' : SendName,
        'content' : content,
        'no_offline' : false,
        'no_notification' : false,
        //'custom_notification':{'enabled':true,'title':'title','alert':'alert','at_prefix':'atprefix'}
        need_receipt:true
    }).onSuccess(function(data,msg) {
        alert("发送成功");
        $(".lite-chatbox").append("<div class='cright cmsg'>"+
            "<img class='headIcon radius' ondragstart='return false;' oncontextmenu='return false;' src='./img/B.jpg' />"+
            "<span class='name'>"+name_span+"</span>"+
            "<span class='content'>"+content1+"</span></div>")
        var content = $('input[name="content"]').val("");

        console.log('success data:' + JSON.stringify(data));
        console.log('succes msg:' + JSON.stringify(msg));
    }).onFail(function(data) {
        console.log('error:' + JSON.stringify(data));
        alert("发送失败，请登录，检查发送人");
    });
}

//单聊消息回执
function addSingleReceiptReport(username,msg_ids){
    JIM.addSingleReceiptReport({
        'username':username,'msg_ids':msg_ids
    }).onSuccess(function(data,data2){
        console.log('success :' + JSON.stringify(data));
        console.log('success :' + JSON.stringify(data2));
    });
}

//获取会话列表
function getConversation() {
    JIM.getConversation().onSuccess(function(data) {
        console.log('success:' + JSON.stringify(data));
    }).onFail(function(data) {
        console.log('error:' + JSON.stringify(data));
    });
}
//获取好友列表
function getFriendList(){
    JIM.getFriendList().onSuccess(function(data) {
        console.log('获取好友列表成功：success:' + JSON.stringify(data));
    }).onFail(function(data) {
        console.log('error:' + JSON.stringify(data));
    });
}

//重置会话未读数
function resetUnreadCount(){
    JIM.resetUnreadCount({'username':across_user});
}

function onclickRegister(){
    $("#register_div").show();
    $("#login_div").hide();
}
function onclickLogin(){
    $("#login_div").show();
    $("#register_div").hide();
}

function addFriend(){
    var addUsername = $(".addFriend_username_input").val();
    alert(addUsername);
    JIM.addFriend(
        {
            'target_name':addUsername,
            'why':'hi,friend '
        }).onSuccess(function(data) {
            alert("添加成功");
        console.log('success:' + JSON.stringify(data));
    }).onFail(function(data) {
        alert("添加失败");
        console.log('error:' + JSON.stringify(data));
    });
}