package common;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
public class ReadExcel {

    public XSSFWorkbook getReadRes(String excelPath) throws Exception{
        try {

            File file = new File(excelPath);

            FileInputStream fileInputStream = new FileInputStream(file);

            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);

            return xssfWorkbook;

        }

        catch (FileNotFoundException e){
            e.fillInStackTrace();
        }
        catch (IOException e){
            e.fillInStackTrace();
        }


        return null;
    }
}
