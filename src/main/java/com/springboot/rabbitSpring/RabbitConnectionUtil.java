package com.springboot.rabbitSpring;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/***
 * Created with IntelliJ IDEA.
 * Description:  rabbit连接
 * User: silence
 * Date: 2019-08-27
 * Time: 下午4:13
 */
public class RabbitConnectionUtil {

    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //服务地址
        factory.setHost("localhost");
        //端口
        factory.setPort(5672);
        //设置帐号信息,用户名．密码．vｈｏｓｔ
        factory.setVirtualHost("test");
        factory.setUsername("admin");
        factory.setPassword("admin");
        //通过工程获取连接
        return factory.newConnection();
    }

}
