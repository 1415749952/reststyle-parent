package cn.ccsu.commom.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-29
 * @Time: 10:41
 */
public enum JobLogStatus
{

    /**
     * 已暂停
     */
    FAILURE("0", "执行失败"),
    /**
     * 运行中
     */
    SUCCESS("1", "执行成功");

    /**
     * 状态
     */
    private String status;

    /**
     * 状态描述
     */
    private String value;

    JobLogStatus(String status, String value)
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
