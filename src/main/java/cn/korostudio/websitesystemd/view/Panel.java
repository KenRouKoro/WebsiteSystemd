package cn.korostudio.websitesystemd.view;

import cn.korostudio.websitesystemd.data.Server;
import cn.korostudio.websitesystemd.data.User;
import cn.korostudio.websitesystemd.sql.ServerRepository;
import cn.korostudio.websitesystemd.sql.UserRepository;
import cn.korostudio.websitesystemd.tools.AesUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class Panel {

    @Value("${btside}")
    private String bt_side;

    @Value("${side}")
    public String side="NULL";

    @Value("${web-name}")
    public String web_name="NULL";

    @Value("${name}")
    public String name="NULL";

    @Value("${phpversion}")
    private String phpversion;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServerRepository serverRepository;



    @RequestMapping(value = "/panel",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String panel(Map<String , Object>map,
                        @RequestParam("email")String email,
                        @RequestParam("password") String password
                        ){
        User user = userRepository.findByEmail(email);
        if (user==null){
            return "error";
        }
        String aesPassWord;//使用aes加密,key为email
        try {
            aesPassWord= AesUtil.encrypt(email,password);
        } catch (Exception e) {
            return "error";
        }
        if(!aesPassWord.equals(user.getPassword())){
            return "error";
        }
        map.put("title_name",web_name);
        map.put("name",name);
        map.put("title_sub","控制面板");
        Server server=serverRepository.findById(user.getId());
        map.put("server_name",server.getServer_name());
        map.put("sql_pw",server.getDatabase_password());
        map.put("ftp_pw",server.getFtp_password());
        map.put("side",side);
        map.put("php_version",phpversion);
        return "panel";
    }




    @Data
    protected class BackData {
        private String data;
    }
}

