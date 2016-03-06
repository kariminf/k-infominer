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

package kariminf.nalanpar.edict2;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kariminf.nalanpar.edict2.Parser;
import kariminf.nalanpar.edict2.lex.LINE;
import kariminf.nalanpar.edict2.lex.MEANS;


public class Parse2sqlite2 {
	
	private static Connection con;
	
	private final String workplace = "/home/kariminf/Data/jap/";
	
	private final String verbstype = "v1|v2a-s|v4h|v4r|v5aru|v5b|v5g|v5k-s|v5k|v5m" +
			"|v5n|v5r-i|v5r|v5s|v5t|v5u-s|v5uru|v5u|v5z|v5|vz|vk|vn|vs-c|vs-i|vs-s|vs";
	
	private HashMap<String,Integer> Ntype = new HashMap<String,Integer>();
	
	private static final String[][] Vtype = {
		{"v1",			"Ichidan verb"},
		{"v2a-s",		"Nidan verb with 'u' ending (archaic)"},
		{"v4h",			"Yondan verb with 'hu/fu' ending (archaic)"},
		{"v4r",			"Yondan verb with 'ru' ending (archaic)"},
		{"v5",			"Godan verb (not completely classified)"},
		{"v5aru",		"Godan verb - -aru special class"},
		{"v5b",			"Godan verb with 'bu' ending"},
		{"v5g",			"Godan verb with 'gu' ending"},
		{"v5k",			"Godan verb with 'ku' ending"},
		{"v5k-s",		"Godan verb - iku/yuku special class"},
		{"v5m",			"Godan verb with 'mu' ending"},
		{"v5n",			"Godan verb with 'nu' ending"},
		{"v5r",			"Godan verb with 'ru' ending"},
		{"v5r-i",		"Godan verb with 'ru' ending (irregular verb)"},
		{"v5s",			"Godan verb with 'su' ending"},
		{"v5t",			"Godan verb with 'tsu' ending"},
		{"v5u",			"Godan verb with 'u' ending"},
		{"v5u-s",		"Godan verb with 'u' ending (special class)"},
		{"v5uru",		"Godan verb - uru old class verb (old form of Eru)"},
		{"v5z",			"Godan verb with 'zu' ending"},
		{"vz",			"Ichidan verb - zuru verb - (alternative form of -jiru verbs)"},
		{"vk",			"kuru verb - special class"},
		{"vn",			"irregular nu verb"},
		{"vs",			"noun or participle which takes the aux. verb suru"},
		{"vs-c",		"su verb - precursor to the modern suru"},
		{"vs-i",		"suru verb - irregular"},
		{"vs-s",		"suru verb - special class"}
			
	};
	
	private Pattern parse = Pattern.compile(".*\\(.*("+verbstype+").*\\).*");	
	
	public void insert_type() throws Exception {
		
		//Statement stat = con.createStatement();
		int num = 1;
		PreparedStatement prep = con
				.prepareStatement("insert into ttype values(?,?,?);");
		
		for (String[] vtype: Vtype)
		{
			prep.setString(2, vtype[0]);
			prep.setString(3, vtype[1]);
			prep.execute();
			Ntype.put(vtype[0], num);
			num++;
		}
		
	}
	
	
	public void insert_verbs() throws Exception {
		
		Statement stat = con.createStatement();
		ResultSet res;
		
		//String NL = System.getProperty("line.separator");
	    Scanner scanner = new Scanner(new FileInputStream(workplace + "edict2u"), "UTF-8");
	    int numkana = 1;
	    int numkanji = 1;
	    
	    //ArrayList<String> ckana;
	    //ArrayList<String> ckanji;
	    	    
	    try {
		      while (scanner.hasNextLine()){
		      	String oldline = scanner.nextLine();
		      	Matcher m = parse.matcher(oldline);
		      	//oldline.matches(".*\\((.*"+verbstype+".*)+\\).*")
		      	if (m.find())
		      	{
		      		LINE parsedline = Parser.ParseLINE(oldline);
		      		
		      		String[] kanjis = parsedline.getKanjiList();
		    		String[] kanas = parsedline.getKanaList();
		    		String add = "";
		    		
		    		String thetype = m.group(1);
		    		int ntype = 0;
		    		
		    		ntype = (Ntype.containsKey(thetype.trim()))?Ntype.get(thetype.trim()):0;
		    		System.out.println("------> "+ thetype + " = " + ntype);	
		    		
		    		if (ntype==24)
		    			add = "する";
		    		 		
		    		PreparedStatement prep = con
		    				.prepareStatement("insert into tkanji values(?,?,?);");
		    		
		    		for(String kanji:kanjis)
		    		{
		    			res = stat.executeQuery("select idkanji from tkanji where kanji ='" 
		    					+ kanji + add + "';");
		    			
		    			if (res.next())
		    			{
		    				//ckanji.add(res.getString("idkanji"));
		    				System.out.println("kanji: '"+ kanji + "' is already in.");
		    			}
		    			else
		    			{
		    				prep.setString(1, "" + numkanji);
		    				//ckanji.add("" + numkanji);
		    				numkanji++;
		    				prep.setString(2, kanji+add);
		    				prep.setString(3, ""+ntype);
		    				prep.execute();
		    				System.out.println("kanji: '"+ kanji + "' is in.");
		    			}
		    			
		    		}
		    		
		    		prep = con
		    				.prepareStatement("insert into tkana values(?,?,?);");
		    		
		    		for(String kana:kanas)
		    		{
		    			res = stat.executeQuery("select idkana from tkana where kana ='" 
		    					+ kana + add + "';");
		    			
		    			if (res.next())
		    			{
		    				//ckanji.add(res.getString("idkana"));
		    				System.out.println("kana: '"+ kana + "' is already in.");
		    			}
		    			else
		    			{
		    				prep.setString(1, ("" + numkana));
		    				//ckana.add("" + numkana);
		    				numkana++;
		    				prep.setString(2, kana+add);
		    				prep.setString(3, ""+ntype);
		    				prep.execute();
		    				System.out.println("kana: '"+ kana + "' is in.");
		    			}
		    			
		    		}
		    		
		    		prep = con
		    				.prepareStatement("insert into tread values(?,?);");
		    		
		    		for(String kanji:kanjis){
		    			res = stat.executeQuery("select idkanji from tkanji where kanji ='" 
		    					+ kanji + add + "';");
		    			int kanjiid = 0;
		    			if (res.next()) kanjiid = res.getInt(1);
		    			
		    			for(String kana:kanas){
		    				res = stat.executeQuery("select idkana from tkana where kana ='" 
			    					+ kana + add + "';");
			    			int kanaid = 0;
			    			if (res.next()) kanaid = res.getInt(1);
			    			
			    			prep.setInt(1, kanjiid);
		    				prep.setInt(2, kanaid);
		    				prep.execute();
		    				System.out.println("kanji:"+ kanjiid + " = kana:" + kanaid);
			    		}
		    		}
		    		
		      	}

		      }
		    }
		    finally{
		      scanner.close();
		    }
		
	}
	
	public void create() throws Exception {

		  Class.forName("org.sqlite.JDBC");
		  con = DriverManager.getConnection("jdbc:sqlite:" + workplace + "edict2verbs.db");
		  Statement stat = con.createStatement();
		  stat.executeUpdate("drop table if exists tkanji");
		  stat.executeUpdate("drop table if exists tkana");
		  stat.executeUpdate("drop table if exists tread");
		  stat.executeUpdate("drop table if exists ttype");

		  //creating table kanji
		  stat.executeUpdate("create table tkanji(idkanji integer,"
		    + "kanji varchar(20)," + "idtype INT," + "primary key (idkanji));");
		  stat.executeUpdate("create unique index idx_tkanji "
				    + "on tkanji (kanji asc);");
		 
		//creating table kana
		  stat.executeUpdate("create table tkana(idkana integer,"
		    + "kana varchar(20)," + "idtype INT," + "primary key (idkana));");
		  stat.executeUpdate("create unique index idx_tkana "
				    + "on tkana (kana asc);");
		  
		  
		//creating table reading
		  stat.executeUpdate("create table tread(idkanji integer,"
		    + "idkana integer);");
	
		//creating table type
		  stat.executeUpdate("create table ttype(idtype integer,"
		    + "acrotype varchar(5)," + "type varchar(40),"
				  + "primary key (idtype));");
		  stat.executeUpdate("create unique index idx_ttype "
				    + "on ttype (acrotype asc);");

		 		 
		  /* getting data
		  ResultSet res = stat.executeQuery("select * from user");
		  while (res.next()) {
		     System.out.println(res.getString("id") + " " + res.getString("age")
		              + " " + res.getString("firstName") + " "
		              + res.getString("sex") + " " + res.getString("weight")
		              + " " + res.getString("height"));
		  }*/
		}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parse2sqlite2 p2sqlite = new Parse2sqlite2();
		
		try {
		      p2sqlite.create();
		      p2sqlite.insert_type();
		      p2sqlite.insert_verbs();
		      con.close();
		    } catch (Exception e) {
		       e.printStackTrace();
		    }
	}

}
