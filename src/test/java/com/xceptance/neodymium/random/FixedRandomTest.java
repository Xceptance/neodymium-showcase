package com.xceptance.neodymium.random;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.Random;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.ex.ElementNotFound;

import io.qameta.allure.Description;

public class FixedRandomTest {
	private Random random;

	protected static final Logger LOGGER = LoggerFactory.getLogger(RandomJobOffersTest.class);

	@Test
	@Description(value = "Showcase for fixec random Test.")
	public void testRandomJobs() {
		// open the demo page and prepare it for the test
		openJobPage();

		// count the job offers in the list
//		.jobs-plain-list>li>a
//		.tab-content>.jobs-plain-list>li>a
		int jobOffersCount = $$(".jobs-plain-list>li>a").size();
		LOGGER.error("Count of job offers = " + jobOffersCount);
		
		// create a random number between 1 and job offers count in the list
		random = new Random();
		final int randomNumber = random.nextInt(jobOffersCount) + 1;
		LOGGER.error("random Number = " + randomNumber);
//		$$(".tab-content>.jobs-plain-list>li>a").get(randomNumber).click();  //li:nth-child(" + position + ")
//		$(".jobs-plain-list>li:nth-child(" + randomNumber + ")>a").click();
		$(".tab-content>.jobs-plain-list>li:nth-child(7)>a").click();
		
		// test job offer detail page contains ".job-listing-col.job-col-header" twice
		// it is unique in this page
		$$(".job-listing-col.job-col-header").shouldHave(sizeGreaterThanOrEqual(2));

	}

	// a helper function to open the job page //and close the GDPR dialog to avoid
	// duplicate code
	private void openJobPage() {
		// open demo page
		Selenide.open("https://www.xceptance.com/de/careers/");

		// close GDPR overlay if visible
		boolean overlayIsVisible = true;
		try {
			$(".btn-link").shouldBe(visible);
		} catch (ElementNotFound e) {
			overlayIsVisible = false;
		}

		if (overlayIsVisible) {
			$(".btn-link").click();
			$(".btn-link").shouldBe(hidden);
			Selenide.refresh();
		}
	}
}
