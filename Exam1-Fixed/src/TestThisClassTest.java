import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestThisClassTest {

	@Test
	public void test() {
		assertTrue(TestThisClass.intersectionSize(1, 3, 2, 4) == 2);
		assertFalse(TestThisClass.intersectionSize(1,2,3,4)== 2);
		assertTrue(TestThisClass.intersectionSize(2,4,1,3) == 2);
		assertFalse(TestThisClass.intersectionSize(13,14,7,8) == -4);
		
//		equals.(TestThisClass.intersectionSize)
//		fail("Not yet implemented");
	}

	private void assertequals(boolean b) {
		// TODO Auto-generated method stub.
		
	}

//	private void AssertTrue(int intersectionSize) {
//		// TODO Auto-generated method stub.
//		if (intersectionSize == 2){
//			
//		}
//	}

	
}
