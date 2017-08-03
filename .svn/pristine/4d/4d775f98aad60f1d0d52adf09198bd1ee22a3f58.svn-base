var accordion = {
	loadContent : null, // 装载内容方法
	openTab : null // 点击菜单后的打开方法
};

$(function() {
	var menuid = '';
	var h = 800;
	
	accordion.loadContent = function(divid, url, height, dataJson) {
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

	accordion.openTab = function(url, text, id,remark) {
		window.open(url, "_blank");
	};
	
	var createMenu = function(data){
		var treeArr = fillContent(data);
		$(menuid).ligerAccordion({
			height : h,
			changeHeightOnResize : true
		});

		// 循环处理每一个菜单项下的树，初始化树控件
		for ( var index = 1; index <= treeArr.length; index++) {
			$("#menutree-" + index).ligerTree({
				clsBgColor: global_param.tree_menu_backcolor,
				data : treeArr[index - 1],
				checkbox : false,
				onBeforeCancelSelect: function(node){
					if (node.data.url) {
						accordion.openTab(node.data.url + global_param.version, node.data.text, node.data.id,node.data.remark);
					}
					return false;
				},
				nodeWidth : global_param.tree_menu_nodeWidth,
				btnClickToToggleOnly: false,
				onExpand: function(node) {
					this.setTreeWidth(global_param.layout_leftWidth - globalUtil.getTreeMaxLevel(this) * 22-24);
		        },
				onClick : function(node) {
					this.setTreeWidth(global_param.layout_leftWidth - globalUtil.getTreeMaxLevel(this) * 22-24);
					if (node!=null&&node.data!=null&&node.data.url) {
						accordion.openTab(node.data.url + global_param.version, node.data.text, node.data.id,node.data.remark);
					}
					
					var treeObj;
					for(var k=1;k <= treeArr.length; k++){
						if('menutree-'+k!==this.id){
							treeObj = $("#menutree-" + k);
							$('.l-body',treeObj).removeClass("l-selected");
						}
					}
				}
			});
		}
	};
	
	

	var fillContent = function(data) {
		var treeArr = [];
		var dataJson = data;
		var htmlStr = '';
		var treeIndex = 1;

		$.each(dataJson, function(index, obj) {
			htmlStr += '<div title="' + obj.text + '" icon='+obj.icon+'>';
			// 处理链接菜单,动态生成页面代码
			if (obj.children) {
				htmlStr += '<div>';
				$.each(obj.children, function(index, child) {
					htmlStr += '<a href="#" class="" onclick='
							+ 'javascript:accordion.openTab("' + child.url+ global_param.version
							+ '","'+child.text+'","'+child.id+'","'+child.remark+'");' + '>' + child.text + '</a><br/>';
				});
				htmlStr += '</div>';
			}
			// 处理树状菜单，动态生成树状控件需要处理的页面代码
			if (obj.childrenTree) {
				htmlStr += '<ul id="menutree-' + treeIndex + '"></ul>';
				treeIndex += 1;
				treeArr.push(obj.childrenTree);
			}
			htmlStr += '</div>';
		});

		$(menuid).html(htmlStr);

		return treeArr;
	};

});