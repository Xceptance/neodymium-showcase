package com.xceptance.neodymium.random;

import java.util.Random;

import org.junit.Test;

import com.xceptance.neodymium.util.Neodymium;

import io.qameta.allure.Description;

public class RandomTest {

	@Test
	@Description(value = "Showcase for usage of Neodymium#getRandom.")
	public void testRandom() {
		Random random = Neodymium.getRandom();
		System.out.println("randomNumber   = ");

		for (int i = 1; i < 100; i++) {
			int randomNumber = random.nextInt(11);
			System.out.println("randomNumber   = " + randomNumber);
		}
	}
}
