package com.springboot.test.data_work;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;

import java.util.List;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2020-01-09
 * Time: 上午9:47
 */
@Data
@JSONType(orders={"entity_type","name","title","time","peoples1","peoples2","peoples3","addresses"})
public class Title {

    private String entity_type;//实体类型

    private String name;//文章名字/标题

    private String title;//标题

    private String time;//时间

    private StringBuilder people1 = new StringBuilder();//set -> string  主,

    private StringBuilder people2 = new StringBuilder();//set -> string  被,

    private StringBuilder people3 = new StringBuilder();//set -> string  陪,

    private StringBuilder addresses = new StringBuilder(); //set -> string

    public Title(){}

    public Title(String entity_type, String name, String title,String time) {
        this.entity_type = entity_type;
        this.name = name;
        this.title = title;
        this.time = time;
    }
}
