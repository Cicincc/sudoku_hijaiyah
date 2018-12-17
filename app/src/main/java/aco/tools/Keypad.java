
package aco.tools;

import com.sudokuhijaiyah.R;
import com.sudokuhijaiyah.puzzleKanvas;
import android.app.Dialog;
import android.content.Context;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Keypad extends Dialog {

	//private View key[] = new View[9];
	private View keypad ;
	private int[] gambar = null;
	puzzleKanvas kanvas;
	
	ImageButton tombol[] = new ImageButton[9];
	
	public Keypad(Context context, puzzleKanvas coba,int[] gambar) {
		super(context);
		this.kanvas = coba;
		this.gambar = gambar;
		
		
		// TODO Auto-generated constructor stub
	}
	
	
	protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);

	      setTitle(R.string.keypad_title);
	      setContentView(R.layout.keypad);
	      setTampilan();
	      for(int i=0; i<tombol.length;i++){
	    	  tombol[i].setVisibility(View.VISIBLE);
	      }
	      setAksi();
	   }
	
	private void setTampilan(){
		keypad = findViewById(R.id.keypad);
		tombol[0] = (ImageButton)findViewById(R.id.keypad_1);
		tombol[1] = (ImageButton)findViewById(R.id.keypad_2);
		tombol[2] = (ImageButton)findViewById(R.id.keypad_3);
		tombol[3] = (ImageButton)findViewById(R.id.keypad_4);
		tombol[4] = (ImageButton)findViewById(R.id.keypad_6);
		tombol[5] = (ImageButton)findViewById(R.id.keypad_5);
		tombol[6] = (ImageButton)findViewById(R.id.keypad_7);
		tombol[7] = (ImageButton)findViewById(R.id.keypad_8);
		tombol[8] = (ImageButton)findViewById(R.id.keypad_9);
	
		for(int i=0;i<tombol.length;i++){
			tombol[i].setImageResource(gambar[i]);
			
		}
		
	}
	
	private void setAksi(){
		for (int i = 0; i < tombol.length; i++) {
	         final int t = i + 1;
	         tombol[i].setOnClickListener(new View.OnClickListener(){
	            public void onClick(View v) {
	               pilih(t);
	            }});
	      }
	      keypad.setOnClickListener(new View.OnClickListener(){
	         public void onClick(View v) {
	            pilih(0);
	         }});
	}
	
	private void pilih(int tile){
		kanvas.setNilai(tile);
		dismiss();
	}

  
  

}