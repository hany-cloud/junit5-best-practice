## Useful Snippets and References
How to verify calls on a mock?
```
verify(todoService).deleteTodo("Learn to Dance");                    // Verify a method is called.     
verify(todoService, Mockito.never()).deleteTodo("Learn Spring MVC"); // Verify a method is never called. 
verify(todoService, Mockito.times(1)).deleteTodo("Learn to Dance");  // Verify how many times a method is called.
```

How to capture an argument which is passed to a mock?
```
	@Test
    public void captureArgument() {
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        TodoService todoService = mock(TodoService.class);

        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        Mockito.when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
        Mockito.verify(todoService).deleteTodo(argumentCaptor.capture());

        assertEquals("Learn to Dance", argumentCaptor.getValue());
    }
```

Hamcrest Matchers
```      
        // Most List assert operations 
        List<Integer> scores = Arrays.asList(99, 100, 101, 105);
        assertThat(scores.size(), is(4));
        assertThat(scores, hasSize(4));
        assertThat(scores, hasItems(100, 101));
        assertThat(scores, everyItem(greaterThan(90)));
        assertThat(scores, everyItem(lessThan(200)));
        //////////////////////////////////////////////////////////
        // List => more about is
        List<String> list = mock(List.class);

		//given
		given(list.get(Mockito.anyInt())).willReturn("in28Minutes");

		//then
		assertThat("in28Minutes", is(list.get(0)));
		assertThat("in28Minutes", is(list.get(1)));        
        //////////////////////////////////////////////////////////
        // String
        assertThat("", isEmptyString());
		assertThat(null, isEmptyOrNullString());
        assertThat("Hany", not(isEmptyString()));
        //////////////////////////////////////////////////////////
        // Array
		Integer[] marks = { 1, 2, 3 };
		assertThat(marks, arrayWithSize(3));
		assertThat(marks, arrayContainingInAnyOrder(2, 3, 1));
```