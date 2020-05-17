/*
 * ## What You Will Learn during this Step:
	- Understand what a Spy does?
	- Creating a spy with Mockito?
	- Overriding specific methods in a spy?

 */
package net.hka.example.mockito.basics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
//import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("When running The Test for ArrayList Class using Spy")
@ExtendWith(MockitoExtension.class)
class ArrayListTest_UsingSpy {
	
	@Spy
	List<String> listSpy = new ArrayList<>();
	
	@Test
	@DisplayName("When creating a Spy on ArrayList to test the add method")
	void testListAddWithSpy() {
		@SuppressWarnings("unchecked")
		List<String> listSpy = spy(ArrayList.class);
		
		// after Spying you can add directly to list, 
		// and you be able to save much more lines of code using spy
		listSpy.add("Ranga");
		listSpy.add("in28Minutes");

		verify(listSpy).add("Ranga");
		verify(listSpy).add("in28Minutes");

		assertEquals(2, listSpy.size(), () -> "Should return 2 but it returns " + listSpy.size());
		assertEquals("Ranga", listSpy.get(0), () -> "Should return Ranga but it returns " + listSpy.get(0));
	}

	@Test
	@DisplayName("When creating a Spy on ArrayList to test the add method and Override the size method")
	void testListAddWithSpy_overridingSpecificMethods() {
//		@SuppressWarnings("unchecked")
//		List<String> listSpy = spy(ArrayList.class);
		
		listSpy.add("Ranga");
		listSpy.add("in28Minutes");

		// you can still override Size method for ArrayList, while using Spy
		// but you be able to save much more lines of code using spy
		//////stub(listSpy.size()).toReturn(-1);
		when(listSpy.size()).thenReturn(-1);

		assertEquals(-1, listSpy.size(), () -> "Should return -1 but it returns " + listSpy.size());
		assertEquals("Ranga", listSpy.get(0), () -> "Should return Ranga but it returns " + listSpy.get(0));

		// @Spy Annotation
	}
}
