$("#loginButton").click(function (){
    var email=$("#userNameText").val();
    var password=$("#pwText").val();
    $.post("/login",{'email':email, 'password':password},function(data) {
        switch (data.data) {
            case 'NoUser':{
                $("#back_show").text("未找到用户！");
                break;
            }
            case 'AESError':{
                $("#back_show").text("AES加密错误！");
                break;
            }
            case 'PWError':{
                $("#back_show").text("密码错误！");
                break;
            }
            case  'GoCreat':{
                $("#back_show").text("检测到未创建服务器，正在跳转！");
                setTimeout(function (){
                    var URL = "/creatserver";
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
            case 'GoPanel':{
                $("#back_show").text("登录成功，正在跳转！");
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
            default:{
                $("#back_show").text("服务器未返回数据！");
                break;
            }

        }
    });
});