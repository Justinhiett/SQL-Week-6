package main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream; 

import static org.junit.jupiter.params.provider.Arguments.arguments; 
import static org.assertj.core.api.Assertions.assertThatThrownBy; 
import static org.assertj.core.api.Assertions.assertThatThrownBy; 
import static org.mockito.Mockito.spy; 
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class TestDemoTest { 
	
	private TestDemo testDemo; 
	private TestDemo mockDemo;
	

	@BeforeEach
	void setUp() throws Exception { 
		testDemo = new TestDemo(); 
		mockDemo = spy(testDemo); 
		
		doReturn(5).when(mockDemo).getRandomInt(); 
		
		
		
		
		
	} 
	
	static Stream<Arguments> GetPositiveNumbersToAdd() {
		return(Stream.of(arguments(3,4,7), 
				arguments(4, 9, 13), 
				arguments(0,6,6)));
	} 
	
	static Stream<Arguments> GetNegativeNumbersToAdd() {
		return(Stream.of(arguments(-5, 6, IllegalArgumentException.class),   
				arguments(-10, -5, IllegalArgumentException.class)));
				
	}

	@ParameterizedTest 
	@MethodSource("main.TestDemoTest#GetPositiveNumbersToAdd")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected) { 
		
		int actual = testDemo.addPositive(a, b);
		
		assertThat(actual).isEqualTo(expected);
		
	} 
	
	@ParameterizedTest 
	@MethodSource("main.TestDemoTest#GetNegativeNumbersToAdd")
	void assertThatANegativeNumeberThrowsAnIllegalArgumentException(int a, int b, Class<? extends Exception >e) {
		
		assertThatThrownBy(() ->testDemo.addPositive(a, b)).isInstanceOf(e);
	} 
	
	@Test 
	void AssertThatNumberSquaredIsCorrect() {  
		int fiveSquared = mockDemo.randomNumberSquared();
		
		assertThat(fiveSquared).isEqualTo(25);
	}

}
