package com.springboot.test.data_work;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.*;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2020-01-07
 * Time: 上午9:10
 */
public class JsonUtil {

    public static void main(String[] args) {
        writeFile();
    }


    public static String readFile() {
        String path = "/home/silence/ruanjian/data/V001.manifest";
        try {
            File file = new File(path);
            Reader reader = new InputStreamReader(new FileInputStream(file),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
            String jsonStr = sb.toString();
            return jsonStr;
        }catch (Exception e){

        }
        return "";
    }

    public static List<Entity> getEntity(){
        String json = readFile();
        String[] str = json.split("\n");//所有数据
        //存放Entity人物地点
        Map<String,Entity> entityMap = new HashMap<>();

        List<Entity> result = new ArrayList<>();
        for(String s : str){
            //存放Annotation 人物地点
            Map<String,Annotation> annotationMap = new HashMap<>();

            //存放annotation关系
            List<Annotation> list = new ArrayList<>();

            JSONObject jsonobject = JSONObject.fromObject(s);

            //文章内的人物地点关系
            JSONArray array = jsonobject.getJSONArray("annotation");

            String source = jsonobject.getString("source");
            String content = source.split("//")[1];//文章内容

            //文章实体
            Entity entity1 = new Entity();
            entity1.setAddressList(new HashSet<>());
            entity1.setPeopleList(new HashSet<>());
            entity1.setEntity_type("文章");
            entity1.setContent(content);
            String t = content.split("发表日期：")[1];
            String time = t.split("@@")[0];
            entity1.setTime(time);
            String n = content.split(":")[1];
            String contentName = n.split("@@")[0];
            //entity1.setTitle(contentName);
            entity1.setName(contentName);
            for (int i = 0; i < array.size(); i++) {
                //建立实体
                Entity entity = new Entity();
                //将array中的数据进行逐条转换
                JSONObject object = (JSONObject) array.get(i);
                //通过JSONObject.toBean()方法进行对象间的转换
                Annotation annotation = (Annotation) JSONObject.toBean(object, Annotation.class);

                if("赴".equals(annotation.getName()) || "会见".equals(annotation.getName())){
                    list.add(annotation);
                    continue;
                }
                String name = content.substring(Integer.parseInt(annotation.getProperty().getStart_index()),Integer.parseInt(annotation.getProperty().getEnd_index()));
                annotation.setType(annotation.getName());
                annotation.setName(name);
                annotationMap.put(annotation.getId(),annotation);
                if(!entityMap.containsKey(annotation.getName())){
                    entity.setEntity_type(annotation.getType());
                    entity.setName(annotation.getName());
                    entity.setPeopleList(new HashSet<>());
                    entity.setAddressList(new HashSet<>());
                    entity.setTitleList(new HashSet<>());
                    entityMap.put(entity.getName(),entity);
                }
                if("人物".equals(annotation.getType())){
                    entity1.getPeopleList().add(annotation.getName());
                }
                if("地点".equals(annotation.getType())){
                    entity1.getAddressList().add(annotation.getName());
                }
                entityMap.get(name).getTitleList().add(contentName);
            }

            result.add(entity1);

            for(Annotation annotation : list){
                //获取annotation  id
                String from = annotation.getProperty().getFrom();
                String to = annotation.getProperty().getTo();
                if(annotationMap.containsKey(from) && annotationMap.containsKey(to)){
                    //拿name
                    Annotation annotationFrom = annotationMap.get(from);
                    Annotation annotationTo = annotationMap.get(to);
                    if(entityMap.containsKey(annotationFrom.getName()) && entityMap.containsKey(annotationTo.getName())){
                        if ("会见".equals(annotation.getName())) {
                            entityMap.get(annotationFrom.getName()).getPeopleList().add(annotationTo.getName());
                        } else {
                            entityMap.get(annotationFrom.getName()).getAddressList().add(annotationTo.getName());
                        }
                    }
                }
            }


        }
        System.out.println("\n\n\n");
        for(String tt : entityMap.keySet()){
            result.add(entityMap.get(tt));
        }
        return result;
    }
    public static void writeFile(){
        String path = "/home/silence/ruanjian/data/news.json";
        try{
            File file = new File(path);
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            List<Entity> list = getEntity();
            for(Entity entity : list){
                if(entity.getAddressList().size() == 0){
                    entity.setAddressList(null);
                } else {
                    String address = "";
                    for(String add : entity.getAddressList()){
                        address += add + ", ";
                    }
                    address.substring(0, address.length()-2);
                    entity.setAddressList(null);
                    entity.setAddress(address.substring(0,address.length()-2));
                }
                if(entity.getPeopleList().size() == 0){
                    entity.setPeopleList(null);
                } else {
                    String people = "";
                    for(String add : entity.getPeopleList()){
                        people += add + ", ";
                    }
                    people.substring(0, people.length()-2);
                    entity.setPeopleList(null);
                    entity.setPeople(people.substring(0,people.length()-2));
                }
                if(entity.getTitleList() == null || entity.getTitleList().size() == 0){
                    entity.setTitleList(null);
                } else {
                    String title =entity.getTitle() == null ? "" : entity.getTitle();
                    for(String t : entity.getTitleList()){
                        title += t + ", ";
                    }
                    entity.setTitleList(null);
                    entity.setTitle(title.substring(0, title.length()-2));
                }
//                System.out.println(entity);
                String content = JSON.toJSONString(entity);
               // write.write(content);
                write.write("\n");
            }
            write.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}





