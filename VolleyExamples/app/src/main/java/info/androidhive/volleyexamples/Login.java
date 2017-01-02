package info.androidhive.volleyexamples;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import info.androidhive.volleyexamples.app.AppController;
import info.androidhive.volleyexamples.utils.ConnectionDetector;
import info.androidhive.volleyexamples.utils.CustomToast;
import info.androidhive.volleyexamples.utils.Utils;

public class Login extends Activity implements OnClickListener {
	private Button btnLogin;
	private String tag_string_req = "string_req";
	private EditText emailid;
	private EditText password;
	private String getEmailId;
	private String getPassword;
	private ConnectionDetector cd;
	private boolean isInternetPresent;
	private RelativeLayout view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		view=(RelativeLayout)findViewById(R.id.login_rel);
		emailid = (EditText) findViewById(R.id.login_emailid);
		password = (EditText) findViewById(R.id.login_password);
		btnLogin = (Button) findViewById(R.id.loginBtn);
		btnLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		validate();
	}

	// Check Validation before login
	private void validate() {

		getEmailId = emailid.getText().toString();
		getPassword = password.getText().toString();

		Pattern p = Pattern.compile(Utils.regEx);
		Matcher m = p.matcher(getEmailId);
		if (getEmailId.equals("") || getEmailId.length() == 0) {

			new CustomToast().Show_Toast(Login.this, view,
					"Enter your Email Id ");

		} else if(getPassword.equals("") || getPassword.length() == 0){
			new CustomToast().Show_Toast(Login.this, view,
					"Enter your Password");
		} else if (!m.find())
			new CustomToast().Show_Toast(Login.this, view,
					"Your Email Id is Invalid.");
		else {
			cd = new ConnectionDetector(this);
			isInternetPresent = cd.isConnectingToInternet();
			if(isInternetPresent) {
				makeLoginReq();
			} else {
				new CustomToast().Show_Toast(Login.this, view,
						"Please check your internet connection");
			}
		}
	}

	private void makeLoginReq() {

		StringRequest strReq = new StringRequest(Request.Method.POST,
				"http://192.168.2.79:8080/Compliance4AllBackend/HomeConnection",
				new Response.Listener<String>()
				{
					@Override
					public void onResponse(String response)
					{
						System.out.println("RESPONSE FROM JSON IS error " + response.toString());
						try {
							Fragment fragment=new Home();
							Bundle bundle=new Bundle();
							bundle.putString("response",response);
							System.out.println("RESPONSE IS "+ bundle.getString("response"));
							fragment.setArguments(bundle);
							FragmentManager fragmentManager = getFragmentManager();
									fragmentManager.beginTransaction()
											.replace(R.id.login_relative, fragment)
											.commit();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				},
				new Response.ErrorListener()
				{
					@Override
					public void onErrorResponse(VolleyError error)
					{
						System.out.println("RESPONSE FROM JSON IS error " + error.toString());
						new CustomToast().Show_Toast(Login.this, view,
								"Server Error");
					}
				})
		{
			@Override
			protected Map<String, String> getParams()
			{
				Map<String, String> params = new HashMap<String, String>();
				params.put("email_id",getEmailId);
				params.put("password", getPassword);
				System.out.println("PARAMS IS "+params.toString());
				return params;
			}
		};

		strReq.setRetryPolicy(new DefaultRetryPolicy(
				10000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
	}
}
