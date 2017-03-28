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

package kariminf.kinfominer.edict2;
import java.util.ArrayList;

import kariminf.kinfominer.edict2.Parser;
import kariminf.kinfominer.edict2.lex.*;


class XMLinConsole {


	public static void main(String[] args) {
		
		LINE parsedline = Parser.ParseLINE("閉まる(P);締まる(P);緊まる [しまる(P)] /(v5r,vi) (1) (esp. 閉まる) to be shut/to close/to be closed/(v5r,vi)(2) to be locked/(3) to tighten/to be tightened/(4) to become sober/to become tense/(P)/EntL1436560X/");
		
		String[] kanjis = parsedline.getKanjiList();
		String[] kanjistype = parsedline.getKanjiTypeList();
		String[] kanas = parsedline.getKanaList();
		String[] kanastype = parsedline.getKanaTypeList();
		
		System.out.print("<kanjis>\n");
		
		for (int i=0;i<kanjis.length;i++){
			System.out.print("	<kanji>\n");
			System.out.print("		<kanjiword>"+kanjis[i]+"</kanjiword>\n");
			System.out.print("		<kanjitype>"+kanjistype[i]+"</kanjitype>\n");
			System.out.print("	</kanji>\n");
		}
			
		System.out.print("</kanjis>\n");
		System.out.print("<kanas>\n");
		
		for (int i=0;i<kanas.length;i++){
			System.out.print("	<kana>\n");
			System.out.print("		<kanaword>"+kanas[i]+"</kanaword>\n");
			System.out.print("		<kanatype>"+kanastype[i]+"</kanatype>\n");
			System.out.print("	</kana>\n");
		}
		
		System.out.print("</kanas>\n");
		System.out.print("<common>"+ parsedline.getCommonWord() +"</common>\n");
		System.out.print("<seq>"+ parsedline.getSeqNum() +"</seq>\n");

		ArrayList<MEANS> allmeans = parsedline.getAllMeanings();
		System.out.print("<sences>\n");
		for (int i=0;i<allmeans.size();i++){
			System.out.print("	<sence>\n");
			System.out.print("		<pos>" + allmeans.get(i).pos+"</pos>\n");
			System.out.print("		<gloss>\n");
			ArrayList<GLOSS> glossaries = allmeans.get(i).getAllGlossaries();
			
			for(int j=0;j<glossaries.size();j++){
				System.out.print("			<mean>\n");
				System.out.print("				<inf>" + glossaries.get(j).information + "</inf>\n");
				System.out.print("				<words>\n");
				ArrayList<String> meanings = glossaries.get(j).getAllMeanings();
				for (int k=0;k<meanings.size();k++){
					System.out.print("					<word>" + meanings.get(k)+"</word>\n");
				}
				System.out.print("				</words>\n");
				System.out.print("			</mean>\n");
			}
			System.out.print("		</gloss>\n");
			System.out.print("	</sence>\n");
		}
		System.out.print("</sences>\n");
	}
	
}
