var form = {
	clearTextClass: null,
	clearDivTree: null
};
$(function() {
    form.clearTextClass	= function(cleanType){
    	$.each($("label.error"),function(index,element){
    		if($(element).hasClass("error")){
    			$(element).attr("style","display: none"); 
    		}
    	});
    	$.each($(":input"),function(index,element){
    		if($(element).hasClass("error")){
    			$(element).removeClass("error");
    		}
    	});
    	if (cleanType===1) {
    		$('#resetButnId').click();     
    	}      
    };
    
    form.clearDivTree	= function(divId){
    	if (divId != null) {
    		$("#"+divId).empty();
    	}
    };

});




