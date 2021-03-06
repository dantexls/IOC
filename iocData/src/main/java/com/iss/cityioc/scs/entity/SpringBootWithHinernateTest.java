package com.iss.cityioc.scs.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by liwei on 2017/11/24
 */
@Table(name = "spring_boot_with_hinernate_test")
@Entity
public class SpringBootWithHinernateTest implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_name",length = 128)
    private String userName;
    @Column(name = "create_time")
    private Timestamp createTime;

    public SpringBootWithHinernateTest() {
    }

    public SpringBootWithHinernateTest(String userName, Timestamp createTime) {
        this.userName = userName;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SpringBootWithHinernateTest{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
