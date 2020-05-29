package cn.ccsu.commom.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-29
 * @Time: 10:42
 */
public enum JobStatus
{

    /**
     * 已暂停
     */
    PAUSING("0", "已暂停"),
    /**
     * 运行中
     */
    RUNNING("1", "运行中"),
    /**
     * 已删除
     */
    DELETED("-1", "已删除");

    /**
     * 状态
     */
    private String status;

    /**
     * 状态描述
     */
    private String value;

    JobStatus(String status, String value)
    {
        this.status = status;
        this.value = value;
    }

    public String status()
    {
        return this.status;
    }

    public String value()
    {
        return this.value;
    }
}
