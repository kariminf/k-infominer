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

package aak.jap.Edict2;


import java.util.regex.Pattern;

import aak.jap.Edict2.lex.*;



public class Parser {
		
	public static LINE ParseLINE(String _line){
		
		LINE ResultParse = new LINE();
		
		int slashindex = _line.indexOf("/");
		String _term="";
		String _info="";
		
		if (slashindex >0){
			_term = _line.substring(0, slashindex);
			_info = _line.substring(slashindex+1);
		}
		
		ResultParse.setEntry(__ENTRY (_term));
		ResultParse.setInfo(__INFO (_info));
		
		//String _parsedline = __ENTRY(_term) + "\n" + __INFO(_info);
		
		return ResultParse;
	}
	
	
	private static ENTRY __ENTRY (String _term){
		if (_term==null) return null;
		_term = _term.trim();
		
		ENTRY entry_container = new ENTRY();
		
		
		String[] everykana;
		
		if (_term.contains("[")) {
			everykana = _term.substring(_term.indexOf("[")+1, _term.length()-1).split(";");
			String[]  everykanji = _term.substring(0,_term.indexOf("[")).split(";");
			
			for(int i=0;i<everykanji.length;i++){
				WORD kanjiword = __createWordEntry(everykanji[i]);
				entry_container.addKanji(kanjiword);
			}
		}
		else
			everykana= _term.split(";");
		
		for(int i=0;i<everykana.length;i++){
			WORD kanaword = __createWordEntry(everykana[i]);
			entry_container.addKana(kanaword);
		}
		return entry_container;
	}
	
	private static WORD __createWordEntry(String _wordANDtype){
		WORD thenewword = new WORD();
		
		_wordANDtype = _wordANDtype.trim();
		
		if (_wordANDtype.contains("(P)")){ //To modify 
			thenewword.type="P";
			//_wordANDtype=_wordANDtype.substring(0, _wordANDtype.indexOf("(P)"));
		}
		
		if (_wordANDtype.contains("(")){ //To modify 
			thenewword.type="P";
			_wordANDtype=_wordANDtype.substring(0, _wordANDtype.indexOf("(")).trim();
		}
		
		thenewword.word=_wordANDtype;
		
		return thenewword;
	}
	
	private static INFO __INFO (String _info){
		if (_info==null) return null;
		
		INFO info_container = new INFO();
		
		boolean hasAudio;
		boolean common;
		
		
		String thesequence = _info.substring( _info.indexOf("/EntL")+5, _info.length()-1);
		hasAudio = thesequence.endsWith("X");
		if (hasAudio)
			thesequence= thesequence.substring(0, thesequence.length()-1);
		int  sequence= new Integer(thesequence);
		
		_info= _info.substring(0, _info.indexOf("/EntL"));
		
		common =  _info.trim().endsWith("(P)");
		
		if (common) _info = _info.substring(0, _info.lastIndexOf("/"));
		
		info_container.setAudioDisponiblity(hasAudio);
		info_container.setCommonWord(common);
		info_container.setSeqNum(sequence);
		
		_info = _info.substring(1);
		String[] meanings = _info.split("/\\(");//"\\(\\d+\\)");
		
		
		int i=0;
		while (i<meanings.length){
			meanings[i] = meanings[i].trim();
			
			MEANS setOfMeanings = new MEANS();
			String pos = meanings[i].substring(0, meanings[i].indexOf(")"));
			setOfMeanings.pos=pos;
			
			meanings[i]=meanings[i].substring(meanings[i].indexOf(")")+1);

			//System.out.print("MEAN-");
			
			do {
				GLOSS setOfGlossaries = new GLOSS();
				meanings[i] = meanings[i].trim();

				if (Pattern.matches("\\(?[\\d].*",meanings[i]))
					meanings[i]=meanings[i].substring(meanings[i].indexOf(")")+1).trim();
				
				String information="";
				while (meanings[i].startsWith("(")){
					information=meanings[i].substring(0, meanings[i].indexOf(")"));
					meanings[i]=meanings[i].substring(meanings[i].indexOf(")")+1).trim();
				}
				
				setOfGlossaries.information=information;
				String[] submeans = meanings[i].split("/");
				
				for (int j=0;j<submeans.length;j++)
					setOfGlossaries.addMeaning(submeans[j]);
				
				//System.out.print("GLOSS-");
				//System.out.print(meanings[i] + "\n");
				setOfMeanings.addGlossary(setOfGlossaries);
				i++;
			}while(i<meanings.length && Pattern.matches("[\\d].*",meanings[i]));
			
			info_container.addMeaning(setOfMeanings);
		}

		return info_container;
	}

}

