var tab = {
	tabObj : null, // tab控件的引用
	tabArray : [], // 已打开的tab的id数组，用于自动关闭最先打开的tab
	tabLimit : 1,  // 默认最多打开的tab数,如果设置为-1则不限制
	allTabs : {},  // 已打开的tab的idmap，如果准备打开的tab已经在列表中则不重新打开
	addTab : function(tabTitle, tabUrl, tabid, hideClose) {
		var url = tabUrl || 'tree.html';
		var text = tabTitle || 'tab';
		var tmpId = '';
		
		this.tabObj.removeAll();
		this.tabObj.addTabItem({
			tabid : tabid,
			text : text,
			url : url
		});
		
		/*
		if(!hideClose){
			hideClose = false;
		}
		
		if(this._showAlert(tabid)){
			
			// 如果限制数量,则自动关闭最先打开的tab
			if(this.tabLimit>0 && !this.allTabs[tabid]){
				if (this.tabArray.length >= this.tabLimit) {
					tmpId = this.tabArray.shift();
					this.tabObj.removeTabItem(tmpId);
					this.allTabs[tmpId] = false;
				}

				this.tabArray.push(tabid);
				this.allTabs[tabid] = true;
			}
			
			
			this.tabObj.removeAll();
			this.tabObj.addTabItem({
				tabid : tabid,
				text : text,
				url : url
				showClose: !hideClose
			});
		}
		*/
	},
	addSubTab : function(tabTitle, tabUrl, tabid, hideClose) {
		var url = tabUrl;
		var text = tabTitle || 'tab';
		if(!hideClose){
			hideClose = true;
		}
		this.tabObj.addTabItem_sub({
			tabid : tabid,
			text : text,
			url : url,
			showClose: !hideClose
		});
	},
	
	initTab : function(divid, height, tablimit) {
		this.tabLimit = tablimit || 7;
		this.tabObj = $("#" + divid).ligerTab({
			height : height || 600,
			onBeforeOverrideTabItem: function(tabID){
				if(parseInt(tabID)==145){
					globalUtil.closeAllInterval();
				}
			},
			onBeforeRemoveTabItem: function(tabID){
				if(parseInt(tabID)== 145){
					globalUtil.closeAllInterval();
				}
			}
		});
	},
	initTabSub : function(divid, height, tablimit) {
		this.tabLimit = tablimit || 7;
		this.tabObj = $("#" + divid).ligerTab({
			ischild:true,
			height : height || 600,
			onBeforeOverrideTabItem: function(tabID){
				if(parseInt(tabID)==145){
					globalUtil.closeAllInterval();
				}
			},
			onBeforeRemoveTabItem: function(tabID){
				if(parseInt(tabID)== 145){
					globalUtil.closeAllInterval();
				}
			}
		});
	},
	
	// 判断是否显示警告，第一次超出会显示警告，并不进行操作，今后不提示警告，这是通过控制一个全局标志位达到的
	_showAlert: function(tabid){
		if(this.tabLimit<0){
			return true;
		}
		if(!global_param.tab_limit_tip && this.tabArray.length >= this.tabLimit && !this.allTabs[tabid]){
			global_param.tab_limit_tip = true;
			$.ligerDialog.warn(global_param.tab_limit_tip_content);
			return false;
		}
		return true;
	}
};
