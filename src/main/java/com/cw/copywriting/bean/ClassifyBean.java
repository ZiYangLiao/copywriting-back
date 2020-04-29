package com.cw.copywriting.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @auther Liao ziyang
 * @date 2020/3/26
 * @desc
 */
@Entity
@Data
@Table(name = "users")
public class ClassifyBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "classify_name")
    private String classifyName;
}
