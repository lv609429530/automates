package common;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.lang.reflect.Method;


@Slf4j
public class GetFileInformation {
    // 获取文件路径

    @DataProvider
    public Object[][] getInformation(Method method) {
        // 获取 data-excel文件夹路径

        String filePath = GetFileInformation.class.getClassLoader().getResource("data-excel").getPath();

        // 获取 class类名
        String className = this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1, this.getClass().getName().length());

        // 拼装 路径及 xlsx文件名
        String excelFilePath = filePath + File.separator + className;



        return null;
    }
}
