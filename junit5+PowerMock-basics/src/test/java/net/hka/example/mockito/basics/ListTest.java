/*
 * ## What You Will Learn during this Step:
	- A few mockito examples mocking List class
	- Multiple return values
	- Introduction to Argument Matchers
 */

package net.hka.example.mockito.basics;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("When running The Test after Mocking the Java List interface")
class ListTest {

	@Test
	@DisplayName("When Testing size method")
	void testListSizeByMock() {
		List<?> list = mock(List.class);
		Mockito.when(list.size()).thenReturn(10);
		assertEquals(10, list.size(), () -> "Should return 10 but it returns " + list.size());
	}

	@Test
	@DisplayName("When Testing size method and Return Multiple Values")
	void testListSizeWithMultipleReturnValues() {
		List<?> list = mock(List.class);
		Mockito.when(list.size()).thenReturn(10).thenReturn(20);
		assertEquals(10, list.size(), () -> "Should return 10"); // First Call
		assertEquals(20, list.size(), () -> "Should return 20"); // Second Call
	}

	@Test
	@DisplayName("When Testing get method")
	void testListGet() {
		@SuppressWarnings("unchecked")
		List<String> list = mock(List.class);
		
		Mockito.when(list.get(0)).thenReturn("in28Minutes");
		Mockito.when(list.get(1)).thenReturn("in60Minutes");
		
		assertEquals("in28Minutes", list.get(0), () -> "Should return in28Minutes but it returns " + list.get(0));
		assertEquals("in60Minutes", list.get(1), () -> "Should return in28Minutes but it returns " + list.get(1));
		assertNull(list.get(2), () -> "Should return null but it returns " + list.get(2));
	}

	@Test
	@DisplayName("When Testing get method using the [anyInt] argument matcher in mockito")
	void letsMockListGetWithAny() {
		@SuppressWarnings("unchecked")
		List<String> list = mock(List.class);
		
		// using argument matchers (anyInt) 
		Mockito.when(list.get(Mockito.anyInt())).thenReturn("in28Minutes");
		//Mockito.when(list.get(Mockito.anyInt())).thenReturn("in28Minutes").thenReturn("in60Minutes");
		
		
		// If you are using argument matchers, all arguments
		// have to be provided by matchers.
		assertEquals("in28Minutes", list.get(0), () -> "Should return in28Minutes but it returns " + list.get(0));
		
		assertEquals("in28Minutes", list.get(1), () -> "Should return in28Minutes but it returns " + list.get(1));
		//assertEquals("in60Minutes", list.get(1), () -> "Should return in28Minutes but it returns " + list.get(1));
		
		// using assertThat from Hamcrest library
		org.hamcrest.MatcherAssert.assertThat("in28Minutes", is(list.get(0)));
	}
	
	@Test
	@DisplayName("When Testing the add method for List interface")
	void testListAddWithNoSpy() {
		@SuppressWarnings("unchecked")
		List<String> list = mock(List.class);
		
		list.add("Ranga");
		list.add("in28Minutes");
		verify(list).add("Ranga");
		verify(list).add("in28Minutes");
		
		Mockito.when(list.add("Ranga")).thenReturn(true);
		Mockito.when(list.add("in28Minutes")).thenReturn(true);
		
		Mockito.when(list.size()).thenReturn(2);
		Mockito.when(list.get(0)).thenReturn("Ranga");
	
		assertEquals(2, list.size(), () -> "Should return 2 but it returns " + list.size());
		assertEquals("Ranga", list.get(0), () -> "Should return Ranga but it returns " + list.get(0));
	}
	
	
//	@Test
//	void bddAliases_UsingGivenWillReturn() {
//		List<String> list = mock(List.class);
//
//		//given
//		given(list.get(Mockito.anyInt())).willReturn("in28Minutes");
//
//		//then
//		assertThat("in28Minutes", is(list.get(0)));
//		assertThat("in28Minutes", is(list.get(0)));
//	}

}
