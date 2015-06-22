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

public class Baas_voorraadbeheer {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8080/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testBaas_voorraadbeheer() throws Exception {
		selenium.open("/cms");
		selenium.click("link=Voorraad");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=button");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=voorraad", "23");
		selenium.click("name=send");
		selenium.waitForPageToLoad("30000");
		selenium.click("//button[@onclick=\"location.href='/cms/voorraad?verwijderen=true&id=552'\"]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Artikel Toevoegen");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=artikel", "Auto band (new)");
		selenium.type("id=prijs", "25.95");
		selenium.type("id=voorraad", "12");
		selenium.click("name=send");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
