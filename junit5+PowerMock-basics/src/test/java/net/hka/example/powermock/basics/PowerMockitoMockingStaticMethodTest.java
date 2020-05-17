/*
 * ## What You Will Learn during this Step:
	- Add dependency on PowerMock.
	- Using PowerMock and Mockito to mock a Static Method.
 */
package net.hka.example.powermock.basics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@DisplayName("When running The Test on systemUnderTest class to invoke a static method using PowerMock running under Junit4")
@RunWith(PowerMockRunner.class)
@PrepareForTest({ UtilityClass.class /*The class with static method to be mocked*/})
public class PowerMockitoMockingStaticMethodTest {
	// Using Mockito to mock the interface.
	@Mock
	Dependency dependencyMock;

	// This class use the Dependency interface and has a static method
	@InjectMocks
	SystemUnderTest systemUnderTest;
	
	@Test
	@DisplayName("When Testing the static method")
	public void powerMockito_MockingAStaticMethodCall() {
		when(dependencyMock.retrieveAllStats()).thenReturn(Arrays.asList(1, 2, 3));

		// Using PowerMock to mock a Static Method.
		PowerMockito.mockStatic(UtilityClass.class);

		when(UtilityClass.staticMethod(anyLong())).thenReturn(150);

		assertEquals(150, systemUnderTest.methodCallingAStaticMethod());

		//To verify a specific method call
		//First : Call PowerMockito.verifyStatic() 
		//Second : Call the method to be verified
		PowerMockito.verifyStatic();
		UtilityClass.staticMethod(1 + 2 + 3);

		// verify exact number of calls
		//PowerMockito.verifyStatic(Mockito.times(1));

	}
}
