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

package kariminf.kinfominer.edict2.lex;

import java.util.ArrayList;

public class INFO {

	private int sequenceNumber;
	private boolean hasAudioReading;
	private boolean isCommonWord;
	private ArrayList<MEANS> meanings;

	
	
	public INFO(){
		sequenceNumber=0;
		hasAudioReading=false;
		isCommonWord=false;
		meanings = new ArrayList<MEANS>();
	}
	
	public void setSeqNum(int seqNum){
		sequenceNumber = seqNum;
	}
	
	public void addMeaning(MEANS _meaning/*, String type*/){
		meanings.add(_meaning);
	}
	
	public void setAudioDisponiblity(boolean disponible){
		hasAudioReading= disponible;
	}
	
	public void setCommonWord(boolean iscommon){
		isCommonWord = iscommon;
	}
	
	public int getSeqNum(){
		return sequenceNumber;
	}
	
	public boolean getAudioDisponiblity(){
		return hasAudioReading;
	}
	
	public boolean getCommonWord(){
		return isCommonWord;
	}
	
	public ArrayList<MEANS> getAllMeanings(){
		return meanings;
	}
	
}
