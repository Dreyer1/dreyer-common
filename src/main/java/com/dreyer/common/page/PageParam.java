package com.dreyer.common.page;


import com.dreyer.common.util.StringUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Dreyer
 * @date: 16/3/21 下午5:57
 * @description 分页参数传递工具类
 */
public class PageParam implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1524297386210323303L;

    /**
     * 每页大小
     */
    private int pageSize;

    /**
     * 当前页码
     */
    private int currentPageIndex;

    /**
     * 需要排序的字段
     */
    private String sort;

    /**
     * 排序规则,升序:asc 降序:desc
     */
    private String order;


    /**
     * 获取分页的起始记录数
     *
     * @return
     */
    public int getPageStart() {
        if (this.currentPageIndex == 0) {
            return 0;
        }
        return (currentPageIndex - 1) * pageSize;
    }

    /**
     * 构造带分页条件的查询参数
     *
     * @param param     查询条件
     * @param pageParam 分页查询条件
     * @return
     */
    public static void buildPageParam(Map<String, Object> param, PageParam pageParam) {
        if (param == null) {
            param = new HashMap<String, Object>();
        }
        if (pageParam == null) {
            pageParam = new PageParam(10, 1);
        }
        param.put("page", pageParam.getPageStart());
        param.put("size", pageParam.getPageSize());
        if (!StringUtil.isEmpty(pageParam.getOrder()) && !StringUtil.isEmpty(pageParam.getSort())) {
            param.put("order", pageParam.getOrder());
            param.put("sort", pageParam.getSort());
        }
    }

    public PageParam() {

    }

    public PageParam(int pageSize, int currentPageIndex) {
        this(pageSize, currentPageIndex, null, null);
    }

    public PageParam(int pageSize, int currentPageIndex, String sort, String order) {
        this.pageSize = pageSize <= 0 ? 10 : pageSize;
        this.currentPageIndex = currentPageIndex <= 0 ? 1 : currentPageIndex;
        this.sort = sort;
        this.order = order;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void setCurrentPageIndex(int currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
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
