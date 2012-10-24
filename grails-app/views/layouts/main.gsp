<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
		<div id="header" role="banner">
            <div id="logo">
                <a href=""><img src="${resource(dir: 'images', file: 'logo-190x75.png')}" alt="logo"/></a>
            </div>
            <div id="title">${message(code: 'app.title')}</div>
            <div id="menu">
                <a href="${createLink(controller: 'ball', action: 'list')}" class="button">${message(code: 'app.balls.title')}</a>
                <a href="#" class="button">${message(code: 'app.plans.title')}</a>
                <a href="#" class="button">${message(code: 'app.friends.title')}</a>
                <a href="${createLink(uri:'/status.gsp')}" class="button">Status</a>
            </div>
        </div>
        <div id="body">
		    <g:layoutBody/>
        </div>
        <div id="footer" role="contentinfo">
            &copy; 2012 by Bobby Out
		</div>

		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application"/>
		<r:layoutResources />
	</body>
</html>
