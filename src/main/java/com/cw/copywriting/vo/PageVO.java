package com.cw.copywriting.vo;

import java.util.List;

/**
 * @author LiaoZiYang
 * @version 1.0
 * @date 2020/4/28 16:46
 * @Desc
 */
public class PageVO<T> {

    public int pageNumber = 0;

    public int pageSize = 12;

    public int total = 0;

    public List<T> data;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
      this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
