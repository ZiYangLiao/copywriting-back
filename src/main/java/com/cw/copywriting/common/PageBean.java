package com.cw.copywriting.common;

/**
 * @author LiaoZiYang
 * @version 1.0
 * @date 2020/4/28 16:46
 * @Desc
 */
public class PageBean {

    public int pageNumber = 0;

    public int pageSize = 12;

    public int total = 0;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        if (pageNumber < 0) {
            pageNumber = (pageNumber - 1);
        } else {
            pageNumber = 0;
        }
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 0) {
            pageSize = 12;
        }
        if (pageSize > 60) {
            pageSize = 12;
        }
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
