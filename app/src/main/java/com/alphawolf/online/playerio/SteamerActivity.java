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
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import java.util.Timer;
import java.util.TimerTask;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.media.MediaPlayer;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.OnProgressListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Continuation;
import java.io.File;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.text.DecimalFormat;
import android.graphics.Typeface;
import com.bumptech.glide.Glide;
import com.facebook.ads.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class SteamerActivity extends  AppCompatActivity  { 
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private String color = "";
	private String colorPrimary = "";
	private boolean isSheeting = false;
	private boolean isLeftBar = false;
	private boolean isDarkMode = false;
	private double tabsPos = 0;
	private HashMap<String, Object> map = new HashMap<>();
	private HashMap<String, Object> commentsMap = new HashMap<>();
	private String currentlyPlaying = "";
	private HashMap<String, Object> likes_map = new HashMap<>();
	private String PlaylistName = "";
	private double numPlaylist = 0;
	private boolean isPlaylist = false;
	private double openNum = 0;
	private HashMap<String, Object> view_map = new HashMap<>();
	private double positionScroll = 0;
	private boolean isSearching = false;
	private String ReasonString = "";
	private HashMap<String, Object> map_report = new HashMap<>();
	private String ID_Placement_banner = "";
	private double banner_fail = 0;
	private String ErrorLoadFBBannerAd = "";
	private double banner_complete = 0;
	private String ID_Interstitial = "";
	private double interstitial_fail = 0;
	private String ErrorFBInterstitial = "";
	private double interestial_complete = 0;
	private  final String TAG = MainActivity.class.getSimpleName();
	private  AdView adView;
	private  InterstitialAd interstitialAd;
	
	private ArrayList<HashMap<String, Object>> playlistMap = new ArrayList<>();
	private ArrayList<String> playlistString = new ArrayList<>();
	private ArrayList<String> username_list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> profile_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> upload_list = new ArrayList<>();
	private ArrayList<String> oldChildKey = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> oldListmap = new ArrayList<>();
	private ArrayList<String> childkey = new ArrayList<>();
	private ArrayList<String> currentlyChild = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> currentlyMap = new ArrayList<>();
	private ArrayList<String> commentsChildKey = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> commentMgMap = new ArrayList<>();
	private ArrayList<String> likeChild = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> like_Mgmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> playKeys = new ArrayList<>();
	
	private LinearLayout main;
	private LinearLayout fragment1;
	private LinearLayout left_bar;
	private LinearLayout mask;
	private LinearLayout linear_holder;
	private LinearLayout bottom_sheet;
	private LinearLayout action_bar;
	private LinearLayout linear_data;
	private LinearLayout banner_container;
	private ListView listview1;
	private ImageView image_menu;
	private TextView music;
	private TextView garage;
	private TextView text_playlist;
	private EditText edittext_search;
	private ImageView image_search;
	private LinearLayout linear_data_prog;
	private TextView text_data;
	private LinearLayout bottom_t;
	private LinearLayout bottom_b;
	private LinearLayout linear10;
	private LinearLayout linear11;
	private ImageView sheeting_icon;
	private ImageView image_fav;
	private ImageView image_repeat;
	private ImageView image_download;
	private ImageView image_focus;
	private LinearLayout linear20;
	private LinearLayout linear_info;
	private LinearLayout linear_seekbar;
	private LinearLayout linear21;
	private LinearLayout linear23;
	private LinearLayout linear13;
	private LinearLayout linear15;
	private LinearLayout cardview;
	private ImageView album;
	private LinearLayout linear14;
	private LinearLayout linear16;
	private TextView song_title;
	private TextView text_artist;
	private TextView text_dot;
	private TextView text_album;
	private LinearLayout linear18;
	private SeekBar seekbar1;
	private TextView text_current;
	private LinearLayout linear19;
	private TextView text_duration;
	private ImageView image_prev;
	private ImageView image_play;
	private ImageView image_next;
	private LinearLayout adcontainer;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear9;
	private LinearLayout linear8;
	private ImageView cancel;
	private ImageView home;
	private ImageView favorite;
	private ImageView played;
	private ImageView playlist;
	private ImageView add_song;
	private ImageView settings;
	private ImageView image_shuffle;
	private ImageView image_nightcore;
	private ImageView profile_image;
	
	private Intent intent = new Intent();
	private SharedPreferences data;
	private TimerTask timer;
	private TimerTask time;
	private Intent intent_st = new Intent();
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
	private MediaPlayer mp;
	private DatabaseReference upload_text = _firebase.getReference("upload/text");
	private ChildEventListener _upload_text_child_listener;
	private DatabaseReference fb_likes = _firebase.getReference("upload/likes");
	private ChildEventListener _fb_likes_child_listener;
	private DatabaseReference profile = _firebase.getReference("profile/info");
	private ChildEventListener _profile_child_listener;
	private DatabaseReference user_pic = _firebase.getReference("profile/picture");
	private ChildEventListener _user_pic_child_listener;
	private DatabaseReference comments_db = _firebase.getReference("upload/msg");
	private ChildEventListener _comments_db_child_listener;
	private AlertDialog.Builder d;
	private TimerTask timing;
	private StorageReference song_upload = _firebase_storage.getReference("upload/song");
	private OnCompleteListener<Uri> _song_upload_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _song_upload_download_success_listener;
	private OnSuccessListener _song_upload_delete_success_listener;
	private OnProgressListener _song_upload_upload_progress_listener;
	private OnProgressListener _song_upload_download_progress_listener;
	private OnFailureListener _song_upload_failure_listener;
	private StorageReference image_upload = _firebase_storage.getReference("upload/image");
	private OnCompleteListener<Uri> _image_upload_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _image_upload_download_success_listener;
	private OnSuccessListener _image_upload_delete_success_listener;
	private OnProgressListener _image_upload_upload_progress_listener;
	private OnProgressListener _image_upload_download_progress_listener;
	private OnFailureListener _image_upload_failure_listener;
	private Calendar cal = Calendar.getInstance();
	private DatabaseReference report_song = _firebase.getReference("upload/report");
	private ChildEventListener _report_song_child_listener;
	private TimerTask ad;
	private TimerTask banner;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.steamer);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		
		main = (LinearLayout) findViewById(R.id.main);
		fragment1 = (LinearLayout) findViewById(R.id.fragment1);
		left_bar = (LinearLayout) findViewById(R.id.left_bar);
		mask = (LinearLayout) findViewById(R.id.mask);
		linear_holder = (LinearLayout) findViewById(R.id.linear_holder);
		bottom_sheet = (LinearLayout) findViewById(R.id.bottom_sheet);
		action_bar = (LinearLayout) findViewById(R.id.action_bar);
		linear_data = (LinearLayout) findViewById(R.id.linear_data);
		banner_container = (LinearLayout) findViewById(R.id.banner_container);
		listview1 = (ListView) findViewById(R.id.listview1);
		image_menu = (ImageView) findViewById(R.id.image_menu);
		music = (TextView) findViewById(R.id.music);
		garage = (TextView) findViewById(R.id.garage);
		text_playlist = (TextView) findViewById(R.id.text_playlist);
		edittext_search = (EditText) findViewById(R.id.edittext_search);
		image_search = (ImageView) findViewById(R.id.image_search);
		linear_data_prog = (LinearLayout) findViewById(R.id.linear_data_prog);
		text_data = (TextView) findViewById(R.id.text_data);
		bottom_t = (LinearLayout) findViewById(R.id.bottom_t);
		bottom_b = (LinearLayout) findViewById(R.id.bottom_b);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		sheeting_icon = (ImageView) findViewById(R.id.sheeting_icon);
		image_fav = (ImageView) findViewById(R.id.image_fav);
		image_repeat = (ImageView) findViewById(R.id.image_repeat);
		image_download = (ImageView) findViewById(R.id.image_download);
		image_focus = (ImageView) findViewById(R.id.image_focus);
		linear20 = (LinearLayout) findViewById(R.id.linear20);
		linear_info = (LinearLayout) findViewById(R.id.linear_info);
		linear_seekbar = (LinearLayout) findViewById(R.id.linear_seekbar);
		linear21 = (LinearLayout) findViewById(R.id.linear21);
		linear23 = (LinearLayout) findViewById(R.id.linear23);
		linear13 = (LinearLayout) findViewById(R.id.linear13);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		cardview = (LinearLayout) findViewById(R.id.cardview);
		album = (ImageView) findViewById(R.id.album);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		song_title = (TextView) findViewById(R.id.song_title);
		text_artist = (TextView) findViewById(R.id.text_artist);
		text_dot = (TextView) findViewById(R.id.text_dot);
		text_album = (TextView) findViewById(R.id.text_album);
		linear18 = (LinearLayout) findViewById(R.id.linear18);
		seekbar1 = (SeekBar) findViewById(R.id.seekbar1);
		text_current = (TextView) findViewById(R.id.text_current);
		linear19 = (LinearLayout) findViewById(R.id.linear19);
		text_duration = (TextView) findViewById(R.id.text_duration);
		image_prev = (ImageView) findViewById(R.id.image_prev);
		image_play = (ImageView) findViewById(R.id.image_play);
		image_next = (ImageView) findViewById(R.id.image_next);
		adcontainer = (LinearLayout) findViewById(R.id.adcontainer);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		cancel = (ImageView) findViewById(R.id.cancel);
		home = (ImageView) findViewById(R.id.home);
		favorite = (ImageView) findViewById(R.id.favorite);
		played = (ImageView) findViewById(R.id.played);
		playlist = (ImageView) findViewById(R.id.playlist);
		add_song = (ImageView) findViewById(R.id.add_song);
		settings = (ImageView) findViewById(R.id.settings);
		image_shuffle = (ImageView) findViewById(R.id.image_shuffle);
		image_nightcore = (ImageView) findViewById(R.id.image_nightcore);
		profile_image = (ImageView) findViewById(R.id.profile_image);
		data = getSharedPreferences("musicgarage", Activity.MODE_PRIVATE);
		Auth = FirebaseAuth.getInstance();
		d = new AlertDialog.Builder(this);
		
		mask.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				isLeftBar = false;
				_OpenCloseLeftBar(isLeftBar);
			}
		});
		
		image_menu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (isPlaylist) {
					isPlaylist = false;
					childkey.clear();
					upload_list.clear();
					listview1.setAdapter(new Listview1Adapter(upload_list));
					_TransitionManager(fragment1, 300);
					listview1.setVisibility(View.GONE);
					listview2.setVisibility(View.VISIBLE);
					text_playlist.setVisibility(View.GONE);
					music.setVisibility(View.VISIBLE);
					garage.setVisibility(View.VISIBLE);
					image_search.setVisibility(View.VISIBLE);
					image_menu.setImageResource(R.drawable.menu_black);
				}
				else {
					if (!isSearching) {
						if (isLeftBar) {
							isLeftBar = false;
							_OpenCloseLeftBar(isLeftBar);
							edittext_search.setEnabled(false);
						}
						else {
							isLeftBar = true;
							_OpenCloseLeftBar(isLeftBar);
							edittext_search.setEnabled(true);
						}
					}
					else {
						isSearching = false;
						_TransitionManager(fragment1, 300);
						edittext_search.setVisibility(View.GONE);
						edittext_search.setText("");
						music.setVisibility(View.VISIBLE);
						garage.setVisibility(View.VISIBLE);
						image_search.setVisibility(View.VISIBLE);
						image_menu.setImageResource(R.drawable.menu_black);
						_setMenu(tabsPos);
					}
					android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow(edittext_search.getWindowToken(), 0);
				}
			}
		});
		
		edittext_search.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				childkey.clear();
				upload_list.clear();
				double _tmpNum = 0;
				for(int _repeat13 = 0; _repeat13 < (int)(oldListmap.size()); _repeat13++) {
					if (oldListmap.get((int)_tmpNum).get("name").toString().toLowerCase().contains(_charSeq.toLowerCase().trim()) || (oldListmap.get((int)_tmpNum).get("uid").toString().toLowerCase().contains(_charSeq.toLowerCase().trim()) || _charSeq.trim().equals(""))) {
						childkey.add((int)(0), oldChildKey.get((int)(_tmpNum)));
						{
							HashMap<String, Object> _item = new HashMap<>();
							_item = oldListmap.get((int)_tmpNum);
							upload_list.add((int)0, _item);
						}
						int _index = listview1.getFirstVisiblePosition();
						View _view = listview1.getChildAt(0);
						int _top = (_view == null) ? 0 : _view.getTop();
						listview1.setAdapter(new Listview1Adapter(upload_list));
						listview1.setSelectionFromTop(_index, _top);
					}
					else {
						if ((_tmpNum == (oldListmap.size() - 1)) && (upload_list.size() == 0)) {
							int _index = listview1.getFirstVisiblePosition();
							View _view = listview1.getChildAt(0);
							int _top = (_view == null) ? 0 : _view.getTop();
							listview1.setAdapter(new Listview1Adapter(upload_list));
							listview1.setSelectionFromTop(_index, _top);
						}
					}
					_tmpNum++;
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		image_search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				isSearching = true;
				_TransitionManager(fragment1, 300);
				listview2.setVisibility(View.GONE);
				listview1.setVisibility(View.VISIBLE);
				music.setVisibility(View.GONE);
				garage.setVisibility(View.GONE);
				image_search.setVisibility(View.GONE);
				edittext_search.setVisibility(View.VISIBLE);
				image_menu.setImageResource(R.drawable.back_grey);
				edittext_search.setText("");
				childkey.clear();
				upload_list.clear();
				double _tmpNum = 0;
				for(int _repeat23 = 0; _repeat23 < (int)(oldListmap.size()); _repeat23++) {
					childkey.add((int)(0), oldChildKey.get((int)(_tmpNum)));
					{
						HashMap<String, Object> _item = new HashMap<>();
						_item = oldListmap.get((int)_tmpNum);
						upload_list.add((int)0, _item);
					}
					int _index = listview1.getFirstVisiblePosition();
					View _viewl = listview1.getChildAt(0);
					int _top = (_viewl == null) ? 0 : _viewl.getTop();
					listview1.setAdapter(new Listview1Adapter(upload_list));
					listview1.setSelectionFromTop(_index, _top);
				}
			}
		});
		
		sheeting_icon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (isSheeting) {
					_CheetAnim(bottom_sheet, 330, 80, 300);
					isSheeting = false;
				}
				else {
					_CheetAnim(bottom_sheet, 330, 80, 300);
					isSheeting = true;
				}
			}
		});
		
		image_fav.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (likeChild.contains(currentlyPlaying.concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))) {
					fb_likes.child(currentlyPlaying.concat(FirebaseAuth.getInstance().getCurrentUser().getUid())).removeValue();
				}
				else {
					likes_map = new HashMap<>();
					likes_map.put("key", currentlyPlaying);
					likes_map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					fb_likes.child(currentlyPlaying.concat(FirebaseAuth.getInstance().getCurrentUser().getUid())).updateChildren(likes_map);
				}
			}
		});
		
		image_repeat.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (data.getString("repeat", "").equals("0")) {
					data.edit().putString("repeat", "1").commit();
					image_repeat.setImageResource(R.drawable.repeat_red);
				}
				else {
					if (data.getString("repeat", "").equals("1")) {
						data.edit().putString("repeat", "2").commit();
						image_repeat.setImageResource(R.drawable.repeat_one_red);
					}
					else {
						if (data.getString("repeat", "").equals("2")) {
							data.edit().putString("repeat", "0").commit();
							if (data.getString("mode", "").equals("light")) {
								image_repeat.setImageResource(R.drawable.repeat_black);
							}
							else {
								image_repeat.setImageResource(R.drawable.repeat_white);
							}
						}
						else {
							
						}
					}
				}
			}
		});
		
		image_download.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final AlertDialog.Builder alert = new AlertDialog.Builder(SteamerActivity.this);
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
				cancel.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
				apply.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
				message.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
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
					title.setText("Download Song");
					message.setText("Are you sure to download ".concat(currentlyMap.get((int)currentlyChild.indexOf(currentlyPlaying)).get("name").toString().concat("?")));
					apply.setText("YES");
					cancel.setText("NO");
				}
				if (isDarkMode) {
					apply.setTextColor(Color.parseColor("#f5f5f5"));
					cancel.setTextColor(Color.parseColor("#f5f5f5"));
					title.setTextColor(Color.parseColor("#f5f5f5"));
					message.setTextColor(Color.parseColor("#f5f5f5"));
					image.setImageResource(R.drawable.download_white);
				}
				else {
					apply.setTextColor(Color.parseColor("#212121"));
					cancel.setTextColor(Color.parseColor("#212121"));
					title.setTextColor(Color.parseColor("#212121"));
					message.setTextColor(Color.parseColor("#212121"));
					image.setImageResource(R.drawable.download_black);
				}
				dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));
				dialog.setCancelable(false);
				apply.setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View v) {
						if (true) {
							_downloadFile(currentlyMap.get((int)currentlyChild.indexOf(currentlyPlaying)).get("url").toString(), FileUtil.getPublicDir(Environment.DIRECTORY_MOVIES));
							_customSnackBar("Downloading starts......", 0);
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
		
		image_focus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				listview1.smoothScrollToPosition((int)(positionScroll));
			}
		});
		
		seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged (SeekBar _param1, int _param2, boolean _param3) {
				final int _progressValue = _param2;
				text_current.setText(new DecimalFormat("00").format(_progressValue / 60).concat(":".concat(new DecimalFormat("00").format(_progressValue % 60))));
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar _param1) {
				mp.pause();
				_removeFocus();
			}
			
			@Override
			public void onStopTrackingTouch(SeekBar _param2) {
				mp.seekTo((int)(seekbar1.getProgress() * 1000));
				_requestFocus();
			}
		});
		
		image_prev.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mp.stop();
				if (data.getString("shuffle", "").equals("true")) {
					_play(currentlyChild.get((int)(SketchwareUtil.getRandom((int)(0), (int)(currentlyMap.size() - 1)))));
				}
				else {
					if (currentlyChild.indexOf(currentlyPlaying) > 0) {
						_play(currentlyChild.get((int)(currentlyChild.indexOf(currentlyPlaying) - 1)));
					}
					else {
						_play(currentlyChild.get((int)(currentlyMap.size() - 1)));
					}
				}
			}
		});
		
		image_play.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (mp.isPlaying()) {
					mp.pause();
					_removeFocus();
				}
				else {
					_requestFocus();
				}
			}
		});
		
		image_next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mp.stop();
				if (data.getString("shuffle", "").equals("true")) {
					_play(currentlyChild.get((int)(SketchwareUtil.getRandom((int)(0), (int)(currentlyMap.size() - 1)))));
				}
				else {
					if (currentlyMap.size() > (currentlyChild.indexOf(currentlyPlaying) + 1)) {
						_play(currentlyChild.get((int)(currentlyChild.indexOf(currentlyPlaying) + 1)));
					}
					else {
						_play(currentlyChild.get((int)(0)));
					}
				}
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				isLeftBar = false;
				_OpenCloseLeftBar(isLeftBar);
			}
		});
		
		home.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_setImageViewRipple(home, "#2196F3", "#2196F3");
				_setImageViewRipple(favorite, "#838383", "#2196F3");
				_setImageViewRipple(played, "#838383", "#2196F3");
				_setImageViewRipple(playlist, "#838383", "#2196F3");
				_setMenu(0);
			}
		});
		
		favorite.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_setImageViewRipple(favorite, "#2196F3", "#2196F3");
				_setImageViewRipple(home, "#838383", "#2196F3");
				_setImageViewRipple(played, "#838383", "#2196F3");
				_setImageViewRipple(playlist, "#838383", "#2196F3");
				_setMenu(1);
			}
		});
		
		played.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_setImageViewRipple(played, "#2196F3", "#2196F3");
				_setImageViewRipple(home, "#838383", "#2196F3");
				_setImageViewRipple(favorite, "#838383", "#2196F3");
				_setImageViewRipple(playlist, "#838383", "#2196F3");
				_setMenu(2);
			}
		});
		
		playlist.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_setImageViewRipple(playlist, "#2196F3", "#2196F3");
				_setImageViewRipple(home, "#838383", "#2196F3");
				_setImageViewRipple(favorite, "#838383", "#2196F3");
				_setImageViewRipple(played, "#838383", "#2196F3");
				_setMenu(3);
			}
		});
		
		add_song.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_setImageViewRipple(add_song, "#838383", "#2196F3");
				intent.setClass(getApplicationContext(), UploadActivity.class);
				startActivity(intent);
			}
		});
		
		settings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_setImageViewRipple(settings, "#838383", "#2196F3");
				intent_st.setClass(getApplicationContext(), SettingActivity.class);
				startActivity(intent_st);
			}
		});
		
		image_shuffle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (data.getString("shuffle", "").equals("false")) {
					data.edit().putString("shuffle", "true").commit();
					image_shuffle.setImageResource(R.drawable.shuffle_red);
				}
				else {
					data.edit().putString("shuffle", "false").commit();
					if (data.getString("mode", "").equals("light")) {
						image_shuffle.setImageResource(R.drawable.shuffle_black);
					}
					else {
						image_shuffle.setImageResource(R.drawable.shuffle_white);
					}
				}
			}
		});
		
		image_nightcore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
					if (data.getString("nightcore", "").equals("1")) {
						data.edit().putString("nightcore", "0").commit();
						image_nightcore.setAlpha((float)(0.5d));
						PlaybackParams params = new PlaybackParams();
						params.setPitch(1.0f);
						params.setSpeed(1.0f);
						if (mp.isPlaying()) {
							mp.setPlaybackParams(params);
							mp.start();
						}
						else {
							mp.setPlaybackParams(params);
							mp.pause();
						}
					}
					else {
						data.edit().putString("nightcore", "1").commit();
						image_nightcore.setAlpha((float)(1.0d));
						PlaybackParams params = new PlaybackParams();
						params.setPitch(1.20f);
						params.setSpeed(1.20f);
						if (mp.isPlaying()) {
							mp.setPlaybackParams(params);
							mp.start();
						}
						else {
							mp.setPlaybackParams(params);
							mp.pause();
						}
					}
				}
				else {
					data.edit().putString("nightcore", "0").commit();
					image_nightcore.setAlpha((float)(0.5d));
					_customSnackBar("Android Lollipop or lower doesn't support nightcore feature!", 2);
				}
			}
		});
		
		_upload_text_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				linear_data.setVisibility(View.GONE);
				listview1.setVisibility(View.VISIBLE);
				oldChildKey.add(_childKey);
				oldListmap.add(_childValue);
				if (isSearching) {
					if (_childValue.get("name").toString().toLowerCase().contains(edittext_search.getText().toString().toLowerCase().trim()) || (_childValue.get("uid").toString().toLowerCase().contains(edittext_search.getText().toString().toLowerCase().trim()) || edittext_search.getText().toString().toLowerCase().trim().equals(""))) {
						childkey.add((int)(0), _childKey);
						upload_list.add((int)0, _childValue);
						int _index = listview1.getFirstVisiblePosition();
						View _view = listview1.getChildAt(0);
						int _top = (_view == null) ? 0 : _view.getTop();
						listview1.setAdapter(new Listview1Adapter(upload_list));
						listview1.setSelectionFromTop(_index, _top);
					}
				}
				else {
					if (tabsPos == 0) {
						childkey.add((int)(0), _childKey);
						upload_list.add((int)0, _childValue);
						int _index = listview1.getFirstVisiblePosition();
						View _view = listview1.getChildAt(0);
						int _top = (_view == null) ? 0 : _view.getTop();
						listview1.setAdapter(new Listview1Adapter(upload_list));
						listview1.setSelectionFromTop(_index, _top);
					}
					if (tabsPos == 2) {
						double _tmpNum = 0;
						boolean _tmp_bool = false;
						for(int _repeat31 = 0; _repeat31 < (int)(upload_list.size()); _repeat31++) {
							if ((Double.parseDouble(_childValue.get("view").toString()) > Double.parseDouble(upload_list.get((int)_tmpNum).get("view").toString())) || (Double.parseDouble(_childValue.get("view").toString()) == Double.parseDouble(upload_list.get((int)_tmpNum).get("view").toString()))) {
								if (!_tmp_bool) {
									childkey.add((int)(_tmpNum), _childKey);
									upload_list.add((int)_tmpNum, _childValue);
									int _index = listview1.getFirstVisiblePosition();
									View _view = listview1.getChildAt(0);
									int _top = (_view == null) ? 0 : _view.getTop();
									listview1.setAdapter(new Listview1Adapter(upload_list));
									listview1.setSelectionFromTop(_index, _top);
									_tmp_bool = true;
								}
							}
							_tmpNum++;
						}
						if (!_tmp_bool) {
							childkey.add(_childKey);
							upload_list.add(_childValue);
							int _index = listview1.getFirstVisiblePosition();
							View _view = listview1.getChildAt(0);
							int _top = (_view == null) ? 0 : _view.getTop();
							listview1.setAdapter(new Listview1Adapter(upload_list));
							listview1.setSelectionFromTop(_index, _top);
						}
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				upload_text.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						oldListmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								oldListmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						if (childkey.contains(_childKey)) {
							upload_list.get((int)childkey.indexOf(_childKey)).put("url", _childValue.get("url").toString());
							upload_list.get((int)childkey.indexOf(_childKey)).put("name", _childValue.get("name").toString());
							upload_list.get((int)childkey.indexOf(_childKey)).put("view", _childValue.get("view").toString());
							upload_list.get((int)childkey.indexOf(_childKey)).put("uid", _childValue.get("uid").toString());
							if (_childValue.containsKey("img")) {
								upload_list.get((int)childkey.indexOf(_childKey)).put("img", _childValue.get("img").toString());
							}
							int _index = listview1.getFirstVisiblePosition();
							View _view = listview1.getChildAt(0);
							int _top = (_view == null) ? 0 : _view.getTop();
							listview1.setAdapter(new Listview1Adapter(upload_list));
							listview1.setSelectionFromTop(_index, _top);
						}
						if (currentlyChild.contains(_childKey)) {
							currentlyMap.get((int)currentlyChild.indexOf(_childKey)).put("url", _childValue.get("url").toString());
							currentlyMap.get((int)currentlyChild.indexOf(_childKey)).put("name", _childValue.get("name").toString());
							currentlyMap.get((int)currentlyChild.indexOf(_childKey)).put("view", _childValue.get("view").toString());
							currentlyMap.get((int)currentlyChild.indexOf(_childKey)).put("uid", _childValue.get("uid").toString());
							if (_childValue.containsKey("img")) {
								currentlyMap.get((int)currentlyChild.indexOf(_childKey)).put("img", _childValue.get("img").toString());
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
				oldListmap.remove((int)(oldChildKey.indexOf(_childKey)));
				oldChildKey.remove((int)(oldChildKey.indexOf(_childKey)));
				if (childkey.contains(_childKey)) {
					upload_list.remove((int)(childkey.indexOf(_childKey)));
					childkey.remove((int)(childkey.indexOf(_childKey)));
				}
				if (mp != null && currentlyChild != null) {
					if (currentlyChild.contains(_childKey)) {
						currentlyMap.remove((int)(currentlyChild.indexOf(_childKey)));
						currentlyChild.remove((int)(currentlyChild.indexOf(_childKey)));
					}
				}
				int _index = listview1.getFirstVisiblePosition();
				View _view = listview1.getChildAt(0);
				int _top = (_view == null) ? 0 : _view.getTop();
				listview1.setAdapter(new Listview1Adapter(upload_list));
				listview1.setSelectionFromTop(_index, _top);
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		upload_text.addChildEventListener(_upload_text_child_listener);
		
		_fb_likes_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				likeChild.add(_childKey);
				like_Mgmap.add(_childValue);
				if (likes_map.containsKey(_childValue.get("key").toString())) {
					likes_map.put(_childValue.get("key").toString(), String.valueOf((long)(Double.parseDouble(likes_map.get(_childValue.get("key").toString()).toString()) + 1)));
				}
				else {
					likes_map.put(_childValue.get("key").toString(), "1");
				}
				_refreshLikes();
				if ((tabsPos == 1) && (_childValue.get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid()) && oldChildKey.contains(_childValue.get("key").toString()))) {
					childkey.add((int)(0), _childValue.get("key").toString());
					{
						HashMap<String, Object> _item = new HashMap<>();
						_item.put("url", oldListmap.get((int)oldChildKey.indexOf(_childValue.get("key").toString())).get("url").toString());
						upload_list.add((int)0, _item);
					}
					
					upload_list.get((int)0).put("name", oldListmap.get((int)oldChildKey.indexOf(_childValue.get("key").toString())).get("name").toString());
					upload_list.get((int)0).put("email", oldListmap.get((int)oldChildKey.indexOf(_childValue.get("key").toString())).get("email").toString());
					upload_list.get((int)0).put("view", oldListmap.get((int)oldChildKey.indexOf(_childValue.get("key").toString())).get("view").toString());
					upload_list.get((int)0).put("uid", oldListmap.get((int)oldChildKey.indexOf(_childValue.get("key").toString())).get("uid").toString());
					if (oldListmap.get((int)oldChildKey.indexOf(_childValue.get("key").toString())).containsKey("img")) {
						upload_list.get((int)0).put("img", oldListmap.get((int)oldChildKey.indexOf(_childValue.get("key").toString())).get("img").toString());
					}
				}
				int _index = listview1.getFirstVisiblePosition();
				View _view = listview1.getChildAt(0);
				int _top = (_view == null) ? 0 : _view.getTop();
				listview1.setAdapter(new Listview1Adapter(upload_list));
				listview1.setSelectionFromTop(_index, _top);
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
				like_Mgmap.remove((int)(likeChild.indexOf(_childKey)));
				likeChild.remove((int)(likeChild.indexOf(_childKey)));
				if (Double.parseDouble(likes_map.get(_childValue.get("key").toString()).toString()) > 1) {
					likes_map.put(_childValue.get("key").toString(), String.valueOf((long)(Double.parseDouble(likes_map.get(_childValue.get("key").toString()).toString()) - 1)));
				}
				else {
					likes_map.remove(_childValue.get("key").toString());
				}
				_refreshLikes();
				if ((tabsPos == 1) && childkey.contains(_childValue.get("key").toString())) {
					if (currentlyPlaying.equals(_childKey)) {
						currentlyPlaying = "";
					}
					upload_list.remove((int)(childkey.indexOf(_childKey)));
					childkey.remove((int)(childkey.indexOf(_childKey)));
				}
				int _index = listview1.getFirstVisiblePosition();
				View _view = listview1.getChildAt(0);
				int _top = (_view == null) ? 0 : _view.getTop();
				listview1.setAdapter(new Listview1Adapter(upload_list));
				listview1.setSelectionFromTop(_index, _top);
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		fb_likes.addChildEventListener(_fb_likes_child_listener);
		
		_profile_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				username_list.add(_childKey);
				profile_map.add(_childValue);
				int _index = listview1.getFirstVisiblePosition();
				View _view = listview1.getChildAt(0);
				int _top = (_view == null) ? 0 : _view.getTop();
				listview1.setAdapter(new Listview1Adapter(upload_list));
				listview1.setSelectionFromTop(_index, _top);
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
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
						int _index = listview1.getFirstVisiblePosition();
						View _view = listview1.getChildAt(0);
						int _top = (_view == null) ? 0 : _view.getTop();
						listview1.setAdapter(new Listview1Adapter(upload_list));
						listview1.setSelectionFromTop(_index, _top);
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
		
		_user_pic_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					_curcle_igm_url(_childValue.get("avatar").toString(), profile_image);
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					_curcle_igm_url(_childValue.get("avatar").toString(), profile_image);
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
		
		_comments_db_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				commentsChildKey.add(_childKey);
				commentMgMap.add(_childValue);
				if (commentsMap.containsKey(_childValue.get("key").toString())) {
					commentsMap.put(_childValue.get("key").toString(), String.valueOf((long)(Double.parseDouble(commentsMap.get(_childValue.get("key").toString()).toString()) + 1)));
				}
				else {
					commentsMap.put(_childValue.get("key").toString(), "1");
				}
				int _index = listview1.getFirstVisiblePosition();
				View _view = listview1.getChildAt(0);
				int _top = (_view == null) ? 0 : _view.getTop();
				listview1.setAdapter(new Listview1Adapter(upload_list));
				listview1.setSelectionFromTop(_index, _top);
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
				commentMgMap.remove((int)(commentsChildKey.indexOf(_childKey)));
				commentsChildKey.remove((int)(commentsChildKey.indexOf(_childKey)));
				if (Double.parseDouble(commentsMap.get(_childValue.get("key").toString()).toString()) > 1) {
					commentsMap.put(_childValue.get("key").toString(), String.valueOf((long)(Double.parseDouble(commentsMap.get(_childValue.get("key").toString()).toString()) - 1)));
				}
				else {
					commentsMap.remove(_childValue.get("key").toString());
				}
				int _index = listview1.getFirstVisiblePosition();
				View _view = listview1.getChildAt(0);
				int _top = (_view == null) ? 0 : _view.getTop();
				listview1.setAdapter(new Listview1Adapter(upload_list));
				listview1.setSelectionFromTop(_index, _top);
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		comments_db.addChildEventListener(_comments_db_child_listener);
		
		_song_upload_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_song_upload_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_song_upload_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_song_upload_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_song_upload_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_song_upload_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_image_upload_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_image_upload_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_image_upload_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_image_upload_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_image_upload_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_image_upload_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_report_song_child_listener = new ChildEventListener() {
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
		report_song.addChildEventListener(_report_song_child_listener);
		
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
		
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		_StartApp();
		playlistMap = new Gson().fromJson(data.getString("playlist", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		for(int _repeat156 = 0; _repeat156 < (int)(playlistMap.size()); _repeat156++) {
			playlistString.add(playlistMap.get((int)playlistString.size()).get("name").toString());
		}
		playlistString.add("Add a new playlist");
		Listview2Adapter = new ArrayAdapter<String>(SteamerActivity.this, R.layout.playlist_clickable, R.id.text_playlist, playlistString) {
			    @Override
			    public View getView(int _position, View _convertView, ViewGroup _parent) {
				        View _view = super.getView(_position, _convertView, _parent);
				((TextView)_view.findViewById(R.id.text_playlist)).setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
				if (_position == playlistString.size() - 1) {
					((ImageView)_view.findViewById(R.id.image_playlist)).setImageResource(R.drawable.add_song_grey);
				}
				else {
					((ImageView)_view.findViewById(R.id.image_playlist)).setImageResource(R.drawable.music_note_grey);
				}
				((LinearLayout)_view.findViewById(R.id.linear_playlist)).setBackground(Drawables.getSelectableDrawableFor(Color.parseColor("#91D0F3")));
				((LinearLayout)_view.findViewById(R.id.linear_playlist)).setClickable(true);
				((ImageView)_view.findViewById(R.id.image_playlist)).setColorFilter(Color.parseColor("#838383"), PorterDuff.Mode.MULTIPLY);
				final double _pos = (double)_position;
				((LinearLayout)_view.findViewById(R.id.linear_playlist)).setOnClickListener(new View.OnClickListener() {
					@Override public void onClick(View _view) {
						_addGrid(_pos);
					}
				});
				((LinearLayout)_view.findViewById(R.id.linear_playlist)).setOnLongClickListener(new View.OnLongClickListener() {
					@Override public boolean onLongClick(View _view) {
						_delGrid(_pos);
						return true;
					}
				});
				return _view;
				    };
		};
		listview2 = new GridView(SteamerActivity.this);
				
				listview2.setLayoutParams(new GridView.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.WRAP_CONTENT));
				
				listview2.setNumColumns(GridView.AUTO_FIT);
				
				listview2.setVerticalSpacing(2);
				
				listview2.setHorizontalSpacing(2);
		
		     listview2.setColumnWidth((int)SketchwareUtil.getDip(getApplicationContext(), (int)118));
				
				listview2.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
				
				linear_holder.removeView(listview1);
				
				linear_holder.addView(listview2);
		     
				linear_holder.addView(listview1);
		
		     listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						  @Override
						  public void onItemClick(AdapterView parent, View view, int _pos, long id) {
						}
				});
		
		listview2.setAdapter(Listview2Adapter);
		if (tabsPos == 0) {
			tabsPos = 0;
		}
		audioListener = new AudioManager.OnAudioFocusChangeListener() {
			    @Override
			    public void onAudioFocusChange(int focusState) {
				switch (focusState) {
					case AudioManager.AUDIOFOCUS_GAIN:
					if (mp != null) {
						if (!mp.isPlaying()) {
							mp.start();
						}
						mp.setVolume(1.0f, 1.0f);
					}
					break;
					case AudioManager.AUDIOFOCUS_LOSS:
					if (mp != null) {
						if (mp.isPlaying()) {
							mp.pause();
						}
					}
					break;
					case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
					if (mp != null) {
						if (mp.isPlaying()) {
							mp.pause();
						}
					}
					break;
					
					case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
					if (mp != null) {
						mp.setVolume(0.1f, 0.1f);
					}
					break;
					
				}
				    }
		};
		song_title.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		song_title.setMarqueeRepeatLimit(-1);
		song_title.setSingleLine(true);
		song_title.setSelected(true);
		mp = null;
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.TRANSPARENT});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , null, null);
		seekbar1.setBackground(ripdrb);
		mHandler = new Handler();
		SteamerActivity.this.runOnUiThread(new Runnable() {
			@Override public void run() {
				try {
					if (mp != null) {
						if (mp.isPlaying()) {
							seekbar1.setProgress((int)mp.getCurrentPosition() / 1000);
							image_play.setImageResource(R.drawable.pause_white);
						}
						else {
							image_play.setImageResource(R.drawable.play_white);
						}
					}
				}
				catch (Exception _error) {
				}
				mHandler.postDelayed(this, 1);
			}
		});
		listview1.setVisibility(View.GONE);
		listview2.setVisibility(View.GONE);
		if (data.getString("nightcore", "").equals("1")) {
			image_nightcore.setAlpha((float)(1.0d));
		}
		else {
			image_nightcore.setAlpha((float)(0.5d));
		}
		if (data.getString("repeat", "").equals("0")) {
			if (data.getString("mode", "").equals("light")) {
				image_repeat.setImageResource(R.drawable.repeat_black);
			}
			else {
				image_shuffle.setImageResource(R.drawable.repeat_white);
			}
		}
		else {
			if (data.getString("repeat", "").equals("1")) {
				if (data.getString("mode", "").equals("light")) {
					image_shuffle.setImageResource(R.drawable.repeat_red);
				}
				else {
					image_shuffle.setImageResource(R.drawable.repeat_red);
				}
			}
			else {
				if (data.getString("repeat", "").equals("2")) {
					if (data.getString("mode", "").equals("light")) {
						image_shuffle.setImageResource(R.drawable.repeat_one_red);
					}
					else {
						image_shuffle.setImageResource(R.drawable.repeat_one_red);
					}
				}
			}
		}
		if (data.getString("shuffle", "").equals("true")) {
			image_shuffle.setImageResource(R.drawable.shuffle_red);
		}
		else {
			if (data.getString("shuffle", "").equals("false")) {
				if (data.getString("mode", "").equals("light")) {
					image_shuffle.setImageResource(R.drawable.shuffle_black);
				}
				else {
					image_shuffle.setImageResource(R.drawable.shuffle_white);
				}
			}
		}
		if (data.getString("mode", "").equals("light")) {
			_UI_DarkMode(false, data.getString("accentColor", ""));
			isDarkMode = false;
		}
		else {
			_UI_DarkMode(true, data.getString("accentColor", ""));
			isDarkMode = true;
		}
		mask.setTranslationX((float)((0 - SketchwareUtil.getDisplayWidthPixels(getApplicationContext())) - SketchwareUtil.getDip(getApplicationContext(), (int)(61))));
		_Animator(mask, "alpha", 0.0d, 200);
		left_bar.setTranslationZ(5);
		_AVLd("LineScale");
		_setImageViewRipple(home, "#2196F3", "#2196F3");
		_setImageViewRipple(favorite, "#838383", "#2196F3");
		_setImageViewRipple(played, "#838383", "#2196F3");
		_setImageViewRipple(playlist, "#838383", "#2196F3");
		AudienceNetworkAds.initialize(this);
		banner = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						_FB_AdView_Banner();
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(banner, (int)(100), (int)(60000));
		ad = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						_AdView_Interstitial();
					}
				});
			}
		};
		_timer.schedule(ad, (int)(1000));
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
		if (isLeftBar) {
			isLeftBar = false;
			_OpenCloseLeftBar(isLeftBar);
		}
		else {
			if (isSheeting) {
				_CheetAnim(bottom_sheet, 320, 56, 300);
				isSheeting = false;
			}
			else {
				if (isPlaylist) {
					isPlaylist = false;
					childkey.clear();
					upload_list.clear();
					listview1.setAdapter(new Listview1Adapter(upload_list));
					_TransitionManager(fragment1, 300);
					listview1.setVisibility(View.GONE);
					listview2.setVisibility(View.VISIBLE);
					text_playlist.setVisibility(View.GONE);
					music.setVisibility(View.VISIBLE);
					garage.setVisibility(View.VISIBLE);
					image_search.setVisibility(View.VISIBLE);
					image_menu.setImageResource(R.drawable.menu_black);
				}
				else {
					if (isSearching) {
						isSearching = false;
						_TransitionManager(fragment1, 300);
						edittext_search.setVisibility(View.GONE);
						edittext_search.setText("");
						music.setVisibility(View.VISIBLE);
						garage.setVisibility(View.VISIBLE);
						image_search.setVisibility(View.VISIBLE);
						image_menu.setImageResource(R.drawable.menu_black);
						_setMenu(tabsPos);
					}
					else {
						if (mp != null) {
							moveTaskToBack(true);
						}
						else {
							finish();
						}
					}
				}
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
		if (data.getString("mode", "").equals("light")) {
			_UI_DarkMode(false, data.getString("accentColor", ""));
			isDarkMode = false;
		}
		else {
			_UI_DarkMode(true, data.getString("accentColor", ""));
			isDarkMode = true;
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		
	}
	
	@Override
	public void onStop() {
		super.onStop();
		
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mp != null) {
			if (mp.isPlaying()) {
				mp.stop();
			}
		}
	}
	public void _UI_DarkMode (final boolean _darkMode, final String _accent) {
		if (_darkMode) {
			
		}
		else {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); }
			color = "#FFFFFF";
			colorPrimary = "#EEEEEE";
			if (isLeftBar) {
				getWindow().setStatusBarColor(Color.parseColor("#cccccc"));
				
				getWindow().setNavigationBarColor(Color.parseColor("#cccccc"));
			}
			else {
				getWindow().setStatusBarColor(Color.parseColor(color));
				
				getWindow().setNavigationBarColor(Color.parseColor("#ffffff"));
			}
			_MusicGarageUI(action_bar, 20, 5, "#FFFFFF", "#FFFFFF", 0, false);
			_SX_CornerRadius_4(bottom_sheet, color, color, 0, 50, 50, 0, 0);
			_SX_CornerRadius_4(left_bar, color, color, 0, 0, 30, 30, 0);
			_GradientDrawable(sheeting_icon, 50, 0, 0, color, color, true, true, 100);
			_addCardView(cardview, 0, 15, 0, 0, true, _accent);
			_GradientDrawable(image_play, 200, 0, 0, _accent, _accent, false, true, 100);
			_GradientDrawable(image_fav, 50, 0, 0, color, color, true, true, 100);
			_GradientDrawable(image_download, 50, 0, 0, color, color, true, true, 100);
			_GradientDrawable(image_focus, 50, 0, 0, color, color, true, true, 100);
			_GradientDrawable(image_next, 50, 0, 0, color, color, true, true, 100);
			_GradientDrawable(image_prev, 50, 0, 0, color, color, true, true, 100);
			_GradientDrawable(image_repeat, 50, 0, 0, color, color, true, true, 100);
			_GradientDrawable(image_shuffle, 50, 0, 0, color, color, true, true, 100);
			_GradientDrawable(image_nightcore, 50, 0, 0, color, color, true, true, 100);
			_GradientDrawable(cancel, 50, 0, 0, color, color, true, true, 100);
			_GradientDrawable(home, 50, 0, 0, color, color, true, true, 100);
			_GradientDrawable(favorite, 50, 0, 0, color, color, true, true, 100);
			_GradientDrawable(played, 50, 0, 0, color, color, true, true, 100);
			_GradientDrawable(playlist, 50, 0, 0, color, color, true, true, 100);
			_GradientDrawable(settings, 50, 0, 0, color, color, true, true, 100);
			_GradientDrawable(profile_image, 50, 0, 0, color, color, true, true, 100);
			_GradientDrawable(add_song, 50, 0, 0, color, color, true, true, 100);
		}
		music.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensanslight.ttf"), 0);
		garage.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 1);
		text_playlist.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		edittext_search.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_data.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		song_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_artist.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_dot.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_album.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_current.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_duration.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
	}
	
	
	public void _MusicGarageUI (final View _view, final double _radius, final double _shadow, final String _color1, final String _color2, final double _border, final boolean _ripple) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color1));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int) _border, Color.parseColor(_color2));
			_view.setBackground(gd);
			_view.setElevation((int)_shadow);
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#91D0F3")});
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
	
	
	public void _Animator (final View _view, final String _propertyName, final double _value, final double _duration) {
		ObjectAnimator anim = new ObjectAnimator();
		anim.setTarget(_view);
		anim.setPropertyName(_propertyName);
		anim.setFloatValues((float)_value);
		anim.setDuration((long)_duration);
		anim.start();
	}
	
	
	public void _CheetAnim (final View _view, final double _size, final double _childSize, final double _duration) {
		if (isSheeting) {
			_Animator(sheeting_icon, "rotation", 0, 300);
			_Animator(_view, "translationY", SketchwareUtil.getDip(getApplicationContext(), (int)(10)), _duration);
			timer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_Animator(_view, "translationY", 0, _duration);
						}
					});
				}
			};
			_timer.schedule(timer, (int)(_duration));
		}
		else {
			_Animator(sheeting_icon, "rotation", 180, 300);
			_Animator(_view, "translationY", SketchwareUtil.getDip(getApplicationContext(), (int)(_childSize - (_size + 10))), _duration);
			timer = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_Animator(_view, "translationY", SketchwareUtil.getDip(getApplicationContext(), (int)(_childSize - _size)), _duration);
						}
					});
				}
			};
			_timer.schedule(timer, (int)(_duration));
		}
	}
	
	
	public void _LayoutParams (final View _view, final double _width, final double _height) {
		_view.setLayoutParams(new LinearLayout.LayoutParams((int)_width,(int) _height));
	}
	
	
	public void _SX_CornerRadius_4 (final View _view, final String _color1, final String _color2, final double _str, final double _n1, final double _n2, final double _n3, final double _n4) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		
		gd.setColor(Color.parseColor(_color1));
		
		gd.setStroke((int)_str, Color.parseColor(_color2));
		
		gd.setCornerRadii(new float[]{(int)_n1,(int)_n1,(int)_n2,(int)_n2,(int)_n3,(int)_n3,(int)_n4,(int)_n4});
		
		_view.setBackground(gd);
		
		_view.setElevation(8);
	}
	
	
	public void _GradientDrawable (final View _view, final double _radius, final double _stroke, final double _shadow, final String _color, final String _borderColor, final boolean _ripple, final boolean _clickAnim, final double _animDuration) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int)_stroke,Color.parseColor(_borderColor));
			if (Build.VERSION.SDK_INT >= 21){
				_view.setElevation((int)_shadow);}
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9e9e9e")});
			android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
			_view.setClickable(true);
			_view.setBackground(ripdrb);
		}
		else {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int)_stroke,Color.parseColor(_borderColor));
			_view.setBackground(gd);
			if (Build.VERSION.SDK_INT >= 21){
				_view.setElevation((int)_shadow);}
		}
		if (_clickAnim) {
			_view.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()){
						case MotionEvent.ACTION_DOWN:{
							ObjectAnimator scaleX = new ObjectAnimator();
							scaleX.setTarget(_view);
							scaleX.setPropertyName("scaleX");
							scaleX.setFloatValues(0.9f);
							scaleX.setDuration((int)_animDuration);
							scaleX.start();
							
							ObjectAnimator scaleY = new ObjectAnimator();
							scaleY.setTarget(_view);
							scaleY.setPropertyName("scaleY");
							scaleY.setFloatValues(0.9f);
							scaleY.setDuration((int)_animDuration);
							scaleY.start();
							break;
						}
						case MotionEvent.ACTION_UP:{
							
							ObjectAnimator scaleX = new ObjectAnimator();
							scaleX.setTarget(_view);
							scaleX.setPropertyName("scaleX");
							scaleX.setFloatValues((float)1);
							scaleX.setDuration((int)_animDuration);
							scaleX.start();
							
							ObjectAnimator scaleY = new ObjectAnimator();
							scaleY.setTarget(_view);
							scaleY.setPropertyName("scaleY");
							scaleY.setFloatValues((float)1);
							scaleY.setDuration((int)_animDuration);
							scaleY.start();
							
							break;
						}
					}
					return false;
				}
			});
		}
	}
	
	
	public void _StartApp () {
		edittext_search.setVisibility(View.GONE);
		text_playlist.setVisibility(View.GONE);
		left_bar.setTranslationX((float)((0 - SketchwareUtil.getDisplayWidthPixels(getApplicationContext())) - SketchwareUtil.getDip(getApplicationContext(), (int)(61))));
		_LayoutParams(mask, SketchwareUtil.getDisplayWidthPixels(getApplicationContext()), SketchwareUtil.getDisplayHeightPixels(getApplicationContext()));
		_LayoutParams(linear_holder, SketchwareUtil.getDisplayWidthPixels(getApplicationContext()), SketchwareUtil.getDisplayHeightPixels(getApplicationContext()));
		bottom_sheet.setTranslationZ(5);
		bottom_sheet.setTranslationZ(5);
		mask.setTranslationX((float)((0 - SketchwareUtil.getDisplayWidthPixels(getApplicationContext())) - SketchwareUtil.getDip(getApplicationContext(), (int)(61))));
		mask.setVisibility(View.GONE);
		mask.setAlpha((float)(0));
		image_nightcore.setVisibility(View.GONE);
	}
	
	
	public void _autoTransitionScroll (final View _scroll) {
		android.transition.TransitionManager.beginDelayedTransition((LinearLayout)_scroll, new android.transition.AutoTransition());
	}
	
	
	public void _navBarColorAnimation (final String _color1, final String _color2, final double _duration) {
		 @android.annotation.SuppressLint("RestrictedApi")
		        android.animation.ValueAnimator colorAnimation = android.animation.ValueAnimator.ofObject(
		                new ArgbEvaluator(), Color.parseColor(_color1), Color.parseColor(_color2));
		        colorAnimation.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(android.animation.ValueAnimator animator) {
				                int color = (int) animator.getAnimatedValue();
				                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					                    getWindow().setNavigationBarColor(color);
					                }
				            }
			
			        });
		        colorAnimation.setDuration((int)_duration);
		        colorAnimation.start();
	}
	
	
	public void _OpenCloseLeftBar (final boolean _open) {
		if (_open) {
			_Animator(left_bar, "translationX", 0 - SketchwareUtil.getDisplayWidthPixels(getApplicationContext()), 200);
			mask.setEnabled(true);
			_Animator(mask, "alpha", 0.2d, 200);
			mask.setVisibility(View.VISIBLE);
			if (isDarkMode) {
				_statusbarColorAnim("#272727", "#1f1f1f", 200);
				_navBarColorAnimation("#212121", "#1a1a1a", 200);
			}
			else {
				_statusbarColorAnim("#ffffff", "#cccccc", 200);
				_navBarColorAnimation("#ffffff", "#cccccc", 200);
			}
		}
		else {
			_Animator(left_bar, "translationX", (0 - SketchwareUtil.getDisplayWidthPixels(getApplicationContext())) - SketchwareUtil.getDip(getApplicationContext(), (int)(61)), 200);
			mask.setEnabled(false);
			_Animator(mask, "alpha", 0, 200);
			time = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							mask.setVisibility(View.GONE);
						}
					});
				}
			};
			_timer.schedule(time, (int)(200));
			if (isDarkMode) {
				_statusbarColorAnim("#1f1f1f", "#272727", 200);
				_navBarColorAnimation("#1a1a1a", "#212121", 200);
			}
			else {
				_statusbarColorAnim("#cccccc", "#ffffff", 200);
				_navBarColorAnimation("#cccccc", "#ffffff", 200);
			}
		}
	}
	
	
	public void _statusbarColorAnim (final String _color1, final String _color2, final double _duration) {
		 @android.annotation.SuppressLint("RestrictedApi")
		        android.animation.ValueAnimator colorAnimation = android.animation.ValueAnimator.ofObject(
		                new ArgbEvaluator(), Color.parseColor(_color1), Color.parseColor(_color2));
		        colorAnimation.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {
			            @Override
			            public void onAnimationUpdate(android.animation.ValueAnimator animator) {
				                int color = (int) animator.getAnimatedValue();
				                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					                    getWindow().setStatusBarColor(color);
					                }
				            }
			
			        });
		        colorAnimation.setDuration((int)_duration);
		        colorAnimation.start();
	}
	
	
	public void _AVLd (final String _key) {
		AVLoadingIndicatorView v = new AVLoadingIndicatorView(this);
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(70, 70);
		v.setLayoutParams(lp);
		linear_data_prog.addView(v);
		
		v.setIndicatorColor(Color.parseColor("#2196F3"));
		
		v.setIndicatorName(_key);
		
		
		v.show();
	}
	
	
	public void _MusicGarageLibrary () {
	}
	
	private double _f;
	private double _t;
	private Handler mHandler;
	private AudioManager audioManager;
	private AudioManager.OnAudioFocusChangeListener audioListener;
	private ArrayAdapter<String> Listview2Adapter;
	private GridView listview2;
	
	private void _requestFocus() {
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		
		int _result = audioManager.requestAudioFocus(audioListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
		
		if (_result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
			mp.start();
		}
	}
	
	private void _removeFocus() {
		audioManager.abandonAudioFocus(audioListener);
	}
	
	
	public static class AVLoadingIndicatorView extends View {
		    private static final String TAG="AVLoadingIndicatorView";
		    private static final BallPulseIndicator DEFAULT_INDICATOR=new BallPulseIndicator();
		    private static final int MIN_SHOW_TIME = 500; // ms
		    private static final int MIN_DELAY = 500; // ms
		    private long mStartTime = -1;
		    private boolean mPostedHide = false;
		    private boolean mPostedShow = false;
		    private boolean mDismissed = false;
		    private final Runnable mDelayedHide = new Runnable() {
			        @Override
			        public void run() {
				            mPostedHide = false;
				            mStartTime = -1;
				            setVisibility(View.GONE);
				        }
			    };
		    private final Runnable mDelayedShow = new Runnable() {
			        @Override
			        public void run() {
				            mPostedShow = false;
				            if (!mDismissed) {
					                mStartTime = System.currentTimeMillis();
					                setVisibility(View.VISIBLE);
					            }
				        }
			    };
		    int mMinWidth;
		    int mMaxWidth;
		    int mMinHeight;
		    int mMaxHeight;
		    private Indicator mIndicator;
		    private int mIndicatorColor;
		    private boolean mShouldStartAnimationDrawable;
		    public AVLoadingIndicatorView(Context context) {
			        super(context);
			        init(context, null,0,0);
			    }
		    public AVLoadingIndicatorView(Context context, AttributeSet attrs) {
			        super(context, attrs);
			        init(context, attrs,0,0);
			    }
		    public AVLoadingIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
			        super(context, attrs, defStyleAttr);
			        init(context, attrs,defStyleAttr,0);
			    }
		    public AVLoadingIndicatorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
			        super(context, attrs, defStyleAttr, defStyleRes);
			        init(context,attrs,defStyleAttr,0);
			    }
		    private void init(Context context,AttributeSet attrs,int defStyleAttr, int defStyleRes) {
			        mMinWidth = 24;
			        mMaxWidth = 48;
			        mMinHeight = 24;
			        mMaxHeight = 48;
			        mMinWidth = mMinWidth;
			        mMaxWidth = mMaxWidth;
			        mMinHeight = mMinHeight;
			        mMaxHeight = mMaxHeight; 
			        String indicatorName="";
			        mIndicatorColor=Color.WHITE;
			        setIndicator(indicatorName);
			        if (mIndicator==null){
				            setIndicator(DEFAULT_INDICATOR);
				        }     
			    }
		    public Indicator getIndicator() {
			        return mIndicator;
			    }
		    public void setIndicator(Indicator d) {
			        if (mIndicator != d) {
				            if (mIndicator != null) {
					                mIndicator.setCallback(null);
					                unscheduleDrawable(mIndicator);
					            }
				            mIndicator = d;
				            setIndicatorColor(mIndicatorColor);
				            if (d != null) {
					                d.setCallback(this);
					            }
				            postInvalidate();
				        }
			    }
		    public void setIndicatorColor(int color){
			        this.mIndicatorColor=color;
			        mIndicator.setColor(color);
			    }
		    public void setIndicatorName(String indi) {
			    	if (indi.contains("TriangleSkew")) {
				    		TriangleSkewSpinIndicator indic = new TriangleSkewSpinIndicator();
				    		setIndicator(indic);
				    } else  if (indi.contains("Square")) {
				    		SquareSpinIndicator indic = new SquareSpinIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("SemiCircle")) {
				    		SemiCircleSpinIndicator indic = new SemiCircleSpinIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("BallPulse")) {
				    		BallPulseSyncIndicator indic = new BallPulseSyncIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("BallPulseRise")) {
				    		BallPulseRiseIndicator indic = new BallPulseRiseIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("BallGridPulse")) {
				    		BallGridPulseIndicator indic = new BallGridPulseIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("BallGridBeat")) {
				    		BallGridBeatIndicator indic = new BallGridBeatIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("BallClipRotatePulse")) {
				    		BallClipRotatePulseIndicator indic = new BallClipRotatePulseIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("BallClipRotateMultiple")) {
				    		BallClipRotateMultipleIndicator indic = new BallClipRotateMultipleIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("BallClipRotate")) {
				    		BallClipRotateIndicator indic = new BallClipRotateIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("BallBeat")) {
				    		BallBeatIndicator indic = new BallBeatIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("BallRotate")) {
				    		BallRotateIndicator indic = new BallRotateIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("BallScale")) {
				    		BallScaleIndicator indic = new BallScaleIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("BallScaleMultiple")) {
				    		BallScaleMultipleIndicator indic = new BallScaleMultipleIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("BallScaleRipple")) {
				    		BallScaleRippleIndicator indic = new BallScaleRippleIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("BallScaleRippleMultiple")) {
				    		BallScaleRippleMultipleIndicator indic = new BallScaleRippleMultipleIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("BallSpinFadeLoader")) {
				    		BallSpinFadeLoaderIndicator indic = new BallSpinFadeLoaderIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("BallTrianglePath")) {
				    		BallTrianglePathIndicator indic = new BallTrianglePathIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("BallZigZagDeflect")) {
				    		BallZigZagDeflectIndicator indic = new BallZigZagDeflectIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("BallZigZag")) {
				    		BallZigZagIndicator indic = new BallZigZagIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("CubeTransition")) {
				    		CubeTransitionIndicator indic = new CubeTransitionIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("LineScale")) {
				    		LineScaleIndicator indic = new LineScaleIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("LineScaleParty")) {
				    		LineScalePartyIndicator indic = new LineScalePartyIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("LineScalePulseOut")) {
				    		LineScalePulseOutIndicator indic = new LineScalePulseOutIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("LineScalePulseOutRapid")) {
				    		LineScalePulseOutRapidIndicator indic = new LineScalePulseOutRapidIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("LineSpinFadeLoader")) {
				    		LineSpinFadeLoaderIndicator indic = new LineSpinFadeLoaderIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("Pacman")) {
				    		PacmanIndicator indic = new PacmanIndicator();
				    		setIndicator(indic);
				    } else if (indi.contains("JavaLibrary")) {
				    		JavaLibrary indic = new JavaLibrary();
				    		setIndicator(indic);
				    }
			    }
		    public void setIndicator(String indicatorName){
			        if (TextUtils.isEmpty(indicatorName)){
				            return;
				        }
			        StringBuilder drawableClassName=new StringBuilder();
			        if (!indicatorName.contains(".")){
				            String defaultPackageName=getClass().getPackage().getName();
				            drawableClassName.append(defaultPackageName)
				                    .append(".indicators")
				                    .append(".");
				        }
			        drawableClassName.append(indicatorName);
			        try {
				            Class<?> drawableClass = Class.forName(drawableClassName.toString());
				            Indicator indicator = (Indicator) drawableClass.newInstance();
				            setIndicator(indicator);
				        } catch (ClassNotFoundException e) {
				            Log.e(TAG,"Didn't find your class , check the name again !");
				        } catch (InstantiationException e) {
				            e.printStackTrace();
				        } catch (IllegalAccessException e) {
				            e.printStackTrace();
				        }
			    }
		    public void smoothToShow(){
			        startAnimation(AnimationUtils.loadAnimation(getContext(),android.R.anim.fade_in));
			        setVisibility(VISIBLE);
			    }
		    public void smoothToHide(){
			        startAnimation(AnimationUtils.loadAnimation(getContext(),android.R.anim.fade_out));
			        setVisibility(GONE);
			    }
		    public void hide() {
			        mDismissed = true;
			        removeCallbacks(mDelayedShow);
			        long diff = System.currentTimeMillis() - mStartTime;
			        if (diff >= MIN_SHOW_TIME || mStartTime == -1) {
				            setVisibility(View.GONE);
				        } else {
				            if (!mPostedHide) {
					                postDelayed(mDelayedHide, MIN_SHOW_TIME - diff);
					                mPostedHide = true;
					            }
				        }
			    }
		    public void show() {
			        mStartTime = -1;
			        mDismissed = false;
			        removeCallbacks(mDelayedHide);
			        if (!mPostedShow) {
				            postDelayed(mDelayedShow, MIN_DELAY);
				            mPostedShow = true;
				        }
			    }
		    @Override
		    protected boolean verifyDrawable(android.graphics.drawable.Drawable who) {
			        return who == mIndicator
			                || super.verifyDrawable(who);
			    }
		    void startAnimation() {
			        if (getVisibility() != VISIBLE) {
				            return;
				        }
			        if (mIndicator instanceof android.graphics.drawable.Animatable) {
				            mShouldStartAnimationDrawable = true;
				        }
			        postInvalidate();
			    }
		    void stopAnimation() {
			        if (mIndicator instanceof android.graphics.drawable.Animatable) {
				            mIndicator.stop();
				            mShouldStartAnimationDrawable = false;
				        }
			        postInvalidate();
			    }
		    @Override
		    public void setVisibility(int v) {
			        if (getVisibility() != v) {
				            super.setVisibility(v);
				            if (v == GONE || v == INVISIBLE) {
					                stopAnimation();
					            } else {
					                startAnimation();
					            }
				        }
			    }
		    @Override
		    protected void onVisibilityChanged(View changedView, int visibility) {
			        super.onVisibilityChanged(changedView, visibility);
			        if (visibility == GONE || visibility == INVISIBLE) {
				            stopAnimation();
				        } else {
				            startAnimation();
				        }
			    }
		    @Override
		    public void invalidateDrawable(android.graphics.drawable.Drawable dr) {
			        if (verifyDrawable(dr)) {
				            final Rect dirty = dr.getBounds();
				            final int scrollX = getScrollX() + getPaddingLeft();
				            final int scrollY = getScrollY() + getPaddingTop();
				            invalidate(dirty.left + scrollX, dirty.top + scrollY,
				                    dirty.right + scrollX, dirty.bottom + scrollY);
				        } else {
				            super.invalidateDrawable(dr);
				        }
			    }
		    @Override
		    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
			        updateDrawableBounds(w, h);
			    }
		    private void updateDrawableBounds(int w, int h) {
			        w -= getPaddingRight() + getPaddingLeft();
			        h -= getPaddingTop() + getPaddingBottom();
			        int right = w;
			        int bottom = h;
			        int top = 0;
			        int left = 0;
			        if (mIndicator != null) {
				            final int intrinsicWidth = mIndicator.getIntrinsicWidth();
				            final int intrinsicHeight = mIndicator.getIntrinsicHeight();
				            final float intrinsicAspect = (float) intrinsicWidth / intrinsicHeight;
				            final float boundAspect = (float) w / h;
				            if (intrinsicAspect != boundAspect) {
					                if (boundAspect > intrinsicAspect) {
						                    final int width = (int) (h * intrinsicAspect);
						                    left = (w - width) / 2;
						                    right = left + width;
						                } else {
						                    final int height = (int) (w * (1 / intrinsicAspect));
						                    top = (h - height) / 2;
						                    bottom = top + height;
						                }
					            }
				            mIndicator.setBounds(left, top, right, bottom);
				        }
			    }
		    @Override
		    protected synchronized void onDraw(Canvas canvas) {
			        super.onDraw(canvas);
			        drawTrack(canvas);
			    }
		    void drawTrack(Canvas canvas) {
			        final android.graphics.drawable.Drawable d = mIndicator;
			        if (d != null) {
				            final int saveCount = canvas.save();
				            canvas.translate(getPaddingLeft(), getPaddingTop());
				            d.draw(canvas);
				            canvas.restoreToCount(saveCount);
				            if (mShouldStartAnimationDrawable && d instanceof android.graphics.drawable.Animatable) {
					                ((android.graphics.drawable.Animatable) d).start();
					                mShouldStartAnimationDrawable = false;
					            }
				        }
			    }
		    @Override
		    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			        int dw = 0;
			        int dh = 0;
			        final android.graphics.drawable.Drawable d = mIndicator;
			        if (d != null) {
				            dw = Math.max(mMinWidth, Math.min(mMaxWidth, d.getIntrinsicWidth()));
				            dh = Math.max(mMinHeight, Math.min(mMaxHeight, d.getIntrinsicHeight()));
				        }
			        updateDrawableState();
			        dw += getPaddingLeft() + getPaddingRight();
			        dh += getPaddingTop() + getPaddingBottom();
			        final int measuredWidth = resolveSizeAndState(dw, widthMeasureSpec, 0);
			        final int measuredHeight = resolveSizeAndState(dh, heightMeasureSpec, 0);
			        setMeasuredDimension(measuredWidth, measuredHeight);
			    }
		    @Override
		    protected void drawableStateChanged() {
			        super.drawableStateChanged();
			        updateDrawableState();
			    }
		    private void updateDrawableState() {
			        final int[] state = getDrawableState();
			        if (mIndicator != null && mIndicator.isStateful()) {
				            mIndicator.setState(state);
				        }
			    }
		    @Override
		    public void drawableHotspotChanged(float x, float y) {
			        super.drawableHotspotChanged(x, y);
			        if (mIndicator != null) {
				            mIndicator.setHotspot(x, y);
				        }
			    }
		    @Override
		    protected void onAttachedToWindow() {
			        super.onAttachedToWindow();
			        startAnimation();
			        removeCallbacks();
			    }
		    @Override
		    protected void onDetachedFromWindow() {
			        stopAnimation();
			        super.onDetachedFromWindow();
			        removeCallbacks();
			    }
		    private void removeCallbacks() {
			        removeCallbacks(mDelayedHide);
			        removeCallbacks(mDelayedShow);
			    }
	}
	
	
	public static class BallPulseIndicator extends Indicator {
		    public static final float SCALE=1.0f;
		    private float[] scaleFloats=new float[]{SCALE,
			            SCALE,
			            SCALE};
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        float circleSpacing=4;
			        float radius=(Math.min(getWidth(),getHeight())-circleSpacing*2)/6;
			        float x = getWidth()/ 2-(radius*2+circleSpacing);
			        float y=getHeight() / 2;
			        for (int i = 0; i < 3; i++) {
				            canvas.save();
				            float translateX=x+(radius*2)*i+circleSpacing*i;
				            canvas.translate(translateX, y);
				            canvas.scale(scaleFloats[i], scaleFloats[i]);
				            canvas.drawCircle(0, 0, radius, paint);
				            canvas.restore();
				        }
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        int[] delays=new int[]{120,240,360};
			        for (int i = 0; i < 3; i++) {
				            final int index=i;
				            ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.3f,1);
				            scaleAnim.setDuration(750);
				            scaleAnim.setRepeatCount(-1);
				            scaleAnim.setStartDelay(delays[i]);
				            addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    scaleFloats[index] = (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            animators.add(scaleAnim);
				        }
			        return animators;
			    }
	}
	
	
	public static abstract class Indicator extends android.graphics.drawable.Drawable implements android.graphics.drawable.Animatable {
		    private HashMap<ValueAnimator,ValueAnimator.AnimatorUpdateListener> mUpdateListeners=new HashMap<>();
		    private ArrayList<ValueAnimator> mAnimators;
		    private int alpha = 255;
		    private static final Rect ZERO_BOUNDS_RECT = new Rect();
		    protected Rect drawBounds = ZERO_BOUNDS_RECT;
		    private boolean mHasAnimators;
		    private Paint mPaint=new Paint();
		    public Indicator(){
			        mPaint.setColor(Color.WHITE);
			        mPaint.setStyle(Paint.Style.FILL);
			        mPaint.setAntiAlias(true);
			    }
		    public int getColor() {
			        return mPaint.getColor();
			    }
		    public void setColor(int color) {
			        mPaint.setColor(color);
			    }
		    @Override
		    public void setAlpha(int alpha) {
			        this.alpha = alpha;
			    }
		    @Override
		    public int getAlpha() {
			        return alpha;
			    }
		    @Override
		    public int getOpacity() {
			        return PixelFormat.OPAQUE;
			    }
		    @Override
		    public void setColorFilter(ColorFilter colorFilter) {}
		    @Override
		    public void draw(Canvas canvas) {
			        draw(canvas,mPaint);
			    }
		    public abstract void draw(Canvas canvas, Paint paint);
		    public abstract ArrayList<ValueAnimator> onCreateAnimators();
		    @Override
		    public void start() {
			        ensureAnimators();
			        if (mAnimators == null) {
				            return;
				        }
			        if (isStarted()) {
				            return;
				        }
			        startAnimators();
			        invalidateSelf();
			    }
		    private void startAnimators() {
			        for (int i = 0; i < mAnimators.size(); i++) {
				            ValueAnimator animator = mAnimators.get(i);
				            ValueAnimator.AnimatorUpdateListener updateListener=mUpdateListeners.get(animator);
				            if (updateListener!=null){
					                animator.addUpdateListener(updateListener);
					            }
				            animator.start();
				        }
			    }
		    private void stopAnimators() {
			        if (mAnimators!=null){
				            for (ValueAnimator animator : mAnimators) {
					                if (animator != null && animator.isStarted()) {
						                    animator.removeAllUpdateListeners();
						                    animator.end();
						                }
					            }
				        }
			    }
		    private void ensureAnimators() {
			        if (!mHasAnimators) {
				            mAnimators = onCreateAnimators();
				            mHasAnimators = true;
				        }
			    }
		    @Override
		    public void stop() {
			        stopAnimators();
			    }
		    private boolean isStarted() {
			        for (ValueAnimator animator : mAnimators) {
				            return animator.isStarted();
				        }
			        return false;
			    }
		    @Override
		    public boolean isRunning() {
			        for (ValueAnimator animator : mAnimators) {
				            return animator.isRunning();
				        }
			        return false;
			    }
		    public void addUpdateListener(ValueAnimator animator, ValueAnimator.AnimatorUpdateListener updateListener){
			        mUpdateListeners.put(animator,updateListener);
			    }
		    @Override
		    protected void onBoundsChange(Rect bounds) {
			        super.onBoundsChange(bounds);
			        setDrawBounds(bounds);
			    }
		    public void setDrawBounds(Rect drawBounds) {
			        setDrawBounds(drawBounds.left, drawBounds.top, drawBounds.right, drawBounds.bottom);
			    }
		    public void setDrawBounds(int left, int top, int right, int bottom) {
			        this.drawBounds = new Rect(left, top, right, bottom);
			    }
		    public void postInvalidate(){
			        invalidateSelf();
			    }
		    public Rect getDrawBounds() {
			        return drawBounds;
			    }
		    public int getWidth(){
			        return drawBounds.width();
			    }
		    public int getHeight(){
			        return drawBounds.height();
			    }
		    public int centerX(){
			        return drawBounds.centerX();
			    }
		    public int centerY(){
			        return drawBounds.centerY();
			    }
		    public float exactCenterX(){
			        return drawBounds.exactCenterX();
			    }
		    public float exactCenterY(){
			        return drawBounds.exactCenterY();
			    }
	}
	
	
	public static class LineScalePulseOutRapidIndicator extends LineScaleIndicator {
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        long[] delays=new long[]{400,200,0,200,400};
			        for (int i = 0; i < 5; i++) {
				            final int index=i;
				            ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.4f,1);
				            scaleAnim.setDuration(1000);
				            scaleAnim.setRepeatCount(-1);
				            scaleAnim.setStartDelay(delays[i]);
				            addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    scaleYFloats[index] = (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            animators.add(scaleAnim);
				        }
			        return animators;
			    }
	}
	
	
	public static class PacmanIndicator extends Indicator {
		    private float translateX;
		    private int alpha;
		    private float degrees1,degrees2;
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        drawPacman(canvas,paint);
			        drawCircle(canvas,paint);
			    }
		    private void drawPacman(Canvas canvas,Paint paint){
			        float x=getWidth()/2;
			        float y=getHeight()/2;
			        canvas.save();
			        canvas.translate(x, y);
			        canvas.rotate(degrees1);
			        paint.setAlpha(255);
			        RectF rectF1=new RectF(-x/1.7f,-y/1.7f,x/1.7f,y/1.7f);
			        canvas.drawArc(rectF1, 0, 270, true, paint);
			        canvas.restore();
			        canvas.save();
			        canvas.translate(x, y);
			        canvas.rotate(degrees2);
			        paint.setAlpha(255);
			        RectF rectF2=new RectF(-x/1.7f,-y/1.7f,x/1.7f,y/1.7f);
			        canvas.drawArc(rectF2,90,270,true,paint);
			        canvas.restore();
			    }
		    private void drawCircle(Canvas canvas, Paint paint) {
			        float radius=getWidth()/11;
			        paint.setAlpha(alpha);
			        canvas.drawCircle(translateX, getHeight() / 2, radius, paint);
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        float startT=getWidth()/11;
			        ValueAnimator translationAnim=ValueAnimator.ofFloat(getWidth()-startT,getWidth()/2);
			        translationAnim.setDuration(650);
			        translationAnim.setInterpolator(new android.view.animation.LinearInterpolator());
			        translationAnim.setRepeatCount(-1);
			        addUpdateListener(translationAnim,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                translateX = (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        ValueAnimator alphaAnim=ValueAnimator.ofInt(255,122);
			        alphaAnim.setDuration(650);
			        alphaAnim.setRepeatCount(-1);
			        addUpdateListener(alphaAnim,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                alpha = (int) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        ValueAnimator rotateAnim1=ValueAnimator.ofFloat(0, 45, 0);
			        rotateAnim1.setDuration(650);
			        rotateAnim1.setRepeatCount(-1);
			        addUpdateListener(rotateAnim1,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                degrees1 = (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        ValueAnimator rotateAnim2=ValueAnimator.ofFloat(0,-45,0);
			        rotateAnim2.setDuration(650);
			        rotateAnim2.setRepeatCount(-1);
			        addUpdateListener(rotateAnim2,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                degrees2 = (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        animators.add(translationAnim);
			        animators.add(alphaAnim);
			        animators.add(rotateAnim1);
			        animators.add(rotateAnim2);
			        return animators;
			    }
	}
	
	
	public static class JavaLibrary extends Indicator{
		    public static final float SCALE=1.0f;
		    private float[] scaleFloats=new float[]{SCALE,
			            SCALE,
			            SCALE,
			            SCALE,
			            SCALE};
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        float circleSpacing=4;
			        float radius=(Math.min(getWidth(),getHeight())-circleSpacing*2)/12;
			        float x = getWidth()/ 2-(radius*2+circleSpacing);
			        float y=getHeight() / 2;
			        for (int i = 0; i < 4; i++) {
				            canvas.save();
				            float translateX=x+(radius*2)*i+circleSpacing*i;
				            canvas.translate(translateX, y);
				            canvas.scale(scaleFloats[i], scaleFloats[i]);
				            canvas.drawCircle(0, 0, radius, paint);
				            canvas.restore();
				        }
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        int[] delays=new int[]{120,240,360,480};
			        for (int i = 0; i < 4; i++) {
				            final int index=i;
				            ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.3f,1);
				            scaleAnim.setDuration(750);
				            scaleAnim.setRepeatCount(-1);
				            scaleAnim.setStartDelay(delays[i]);
				            addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    scaleFloats[index] = (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            animators.add(scaleAnim);
				        }
			        return animators;
			    }
	}
	
	
	public static class LineSpinFadeLoaderIndicator extends BallSpinFadeLoaderIndicator {
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        float radius=getWidth()/10;
			        for (int i = 0; i < 8; i++) {
				            canvas.save();
				            Point point=circleAt(getWidth(),getHeight(),getWidth()/2.5f-radius,i*(Math.PI/4));
				            canvas.translate(point.x, point.y);
				            canvas.scale(scaleFloats[i], scaleFloats[i]);
				            canvas.rotate(i*45);
				            paint.setAlpha(alphas[i]);
				            RectF rectF=new RectF(-radius,-radius/1.5f,1.5f*radius,radius/1.5f);
				            canvas.drawRoundRect(rectF,5,5,paint);
				            canvas.restore();
				        }
			    }
	}
	
	
	public static class BallZigZagIndicator extends Indicator {
		    float[] translateX=new float[2],translateY=new float[2];
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        for (int i = 0; i < 2; i++) {
				            canvas.save();
				            canvas.translate(translateX[i], translateY[i]);
				            canvas.drawCircle(0, 0, getWidth() / 10, paint);
				            canvas.restore();
				        }
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        float startX=getWidth()/6;
			        float startY=getWidth()/6;
			        for (int i = 0; i < 2; i++) {
				            final int index=i;
				            ValueAnimator translateXAnim=ValueAnimator.ofFloat(startX,getWidth()-startX,getWidth()/2,startX);
				            if (i==1){
					                translateXAnim=ValueAnimator.ofFloat(getWidth()-startX,startX,getWidth()/2,getWidth()-startX);
					            }
				            ValueAnimator translateYAnim=ValueAnimator.ofFloat(startY,startY,getHeight()/2,startY);
				            if (i==1){
					                translateYAnim=ValueAnimator.ofFloat(getHeight()-startY,getHeight()-startY,getHeight()/2,getHeight()-startY);
					            }
				            translateXAnim.setDuration(1000);
				            translateXAnim.setInterpolator(new android.view.animation.LinearInterpolator());
				            translateXAnim.setRepeatCount(-1);
				            addUpdateListener(translateXAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    translateX[index] = (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            translateYAnim.setDuration(1000);
				            translateYAnim.setInterpolator(new android.view.animation.LinearInterpolator());
				            translateYAnim.setRepeatCount(-1);
				            addUpdateListener(translateYAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    translateY[index] = (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            animators.add(translateXAnim);
				            animators.add(translateYAnim);
				        }
			        return animators;
			    }
	}
	
	
	public static class LineScalePulseOutIndicator extends LineScaleIndicator {
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        long[] delays=new long[]{500,250,0,250,500};
			        for (int i = 0; i < 5; i++) {
				            final int index=i;
				            ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.3f,1);
				            scaleAnim.setDuration(900);
				            scaleAnim.setRepeatCount(-1);
				            scaleAnim.setStartDelay(delays[i]);
				            addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    scaleYFloats[index] = (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            animators.add(scaleAnim);
				        }
			        return animators;
			    }
	}
	
	public static class CubeTransitionIndicator extends Indicator {
		    float[] translateX=new float[2],translateY=new float[2];
		    float degrees,scaleFloat=1.0f;
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        float rWidth=getWidth()/5;
			        float rHeight=getHeight()/5;
			        for (int i = 0; i < 2; i++) {
				            canvas.save();
				            canvas.translate(translateX[i], translateY[i]);
				            canvas.rotate(degrees);
				            canvas.scale(scaleFloat,scaleFloat);
				            RectF rectF=new RectF(-rWidth/2,-rHeight/2,rWidth/2,rHeight/2);
				            canvas.drawRect(rectF,paint);
				            canvas.restore();
				        }
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        float startX=getWidth()/5;
			        float startY=getHeight()/5;
			        for (int i = 0; i < 2; i++) {
				            final int index=i;
				            translateX[index]=startX;
				            ValueAnimator translationXAnim=ValueAnimator.ofFloat(startX,getWidth()-startX,getWidth()-startX, startX,startX);
				            if (i==1){
					                translationXAnim=ValueAnimator.ofFloat(getWidth()-startX,startX,startX, getWidth()-startX,getWidth()-startX);
					            }
				            translationXAnim.setInterpolator(new android.view.animation.LinearInterpolator());
				            translationXAnim.setDuration(1600);
				            translationXAnim.setRepeatCount(-1);
				            translationXAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    translateX[index] = (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            translateY[index]=startY;
				            ValueAnimator translationYAnim=ValueAnimator.ofFloat(startY,startY,getHeight()-startY,getHeight()- startY,startY);
				            if (i==1){
					                translationYAnim=ValueAnimator.ofFloat(getHeight()-startY,getHeight()-startY,startY,startY,getHeight()-startY);
					            }
				            translationYAnim.setDuration(1600);
				            translationYAnim.setInterpolator(new android.view.animation.LinearInterpolator());
				            translationYAnim.setRepeatCount(-1);
				            addUpdateListener(translationYAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    translateY[index] = (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            animators.add(translationXAnim);
				            animators.add(translationYAnim);
				        }
			        ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.5f,1,0.5f,1);
			        scaleAnim.setDuration(1600);
			        scaleAnim.setInterpolator(new android.view.animation.LinearInterpolator());
			        scaleAnim.setRepeatCount(-1);
			        addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                scaleFloat = (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        ValueAnimator rotateAnim=ValueAnimator.ofFloat(0,180,360,1.5f*360,2*360);
			        rotateAnim.setDuration(1600);
			        rotateAnim.setInterpolator(new android.view.animation.LinearInterpolator());
			        rotateAnim.setRepeatCount(-1);
			        addUpdateListener(rotateAnim,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                degrees = (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        animators.add(scaleAnim);
			        animators.add(rotateAnim);
			        return animators;
			    }
	}
	
	
	//TriangleSkewSpinIndicator
	
	public static class TriangleSkewSpinIndicator extends Indicator {
		    private float rotateX;
		    private float rotateY;
		    private Camera mCamera;
		    private Matrix mMatrix;
		    public TriangleSkewSpinIndicator(){
			        mCamera=new Camera();
			        mMatrix=new Matrix();
			    }
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        mMatrix.reset();
			        mCamera.save();
			        mCamera.rotateX(rotateX);
			        mCamera.rotateY(rotateY);
			        mCamera.getMatrix(mMatrix);
			        mCamera.restore();
			        mMatrix.preTranslate(-centerX(), -centerY());
			        mMatrix.postTranslate(centerX(), centerY());
			        canvas.concat(mMatrix);
			        Path path=new Path();
			        path.moveTo(getWidth()/5,getHeight()*4/5);
			        path.lineTo(getWidth()*4/5, getHeight()*4/5);
			        path.lineTo(getWidth()/2,getHeight()/5);
			        path.close();
			        canvas.drawPath(path, paint);
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        ValueAnimator animator=ValueAnimator.ofFloat(0,180,180,0,0);
			        addUpdateListener(animator,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                rotateX= (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        animator.setInterpolator(new android.view.animation.LinearInterpolator());
			        animator.setRepeatCount(-1);
			        animator.setDuration(2500);
			        ValueAnimator animator1=ValueAnimator.ofFloat(0,0,180,180,0);
			        addUpdateListener(animator1,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                rotateY= (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        animator1.setInterpolator(new android.view.animation.LinearInterpolator());
			        animator1.setRepeatCount(-1);
			        animator1.setDuration(2500);
			        animators.add(animator);
			        animators.add(animator1);
			        return animators;
			    }
	}
	
	//SquareSpinIndicator
	
	public static class SquareSpinIndicator extends Indicator {
		    private float rotateX;
		    private float rotateY;
		    private Camera mCamera;
		    private Matrix mMatrix;
		    public SquareSpinIndicator(){
			        mCamera=new Camera();
			        mMatrix=new Matrix();
			    }
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        mMatrix.reset();
			        mCamera.save();
			        mCamera.rotateX(rotateX);
			        mCamera.rotateY(rotateY);
			        mCamera.getMatrix(mMatrix);
			        mCamera.restore();
			        mMatrix.preTranslate(-centerX(), -centerY());
			        mMatrix.postTranslate(centerX(), centerY());
			        canvas.concat(mMatrix);
			        canvas.drawRect(new RectF(getWidth()/5,getHeight()/5,getWidth()*4/5,getHeight()*4/5),paint);
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        ValueAnimator animator=ValueAnimator.ofFloat(0,180,180,0,0);
			        addUpdateListener(animator,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                rotateX= (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        animator.setInterpolator(new android.view.animation.LinearInterpolator());
			        animator.setRepeatCount(-1);
			        animator.setDuration(2500);
			        ValueAnimator animator1=ValueAnimator.ofFloat(0,0,180,180,0);
			        addUpdateListener(animator1,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                rotateY= (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        animator1.setInterpolator(new android.view.animation.LinearInterpolator());
			        animator1.setRepeatCount(-1);
			        animator1.setDuration(2500);
			        animators.add(animator);
			        animators.add(animator1);
			        return animators;
			    }
	}
	
	
	public static class LineScaleIndicator extends Indicator {
		    public static final float SCALE=1.0f;
		    float[] scaleYFloats=new float[]{SCALE,
			            SCALE,
			            SCALE,
			            SCALE,
			            SCALE,};
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        float translateX=getWidth()/11;
			        float translateY=getHeight()/2;
			        for (int i = 0; i < 5; i++) {
				            canvas.save();
				            canvas.translate((2 + i * 2) * translateX - translateX / 2, translateY);
				            canvas.scale(SCALE, scaleYFloats[i]);
				            RectF rectF=new RectF(-translateX/2,-getHeight()/2.5f,translateX/2,getHeight()/2.5f);
				            canvas.drawRoundRect(rectF, 5, 5, paint);
				            canvas.restore();
				        }
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        long[] delays=new long[]{100,200,300,400,500};
			        for (int i = 0; i < 5; i++) {
				            final int index=i;
				            ValueAnimator scaleAnim=ValueAnimator.ofFloat(1, 0.4f, 1);
				            scaleAnim.setDuration(1000);
				            scaleAnim.setRepeatCount(-1);
				            scaleAnim.setStartDelay(delays[i]);
				            addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    scaleYFloats[index] = (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            animators.add(scaleAnim);
				        }
			        return animators;
			    }
	}
	
	//SemiCircleSpinIndicator
	
	public static class SemiCircleSpinIndicator extends Indicator {
		    private float degress;
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        canvas.rotate(degress,centerX(),centerY());
			        RectF rectF=new RectF(0,0,getWidth(),getHeight());
			        canvas.drawArc(rectF,-60,120,false,paint);
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        ValueAnimator rotateAnim=ValueAnimator.ofFloat(0,180,360);
			        addUpdateListener(rotateAnim,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                degress= (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        rotateAnim.setDuration(600);
			        rotateAnim.setRepeatCount(-1);
			        animators.add(rotateAnim);
			        return animators;
			    }
	}
	
	
	public static class LineScalePartyIndicator extends Indicator {
		    public static final float SCALE=1.0f;
		    float[] scaleFloats=new float[]{SCALE,
			            SCALE,
			            SCALE,
			            SCALE,
			            SCALE,};
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        float translateX=getWidth()/9;
			        float translateY=getHeight()/2;
			        for (int i = 0; i < 4; i++) {
				            canvas.save();
				            canvas.translate((2 + i * 2) * translateX - translateX / 2, translateY);
				            canvas.scale(scaleFloats[i], scaleFloats[i]);
				            RectF rectF=new RectF(-translateX/2,-getHeight()/2.5f,translateX/2,getHeight()/2.5f);
				            canvas.drawRoundRect(rectF,5,5,paint);
				            canvas.restore();
				        }
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        long[] durations=new long[]{1260, 430, 1010, 730};
			        long[] delays=new long[]{770, 290, 280, 740};
			        for (int i = 0; i < 4; i++) {
				            final int index=i;
				            ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.4f,1);
				            scaleAnim.setDuration(durations[i]);
				            scaleAnim.setRepeatCount(-1);
				            scaleAnim.setStartDelay(delays[i]);
				            addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    scaleFloats[index] = (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            animators.add(scaleAnim);
				        }
			        return animators;
			    }
	}
	
	
	//BallPulseSyncIndicator
	public static class BallPulseSyncIndicator extends Indicator {
		    float[] translateYFloats=new float[3];
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        float circleSpacing=4;
			        float radius=(getWidth()-circleSpacing*2)/6;
			        float x = getWidth()/ 2-(radius*2+circleSpacing);
			        for (int i = 0; i < 3; i++) {
				            canvas.save();
				            float translateX=x+(radius*2)*i+circleSpacing*i;
				            canvas.translate(translateX, translateYFloats[i]);
				            canvas.drawCircle(0, 0, radius, paint);
				            canvas.restore();
				        }
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        float circleSpacing=4;
			        float radius=(getWidth()-circleSpacing*2)/6;
			        int[] delays=new int[]{70,140,210};
			        for (int i = 0; i < 3; i++) {
				            final int index=i;
				            ValueAnimator scaleAnim=ValueAnimator.ofFloat(getHeight()/2,getHeight()/2-radius*2,getHeight()/2);
				            scaleAnim.setDuration(600);
				            scaleAnim.setRepeatCount(-1);
				            scaleAnim.setStartDelay(delays[i]);
				            addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    translateYFloats[index] = (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            animators.add(scaleAnim);
				        }
			        return animators;
			    }
	}
	//BallPulseRiseIndicator
	public static class BallPulseRiseIndicator extends Indicator {
		    private Camera mCamera;
		    private Matrix mMatrix;
		    private float degress;
		    public BallPulseRiseIndicator(){
			        mCamera=new Camera();
			        mMatrix=new Matrix();
			    }
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        mMatrix.reset();
			        mCamera.save();
			        mCamera.rotateX(degress);
			        mCamera.getMatrix(mMatrix);
			        mCamera.restore();
			        mMatrix.preTranslate(-centerX(), -centerY());
			        mMatrix.postTranslate(centerX(), centerY());
			        canvas.concat(mMatrix);
			        float radius=getWidth()/10;
			        canvas.drawCircle(getWidth()/4,radius*2,radius,paint);
			        canvas.drawCircle(getWidth()*3/4,radius*2,radius,paint);
			        canvas.drawCircle(radius,getHeight()-2*radius,radius,paint);
			        canvas.drawCircle(getWidth()/2,getHeight()-2*radius,radius,paint);
			        canvas.drawCircle(getWidth()-radius,getHeight()-2*radius,radius,paint);
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        ValueAnimator animator=ValueAnimator.ofFloat(0,360);
			        addUpdateListener(animator,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                degress = (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        animator.setInterpolator(new android.view.animation.LinearInterpolator());
			        animator.setRepeatCount(-1);
			        animator.setDuration(1500);
			        animators.add(animator);
			        return animators;
			    }
	}
	
	//BallGridPulseIndicator
	public static class BallGridPulseIndicator extends Indicator {
		    public static final int ALPHA=255;
		    public static final float SCALE=1.0f;
		    int[] alphas=new int[]{ALPHA,
			            ALPHA,
			            ALPHA,
			            ALPHA,
			            ALPHA,
			            ALPHA,
			            ALPHA,
			            ALPHA,
			            ALPHA};
		    float[] scaleFloats=new float[]{SCALE,
			            SCALE,
			            SCALE,
			            SCALE,
			            SCALE,
			            SCALE,
			            SCALE,
			            SCALE,
			            SCALE};
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        float circleSpacing=4;
			        float radius=(getWidth()-circleSpacing*4)/6;
			        float x = getWidth()/ 2-(radius*2+circleSpacing);
			        float y = getWidth()/ 2-(radius*2+circleSpacing);
			        for (int i = 0; i < 3; i++) {
				            for (int j = 0; j < 3; j++) {
					                canvas.save();
					                float translateX=x+(radius*2)*j+circleSpacing*j;
					                float translateY=y+(radius*2)*i+circleSpacing*i;
					                canvas.translate(translateX, translateY);
					                canvas.scale(scaleFloats[3 * i + j], scaleFloats[3 * i + j]);
					                paint.setAlpha(alphas[3 * i + j]);
					                canvas.drawCircle(0, 0, radius, paint);
					                canvas.restore();
					            }
				        }
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        int[] durations={720, 1020, 1280, 1420, 1450, 1180, 870, 1450, 1060};
			        int[] delays= {-60, 250, -170, 480, 310, 30, 460, 780, 450};
			        for (int i = 0; i < 9; i++) {
				            final int index=i;
				            ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.5f,1);
				            scaleAnim.setDuration(durations[i]);
				            scaleAnim.setRepeatCount(-1);
				            scaleAnim.setStartDelay(delays[i]);
				            addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    scaleFloats[index] = (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            ValueAnimator alphaAnim=ValueAnimator.ofInt(255, 210, 122, 255);
				            alphaAnim.setDuration(durations[i]);
				            alphaAnim.setRepeatCount(-1);
				            alphaAnim.setStartDelay(delays[i]);
				            addUpdateListener(alphaAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    alphas[index] = (int) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            animators.add(scaleAnim);
				            animators.add(alphaAnim);
				        }
			        return animators;
			    }
	}
	//BallGridBeatIndicator
	public static class BallGridBeatIndicator extends Indicator {
		    public static final int ALPHA=255;
		    int[] alphas=new int[]{ALPHA,
			            ALPHA,
			            ALPHA,
			            ALPHA,
			            ALPHA,
			            ALPHA,
			            ALPHA,
			            ALPHA,
			            ALPHA};
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        float circleSpacing=4;
			        float radius=(getWidth()-circleSpacing*4)/6;
			        float x = getWidth()/ 2-(radius*2+circleSpacing);
			        float y = getWidth()/ 2-(radius*2+circleSpacing);
			        for (int i = 0; i < 3; i++) {
				            for (int j = 0; j < 3; j++) {
					                canvas.save();
					                float translateX=x+(radius*2)*j+circleSpacing*j;
					                float translateY=y+(radius*2)*i+circleSpacing*i;
					                canvas.translate(translateX, translateY);
					                paint.setAlpha(alphas[3 * i + j]);
					                canvas.drawCircle(0, 0, radius, paint);
					                canvas.restore();
					            }
				        }
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        int[] durations={960, 930, 1190, 1130, 1340, 940, 1200, 820, 1190};
			        int[] delays= {360, 400, 680, 410, 710, -150, -120, 10, 320};
			        for (int i = 0; i < 9; i++) {
				            final int index=i;
				            ValueAnimator alphaAnim=ValueAnimator.ofInt(255, 168,255);
				            alphaAnim.setDuration(durations[i]);
				            alphaAnim.setRepeatCount(-1);
				            alphaAnim.setStartDelay(delays[i]);
				            addUpdateListener(alphaAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    alphas[index] = (int) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            animators.add(alphaAnim);
				        }
			        return animators;
			    }
	}
	//BallClipRotatePulseIndicator
	public static class BallClipRotatePulseIndicator extends Indicator {
		    float scaleFloat1,scaleFloat2,degrees;
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        float circleSpacing=12;
			        float x=getWidth()/2;
			        float y=getHeight()/2;
			        canvas.save();
			        canvas.translate(x, y);
			        canvas.scale(scaleFloat1, scaleFloat1);
			        paint.setStyle(Paint.Style.FILL);
			        canvas.drawCircle(0, 0, x / 2.5f, paint);
			        canvas.restore();
			        canvas.translate(x, y);
			        canvas.scale(scaleFloat2, scaleFloat2);
			        canvas.rotate(degrees);
			        paint.setStrokeWidth(3);
			        paint.setStyle(Paint.Style.STROKE);
			        float[] startAngles=new float[]{225,45};
			        for (int i = 0; i < 2; i++) {
				            RectF rectF=new RectF(-x+circleSpacing,-y+circleSpacing,x-circleSpacing,y-circleSpacing);
				            canvas.drawArc(rectF, startAngles[i], 90, false, paint);
				        }
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.3f,1);
			        scaleAnim.setDuration(1000);
			        scaleAnim.setRepeatCount(-1);
			        addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                scaleFloat1 = (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        ValueAnimator scaleAnim2=ValueAnimator.ofFloat(1,0.6f,1);
			        scaleAnim2.setDuration(1000);
			        scaleAnim2.setRepeatCount(-1);
			        addUpdateListener(scaleAnim2,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                scaleFloat2 = (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        ValueAnimator rotateAnim=ValueAnimator.ofFloat(0, 180,360);
			        rotateAnim.setDuration(1000);
			        rotateAnim.setRepeatCount(-1);
			        addUpdateListener(rotateAnim,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                degrees = (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        animators.add(scaleAnim);
			        animators.add(scaleAnim2);
			        animators.add(rotateAnim);
			        return animators;
			    }
	}
	
	//BallClipRotateMultipleIndicator
	public static class BallClipRotateMultipleIndicator extends Indicator {
		    float scaleFloat=1,degrees;
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        paint.setStrokeWidth(3);
			        paint.setStyle(Paint.Style.STROKE);
			        float circleSpacing=12;
			        float x=getWidth()/2;
			        float y=getHeight()/2;
			        canvas.save();
			        canvas.translate(x, y);
			        canvas.scale(scaleFloat, scaleFloat);
			        canvas.rotate(degrees);
			        float[] bStartAngles=new float[]{135,-45};
			        for (int i = 0; i < 2; i++) {
				            RectF rectF=new RectF(-x+circleSpacing,-y+circleSpacing,x-circleSpacing,y-circleSpacing);
				            canvas.drawArc(rectF, bStartAngles[i], 90, false, paint);
				        }
			        canvas.restore();
			        canvas.translate(x, y);
			        canvas.scale(scaleFloat, scaleFloat);
			        canvas.rotate(-degrees);
			        float[] sStartAngles=new float[]{225,45};
			        for (int i = 0; i < 2; i++) {
				            RectF rectF=new RectF(-x/1.8f+circleSpacing,-y/1.8f+circleSpacing,x/1.8f-circleSpacing,y/1.8f-circleSpacing);
				            canvas.drawArc(rectF, sStartAngles[i], 90, false, paint);
				        }
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.6f,1);
			        scaleAnim.setDuration(1000);
			        scaleAnim.setRepeatCount(-1);
			        addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                scaleFloat = (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        ValueAnimator rotateAnim=ValueAnimator.ofFloat(0, 180,360);
			        rotateAnim.setDuration(1000);
			        rotateAnim.setRepeatCount(-1);
			        addUpdateListener(rotateAnim,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                degrees = (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        animators.add(scaleAnim);
			        animators.add(rotateAnim);
			        return animators;
			    }
	}
	
	//BallClipRotateIndicator
	public static class BallClipRotateIndicator extends Indicator {
		    float scaleFloat=1,degrees;
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        paint.setStyle(Paint.Style.STROKE);
			        paint.setStrokeWidth(3);
			        float circleSpacing=12;
			        float x = (getWidth()) / 2;
			        float y=(getHeight()) / 2;
			        canvas.translate(x, y);
			        canvas.scale(scaleFloat, scaleFloat);
			        canvas.rotate(degrees);
			        RectF rectF=new RectF(-x+circleSpacing,-y+circleSpacing,0+x-circleSpacing,0+y-circleSpacing);
			        canvas.drawArc(rectF, -45, 270, false, paint);
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.6f,0.5f,1);
			        scaleAnim.setDuration(750);
			        scaleAnim.setRepeatCount(-1);
			        addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                scaleFloat = (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        ValueAnimator rotateAnim=ValueAnimator.ofFloat(0,180,360);
			        rotateAnim.setDuration(750);
			        rotateAnim.setRepeatCount(-1);
			        addUpdateListener(rotateAnim,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                degrees = (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        animators.add(scaleAnim);
			        animators.add(rotateAnim);
			        return animators;
			    }
	}
	
	
	// BallBeatIndicator
	
	public static class BallBeatIndicator extends Indicator {
		    public static final float SCALE=1.0f;
		    public static final int ALPHA=255;
		    private float[] scaleFloats=new float[]{SCALE,
			            SCALE,
			            SCALE};
		    int[] alphas=new int[]{ALPHA,
			            ALPHA,
			            ALPHA,};
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        float circleSpacing=4;
			        float radius=(getWidth()-circleSpacing*2)/6;
			        float x = getWidth()/ 2-(radius*2+circleSpacing);
			        float y=getHeight() / 2;
			        for (int i = 0; i < 3; i++) {
				            canvas.save();
				            float translateX=x+(radius*2)*i+circleSpacing*i;
				            canvas.translate(translateX, y);
				            canvas.scale(scaleFloats[i], scaleFloats[i]);
				            paint.setAlpha(alphas[i]);
				            canvas.drawCircle(0, 0, radius, paint);
				            canvas.restore();
				        }
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        int[] delays=new int[]{350,0,350};
			        for (int i = 0; i < 3; i++) {
				            final int index=i;
				            ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.75f,1);
				            scaleAnim.setDuration(700);
				            scaleAnim.setRepeatCount(-1);
				            scaleAnim.setStartDelay(delays[i]);
				            addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    scaleFloats[index] = (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            ValueAnimator alphaAnim=ValueAnimator.ofInt(255,51,255);
				            alphaAnim.setDuration(700);
				            alphaAnim.setRepeatCount(-1);
				            alphaAnim.setStartDelay(delays[i]);
				            addUpdateListener(alphaAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    alphas[index] = (int) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            animators.add(scaleAnim);
				            animators.add(alphaAnim);
				        }
			        return animators;
			    }
	}
	
	public static class BallRotateIndicator extends Indicator {
		    float scaleFloat=0.5f;
		    float degress;
		    private Matrix mMatrix;
		    public BallRotateIndicator(){
			        mMatrix=new Matrix();
			    }
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        float radius=getWidth()/10;
			        float x = getWidth()/ 2;
			        float y=getHeight()/2;
			        canvas.rotate(degress,centerX(),centerY());
			        canvas.save();
			        canvas.translate(x - radius * 2 - radius, y);
			        canvas.scale(scaleFloat, scaleFloat);
			        canvas.drawCircle(0, 0, radius, paint);
			        canvas.restore();
			        canvas.save();
			        canvas.translate(x, y);
			        canvas.scale(scaleFloat, scaleFloat);
			        canvas.drawCircle(0, 0, radius, paint);
			        canvas.restore();
			        canvas.save();
			        canvas.translate(x + radius * 2 + radius, y);
			        canvas.scale(scaleFloat, scaleFloat);
			        canvas.drawCircle(0,0,radius, paint);
			        canvas.restore();
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        ValueAnimator scaleAnim=ValueAnimator.ofFloat(0.5f,1,0.5f);
			        scaleAnim.setDuration(1000);
			        scaleAnim.setRepeatCount(-1);
			        addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                scaleFloat = (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        ValueAnimator rotateAnim=ValueAnimator.ofFloat(0,180,360);
			        addUpdateListener(rotateAnim,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                degress = (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        rotateAnim.setDuration(1000);
			        rotateAnim.setRepeatCount(-1);
			        animators.add(scaleAnim);
			        animators.add(rotateAnim);
			        return animators;
			    }
	}
	
	
	public static class BallScaleIndicator extends Indicator {
		    float scale=1;
		    int alpha=255;
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        float circleSpacing=4;
			        paint.setAlpha(alpha);
			        canvas.scale(scale,scale,getWidth()/2,getHeight()/2);
			        paint.setAlpha(alpha);
			        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2-circleSpacing,paint);
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        ValueAnimator scaleAnim=ValueAnimator.ofFloat(0,1);
			        scaleAnim.setInterpolator(new android.view.animation.LinearInterpolator());
			        scaleAnim.setDuration(1000);
			        scaleAnim.setRepeatCount(-1);
			        addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                scale = (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        ValueAnimator alphaAnim=ValueAnimator.ofInt(255, 0);
			        alphaAnim.setInterpolator(new android.view.animation.LinearInterpolator());
			        alphaAnim.setDuration(1000);
			        alphaAnim.setRepeatCount(-1);
			        addUpdateListener(alphaAnim,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                alpha = (int) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        animators.add(scaleAnim);
			        animators.add(alphaAnim);
			        return animators;
			    }
	}
	
	public static class BallScaleMultipleIndicator extends Indicator {
		    float[] scaleFloats=new float[]{1,1,1};
		    int[] alphaInts=new int[]{255,255,255};
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        float circleSpacing=4;
			        for (int i = 0; i < 3; i++) {
				            paint.setAlpha(alphaInts[i]);
				            canvas.scale(scaleFloats[i],scaleFloats[i],getWidth()/2,getHeight()/2);
				            canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2-circleSpacing,paint);
				        }
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        long[] delays=new long[]{0, 200, 400};
			        for (int i = 0; i < 3; i++) {
				            final int index=i;
				            ValueAnimator scaleAnim=ValueAnimator.ofFloat(0,1);
				            scaleAnim.setInterpolator(new android.view.animation.LinearInterpolator());
				            scaleAnim.setDuration(1000);
				            scaleAnim.setRepeatCount(-1);
				            addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    scaleFloats[index] = (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            scaleAnim.setStartDelay(delays[i]);
				            ValueAnimator alphaAnim=ValueAnimator.ofInt(255,0);
				            alphaAnim.setInterpolator(new android.view.animation.LinearInterpolator());
				            alphaAnim.setDuration(1000);
				            alphaAnim.setRepeatCount(-1);
				            addUpdateListener(alphaAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    alphaInts[index] = (int) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            scaleAnim.setStartDelay(delays[i]);
				            animators.add(scaleAnim);
				            animators.add(alphaAnim);
				        }
			        return animators;
			    }
	}
	
	public static class BallScaleRippleIndicator extends BallScaleIndicator {
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        paint.setStyle(Paint.Style.STROKE);
			        paint.setStrokeWidth(3);
			        super.draw(canvas, paint);
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        ValueAnimator scaleAnim=ValueAnimator.ofFloat(0,1);
			        scaleAnim.setInterpolator(new android.view.animation.LinearInterpolator());
			        scaleAnim.setDuration(1000);
			        scaleAnim.setRepeatCount(-1);
			        addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                scale = (float) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        ValueAnimator alphaAnim=ValueAnimator.ofInt(0, 255);
			        alphaAnim.setInterpolator(new android.view.animation.LinearInterpolator());
			        alphaAnim.setDuration(1000);
			        alphaAnim.setRepeatCount(-1);
			        addUpdateListener(alphaAnim,new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                alpha = (int) animation.getAnimatedValue();
					                postInvalidate();
					            }
				        });
			        animators.add(scaleAnim);
			        animators.add(alphaAnim);
			        return animators;
			    }
	}
	
	public static class BallScaleRippleMultipleIndicator extends BallScaleMultipleIndicator {
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        paint.setStyle(Paint.Style.STROKE);
			        paint.setStrokeWidth(3);
			        super.draw(canvas, paint);
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        long[] delays=new long[]{0, 200, 400};
			        for (int i = 0; i < 3; i++) {
				            final int index=i;
				            ValueAnimator scaleAnim=ValueAnimator.ofFloat(0,1);
				            scaleAnim.setInterpolator(new android.view.animation.LinearInterpolator());
				            scaleAnim.setDuration(1000);
				            scaleAnim.setRepeatCount(-1);
				            addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    scaleFloats[index] = (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            scaleAnim.setStartDelay(delays[i]);
				            ValueAnimator alphaAnim=ValueAnimator.ofInt(0,255);
				            scaleAnim.setInterpolator(new android.view.animation.LinearInterpolator());
				            alphaAnim.setDuration(1000);
				            alphaAnim.setRepeatCount(-1);
				            addUpdateListener(alphaAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    alphaInts[index] = (int) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            scaleAnim.setStartDelay(delays[i]);
				            animators.add(scaleAnim);
				            animators.add(alphaAnim);
				        }
			        return animators;
			    }
	}
	
	
	public static class BallSpinFadeLoaderIndicator extends Indicator {
		    public static final float SCALE=1.0f;
		    public static final int ALPHA=255;
		    float[] scaleFloats=new float[]{SCALE,
			            SCALE,
			            SCALE,
			            SCALE,
			            SCALE,
			            SCALE,
			            SCALE,
			            SCALE};
		    int[] alphas=new int[]{ALPHA,
			            ALPHA,
			            ALPHA,
			            ALPHA,
			            ALPHA,
			            ALPHA,
			            ALPHA,
			            ALPHA};
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        float radius=getWidth()/10;
			        for (int i = 0; i < 8; i++) {
				            canvas.save();
				            Point point=circleAt(getWidth(),getHeight(),getWidth()/2-radius,i*(Math.PI/4));
				            canvas.translate(point.x,point.y);
				            canvas.scale(scaleFloats[i],scaleFloats[i]);
				            paint.setAlpha(alphas[i]);
				            canvas.drawCircle(0,0,radius,paint);
				            canvas.restore();
				        }
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        int[] delays= {0, 120, 240, 360, 480, 600, 720, 780, 840};
			        for (int i = 0; i < 8; i++) {
				            final int index=i;
				            ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.4f,1);
				            scaleAnim.setDuration(1000);
				            scaleAnim.setRepeatCount(-1);
				            scaleAnim.setStartDelay(delays[i]);
				            addUpdateListener(scaleAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    scaleFloats[index] = (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            ValueAnimator alphaAnim=ValueAnimator.ofInt(255, 77, 255);
				            alphaAnim.setDuration(1000);
				            alphaAnim.setRepeatCount(-1);
				            alphaAnim.setStartDelay(delays[i]);
				            addUpdateListener(alphaAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    alphas[index] = (int) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            animators.add(scaleAnim);
				            animators.add(alphaAnim);
				        }
			        return animators;
			    }
		    Point circleAt(int width,int height,float radius,double angle){
			        float x= (float) (width/2+radius*(Math.cos(angle)));
			        float y= (float) (height/2+radius*(Math.sin(angle)));
			        return new Point(x,y);
			    }
		    final class Point{
			        public float x;
			        public float y;
			        public Point(float x, float y){
				            this.x=x;
				            this.y=y;
				        }
			    }
	}
	
	
	public static class BallTrianglePathIndicator extends Indicator {
		    float[] translateX=new float[3],translateY=new float[3];
		    @Override
		    public void draw(Canvas canvas, Paint paint) {
			        paint.setStrokeWidth(3);
			        paint.setStyle(Paint.Style.STROKE);
			        for (int i = 0; i < 3; i++) {
				            canvas.save();
				            canvas.translate(translateX[i], translateY[i]);
				            canvas.drawCircle(0, 0, getWidth() / 10, paint);
				            canvas.restore();
				        }
			    }
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        float startX=getWidth()/5;
			        float startY=getWidth()/5;
			        for (int i = 0; i < 3; i++) {
				            final int index=i;
				            ValueAnimator translateXAnim=ValueAnimator.ofFloat(getWidth()/2,getWidth()-startX,startX,getWidth()/2);
				            if (i==1){
					                translateXAnim=ValueAnimator.ofFloat(getWidth()-startX,startX,getWidth()/2,getWidth()-startX);
					            }else if (i==2){
					                translateXAnim=ValueAnimator.ofFloat(startX,getWidth()/2,getWidth()-startX,startX);
					            }
				            ValueAnimator translateYAnim=ValueAnimator.ofFloat(startY,getHeight()-startY,getHeight()-startY,startY);
				            if (i==1){
					                translateYAnim=ValueAnimator.ofFloat(getHeight()-startY,getHeight()-startY,startY,getHeight()-startY);
					            }else if (i==2){
					                translateYAnim=ValueAnimator.ofFloat(getHeight()-startY,startY,getHeight()-startY,getHeight()-startY);
					            }
				            translateXAnim.setDuration(2000);
				            translateXAnim.setInterpolator(new android.view.animation.LinearInterpolator());
				                translateXAnim.setRepeatCount(-1);
				            addUpdateListener(translateXAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    translateX [index]= (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            translateYAnim.setDuration(2000);
				            translateYAnim.setInterpolator(new android.view.animation.LinearInterpolator());
				            translateYAnim.setRepeatCount(-1);
				            addUpdateListener(translateYAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    translateY [index]= (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            animators.add(translateXAnim);
				            animators.add(translateYAnim);
				        }
			        return animators;
			    }
	}
	
	
	public static class BallZigZagDeflectIndicator extends BallZigZagIndicator {
		    @Override
		    public ArrayList<ValueAnimator> onCreateAnimators() {
			        ArrayList<ValueAnimator> animators=new ArrayList<>();
			        float startX=getWidth()/6;
			        float startY=getWidth()/6;
			        for (int i = 0; i < 2; i++) {
				            final int index=i;
				            ValueAnimator translateXAnim=ValueAnimator.ofFloat(startX,getWidth()-startX,startX,getWidth()-startX,startX);
				            if (i==1){
					                translateXAnim=ValueAnimator.ofFloat(getWidth()-startX,startX,getWidth()-startX,startX,getWidth()-startX);
					            }
				            ValueAnimator translateYAnim=ValueAnimator.ofFloat(startY,startY,getHeight()-startY,getHeight()-startY,startY);
				            if (i==1){
					                translateYAnim=ValueAnimator.ofFloat(getHeight()-startY,getHeight()-startY,startY,startY,getHeight()-startY);
					            }
				            translateXAnim.setDuration(2000);
				            translateXAnim.setInterpolator(new android.view.animation.LinearInterpolator());
				            translateXAnim.setRepeatCount(-1);
				            addUpdateListener(translateXAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    translateX [index]= (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            translateYAnim.setDuration(2000);
				            translateYAnim.setInterpolator(new android.view.animation.LinearInterpolator());
				            translateYAnim.setRepeatCount(-1);
				            addUpdateListener(translateYAnim,new ValueAnimator.AnimatorUpdateListener() {
					                @Override
					                public void onAnimationUpdate(ValueAnimator animation) {
						                    translateY [index]= (float) animation.getAnimatedValue();
						                    postInvalidate();
						                }
					            });
				            animators.add(translateXAnim);
				            animators.add(translateYAnim);
				        }
			        return animators;
			    }
	}
	
	
	
	private android.graphics.drawable.AnimationDrawable rocketAnimation;
	
	public static class Drawables {
		    public static android.graphics.drawable.Drawable getSelectableDrawableFor(int color) {
			        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
				            android.graphics.drawable.StateListDrawable stateListDrawable = new android.graphics.drawable.StateListDrawable();
				            stateListDrawable.addState(
				                new int[]{android.R.attr.state_pressed},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#ffffff"))
				            );
				            stateListDrawable.addState(
				                new int[]{android.R.attr.state_focused},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"))
				            );
				            stateListDrawable.addState(
				                new int[]{},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"))
				            );
				            return stateListDrawable;
				        } else {
				            android.content.res.ColorStateList pressedColor = android.content.res.ColorStateList.valueOf(color);
				            android.graphics.drawable.ColorDrawable defaultColor = new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"));
				            
				android.graphics.drawable.Drawable rippleColor = getRippleColor(color);
				            return new android.graphics.drawable.RippleDrawable(
				                pressedColor,
				                defaultColor,
				                rippleColor
				            );
				        }
			    }
		
		    private static android.graphics.drawable.Drawable getRippleColor(int color) {
			        float[] outerRadii = new float[8];
			        Arrays.fill(outerRadii, 0);
			        android.graphics.drawable.shapes.RoundRectShape r = new android.graphics.drawable.shapes.RoundRectShape(outerRadii, null, null);
			        
			android.graphics.drawable.ShapeDrawable shapeDrawable = new 
			android.graphics.drawable.ShapeDrawable(r);
			        shapeDrawable.getPaint().setColor(color);
			        return shapeDrawable;
			    }
		 
		    private static int lightenOrDarken(int color, double fraction) {
			        if (canLighten(color, fraction)) {
				            return lighten(color, fraction);
				        } else {
				            return darken(color, fraction);
				        }
			    }
		 
		    private static int lighten(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        red = lightenColor(red, fraction);
			        green = lightenColor(green, fraction);
			        blue = lightenColor(blue, fraction);
			        int alpha = Color.alpha(color);
			        return Color.argb(alpha, red, green, blue);
			    }
		 
		    private static int darken(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        red = darkenColor(red, fraction);
			        green = darkenColor(green, fraction);
			        blue = darkenColor(blue, fraction);
			        int alpha = Color.alpha(color);
			 
			        return Color.argb(alpha, red, green, blue);
			    }
		 
		    private static boolean canLighten(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        return canLightenComponent(red, fraction)
			            && canLightenComponent(green, fraction)
			            && canLightenComponent(blue, fraction);
			    }
		 
		    private static boolean canLightenComponent(int colorComponent, double fraction) {
			        int red = Color.red(colorComponent);
			        int green = Color.green(colorComponent);
			        int blue = Color.blue(colorComponent);
			        return red + (red * fraction) < 255
			            && green + (green * fraction) < 255
			            && blue + (blue * fraction) < 255;
			    }
		 
		    private static int darkenColor(int color, double fraction) {
			        return (int) Math.max(color - (color * fraction), 0);
			    }
		 
		    private static int lightenColor(int color, double fraction) {
			        return (int) Math.min(color + (color * fraction), 255);
			    }
	}
	public static class CircleDrawables {
		    public static android.graphics.drawable.Drawable getSelectableDrawableFor(int color) {
			        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
				            android.graphics.drawable.StateListDrawable stateListDrawable = new android.graphics.drawable.StateListDrawable();
				            stateListDrawable.addState(
				                new int[]{android.R.attr.state_pressed},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#ffffff"))
				            );
				            stateListDrawable.addState(
				                new int[]{android.R.attr.state_focused},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"))
				            );
				            stateListDrawable.addState(
				                new int[]{},
				                new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"))
				            );
				            return stateListDrawable;
				        } else {
				            android.content.res.ColorStateList pressedColor = android.content.res.ColorStateList.valueOf(color);
				            android.graphics.drawable.ColorDrawable defaultColor = new android.graphics.drawable.ColorDrawable(Color.parseColor("#00ffffff"));
				            
				android.graphics.drawable.Drawable rippleColor = getRippleColor(color);
				            return new android.graphics.drawable.RippleDrawable(
				                pressedColor,
				                defaultColor,
				                rippleColor
				            );
				        }
			    }
		
		    private static android.graphics.drawable.Drawable getRippleColor(int color) {
			        float[] outerRadii = new float[180];
			        Arrays.fill(outerRadii, 80);
			        android.graphics.drawable.shapes.RoundRectShape r = new android.graphics.drawable.shapes.RoundRectShape(outerRadii, null, null);
			        
			android.graphics.drawable.ShapeDrawable shapeDrawable = new 
			android.graphics.drawable.ShapeDrawable(r);
			        shapeDrawable.getPaint().setColor(color);
			        return shapeDrawable;
			    }
		 
		    private static int lightenOrDarken(int color, double fraction) {
			        if (canLighten(color, fraction)) {
				            return lighten(color, fraction);
				        } else {
				            return darken(color, fraction);
				        }
			    }
		 
		    private static int lighten(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        red = lightenColor(red, fraction);
			        green = lightenColor(green, fraction);
			        blue = lightenColor(blue, fraction);
			        int alpha = Color.alpha(color);
			        return Color.argb(alpha, red, green, blue);
			    }
		 
		    private static int darken(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        red = darkenColor(red, fraction);
			        green = darkenColor(green, fraction);
			        blue = darkenColor(blue, fraction);
			        int alpha = Color.alpha(color);
			 
			        return Color.argb(alpha, red, green, blue);
			    }
		 
		    private static boolean canLighten(int color, double fraction) {
			        int red = Color.red(color);
			        int green = Color.green(color);
			        int blue = Color.blue(color);
			        return canLightenComponent(red, fraction)
			            && canLightenComponent(green, fraction)
			            && canLightenComponent(blue, fraction);
			    }
		 
		    private static boolean canLightenComponent(int colorComponent, double fraction) {
			        int red = Color.red(colorComponent);
			        int green = Color.green(colorComponent);
			        int blue = Color.blue(colorComponent);
			        return red + (red * fraction) < 255
			            && green + (green * fraction) < 255
			            && blue + (blue * fraction) < 255;
			    }
		 
		    private static int darkenColor(int color, double fraction) {
			        return (int) Math.max(color - (color * fraction), 0);
			    }
		 
		    private static int lightenColor(int color, double fraction) {
			        return (int) Math.min(color + (color * fraction), 255);
		}
	}
	
	public void drawableclass() {
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
	
	
	public void _refreshLikes () {
		if (mp != null) {
			if (likeChild.contains(currentlyPlaying.concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))) {
				image_fav.setImageResource(R.drawable.favourite_red);
			}
			else {
				if (data.getString("mode", "").equals("light")) {
					image_fav.setImageResource(R.drawable.favourite_black);
				}
				else {
					image_fav.setImageResource(R.drawable.favourite_white);
				}
			}
		}
	}
	
	
	public void _setMenu (final double _position) {
		tabsPos = _position;
		listview2.setVisibility(View.GONE);
		listview1.setVisibility(View.VISIBLE);
		if (_position == 0) {
			childkey.clear();
			upload_list.clear();
			double _tmpNum = 0;
			for(int _repeat23 = 0; _repeat23 < (int)(oldListmap.size()); _repeat23++) {
				childkey.add((int)(0), oldChildKey.get((int)(_tmpNum)));
				{
					HashMap<String, Object> _item = new HashMap<>();
					_item = oldListmap.get((int)_tmpNum);
					upload_list.add((int)0, _item);
				}
				int _index = listview1.getFirstVisiblePosition();
				View _view = listview1.getChildAt(0);
				int _top = (_view == null) ? 0 : _view.getTop();
				listview1.setAdapter(new Listview1Adapter(upload_list));
				listview1.setSelectionFromTop(_index, _top);
				_tmpNum++;
			}
		}
		if (_position == 1) {
			childkey.clear();
			upload_list.clear();
			double _tmpNum = 0;
			for(int _repeat40 = 0; _repeat40 < (int)(like_Mgmap.size()); _repeat40++) {
				if (like_Mgmap.get((int)_tmpNum).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid()) && oldChildKey.contains(like_Mgmap.get((int)_tmpNum).get("key").toString())) {
					childkey.add((int)(0), like_Mgmap.get((int)_tmpNum).get("key").toString());
					{
						HashMap<String, Object> _item = new HashMap<>();
						_item = oldListmap.get((int)oldChildKey.indexOf(like_Mgmap.get((int)_tmpNum).get("key").toString()));
						upload_list.add((int)0, _item);
					}
					int _index = listview1.getFirstVisiblePosition();
					View _view = listview1.getChildAt(0);
					int _top = (_view == null) ? 0 : _view.getTop();
					listview1.setAdapter(new Listview1Adapter(upload_list));
					listview1.setSelectionFromTop(_index, _top);
				}
				else {
					if ((_tmpNum == (like_Mgmap.size() - 1)) && (like_Mgmap.size() == 0)) {
						int _index = listview1.getFirstVisiblePosition();
						View _view = listview1.getChildAt(0);
						int _top = (_view == null) ? 0 : _view.getTop();
						listview1.setAdapter(new Listview1Adapter(upload_list));
						listview1.setSelectionFromTop(_index, _top);
					}
				}
				_tmpNum++;
			}
		}
		if (_position == 2) {
			childkey.clear();
			upload_list.clear();
			oldChildKey.clear();
			oldListmap.clear();
			listview1.setVisibility(View.GONE);
			linear_data.setVisibility(View.VISIBLE);
			upload_text.removeEventListener(_upload_text_child_listener);
			upload_text.addChildEventListener(_upload_text_child_listener);
		}
		if (_position == 3) {
			childkey.clear();
			upload_list.clear();
			listview1.setAdapter(new Listview1Adapter(upload_list));
			listview1.setVisibility(View.GONE);
			listview2.setVisibility(View.VISIBLE);
		}
	}
	
	
	public void _downloadFile (final String _url, final String _path) {
		try {
			String _fileName = URLUtil.guessFileName(_url, null, null);
			java.io.File _filePath = new java.io.File(_path + "/" + _fileName);
			DownloadManager.Request request = new DownloadManager.Request(Uri.parse(_url));
			
			request.setDescription(_url);
			
			request.setTitle(_fileName);
			
			request.allowScanningByMediaScanner();
			
			request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
			
			request.setDestinationUri(Uri.fromFile(_filePath));
			
			final DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
			
			final long downloadID = downloadManager.enqueue(request);
		}
		catch (Exception _error) {
			_customSnackBar(_error.toString(), 2);
		}
	}
	
	
	public void _ResetDialog () {
		d = null;
		d = new AlertDialog.Builder(this);
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
			txt1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
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
	
	
	public void _addToPlaylist (final double _position, final double _listviewpos) {
		if (!((playlistString.size() - 1) == _position)) {
			playKeys = new Gson().fromJson(playlistMap.get((int)_position).get("keys").toString(), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("key", childkey.get((int)(_listviewpos)));
				playKeys.add(_item);
			}
			
			playlistMap.get((int)_position).put("keys", new Gson().toJson(playKeys));
			data.edit().putString("playlist", new Gson().toJson(playlistMap)).commit();
			playKeys.clear();
			_customSnackBar("Song Added In Playlist!", 1);
		}
		else {
			final AlertDialog.Builder alert = new AlertDialog.Builder(SteamerActivity.this);
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
			if (isDarkMode) {
				apply.setTextColor(Color.parseColor("#f5f5f5"));
				cancel.setTextColor(Color.parseColor("#f5f5f5"));
				title.setTextColor(Color.parseColor("#f5f5f5"));
				message.setTextColor(Color.parseColor("#ffffff"));
			}
			else {
				apply.setTextColor(Color.parseColor("#424242"));
				cancel.setTextColor(Color.parseColor("#424242"));
				title.setTextColor(Color.parseColor("#424242"));
				message.setTextColor(Color.parseColor("#424242"));
			}
			if (true) {
				title.setText("Add Playlist");
				message.setText("Enter Your New Playlist Name");
				apply.setText("CREATE");
				cancel.setText("CANCEL");
				image.setImageResource(R.drawable.music_note_grey);
			}
			dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));
			dialog.setCancelable(false);
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
					PlaylistName = email.getText().toString();
					if (true) {
						if (PlaylistName.trim().equals("")) {
							_customSnackBar("Enter Your Playlist Name!", 2);
						}
						else {
							playlistString.clear();
							{
								HashMap<String, Object> _item = new HashMap<>();
								_item.put("key", childkey.get((int)(_position)));
								playKeys.add(_item);
							}
							
							{
								HashMap<String, Object> _item = new HashMap<>();
								_item.put("name", PlaylistName);
								playlistMap.add(_item);
							}
							
							playlistMap.get((int)playlistMap.size() - 1).put("keys", new Gson().toJson(playKeys));
							data.edit().putString("playlist", new Gson().toJson(playlistMap)).commit();
							playKeys.clear();
							for(int _repeat151 = 0; _repeat151 < (int)(playlistMap.size()); _repeat151++) {
								playlistString.add(playlistMap.get((int)playlistString.size()).get("name").toString());
							}
							playlistString.add("Add a new playlist");
							((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
							_customSnackBar("Song Added!", 1);
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
	}
	
	
	public void _deleteSongInPlaylist (final double _position) {
		playlistMap = new Gson().fromJson(playlistMap.get((int)numPlaylist).get("keys").toString(), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		playlistString.remove((int)(_position));
		childkey.remove((int)(_position));
		upload_list.remove((int)(_position));
		int _index = listview1.getFirstVisiblePosition();
		View _view = listview1.getChildAt(0);
		int _top = (_view == null) ? 0 : _view.getTop();
		listview1.setAdapter(new Listview1Adapter(upload_list));
		listview1.setSelectionFromTop(_index, _top);
		playlistMap.get((int)numPlaylist).put("keys", new Gson().toJson(playKeys));
		data.edit().putString("playlist", new Gson().toJson(playlistMap)).commit();
		playKeys.clear();
	}
	
	
	public void _addGrid (final double _position) {
		if (!((playlistString.size() - 1) == _position)) {
			isPlaylist = true;
			childkey.clear();
			upload_list.clear();
			listview2.setVisibility(View.GONE);
			_TransitionManager(fragment1, 300);
			listview1.setVisibility(View.VISIBLE);
			music.setVisibility(View.GONE);
			garage.setVisibility(View.GONE);
			image_search.setVisibility(View.GONE);
			text_playlist.setVisibility(View.VISIBLE);
			image_menu.setImageResource(R.drawable.back_grey);
			text_playlist.setText(playlistMap.get((int)_position).get("name").toString());
			numPlaylist = _position;
			playKeys = new Gson().fromJson(playlistMap.get((int)_position).get("keys").toString(), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			double _tmpNum = 0;
			for(int _repeat36 = 0; _repeat36 < (int)(playKeys.size()); _repeat36++) {
				if (oldChildKey.contains(playKeys.get((int)_tmpNum).get("key").toString())) {
					childkey.add((int)(0), playKeys.get((int)_tmpNum).get("key").toString());
					{
						HashMap<String, Object> _item = new HashMap<>();
						_item = oldListmap.get((int)oldChildKey.indexOf(playKeys.get((int)_tmpNum).get("key").toString()));
						upload_list.add((int)0, _item);
					}
					int _index = listview1.getFirstVisiblePosition();
					View _view = listview1.getChildAt(0);
					int _top = (_view == null) ? 0 : _view.getTop();
					listview1.setAdapter(new Listview1Adapter(upload_list));
					listview1.setSelectionFromTop(_index, _top);
				}
				_tmpNum++;
			}
		}
		else {
			final AlertDialog.Builder alert = new AlertDialog.Builder(SteamerActivity.this);
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
			if (isDarkMode) {
				apply.setTextColor(Color.parseColor("#f5f5f5"));
				cancel.setTextColor(Color.parseColor("#f5f5f5"));
				title.setTextColor(Color.parseColor("#f5f5f5"));
				message.setTextColor(Color.parseColor("#ffffff"));
			}
			else {
				apply.setTextColor(Color.parseColor("#424242"));
				cancel.setTextColor(Color.parseColor("#424242"));
				title.setTextColor(Color.parseColor("#424242"));
				message.setTextColor(Color.parseColor("#424242"));
			}
			if (true) {
				title.setText("Playlist name");
				message.setText("Enter your new playlist name");
				apply.setText("CREATE");
				cancel.setText("CANCEL");
				image.setImageResource(R.drawable.music_note_grey);
			}
			dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));
			dialog.setCancelable(false);
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
					PlaylistName = email.getText().toString();
					if (true) {
						if (PlaylistName.trim().equals("")) {
							_customSnackBar("Playlist name can't be empty!", 2);
						}
						else {
							playlistString.clear();
							{
								HashMap<String, Object> _item = new HashMap<>();
								_item.put("name", PlaylistName);
								playlistMap.add(_item);
							}
							
							playlistMap.get((int)playlistMap.size() - 1).put("keys", "[]");
							data.edit().putString("playlist", new Gson().toJson(playlistMap)).commit();
							playKeys.clear();
							for(int _repeat167 = 0; _repeat167 < (int)(playlistMap.size()); _repeat167++) {
								playlistString.add(playlistMap.get((int)playlistString.size()).get("name").toString());
							}
							playlistString.add("Add a new playlist");
							((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
							_customSnackBar("Added Successfully!", 1);
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
	}
	
	
	public void _delGrid (final double _position) {
		if (!((playlistString.size() - 1) == _position)) {
			final AlertDialog.Builder alert = new AlertDialog.Builder(SteamerActivity.this);
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
			cancel.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
			apply.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
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
				title.setText("Delete Playlist");
				message.setText("Are you sure do you want to delete this playlist now?");
				apply.setText("YES");
				cancel.setText("NO");
			}
			if (isDarkMode) {
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
						playlistMap.remove((int)(_position));
						playlistString.remove((int)(_position));
						((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
						data.edit().putString("playlist", new Gson().toJson(playlistMap)).commit();
						_customSnackBar("Playlist Deleted Successfully!", 1);
					}
					dialog.dismiss();}});
			cancel.setOnClickListener(new View.OnClickListener() {
				@Override public void onClick(View v) {
					if (true) {
						
					}
					dialog.dismiss();}});
			dialog.show();
		}
	}
	
	
	public void _play (final String _key) {
		try {
			_MusicGarageLoading(true, "");
			if (mp != null) {
				if (mp.isPlaying()) {
					mp.stop();
				}
				mp.release();
				mp = null;
			}
			final double _position = currentlyChild.indexOf(_key);
			mp = new MediaPlayer();
			mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mp.setDataSource(currentlyMap.get((int)_position).get("url").toString());
			currentlyPlaying = _key;
			positionScroll = _position;
			song_title.setText(currentlyMap.get((int)_position).get("name").toString());
			if (currentlyMap.get((int)_position).containsKey("Media Artist")) {
				if (username_list.contains(currentlyMap.get((int)_position).get("uid").toString())) {
					text_artist.setText(profile_map.get((int)username_list.indexOf(currentlyMap.get((int)_position).get("uid").toString())).get("username").toString());
				}
				else {
					text_artist.setText(currentlyMap.get((int)_position).get("uid").toString());
				}
			}
			else {
				text_artist.setText(currentlyMap.get((int)_position).get("Media Artist").toString());
			}
			text_album.setText(currentlyMap.get((int)_position).get("Media Album").toString());
			if (currentlyMap.get((int)_position).containsKey("img")) {
				Glide.with(getApplicationContext()).load(Uri.parse(currentlyMap.get((int)_position).get("img").toString())).into(album);
			}
			else {
				album.setImageResource(R.drawable.music_note_large_white);
			}
			mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
				        @Override
				        public void onPrepared(MediaPlayer _unmp) {
					if (data.getString("nightcore", "").equals("1")) {
						PlaybackParams params = new PlaybackParams();
						params.setPitch(1.20f);
						params.setSpeed(1.20f);
						mp.setPlaybackParams(params);
					}
					_requestFocus();
					_MusicGarageLoading(false, "");
					_refreshLikes();
					if (openNum == 0) {
						_showPlayerBlock();
					}
					view_map = new HashMap<>();
					view_map = currentlyMap.get((int)_position);
					view_map.put("view", String.valueOf((long)(Double.parseDouble(view_map.get("view").toString()) + 1)));
					upload_text.child(currentlyPlaying).updateChildren(view_map);
					text_duration.setText(new DecimalFormat("00").format(mp.getDuration() / 60000).concat(":".concat(new DecimalFormat("00").format((mp.getDuration() / 1000) % 60))));
					seekbar1.setMax((int)mp.getDuration() / 1000);
					mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
						            @Override
						            public void onBufferingUpdate(MediaPlayer _unmp, int _percent) {
							               try {
								               seekbar1.setSecondaryProgress((_percent * mp.getDuration()) / 100000);
							}
							catch (Exception _e) {
							}
							            }
						        });
					
					mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
						
						public void onCompletion(MediaPlayer _unmp){
							if (data.getString("repeat", "").equals("0")) {
								if (currentlyMap.size() > (_position + 1)) {
									_play(currentlyChild.get((int)(_position + 1)));
								}
								else {
									currentlyPlaying = "";
									openNum = 0;
									_autoTransitionScroll(fragment1);
									_LayoutParams(linear_holder, SketchwareUtil.getDisplayWidthPixels(getApplicationContext()), SketchwareUtil.getDisplayHeightPixels(getApplicationContext()) - SketchwareUtil.getDip(getApplicationContext(), (int)(80)));
									image_nightcore.setVisibility(View.VISIBLE);
									_removeFocus();
									mp.release();
									mp = null;
									currentlyChild.clear();
									currentlyMap.clear();
								}
							}
							else {
								if (data.getString("repeat", "").equals("1")) {
									if (currentlyMap.size() > (_position + 1)) {
										_play(currentlyChild.get((int)(_position + 1)));
									}
									else {
										_play(currentlyChild.get((int)(0)));
									}
								}
								else {
									if (data.getString("repeat", "").equals("2")) {
										mp.seekTo((int)(0));
										mp.start();
										view_map = new HashMap<>();
										view_map = currentlyMap.get((int)_position);
										view_map.put("view", String.valueOf((long)(Double.parseDouble(view_map.get("view").toString()) + 1)));
										upload_text.child(currentlyPlaying).updateChildren(view_map);
									}
									else {
										if (data.getString("shuffle", "").equals("true")) {
											_play(currentlyChild.get((int)(SketchwareUtil.getRandom((int)(0), (int)(currentlyMap.size() - 1)))));
										}
									}
								}
							}
						}
					});
					
					        }
				    });
			mp.prepareAsync();
		} catch (Exception _error) {
			_MusicGarageLoading(false, "");
			_customSnackBar(_error.toString(), 2);
			currentlyPlaying = "";
			openNum = 0;
			_autoTransitionScroll(fragment1);
			_LayoutParams(linear_holder, SketchwareUtil.getDisplayWidthPixels(getApplicationContext()), SketchwareUtil.getDisplayHeightPixels(getApplicationContext()) - SketchwareUtil.getDip(getApplicationContext(), (int)(80)));
			image_nightcore.setVisibility(View.VISIBLE);
			_removeFocus();
			mp = null;
			currentlyChild.clear();
			currentlyMap.clear();
		}
	}
	
	
	public void _showPlayerBlock () {
		openNum = 2;
		_autoTransitionScroll(fragment1);
		_LayoutParams(linear_holder, SketchwareUtil.getDisplayWidthPixels(getApplicationContext()), SketchwareUtil.getDisplayHeightPixels(getApplicationContext()) - SketchwareUtil.getDip(getApplicationContext(), (int)(80)));
		image_nightcore.setVisibility(View.VISIBLE);
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
	
	
	public void _setImageViewRipple (final ImageView _imageview, final String _color1, final String _color2) {
		_imageview.setImageTintList(new android.content.res.ColorStateList(new int[][] {{-android.R.attr.state_pressed},{android.R.attr.state_pressed}},new int[]{Color.parseColor(_color1), Color.parseColor(_color2)}));
	}
	
	
	public void _TransitionManager (final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
	}
	
	
	public void _DeleteSongInPlaylist (final double _position) {
		final AlertDialog.Builder alert = new AlertDialog.Builder(SteamerActivity.this);
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
		apply.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
		message.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
		cancel.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
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
			title.setText("Delete Now");
			message.setText("Are you sure to delete this song from your playlist");
			apply.setText("YES");
			cancel.setText("NO");
		}
		if (isDarkMode) {
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
					_deleteSongInPlaylist(_position);
				}
				dialog.dismiss();}});
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				if (true) {
					
				}
				dialog.dismiss();}});
		dialog.show();
	}
	
	
	public void _ReportDialogMG (final double _position) {
		final AlertDialog.Builder alert = new AlertDialog.Builder(SteamerActivity.this);
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
			message.setText("Enter your valid reason why you are reporting this song if your reason is valid then our team will delete this song");
			email.setHint("Enter your reason");
			apply.setText("SUBMIT");
			cancel.setText("CANCEL");
			image.setImageResource(R.drawable.report_red);
		}
		title.setTextColor(Color.parseColor("#f44336"));
		apply.setTextColor(Color.parseColor("#f44336"));
		if (isDarkMode) {
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
						map_report.put("reported id", upload_list.get((int)_position).get("uid").toString());
						map_report.put("reported song", upload_list.get((int)_position).get("name").toString());
						cal = Calendar.getInstance();
						map_report.put("time", String.valueOf((long)(cal.getTimeInMillis())));
						report_song.push().updateChildren(map_report);
						map_report.clear();
						final AlertDialog.Builder alert = new AlertDialog.Builder(SteamerActivity.this);
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
							title.setText("Song Reported");
							message.setText("Thank you for reporting this song our team will manually check your report and take action if your reason is valid and our team will send you email about this reported song and also we send your report to the song owner its take sometime but we will inform you.");
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
	
	
	public void _curcle_igm_url (final String _url, final ImageView _img_view) {
		
		Glide.with(getApplicationContext()).asBitmap().load(_url).centerCrop().into(new com.bumptech.glide.request.target.BitmapImageViewTarget(_img_view) {
			@Override protected void setResource(Bitmap resource) {
				androidx.core.graphics.drawable.RoundedBitmapDrawable circularBitmapDrawable = androidx.core.graphics.drawable.RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource); circularBitmapDrawable.setCircular(true); _img_view.setImageDrawable(circularBitmapDrawable);
			}
		});
	}
	
	
	public void _FB_AdView_Banner () {
		ID_Placement_banner = "412172596454360_413073836364236";
		AdView adView = new AdView(this, ID_Placement_banner, AdSize.BANNER_HEIGHT_50);
		        // Find the Ad Container
		        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
		        // Add the ad view to your activity layout
		        adContainer.addView(adView);
		        // Request an ad
		
		        AdListener adListener = new AdListener() {
			            @Override
			            public void onError(Ad ad, AdError adError) {
			}
			            @Override
			            public void onAdLoaded(Ad ad) {
			}                @Override
			            public void onAdClicked(Ad ad) {
				                // Ad clicked callback
				//What To Do
			}
			
			            @Override
			            public void onLoggingImpression(Ad ad) {
				                // Ad impression logged callback
				            }
			        };
		        // Request an ad
		        AdView.AdViewLoadConfig loadAdConfig = adView.buildLoadAdConfig()
					.withAdListener(adListener)
					.build();
		
				adView.loadAd(loadAdConfig);
	}
	
	
	public void _AdView_Interstitial () {
		ID_Interstitial = "412172596454360_413076706363949";
		 // Instantiate an InterstitialAd object.
		        // NOTE: the placement ID will eventually identify this as your App, you can ignore it for
		        // now, while you are testing and replace it later when you have signed up.
		        // While you are using this temporary code you will only get test ads and if you release
		        // your code like this to the Google Play your users will not receive ads (you will get a no fill error).
		        interstitialAd = new InterstitialAd(this, ID_Interstitial);
		// Set listeners for the Interstitial Ad
		        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
			            @Override
			            public void onInterstitialDisplayed(Ad ad) {
				                // Interstitial ad displayed callback
				                Log.e(TAG, "Interstitial ad displayed.");
				            }
			
			            @Override
			            public void onInterstitialDismissed(Ad ad) {
				                // Interstitial dismissed callback
				                Log.e(TAG, "Interstitial ad dismissed.");
				                interstitialAd.loadAd();
				            }
			
			            @Override
			            public void onError(Ad ad, AdError adError) {
				                // Ad error callback
				                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
				           
				 }
			
			            @Override
			            public void onAdLoaded(Ad ad) {
				                // Interstitial ad is loaded and ready to be displayed
				                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
				
				            
			}
			
			            @Override
			            public void onAdClicked(Ad ad) {
				                // Ad clicked callback
				                Log.d(TAG, "Interstitial ad clicked!");
				            }
			
			            @Override
			            public void onLoggingImpression(Ad ad) {
				                // Ad impression logged callback
				                Log.d(TAG, "Interstitial ad impression logged!");
				            }
			        };
		
		        // For auto play video ads, it's recommended to load the ad
		        // at least 30 seconds before it is shown
		        interstitialAd.loadAd(interstitialAd.buildLoadAdConfig()
				.withAdListener(interstitialAdListener) 
				
				.build());
	}
	
	
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.list, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = (LinearLayout) _view.findViewById(R.id.linear3);
			final LinearLayout linear5 = (LinearLayout) _view.findViewById(R.id.linear5);
			final LinearLayout linear4 = (LinearLayout) _view.findViewById(R.id.linear4);
			final LinearLayout cardview = (LinearLayout) _view.findViewById(R.id.cardview);
			final ImageView image_album = (ImageView) _view.findViewById(R.id.image_album);
			final TextView text_song = (TextView) _view.findViewById(R.id.text_song);
			final TextView text_artist = (TextView) _view.findViewById(R.id.text_artist);
			final ImageView imageview2 = (ImageView) _view.findViewById(R.id.imageview2);
			final TextView text_like = (TextView) _view.findViewById(R.id.text_like);
			final ImageView imageview3 = (ImageView) _view.findViewById(R.id.imageview3);
			final TextView text_comments = (TextView) _view.findViewById(R.id.text_comments);
			final ImageView imageview4 = (ImageView) _view.findViewById(R.id.imageview4);
			final TextView text_view = (TextView) _view.findViewById(R.id.text_view);
			
			if (data.getString("mode", "").equals("light")) {
				_MusicGarageUI(linear1, SketchwareUtil.getDip(getApplicationContext(), (int)(20)), 0, "#FFFFFF", "#FFFFFF", 0, true);
				text_song.setTextColor(0xFF212121);
				text_artist.setTextColor(0xFF404040);
				text_like.setTextColor(0xFF252525);
				text_comments.setTextColor(0xFF252525);
				text_view.setTextColor(0xFF252525);
			}
			else {
				_MusicGarageUI(linear1, SketchwareUtil.getDip(getApplicationContext(), (int)(20)), 0, "#252525", "#FFFFFF", 0, true);
				text_song.setTextColor(0xFF838383);
				text_artist.setTextColor(0xFFF5F5F5);
				text_like.setTextColor(0xFFF5F5F5);
				text_comments.setTextColor(0xFFF5F5F5);
				text_view.setTextColor(0xFFF5F5F5);
			}
			linear1.setClickable(true);
			_addCardView(cardview, 5, SketchwareUtil.getDip(getApplicationContext(), (int)(15)), 0, 0, true, data.getString("accentColor", ""));
			text_song.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
			text_artist.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
			text_like.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
			text_comments.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
			text_view.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
			text_song.setText(upload_list.get((int)_position).get("name").toString());
			if (upload_list.get((int)_position).get("Media Artist").toString().equals("Unknown Artist")) {
				if (username_list.contains(upload_list.get((int)_position).get("uid").toString())) {
					text_artist.setText(profile_map.get((int)username_list.indexOf(upload_list.get((int)_position).get("uid").toString())).get("username").toString());
				}
				else {
					text_artist.setText(upload_list.get((int)_position).get("uid").toString());
				}
			}
			else {
				text_artist.setText(upload_list.get((int)_position).get("Media Artist").toString());
			}
			if (Double.parseDouble(upload_list.get((int)_position).get("view").toString()) > 999) {
				text_view.setText(new DecimalFormat("0.00").format(Double.parseDouble(upload_list.get((int)_position).get("view").toString()) / 1000).concat("K"));
			}
			else {
				text_view.setText(upload_list.get((int)_position).get("view").toString());
			}
			if (upload_list.get((int)_position).containsKey("img")) {
				Glide.with(getApplicationContext()).load(Uri.parse(upload_list.get((int)_position).get("img").toString())).into(image_album);
			}
			else {
				image_album.setImageResource(R.drawable.music_note_large_white);
			}
			if (likes_map.containsKey(childkey.get((int)(_position)))) {
				text_like.setText(likes_map.get(childkey.get((int)(_position))).toString());
			}
			else {
				text_like.setText("0");
			}
			if (commentsMap.containsKey(childkey.get((int)(_position)))) {
				text_comments.setText(commentsMap.get(childkey.get((int)(_position))).toString());
			}
			else {
				text_comments.setText("0");
			}
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override public void onClick(View _view) {
					currentlyChild.clear();
					currentlyMap.clear();
					/*
currentlyMap = new ArrayList<>(upload_list);
currentlyChild = new ArrayList<>(childkey);
*/
					for(int _repeat113 = 0; _repeat113 < (int)(upload_list.size()); _repeat113++) {
						{
							HashMap<String, Object> _item = new HashMap<>();
							_item = upload_list.get((int)currentlyMap.size());
							currentlyMap.add(_item);
						}
						if (currentlyMap.size() == upload_list.size()) {
							for(int _repeat120 = 0; _repeat120 < (int)(childkey.size()); _repeat120++) {
								currentlyChild.add(childkey.get((int)(currentlyChild.size())));
								if (currentlyChild.size() == childkey.size()) {
									_play(currentlyChild.get((int)(_position)));
								}
							}
						}
					}
				}
			});
			linear1.setOnLongClickListener(new View.OnLongClickListener() {
				@Override public boolean onLongClick(View _view) {
					if (!(tabsPos == 3)) {
						final AlertDialog.Builder alert = new AlertDialog.Builder(SteamerActivity.this);
						View view = getLayoutInflater().inflate(R.layout.dialog_option,null);
						TextView cancel = (TextView)view.findViewById(R.id.cancel);
						TextView title = (TextView)view.findViewById(R.id.title);
						TextView profile_text = (TextView)view.findViewById(R.id.profile_text);
						TextView delete_text = (TextView)view.findViewById(R.id.delete_text);
						TextView report_text = (TextView)view.findViewById(R.id.report_text);
						TextView comment_text = (TextView)view.findViewById(R.id.comment_text);
						TextView playlist_text = (TextView)view.findViewById(R.id.playlist_text);
						LinearLayout main = (LinearLayout)view.findViewById(R.id.main);
						LinearLayout profile = (LinearLayout)view.findViewById(R.id.profile);
						LinearLayout delete = (LinearLayout)view.findViewById(R.id.delete);
						LinearLayout report = (LinearLayout)view.findViewById(R.id.report);
						LinearLayout comment = (LinearLayout)view.findViewById(R.id.comment);
						LinearLayout playlist = (LinearLayout)view.findViewById(R.id.playlist);
						ImageView image = (ImageView)view.findViewById(R.id.image);
						ImageView profile_image = (ImageView)view.findViewById(R.id.profile_image);
						ImageView delete_image = (ImageView)view.findViewById(R.id.delete_image);
						ImageView report_image = (ImageView)view.findViewById(R.id.report_image);
						ImageView comment_image = (ImageView)view.findViewById(R.id.comment_image);
						ImageView playlist_image = (ImageView)view.findViewById(R.id.playlist_image);
						alert.setView(view);
						final AlertDialog dialog = alert.create();
						title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
						cancel.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansbold.ttf"), 0);
						profile_text.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
						delete_text.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
						report_text.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
						comment_text.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
						playlist_text.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
						_setBackground(main,(double)50,(double)0,color,false);
						_setBackground(profile,(double)20,(double)0,color,true);
						_setBackground(delete,(double)20,(double)0,color,true);
						_setBackground(report,(double)20,(double)0,color,true);
						_setBackground(comment,(double)20,(double)0,color,true);
						_setBackground(playlist,(double)20,(double)0,color,true);
						_setBackground(cancel,(double)50,(double)0,color,true);
						if (upload_list.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
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
							delete_text.setText("Delete this song");
							report_text.setText("Report this song");
							comment_text.setText("View Comments");
							playlist_text.setText("Add to my playlist");
						}
						delete_text.setTextColor(Color.parseColor("#f44336"));
						report_text.setTextColor(Color.parseColor("#f44336"));
						delete_image.setImageResource(R.drawable.delete_red);
						report_image.setImageResource(R.drawable.report_red);
						if (isDarkMode) {
							title.setTextColor(Color.parseColor("#f5f5f5"));
							cancel.setTextColor(Color.parseColor("#f5f5f5"));
							profile_text.setTextColor(Color.parseColor("#f5f5f5"));
							comment_text.setTextColor(Color.parseColor("#f5f5f5"));
							playlist_text.setTextColor(Color.parseColor("#f5f5f5"));
							profile_image.setImageResource(R.drawable.user_white);
							image.setImageResource(R.drawable.icon_info_white);
							comment_image.setImageResource(R.drawable.comment_white);
							playlist_image.setImageResource(R.drawable.music_note_white);
						}
						else {
							cancel.setTextColor(Color.parseColor("#212121"));
							profile_text.setTextColor(Color.parseColor("#212121"));
							title.setTextColor(Color.parseColor("#212121"));
							comment_text.setTextColor(Color.parseColor("#212121"));
							playlist_text.setTextColor(Color.parseColor("#212121"));
							profile_image.setImageResource(R.drawable.user_black);
							comment_image.setImageResource(R.drawable.comment_black);
							image.setImageResource(R.drawable.icon_info_white);
							image.setColorFilter(0xFF212121, PorterDuff.Mode.MULTIPLY);
							playlist_image.setImageResource(R.drawable.music_note_black);
						}
						dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));
						dialog.setCancelable(false);
						profile.setOnClickListener(new View.OnClickListener() {
							@Override public void onClick(View v) {
								if (true) {
									intent.setClass(getApplicationContext(), ProfileActivity.class);
									intent.putExtra("uid", upload_list.get((int)_position).get("uid").toString());
									startActivity(intent);
								}
								dialog.dismiss();}});
						delete.setOnClickListener(new View.OnClickListener() {
							@Override public void onClick(View v) {
								if (true) {
									final AlertDialog.Builder alert = new AlertDialog.Builder(SteamerActivity.this);
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
									cancel.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
									apply.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/opensansregular.ttf"), 0);
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
										title.setText("Delete Song");
										message.setText("Are you sure to delete ".concat(upload_list.get((int)_position).get("name").toString().concat("?")));
										apply.setText("DELETE");
										cancel.setText("CANCEL");
									}
									if (isDarkMode) {
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
												_firebase_storage.getReferenceFromUrl(upload_list.get((int)_position).get("url").toString()).delete().addOnSuccessListener(_song_upload_delete_success_listener).addOnFailureListener(_song_upload_failure_listener);
												if (upload_list.get((int)_position).containsKey("img")) {
													_firebase_storage.getReferenceFromUrl(upload_list.get((int)_position).get("img").toString()).delete().addOnSuccessListener(_image_upload_delete_success_listener).addOnFailureListener(_image_upload_failure_listener);
												}
												upload_text.child(childkey.get((int)(_position))).removeValue();
												_customSnackBar("Delete success!", 1);
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
						comment.setOnClickListener(new View.OnClickListener() {
							@Override public void onClick(View v) {
								if (true) {
									intent.setClass(getApplicationContext(), MessageActivity.class);
									intent.putExtra("key", childkey.get((int)(_position)));
									startActivity(intent);
								}
								dialog.dismiss();}});
						playlist.setOnClickListener(new View.OnClickListener() {
							@Override public void onClick(View v) {
								if (true) {
									d.setCancelable(false);
									d.setAdapter(new ArrayAdapter(SteamerActivity.this, android.R.layout.simple_list_item_1, playlistString), new DialogInterface.OnClickListener() {
										@Override public void onClick(DialogInterface dia, int _pos_dialog2) {
											_ResetDialog();
											_addToPlaylist(_pos_dialog2, _position);
										}
									});
									d.show();
								}
								dialog.dismiss();}});
						cancel.setOnClickListener(new View.OnClickListener() {
							@Override public void onClick(View v) {
								if (true) {
									
								}
								dialog.dismiss();}});
						dialog.show();
					}
					else {
						_DeleteSongInPlaylist(_position);
					}
					return true;
				}
			});
			if (currentlyPlaying.equals(childkey.get((int)(_position)))) {
				if (mp != null) {
					if (mp.isPlaying()) {
						text_song.setTextColor(0xFF2196F3);
					}
					else {
						text_song.setTextColor(0xFF2196F3);
					}
				}
				else {
					if (data.getString("mode", "").equals("light")) {
						text_song.setTextColor(0xFF212121);
					}
					else {
						text_song.setTextColor(0xFF838383);
					}
				}
			}
			else {
				if (data.getString("mode", "").equals("light")) {
					text_song.setTextColor(0xFF212121);
				}
				else {
					text_song.setTextColor(0xFF838383);
				}
			}
			
			return _view;
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