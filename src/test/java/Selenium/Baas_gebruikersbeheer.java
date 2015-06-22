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

public class Baas_gebruikersbeheer {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8080/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testBaas_gebruikersbeheer() throws Exception {
		selenium.open("/cms");
		selenium.click("link=Gebruikers");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Gebruiker aanmaken");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=name", "Testpersoon");
		selenium.type("id=email", "test@test.com");
		selenium.type("id=password", "admin");
		selenium.type("id=repeat", "admin");
		selenium.select("id=role", "label=CUSTOMER");
		selenium.type("id=adress", "straatnaam 2");
		selenium.type("id=zipcode", "1234AB");
		selenium.type("id=place", "Zeewolde");
		selenium.click("name=send");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
