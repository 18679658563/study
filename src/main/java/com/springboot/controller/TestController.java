package com.springboot.controller;

import com.springboot.mqtt.MqttGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-06-10
 * Time: 上午10:46
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MqttGateway mqttGateway;

    @RequestMapping("/sendMqtt")
    public String sendMqtt(String sendData) {
        mqttGateway.sendToMqtt(sendData, "testtopic");
        return "OK";
    }


    @Autowired
    private JedisPool jedisPool;

    @GetMapping("/redis")
    public String getString(String str){
        try{
            Jedis jedis = jedisPool.getResource();
            return jedis.get(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
