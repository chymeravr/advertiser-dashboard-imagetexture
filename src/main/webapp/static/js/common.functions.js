function displayQuickNotification(message ,timeout){
	var divElem = document.createElement("div");
	$("body").append(divElem);
	$(divElem).addClass("quicknotification");
	$(divElem).text(message).fadeIn("fast");
	setTimeout( function(){
		$(divElem).fadeOut("fast");
		$(divElem).remove();
	}, timeout);
}