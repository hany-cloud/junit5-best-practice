package net.hka.example.mockito.basics;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Every.everyItem;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
//import org.hamcrest.CoreMatchers;
//import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;

@DisplayName("When running The Test on List interface, String, and Array using Hamcrest Matchers")
class HamcrestMatcherTest {

	@Test
	@DisplayName("When Testing List interface, String, and Array using Hamcrest Matchers")
	void testBasicHamcrestMatchers() {
		List<Integer> scores = Arrays.asList(99, 100, 101, 105);
		assertThat(scores, hasSize(4));
//		assertThat(scores, hasItems(100, 101));
		assertThat(scores, everyItem(greaterThan(90)));
		assertThat(scores, everyItem(lessThan(200)));

		// String
//		assertThat("Hany", not(isEmptyString()));
//		assertThat(null, isEmptyOrNullString());
		
		assertTrue("".isEmpty());
		String nullString = null;
		assertTrue(nullString == null || nullString.isEmpty());
		

		// Array
		Integer[] marks = { 1, 2, 3 };
		assertThat(marks, arrayWithSize(3));
		assertThat(marks, arrayContainingInAnyOrder(2, 3, 1));

	}
}
