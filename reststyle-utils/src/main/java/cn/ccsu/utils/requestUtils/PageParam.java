package cn.ccsu.utils.requestUtils;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:请求分页
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-25
 * @Time: 17:16
 */
@Data
public class PageParam
{
    /**
     * 页码，从1开始
     */
    private int pageNum;
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 排序（哪一个字段）
     */
    private String orderBy;
    /**
     * ASC：升序（默认），DESC：降序。
     */
    private String sequence;
}
