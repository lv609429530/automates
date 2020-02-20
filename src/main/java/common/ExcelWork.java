package common;


import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class ExcelWork {

    ReadExcel readExcel = new ReadExcel();

    /**
     * 获取Excel数据
     *
     * @param excelPath
     * @param sheetName
     * @return
     * @throws Exception
     */
    public String getTestData(String excelPath, String sheetName) throws Exception {
        //读取Excel
        XSSFWorkbook xssfWorkbook = readExcel.getReadRes(excelPath);
        //获取test sheet
        XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);

        //获取Excel数据
            //获取表头数据

        List<String> headData = getHeadData(xssfSheet);
        log.info("获取的表头信息:{}", headData);

            //获取测试数据
        List<List<String>> testData = getTestData(xssfSheet);
        log.info("获取的测试数据:{}",testData);

        return null;
    }


    //获取表头数据
    public static List<String> getHeadData(XSSFSheet xssfSheet) throws Exception {


        if (xssfSheet == null) {
            throw new NullPointerException("There is no sheet named '" + xssfSheet.getSheetName() + "' exists in the excel, please create it before your test!");
        } else {
            List<String> headList = new ArrayList<String>();
            Row row = xssfSheet.getRow(xssfSheet.getFirstRowNum());
            if (row==null){
                return headList;
           }
           for (int l = row.getFirstCellNum(); l <row.getLastCellNum(); l++){
               Cell cell = row.getCell(l);
               if (cell!=null) {
                   headList.add(getCellValue(cell));
               }
           }
           return headList;
        }
    }


    //获取测试数据
    public static List<List<String>> getTestData(XSSFSheet xssfSheet) throws Exception {

        List<List<String>> testData = new ArrayList<List<String>>();
        for (int h = xssfSheet.getFirstRowNum() + 1; h <= xssfSheet.getLastRowNum(); h++) {
            for (int l = xssfSheet.getRow(h).getFirstCellNum(); l < xssfSheet.getRow(h).getPhysicalNumberOfCells(); l++) {
                if (l != -1) {
                    Cell cell = xssfSheet.getRow(h).getCell(l);
                    List<String> everyData = new ArrayList<String>();
                    if (cell != null) {
                        everyData.add(getCellValue(cell));
                    }
                    testData.add(everyData);
                }
            }
        }
                return testData;
    }


    //字段处理
    public static String getCellValue(Cell cell) throws Exception{
        String cellValue = null;
        if (cell==null){
            return cellValue;
        }

        if (cell.getCellType()==cell.CELL_TYPE_NUMERIC){
            cell.setCellType(cell.CELL_TYPE_STRING);
        }
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue()).trim();
                break;
            case  Cell.CELL_TYPE_STRING:
                // 把返回的泛型转换成字符串，trim() 去掉尾部空格、特殊字符等。
                cellValue = String.valueOf(cell.getStringCellValue()).trim();
                break;
        }
        return cellValue;
    }
}