package com.example.nz160.quicksort;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by nz160 on 29-11-2016.
 */
public class Student extends Activity {

    private int[] sciencemarks={ 58,12,13,58,12,58,36};
    private int[] englishmarks={ 58,12,13,58,12,58,36};
    private int[] mathsmarks={ 58,12,13,58,12,58,36};
    private int[] total;
    List<Integer> totalmarks=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);
         String[] row = { "ROW1", "ROW2", "Row3", "Row4", "Row 5", "Row 6",
                "Row 7" };
        String[] column = { "Science","English", "Maths","Total", "Science" };
        int rl=row.length; int cl=column.length;
        total=new int[sciencemarks.length];
        Log.d("--", "R-Lenght--" + rl + "   " + "C-Lenght--" + cl);
        for(int i=0;i<sciencemarks.length;i++){
            total[i]=sciencemarks[i]+englishmarks[i]+mathsmarks[i];
            totalmarks.add(total[i]);

        }
        Collections.sort(totalmarks);
        ScrollView sv = new ScrollView(this);
        TableLayout tableLayout = createTableLayout(row, column,rl, cl);
        HorizontalScrollView hsv = new HorizontalScrollView(this);

        hsv.addView(tableLayout);
        sv.addView(hsv);
        setContentView(sv);

    }

    private TableLayout createTableLayout(String [] rv, String [] cv,int rowCount, int columnCount) {
        // 1) Create a tableLayout and its params
        TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams();
        TableLayout tableLayout = new TableLayout(this);
        tableLayout.setBackgroundColor(Color.BLACK);

        // 2) create tableRow params
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams();
        tableRowParams.setMargins(1, 1, 1, 1);
        tableRowParams.weight = 1;

        for (int i = 0; i < rowCount; i++) {
            // 3) create tableRow
            TableRow tableRow = new TableRow(this);
            tableRow.setBackgroundColor(Color.BLACK);

            for (int j= 0; j < columnCount; j++) {
                // 4) create textView
                TextView textView = new TextView(this);
                //  textView.setText(String.valueOf(j));
                textView.setBackgroundColor(Color.WHITE);
                textView.setGravity(Gravity.CENTER);

                String s1 = Integer.toString(i);
                String s2 = Integer.toString(j);
                String s3 = s1 + s2;
                int id = Integer.parseInt(s3);
                Log.d("TAG", "-___>" + id);
                if (i ==0 && j==0){
                    textView.setText("Name");
                } else if(i==0){
                    Log.d("TAAG", "set Column Headers");
                    textView.setText(cv[j-1]);
                }else if( j==0){
                    Log.d("TAAG", "Set Row Headers");
                    textView.setText(rv[i-1]);
                }else if(j==4) {
                    textView.setText(""+totalmarks.get(i));
                    // check id=23
                } else if(j==3){
                    textView.setText(""+englishmarks[i]);
                }else if(j==2) {
                    textView.setText(""+sciencemarks[i]);
                } else if(j==1) {
                    textView.setText(""+mathsmarks[i]);
                } else{
                    textView.setText("" + id);
                }
                // 5) add textView to tableRow
                tableRow.addView(textView, tableRowParams);
            }
            // 6) add tableRow to tableLayout
            tableLayout.addView(tableRow, tableLayoutParams);
        }

        return tableLayout;
    }
}
