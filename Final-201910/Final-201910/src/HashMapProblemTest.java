

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class HashMapProblemTest {

	HashMap<String, ArrayList<String>> carMap1 = new HashMap<String, ArrayList<String>>();
	HashMap<String, ArrayList<String>> emptyMap1 = new HashMap<String, ArrayList<String>>();
	final int carCount = 4;
	String vins[] = { "WAU", "1FM", "5YJ", "1B3" };
	String makes[] = { "Audi", "Ford", "Tesla", "Dodge"};
	String models[] = { "A4", "Explorer", "Model X 75D", "Viper SRT 10"};

	@Before
	public void setUp() throws Exception {
		for (int k = 0; k < carCount; k++) {
			ArrayList<String> a1 = new ArrayList<String>();
			a1.add(vins[k]);
			a1.add(makes[k]);
			a1.add(models[k]);
			carMap1.put(vins[k], a1);
		} // end for
	}

	@Test
	public void test1() {	
		String s1 = "sold";
		int mapSizeBefore = carMap1.size();
		HashMapProblem.updateMapValues(carMap1, s1);
		assertEquals(carMap1.size(), mapSizeBefore);
		
		for(int k = 0; k < carMap1.size(); k++) {
			ArrayList<String> a1 = carMap1.get(vins[k]);
			assertEquals(a1.get(a1.size() - 1), s1);
		}
	}

	@Test
	public void test2() {	
		String s1 = "rusted out";	
		int mapSizeBefore = emptyMap1.size();
		HashMapProblem.updateMapValues(emptyMap1, s1);
		assertEquals(emptyMap1.size(), mapSizeBefore);
		
		for(int k = 0; k < emptyMap1.size(); k++) {
			ArrayList<String> a1 = emptyMap1.get(vins[k]);
			assertEquals(a1.get(a1.size() - 1), s1);
		}
	}

}
