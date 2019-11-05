package com.imagine.colorpalette;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.IntDef;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Muhammed Refaat
 * 23 Oct 2019
 */
public class ColorPalette extends HorizontalScrollView implements View.OnClickListener {

    /*Different Palettes*/

    public static final int ORIGINAL_COLORS = 0;
    public static final int SOFT_COLORS = 1;
    public static final int FASHION_COLORS = 2;
    public static final int TURBO_COLORS = 3;
    public static final int BLACK_WHITE_COLORS = 4;
    private int height = ViewGroup.LayoutParams.MATCH_PARENT;

    @IntDef(value = {ORIGINAL_COLORS, SOFT_COLORS, FASHION_COLORS, TURBO_COLORS, BLACK_WHITE_COLORS})
    @interface ColorPalettsSelections {
    }

    /*Variables*/

    private LinearLayout colorsContainer;
    private Context context;
    private int selectedColor;
    private ColorSelectListener mListener;

    /*Constructors*/

    public ColorPalette(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorPalette(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ColorPalette(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    /**
     * Calling the init methods
     *
     * @param context the current holding context
     * @param attrs   the attrs that got set for the custom view
     */
    private void init(Context context, AttributeSet attrs) {
        // set the current holding context
        setContext(context);
        // setting the layout container that will have the colors
        setTheContainer();
        // setting the colors inside the Palette
        applyColorPalette(context, attrs);
    }

    /**
     * Setting the View Context
     *
     * @param context the current holding context
     */
    private void setContext(Context context) {
        this.context = context;
    }

    /**
     * creating and setting att.s for the linear layout
     */
    private void setTheContainer() {
        // first, check for height change
        height = LinearLayout.LayoutParams.MATCH_PARENT;
        ViewGroup.LayoutParams parentLayoutParams = this.getLayoutParams();
        try {
            if (parentLayoutParams.height != ViewGroup.LayoutParams.MATCH_PARENT &&
                    parentLayoutParams.height != ViewGroup.LayoutParams.WRAP_CONTENT)
                height = parentLayoutParams.height;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Now, Apply the changes
        colorsContainer = new LinearLayout(context);
        colorsContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                height));
        colorsContainer.setGravity(Gravity.CENTER_VERTICAL);
        colorsContainer.setOrientation(LinearLayout.HORIZONTAL);
        this.addView(colorsContainer);
    }

    /**
     * Setting the Colors for the ColorPalette
     */
    private void setTheColors(int colorPaletteNumber) {
        List<String> savedColorsArray = new ArrayList<>();
        // getting the color set
        switch (colorPaletteNumber) {
            case ORIGINAL_COLORS:
                savedColorsArray = Arrays.asList(getResources().getStringArray(R.array.colors));
                break;
            case FASHION_COLORS:
                savedColorsArray = Arrays.asList(getResources().getStringArray(R.array.fashion_colors));
                break;
            case TURBO_COLORS:
                savedColorsArray = Arrays.asList(getResources().getStringArray(R.array.turbo_colors));
                break;
            case SOFT_COLORS:
                savedColorsArray = Arrays.asList(getResources().getStringArray(R.array.soft_colors));
                break;
            case BLACK_WHITE_COLORS:
                savedColorsArray = Arrays.asList(getResources().getStringArray(R.array.black_white_colors));
                break;
        }
        // setting the color set
        for (int i = 0; i < savedColorsArray.size(); i++) {
            addTheColorLayout(savedColorsArray.get(i), i == 0);
        }
    }

    /**
     * Setting custom Colors for the ColorPalette
     */
    private void setColors(List<String> colorsAsHexArray) {
        // clear the palette from the previous colors
        colorsContainer.removeAllViews();
        // adding the new colors list
        for (int i = 0; i < colorsAsHexArray.size(); i++) {
            addTheColorLayout(colorsAsHexArray.get(i), i == 0);
        }
    }

    /**
     * Setting custom Colors for the ColorPalette
     */
    private void setColorPallete(@ColorPalettsSelections int colorPaletteNumber) {
        // clear the palette from the previous colors
        colorsContainer.removeAllViews();
        // adding the new colors list
        setTheColors(colorPaletteNumber);
    }

    /**
     * Building the Single Color Layout and adding it to the container layout
     *
     * @param colorValue  the color String value (HEX)
     * @param addingFrame if the color frame (refers to selection needs to be added)
     */
    private void addTheColorLayout(String colorValue, boolean addingFrame) {
        // inflating & setting the color layout
        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        View colorLayout = mLayoutInflater.inflate(R.layout.single_color_layout, null);
        View colorFrame = colorLayout.findViewById(R.id.color_frame);
        View color = colorLayout.findViewById(R.id.color);
        if (addingFrame)
            colorFrame.setVisibility(VISIBLE);
        // setting the color value
        GradientDrawable backgroundGradient = (GradientDrawable) color.getBackground();
        backgroundGradient.setColorFilter(Color.parseColor(colorValue), PorterDuff.Mode.SRC_ATOP);
        // Add the color to the layout
        colorsContainer.addView(colorLayout);
        // setting identifier tag to the color layout
        colorLayout.setTag(colorValue);
        // setting the onCLick Listener for the color selection
        colorLayout.setOnClickListener(this);
        // check for height changes
        if (height != ViewGroup.LayoutParams.MATCH_PARENT) {
            try {
                // set colorFrame height
                LinearLayout.LayoutParams colorFrameLp = (LinearLayout.LayoutParams) colorFrame.getLayoutParams();
                colorFrameLp.height = height;
                colorFrame.setLayoutParams(colorFrameLp);
                // set colorElement height
                int colorElementHeight = (int) (height * 0.58333333333);
                LinearLayout.LayoutParams colorElementLp = (LinearLayout.LayoutParams) color.getLayoutParams();
                colorElementLp.height = colorElementHeight;
                color.setLayoutParams(colorElementLp);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Selecting a specific color from the ColorPalette
     *
     * @param selectedColor the selected color (HEX) to apply the selection to
     */
    private void selectColor(String selectedColor) {
        View singleColor;
        View colorFrame;
        for (int i = 0; i < colorsContainer.getChildCount(); i++) {
            singleColor = colorsContainer.getChildAt(i);
            colorFrame = singleColor.findViewById(R.id.color_frame);
            if (singleColor.getTag().toString().equals(selectedColor))
                colorFrame.setVisibility(VISIBLE);
            else
                colorFrame.setVisibility(INVISIBLE);
        }
    }

    /**
     * Selecting a specific color from the ColorPalette
     *
     * @param selectedColorInt the selected color (Int) to apply the selection to
     */
    private void selectColor(int selectedColorInt) {
        View singleColor;
        View colorFrame;
        String selectedColor = String.format("#%06X", (0xFFFFFF & selectedColorInt));
        for (int i = 0; i < colorsContainer.getChildCount(); i++) {
            singleColor = colorsContainer.getChildAt(i);
            colorFrame = singleColor.findViewById(R.id.color_frame);
            if (singleColor.getTag().toString().equals(selectedColor))
                colorFrame.setVisibility(VISIBLE);
            else
                colorFrame.setVisibility(INVISIBLE);
        }
    }

    /**
     * Adding a new color to the Palette
     *
     * @param colorValue       The color value to be added
     * @param allowDuplication if set to true, will allow the color to be added to the palette even
     *                         if another is already added there with the same color value
     */
    public void addColor(String colorValue, boolean allowDuplication) {
        // check if the color is already there
        if (!allowDuplication) {
            for (int i = 0; i < colorsContainer.getChildCount(); i++) {
                if (colorsContainer.getChildAt(i).getTag().toString().trim().equals(colorValue)) {
                    return;
                }
            }
        }
        // adding it to the palette
        addTheColorLayout(colorValue, false);
    }

    /**
     * Removing a color from the palette
     *
     * @param idx the color index at the color palette
     */
    public void removeColorByIndex(int idx) {
        colorsContainer.getChildAt(idx).setVisibility(GONE);
    }

    /**
     * Removing a color from the palette
     *
     * @param colorValue the color to be removed value
     */
    public void removeColorByHexValue(String colorValue) {
        for (int i = 0; i < colorsContainer.getChildCount(); i++) {
            if (colorsContainer.getChildAt(i).getTag().toString().trim().equals(colorValue)) {
                colorsContainer.getChildAt(i).setVisibility(GONE);
                return;
            }
        }
    }

    /**
     * Getter for the selected color value
     *
     * @return the selectedColor value
     */
    public int getSelectedColor() {
        return selectedColor;
    }

    /**
     * Setter for the selected color value
     *
     * @param selectedColor the selectedColor value
     */
    public void setSelectedColor(String selectedColor) {
        this.selectedColor = Color.parseColor(selectedColor);
        selectColor(selectedColor);
    }

    /**
     * Setter for the selected color value
     *
     * @param selectedColor the selectedColor value
     */
    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
        selectColor(selectedColor);
    }

    @Override
    public void onClick(View v) {
        String selectedColor = v.getTag().toString().trim();
        selectColor(selectedColor);
        setSelectedColor(Color.parseColor(selectedColor));
        mListener.onColorSelected(selectedColor);
    }

    /**
     * Setting onColor selected listener
     *
     * @param listener the listener which got set at the holding view
     */
    public void setListener(ColorSelectListener listener) {
        mListener = listener;
    }

    /**
     * Interface for receiving color selection in {@link ColorPalette}
     *
     * @author Muhammed Refaat
     */
    public interface ColorSelectListener {
        void onColorSelected(String color);
    }

    /**
     * Applying the color palette if selected from the xml
     *
     * @param context the current holding context
     * @param attrs   the attrs that got set for the custom view
     */
    private void applyColorPalette(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.palette_selection,
                0, 0);
        int colorPalette = 0;
        try {
            colorPalette = a.getInteger(R.styleable.palette_selection_palette, 0);
        } finally {
            a.recycle();
        }
        setTheColors(colorPalette);
    }

}
