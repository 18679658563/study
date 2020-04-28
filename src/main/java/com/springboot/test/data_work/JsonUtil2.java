package com.springboot.test.data_work;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.*;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2020-01-07
 * Time: 上午9:10
 */
public class JsonUtil2 {

    public static void main(String[] args) {
         writeFile();
    }


    public static String readFile() {
        String path = "/home/silence/ruanjian/data/V001.manifest";
        try {
            Reader reader = new InputStreamReader(new FileInputStream(new File(path)),"utf-8");
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

    public static List<Object> getEntity(){
        String json = readFile();
        String[] strs = json.split("\n");//所有数据
        //所有数据
        Map<String,Object> result = new HashMap<>();
        for(int i = 0 ; i < strs.length ; i++){
            //存放人物
            Map<String, String> peopleMap = new HashMap<>();
            //存放人物
            Map<String, String> pMap = new HashMap<>();
            JSONObject jsonobject = JSONObject.fromObject(strs[i]);
            String source = jsonobject.getString("source");
            //文章内容
            String con = source.split("//")[1];

            String t = con.split("发表日期：")[1];
            //时间
            String time = t.split("@@")[0].substring(0,10);
            String event = con.split(":")[1];
            //事件
            String titleName = event.split("@@")[0];
            //创建文章
            Content content = new Content("文章","文章" + i, titleName, con, time);
            result.put(content.getName(),content);
            //创建事件
            Title title = new Title("事件", titleName, titleName,time);
            //文章内的人物地点
            JSONArray array = jsonobject.getJSONArray("annotation");
            //地点集合
            Set<String> addressSet = new HashSet<>();
            for(Object object : array){
                JSONObject jsonObject = (JSONObject) object;
                Annotation annotation = (Annotation) JSONObject.toBean(jsonObject, Annotation.class);
                if("人物".equals(annotation.getName()) || "地点".equals(annotation.getName())){
                    PeopleAddress pa = new PeopleAddress();
                    pa.setEntity_type(annotation.getName());
                    String name = con.substring(Integer.parseInt(annotation.getProperty().getStart_index()),Integer.parseInt(annotation.getProperty().getEnd_index()));
                    pa.setName(name);
                    result.put(name,pa);
                    if("人物".equals(annotation.getName())){
                        peopleMap.put(annotation.getId(),name);
                    }
                    if("地点".equals(annotation.getName())){
                        addressSet.add(name);
                    }
                    result.put(name,pa);
                }
                if("会见".equals(annotation.getName())){
                    pMap.put(annotation.getProperty().getFrom(),annotation.getProperty().getTo());
                }
            }
            for (String str : addressSet) {
                title.setAddresses(title.getAddresses().append(str).append(", "));
            }
            //将任务地点放入title
            Set<String> set = new HashSet<>();
            Set<String> ps1 = new HashSet<>();
            Set<String> ps2 = new HashSet<>();
            Set<String> ps3 = new HashSet<>();
            if(!pMap.isEmpty()){
                for(String s : pMap.keySet()){
                    set.add(s);
                    set.add(pMap.get(s));
                    ps1.add(peopleMap.get(s));
                    ps2.add(peopleMap.get(pMap.get(s)));
                }
            }
            for(String s: peopleMap.keySet()){
                if(set.contains(s)){
                    continue;
                }
                ps3.add(peopleMap.get(s));
            }
            Iterator<String> it1 = ps1.iterator();
            while(it1.hasNext()){
                title.setPeople1(title.getPeople1().append(it1.next()).append(", "));
            }
            Iterator<String> it2 = ps2.iterator();
            while(it2.hasNext()){
                title.setPeople2(title.getPeople2().append(it2.next()).append(", "));
            }
            Iterator<String> it3 = ps3.iterator();
            while(it3.hasNext()){
                title.setPeople3(title.getPeople3().append(it3.next()).append(", "));
            }
            if(StringUtils.isNotEmpty(title.getPeople1().toString())){
                title.setPeople1(new StringBuilder(title.getPeople1().substring(0,title.getPeople1().length()-2)));
            }
            if(StringUtils.isNotEmpty(title.getPeople2().toString())){
                title.setPeople2(new StringBuilder(title.getPeople2().substring(0,title.getPeople2().length()-2)));
            }
            if(StringUtils.isNotEmpty(title.getPeople3().toString())){
                title.setPeople3(new StringBuilder(title.getPeople3().substring(0,title.getPeople3().length()-2)));
            }
            if(StringUtils.isNotEmpty(title.getAddresses().toString())){
                title.setAddresses(new StringBuilder(title.getAddresses().substring(0,title.getAddresses().length()-2)));
            }
            result.put(titleName,title);
        }
        List<Object> list = new ArrayList<>();
        for(String name : result.keySet() ){
            list.add(result.get(name));
        }
        return list;
    }


    public static void writeFile(){
        String path = "/home/silence/ruanjian/data/news.json";
        try{
            Writer write = new OutputStreamWriter(new FileOutputStream(new File(path)), "UTF-8");
            List<Object> list = getEntity();
            for(Object entity : list){
                String content = JSON.toJSONString(entity);
                write.write(content);
                write.write("\n");
            }
            write.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}





