<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/views/includes/taglibs.jsp"%>
<c:url var="post_url" value="/dashboard/cm/addAd" />
<c:url var="static_url" value="/static" />

<tiles:insertDefinition name="advertiser_dashboard">
	<tiles:putAttribute name="maincontent">
		<form role="form" method="POST" action="${post_url}"
			class="creation-form" id="adForm" enctype="multipart/form-data">
			<div class="row">
				<div class="col-md-3">
					<h3>Create Ad:</h3>
				</div>
			</div>
			<hr class="medium" />
			<c:choose>
				<c:when test="${not empty campaignTree}">
					<div class="row">
						<div class="col-md-2 right-align">
							<label for="adGroup">Select campaign and ad group</label>
						</div>
						<div class="col-md-3">
							<select class="form-control" name="ad.adGroup.adGroupId"
								id="adgroup">
								<c:forEach var="campaign" items="${campaignTree}" varStatus="loop">
									<optgroup label="${campaign.name}">
										<c:forEach var="adGroup" items="${campaign.adGroups}" >
											<option value="${adGroup.adGroupId}">${adGroup.name}</option>
										</c:forEach>
									</optgroup>
								</c:forEach>
							</select>
						</div>
					</div>
				</c:when>
				<c:when test="${not empty adGroups}">
					<div class="row">
						<div class="col-md-2 right-align">
							<label for="adGroup">Select Adgroup</label>
						</div>
						<div class="col-md-3">
							<select class="form-control" name="ad.adGroup.adGroupId"
								id="adgroup">
								<c:forEach var="item" items="${adGroups}" varStatus="loop">
									<option value="${item.adGroupId}">${item.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<input name="ad.adGroup.adGroupId" type="hidden" value="${adGroupId}" />
				</c:otherwise>
			</c:choose>
			<div class="row">
				<div class="col-md-2 right-align">
					<label for="adName">Ad Name</label>
				</div>
				<div class="col-md-3">
					<input class="form-control" name="ad.name" id="adName"
						type="text" placeholder="Ad #1" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 right-align">
					<label for="resolution">Image Resolution</label>
				</div>
				<div class="col-md-3">
					<select class="form-control" name="resolution" id="resolution">
						<c:forEach var="item" items="${imageFormats}">
							<option value="${item.width}x${item.height}">${item.width} x ${item.height}</option>
						</c:forEach>
					</select>
				</div>
			</div>	
			<input type="hidden" name="ad.resolutionWidth" id="resolutionWidth" value="" />
			<input type="hidden" name="ad.resolutionHeight" id="resolutionHeight" value="" />
			<div class="row">
				<div class="col-md-2 right-align">
					<label for="imageuploadinput">Image</label>
				</div>
				<div class="col-md-3">
					<input class="form-control" name="file" id="imageuploadinput" type="file" />
				</div>
				<div id="imageUploadPreview" class="col-md-3">
				</div>
			</div>
			<div class="row">
				<div class="col-md-1">
					<input type="submit" class="btn btn-md btn-primary btn-block"
						id="adSave" value="Save" />
				</div>
				<div class="col-md-1">
					<div class="btn btn-md btn-warning btn-block" id="cancelButton">Cancel</div>
				</div>
			</div>
		</form>
	</tiles:putAttribute>
	<tiles:putAttribute name="css">
		<style>
			#imageUploadPreview > img{
				width:300px;
			}
		</style>
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
			
			$(document).ready(function(){
				var resolutionWidth;
				var resolutionHeight;
				$('#adSave').click(function(event) {
					event.preventDefault();
					$("span.error").remove();
					$(".error").toggleClass("error");
		
					var elem;
					var errors = 0;
					if (isEmpty(elem = $('#adName'))) {
						displayError(elem,
								"Please give a name to your new ad");
						errors++;
					}
					//fill resolution data
					var resolutionData = $("select#resolution").val();
					resolutionWidth = resolutionData.split("x")[0];
					resolutionHeight = resolutionData.split("x")[1];
					$("input#resolutionWidth").val(resolutionWidth);
					$("input#resolutionHeight").val(resolutionHeight);
					if (errors == 0) {
						$('#adForm').submit();
					}
				});
				$('#cancelButton').click(function(event){
					window.location.href = "/advertiser/dashboard/cm/ui/_ac/gcmt";
				});
				$("#imageuploadinput").change(function(event){
					var imageInput = this;
					$('#imageUploadPreview').find("img").remove();
					function processfile(file) {
						if( !( /image/i ).test( file.type ) )
						{
							alert( "File "+ file.name +" is not an image." );
							return false;
						}
						var reader = new FileReader();
						reader.readAsDataURL(file);
			
						reader.onload = function (event) {
							var image = new Image();
							image.src = event.target.result;
							image.onload = function() {
								var width = image.width;
								var height = image.height;
								var type = file.type;
								var filename = file.name;
								var size = file.size;//in Bytes
								var resolutionData = $("select#resolution").val();
								resolutionWidth = resolutionData.split("x")[0];
								resolutionHeight = resolutionData.split("x")[1];
								if(resolutionWidth!=width || resolutionHeight!=height){
									alert( "File "+ file.name +" is of the resolution "+width+"X"+height+". Your chosen resolution is "+resolutionWidth+"X"+resolutionHeight+"." );
									$(imageInput).val('');
									return false;
								}
								$('#imageUploadPreview').append('<img src="'+ this.src +'"> ');
							}
						};
					}
					processfile(imageInput.files[0]);
				});
				$("div#wrapper").css("padding-left", "0px");
			});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>