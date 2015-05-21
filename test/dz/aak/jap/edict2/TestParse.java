/*
 * Edict2Ext
 * This is a parser which used to extract Edict2 dictionary's content
 * 
 * Copyright (C) 2013  Abdelkrime Aries <kariminfo0@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package dz.aak.jap.edict2;
import java.util.ArrayList;

import dz.aak.jap.edict2.Parser;
import dz.aak.jap.edict2.lex.*;

class TestParse {


	public static void main(String[] args) {
		
		LINE parsedline = Parser.ParseLINE("閉まる(P);締まる(P);緊まる [しまる(P)] /(v5r,vi) (1) (esp. 閉まる) to be shut/to close/to be closed/(v5r,vi)(2) to be locked/(3) to tighten/to be tightened/(4) to become sober/to become tense/(P)/EntL1436560X/");
		//LINE parsedline = Parser.ParseLINE("て /(aux) (で after certain verb forms) (See で・5) indicates continuing action/EntL2654270/");
		String[] kanjis = parsedline.getKanjiList();
		String[] kanjistype = parsedline.getKanjiTypeList();
		String[] kanas = parsedline.getKanaList();
		String[] kanastype = parsedline.getKanaTypeList();
		
		System.out.print("The kanji:\n-----------\n");
		if (kanjis.length==0) System.out.print("no kanjis\n");
		for (int i=0;i<kanjis.length;i++){
			System.out.print(kanjis[i] + " : "+kanjistype[i]+ "\n");
		}
			
		
		System.out.print("The kana:\n-----------\n");
		
		for (int i=0;i<kanas.length;i++)
			System.out.print(kanas[i] + " : "+kanastype[i]+ "\n");
		
		System.out.print("Information:\n===================================\n");
		System.out.print("Has audio at japanesepod101?:"+ parsedline.getAudioDisponiblity() +"\n");
		System.out.print("Is it common word?:"+ parsedline.getCommonWord() +"\n");
		System.out.print("The sequence number of it:"+ parsedline.getSeqNum() +"\n");
		
		System.out.print("The meanings:\n===================================\n");
		ArrayList<MEANS> allmeans = parsedline.getAllMeanings();
		for (int i=0;i<allmeans.size();i++){
			System.out.print(i + ":" + allmeans.get(i).pos +"\n");
			ArrayList<GLOSS> glossaries = allmeans.get(i).getAllGlossaries();
			
			for(int j=0;j<glossaries.size();j++){
				System.out.print(i + "-" +j + ":" + glossaries.get(j).information + "\n");
				ArrayList<String> meanings = glossaries.get(j).getAllMeanings();
				for (int k=0;k<meanings.size();k++){
					System.out.print(i + "-" + j + "-" + k +  ":" + meanings.get(k)+"\n");
				}
			}
			
		}
	}
	
}
