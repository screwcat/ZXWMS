/*
 * 每个页面加载默认执行的方法
 */
$(function() {
	$("#left-center").ligerLayout(
			{
				leftWidth : ($("#left-center").attr("leftwidth") ? parseInt($("#left-center").attr("leftwidth")) : 200),
				allowLeftCollapse : false,
				allowRightCollapse : false,
				allowLeftResize : false,
				allowRightResize : false,
				rightWidth: ($("#left-center").attr("rightwidth") ? parseInt($("#left-center").attr("rightwidth")) : 250)
			});
	if (global_param.session_check) {
		var url = window.location.href;
		var chechFlag = true;
		if (url.indexOf("login.html") > -1 || url.indexOf("top.html") > -1 || url.indexOf("index.html") > -1) {
			chechFlag = false;
		};
		if (chechFlag) {
			globalUtil.checkSession();
		}
	}
	$("div").ajaxError(function(e, xhr, opt) {
		if(xhr.readyState == 4){
			globalUtil.errorMsg(globalErrorMsg['100093']);
		}
		parent.window.globalUtil.closeAllInterval();
		return false;
	});

	$("input[class!='l-text-field'], textarea").bind("click", function() {
		globalUtil.clearAllCombo();
	});		
	$("td[tabname='mytab']").bind("click", function() {
		globalUtil.clearAllCombo();
	});		
    $("div[name='suofang']").toggle(function(){
    	var a = $(this).parent().siblings();
    	a.slideUp(500);
    	$(this).attr('class','show-hide l-icon-show');
    },function(){
    	if($(this).parent().find(':input[value=0]').attr('checked')){
    		var a = $(this).parent().siblings(':not(.minwidth400)');
        	a.slideDown(500);
        	$(this).attr('class','show-hide l-icon-hide');	
    	}else{
    		var a = $(this).parent().siblings();
        	a.slideDown(500);
        	$(this).attr('class','show-hide l-icon-hide');	
        }  	
    });
    
    $("div[name='suofang']").mouseover(function(){
    	
    	if($(this).attr('class') == "show-hide l-icon-show")
    	{
    		
    		$(this).attr('class','show-hide l-icon-show-over');
    	}
    	else if($(this).attr('class') == "show-hide l-icon-hide")
    	{
    		
    		$(this).attr('class','show-hide l-icon-hide-over');
    	}
    });
    $("div[name='suofang']").mouseout(function(){
    	if($(this).attr('class') == "show-hide l-icon-show-over")
    	{
    		$(this).attr('class','show-hide l-icon-show');
    	}
    	else if($(this).attr('class') == "show-hide l-icon-hide-over")
    	{
    		$(this).attr('class','show-hide l-icon-hide');
    	}
    });
    //滚动条滚动时清楚表格的编辑模式和下拉框
    $("div").scroll(function(){
    	globalUtil.clearAllCombo();
    	//$('.l-grid-editor .l-text-combobox').hide();
    	$('.l-panel').each(function(){
    		var id = this.id;
    		var grid = $("#"+id).ligerGetGridManager();
    		if(grid){
    			grid.endEdit();
    		}
    	});
    });
});