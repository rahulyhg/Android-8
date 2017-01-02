package com.example.nz160.tejaswini;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailViewSpeaker extends Fragment {
    private List<SetterGetter> product=new ArrayList<>( );;
	private boolean isInternetPresent;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_detail_view_speaker, container, false);
		try {
			product = ServerConnection.getProducts(ServerConnection.jsonarray);
			RelativeLayout relativeLayout=(RelativeLayout)v.findViewById(R.id.speaker_profile);
			TextView textView = (TextView) v.findViewById(R.id.speaker_overview);
			textView.setText(android.text.Html.fromHtml(product.get(0).getPersonProfile()).toString());
			String[] img = product.get(0).getPersonProfileImg().split("/");
			ImageView imgview = (ImageView) v.findViewById(R.id.speaker_profile_pic);
			Picasso.with(getActivity())
					.load( "http://www.compliance4all.com"+ "/images/speakers/" + product.get(0).getPersonId() + "/" + img[4])
					.placeholder(R.drawable.ic_launcher)
					.error(R.drawable.no_image)
					.into(imgview);

			TextView speakerView = (TextView) v.findViewById(R.id.speaker_name);
			speakerView.setText("Instructor : " + product.get(0).getPersonName()+" "+product.get(0).getPersonLastName());

			TextView textView1 = (TextView) v.findViewById(R.id.speaker_id);
			textView1.setText(product.get(0).getPersonDesignation());

			textView.setPadding(0, 0, 0, 100);
			textView.setMovementMethod(new ScrollingMovementMethod());

		} catch (Exception e) {
			System.out.println("EXCEPTION OCCURED DetailViewSpeaker "+e.toString());
		}
		return v;
	
    }
}
