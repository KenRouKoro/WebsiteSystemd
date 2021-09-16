<#include "header.ftl">
<div class="main-panel" style="height: 650px;width: 70%;top: -100px;">
                <p style="font-size: 32px">虚拟主机${server_name}信息</p>
    <br/>
    <br/>
    <table style="width: 80%;margin: 0 auto;">
        <tr>
            <td>名称</td>
            <td>${server_name}</td>
        </tr>
        <tr>
            <td>完整地址</td>
            <td><a target="_blank" href="https://${server_name}.${side}">https://${server_name}.${side}</a></td>
        </tr>
        <tr>
            <td>PHP版本</td>
            <td>${php_version}</td>
        </tr>
        <tr>
            <td>数据库版本</td>
            <td>MySQL8.0</td>
        </tr>
        <tr>
            <td>数据库名</td>
            <td>${server_name}</td>
        </tr>
        <tr>
            <td>数据库密码</td>
            <td id="sql_pw">点击显示/隐藏</td>
        </tr>
        <tr>
            <td>FTP用户名</td>
            <td>${server_name}</td>
        </tr>
        <tr>
            <td>FTP密码</td>
            <td id="ftp_pw">点击显示/隐藏</td>
        </tr>
    </table>
    <br/>
    <br/>
    <br/>
    <button id="go_sql" style="width: 200px">前往phpMyAdmin</button><button id="go_ftp" style="width: 200px">前往在线FTP</button>

<script type="text/javascript">
    var sql_pw_show=false;
    $("#sql_pw").click(function (){
        if (sql_pw_show){
            sql_pw_show=false;
            $("#sql_pw").text("点击显示/隐藏");
        }else {
            sql_pw_show=true;
            $("#sql_pw").text("${sql_pw}");
        }
    });
    var ftp_pw_show=false;
    $("#ftp_pw").click(function (){
        if (ftp_pw_show){
            ftp_pw_show=false;
            $("#ftp_pw").text("点击显示/隐藏");
        }else {
            ftp_pw_show=true;
            $("#ftp_pw").text("${ftp_pw}");
        }
    });
    $("#go_sql").click(function (){
        window.open("https://phpadmin.${side}");
    });
    $("#go_ftp").click(function (){
        window.open("https://ftp.${side}");
    });
</script>
</div>
<#include "footer.ftl">
