package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plant")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;


    private String pic;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String descpiprion;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content_html")
    private String contentHtml;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content_md")
    private String contentMd;
}
