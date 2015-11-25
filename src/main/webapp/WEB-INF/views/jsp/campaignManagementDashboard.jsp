<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<tiles:insertDefinition name="advertiser_dashboard">
	<tiles:putAttribute name="sidebar">
		<div id="sidebar-wrapper">
			<div class="list-heading">All Campaigns</div>
			<ul class="sidebar-nav">
				<li class="sidebar-brand"><a href="#">Home</a></li>
				<li><a href="#">Another link</a></li>
				<li><a href="#">Next link</a></li>
				<li><a href="#">Last link</a></li>
			</ul>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="topbar">
		<ul id="topbar" class="nav nav-tabs">
			<li class="active"><a href="#">Campaign</a></li>
			<li><a href="#">Ad Group</a></li>
			<li><a href="#">Ad</a></li>
		</ul>
	</tiles:putAttribute>
	<tiles:putAttribute name="maincontent">
		<h2>Bordered Table</h2>
		<div class="btn btn-success" id="addButton" >+ Campaign</div>
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
	</tiles:putAttribute>
	
	<tiles:putAttribute name="js">
		<script type="text/javascript">
			var sidebarState = "campaign";
			var topBarState = "campaign";
			var wrapperPadding = $("div#wrapper").css("padding-left");
			var advertiserId = "3234";
			var actionUrl = "/advertiser/dashboard/cm/ui/_ac/";
			$(document).ready(function(){
				$("#addButton").click(function(event){
					var url = "";
					/*function success(data){
						$("div#sidebar-wrapper").hide();
						$("ul#topbar").hide();
						$("div#wrapper").css("padding-left", "0px");
						$("div#maincontent").html(data);
						
					}
					function fail(){
						
					}*/
					if(topBarState === "campaign"){
						var url = actionUrl + "gacf";
						window.location.href = url;
						/*$.ajax({
							url: url,
							success: success,
							fail: fail,
							method: "GET"
						});*/
					}
					else if(topBarState === "adgroup"){
						
					}
					else if(topbarState === "ad"){
					}
				});
			});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>