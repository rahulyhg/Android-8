package info.androidhive.volleyexamples;

import info.androidhive.volleyexamples.app.AppController;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class StringRequestActivity extends Activity {

	private String TAG = StringRequestActivity.class.getSimpleName();
	private Button btnStringReq;
	private TextView msgResponse;
	private ProgressDialog pDialog;

	// This tag will be used to cancel the request
	private String tag_string_req = "string_req";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_string);

		btnStringReq = (Button) findViewById(R.id.btnStringReq);


		btnStringReq.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				makeStringReq();
			}
		});
	}
	/**
	 * Making json object request
	 * */
	private void makeStringReq() {

		StringRequest strReq = new StringRequest(Request.Method.POST, "http://android.compliance4all.com/Compliance4AllMain",
				new Response.Listener<String>()
				{
					@Override
					public void onResponse(String response)
					{
						System.out.println("RESPONSEFROMJSONIS "+response);
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
				params.put("email", "null");
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