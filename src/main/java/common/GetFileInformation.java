package common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.lang.reflect.Method;


@Slf4j
@Service
public class GetFileInformation {
    // 获取文件路径
    @Autowired
    ExcelWork excelWork = new ExcelWork();

    @DataProvider
    public Object[][] getInformation(Method method) throws Exception {

            log.info("Reading excel file----------------");
            // 获取 data-excel文件夹路径
            String filePath = GetFileInformation.class.getClassLoader().getResource("data-excel").getPath();

            // 获取 class类名
            String className = this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1, this.getClass().getName().length());

            // 拼装 路径及 xlsx文件名
            String excelFilePath = filePath + File.separator + className + ".xlsx";
            log.info("测试成功");
            // sheet名称 即方法名称
            String sheetName = method.getName();

            String testData = excelWork.getTestData(excelFilePath, sheetName);


        return null;
    }
}
