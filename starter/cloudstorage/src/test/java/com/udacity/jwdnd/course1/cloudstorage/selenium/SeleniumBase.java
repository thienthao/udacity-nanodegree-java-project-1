package com.udacity.jwdnd.course1.cloudstorage.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SeleniumBase {

	protected WebDriver webDriver;
	@LocalServerPort
	protected int port;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		webDriver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		webDriver.quit();
	}

	protected HomePage signupAndLogin(String username, String password) {
		webDriver.get("http://localhost:" + this.port + "/signup");

		new SignupPage(webDriver) {{
			setFirstName("Thao");
			setLastName("Tran");
			setUserName(username);
			setPassword(password);
		}}.signup();

		webDriver.get("http://localhost:" + this.port + "/login");

		new LoginPage(webDriver) {{
			setUserName(username);
			setPassword(password);
		}}.login();

		return new HomePage(webDriver);
	}
}
