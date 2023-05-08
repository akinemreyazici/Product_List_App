# product_list_app

  This project fetches relevant JSON data from a RESTful API called DummyJson to create an application with a login screen, a screen for listing products and searching for them, and a detail screen that displays information about a selected product. All data is fetched from Kotlin using the Retrofit library. The Glide library is used to display images of the products.
  
## Used Technologies

- Kotlin programming language
- Android Studio IDE 
- Retrofit library for making HTTP calls and parsing JSON responses
- Gson Converter for serializing and deserializing JSON objects
- Glide library for loading and caching images
- Android Nougat API (version 7.0) or higher for compatibility with modern Android devices

## Dependencies

To use this project, the following dependencies must be added to the `build.gradle` file:

```gradle
implementation group: 'com.squareup.retrofit2', name: 'retrofit', version: '2.9.0'
implementation group: 'com.squareup.retrofit2', name: 'converter-gson', version: '2.9.0'
implementation 'com.github.bumptech.glide:glide:4.15.1'

The above dependencies include Retrofit, Gson Converter, and Glide libraries. You can add them by copying and pasting the above code to the dependencies block of your build.gradle file.
```

## AndoridManifest.xml File

```AndroidManifest.xml
<uses-permission android:name="android.permission.INTERNET" />

Don't forget to allow your application to connect to the Internet.

```

