package com.springboot.test.data_work;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2020-01-07
 * Time: 上午9:30
 */
@Data
@JSONType(orders={"entity_type","name","title","time","content","address","people"})
public class Entity {

    private String entity_type;//人物/地址/文章

    private String name;// 名字

    private String time;

    private String content;

    private Set<String> peopleList;//人

    private String people;

    private String peoples;

    private Set<String> addressList; //地点

    private String address;

    private String addresses;

    private Set<String> titleList;//人

    private String title;

    private String titles;



}
