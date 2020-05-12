package com.example.demo.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;
    private String abs;
    private String pic;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content_html")
    private String contentHtml;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content_md")
    private String contentMd;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "last_modified_time")
    private Timestamp lastModifiedTime;


}
