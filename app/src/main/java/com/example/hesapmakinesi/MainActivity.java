package com.example.hesapmakinesi;

import androidx.appcompat.app.AppCompatActivity;

import android.media.TimedText;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {


    TextView output;
    TextView deger;
    EditText editTextNumberDecimal;

    Double deger1 = null;
    Double deger2 = null;
    String pendingOperator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button nokta = findViewById(R.id.nokta);
        Button btn_ac = findViewById(R.id.btn_ac);
        Button btn_del = findViewById(R.id.btn_del);
        Button btn_p = findViewById(R.id.btn_p);
        Button esit = findViewById(R.id.esit);
        Button carp = findViewById(R.id.carp);
        Button bolme = findViewById(R.id.bolme);
        Button cikarma = findViewById(R.id.cikarma);
        Button topla = findViewById(R.id.topla);

        output = findViewById(R.id.output);
        deger = findViewById(R.id.deger);
        editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);


        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                editTextNumberDecimal.append(button.getText().toString());
            }
        };
        button0.setOnClickListener(numberListener);
        button1.setOnClickListener(numberListener);
        button2.setOnClickListener(numberListener);
        button3.setOnClickListener(numberListener);
        button4.setOnClickListener(numberListener);
        button5.setOnClickListener(numberListener);
        button6.setOnClickListener(numberListener);
        button7.setOnClickListener(numberListener);
        button8.setOnClickListener(numberListener);
        button9.setOnClickListener(numberListener);
        nokta.setOnClickListener(numberListener);

        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String op = button.getText().toString();
                String value = editTextNumberDecimal.getText().toString();
                try {
                    Double doubleValue = Double.valueOf(value);
                    operation(doubleValue, op);
                }
                catch (NumberFormatException e){
                    editTextNumberDecimal.setText("");
                }
                pendingOperator = op;
                deger.setText(pendingOperator);
            }
        };
        carp.setOnClickListener(operatorListener);
        bolme.setOnClickListener(operatorListener);
        topla.setOnClickListener(operatorListener);
        cikarma.setOnClickListener(operatorListener);
        esit.setOnClickListener(operatorListener);
        btn_p.setOnClickListener(operatorListener);


        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextNumberDecimal.toString().length() > 0){
                    editTextNumberDecimal.setText(editTextNumberDecimal.getText().toString().substring(0,editTextNumberDecimal.getText().toString().length()-1));
                }
            }
        });
        btn_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deger1=null;
                deger2= null;
                pendingOperator = null;
                output.setText("");
                deger.setText("");
                editTextNumberDecimal.setText("");
            }
        });
    }
    private void operation(Double value, String operator){
        if(deger1 == null){
            deger1 = value;
        }
        else{
            deger2 = value;
            if(pendingOperator.equals("=")){
                pendingOperator = operator;
            }
            switch (pendingOperator){
                case "=":
                    deger1 = deger2;
                    break;
                case "+":
                    deger1 +=deger2;
                    break;
                case "-":
                    deger1 -=deger2;
                    break;
                case "*":
                    deger1 *=deger2;
                    break;
                case "/":
                    if(deger2 == 0){
                        deger1 = 0.0;
                    }
                    deger1 /= deger2;
                    break;
                case "%":
                    deger1 *= deger2/100;
                    break;
            }
        }
        output.setText(deger1.toString());
        deger.setText(operator);
        editTextNumberDecimal.setText("");

    }
}