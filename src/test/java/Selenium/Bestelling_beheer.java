package com.example.tests;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringUtils.join;

public class Bestelling_beheer {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8080/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testBestelling_beheer() throws Exception {
		selenium.open("/cms");
		selenium.click("link=Bestellingen");
		selenium.waitForPageToLoad("30000");
		selenium.click("//button[@onclick=\"location.href='/cms/bestellingen?status=3&id=502'\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Nieuwe bestelling");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=price", "15");
		selenium.click("name=send");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=button");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Toon Geannuleerde");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Toon In Bestelling");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Toon Geleverde");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
