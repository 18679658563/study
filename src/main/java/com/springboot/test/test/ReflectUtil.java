package com.springboot.test.test;

import com.google.common.collect.Maps;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/***
 * Created with IntelliJ IDEA.
 * Description: 动态添加类字段
 * User: silence
 * Date: 2019-04-18
 * Time: 上午9:59
 */
public class ReflectUtil {
    static Logger logger = LoggerFactory.getLogger(ReflectUtil.class);

    public static Object getTarget(Object dest, Map<String, Object> addProperties) {
        PropertyUtilsBean propertyUtilsBean =new PropertyUtilsBean();
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(dest);
        Map<String, Class> propertyMap = Maps.newHashMap();
        for(PropertyDescriptor d : descriptors) {
            if(!"class".equalsIgnoreCase(d.getName())) {
                propertyMap.put(d.getName(), d.getPropertyType());
            }
        }
        addProperties.forEach((k, v) -> propertyMap.put(k, v.getClass()));
        DynamicBean dynamicBean =new DynamicBean(dest.getClass(), propertyMap);
        propertyMap.forEach((k, v) -> {
            try{
                if(!addProperties.containsKey(k)) {
                    Object Obj = propertyUtilsBean.getNestedProperty(dest,k);
                    dynamicBean.setValue(k, Obj);
                }
            }catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        });
        addProperties.forEach((k, v) -> {
            try{
                dynamicBean.setValue(k, v);
            }catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        });
        Object target = dynamicBean.getTarget();
        return target;
    }


    public static void main(String[] args) {
        LoggerEntity entity =new LoggerEntity();
        entity.setAppName("appname");
        entity.setOperator("add");
        entity.setResult("result");
        entity.setUri("uri");
        entity.setMethod("method");
        Map<String, Object> addProperties =new HashMap();
        addProperties.put("hello","world");
        addProperties.put("abc","123");
        Object obj = getTarget(entity,addProperties);
        System.out.println("1-------------"+obj);
        Field[] fields = obj.getClass().getDeclaredFields();
        System.out.println("2-------------"+fields[1].getName());
        for(int i = 0 ; i< fields.length;i++){
            int x = fields[i].getName().lastIndexOf("_");
            String name = fields[i].getName().substring(x+1);
            Object value = getFieldValueByName(name,entity);
            System.out.println(name+"\t"+value);
        }
    }
    /*
    获取属性字段的value
     */
    public static Object getFieldValueByName(String fieldName, Object o) {
            try {
                String firstLetter = fieldName.substring(0, 1).toUpperCase();
                String getter = "get" + firstLetter + fieldName.substring(1);
                Method method = o.getClass().getMethod(getter, new Class[] {});
                Object value =  method.invoke(o, new Object[] {});
                return value;
            } catch (Exception e) {
                return "1";
            }
    }


    public static class DynamicBean {
        /**
         * 目标对象
         */
        private Object target;

        /**
         * 属性集合
         */
        private BeanMap beanMap;

        public DynamicBean(Class superclass, Map<String, Class> propertyMap) {
            this.target = generateBean(superclass, propertyMap);
            this.beanMap = BeanMap.create(this.target);
        }


        /**
         * bean 添加属性和值
         *
         * @param property
         * @param value
         */
        public void setValue(String property, Object value) {
            beanMap.put(property, value);
        }

        /**
         * 获取属性值
         *
         * @param property
         * @return
         */
        public Object getValue(String property) {
            return beanMap.get(property);
        }

        /**
         * 获取对象
         *
         * @return
         */
        public Object getTarget() {
            return this.target;
        }


        /**
         * 根据属性生成对象
         *
         * @param superclass
         * @param propertyMap
         * @return
         */
        private Object generateBean(Class superclass, Map<String, Class> propertyMap) {
            BeanGenerator generator =new BeanGenerator();
            if(null != superclass) {
                generator.setSuperclass(superclass);
            }
            BeanGenerator.addProperties(generator, propertyMap);
            return generator.create();
        }
    }

}

