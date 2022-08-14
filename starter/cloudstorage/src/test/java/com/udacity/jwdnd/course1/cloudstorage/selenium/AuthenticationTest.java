package com.udacity.jwdnd.course1.cloudstorage.selenium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationTest extends SeleniumBase {

	/**
	 * Write a test that verifies that an unauthorized user can only access the login and signup pages.
	 */
	@Test
	public void givenUnauthorizedUser_WhenAccess_ShouldOnlyAccessLoginAndSignup() {
		webDriver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", webDriver.getTitle());

		webDriver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", webDriver.getTitle());

		webDriver.get("http://localhost:" + this.port + "/");
		Assertions.assertEquals("Login", webDriver.getTitle());

		webDriver.get("http://localhost:" + this.port + "/randomurl");
		Assertions.assertEquals("Login", webDriver.getTitle());
	}

	/**
	 * Write a test that signs up a new user, logs in, verifies that the home page is accessible, logs out,
	 * and verifies that the home page is no longer accessible.
	 */
	@Test
	public void givenAuthorizedUser_WhenSignupLogin_ShouldBeAccessAll() {
		webDriver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", webDriver.getTitle());

		new SignupPage(webDriver) {{
			setFirstName("Thao");
			setLastName("Tran");
			setUserName("thienthao");
			setPassword("thienthao");
		}}.signup();

		webDriver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", webDriver.getTitle());

		new LoginPage(webDriver) {{
			setUserName("thienthao");
			setPassword("thienthao");
		}}.login();

		new HomePage(webDriver).logout();

		webDriver.get("http://localhost:" + this.port + "/");
		Assertions.assertEquals("Login", webDriver.getTitle());
	}
}
