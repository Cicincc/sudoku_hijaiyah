package com.sudokuhijaiyah;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

public class puzzleKanvas extends View {

	private long waktuStart=0;
	public static int aSolver = 0;
	private float widthSpot, heightSpot; // Width and Height per Spot
	private int selX;
	private int selY;
	private final Rect selRect = new Rect();
	Bitmap gambarku[] = null;
	MediaPlayer suaraku[] = null;
	puzzle game;
	public static int puzzleUser[][];

	public puzzleKanvas(Context context, int[][] puz, Bitmap[] g) {
		super(context);
		this.game = (puzzle) context;
		this.gambarku = g;
		suaraku = null;
		puzzleUser = new int[9][9];
		setPuzzleUser(puz);

		setFocusable(true);
		setFocusableInTouchMode(true);
	}

	public puzzleKanvas(Context context, int[][] puz, Bitmap[] g,
			MediaPlayer[] s) {
		super(context);
		this.game = (puzzle) context;
		this.gambarku = g;
		this.suaraku = s;
		puzzleUser = new int[9][9];
		setPuzzleUser(puz);

		setFocusable(true);
		setFocusableInTouchMode(true);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		widthSpot = w / 10f;
		heightSpot = h / 10f;
		// getRect(selX, selY, selRect);
		super.onSizeChanged(w, h, oldw, oldh);
	}

	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_DOWN)
			return super.onTouchEvent(event);

		select((int) (event.getX() / widthSpot),
				(int) (event.getY() / heightSpot));

		game.tampilkanKeypad(selX, selY);

		return true;
	}

	// public boolean onKeyDown(int keyCode, KeyEvent event) {
	// switch (keyCode) {
	// case KeyEvent.KEYCODE_DPAD_UP:
	// select(selX, selY - 1);
	// break;
	// case KeyEvent.KEYCODE_DPAD_DOWN:
	// select(selX, selY + 1);
	// break;
	// case KeyEvent.KEYCODE_DPAD_LEFT:
	// select(selX - 1, selY);
	// break;
	// case KeyEvent.KEYCODE_DPAD_RIGHT:
	// select(selX + 1, selY);
	// break;
	// case KeyEvent.KEYCODE_BACK:
	// game.keluar();
	// break;
	//
	// default:
	// return super.onKeyDown(keyCode, event);
	// }
	// return true;
	// }

	private void select(int x, int y) {
		invalidate(selRect);
		selX = Math.min(Math.max(x, 0), 8);
		selY = Math.min(Math.max(y, 0), 8);
		getRect(selX, selY, selRect);
		invalidate(selRect);
	}

	private void getRect(int x, int y, Rect rect) {
		rect.set((int) (x * widthSpot), (int) (y * heightSpot), (int) (x
				* widthSpot + widthSpot), (int) (y * heightSpot + heightSpot));
	}

	protected void onDraw(Canvas canvas) {
		// GAMBAR BACKGROUND
		Paint background = new Paint();
		background.setColor(getResources().getColor(R.color.background));
		canvas.drawRect(0, 0, getWidth(), getHeight(), background);

		// GAMBAR HURUF
		Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
		foreground.setColor(getResources().getColor(R.color.puzzle_foreground));
		foreground.setStyle(Style.FILL);
		foreground.setTextSize(heightSpot * 0.75f);
		foreground.setTextScaleX(widthSpot / heightSpot);
		foreground.setTextAlign(Paint.Align.CENTER);
		FontMetrics fm = foreground.getFontMetrics();
		float x = widthSpot / 8;
		float y = heightSpot / 8 - (fm.ascent + fm.descent) / 8;

		Paint hint = new Paint();
		hint.setColor(getResources().getColor(R.color.transparan));
		// hint.setColor(getResources().getColor(R.color.puzzle_selected));

		// ISI PUZZLE
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int t = puzzleUser[i][j];
				int tSoal = game.puzzleSoal[i][j];
				if (t != 0) {
					if (tSoal != 0) {
						// JIKA YANG DIGAMBAR ADALAH SOAL
						canvas.drawRect((i * widthSpot + x) - 7, (j
								* heightSpot + y) - 13, i * widthSpot + x + 26,
								j * heightSpot + y + 26, hint);
					} else {
						// JIKA YANG DIGAMBAR ADALAH INPUTAN USER
						canvas.drawRect((i * widthSpot + x) - 7, (j
								* heightSpot + y) - 13, i * widthSpot + x + 26,
								j * heightSpot + y + 26, foreground);
					}
				}
				Bitmap gambar = getGambar(i, j);
				canvas.drawBitmap(gambar, i * widthSpot + x,
						j * heightSpot + y, foreground);

			}
		}

		Paint selected = new Paint();
		selected.setColor(getResources().getColor(R.color.transparan));
		canvas.drawRect(selRect, selected);

		// GAMBAR GARIS PEMBATAS

		// PEMBATAS KOTAK BESAR
		Paint dark = new Paint();
		dark.setColor(getResources().getColor(R.color.puzzle_dark));
		dark.setStrokeWidth(5);

		// DUA PEMBATAS KOTAK KECIL
		Paint hilite = new Paint();
		hilite.setColor(getResources().getColor(R.color.puzzle_hilite));

		Paint light = new Paint();
		light.setColor(getResources().getColor(R.color.puzzle_light));

		// CETAK GARIS KOTAK KECIL
		for (int i = 0; i < 9; i++) {
			// GARIS HORIZONTAL
			canvas.drawLine(0, i * heightSpot, getWidth(), i * heightSpot,
					light);
			canvas.drawLine(0, i * heightSpot + 1, getWidth(), i * heightSpot
					+ 1, hilite);
			// GARIS VERTIKAL
			canvas.drawLine(i * widthSpot, 0, i * widthSpot, getHeight(), light);
			canvas.drawLine(i * widthSpot + 1, 0, i * widthSpot + 1,
					getHeight(), hilite);
		}
		// CETAK GARIS KOTAK BESAR
		for (int i = 0; i < 9; i++) {
			if (i % 3 != 0)
				continue;
			// GARIS HORIZONTAL

			canvas.drawLine(0, i * heightSpot, getWidth(), i * heightSpot, dark);
			canvas.drawLine(0, i * heightSpot + 1, getWidth(), i * heightSpot
					+ 1, hilite);
			// GARIS VERTIKAL
			canvas.drawLine(i * widthSpot, 0, i * widthSpot, getHeight(), dark);
			canvas.drawLine(i * widthSpot + 1, 0, i * widthSpot + 1,
					getHeight(), hilite);
		}
	}

	public static void setPuzzleUser(int Puzzle[][]) {
		for (int i = 0; i < Puzzle.length; i++) {
			for (int j = 0; j < Puzzle[0].length; j++) {
				puzzleUser[i][j] = Puzzle[i][j];
			}
		}
	}

	/*
	 * Metode untuk mengambil gambar yang akan digambar
	 */
	private Bitmap getGambar(int baris, int kolom) {
		Bitmap gambar = null;

		int temp = puzzleUser[baris][kolom];
		if (temp == 0) {
			gambar = BitmapFactory.decodeResource(getResources(),
					R.drawable.kosong);
		} else {
			gambar = gambarku[temp - 1];
		}
		return gambar;
	}

	private int getTile(int baris, int kolom) {
		return puzzleUser[baris][kolom];
	}

	private void setTile(int baris, int kolom, int huruf) {
		puzzleUser[baris][kolom] = huruf;
	}

	public void setNilai(int tile) {
		if (cek(selX, selY, tile)) {
			setTile(selX, selY, tile);
			if (suaraku != null) {
				suaraku[tile - 1].start();
			}
			invalidate();
			cekSelesai(false);
		}
	}

	public void cekSelesai(boolean solving) {
		boolean kelar = true;
		for (int baris = 0; baris < puzzleUser.length; baris++) {
			for (int kolom = 0; kolom < puzzleUser[baris].length; kolom++) {
				if (puzzleUser[baris][kolom] == 0) {
					kelar = false;
					if(solving){
						game.mesin.getSolutionPuzzle(puzzleUser);
					}
					break;
				}
			}
		}
		if (kelar) {
			String pesan = "";
			if(solving){
				long waktuFinish = System.currentTimeMillis(); 
		    	double wkt = ((double)(waktuFinish - waktuStart))/1000;
//		    	wkt = ((double)Math.round(wkt*100))/100;//pembulatan 2 angka di belakang koma
				pesan = "Puzzle Telah Diselesaikan Menggunakan Algoritma Backtracking " +
						"\ndan Multiplicative CRNG dengan Parameter:" +
						"\n Z0 = 12357" +
						"\n a = " + aSolver+
						"\n m = 11, " +
						"\n Selama "+wkt+" detik";
			}else{
				pesan = "Selamat! Puzzle Anda Benar\nSilahkan "
						+ "keluar untuk bermain dengan puzzle lainnya\n"
						+ "Terima Kasih";
			}
			game.tampilkanPesan(pesan);
		}
	}

	private boolean cek(int baris, int kolom, int tile) {
		String pesan = "Huruf yang Anda pilih ada yang sama pada";
		boolean aman = true;
		for (int i = 0; i < 9; i++) {
			if (i == kolom)
				continue;
			int t = getTile(baris, i);
			if (tile == t) {
				pesan = pesan + "\nKolom ";
				aman = false;
				break;
			}
		}
		for (int i = 0; i < 9; i++) {
			if (i == baris)
				continue;
			int t = getTile(i, kolom);
			if (tile == t) {
				pesan = pesan + "\nBaris ";
				aman = false;
				break;
			}
		}
		int startx = (baris / 3) * 3;
		int starty = (kolom / 3) * 3;
		for (int i = startx; i < startx + 3; i++) {
			for (int j = starty; j < starty + 3; j++) {
				if (i == baris && j == kolom)
					continue;
				int t = getTile(i, j);
				if (tile == t) {
					pesan = pesan + "\nArea ";
					aman = false;
					break;
				}
			}
		}
		if (aman == false) {
			pesan = pesan + "\nSilahkan pilih huruf lain!";
			game.tampilkanPesan(pesan);

		}
		return aman;
	}

	public void SelesaikanPuzzleUser(int[][] solusi,long waktuStart) {
		this.waktuStart = waktuStart;
		setPuzzleUser(solusi);
		invalidate();
//		invalidate();
		cekSelesai(true);
		puzzle.progres.dismiss();
	}
}
