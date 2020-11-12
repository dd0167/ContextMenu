package com.example.contextmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author Dean David <dd0167@bs.amalnet.k12.il>
 * @version 1.6
 * @since 12 /11/2020
 */

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnCreateContextMenuListener  {

    ListView list;
    String[] numbers;
    /**
     * The Sum of the numbers
     */
    double sum=0;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent la=getIntent();
        double fnum=la.getDoubleExtra("fnum",1);
        double snum=la.getDoubleExtra("snum",1);
        String s=la.getStringExtra("s");

        list=(ListView) findViewById(R.id.list);
        numbers=new String[20];

        if (s.equals("han")) /** The selected series is an invoice series */
        {
            for(int i=1;i<=numbers.length;i++){
                numbers[i-1]=EditNumber(fnum*(double)Math.pow((double)snum,(double)(i-1)));
            }
        }
        else if (s.equals("hes")) /** The series selected is an engineering series */
        {
            for(int i=1;i<=numbers.length;i++){
                numbers[i-1]=EditNumber(fnum+(i-1)*snum);
            }
        }

        list.setOnItemClickListener(this);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, numbers);
        list.setAdapter(adp);
        registerForContextMenu(list);

        textView2=(TextView) findViewById(R.id.textView2);
    }

    /**
     * Converting a number to an integer in a string
     * <p>
     * @param	v
     * @return	integer number in a string
     */
    private String EditNumber(double v) {
        if((((double)((int)v))==(double)v))
        {
            return(String.valueOf((int)v));
        }
        else
        {
            return String.valueOf(v);
        }
    }

    /**
     * Close activity
     * <p>
     * @param view the view
     */
    public void back(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    /**
     * Create context menu
     * <p>
     * @param	menu,v.menuinfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Choose what to display");
        menu.add("The sum of the numbers");
        menu.add("Number location");
    }

    /**
     * Displays the selected figure
     * <p>
     * @param	item
     * @return	true/false
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo a=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String t=item.getTitle().toString();
        if (t.equals("The sum of the numbers")) {
            sum=0;
            for(int i=0;i<=a.position;i++){
                sum=sum+Double.parseDouble(numbers[i]);
            }
            textView2.setText("The sum of the numbers up to the number pressed: "+EditNumber(sum));
            return true;
        }
        else if (t.equals("Number location"))
        {
            textView2.setText("Number location: "+a.position);
            return true;
        }
        return false;
    }
}