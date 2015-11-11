package com.example.testse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;

public class SpeechAdapter {
	
	public static String[]  recognize(Context context, String testo_input){
		String out = null;
		String path = "words_library.txt";
		String riga = null;
		String[] text = testo_input.split(" ");
		boolean keyword_confirmed = false;
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("words_library.txt")));
			do{
				riga = br.readLine();
				if(riga != null){
					if(!keyword_confirmed){
						if(riga.charAt(0) == '+'){
							String stringa_controllo = controllo(conversione(riga.substring(1)), text);
							if(stringa_controllo != null){
								out = stringa_controllo;
								keyword_confirmed = true;
							}
						}
					}else{
						if(riga.charAt(0) != '+'){
							String stringa_controllo = controllo(conversione(riga), text);
							if(stringa_controllo != null){
								out += " " + stringa_controllo;
							}else{
								out = null;
								break;
							}
						}else{
							break;
						}
					}
				}
			}while(riga != null);
		}
	    catch(IOException e) { 
	      e.printStackTrace();
	    }
		if(out != null){
			return out.split(" ");
		}else{
			return new String[] {null};
		}
	}
	
	
	
	
	private static String controllo(String[][] riga, String[] testo){
		String out = null;
		boolean b=false;
		for(int i=0; i<riga.length; i++){
			for(int j=0; j<riga[i].length; j++){
				for(int c=0; c<testo.length; c++){
					if(riga[i][j].charAt(0) == '&'){
						switch(riga[i][j].charAt(1)){
						case 'i':
							try{
								int n = Integer.parseInt(testo[c]);
								out = testo[c];
								b=true;
							}catch(Exception e){
								
							}
							break;
						case 'f':
							try{
								float n = Float.parseFloat(testo[c]);
								out = testo[c];
								b=true;
							}catch(Exception e){
								
							}
							break;
						}
					}else if(riga[i][j].charAt(0) == '>'){
						if(riga[i][j].equals('>'+testo[c])){
							out=testo[c+1];
							b=true;
						}
					}else if(riga[i][j].charAt(0) == '<'){
						if(riga[i][j].equals('<'+testo[c])){
							out=testo[c-1];
							b=true;
						}
					}else if(riga[i][j].equals(testo[c])){
						out=riga[i][0];
						b=true;
					}
					if(b){
						break;
					}
				}
				if(b){
					break;
				}
			}
			if(b){
				break;
			}
		}
		return out;
	}
	
	
	
	
	private static String[][] conversione(String riga){
		String[] riga_array = riga.split("/");
		String out[][] = new String[riga_array.length][0];
		for(int i=0; i<riga_array.length; i++){
			out[i] = riga_array[i].split(";");
		}
		
		return out;
		}
}
