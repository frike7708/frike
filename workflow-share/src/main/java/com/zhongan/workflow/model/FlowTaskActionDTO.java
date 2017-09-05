package com.zhongan.workflow.model;

import java.util.Map;

/**
 * Created by chenshiyang on 2017/6/28.
 */
public class FlowTaskActionDTO {

    /**
     * Action-Id
     */
    private String id;

    /**
     * Action-Name
     */
    private String name;

    /**
     * Action属性
     */
    private Map<?,?> propertys;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<?, ?> getPropertys() {
        return propertys;
    }

    public void setPropertys(Map<?, ?> propertys) {
        this.propertys = propertys;
    }
}
