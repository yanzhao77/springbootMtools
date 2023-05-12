package com.softroad.seleniumdemo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.softroad.seleniumdemo.selenium.mmc.app.TestApp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SeleniumDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SeleniumDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		TestApp app = new TestApp();
		app.init(driver);
		app.body();

		driver.close();
	}
}
