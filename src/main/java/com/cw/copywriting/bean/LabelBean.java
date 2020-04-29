package com.cw.copywriting.bean;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;

/**
 * @auther Liao ziyang
 * @date 2020/3/26
 * @desc
 */
@Entity
@Table(name = "label")
public class LabelBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Ignore
    @Column(name = "classify_id")
    private int classifyId;

    @Column(name = "label_name")
    private String labelName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}
