package com.springboot.test.data_work;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2020-01-09
 * Time: 上午9:22
 */
@Data
@JSONType(orders={"entity_type","name"})
public class PeopleAddress {

    private String entity_type;

    private String name;

}
