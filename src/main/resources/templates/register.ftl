<#include "header.ftl">

<div class="main-panel">
    <div class="main-panel-title">
        <p >
            ${name!"服务器错误!"}自助式注册
        </p>
    </div>
    <div class="main-panel-todo">
        <div class="main-panel-todo-find">
            <p>请提供您的邮箱与密码</p>
            <p>邮箱：<input id="email" name="email" type="text" placeholder="输入邮箱"/></p>  <!--用户名文本框-->
            <p>密码：<input id="password" name="password" type="password" placeholder="输入密码"/></p>                     <!--密码文本框-->
            <p><input id="register_button"  name ="register_button" type="button" value="提交" /></p><!--提交按钮-->
        </div>
    </div>
    <p style="color: #bb0a00" id="callback_p"></p>
</div>

    <script src="/js/register.js" type="text/javascript"></script>
<#include "footer.ftl">