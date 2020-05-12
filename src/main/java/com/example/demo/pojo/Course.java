package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;

    private Integer price;

    private Integer num;
    @Lob
    @Basic(fetch = FetchType.LAZY)

    private String description;

    private String pic;

    private Date time;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content_html")
    private String contentHtml;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content_md")
    private String contentMd;


    public Course(){
        super();
    }

    public Course(String name){
        super();
        this.name = name;
    }
    @Override
    public String toString(){
        return String.format("Course[id=%s,name=%s]",id,name);
    }


}
