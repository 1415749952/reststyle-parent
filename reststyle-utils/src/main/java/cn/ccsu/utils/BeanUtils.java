package cn.ccsu.utils;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-10-29
 * @Time: 10:40 上午
 */
public class BeanUtils
{
    /**
     * 使object中为null的属性转换成空字符串
     *
     * @param object
     */
    public static void nullToEmpty(Object object)
    {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields)
        {
            field.setAccessible(true);
            try
            {
                Object var = field.get(object);
                String type = field.getType().toString();
                if ("class java.lang.String".equals(type))
                {
                    if (var == null || "".equals(var))
                    {
                        field.set(object, new String(""));
                    }
                }
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使object中空字符串转换成为null的属性
     *
     * @param object
     */
    public static void emptyToNull(Object object)
    {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields)
        {
            //设置属性可以访问
            field.setAccessible(true);
            try
            {
                Object var = field.get(object);
                String type = field.getType().toString();
                //获取类型
                if ("class java.lang.String".equals(type))
                {
                    if (var == null || "".equals(var))
                    {
                        field.set(object, null);
                    }
                }
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }
}
