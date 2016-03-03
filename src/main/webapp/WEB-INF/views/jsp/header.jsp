<%@page import="org.springframework.context.annotation.Import"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>

<nav class="navbar navbar-default" role="navigation">

	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#example-navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>

		<a class="navbar-brand" href="#" style="padding-top: 5px;">
			<div>Chimera AdVR</div>
			<div style="font-size: 0.7em; float: right;">Advertiser</div>
		</a>
	</div>

	<div class="collapse navbar-right navbar-collapse"
		id="example-navbar-collapse">

		<ul class="nav navbar-nav">
			<li class="active">
				<a href="#">Dashboard</a>
			</li>
			<li>
				<a href="#"> Reports</a>
			</li>

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> Account <b class="caret"></b>
			</a>

				<ul class="dropdown-menu">
					<li><a href='<c:url value="/j_spring_security_logout"/>'>Logout</a></li>
<!-- 					<li><a href="#">EJB</a></li> -->
<!-- 					<li><a href="#">Jasper Report</a></li> -->

<!-- 					<li class="divider"></li> -->
<!-- 					<li><a href="#">Separated link</a></li> -->

<!-- 					<li class="divider"></li> -->
<!-- 					<li><a href="#">One more separated link</a></li> -->
				</ul></li>

		</ul>
	</div>
</nav>