package com.baidu.disconf.web.service.app.bo;

import com.baidu.disconf.web.service.app.vo.AppListVo;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.dsp.common.dao.Columns;
import com.baidu.dsp.common.dao.DB;
import com.baidu.unbiz.common.genericdao.annotation.Column;
import com.baidu.unbiz.common.genericdao.annotation.Table;
import com.github.knightliao.apollo.db.bo.BaseObject;

/**
 * @author liaoqiqi
 * @version 2014-6-16
 */
@Data
@Table(db = DB.DB_NAME, name = "app", keyColumn = Columns.APP_ID)
public class App extends BaseObject<Long> {

    protected static final Logger LOG = LoggerFactory.getLogger(App.class);

    /**
     *
     */
    private static final long serialVersionUID = -2217832889126331664L;

    /**
     *
     */
    @Column(value = Columns.NAME)
    private String name;

    /**
     *
     */
    @Column(value = Columns.DESC)
    private String desc;

    /**
     *
     */
    @Column(value = Columns.EMAILS)
    private String emails;

    /**
     * 创建时间
     */
    @Column(value = Columns.CREATE_TIME)
    private String createTime;

    /**
     * 更新时间
     */
    @Column(value = Columns.UPDATE_TIME)
    private String updateTime;


    @Override
    public String toString() {
        return "App [name=" + name + ", desc=" + desc + ", emails=" + emails + ", createTime=" + createTime +
                   ", updateTime=" + updateTime + "]";
    }

    public AppListVo getAppListVo(){
        return new AppListVo(this.id,this.name);
    }
}
