package com.sudokuhijaiyah;

import com.sudokuhijaiyah.R;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {

	Button btSound, btMusic, btExit, btHelp;
	static boolean statusMusic = true;// true = Music On, false = Music Off
	static boolean statusSound = true;// true = Sound On, false = Sound Off
	static MediaPlayer klik = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		setContentView(R.layout.activity_main);
		
		klik = MediaPlayer.create(this, R.raw.beep_one);

		View btMain = findViewById(R.id.btPlay);
		btMain.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(statusSound){klik.start();}
				pilihLevel(statusMusic, statusSound);
			}
		});

		btSound = (Button) findViewById(R.id.btSound);
		btSound.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(statusSound){klik.start();}
				pilihSound();
			}
		});

		btMusic = (Button) findViewById(R.id.btMusic);
		btMusic.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(statusSound){klik.start();}
				pilihMusic();
			}
		});
		
		if (statusMusic == true) {
//			btMusic.setText("Music On");
			btMusic.setBackgroundResource(R.drawable.btmusic_on);
		} else if (statusMusic == false) {
//			btMusic.setText("Music Off");
			btMusic.setBackgroundResource(R.drawable.btmusic_off);
		}
		if (statusSound == true) {
//			btSound.setText("Sound On");
			btSound.setBackgroundResource(R.drawable.btsound_on);
		} else if (statusSound == false) {
//			btSound.setText("Sound Off");
			btSound.setBackgroundResource(R.drawable.btsound_off);
		}
		
		btExit = (Button) findViewById(R.id.btExit);
		btExit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if(statusSound){klik.start();}
				finish();
				System.exit(0);
			}
		});
		
		btHelp = (Button) findViewById(R.id.btHelp);
		btHelp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(statusSound){klik.start();}
				gotoHelp();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	public void pilihLevel(boolean music, boolean sound) {
		Intent i = new Intent(this, pilihLevel.class);
		boolean audio[] = { music, sound };
		i.putExtra(pilihLevel.AUDIO_SERVICE, audio);
		startActivity(i);
	}

	public void pilihSound() {
		if (statusSound == true) {
//			btSound.setText("Sound Off");
			btSound.setBackgroundResource(R.drawable.btsound_off);
			statusSound = false;
		} else if (statusSound == false) {
//			btSound.setText("Sound On");
			btSound.setBackgroundResource(R.drawable.btsound_on);
			statusSound = true;
		}
	}

	public void pilihMusic() {
		if (statusMusic == true) {
//			btMusic.setText("Music Off");
			btMusic.setBackgroundResource(R.drawable.btmusic_off);
			statusMusic = false;
		} else if (statusMusic == false) {
//			btMusic.setText("Music On");
			btMusic.setBackgroundResource(R.drawable.btmusic_on);
			statusMusic = true;
		}
	}
	
	@Override
	public void onBackPressed() {
		System.exit(0);
	}
	
	public void gotoHelp() {
		Intent i = new Intent(this, help.class);
		startActivity(i);
	}
}
