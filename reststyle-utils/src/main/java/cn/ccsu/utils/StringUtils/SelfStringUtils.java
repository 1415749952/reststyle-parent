package cn.ccsu.utils.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * Description:自己处理字符串的工具类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-08-12
 * @Time: 4:14 下午
 */
public class SelfStringUtils
{
    /**
     * 获取指定字符串出现的次数
     *
     * @param srcText  源字符串
     * @param findText 要查找的字符串
     * @return
     */
    public static int appearNumber(String srcText, String findText)
    {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find())
        {
            count++;
        }
        return count;
    }
}
