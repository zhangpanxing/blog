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

<script language="JavaScript">
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

</script>

<div class="lite-chatbox">


</div>



<#-- s-footer -->
<#include "public/footer.ftl">
<#-- e-footer -->

<#-- JS -->
<#include "public/front_js.ftl">
</body>
</html>
