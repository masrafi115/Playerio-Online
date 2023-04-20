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
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import com.bumptech.glide.Glide;
import android.graphics.Typeface;
import com.facebook.ads.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class SettingActivity extends  AppCompatActivity  { 
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private boolean IsSwitch = false;
	private HashMap<String, Object> ma = new HashMap<>();
	private boolean isDark = false;
	private String color = "";
	private String colorPrimary = "";
	private HashMap<String, Object> map_update = new HashMap<>();
	private HashMap<String, Object> map = new HashMap<>();
	
	private ScrollView vscroll1;
	private LinearLayout linear_main;
	private LinearLayout action_bar_1;
	private LinearLayout linear5;
	private LinearLayout user_info;
	private LinearLayout ac_setting;
	private LinearLayout linear_s_settings;
	private LinearLayout linear_help;
	private LinearLayout linear_more;
	private ImageView image_back;
	private TextView text_setting;
	private LinearLayout linear_image;
	private LinearLayout linear7;
	private LinearLayout cardview;
	private ImageView imageview3;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private TextView text_username;
	private ImageView image_edit;
	private TextView verification_status;
	private LinearLayout user_info_title;
	private LinearLayout linear_country;
	private LinearLayout linear_bio;
	private ImageView imageview5;
	private TextView text_ac_info;
	private LinearLayout linear16;
	private LinearLayout linear14;
	private TextView country;
	private ImageView image_country;
	private TextView text_country;
	private TextView text_bio;
	private TextView text_msg;
	private LinearLayout acc_setting_title;
	private LinearLayout linear_email;
	private LinearLayout linear_pass;
	private LinearLayout linear_delete_acc;
	private ImageView image_acc_st;
	private TextView text_acc_st;
	private LinearLayout linear_holder1;
	private ImageView image_next1;
	private TextView text_email;
	private TextView email;
	private LinearLayout linear_holder2;
	private ImageView image_next2;
	private TextView textv_pass;
	private TextView password;
	private LinearLayout linear_holder3;
	private ImageView image_next3;
	private TextView text_delete_acc;
	private TextView text_uid;
	private LinearLayout linear_app_st_title;
	private LinearLayout linear_mode;
	private TextView text_logs;
	private ImageView imageview6;
	private TextView text_app_st;
	private TextView text_mode;
	private LinearLayout switch_mode;
	private LinearLayout switch_in;
	private LinearLayout linear_help_title;
	private TextView text_pp;
	private TextView text_tac;
	private TextView text_copy;
	private TextView text_contact;
	private TextView text_about;
	private ImageView image_help;
	private TextView text_help;
	private LinearLayout linear15;
	private LinearLayout linear_logout;
	private LinearLayout linear_share;
	private ImageView imageview7;
	private TextView textview1;
	private ImageView image_logout;
	private TextView text_logout;
	private ImageView imageview8;
	private TextView textview2;
	
	private Intent intent = new Intent();
	private SharedPreferences data;
	private Intent Intent2 = new Intent();
	private Intent intent3 = new Intent();
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
	private DatabaseReference user_pic = _firebase.getReference("profile/picture");
	private ChildEventListener _user_pic_child_listener;
	private AlertDialog.Builder dialog;
	private Intent intent4 = new Intent();
	private DatabaseReference verification = _firebase.getReference("profile/verification");
	private ChildEventListener _verification_child_listener;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.setting);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear_main = (LinearLayout) findViewById(R.id.linear_main);
		action_bar_1 = (LinearLayout) findViewById(R.id.action_bar_1);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		user_info = (LinearLayout) findViewById(R.id.user_info);
		ac_setting = (LinearLayout) findViewById(R.id.ac_setting);
		linear_s_settings = (LinearLayout) findViewById(R.id.linear_s_settings);
		linear_help = (LinearLayout) findViewById(R.id.linear_help);
		linear_more = (LinearLayout) findViewById(R.id.linear_more);
		image_back = (ImageView) findViewById(R.id.image_back);
		text_setting = (TextView) findViewById(R.id.text_setting);
		linear_image = (LinearLayout) findViewById(R.id.linear_image);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		cardview = (LinearLayout) findViewById(R.id.cardview);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		text_username = (TextView) findViewById(R.id.text_username);
		image_edit = (ImageView) findViewById(R.id.image_edit);
		verification_status = (TextView) findViewById(R.id.verification_status);
		user_info_title = (LinearLayout) findViewById(R.id.user_info_title);
		linear_country = (LinearLayout) findViewById(R.id.linear_country);
		linear_bio = (LinearLayout) findViewById(R.id.linear_bio);
		imageview5 = (ImageView) findViewById(R.id.imageview5);
		text_ac_info = (TextView) findViewById(R.id.text_ac_info);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		country = (TextView) findViewById(R.id.country);
		image_country = (ImageView) findViewById(R.id.image_country);
		text_country = (TextView) findViewById(R.id.text_country);
		text_bio = (TextView) findViewById(R.id.text_bio);
		text_msg = (TextView) findViewById(R.id.text_msg);
		acc_setting_title = (LinearLayout) findViewById(R.id.acc_setting_title);
		linear_email = (LinearLayout) findViewById(R.id.linear_email);
		linear_pass = (LinearLayout) findViewById(R.id.linear_pass);
		linear_delete_acc = (LinearLayout) findViewById(R.id.linear_delete_acc);
		image_acc_st = (ImageView) findViewById(R.id.image_acc_st);
		text_acc_st = (TextView) findViewById(R.id.text_acc_st);
		linear_holder1 = (LinearLayout) findViewById(R.id.linear_holder1);
		image_next1 = (ImageView) findViewById(R.id.image_next1);
		text_email = (TextView) findViewById(R.id.text_email);
		email = (TextView) findViewById(R.id.email);
		linear_holder2 = (LinearLayout) findViewById(R.id.linear_holder2);
		image_next2 = (ImageView) findViewById(R.id.image_next2);
		textv_pass = (TextView) findViewById(R.id.textv_pass);
		password = (TextView) findViewById(R.id.password);
		linear_holder3 = (LinearLayout) findViewById(R.id.linear_holder3);
		image_next3 = (ImageView) findViewById(R.id.image_next3);
		text_delete_acc = (TextView) findViewById(R.id.text_delete_acc);
		text_uid = (TextView) findViewById(R.id.text_uid);
		linear_app_st_title = (LinearLayout) findViewById(R.id.linear_app_st_title);
		linear_mode = (LinearLayout) findViewById(R.id.linear_mode);
		text_logs = (TextView) findViewById(R.id.text_logs);
		imageview6 = (ImageView) findViewById(R.id.imageview6);
		text_app_st = (TextView) findViewById(R.id.text_app_st);
		text_mode = (TextView) findViewById(R.id.text_mode);
		switch_mode = (LinearLayout) findViewById(R.id.switch_mode);
		switch_in = (LinearLayout) findViewById(R.id.switch_in);
		linear_help_title = (LinearLayout) findViewById(R.id.linear_help_title);
		text_pp = (TextView) findViewById(R.id.text_pp);
		text_tac = (TextView) findViewById(R.id.text_tac);
		text_copy = (TextView) findViewById(R.id.text_copy);
		text_contact = (TextView) findViewById(R.id.text_contact);
		text_about = (TextView) findViewById(R.id.text_about);
		image_help = (ImageView) findViewById(R.id.image_help);
		text_help = (TextView) findViewById(R.id.text_help);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		linear_logout = (LinearLayout) findViewById(R.id.linear_logout);
		linear_share = (LinearLayout) findViewById(R.id.linear_share);
		imageview7 = (ImageView) findViewById(R.id.imageview7);
		textview1 = (TextView) findViewById(R.id.textview1);
		image_logout = (ImageView) findViewById(R.id.image_logout);
		text_logout = (TextView) findViewById(R.id.text_logout);
		imageview8 = (ImageView) findViewById(R.id.imageview8);
		textview2 = (TextView) findViewById(R.id.textview2);
		data = getSharedPreferences("musicgarage", Activity.MODE_PRIVATE);
		Auth = FirebaseAuth.getInstance();
		dialog = new AlertDialog.Builder(this);
		
		linear_more.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		image_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		image_edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent4.setClass(getApplicationContext(), AccountChgActivity.class);
				data.edit().putString("event", "profile").commit();
				startActivity(intent4);
			}
		});
		
		linear_email.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent4.setClass(getApplicationContext(), AccountChgActivity.class);
				data.edit().putString("event", "email").commit();
				startActivity(intent4);
			}
		});
		
		linear_pass.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent4.setClass(getApplicationContext(), AccountChgActivity.class);
				data.edit().putString("event", "password").commit();
				startActivity(intent4);
			}
		});
		
		linear_delete_acc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog.Builder alert = new AlertDialog.Builder(SettingActivity.this);
				View view = getLayoutInflater().inflate(R.layout.dialog_default,null);
				TextView apply = (TextView)view.findViewById(R.id.apply);
				TextView cancel = (TextView)view.findViewById(R.id.cancel);
				TextView title = (TextView)view.findViewById(R.id.title);
				TextView message = (TextView)view.findViewById(R.id.message);
				ImageView image = (ImageView)view.findViewById(R.id.image);
				LinearLayout parent = (LinearLayout)view.findViewById(R.id.parent);
				alert.setView(view);
				final AlertDialog dialog = alert.create();
				title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
				message.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
				apply.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
				cancel.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
				_setBackground(parent,(double)50,(double)5,colorPrimary,false);
				_setBackground(apply,(double)50,(double)0,colorPrimary,true);
				_setBackground(cancel,(double)50,(double)0,colorPrimary,true);
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
					title.setText("Delete Account");
					message.setText("You want to delete your Music Garage account permanently?");
					apply.setText("DELETE");
					cancel.setText("CANCEL");
				}
				if (isDark) {
					apply.setTextColor(Color.parseColor("#f5f5f5"));
					cancel.setTextColor(Color.parseColor("#f5f5f5"));
					title.setTextColor(Color.parseColor("#f5f5f5"));
					message.setTextColor(Color.parseColor("#f5f5f5"));
					image.setImageResource(R.drawable.delete_white);
				}
				else {
					apply.setTextColor(Color.parseColor("#212121"));
					cancel.setTextColor(Color.parseColor("#212121"));
					title.setTextColor(Color.parseColor("#212121"));
					message.setTextColor(Color.parseColor("#212121"));
					image.setImageResource(R.drawable.delete_black);
				}
				dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));
				dialog.setCancelable(false);
				apply.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						if (true) {
							_MusicGarageProgress(true, true, "");
							deleteAccount();
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
		
		switch_mode.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (IsSwitch) {
					IsSwitch = false;
					_TransitionManager(linear_main, 200);
					switch_mode.setGravity(Gravity.LEFT);
					data.edit().putString("mode", "dark").commit();
				}
				else {
					IsSwitch = true;
					_TransitionManager(linear_main, 200);
					switch_mode.setGravity(Gravity.RIGHT);
					data.edit().putString("mode", "light").commit();
				}
			}
		});
		
		switch_in.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		text_pp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Intent2.setClass(getApplicationContext(), ExtraActivity.class);
				data.edit().putString("info", "pp").commit();
				startActivity(Intent2);
			}
		});
		
		text_tac.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Intent2.setClass(getApplicationContext(), ExtraActivity.class);
				data.edit().putString("info", "tac").commit();
				startActivity(Intent2);
			}
		});
		
		text_copy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Intent2.setClass(getApplicationContext(), ExtraActivity.class);
				data.edit().putString("info", "copyright").commit();
				startActivity(Intent2);
			}
		});
		
		text_contact.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		linear_logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog.Builder alert = new AlertDialog.Builder(SettingActivity.this);
				View view = getLayoutInflater().inflate(R.layout.dialog_default,null);
				TextView apply = (TextView)view.findViewById(R.id.apply);
				TextView cancel = (TextView)view.findViewById(R.id.cancel);
				TextView title = (TextView)view.findViewById(R.id.title);
				TextView message = (TextView)view.findViewById(R.id.message);
				ImageView image = (ImageView)view.findViewById(R.id.image);
				LinearLayout parent = (LinearLayout)view.findViewById(R.id.parent);
				alert.setView(view);
				final AlertDialog dialog = alert.create();
				title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
				message.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
				apply.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
				cancel.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
				_setBackground(parent,(double)50,(double)5,colorPrimary,false);
				_setBackground(apply,(double)50,(double)0,colorPrimary,true);
				_setBackground(cancel,(double)50,(double)0,colorPrimary,true);
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
					title.setText("Logout");
					message.setText("Are you sure do you want to logout from current Music Garage account?");
					apply.setText("YES");
					cancel.setText("NO, NOT NOW");
				}
				if (isDark) {
					apply.setTextColor(Color.parseColor("#f5f5f5"));
					cancel.setTextColor(Color.parseColor("#f5f5f5"));
					title.setTextColor(Color.parseColor("#f5f5f5"));
					message.setTextColor(Color.parseColor("#f5f5f5"));
					image.setImageResource(R.drawable.logout_white);
				}
				else {
					apply.setTextColor(Color.parseColor("#212121"));
					cancel.setTextColor(Color.parseColor("#212121"));
					title.setTextColor(Color.parseColor("#212121"));
					message.setTextColor(Color.parseColor("#212121"));
					image.setImageResource(R.drawable.logout_black);
				}
				dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));
				dialog.setCancelable(false);
				apply.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						if (true) {
							FirebaseAuth.getInstance().signOut();
							intent4.setClass(getApplicationContext(), LoginActivity.class);
							startActivity(intent4);
							finish();
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
		
		_profile_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					text_username.setText(_childValue.get("username").toString());
					text_country.setText(_childValue.get("country").toString());
					text_email.setText(_childValue.get("email").toString());
					text_uid.setText("UID: ".concat(_childKey));
					if (_childValue.containsKey("bio")) {
						text_msg.setText(_childValue.get("bio").toString());
					}
					else {
						linear_bio.setVisibility(View.GONE);
					}
					Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("countryflag").toString())).into(image_country);
				}
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
		
		_user_pic_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					_curcle_igm_url(_childValue.get("avatar").toString(), imageview3);
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					_curcle_igm_url(_childValue.get("avatar").toString(), imageview3);
				}
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
		user_pic.addChildEventListener(_user_pic_child_listener);
		
		_verification_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.get("verification").toString().equals("default")) {
						verification_status.setText("Melophile");
					}
					else {
						if (_childValue.get("verification").toString().equals("artist")) {
							verification_status.setText("Verified Artist");
						}
						else {
							if (_childValue.get("verification").toString().equals("moderator")) {
								verification_status.setText("Moderator");
							}
						}
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.get("verification").toString().equals("default")) {
						verification_status.setText("Melophile");
					}
					else {
						if (_childValue.get("verification").toString().equals("artist")) {
							verification_status.setText("Verified Artist");
						}
						else {
							if (_childValue.get("verification").toString().equals("moderator")) {
								verification_status.setText("Moderator");
							}
						}
					}
				}
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
		vscroll1.setOverScrollMode(View.OVER_SCROLL_NEVER);
		cardview.setTranslationZ(5);
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
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
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
	
	
	public void _SetStatusBarColor (final String _color) {
		Window w = this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(Color.parseColor(_color));
	}
	
	
	public void _removeScollBar (final View _view) {
		_view.setVerticalScrollBarEnabled(false); _view.setHorizontalScrollBarEnabled(false);
	}
	
	
	public void _TransitionManager (final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
	}
	
	
	public void _Elevation (final View _view, final double _number) {
		
		_view.setElevation((int)_number);
	}
	
	
	public void _fonts () {
		text_setting.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 1);
		text_username.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		verification_status.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_ac_info.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		country.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_country.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_bio.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_msg.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_acc_st.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_email.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		password.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_delete_acc.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_uid.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_app_st.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_mode.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_logs.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_help.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_pp.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_tac.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_copy.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_contact.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_about.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_logout.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
	}
	
	
	public void _UI_DarkMode (final boolean _isDarkMode, final String _accentColor) {
		if (_isDarkMode) {
			switch_mode.setGravity(Gravity.RIGHT);
		}
		else {
			color = "#FFFFFF";
			colorPrimary = "#f5f5f5";
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); }
			_SetStatusBarColor("#FFFFFF");
			Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  window.setNavigationBarColor(Color.parseColor("#00000000"));
			_Elevation(action_bar_1, 16);
			_removeScollBar(vscroll1);
			_setBackground(ac_setting, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 5, "#FFFFFF", false);
			_setBackground(user_info, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 5, "#FFFFFF", false);
			_setBackground(linear_s_settings, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 5, "#FFFFFF", false);
			_setBackground(linear_help, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 5, "#FFFFFF", false);
			_setBackground(image_back, SketchwareUtil.getDip(getApplicationContext(), (int)(50)), 0, "#FFFFFF", true);
			_setBackground(image_edit, SketchwareUtil.getDip(getApplicationContext(), (int)(50)), 0, "#FFFFFF", true);
			_setBackground(linear_image, SketchwareUtil.getDip(getApplicationContext(), (int)(90)), 8, "#FFFFFF", false);
			imageview5.setColorFilter(0xFF212121, PorterDuff.Mode.MULTIPLY);
			_setBackground(linear_email, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 0, "#FFFFFF", true);
			_setBackground(linear_pass, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 0, "#FFFFFF", true);
			_setBackground(linear_delete_acc, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 0, "#FFFFFF", true);
			_setBackground(text_logs, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 0, "#FFFFFF", true);
			_setBackground(text_pp, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 0, "#FFFFFF", true);
			_setBackground(text_tac, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 0, "#FFFFFF", true);
			_setBackground(text_copy, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 0, "#FFFFFF", true);
			_setBackground(text_contact, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 0, "#FFFFFF", true);
			_setBackground(text_about, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 0, "#FFFFFF", true);
			_setBackground(linear_more, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 5, "#FFFFFF", false);
			_setBackground(linear_logout, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 0, "#FFFFFF", true);
			_setBackground(linear_share, SketchwareUtil.getDip(getApplicationContext(), (int)(10)), 0, "#FFFFFF", true);
			_setBackground(switch_mode, SketchwareUtil.getDip(getApplicationContext(), (int)(75)), SketchwareUtil.getDip(getApplicationContext(), (int)(0)), "#BDBDBD", false);
			_setBackground(switch_in, SketchwareUtil.getDip(getApplicationContext(), (int)(75)), SketchwareUtil.getDip(getApplicationContext(), (int)(0)), "#212121", false);
			switch_mode.setGravity(Gravity.LEFT);
		}
		_fonts();
	}
	
	
	public void _Extra () {
	}
	private void deleteAccount() {
		
		final com.google.firebase.auth.FirebaseUser currentUser = Auth.getCurrentUser();
		 currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
			
			@Override public void onComplete(@androidx.annotation.NonNull Task<Void> task) {
				
				if (task.isSuccessful()) {
					_MusicGarageProgress(false, false, "");
					SketchwareUtil.showMessage(getApplicationContext(), "Account deleted successfully!");
					FirebaseAuth.getInstance().signOut();
					intent.setClass(getApplicationContext(), LoginActivity.class);
					startActivity(intent);
					finishAffinity();
				} else {
					_MusicGarageProgress(false, false, "");
					SketchwareUtil.showMessage(getApplicationContext(), "Oops! something went wrong");
				}}});
	}
	
	
	public void _MusicGarageProgress (final boolean _IfShow, final boolean _IfMSG, final String _msg) {
		if (_IfShow) {
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
			LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final TextView txt1 = (TextView) _view.findViewById(R.id.textview1);
			
			txt1.setText(_msg);
			if (_IfMSG) {
				txt1.setVisibility(View.VISIBLE);
			}
			else {
				txt1.setVisibility(View.GONE);
			}
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
	
	
	public void _curcle_igm_url (final String _url, final ImageView _img_view) {
		
		Glide.with(getApplicationContext()).asBitmap().load(_url).centerCrop().into(new com.bumptech.glide.request.target.BitmapImageViewTarget(_img_view) {
			@Override protected void setResource(Bitmap resource) {
				androidx.core.graphics.drawable.RoundedBitmapDrawable circularBitmapDrawable = androidx.core.graphics.drawable.RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource); circularBitmapDrawable.setCircular(true); _img_view.setImageDrawable(circularBitmapDrawable);
			}
		});
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