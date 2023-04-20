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
import android.app.Activity;
import android.content.SharedPreferences;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.content.Intent;
import android.net.Uri;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Context;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.graphics.Typeface;
import com.bumptech.glide.Glide;
import android.content.ClipData;
import android.content.ClipboardManager;
import com.facebook.ads.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class MessageActivity extends  AppCompatActivity  { 
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private boolean reply_check = false;
	private String reply_user_id = "";
	private String reply_user_message = "";
	private String reply_message_key = "";
	private HashMap<String, Object> map_upload = new HashMap<>();
	private String keyTextChild = "";
	private boolean DarkMode = false;
	private String color = "";
	private String colorPrimary = "";
	private String msg_key = "";
	private double reply_length = 0;
	private double rp_current = 0;
	private double n = 0;
	private String firstLetter = "";
	private double length = 0;
	private double num = 0;
	private boolean isDarkMode = false;
	private String ReasonString = "";
	private HashMap<String, Object> map_report = new HashMap<>();
	private double position = 0;
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> commentMap = new ArrayList<>();
	private ArrayList<String> childKays = new ArrayList<>();
	private ArrayList<String> profileList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> profileMap = new ArrayList<>();
	private ArrayList<String> reply_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> user_picMap = new ArrayList<>();
	private ArrayList<String> user_picKey = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> upload_list = new ArrayList<>();
	
	private LinearLayout main;
	private LinearLayout action_bar;
	private LinearLayout linear_no_data;
	private LinearLayout linear17;
	private LinearLayout reply;
	private LinearLayout linear_message;
	private ImageView image_back;
	private TextView textview6;
	private TextView textview7;
	private LinearLayout recycleview_holder;
	private LinearLayout rp;
	private ImageView reply_view;
	private TextView reply_length_text;
	private ImageView image_reply;
	private LinearLayout linear14;
	private ImageView image_cancel;
	private TextView text_reply_usename;
	private TextView text_reply_message;
	private EditText edittext1;
	private ImageView image_send;
	
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
	private DatabaseReference comments_db = _firebase.getReference("upload/msg");
	private ChildEventListener _comments_db_child_listener;
	private DatabaseReference user_pic = _firebase.getReference("profile/picture");
	private ChildEventListener _user_pic_child_listener;
	private SharedPreferences data;
	private Calendar cal = Calendar.getInstance();
	private Calendar now = Calendar.getInstance();
	private Calendar calendar = Calendar.getInstance();
	private DatabaseReference upload_text = _firebase.getReference("upload/text");
	private ChildEventListener _upload_text_child_listener;
	private Intent intent = new Intent();
	private TimerTask t;
	private DatabaseReference report_comment = _firebase.getReference("msg/report");
	private ChildEventListener _report_comment_child_listener;
	private Calendar c = Calendar.getInstance();
	private Vibrator v;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.message);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		main = (LinearLayout) findViewById(R.id.main);
		action_bar = (LinearLayout) findViewById(R.id.action_bar);
		linear_no_data = (LinearLayout) findViewById(R.id.linear_no_data);
		linear17 = (LinearLayout) findViewById(R.id.linear17);
		reply = (LinearLayout) findViewById(R.id.reply);
		linear_message = (LinearLayout) findViewById(R.id.linear_message);
		image_back = (ImageView) findViewById(R.id.image_back);
		textview6 = (TextView) findViewById(R.id.textview6);
		textview7 = (TextView) findViewById(R.id.textview7);
		recycleview_holder = (LinearLayout) findViewById(R.id.recycleview_holder);
		rp = (LinearLayout) findViewById(R.id.rp);
		reply_view = (ImageView) findViewById(R.id.reply_view);
		reply_length_text = (TextView) findViewById(R.id.reply_length_text);
		image_reply = (ImageView) findViewById(R.id.image_reply);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		image_cancel = (ImageView) findViewById(R.id.image_cancel);
		text_reply_usename = (TextView) findViewById(R.id.text_reply_usename);
		text_reply_message = (TextView) findViewById(R.id.text_reply_message);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		image_send = (ImageView) findViewById(R.id.image_send);
		Auth = FirebaseAuth.getInstance();
		data = getSharedPreferences("musicgarage", Activity.MODE_PRIVATE);
		v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		
		image_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (reply_check) {
					_reply_block(false);
				}
				else {
					finish();
				}
			}
		});
		
		reply_view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				n = 0;
				for(int _repeat11 = 0; _repeat11 < (int)(commentMap.size()); _repeat11++) {
					if (reply_list.size() == 0) {
						
					}
					else {
						if (commentMap.get((int)n).get("message_key").toString().equals(reply_list.get((int)(0)))) {
							reply_list.remove((int)(0));
							reply_length_text.setText(String.valueOf((long)(reply_list.size())));
							final androidx.recyclerview.widget.RecyclerView recyclerview1 = (androidx.recyclerview.widget.RecyclerView)findViewById(102204);
							recyclerview1.smoothScrollToPosition((int)n);
							if (reply_list.size() == 0) {
								rp.setVisibility(View.GONE);
							}
							map_upload = new HashMap<>();
							map_upload.put("reply_user_uid", "true");
							comments_db.child(commentMap.get((int)n).get("message_key").toString()).updateChildren(map_upload);
							map_upload.clear();
							break;
						}
						n++;
					}
				}
			}
		});
		
		image_cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_reply_block(false);
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (_charSeq.length() > 0) {
					_TransitionManager(linear_message, 100);
					image_send.setVisibility(View.VISIBLE);
				}
				else {
					_TransitionManager(linear_message, 100);
					image_send.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		image_send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext1.getText().toString().trim().length() > 0) {
					msg_key = comments_db.push().getKey();
					if (reply_check) {
						map_upload = new HashMap<>();
						map_upload.put("key", keyTextChild);
						map_upload.put("user_message", edittext1.getText().toString().trim());
						map_upload.put("user_uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
						map_upload.put("user_reply", "true");
						map_upload.put("reply_user_uid", reply_user_id);
						map_upload.put("reply_user_uid2", reply_user_id);
						map_upload.put("reply_user_message", reply_user_message);
						map_upload.put("reply_message_key", reply_message_key);
						cal = Calendar.getInstance();
						map_upload.put("message_time", String.valueOf((long)(cal.getTimeInMillis())));
						map_upload.put("message_date", new SimpleDateFormat("dd MMM yyyy").format(cal.getTime()));
						map_upload.put("message_key", msg_key);
						comments_db.child(msg_key).updateChildren(map_upload);
						map_upload.clear();
						_reply_block(false);
					}
					else {
						map_upload = new HashMap<>();
						map_upload.put("key", keyTextChild);
						map_upload.put("user_uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
						map_upload.put("user_message", edittext1.getText().toString());
						map_upload.put("user_reply", "false");
						cal = Calendar.getInstance();
						map_upload.put("message_time", String.valueOf((long)(cal.getTimeInMillis())));
						map_upload.put("message_date", new SimpleDateFormat("dd MMM yyyy").format(cal.getTime()));
						map_upload.put("message_key", msg_key);
						comments_db.child(msg_key).updateChildren(map_upload);
						map_upload.clear();
					}
					edittext1.setText("");
				}
				else {
					_customSnackBar("Message can't be empty!", 2);
				}
			}
		});
		
		_profile_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				profileList.add(_childKey);
				profileMap.add(_childValue);
				final androidx.recyclerview.widget.RecyclerView recyclerview1 = (androidx.recyclerview.widget.RecyclerView)findViewById(102204);
				recyclerview1.setAdapter(new Recyclerview1Adapter(commentMap));
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				profile.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						profileMap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								profileMap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						final androidx.recyclerview.widget.RecyclerView recyclerview1 = (androidx.recyclerview.widget.RecyclerView)findViewById(102204);
						recyclerview1.setAdapter(new Recyclerview1Adapter(commentMap));
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
		
		_comments_db_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("key").toString().equals(keyTextChild)) {
					childKays.add((int)(0), _childKey);
					commentMap.add((int)0, _childValue);
					final androidx.recyclerview.widget.RecyclerView recyclerview1 = (androidx.recyclerview.widget.RecyclerView)findViewById(102204);
					recyclerview1.setAdapter(new Recyclerview1Adapter(commentMap));
					if (_childValue.containsKey("user_reply")) {
						if (_childValue.get("user_reply").toString().equals("true")) {
							if (_childValue.get("reply_user_uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
								rp.setVisibility(View.VISIBLE);
								reply_length++;
								reply_list.add(_childValue.get("message_key").toString());
								reply_length_text.setText(String.valueOf((long)(reply_list.size())));
							}
							else {
								
							}
						}
						else {
							
						}
					}
				}
				if (commentMap.size() > 0) {
					linear_no_data.setVisibility(View.GONE);
					linear17.setVisibility(View.VISIBLE);
				}
				else {
					linear_no_data.setVisibility(View.VISIBLE);
					linear17.setVisibility(View.GONE);
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
				if (childKays.contains(_childKey)) {
					commentMap.remove((int)(childKays.indexOf(_childKey)));
					childKays.remove((int)(childKays.indexOf(_childKey)));
					final androidx.recyclerview.widget.RecyclerView recyclerview1 = (androidx.recyclerview.widget.RecyclerView)findViewById(102204);
					recyclerview1.setAdapter(new Recyclerview1Adapter(commentMap));
				}
				if (commentMap.size() > 0) {
					linear17.setVisibility(View.VISIBLE);
					linear_no_data.setVisibility(View.GONE);
				}
				else {
					linear17.setVisibility(View.GONE);
					linear_no_data.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		comments_db.addChildEventListener(_comments_db_child_listener);
		
		_user_pic_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				user_picKey.add(_childKey);
				user_picMap.add(_childValue);
				final androidx.recyclerview.widget.RecyclerView recyclerview1 = (androidx.recyclerview.widget.RecyclerView)findViewById(102204);
				recyclerview1.setAdapter(new Recyclerview1Adapter(commentMap));
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				user_pic.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						user_picMap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								user_picMap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						final androidx.recyclerview.widget.RecyclerView recyclerview1 = (androidx.recyclerview.widget.RecyclerView)findViewById(102204);
						recyclerview1.setAdapter(new Recyclerview1Adapter(commentMap));
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
		user_pic.addChildEventListener(_user_pic_child_listener);
		
		_upload_text_child_listener = new ChildEventListener() {
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
		upload_text.addChildEventListener(_upload_text_child_listener);
		
		_report_comment_child_listener = new ChildEventListener() {
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
		report_comment.addChildEventListener(_report_comment_child_listener);
		
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
		final androidx.recyclerview.widget.RecyclerView recyclerview1;
		
		recyclerview1 = new androidx.recyclerview.widget.RecyclerView(this);
		recyclerview1.setLayoutParams(new LinearLayout.LayoutParams(
		                                     LinearLayout.LayoutParams.MATCH_PARENT,
		                                     LinearLayout.LayoutParams.MATCH_PARENT));
		
		recyclerview1.setId(102204);
		
		recycleview_holder.addView(recyclerview1);
		
		androidx.recyclerview.widget.LinearLayoutManager layoutManager=
		                new androidx.recyclerview.widget.LinearLayoutManager(getApplicationContext(),androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,true);
		layoutManager.setReverseLayout(true);
		layoutManager.setStackFromEnd(false);
		recyclerview1.setLayoutManager(layoutManager);
		keyTextChild = getIntent().getStringExtra("key");
		reply.setVisibility(View.GONE);
		rp.setVisibility(View.GONE);
		image_send.setVisibility(View.GONE);
		_reply_block(false);
		_Elevation(action_bar, 10);
		if (data.getString("mode", "").equals("light")) {
			_UI_DarkMode(false, data.getString("accentColor", ""));
			DarkMode = false;
		}
		else {
			_UI_DarkMode(true, data.getString("accentColor", ""));
			DarkMode = true;
		}
		image_cancel.setColorFilter(0xFFF44336, PorterDuff.Mode.MULTIPLY);
		image_reply.setColorFilter(0xFFF44336, PorterDuff.Mode.MULTIPLY);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_reply_usename.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_reply_message.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		//initialize itemtouch helper
		SwipeController controller = new SwipeController(getApplicationContext(), new ISwipeControllerActions() {
			            @Override
			            public void onSwipePerformed(int position) {
				              
				v.vibrate((long)(100));
				_reply_block(true);
				_getReplyData(position);
				 }
			        });
		        androidx.recyclerview.widget.ItemTouchHelper itemTouchHelper = new androidx.recyclerview.widget.ItemTouchHelper(controller);
		        itemTouchHelper.attachToRecyclerView(recyclerview1);
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
		if (reply_check) {
			_reply_block(false);
		}
		else {
			finish();
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
	public void _swipeController () {
		//This is the swipe controller class
	}
	//added a closing bracket to close the more block
	public class SwipeController extends androidx.recyclerview.widget.ItemTouchHelper.Callback {
		
		    private Context mContext;
		    private ISwipeControllerActions mSwipeControllerActions;
		
		    private android.graphics.drawable.Drawable mReplyIcon;
		    private android.graphics.drawable.Drawable mReplyIconBackground;
		
		    private androidx.recyclerview.widget.RecyclerView.ViewHolder mCurrentViewHolder;
		    private View mView;
		
		    private float mDx = 0f;
		
		    private float mReplyButtonProgress = 0f;
		    private long  mLastReplyButtonAnimationTime = 0;
		
		    private boolean mSwipeBack = false;
		    private boolean mIsVibrating = false;
		    private boolean mStartTracking = false;
		
		    private int mBackgroundColor = 0x20606060;
		
		    private int mReplyBackgroundOffset = 18;
		
		    private int mReplyIconXOffset = 12;
		    private int mReplyIconYOffset = 11;
		
		    public SwipeController(Context context, ISwipeControllerActions swipeControllerActions){
			        mContext = context;
			        mSwipeControllerActions = swipeControllerActions;
			
			        mReplyIcon = mContext.getResources().getDrawable(R.drawable.ic_reply);
			        mReplyIconBackground = mContext.getResources().getDrawable(R.drawable.ic_round_shape);
			    }
		
		
		    public SwipeController(Context context, ISwipeControllerActions swipeControllerActions, int replyIcon, int replyIconBackground, int backgroundColor){
			        mContext = context;
			        mSwipeControllerActions = swipeControllerActions;
			
			        mReplyIcon = mContext.getResources().getDrawable(replyIcon);
			        mReplyIconBackground = mContext.getResources().getDrawable(replyIconBackground);
			        mBackgroundColor = backgroundColor;
			    }
		
		    @Override
		    public int getMovementFlags(@androidx.annotation.NonNull androidx.recyclerview.widget.RecyclerView recyclerView, @androidx.annotation.NonNull androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder) {
			        mView = viewHolder.itemView;
			        return androidx.recyclerview.widget.ItemTouchHelper.Callback.makeMovementFlags(androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_IDLE, androidx.recyclerview.widget.ItemTouchHelper.RIGHT);
			    }
		
		    @Override
		    public boolean onMove(@androidx.annotation.NonNull androidx.recyclerview.widget.RecyclerView recyclerView, @androidx.annotation.NonNull androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder, @androidx.annotation.NonNull androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder1) {
			        return false;
			    }
		
		    @Override
		    public void onSwiped(@androidx.annotation.NonNull androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder, int i) {
			
			    }
		
		    @Override
		    public int convertToAbsoluteDirection(int flags, int layoutDirection){
			        if (mSwipeBack){
				            mSwipeBack = false;
				            return 0;
				        }
			        return super.convertToAbsoluteDirection(flags, layoutDirection);
			    }
		
		    @Override
		    public void onChildDraw(@androidx.annotation.NonNull Canvas c, @androidx.annotation.NonNull androidx.recyclerview.widget.RecyclerView recyclerView, @androidx.annotation.NonNull androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){
			        if (actionState == androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE){
				            setTouchListener(recyclerView, viewHolder);
				        }
			        if (mView.getTranslationX() < convertToDp(130) || dX < mDx ){
				            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
				            mDx = dX;
				            mStartTracking = true;
				        }
			        mCurrentViewHolder = viewHolder;
			        drawReplyButton(c);
			    }
		
		    @android.annotation.SuppressLint("ClickableViewAccessibility")
		    private void setTouchListener(androidx.recyclerview.widget.RecyclerView recyclerView, final androidx.recyclerview.widget.RecyclerView.ViewHolder viewHolder){
			        recyclerView.setOnTouchListener(new View.OnTouchListener() {
				            @Override
				            public boolean onTouch(View v, MotionEvent event) {
					                if (event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP){
						                    mSwipeBack = true;
						                } else{
						                    mSwipeBack = false;
						                }
					                if (mSwipeBack){
						                    if (Math.abs(mView.getTranslationX()) >= convertToDp(100)){
							                        mSwipeControllerActions.onSwipePerformed(viewHolder.getAdapterPosition());
							                    }
						                }
					                return false;
					            }
				        });
			    }    
		private int convertToDp(int pixels){
			        if (pixels == 0){
				            return 0;
				        } else{
				            float density = mContext.getResources().getDisplayMetrics().density;
				            return (int) Math.ceil((density * pixels));
				        }
			    }
		  private void drawReplyButton(Canvas canvas){
			        if (mCurrentViewHolder == null){
				            return;
				        }
			
			        float translationX = mView.getTranslationX();
			        long newTime = System.currentTimeMillis();
			        long dt = Math.min(17, newTime - mLastReplyButtonAnimationTime);
			        mLastReplyButtonAnimationTime = newTime;
			        boolean showing = false;
			        if (translationX >= convertToDp(30)){
				            showing = true;
				        }
			        if (showing){
				            if (mReplyButtonProgress < 1.0f){
					                mReplyButtonProgress += dt / 180.0f;
					                if (mReplyButtonProgress > 1.0f){
						                    mReplyButtonProgress = 1.0f;
						                } else {
						                    mView.invalidate();
						                }
					            }
				        } else if (translationX <= 0.0f){
				            mReplyButtonProgress = 0f;
				            mStartTracking = false;
				            mIsVibrating = false;
				        } else {
				            if (mReplyButtonProgress > 0.0f){
					                mReplyButtonProgress -= dt / 180.0f;
					                if (mReplyButtonProgress < 0.1f){
						                    mReplyButtonProgress = 0f;
						                }
					            }
				            mView.invalidate();
				        }
			        int alpha;
			        float scale;
			        if (showing){
				            if (mReplyButtonProgress <= 0.8f){
					                scale = 1.2f * (mReplyButtonProgress / 0.8f);
					            } else{
					                scale = 1.2f - 0.2f * ((mReplyButtonProgress - 0.8f) / 0.2f);
					            }
				            alpha = Math.min(255, 255 * ((int)(mReplyButtonProgress / 0.8f)));
				        } else{
				            scale = mReplyButtonProgress;
				            alpha = Math.min(255, 255 * (int)mReplyButtonProgress);
				        }
			        mReplyIconBackground.setAlpha(alpha);
			        mReplyIcon.setAlpha(alpha);
			        if (mStartTracking){
				            if (!mIsVibrating && mView.getTranslationX() >= convertToDp(100)){
					                mView.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
					            }
				            mIsVibrating = true;
				        }
			
			        int x;
			        float y;
			        if (mView.getTranslationX() > convertToDp(130)){
				            x = convertToDp(130) / 2;
				        }
			        else {
				            x = (int) mView.getTranslationX() /2;
				        }
			
			        y = mView.getTop() + ((float) mView.getMeasuredHeight() /2);
			        mReplyIconBackground.setColorFilter(mBackgroundColor, PorterDuff.Mode.MULTIPLY);
			
			        mReplyIconBackground.setBounds(new Rect(
			                (int)(x - convertToDp(mReplyBackgroundOffset) * scale),
			                (int)(y - convertToDp(mReplyBackgroundOffset) * scale),
			                (int)(x + convertToDp(mReplyBackgroundOffset) * scale),
			                (int)(y + convertToDp(mReplyBackgroundOffset) * scale)
			        ));
			        mReplyIconBackground.draw(canvas);
			
			        mReplyIcon.setBounds(new Rect(
			                (int)(x - convertToDp(mReplyIconXOffset) * scale),
			                (int)(y - convertToDp(mReplyIconYOffset) * scale),
			                (int)(x + convertToDp(mReplyIconXOffset) * scale),
			                (int)(y + convertToDp(mReplyIconYOffset) * scale)
			        ));
			        mReplyIcon.draw(canvas);
			
			        mReplyIconBackground.setAlpha(255);
			        mReplyIcon.setAlpha(255);
			    }
	}
	
	
	public void _swipeInterface () {
		//swipe actions interface
	}
	public interface ISwipeControllerActions {
		
		    void onSwipePerformed(int position);
		
		
	}
	
	
	public void _recycleviewAdapter () {
		//here we create our own adapter
	}
	public class Recyclerview1Adapter extends androidx.recyclerview.widget.RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
				ArrayList<HashMap<String, Object>> _data;
				public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
						_data = _arr;
				}
				
				@Override
				public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
						LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
						View _v = _inflater.inflate(R.layout.custom, null);
						return new ViewHolder(_v);
				}
				
				@Override
				public void onBindViewHolder(ViewHolder _holder, final int _position) {
						View _view = _holder.itemView;
				
			//initialize our views
			            final LinearLayout a1 = (LinearLayout) _view.findViewById(R.id.a1);
			            
						final LinearLayout b1 = (LinearLayout) _view.findViewById(R.id.b1);
						
						final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
						
						final LinearLayout a1_view = (LinearLayout) _view.findViewById(R.id.a1_view);
						
						final LinearLayout linear10 = (LinearLayout) _view.findViewById(R.id.linear10);
						
						final LinearLayout a1_cardview = (LinearLayout) _view.findViewById(R.id.a1_cardview);
						
						final ImageView a1_image = (ImageView) _view.findViewById(R.id.a1_image);
						
						final LinearLayout a1_line = (LinearLayout) _view.findViewById(R.id.a1_line);
						
						final LinearLayout a1_reply = (LinearLayout) _view.findViewById(R.id.a1_reply);
						
						final TextView a1_username = (TextView) _view.findViewById(R.id.a1_username);
						
						final TextView a1_message = (TextView) _view.findViewById(R.id.a1_message);
						
						final LinearLayout a1_bulbing = (LinearLayout) _view.findViewById(R.id.a1_bulbing);
						
						final LinearLayout linear8 = (LinearLayout) _view.findViewById(R.id.linear8);
						
						final TextView a1_reply_user = (TextView) _view.findViewById(R.id.a1_reply_user);
						
						final TextView a1_reply_message = (TextView) _view.findViewById(R.id.a1_reply_message);
						
						final LinearLayout b1_line = (LinearLayout) _view.findViewById(R.id.b1_line);
						
						final LinearLayout b1_reply = (LinearLayout) _view.findViewById(R.id.b1_reply);
						
						final TextView b1_message = (TextView) _view.findViewById(R.id.b1_message);
						
						final LinearLayout b1_bulbing = (LinearLayout) _view.findViewById(R.id.b1_bulbing);
						
						final LinearLayout linear4 = (LinearLayout) _view.findViewById(R.id.linear4);
						
						final TextView b1_reply_user = (TextView) _view.findViewById(R.id.b1_reply_user);
						
						final TextView b1_reply_message = (TextView) _view.findViewById(R.id.b1_reply_message);
			
			            final LinearLayout profile_in = (LinearLayout) _view.findViewById(R.id.profile_in);
						
						final TextView username_alpha = (TextView) _view.findViewById(R.id.username_alpha);
			
						final TextView a1_time = (TextView) _view.findViewById(R.id.a1_time);
						
						final TextView b1_time = (TextView) _view.findViewById(R.id.b1_time);
						
						final LinearLayout linear_date = (LinearLayout) _view.findViewById(R.id.linear_date);
						
						final TextView date = (TextView) _view.findViewById(R.id.date);
			//onBindCustomView logic
				final View rootView = _view.getRootView();
						
						LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
						rootView.setLayoutParams(lp);
			a1_reply_user.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
			a1_username.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
			b1_reply_user.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
			a1_reply_message.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
			a1_message.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
			b1_reply_message.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
			b1_message.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
			b1_time.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
			a1_time.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
			date.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
			profile_in.setVisibility(View.GONE);
			if (DarkMode) {
				a1.setBackgroundColor(0xFF252525);
				b1.setBackgroundColor(0xFF252525);
				linear_date.setBackgroundColor(0xFF202125);
			}
			else {
				a1.setBackgroundColor(0xFFFFFFFF);
				b1.setBackgroundColor(0xFFFFFFFF);
				linear_date.setBackgroundColor(0xFFFFFFFF);
			}
			_singleDateBar(linear_date, date, commentMap, _position);
			if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(commentMap.get((int)_position).get("user_uid").toString())) {
				b1_message.setAutoLinkMask(android.text.util.Linkify.ALL);
				    b1_message.setLinkTextColor(Color.parseColor("#E0E0E0"));
				
				b1_message.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
				a1.setVisibility(View.GONE);
				b1.setVisibility(View.VISIBLE);
				if (commentMap.get((int)_position).containsKey("user_message")) {
					b1_message.setText(commentMap.get((int)_position).get("user_message").toString());
				}
				if (commentMap.get((int)_position).containsKey("user_reply")) {
					if (commentMap.get((int)_position).get("user_reply").toString().equals("true")) {
						b1_reply.setVisibility(View.VISIBLE);
						if (profileList.contains(commentMap.get((int)_position).get("user_uid").toString())) {
							b1_reply_user.setText(profileMap.get((int)profileList.indexOf(commentMap.get((int)_position).get("user_uid").toString())).get("username").toString());
						}
						b1_reply_message.setText(commentMap.get((int)_position).get("reply_user_message").toString());
					}
					else {
						b1_reply.setVisibility(View.GONE);
					}
				}
				_setTimeAndDate(b1_time, linear_date, date, _position);
				if (_position == 0) {
					_SX_CornerRadius_4(b1_line, data.getString("accentColor", ""), data.getString("accentColor", ""), 0, SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(5)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)));
					if (commentMap.size() == 1) {
						_SX_CornerRadius_4(b1_line, data.getString("accentColor", ""), data.getString("accentColor", ""), 0, SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)));
					}
					else {
						if (commentMap.get((int)_position).get("user_uid").toString().equals(commentMap.get((int)_position + 1).get("user_uid").toString())) {
							_SX_CornerRadius_4(b1_line, data.getString("accentColor", ""), data.getString("accentColor", ""), 0, SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(5)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)));
						}
					}
				}
				else {
					if (!commentMap.get((int)_position).get("user_uid").toString().equals(commentMap.get((int)_position - 1).get("user_uid").toString())) {
						_SX_CornerRadius_4(b1_line, data.getString("accentColor", ""), data.getString("accentColor", ""), 0, SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(5)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)));
						if (_position == (commentMap.size() - 1)) {
							_SX_CornerRadius_4(b1_line, data.getString("accentColor", ""), data.getString("accentColor", ""), 0, SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)));
						}
						else {
							if (!commentMap.get((int)_position).get("user_uid").toString().equals(commentMap.get((int)_position + 1).get("user_uid").toString())) {
								_SX_CornerRadius_4(b1_line, data.getString("accentColor", ""), data.getString("accentColor", ""), 0, SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)));
							}
						}
					}
					else {
						if (_position == (commentMap.size() - 1)) {
							if (commentMap.get((int)_position).get("user_uid").toString().equals(commentMap.get((int)_position - 1).get("user_uid").toString())) {
								_SX_CornerRadius_4(b1_line, data.getString("accentColor", ""), data.getString("accentColor", ""), 0, SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(5)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)));
							}
							else {
								_SX_CornerRadius_4(b1_line, data.getString("accentColor", ""), data.getString("accentColor", ""), 0, SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)));
							}
						}
						else {
							if (commentMap.get((int)_position).get("user_uid").toString().equals(commentMap.get((int)_position - 1).get("user_uid").toString()) && commentMap.get((int)_position).get("user_uid").toString().equals(commentMap.get((int)_position + 1).get("user_uid").toString())) {
								_SX_CornerRadius_4(b1_line, data.getString("accentColor", ""), data.getString("accentColor", ""), 0, SketchwareUtil.getDip(getApplicationContext(), (int)(50)), SketchwareUtil.getDip(getApplicationContext(), (int)(20)), SketchwareUtil.getDip(getApplicationContext(), (int)(20)), SketchwareUtil.getDip(getApplicationContext(), (int)(50)));
							}
							else {
								if (commentMap.get((int)_position).get("user_uid").toString().equals(commentMap.get((int)_position + 1).get("user_uid").toString())) {
									_SX_CornerRadius_4(b1_line, data.getString("accentColor", ""), data.getString("accentColor", ""), 0, SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(5)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)));
								}
								else {
									_SX_CornerRadius_4(b1_line, data.getString("accentColor", ""), data.getString("accentColor", ""), 0, SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(5)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)));
								}
							}
						}
					}
				}
				_LongClickAction(b1_reply, b1, _position);
			}
			else {
				a1_message.setAutoLinkMask(android.text.util.Linkify.ALL);
				    a1_message.setLinkTextColor(Color.parseColor("#4dd0e1"));
				
				a1_message.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
				a1.setVisibility(View.VISIBLE);
				b1.setVisibility(View.GONE);
				linear2.setVisibility(View.GONE);
				a1_view.setVisibility(View.VISIBLE);
				if (true) {
					n = 0;
					for(int _repeat267 = 0; _repeat267 < (int)(profileMap.size()); _repeat267++) {
						if (commentMap.get((int)_position).get("user_uid").toString().equals(profileMap.get((int)n).get("uid").toString())) {
							_textview_color(a1_username, profileMap.get((int)n).get("user_color").toString());
							_SX_CornerRadius_card(profile_in, profileMap.get((int)n).get("user_color").toString(), 200);
							break;
						}
						n++;
					}
				}
				if (commentMap.get((int)_position).containsKey("user_message")) {
					a1_message.setText(commentMap.get((int)_position).get("user_message").toString());
				}
				if (commentMap.get((int)_position).containsKey("user_uid")) {
					if (profileList.contains(commentMap.get((int)_position).get("user_uid").toString())) {
						a1_username.setText(profileMap.get((int)profileList.indexOf(commentMap.get((int)_position).get("user_uid").toString())).get("username").toString());
					}
				}
				if (commentMap.get((int)_position).containsKey("user_reply")) {
					if (commentMap.get((int)_position).get("user_reply").toString().equals("true")) {
						a1_reply.setVisibility(View.VISIBLE);
						if (profileList.contains(commentMap.get((int)_position).get("reply_user_uid2").toString())) {
							a1_reply_user.setText(profileMap.get((int)profileList.indexOf(commentMap.get((int)_position).get("reply_user_uid2").toString())).get("username").toString());
						}
						a1_reply_message.setText(commentMap.get((int)_position).get("reply_user_message").toString());
					}
					else {
						a1_reply.setVisibility(View.GONE);
					}
				}
				if (user_picKey.contains(commentMap.get((int)_position).get("user_uid").toString())) {
					Glide.with(getApplicationContext()).load(Uri.parse(user_picMap.get((int)user_picKey.indexOf(commentMap.get((int)_position).get("user_uid").toString())).get("avatar").toString())).into(a1_image);
					_addCardView(a1_cardview, 8, SketchwareUtil.getDip(getApplicationContext(), (int)(200)), 2, 2, true, "#FFFFFF");
				}
				else {
					_setProfileName(username_alpha, _position);
				}
				_setTimeAndDate(a1_time, linear_date, date, _position);
				if (_position == 0) {
					_SX_CornerRadius_4(a1_line, "#F5F5F5", "#F5F5F5", 0, SketchwareUtil.getDip(getApplicationContext(), (int)(5)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)));
					if (_position == 1) {
						if (user_picKey.contains(commentMap.get((int)_position).get("user_uid").toString())) {
							linear2.setVisibility(View.VISIBLE);
						}
						else {
							profile_in.setVisibility(View.VISIBLE);
						}
						a1_view.setVisibility(View.GONE);
					}
					else {
						if (!commentMap.get((int)_position).get("user_uid").toString().equals(commentMap.get((int)_position + 1).get("user_uid").toString())) {
							_SX_CornerRadius_4(a1_line, "#F5F5F5", "#F5F5F5", 0, SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)));
							if (user_picKey.contains(commentMap.get((int)_position).get("user_uid").toString())) {
								linear2.setVisibility(View.VISIBLE);
							}
							else {
								profile_in.setVisibility(View.VISIBLE);
							}
							a1_view.setVisibility(View.GONE);
						}
					}
				}
				else {
					if (!commentMap.get((int)_position).get("user_uid").toString().equals(commentMap.get((int)_position - 1).get("user_uid").toString())) {
						_SX_CornerRadius_4(a1_line, "#F5F5F5", "#F5F5F5", 0, SketchwareUtil.getDip(getApplicationContext(), (int)(5)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)));
						a1_view.setVisibility(View.VISIBLE);
						if (user_picKey.contains(commentMap.get((int)_position).get("user_uid").toString())) {
							linear2.setVisibility(View.GONE);
						}
						else {
							profile_in.setVisibility(View.GONE);
						}
						if (_position == (commentMap.size() - 1)) {
							_SX_CornerRadius_4(a1_line, "#F5F5F5", "#F5F5F5", 0, SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)));
							if (user_picKey.contains(commentMap.get((int)_position).get("user_uid").toString())) {
								linear2.setVisibility(View.VISIBLE);
							}
							else {
								profile_in.setVisibility(View.VISIBLE);
							}
							a1_view.setVisibility(View.GONE);
						}
						else {
							if (!commentMap.get((int)_position).get("user_uid").toString().equals(commentMap.get((int)_position + 1).get("user_uid").toString())) {
								_SX_CornerRadius_4(a1_line, "#F5F5F5", "#F5F5F5", 0, SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)));
								if (user_picKey.contains(commentMap.get((int)_position).get("user_uid").toString())) {
									linear2.setVisibility(View.VISIBLE);
								}
								else {
									profile_in.setVisibility(View.VISIBLE);
								}
								a1_view.setVisibility(View.GONE);
							}
						}
					}
					else {
						if (_position == (commentMap.size() - 1)) {
							if (commentMap.get((int)_position).get("user_uid").toString().equals(commentMap.get((int)_position - 1).get("user_uid").toString())) {
								_SX_CornerRadius_4(a1_line, "#F5F5F5", "#F5F5F5", 0, SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(5)));
							}
							else {
								_SX_CornerRadius_4(a1_line, "#F5F5F5", "#F5F5F5", 0, SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)));
							}
							if (user_picKey.contains(commentMap.get((int)_position).get("user_uid").toString())) {
								linear2.setVisibility(View.VISIBLE);
							}
							else {
								profile_in.setVisibility(View.VISIBLE);
							}
							a1_view.setVisibility(View.GONE);
						}
						else {
							if (commentMap.get((int)_position).get("user_uid").toString().equals(commentMap.get((int)_position - 1).get("user_uid").toString()) && commentMap.get((int)_position).get("user_uid").toString().equals(commentMap.get((int)_position + 1).get("user_uid").toString())) {
								_SX_CornerRadius_4(a1_line, "#F5F5F5", "#F5F5F5", 0, SketchwareUtil.getDip(getApplicationContext(), (int)(20)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(20)));
								if (user_picKey.contains(commentMap.get((int)_position).get("user_uid").toString())) {
									linear2.setVisibility(View.GONE);
								}
								else {
									profile_in.setVisibility(View.GONE);
								}
								a1_view.setVisibility(View.VISIBLE);
							}
							else {
								if (commentMap.get((int)_position).get("user_uid").toString().equals(commentMap.get((int)_position + 1).get("user_uid").toString())) {
									_SX_CornerRadius_4(a1_line, "#F5F5F5", "#F5F5F5", 0, SketchwareUtil.getDip(getApplicationContext(), (int)(5)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)));
									if (user_picKey.contains(commentMap.get((int)_position).get("user_uid").toString())) {
										linear2.setVisibility(View.GONE);
									}
									else {
										profile_in.setVisibility(View.GONE);
									}
									a1_view.setVisibility(View.VISIBLE);
								}
								else {
									_SX_CornerRadius_4(a1_line, "#F5F5F5", "#F5F5F5", 0, SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(40)), SketchwareUtil.getDip(getApplicationContext(), (int)(5)));
									if (user_picKey.contains(commentMap.get((int)_position).get("user_uid").toString())) {
										linear2.setVisibility(View.VISIBLE);
									}
									else {
										profile_in.setVisibility(View.VISIBLE);
									}
									a1_view.setVisibility(View.GONE);
								}
							}
						}
					}
				}
				_LongClickAction(a1_reply, a1, _position);
			}
			//end of logic
			}
				
				@Override
				public int getItemCount() {
						return _data.size();
				}
				
				public class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder{
						public ViewHolder(View v){
								super(v);
						}
				}
				
			
	}
	
	
	public void _CardStyle (final View _view, final double _shadow, final double _radius, final String _color, final boolean _touch) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color));
		gd.setCornerRadius((int)_radius);
		_view.setBackground(gd);
		
		if (Build.VERSION.SDK_INT >= 21){
			_view.setElevation((int)_shadow);}
		if (_touch) {
			_view.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()){
						case MotionEvent.ACTION_DOWN:{
							ObjectAnimator scaleX = new ObjectAnimator();
							scaleX.setTarget(_view);
							scaleX.setPropertyName("scaleX");
							scaleX.setFloatValues(0.9f);
							scaleX.setDuration(100);
							scaleX.start();
							
							ObjectAnimator scaleY = new ObjectAnimator();
							scaleY.setTarget(_view);
							scaleY.setPropertyName("scaleY");
							scaleY.setFloatValues(0.9f);
							scaleY.setDuration(100);
							scaleY.start();
							break;
						}
						case MotionEvent.ACTION_UP:{
							
							ObjectAnimator scaleX = new ObjectAnimator();
							scaleX.setTarget(_view);
							scaleX.setPropertyName("scaleX");
							scaleX.setFloatValues((float)1);
							scaleX.setDuration(100);
							scaleX.start();
							
							ObjectAnimator scaleY = new ObjectAnimator();
							scaleY.setTarget(_view);
							scaleY.setPropertyName("scaleY");
							scaleY.setFloatValues((float)1);
							scaleY.setDuration(100);
							scaleY.start();
							
							break;
						}
					}
					return false;
				}
			});
		}
	}
	
	
	public void _TransitionManager (final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
	}
	
	
	public void _SX_CornerRadius_4 (final View _view, final String _color1, final String _color2, final double _str, final double _n1, final double _n2, final double _n3, final double _n4) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		
		gd.setColor(Color.parseColor(_color1));
		
		gd.setStroke((int)_str, Color.parseColor(_color2));
		
		gd.setCornerRadii(new float[]{(int)_n1,(int)_n1,(int)_n2,(int)_n2,(int)_n3,(int)_n3,(int)_n4,(int)_n4});
		
		_view.setBackground(gd);
		
		_view.setElevation(0);
	}
	
	
	public void _textview_color (final TextView _view, final String _color) {
		_view.setTextColor(Color.parseColor(_color));
	}
	
	
	public void _imageColorFilter (final ImageView _view, final String _color) {
		_view.setColorFilter(Color.parseColor(_color), PorterDuff.Mode.MULTIPLY);
	}
	
	
	public void _reply_block (final boolean _reply) {
		_autoTransitionScroll(main);
		if (_reply) {
			reply.setVisibility(View.VISIBLE);
			reply_check = true;
		}
		else {
			reply.setVisibility(View.GONE);
			reply_check = false;
		}
	}
	
	
	public void _getReplyData (final double _position) {
		reply_user_id = commentMap.get((int)_position).get("user_uid").toString();
		reply_user_message = commentMap.get((int)_position).get("user_message").toString();
		reply_message_key = commentMap.get((int)_position).get("message_key").toString();
		if (profileList.contains(commentMap.get((int)_position).get("user_uid").toString())) {
			text_reply_usename.setText(profileMap.get((int)profileList.indexOf(commentMap.get((int)_position).get("user_uid").toString())).get("username").toString());
		}
		else {
			text_reply_usename.setText(commentMap.get((int)_position).get("user_uid").toString());
		}
		text_reply_message.setText(reply_user_message);
	}
	
	
	public void _Elevation (final View _view, final double _number) {
		
		_view.setElevation((int)_number);
	}
	
	
	public void _UI_DarkMode (final boolean _isDarkMode, final String _accentColor) {
		if (_isDarkMode) {
			
		}
		else {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); }
			_SetStatusBarColor("#FFFFFF");
			Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  window.setNavigationBarColor(Color.parseColor("#EEEEEE"));
			color = "#FFFFFF";
			colorPrimary = "#cccccc";
			_setBackground(image_back, SketchwareUtil.getDip(getApplicationContext(), (int)(50)), 0, color, true);
			_setBackground(image_cancel, SketchwareUtil.getDip(getApplicationContext(), (int)(50)), 0, "#EEEEEE", true);
			_setBackground(image_send, SketchwareUtil.getDip(getApplicationContext(), (int)(50)), 0, "#EEEEEE", true);
			_SX_CornerRadius_card(reply_view, "#E0E0E0", SketchwareUtil.getDip(getApplicationContext(), (int)(200)));
			_SX_CornerRadius_card(reply_length_text, _accentColor, SketchwareUtil.getDip(getApplicationContext(), (int)(50)));
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
	
	
	public void _autoTransitionScroll (final View _scroll) {
		android.transition.TransitionManager.beginDelayedTransition((LinearLayout)_scroll, new android.transition.AutoTransition());
	}
	
	
	public void _SX_CornerRadius_card (final View _view, final String _color, final double _value) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color));
		gd.setCornerRadius((int)_value);
		_view.setBackground(gd);
		
		if (Build.VERSION.SDK_INT >= 21){
			_view.setElevation(5);
		}
	}
	
	
	public void _scrollMessage (final String _key) {
		n = 0;
		for(int _repeat11 = 0; _repeat11 < (int)(commentMap.size()); _repeat11++) {
			if (_key.equals(commentMap.get((int)n).get("message_key").toString())) {
				final androidx.recyclerview.widget.RecyclerView recyclerview1 = (androidx.recyclerview.widget.RecyclerView)findViewById(102204);
				recyclerview1.smoothScrollToPosition((int)n);
				break;
			}
			n++;
		}
	}
	
	
	public void _setProfileName (final TextView _text, final double _position) {
		if (profileList.contains(commentMap.get((int)_position).get("user_uid").toString())) {
			firstLetter = profileMap.get((int)profileList.indexOf(commentMap.get((int)_position).get("user_uid").toString())).get("username").toString().substring((int)(0), (int)(1));
			length = 0;
			for(int _repeat15 = 0; _repeat15 < (int)(profileMap.get((int)profileList.indexOf(commentMap.get((int)_position).get("user_uid").toString())).get("username").toString().length()); _repeat15++) {
				if (profileMap.get((int)profileList.indexOf(commentMap.get((int)_position).get("user_uid").toString())).get("username").toString().substring((int)(length), (int)(length + 1)).equals(" ")) {
					_text.setText(firstLetter.concat(profileMap.get((int)profileList.indexOf(commentMap.get((int)_position).get("user_uid").toString())).get("username").toString().substring((int)(length + 1), (int)(length + 2))).toUpperCase());
				}
				else {
					length++;
				}
				if (profileMap.get((int)profileList.indexOf(commentMap.get((int)_position).get("user_uid").toString())).get("username").toString().length() == 1) {
					_text.setText(profileMap.get((int)profileList.indexOf(commentMap.get((int)_position).get("user_uid").toString())).get("username").toString());
				}
			}
		}
	}
	
	
	public void _addCardView (final View _layoutView, final double _margins, final double _cornerRadius, final double _cardElevation, final double _cardMaxElevation, final boolean _preventCornerOverlap, final String _backgroundColor) {
		androidx.cardview.widget.CardView cv = new androidx.cardview.widget.CardView(this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		int m = (int)_margins;
		lp.setMargins(m,m,m,m);
		cv.setLayoutParams(lp);
		int c = Color.parseColor(_backgroundColor);
		cv.setCardBackgroundColor(c);
		cv.setRadius((float)_cornerRadius);
		cv.setCardElevation((float)_cardElevation);
		cv.setMaxCardElevation((float)_cardMaxElevation);
		cv.setPreventCornerOverlap(_preventCornerOverlap);
		if(_layoutView.getParent() instanceof LinearLayout){
			ViewGroup vg = ((ViewGroup)_layoutView.getParent());
			vg.removeView(_layoutView);
			vg.removeAllViews();
			vg.addView(cv);
			cv.addView(_layoutView);
		}else{
			
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
	
	
	public void _LongClickAction (final View _view1, final View _view2, final double _position) {
		_view1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					
				_scrollMessage(commentMap.get((int)_position).get("reply_message_key").toString());
				}
		});
		_view2.setOnLongClickListener(new View.OnLongClickListener() {
			@Override public boolean onLongClick(View _view) {
				final AlertDialog.Builder alert = new AlertDialog.Builder(MessageActivity.this);
				View view = getLayoutInflater().inflate(R.layout.dialog_option,null);
				TextView cancel = (TextView)view.findViewById(R.id.cancel);
				TextView title = (TextView)view.findViewById(R.id.title);
				TextView profile_text = (TextView)view.findViewById(R.id.profile_text);
				TextView delete_text = (TextView)view.findViewById(R.id.delete_text);
				TextView report_text = (TextView)view.findViewById(R.id.report_text);
				TextView copy_text = (TextView)view.findViewById(R.id.comment_text);
				TextView pin_text = (TextView)view.findViewById(R.id.playlist_text);
				LinearLayout main = (LinearLayout)view.findViewById(R.id.main);
				LinearLayout profile = (LinearLayout)view.findViewById(R.id.profile);
				LinearLayout delete = (LinearLayout)view.findViewById(R.id.delete);
				LinearLayout report = (LinearLayout)view.findViewById(R.id.report);
				LinearLayout copy = (LinearLayout)view.findViewById(R.id.comment);
				LinearLayout pin = (LinearLayout)view.findViewById(R.id.playlist);
				ImageView image = (ImageView)view.findViewById(R.id.image);
				ImageView profile_image = (ImageView)view.findViewById(R.id.profile_image);
				ImageView delete_image = (ImageView)view.findViewById(R.id.delete_image);
				ImageView report_image = (ImageView)view.findViewById(R.id.report_image);
				ImageView copy_image = (ImageView)view.findViewById(R.id.comment_image);
				ImageView pin_image = (ImageView)view.findViewById(R.id.playlist_image);
				alert.setView(view);
				final AlertDialog dialog = alert.create();
				title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
				cancel.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
				profile_text.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
				delete_text.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
				report_text.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
				copy_text.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
				pin_text.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
				_setBackground(main,(double)50,(double)0,color,false);
				_setBackground(profile,(double)20,(double)0,color,true);
				_setBackground(delete,(double)20,(double)0,color,true);
				_setBackground(report,(double)20,(double)0,color,true);
				_setBackground(copy,(double)20,(double)0,color,true);
				_setBackground(pin,(double)20,(double)0,color,true);
				_setBackground(cancel,(double)50,(double)0,color,true);
				pin.setVisibility(View.GONE);
				if (commentMap.get((int)_position).get("user_uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					delete.setVisibility(View.VISIBLE);
					report.setVisibility(View.GONE);
				}
				else {
					delete.setVisibility(View.GONE);
					report.setVisibility(View.VISIBLE);
				}
				if (true) {
					ObjectAnimator scaleX = new ObjectAnimator();
					scaleX.setTarget(main);
					scaleX.setPropertyName("scaleX");
					scaleX.setFloatValues((float)0.8f,(float)1f);
					scaleX.setDuration(300);
					scaleX.setInterpolator(new AccelerateDecelerateInterpolator());
					scaleX.start();
					ObjectAnimator scaleY = new ObjectAnimator();
					scaleY.setTarget(main);
					scaleY.setPropertyName("scaleY");
					scaleY.setFloatValues((float)0.8f,(float)1f);
					scaleY.setDuration(300);
					scaleY.setInterpolator(new AccelerateDecelerateInterpolator());
					scaleY.start();
					ObjectAnimator alpha = new ObjectAnimator();
					alpha.setTarget(main);
					alpha.setPropertyName("alpha");
					alpha.setFloatValues((float)0f,(float)1);
					alpha.setDuration(300);
					alpha.setInterpolator(new AccelerateDecelerateInterpolator());
					alpha.start();
				}
				if (true) {
					title.setText("Choose your action");
					profile_text.setText("View Artist Profile");
					delete_text.setText("Delete this comment");
					report_text.setText("Report this comment");
					copy_text.setText("Copy this comment");
					pin_text.setText("Pin this comment");
				}
				delete_text.setTextColor(Color.parseColor("#f44336"));
				report_text.setTextColor(Color.parseColor("#f44336"));
				delete_image.setImageResource(R.drawable.delete_red);
				report_image.setImageResource(R.drawable.report_red);
				if (DarkMode) {
					title.setTextColor(Color.parseColor("#f5f5f5"));
					cancel.setTextColor(Color.parseColor("#f5f5f5"));
					profile_text.setTextColor(Color.parseColor("#f5f5f5"));
					copy_text.setTextColor(Color.parseColor("#f5f5f5"));
					pin_text.setTextColor(Color.parseColor("#f5f5f5"));
					profile_image.setImageResource(R.drawable.user_white);
					image.setImageResource(R.drawable.icon_info_white);
					copy_image.setImageResource(R.drawable.copy_white);
					pin_image.setImageResource(R.drawable.music_note_white);
				}
				else {
					cancel.setTextColor(Color.parseColor("#212121"));
					profile_text.setTextColor(Color.parseColor("#212121"));
					title.setTextColor(Color.parseColor("#212121"));
					copy_text.setTextColor(Color.parseColor("#212121"));
					pin_text.setTextColor(Color.parseColor("#212121"));
					profile_image.setImageResource(R.drawable.user_black);
					copy_image.setImageResource(R.drawable.copy_black);
					image.setImageResource(R.drawable.icon_info_white);
					image.setColorFilter(0xFF212121, PorterDuff.Mode.MULTIPLY);
					pin_image.setImageResource(R.drawable.music_note_black);
				}
				dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));
				dialog.setCancelable(false);
				profile.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						if (true) {
							intent.setClass(getApplicationContext(), ProfileActivity.class);
							intent.putExtra("uid", commentMap.get((int)_position).get("user_uid").toString());
							startActivity(intent);
						}
						dialog.dismiss();}});
				delete.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						if (true) {
							final AlertDialog.Builder alert = new AlertDialog.Builder(MessageActivity.this);
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
							cancel.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
							apply.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
							_setBackground(parent,(double)50,(double)5,color,false);
							_setBackground(apply,(double)50,(double)0,color,true);
							_setBackground(cancel,(double)50,(double)0,color,true);
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
								title.setText("Delete Comment");
								message.setText("Are you sure do you want to delete your comment?");
								apply.setText("DELETE");
								cancel.setText("CANCEL");
							}
							if (DarkMode) {
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
										comments_db.child(childKays.get((int)(_position))).removeValue();
										final androidx.recyclerview.widget.RecyclerView recyclerview1 = (androidx.recyclerview.widget.RecyclerView)findViewById(102204);
										recyclerview1.getAdapter().notifyItemInserted((int)_position);
										_customSnackBar("Comment deleted successfully!", 1);
									}
									dialog.dismiss();}});
							cancel.setOnClickListener(new View.OnClickListener() {
								@Override public void onClick(View v) {
									if (true) {
										
									}
									dialog.dismiss();}});
							dialog.show();
						}
						dialog.dismiss();}});
				report.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						if (true) {
							_ReportDialogMG(_position);
						}
						dialog.dismiss();}});
				copy.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						if (true) {
							((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", commentMap.get((int)_position).get("user_message").toString()));
							_customSnackBar("Comment copied!", 1);
						}
						dialog.dismiss();}});
				pin.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						if (true) {
							
						}
						dialog.dismiss();}});
				cancel.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						if (true) {
							
						}
						dialog.dismiss();}});
				dialog.show();
				return true;
			}
		});
	}
	
	
	public void _Animator (final View _view, final String _propertyName, final double _value, final double _duration) {
		ObjectAnimator anim = new ObjectAnimator();
		anim.setTarget(_view);
		anim.setPropertyName(_propertyName);
		anim.setFloatValues((float)_value);
		anim.setDuration((long)_duration);
		anim.start();
	}
	
	
	public void _ReportDialogMG (final double _position) {
		final AlertDialog.Builder alert = new AlertDialog.Builder(MessageActivity.this);
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
		_setBackground(parent,(double)50,(double)5,color,false);
		_setBackground(email,(double)10,(double)0,colorPrimary,false);
		_setBackground(apply,(double)50,(double)0,color,true);
		_setBackground(cancel,(double)50,(double)0,color,true);
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
			title.setText("Report Song");
			message.setText("Enter your valid reason why you are reporting this comment if your reason is valid then our team will delete this comment");
			email.setHint("Enter your reason");
			apply.setText("SUBMIT");
			cancel.setText("CANCEL");
			image.setImageResource(R.drawable.report_red);
		}
		title.setTextColor(Color.parseColor("#f44336"));
		apply.setTextColor(Color.parseColor("#f44336"));
		if (DarkMode) {
			message.setTextColor(Color.parseColor("#f5f5f5"));
			cancel.setTextColor(Color.parseColor("#f5f5f5"));
		}
		else {
			cancel.setTextColor(Color.parseColor("#212121"));
			message.setTextColor(Color.parseColor("#212121"));
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
				ReasonString = email.getText().toString();
				if (true) {
					if (ReasonString.trim().equals("")) {
						_customSnackBar("Enter Reason!", 2);
					}
					else {
						map_report = new HashMap<>();
						map_report.put("report by", FirebaseAuth.getInstance().getCurrentUser().getEmail());
						map_report.put("reported id", commentMap.get((int)_position).get("user_uid").toString());
						map_report.put("reported comment", commentMap.get((int)_position).get("user_message").toString());
						c = Calendar.getInstance();
						map_report.put("time", String.valueOf((long)(c.getTimeInMillis())));
						report_comment.push().updateChildren(map_report);
						map_report.clear();
						final AlertDialog.Builder alert = new AlertDialog.Builder(MessageActivity.this);
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
						_setBackground(parent,(double)50,(double)5,color,false);
						_setBackground(apply,(double)50,(double)0,color,true);
						_setBackground(cancel,(double)50,(double)0,color,true);
						cancel.setVisibility(View.GONE);
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
							title.setText("Comment Reported");
							message.setText("Thank you for reporting this comment our team will manually check your report and take action if your reason is valid and our team will send you email about this reported comment and also we send your report to the comment owner its take sometime but we will inform you. :-)");
							apply.setText("OK");
						}
						if (isDarkMode) {
							apply.setTextColor(Color.parseColor("#f5f5f5"));
							title.setTextColor(Color.parseColor("#f5f5f5"));
							message.setTextColor(Color.parseColor("#f5f5f5"));
							image.setImageResource(R.drawable.success_white);
						}
						else {
							apply.setTextColor(Color.parseColor("#212121"));
							title.setTextColor(Color.parseColor("#212121"));
							message.setTextColor(Color.parseColor("#212121"));
							image.setImageResource(R.drawable.success_black);
						}
						dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));
						dialog.setCancelable(false);
						apply.setOnClickListener(new View.OnClickListener() {
							@Override public void onClick(View v) {
								if (true) {
									
								}
								dialog.dismiss();}});
						dialog.show();
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
	
	
	public void _setTimeAndDate (final TextView _txt, final View _view, final TextView _txt_d, final double _position) {
		if (commentMap.get((int)_position).containsKey("message_time")) {
			calendar.setTimeInMillis((long)(Double.parseDouble(commentMap.get((int)_position).get("message_time").toString())));
			now = Calendar.getInstance();
			if ((long)(now.getTimeInMillis() - calendar.getTimeInMillis()) > (1000 * (3600 * 24))) {
				_txt.setText(new SimpleDateFormat("dd MMM yyyy").format(calendar.getTime()));
			}
			else {
				_txt.setText(new SimpleDateFormat("hh:mm a").format(calendar.getTime()));
			}
			_txt.setText(new SimpleDateFormat("hh:mm a").format(calendar.getTime()));
		}
	}
	
	
	public void _singleDateBar (final View _DateBar, final TextView _textview, final ArrayList<HashMap<String, Object>> _list, final double _position) {
		if (_position == (_list.size() - 1)) {
			if (_list.get((int)_position).containsKey("message_date")) {
				_DateBar.setVisibility(View.VISIBLE);
				_textview.setText(_list.get((int)_position).get("message_date").toString());
			}
			else {
				_DateBar.setVisibility(View.GONE);
			}
		}
		else {
			if (_list.get((int)_position + 1).containsKey("message_date") && _list.get((int)_position).containsKey("message_date")) {
				if (_list.get((int)_position + 1).get("message_date").toString().equals(_list.get((int)_position).get("message_date").toString())) {
					_DateBar.setVisibility(View.GONE);
				}
				else {
					_DateBar.setVisibility(View.VISIBLE);
					_textview.setText(_list.get((int)_position).get("message_date").toString());
				}
			}
			else {
				if (_list.get((int)_position).containsKey("message_date")) {
					_DateBar.setVisibility(View.VISIBLE);
					_textview.setText(_list.get((int)_position).get("message_date").toString());
				}
				else {
					_DateBar.setVisibility(View.GONE);
				}
			}
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