package com.springboot.test.test;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import lombok.Data;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-04-16
 * Time: 上午10:31
 */
public class ExcelUtil {

//    public static void main(String[] args) {
//        List<Test> list = ExcelUtil.getTest();
//        double resultx = 0;
//        for(int i = 0; i<list.size()-1;i++){
//            resultx += (list.get(i+1).getMy()-list.get(i).getMy())/((double)list.get(i).getMy());
//        }
//        double x = resultx/list.size();
//        double resulty = 0;
//        for(int i = 0; i<list.size()-1;i++){
//            resulty += (list.get(i+1).getYy()-list.get(i).getYy())/((double)list.get(i).getYy());
//        }
//        double y = resulty/list.size();
//        System.out.println(x+"\t"+y);
//        double monthChang = list.get(0).getMy()*(1+x);
//        double yearChang = list.get(0).getYy()*(1+x);
//        for(int year = 1996;year>=1970;year--){
//            for(int month=12;month>0;month--){
//                Test test = new Test();
//                test.setName("AS");
//                test.setMonth(month<10?year+"0"+month:year+""+month);
//                test.setMy((int)monthChang);
//                test.setYy((int)yearChang);
//                list.add(test);
//                if(Math.random()*3>=1){
//                    monthChang *= (1+x);
//                    yearChang *= (1+y);
//                }else{
//                    monthChang *= (1-x);
//                    yearChang *= (1-y);
//                }
//
//            }
//        }
//        Collections.sort(list, new Comparator<Test>() {
//            @Override
//            public int compare(Test o1, Test o2) {
//                return o1.getMonth().compareTo(o2.getMonth());
//            }
//        });
//        for(int i = 0; i<list.size();i++){
//            list.get(i).setId(""+(i+1));
//        }
//        ExcelUtil.toExcel(list);
//        for(Test test : list){
//            System.out.println(test);
//        }
//
//    }
    public static void main(String[] args){
       List<Test> tests =  ExcelUtil.getTest();
       ExcelUtil.toExcel(tests);
    }

    /**
     * excel转对象
     * @return
     */
    public static List<Test> getTest(){
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<Test> list = null;
        String cellData = null;
        String filePath = "/home/silence/下载/AS.xlsx";
        wb = readExcel(filePath);
        if(wb != null){
            //用来存放表中数据
            list = new ArrayList<>();
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i<rownum; i++) {
                Test test = new Test();
                row = sheet.getRow(i);
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        switch (j) {
                            case 0:
                                test.setId(cellData);break;
                            case 1:
                                test.setName(cellData);break;
                            case 2:
                                test.setMonth(cellData);break;
                            case 3:
                                double x = Double.valueOf(cellData);
                                test.setMy(cellData);break;
                            case 4:
                                double y = Double.valueOf(cellData);
                                test.setYy(cellData);break;
                        }
                    }
                }else{
                    break;
                }
                list.add(test);
            }
        }
        return list;
    }

    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }

    public static void toExcel(List<Test> list){
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("测试表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("id");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("key");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("value");
        cell.setCellStyle(style);
//        cell = row.createCell((short) 3);
//        cell.setCellValue("my");
//        cell.setCellStyle(style);
//        cell = row.createCell((short) 4);
//        cell.setCellValue("yy");
//        cell.setCellStyle(style);
        int x = 0;
        for (int i = 0; i < list.size(); i++){
            Test test =  list.get(i);
            // 第四步，创建单元格，并设置值
            //获取所有属性名称
            Field[] fields=test.getClass().getDeclaredFields();
//            for(int j=0;j < fields.length-1;j++){
//                ++x;
//                row = sheet.createRow( x);
//                row.createCell((short) 0).setCellValue(test.getId());
//                row.createCell((short) 1).setCellValue(fields[0].getName()+"+"+fields[j+1].getName());
//                row.createCell((short) 2).setCellValue(getFieldValueByName(fields[j+1].getName(),test));
//            }
//            row.createCell((short) 0).setCellValue(test.getId());
//            row.createCell((short) 1).setCellValue(test.getName());
//            row.createCell((short) 2).setCellValue(test.getMonth());
//            row.createCell((short) 3).setCellValue(test.getMy());
//            row.createCell((short) 4).setCellValue(test.getYy());
        }
        // 第六步，将文件存到指定位置
        try{
            FileOutputStream fout = new FileOutputStream("/home/silence/下载/test.xlsx");
            wb.write(fout);
            fout.close();
        }catch (Exception e)  {
            e.printStackTrace();
        }
    }

    /*
    获取属性字段的value
     */
    public static String getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            String value = (String)method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {

            return "1";
        }
    }


}
@Data
class Test {

    private String id;

    private String name;

    private String month;

    private String my;

    private String yy;

}


