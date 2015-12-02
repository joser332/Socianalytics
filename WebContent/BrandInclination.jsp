<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7 ]><html class='ie ie6' lang='en'> <![endif]-->
<!--[if IE 7 ]><html class='ie ie7' lang='en'> <![endif]-->
<!--[if IE 8 ]><html class='ie ie8' lang='en'> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang='en'> <!--<![endif]-->
<head>
<meta charset='utf-8'>

<title>Brand Inclination</title>
<meta name='description' content='Project 01 detail view'>
<meta name='author' content='Ivan Designostrom'>
<meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1'>

<!-- CSS
================================================================================================= -->
<link rel='stylesheet' href='css/base.css'>
<link rel='stylesheet' href='css/themes/type_05.css'>
<link rel='stylesheet' href='css/themes/color_29.css'>

<!--[if lt IE 9]>
	<script src='http://html5shim.googlecode.com/svn/trunk/html5.js'></script>
<![endif]-->

<!-- Favicons
============================================================================================= -->
<link rel='shortcut icon' href='../../images/favicons/favicon2.ico'>
<link rel='apple-touch-icon' href='../../images/favicons/apple-touch-icon.png'>
<link rel='apple-touch-icon' sizes='72x72' href='../../images/favicons/apple-touch-icon-72x72.png'>
<link rel='apple-touch-icon' sizes='114x114' href='../../images/favicons/apple-touch-icon-114x114.png'>

<!-- JS
================================================================================================= -->
<script src='../../js/libs/modernizr.min.js'></script>
<script src='../../js/libs/jquery-1.8.3.min.js'></script>
<script src='../../js/libs/jquery.easing.1.3.min.js'></script>
<script src='../../js/libs/jquery.fitvids.js'></script>
<script src='../../js/script.js'></script>
</head>
<body>

<!-- Write preloader to page - this allows the site to load for users with JS disabled -->
<script type='text/javascript'>
	document.write('<div id='sitePreloader'><div id='preloaderImage'><img src='../../images/site_preloader.gif' alt='Preloader' /></div></div>');
</script>

<div class='container'>
	
	<!-- Header begins ========================================================================== -->
	<header class='sixteen columns'>
		<div id='logoDetailView'>
			<h1>Brand Inclination</h1>
			<h2><% 
			String search=request.getParameter("search");
			out.print(search);%>
			</h2>
			<!--
			<img src='images/logo.png' width='275' height='35' alt='Logo' />
			-->
		</div>
		<nav>
			<ul>
				<li><a href='../../Socianalytics/index.html'>&laquo; Back</a></li>
			</ul>
		</nav>
		<hr />
	</header>
	<!-- Header ends ============================================================================ -->
	
	<div id='detailView' class='sixteen columns'  >		
		<center>
		<img src='images/<% 
			String brand=request.getParameter("brand");
			out.print(brand);%>.jpg' Style="width:460px;height:284px;">			
		</center>
		<div id='detailViewBack'>
		<br>
			<a href='../../index.html'>&laquo; Back</a>
		</div>
		
	</div>
	
	<!-- Footer begins ========================================================================== -->
	<footer class='sixteen columns'>
		<hr />
		<ul id='footerLinks'>
			<li>&copy; 2015 IBM Running Champions. All rights reserved.</li>
			<li><a href='mailto:mishnish@in.ibm.com'>Contact Us</a></li>
			
		</ul>
	</footer>
	<!-- Footer ends ============================================================================ -->
	
</div><!-- container -->
</body>
</html>