import common.GetFileInformation;
import org.testng.annotations.Test;

public class test extends GetFileInformation {

    @Test(
            dataProvider = "getInformation"
    )

    public void test() throws  Exception{
        System.out.println("Hello");
    }
}
