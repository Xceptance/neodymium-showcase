# Introduction
This repository is a Showcase project to demonstrate the usage of several features of the [Neodymium Library](https://github.com/Xceptance/neodymium-library).

To use Neodymium features in test development as intended from the very beginning on without struggling around with several required configurations each show case explains the approach technology, the implementation options as well as the belonging configuration next to where it is implemented. The reader gets also some recommendation about when an approach should be used or even not. 


# Show cases
Below is a summary of the show cases. Deeper documentation can be found in the show case code next to each show case. It is also possible to execute a show case after [setting up the Chrome WebDriver](https://github.com/Xceptance/neodymium-library/wiki/How-to-set-up-a-WebDriver). Just run the show case of interest as a JUnit test from with the IDE.

## 1. Shadow DOM
The `showcase.neodymium.tests.shadowdom` package contains use cases for the shadow DOM automation. Please also have a look at our [wiki](https://github.com/Xceptance/neodymium-library/wiki/Shadow-DOM-Testing) for more information.

* `ShadowDomTextTest.java` demonstrates a simple use case on how to automatically test shadow DOM elements.
* `ShadowDomCheckboxTest.java` contains more sophisticated use cases for shadow DOM including nested shadow DOMs.

## 2. Localization
The tests in the `showcase.neodymium.tests.localization` package highlight how the [localization](https://github.com/Xceptance/neodymium-library/wiki/Localization) feature of Neodymium is used. Note that all the translated wordings and their keys can be found in the `config/localization.yaml` file. 

## 3. PDF Download
In the `showcase.neodymium.tests.pdf` package is a basic test for downloading and checking a PDF file. In this test, we use the PDFBox framework for handling a PDF file. To download such a file, we use Selenide's `download` function It downloads a file directly via a given link.
Afterwards, the PDF file is loaded with the PDFBox framework, so we can access the text and verify it.

## 4. Web slider 
The `showcase.neodymium.tests.slider` package contains use cases to drag and drop a web slider into different directions e.g. horizontal and vertical. The function provided by Neodymium can also be used to drag and drop any element that supports this.

## 5. Basic authentication  
The `showcase.neodymium.tests.basicauth` package contains several approaches how to setup basic authentication within your test automation project.
 
* `SelenideBasicAuthenticationTest.java` demonstrates how to perform a standard basic authentication via URL.
* `NeodymiumLocalProxyBasicAuthenticationTest.java` shows what is needed to perform an automatic authentication via the embedded local proxy provided by Neodymium.
* `NeodymiumLocalProxyBasicAuthenticationHostTest.java` shows what is needed to perform a manual authentication via the embedded local proxy provided by Neodymium.
* `NeodymiumLocalProxyGenerateCertificateAuthenticationTest.java` shows how to secure the traffic with an automatic generated certificate via the embedded local proxy provided by Neodymium.
* `NeodymiumLocalProxyCertificateAuthenticationTest.java` shows how to secure the traffic by providing an own certificate via the embedded local proxy provided by Neodymium.

## 6. XcMailr Plugin
The `showcase.neodymium.tests.xcmailr` package contains a simple test case for the XcMailr plugin for Neodymium. You can find more information about it by consulting the `README.md` of the XcMailr plugin which can be found [here](https://github.com/Xceptance/neodymium-plugin-xcmailr).
This test case requires some effort in setting up the test environment since there is no publicly free XcMailr service available. Hence you need to set up an instance of the [XCMailr](https://github.com/Xceptance/XCMailr) on your own or get in contact with [Xceptance](https://www.xceptance.com/en/contact/) if you are in need for using such a service within your test. The remaining setup is described within the test case and shouldn't be to challenging.

## 7. Mobile Test
In the `showcase.neodymium.tests.responsive` package is a basic test case to test a mobile device. In order to test a cell phone for example, the emulation of various mobile devices integrated in Chrome can be used.

## 8. Random test data  
The `showcase.neodymium.tests.random` package contains the selection of random web elements from a website under test.
 
* `FixedRandomTest.java` shows how a random test can be run again in the same way.
* `RandomJobOffersTest.java` demonstrates the selection of different job offers. 

## 9. Logging with log4j2  
The `showcase.neodymium.tests.logging` package shows how log4j2 can be used for logging. The configuration is specified in the log4j2.properties file. Logging can be set differently for console output and log files. With two test cases it is shown that it is even possible to define the logging per test.

## 10. iFrame
This show case in the `showcase.neodymium.tests.iframe` package explains with an example implementation how to test a web site using frames. Selenide offers a straight forward approach to handle such frames and within Neodymium this is simply applied.

## 10. Test data access
The various options for providing test data are shown in the package `showcase.neodymium.tests.data`.

* `AccessDataViaDataObjectsTest.java` demonstrates how to perform a data access via POJO.
* `AccessDataViaDataSetAnnotationTest.java` shows how different data can be selected using annotation.
* `AccessDataViaDataUtilsTest.java` shows data access with the help of DataUtils class in the Neodymium library.
* `AccessDataViaJsonPathTest.java` shows data access by means of Json Path.
* `DataFilePriotityTest.java` shows the priority when accessing different file formats.


## License
MIT