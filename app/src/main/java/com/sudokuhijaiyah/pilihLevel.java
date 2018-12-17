package com.sudokuhijaiyah;

import com.sudokuhijaiyah.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class pilihLevel extends Activity implements OnCheckedChangeListener {

//	public static String MUSIC, SOUND;
	RadioGroup rgLevel;
	int LEVEL = 0;
	public static final int MUDAH = 1;
	public static final int SEDANG = 2;
	public static final int SULIT = 3;
	int huruflevel[] = new int[10];
	boolean audio[] = new boolean[2];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		setContentView(R.layout.pilihlevel);

		// SET DEFAULT HURUF DAN LEVEL
		LEVEL = MUDAH;
		for (int i = 0; i < 9; i++) {
			huruflevel[i] = i;
		}
		huruflevel[9] = LEVEL;

		// AUDIO = {MUSIC,SOUND}
		audio = getIntent().getBooleanArrayExtra(AUDIO_SERVICE);
		rgLevel = (RadioGroup) findViewById(R.id.rgLevel);
		rgLevel.setOnCheckedChangeListener(this);

		View btLangsungMain = findViewById(R.id.btLangsungMain);
		btLangsungMain.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				langsungMain();
			}
		});
		
		View btPilihHuruf = findViewById(R.id.btPilihHuruf);
		btPilihHuruf.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				pilihHuruf(LEVEL);	
			}
		});

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.rbMudah:
			LEVEL = MUDAH;
			for (int i = 0; i < 9; i++) {
				huruflevel[i] = i;
			}
			huruflevel[9] = LEVEL;
			break;
		case R.id.rbSedang:
			LEVEL = SEDANG;
			for (int i = 0; i < 9; i++) {
				huruflevel[i] = i + 9;
			}
			huruflevel[9] = LEVEL;
			break;
		case R.id.rbSulit:
			LEVEL = SULIT;
			for (int i = 0; i < 9; i++) {
				huruflevel[i] = i + 18;
			}
			huruflevel[9] = LEVEL;
			break;
		default:
			LEVEL = MUDAH;
			for (int i = 0; i < 9; i++) {
				huruflevel[i] = i;
			}
			huruflevel[9] = LEVEL;
			break;
		}
	}

	private void langsungMain() {
		puzzle.progres = ProgressDialog.show(this, "Generating Puzzle", "Tunggu Sebentar...",true,false);
		Intent i = new Intent(this, puzzle.class);
		i.putExtra(puzzle.HURUF_LEVEL, huruflevel);
		i.putExtra(puzzle.AUDIO_SERVICE, audio);
		startActivity(i);
	}
	
	private void pilihHuruf(int Level){
		Intent i = new Intent(this,pilihHuruf.class);
		i.putExtra(puzzle.HURUF_LEVEL, Level);
		i.putExtra(pilihHuruf.AUDIO_SERVICE, audio);
		startActivity(i);
	}

}
