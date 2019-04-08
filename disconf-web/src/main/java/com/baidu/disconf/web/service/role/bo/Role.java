package com.baidu.disconf.web.service.role.bo;

import com.baidu.disconf.web.service.menu.bo.Menu;
import com.baidu.dsp.common.dao.Columns;
import com.baidu.dsp.common.dao.DB;
import com.baidu.unbiz.common.genericdao.annotation.Column;
import com.baidu.unbiz.common.genericdao.annotation.Table;
import com.github.knightliao.apollo.db.bo.BaseObject;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import lombok.Data;

/**
 * @author weiwei
 * @date 2013-12-24 下午2:43:37
 */
@Table(db = DB.DB_NAME, keyColumn = Columns.ROLE_ID, name = "role")
@Data
public class Role extends BaseObject<Long> implements Serializable {

    @Column(value = Columns.RoleColumns.ROLE_NAME)
    private String roleName;

    @Column(value = Columns.CREATE_TIME)
    private String createTime;

    @Column(value = Columns.CREATE_BY)
    private String createBy;

    @Column(value = Columns.UPDATE_TIME)
    private String updateTime;

    @Column(value = Columns.UPDATE_BY)
    private String updateBy;

    private Set<String> permissions;

    private List<Menu> menuList;

    @Override
    public String toString() {
        return "Role [roleName=" + roleName + ", createTime=" + createTime + ", createBy=" + createBy +
                   ", updateTime=" + updateTime + ", updateBy=" + updateBy + "]";
    }

}
