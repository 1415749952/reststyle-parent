package cn.ccsu.ipUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Description: Ip工具类
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-03-02
 * @Time: 18:07
 */
public class IPUtil
{
    /**
     * 获取IP 地址
     *
     * @param request
     * @return
     */
    public static String getIP(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ip.split(",");
        for (final String str : arr)
        {
            if (!"unknown".equalsIgnoreCase(str))
            {
                ip = str;
                break;
            }
        }
        return ip;
    }
}
