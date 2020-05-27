package cn.ccsu.commom.enums;

/**
 * @author lazycece
 * @date 2019/02/15
 */
public enum Sex
{
    /**
     * 男性
     */
    MAIL("1", "男性"),
    /**
     * 女性
     */
    FEMALE("2", "女性"),
    /**
     * 未知
     */
    OTHER("9", "未知");


    private String value;
    private String desc;


    Sex(String value, String desc)
    {
        this.value = value;
        this.desc = desc;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }
}
