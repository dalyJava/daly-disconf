package com.baidu.disconf.web.service.app.vo;

import lombok.Data;

/**
 * @author liaoqiqi
 * @version 2014-6-22
 */
@Data
public class AppListVo {

    private long id;
    private String name;

    public AppListVo(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
