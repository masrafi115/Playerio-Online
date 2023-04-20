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
import android.widget.ScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.OnProgressListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Continuation;
import java.io.File;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View;
import android.graphics.Typeface;
import com.facebook.ads.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class UploadActivity extends  AppCompatActivity  { 
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private String path = "";
	private String getMediaTitle = "";
	private String getMediaArtist = "";
	private String getMediaAlbum = "";
	private String img_path = "";
	private String getMediaDuration = "";
	private String getMediaSize = "";
	private String name = "";
	private boolean isUploading = false;
	private String song_url = "";
	private HashMap<String, Object> upload_map = new HashMap<>();
	private String ImageRandomName = "";
	
	private ArrayList<String> ListStringName = new ArrayList<>();
	
	private LinearLayout linear1;
	private ScrollView vscroll2;
	private ImageView image_back;
	private TextView textview1;
	private LinearLayout linear3;
	private LinearLayout linear_upload;
	private LinearLayout linear_uploading;
	private LinearLayout linear_dialog;
	private TextView title;
	private TextView message;
	private LinearLayout linear_;
	private EditText edittext_name;
	private Button button_upload;
	private LinearLayout linear6;
	private TextView textview_path;
	private LinearLayout cardview;
	private ImageView imageview1;
	private ImageView imageview2;
	private LinearLayout linear7;
	private ImageView image_cancel;
	private LinearLayout linear10;
	private LinearLayout linear8;
	private TextView text_title;
	private TextView text_msg;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private TextView text_cancel;
	private TextView text_percentage;
	private ProgressBar progressbar1;
	private ImageView image_dialog;
	private LinearLayout linear14;
	private ImageView imageview3;
	private LinearLayout linear15;
	private TextView text_dialog_title;
	private TextView text_dialog_msg;
	private LinearLayout linear16;
	private TextView dialog_cancel;
	
	private SharedPreferences data;
	private Intent intent = new Intent();
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
	private DatabaseReference upload_text = _firebase.getReference("upload/text");
	private ChildEventListener _upload_text_child_listener;
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
	private Calendar cal = Calendar.getInstance();
	private TimerTask timer;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.upload);
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
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		vscroll2 = (ScrollView) findViewById(R.id.vscroll2);
		image_back = (ImageView) findViewById(R.id.image_back);
		textview1 = (TextView) findViewById(R.id.textview1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear_upload = (LinearLayout) findViewById(R.id.linear_upload);
		linear_uploading = (LinearLayout) findViewById(R.id.linear_uploading);
		linear_dialog = (LinearLayout) findViewById(R.id.linear_dialog);
		title = (TextView) findViewById(R.id.title);
		message = (TextView) findViewById(R.id.message);
		linear_ = (LinearLayout) findViewById(R.id.linear_);
		edittext_name = (EditText) findViewById(R.id.edittext_name);
		button_upload = (Button) findViewById(R.id.button_upload);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		textview_path = (TextView) findViewById(R.id.textview_path);
		cardview = (LinearLayout) findViewById(R.id.cardview);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		image_cancel = (ImageView) findViewById(R.id.image_cancel);
		linear10 = (LinearLayout) findViewById(R.id.linear10);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		text_title = (TextView) findViewById(R.id.text_title);
		text_msg = (TextView) findViewById(R.id.text_msg);
		linear11 = (LinearLayout) findViewById(R.id.linear11);
		linear12 = (LinearLayout) findViewById(R.id.linear12);
		text_cancel = (TextView) findViewById(R.id.text_cancel);
		text_percentage = (TextView) findViewById(R.id.text_percentage);
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		image_dialog = (ImageView) findViewById(R.id.image_dialog);
		linear14 = (LinearLayout) findViewById(R.id.linear14);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		linear15 = (LinearLayout) findViewById(R.id.linear15);
		text_dialog_title = (TextView) findViewById(R.id.text_dialog_title);
		text_dialog_msg = (TextView) findViewById(R.id.text_dialog_msg);
		linear16 = (LinearLayout) findViewById(R.id.linear16);
		dialog_cancel = (TextView) findViewById(R.id.dialog_cancel);
		data = getSharedPreferences("musicgarage", Activity.MODE_PRIVATE);
		Auth = FirebaseAuth.getInstance();
		
		image_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		button_upload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (16 > (FileUtil.getFileLength(path) / 1048576)) {
					if (edittext_name.getText().toString().trim().length() > 0) {
						if (ListStringName.contains(edittext_name.getText().toString().trim())) {
							_customSnackBar("Song Already Exists In Music Garage `~`", 2);
						}
						else {
							name = edittext_name.getText().toString().trim();
							final String _reservedFilename = name.replace("/", " ");
							song_upload.child(_reservedFilename.concat(Uri.parse(path).getLastPathSegment().replace(Uri.parse(path).getLastPathSegment().substring((int)(0), (int)(Uri.parse(path).getLastPathSegment().lastIndexOf("."))), ""))).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_song_upload_failure_listener).addOnProgressListener(_song_upload_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
								@Override
								public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
									return song_upload.child(_reservedFilename.concat(Uri.parse(path).getLastPathSegment().replace(Uri.parse(path).getLastPathSegment().substring((int)(0), (int)(Uri.parse(path).getLastPathSegment().lastIndexOf("."))), ""))).getDownloadUrl();
								}}).addOnCompleteListener(_song_upload_upload_success_listener);
							_TransitionManager(linear3, 300);
							linear_upload.setVisibility(View.GONE);
							linear_uploading.setVisibility(View.VISIBLE);
							isUploading = true;
						}
					}
					else {
						_customSnackBar("Please Enter Song Name '–'", 2);
					}
				}
				else {
					_customSnackBar("You can't upload song file above 15MB °–°", 2);
				}
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), FilepickerActivity.class);
				intent.putExtra("fileType", "audio/*");
				startActivity(intent);
			}
		});
		
		image_cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		text_cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				timer.cancel();
				finish();
			}
		});
		
		dialog_cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		_song_upload_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				if (img_path.equals("")) {
					progressbar1.setProgress((int)_progressValue);
					text_percentage.setText(String.valueOf((long)(_progressValue)).concat("%"));
				}
				else {
					progressbar1.setProgress((int)_progressValue / 2);
					text_percentage.setText(String.valueOf((long)(_progressValue / 2)).concat("%"));
				}
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
				if (img_path.equals("")) {
					upload_map = new HashMap<>();
					upload_map.put("url", _downloadUrl);
					upload_map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					upload_map.put("name", name);
					upload_map.put("view", "0");
					cal = Calendar.getInstance();
					upload_map.put("time", String.valueOf((long)(cal.getTimeInMillis())));
					upload_map.put("Media Duration", getMediaDuration);
					upload_map.put("Media Size", getMediaSize);
					upload_map.put("Media Artist", getMediaArtist);
					upload_map.put("Media Album", getMediaAlbum);
					upload_map.put("category", "other");
					upload_text.push().updateChildren(upload_map);
					SketchwareUtil.showMessage(getApplicationContext(), "Song Uploaded Successfully!");
					_TransitionManager(linear3, 300);
					_setBackground(linear_dialog, "#212121", "#1DE9B6", 20, 10, "#1DE9B6");
					_MusicGarageUI(dialog_cancel, 10, 0, "#65E0E0E0", "#FFFFFF", 0, true);
					_MusicGarageUI(imageview3, 50, 0, "#65000000", "#FFFFFF", 0, true);
					_MusicGarageUI(image_back, 50, 0, "#212121", "#FFFFFF", 0, true);
					_setBackground(linear_, "#353535", "#353535", 75, 0, "#212121");
					linear_uploading.setVisibility(View.GONE);
					linear_dialog.setVisibility(View.VISIBLE);
				}
				else {
					song_url = _downloadUrl;
					ImageRandomName = "mg_picture".concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(100000), (int)(999999)))).concat(".jpg"));
					image_upload.child(ImageRandomName).putFile(Uri.fromFile(new File(img_path))).addOnFailureListener(_image_upload_failure_listener).addOnProgressListener(_image_upload_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
						@Override
						public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
							return image_upload.child(ImageRandomName).getDownloadUrl();
						}}).addOnCompleteListener(_image_upload_upload_success_listener);
				}
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
				_TransitionManager(linear1, 300);
				linear_uploading.setVisibility(View.GONE);
				linear_dialog.setVisibility(View.VISIBLE);
				_setBackground(linear_dialog, "#212121", "#F44336", 20, 10, "#1DE9B6");
				_MusicGarageUI(dialog_cancel, 10, 0, "#65E0E0E0", "#FFFFFF", 0, true);
				_MusicGarageUI(imageview3, 50, 0, "#65000000", "#FFFFFF", 0, true);
				_MusicGarageUI(image_back, 50, 0, "#212121", "#FFFFFF", 0, true);
				image_dialog.setImageResource(R.drawable.icon_exit_white);
				text_dialog_title.setText("Upload Failed!");
				text_dialog_msg.setText(_message);
				dialog_cancel.setText("Retry Again");
			}
		};
		
		_image_upload_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				progressbar1.setProgress((int)(int)(50 + (_progressValue / 2)));
				text_percentage.setText(String.valueOf((long)((int)(50 + (_progressValue / 2)))).concat("%"));
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
				upload_map = new HashMap<>();
				upload_map.put("url", song_url);
				upload_map.put("img", _downloadUrl);
				upload_map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				upload_map.put("name", name);
				upload_map.put("view", "0");
				cal = Calendar.getInstance();
				upload_map.put("time", String.valueOf((long)(cal.getTimeInMillis())));
				upload_map.put("Media Duration", getMediaDuration);
				upload_map.put("Media Size", getMediaSize);
				upload_map.put("Media Artist", getMediaArtist);
				upload_map.put("Media Album", getMediaAlbum);
				upload_map.put("category", "other");
				upload_text.push().updateChildren(upload_map);
				SketchwareUtil.showMessage(getApplicationContext(), "Song Uploaded Successfully!");
				_TransitionManager(linear3, 300);
				_setBackground(linear_dialog, "#212121", "#1DE9B6", 20, 10, "#1DE9B6");
				_MusicGarageUI(dialog_cancel, 10, 0, "#65E0E0E0", "#FFFFFF", 0, true);
				_MusicGarageUI(imageview3, 50, 0, "#65000000", "#FFFFFF", 0, true);
				_MusicGarageUI(image_back, 50, 0, "#212121", "#FFFFFF", 0, true);
				_setBackground(linear_, "#353535", "#353535", 75, 0, "#212121");
				linear_uploading.setVisibility(View.GONE);
				linear_dialog.setVisibility(View.VISIBLE);
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
				SketchwareUtil.showMessage(getApplicationContext(), _message);
			}
		};
		
		_upload_text_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				ListStringName.add(_childValue.get("name").toString());
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
				if (ListStringName.contains(_childValue.get("name").toString())) {
					ListStringName.remove((int)(ListStringName.indexOf(_childValue.get("name").toString())));
				}
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		upload_text.addChildEventListener(_upload_text_child_listener);
		
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
		_UI_DarkMode(false, data.getString("accentColor", ""));
		_FontsFile();
		linear_uploading.setVisibility(View.GONE);
		linear_dialog.setVisibility(View.GONE);
		if (path.equals("")) {
			edittext_name.setVisibility(View.GONE);
			button_upload.setVisibility(View.GONE);
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
		if (!isUploading) {
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
		if (!data.getString("filePath", "").equals("")) {
			path = data.getString("filePath", "");
			data.edit().remove("filePath").commit();
			if (FileUtil.isExistFile(path)) {
				if (FileUtil.isExistFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/mg_picture.png"))) {
					FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/mg_picture.png"));
				}
				_TransitionManager(linear3, 300);
				edittext_name.setVisibility(View.VISIBLE);
				button_upload.setVisibility(View.VISIBLE);
				edittext_name.setEnabled(true);
				button_upload.setEnabled(true);
				edittext_name.setAlpha((float)(1));
				button_upload.setAlpha((float)(1));
				textview_path.setText(path);
				edittext_name.setText(Uri.parse(path).getLastPathSegment().substring((int)(0), (int)(Uri.parse(path).getLastPathSegment().lastIndexOf("."))).replace("_", " "));
				try {
					img_path = FileUtil.getPackageDataDir(getApplicationContext()).concat("/mg_picture.png");
					MediaMetadataRetriever mmr = new MediaMetadataRetriever(); mmr.setDataSource(path); byte [] data = mmr.getEmbeddedPicture(); Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
					imageview1.clearColorFilter();
					{
						Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
						Canvas canvas = new Canvas(output);
						final int color = 0xff424242;
						final Paint paint = new Paint();
						final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
						final RectF rectF = new RectF(rect);
						final float roundPx = 15;
						paint.setAntiAlias(true);
						canvas.drawARGB(0, 0, 0, 0);
						paint.setColor(color);
						canvas.drawRoundRect(rectF, roundPx, roundPx, paint); 
						paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN)); 
						canvas.drawBitmap(bitmap, rect, rect, paint);
						imageview1.setImageBitmap(output);
					}
					
					java.io.File file = new java.io.File(img_path);
					java.io.FileOutputStream fOut = new java.io.FileOutputStream(file);
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
				} catch(Exception e){
					imageview1.setImageResource(R.drawable.music_note_white);
					img_path = "";
				}
				_mediaInfo(path);
				if (getMediaAlbum==null) {
					getMediaAlbum = "Unknown Album";
				}
				if (getMediaArtist==null) {
					getMediaArtist = "Unknown Artist";
				}
			}
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
	
	
	public void _setBackground (final View _view, final String _color1, final String _color2, final double _radius, final double _shadow, final String _shadowColor) {
		int[] colors = { Color.parseColor(_color1), Color.parseColor(_color2) }; android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TR_BL, colors); gd.setCornerRadius((int)_radius); _view.setBackground(gd);
		_view.setElevation((int)_shadow);
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
	
	
	public void _Elevation (final View _view, final double _number) {
		
		_view.setElevation((int)_number);
	}
	
	
	public void _UI_DarkMode (final boolean _IsDark, final String _accentColor) {
		if (_IsDark) {
			
		}
		else {
			_Elevation(linear1, 16);
			_MusicGarageUI(linear_upload, 20, 10, "#FAFAFA", "#212121", 0, false);
			_MusicGarageUI(button_upload, 20, 0, "#FFFFFF", "#212121", 3, true);
			_MusicGarageUI(text_cancel, 10, 0, "#65E0E0E0", "#65E0E0E0", 0, true);
			_MusicGarageUI(image_cancel, 50, 0, "#212121", "#212121", 0, true);
			_setBackground(linear_uploading, "#212121", "#424242", 20, 10, "#212121");
			_addCardView(cardview, 0, 200, 0, 0, true, "#212121");
			_setBackground(linear_, "#353535", "#353535", 75, 0, "#212121");
			_removeScollBar(vscroll2);
		}
	}
	
	
	public void _FontsFile () {
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		edittext_name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		button_upload.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		textview_path.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_msg.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_cancel.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_percentage.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		text_dialog_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_dialog_msg.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		dialog_cancel.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 1);
		message.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
	}
	
	
	public void _TransitionManager (final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
	}
	
	
	public void _mediaInfo (final String _path) {
		MediaMetadataRetriever mr = new MediaMetadataRetriever();
		mr.setDataSource(_path);
		getMediaTitle = mr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
		getMediaArtist = mr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
		getMediaAlbum = mr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
		MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
		        metaRetriever.setDataSource(_path);
		
		        String out = "";
		        // get mp3 info
		
		        // convert duration to minute:seconds
		        String duration =
		                metaRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
		
		        Log.v("time", duration);
		        long dur = Long.parseLong(duration);
		        String seconds = String.valueOf((dur % 60000) / 1000);
		
		        Log.v("seconds", seconds);
		        String minutes = String.valueOf(dur / 60000);
		        out = minutes + ":" + seconds;
		        if (seconds.length() == 1) {
			
			            getMediaDuration = "0" + minutes + ":0" + seconds;
			        }else {
			            getMediaDuration = "0" + minutes + ":" + seconds;
			        }
		getMediaSize = size(FileUtil.getFileLength(_path));
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
	
	
	public void _Size (final double _Size) {
	}
	
	public String size(double size){
		    String hrSize = "";
		double k = size/1024.0;
		    double m = size/(1024.0*1024.0);
		double g = size/(1024.0*1024*1024);
		
		    DecimalFormat dec = new DecimalFormat("0.00");
		
		if (g > 1) {
			        hrSize = dec.format(g).concat(" GB");
		}
		    else if (m > 1) {
			        hrSize = dec.format(m).concat(" MB");
			    } else if (k > 1) {
			        hrSize = dec.format(k).concat(" KB");
			    }
		else {
			hrSize = dec.format(size/1024).concat(" KB");
		}
		
		    return hrSize;
		
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