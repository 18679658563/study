package com.springboot.test.data_work.model;

import lombok.Data;

import java.util.List;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2020-01-13
 * Time: 下午2:28
 */
@Data
public class Properties {

    private List<String> name;

    private List<String> time;

    private List<String> title;

    private List<String> content;
}
