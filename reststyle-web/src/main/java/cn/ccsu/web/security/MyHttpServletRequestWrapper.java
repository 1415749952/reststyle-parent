package cn.ccsu.security;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:使用servlet提供的HttpServletRequestWrapper类，重写相关ServletRequest方法，实现多次读取request流中数据的能力
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-03-19
 * @Time: 15:05
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper
{
    private ServletInputStream inputStream;

    public MyHttpServletRequestWrapper(HttpServletRequest request)
    {
        super(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException
    {
        if (this.getContentLength() < 1)
        {
            return null;
        }
        if (this.inputStream == null)
        {
            this.inputStream = new ReadRepeatableInputStream(this.getRequest().getInputStream(), this.getRequest().getContentLength());
        }
        return inputStream;
    }

    private class ReadRepeatableInputStream extends ServletInputStream
    {
        private final ByteArrayInputStream bi;

        private ReadRepeatableInputStream(ServletInputStream inputStream, int length) throws IOException
        {
            if (length > 0)
            {
                byte[] bytes = new byte[length];
                IOUtils.read(inputStream, bytes, 0, length);
                inputStream.read(bytes, 0, length);
                bi = new ByteArrayInputStream(bytes, 0, length);
            }
            else
            {
                bi = null;
            }
        }

        @Override
        public int read() throws IOException
        {
            int bt = -1;
            if (bi != null)
            {
                bt = this.bi.read();
                if (bt == -1)
                {
                    this.bi.reset();
                }
            }
            return bt;
        }

        @Override
        public void reset()
        {
            this.bi.reset();
        }

        @Override
        public boolean isFinished()
        {
            return false;
        }

        @Override
        public boolean isReady()
        {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener)
        {

        }
    }
}
