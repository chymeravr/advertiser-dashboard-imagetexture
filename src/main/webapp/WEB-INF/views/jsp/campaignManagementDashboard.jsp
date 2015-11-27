<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<tiles:insertDefinition name="advertiser_dashboard">
	<tiles:putAttribute name="sidebar">
		<div id="sidebar-wrapper">
			<div id="allCampaigns" class="list-heading pointer-on-hover">All Campaigns</div>
			<ul class="sidebar-nav">
				<c:forEach var="campaign" items="${campaignTree}">
					<li class="sidebar pointer-on-hover" id="campaign_${campaign.campaignId}">${campaign.name}</li>
					<ul class="sidebar-sub-nav">
						<c:forEach var="adGroup" items="${campaign.adGroups}">
							<li class="sidebar-sub pointer-on-hover" id="adgroup_${adGroup.adGroupId}">${adGroup.name}</li>
						</c:forEach>
					</ul>
				</c:forEach>
<!-- 				<li class="sidebar-brand sidebar"><a href="#">Home</a></li> -->
			</ul>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="topbar">
		<ul id="topbar" class="nav nav-tabs">
			<li id="campaign" class="active topbar"><a href="#">Campaign</a></li>
			<li id="adgroup" class="topbar"><a href="#">Ad Group</a></li>
			<li id="ad" class="topbar"><a href="#">Ad</a></li>
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
						<th class="campaign">Campaign</th>
						<th class="budget">Budget</th>
						<th class="impr">Impr</th>
						<th class="interactions">Interactions</th>
						<th class="interaction-rate">Interaction rate</th>
						<th class="avg-cost">Avg. Cost</th>
						<th class="cost">Cost</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="campaign">Campaign</td>
						<td class="budget">100</td>
						<td class="impr">1000</td>
						<td class="interactions">0.6</td>
						<td class="interaction-rate">13</td>
						<td class="avg-cost">13</td>
						<td class="cost">300</td>
					</tr>
					<tr>
						<td class="campaign">Campaign</td>
						<td class="budget">100</td>
						<td class="impr">1000</td>
						<td class="interactions">0.6</td>
						<td class="interaction-rate">13</td>
						<td class="avg-cost">13</td>
						<td class="cost">300</td>
					</tr>
					<tr>
						<td class="campaign">Campaign</td>
						<td class="budget">100</td>
						<td class="impr">1000</td>
						<td class="interactions">0.6</td>
						<td class="interaction-rate">13</td>
						<td class="avg-cost">13</td>
						<td class="cost">300</td>
					</tr>
				</tbody>
			</table>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="css">
		<style>
			ul.sidebar-sub-nav{
				margin-left: 10px;
			}
		</style>
	</tiles:putAttribute>
	<tiles:putAttribute name="js">
		<script type="text/javascript">
			var sidebarState = "campaign";
			var topbarState = "campaign";
			var selectedCampaignId;
			var selectedAdGroupId;
			var wrapperPadding = $("div#wrapper").css("padding-left");
			var advertiserId = "3234";
			var actionUrl = "/advertiser/dashboard/cm/ui/_ac";
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
					if(topbarState === "campaign"){
						var url = actionUrl + "/gacf";
						window.location.href = url;
						/*$.ajax({
							url: url,
							success: success,
							fail: fail,
							method: "GET"
						});*/
					}
					else if(topbarState === "adgroup"){
						var url = actionUrl + "/gagf";
						if(sidebarState === "adgroup")
							url += "?cid="+selectedCampaignId;
						window.location.href = url;
					}
					else if(topbarState === "ad"){
						var url = actionUrl + "/gadf";
						if(sidebarState === "ad")
							url += "?agid="+selectedAdGroupId;
						window.location.href = url;
					}
				});
				$("li.sidebar").click(function(event){
					$("li.topbar").removeClass("active");
					$("li#campaign").hide();
					$("li#adgroup").addClass("active");
					sidebarState = "adgroup";
					topbarState = "adgroup";
					$("#addButton").html("+ AdGroup");
					selectedCampaignId = $(this).attr("id").split("_")[1];
				});
				$("li.sidebar-sub").click(function(event){
					$("li.topbar").removeClass("active");
					$("li#campaign").hide();
					$("li#adgroup").hide();
					$("li#ad").addClass("active");
					sidebarState = "ad";
					topbarState = "ad";
					$("#addButton").html("+ Ad");
					selectedAdGroupId = $(this).attr("id").split("_")[1];
				});
				$("div#allCampaigns").click(function(event){
					sidebarState = "campaign";
					$("li#campaign").show();
				})
				$("li#campaign").click(function(event){
					$("li.topbar").removeClass("active");
					$(this).addClass("active");
					topbarState = "campaign";
					$("#addButton").html("+ Campaign");
				});
				$("li#adgroup").click(function(event){
					$("li.topbar").removeClass("active");
					$(this).addClass("active");
					topbarState = "adgroup";
					$("#addButton").html("+ AdGroup");
				});
				$("li#ad").click(function(event){
					$("li.topbar").removeClass("active");
					$(this).addClass("active");
					topbarState = "ad";
					$("#addButton").html("+ Ad");
				});
			});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>