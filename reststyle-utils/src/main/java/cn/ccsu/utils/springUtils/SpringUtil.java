package cn.ccsu.utils.springUtils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 依据数据（湖南）科技有限公司 版权所有Copyright (C) 2012
 * <p>
 * ${Id}
 * ${Revision}
 * ${Author} created by Yun Zhao
 * created by date 2018/5/22
 */
@Component
public class SpringUtil implements ApplicationContextAware
{
    /**
     * applicationContext
     */
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        if (SpringUtil.applicationContext == null)
        {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean
     *
     * @param name name
     * @return Object
     */
    public static Object getBean(String name)
    {
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.

    /**
     * getBean
     *
     * @param clazz clazz
     * @param <T>   T
     * @return T
     */
    public static <T> T getBean(Class<T> clazz)
    {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name  name
     * @param clazz clazz
     * @param <T>   T
     * @return T
     */
    public static <T> T getBean(String name, Class<T> clazz)
    {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 创建对象
     * Description:
     *
     * @param clazz clazz
     * @param <T>   T
     * @return T
     * @throws IllegalAccessException IllegalAccessException
     * @throws InstantiationException InstantiationException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    public <T> T loadClass(Class<T> clazz) throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        if (clazz != null)
        {
            return clazz.newInstance();
        }
        else
        {
            throw new ClassNotFoundException();
        }

    }
}
