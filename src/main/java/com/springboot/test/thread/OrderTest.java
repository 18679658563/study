package com.springboot.test.thread;

import org.apache.commons.collections.list.SynchronizedList;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * OrderTest  订单号生成
 *
 * @author txw
 * @date 2020/9/10 9:23
 */
public class OrderTest implements Runnable{

	private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1000);

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSS");

	private static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

	private static Integer ATOMIC_NUM = 10;

	private static List<String> list = Collections.synchronizedList(new ArrayList<>());

	public static String generateOrderNo(){
		LocalDateTime localDateTime = LocalDateTime.now(ZONE_ID);
		if(ATOMIC_INTEGER.intValue() > ATOMIC_NUM){
			ATOMIC_INTEGER.getAndSet(1000);
		}
		return localDateTime.format(DATE_TIME_FORMATTER) + ATOMIC_INTEGER.getAndIncrement();
	}

	public static void main(String[] args) throws Exception{
		for (int i = 0 ; i < ATOMIC_NUM; i++){
			new Thread(new OrderTest()).start();
		}
	}

	@Override
	public void run() {
		String s = generateOrderNo();
		System.out.println(s);
	}
}
