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

<style>
    html,body{
        height:100%;
        width: 100%;
        background-color: #669900;
        margin: auto;
    }


    .col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9{
        padding-right: 0px;
        padding-left: 0px;}

    .nikeName_li{
        list-style: none;
        background-color: white;
        height: 5%;
    }
    .nikeName_li:hover{
        background-color: whitesmoke;;
        opacity: 0.5;

    }


    @media (max-width: 768px) {
        .font_size_p{
            font-size: 10px;
        }
        .input_size{
            width: 100px;
        }
        .not_show_div{
            display: none;
        }


    }
    @media (min-width: 768px) {
        .font_size_p{
            font-size: 20px;
        }

        .input_size{
            width: 200px;

        }
        .not_show_div{
            position: fixed;bottom: 0px;justify-content: center;text-align: center;background-color: white;
            display:flex;
            /* display: none; */
        }
        .show_div{
            display: none;
        }

    }




</style>





<script language="JavaScript">
    <#if Session ["session_current_user"]??>
        function loginJGIM() {
        var username = '${Session ["session_current_user"].username}';
        var password = '${Session ["session_current_user"].password}';
        var nickname = '${Session ["session_current_user"].nickname}';
        if(username == null && password == null){
            alert("请先登陆");
        }else{
            setTimeout(function () {
                login(username,password);
            },1000)

        }
    }
        loginJGIM();
    <#else>
        alert("请先登陆");
    </#if>


</script>

<body>

    <div class="row" style="background-color: red;width: 100%;height: 100%;margin: auto;">
        <#-- s-nav.ftl 头部 -->
        <#include "public/nav.ftl">
        <#-- e-nav.ftl -->

        <#--主要聊天界面-->
        <div class=" col-12 col-md-12  col-sm-12 col-lg-12" style=" height: 100%;background-color: #00c4ff;margin-top: 54px">
            <#--好友列表-->
            <div class="col-3 col-md-2  col-sm-2 col-lg-2" style="height: 100%;background-color: #acacac;float: left" >
                <div class="col-12 col-lg-12"><p class="font_size_p" style="text-align: center;">VV 好友列表 VV</p></div>
                <ul class="col-12 col-lg-12" style="width: 100%; padding-left: 0px;padding-right: 0px;">
                    <li class="nikeName_li">
                        <p class="font_size_p" style="text-align: center;">张盼兴</p>
                    </li>
                    <li class="nikeName_li">
                        <p class="font_size_p" style="text-align: center;">张盼兴</p>
                    </li>
                    <li class="nikeName_li">
                        <p  class="font_size_p" style="text-align: center;">张盼兴</p>
                    </li>
                </ul>

                <#--添加好友输入框-->
                <div  class="col-3 col-md-2 col-sm-2 col-lg-2 not_show_div" style="height: 30px">
                    <input class=" col-lg-7 addFriend_username_input"  type="text" placeholder="请输入用户名">
                    <button class=" col-lg-2 btn btn-info " style="text-align: center;" onclick="addFriend()"><p class="">添加</p></button>
                    <button class=" col-lg-3 btn btn-info" style="text-align: center;"><p class="">消息</p></button>
                </div>
                <div class="col-3 col-md-2 col-sm-2 col-lg-2 show_div" style="position: fixed;bottom: 0px;background-color: khaki;" >
                    <div class="input_not_show" style="width: 100%; display:none;">
                        <input  type="text" class="addFriend_username_input" placeholder="请输入用户名"><br>
                        <button class="btn btn-info" style="width: 100%; height: 30px" onclick="addFriend()"><p>添加</p></button><br>
                        <button class="btn btn-info" style="width: 100%; height: 30px" "><p>消息</p></button><br>
                    </div>
                    <div><p class="font_size_p"><a id="addFriend_a" href="#" onclick="addFriend1()">添加好友</a></p></div>
                </div>

                <script>
                    $(document).ready(function(){



                    });

                    function addFriend1(){
                        var display =$(".input_not_show").css('display');
                        if(display == 'none'){
                            $(".input_not_show").show();
                        }else{
                            $(".input_not_show").hide();
                        }

                    }
                </script>



            </div>
            <#--聊天界面-->
            <div class="col-9 col-md-10  col-sm-10 col-lg-10" style="height: 100%;background-color: #7effb0;float: right">


                <div style="height: 4%;"><p class="font_size_p" style="text-align: center;margin: 0 0 0 0;">张盼兴</p></div>
                <div style="width:100%;height:2px; background: dimgrey;"></div>
                <div class="lite-chatbox col-12 col-md-12  col-sm-12 col-lg-12">

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

                <!-- 输入框 -->
                <div class="col-9 col-md-10  col-sm-10 col-lg-10" style="width:100%;height:6%;position: fixed;bottom: 0px; background-color: gainsboro;">
                    <textarea style="height: 100%;float: left" class="col-9 col-md-10  col-sm-10 col-lg-10"></textarea>
                    <button type="button" class="btn btn-info col-3 col-md-2  col-sm-2 col-lg-2" style="height: 100%;float: right">发送</button>

                </div>



            </div>


        </div>


    </div>







</body>
</html>
