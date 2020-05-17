/*
 * ## What You Will Learn during this Step:
	- Mockito Annotations
	  - @Mock
	  - @InjectMocks
	  - @RunWith(MockitoJUnitRunner.class)
	  - @Captor
 */
package net.hka.example.mockito.basics.business;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.api.TestInfo;
//import org.junit.jupiter.api.TestReporter;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.mockito.Captor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnit;
//import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

import net.hka.example.mockito.basics.data.api.TodoService;

//@RunWith(MockitoJUnitRunner.class) // only with Junit4
@ExtendWith(MockitoExtension.class)
@DisplayName("When running The Test for TodoBusinessImpl Class using Mockito and Inject Mocks")
class TodoBusinessImplTest_UsingInjectMocks {
	// only junit4
	//@Rule
	//MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock 
	TodoService todoService;

	@InjectMocks 
	TodoBusinessImpl todoBusinessImpl;

	@Captor 
	ArgumentCaptor<String> stringArgumentCaptor;

	
	/*
	 * @BeforeEach void init() { this.todoService = mock(TodoService.class);;
	 * this.todoBusinessImpl = new TodoBusinessImpl(todoService);
	 * this.stringArgumentCaptor = ArgumentCaptor.forClass(String.class); }
	 */

	
	@Test
	@DisplayName("When Testing retrieveTodosRelatedToSpring method")
	void testRetrieveTodosRelatedToSpringUsingMockito() {		
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");		
		
		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
		
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");
		
		//assertEquals(2, todos.size());
		assertThat(todos.size(), is(2));
	}

	@Test
	@DisplayName("When Testing retrieveTodosRelatedToSpring method by BDD syntax")
	void testRetrieveTodosRelatedToSpringUsingMockito_ByBDDsyntax() {
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
		List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
		when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

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
        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        
        when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
        
        verify(todoService).deleteTodo(stringArgumentCaptor.capture());

        assertEquals("Learn to Dance", stringArgumentCaptor.getValue(), () -> "Should return \"Learn to Dance\" but it returns " + stringArgumentCaptor.getValue());
    }


}
