import static org.junit.Assert.*;

import org.junit.Test;

public class TestThisClassTest {

	@Test
	public void test() {
		assertEquals(2,TestThisClass.numberOfQs("QQqq"));
		assertEquals(0,TestThisClass.numberOfQs("qq"));
		assertEquals(4,TestThisClass.numberOfQs("QQQQ"));
		assertEquals(0,TestThisClass.numberOfQs("sdofsd"));
		assertEquals(0,TestThisClass.numberOfQs(""));
		assertEquals(0,TestThisClass.numberOfQs("19283"));
		
	}

}
