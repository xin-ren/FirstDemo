package com.example.ysd.firstdemo.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 任新 on 2016/12/26 14:01
 * Function：RetrofitBean实体类
 * Desc：
 */
public class RetrofitBean {

    private Integer total_count;
    private Boolean incompleteResults;
    private List<Item> items = new ArrayList<Item>();

    /**
     *
     * @return
     *     The totalCount
     */
    public Integer getTotalCount() {
        return total_count;
    }

    /**
     *
     * @param totalCount
     *     The total_count
     */
    public void setTotalCount(Integer totalCount) {
        this.total_count = totalCount;
    }

    /**
     *
     * @return
     *     The incompleteResults
     */
    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    /**
     *
     * @param incompleteResults
     *     The incomplete_results
     */
    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    /**
     *
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }
}
