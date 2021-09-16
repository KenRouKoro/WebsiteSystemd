$("#creat_button").click(function (){
    $("#creat_button").hide();
    $("#backp").text("正在创建，请稍等......");
    var name = String($("#name").val());
    var sql_pw = String($("#sql_password").val());
    var ftp_pw = String($("#ftp_password").val());
    $.post("/creat",{'name':name, 'sql_pw':sql_pw,'ftp_pw':ftp_pw,'id':id},function(data) {
        switch (data.data){
            case "ServerError":{
                $("#backp").text("服务器错误！请更换地址重试。");
                $("#creat_button").show();
                break;
            }
            case "AllFine":{
                $("#backp").text("创建完成！正在跳转至控制面板。");
                setTimeout(function (){
                    var URL = "/panel";
                    var temp = document.createElement("form");
                    temp.action = URL;
                    temp.method = "post";
                    temp.style.display = "none";
                    var PARAMS = {'email':email,'password':password};
                    for (var x in PARAMS) {
                        var opt = document.createElement("textarea");
                        opt.name = x;
                        opt.value = PARAMS[x];
                        temp.appendChild(opt);
                    }
                    document.body.appendChild(temp);
                    temp.submit();
                }, 3000);
                break;
            }
            default :{
                $("#backp").text("服务器错误！服务器未返回数据！");
                break;
            }
        }
    });
});