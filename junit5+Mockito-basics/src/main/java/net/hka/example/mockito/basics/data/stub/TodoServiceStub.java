package net.hka.example.mockito.basics.data.stub;

import java.util.Arrays;
import java.util.List;

import net.hka.example.mockito.basics.data.api.TodoService;

/*
 * Creating a Stub class for unimplemented yet TodoService
 */
public class TodoServiceStub implements TodoService {
	public List<String> retrieveTodos(String user) {
		return Arrays.asList("Learn Spring MVC", "Learn Spring",
				"Learn to Dance");
	}
	
	public void deleteTodo(String todo) {
		throw new UnsupportedOperationException();
	}
}
