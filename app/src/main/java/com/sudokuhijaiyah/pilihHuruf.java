package com.sudokuhijaiyah;

import android.R.color;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class pilihHuruf extends Activity {

	pilihLevel p;

	private int LEVEL = 0, level1 = 0;
	TextView tvJmlPilihHuruf;
	Button tombolMain;
	int jmlPilih;
	public static final String HURUF_LEVEL = "org.sudoku.huruflevel";
	CheckBox chek[] = new CheckBox[30];
	boolean audio[] = new boolean[2];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		setContentView(R.layout.pilihhuruf);
		LEVEL = getIntent().getIntExtra(HURUF_LEVEL, level1);
		// AUDIO = {MUSIC,SOUND}
		audio = getIntent().getBooleanArrayExtra(AUDIO_SERVICE);
		getTampilan();
		setAksi();
	}

	private void getTampilan() {
		chek[0] = (CheckBox) findViewById(R.id.ckPilihHuruf_Alif);
		chek[1] = (CheckBox) findViewById(R.id.ckPilihHuruf_Ba);
		chek[2] = (CheckBox) findViewById(R.id.ckPilihHuruf_Ta);
		chek[3] = (CheckBox) findViewById(R.id.ckPilihHuruf_Tsa);
		chek[4] = (CheckBox) findViewById(R.id.ckPilihHuruf_Jim);
		chek[5] = (CheckBox) findViewById(R.id.ckPilihHuruf_Cha);
		chek[6] = (CheckBox) findViewById(R.id.ckPilihHuruf_Kho);
		chek[7] = (CheckBox) findViewById(R.id.ckPilihHuruf_Dal);
		chek[8] = (CheckBox) findViewById(R.id.ckPilihHuruf_Dzal);
		chek[9] = (CheckBox) findViewById(R.id.ckPilihHuruf_Ro);
		chek[10] = (CheckBox) findViewById(R.id.ckPilihHuruf_Zai);
		chek[11] = (CheckBox) findViewById(R.id.ckPilihHuruf_Sin);
		chek[12] = (CheckBox) findViewById(R.id.ckPilihHuruf_Syin);
		chek[13] = (CheckBox) findViewById(R.id.ckPilihHuruf_Shod);
		chek[14] = (CheckBox) findViewById(R.id.ckPilihHuruf_Dlod);
		chek[15] = (CheckBox) findViewById(R.id.ckPilihHuruf_Tho);
		chek[16] = (CheckBox) findViewById(R.id.ckPilihHuruf_Dho);
		chek[17] = (CheckBox) findViewById(R.id.ckPilihHuruf_Ain);
		chek[18] = (CheckBox) findViewById(R.id.ckPilihHuruf_Ghoin);
		chek[19] = (CheckBox) findViewById(R.id.ckPilihHuruf_Fa);
		chek[20] = (CheckBox) findViewById(R.id.ckPilihHuruf_Qof);
		chek[21] = (CheckBox) findViewById(R.id.ckPilihHuruf_Kaf);
		chek[22] = (CheckBox) findViewById(R.id.ckPilihHuruf_Lam);
		chek[23] = (CheckBox) findViewById(R.id.ckPilihHuruf_Mim);
		chek[24] = (CheckBox) findViewById(R.id.ckPilihHuruf_Nun);
		chek[25] = (CheckBox) findViewById(R.id.ckPilihHuruf_Wawu);
		chek[26] = (CheckBox) findViewById(R.id.ckPilihHuruf_Ha);
		chek[27] = (CheckBox) findViewById(R.id.ckPilihHuruf_LamAlif);
		chek[28] = (CheckBox) findViewById(R.id.ckPilihHuruf_Hamzah);
		chek[29] = (CheckBox) findViewById(R.id.ckPilihHuruf_Ya);
		tvJmlPilihHuruf = (TextView) findViewById(R.id.tvJmlPilihHuruf);
		jmlPilih = 0;
		tombolMain = (Button) findViewById(R.id.btPilihHuruf_Main);
		tombolMain.setEnabled(false);
	}

	private void setAksi() {
		for (int i = 0; i < chek.length; i++) {
			final int indexChek = i;
			chek[indexChek].setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if (chek[indexChek].isChecked()) {
						jmlPilih += 1;
					} else {
						if (jmlPilih > 0)
							jmlPilih -= 1;
					}
					String ket = "Anda Sudah Memilih " + jmlPilih + " Huruf";
					tvJmlPilihHuruf.setText(ket);
					if (jmlPilih == 9)
						tombolMain.setEnabled(true);
					else
						tombolMain.setEnabled(false);
				}
			});
		}

		tombolMain.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				gotoPuzzle();
			}
		});
	}

	private void gotoPuzzle() {
		puzzle.progres = ProgressDialog.show(this,
				"Generating Puzzle", "Tunggu Sebentar...");
		Intent i = new Intent(this, puzzle.class);
		int hurufnya[] = new int[10];
		int k = 0;
		for (int iCek = 0; iCek < chek.length; iCek++) {
			if (chek[iCek].isChecked()) {
				hurufnya[k] = iCek;
				k++;
			}
		}
		hurufnya[9] = LEVEL;
		i.putExtra(puzzle.HURUF_LEVEL, hurufnya);
		i.putExtra(pilihHuruf.AUDIO_SERVICE, audio);
		startActivity(i);
	}
}
