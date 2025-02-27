package org.snhu.cs320.contact;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
	
	@BeforeEach
	void init() {
		ContactService.getInstance().database.clear();
	}
	
	@Test
	void testGetInstance() {
		assertThat(ContactService.getInstance()).isNotNull();
	}
	
	@Test
	void testAdd() throws Exception {
		Contact contact = new Contact("12345", "First", "Last", "1112223333", "1234 Alphabet Lane");
		assertThat(ContactService.getInstance().add(contact)).isTrue();
		assertThat(ContactService.getInstance().database)
			.containsEntry("12345", contact);
	}
	
	@Test
	void testDelete() throws Exception {
		Contact contact = new Contact("12345", "First", "Last", "1112223333", "1234 Alphabet Lane");
		assertThat(ContactService.getInstance().add(contact)).isTrue();
		assertThat(ContactService.getInstance().delete("12345")).isTrue();
		assertThat(ContactService.getInstance().database).doesNotContainEntry("12345", contact);
	}
	
	@Test
	void testUpdate() throws Exception {
		Contact contact = new Contact("12345", "First", "Last", "1112223333", "1234 Alphabet Lane");
		assertThat(ContactService.getInstance().add(contact)).isTrue();
		
		Contact updated = new Contact("99999", "Bob", "Matt", "2223334444", "1234 Alphabet Drive");
		assertThat(ContactService.getInstance().update("12345", updated)).isTrue();
		assertThat(ContactService.getInstance().database)
			.extracting("12345")
			.hasFieldOrPropertyWithValue("firstName", "Bob")
			.hasFieldOrPropertyWithValue("lastName", "Matt")
			.hasFieldOrPropertyWithValue("phone", "2223334444")
			.hasFieldOrPropertyWithValue("address", "1234 Alphabet Drive");
	}
}
