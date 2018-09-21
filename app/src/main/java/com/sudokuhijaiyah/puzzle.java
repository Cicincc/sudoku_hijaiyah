package com.sudokuhijaiyah;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Date;

import aco.tools.Keypad;
import aco.method.AntColonyOptimization;

public class puzzle extends Activity {
	public static final String HURUF_LEVEL = "org.sudoku.huruflevel";
	puzzleKanvas kanvas;
	public static int puzzleSoal[][];
	Bitmap gambarku[] = null;
	MediaPlayer suaraku[] = null;
	int[] nilaiGambar = null;
	int[] nilaiSuara = null;
	int[] gambarKey = new int[9];
	static AntColonyOptimization mesin;
	// DEKLARASI PROSES
	public static ProgressDialog progres = null;
	public static ProgressDialog progresSolving = null;
	Date dt = new Date();
	static long waktuStart;
	boolean audio[] = new boolean[2];
	MediaPlayer music = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// progres = ProgressDialog.show(this, "Backtrac
		// k Generating Puzzle",
		// "Tunggu Sebentar...");
		// waktuStart = System.currentTimeMillis();

		mesin = new AntColonyOptimization();
		puzzleSoal = new int[9][9];
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// AUDIO = {MUSIC,SOUND}
		audio = getIntent().getBooleanArrayExtra(AUDIO_SERVICE);
		music = MediaPlayer.create(this, R.raw.music);
		int huruf[] = getIntent().getIntArrayExtra(HURUF_LEVEL);
		int pil[] = new int[9];
		for (int i = 0; i < 9; i++) {
			pil[i] = huruf[i];
		}
		int level = huruf[9];
		setpuzzleSoal(mesin.getPuzzle(level));
		if (audio[1]) {
			kanvas = new puzzleKanvas(this, this.puzzleSoal, getGambar(pil),
					getSuara(pil));
		}else{
			kanvas = new puzzleKanvas(this, this.puzzleSoal, getGambar(pil));
		}
		kanvas.setFocusable(true);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		if(audio[0]){
			music.setLooping(true);
			music.start();
		}
		setContentView(kanvas);
		progres.dismiss();
	}

	private Bitmap[] getGambar(int[] pilihan) {
		setNilaiGambar();
		gambarku = new Bitmap[9];
		for (int i = 0; i < pilihan.length; i++) {
			gambarku[i] = BitmapFactory.decodeResource(getResources(),
					nilaiGambar[pilihan[i]]);
			gambarKey[i] = nilaiGambar[pilihan[i]];
		}
		return gambarku;
	}

	private MediaPlayer[] getSuara(int[] pilihan) {
		setNilaiSuara();
		suaraku = new MediaPlayer[9];
		for (int i = 0; i < pilihan.length; i++) {
			suaraku[i] = MediaPlayer.create(this, nilaiSuara[pilihan[i]]);
		}
		System.out.println("LENGTH PILIHAN = "+pilihan.length);
		return suaraku;
	}

	private void setNilaiGambar() {
		nilaiGambar = new int[30];
		nilaiGambar[0] = R.drawable.alif;
		nilaiGambar[1] = R.drawable.ba;
		nilaiGambar[2] = R.drawable.ta;
		nilaiGambar[3] = R.drawable.tsa;
		nilaiGambar[4] = R.drawable.jim;
		nilaiGambar[5] = R.drawable.ha;
		nilaiGambar[6] = R.drawable.kha;
		nilaiGambar[7] = R.drawable.dal;
		nilaiGambar[8] = R.drawable.dzal;
		nilaiGambar[9] = R.drawable.ra;
		nilaiGambar[10] = R.drawable.zai;
		nilaiGambar[11] = R.drawable.sin;
		nilaiGambar[12] = R.drawable.syin;
		nilaiGambar[13] = R.drawable.shad;
		nilaiGambar[14] = R.drawable.dhad;
		nilaiGambar[15] = R.drawable.tha;
		nilaiGambar[16] = R.drawable.zha;
		nilaiGambar[17] = R.drawable.ain;
		nilaiGambar[18] = R.drawable.ghain;
		nilaiGambar[19] = R.drawable.fa;
		nilaiGambar[20] = R.drawable.qof;
		nilaiGambar[21] = R.drawable.kaf;
		nilaiGambar[22] = R.drawable.lam;
		nilaiGambar[23] = R.drawable.mim;
		nilaiGambar[24] = R.drawable.nun;
		nilaiGambar[25] = R.drawable.wau;
		nilaiGambar[26] = R.drawable.haa;
		nilaiGambar[27] = R.drawable.lamalif;
		nilaiGambar[28] = R.drawable.hamzah;
		nilaiGambar[29] = R.drawable.ya;
	}

	private void setNilaiSuara() {
		nilaiSuara = new int[30];
		nilaiSuara[0] = R.raw.alif;
		nilaiSuara[1] = R.raw.ba;
		nilaiSuara[2] = R.raw.ta;
		nilaiSuara[3] = R.raw.tsa;
		nilaiSuara[4] = R.raw.jim;
		nilaiSuara[5] = R.raw.cha;
		nilaiSuara[6] = R.raw.kho;
		nilaiSuara[7] = R.raw.dal;
		nilaiSuara[8] = R.raw.dzal;
		nilaiSuara[9] = R.raw.ro;
		nilaiSuara[10] = R.raw.zai;
		nilaiSuara[11] = R.raw.sin;
		nilaiSuara[12] = R.raw.syin;
		nilaiSuara[13] = R.raw.shad;
		nilaiSuara[14] = R.raw.dlod;
		nilaiSuara[15] = R.raw.tha;
		nilaiSuara[16] = R.raw.dho;
		nilaiSuara[17] = R.raw.ain;
		nilaiSuara[18] = R.raw.ghain;
		nilaiSuara[19] = R.raw.fa;
		nilaiSuara[20] = R.raw.qof;
		nilaiSuara[21] = R.raw.kaf;
		nilaiSuara[22] = R.raw.lam;
		nilaiSuara[23] = R.raw.mim;
		nilaiSuara[24] = R.raw.nun;
		nilaiSuara[25] = R.raw.wau;
		nilaiSuara[26] = R.raw.ha;
		nilaiSuara[27] = R.raw.lamalif;
		nilaiSuara[28] = R.raw.hamzah;
		nilaiSuara[29] = R.raw.ya;
	}

	public void tampilkanKeypad(int baris, int kolom) {
		try{
		int tile = puzzleSoal[baris][kolom];
		if (tile == 0) {
			Dialog v = new Keypad(this, kanvas, gambarKey);
			v.show();

		} else {
//			String pesan = "Afwan, La Yajuzz \nMaaf, Tidak diperkenankan";
//			pesanCepat(pesan);
			System.out.println("audio[1] = "+audio[1]);
			System.out.println("suaraku[tile - 1] = suaraku["+tile+" - 1] = "+suaraku[tile - 1]);
			if(audio[1]){suaraku[tile - 1].start();}
		}}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void keluar() {
		String pesan = "Afwan, La Yajuzz\nMaaf, Tidak diperkenankan\n"
				+ "Silahkan tekan tombol Menu pada HP Anda!";
		tampilkanPesan(pesan);
	}

	//control loop {pengatur acak}
	public static void setpuzzleSoal(int Puzzle[][]) {
		try {
			for (int i = 0; i < Puzzle.length; i++) {
				for (int j = 0; j < Puzzle[0].length; j++) {
					puzzleSoal[i][j] = Puzzle[i][j];
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tampilkanPesan(String pesan) {
		Toast toast = Toast.makeText(this, pesan, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	public void pesanCepat(String pesan) {
		Toast toast = Toast.makeText(this, pesan, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	public void AntAntColonyOptimizationSolving() {
		waktuStart = System.currentTimeMillis();
		kanvas.SelesaikanPuzzleUser(mesin
				.getSolutionPuzzle(puzzleKanvas.puzzleUser),waktuStart);
	}

	private void BuatMenu(Menu menu) {
		menu.setQwertyMode(true);
		menu.add(0, 0, 0, "Selesaikan");
		menu.add(0, 1, 1, "Keluar");
	}

	private boolean MenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			progres = ProgressDialog.show(this, "ACO Solving Puzzle",
					"Tunggu Sebentar...", true, false);
			AntAntColonyOptimizationSolving();
			return true;
		case 1:

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Apakah Anda akan keluar dari Puzzle?")
					.setCancelable(false)
					.setPositiveButton("Ya",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
//									if(music.isPlaying()){
//										if(music.isLooping()){
//											music.setLooping(false);
//										}
										music.stop();
//									}
										gotoHome();
								}
							})
					.setNegativeButton("Tidak",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			AlertDialog alert = builder.create();
			alert.show();
			return true;
		}
		return false;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		BuatMenu(menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		return MenuChoice(item);
	}

	@Override
	public void onBackPressed() {
		// super.onBackPressed();
		keluar();
	}
	
	
	public void gotoHome() {
		Intent i = new Intent(this, MainActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
		finish();
	}
}
