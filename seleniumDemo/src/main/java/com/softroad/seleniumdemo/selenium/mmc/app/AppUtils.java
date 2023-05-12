package com.softroad.seleniumdemo.selenium.mmc.app;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yanzhao
 * @version 1.0
 * @classname appUtils
 * @date 2023/04/13 10:23
 * @description
 */
public class AppUtils {
	public static final String MMC_BATCH_ADDRESS = "http://192.168.3.228:9080/mmc/PMN11132/init";
	WebDriver driver;

	@Autowired
	FakeValuesService fakeValuesService;

	public void init(WebDriver driver) {
		this.driver = driver;
		driver.get(MMC_BATCH_ADDRESS);
		fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());
	}

	public void body() {

		pf9("0", "a0600");
	}

	public void execute() {
		driver.findElement(By.xpath("//*[@id=\"execute_button\"]")).click();
	}

	public void pf1() {
		String yyValue = "23";
		String mmValue = "03";
		String ddValue = "06";
		String ﾆﾂｽｳValue = "26";

//		yyValue = "23";
//		mmValue = "04";
//		ddValue = "19";
//		ﾆﾂｽｳValue = "165";

		// open pf1
		driver.findElement(By.xpath("//*[@id=\"button_1\"]")).click();

		driver.findElement(By.xpath("//*[@id=\"i1Yy\"]")).sendKeys(yyValue);
		driver.findElement(By.xpath("//*[@id=\"i1Mm\"]")).sendKeys(mmValue);
		driver.findElement(By.xpath("//*[@id=\"i1Dd\"]")).sendKeys(ddValue);
		driver.findElement(By.xpath("//*[@id=\"i1Nisu\"]")).sendKeys(ﾆﾂｽｳValue);

		execute();
		driver.findElement(By.xpath("//*[@id=\"button_2\"]")).click();

	}

	public void pf3() {
		// open pf3
		driver.findElement(By.xpath("//*[@id=\"button_3\"]")).click();

		String ｶｲｼﾔValue = "0";
		String ｼﾔﾊﾞﾝValue = "A0601";
		String ｸﾌﾞﾝValue = "1";
		String ｷﾝｶﾞｸValue = "16000";

		String yyValue = "23";
		String mmValue = "03";
		String ddValue = "22";

		driver.findElement(By.xpath("//*[@id=\"i1Kaisya\"]")).sendKeys(ｶｲｼﾔValue);
		driver.findElement(By.xpath("//*[@id=\"i1Syaban\"]")).sendKeys(ｼﾔﾊﾞﾝValue);
		driver.findElement(By.xpath("//*[@id=\"i1NKub\"]")).sendKeys(ｸﾌﾞﾝValue);
		driver.findElement(By.xpath("//*[@id=\"i1Zan\"]")).sendKeys(ｷﾝｶﾞｸValue);

		driver.findElement(By.xpath("//*[@id=\"i1Yy\"]")).sendKeys(yyValue);
		driver.findElement(By.xpath("//*[@id=\"i1Mm\"]")).sendKeys(mmValue);
		driver.findElement(By.xpath("//*[@id=\"i1Dd\"]")).sendKeys(ddValue);

		execute();

		driver.findElement(By.xpath("//*[@id=\"button_2\"]")).click();
		pf9("0", "A0601");
	}

	public void pf4() {
		// open pf3
		driver.findElement(By.xpath("//*[@id=\"button_4\"]")).click();

		String ﾀｲﾌﾟValue = "1";
		String ｼﾔﾊﾞﾝ_ｷﾕｳ_ｶｲｼﾔValue = "0";
		String ｼﾔﾊﾞﾝ_ｷﾕｳ_ｼﾔﾊﾞﾝValue = "A0600";
		String ｼﾒｲValue = "YANG FENGHAI";

		driver.findElement(By.xpath("//*[@id=\"i1Type\"]")).sendKeys(ﾀｲﾌﾟValue);
		driver.findElement(By.xpath("//*[@id=\"i1TKub\"]")).sendKeys(ﾀｲﾌﾟValue);
		driver.findElement(By.xpath("//*[@id=\"i1Kaisya1\"]")).sendKeys(ｼﾔﾊﾞﾝ_ｷﾕｳ_ｶｲｼﾔValue);
		driver.findElement(By.xpath("//*[@id=\"i1Syaban1\"]")).sendKeys(ｼﾔﾊﾞﾝ_ｷﾕｳ_ｼﾔﾊﾞﾝValue);
		driver.findElement(By.xpath("//*[@id=\"i1Name\"]")).sendKeys(ｼﾒｲValue);

		execute();

		driver.findElement(By.xpath("//*[@id=\"button_2\"]")).click();
		pf9("0", "A0600");
	}

	public void pf5() {
		// open pf3
		driver.findElement(By.xpath("//*[@id=\"button_5\"]")).click();

		String ｼﾖﾘValue = "0";
		String ﾀｲﾌﾟValue = "1";
		String ｶｲｼﾔValue = "0";
		String ｼﾔﾊﾞﾝValue = "A0600";

		String yyValue = "23";
		String mmValue = "03";
		String ddValue = "22";

		driver.findElement(By.xpath("//*[@id=\"i1Syori\"]")).sendKeys(ｼﾖﾘValue);
		driver.findElement(By.xpath("//*[@id=\"i1Type\"]")).sendKeys(ﾀｲﾌﾟValue);
		driver.findElement(By.xpath("//*[@id=\"i1Kaisya\"]")).sendKeys(ｶｲｼﾔValue);
		driver.findElement(By.xpath("//*[@id=\"i1Syaban\"]")).sendKeys(ｼﾔﾊﾞﾝValue);

		driver.findElement(By.xpath("//*[@id=\"i1Yy\"]")).sendKeys(yyValue);
		driver.findElement(By.xpath("//*[@id=\"i1Mm\"]")).sendKeys(mmValue);
		driver.findElement(By.xpath("//*[@id=\"i1Dd\"]")).sendKeys(ddValue);

		execute();

		driver.findElement(By.xpath("//*[@id=\"button_2\"]")).click();
		pf9("0", "A0600");
	}

	public void pf6() {
		pf9("0", "A0601");
		driver.findElement(By.xpath("//*[@id=\"button_2\"]")).click();
		// open pf3
		driver.findElement(By.xpath("//*[@id=\"button_6\"]")).click();

		String ｶｲｼﾔValue = "0";
		String ｼﾔﾊﾞﾝﾟValue = "A0602";

		String yyValue = "23";
		String mmValue = "02";
		String ddValue = "06";
		String ｼﾒｲValue = "YANG3";

		String ｷﾕｳﾖ_ｸﾌﾞﾝValue = "1";
		String ﾋｶｾﾞｲ_ｹﾞﾝﾄﾞValue = "001";
		String ｾﾝﾀｸ_ｸﾌﾞﾝValue = "3";

		String ｼﾞﾕｳｼﾖValue = "ﾄｳｷｮｳﾄ ﾏﾁﾀﾞｼ ｶﾅｲ";
		String ｼﾞﾕｳｼﾖValue2 = "4-53-14";

		String ｼﾕﾂｺｳ_ｸﾌﾞﾝValue = "1";
		String ｼﾕﾂｺｳ_ｻｷValue = "ﾀﾞｲﾚﾝ";
		String ｺｳｻﾞ_ｸﾌﾞﾝValue = "1";
		String ｺｳｻﾞ_ﾊﾞﾝｺﾞｳValue = "8372872";
		String ﾌﾘｺﾐ_ｷﾞﾝｺｳValue = "ﾐﾂｲ";
		String ｳｹﾄﾘ_ﾒｲｷﾞValue = "YFH";
		String ｻﾞﾝﾀﾞｶ_ｼﾕｳｾｲValue = "A";

		driver.findElement(By.xpath("//*[@id=\"i1Kaisya\"]")).sendKeys(ｶｲｼﾔValue);
		driver.findElement(By.xpath("//*[@id=\"i1Syaban\"]")).sendKeys(ｼﾔﾊﾞﾝﾟValue);

		driver.findElement(By.xpath("//*[@id=\"i1Yy\"]")).sendKeys(yyValue);
		driver.findElement(By.xpath("//*[@id=\"i1Mm\"]")).sendKeys(mmValue);
		driver.findElement(By.xpath("//*[@id=\"i1Dd\"]")).sendKeys(ddValue);
		driver.findElement(By.xpath("//*[@id=\"i1Name\"]")).sendKeys(ｼﾒｲValue);

		driver.findElement(By.xpath("//*[@id=\"i1Kyuryo\"]")).sendKeys(ｷﾕｳﾖ_ｸﾌﾞﾝValue);
		driver.findElement(By.xpath("//*[@id=\"i1Gendo\"]")).sendKeys(ﾋｶｾﾞｲ_ｹﾞﾝﾄﾞValue);
		driver.findElement(By.xpath("//*[@id=\"i1Senta\"]")).sendKeys(ｾﾝﾀｸ_ｸﾌﾞﾝValue);
		driver.findElement(By.xpath("//*[@id=\"i1Zyusyo\"]")).sendKeys(ｼﾞﾕｳｼﾖValue);
		driver.findElement(By.xpath("//*[@id=\"i1Zyusyo2\"]")).sendKeys(ｼﾞﾕｳｼﾖValue2);

		driver.findElement(By.xpath("//*[@id=\"i1SKub\"]")).sendKeys(ｼﾕﾂｺｳ_ｸﾌﾞﾝValue);
		driver.findElement(By.xpath("//*[@id=\"i1Syukou\"]")).sendKeys(ｼﾕﾂｺｳ_ｻｷValue);
		driver.findElement(By.xpath("//*[@id=\"i1KKub\"]")).sendKeys(ｺｳｻﾞ_ｸﾌﾞﾝValue);
		driver.findElement(By.xpath("//*[@id=\"i1KNo\"]")).sendKeys(ｺｳｻﾞ_ﾊﾞﾝｺﾞｳValue);
		driver.findElement(By.xpath("//*[@id=\"i1BMei\"]")).sendKeys(ﾌﾘｺﾐ_ｷﾞﾝｺｳValue);
		driver.findElement(By.xpath("//*[@id=\"i1KMei\"]")).sendKeys(ｳｹﾄﾘ_ﾒｲｷﾞValue);
		driver.findElement(By.xpath("//*[@id=\"i1TKub\"]")).sendKeys(ｻﾞﾝﾀﾞｶ_ｼﾕｳｾｲValue);

		execute();

	}

	public void pf9(String ｶｲｼﾔValue, String ｼﾔｲﾝ_ﾊﾞﾝｺﾞｳValue) {
		// open pf9
		driver.findElement(By.xpath("//*[@id=\"button_9\"]")).click();

		driver.findElement(By.xpath("//*[@id=\"i1Kaisya\"]")).sendKeys(ｶｲｼﾔValue);
		driver.findElement(By.xpath("//*[@id=\"i1Syaban\"]")).sendKeys(ｼﾔｲﾝ_ﾊﾞﾝｺﾞｳValue);
		execute();
	}
}
