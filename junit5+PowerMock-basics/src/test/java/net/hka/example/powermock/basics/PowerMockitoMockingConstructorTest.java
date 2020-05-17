/*
 * ## What You Will Learn during this Step:
	- Using PowerMock and Mockito to mock a constructor
	- PowerMockitoMockingConstructorTest.java
		-- 	PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockList);
 */
package net.hka.example.powermock.basics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ SystemUnderTest.class /*To be able to mock the Constructor, we need to add in the Class that creates the new object*/})
public class PowerMockitoMockingConstructorTest {
	private static final int SOME_DUMMY_SIZE = 100;

	@Mock
	Dependency dependencyMock;

	@InjectMocks
	SystemUnderTest systemUnderTest;

	@Test
	public void powerMockito_MockingAConstructor() throws Exception {

		@SuppressWarnings("unchecked")
		ArrayList<String> list = mock(ArrayList.class);

		stub(list.size()).toReturn(SOME_DUMMY_SIZE);

		PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(list);

		int size = systemUnderTest.methodUsingAnArrayListConstructor();

		assertEquals(SOME_DUMMY_SIZE, size);
	}
}
