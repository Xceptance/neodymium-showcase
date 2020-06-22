# Introduction
This repository is a showcase project to demonstrate the usage of several features of the [Neodymium Library](https://github.com/Xceptance/neodymium-library).

It is a collection of single tests to demonstrate how Neodymium works and to give a an easy hands on experience. 
Running all tests together can lead to errors due to the demo character and the different configurations needed for different systems under test.


# Content
Below is a summary of the content from this project.

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

## License
MIT