package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.widget.EditText; // for weight and height input
import android.widget.TextView; // for displaying text

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    // result formatter objects
    private static final DecimalFormat resultFormat = new DecimalFormat("0.00");
    // height and weight formatter objects
    private static final NumberFormat parameterFormat = NumberFormat.getInstance();


    private TextView weightTextView; // shows formatted weight number
    private TextView heightTextView; // shows formatted height number
    private TextView resultTextView; // shows BMI
    private double weight = 0.0; // user weight
    private double height = 0.0; // user height

    // called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass onCreate
        setContentView(R.layout.activity_main); // inflate the GUI

        // get references to programmatically manipulated TextViews
        weightTextView = (TextView) findViewById(R.id.weightTextView);
        heightTextView = (TextView) findViewById(R.id.heightTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);

        // set weightEditText's TextWatcher
        EditText weightEditText = (EditText) findViewById(R.id.weightEditText);
        weightEditText.addTextChangedListener(weightEditTextWatcher);

        // set heightEditText's TextWatcher
        EditText heightEditText = (EditText) findViewById(R.id.heightEditText);
        heightEditText.addTextChangedListener(heightEditTextWatcher);
    }

    // calculate and display BMI
    private void calculate() {
        if (weight > 0 && height > 0) {
            // calculate the BMI
            double result = (weight / ((height / 100) * (height / 100)));

            // displKay result formatted as a number
            resultTextView.setText(resultFormat.format(result));
        } else {
            resultTextView.setText("");
        }
    }

    // listener object for the EditText's text-changed events
    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        // called when the user modifies weight
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            // get weight, show formatted number
            try {
                weight = Double.parseDouble(s.toString());
                weightTextView.setText(parameterFormat.format(weight));
            }
            // catch empty or no numeric
            catch (NumberFormatException e) {
                resultTextView.setText("");
            }

            calculate(); // update the result
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };

    // watch text changing
    private final TextWatcher heightEditTextWatcher = new TextWatcher() {
        // called when the user modifies height
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            // get height, show formatted number
            try {
                height = Double.parseDouble(s.toString());
                heightTextView.setText(parameterFormat.format(height));
            }
            // catch empty or no numeric
            catch (NumberFormatException e) {
                resultTextView.setText("");
            }

            calculate(); // update the result
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };
}


/*************************************************************************
 * (C) Copyright 1992-2016 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
