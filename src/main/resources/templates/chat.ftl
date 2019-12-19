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
    <script src="/js/md5.js"></script>
    <#-- 自定义 样式 -->
    <#include "public/front_custom_css.ftl">

    <#-- CSS -->
    <#include "public/front_css.ftl">
</head>

<body>

<#-- s-nav.ftl -->
<#include "public/nav.ftl">
<#-- e-nav.ftl -->

<!-- 主容器 -->


<div  style="background-color: #00c4ff;width: 100%;line-height: 100%;margin-top: 55px">
    <#--聊天界面-->
    <div  style="width: 70%;height: 100%;background-color: #1b6d85;float: right" >
        <div class="lite-chatbox">

        </div>
    </div>
    <#--好友列表界面-->
    <div  style="width: 30%;height: 100%;background-color: #a03836;float: left">
        <style>
            .friend_list_ul{
                list-style-type:none;
                width: 100%;
                background-color: #d8d8d9;
                padding-left: 0px;
                padding-top: 10px;
            }
            .friend_list_li{
               padding-top: 13px;
                padding-bottom: 10px;
                width: 100%;
                height: 50px;
                background-color: #3ad900;
                text-align:center;
                margin-top: 10px;
            }
            .friend_list_name{

                font-size: 20px;


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

<#-- JS -->
<#include "public/front_js.ftl">
<#--<script language="JavaScript">-->
    <#--function loginJGIM() {-->
        <#--var username = '${Session ["session_current_user"].username}';-->
        <#--var password = '${Session ["session_current_user"].password}';-->
        <#--var nickname = '${Session ["session_current_user"].nickname}';-->
        <#--if(username == null && password == null){-->
            <#--alert("请先登陆");-->
        <#--}else{-->
            <#--login(username,password);-->
        <#--}-->
    <#--}-->
    <#--loginJGIM();-->

<#--</script>-->
</body>
</html>
