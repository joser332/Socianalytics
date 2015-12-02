#connect all libraries
 library(twitteR)
 library(ROAuth)
 library(plyr)
 library(dplyr)
 library(stringr)
 library(ggplot2)
 library(plotrix)
#connect to API

#the function for loading csv
 loading <- function(filename)
 {
   MyData <- read.csv(file=filename, header=TRUE, sep=",")
}

#for merging the tweets
mergeText<- function(mydata){
  mydata<-paste(mydata, collapse = '')
  
}


#cleaning and filtering the data
cleaning<-function(sentence){
  sentence <- gsub('[[:punct:]]', "", sentence)
  sentence <- gsub('[[:cntrl:]]', "", sentence)
  sentence <- gsub('\\d+', "", sentence)
  sentence <- tolower(sentence)
  sentence <- tolower(sentence)
}


#Main algorithm for scoring
scoring<-function(sentence,pos.words){
  
  word.list <- strsplit(sentence, '\\s+')
  words <- unlist(word.list)
  pos.matches <- match(words, pos.words)
  pos.matches <- !is.na(pos.matches)
  percentage.score<-(length(words[pos.matches])*100)/length(words)
  return(percentage.score)
}

#Ploting function
analytics<-function(slices,labels,main){
  pie3D(slices, labels = labels, main = main,radius=1.5, labelcex =.8,  start=0.1)  
}
