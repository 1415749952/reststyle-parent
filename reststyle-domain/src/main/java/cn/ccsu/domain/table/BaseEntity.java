package cn.ccsu.domain.table;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 数据库实体类的父类，包装了创建时间，创建人，修改时间，修改人，唯一标识符id，软删除状态
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-20
 * @Time: 15:53
 */
@Data
public class BaseEntity
{
    /**
     * 唯一标识符id
     */
    private String id;
    /**
     * 创建者
     */
    private String createUser;

    /**
     * 最后一次修改者
     */
    private String modifyUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后一次更新时间
     */
    private Date modifyTime;

    /**
     * 删除标志 0否 1是
     */
    private String delFlag;

}
