package kariminf.nalanpar.edict2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class FindVerbs {
	 /** Requires two arguments - the file name, and the encoding to use.  */
	private final String verbstype = "v1|v2a-s|v4h|v4r|v5|v5aru|v5b|v5g|v5k|v5k-s|v5m" +
			"|v5n|v5r|v5r-i|v5s|v5t|v5u|v5u-s|v5uru|v5z|vz|vi|vk|vn|vs-c|vs-i|vs-s|vt";
	private Pattern parse = Pattern.compile("([^\\[/]+)[^/]+/(\\([^\\)]*\\))");	
	
	  public static void main(String... aArgs) throws IOException {
	    String fileName = "edict2u";//"verbs.txt";
	    String encoding = "UTF-8";
	    FindVerbs test = new FindVerbs(fileName, encoding);
	     test.read();
	    test.write();
	  }
	  
	  /** Constructor. */
	  FindVerbs(String aFileName, String aEncoding){
	    fEncoding = aEncoding;
	    bFileName = aFileName;
	    fFileName = "verbs2.txt";
	  }
	  
	  /** Write fixed content to the given file. */
	  void write() throws IOException  {
	    log("Writing to file named " + fFileName + ". Encoding: " + fEncoding);
	    Writer out = new OutputStreamWriter(new FileOutputStream(fFileName), fEncoding);
	    try {
	      out.write(FIXED_TEXT);
	    }
	    finally {
	      out.close();
	    }
	    log("Writing Finished Successfully");
	  }
	  
	  /** Read the contents of the given file. */
	  void read() throws IOException {
	    log("Reading from file.");
	    StringBuilder text = new StringBuilder();
	    String NL = System.getProperty("line.separator");
	    Scanner scanner = new Scanner(new FileInputStream(bFileName), fEncoding);
	    try {
	      while (scanner.hasNextLine()){
	      	String oldline = scanner.nextLine();
	      	if (oldline.matches("[^/]*/[^/]*[\\(,]("+verbstype+")[\\),].*"))
	      	{
	      	   Matcher m=parse.matcher(oldline);
	           if(m.find()) {
	        	
	        	  String newline;
	        	  String type ="";
	        	  Matcher mm = Pattern.compile("[^;a-zA-Z\\(\\)\\s]+").matcher(m.group(1));
	        	  if(m.group(2).contains("vt")) type="vt";
	        	  if(m.group(2).contains("vi")) type="vi";
	        	  
	        	  while (mm.find()){
	        		  newline = mm.group(0) + "\t" + m.group(2)+ "\t" + type;
	        	  text.append(newline + NL);
	        	  }
	        	  
	        	  /*
	        		  String[] verbs = m.group(1).split(";");
	        		  for (String verb : verbs){
	        			  newline=verb + "\t" + m.group(2);
	        			  text.append(newline + NL);
	        		  }*/
	        	  
	        	  
	           }
	      	}

	      	/*
	      	//get the line that contain a verb
	      	if (oldline.matches(".*[\\(,]("+verbstype+").*"))
	      		text.append(oldline + NL);
	      	*/
	      }
	    }
	    finally{
	      scanner.close();
	    }
	    //log("Text read in: " + text);
	    FIXED_TEXT = text.toString();
	  }
	  
	  // PRIVATE 
	  private  String fFileName;
	  private  String bFileName;
	  private  String fEncoding;
	  private String FIXED_TEXT = "But soft! what code in yonder program breaks?";
	  private final CharSequence[] hitable={"きゃ","きょ","せ"
	  };
	  private final CharSequence[] rotable={"kya","kyo","se"
	  };
	  
	  private void log(String aMessage){
	    System.out.println(aMessage);
	  }
	  
	  private String transform (String wkana){
	  	String wromaji=wkana;
	  	for(int i=0;i<hitable.length;i++){
	  		wromaji=wromaji.replace(hitable[i], rotable[i]);
	  	}
	  	return wromaji;
	  }
}
