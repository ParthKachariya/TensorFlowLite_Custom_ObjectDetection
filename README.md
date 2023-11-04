# TensorFlow Lite Custom Object Detection Coding Test
This document provides essential information about the Android Coding Test from upfeat.

# App Description
The purpose of this Android application is to show how to classify images using a personalized bounding box and store screenshots to the device's storage. Every session, the application provides different colors for the enclosing box of each category.

# Steps to Build and Run the App
Note: TensorFlow Lite is best practiced on a Physical Device, In my case I used OnePlus 3 running Android 9.0.

To build and run the app, follow these steps:

1) Open Android Studio.
2) Import the project into Android Studio.
3) Choose a device.
4) Click the "Run" button.

Please be aware that, depending on the operating system version of the device, the snapshots taken by the program will be saved in either the Internal Storage/Pictures/TFLiteDetect or Internal Storage/TFLiteDetect directory.


# Assumptions Made
While developing this app, the following assumptions were made:

1) Appropriate Lighting and Camera Quality: In order to achieve precise image classification, the program makes the assumption that the lighting and camera quality are ideal.

2) Minimum SDK Version: Android 5.0 (Lollipop) and later Android smartphones can use the app because it has a minimum SDK version of 21.

# Challenges Faced and Their Solutions
There were no major obstacles to overcome in the course of developing the software. But maintaining lag-free and seamless performance was crucial. The following tactics were used to accomplish this:

1) LiveData: By ensuring that data is monitored and updated in real-time, live data is used to improve user experience.

2) Coroutines: Coroutines are used to manage asynchronous operations, which frees up the main thread to perform background tasks effectively.

3) UI Changes: The app was designed keeping functionality as a priority without major UI changes.
   
# APK Destination
Located under the --> app/release 

All things considered, the app offers the features outlined in the test guidelines. Users can effortlessly save snapshots and experience image classification with distinct bounding box colors. Items that are confidently detected to be below the user-specified threshold is not shown.
