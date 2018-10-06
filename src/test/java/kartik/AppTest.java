package kartik;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Test
    public void kk()
    {
        System.out.println("junit test");
    }

    @Test
    public void testSelenium() {

        String chromeDriverPath = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        ChromeDriver cDriver = new ChromeDriver();


        cDriver.get("http://www.google.com/");
        cDriver.close();
        cDriver.quit();

    }
}
