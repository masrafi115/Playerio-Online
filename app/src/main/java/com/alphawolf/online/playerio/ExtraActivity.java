package com.alphawolf.online.playerio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import android.widget.ScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.SharedPreferences;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View;
import android.graphics.Typeface;
import com.facebook.ads.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class ExtraActivity extends  AppCompatActivity  { 
	
	private Timer _timer = new Timer();
	
	private FloatingActionButton _fab;
	private String title = "";
	private String content = "";
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private ScrollView vscroll1;
	private LinearLayout linear4;
	private ImageView image_back;
	private TextView text_title;
	private TextView text_msg;
	private LinearLayout linear6;
	private TextView music;
	private TextView garage;
	
	private SharedPreferences data;
	private TimerTask timer;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.extra);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_fab = (FloatingActionButton) findViewById(R.id._fab);
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		image_back = (ImageView) findViewById(R.id.image_back);
		text_title = (TextView) findViewById(R.id.text_title);
		text_msg = (TextView) findViewById(R.id.text_msg);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		music = (TextView) findViewById(R.id.music);
		garage = (TextView) findViewById(R.id.garage);
		data = getSharedPreferences("musicgarage", Activity.MODE_PRIVATE);
		
		image_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
		_SetStatusBarColor("#FFFFFF");
		linear1.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  window.setNavigationBarColor(Color.parseColor("#FFFFFF"));
		_Animation();
		_setBackground(image_back, 200, 0, "#FFFFFF", true);
		text_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 0);
		text_msg.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansregular.ttf"), 0);
		music.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensanslight.ttf"), 0);
		garage.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/opensansbold.ttf"), 1);
		_removeScollBar(vscroll1);
		if (data.getString("info", "").equals("pp")) {
			title = "PRIVACY POLICY";
			content = "AlphaWolf Inc. built the Playerio app as a Freemium app. This SERVICE is provided by AlphaWolf at no cost and is intended for use as is.\nThis page is used to inform visitors regarding my policies with the collection, use, and disclosure of Personal Information if anyone decided to use my Service.\n\nIf you choose to use my Service, then you agree to the collection and use of information in relation to this policy. The Personal Information that I collect is used for providing and improving the Service. I will not use or share your information with anyone except as described in this Privacy Policy.\nThe terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, which is accessible at Playerio unless otherwise defined in this Privacy Policy.\n\nInformation Collection and Use\nFor a better experience, while using our Service, I may require you to provide us with certain personally identifiable information, including but not limited to Username, Email Address, User Pic, Country Information. The information that I request will be retained on your device and is not collected by me in any way.\n\nThe app does use third party services that may collect information used to identify you.\nLink to privacy policy of third party service providers used by the app\n\n• Google Play Services\n\n• Google Analytics for Firebase\n\nLog Data\n\nI want to inform you that whenever you use my Service, in a case of an error in the app I collect data and information (through third party products) on your phone called Log Data. This Log Data may include information such as your device Internet Protocol (“IP”) address, device name, operating system version, the configuration of the app when utilizing my Service, the time and date of your use of the Service, and other statistics.\n\nCookies\n\nCookies are files with a small amount of data that are commonly used as anonymous unique identifiers. These are sent to your browser from the websites that you visit and are stored on your device's internal memory.\n\nThis Service does not use these “cookies” explicitly. However, the app may use third party code and libraries that use “cookies” to collect information and improve their services. You have the option to either accept or refuse these cookies and know when a cookie is being sent to your device. If you choose to refuse our cookies, you may not be able to use some portions of this Service.\n\nService Providers\n\nI may employ third-party companies and individuals due to the following reasons:\n\n• To facilitate our Service;\n\n• To provide the Service on our behalf;\n\n• To perform Service-related services; or\n\n• To assist us in analyzing how our Service is used.\n\nI want to inform users of this Service that these third parties have access to your Personal Information. The reason is to perform the tasks assigned to them on our behalf. However, they are obligated not to disclose or use the information for any other purpose.\n\nSecurity\n\nI value your trust in providing us your Personal Information, thus we are striving to use commercially acceptable means of protecting it. But remember that no method of transmission over the internet, or method of electronic storage is 100% secure and reliable, and I cannot guarantee its absolute security.\nLinks to Other Sites\nThis Service may contain links to other sites. If you click on a third-party link, you will be directed to that site. Note that these external sites are not operated by me. Therefore, I strongly advise you to review the Privacy Policy of these websites. I have no control over and assume no responsibility for the content, privacy policies, or practices of any third-party sites or services.\n\nChildren’s Privacy\n\nThese Services do not address anyone under the age of 13. I do not knowingly collect personally identifiable information from children under 13. In the case I discover that a child under 13 has provided me with personal information, I immediately delete this from our servers. If you are a parent or guardian and you are aware that your child has provided us with personal information, please contact me so that I will be able to do necessary actions.\n\nThis policy is effective as of 2020-11-10\n\nContact Us\n\nIf you have any questions or suggestions about my Privacy Policy, do not hesitate to contact me at playerioofficial@gmail.com\n";
			text_title.setText(title);
			text_msg.setText(content);
		}
		else {
			if (data.getString("info", "").equals("tac")) {
				title = "TERMS & CONDITIONS";
				content = "By downloading or using the app, these terms will automatically apply to you – you should make sure therefore that you read them carefully before using the app. You’re not allowed to copy, or modify the app, any part of the app, or our trademarks in any way. You’re not allowed to attempt to extract the source code of the app, and you also shouldn’t try to translate the app into other languages, or make derivative versions. The app itself, and all the trade marks, copyright, database rights and other intellectual property rights related to it, still belong to AlphaWolf Inc.\n\nAlphaWolf Inc is committed to ensuring that the app is as useful and efficient as possible. For that reason, we reserve the right to make changes to the app or to charge for its services, at any time and for any reason. We will never charge you for the app or its services without making it very clear to you exactly what you’re paying for.\n\nThe AlphaWolf Inc app stores and processes personal data that you have provided to us, in order to provide my Service. It’s your responsibility to keep your phone and access to the app secure. We therefore recommend that you do not jailbreak or root your phone, which is the process of removing software restrictions and limitations imposed by the official operating system of your device. It could make your phone vulnerable to malware/viruses/malicious programs, compromise your phone’s security features and it could mean that the AlphaWolf Inc app won’t work properly or at all.\n\nThe app does use third party services that declare their own Terms and Conditions.\nLink to Terms and Conditions of third party service providers used by the app\n\n• Google Play Services\n\n• Google Analytics for Firebase\n\nYou should be aware that there are certain things that AlphaWolf Inc will not take responsibility for. Certain functions of the app will require the app to have an active internet connection. The connection can be Wi-Fi, or provided by your mobile network provider, but AlphaWolf Inc cannot take responsibility for the app not working at full functionality if you don’t have access to Wi-Fi, and you don’t have any of your data allowance left.\n\nIf you’re using the app outside of an area with Wi-Fi, you should remember that your terms of the agreement with your mobile network provider will still apply. As a result, you may be charged by your mobile provider for the cost of data for the duration of the connection while accessing the app, or other third party charges. In using the app, you’re accepting responsibility for any such charges, including roaming data charges if you use the app outside of your home territory (i.e. region or country) without turning off data roaming. If you are not the bill payer for the device on which you’re using the app, please be aware that we assume that you have received permission from the bill payer for using the app.\n\nAlong the same lines, AlphaWolf Inc cannot always take responsibility for the way you use the app i.e. You need to make sure that your device stays charged – if it runs out of battery and you can’t turn it on to avail the Service, AlphaWolf Inc cannot accept responsibility.\nWith respect to AlphaWolf Inc’s responsibility for your use of the app, when you’re using the app, it’s important to bear in mind that although we endeavour to ensure that it is updated and correct at all times, we do rely on third parties to provide information to us so that we can make it available to you. AlphaWolf Inc accepts no liability for any loss, direct or indirect, you experience as a result of relying wholly on this functionality of the app.\n\nAt some point, we may wish to update the app. The app is currently available on Android – the requirements for system(and for any additional systems we decide to extend the availability of the app to) may change, and you’ll need to download the updates if you want to keep using the app. AlphaWolf Inc does not promise that it will always update the app so that it is relevant to you and/or works with the Android version that you have installed on your device. However, you promise to always accept updates to the application when offered to you, We may also wish to stop providing the app, and may terminate use of it at any time without giving notice of termination to you. Unless we tell you otherwise, upon any termination, (a) the rights and licenses granted to you in these terms will end; (b) you must stop using the app, and (if needed) delete it from your device.\n\nChanges to This Terms and Conditions\n\nI may update our Terms and Conditions from time to time. Thus, you are advised to review this page periodically for any changes. I will notify you of any changes by posting the new Terms and Conditions on this page.\n\nThese terms and conditions are effective as of 2020-11-10\n\nContact Us\n\nIf you have any questions or suggestions about my Terms and Conditions, do not hesitate to contact me at playerioofficial@gmail.com";
				text_title.setText(title);
				text_msg.setText(content);
			}
			else {
				if (data.getString("info", "").equals("copyright")) {
					title = "COPYRIGHT POLICY";
					content = "Hi, and thank you for visiting Playerio’s copyright policy page. Playerio respects intellectual property rights and expects its users to do the same. If you are a copyright holder, or its agent, and you believe that any of the copyrighted material which is directly available via the Playerio Service infringes your copyrighted work, please let us know.\n\nPlease use this web form to submit a notice of alleged copyright infringement. Alternatively a notice of alleged copyright infringement may be sent to Playerio's designated copyright email at the following email address:\n\nCopyright-playeriosupport@gmail.com\n\nPlease include as much detail as possible to allow us to identify the facts or circumstances, including, where possible:\n\n1. A physical or electronic signature of the owner (or person authorised to act on behalf of the owner) of the copyright that is allegedly infringed;\n\n2. Specific identification of each copyrighted work claimed to have been infringed;\n\n3. A description of where the material believed to be infringing is located on Playerio Service or the Playerio Websites (please be as detailed as possible and provide some screenshots and anything you think it's will use like as a proof);\n\n4. Contact information for the complaining party, such as a complete name, address, telephone number, and email address;\n\n5. A statement that the complaining party has a good faith belief that use of the work(s) in the manner complained of is not authorised by the copyright owner, its agent, or the law; and\n\n6. A statement that the information in the notification is accurate, and that the complaining party is the owner of the right that is allegedly infringed, or agent for the owner.\n\nWe should also let you know that Playerio has a policy to terminate in appropriate circumstances the accounts of subscribers who are repeat infringers.\n\nLast updated: November 10, 2020\n\nCopyright © 2020 Playerio. All rights reserved.";
					text_title.setText(title);
					text_msg.setText(content);
				}
			}
		}
		vscroll1.setOnTouchListener(new View.OnTouchListener() {
			Boolean scroll;
			@Override public boolean onTouch(View v, MotionEvent event) {
				scroll = false;
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
					x = event.getY();
					scroll = false;
					case MotionEvent.ACTION_UP:
					
					y = event.getY();
					if (((x - y) < -15)) {
						
						_FabAnimator("translationX", 500, 300);
					} 
					if (((y - x) < -15)) {
						
						_FabAnimator("translationX", 0, 300);
					}
					
					scroll = false;
				}
				return scroll;
			}
		});
	}
	private double x = 0;
	private double y = 0;
	{
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _FabAnimator (final String _propertyName, final double _value, final double _duration) {
		ObjectAnimator anim = new ObjectAnimator();
		anim.setTarget(_fab);
		anim.setPropertyName(_propertyName);
		anim.setFloatValues((float)_value);
		anim.setDuration((long)_duration);
		anim.start();
	}
	
	
	public void _Animator (final View _view, final String _propertyName, final double _value, final double _duration) {
		ObjectAnimator anim = new ObjectAnimator();
		anim.setTarget(_view);
		anim.setPropertyName(_propertyName);
		anim.setFloatValues((float)_value);
		anim.setDuration((long)_duration);
		anim.start();
	}
	
	
	public void _Animation () {
		_FabAnimator("translationX", 500, 200);
		_Animator(linear6, "translationX", -250, 200);
		timer = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						_Animator(linear6, "translationX", 0, 300);
					}
				});
			}
		};
		_timer.schedule(timer, (int)(850));
	}
	
	
	public void _SetStatusBarColor (final String _color) {
		Window w = this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(Color.parseColor(_color));
	}
	
	
	public void _removeScollBar (final View _view) {
		_view.setVerticalScrollBarEnabled(false); _view.setHorizontalScrollBarEnabled(false);
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