// https://www.youtube.com/watch?v=2E3WqYupx7c&list=PLqq-6Pq4lTTa4ad5JISViSb2FVG8Vwa4o&index=1

package net.hka.example.junit5.basics;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

// @TestInstance(TestInstance.Lifecycle.PER_METHOD) //=> this is the default behavior and in this case, you have to mark the methods for @BeforeAll and @AfterAll as static
// @TestInstance(TestInstance.Lifecycle.PER_CLASS) //=> now you have the choice to mark the methods for @BeforeAll and @AfterAll as static methods or as instance methods
@DisplayName("When running The Test for MathUtils Class")
class MathUtilsTest {

	// another DI from Junit
	//TestInfo testInfo;
	//TestReporter testReporter;
	
	
	MathUtils math;
	
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("beforeAllInit => @BeforeAll and @AfterAll methods should be marked as static, because these methods run even before creating an instance from the test class itself");
	}
	
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		//this.testInfo = testInfo;
		//this.testReporter = testReporter;
		
		math = new MathUtils();
		
		testReporter.publishEntry("Running " + testInfo.getDisplayName() + " with Annotations " + Arrays.toString(testInfo.getTestMethod().get().getAnnotations()));
	}
	
	@AfterEach
	void cleanUp() {
		System.out.println("Clean up after each method");
	}
	
	@AfterAll
	static void afterAllCleanUp() {
		System.out.println("afterAllCleanUp => @BeforeAll and @AfterAll methods should be marked as static, because these methods run even before creating an instance from the test class itself");
	}
	
	@Nested
	@DisplayName("When running Add Two Numbers Test Cases")
	@Tag("Math")
	class AddTestCases {
		@Test
		@DisplayName("When adding two (+) numbers")
		void testAddPositives() {
//			fail("Not yet implemented");
			assertEquals(2, math.add(1, 1), () -> "Should return a right value (+) number");
		}
		
		@Test
		@DisplayName("When adding two (-) numbers")
		void testAddNegatives() {
			assertEquals(-2, math.add(-1, -1), () -> "Should return a right value (-) number");
		}
		
		@Test
		@DisplayName("When adding one (-) number and one (+) number with same values")
		void testAddOneNegativeSame() {
			assertEquals(0, math.add(-1, 1), () -> "Should return Zero");
		}
		
		@Test
		@DisplayName("When adding one (-) number and one (+) number with different values")
		void testAddOneNegativeDiff() {
			int supplier = 2;
			int actual = math.add(-1, 3);
			assertEquals(supplier, actual, () -> "Should return the right " + supplier  +  " value but it returns " + actual + " instead!");
		}
		
		@Test
		@DisplayName("When adding Zero value to a positive number")
		void testAddZero() {
			assertEquals(4, math.add(0, 4), () -> "Should return the non zero value in the passed parameters to the method");
		}
	}
	
	
	@Test
	@DisplayName("When Testing divide method and divide by Zero")
	@Tag("Math")
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> math.divide(1, 0), () -> "Should throw an exception when Dividing by Zero");
	}
	
	
	@Test
	@DisplayName("When Testing multiply method With All asserts, should return the right values in all cases")
	@Tag("Math")
	void testmultiply() {
		assertAll(
				() -> assertEquals(0, math.multiply(10, 0)), //, () -> "Multiply by Zero" 
				() -> assertEquals(10, math.multiply(10, 1)), //, () -> "Multiply by One" 
				() -> assertEquals(20, math.multiply(10, 2)), //, () -> "Multiply by positive numbers"
				() -> assertEquals(20, math.multiply(-10, -2)), //, () -> "Multiply by negative numbers"
				() -> assertEquals(-20, math.multiply(10, -2)) //, () -> "Multiply by one negative numbers"
		);		
	}
	
	
	//@Test
	@RepeatedTest(3) // It's one of Dependency Injection that "Junit" gives to you
					 // so by using @RepeatedTest tag, Junit will automatically inject RepetitionInfo class for you 	
	@DisplayName("When Testing Compute Circle Area method")
	@Tag("Circle")
	void testComputeCircleArea(/*RepetitionInfo ri*/) {
		assertEquals(314.1592653589793, math.computeCircleArea(10), () -> "Should caculate the circle area");
	}

	
	@Test
	@DisplayName("Testing Disabled Annotation")
	@Disabled // very handy special in TDD approach, because normally in this approach you build your test case even before building the actual implementation
	@Tag("Extras")
	void testDisabledAnnotation() {
		fail("Not yet implemented");		
	}
	
	@Test
	@DisplayName("Testing Enable Annotation")
	@EnabledOnOs(OS.LINUX)
	@Tag("Extras")
	void testEnabledOnOsAnnotation() {
		fail("Not yet implemented");		
	}
	
	@Test
	@DisplayName("Testing Assumption")
	@Tag("Extras")
	void testAssumption() {
		boolean isServerUpAndRunning = false;
		assumeTrue(isServerUpAndRunning);
		
		fail("Not yet implemented");		
	}
}
