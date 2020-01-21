package common;


import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

        //获取Excel数据
        getData(sheetName, xssfWorkbook);

        log.info("信息:{}", excelPath);


        return null;
    }

    public static List<String> getData(String sheetName, XSSFWorkbook xssfWorkbook) throws Exception {
        XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);

        if (xssfSheet == null) {
            throw new NullPointerException("There is no sheet named '" + sheetName + "' exists in the excel, please create it before your test!");
        } else {
            List<String> headList = new ArrayList<String>();
            Row row = xssfSheet.getRow(xssfSheet.getFirstRowNum());
            if (row==null){
                return headList;
           }
           for (int j = xssfSheet.getFirstRowNum(); j < xssfSheet.getPhysicalNumberOfRows(); j++){
               Cell cell = row.getCell(j);
               headList.add(getCellValue(cell));
           }
           return null;
        }
    }

//    public static void init(XSSFSheet xssfSheet) {
//        int rowCount = 0;
//        int colCount = 0;
//        XSSFRow topRow;
//        XSSFCell xssfCell;
//        while (true) {
//            topRow = xssfSheet.getRow(rowCount);
//            if (topRow == null) {
//                break;
//            }
//
//            xssfCell = topRow.getCell(0);
//            topRow = xssfSheet.getRow(0);
//            while (true) {
//                xssfCell = topRow.getCell(colCount);
//                if (xssfCell==null){
//                    break;
//                }
//            }
//        }
//    }



    public static String getCellValue(Cell cell) throws Exception{
        String cellValue = "";
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
                cellValue = String.valueOf(cell.getStringCellValue()).trim();
                break;
        }
        return cellValue;
    }
}