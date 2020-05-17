package net.hka.example.mockito.basics.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.hka.example.mockito.basics.data.api.TodoService;
import net.hka.example.mockito.basics.data.stub.TodoServiceStub;

@DisplayName("When running The Test for TodoBusinessImpl Class using Stub Tecnique")
class TodoBusinessImplTest_UsingStubTecnique {

	@Test
	@DisplayName("When Testing retrieveTodosRelatedToSpring method")
	void testRetrieveTodosRelatedToSpringUsingAStub() {
		TodoService todoService = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Ranga");
		assertEquals(2, todos.size(), () -> "Should return list of size 2 but it returns " + todos.size());
	}

}
