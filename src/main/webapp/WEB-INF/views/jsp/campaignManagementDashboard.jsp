<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<c:url var="static_url" value="/static" />

<tiles:insertDefinition name="advertiser_dashboard">
	<tiles:putAttribute name="sidebar">
		<div id="sidebar-wrapper">
			<div id="allCampaigns" class="list-heading pointer-on-hover">All
				Campaigns</div>
			<ul class="sidebar-nav">
				<c:forEach var="campaign" items="${campaignTree}">
					<li class="sidebar pointer-on-hover"
						id="campaign_${campaign.campaignId}">${campaign.name}</li>
					<ul class="sidebar-sub-nav">
						<c:forEach var="adGroup" items="${campaign.adGroups}">
							<li class="sidebar-sub pointer-on-hover"
								id="adgroup_${adGroup.adGroupId}">${adGroup.name}</li>
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
		<div class="table-action-list">
			<div class="btn btn-success table-action-button" id="addButton">+
				Campaign</div>
			<div class="dropdown table-action-button">
				<button class="btn btn-default dropdown-toggle" type="button"
					id="rowActionDropdownMenu" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="true">
					Action <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" aria-labelledby="rowActionDropdownMenu">
					<li><a href="#" id="rowActivate">Activate</a></li>
					<li><a href="#" id="rowDeactivate">Deactivate</a></li>
					<li><a href="#" id="rowDelete">Remove</a></li>
				</ul>
			</div>
		</div>
		<div class="main-table-container">
			<p></p>
			<table id="dataTable">
			</table>
		</div>
	</tiles:putAttribute>
	<tiles:putAttribute name="css">
		<link rel="stylesheet"
			href="${static_url}/bootstrap-table-master/dist/bootstrap-table.min.css">
		<style>
ul.sidebar-sub-nav {
	margin-left: 10px;
}
</style>
	</tiles:putAttribute>
	<tiles:putAttribute name="js">
		<script
			src="${static_url}/bootstrap-table-master/dist/bootstrap-table.min.js"></script>
		<script src="${static_url}/js/common.functions.js"></script>
		<script type="text/javascript">
			var sidebarState = "campaign";
			var topbarState = "campaign";
			var selectedCampaignId;
			var selectedAdGroupId;
			var wrapperPadding = $("div#wrapper").css("padding-left");
			var advertiserId = "3234";
			var actionUrl = "/advertiser/dashboard/cm/ui/_ac";
			var dataUrl = "/advertiser/dashboard/cm/data/_ac";

			function campaignNameFormatter(value, row) {
				return "<a class='campaign' href='#' id='" + row.cid + "'>"
						+ row.campaignName + "</a>";
			}
			function adgroupNameFormatter(value, row) {
				return "<a class='adgroup' href='#' id='" + row.agid + "'>"
						+ row.adGroupName + "</a>";
			}
			function adFormatter(value, row) {
				return "<h4 class='ad' id='"+row.adId+"'>"
						+ row.adName
						+ "</h4><img style='width:100px'  src='" + row.adImageUrl + "' />";
			}
			function getAndDisplayData() {
				if (topbarState === "campaign") {
					var url = dataUrl + "/gcd";
					$('#dataTable').bootstrapTable('destroy').bootstrapTable({
						method : 'get',
						url : url,
						striped : false,
						pagination : false,
						pageSize : 50,
						pageList : [ 10, 25, 50, 100, 200 ],
						search : false,
						showColumns : false,
						showRefresh : false,
						minimumCountColumns : 2,
						columns : [ {
							field : 'cid',
							visible : false
						}, {
							field : 'state',
							checkbox : true
						}, {
							field : 'campaignName',
							title : 'Campaign',
							align : 'left',
							valign : 'middle',
							formatter : campaignNameFormatter
						}, {
							field : 'budget',
							title : 'Budget',
							align : 'left',
							valign : 'middle'
						}, {
							field : 'interaction',
							title : 'Interaction',
							align : 'left',
							valign : 'middle'
						}, {
							field : 'interactionRate',
							title : 'Interaction Rate',
							align : 'left',
							valign : 'middle'
						}, {
							field : 'avgCost',
							title : 'Avg. Cost',
							align : 'left',
							valign : 'middle'
						}, {
							field : 'cost',
							title : 'cost',
							align : 'left',
							valign : 'middle'
						} ]
					});
				} else if (topbarState === "adgroup") {
					var url = dataUrl + "/gagd";
					if (sidebarState === "adgroup")
						url += "?cid=" + selectedCampaignId;
					var showCampaign = false;
					if (sidebarState === "campaign")
						showCampaign = true;
					$('#dataTable').bootstrapTable('destroy').bootstrapTable({
						method : 'get',
						url : url,
						striped : false,
						pagination : false,
						pageSize : 50,
						pageList : [ 10, 25, 50, 100, 200 ],
						search : false,
						showColumns : false,
						showRefresh : false,
						minimumCountColumns : 2,
						columns : [ {
							field : 'cid',
							visible : false
						}, {
							field : 'agid',
							visible : false
						}, {
							field : 'state',
							checkbox : true
						}, {
							field : 'adGroupName',
							title : 'AdGroup',
							align : 'left',
							valign : 'middle',
							formatter : adgroupNameFormatter
						}, {
							field : 'campaignName',
							title : 'Campaign',
							align : 'left',
							valign : 'middle',
							formatter : campaignNameFormatter,
							visible : showCampaign
						}, {
							field : 'bid',
							title : 'Bid',
							align : 'left',
							valign : 'middle'
						}, {
							field : 'interaction',
							title : 'Interaction',
							align : 'left',
							valign : 'middle'
						}, {
							field : 'interactionRate',
							title : 'Interaction Rate',
							align : 'left',
							valign : 'middle'
						}, {
							field : 'avgCost',
							title : 'Avg. Cost',
							align : 'left',
							valign : 'middle'
						}, {
							field : 'cost',
							title : 'cost',
							align : 'left',
							valign : 'middle'
						} ]
					});

				} else if (topbarState === "ad") {
					var url = dataUrl + "/gaad";
					if (sidebarState === "ad")
						url += "?agid=" + selectedAdGroupId;
					else if (sidebarState === "adgroup")
						url += "?cid=" + selectedCampaignId;
					var showCampaign = false;
					var showAdGroup = false;
					if (sidebarState === "campaign") {
						showCampaign = true;
						showAdGroup = true;
					}
					if (sidebarState === "adgroup")
						showAdGroup = true;
					$('#dataTable').bootstrapTable('destroy').bootstrapTable({
						method : 'get',
						url : url,
						striped : false,
						pagination : false,
						pageSize : 50,
						pageList : [ 10, 25, 50, 100, 200 ],
						search : false,
						showColumns : false,
						showRefresh : false,
						minimumCountColumns : 2,
						columns : [ {
							field : 'cid',
							visible : false
						}, {
							field : 'agid',
							visible : false
						}, {
							field : 'adid',
							visible : false
						}, {
							field : 'state',
							checkbox : true
						}, {
							field : 'adName',
							title : 'Ad',
							align : 'left',
							valign : 'middle',
							formatter : adFormatter
						}, {
							field : 'adGroupName',
							title : 'AdGroup',
							align : 'left',
							valign : 'middle',
							formatter : adgroupNameFormatter,
							visible : showAdGroup
						}, {
							field : 'campaignName',
							title : 'Campaign',
							align : 'left',
							valign : 'middle',
							formatter : campaignNameFormatter,
							visible : showCampaign
						}, {
							field : 'interaction',
							title : 'Interaction',
							align : 'left',
							valign : 'middle'
						}, {
							field : 'interactionRate',
							title : 'Interaction Rate',
							align : 'left',
							valign : 'middle'
						}, {
							field : 'avgCost',
							title : 'Avg. Cost',
							align : 'left',
							valign : 'middle'
						}, {
							field : 'cost',
							title : 'cost',
							align : 'left',
							valign : 'middle'
						} ]
					});
				}
			}
			function rowAction(action){
				var ids = [];
				var target = "";
				var url = "/advertiser/dashboard/cm" + "/rowaction";//perform data row action
				if(topbarState==="campaign"){/*determine the target for the action*/
					$("table#dataTable tbody input:checked").each(function(){
						ids.push($(this).parent().parent().find(".campaign").attr("id"));
					});
					target="campaign";
				}
				else if(topbarState==="adgroup"){
					$("table#dataTable tbody input:checked").each(function(){
						ids.push($(this).parent().parent().find(".adgroup").attr("id"));
					});	
					target="adgroup";
				}
				else if(topbarState==="ad"){
					$("table#dataTable tbody input:checked").each(function(){
						ids.push($(this).parent().parent().find(".ad").attr("id"));
					});
					target="ad";
				}
				var jsonObj = new Object();
				jsonObj.action = action;
				jsonObj.target = target;
				jsonObj.idList = ids;
				$.ajax({
					url: url,
					success: rowActionSuccess,
					fail: rowActionFail,
					method: "POST",
					data: JSON.stringify(jsonObj),
					contentType: "application/json"
				});
				function rowActionSuccess(data){
					if(action === "delete")
						displayQuickNotification("Removed", 3000);
					if(action === "activate")
						displayQuickNotification("Action not supported currently", 3000);
					if(action === "deactivate")
						displayQuickNotification("Action no supported currently", 3000);
					getAndDisplayData();
				}
				function rowActionFail(){
					
				}
			}
			$(document).ready(
					function() {
						$("#addButton").click(function(event) {
							var url = "";
							/*function success(data){
								$("div#sidebar-wrapper").hide();
								$("ul#topbar").hide();
								$("div#wrapper").css("padding-left", "0px");
								$("div#maincontent").html(data);
								
							}
							function fail(){
								
							}*/
							if (topbarState === "campaign") {
								var url = actionUrl + "/gacf";
								window.location.href = url;
								/*$.ajax({
									url: url,
									success: success,
									fail: fail,
									method: "GET"
								});*/
							} else if (topbarState === "adgroup") {
								var url = actionUrl + "/gagf";
								if (sidebarState === "adgroup")
									url += "?cid=" + selectedCampaignId;
								window.location.href = url;
							} else if (topbarState === "ad") {
								var url = actionUrl + "/gadf";
								if (sidebarState === "ad")
									url += "?agid=" + selectedAdGroupId;
								else if (sidebarState === "adgroup")
									url += "?cid=" + selectedCampaignId;
								window.location.href = url;
							}
						});
						$("li.sidebar").click(
								function(event) {
									$("li.topbar").removeClass("active");
									$("ul#topbar > li").show();
									$("li#campaign").hide();
									$("li#adgroup").addClass("active");
									sidebarState = "adgroup";
									topbarState = "adgroup";
									$("#addButton").html("+ AdGroup");
									selectedCampaignId = $(this).attr("id")
											.split("_")[1];
									getAndDisplayData();
								});
						$("li.sidebar-sub").click(
								function(event) {
									$("li.topbar").removeClass("active");
									$("ul#topbar > li").show();
									$("li#campaign").hide();
									$("li#adgroup").hide();
									$("li#ad").addClass("active");
									sidebarState = "ad";
									topbarState = "ad";
									$("#addButton").html("+ Ad");
									selectedAdGroupId = $(this).attr("id")
											.split("_")[1];
									getAndDisplayData();
								});
						$("div#allCampaigns").click(function(event) {
							sidebarState = "campaign";
							$("ul#topbar > li").show();
							getAndDisplayData();
						})
						$("li#campaign").click(function(event) {
							$("li.topbar").removeClass("active");
							$(this).addClass("active");
							topbarState = "campaign";
							$("#addButton").html("+ Campaign");
							getAndDisplayData();
						});
						$("li#adgroup").click(function(event) {
							$("li.topbar").removeClass("active");
							$(this).addClass("active");
							topbarState = "adgroup";
							$("#addButton").html("+ AdGroup");
							getAndDisplayData();
						});
						$("li#ad").click(function(event) {
							$("li.topbar").removeClass("active");
							$(this).addClass("active");
							topbarState = "ad";
							$("#addButton").html("+ Ad");
							getAndDisplayData();
						});
						$("a#rowActivate").click(function(event){
							rowAction("activate");
						});
						$("a#rowDeactivate").click(function(event) {
							rowAction("deactivate");
						});
						$("a#rowDelete").click(function(event) {
							rowAction("delete");
						});
						$(document).on("click", "a.campaign", function(event) {
							event.preventDefault();
							$("li.topbar").removeClass("active");
							$("li#campaign").hide();
							$("li#adgroup").addClass("active");
							topbarState = "adgroup";
							sidebarState = "adgroup";
							selectedCampaignId = $(this).attr("id");
							getAndDisplayData();
							$("#addButton").html("+ AdGroup");
						});
						$(document).on("click", "a.adgroup", function(event) {
							event.preventDefault();
							$("li.topbar").removeClass("active");
							$("li#campaign").hide();
							$("li#adgroup").hide();
							$("li#ad").addClass("active");
							topbarState = "ad";
							sidebarState = "ad";
							selectedAdGroupId = $(this).attr("id");
							getAndDisplayData();
							$("#addButton").html("+ Ad");
						});
						getAndDisplayData();
					});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>