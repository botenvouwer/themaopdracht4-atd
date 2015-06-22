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

public class Klant_reperatie_aanvragen {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8080/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testKlant_reperatie_aanvragen() throws Exception {
		selenium.open("/klant");
		selenium.click("link=Reparatie");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=hier");
		selenium.waitForPageToLoad("30000");
		selenium.select("id=type", "label=Reparatie");
		selenium.select("name=car", "label=fg-67-ty (Audi a3 2012)");
		selenium.type("name=note", "De voorbanden zijn lek gestoken.");
		selenium.click("name=send");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
