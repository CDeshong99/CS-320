package org.snhu.cs320.contact;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ContactTest {

	@Test
	void testSuccessdulCreation() throws Exception {
		Contact contact = new Contact("1", "First", "Last", "1112223333", "1234 Alphabet Lane");
		assertThat(contact)
			.hasFieldOrPropertyWithValue("id", "1")
			.hasFieldOrPropertyWithValue("firstName", "First")
			.hasFieldOrPropertyWithValue("lastName", "Last")
			.hasFieldOrPropertyWithValue("phone", "1112223333")
			.hasFieldOrPropertyWithValue("address", "1234 Alphabet Lane");

	}
	
	@Test
	void testSuccessfulSetters() throws Exception {
		Contact contact = new Contact("1", "First", "Last", "1112223333", "1234 Alphabet Lane");
		contact.setFirstName("Bob");
		contact.setLastName("Matt");
		contact.setPhone("2223334444");
		contact.setAddress("1234 Alphabet Drive");
		assertThat(contact)
			.hasFieldOrPropertyWithValue("id", "1")
			.hasFieldOrPropertyWithValue("firstName", "Bob")
			.hasFieldOrPropertyWithValue("lastName", "Matt")
			.hasFieldOrPropertyWithValue("phone", "2223334444")
			.hasFieldOrPropertyWithValue("address", "1234 Alphabet Drive");
	}
	
	@CsvSource({
		"' ',First, Last,1112223333,1234 Alphabet Lane, id must not be blank", // Blank ID
		",First,Last,1112223333,1234 Alphabet Lane, id must not be null", //Null ID
		"12345678901,First,Last,1112223333,1234 Alphabet Lane, id must be at least 1 and no greater than 10 characters in length", // Too Long ID
		"12345,' ',Last,1112223333,1234 Alphabet Lane, firstName must not be blank", // Blank First Name
		"12345,,Last,1112223333,1234 Alphabet Lane,firstName must not be null", // Null First Name
		"12345,FirstFirstF,Last,1112223333,1234 Alphabet Lane,firstName must be at least 1 and no greater than 10 characters in length", // Too Long First Name
		"12345,First,Last,' ',1234 Alphabet Lane,phone must not be blank", // Blank Phone
		"12345,First,Last,,1234 Alphabet Lane,phone must not be null", // Null Phone
		"12345,First,Last,11122233334,1234 Alphabet Lane,phone must be at least 10 and no greater than 10 characters in length", // Too Long Phone
		"12345,First,Last,111222333A,1234 Alphabet Lane,phone must only contain digits", // Phone with Letters
		"12345,First,Last,111222-333,1234 Alphabet Lane,phone must only contain digits", // Phone with Punctuation
		"12345,First,Last,111222 333,1234 Alphabet Lane,phone must only contain digits", // Phone with Spaces
	})
	@ParameterizedTest
	void testFailedCreation(String id, String firstName, String lastName, String phone, String address, String message) {
		assertThatThrownBy(() -> new Contact(id, firstName, lastName, phone, address))
			.isNotNull()
			.hasMessage(message);
	}
	
	@CsvSource({
		",firstName must not be null",
		"'  ',firstName must not be blank",
		"FirstNameFirstName,firstName must be at least 1 and no greater than 10 characters in length"
	})
	@ParameterizedTest
	void testSettingFirstName(String firstName, String message) throws Exception {
		Contact contact = new Contact("1", "First", "Last", "1112223333", "1234 Alphabet Lane");
		assertThatThrownBy(() -> contact.setFirstName(firstName))
			.isNotNull()
			.hasMessage(message);
	}
	
}
