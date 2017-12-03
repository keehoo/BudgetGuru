<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="uri" value="${req.requestURI}" />
<c:set var="url">${req.requestURL}</c:set>
<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html; charset=UTF-8" %>
	<meta charset="utf-8">
	<title>Lets_Code_2017</title>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />

	<script src="src/presentation.js"></script>
	<script src="src/raphael-min.js"></script>
	<script src="src/fuzzy-min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/testStyle.css">
</head>

<h1>Hello Lets Code 2017</h1>
<h2>Team Seven π sii'ów Wataha</h2>
<h3>Team Members:</h3>
<ul style="list-style-type:circle">
	  <li>Przemysław Kant</li>
	  <li>Piotr Wierzgała</li>
	  <li>Krzysztof Kubicki</li>
	  <li>Paweł Czapiewski</li>
</ul>

<body onLoad="presentation();">
	<h1>Input Variables:</h1>
	<div id="variables_input"></div>
	<hr>
	<h1>Output Variables:</h1>
	<div id="variable_output"></div>
	<hr>
	<h1>Inferences:</h1>
	<div id="inferences"></div>
	<hr>
	<h1>Input:</h1>
	<div id="input"></div>
	<hr>
	<input type="button" value="Calculate"
		   onClick="calculateFuzzy()">
	<span id="result"></span>
	<div id="draw_result"></div>

	<script>
		var calculateFuzzy = function(){
			document.getElementById('result').innerHTML='Result: '+fl.getResult(obj);
		}
		var obj = {
			crisp_input: [3, 8, 10],
			variables_input: [
				{
					name: "Seniorty",
					setsName: ["Junior", "Regular", "Senior"],
					sets: [
						[0,0,2,4],
						[1,2,7,8],
						[6,7,10,10]
					]
				},
				{
					name: "Team Spirit",
					setsName: ["Negative", "Ok", "Positive"],
					sets: [
						[0,0,2,4],
						[1,2,5,6],
						[6,7,8,10]
					]
				},
				{
					name: "Performance",
					setsName: ["Weak", "Ok", "Excelent"],
					sets: [
						[0,0,0,10],
						[0,10,10,30],
						[10,30,40,40]
					]
				}
			],
			variable_output: {
				name: "Salary",
				setsName: ["Salary is Low", "Selary is Medium", "Salary is High"],
				sets: [
					[2000,4000,5000,6000],
					[5000,6500,7000,7500],
					[6000,8000,10000,14000]
				]
			},
			inferences: [
				[0,1,2],
				[0,1,2],
				[0,1,2]
			]
		};
		var fl = new FuzzyLogic();
	</script>
</body>
</html>
