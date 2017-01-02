package com.example.nz160.tejaswini;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nz160 on 18-03-2016.
 */
public class Price extends Fragment {
    RelativeLayout linearLayout;
    private List<SetterGetter> product=new ArrayList<>();;
    public static Button register;
    public static double price=0.00;
    private int count=0;
	public static CheckBox corporate_checkbox;
	public static CheckBox live_checkbox;
	public static CheckBox recorded_checkbox;
	public static CheckBox corp_rec_checkbox;
	private String[] live_price_str ;
	private String[] corporate_price_str ;
	private String[] rec_price_str ;
	private String[] corporate_rec_price_str ;
	private String live_str="null";
	private String corp_rec_str="null";
	private String corp_live_str="null";
	private String rec_str="null";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.activity_prize, container, false);
		try {
			live_price_str = new String[3];
			corporate_price_str = new String[3];
			rec_price_str = new String[3];
			corporate_rec_price_str = new String[3];
			//Initializing the price variable so that the price saved will be reinitialized
			price = 0.00;

			linearLayout = (RelativeLayout) v.findViewById(R.id.prize_layout);
			product = ServerConnection.getProductPrice(ServerConnection.product_price_array);
			live_checkbox = (CheckBox) v.findViewById(R.id.live_checkBox);
			recorded_checkbox = (CheckBox) v.findViewById(R.id.recorded_checkBox);
			corporate_checkbox = (CheckBox) v.findViewById(R.id.corporate_live_detail_checkBox);
			corp_rec_checkbox = (CheckBox) v.findViewById(R.id.corp_recorded_checkBox);
			System.out.println("PRICEPRODUCTID " + product);
			for (int i = 0; i < product.size(); i++) {
				System.out.println("PRICEPRODUCTID " + product.get(i).getPrice());
				String product_id = product.get(i).getproductId();
				if (product_id.substring(product_id.length() - 5).endsWith("_LIVE")) {
					corp_live_str = product.get(i).getPrice();
					corporate_price_str = corp_live_str.split("\\.");
					System.out.println("PRICEPRODUCTID corporate_price_str " + product_id +
							" " + corporate_price_str[0]);
				} else if (product_id.substring(product_id.length() - 5).endsWith("LIVE")) {
					live_str = product.get(i).getPrice();
					live_price_str = live_str.split("\\.");
					System.out.println("PRICEPRODUCTID live_price_str " + product_id +
							" " + live_price_str[0]);
				} else if (product_id.substring(product_id.length() - 5).endsWith("REC")) {
					rec_str = product.get(i).getPrice();
					rec_price_str = rec_str.split("\\.");
					System.out.println("PRICEPRODUCTID rec_price_str " + product_id +
							" " + rec_price_str[0]);
				} else if (product_id.substring(product_id.length() - 5).endsWith("REC_2")) {
					corp_rec_str = product.get(i).getPrice();
					corporate_rec_price_str = corp_rec_str.split("\\.");
					System.out.println("PRICEPRODUCTID corp_rec_price_str " + product_id
							+ " " + corporate_rec_price_str[0]);
				}
			}

			TextView live_price = (TextView) v.findViewById(R.id.live_prise);
			if (!live_str.equals("null")) {
				live_price.setText("$" + live_price_str[0]);

				live_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						System.out.println("ISCHECKEDPRICE " + corporate_checkbox.isChecked());
						if (!corporate_checkbox.isChecked()) {
							if (live_checkbox.isChecked()) {
								price = price + Double.parseDouble(live_price_str[0]);
							} else {
								price = price - Double.parseDouble(live_price_str[0]);
							}
						} else {
							Toast.makeText(getActivity(), "Corporate price is selected. Uncheck live ",
									Toast.LENGTH_LONG).show();
							live_checkbox.setChecked(false);

						}
					}
				});

			} else {
				RelativeLayout live_price_rel = (RelativeLayout) v.findViewById(R.id.live_prize);
				live_price_rel.setVisibility(View.GONE);
			}

			TextView recorded = (TextView) v.findViewById(R.id.recorded_prise);
			if (!rec_str.equals("null")) {
				recorded.setText("$" + rec_price_str[0]);
				recorded_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if (!corp_rec_checkbox.isChecked()) {
							if (recorded_checkbox.isChecked()) {
								price = price + Double.parseDouble(rec_price_str[0]);
							} else {
								price = price - Double.parseDouble(rec_price_str[0]);
							}
						} else {
							Toast.makeText(getActivity(), "Corporate price is selected. Uncheck live ",
									Toast.LENGTH_LONG).show();
							recorded_checkbox.setChecked(false);
						}
						System.out.println("PRICEEE " + price);

					}

				});
			} else {
				RelativeLayout rec_price_rel = (RelativeLayout) v.findViewById(R.id.recorded_prize);
				rec_price_rel.setVisibility(View.GONE);
			}

			TextView corporate = (TextView) v.findViewById(R.id.corporate_live_prise);
			if (!corp_live_str.equals("null")) {
				corporate.setText("$" + corporate_price_str[0]);

				corporate_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

						if (!live_checkbox.isChecked()) {
							if (corporate_checkbox.isChecked()) {
								price = price + Double.parseDouble(corporate_price_str[0]);
							} else {
								price = price - Double.parseDouble(corporate_price_str[0]);

							}
							System.out.println("PRICEEE " + price + " " + live_checkbox.isChecked());
						} else {
							corporate_checkbox.setChecked(false);
							Toast.makeText(getActivity(), "Live price is selected. Uncheck live ",
									Toast.LENGTH_LONG).show();
						}
					}
				});
			} else {
				RelativeLayout corp_live_price_rel = (RelativeLayout) v.findViewById(R.id.corporate_live_prize);
				corp_live_price_rel.setVisibility(View.GONE);
			}

			TextView corp_rec = (TextView) v.findViewById(R.id.corp_recorded_prise);
			if (!corp_rec_str.equals("null")) {
				corp_rec.setText("$" + corporate_rec_price_str[0]);
				corp_rec_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

						if (!recorded_checkbox.isChecked()) {
							if (corp_rec_checkbox.isChecked()) {
								price = price + Double.parseDouble(corporate_rec_price_str[0]);
							} else {
								price = price - Double.parseDouble(corporate_rec_price_str[0]);
							}
							System.out.println("PRICEEE " + price + " " + live_checkbox.isChecked());
						} else {
							corp_rec_checkbox.setChecked(false);
							Toast.makeText(getActivity(), "Recorded price is selected. Uncheck Recorded ",
									Toast.LENGTH_LONG).show();
						}
					}
				});
			} else {
				RelativeLayout corp_live_price_rel = (RelativeLayout) v.findViewById(R.id.corp_rec);
				corp_live_price_rel.setVisibility(View.GONE);
			}
			System.out.println("PRICEEE " + price);
		}catch (Exception e){
			System.out.println("EXCEPTION OCCURED PRICE "+e.toString());
		}
		return v;
       
    }
}
