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
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ProgressBar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.animation.Animator;
import android.graphics.Typeface;
import com.facebook.ads.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class MainActivity extends  AppCompatActivity  { 
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private HashMap<String, Object> tmpUpdateMap = new HashMap<>();
	private double num = 0;
	private double random = 0;
	private String color = "";
	private HashMap<String, Object> map_send = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> profile_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> profile_bans = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> verification_listmap = new ArrayList<>();
	
	private LinearLayout linear_main;
	private LinearLayout linear7;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear9;
	private LinearLayout linear6;
	private ImageView image_logo;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear11;
	private TextView text_music;
	private TextView text_garage;
	private LinearLayout linear8;
	private TextView textview1;
	private LinearLayout linear12;
	private LinearLayout error_occurs;
	private ProgressBar progressbar2;
	private ImageView imageview1;
	private TextView textview2;
	private TextView textview3;
	private TextView text_version;
	
	private FirebaseAuth Auth;
	private OnCompleteListener<Void> Auth_updateEmailListener;
	private OnCompleteListener<Void> Auth_updatePasswordListener;
	private OnCompleteListener<Void> Auth_emailVerificationSentListener;
	private OnCompleteListener<Void> Auth_deleteUserListener;
	private OnCompleteListener<Void> Auth_updateProfileListener;
	private OnCompleteListener<AuthResult> Auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> Auth_googleSignInListener;
	private OnCompleteListener<AuthResult> _Auth_create_user_listener;
	private OnCompleteListener<AuthResult> _Auth_sign_in_listener;
	private OnCompleteListener<Void> _Auth_reset_password_listener;
	private ObjectAnimator obj1 = new ObjectAnimator();
	private ObjectAnimator obj2 = new ObjectAnimator();
	private ObjectAnimator obj3 = new ObjectAnimator();
	private ObjectAnimator obj4 = new ObjectAnimator();
	private TimerTask timer;
	private Intent intent = new Intent();
	private SharedPreferences data;
	private TimerTask t;
	private RequestNetwork request;
	private RequestNetwork.RequestListener _request_request_listener;
	private DatabaseReference profile = _firebase.getReference("profile/info");
	private ChildEventListener _profile_child_listener;
	private TimerTask time;
	private DatabaseReference pro_bans = _firebase.getReference("profile/bans");
	private ChildEventListener _pro_bans_child_listener;
	private Intent intent2 = new Intent();
	private DatabaseReference verification = _firebase.getReference("profile/verification");
	private ChildEventListener _verification_child_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear_main = (LinearLayout) findViewById(R.id.linear_main);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		image_logo = (ImageView) findViewById(R.id.image_logo);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		text_music = (TextView) findViewById(R.id.text_music);
		text_garage = (TextView) findViewById(R.id.text_garage);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		textview1 = (TextView) findViewById(R.id.textview1);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		error_occurs = (LinearLayout) findViewById(R.id.error_occurs);
		progressbar2 = (ProgressBar) findViewById(R.id.progressbar2);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview2 = (TextView) findViewById(R.id.textview2);
		textview3 = (TextView) findViewById(R.id.textview3);
		text_version = (TextView) findViewById(R.id.text_version);
		Auth = FirebaseAuth.getInstance();
		data = getSharedPreferences("musicgarage", Activity.MODE_PRIVATE);
		request = new RequestNetwork(this);
		
		obj1.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator _param1) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator _param1) {
				profile.addChildEventListener(_profile_child_listener);
				verification.addChildEventListener(_verification_child_listener);
				_next();
				_NextStep();
				request.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "A", _request_request_listener);
			}
			
			@Override
			public void onAnimationCancel(Animator _param1) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animator _param1) {
				
			}
		});
		
		_request_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (_isNoInternet) {
					_isNoInternet = false;
					_autoTransitionScroll(linear_main);
					textview3.setVisibility(View.GONE);
					textview2.setText("Internet Is Back!");
					progressbar2.setVisibility(View.VISIBLE);
					error_occurs.setVisibility(View.GONE);
				}
				else {
					request.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "A", _request_request_listener);
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				if (_isNoInternet) {
					request.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "A", _request_request_listener);
				}
				else {
					_isNoInternet = true;
					_autoTransitionScroll(linear_main);
					progressbar2.setVisibility(View.GONE);
					error_occurs.setVisibility(View.VISIBLE);
					textview2.setVisibility(View.VISIBLE);
					textview3.setVisibility(View.VISIBLE);
					textview2.setText("Whoops!");
					textview3.setText("Slow or no internet connection.\nPlease check your internet settings");
					request.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "A", _request_request_listener);
				}
			}
		};
		
		_profile_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		profile.addChildEventListener(_profile_child_listener);
		
		_pro_bans_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		pro_bans.addChildEventListener(_pro_bans_child_listener);
		
		_verification_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		verification.addChildEventListener(_verification_child_listener);
		
		Auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		Auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		Auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		Auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		Auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		Auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		Auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task){
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_Auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_Auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_Auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
		_TransparentStatusbar();
		setTheme(android.R.style.ThemeOverlay_Material_Dark);
		getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		error_occurs.setVisibility(View.GONE);
		linear_main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		try {
			packageInfo = MainActivity.this.getPackageManager().getPackageInfo(getPackageName(), 0);
			text_version.setText("v" + packageInfo.versionName);
		}
		catch (Exception _e) {
		}
		text_music.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensanslight.ttf"), 0);
		text_garage.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_version.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 1);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		_ProgressBarColor(progressbar2, "#252525");
		profile.removeEventListener(_profile_child_listener);
		pro_bans.removeEventListener(_pro_bans_child_listener);
		verification.removeEventListener(_verification_child_listener);
		if (data.getString("mode", "").equals("")) {
			data.edit().putString("mode", "light").commit();
		}
		if (data.getString("mode", "").equals("dark")) {
			data.edit().putString("mode", "light").commit();
		}
		if (data.getString("welcome", "").equals("")) {
			data.edit().putString("welcome", "0").commit();
		}
		if (data.getString("accentColor", "").equals("")) {
			data.edit().putString("accentColor", "#2196F3").commit();
		}
		if (data.getString("repeat", "").equals("")) {
			data.edit().putString("repeat", "0").commit();
		}
		if (data.getString("shuffle", "").equals("")) {
			data.edit().putString("shuffle", "0").commit();
		}
		if (data.getString("nightcore", "").equals("")) {
			data.edit().putString("nightcore", "0").commit();
		}
		if (data.getString("playlist", "").equals("")) {
			data.edit().putString("playlist", "[]").commit();
		}
		t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						obj1.setTarget(linear2);
						obj1.setPropertyName("translationX");
						obj1.setFloatValues((float)(SketchwareUtil.getDip(getApplicationContext(), (int)(250))), (float)(0.0d));
						obj1.setDuration((int)(500));
						obj1.setInterpolator(new DecelerateInterpolator());
						obj2.setTarget(image_logo);
						obj2.setPropertyName("alpha");
						obj2.setFloatValues((float)(0.5d), (float)(1.0d));
						obj2.setDuration((int)(500));
						obj2.setInterpolator(new DecelerateInterpolator());
						obj4.setTarget(linear3);
						obj4.setPropertyName("alpha");
						obj4.setFloatValues((float)(0.5d), (float)(1.0d));
						obj4.setFloatValues((float)(500));
						obj4.setInterpolator(new AccelerateInterpolator());
						obj3.setTarget(text_version);
						obj3.setPropertyName("translationX");
						obj3.setFloatValues((float)(SketchwareUtil.getDip(getApplicationContext(), (int)(-500))), (float)(0.0d));
						obj3.setDuration((int)(700));
						obj3.setInterpolator(new DecelerateInterpolator());
						obj1.start();
						obj2.start();
						obj3.start();
						obj4.start();
					}
				});
			}
		};
		_timer.schedule(t, (int)(500));
	}
	private android.content.pm.PackageInfo packageInfo;
	private boolean _isNoInternet = false;
	private double _closeNum = 0; {
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
	public void _TransparentStatusbar () {
		 
		Window w = this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(Color.parseColor("#00000000"));
		
		w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); 
	}
	
	
	public void _ProgressBarColor (final ProgressBar _progressbar, final String _color) {
		if (android.os.Build.VERSION.SDK_INT >= 21) {
			_progressbar.getIndeterminateDrawable().setColorFilter(Color.parseColor(_color), PorterDuff.Mode.SRC_IN);
		}
	}
	
	
	public void _NextStep () {
		time = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (_closeNum == 0) {
							_NextStep();
						}
						else {
							if (_closeNum == 1) {
								intent2.setClass(getApplicationContext(), SteamerActivity.class);
								startActivity(intent2);
								finish();
							}
							else {
								if (_closeNum == 2) {
									intent.setClass(getApplicationContext(), SetUpActivity.class);
									startActivity(intent);
									finish();
								}
								else {
									if (_closeNum == 3) {
										
									}
									else {
										if (_closeNum == 4) {
											profile.removeEventListener(_profile_child_listener);
											intent.setClass(getApplicationContext(), LoginActivity.class);
											startActivity(intent);
											finish();
										}
										else {
											if (_closeNum == 5) {
												profile.removeEventListener(_profile_child_listener);
												intent.setClass(getApplicationContext(), UpdateActivity.class);
												startActivity(intent);
												finish();
											}
											else {
												if (_closeNum == 6) {
													intent.setClass(getApplicationContext(), WelcomeActivity.class);
													startActivity(intent);
													finish();
												}
												else {
													intent.setClass(getApplicationContext(), DebugActivity.class);
													intent.putExtra("error", "Seems something wrong please check you downloaded correct application!");
													startActivity(intent);
													finish();
												}
											}
										}
									}
								}
							}
						}
					}
				});
			}
		};
		_timer.schedule(time, (int)(500));
	}
	
	
	public void _user_color () {
		random = SketchwareUtil.getRandom((int)(1), (int)(18));
		if (random == 1) {
			color = "#D32F2F";
		}
		if (random == 2) {
			color = "#C2185B";
		}
		if (random == 3) {
			color = "#7B1FA2";
		}
		if (random == 4) {
			color = "#512DA8";
		}
		if (random == 5) {
			color = "#303F9F";
		}
		if (random == 6) {
			color = "#1976D2";
		}
		if (random == 7) {
			color = "#03A9F4";
		}
		if (random == 8) {
			color = "#0097A7";
		}
		if (random == 9) {
			color = "#009688";
		}
		if (random == 10) {
			color = "#4CAF50";
		}
		if (random == 11) {
			color = "#8BC34A";
		}
		if (random == 12) {
			color = "#AFB42B";
		}
		if (random == 13) {
			color = "#FBC02D";
		}
		if (random == 14) {
			color = "#FFA000";
		}
		if (random == 15) {
			color = "#E64A19";
		}
		if (random == 16) {
			color = "#795548";
		}
		if (random == 17) {
			color = "#607D8B";
		}
		if (random == 18) {
			color = "#008DCD";
		}
	}
	
	
	public void _autoTransitionScroll (final View _scroll) {
		android.transition.TransitionManager.beginDelayedTransition((LinearLayout)_scroll, new android.transition.AutoTransition());
	}
	
	
	public void _next () {
		if (data.getString("welcome", "").equals("1")) {
			if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
				profile.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						profile_map = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								profile_map.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						if (profile_map.size() > 0) {
							double _tmpNum = 0;
							boolean _endTask = false;
							for(int _repeat20 = 0; _repeat20 < (int)(profile_map.size()); _repeat20++) {
								if (profile_map.get((int)_tmpNum).get("email").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
									_endTask = true;
									if (!profile_map.get((int)_tmpNum).containsKey("user_color")) {
										map_send = new HashMap<>();
										map_send = profile_map.get((int)_tmpNum);
										map_send.put("user_color", color);
										profile.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map_send);
									}
									verification.addListenerForSingleValueEvent(new ValueEventListener() {
										@Override
										public void onDataChange(DataSnapshot _dataSnapshot) {
											verification_listmap = new ArrayList<>();
											try {
												GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
												for (DataSnapshot _data : _dataSnapshot.getChildren()) {
													HashMap<String, Object> _map = _data.getValue(_ind);
													verification_listmap.add(_map);
												}
											}
											catch (Exception _e) {
												_e.printStackTrace();
											}
											num = 0;
											for(int _repeat41 = 0; _repeat41 < (int)(verification_listmap.size()); _repeat41++) {
												if (!profile_map.get((int)num).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
													map = new HashMap<>();
													map = verification_listmap.get((int)num);
													map.put("verification", "default");
													map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
													verification.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
												}
											}
										}
										@Override
										public void onCancelled(DatabaseError _databaseError) {
										}
									});
									pro_bans.addListenerForSingleValueEvent(new ValueEventListener() {
										@Override
										public void onDataChange(DataSnapshot _dataSnapshot) {
											profile_bans = new ArrayList<>();
											try {
												GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
												for (DataSnapshot _data : _dataSnapshot.getChildren()) {
													HashMap<String, Object> _map = _data.getValue(_ind);
													profile_bans.add(_map);
												}
											}
											catch (Exception _e) {
												_e.printStackTrace();
											}
											if (profile_bans.size() > 0) {
												pro_bans.addChildEventListener(_pro_bans_child_listener);
											}
											else {
												_closeNum = 1;
											}
										}
										@Override
										public void onCancelled(DatabaseError _databaseError) {
										}
									});
								}
								else {
									if ((_tmpNum == (profile_map.size() - 1)) && !_endTask) {
										_closeNum = 2;
									}
								}
								_tmpNum++;
							}
						}
						else {
							_closeNum = 2;
						}
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			else {
				_closeNum = 4;
			}
		}
		else {
			_closeNum = 6;
		}
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