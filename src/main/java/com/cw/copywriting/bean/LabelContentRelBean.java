package com.cw.copywriting.bean;

import javax.persistence.*;

/**
 * @auther Liao ziyang
 * @date 2020/3/26
 * @desc
 */
@Entity
@Table(name = "label_content_rel")
public class LabelContentRelBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content_id")
    private int contentId;

    @Column(name = "labelId")
    private int labelId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }
}
