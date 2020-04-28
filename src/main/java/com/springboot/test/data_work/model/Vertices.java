package com.springboot.test.data_work.model;

import lombok.Data;

import java.util.List;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2020-01-13
 * Time: 下午2:24
 */
@Data
public class Vertices {

   private String id;
   private String label;
   private List<Properties> properties;

   private String bian;
}
