package cn.korostudio.websitesystemd.sql;

import cn.korostudio.websitesystemd.data.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(User user){
        userRepository.save(user);
    }
}
