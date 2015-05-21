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

package aak.jap.Edict2.lex;

import java.util.ArrayList;

public class LINE {
	
	private ENTRY entry;
	private INFO info;
	
	public LINE(){
		//entry=new ENTRY();
	}

	public void setEntry(ENTRY _entry){
		entry=_entry;
	}
	
	public void setInfo(INFO _info){
		info=_info;
	}
	
	public WORD[] getKanjiWORDList(){
		
		return entry.getKanji();
	}

	public WORD[] getKanaWORDList(){
		
		return entry.getKana();
	}
	
	public String[] getKanjiList(){
		
		
		WORD[] kanjiwords = entry.getKanji();
		String[] kanjiList = new String[kanjiwords.length];
		
		for (int i=0; i<kanjiwords.length;i++)
			kanjiList[i] = kanjiwords[i].word;
		
		return kanjiList;
	}
	
	public String[] getKanjiTypeList(){
		
		
		WORD[] kanjiwords = entry.getKanji();
		String[] kanjitypeList = new String[kanjiwords.length];
		
		for (int i=0; i<kanjiwords.length;i++)
			kanjitypeList[i] = kanjiwords[i].type;
		
		return kanjitypeList;
	}

	public String[] getKanaList(){
				
		WORD[] kanawords = entry.getKana();
		String[] kanaList = new String[kanawords.length];
		
		for (int i=0; i<kanawords.length;i++)
			kanaList[i] = kanawords[i].word;
		
		return kanaList;
	}
	
	
	public String[] getKanaTypeList(){
		
		
		WORD[] kanawords = entry.getKana();
		String[] kanatypeList = new String[kanawords.length];
		
		for (int i=0; i<kanawords.length;i++)
			kanatypeList[i] = kanawords[i].type;
		
		return kanatypeList;
	}
	
	
	public int getSeqNum(){
		return info.getSeqNum();
	}
	
	public boolean getAudioDisponiblity(){
		return info.getAudioDisponiblity();
	}
	
	public boolean getCommonWord(){
		return info.getCommonWord();
	}
	
	public ArrayList<MEANS> getAllMeanings(){
		return info.getAllMeanings();
	}

}
