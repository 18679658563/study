package com.springboot.rabbitSpring;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/***
 * Created with IntelliJ IDEA.
 * Description: 接收者
 * User: silence
 * Date: 2019-08-27
 * Time: 下午4:29
 */
public class Recevice {

    private final static String QUEUE_NAME = "test_01";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //定义队列中的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);
        //获取信息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }
    }

}
