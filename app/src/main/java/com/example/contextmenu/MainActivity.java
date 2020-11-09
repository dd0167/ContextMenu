package com.example.contextmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView sidra;
    TextView hefreshormacpil;
    EditText firstnum;
    EditText sidranum;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sidra=(TextView) findViewById(R.id.sidra);
        hefreshormacpil=(TextView) findViewById(R.id.hefreshormacpil);
        firstnum=(EditText) findViewById(R.id.firstnum);
        sidranum=(EditText) findViewById(R.id.sidranum);

        s="";
    }

    public void enter(View view) {
        String fn=firstnum.getText().toString();
        String sn=sidranum.getText().toString();
        if (s.equals(""))
        {
            Toast.makeText(this, "בחר סדרה!", Toast.LENGTH_SHORT).show();
        }
        else if (fn.equals("") || sn.equals(""))
        {
            Toast.makeText(this, "הכנס מספר!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            double fnum=Double.parseDouble(fn);
            double snum=Double.parseDouble(sn);
            Intent la=new Intent(this, ListActivity.class);
            la.putExtra("snum",snum);
            la.putExtra("fnum",fnum);
            la.putExtra("s",s);
            startActivity(la);
        }
    }

    public void heshbonit(View view) {
        sidra.setText("הסדרה שנבחרה היא חשבונית");
        hefreshormacpil.setText("הכנס את הפרש הסדרה");
        s="hes";
    }

    public void handasit(View view) {
        sidra.setText("הסדרה שנבחרה היא הנדסית");
        hefreshormacpil.setText("הכנס את מכפיל הסדרה");
        s="han";
    }
}