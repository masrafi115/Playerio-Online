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
import android.widget.EditText;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
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


public class FilepickerActivity extends  AppCompatActivity  { 
	
	
	private String currentPath = "";
	private double num = 0;
	private String fileType = "";
	private boolean isShow = false;
	
	private ArrayList<String> fileListString = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> gridMap = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private ImageView image_back;
	private TextView text_title;
	private EditText edittext_path;
	private ImageView image_folder;
	private TextView text_folder;
	
	private SharedPreferences data;
	private Intent intent = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.filepicker);
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
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		image_back = (ImageView) findViewById(R.id.image_back);
		text_title = (TextView) findViewById(R.id.text_title);
		edittext_path = (EditText) findViewById(R.id.edittext_path);
		image_folder = (ImageView) findViewById(R.id.image_folder);
		text_folder = (TextView) findViewById(R.id.text_folder);
		data = getSharedPreferences("musicgarage", Activity.MODE_PRIVATE);
		
		image_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				onBackPressed();
			}
		});
		
		text_title.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				text_title.setVisibility(View.GONE);
				edittext_path.setVisibility(View.VISIBLE);
				edittext_path.requestFocus();
				android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(edittext_path, android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT);
				isShow = true;
			}
		});
	}
	
	private void initializeLogic() {
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
		image_back.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("0", 1024, 1024));
		Glide.with(getApplicationContext()).load(Uri.parse("0")).into(image_back);
		fileType = getIntent().getStringExtra("fileType");
		_Elevation(linear1, 10);
		_circleRipple("#E0E0E0", image_back);
		image_back.setImageResource(R.drawable.back_arrow_white);
		text_title.setEllipsize(TextUtils.TruncateAt.END);
		linear3.setVisibility(View.GONE);
		edittext_path.setVisibility(View.GONE);
		text_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		edittext_path.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_folder.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		image_folder.setColorFilter(0xFF212121, PorterDuff.Mode.MULTIPLY);
		edittext_path.setOnEditorActionListener(new EditText.OnEditorActionListener() { public boolean onEditorAction(TextView _v, int _actionId, KeyEvent _event) {
				if (_actionId == android.view.inputmethod.EditorInfo.IME_ACTION_DONE) {
					if (edittext_path.getText().toString().length() > 7) {
						if (edittext_path.getText().toString().substring((int)(0), (int)(8)).equals("/storage")) {
							_setPath(edittext_path.getText().toString());
						}
						else {
							edittext_path.setText(currentPath);
						}
					}
					else {
						edittext_path.setText(currentPath);
					}
					edittext_path.setVisibility(View.GONE);
					text_title.setVisibility(View.VISIBLE);
					isShow = false;
					_abandonFocus();
					return true;
				};
				return false;
			}
		});
		grid = new GridView(FilepickerActivity.this);
				
				grid.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.WRAP_CONTENT));
				
				grid.setNumColumns(GridView.AUTO_FIT);
				
				grid.setVerticalSpacing(2);
				
				grid.setHorizontalSpacing(2);
		
		     grid.setColumnWidth((int)SketchwareUtil.getDip(getApplicationContext(), (int)118));
				
				grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
				
				grid.setAdapter(new Gridview1Adapter(gridMap));
				
				linear2.addView(grid);
		
				grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						  @Override
						  public void onItemClick(AdapterView parent, View view, int _pos, long id) {
				_itemClicked(_pos);
			}
				});
		
		_setPath(FileUtil.getExternalStorageDir());
		}
		
		private GridView grid;
		
		
		public class Gridview1Adapter extends BaseAdapter {
						ArrayList<HashMap<String, Object>> _data;
						public Gridview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
									_data = _arr;
						}
						
						@Override
						public int getCount() {
									return _data.size();
						}
						
						@Override
						public HashMap<String, Object> getItem(int _index) {
									return _data.get(_index);
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
												_v = _inflater.inflate(R.layout.list_file, null);
									}
									
									ImageView image_grid = (ImageView) _v.findViewById(R.id.image_grid);
			
									TextView text_grid = (TextView) _v.findViewById(R.id.text_grid);
			
			LinearLayout linear_grid = (LinearLayout) _v.findViewById(R.id.linear_grid);
			
			linear_grid.setElevation(10);
			image_grid.setColorFilter(Color.parseColor("#212121"), PorterDuff.Mode.MULTIPLY);
			
			if (gridMap.get((int)_position).get("type").toString().equals("folder")) {
				image_grid.setImageResource(R.drawable.folder_white);
				image_grid.setAlpha((float)(1.0));
			}
			else {
				image_grid.setImageResource(R.drawable.file_white);
				image_grid.setAlpha((float)(0.5));
				if (gridMap.get((int)_position).get("type").toString().equals("image")) {
					image_grid.clearColorFilter();
					com.bumptech.glide.Glide.with(getApplicationContext()).load(gridMap.get((int)_position).get("path").toString()).into(image_grid);
					if (fileType.equals("image/*")) {
						image_grid.setAlpha((float)(1.0));
					}
				}
				else {
					if (gridMap.get((int)_position).get("type").toString().equals("audio")) {
						image_grid.setImageResource(R.drawable.music_note_white);
						image_grid.setColorFilter(Color.parseColor("#F44336"), PorterDuff.Mode.MULTIPLY);
						if (fileType.equals("audio/*")) {
							image_grid.setAlpha((float)(1.0));
						}
					}
				}
			}
			
			text_grid.setText(Uri.parse(gridMap.get((int)_position).get("path").toString()).getLastPathSegment());
			
			text_grid.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
									
									return _v;
						}
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
		if (isShow) {
			edittext_path.setText(currentPath);
			edittext_path.setVisibility(View.GONE);
			text_title.setVisibility(View.VISIBLE);
			isShow = false;
		}
		else {
			if (currentPath.equals("/storage")) {
				finish();
			}
			else {
				_setPath(currentPath.substring((int)(0), (int)(currentPath.lastIndexOf("/"))));
			}
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
	}
	
	@Override
	public void onPause() {
		super.onPause();
		
	}
	
	@Override
	public void onStop() {
		super.onStop();
		
	}
	public void _setPath (final String _filePath) {
		fileListString.clear();
		gridMap.clear();
		currentPath = _filePath;
		text_title.setText(_filePath);
		edittext_path.setText(_filePath);
		FileUtil.listDir(_filePath, fileListString);
		java.util.Collections.sort(fileListString);
		if (fileListString.size() > 0) {
			num = 0;
			linear2.setVisibility(View.VISIBLE);
			linear3.setVisibility(View.GONE);
			for(int _repeat13 = 0; _repeat13 < (int)(fileListString.size()); _repeat13++) {
				{
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("path", fileListString.get((int)(num)));
					gridMap.add(_item);
				}
				
				if (FileUtil.isDirectory(fileListString.get((int)(num)))) {
					gridMap.get((int)gridMap.size() - 1).put("type", "folder");
				}
				else {
					if (FileUtil.isFile(fileListString.get((int)(num))) && (fileListString.get((int)(num)).endsWith(".png") || (fileListString.get((int)(num)).endsWith(".jpg") || (fileListString.get((int)(num)).endsWith(".jpeg") || (fileListString.get((int)(num)).endsWith(".gif") || (fileListString.get((int)(num)).endsWith(".bmp") || fileListString.get((int)(num)).endsWith(".webp"))))))) {
						gridMap.get((int)gridMap.size() - 1).put("type", "image");
					}
					else {
						if (FileUtil.isFile(fileListString.get((int)(num))) && (fileListString.get((int)(num)).endsWith(".m4a") || (fileListString.get((int)(num)).endsWith(".aac") || (fileListString.get((int)(num)).endsWith(".flac") || (fileListString.get((int)(num)).endsWith(".gsm") || (fileListString.get((int)(num)).endsWith(".mid") || (fileListString.get((int)(num)).endsWith(".xmf") || (fileListString.get((int)(num)).endsWith(".mxmf") || (fileListString.get((int)(num)).endsWith(".rtttl") || (fileListString.get((int)(num)).endsWith(".ota") || (fileListString.get((int)(num)).endsWith(".imy") || (fileListString.get((int)(num)).endsWith(".mp3") || (fileListString.get((int)(num)).endsWith(".wav") || fileListString.get((int)(num)).endsWith(".ogg")))))))))))))) {
							gridMap.get((int)gridMap.size() - 1).put("type", "audio");
						}
						else {
							gridMap.get((int)gridMap.size() - 1).put("type", "file");
						}
					}
				}
				if (num == (fileListString.size() - 1)) {
					grid.setAdapter(new Gridview1Adapter(gridMap));
				}
				num++;
			}
		}
		else {
			linear2.setVisibility(View.GONE);
			linear3.setVisibility(View.VISIBLE);
			grid.setAdapter(new Gridview1Adapter(gridMap));
		}
	}
	
	
	public void _circleRipple (final String _color, final View _v) {
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_color)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , null, null);
		_v.setBackground(ripdrb);
	}
	
	
	public void _itemClicked (final double _position) {
		if (gridMap.get((int)_position).get("type").toString().equals("folder")) {
			_setPath(gridMap.get((int)_position).get("path").toString());
		}
		else {
			if (fileType.equals("image/*") && gridMap.get((int)_position).get("type").toString().equals("image")) {
				intent.setClass(getApplicationContext(), CropActivity.class);
				intent.putExtra("path", gridMap.get((int)_position).get("path").toString());
				startActivity(intent);
				finish();
			}
			if (fileType.equals("audio/*") && gridMap.get((int)_position).get("type").toString().equals("audio")) {
				data.edit().putString("filePath", gridMap.get((int)_position).get("path").toString()).commit();
				finish();
			}
		}
	}
	
	
	public void _abandonFocus () {
		View _tmpView = this.getCurrentFocus();
		if (_tmpView != null) {
			android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(_tmpView.getWindowToken(), 0);
		}
	}
	
	
	public void _Elevation (final View _view, final double _number) {
		
		_view.setElevation((int)_number);
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