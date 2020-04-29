package com.cw.copywriting.vo;

import com.cw.copywriting.bean.LabelBean;

import java.util.List;

/**
 * @auther Liao ziyang
 * @date 2020/3/26
 * @desc
 */
public class ContentVO {

    private int id;

    private int type;

    private String content;

    private String imageUrl;

    private String datetime;

    private List<LabelBean> labelBeanList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public List<LabelBean> getLabelBeanList() {
        return labelBeanList;
    }

    public void setLabelBeanList(List<LabelBean> labelBeanList) {
        this.labelBeanList = labelBeanList;
    }
}
