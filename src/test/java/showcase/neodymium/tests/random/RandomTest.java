package showcase.neodymium.tests.random;

import java.util.Random;

import org.junit.Test;

import com.xceptance.neodymium.util.Neodymium;

import io.qameta.allure.Description;

public class RandomTest {

	@Test
	@Description(value = "Showcase for usage of Neodymium#getRandom.")
	public void testRandom() {

		Random random = Neodymium.getRandom();

		for (int i = 1; i < 100; i++) {
			// generate values between zero (inclusive) and nextInt(bound) (exclusive)
			// add 1 to get values between 1 and nextInt(bound)
			int randomNumber = random.nextInt(10) +1;
			System.out.println("randomNumber   = " + randomNumber);
		}
	}
}
