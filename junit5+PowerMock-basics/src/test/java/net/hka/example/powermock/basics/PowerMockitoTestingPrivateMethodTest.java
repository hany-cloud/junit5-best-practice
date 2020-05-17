/*
 * ## What You Will Learn during this Step:
	- Using PowerMock and Mockito to invoke a private Method.
 */
package net.hka.example.powermock.basics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@DisplayName("When running The Test on systemUnderTest class to invoke a private method using PowerMock running under Junit4")
@RunWith(PowerMockRunner.class)
public class PowerMockitoTestingPrivateMethodTest {
	// Using Mockito to mock the interface.
	@Mock
	Dependency dependencyMock;

	// This class use the Dependency interface and has a private method
	@InjectMocks
	SystemUnderTest systemUnderTest;

	@Test
	@DisplayName("When Testing the private method")
	public void powerMockito_CallingAPrivateMethod() throws Exception {
		when(dependencyMock.retrieveAllStats()).thenReturn(Arrays.asList(1, 2, 3));
		
		long value = (Long) Whitebox.invokeMethod(systemUnderTest, "privateMethodUnderTest"); // 1 + 2 + 3 
		
		assertEquals(6, value);
	}
}
