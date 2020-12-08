package cn.ccsu.utils.cornUtils;

import org.quartz.CronExpression;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-29
 * @Time: 18:07
 */
public class CornUtil
{
    /**
     * 判断是不是Corn表达式
     * @param value
     * @return
     */
    public static boolean isCorn(String value)
    {
        if (!CronExpression.isValidExpression(value))
        {
            return false;
        }
        return true;
    }
}
