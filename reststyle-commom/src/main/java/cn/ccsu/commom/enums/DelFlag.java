package cn.ccsu.commom.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-09-09
 * @Time: 9:57 上午
 */
public enum DelFlag
{
    /**
     * 数据是存在的
     */
    EXIST("0", "未删除"),
    /**
     * 数据已经被删除
     */
    HASDEL("1", "已删除");

    /**
     * 状态
     */
    private String status;

    /**
     * 状态描述
     */
    private String value;

    DelFlag(String status, String value)
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
