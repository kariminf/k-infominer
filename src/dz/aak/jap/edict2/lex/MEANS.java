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

public class MEANS {
	public String pos;//part of speach markings
	private boolean thereIsElements=false;
	private ArrayList<GLOSS> glossaries;
	
	public MEANS (){
		glossaries = new ArrayList<GLOSS>();
	}
	
	public void addGlossary(GLOSS glossary){
		glossaries.add(glossary);
		thereIsElements=true;
	}
	
	public ArrayList<GLOSS> getAllGlossaries(){
		return glossaries;
	}
	
	public boolean hasElements(){
		return thereIsElements;
	}
	
}
