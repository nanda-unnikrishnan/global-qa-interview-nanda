package com.saucedemo.qa.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.saucedemo.qa.base.AppConfig;
import com.saucedemo.qa.pages.LoginPage;
import com.saucedemo.qa.pages.ProductsPage;
import com.saucedemo.qa.pages.YourCartPage;
import com.saucedemo.qa.pages.YourInfoPage;

public class YourCartPageTest extends TestBase {

	LoginPage loginPage;
	ProductsPage productsPage;
	YourCartPage yourCartPage;
	YourInfoPage yourInfoPage;

	@Override
	@BeforeTest
	public void setUp() {
		super.setUp();
		loginPage = new LoginPage(getDriver());
		productsPage = loginPage.login(AppConfig.getConfigValue("username"), AppConfig.getConfigValue("password"));
		productsPage.sortBy("Price (high to low)");
		productsPage.addToCart();
		yourCartPage = productsPage.goToCart();
	}

	@Test
	public void testCheckout() {
		yourInfoPage = yourCartPage.checkout();
		assertEquals(yourInfoPage.getTitle(), "CHECKOUT: YOUR INFORMATION");
	}

	@Override
	@AfterTest
	public void tearDown() {
		super.tearDown();
	}

}
