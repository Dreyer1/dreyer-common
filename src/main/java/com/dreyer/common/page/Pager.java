package com.dreyer.common.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Dreyer
 * @date: 16/3/21 下午6:16
 * @description 分页组件
 */
public class Pager<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 5883634961024961545L;

    /**
     * 指定分页参数
     */
    private int currentPageIndex;   // 当前页码

    private int pageSize;   // 分页的大小

    /**
     * 查询数据库相关
     */
    private long total; // 总记录数

    private List<T> pageData = new ArrayList<T>();  // 分页的数据集合

    /**
     * 计算得出部分
     */
    private int pageCount;  // 总的页数

    private int pageBeginIndex; // 页码列表的开始索引(包含)

    private int pageEndIndex;   // 页码列表的结束索引(包含)

    /**
     * 指定排序规则
     */
    private String sort;    // 指定排序的字段

    private String order;   // 升序或者降序


    public Pager() {
    }

    /**
     * 只接受前4个必要的属性，会自动的计算出其他3个属性的值 >> 没指定排序规则的情况
     *
     * @param currentPageIndex 当前页码
     * @param pageSize         每页大小
     * @param total            总记录数
     * @param pageData         分页数据
     */
    public Pager(int currentPageIndex, int pageSize, int total, List<T> pageData) {

        this(currentPageIndex, pageSize, total, null, null, pageData);
    }

    /**
     * 只接受前6个属性,会自动计算出其他3个属性的值  >> 指定排序规则的情况
     *
     * @param currentPageIndex
     * @param pageSize
     * @param total
     * @param sort
     * @param order
     * @param pageData
     */
    public Pager(int currentPageIndex, int pageSize, int total, String sort, String order, List<T> pageData) {
        this.currentPageIndex = currentPageIndex;
        this.pageSize = pageSize;
        this.total = total;
        this.pageData = pageData;
        this.sort = sort;
        this.order = order;

        // 计算总页码
        pageCount = (total + pageSize - 1) / pageSize;

        /**
         * 计算 beginPageIndex 和 endPageIndex
         */
        // >> 总页数不多于10页，则全部显示
        if (pageCount <= 10) {
            pageBeginIndex = 1;
            pageEndIndex = pageCount;
        }
        // >> 总页数多于10页，则显示当前页附近的共10个页码
        else {
            // 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
            pageBeginIndex = currentPageIndex - 4;
            pageEndIndex = currentPageIndex + 5;
            // 当前面的页码不足4个时，则显示前10个页码
            if (pageBeginIndex < 1) {
                pageBeginIndex = 1;
                pageEndIndex = 10;
            }
            // 当后面的页码不足5个时，则显示后10个页码
            if (pageEndIndex > pageCount) {
                pageEndIndex = pageCount;
                pageBeginIndex = pageCount - 10 + 1;
            }
        }

    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void setCurrentPageIndex(int currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageBeginIndex() {
        return pageBeginIndex;
    }

    public void setPageBeginIndex(int pageBeginIndex) {
        this.pageBeginIndex = pageBeginIndex;
    }

    public int getPageEndIndex() {
        return pageEndIndex;
    }

    public void setPageEndIndex(int pageEndIndex) {
        this.pageEndIndex = pageEndIndex;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
