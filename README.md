# Upfeat Android Engineer Coding Test 2023
This document provides essential information about the Android Engineer Coding Test for Upfeat's Android Engineer position.

# App Description
This Android application is designed to demonstrate image classification using a custom bounding box, as well as saving snapshots to the device's storage. The app offers unique colors for each category's bounding box in every session.

# Steps to Build and Run the App
To build and run the app, follow these steps:

1) Open Android Studio.
2) Import the project into Android Studio.
3) Choose a suitable emulator or device.
4) Click the "Run" button.

Please note that the snapshots captured by the app will be saved in either the Internal Storage/Pictures/TFLiteDetect or Internal Storage/TFLiteDetect directory, depending on the device's operating system version.

# Assumptions Made
While developing this app, the following assumptions were made:

1) Suitable Conditions for Image Classification: The app assumes optimal lighting conditions and good camera quality to achieve accurate image classification.

2) Minimum SDK Version: The app has a minimum SDK version of 21, meaning it is compatible with Android devices running Android 5.0 (Lollipop) and above.

# Challenges Faced and Their Solutions
During the development of the app, there were no significant challenges encountered. However, ensuring smooth and lag-free performance was a priority. This was achieved by implementing the following strategies:

1) Use of LiveData: LiveData is employed to ensure that data is observed and updated in real-time, enhancing the user experience.

2) Use of Coroutines: Coroutines are used for managing asynchronous operations, allowing the app to perform background tasks efficiently without blocking the main thread.

3) Minimal Approach: The app was designed with a minimalistic approach to maintain a lightweight and responsive user interface.

Overall, the app provides the functionalities as described in the test instructions. Users can experience image classification with unique bounding box colors and save snapshots seamlessly.

Thank you for considering this Android Engineer Coding Test submission for the Upfeat Android Engineer position.