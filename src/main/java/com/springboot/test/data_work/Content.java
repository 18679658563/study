package com.springboot.test.data_work;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;

import java.util.Set;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2020-01-09
 * Time: 上午9:20
 */
@Data
@JSONType(orders={"entity_type","name","title","time","content"})
public class Content {

    private String entity_type;//实体类型

    private String name;//文章名字/标题

    private String title;//标题

    private String content;//文章内容

    private String time;//时间

    public Content(){}

    public Content(String entity_type, String name, String title, String content, String time) {
        this.entity_type = entity_type;
        this.name = name;
        this.title = title;
        this.content = content;
        this.time = time;
    }
}
