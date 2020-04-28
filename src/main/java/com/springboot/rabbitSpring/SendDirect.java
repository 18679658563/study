package com.springboot.rabbitSpring;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/***
 * Created with IntelliJ IDEA.
 * Description: 路由模式
 * User: silence
 * Date: 2019-08-28
 * Time: 上午10:24
 */
public class SendDirect {

    private static final String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机类型
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        String message = "交换机类型　rabbit test";
        //delete 消息的ｋｅｙ
        channel.basicPublish(EXCHANGE_NAME,"insert",null,message.getBytes());
        System.out.println("send "+ message);
        channel.close();
        connection.close();
    }
}
