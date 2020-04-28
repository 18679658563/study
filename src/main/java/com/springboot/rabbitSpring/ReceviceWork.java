package com.springboot.rabbitSpring;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/***
 * Created with IntelliJ IDEA.
 * Description: work模式　，一个生产者，多个消费者　，但是只有一个消费者能拿到消息
 * User: silence
 * Date: 2019-08-27
 * Time: 下午5:02
 */
public class ReceviceWork {

    private final static String QUEUE_NAME = "test_work";

    public static void main(String[] args){
        try {
            // 获取到连接以及mq通道
            Connection connection = RabbitConnectionUtil.getConnection();
            Channel channel = connection.createChannel();
            // 声明队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 同一时刻服务器只会发一条消息给消费者
            channel.basicQos(1);
            // 定义队列的消费者
            QueueingConsumer consumer = new QueueingConsumer(channel);
            // 监听队列，false表示手动返回完成状态，true表示自动
            channel.basicConsume(QUEUE_NAME, false, consumer);
            // 获取消息
            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println( "\t [y] Received '" + message + "'");
                //休眠
                Thread.sleep(100);
                // 返回确认状态，注释掉表示使用自动确认模式
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
