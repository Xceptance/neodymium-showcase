# Introduction
This repository is supposed to be used as a showcase project to show the usage of a number of feature for the [Neodymium Library](https://github.com/Xceptance/neodymium-library). It was created based on the [Neodymium template](https://github.com/Xceptance/neodymium-template).



# Content
Below is a tabel of the content fro this project.

## 1. Shadow DOM
The `showcase.neodymium.tests.shadowdom` package contains the uscases for the shadow DOM automation.
#### 1.1 General Shadow DOM
In `TextTest.java` you can find a simple usecase on how to automatically test shadow DOM elements
#### 1.2. Nested Shadow DOM
In `CheckboxTest.java` there is are more sophisticated usecases for shadow DOM including nested shadow DOM's.
## 2. Localization
Under `showcase.neodymium.tests.localization` you can find how to use the localization feature of neodymium. Note that all the translated strings can be found in the `localization.yaml` in the config folder.
## 3. PDF Download
In the `showcase.neodymium.tests.pdf` package is a basic test for downloading and checking a PDF file. In this test, we use the PDFBox framework for handling a PDF file.
To first download such a file, we use Selenide's `download` function in order to download a file directly via a given link.
Then, the PDF file is loaded with the PDFBox framework, so we can access the text and verify it.


## License
MIT
