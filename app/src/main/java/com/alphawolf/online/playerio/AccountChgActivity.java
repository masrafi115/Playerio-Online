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
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import android.graphics.Typeface;
import com.facebook.ads.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class AccountChgActivity extends  AppCompatActivity  { 
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map_update = new HashMap<>();
	private String email1 = "";
	private String email2 = "";
	private String currentUsername = "";
	private String link = "";
	private double n = 0;
	private double exist = 0;
	
	private ArrayList<HashMap<String, Object>> listmap_user = new ArrayList<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear_main;
	private ImageView image_back;
	private TextView title;
	private TextView msg;
	private LinearLayout linear_holder;
	private LinearLayout linear5;
	private TextView textview4;
	private LinearLayout acc_st;
	private LinearLayout edit_profile;
	private LinearLayout button_done;
	private LinearLayout linear_email;
	private LinearLayout linear_new_email;
	private EditText edittext_email;
	private EditText edittext_new_email;
	private TextView textview_username;
	private LinearLayout linear_username;
	private TextView textview_web_yt;
	private LinearLayout linear_link;
	private TextView textview_bio;
	private LinearLayout linear_bio;
	private EditText edittext_username;
	private EditText edittext_link;
	private EditText edittext_bio;
	private TextView textview_count;
	private TextView done;
	private ProgressBar progressbar1;
	private TextView textview5;
	
	private DatabaseReference profile = _firebase.getReference("profile/info");
	private ChildEventListener _profile_child_listener;
	private Intent intent = new Intent();
	private SharedPreferences data;
	private RequestNetwork request;
	private RequestNetwork.RequestListener _request_request_listener;
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
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.account_chg);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear_main = (LinearLayout) findViewById(R.id.linear_main);
		image_back = (ImageView) findViewById(R.id.image_back);
		title = (TextView) findViewById(R.id.title);
		msg = (TextView) findViewById(R.id.msg);
		linear_holder = (LinearLayout) findViewById(R.id.linear_holder);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		textview4 = (TextView) findViewById(R.id.textview4);
		acc_st = (LinearLayout) findViewById(R.id.acc_st);
		edit_profile = (LinearLayout) findViewById(R.id.edit_profile);
		button_done = (LinearLayout) findViewById(R.id.button_done);
		linear_email = (LinearLayout) findViewById(R.id.linear_email);
		linear_new_email = (LinearLayout) findViewById(R.id.linear_new_email);
		edittext_email = (EditText) findViewById(R.id.edittext_email);
		edittext_new_email = (EditText) findViewById(R.id.edittext_new_email);
		textview_username = (TextView) findViewById(R.id.textview_username);
		linear_username = (LinearLayout) findViewById(R.id.linear_username);
		textview_web_yt = (TextView) findViewById(R.id.textview_web_yt);
		linear_link = (LinearLayout) findViewById(R.id.linear_link);
		textview_bio = (TextView) findViewById(R.id.textview_bio);
		linear_bio = (LinearLayout) findViewById(R.id.linear_bio);
		edittext_username = (EditText) findViewById(R.id.edittext_username);
		edittext_link = (EditText) findViewById(R.id.edittext_link);
		edittext_bio = (EditText) findViewById(R.id.edittext_bio);
		textview_count = (TextView) findViewById(R.id.textview_count);
		done = (TextView) findViewById(R.id.done);
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		textview5 = (TextView) findViewById(R.id.textview5);
		data = getSharedPreferences("musicgarage", Activity.MODE_PRIVATE);
		request = new RequestNetwork(this);
		Auth = FirebaseAuth.getInstance();
		
		image_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				finish();
			}
		});
		
		button_done.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				n = 0;
				exist = 0;
				if (data.getString("event", "").equals("password")) {
					if ((edittext_email.getText().toString().length() > 6) && (edittext_new_email.getText().toString().length() > 6)) {
						if (edittext_email.getText().toString().equals(edittext_new_email.getText().toString())) {
							_TransitionManager(linear_main, 200);
							progressbar1.setVisibility(View.VISIBLE);
							done.setVisibility(View.GONE);
							button_done.setEnabled(false);
							edittext_email.setEnabled(false);
							edittext_new_email.setEnabled(false);
							Auth.getCurrentUser().updatePassword(edittext_new_email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>(){
													@Override
								public void onComplete(Task<Void> task) {
									          if(task.isSuccessful()){
										SketchwareUtil.showMessage(getApplicationContext(), "Password Charged Successfully!");
										FirebaseAuth.getInstance().signOut();
										intent.setClass(getApplicationContext(), LoginActivity.class);
										startActivity(intent);
										finishAffinity();
									} else {
										_customSnackBar("Oops! something went wrong", 2);
										_TransitionManager(linear_main, 200);
										done.setVisibility(View.VISIBLE);
										progressbar1.setVisibility(View.GONE);
										button_done.setEnabled(true);
										edittext_email.setEnabled(true);
										edittext_new_email.setEnabled(true);
										
									}
								} });
						}
						else {
							_customSnackBar("Passwords do not match", 2);
						}
					}
					else {
						_customSnackBar("Password should be atleast 6 characters in length", 2);
					}
				}
				else {
					if (data.getString("event", "").equals("email")) {
						email1 = edittext_email.getText().toString();
						email2 = edittext_new_email.getText().toString();
						if (edittext_email.getText().toString().length() > 0) {
							if (edittext_new_email.getText().toString().length() > 0) {
								if (email1.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
									if (email2.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
										if (edittext_email.getText().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
											_TransitionManager(linear_main, 200);
											progressbar1.setVisibility(View.VISIBLE);
											done.setVisibility(View.GONE);
											button_done.setEnabled(false);
											edittext_email.setEnabled(false);
											edittext_new_email.setEnabled(false);
											Auth.getInstance().getCurrentUser().updateEmail(edittext_new_email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>(){
												@Override
												public void onComplete(Task<Void> task){
													if(task.isSuccessful()){
														map_update = new HashMap<>();
														map_update.put("email", edittext_new_email.getText().toString());
														profile.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map_update);
														map_update.clear();
														FirebaseAuth.getInstance().signOut();
														intent.setClass(getApplicationContext(), LoginActivity.class);
														startActivity(intent);
														SketchwareUtil.showMessage(getApplicationContext(), "Completed!");
														finishAffinity();
													} else{
														_TransitionManager(linear_main, 200);
														done.setVisibility(View.VISIBLE);
														progressbar1.setVisibility(View.GONE);
														button_done.setEnabled(true);
														edittext_email.setEnabled(true);
														linear_new_email.setEnabled(true);
														showMessage ("Unexpected error occurred");
													}
												} });
										}
										else {
											_customSnackBar("Incorrect Current Email Address", 2);
										}
									} else {
										_customSnackBar("Invalid Email Address", 2);
									}
								} else {
									_customSnackBar("Invalid Email Address", 2);
								}
							}
							else {
								_customSnackBar("Enter New Email Address", 2);
							}
						}
						else {
							_customSnackBar("Enter Current Email Address", 2);
						}
					}
					else {
						if (data.getString("event", "").equals("profile")) {
							if (!currentUsername.equals(edittext_username.getText().toString())) {
								if (edittext_username.getText().toString().length() > 0) {
									profile.addListenerForSingleValueEvent(new ValueEventListener() {
										@Override
										public void onDataChange(DataSnapshot _dataSnapshot) {
											listmap_user = new ArrayList<>();
											try {
												GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
												for (DataSnapshot _data : _dataSnapshot.getChildren()) {
													HashMap<String, Object> _map = _data.getValue(_ind);
													listmap_user.add(_map);
												}
											}
											catch (Exception _e) {
												_e.printStackTrace();
											}
											for(int _repeat188 = 0; _repeat188 < (int)(listmap_user.size()); _repeat188++) {
												if (edittext_username.getText().toString().trim().toLowerCase().equals(listmap_user.get((int)n).get("username").toString().trim().toLowerCase())) {
													_customSnackBar("Username already available!", 2);
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
										if (edittext_link.getText().toString().length() > 0) {
											link = edittext_link.getText().toString();
											if (link.matches("[htts://-http://-www.-]+[a-z]+.[a-z]+")) {
												request.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com/", "A", _request_request_listener);
												done.setVisibility(View.GONE);
												progressbar1.setVisibility(View.VISIBLE);
												button_done.setEnabled(false);
												edittext_username.setEnabled(false);
												edittext_link.setEnabled(false);
												edittext_bio.setEnabled(false);
											} else {
												_customSnackBar("Invalid link!", 2);
											}
										}
										else {
											link = "";
											request.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com/", "A", _request_request_listener);
											done.setVisibility(View.GONE);
											progressbar1.setVisibility(View.VISIBLE);
											button_done.setEnabled(false);
											edittext_username.setEnabled(false);
											edittext_link.setEnabled(false);
											edittext_bio.setEnabled(false);
										}
									}
								}
								else {
									_customSnackBar("Empty Username Field!", 2);
								}
							}
							else {
								if (edittext_link.getText().toString().length() > 0) {
									link = edittext_link.getText().toString();
									if (link.matches("[htts://-http://-www.-]+[a-z]+.[a-z]+")) {
										request.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com/", "A", _request_request_listener);
										done.setVisibility(View.GONE);
										progressbar1.setVisibility(View.VISIBLE);
										button_done.setEnabled(false);
										edittext_username.setEnabled(false);
										edittext_link.setEnabled(false);
										edittext_bio.setEnabled(false);
									} else {
										_customSnackBar("Invalid link!", 2);
									}
								}
								else {
									link = "";
									request.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com/", "A", _request_request_listener);
									done.setVisibility(View.GONE);
									progressbar1.setVisibility(View.VISIBLE);
									button_done.setEnabled(false);
									edittext_username.setEnabled(false);
									edittext_link.setEnabled(false);
									edittext_bio.setEnabled(false);
								}
							}
						}
					}
				}
			}
		});
		
		edittext_new_email.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() > 0) {
					_TransitionManager(linear_main, 200);
					button_done.setVisibility(View.VISIBLE);
				}
				else {
					_TransitionManager(linear_main, 200);
					button_done.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		edittext_bio.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				textview_count.setText(String.valueOf((long)(_charSeq.length())).concat("/70"));
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		_profile_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				profile.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listmap_user = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listmap_user.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
							currentUsername = _childValue.get("username").toString();
							edittext_username.setText(_childValue.get("username").toString());
							if (_childValue.containsKey("link")) {
								edittext_link.setText(_childValue.get("link").toString());
							}
							if (_childValue.containsKey("bio")) {
								edittext_bio.setText(_childValue.get("bio").toString());
								textview_count.setText(String.valueOf((long)(_childValue.get("bio").toString().length())).concat("/70"));
							}
						}
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				profile.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listmap_user = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listmap_user.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
							currentUsername = _childValue.get("username").toString();
							edittext_username.setText(_childValue.get("username").toString());
							if (_childValue.containsKey("link")) {
								edittext_link.setText(_childValue.get("link").toString());
							}
							if (_childValue.containsKey("bio")) {
								edittext_link.setText(_childValue.get("bio").toString());
								textview_count.setText(String.valueOf((long)(_childValue.get("bio").toString().length())).concat("/70"));
							}
						}
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
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
		
		_request_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (link.length() > 0) {
					if (edittext_bio.getText().toString().length() > 0) {
						map_update = new HashMap<>();
						map_update.put("username", edittext_username.getText().toString());
						map_update.put("link", edittext_link.getText().toString());
						map_update.put("bio", edittext_bio.getText().toString());
						profile.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map_update);
						map_update.clear();
						SketchwareUtil.showMessage(getApplicationContext(), "Profile Changes Completed!");
						finish();
					}
					else {
						map_update = new HashMap<>();
						map_update.put("username", edittext_username.getText().toString());
						map_update.put("link", edittext_link.getText().toString());
						profile.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map_update);
						map_update.clear();
						SketchwareUtil.showMessage(getApplicationContext(), "Profile Changes Completed!");
						finish();
					}
				}
				else {
					if (edittext_bio.getText().toString().length() > 0) {
						map_update = new HashMap<>();
						map_update.put("username", edittext_username.getText().toString());
						map_update.put("bio", edittext_bio.getText().toString());
						profile.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map_update);
						map_update.clear();
						SketchwareUtil.showMessage(getApplicationContext(), "Profile Changes Completed!");
						finish();
					}
					else {
						map_update = new HashMap<>();
						map_update.put("username", edittext_username.getText().toString());
						profile.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map_update);
						map_update.clear();
						SketchwareUtil.showMessage(getApplicationContext(), "Profile Changes Completed!");
						finish();
					}
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
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
		if (data.getString("mode", "").equals("light")) {
			_UI_DarkMode(false, data.getString("accentColor", ""));
		}
		else {
			if (data.getString("mode", "").equals("dark")) {
				_UI_DarkMode(false, data.getString("accentColor", ""));
			}
		}
		vscroll1.setOverScrollMode(View.OVER_SCROLL_NEVER);
		_removeScollBar(vscroll1);
		progressbar1.setVisibility(View.GONE);
		if (!data.getString("event", "").equals("")) {
			if (data.getString("event", "").equals("password")) {
				edit_profile.setVisibility(View.GONE);
				button_done.setVisibility(View.GONE);
				edittext_email.setHint("New Password");
				edittext_new_email.setHint("Confirm Password");
				title.setText("Change Password");
				msg.setText("Please note that when changing your password, we ask you to set yourself a secure password that contains both uppercase and lowercase letter as well as numbers. This is for your own safety. If you face any problem when changing your password our team always ready to help you. Thank you!");
				getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
			}
			else {
				if (data.getString("event", "").equals("email")) {
					edit_profile.setVisibility(View.GONE);
					button_done.setVisibility(View.GONE);
					edittext_email.setHint("Current Email Address");
					edittext_new_email.setHint("New Email Address");
					title.setText("Update Email Address");
					msg.setText("Wants to change the email address on your Music Garage account? No worries, changing your email is easy just enter your current email address and new email address and then click on done that's it your email will changed. After that login again in your Music Garage account with your new email address. If you face any problem while changing email simply contact us our team always ready to help you. Thank you!");
					getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
				}
				else {
					if (data.getString("event", "").equals("profile")) {
						acc_st.setVisibility(View.GONE);
						edit_profile.setVisibility(View.VISIBLE);
						title.setText("Edit Profile ");
						msg.setText("Here you can edit your profile, change your preferences, connect your social accounts (such as website and YouTube channel link) and change profile color theme. if you facing any problem our team always ready to help you. Thank you!");
						edittext_bio.setFilters(new InputFilter[] {new InputFilter.LengthFilter(70)});
					}
				}
			}
		}
		else {
			finish();
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
		data.edit().remove("event").commit();
		finish();
	}
	public void _UI_DarkMode (final boolean _IsDarkMode, final String _accent) {
		if (_IsDarkMode) {
			_ProgressBarColor(progressbar1, "#252525");
		}
		else {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); }
			getWindow().setStatusBarColor(Color.parseColor("#FFFFFF"));
			
			getWindow().setNavigationBarColor(Color.parseColor("#FFFFFF"));
			_MusicGarageUI(linear_email, 25, 0, "#EEEEEE", "#EEEEEE", 0, false);
			_MusicGarageUI(linear_new_email, 25, 0, "#EEEEEE", "#EEEEEE", 0, false);
			_MusicGarageUI(linear_username, 25, 0, "#EEEEEE", "#EEEEEE", 0, false);
			_MusicGarageUI(linear_link, 25, 0, "#EEEEEE", "#EEEEEE", 0, false);
			_MusicGarageUI(linear_bio, 25, 0, "#EEEEEE", "#EEEEEE", 0, false);
			_MusicGarageUI(button_done, 25, 0, "#2196F3", "#EEEEEE", 0, true);
			_MusicGarageUI(image_back, 50, 0, "#FFFFFF", "#EEEEEE", 0, true);
			_MusicGarageUI(edit_profile, 20, 0, "#CCCCCC", "#CCCCCC", 0, false);
			_ProgressBarColor(progressbar1, "#F5F5F5");
		}
		_Fonts();
	}
	
	
	public void _MusicGarageUI (final View _view, final double _radius, final double _shadow, final String _color1, final String _color2, final double _border, final boolean _ripple) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color1));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int) _border, Color.parseColor(_color2));
			_view.setBackground(gd);
			_view.setElevation((int)_shadow);
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9e9e9e")});
			android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
			_view.setClickable(true);
			_view.setBackground(ripdrb);
		}
		else {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color1));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int) _border, Color.parseColor(_color2));
			_view.setBackground(gd);
			_view.setElevation((int)_shadow);
		}
	}
	
	
	public void _TransitionManager (final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
	}
	
	
	public void _Fonts () {
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 1);
		msg.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		edittext_email.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		edittext_new_email.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		edittext_username.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		edittext_link.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		edittext_bio.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		done.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		textview_username.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		textview_bio.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		textview_web_yt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		textview_count.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
	}
	
	
	public void _ProgressBarColor (final ProgressBar _progressbar, final String _color) {
		if (android.os.Build.VERSION.SDK_INT >= 21) {
			_progressbar.getIndeterminateDrawable().setColorFilter(Color.parseColor(_color), PorterDuff.Mode.SRC_IN);
		}
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
			_MusicGarageUI(layout, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 0, "#2090F0", "#2090F0", 0, false);
		}
		else {
			if (_icon == 1) {
				image.setImageResource(R.drawable.icon_done_white);
				_MusicGarageUI(layout, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 0, "#48B048", "#2090F0", 0, false);
			}
			else {
				if (_icon == 2) {
					image.setImageResource(R.drawable.icon_exit_white);
					_MusicGarageUI(layout, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 0, "#E03830", "#2090F0", 0, false);
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