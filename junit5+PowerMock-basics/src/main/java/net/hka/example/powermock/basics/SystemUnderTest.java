package net.hka.example.powermock.basics;

import java.util.ArrayList;
import java.util.List;

interface Dependency {
	List<Integer> retrieveAllStats();
}

public class SystemUnderTest {
	private Dependency dependency;
	
	public int methodUsingAnArrayListConstructor() {
		List<String> list = new ArrayList<>();
		return list.size();
	}

	public int methodCallingAStaticMethod() {
//		List<Integer> stats = dependency.retrieveAllStats();
//		long sum = 0;
//		for (int stat : stats)
//			sum += stat;
		long sum = this.privateMethodUnderTest();
		
		return UtilityClass.staticMethod(sum); // static method call
	}

	private long privateMethodUnderTest() {
		List<Integer> stats = dependency.retrieveAllStats();
		long sum = 0;
		for (int stat : stats)
			sum += stat;
		return sum;
	}
}
