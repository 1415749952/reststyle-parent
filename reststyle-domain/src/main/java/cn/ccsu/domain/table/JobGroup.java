package cn.ccsu.domain.table;

import lombok.Data;

import java.util.Date;

/**
 * @author zxq
 */
@Data
public class JobGroup
{
    /**
     * 主键
     */
    private String id;

    /**
     * 分组名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

}