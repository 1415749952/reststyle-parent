package cn.ccsu.commom.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:记录操作日志的操作类型枚举类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-27
 * @Time: 17:23
 */
public enum OperationType
{
    /**
     * 操作类型
     */
    UNKNOWN("unknown"),
    DELETE("delete"),
    SELECT("select"),
    UPDATE("update"),
    INSERT("insert");

    private String value;

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    OperationType(String s)
    {
        this.value = s;
    }
}
