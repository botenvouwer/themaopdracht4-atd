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

public class Klant_registreren {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8080/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testKlant_registreren() throws Exception {
		selenium.open("/");
		selenium.click("link=Registreren");
		selenium.waitForPageToLoad("30000");
		selenium.type("name=name", "Jan klaasen");
		selenium.type("name=email", "laurens_sieval@hotmail.com");
		selenium.type("name=password", "Test");
		selenium.type("name=repeat", "Test");
		selenium.type("name=adress", "Zate 32");
		selenium.type("name=zipcode", "8345BC");
		selenium.type("name=place", "Urk");
		selenium.click("name=send");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
