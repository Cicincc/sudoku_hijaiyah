package suhi.mesin;

import java.util.Date;

import com.sudokuhijaiyah.puzzle;
import com.sudokuhijaiyah.puzzleKanvas;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class backtrack {
	// DEKLARASI INDEKS PENGACAKAN MCRNG
	int aGeneration[] = { 2, 6, 7, 8 };
	static int iaGeneration;
	int aElimination[] = { 3, 23, 13, 37, 39, 40, 45, 112, 71, 81, 103, 129,
			135, 139, 147, 157 };
	int iaElimination;

	static int model[][];
	static int solusiCadangan[][];
	protected String cekHurufBlok[][];
	String pesanGagal = "";

	static String koordinatPerBlok[][] = {
			{ "0,0", "0,1", "0,2", "1,0", "1,1", "1,2", "2,0", "2,1", "2,2" },// DATA BLOK 1
			{ "0,3", "0,4", "0,5", "1,3", "1,4", "1,5", "2,3", "2,4", "2,5" },// DATA BLOK 2
			{ "0,6", "0,7", "0,8", "1,6", "1,7", "1,8", "2,6", "2,7", "2,8" },// DATA BLOK 3

			{ "3,0", "3,1", "3,2", "4,0", "4,1", "4,2", "5,0", "5,1", "5,2" },// DATA BLOK 4
			{ "3,3", "3,4", "3,5", "4,3", "4,4", "4,5", "5,3", "5,4", "5,5" },// DATA BLOK 5
			{ "3,6", "3,7", "3,8", "4,6", "4,7", "4,8", "5,6", "5,7", "5,8" },// DATA BLOK 6

			{ "6,0", "6,1", "6,2", "7,0", "7,1", "7,2", "8,0", "8,1", "8,2" },// DATA BLOK 7
			{ "6,3", "6,4", "6,5", "7,3", "7,4", "7,5", "8,3", "8,4", "8,5" },// DATA BLOK 8
			{ "6,6", "6,7", "6,8", "7,6", "7,7", "7,8", "8,6", "8,7", "8,8" } // DATA BLOK 9
	};

	public backtrack() {
		model = new int[9][9];
		solusiCadangan = new int[9][9];
		cekHurufBlok = new String[9][9];
		iaGeneration = (int) (Math.random() * 10) % 3;
		iaElimination = (int) (Math.random() * 100) % 15;
		// createModel();
	}

	public int[][] getPuzzle(int level) {
		System.out.println("------------------------------");
		System.out.println("INDEKS GET PUZZLE");
		System.out.println("INDEKS GENERATION = " + iaGeneration);
		System.out.println("INDEKS ELIMINATION = " + iaElimination);
		System.out.println("------------------------------");
		createModel();
		
		try {
			stop = false;
			backtracking(0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// SOLUSI CADANGAN
		setSolusiCadangan();
		iaGeneration++;// UBAH PENGACAKAN
		eliminationPuzzle(level);// ELIMINASI BEBERAPA SPOT SESUAI LEVEL
		// puzzle.progres.dismiss();
		return model;
	}

	public int[][] getSolutionPuzzle(int Puzzle[][]) {
		createCekHurufBlok();
		setModelSolution(Puzzle);
		System.out.println("------------------------------");
		System.out.println("INDEKS GET SOLUTION PUZZLE");
		System.out.println("INDEKS GENERATION = " + iaGeneration);
		System.out.println("INDEKS ELIMINATION = " + iaElimination);
		System.out.println("------------------------------");
		puzzleKanvas.aSolver = getAGeneration();

		try {
			stop = false;
			backtracking(0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!pesanGagal.equals("") || true == stop) {
			// JIKA TIDAK DITEMUKAN SOLUSI, MAKA TAMPILKAN SOLUSI CADANGAN
			System.out.println("----------------------------");
			System.out.println("----------------------------");
			System.out.println("BIOH GAG KETEMU");
			System.out.println("----------------------------");
			System.out.println("----------------------------");
//			puzzle.progresSolving.dismiss();
			return solusiCadangan;
		} else {
			System.out.println("----------------------------");
			System.out.println("----------------------------");
			System.out.println("KETEMU IHIR");
			System.out.println("----------------------------");
			System.out.println("----------------------------");
//			puzzle.progresSolving.dismiss();
			return model;
		}
	}

	public int[][] getSolusiCadangan() {
		return solusiCadangan;
	}

	protected void createModel() {
		// Clear all cells
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				model[row][col] = 0;
			}
		}
		createCekHurufBlok();
	}

	protected void createCekHurufBlok() {
		pesanGagal = "";
		prosesBacktracking = 0;
		// Clear all cells
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				cekHurufBlok[row][col] = "0";
			}
		}
	}

	// METHOD-METHOD
	int prosesBacktracking = 0;
	boolean stop = false;
	
	public void backtracking(int huruf, int blok) throws Exception {
		if(false == stop){
		System.out.println("PROSES ke-" + prosesBacktracking + " -> HURUF="
				+ huruf + ", BLOK=" + blok);
			if (((3 < prosesBacktracking ) && (1 > huruf))||(200 < prosesBacktracking)) {
			System.out.println("KELAMAAN---");
			toMath.setZ(12357);
			stop = true;
			prosesBacktracking = 0;
		}
		int hijaiyah = huruf + 1;
		if (huruf > 8) {
			System.out.println("--------------------");
			System.out.println("MISSION COMPLITE");
			System.out.println("--------------------");
			pesanGagal = "";
		} else if ((huruf == 0 && blok > 0)||(huruf == 0 && prosesBacktracking > 0)) {
			// TIDAK DITEMUKAN SOLUSI
			System.out.println("--------------------");
			System.out.println("TIDAK DITEMUKAN SOLUSI");
			System.out.println("--------------------");
			pesanGagal = "TIDAK DITEMUKAN SOLUSI";
		}
		// Jika Huruf tertentu pada blok tertentu sudah terisi
		if (!cekHurufBlok[huruf][blok].equals("0")
				|| cekHurufBlok[huruf][blok].equals("-")) {
			next(huruf, blok);
		} else {
			int xd = 0;
			int yd = 0;

			for (int iblok = 0; iblok < koordinatPerBlok[blok].length; iblok++) {
				int kolomrandom = toMath.multiplicativeCRNG(getAGeneration(),
						10);
				if (kolomrandom >= 10) {
					kolomrandom = toMath.multiplicativeCRNG(getAGeneration(),
							10);
				}
				if (kolomrandom <= 0) {
					System.out.println("a = " + getAGeneration());
					for (int i = 0; i < 20; i++) {
						System.out.println("- "
								+ toMath.multiplicativeCRNG(getAGeneration(),
										10) + " - ");
					}
					System.out.println("REPEAT MCRNG = " + toMath.getRepeat());
					System.out.println("Z = " + toMath.getZ());
					toMath.setZ(12357);
					System.out.println("Z = " + toMath.getZ());
					kolomrandom = toMath.multiplicativeCRNG(getAGeneration(),
							10);
					System.out.println("KETEMU KOLOM RANDOM = 0");
					System.out
							.println("koordinatPerBlok[blok][kolomrandom - 1] = koordinatPerBlok["
									+ blok + "][" + kolomrandom + " - 1]");
				}
				String koordinat = koordinatPerBlok[blok][kolomrandom - 1];
				String kor[] = koordinat.split(",");
				xd = Integer.parseInt(kor[0]);
				yd = Integer.parseInt(kor[1]);

				// Jika baris xd dan kolom yd sudah terisi
				if (model[xd][yd] != 0) {
					// JIKA DALAM POSISI MENCARI SOLUSI DARI PUZZLE
					if (model[xd][yd] == hijaiyah) {
						cekHurufBlok[huruf][blok] = xd + "," + yd;
						next(huruf, blok);
					} else {
						 continue;
					}
				} else {
					if (checkRow(xd, hijaiyah) && checkCol(yd, hijaiyah)) {
						model[xd][yd] = hijaiyah;
						cekHurufBlok[huruf][blok] = xd + "," + yd;
						Thread.sleep(1);
						next(huruf, blok);
					}
				}
			}

			// JIKA TIDAK DITEMUKAN HINGGA SELURUH SPOT DALAM BLOK, MAKA LAKUKAN
			// BACKTRACKING
			kosongkanSebelumnya(huruf, blok);
			cekHurufBlok[huruf][blok] = "0";
			prosesBacktracking++;
		}
	}}

	// private Handler hd = new Handler() {
	//
	// public void handleMessage(Message msg) {
	// progres.dismiss();
	// String pesan = "";
	// long wk = System.currentTimeMillis();
	// long wkt = (wk - waktuStart) / 1000;
	// pesan = "Proses Backtracking Selesai\n" + "Waktu : " + wkt
	// + " detik";
	// Toast.makeText(null, pesan, Toast.LENGTH_LONG);
	// }
	// };

	public void next(int huruf, int blok) throws Exception {
		if (blok < 8)
			backtracking(huruf, blok + 1);
		else
			backtracking(huruf + 1, 0);
	}

	public void kosongkanSebelumnya(int huruf, int blok) throws Exception {
		if (blok > 0) {
			String koordinat = cekHurufBlok[huruf][blok - 1];
			// JIKA KOORDINAT ADALAH SOAL PUZZLE
			if (koordinat.equals("-")) {
				kosongkanSebelumnya(huruf, blok - 1);
			} else {
				String kor[] = koordinat.split(",");
				int x = Integer.parseInt(kor[0]);
				int y = Integer.parseInt(kor[1]);
				model[x][y] = 0;
			}
		} else {
			String koordinat = cekHurufBlok[huruf - 1][8];
			// JIKA KOORDINAT ADALAH SOAL PUZZLE
			if (koordinat.equals("-")) {
				kosongkanSebelumnya(huruf - 1, 8);
			} else {
				String kor[] = koordinat.split(",");
				int x = Integer.parseInt(kor[0]);
				int y = Integer.parseInt(kor[1]);
				model[x][y] = 0;
			}
		}
	}

	// METHOD CEK BARIS,KOLOM,BOX
	protected boolean checkRow(int row, int num) {
		for (int col = 0; col < 9; col++)
			if (model[row][col] == num)
				return false;

		return true;
	}

	protected boolean checkCol(int col, int num) {
		for (int row = 0; row < 9; row++)
			if (model[row][col] == num)
				return false;

		return true;
	}

	protected boolean checkBox(int row, int col, int num) {
		row = (row / 3) * 3;
		col = (col / 3) * 3;

		for (int r = 0; r < 3; r++)
			for (int c = 0; c < 3; c++)
				if (model[row + r][col + c] == num)
					return false;

		return true;
	}

	protected int getAGeneration() {
		if (iaGeneration >= aGeneration.length) {
			iaGeneration = 0;
		}
		return aGeneration[iaGeneration];
	}

	protected void setSolusiCadangan() {
		for (int i = 0; i < model.length; i++) {
			for (int j = 0; j < model[0].length; j++) {
				solusiCadangan[i][j] = model[i][j];
			}
		}
	}

	protected void setModelSolution(int Puzzle[][]) {
		for (int i = 0; i < model.length; i++) {
			for (int j = 0; j < model[0].length; j++) {
				model[i][j] = Puzzle[i][j];
				// SETTING SPOT SOAL YANG TIDAK BOLEH DI UBAH
				if (Puzzle[i][j] != 0) {
					int blok = 0;
					if (i <= 2 && j <= 2) {
						blok = 0;
					} else if (i <= 2 && j <= 5) {
						blok = 1;
					} else if (i <= 2 && j <= 8) {
						blok = 2;
					} else if (i <= 5 && j <= 2) {
						blok = 3;
					} else if (i <= 5 && j <= 5) {
						blok = 4;
					} else if (i <= 5 && j <= 8) {
						blok = 5;
					} else if (i <= 8 && j <= 2) {
						blok = 6;
					} else if (i <= 8 && j <= 5) {
						blok = 7;
					} else if (i <= 8 && j <= 8) {
						blok = 8;
					}
					cekHurufBlok[Puzzle[i][j] - 1][blok] = "-";
					// System.out.println("baris="+i+" kolom="+j+" => BLOK="+blok+" HURUF="+(Puzzle[i][j]-1));
				}
			}
		}
	}

	protected void eliminationPuzzle(int level) {
		if (iaElimination >= aElimination.length) {
			iaElimination = 0;
		}
		
//		int lenyap = 0;
		int tampil = 0;
		if (level >= 3) {
//			lenyap = 40; 81-48 = 33
			tampil  = 33;//31
		} else if (level >= 2) {
//			lenyap = 30;
			tampil  = 36;
		} else if (level >= 1) {
//			lenyap = 20;
			tampil  = 41;
		} else {
//			lenyap = 10;
			tampil  = 41;
		}
		System.out.println("------------------------------");
		System.out.println("------------------------------");
		System.out.println("LEVEL = " + level);
		System.out.println("TAMPIL = " + tampil);
		System.out.println("------------------------------");
		System.out.println("------------------------------");
		
		int[][] temp = new int[9][9];
		for(int brs = 0;brs<temp.length;brs++){
			for(int klm = 0;klm<temp[0].length;klm++){
				temp[brs][klm] = 0;
			}
		}
		
		for (int i = 0; i < tampil; i++) {
//			for (int i = 0; i < lenyap; i++) {
			int xy = toMath.multiplicativeCRNG(aElimination[iaElimination],
					65536);
			xy = xy % 81;
			int baris = xy / model[0].length;
			int kolom = xy % model[0].length;
			if (0 != temp[baris][kolom]) {
				i--;
			}
//			if (model[baris][kolom] == 0) {
//				i--;
//			}
//			model[baris][kolom] = 0;
			temp[baris][kolom] = model[baris][kolom]; 
		}
		model = temp;
		iaElimination++;
	}

	// public static void startProgressDialog(Context context, String title,
	// String content){

	// }
}
