var menu = {
	loadContent : null, // 装载内容方法
	openTab : null // 点击菜单后的打开方法
};

$(function() {
	var menuid = '';
	var h = 800;
	
	menu.loadContent = function(divid, url, height, dataJson) {
		menuid = '#' + divid;
		h = height || 800;

		if(dataJson){
			createMenu(dataJson);
		}else{
			$.getJSON(url, function(data) {
				if(globalUtil.errorHandle(data)){
					createMenu(data);
					//$("#layout1").ligerLayout().setLeftCollapse(true);
				}
			});
		}
	};

	menu.openTab = function(url, text, id,remark) {
		window.open(url, "_blank");
	};
	
	menu.showMenu = function(menuId){
		var menu = document.getElementById("conf_menu"+menuId); 
		var image = document.getElementById("conf_img"+menuId);
		var isdisplay = menu.style.display;
		
		var menus = document.getElementsByName("conf_menu");
		var images = document.getElementsByName("conf_img");
		
		for(var i =0;i<menus.length;i++){
			menus[i].style.display = "none";
		}
		
		for(var i =0;i<images.length;i++){
			images[i].src="../images/menu_new/menu_icon.gif";
		}
		
		
		
		if(isdisplay == 'none'){//关闭到打开状态
			menu.style.display = "";
			image.src="../images/menu_new/menu_icon_open.gif";
		}else{//打开到关闭状态
			menu.style.display = "none";
			image.src="../images/menu_new/menu_icon.gif";
		}
		
		
		
		/*if( menu1.style.display == "")
		{
		 menu1.style.display = "none";
		 img1.src="../../images/menu_icon.gif";
		}
		else
		{
	     menu1.style.display = "";
		 img1.src="../../images/menu_icon_open.gif";
		}
		menu2.style.display = "none";
		menu3.style.display = "none";
		//img1.src="../../images/menu_icon_open.gif";
		img2.src="../../images/menu_icon.gif";
		img3.src="../../images/menu_icon.gif";*/
		
	}
		
	var createMenu = function(data) {
		var dataJson = data;
		if(dataJson.length == 0){
			return;
		}
		var menuList = dataJson[0].childrenTree;
		var htmlStr = getMenuTree(menuList);
		$(menuid).html(htmlStr);
	};
	
	var getMenuTree = function(dataJson){
		var htmlStr = '';
		var remark = '';
		$.each(dataJson, function(index, obj) {
			htmlStr += '<div  class="div_main">';
			var isdisplay = 'none';
			var image = '../images/menu_new/menu_icon.gif';
			if(index == 0){
				isdisplay= '';
				image = '../images/menu_new/menu_icon_open.gif';
			}
			if (obj.children) {
				htmlStr += '<div class="main_menu" onclick="menu.showMenu('+obj.id+');" >';
				htmlStr += '<img name="conf_img" id="conf_img'+obj.id+'" src="'+image+'" /><span>'+obj.text+'</span></div>';
				htmlStr += '<div name="conf_menu" id="conf_menu'+obj.id+'"  class="div_main" style="display:'+isdisplay+'">';
				
				
				$.each(obj.children, function(index, child) {
					//htmlStr += '<div class="second_menu_select" onclick="gotourl(\'myApply.html\')"> <span>新增客户</span></div>';
					htmlStr += '<div class="second_menu" onclick=\''
							+ 'menu.openTab("' + child.url + global_param.version
							+ '","'+child.text+'","'+child.id+'","'+remark+'");' + '\'><span>' + child.text + '</span></div>';
				});
				htmlStr += '</div>';
			}else{
				htmlStr += '<div class="second_menu" onclick=\''
					+ 'menu.openTab("' + obj.url + global_param.version
					+ '","'+obj.text+'","'+obj.id+'","'+remark+'");' + '\'><span>' + obj.text + '</span></div>';
			}
			htmlStr += '</div>';
		});
		return htmlStr;
	};

});