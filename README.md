# ColorPalette
Customizable Color Palette Library

You can use this library to add customizable color palette to your Android application, which comes with an attractive sets of colors to be used for the palette, in addition to a nice UI for displaying the color palette.

How to include the Library in your application:

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step 2. Add the dependency

for Gradle:

	dependencies {
	        implementation 'com.github.MuhammedRefaat:ColorPalette:1.0'
	}
  
  for Maven:
  
        <dependency>
	        <groupId>com.github.MuhammedRefaat</groupId>
	        <artifactId>ColorPalette</artifactId>
	        <version>1.0</version>
	    </dependency>


