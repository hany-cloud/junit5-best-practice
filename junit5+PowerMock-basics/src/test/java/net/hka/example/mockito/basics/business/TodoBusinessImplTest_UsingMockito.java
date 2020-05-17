/*
 * ## What You Will Learn during this Step:
	- Introduction to BDD (Behavior Driven Development)
	- Given When Then
	- BDD Mockito Syntax
	
	
	- How to verify calls on a mock?  >> deleteTodo method in TodoService
	  - Verify how many times a method is called.	  	
			verify(todoService).deleteTodo("Learn to Dance");
			verify(todoService, Mockito.never()).deleteTodo("Learn Spring MVC");
 */
package net.hka.example.mockito.basics.business;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import net.hka.example.mockito.basics.data.api.TodoService;

@DisplayName("When running The Test for TodoBusinessImpl Class using Mockito")
class TodoBusinessImplTest_UsingMockito {
	
	@Test
	@DisplayName("When Testing retrieveTodosRelatedToSpring method")
	void testRetrieveTodosRelatedToSpringUsingMockito() {		
		TodoService todoService = mock(TodoService.class);
		
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");		
		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");
		
		//assertEquals(2, todos.size());
		assertThat(todos.size(), is(2));
	}

	@Test
	@DisplayName("When Testing retrieveTodosRelatedToSpring method by BDD syntax")
	void testRetrieveTodosRelatedToSpringUsingMockito_ByBDDsyntax() {
		TodoService todoService = mock(TodoService.class);
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		//given
		given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);

		//when
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");

		//then
		assertThat(todos.size(), is(2));
		//assertEquals(2, todos.size());
	}
	
	@Test
	@DisplayName("When Testing deleteTodosNotRelatedToSpring method and Veryfing Calls on mocked Interface")
	void testdeleteTodosNotRelatedToSpring_VeryfingCalls() {
		TodoService todoService = mock(TodoService.class);
		
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

		
		verify(todoService).deleteTodo("Learn to Dance");

		verify(todoService, Mockito.never()).deleteTodo("Learn Spring MVC");

		verify(todoService, Mockito.never()).deleteTodo("Learn Spring");

		verify(todoService, Mockito.times(1)).deleteTodo("Learn to Dance");
		// atLeastOnce, atLeast

	}
		
	@Test
	@DisplayName("When Testing deleteTodosNotRelatedToSpring method and Veryfing Calls and Capture Argument on mocked Interface")
    void testdeleteTodosNotRelatedToSpring_VeryfingCalls_AndCaptureArgument() {
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        TodoService todoService = mock(TodoService.class);

        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
        
        verify(todoService).deleteTodo(stringArgumentCaptor.capture());

        assertEquals("Learn to Dance", stringArgumentCaptor.getValue(), () -> "Should return \"Learn to Dance\" but it returns " + stringArgumentCaptor.getValue());
    }
}
