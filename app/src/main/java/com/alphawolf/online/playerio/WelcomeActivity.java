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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.View;
import android.graphics.Typeface;
import com.facebook.ads.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class WelcomeActivity extends  AppCompatActivity  { 
	
	
	private LinearLayout linear_main;
	private LinearLayout linear_top;
	private LinearLayout linear_bottom;
	private LinearLayout linear_heading;
	private LinearLayout linearall;
	private LinearLayout linear5;
	private TextView music;
	private TextView garage;
	private LinearLayout tabslinear;
	private LinearLayout linearalltabs;
	private LinearLayout linear6;
	private LinearLayout base;
	private LinearLayout trash;
	private LinearLayout layout1;
	private LinearLayout layout2;
	private LinearLayout layout3;
	private LinearLayout layout4;
	private ImageView imageview1;
	private TextView text_heading1;
	private TextView text_msg1;
	private ImageView imageview2;
	private TextView text_heading2;
	private TextView text_msg2;
	private ImageView imageview3;
	private TextView text_heading3;
	private TextView text_msg3;
	private ImageView imageview4;
	private TextView text_heading4;
	private TextView text_msg4;
	private ImageView dot1;
	private ImageView dot2;
	private ImageView dot3;
	private ImageView dot4;
	private LinearLayout linear9;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private TextView text_skip;
	private TextView textview_next;
	
	private Intent intent = new Intent();
	private SharedPreferences data;
	private ObjectAnimator obj = new ObjectAnimator();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.welcome);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear_main = (LinearLayout) findViewById(R.id.linear_main);
		linear_top = (LinearLayout) findViewById(R.id.linear_top);
		linear_bottom = (LinearLayout) findViewById(R.id.linear_bottom);
		linear_heading = (LinearLayout) findViewById(R.id.linear_heading);
		linearall = (LinearLayout) findViewById(R.id.linearall);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		music = (TextView) findViewById(R.id.music);
		garage = (TextView) findViewById(R.id.garage);
		tabslinear = (LinearLayout) findViewById(R.id.tabslinear);
		linearalltabs = (LinearLayout) findViewById(R.id.linearalltabs);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		base = (LinearLayout) findViewById(R.id.base);
		trash = (LinearLayout) findViewById(R.id.trash);
		layout1 = (LinearLayout) findViewById(R.id.layout1);
		layout2 = (LinearLayout) findViewById(R.id.layout2);
		layout3 = (LinearLayout) findViewById(R.id.layout3);
		layout4 = (LinearLayout) findViewById(R.id.layout4);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		text_heading1 = (TextView) findViewById(R.id.text_heading1);
		text_msg1 = (TextView) findViewById(R.id.text_msg1);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		text_heading2 = (TextView) findViewById(R.id.text_heading2);
		text_msg2 = (TextView) findViewById(R.id.text_msg2);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		text_heading3 = (TextView) findViewById(R.id.text_heading3);
		text_msg3 = (TextView) findViewById(R.id.text_msg3);
		imageview4 = (ImageView) findViewById(R.id.imageview4);
		text_heading4 = (TextView) findViewById(R.id.text_heading4);
		text_msg4 = (TextView) findViewById(R.id.text_msg4);
		dot1 = (ImageView) findViewById(R.id.dot1);
		dot2 = (ImageView) findViewById(R.id.dot2);
		dot3 = (ImageView) findViewById(R.id.dot3);
		dot4 = (ImageView) findViewById(R.id.dot4);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		text_skip = (TextView) findViewById(R.id.text_skip);
		textview_next = (TextView) findViewById(R.id.textview_next);
		data = getSharedPreferences("musicgarage", Activity.MODE_PRIVATE);
		
		linear9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_clickAnimation(linear9);
				viewPager.setCurrentItem(3);
			}
		});
		
		linear8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (viewPager.getCurrentItem() == 0) {
					viewPager.setCurrentItem(1);
				}
				else {
					if (viewPager.getCurrentItem() == 1) {
						viewPager.setCurrentItem(2);
					}
					else {
						if (viewPager.getCurrentItem() == 2) {
							viewPager.setCurrentItem(3);
						}
						else {
							if (viewPager.getCurrentItem() == 3) {
								_clickAnimation(linear8);
								intent.setClass(getApplicationContext(), LoginActivity.class);
								data.edit().putString("welcome", "1").commit();
								startActivity(intent);
								finish();
							}
						}
					}
				}
			}
		});
	}
	
	private void initializeLogic() {
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
		Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  window.setNavigationBarColor(Color.parseColor("#FFFFFF"));
		_fonts();
		_AdvancedCorners(linear_top, "#212121", "#212121", 0, 0, 60, 60);
		_Elevation(linear_top, 10);
		_AdvancedCorners(linearall, "#FFFFFF", "#FFFFFF", 50, 50, 0, 0);
		_AdvancedCorners(linear5, "#FFFFFF", "#FFFFFF", 0, 0, 50, 50);
		_setImageViewRipple(dot1, "#212121", "#E0E0E0");
		_setImageViewRipple(dot2, "#E0E0E0", "#E0E0E0");
		_setImageViewRipple(dot3, "#E0E0E0", "#E0E0E0");
		_setImageViewRipple(dot4, "#E0E0E0", "#E0E0E0");
		obj.setTarget(linear_heading);
		obj.setPropertyName("alpha");
		obj.setFloatValues((float)(0.5d), (float)(1.0d));
		obj.setInterpolator(new AccelerateInterpolator());
		obj.setDuration((int)(500));
		obj.start();
		_setBackground(linear8, 20, 0, "#FFFFFF", true);
		_setBackground(linear9, 20, 0, "#FFFFFF", true);
		_viewPager();
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
		
	}
	public void _AdvancedCorners (final View _view, final String _color1, final String _color2, final double _tl, final double _tr, final double _bl, final double _br) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setStroke(2, Color.parseColor(_color2));
		
		gd.setCornerRadii(new float[]{(int)_tl,(int)_tl,(int)_tr,(int)_tr,(int)_bl,(int)_bl,(int)_br,(int)_br});
		
		_view.setBackground(gd);
	}
	
	
	public void _setImageViewRipple (final ImageView _imageview, final String _color1, final String _color2) {
		_imageview.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor(_color1), Color.parseColor(_color2)}));
	}
	
	
	public void _rippleRoundStroke (final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#FF757575")}), GG, null);
		_view.setBackground(RE);
		_view.setElevation(5);
	}
	
	
	public void _clickAnimation (final View _view) {
		ScaleAnimation fade_in = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
		fade_in.setDuration(300);
		fade_in.setFillAfter(true);
		_view.startAnimation(fade_in);
	}
	
	
	public void _viewPager () {
		viewPager = new androidx.viewpager.widget.ViewPager(this);
		
		viewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		MyPagerAdapter adapter = new MyPagerAdapter();
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(0);
		base.addView(viewPager);
		viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
		
		viewPager.addOnPageChangeListener(new androidx.viewpager.widget.ViewPager.OnPageChangeListener() {
			public void onPageSelected(int position) {
				
				if (viewPager.getCurrentItem() == 0) {
					_TransitionManager(linear5, 200);
					_setImageViewRipple(dot1, "#212121", "#E0E0E0");
					_setImageViewRipple(dot2, "#E0E0E0", "#E0E0E0");
					_setImageViewRipple(dot3, "#E0E0E0", "#E0E0E0");
					_setImageViewRipple(dot4, "#E0E0E0", "#E0E0E0");
					_TransitionManager(linear_bottom, 200);
					text_skip.setVisibility(View.VISIBLE);
					textview_next.setText("NEXT");
				}
				else {
					if (viewPager.getCurrentItem() == 1) {
						_TransitionManager(linear5, 200);
						_setImageViewRipple(dot2, "#212121", "#E0E0E0");
						_setImageViewRipple(dot1, "#E0E0E0", "#E0E0E0");
						_setImageViewRipple(dot3, "#E0E0E0", "#E0E0E0");
						_setImageViewRipple(dot4, "#E0E0E0", "#E0E0E0");
						_TransitionManager(linear_bottom, 200);
						text_skip.setVisibility(View.VISIBLE);
						textview_next.setText("NEXT");
					}
					else {
						if (viewPager.getCurrentItem() == 2) {
							_TransitionManager(linear5, 200);
							_setImageViewRipple(dot3, "#212121", "#E0E0E0");
							_setImageViewRipple(dot1, "#E0E0E0", "#E0E0E0");
							_setImageViewRipple(dot2, "#E0E0E0", "#E0E0E0");
							_setImageViewRipple(dot4, "#E0E0E0", "#E0E0E0");
							_TransitionManager(linear_bottom, 200);
							text_skip.setVisibility(View.VISIBLE);
							textview_next.setText("NEXT");
						}
						else {
							if (viewPager.getCurrentItem() == 3) {
								_TransitionManager(linear5, 200);
								_setImageViewRipple(dot4, "#212121", "#E0E0E0");
								_setImageViewRipple(dot1, "#E0E0E0", "#E0E0E0");
								_setImageViewRipple(dot2, "#E0E0E0", "#E0E0E0");
								_setImageViewRipple(dot3, "#E0E0E0", "#E0E0E0");
								_TransitionManager(linear_bottom, 200);
								text_skip.setVisibility(View.GONE);
								textview_next.setText("START NOW");
							}
						}
					}
				}
			}
			@Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				
			}
			@Override public void onPageScrollStateChanged(int state) {
				
			}
		});
		
		
		
		tabLayout = new com.google.android.material.tabs.TabLayout(this);
		tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);
	}
	
	private class MyPagerAdapter extends androidx.viewpager.widget.PagerAdapter {
		public int getCount() {
			return 4;
		}
		
		@Override public Object instantiateItem(ViewGroup collection, int position) {
			
			LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = inflater.inflate(R.layout.empty, null);
			
			LinearLayout container = (LinearLayout) v.findViewById(R.id.linear1);
			
			if (position == 0) {
				ViewGroup parent = (ViewGroup) layout1.getParent();
				if (parent != null) {
					parent.removeView(layout1);
				}container.addView(layout1);
				
			} else if (position == 1) {
				ViewGroup parent = (ViewGroup) layout2.getParent();
				if (parent != null) {
					parent.removeView(layout2);
				}
				container.addView(layout2);
				
			} else if (position == 2) {
				ViewGroup parent = (ViewGroup) layout3.getParent();
				if (parent != null) {
					parent.removeView(layout3);
				}
				container.addView(layout3);
				
			} else if (position == 3) {
				ViewGroup parent = (ViewGroup) layout4.getParent();
				if (parent != null) {
					parent.removeView(layout4);
				}
				container.addView(layout4);
			}
			collection.addView(v, 0);
			return v;
		}
		@Override public void destroyItem(ViewGroup collection, int position, Object view) {
			collection.removeView((View) view);
			trash.addView((View) view);
		}
		@Override public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == ((View) arg1);}
		@Override public Parcelable saveState() {
			return null;
		}
	}
	androidx.viewpager.widget.ViewPager viewPager;
	com.google.android.material.tabs.TabLayout tabLayout;
	private void foo() {
	}
	
	
	public void _fonts () {
		music.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensanslight.ttf"), 0);
		garage.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 1);
		text_heading1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_heading2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_heading3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_heading4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_msg1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_msg2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_msg3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_msg4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_skip.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		textview_next.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
	}
	
	
	public void _Elevation (final View _view, final double _number) {
		
		_view.setElevation((int)_number);
	}
	
	
	public void _setBackground (final View _view, final double _radius, final double _shadow, final String _color, final boolean _ripple) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setElevation((int)_shadow);
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9e9e9e")});
			android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
			_view.setClickable(true);
			_view.setBackground(ripdrb);
		}
		else {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			_view.setBackground(gd);
			_view.setElevation((int)_shadow);
		}
	}
	
	
	public void _TransitionManager (final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
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