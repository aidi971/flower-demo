package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@JsonIgnoreProperties({"handler", "hiberanteLazyInitializer"})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String username;
    private String password;
    private String salt;

    @JoinColumn(name = "role_id")
    @Column(insertable = false,columnDefinition = "int default 1")
    private Integer roleId;

    @Column(insertable = false,columnDefinition = "int default 200")
    private Integer coin;

    private Date birthday;

    private String phone;

    @Override
    public String toString(){
        return String.format("User[id=%s,username=%s]",id,username);
    }
}
