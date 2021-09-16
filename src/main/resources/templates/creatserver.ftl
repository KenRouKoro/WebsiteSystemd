<#include "header.ftl">
<div class="main-panel" style="height: 600px">
<p style="font-size: 32px">${name!}虚拟主机创建</p>

    <div style="text-align: left;padding-left: 20px">
        <p style="padding-left: 50px">输入想要的三级域名名称，数据库与ftp将与之同名</p>
        <p>地址：<input id="name" name="name" type="text" placeholder="输入地址" style="width: 100px"/>.${side!}</p><p id="name_back" style="color: #bb0a00"></p>
        <br/>
        <p style="padding-left: 50px">输入数据库密码，数据库版本为Mysql 8.0</p>
        <p>密码：<input id="sql_password" name="sql_password" type="password" placeholder="输入数据库密码"/></p>
        <br/>
        <p style="padding-left: 50px">输入FTP密码。</p>
        <p>密码：<input id="ftp_password" name="ftp_password" type="password" placeholder="输入FTP密码"/></p>
        <br/>
        <br/>
        <input id="creat_button"  name ="creat_button" type="button" value="提交" style="margin-left: 50px" />
        <p style="color: #bb0a00;text-align: center;" id="backp"></p>
        <br/>
        <p style="font-size: 8px;padding-left: 50px;padding-right: 50px ;color: #bababa">请注意，虚拟主机使用的php版本为7.4，并且密码一但创建只能提交邮件更改。</p>
    </div>
    <script type="text/javascript">
        var id='${user_id!}'
        var email='${email}';
        var password='${password}';
    if (${back})self.location='/';
    </script>
    <script type="text/javascript" src="/js/creat.js"></script>
</div>
<#include "footer.ftl">