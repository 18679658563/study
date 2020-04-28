package com.springboot.rabbitSpring;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/***
 * Created with IntelliJ IDEA.
 * Description: 订阅模式，
 *              一个生产者，多个消费者
 *              生产者没有将消息发送到队列，而是发送到了交换机
 *              每个队列都要绑定交换机
 *              生产者发送的消息经过交换机到达队列．实现一个消息被多个消费者获取
 *     ＰＳ　：　一个消费者队列可以有多个消费者实例，只有其中一个消费者实例会消费
 * User: silence
 * Date: 2019-08-28
 * Time: 上午9:34
 */
public class SendFanout {

    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws Exception{
        //获取连接以及ｍｑ通道
        Connection connection = RabbitConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明ｅｘｃｈａｎｇｅ
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        //消息内容
        String message = "订阅模式　rabbit Mq test";
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }



}
