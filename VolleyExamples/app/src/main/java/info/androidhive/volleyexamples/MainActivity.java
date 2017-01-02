package info.androidhive.volleyexamples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import info.androidhive.volleyexamples.app.AppController;
import info.androidhive.volleyexamples.utils.ConnectionDetector;
import info.androidhive.volleyexamples.utils.Utils;

public class MainActivity extends Activity implements OnClickListener {
	private Button btnJson, btnString, btnImage;
	private String tag_string_req = "string_req";
	private EditText emailid;
	private EditText password;
	private String getEmailId;
	private String getPassword;
	private ConnectionDetector cd;
	private boolean isInternetPresent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		emailid = (EditText) findViewById(R.id.login_emailid);
		password = (EditText) findViewById(R.id.login_password);
		btnString = (Button) findViewById(R.id.loginBtn);
		btnString.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		validate();
	}

	// Check Validation before login
	private void validate() {
		System.out.println("CHECKINGFLOW " + " checkValidation");

		// Get email id and password
		getEmailId = emailid.getText().toString();
		getPassword = password.getText().toString();

		// Check patter for email id
		Pattern p = Pattern.compile(Utils.regEx);

		Matcher m = p.matcher(getEmailId);

		// Check for both field is empty or not
		if (getEmailId.equals("") || getEmailId.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0) {
			Toast.makeText(getApplicationContext(),"Enter Both the credentials",Toast.LENGTH_LONG).show();

		}
		// Check if email id is valid or not
		else if (!m.find())
			Toast.makeText(getApplicationContext(),"Your Email Id is Invalid.",Toast.LENGTH_LONG).show();
			// Else do login and do your stuff
		else {
			cd = new ConnectionDetector(this);
			isInternetPresent = cd.isConnectingToInternet();
			if(isInternetPresent) {
				makeLoginReq();
			}


		}
	}
	private void makeLoginReq() {

		StringRequest strReq = new StringRequest(Request.Method.POST, "http://android.compliance4all.com/Login",
				new Response.Listener<String>()
				{
					@Override
					public void onResponse(String response)
					{
						System.out.println("RESPONSEFROMJSONIS "+response);
						Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
					}
				},
				new Response.ErrorListener()
				{
					@Override
					public void onErrorResponse(VolleyError error)
					{
						System.out.println("RESPONSEFROMJSONIS error "+error.toString());

					}
				})
		{
			@Override
			protected Map<String, String> getParams()
			{
				Map<String, String> params = new HashMap<String, String>();
				params.put("email_id", getEmailId);
				params.put("password", getPassword);
				System.out.println("PARAMSIS "+params.toString());
				return params;
			}
		};
		strReq.setRetryPolicy(new DefaultRetryPolicy(
				10000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

	}
}
