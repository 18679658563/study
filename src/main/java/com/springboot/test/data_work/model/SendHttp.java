package com.springboot.test.data_work.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.*;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2020-01-13
 * Time: 上午8:49
 */
public class SendHttp {

    public static void main(String[] args) {
        String name = "王宜林";
        String result = getData("{\n" +
                "\t\"command\": \"g.V().has(\\\"name\\\",Text.textCISubString(\\\"王宜林\\\")).both()\"\n" +
                "}\n");
        JSONObject jsonObject = JSON.parseObject(result);
        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
        JSONArray vertices = jsonObject1.getJSONArray("vertices");
        List<Vertices> verticesList = new ArrayList<>();
        for(Object object: vertices){
            Vertices vertices1 = JSON.parseObject(object.toString(),new TypeReference<Vertices>(){});
            verticesList.add(vertices1);
        }
        Set<Object> set = new HashSet<>();
        for(Vertices vertices1 : verticesList){
            if("人物".equals(vertices1.getLabel()) && !"王宜林".equals(vertices1.getProperties().get(0).getName().get(0))){
                set.add(vertices1.getProperties().get(0).getName().get(0));
            }
        }

//        String result1 = getData("{\n" +
//                "\t\"command\": \"g.V().has(\\\"name\\\",Text.textCISubString(\\\"王宜林\\\")).bothE()\"\n" +
//                "}");
//        JSONObject json = JSON.parseObject(result1);
//        JSONObject json2 = json.getJSONObject("data");
//        JSONArray jsonArray = json2.getJSONArray("edges");
//        Map<String,String> map = new HashMap<>();
//        for(Object object : jsonArray){
//            Edges edges = JSON.parseObject(object.toString(),new TypeReference<Edges>(){});
//            map.put(edges.getTarget(),edges.getLabel());
//        }
//        for(Vertices vertices1 : verticesList){
//            if(map.containsKey(vertices1.getId())){
//                vertices1.setBian(map.get(vertices1.getId()));
//            }
//            System.out.println(vertices1);
//        }

    }

    public static String getData(String sql){
        Map<String,String> getToken = new HashMap<>();
        getToken.put("Content-Type", "application/json;charset=utf8");
        String token = doPost("https://iam.cn-north-1.myhuaweicloud.com/v3/auth/tokens", getToken, "{\n" +
                "\t\"auth\": {\n" +
                "\t\t\"identity\": {\n" +
                "\t\t\t\"methods\": [\"password\"],\n" +
                "\t\t\t\"password\": {\n" +
                "\t\t\t\t\"user\": {\n" +
                "\t\t\t\t\t\"name\": \"jrn1012\",\n" +
                "\t\t\t\t\t\"password\": \"jiao1202\",\n" +
                "\t\t\t\t\t\"domain\": {\n" +
                "\t\t\t\t\t\t\"name\": \"jrn1012\"\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"scope\": {\n" +
                "\t\t\t\"project\": {\n" +
                "\t\t\t\t\"id\": \"04da14b81f0026ac2f53c00f7882d530\",\n" +
                "\t\t\t\t\"name\": \"cn-north-4\"\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "  }");

        Map<String,String> map = new HashMap<>();
        map.put("Content-Type", "application/json");
        map.put("X-Auth-Token",token);
        String url = "https://nlp-ext.cn-north-4.myhuaweicloud.com/v1/04da14b81f0026ac2f53c00f7882d530/kg/kg-instances/5473072a-d81d-44fd-abfe-9711a18e0d24/gremlin-query";
        String response1 = doPost(url, map, sql);
        return response1;
    }

    public static String doPost(String url,Map<String,String> headers,String request){
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try{
            //创建http请求
            HttpPost httpPost = new HttpPost(url);
            for(String  s : headers.keySet()){
                httpPost.addHeader(s, headers.get(s));
            }
            //创建请求内容
            StringEntity entity = new StringEntity(request,"UTF-8");
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            for(Header header : response.getAllHeaders()){
                if("X-Subject-Token".equals(header.getName())){
                    return header.getValue();
                }
            }
            String resData = EntityUtils.toString(response.getEntity());
            return resData;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭资源
            if(response != null){
                try {
                    response.close();
                }catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
            if(httpClient != null){
                try{
                    httpClient.close();
                }catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
        return null;
    }
}
