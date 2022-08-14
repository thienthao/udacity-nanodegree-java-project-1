package com.udacity.jwdnd.course1.cloudstorage.selenium;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CredentialTest extends SeleniumBase {

	/**
	 * Test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed
	 * password is encrypted.
	 */
	@Test
	public void whenCreatedCredential_ShouldDisplayOnTable() {
		HomePage homePage = signupAndLogin("testCreateCredential", "password");
		create("http://testurl/", "thienthaocredential", "thienthaocredential", homePage);
		Credential credential = homePage.getCredentialFromTable();
		Assertions.assertEquals("http://testurl/", credential.getUrl());
		Assertions.assertEquals("thienthaocredential", credential.getUsername());
		Assertions.assertNotEquals("thienthaocredential", credential.getPassword());
	}

	/**
	 * Test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the
	 * credentials, and verifies that the changes are displayed.
	 */
	@Test
	public void whenEditCredential_ShouldDisplayOnTable() {
		HomePage homePage = signupAndLogin("testEditCredential", "password");
		create("http://testurl/", "thienthaocredential", "thienthaocredential", homePage);
		Credential original = homePage.getCredentialFromTable();
		homePage.openEditCredentialModal();
		String newUrl = "http://newtesturl/";
		String newCredentialUsername = "newcredential";
		String newPassword = "newcredential";
		setEditCredentialFields(newUrl, newCredentialUsername, newPassword, homePage);
		homePage.editCredential();
		new ResultPage(webDriver).backFromSuccess();
		homePage.toCredentialTab();
		Credential editedCredential = homePage.getCredentialFromTable();
		Assertions.assertEquals(newUrl, editedCredential.getUrl());
		Assertions.assertEquals(newCredentialUsername, editedCredential.getUsername());
		Assertions.assertNotEquals(newPassword, editedCredential.getPassword());
		Assertions.assertNotEquals(original.getPassword(), editedCredential.getPassword());
	}

	/**
	 * Test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.
	 */
	@Test
	public void whenDeleteCredential_ShouldNotDisplayOnTable() {
		HomePage homePage = signupAndLogin("testDeleteCredential", "password");
		create("http://testurl/", "thienthaocredential", "thienthaocredential", homePage);
		Assertions.assertFalse(homePage.noCredentials(webDriver));
		homePage.deleteCredential();
		new ResultPage(webDriver).backFromSuccess();
		homePage.toCredentialTab();
		Assertions.assertTrue(homePage.noCredentials(webDriver));
	}

	private void create(String url, String username, String password, HomePage homePage) {
		homePage.toCredentialTab();
		homePage.openAddCredentialModal();
		setCredentialFields(url, username, password, homePage);
		homePage.addCredential();
		new ResultPage(webDriver).backFromSuccess();
		homePage.toCredentialTab();
		homePage.toCredentialTab();
	}

	private void setCredentialFields(String url, String username, String password, HomePage homePage) {
		homePage.setCredentialUrl(url);
		homePage.setCredentialUsername(username);
		homePage.setCredentialPassword(password);
	}

	private void setEditCredentialFields(String url, String username, String password, HomePage homePage) {
		homePage.setEditCredentialUrl(url);
		homePage.setEditCredentialUsername(username);
		homePage.setEditCredentialPassword(password);
	}

}
