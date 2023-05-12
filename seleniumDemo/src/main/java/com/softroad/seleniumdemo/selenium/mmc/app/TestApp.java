package com.softroad.seleniumdemo.selenium.mmc.app;

import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

/**
 * @author yanzhao
 * @version 1.0
 * @classname appUtils
 * @date 2023/04/13 10:23
 * @description
 */
public class TestApp {
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

		for (int i = 0; i < 100000; i++) {
			// open pf1
			System.out.println(i);
			pf4();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			driver.findElement(By.xpath("//*[@id=\"button_2\"]")).click();

		}

		// pf9("0", "a0600");
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

		yyValue = fakeValuesService.regexify("[1-9]{2}");
		mmValue = fakeValuesService.regexify("[1-9]{2}");
		ddValue = fakeValuesService.regexify("[1-9]{2}");
		ﾆﾂｽｳValue = fakeValuesService.regexify("[1-9]{2}");

		// open pf1
		driver.findElement(By.xpath("//*[@id=\"button_1\"]")).click();

		driver.findElement(By.xpath("//*[@id=\"i1Yy\"]")).sendKeys(yyValue);
		driver.findElement(By.xpath("//*[@id=\"i1Mm\"]")).sendKeys(mmValue);
		driver.findElement(By.xpath("//*[@id=\"i1Dd\"]")).sendKeys(ddValue);
		driver.findElement(By.xpath("//*[@id=\"i1Nisu\"]")).sendKeys(ﾆﾂｽｳValue);

		execute();

	}

	public void keywordF5() {
		WebElement findElement = driver.findElement(By.xpath("//*[@id=\"execute_button\"]"));
		findElement.sendKeys(Keys.F5);
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div/div/div/div[4]/button")).click();
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

		yyValue = fakeValuesService.regexify("[1-9]{2}");
		mmValue = fakeValuesService.regexify("[1-9]{2}");
		ddValue = fakeValuesService.regexify("[1-9]{2}");
		ｶｲｼﾔValue = fakeValuesService.regexify("[1-9]{2}");
		ｼﾔﾊﾞﾝValue = fakeValuesService.regexify("[1-9]{5}");
		ｸﾌﾞﾝValue = fakeValuesService.regexify("[1-9]{2}");
		ｷﾝｶﾞｸValue = fakeValuesService.regexify("[1-9]{8}");

		driver.findElement(By.xpath("//*[@id=\"i1Kaisya\"]")).sendKeys(ｶｲｼﾔValue);
		driver.findElement(By.xpath("//*[@id=\"i1Syaban\"]")).sendKeys(ｼﾔﾊﾞﾝValue);
		driver.findElement(By.xpath("//*[@id=\"i1NKub\"]")).sendKeys(ｸﾌﾞﾝValue);
		driver.findElement(By.xpath("//*[@id=\"i1Zan\"]")).sendKeys(ｷﾝｶﾞｸValue);

		driver.findElement(By.xpath("//*[@id=\"i1Yy\"]")).sendKeys(yyValue);
		driver.findElement(By.xpath("//*[@id=\"i1Mm\"]")).sendKeys(mmValue);
		driver.findElement(By.xpath("//*[@id=\"i1Dd\"]")).sendKeys(ddValue);

		String i1SKub = fakeValuesService.regexify("[1-9]{1}");
		driver.findElement(By.xpath("//*[@id=\"i1SKub\"]")).sendKeys(i1SKub);
		String i1TKub = fakeValuesService.regexify("[1-9]{1}");
		driver.findElement(By.xpath("//*[@id=\"i1TKub\"]")).sendKeys(i1TKub);
		String i1PKub = fakeValuesService.regexify("[1-9]{1}");
		driver.findElement(By.xpath("//*[@id=\"i1PKub\"]")).sendKeys(i1PKub);
		driver.findElement(By.xpath("//*[@id=\"i1Yn\"]")).sendKeys("N");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		driver.findElement(By.xpath("//*[@id='execute_button']")).click();

//        driver.findElement(By.xpath("//*[@id=\"button_2\"]")).click();
//        pf9("0", "A0601");
	}

	public void pf4() {
		// open pf3
		driver.findElement(By.xpath("//*[@id=\"button_4\"]")).click();

		String ﾀｲﾌﾟValue = "1";
		String ｼﾔﾊﾞﾝ_ｷﾕｳ_ｶｲｼﾔValue = "0";
		String ｼﾔﾊﾞﾝ_ｷﾕｳ_ｼﾔﾊﾞﾝValue = "A0600";
		String ｼﾒｲValue = "YANG FENGHAI";


		driver.findElement(By.xpath("//*[@id=\"i1Type\"]")).sendKeys(ﾀｲﾌﾟValue);
		driver.findElement(By.xpath("//*[@id=\"i1TKub\"]")).sendKeys("Y");
		driver.findElement(By.xpath("//*[@id=\"i1Kaisya1\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{1}"));
		driver.findElement(By.xpath("//*[@id=\"i1Syaban1\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{5}"));
		driver.findElement(By.xpath("//*[@id=\"i1Kaisya2\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{1}"));
		driver.findElement(By.xpath("//*[@id=\"i1Syaban2\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{5}"));
		driver.findElement(By.xpath("//*[@id=\"i1Y\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{2}"));
		driver.findElement(By.xpath("//*[@id=\"i1M\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{2}"));
		driver.findElement(By.xpath("//*[@id=\"i1D\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{2}"));
		driver.findElement(By.xpath("//*[@id=\"i1Name\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{20}"));
		
		driver.findElement(By.xpath("//*[@id=\"i1Kyuryo\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{1}"));
		driver.findElement(By.xpath("//*[@id=\"i1Gendo\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{3}"));
		driver.findElement(By.xpath("//*[@id=\"i1Senta\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{1}"));
		
		driver.findElement(By.xpath("//*[@id=\"i1Zyusyo\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{40}"));
		driver.findElement(By.xpath("//*[@id=\"i1Zyusyo2\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{40}"));
		driver.findElement(By.xpath("//*[@id=\"i1SKub1\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{1}"));
		driver.findElement(By.xpath("//*[@id=\"i1KKub\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{1}"));
		driver.findElement(By.xpath("//*[@id=\"i1KNo\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{7}"));
		
		driver.findElement(By.xpath("//*[@id=\"i1Syukou\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{40}"));
		driver.findElement(By.xpath("//*[@id=\"i1BMei\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{40}"));
		driver.findElement(By.xpath("//*[@id=\"i1KMei\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{40}"));
		
		driver.findElement(By.xpath("//*[@id=\"i1UKub\"]")).sendKeys(fakeValuesService.regexify("[a-z1-9]{1}"));

		execute();

//		driver.findElement(By.xpath("//*[@id=\"button_2\"]")).click();
//		pf9("0", "A0600");
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

		ｼﾖﾘValue = fakeValuesService.regexify("[1-9]{20}");
		ﾀｲﾌﾟValue = fakeValuesService.regexify("[1-9]{20}");
		ｶｲｼﾔValue = fakeValuesService.regexify("[1-9]{20}");
		ｼﾔﾊﾞﾝValue = fakeValuesService.regexify("[1-9]{20}");
		yyValue = fakeValuesService.regexify("[1-9]{20}");
		mmValue = fakeValuesService.regexify("[1-9]{20}");
		ddValue = fakeValuesService.regexify("[1-9]{20}");

		driver.findElement(By.xpath("//*[@id=\"i1Syori\"]")).sendKeys(ｼﾖﾘValue);
		driver.findElement(By.xpath("//*[@id=\"i1Type\"]")).sendKeys(ﾀｲﾌﾟValue);
		driver.findElement(By.xpath("//*[@id=\"i1Kaisya\"]")).sendKeys(ｶｲｼﾔValue);
		driver.findElement(By.xpath("//*[@id=\"i1Syaban\"]")).sendKeys(ｼﾔﾊﾞﾝValue);

		driver.findElement(By.xpath("//*[@id=\"i1Yy\"]")).sendKeys(yyValue);
		driver.findElement(By.xpath("//*[@id=\"i1Mm\"]")).sendKeys(mmValue);
		driver.findElement(By.xpath("//*[@id=\"i1Dd\"]")).sendKeys(ddValue);

		driver.findElement(By.xpath("//*[@id=\"i1Furikomi\"]")).sendKeys(fakeValuesService.regexify("[1-9]{12}"));
		String i1TKub = fakeValuesService.regexify("[1-9]{2}");
		driver.findElement(By.xpath("//*[@id=\"i1TKub\"]")).sendKeys(i1TKub);
		String i1UKub = fakeValuesService.regexify("[1-9]{2}");
		driver.findElement(By.xpath("//*[@id=\"i1UKub\"]")).sendKeys(i1UKub);

		execute();

//		driver.findElement(By.xpath("//*[@id=\"button_2\"]")).click();
//		pf9("0", "A0600");i1Furikomi
	}

	public void pf6() {
		pf9("0", "A0601");
		driver.findElement(By.xpath("//*[@id=\"button_2\"]")).click();
		// open pf3
		driver.findElement(By.xpath("//*[@id=\"button_6\"]")).click();

		String ｶｲｼﾔValue = fakeValuesService.regexify("[1-9]{20}");
		String ｼﾔﾊﾞﾝﾟValue = fakeValuesService.regexify("[1-9]{20}");

		String yyValue = fakeValuesService.regexify("[1-9]{20}");
		String mmValue = fakeValuesService.regexify("[1-9]{20}");
		String ddValue = fakeValuesService.regexify("[1-9]{20}");
		String ｼﾒｲValue = fakeValuesService.regexify("[1-9]{20}");

		String ｷﾕｳﾖ_ｸﾌﾞﾝValue = fakeValuesService.regexify("[1-9]{20}");
		String ﾋｶｾﾞｲ_ｹﾞﾝﾄﾞValue = fakeValuesService.regexify("[1-9]{20}");
		String ｾﾝﾀｸ_ｸﾌﾞﾝValue = fakeValuesService.regexify("[1-9]{20}");

		String ｼﾞﾕｳｼﾖValue = fakeValuesService.regexify("[1-9]{20}");
		String ｼﾞﾕｳｼﾖValue2 = fakeValuesService.regexify("[1-9]{20}");

		String ｼﾕﾂｺｳ_ｸﾌﾞﾝValue = fakeValuesService.regexify("[1-9]{20}");
		String ｼﾕﾂｺｳ_ｻｷValue = fakeValuesService.regexify("[1-9]{20}");
		String ｺｳｻﾞ_ｸﾌﾞﾝValue = fakeValuesService.regexify("[1-9]{20}");
		String ｺｳｻﾞ_ﾊﾞﾝｺﾞｳValue = fakeValuesService.regexify("[1-9]{20}");
		String ﾌﾘｺﾐ_ｷﾞﾝｺｳValue = fakeValuesService.regexify("[1-9]{20}");
		String ｳｹﾄﾘ_ﾒｲｷﾞValue = fakeValuesService.regexify("[1-9]{20}");
		String ｻﾞﾝﾀﾞｶ_ｼﾕｳｾｲValue = fakeValuesService.regexify("[1-9]{20}");

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

	public void pf7() {
		// open pf9
		driver.findElement(By.xpath("//*[@id=\"button_7\"]")).click();

		String FROM = fakeValuesService.regexify("[1-9]{20}");
		String TO = fakeValuesService.regexify("[1-9]{20}");

		driver.findElement(By.xpath("//*[@id=\"i1Log1\"]")).sendKeys(FROM);
		driver.findElement(By.xpath("//*[@id=\"i1Log2\"]")).sendKeys(TO);
		execute();
	}

	public void pf8() {
		// open pf9
		driver.findElement(By.xpath("//*[@id=\"button_8\"]")).click();

		String i1Sw1 = fakeValuesService.regexify("[1-9]{2}");
		String i1Sw2 = fakeValuesService.regexify("[1-9]{2}");
		String i1Sw3 = fakeValuesService.regexify("[1-9]{2}");
		String i1Sw4 = fakeValuesService.regexify("[1-9]{2}");
		String i1Sw5 = fakeValuesService.regexify("[1-9]{2}");
		String i1Sw6 = fakeValuesService.regexify("[1-9]{2}");
		String i1Sw7 = fakeValuesService.regexify("[1-9]{2}");
		String i1Sw8 = fakeValuesService.regexify("[1-9]{2}");
		String i1Sw9 = fakeValuesService.regexify("[1-9]{2}");
		String i1Sw10 = fakeValuesService.regexify("[1-9]{2}");

		driver.findElement(By.xpath("//*[@id=\"i1Sw1\"]")).sendKeys(i1Sw1);
		driver.findElement(By.xpath("//*[@id=\"i1Sw2\"]")).sendKeys(i1Sw2);
		driver.findElement(By.xpath("//*[@id=\"i1Sw3\"]")).sendKeys(i1Sw3);
		driver.findElement(By.xpath("//*[@id=\"i1Sw4\"]")).sendKeys(i1Sw4);
		driver.findElement(By.xpath("//*[@id=\"i1Sw5\"]")).sendKeys(i1Sw5);
		driver.findElement(By.xpath("//*[@id=\"i1Sw6\"]")).sendKeys(i1Sw6);
		driver.findElement(By.xpath("//*[@id=\"i1Sw7\"]")).sendKeys(i1Sw7);
		driver.findElement(By.xpath("//*[@id=\"i1Sw8\"]")).sendKeys(i1Sw8);
		driver.findElement(By.xpath("//*[@id=\"i1Sw9\"]")).sendKeys(i1Sw9);
		driver.findElement(By.xpath("//*[@id=\"i1Sw10\"]")).sendKeys(i1Sw10);

		execute();
	}

	public void pf9(String ｶｲｼﾔValue, String ｼﾔｲﾝ_ﾊﾞﾝｺﾞｳValue) {
		// open pf9
		driver.findElement(By.xpath("//*[@id=\"button_9\"]")).click();

		driver.findElement(By.xpath("//*[@id=\"i1Kaisya\"]")).sendKeys(ｶｲｼﾔValue);
		driver.findElement(By.xpath("//*[@id=\"i1Syaban\"]")).sendKeys(ｼﾔｲﾝ_ﾊﾞﾝｺﾞｳValue);
		execute();
	}

	public void pf43() {
		// open pf9
		driver.findElement(By.xpath("//*[@id=\"button_10\"]")).click();
		String ｶｲｼﾔValue = fakeValuesService.regexify("[1-9]{1}");
		String ｼﾔｲﾝ_ﾊﾞﾝｺﾞｳValue = fakeValuesService.regexify("[1-9]{5}");
		driver.findElement(By.xpath("//*[@id=\"i1Kaisya\"]")).sendKeys(ｶｲｼﾔValue);
		driver.findElement(By.xpath("//*[@id=\"i1Syaban\"]")).sendKeys(ｼﾔｲﾝ_ﾊﾞﾝｺﾞｳValue);
		execute();
	}
}
