package com.example.nz160.quicksort;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private View addView;
    private LinearLayout mLayout;
    public static List<Integer> sub=new ArrayList<>();
    List<EditText> NameEditTextList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button subject_submit = (Button) findViewById(R.id.subject_submit);

        int[] marks={30,60,82,95,78};
        for(int i=0;i<marks.length;i++) {
            sub.add(marks[i]);
        }
        Collections.sort(sub);
        for (int i=0;i<sub.size();i++){
            System.out.println("SUBJECTS "+sub.get(i));
        }

        /*mLayout = (LinearLayout) findViewById(R.id.subjects);
        final Button add = (Button)findViewById(R.id.add);

        LayoutInflater layoutInflater =
                (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addView = layoutInflater.inflate(R.layout.subject_content, null);
        final EditText subjects = (EditText) addView.findViewById(R.id.subject);
        Button subject_submit = (Button) findViewById(R.id.subject_submit);
        NameEditTextList.add(subjects);
        mLayout.addView(addView);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater =
                        (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.subject_content, null);
                final EditText subjects = (EditText) addView.findViewById(R.id.subject);
                mLayout.addView(addView);
                NameEditTextList.add(subjects);
            }
        });*/

        subject_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSubmit=true;
               /* for (int i = 0; i < NameEditTextList.size(); i++) {
                    String name = NameEditTextList.get(i).getText().toString();
                    System.out.println("NAME IS "+name);
                    if(name.equals("")){
                        Toast.makeText(getApplicationContext(),"Subject cannot be empty",Toast.LENGTH_LONG).show();
                        isSubmit=false;
                    } else {
                        sub.add(name);
                        isSubmit=true;
                    }
                }*/
                if(isSubmit) {
                    Intent intent = new Intent(MainActivity.this, Student.class);
                    startActivity(intent);
                }
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        sub.clear();
    }

    public void makeCellEmpty(TableLayout tableLayout, int rowIndex, int columnIndex) {
        // get row from table with rowIndex
        TableRow tableRow = (TableRow) tableLayout.getChildAt(rowIndex);

        // get cell from row with columnIndex
        TextView textView = (TextView)tableRow.getChildAt(columnIndex);

        // make it black
        textView.setBackgroundColor(Color.BLACK);
    }
    public void setHeaderTitle(TableLayout tableLayout, int rowIndex, int columnIndex){

        // get row from table with rowIndex
        TableRow tableRow = (TableRow) tableLayout.getChildAt(rowIndex);

        // get cell from row with columnIndex
        TextView textView = (TextView)tableRow.getChildAt(columnIndex);

        textView.setText("Hello");
    }


}
