package test;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TempTest {
	@Test
	public void test1() {
		Integer integer = null;
		BigDecimal bigDecimal = new BigDecimal(10);
		BigDecimal[] bigDecimals = bigDecimal.divideAndRemainder(BigDecimal.valueOf(integer));

		System.out.println(bigDecimal);
	}
}
