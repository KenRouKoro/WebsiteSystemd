package cn.korostudio.websitesystemd.view;

import cn.korostudio.websitesystemd.data.Server;
import cn.korostudio.websitesystemd.data.User;
import cn.korostudio.websitesystemd.sql.ServerRepository;
import cn.korostudio.websitesystemd.sql.UserRepository;
import cn.korostudio.websitesystemd.tools.AesUtil;
import cn.korostudio.websitesystemd.tools.BTConnector;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;

@Controller
public class Register {
    @Value("${side}")
    public String side="NULL";

    @Value("${web-name}")
    public String web_name="NULL";

    @Value("${name}")
    public String name="NULL";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServerRepository serverRepository;

    @RequestMapping(value = "/creatserver",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String creatServer(@RequestParam("email")String email,
                              @RequestParam("password") String password,
                              HashMap<String,Object> map){
        User loginUser=userRepository.findByEmail(email);
        boolean back=false;
        if (loginUser==null){
            back=true;
        }
        String aesPassWord=null;//使用aes加密,key为email
        try {
            aesPassWord= AesUtil.encrypt(email,password);
        } catch (Exception ignored) {
        }
        if(aesPassWord==null||!aesPassWord.equals(Objects.requireNonNull(loginUser).getPassword())){
            back=true;
        }
        map.put("title_name",web_name);
        map.put("name",name);
        map.put("title_sub","创建服务器");
        map.put("side",side);
        map.put("email",loginUser.getEmail());
        try {
            map.put("password",AesUtil.decrypt(loginUser.getEmail(),loginUser.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
            if(back){
                map.put("user_id","0000");
                map.put("back","true");
            }
            else {
                map.put("user_id",loginUser.getId());
                map.put("back","false");
            }
            return "creatserver";

    }

    @RequestMapping("/register")
    public String register(HashMap<String,Object> map){
        map.put("title_name",web_name);
        map.put("name",name);
        map.put("title_sub","用户注册");
        return "register";
    }

    @Value("${btapi}")
    private String bt_api;
    @Value("${btside}")
    private String bt_side;
    @Value("${phpversion}")
    private String phpversion;
    @ResponseBody
    @RequestMapping(value = "/creat",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public BackData creat(@RequestParam("name")String name,
                          @RequestParam("sql_pw")String sql_pw,
                          @RequestParam("ftp_pw")String ftp_pw,
                          @RequestParam("id")String id
                          ){

        BackData backData=new BackData();
        User user = userRepository.findById(id);
        if (user==null){
            backData.setData("NoUser");
            return  backData;
        }
        try {

            String url = bt_side + "/site?action=AddSite";
            String timestamp = (new Date().getTime() + "");
            String md5Sign = BTConnector.getMd5(bt_api);
            String temp = timestamp + md5Sign;
            String token = BTConnector.getMd5(temp);

            String json = "request_time="+timestamp+"&request_token="+token;
            String responseText = BTConnector.sendPost(bt_side + "/system?action=GetSystemTotal",json);


            String data= "request_time="+timestamp+"&request_token="+token+"&webname="+
                    "{\"domain\":\""+name+"."+side+"\",\"domainlist\":[],\"count\":0}"
                    +"&path="+"/www/wwwroot/"+name+"."+side+"&type_id=0"+
                    "&type=PHP"+"&version="+phpversion+"&port=80"+"&ps="+user.getEmail()+"&ftp=false"+"&sql=false"+
                    "&codeing=utf8";
            responseText = BTConnector.sendPost(url,data);

            url=bt_side + "/ftp?action=AddUser";
            data= "request_time="+timestamp+"&request_token="+token+"&ftp_username="+name+"&ftp_password="+ftp_pw+"&path=/www/wwwroot/"+name+"."+side+"&ps="+user.getEmail();
            responseText = BTConnector.sendPost(url,data);



            url=bt_side + "/database?action=AddDatabase";//&name=bs&codeing=utf8&db_user=bs&password=PffBthSWLHdPMxZe&dtype=MySQL&dataAccess=127.0.0.1&address=127.0.0.1&ps=b
            data= "request_time="+timestamp+"&request_token="+token+"&name="+name+"&codeing=utf8"+"&db_user="+name+"&password="+sql_pw+"&dtype=MySQL"+"&dataAccess=127.0.0.1"+"&address=127.0.0.1"+"&ps="+user.getEmail();
            responseText = BTConnector.sendPost(url,data);

        }catch (Exception e){
            backData.setData("ServerError");
            return backData;
        }


        Server server=new Server();
        server.setServer_name(name);
        server.setDatabase_name(name);
        server.setFtp_name(name);
        server.setDatabase_password(sql_pw);
        server.setFtp_password(ftp_pw);
        server.setId(id);
        server.setPhpVersion(phpversion);
        serverRepository.save(server);
        user.setServer_name(name);
        userRepository.save(user);
        backData.setData("AllFine");
        return backData;
    }
    @ResponseBody
    @RequestMapping(value = "/doregister",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public BackData doregister(@RequestParam("email")String email,
                               @RequestParam("password") String password){
        BackData registerBackData=new BackData();
        if(!checkEmail(email)){
            registerBackData.setData("EmailError");
            return registerBackData;
        }else if(checkEmailUsing(email)) {
            registerBackData.setData("EmailUsing");
            return registerBackData;
        }
        User user=new User();
        user.setEmail(email);
        String aesPassWord;//使用aes加密,key为email
        try {
            aesPassWord= AesUtil.encrypt(email,password);
        } catch (Exception e) {
            registerBackData.setData("AESError");
            return registerBackData;
        }
        user.setPassword(aesPassWord);
        userRepository.save(user);
        registerBackData.setData("Find");
        return registerBackData;
    }
    protected boolean checkEmailUsing(String email){
        return userRepository.findByEmail(email) != null;
    }
    protected static boolean checkEmail(String email){
        if ((email != null) && (!email.isEmpty())) {
            return Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email);
        }
        return false;
    }
    @Data
    protected class BackData {
        private String data;
    }
}
