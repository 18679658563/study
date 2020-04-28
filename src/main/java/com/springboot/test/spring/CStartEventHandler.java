package com.springboot.test.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/***
 * Created with IntelliJ IDEA.
 * Description: 当使用 ConfigurableApplicationContext 接口中的 start() 方法启动 ApplicationContext 时
 * User: silence
 * Date: 2019-05-05
 * Time: 上午9:02
 */
public class CStartEventHandler implements ApplicationListener<ContextStartedEvent> {

    @Override
    public void onApplicationEvent(ContextStartedEvent contextStartedEvent) {
        System.out.println("ContextStartedEvent Received");
    }
}
