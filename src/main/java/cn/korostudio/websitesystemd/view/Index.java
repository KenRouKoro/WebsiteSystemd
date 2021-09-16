package cn.korostudio.websitesystemd.view;

import cn.korostudio.websitesystemd.data.User;
import cn.korostudio.websitesystemd.sql.UserRepository;
import cn.korostudio.websitesystemd.tools.AesUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class Index {
    @Value("${side}")
    public String side="NULL";
    @Value("${web-name}")
    public String web_name="NULL";
    @Autowired
    private UserRepository userRepository;
    @RequestMapping("/")
    public String index(Map<String, Object> map){
        map.put("title_name",web_name);
        map.put("title",web_name);
        return "index";
    }
    @ResponseBody
    @RequestMapping(value = "/getUserID",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public BackData getUser(@RequestParam("email")String email,
                            @RequestParam("password") String password){
        BackData backData=new BackData();
        User user=userRepository.findByEmail(email);
        if (user==null){
            backData.setData("Error");
            return backData;
        }
        String aesPassWord;//使用aes加密,key为email
        try {
            aesPassWord= AesUtil.encrypt(email,password);
        } catch (Exception e) {
            backData.setData("Error");
            return backData;
        }
        if(!aesPassWord.equals(user.getPassword())){
            backData.setData("Error");
            return backData;
        }
        backData.setData(user.getId());
        return backData;
    }
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public BackData login(@RequestParam("email")String email,
                        @RequestParam("password") String password){
        User loginUser=userRepository.findByEmail(email);
        BackData backData=new BackData();
        if (loginUser==null){
            backData.setData("NoUser");
            return backData;
        }
        String aesPassWord;//使用aes加密,key为email
        try {
            aesPassWord= AesUtil.encrypt(email,password);
        } catch (Exception e) {
            backData.setData("AESError");
            return backData;
        }
        if(!aesPassWord.equals(loginUser.getPassword())){
            backData.setData("PWError");
            return backData;
        }
        if (loginUser.getServer_name()==null){
            backData.setData("GoCreat");
            return backData;
        }
        backData.setData("GoPanel");
        return backData;
    }
    @Data
    protected class BackData{
        private String data;
    }

}
