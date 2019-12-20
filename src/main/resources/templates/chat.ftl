<html lang="zh">
<head>
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>激情在线聊天</title>

    <#-- EditorMD -->
    <link href="/vendor/editor/css/editormd.css" rel="stylesheet">
    <link href="/css/litewebchat.css" rel="stylesheet">
    <#-- 自定义 样式 -->
    <#include "public/front_custom_css.ftl">

    <#-- CSS -->
    <#include "public/front_css.ftl">

    <#-- JS -->
    <#include "public/front_js.ftl">

</head>


<#-- s-nav.ftl -->
<#include "public/nav.ftl">
<#-- e-nav.ftl -->


<script language="JavaScript">
    <#if Session ["session_current_user"]??>
        function loginJGIM() {
        var username = '${Session ["session_current_user"].username}';
        var password = '${Session ["session_current_user"].password}';
        var nickname = '${Session ["session_current_user"].nickname}';
        if(username == null && password == null){
            alert("请先登陆");
        }else{
            login(username,password);
        }
    }
        loginJGIM();
    <#else>
        alert("请先登陆");
    </#if>


</script>

<body>

<!-- 主容器 -->


<div  style="background-color: #00c4ff;width: 100%;height: 100%;margin-top: 55px">
    <#--聊天界面-->
    <div  style="width: 70%;height: 100%;background-color: #1b6d85;float: right" >
        <#--聊天框-->
        <div class="lite-chatbox">
            <!-- cleft 左 -->
            <div class="cleft cmsg">
                <img class="headIcon radius" ondragstart="return false;"  oncontextmenu="return false;" src="./img/A.jpg" />
                <span class="name">chenjunyu19</span>
                <span class="content">这是什么什么鬼？</span>
            </div>

            <!-- cright 右 -->
            <div class="cright cmsg">
                <img class="headIcon radius" ondragstart="return false;"  oncontextmenu="return false;"  src="./img/B.jpg" />
                <span class="name">SuperPaxxs</span>
                <span class="content">LiteChat_Frame（轻聊天气泡框架），一个贼简洁 <del>(简单)</del> 、美观、易用的 HTML 聊天界面框架</span>
            </div>
        </div>
         <div style="background-color: red;width: 100%;height: 10%;float: right;position:fixed; bottom: 0px;">
            <textarea style="height: 100%;width:60%;float: left;"></textarea>
             <button style="height: 100%;width: 40%;float: right;"></button>
        </div>
    </div>
    <#--好友列表界面-->
    <div  style="width: 30%;height: 100%;background-color: #a03836;float: left ;position: fixed">
        <style>
            .friend_list_ul{
                list-style-type:none;
                width: 100%;
                background-color: #d8d8d9;
                padding-left: 0px;
                padding-top: 5px;
            }
            .friend_list_li{
               /*padding-top: 13px;padding-bottom: 10px;*/
                width: 100%;
                height: 5%;
                background-color: #3ad900;
                text-align:center;
                margin-top: 10px;
            }
            .friend_list_name{

                font-size: 1.5vw;


            }

        </style>
        <ul class="friend_list_ul">
            <a style="margin-left: 20px;font-size: 22px;">好友列表 VVV</a>
            <li class="friend_list_li">
                <a class="friend_list_name"> 张盼兴 </a>
            </li>
            <li class="friend_list_li">
                <a class="friend_list_name"> 张盼兴 </a>
            </li>
            <li class="friend_list_li">
                <a class="friend_list_name"> 张盼兴 </a>
            </li>
            <li class="friend_list_li">
                <a class="friend_list_name"> 张盼兴 </a>
            </li>
        </ul>
    </div>

</div>



<#-- s-footer -->
<#--<#include "public/footer.ftl">-->
<#-- e-footer -->


</body>
</html>
