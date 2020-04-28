package com.springboot.test.spring;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2020-01-03
 * Time: 上午9:51
 */
@SuppressWarnings("deprecation")
public class SpringTest {

    @Test
    public void testSimpleLoad()throws Exception{
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beans.xml"));
        MyTestBean mtb = (MyTestBean)bf.getBean("myTestBean");
        System.out.println("testStr".equals(mtb.getTestStr()));
    }
}
