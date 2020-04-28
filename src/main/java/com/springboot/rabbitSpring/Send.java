package com.springboot.rabbitSpring;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/***
 * Created with IntelliJ IDEA.
 * Description: 发送者
 * User: silence
 * Date: 2019-08-27
 * Time: 下午4:18
 */
public class Send {

    private final static String QUEUE_NAME = "test_work";//主题

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = RabbitConnectionUtil.getConnection();
        //从连接中创建通道
        Channel channel = connection.createChannel();//
        //申明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //消息内容
        for (int i = 0 ; i < 100; i++){
            String message = i+"\tHello World! RabbitMq test!";
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            Thread.sleep(400);
        }
        //关闭连接和通道
        channel.close();
        connection.close();
    }
}
