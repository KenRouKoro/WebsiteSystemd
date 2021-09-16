package cn.korostudio.websitesystemd.sql;

import cn.korostudio.websitesystemd.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findById(String id);
}
