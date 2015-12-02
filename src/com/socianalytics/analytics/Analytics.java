package com.socianalytics.analytics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.rosuda.REngine.*;
import org.rosuda.REngine.Rserve.*;


/**
 * Servlet implementation class Analytics
 */
public class Analytics extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Hashtable<String, Double> ob= new Hashtable();
	String labels[];
	double[] slices;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Analytics() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub		
		Boolean exists;
		String searchResult;
		
		double Extraversion=0.0;
		double Agreeableness=0.0;
		double Conscientiousness=0.0;
		double Neuroticism=0.0;
		double Openness=0.0;
		
		
		String search=request.getParameter("Search").toLowerCase();
		//System.out.println(request.getContextPath());
		searchResult=doesGroupExist(search);
		
		if(searchResult==null){
			response.sendRedirect("404.html");
		}
		else{
			
			rCalling(searchResult);	
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String str0="<!doctype html> <!--[if lt IE 7 ]><html class='ie ie6' lang='en'> <![endif]--> <!--[if IE 7 ]><html class='ie ie7' lang='en'> <![endif]--> <!--[if IE 8 ]><html class='ie ie8' lang='en'> <![endif]--> <!--[if (gte IE 9)|!(IE)]><!--><html lang='en'> <!--<![endif]--> <head> <meta charset='utf-8'>  <title>Result Page</title> <meta name='description' content='Project 01 detail view'> <meta name='author' content='Ivan Designostrom'> <meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1'>  <!-- CSS ================================================================================================= --> <link rel='stylesheet' href='css/base.css'> <link rel='stylesheet' href='css/themes/type_05.css'> <link rel='stylesheet' href='css/themes/color_29.css'>  <!--[if lt IE 9]> <script src='http://html5shim.googlecode.com/svn/trunk/html5.js'></script> <![endif]-->  <!-- Favicons ============================================================================================= --> <link rel='shortcut icon' href='../../images/favicons/favicon2.ico'> <link rel='apple-touch-icon' href='../../images/favicons/apple-touch-icon.png'> <link rel='apple-touch-icon' sizes='72x72' href='../../images/favicons/apple-touch-icon-72x72.png'> <link rel='apple-touch-icon' sizes='114x114' href='../../images/favicons/apple-touch-icon-114x114.png'>  <!-- JS ================================================================================================= --> <script src='../../js/libs/modernizr.min.js'></script> <script src='../../js/libs/jquery-1.8.3.min.js'></script> <script src='../../js/libs/jquery.easing.1.3.min.js'></script> <script src='../../js/libs/jquery.fitvids.js'></script> <script src='../../js/script.js'></script>";
			
			str0+="<script type='text/javascript' src='https://www.google.com/jsapi'></script> <script type='text/javascript'> google.load('visualization', '1', {packages:['corechart']}); google.setOnLoadCallback(drawChart); function drawChart() { var data = google.visualization.arrayToDataTable([['Traits', 'Percentage'] ";
    		String str1="";
			for(int i=0;i<labels.length;i++){            	
				String str = (String)labels[i];
		        str1=str1 +",['" + str +"'," + slices[i] +"]"; 
		        
		        if(i<6){
		        	Extraversion+= slices[i];
		        }
		        else if(i>=6 & i<12){
		        	Agreeableness+= slices[i];
		        }
		        else if(i>=12 & i<18){
		        	Conscientiousness+= slices[i];
		        }
		        else if(i>=18 & i<24){
		        	Neuroticism+= slices[i];
		        }
		        else{
		        	Openness+=slices[i];
		        	}
            }  

  
//			
//			 String str2="]);  var options = { title: 'Personality traits', pieHole: 0.4, };  var chart = new google.visualization.PieChart(document.getElementById('chart_div')); chart.draw(data, options); } </script>";
//			 
//			 str2+="</script> <script type='text/javascript'> google.load('visualization', '1', {packages:['corechart']}); google.setOnLoadCallback(drawChart); function drawChart() { var data = google.visualization.arrayToDataTable([['FFT Traits', 'Percentage'] ";
//	    	 str2+=","+"['Extraversion'," +	Extraversion + "]";
//	    	 str2+=","+"['Agreeableness'," +	Agreeableness + "]";
//	    	 str2+=","+"['Conscientiousness'," +	Conscientiousness + "]";
//	    	 str2+=","+"['Neuroticism'," +	Neuroticism + "]";
//	    	 str2+=","+"['Openness'," +	Openness + "]]";
			
			
			String str2="]);var options = { title: 'In-Depth Analysis', pieHole: 0.4,'width':500,'height':400};var chart = new google.visualization.PieChart(document.getElementById('donutchart')); chart.draw(data, options); }</script><script type='text/javascript' src='https://www.google.com/jsapi'></script><script type='text/javascript'>google.load('visualization', '1.0', {'packages':['corechart']});";
			str2+="google.setOnLoadCallback(drawChart);function drawChart() { var data = new google.visualization.DataTable();data.addColumn('string', 'Topping');data.addColumn('number', 'Slices');";
			str2+="data.addRows([";
			str2+="['Extraversion'," +	Extraversion + "]";
			str2+=","+"['Agreeableness'," +	Agreeableness + "]";
			str2+=","+"['Conscientiousness'," +	Conscientiousness + "]";
			str2+=","+"['Neuroticism'," +	Neuroticism + "]";
			str2+=","+"['Openness'," +	Openness + "]]);";
			
			str2+="var options = {'title':'Overview : Five Factors Model','width':500,'height':400};";
			str2+="var chart = new google.visualization.PieChart(document.getElementById('chart_div'));chart.draw(data, options);}</script>";
			 		 
			 
			 
			 double sumOfFFT=Extraversion+Agreeableness+Conscientiousness+Neuroticism+Openness;
			 
			 System.out.println("Sum of FFT: "+ sumOfFFT);
			 
			 
			 int perExtraversion=(int) ((Extraversion/sumOfFFT)*100);
			 int perAgreeableness=(int) ((Agreeableness/sumOfFFT)*100);
			 int perConscientiousness=(int) ((Conscientiousness/sumOfFFT)*100);
			 int perNeuroticism=(int) ((Neuroticism/sumOfFFT)*100);
			 int perOpenness=(int) ((Openness/sumOfFFT)*100);
			 
			 int totalScore=totalScore(perExtraversion,perAgreeableness,perConscientiousness,perNeuroticism,perOpenness);
			 
			 System.out.println(perExtraversion+"\n"+perAgreeableness+"\n"+perConscientiousness+"\n"+perNeuroticism+"\n"+perOpenness);
			 
			 System.out.println("totalScore:"+totalScore);
			 
			 
			 double max=Math.max(Math.max(Math.max(Math.max(Extraversion, Agreeableness),Conscientiousness),Neuroticism),Openness);
			 
			 System.out.println("Maximum:"+max);
			 
			 String Max;
			 
			 if(max==Extraversion){
				 Max="E";
			 }
			 else if(max==Agreeableness){
				 Max="A";
			 }
			 else if(max==Conscientiousness){
				 Max="C";
			 }
			 else if(max==Neuroticism){
				 Max="N";
			 }
			 else{
				 Max="O";
			 }
			 
			 System.out.println("Max:"+Max);
			 String Brand=brandInclination(Max,totalScore);
			 System.out.println(Brand);
			 
			 
			 
			 
			 str2+="</head> <body>  <!-- Write preloader to page - this allows the site to load for users with JS disabled --> <script type='text/javascript'> document.write('<div id='sitePreloader'><div id='preloaderImage'><img src='../../images/site_preloader.gif' alt='Preloader' /></div></div>'); </script>  <div class='container'>  <!-- Header begins ========================================================================== --> <header class='sixteen columns'> <div id='logoDetailView'> <h1>Statistical Representation of Personality Insights</h1> <h2>"+ request.getParameter("Search") +"</h2> <!-- <img src='images/logo.png' width='275' height='35' alt='Logo' /> --> </div> <nav> <ul> <li><a href='../../Socianalytics/index.html'>&laquo; Back</a></li> </ul> </nav> <hr /> </header> <!-- Header ends ============================================================================ -->  <div id='detailView' class='sixteen columns' > <table><tr><td><div id='chart_div' style='width:500; height:400'></div></td><td> <div id='donutchart' style='width: 500px; height: 400px;'></div></td></tr></table><nav> <ul> <li><a href='../../Socianalytics/BrandInclination.jsp?brand="+Brand+"&search="+request.getParameter("Search")+"'> Brand Inclination&raquo;</a></li> </ul> </nav><div id='detailViewBack'> <a href='../../Socianalytics/index.html'>&laquo; Back</a> </div>  </div>  <!-- Footer begins ========================================================================== --> <footer class='sixteen columns'> <hr /> <ul id='footerLinks'> <li>&copy; 2015 IBM Running Champions. All rights reserved.</li> <li><a href='mailto:mishnish@in.ibm.com'>Contact Us</a></li>  </ul> </footer> <!-- Footer ends ============================================================================ -->  </div><!-- container --> </body> </html>";
			 //str2+=Brand+ "</h1><td></tr><tr><td><div id='chart_div' style='width:700; height:500'></div></td><td><div id='donutchart' style='width: 600px; height: 500px;'></div></td></tr></table></form></body></html>";
			 
			 str0=str0+str1+str2;
			 System.out.println(str0);
			 out.println(str0);
			 
			 
			   		 
			
//			Hashtable ob= new Hashtable();									
//			ob=fetchAnalysisresult(searchResult);	
//			
//			response.setContentType("text/html");
//			PrintWriter out = response.getWriter();
//			String str0="<html> <head> <script type='text/javascript' src='https://www.google.com/jsapi'></script> <script type='text/javascript'> google.load('visualization', '1', {packages:['corechart']}); google.setOnLoadCallback(drawChart); function drawChart() { var data = google.visualization.arrayToDataTable([['Traits', 'Percentage'] ";
//			 Enumeration names;
//			 String str;
//			 names = ob.keys();
//			 String str1="";
//		     while(names.hasMoreElements()) {
//		        str = (String) names.nextElement();
//		        //System.out.println(str + ": " +ob.get(str));
//		        str1=str1 +",['" + str +"'," + ob.get(str) +"]";
//		     }		     
//		     
//		     
//			 String str2="]);  var options = { title: 'Personality traits', pieHole: 0.4, };  var chart = new google.visualization.PieChart(document.getElementById('donutchart')); chart.draw(data, options); } </script> </head> <body> <div id='donutchart' style='width: 900px; height: 500px;'></div> </body> </html>";
//			 str0=str0+str1+str2;
//			 System.out.println(str0);
//			 out.println(str0);
			
			//Hashtable ob= new Hashtable();									
			//ob=fetchAnalysisresult(searchResult);
			//plotStat(ob, response);
			
			
			
		}
	
	}
	
	
	public String doesGroupExist(String search) {

		String csvFile = "C://Socianalytics//Socianalytics//WebContent//data//handlelist.csv";
		//String csvFile = request.getContextPath()+ "/grouplist.csv";
		
		//System.out.println(csvFile);
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String searchResult=null;

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

			        // use comma as separator
				String[] grp = line.split(cvsSplitBy);
				if(grp[0].toLowerCase().equals(search)){
					
					return grp[1];
				}
				
				
			}
			if(searchResult==null){
				return searchResult;
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return searchResult;	
}

	public Hashtable fetchAnalysisresult(String search) {

		String csvFile = "C://Socianalytics//Socianalytics//WebContent//data//"+search+".csv";
		//String csvFile = request.getContextPath()+ "/labels.csv";
		System.out.println("csvFile");
		
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		Boolean exists=false;
		Hashtable ob= new Hashtable();

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

			        // use comma as separator
				String[] grp = line.split(cvsSplitBy);
				ob.put(grp[0], grp[1]);			
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ob;
			
}


	public void plotStat(Hashtable stat,HttpServletResponse response) {

		Hashtable ob= stat;
		
		response.setContentType("text/html");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str0="<html> <head> <script type='text/javascript' src='https://www.google.com/jsapi'></script> <script type='text/javascript'> google.load('visualization', '1', {packages:['corechart']}); google.setOnLoadCallback(drawChart); function drawChart() { var data = google.visualization.arrayToDataTable([['Traits', 'Percentage'] ";
		 Enumeration names;
		 String str;
		 names = ob.keys();
		 String str1="";
	     while(names.hasMoreElements()) {
	        str = (String) names.nextElement();
	        //System.out.println(str + ": " +ob.get(str));
	        str1=str1 +",['" + str +"'," + ob.get(str) +"]";
	     }		     
	     
	     
		 String str2="]);  var options = { title: 'Personality traits', pieHole: 0.4, };  var chart = new google.visualization.PieChart(document.getElementById('donutchart')); chart.draw(data, options); } </script> </head> <body> <div id='donutchart' style='width: 900px; height: 500px;'></div> </body> </html>";
		 str0=str0+str1+str2;
		 System.out.println(str0);
		 out.println(str0);

			
}
	
	void rCalling(String search){
		
		rserveuseClass threadOb= new rserveuseClass(search);
		threadOb.start();
		try {
			threadOb.join();
			
			labels=threadOb.labels;
			slices=threadOb.slices;
			
			
//			for(int i=0;i<threadOb.labels.length;i++){            	
//            	
//            	System.out.println(threadOb.labels[i]+threadOb.slices[i]);           
//            	
//            }  
//            
			
			
			//ob=threadOb.getLableSlices();
//			if(ob.isEmpty()){
//				System.out.println("empty");
//			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	int totalScore(int E,int A,int C,int N,int O){
		
		int scorecard[][]={
				{2,15,5,10,6},
				{20,3,10,4,10},
				{15,20,30,15,15}
				};
		
		int scoreE;
		int scoreA;
		int scoreC;
		int scoreN;
		int scoreO;
		
		if(E>=0 && E<=30){
			scoreE=scorecard[0][0];
		}
		else if(E>=31 && E<=60){
			scoreE=scorecard[1][0];
		}
		else{
			scoreE=scorecard[2][0];
		}
		
		
		
		if(A>=0 && A<=30){
			scoreA=scorecard[0][1];
		}
		else if(A>=31 && A<=60){
			scoreA=scorecard[1][1];
		}
		else{
			scoreA=scorecard[2][1];
		}
		
		
		if(C>=0 && C<=30){
			scoreC=scorecard[0][2];
		}
		else if(C>=31 && C<=60){
			scoreC=scorecard[1][2];
		}
		else{
			scoreC=scorecard[2][2];
		}
		
		if(N>=0 && N<=30){
			scoreN=scorecard[0][3];
		}
		else if(N>=31 && N<=60){
			scoreN=scorecard[1][3];
		}
		else{
			scoreN=scorecard[2][3];
		}
		
		
		
		
		if(O>=0 && O<=30){
			scoreO=scorecard[0][4];
		}
		else if(O>=31 && O<=60){
			scoreO=scorecard[1][4];
		}
		else{
			scoreO=scorecard[2][4];
		}
		
		
		return(scoreE + scoreA + scoreC + scoreN + scoreO);		
		
	}	
	
	
	String brandInclination(String Max,int totalScore){
		
		String csvFile = "C://Socianalytics//Socianalytics//WebContent//data//BrandInclination.csv";
		//String csvFile = request.getContextPath()+ "/labels.csv";
		System.out.println("csvFile");
		
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		String Brand=null;
		

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

			        // use comma as separator
				String[] grp = line.split(cvsSplitBy);
				if(grp[0].equals(Max)){
					if(totalScore>=Integer.parseInt(grp[1]) && totalScore<=Integer.parseInt(grp[2])){
						Brand=grp[3];
						break;
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return Brand;
		
	}

}
