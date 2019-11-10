# ColorPalette
Customizable Color Palette Library

You can use this library to add customizable color palette to your Android application, which comes with an attractive sets of colors to be used for the palette, in addition to a nice UI for displaying the color palette.


![](https://ibb.co/tHssnhM)


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
	        implementation 'com.github.MuhammedRefaat:ColorPalette:1.4'
	}
  
***for Maven:***
  
    <dependency>
	    <groupId>com.github.MuhammedRefaat</groupId>
	    <artifactId>ColorPalette</artifactId>
	    <version>1.4</version>
	</dependency>


## How to use the Library:

   **Define the ColorPalette**
    
   ***in XML:***
    
    <com.imagine.colorpalette.ColorPalette xmlns:colorPalette="http://schemas.android.com/apk/res-auto"
        android:id="@+id/color_palette4"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        colorPalette:palette="blackAndWhite" />
	
   ***in Java***
    
     ColorPalette colorPalette = findViewById(R.id.color_palette1);
     colorPalette.setListener(onColorSelected);
	
     /**
     * The colorSelection Listener (returns the Hex value for that color)
     */
    ColorPalette.ColorSelectListener onColorSelected = new ColorPalette.ColorSelectListener() {
        @Override
        public void onColorSelected(String color) {
            // TODO use color (The Hex Value of the Selected color)
            Toast.makeText(MainActivity.this, "Selected Color HeX:" + color, Toast.LENGTH_SHORT).show();
        }
    };
    
   **Customize the ColorPalette**	
	
   ***Add Color:***
	
    colorPalette.addColor(String coloeHexValue, boolean allowDuplication);
	
   ***Remove Color By HEX Value:***
	
    colorPalette.removeColorByHexValue("#FFFFFF");

   ***Remove color By Index:***
   
    colorPalette.removeColorByIndex(1);
    
   ***Set Selected Color by Hex value or color Int value:***
   
    colorPalette.setSelectedColor(String colorHexValue);
    colorPalette.setSelectedColor(colorIntValue);
    
   ***Get Selected Color:***
   
    colorPalette.getSelectedColor();
    
    
    
   *Sample App:*
	
   [ColorPaletteSample](https://github.com/MuhammedRefaat/ColorPaletteSample.git)
