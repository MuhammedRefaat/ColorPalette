# ColorPalette
Customizable Color Palette Library

You can use this library to add customizable color palette to your Android application, which comes with an attractive sets of colors to be used for the palette, in addition to a nice UI for displaying the color palette.

## How to include the Library in your application:

**Step 1. Add the JitPack repository to your build file (project level)**

Add it in your root build.gradle at the end of repositories:

***for Gradle:***

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  

***for Maven:***

    <repositories>
	    <repository>
	        <id>jitpack.io</id>
	        <url>https://jitpack.io</url>
	    </repository>
    </repositories>


**Step 2. Add the dependency**

***for Gradle:***

	dependencies {
	        implementation 'com.github.MuhammedRefaat:ColorPalette:Tag'
	}
  
***for Maven:***
  
    <dependency>
	    <groupId>com.github.MuhammedRefaat</groupId>
	    <artifactId>ColorPalette</artifactId>
	    <version>Tag</version>
	</dependency>


## How to use the Library:

    <com.imagine.colorpalette.ColorPalette
        android:id="@+id/color_palette4"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        colorPalette:palette="blackAndWhite" />
