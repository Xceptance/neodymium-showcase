# Introduction
This repository is a Showcase project to demonstrate the usage of several features of the [Neodymium Library](https://github.com/Xceptance/neodymium-library).

To use Neodymium features in test development as intended from the very beginning on without struggling around with several required configurations each show case explains the approach technology, the implementation options as well as the belonging configuration next to where it is implemented. The reader gets also some recommendation about when an approach should be used or even not. 


# Show cases
Below is a summary of the show cases. Deeper documentation can be found in the show case code next to each show case. It is also possible to execute a show case after [setting up the Chrome Webdriver](https://github.com/Xceptance/neodymium-library/wiki/How-to-set-up-a-WebDriver). Just run the show case of interest as a JUnit test from with the IDE.

## 1. Shadow DOM
The `showcase.neodymium.tests.shadowdom` package contains use cases for the shadow DOM automation. Please also have a look at our [wiki](https://github.com/Xceptance/neodymium-library/wiki/Shadow-DOM-Testing) for more information.
#### 1.1 General Shadow DOM
In `TextTest.java` you can find a simple use case on how to automatically test shadow DOM elements.
#### 1.2. Nested Shadow DOM
`CheckboxTest.java` contains more sophisticated use cases for shadow DOM including nested shadow DOMs. More information can be found [here](https://github.com/Xceptance/neodymium-library/wiki/Shadow-DOM-Testing).
## 2. Localization
The tests in the `showcase.neodymium.tests.localization` package highlight how the [localization](https://github.com/Xceptance/neodymium-library/wiki/Localization) feature of neodymium is used. Note that all the translated strings and their keys can be found in the `config/localization.yaml` file. 
## 3. PDF Download
In the `showcase.neodymium.tests.pdf` package is a basic test for downloading and checking a PDF file. In this test, we use the PDFBox framework for handling a PDF file.
To first download such a file, we use Selenide's `download` function in order to download a file directly via a given link.
Then, the PDF file is loaded with the PDFBox framework, so we can access the text and verify it.
## 4. Web slider 
The `showcase.neodymium.tests.slider` package contains the use cases to drag and drop a web slider into different directions e.g. horizontal and vertical.

## License
MIT