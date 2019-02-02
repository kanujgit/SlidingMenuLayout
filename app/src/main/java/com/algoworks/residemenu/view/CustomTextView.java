package com.algoworks.residemenu.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.algoworks.residemenu.R;


public class CustomTextView extends AppCompatTextView {
    private String typeFace;
    private final Context context;
    private ColorStateList textColors;

    public CustomTextView(Context context, String typeface) {
        super(context);
        this.context = context;
       // setCTypeFace(typeface);
        textColors = getTextColors();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.TextView);

        String typeface = a.getString(R.styleable.TextView_ctypeface);
     //   setCTypeFace(typeface);
        textColors = getTextColors();
    }

    public String getCTypeFace() {
        return typeFace;
    }


}
