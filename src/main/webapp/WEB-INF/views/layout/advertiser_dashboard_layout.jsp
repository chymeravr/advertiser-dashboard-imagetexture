<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<c:url var="static_url" value="/static" />

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ=="
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"
	integrity="sha384-aUGj/X2zp5rLCbBxumKTCw2Z50WgIr1vs/PFN4praOTvYXWlVyh2UtNUU0KAUhAX"
	crossorigin="anonymous">

<link rel="stylesheet" href="${static_url}/css/dashboard-main.css" />
<tiles:insertAttribute name="css" ignore="true" />
<title>ChimeraLabs | Advertiser Dashboard</title>

</head>

<body role="document">
	<tiles:insertAttribute name="header" ignore="true" />
	<div id="wrapper">
		<tiles:insertAttribute name="sidebar" ignore="true" />
		<div id="page-content-wrapper">
			<div class="page-content">
				<div class="row">
					<div class="col-md-12">
						<tiles:insertAttribute name="topbar" />
						<div id="maincontent">
							<tiles:insertAttribute name="maincontent" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<tiles:insertAttribute name="footer" ignore="true" />
	<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
		integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ=="
		crossorigin="anonymous"></script>

	<tiles:insertAttribute name="js" ignore="true" />

</body>
</html>