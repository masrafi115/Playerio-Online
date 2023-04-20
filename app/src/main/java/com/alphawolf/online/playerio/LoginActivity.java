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
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.EditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import android.graphics.Typeface;
import com.facebook.ads.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class LoginActivity extends  AppCompatActivity  { 
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private boolean isLogin = false;
	private double n = 0;
	private double exist = 0;
	private double pass_num_one = 0;
	private double pass_num_two = 0;
	private String colorPrimary = "";
	private String email_address = "";
	private String color = "";
	private boolean isDark = false;
	
	private ArrayList<HashMap<String, Object>> listmap_username = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear_main;
	private LinearLayout linear1;
	private LinearLayout linear_header;
	private LinearLayout linear_info;
	private LinearLayout linear_username;
	private LinearLayout linear_email;
	private LinearLayout linear_pass;
	private LinearLayout linear_com_pass;
	private LinearLayout linear10;
	private Button button1;
	private LinearLayout linear12;
	private LinearLayout linear16;
	private LinearLayout linear3;
	private TextView text_music;
	private TextView text_garage;
	private ImageView imageview2;
	private TextView title_1;
	private TextView title_2;
	private EditText edittext_username;
	private EditText edittext_email;
	private EditText edittext_pass;
	private ImageView image_hide1;
	private EditText edittext_com_pass;
	private ImageView image_hide2;
	private TextView text_forgot_pass;
	private TextView text_login;
	private TextView text_login_act;
	private LinearLayout linear17;
	private LinearLayout linear15;
	private LinearLayout linear14;
	private TextView textview4;
	private TextView text_pp;
	private TextView text_and;
	private TextView text_tac;
	
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
	private DatabaseReference profile = _firebase.getReference("profile/info");
	private ChildEventListener _profile_child_listener;
	private RequestNetwork network;
	private RequestNetwork.RequestListener _network_request_listener;
	private Intent intent_home = new Intent();
	private RequestNetwork request;
	private RequestNetwork.RequestListener _request_request_listener;
	private Intent intent_next = new Intent();
	private SharedPreferences data;
	private Intent intent_pp = new Intent();
	private Intent intent_tac = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.login);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear_main = (LinearLayout) findViewById(R.id.linear_main);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear_header = (LinearLayout) findViewById(R.id.linear_header);
		linear_info = (LinearLayout) findViewById(R.id.linear_info);
		linear_username = (LinearLayout) findViewById(R.id.linear_username);
		linear_email = (LinearLayout) findViewById(R.id.linear_email);
		linear_pass = (LinearLayout) findViewById(R.id.linear_pass);
		linear_com_pass = (LinearLayout) findViewById(R.id.linear_com_pass);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		button1 = (Button) findViewById(R.id.button1);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		text_music = (TextView) findViewById(R.id.text_music);
		text_garage = (TextView) findViewById(R.id.text_garage);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		title_1 = (TextView) findViewById(R.id.title_1);
		title_2 = (TextView) findViewById(R.id.title_2);
		edittext_username = (EditText) findViewById(R.id.edittext_username);
		edittext_email = (EditText) findViewById(R.id.edittext_email);
		edittext_pass = (EditText) findViewById(R.id.edittext_pass);
		image_hide1 = (ImageView) findViewById(R.id.image_hide1);
		edittext_com_pass = (EditText) findViewById(R.id.edittext_com_pass);
		image_hide2 = (ImageView) findViewById(R.id.image_hide2);
		text_forgot_pass = (TextView) findViewById(R.id.text_forgot_pass);
		text_login = (TextView) findViewById(R.id.text_login);
		text_login_act = (TextView) findViewById(R.id.text_login_act);
		linear17 = (LinearLayout) findViewById(R.id.linear17);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		textview4 = (TextView) findViewById(R.id.textview4);
		text_pp = (TextView) findViewById(R.id.text_pp);
		text_and = (TextView) findViewById(R.id.text_and);
		text_tac = (TextView) findViewById(R.id.text_tac);
		Auth = FirebaseAuth.getInstance();
		network = new RequestNetwork(this);
		request = new RequestNetwork(this);
		data = getSharedPreferences("musicgarage", Activity.MODE_PRIVATE);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				exist = 0;
				n = 0;
				if (isLogin) {
					if (edittext_email.getText().toString().equals("")) {
						_customSnackBar("Enter Email Address!", 2);
					}
					else {
						if (edittext_pass.getText().toString().equals("")) {
							_customSnackBar("Enter Password!", 2);
						}
						else {
							edittext_email.setEnabled(false);
							edittext_pass.setEnabled(false);
							button1.setEnabled(false);
							network.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "A", _network_request_listener);
							_MusicGarageLoading(true, "Please Wait...");
						}
					}
				}
				else {
					if (!edittext_username.getText().toString().equals("")) {
						if (edittext_email.getText().toString().equals("")) {
							_customSnackBar("Enter Email Address!", 2);
						}
						else {
							if (edittext_pass.getText().toString().equals("")) {
								_customSnackBar("Enter Password!", 2);
							}
							else {
								if (edittext_com_pass.getText().toString().equals("")) {
									_customSnackBar("Enter Confirm Password!", 2);
								}
								else {
									if (edittext_pass.getText().toString().equals(edittext_com_pass.getText().toString())) {
										profile.addListenerForSingleValueEvent(new ValueEventListener() {
											@Override
											public void onDataChange(DataSnapshot _dataSnapshot) {
												listmap_username = new ArrayList<>();
												try {
													GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
													for (DataSnapshot _data : _dataSnapshot.getChildren()) {
														HashMap<String, Object> _map = _data.getValue(_ind);
														listmap_username.add(_map);
													}
												}
												catch (Exception _e) {
													_e.printStackTrace();
												}
												for(int _repeat60 = 0; _repeat60 < (int)(listmap_username.size()); _repeat60++) {
													if (listmap_username.get((int)n).get("username").toString().toLowerCase().equals(edittext_username.getText().toString().toLowerCase())) {
														_customSnackBar("Username already taken!", 2);
														exist = 1;
													}
													n++;
												}
											}
											@Override
											public void onCancelled(DatabaseError _databaseError) {
											}
										});
										if (exist == 0) {
											request.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com", "A", _request_request_listener);
											_MusicGarageLoading(true, "Please Wait...");
										}
									}
									else {
										_customSnackBar("Confirm Password Doesn't Match! ", 2);
									}
								}
							}
						}
					}
					else {
						_customSnackBar("Enter Username!", 2);
					}
				}
			}
		});
		
		edittext_pass.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() > 0) {
					image_hide1.setVisibility(View.VISIBLE);
				}
				else {
					image_hide1.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		image_hide1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (pass_num_one == 0) {
					edittext_pass.setTransformationMethod(android.text.method.HideReturnsTransformationMethod.getInstance());
					image_hide1.setImageResource(R.drawable.unhide_black);
					pass_num_one++;
				}
				else {
					edittext_pass.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
					image_hide1.setImageResource(R.drawable.hide_black);
					pass_num_one = 0;
				}
			}
		});
		
		edittext_com_pass.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.equals(edittext_pass.getText().toString())) {
					_RoundAndBorder(linear_pass, "#FFFFFF", 2, "#2090F0", 50);
					_RoundAndBorder(linear_pass, "#FFFFFF", 2, "#2090F0", 50);
					edittext_pass.setTextColor(0xFF2090F0);
					edittext_com_pass.setTextColor(0xFF2090F0);
				}
				else {
					_RoundAndBorder(linear_pass, "#FFFFFF", 2, "#f44336", 50);
					_RoundAndBorder(linear_pass, "#FFFFFF", 2, "#f44336", 50);
					edittext_pass.setTextColor(0xFFF44336);
					edittext_com_pass.setTextColor(0xFFF44336);
				}
				if (_charSeq.length() > 0) {
					image_hide2.setVisibility(View.VISIBLE);
				}
				else {
					image_hide2.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		image_hide2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (pass_num_two == 0) {
					edittext_com_pass.setTransformationMethod(android.text.method.HideReturnsTransformationMethod.getInstance());
					image_hide2.setImageResource(R.drawable.unhide_black);
					pass_num_two++;
				}
				else {
					edittext_com_pass.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
					image_hide2.setImageResource(R.drawable.hide_black);
					pass_num_two = 0;
				}
			}
		});
		
		text_forgot_pass.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
				View view = getLayoutInflater().inflate(R.layout.forgot_pass,null);
				TextView apply = (TextView)view.findViewById(R.id.apply);
				TextView cancel = (TextView)view.findViewById(R.id.cancel);
				TextView title = (TextView)view.findViewById(R.id.title);
				TextView message = (TextView)view.findViewById(R.id.message);
				final EditText email = (EditText) view.findViewById(R.id.email);
				ImageView image = (ImageView)view.findViewById(R.id.image);
				LinearLayout parent = (LinearLayout)view.findViewById(R.id.parent);
				alert.setView(view);
				final AlertDialog dialog = alert.create();
				_setBackground(parent,(double)50,(double)5,colorPrimary,false);
				_setBackground(email,(double)10,(double)0,color,false);
				_setBackground(apply,(double)50,(double)0,colorPrimary,true);
				_setBackground(cancel,(double)50,(double)0,colorPrimary,true);
				title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
				message.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
				apply.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
				cancel.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
				email.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
				if (true) {
					ObjectAnimator scaleX = new ObjectAnimator();
					scaleX.setTarget(parent);
					scaleX.setPropertyName("scaleX");
					scaleX.setFloatValues((float)0.8f,(float)1f);
					scaleX.setDuration(300);
					scaleX.setInterpolator(new AccelerateDecelerateInterpolator());
					scaleX.start();
					ObjectAnimator scaleY = new ObjectAnimator();
					scaleY.setTarget(parent);
					scaleY.setPropertyName("scaleY");
					scaleY.setFloatValues((float)0.8f,(float)1f);
					scaleY.setDuration(300);
					scaleY.setInterpolator(new AccelerateDecelerateInterpolator());
					scaleY.start();
					ObjectAnimator alpha = new ObjectAnimator();
					alpha.setTarget(parent);
					alpha.setPropertyName("alpha");
					alpha.setFloatValues((float)0f,(float)1);
					alpha.setDuration(300);
					alpha.setInterpolator(new AccelerateDecelerateInterpolator());
					alpha.start();
				}
				if (true) {
					title.setText("Forget Password?");
					message.setText("Enter Your Account Email Address And Submit We Will Send You Password Reset Email On Your Email Address");
					apply.setText("SUBMIT");
					cancel.setText("CANCEL");
					image.setImageResource(R.drawable.password);
				}
				dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));
				email.setFocusableInTouchMode(true);
				
				
				email.addTextChangedListener(new TextWatcher() {
					
					   @Override
					
					   public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
						
						    final String _charSeq = _param1.toString();
						
						    ///code
						
						
						   }
					
					   
					
					   @Override
					
					   public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
						
						    
						
						   }
					
					   
					
					   @Override
					
					   public void afterTextChanged(Editable _param1) {
						
						    
						
						   }
					
					  });
				apply.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						email_address = email.getText().toString();
						if (true) {
							if (email_address.trim().equals("")) {
								_customSnackBar("Enter Email Address!", 2);
							}
							else {
								_MusicGarageLoading(true, "Hold On...");
								Auth.sendPasswordResetEmail(email_address).addOnCompleteListener(_Auth_reset_password_listener);
								SketchwareUtil.showMessage(getApplicationContext(), "Please Wait...");
							}
						}
						dialog.dismiss();}});
				cancel.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						if (true) {
							
						}
						dialog.dismiss();}});
				dialog.show();
			}
		});
		
		text_login_act.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (isLogin) {
					isLogin = false;
					_TransitionManager(linear1, 300);
					text_forgot_pass.setVisibility(View.GONE);
					linear_username.setVisibility(View.VISIBLE);
					linear_com_pass.setVisibility(View.VISIBLE);
					button1.setText("CREATE");
					title_1.setText("Let's Get Started!");
					title_2.setText("Create an account of Music Garage to get all features");
					text_login.setText("Already have an account?");
					text_login_act.setText("Login here");
				}
				else {
					isLogin = true;
					_TransitionManager(linear1, 300);
					text_forgot_pass.setVisibility(View.VISIBLE);
					linear_username.setVisibility(View.GONE);
					linear_com_pass.setVisibility(View.GONE);
					button1.setText("LOG IN");
					title_1.setText("Welcome Back!");
					title_2.setText("Sign In To Continue");
					text_login.setText("Don't have an account?");
					text_login_act.setText("Sign Up");
				}
			}
		});
		
		text_pp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent_pp.setClass(getApplicationContext(), ExtraActivity.class);
				data.edit().putString("info", "pp").commit();
				startActivity(intent_pp);
			}
		});
		
		text_tac.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent_tac.setClass(getApplicationContext(), ExtraActivity.class);
				data.edit().putString("info", "tac").commit();
				startActivity(intent_tac);
			}
		});
		
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
		
		_network_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				Auth.signInWithEmailAndPassword(edittext_email.getText().toString(), edittext_pass.getText().toString()).addOnCompleteListener(LoginActivity.this, _Auth_sign_in_listener);
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				_MusicGarageLoading(false, "");
				_customSnackBar(_message, 2);
			}
		};
		
		_request_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				Auth.createUserWithEmailAndPassword(edittext_email.getText().toString(), edittext_pass.getText().toString()).addOnCompleteListener(LoginActivity.this, _Auth_create_user_listener);
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				_MusicGarageLoading(false, "");
				_customSnackBar(_message, 2);
			}
		};
		
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
				if (_success) {
					_MusicGarageLoading(false, "");
					intent_next.setClass(getApplicationContext(), MainActivity.class);
					data.edit().putString("username", edittext_username.getText().toString()).commit();
					SketchwareUtil.showMessage(getApplicationContext(), "Signup Complete!");
					startActivity(intent_next);
					finish();
				}
				else {
					_MusicGarageLoading(false, "");
					_customSnackBar(_errorMessage, 2);
				}
			}
		};
		
		_Auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					_MusicGarageLoading(false, "");
					SketchwareUtil.showMessage(getApplicationContext(), "Login Successfully!");
					intent_home.setClass(getApplicationContext(), MainActivity.class);
					startActivity(intent_home);
					finish();
				}
				else {
					_MusicGarageLoading(false, "");
					edittext_email.setEnabled(true);
					edittext_pass.setEnabled(true);
					button1.setEnabled(true);
					_customSnackBar(_errorMessage, 2);
				}
			}
		};
		
		_Auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				_MusicGarageLoading(false, "");
				if (_success) {
					SketchwareUtil.showMessage(getApplicationContext(), "Reset Email Successfully Send To Email Address!");
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "Something Went Wrong!");
				}
			}
		};
	}
	
	private void initializeLogic() {
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
		image_hide1.setVisibility(View.GONE);
		image_hide2.setVisibility(View.GONE);
		text_forgot_pass.setVisibility(View.GONE);
		vscroll1.setOverScrollMode(View.OVER_SCROLL_NEVER);
		if (data.getString("mode", "").equals("light")) {
			_UI_DarkMode(false, data.getString("accentColor", ""));
			isDark = false;
		}
		else {
			if (data.getString("mode", "").equals("dark")) {
				_UI_DarkMode(true, data.getString("accentColor", ""));
				isDark = true;
			}
		}
		_removeScollBar(vscroll1);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _fonts () {
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_music.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensanslight.ttf"), 0);
		text_garage.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 1);
		title_1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		title_2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		edittext_username.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		edittext_email.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		edittext_pass.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		edittext_com_pass.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_forgot_pass.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_login.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_login_act.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_pp.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_and.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_tac.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
	}
	
	
	public void _RoundAndBorder (final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
	}
	
	
	public void _SetStatusBarColor (final String _color) {
		Window w = this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(Color.parseColor(_color));
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
	
	
	public void _Animator (final View _view, final String _propertyName, final double _value, final double _duration) {
		ObjectAnimator anim = new ObjectAnimator();
		anim.setTarget(_view);
		anim.setPropertyName(_propertyName);
		anim.setFloatValues((float)_value);
		anim.setDuration((long)_duration);
		anim.start();
	}
	
	
	public void _TransitionManager (final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
	}
	
	
	public void _customSnackBar (final String _txt, final double _icon) {
		// Create the Snackbar
		ViewGroup containerLayout = (ViewGroup) ((ViewGroup) this .findViewById(android.R.id.content)).getChildAt(0);
		
		com.google.android.material.snackbar.Snackbar snackbar = com.google.android.material.snackbar.Snackbar.make(containerLayout, "", com.google.android.material.snackbar.Snackbar.LENGTH_LONG);
		
		View snackb = snackbar.getView();
		
		final ViewGroup.MarginLayoutParams params = ((ViewGroup.MarginLayoutParams)snackb.getLayoutParams());
		
		params.setMargins(params.leftMargin + 32, params.topMargin, params.rightMargin + 32, params.bottomMargin + 32); snackb.setLayoutParams(params);
		
		// Get the Snackbar's layout view
		com.google.android.material.snackbar.Snackbar.SnackbarLayout layout = (com.google.android.material.snackbar.Snackbar.SnackbarLayout) snackbar.getView();
		// Inflate our custom view
		View snackview = getLayoutInflater().inflate(R.layout.custom_snack, null);
		// Configure the view
		ImageView image = (ImageView) snackview.findViewById(R.id.imageview);
		if (_icon == 0) {
			image.setImageResource(R.drawable.icon_info_white);
			_setBackground(layout, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 0, "#2090F0", false);
		}
		else {
			if (_icon == 1) {
				image.setImageResource(R.drawable.icon_done_white);
				_setBackground(layout, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 0, "#48B048", false);
			}
			else {
				if (_icon == 2) {
					image.setImageResource(R.drawable.icon_exit_white);
					_setBackground(layout, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 0, "#E03830", false);
				}
			}
		}
		TextView text = (TextView) snackview.findViewById(R.id.textview);
		text.setText(_txt);
		text.setTextColor(Color.WHITE);
		text.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
		layout.setPadding(0, 0, 0, 0);
		// Add the view to the Snackbar's layout
		layout.addView(snackview, 0);
		// Show the Snackbar
		snackbar.show();
	}
	
	
	public void _MusicGarageLoading (final boolean _IsShow, final String _msg) {
		if (_IsShow) {
			if (coreprog == null){
				coreprog = new ProgressDialog(this);
				coreprog.setCancelable(false);
				coreprog.setCanceledOnTouchOutside(false);
				
				coreprog.requestWindowFeature(Window.FEATURE_NO_TITLE);  coreprog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
				
			}
			coreprog.setMessage(null);
			coreprog.show();
			coreprog.setContentView(R.layout.custom_dialog);
			
			View _view = getLayoutInflater().inflate(R.layout.custom_dialog, null);
			final TextView txt1 = (TextView) _view.findViewById(R.id.textview1);
			txt1.setText(_msg);
		}
		else {
			if (coreprog != null){
				coreprog.dismiss();
			}
		}
	}
	private ProgressDialog coreprog;
	{
	}
	
	
	public void _UI_DarkMode (final boolean _isDark, final String _accentColor) {
		if (_isDark) {
			
		}
		else {
			_SetStatusBarColor("#FFFFFF");
			linear1.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
			Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  window.setNavigationBarColor(Color.parseColor("#FFFFFF"));
			colorPrimary = "#FFFFFF";
			color = "#EEEEEE";
			_RoundAndBorder(linear_username, "#FFFFFF", 2, "#212121", 50);
			_RoundAndBorder(linear_email, "#FFFFFF", 2, "#212121", 50);
			_RoundAndBorder(linear_pass, "#FFFFFF", 2, "#212121", 50);
			_RoundAndBorder(linear_com_pass, "#FFFFFF", 2, "#212121", 50);
			_setBackground(button1, 50, 16, "#212121", true);
			_setBackground(image_hide1, 200, 0, "#FFFFFF", true);
			_setBackground(image_hide2, 200, 0, "#FFFFFF", true);
			_setBackground(text_login_act, 10, 0, "#FFFFFF", true);
			_setBackground(text_forgot_pass, 10, 0, "#FFFFFF", true);
			_setBackground(text_pp, 10, 0, "#FFFFFF", true);
			_setBackground(text_tac, 10, 0, "#FFFFFF", true);
		}
		_fonts();
	}
	
	
	public void _removeScollBar (final View _view) {
		_view.setVerticalScrollBarEnabled(false); _view.setHorizontalScrollBarEnabled(false);
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