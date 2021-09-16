<#include "header.ftl">
    <div class="main-panel">
        <div class="main-panel-title">
            <p >
                ${title_name!"服务器错误!"}
            </p>
        </div>
        <!--
        <div class="main-panel-info">
            <p>
                这是一个基于宝塔面板与Spring-Boot的自动化PHP+FTP+SQL控制程序。
            </p>
        </div>
        -->
        <div class="main-panel-todo">
            <div class="main-panel-todo-find">
                <p>使用您注册的邮箱与密码来查询您的虚拟主机信息</p>
                    <p>邮箱：<input id="userNameText" name="userNameText" type="text" placeholder="输入邮箱"/></p>  <!--用户名文本框-->
                    <p>密码：<input id="pwText" name="pwText" type="password" placeholder="输入密码"/></p>                     <!--密码文本框-->
                    <p><input id="loginButton"  name ="loginButton" type="button" value="提交" /></p><!--提交按钮-->
                <p id="back_show" style="color: #bb0a00;text-align: center"></p>
            </div>
        </div>
    </div>
    <script src="/js/login.js"  type="text/javascript"></script>
<#include "footer.ftl">