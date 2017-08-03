var wms_page = {
		//改变页码 operate为操作 是上一页  还是下一页 params为查询参数
		 changeCss:	function(params,paramsId){
			//firstPage首页   frontPage 上一页 nextPage 下一页 lastPage尾页
		    var maxPage=1;
			if(params.Total!=null&&params.pagesize!=null){
				maxPage=Math.ceil(params.Total/params.pagesize);
			}
			if(params.page=="1"||params.page==1||params.Total==0){ 
				$('#'+paramsId.frontPage).css("color","#ccc");
			}else{
				$('#'+paramsId.frontPage).css("color","");
			}
			if(params.page==maxPage||params.Total==0){ 
				$('#'+paramsId.nextPage).css("color","#ccc");
			}else{
				$('#'+paramsId.nextPage).css("color","");
			}
			$('#'+paramsId.page).text(params.page+''+'/'+'共'+maxPage+"页");
		},
		//改变页码 operate为操作 是上一页  还是下一页 params为查询参数
		changePage:	function (params,operate){
			var boolean=false;
			var maxPage=1;
			if(params.Total!=null&&params.pagesize!=null){
				maxPage=Math.ceil(params.Total/params.pagesize);
			}
			//firstPage   frontPage  nextPage  lastPage
			if(operate=="firstPage"){
				params.page=1;
			}else if(operate=="frontPage"){
				if(params.page>1){
					params.page=params.page-1;	
				}else{
					boolean=true;
				}
			}else if(operate=="nextPage"){
				if(params.page<maxPage){
					params.page=params.page+1;
				}else{
					boolean=true;
				}
			}else if(operate=="lastPage"){
				params.page=maxPage;
			}
			return boolean;
		},//查询后返回总条数和当前页码
		getPage :function (params,json,paramsId) {
			 params.page=json.page;//当前页码
			 params.Total=json.Total;//总条数
			 //改变页码 operate为操作 是上一页  还是下一页 params为查询参数
			 wms_page.changeCss(params,paramsId);
			 //remove();
		}
}