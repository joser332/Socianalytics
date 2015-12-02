package com.socianalytics.analytics;


import java.util.Hashtable;

import org.rosuda.REngine.*;
import org.rosuda.REngine.Rserve.*;

public class rserveuseClass extends Thread{
	String Search;
	public String labels[];
	public double[] slices;
	
	
	rserveuseClass(String search){
		Search=search;
		//labelSlices=null;
	}
	
		
    public void run() {
        try {
        	
        	System.out.println("Started");
        	RConnection c = new RConnection();// make a new local connection on default port (6311)
            c.eval("source('C:\\\\Socianalytics\\\\Socianalytics\\\\src\\\\com\\\\socianalytics\\\\analytics\\\\CleaningAndAnalyzing.R')");
            
            c.eval("tweets<-loading('C:\\\\Socianalytics\\\\Socianalytics\\\\WebContent\\\\data\\\\"+ Search +".csv')");
            
            c.eval("sentence<-mergeText(tweets$text)");
            
            c.eval("sentence<-cleaning(sentence)");
            
            c.eval("dictionary<-loading('C:\\\\Socianalytics\\\\Socianalytics\\\\WebContent\\\\data\\\\dictionary.csv')");
            
            c.eval("score.vector<-c()");
            
            String st="for(i in 1:ncol(dictionary)){";
            st+="adj.words<-dictionary[names(dictionary)[i]];";
            st+="adj.words<-adj.words[!is.na(adj.words)];";
            st+=" adj.score<-scoring(sentence,adj.words);";
            st+="score.vector<-append(score.vector,adj.score)}";
            
            c.eval(st);
            
            c.eval("score.vector[score.vector==Inf]<-0.0000");
            
            slices  = c.eval("slices<-score.vector").asDoubles();
            
            labels= c.eval("labels<-names(dictionary)").asStrings();
            
            //REXP labelSlices =c.eval("labels.slices<-data.frame(labels=labels,slices=slices)");
           
//            
//            for(int i=0;i<labels.length;i++){            	
//            	
//            	System.out.println(labels[i]+slices[i]);           
//            	
//            }  
            
            
            
            System.out.println("complete");
            c.close();
} catch (Exception e) {
            System.out.println(e);
		
        }       

    }
}