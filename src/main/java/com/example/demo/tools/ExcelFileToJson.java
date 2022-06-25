package com.example.demo.tools;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

/**ExcelFileToJson，把表格转为JSON
 * @author GYJ
 */
@Slf4j
public class ExcelFileToJson {

    @NotNull
    public  static JSONArray tryExclTranslateToJson(String fileUrl){
        int starRow = 1;
        Sheet sheet;
        Workbook book;
        Cell cell1, cell2, cell3, cell4, cell5,cell6;
        JSONArray array = new JSONArray();
        try {
            //为要读取的excel文件名  "F://a.xls"
            book = Workbook.getWorkbook(new File(fileUrl));
            sheet = book.getSheet(0);
            for (int i = starRow ; i < sheet.getRows(); i++) {
                //获取每一行的单元格
                //（列，行）
                cell1 = sheet.getCell(0, i);
                cell2 = sheet.getCell(1, i);
                cell3 = sheet.getCell(2, i);
                cell4 = sheet.getCell(3, i);
                cell5 = sheet.getCell(4, i);
                cell6 = sheet.getCell(5, i);
                //如果读取的数据为空
                if ("".equals(cell1.getContents())) {
                    break;
                }
                JSONObject object = new JSONObject();
                object.put("序号",cell1.getContents());
                object.put("description",cell2.getContents());
                object.put("input",cell3.getContents());
                object.put("output",cell4.getContents());
                object.put("point",cell5.getContents());
                object.put("type",cell6.getContents());
                array.put(object);
            }
            book.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("here is excel "+array);
        return array;
    }
}
