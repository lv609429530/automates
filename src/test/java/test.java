import common.GetFileInformation;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class test extends GetFileInformation {



    @Test(
            dataProvider = "getInformation"
    )

    public void test() throws  Exception{
        System.out.println("Hello");

    }
}
