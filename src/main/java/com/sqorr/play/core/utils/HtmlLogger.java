package com.sqorr.play.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

/**
 * The Class HtmlLogger.
 */
public class HtmlLogger {
	
	static int index = -1;
	
	
	@Test(testName="")
	public void createHtmlLogFile() throws IOException {
		String img = "";
		String tcName = "";
		OutputStream htmlfile = new FileOutputStream(new File(
				"logs/sqorr-info.html"));
		PrintStream printhtml = new PrintStream(htmlfile);
		
		
		BufferedReader br = new BufferedReader(new FileReader(
				"logs/sqorr-info.log"));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			String htmlheader = "<html><head>";
			htmlheader += "<title>Execution Log - Sqorr</title>";
			htmlheader += "</head><body>";
			String htmlfooter = "</body></html>";
			sb.append(htmlheader);
			
			
			int count = 1;
			while (line != null) {
				if(line.contains("------- Executing")) {
					String dc = line;
					tcName = dc.split("------- Executing")[1].split("\\]")[0].trim();
					img = "screen/"+tcName+".jpg";
				}
				line = line.replace("[main]", "");
				
				if (line.contains("FUNCTIONAL FAILURE ")){
					sb.append("<font color='#0000FF'>" + "<" + "br" + "/><b>"
							+ line + "</b></font>");
					
//					For Failure's screen shot
//					sb.append("</br><img src="+img+" alt="+tcName+" style='width:250px;height:304px;' border=1>");
				}


				else if (line.contains("TEST FAILED ")){
					sb.append("<font color='red'>" + "<" + "br" + "/><b>"
							+ line + "</b></font><br/>");
				} 
				else if (line.contains("TEST FAILED ")){
					sb.append("<font color='red'>" + "<" + "br" + "/><b>"
							+ line + "</b></font><br/>");
				}
				else if (line.contains("TEST IS SUCCESSFUL")){
					sb.append("<font color='#006400'>" + "<" + "br" + "/><b>"
							+ line + "</b></font><br/>");
				}
	
				else if (line.contains("TEST IS SKIPPED")){
					line = line.replace("skipped]","skipped, please see the emailable-report.html for the details]");
					sb.append("<font color='#C71586'>" + "<" + "br" + "/><b>"
							+ line + "</b></font><br/>");
				}
				else {
					if (count == 1)
						sb.append(line);
					else
						sb.append("<" + "br" + "/>" + line);
					
					++count;
				}

				line = br.readLine();
			}

			sb.append(htmlfooter);
			printhtml.println(sb);
			printhtml.close();
			htmlfile.close();
		} finally {
			br.close();
		}
	}
	
	
	public void createHtmlAnalyticsLogFile(List<String[]> result) throws Exception {
		OutputStream htmlfile = new FileOutputStream(new File(
				"logs/sqorr-play.html"));
		PrintStream printhtml = new PrintStream(htmlfile);
		

		BufferedReader br = new BufferedReader(new FileReader( 
				"logs/sqorr-info.log"));
		StringBuilder sb = new StringBuilder();
		String htmlheader = "<html><head>";
		String htmlfooter = "</body></html>";
		try {
			String line = br.readLine();
			htmlheader = "<html><head>";
			htmlheader += "<title>Execution Log - Sqorr</title>";
			htmlheader += "</head><body>";
			sb.append(htmlheader);


			int count = 1;
			while (line != null) {
				line = line.replace("[main]", "");

				if (line.contains("FUNCTIONAL FAILURE ")){
					sb.append("<font color='#0000FF'>" + "<" + "br" + "/><b>"
							+ line + "</b></font>");
				} else if (line.contains("TEST FAILED ")){
					sb.append("<font color='red'>" + "<" + "br" + "/><b>"
							+ line + "</b></font><br/>");
				} else if (line.contains("TEST FAILED ")){
					sb.append("<font color='red'>" + "<" + "br" + "/><b>"
							+ line + "</b></font><br/>");
				} else if (line.contains("TEST IS SUCCESSFUL")){
					sb.append("<font color='#006400'>" + "<" + "br" + "/><b>"
							+ line + "</b></font><br/>");
				} else if (line.contains("TEST IS SKIPPED")){
					line = line.replace("skipped]","skipped, please see the emailable-report.html for the details]");
					sb.append("<font color='#C71586'>" + "<" + "br" + "/><b>"
							+ line + "</b></font><br/>");
				} else {
					if (count == 1)
						sb.append(line);
					else
						sb.append("<" + "br" + "/>" + line);
					++count;
				}
				if(line.contains("TEST IS SUCCESSFUL") || line.contains("FUNCTIONAL FAILURE")) {
					
					sb.append(generateHTML(result));
				}

				line = br.readLine();
			}

			sb.append(htmlfooter);
			printhtml.println(sb);
			printhtml.close();
			htmlfile.close();
		} finally {
			sb.append(htmlfooter);
			printhtml.println(sb);
			br.close();
		}
	}	
	
	
	public String generateHTML(List<String[]> result) throws Exception {
		String line = "";
		String red = "#ff0000", grey = "#8c8c87";
		try {
		for (index = index+1; index < result.size(); index++) {
			if(result.get(index)[0] == null)
				continue;
			if(result.get(index)[0].equals("&")) {
				line += "</TR></TABLE></TABLE></BR>";
				line += "<SPAN>MATRIC's ANALYTICS VALUE</SPAN>";
				line += "<TABLE><TABLE BORDER><TR BGCOLOR="+grey+"><TH>KEY<TH>Expected<TH>ACTUAL<TH>RESULT</TR>";
				continue;
			}
			if(result.get(index)[0].equals("%")) {
				line += "</TR></TABLE></TABLE></BR>";
				line += "<SPAN>PIXEL's ANALYTICS VALUE</SPAN>";
				line += "<TABLE><TABLE BORDER><TR BGCOLOR="+grey+"><TH>KEY<TH>Expected<TH>ACTUAL<TH>RESULT</TR>";
				continue;
			}
			if(result.get(index)[0].equals("#"))
				continue;
			if(result.get(index)[0].equals("**"))
				break;
			System.out.println("PRint value"+result.get(index)[0]);
			System.out.println(index);
			String ar[] = result.get(index);
			if(ar[3].equals("FALSE"))
				line += "<TR BGCOLOR="+red+"><TD>" + ar[0] + "<TD>" + ar[1] +"<TD>"+ar[2]+"<TD>"+ar[3];
			else
				line += "<TR><TD>" + ar[0] + "<TD>" + ar[1] +"<TD>"+ar[2]+"<TD>"+ar[3];
		}
		}catch(Exception e) {
			e.getMessage();
		}
		line += "</TABLE></TABLE> ";
		return line;
	}
	
	
	public void createHtmlAPILogFile(Map<String,String>result) throws Exception {
		OutputStream htmlfile = new FileOutputStream(new File(
				"logs/sqorr-play.html"));
		String line=null;
		PrintStream printhtml = new PrintStream(htmlfile);
		StringBuilder sb = new StringBuilder();
		String htmlheader = "<html><head>";
		String htmlfooter = "</body></html>";
		try {
			htmlheader = "<html><head>";
			htmlheader += "<title>API Execution Log - Sqorr</title>";
			htmlheader += "</head><body>";
			sb.append(htmlheader);
			String red = "#ff0000", grey = "#8c8c87";
			line = "<TABLE><TABLE BORDER><TR BGCOLOR="+grey+"><TH>KEY<TH>Expected<TH>VALUE</TR>";
			for ( String key : result.keySet() ) {
				line += "<TR><TD>" +key+ "</TD>" +"<TD>"+result.get(key)+"</TD>";
			}
			
			line += "</TABLE>";
			
			sb.append(htmlfooter);
			printhtml.println(sb);
			printhtml.close();
			htmlfile.close();
		} finally {
			sb.append(htmlfooter);
			printhtml.println(sb);
		}
	}	
	

}
