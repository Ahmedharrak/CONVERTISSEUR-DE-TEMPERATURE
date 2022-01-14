package com.example.conversiondetemprature;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private EditText mEditText1;
    private TextView mTextView1;
    private TextView mTextView2;
    private RadioGroup radioGroup;
    private RadioButton mR1;
    private RadioButton mR2;
    private Button mButton;
    private static final String KEY_RESULTAT= "resultat_key";
    private static final String KEY_DEGRES = "degres_key";
    private static final String SOME_VALUE = "int_value";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText1 = findViewById(R.id.edit_text1);

        mTextView1 = findViewById(R.id.edit_view2);
        mTextView2 = findViewById(R.id.edit_view3);

        mButton = findViewById(R.id.b1);
        radioGroup = findViewById(R.id.rd);
        radioGroup.setOnCheckedChangeListener(this::onCheckedChanged);
        mR1 = findViewById(R.id.radio1);
        mR2 = findViewById(R.id.radio2);

        if (savedInstanceState != null) {
            String savedResultat = savedInstanceState.getString(KEY_RESULTAT);
            mTextView2.setText(savedResultat);

            String savedDegres = savedInstanceState.getString(KEY_DEGRES);
            mTextView1.setText(savedDegres);

            String savedValue = savedInstanceState.getString(SOME_VALUE);
            mEditText1.setText(savedValue);



        }else{
            Toast.makeText(this, "Nouvelle Page", Toast.LENGTH_SHORT).show();
        }

    }
    public void convert(View v){
        if (mEditText1.getText().toString().isEmpty()  ){
            Toast.makeText(getApplicationContext(), "Entrer Temperature", Toast.LENGTH_SHORT).show();
        } else {
            double value = new Double(mEditText1.getText().toString());
            if (mR1.isChecked()){
                double value2 =(9*value)/5 +32;

                mTextView2.setText(String.valueOf("La Température T = "+value +" degré Celsius \n correspond a F = "+value2 +" degré Fahrenheit."));


            }
            else{
                double value2 = (5*(value-32)/9);
                mTextView2.setText(String.valueOf("La Température F = "+value +" degré Fahrenheit \n correspond a T = "+value2 +" degré Celsius."));


            }

        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putString(KEY_RESULTAT, mTextView2.getText().toString());
        savedInstanceState.putString(KEY_DEGRES, mTextView1.getText().toString());
        savedInstanceState.putString(SOME_VALUE, mEditText1.getText().toString());


        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mTextView1.setText(savedInstanceState.getString(KEY_DEGRES));
        mTextView2.setText(savedInstanceState.getString(KEY_RESULTAT));
        mEditText1.setText(savedInstanceState.getString(SOME_VALUE));



    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (mR1.isChecked())
            mTextView1.setText("°C");
        if (mR2.isChecked())
            mTextView1.setText("°F");
    }

}