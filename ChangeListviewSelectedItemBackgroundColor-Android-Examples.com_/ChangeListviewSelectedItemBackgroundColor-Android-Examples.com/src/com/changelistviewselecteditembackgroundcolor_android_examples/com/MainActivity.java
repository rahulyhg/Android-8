package com.changelistviewselecteditembackgroundcolor_android_examples.com;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView listView;
	 String[] listValue = new String[] {"Android Examples","Android Studio","SDK Manager","Eclipse"
	 ,"Java","JDK"};
	 
	 @Override
	 protected void onCreate(Bundle savedInstanceState) 
	 {
	 super.onCreate(savedInstanceState);
	 
	 setContentView(R.layout.activity_main);
	 
	 listView = (ListView)findViewById(R.id.listView1);
	 
	 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2, android.R.id.text1, listValue);
	 
	 listView.setAdapter(adapter); 
	 
	 
   }

}