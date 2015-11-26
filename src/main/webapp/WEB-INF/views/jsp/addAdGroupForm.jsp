<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<c:url var="post_url" value="/dashboard/cm/addAdGroup" />
<c:url var="static_url" value="/static" />

<tiles:insertDefinition name="advertiser_dashboard">
	<tiles:putAttribute name="maincontent">
		<form role="form" method="POST" action="${post_url}"
			class="creation-form" id="adgroupForm">
			<div class="row">
				<div class="col-md-3">
					<h3>Add Ad Group:</h3>
				</div>
			</div>
			<hr class="medium" />
			<c:choose>
				<c:when test="${empty campaignId}">
					<div class="row">
						<div class="col-md-2 right-align">
							<label for="campaign">Select Campaign</label>
						</div>
						<div class="col-md-3">
							<select class="form-control" name="campaign.campaignId"
								id="campaign">
								<c:forEach var="item" items="${campaigns}" varStatus="loop">
									<option value="${item.campaignId}">${item.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<input name="campaign.campaignId" type="hidden" value="${campaignId}" />
				</c:otherwise>
			</c:choose>
			<div class="row">
				<div class="col-md-2 right-align">
					<label for="adgroupName">Adgroup Name</label>
				</div>
				<div class="col-md-3">
					<input class="form-control" name="name" id="adgroupName"
						type="text" placeholder="AdGroup #1" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 right-align">
					<label for="bid">Bid (INR per day)</label>
				</div>
				<div class="col-md-2">
					<input class="form-control" name="bid" id="bid" placeholder="10"
						type="text" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-1">
					<input type="submit" class="btn btn-md btn-primary btn-block"
						id="adgroupSave" value="Save" />
				</div>
				<div class="col-md-1">
					<div class="btn btn-md btn-warning btn-block" id="cancelButton">Cancel</div>
				</div>
			</div>
		</form>
	</tiles:putAttribute>
	<tiles:putAttribute name="js">
		<script type="text/javascript">
			function displayError(elem, errorMsg) {
				$("<span>" + errorMsg + "</span>").insertAfter(elem)
						.toggleClass("error");
				elem.addClass("error");
			}
			function isEmpty(elem) {
				if (!elem.val())
					return true;
				else
					return false;
			}
			function isPositiveDecimal(elem) {
				var number = elem.val();
				var pattern = /^[0-9]+([,.][0-9]+)?$/;
				var isnum = pattern.test(number);
				if (!isnum)
					return false;
				var floatnum = parseFloat(number);
				if (floatnum > 0)
					return true;
				else
					return false;
			}
			$('#adgroupSave').click(function(event) {
				event.preventDefault();
				$("span.error").remove();
				$(".error").toggleClass("error");
	
				var elem;
				var errors = 0;
				if (isEmpty(elem = $('#adgroupName'))) {
					displayError(elem,
							"Please give a name to your new adgroup");
					errors++;
				}
				if (isEmpty(elem = $('#bid'))) {
					displayError(elem, "Bid cannot be empty");
					errors++;
				}
				if (!isPositiveDecimal(elem = $('#bid'))) {
					displayError(elem,
							"Please enter a valid non-zero amount");
					errors++;
				}
				if (errors == 0) {
					$('#adgroupForm').submit();
				}
			});
			$('#cancelButton').click(function(event){
				window.location.href = "/advertiser/dashboard/cm/ui/_ac/gcmt";
			});
			$(document).ready(function(){
				$("div#wrapper").css("padding-left", "0px");
			});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>