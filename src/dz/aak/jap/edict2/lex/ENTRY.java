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

package dz.aak.jap.edict2.lex;

import java.util.ArrayList;

public class ENTRY {
	private ArrayList<WORD> kanjis;
	private ArrayList<WORD> kanas;
	
	public ENTRY(){
		kanjis = new ArrayList<WORD>();
		kanas = new ArrayList<WORD>();
	}
	
	public void addKana(WORD _kana){
		kanas.add(_kana);
	}
	
	public void addKana(WORD[] _kanaList){
		for (int i=0; i<_kanaList.length; i++)
			kanas.add(_kanaList[i]);
			
	}
	
	public void addKanji(WORD _kanji){
		kanjis.add(_kanji);
	}
	
	public void addKanji(WORD[] _kanjiList){
		for (int i=0; i<_kanjiList.length; i++)
			kanjis.add(_kanjiList[i]);
	}

	
	public WORD[] getKana(){
		WORD[] result = new WORD[kanas.size()];
		for(int i=0;i<kanas.size();i++)
			result[i] = kanas.get(i);
		return result;
	}

	
	public WORD[] getKanji(){
		WORD[] result = new WORD[kanjis.size()];
		for(int i=0;i<kanjis.size();i++)
			result[i] = kanjis.get(i);
		return result;
	}
}
