package net.hka.example.mockito.basics.data.api;

import java.util.List;

//External Service - Lets say this comes from WunderList
// Or let say another team will build this service, So it's not implemented yet
public interface TodoService {
	public List<String> retrieveTodos(String user);
	void deleteTodo(String todo);
}
