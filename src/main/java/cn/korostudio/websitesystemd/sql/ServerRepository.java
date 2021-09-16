package cn.korostudio.websitesystemd.sql;

import cn.korostudio.websitesystemd.data.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
    Server findById(String id);
}
