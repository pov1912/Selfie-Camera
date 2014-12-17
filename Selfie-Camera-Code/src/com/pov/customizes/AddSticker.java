package com.pov.customizes;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.pov.hd.selfiecamera.MainActivity;
import com.pov.hd.selfiecamera.R;

public class AddSticker extends Activity {
	public static final boolean IS_DEBUG = true;
	private static final String TAG = AddSticker.class.getSimpleName();
	private AdView mAdView;
	AdRequest request;
	public static int[] accessories = { R.drawable.it_b7, R.drawable.f1,
			R.drawable.f2, R.drawable.f3, R.drawable.f4, R.drawable.f5,
			R.drawable.f6, R.drawable.f7, R.drawable.f8 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_accessories);

		mAdView = (AdView) findViewById(R.id.adView);
		if (IS_DEBUG) {
			request = new AdRequest.Builder().addTestDevice(
					"9E30721B85A31BB430F61FFDE8D2ECA4").build();
		} else {
			request = new AdRequest.Builder().build();
		}
		mAdView.loadAd(request);

		List<Item> list = new ArrayList<Item>();
		for (int i = 0; i < accessories.length; i++) {
			list.add(new Item(accessories[i]));
		}

		MyAdapter myAdapter = new MyAdapter(this,
				R.layout.list_accessories_item, list);
		GridView listView = (GridView) findViewById(android.R.id.list);
		listView.setAdapter(myAdapter);
		listView.setOnItemClickListener(listener);
	}

	private OnItemClickListener listener = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			try {
				MainActivity.stickerIndex = position;
				AddSticker.this.finish();
			} catch (NullPointerException e) {
				Log.d(TAG, "Error occured, use default");
			}
			Intent intent = new Intent();
			setResult(RESULT_OK, intent);
			finish();
		}
	};

	/**
	 * Adapter for list view.
	 * */
	private class MyAdapter extends ArrayAdapter<Item> {

		public MyAdapter(Context context, int textViewResourceId,
				List<Item> objects) {
			super(context, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			if (row == null) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(R.layout.list_accessories_item, parent,
						false);

				ViewHolder holder = new ViewHolder();
				holder.imgView = (ImageView) row.findViewById(R.id.imageView);
				row.setTag(holder);
			}

			Item item = getItem(position);
			ViewHolder holder = (ViewHolder) row.getTag();
			holder.imgView.setImageResource(item.getId());

			return row;
		}

		private class ViewHolder {
			private ImageView imgView;
		}
	}
}
