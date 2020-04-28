package com.springboot.test.data_work;

import lombok.Data;

import java.util.List;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2020-01-07
 * Time: 上午10:55
 */
@Data
public class JsonEntity{

    private List<Annotation> annotation;

    private String usage;

    private String source;

    private String id;



}
