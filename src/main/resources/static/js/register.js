$("#register_button").click(function (){
    var email=$("#email").val();
    var password=$("#password").val();
    $.post("/doregister",{'email':email, 'password':password},function(data) {
        console.log(data.data);
        if(data.data=="EmailError"){
            $("#callback_p").text("邮件格式错误！");
        }else if(data.data=="EmailUsing"){
            $("#callback_p").text("邮件已经被使用！");
        }else if(data.data=="AESError"){
            $("#callback_p").text("加密错误！，请联系网站管理员！");
        }else if(data.data=="Find"){
            $("#callback_p").text("完成！,三秒后跳转到服务器创建页面。");
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
        }else{
            $("#callback_p").text("服务器未返回数据！");
        }
    });

})