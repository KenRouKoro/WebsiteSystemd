package cn.korostudio.websitesystemd.data;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Server {
    @Id
    private String id;
    private String server_name;
    private String database_name;
    private String database_password;
    private String ftp_name;//name==username
    private String ftp_password;
    private String phpVersion;
}
