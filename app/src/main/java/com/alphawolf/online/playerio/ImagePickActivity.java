package com.alphawolf.online.playerio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import com.bumptech.glide.Glide;
import android.graphics.Typeface;
import com.facebook.ads.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class ImagePickActivity extends  AppCompatActivity  { 
	
	
	private String imageFormats = "";
	private boolean inFolder = false;
	private String a = "";
	private String folderPaths = "";
	private double n1 = 0;
	private double selected = 0;
	
	private ArrayList<HashMap<String, Object>> itemsList = new ArrayList<>();
	private ArrayList<String> cache = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout action_bar;
	private LinearLayout linear2;
	private ImageView imageview1;
	private LinearLayout linear3;
	private TextView text_title;
	private TextView text_path_name;
	private TextView textview1;
	
	private Intent intent = new Intent();
	private SharedPreferences data;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.image_pick);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		action_bar = (LinearLayout) findViewById(R.id.action_bar);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		text_title = (TextView) findViewById(R.id.text_title);
		text_path_name = (TextView) findViewById(R.id.text_path_name);
		textview1 = (TextView) findViewById(R.id.textview1);
		data = getSharedPreferences("musicgarage", Activity.MODE_PRIVATE);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				onBackPressed();
			}
		});
	}
	
	private void initializeLogic() {
		text_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_path_name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		imageFormats = "|.jpeg|.bmp|.png|.jpg|";
		inFolder = false;
		getAllShownImagesPath(this);
		gridview1 = new android.widget.GridView(ImagePickActivity.this);
		
		gridview1.setLayoutParams(new LinearLayout.LayoutParams(-1,-1));
		
		gridview1.setAdapter(new CustomGridViewAdapter(itemsList));
		
		gridview1.setNumColumns(2);
		
		gridview1.setStretchMode(android.widget.GridView.STRETCH_COLUMN_WIDTH);
		
		gridview1.setGravity(Gravity.CENTER);
		
		gridview1.setBackgroundColor(Color.WHITE);
		
		linear2.addView(gridview1);
		
		gridview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View b, int c, long d) {
				_onItemClick(c);
			}
		});
		textview1.setVisibility(View.GONE);
		_circleRipple("#E0E0E0", imageview1);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (inFolder) {
			folderPaths = "";
			inFolder = false;
			itemsList.clear();
			getAllShownImagesPath(ImagePickActivity.this);
			((BaseAdapter)gridview1.getAdapter()).notifyDataSetChanged();
			gridview1.setVisibility(View.VISIBLE);
			textview1.setVisibility(View.GONE);
			text_path_name.setText("Folder");
		}
		else {
			finish();
		}
	}
	public void _init () {
	}
	private android.widget.GridView gridview1;
	public void getAllShownImagesPath(Activity activity) {
		
		android.net.Uri uri;
		android.database.Cursor cursor;
		int column_index_data, column_index_folder_name;
		
		String absolutePathOfImage = null;
		
		uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI; String[] projection = { android.provider.MediaStore.MediaColumns.DATA, android.provider.MediaStore.Images.Media.BUCKET_DISPLAY_NAME };
		
		cursor = activity.getContentResolver().query(uri, projection, null, null, null); 
		
		column_index_data = cursor.getColumnIndexOrThrow(android.provider.MediaStore.MediaColumns.DATA);
		
		column_index_folder_name = cursor .getColumnIndexOrThrow(android.provider.MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
		
		while (cursor.moveToNext()) {
			absolutePathOfImage = cursor.getString(column_index_data);
			
			_filterToFolder(absolutePathOfImage);
		};
	}
	public class CustomGridViewAdapter extends BaseAdapter {
				ArrayList<HashMap<String, Object>> _data;
				public CustomGridViewAdapter(ArrayList<HashMap<String, Object>> _arr) {
						_data = _arr;
				}
				
				@Override
				public int getCount() {
						return _data.size();
				}
				
		
		@Override
		public Object getItem(int position) {
			return _data.get(position);
		}
		
				@Override
				public long getItemId(int _index) {
						return _index;
				}
				
				@Override
				public View getView(final int _position, View _view, ViewGroup _viewGroup) {
						LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
						View _v = _view;
						if (_v == null) {
								_v = _inflater.inflate(R.layout.grid_item, null);
						}
						
						final LinearLayout linear1 = (LinearLayout) _v.findViewById(R.id.linear1);
						final LinearLayout linear2 = (LinearLayout) _v.findViewById(R.id.linear2);
						final ImageView imageview1 = (ImageView) _v.findViewById(R.id.imageview1);
						final TextView textview1 = (TextView) _v.findViewById(R.id.textview1);
						textview1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
			
			_applySetItem(_position, linear1, linear2, imageview1, textview1);
			
						
						
						return _v;
				}
		}
	{
	}
	
	
	public void _filterToFolder (final String _path) {
		a = _path.substring((int)(0), (int)(_path.lastIndexOf("/")));
		if (folderPaths.contains(":".concat(a.concat(":")))) {
			
		}
		else {
			folderPaths = folderPaths.concat(":".concat(a.concat(":")));
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("type", "folder");
				itemsList.add(_item);
			}
			
			itemsList.get((int)itemsList.size() - 1).put("path", a);
			itemsList.get((int)itemsList.size() - 1).put("name", Uri.parse(a).getLastPathSegment());
			itemsList.get((int)itemsList.size() - 1).put("icon", _path);
		}
	}
	
	
	public void _loadFolder (final String _path) {
		n1 = 0;
		cache.clear();
		itemsList.clear();
		FileUtil.listDir(_path, cache);
		if (cache.size() > 0) {
			textview1.setVisibility(View.GONE);
			gridview1.setVisibility(View.VISIBLE);
			for(int _repeat22 = 0; _repeat22 < (int)(cache.size()); _repeat22++) {
				if (FileUtil.isFile(cache.get((int)(n1))) && (cache.get((int)(n1)).contains(".") && imageFormats.contains("|".concat(cache.get((int)(n1)).substring((int)(cache.get((int)(n1)).lastIndexOf(".")), (int)(cache.get((int)(n1)).length())).concat("|"))))) {
					{
						HashMap<String, Object> _item = new HashMap<>();
						_item.put("type", "file");
						itemsList.add(_item);
					}
					
					itemsList.get((int)itemsList.size() - 1).put("path", cache.get((int)(n1)));
					itemsList.get((int)itemsList.size() - 1).put("name", Uri.parse(cache.get((int)(n1))).getLastPathSegment());
					itemsList.get((int)itemsList.size() - 1).put("select", "0");
				}
				n1++;
			}
			cache.clear();
		}
		else {
			textview1.setVisibility(View.VISIBLE);
			gridview1.setVisibility(View.GONE);
		}
		((BaseAdapter)gridview1.getAdapter()).notifyDataSetChanged();
		text_path_name.setText(_path);
	}
	
	
	public void _onItemClick (final double _pos) {
		if (itemsList.get((int)_pos).get("type").toString().equals("folder")) {
			_loadFolder(itemsList.get((int)_pos).get("path").toString());
			inFolder = true;
		}
		else {
			intent.setClass(getApplicationContext(), CropActivity.class);
			data.edit().putString("result", itemsList.get((int)_pos).get("path").toString()).commit();
			startActivity(intent);
			finish();
		}
	}
	
	
	public void _circleRipple (final String _color, final View _v) {
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_color)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , null, null);
		_v.setBackground(ripdrb);
	}
	
	
	public void _setImageFromFile (final ImageView _img, final String _path) {
		/*
Glide.with(getApplicationContext()).load(Uri.parse("this block is necessary, don't remove")).into(_img);
*/
		Uri imageUri = Uri.fromFile(new java.io.File(_path));
		
		Glide.with(getApplicationContext ()).load(imageUri).into(_img);
	}
	
	
	public void _applySetItem (final double _pos, final View _a, final View _b, final ImageView _c, final TextView _d) {
		_a.setElevation(9f);
		_removeView(_b);
		_removeView(_c);
		if (itemsList.get((int)_pos).get("type").toString().equals("folder")) {
			if (FileUtil.getFileLength(itemsList.get((int)_pos).get("icon").toString()) > 0) {
				_setImageFromFile(_c, itemsList.get((int)_pos).get("icon").toString());
			}
			else {
				_c.setImageResource(R.drawable.folder_white);
				_c.setColorFilter(0xFFFFD600, PorterDuff.Mode.MULTIPLY);
			}
		}
		else {
			_setImageFromFile(_c, itemsList.get((int)_pos).get("path").toString());
		}
		((LinearLayout)_a).removeAllViews();
		android.widget.RelativeLayout rl = new android.widget.RelativeLayout(ImagePickActivity.this);
		
		rl.setLayoutParams(new LinearLayout.LayoutParams(-1,-1));
		
		rl.addView(_c);
		
		rl.addView(_b);
		
		((LinearLayout)_a).addView(rl);
		_d.setBackgroundColor(Color.parseColor("#65000000"));
		_d.setText(itemsList.get((int)_pos).get("name").toString());
	}
	
	
	public void _removeView (final View _v) {
		if (_v.getParent() != null) {
			
			((ViewGroup)_v.getParent()).removeView(_v);
			
		};
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}