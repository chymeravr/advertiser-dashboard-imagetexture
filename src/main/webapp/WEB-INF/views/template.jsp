<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
	integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ=="
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"
	integrity="sha384-aUGj/X2zp5rLCbBxumKTCw2Z50WgIr1vs/PFN4praOTvYXWlVyh2UtNUU0KAUhAX"
	crossorigin="anonymous">

<link rel="stylesheet" href="../../static/css/dashboard-main.css" />
<title>ChimeraLabs | Advertiser Dashboard</title>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#example-navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

			<a class="navbar-brand" href="#">ChimeraLabs</a>
		</div>

		<div class="collapse navbar-right navbar-collapse"
			id="example-navbar-collapse">

			<ul class="nav navbar-nav">
				<li class="active"><a href="#">iOS</a></li>
				<li><a href="#">SVN</a></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> Java <b class="caret"></b>
				</a>

					<ul class="dropdown-menu">
						<li><a href="#">jmeter</a></li>
						<li><a href="#">EJB</a></li>
						<li><a href="#">Jasper Report</a></li>

						<li class="divider"></li>
						<li><a href="#">Separated link</a></li>

						<li class="divider"></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>

			</ul>
		</div>

	</nav>
	<div id="wrapper">
		<div id="sidebar-wrapper">
			<div class="list-heading">All Campaigns</div>
			<ul class="sidebar-nav">
				<li class="sidebar-brand"><a href="#">Home</a></li>
				<li><a href="#">Another link</a></li>
				<li><a href="#">Next link</a></li>
				<li><a href="#">Last link</a></li>
			</ul>
		</div>
		<div id="page-content-wrapper">
			<div class="page-content">
				<div>
					<div class="row">
						<div class="col-md-12">
							<!-- content of page -->
							<ul class="nav nav-tabs">
								<li><a href="#">Home</a></li>
								<li class="active"><a href="#">SVN</a></li>
								<li><a href="#">iOS</a></li>
								<li><a href="#">VB.Net</a></li>
								<li><a href="#">Java</a></li>
								<li><a href="#">PHP</a></li>
							</ul>
							<h2>Bordered Table</h2>
							<div class="btn btn-success">+ Campaign</div>
							<div class="main-table-container">
								<p></p>
								<table class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>Firstname</th>
											<th>Lastname</th>
											<th>Email</th>
											<th>Firstname</th>
											<th>Lastname</th>
											<th>Email</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>John</td>
											<td>Doe</td>
											<td>john@example.com</td>
											<td>John</td>
											<td>Doe</td>
											<td>john@example.com</td>
										</tr>
										<tr>
											<td>Mary</td>
											<td>Moe</td>
											<td>mary@example.com</td>
											<td>Mary</td>
											<td>Moe</td>
											<td>mary@example.com</td>
										</tr>
										<tr>
											<td>July</td>
											<td>Dooley</td>
											<td>july@example.com</td>
											<td>July</td>
											<td>Dooley</td>
											<td>july@example.com</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Latest compiled and minified JavaScript -->

	<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
		integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ=="
		crossorigin="anonymous"></script>
	<script>
		var url = "http://localhost:8080/adserver/publisher/api/loadad";
		$.ajax({
			  dataType: "json",
			  url: url,
			  data: "",
			  success: success
			});
		function success(data){
		}
	</script>
</body>
</html>