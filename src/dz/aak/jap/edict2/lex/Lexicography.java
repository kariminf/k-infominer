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

/**
 * @author karim
 *
 */
public  class Lexicography {
	
	//Part of Speech Markings
	private static final String POSStartings = "aceinpsv";
	private static final String[][] POS = {
		{"adj-i",		"adjective (keiyoushi)"},
		{"adj-na",		"adjectival nouns or quasi-adjectives (keiyodoshi)"},
		{"adj-no",		"nouns which may take the genitive case particle 'no'"},
		{"adj-pn",		"pre-noun adjectival (rentaishi)"},
		{"adj-t",		"'taru' adjective"},
		{"adj-f",		"noun or verb acting prenominally (other than the above)"},
		{"adj",			"former adjective classification (being removed)"},
		{"adv",			"adverb (fukushi)"},
		{"adv-n",		"adverbial noun"},
		{"adv-to",		"adverb taking the `to' particle"},
		{"aux",			"auxiliary"},
		{"aux-v",		"auxiliary verb"},
		{"aux-adj",		"auxiliary adjective"},
		{"conj",		"conjunction"},
		{"ctr",			"counter"},
		{"exp",			"Expressions (phrases, clauses, etc.)"},
		{"id",			"idiomatic expression"},
		{"int",			"interjection (kandoushi)"},
		{"iv",			"irregular verb"},
		{"n",			"noun (common) (futsuumeishi)"},
		{"n-adv",		"adverbial noun (fukushitekimeishi)"},
		{"n-pref",		"noun, used as a prefix"},
		{"n-suf",		"noun, used as a suffix"},
		{"n-t",			"noun (temporal) (jisoumeishi)"},
		{"num",			"numeric"},
		{"pn",			"pronoun"},
		{"pref",		"prefix"},
		{"prt",			"particle"},
		{"suf",			"suffix"},
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
		{"vi",			"intransitive verb"},
		{"vk",			"kuru verb - special class"},
		{"vn",			"irregular nu verb"},
		{"vs",			"noun or participle which takes the aux. verb suru"},
		{"vs-c",		"su verb - precursor to the modern suru"},
		{"vs-i",		"suru verb - irregular"},
		{"vs-s",		"suru verb - special class"},
		{"vt",			"transitive verb"}
			
	};
	
	//Field of Application
	private static final String[][] FOA ={
		{"Buddh",			"Buddhist term"},
		{"MA",				"martial arts term"},
		{"comp",			"computer terminology"},
		{"food",			"food term"},
		{"geom",			"geometry term"},
		{"gram",			"grammatical term"},
		{"ling",			"linguistics terminology"},
		{"math",			"mathematics"},
		{"mil",				"military"},
		{"physics",			"physics terminology"}
	};
	/*
	 * 
	 * Miscellaneous Markings 

X	rude or X-rated term
abbr	abbreviation
arch	archaism
ateji	ateji (phonetic) reading
chn	children's language
col	colloquialism
derog	derogatory term
eK	exclusively kanji
ek	exclusively kana
fam	familiar language
fem	female term or language
gikun	gikun (meaning) reading
hon	honorific or respectful (sonkeigo) language
hum	humble (kenjougo) language
iK	word containing irregular kanji usage
id	idiomatic expression
io	irregular okurigana usage
m-sl	manga slang
male	male term or language
male-sl	male slang
ng	neuter gender
oK	word containing out-dated kanji
obs	obsolete term
obsc	obscure term
ok	out-dated or obsolete kana usage
on-mim	onomatopoeic or mimetic word
poet	poetical term
pol	polite (teineigo) language
rare	rare (now replaced by "obsc")
sens	sensitive word
sl	slang
uK	word usually written using kanji alone
uk	word usually written using kana alone
vulg	vulgar expression or word
	 * 
	 */
	
	//Regional words
	private static final String[][] RW = {
		{"kyb",				"Kyoto-ben"},
		{"osb",				"Osaka-ben"},
		{"ksb",				"Kansai-ben"},
		{"ktb",				"Kantou-ben"},
		{"tsb",				"Tosa-ben"},
		{"thb",				"Touhoku-ben"},
		{"tsug",			"Tsugaru-ben"},
		{"kyu",				"Kyuushuu-ben"},
		{"rkb",				"Ryuukyuu-ben"}
	};
	
	/*
	 * @Function String PartOfSpeechMarking(String mark)
	 * If there is a POS it retruns it's signification
	 * Else It returns null
	 */
	public static String PartOfSpeechMarking(String mark){
		
		if (POSStartings.contains(mark.substring(0, 1)))
			for (int i=0;i<POS.length;i++)
				if (mark==POS[i][0]) return POS[i][1];
		
		return "";
		
	}
	
	
	public static String FieldOfApplicationMarking(String mark){
		
		for (int i=0;i<FOA.length;i++)
			if (mark==FOA[i][0]) return FOA[i][1];
		
		return "";
	}
	
	public static String RegionalMarking(String mark){
		
		for (int i=0;i<RW.length;i++)
			if (mark==RW[i][0]) return RW[i][1];
		
		return "";
	}
	

}

