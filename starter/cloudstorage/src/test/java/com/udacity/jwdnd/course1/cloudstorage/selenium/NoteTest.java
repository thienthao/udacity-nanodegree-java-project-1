package com.udacity.jwdnd.course1.cloudstorage.selenium;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoteTest extends SeleniumBase {

	/**
	 * Test that creates a note, and verifies it is displayed.
	 */
	@Test
	public void whenCreatedNote_ShouldDisplayOnTable() {
		HomePage homePage = signupAndLogin("testCreateNote", "testPassword");
		String title = "Thao's note";
		String description = "Day la note cua Thao oke chua";
		create(title, description, homePage);
		homePage.toNoteTab();
		homePage = new HomePage(webDriver);
		Note note = homePage.getNoteFromTable();
		Assertions.assertEquals(title, note.getTitle());
		Assertions.assertEquals(description, note.getDescription());
		homePage.deleteNote();
		new ResultPage(webDriver).backFromSuccess();
		homePage.logout();
	}

	/**
	 * Test that edits an existing note and verifies that the changes are displayed.
	 */
	@Test
	public void whenEditedNote_ShouldDisplayOnTable() {
		HomePage homePage = signupAndLogin("testEidtNote", "testPassword");
		String title = "Thao's note";
		String description = "Day la note cua Thao oke chua";
		create(title, description, homePage);
		homePage.toNoteTab();
		homePage = new HomePage(webDriver);
		homePage.openEditNoteModal();
		String editedTitle = "Edited note";
		homePage.editNoteTitle("Edited note");
		String editedDescription = "Edited description";
		homePage.editNoteDescription(editedDescription);
		homePage.saveEditNote();
		new ResultPage(webDriver).backFromSuccess();
		homePage.toNoteTab();
		Note note = homePage.getNoteFromTable();
		Assertions.assertEquals(editedTitle, note.getTitle());
		Assertions.assertEquals(editedDescription, note.getDescription());
		homePage.deleteNote();
	}

	/**
	 * Test that edits an existing note and verifies that the changes are displayed.
	 */
	@Test
	public void whenDeleteNote_ShouldHaveNoNote() {
		HomePage homePage = signupAndLogin("testDeleteNote", "password");
		String title = "Thao's note";
		String description = "Day la note cua Thao oke chua";
		create(title, description, homePage);
		homePage.toNoteTab();
		homePage = new HomePage(webDriver);
		Assertions.assertFalse(homePage.noNotes(webDriver));
		delete(homePage);
		Assertions.assertTrue(homePage.noNotes(webDriver));
	}

	private void create(String title, String description, HomePage homePage) {
		homePage.toNoteTab();
		homePage.openAddNoteModal();
		homePage.setNoteTitle(title);
		homePage.setNoteDescription(description);
		homePage.addNote();
		ResultPage resultPage = new ResultPage(webDriver);
		resultPage.backFromSuccess();
		homePage.toNoteTab();
	}

	private void delete(HomePage homePage) {
		homePage.deleteNote();
		ResultPage resultPage = new ResultPage(webDriver);
		resultPage.backFromSuccess();
	}
}
