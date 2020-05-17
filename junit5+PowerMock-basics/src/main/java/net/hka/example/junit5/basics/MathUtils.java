// https://www.youtube.com/watch?v=2E3WqYupx7c&list=PLqq-6Pq4lTTa4ad5JISViSb2FVG8Vwa4o&index=1

package net.hka.example.junit5.basics;

public class MathUtils {
	public int add(int a, int b) {
		return a + b;
	}
	
	public int subtract(int a, int b) {
		return a - b;
	}
	
	public int multiply(int a, int b) {
		return a * b;
	}
	
	public int divide(int a, int b) {
		return a / b;
	}

	public double computeCircleArea(double radius) {
		return Math.PI * radius * radius;
	}
	
	public static void main(String[] args) {
		System.err.println(new MathUtils().computeCircleArea(10));
	}
	
}
