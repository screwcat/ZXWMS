var global_ligerui_extend = {
    /**
     * 下拉框
     */
		
	  /**
	   * 初始化下拉框，如果下拉框没有上级联动，将下拉框的值进行初始化
	   * 下拉框的值通过$("#"+inputObjName+"_hidden").val()的形式获取
	   * @param inputObjName 下拉框对应的input名称
	   * @param onSelectedFunc 值被选择的事件
	   * @param params json对象，可以包含如下的值：
	   * 1 valueField   下拉框value对应的值，默认为id
	   * 2 textField    下拉框text对应的值，默认为text
	   * 3 dest_url     下拉框data对应的url
	   * 4 t_col_name   下拉框对应的数据库表字段名称
	   * 5 p_input_name 上级下拉框对应的文本框名称，多个使用逗号,分隔
	   * 6 c_input_name 下级下拉框对应的文本框名称，多个使用逗号,分隔
	   * 7 def_val      下拉框的默认值，如果为''或者null，则联动后值置为空，如果为'first'，联动后置为下拉框的第一个值
	   *                其他则直接选择值
	   * 8 input_width 下拉框宽度，default=135
	   * 9 divheight   弹出下拉框的宽度，默认为null
	   * 10 autoChangeWidth 是否重新计算下拉框宽度，默认值为true；
	   * @returns
	   */
	  initCombox:function(inputObjName,onSelectedFunc,params){
		  var that = this;
		  var  manager = $("#"+inputObjName).ligerComboBox({
			  	data: [],
			    width:  params.input_width || 135,
			    valueField: params.valueField || 'id',
			   	textField: params.textField || 'text',
			   	dest_url:params.dest_url || '',
			   	t_col_name:params.t_col_name || '',
			   	p_input_name:params.p_input_name || '',
			   	c_input_name:params.c_input_name||'',
			   	divheight:params.divheight||null,
			    valueFieldID: inputObjName+'_hidden',
			    is_linkage:true, //是否执行联动内容
			    def_val:params.def_val||'',
			    autoChangeWidth:params.autoChangeWidth == 'false' ? false :true,
				onSelected: function(val){
					var options = this.options;
					//如果处于联动状态，且又下级下拉框，则进行联动
					if(options.is_linkage && $.trim(options.c_input_name)!=''){
						var c_input_name_arr = options.c_input_name.split(',');
						for(var i=0;i<c_input_name_arr.length;i++){
							that.asyncRequestInitComboxData(c_input_name_arr[i]);
						}
					}
					//如果处于联动状态,且有回调函数，则执行回调方法
					if(options.is_linkage && onSelectedFunc){
						onSelectedFunc.call(this,val);
					}
					
				}
			});
		  return manager;
	  },
	  /**
	   * 初始化树形下拉框，如果下拉框没有上级联动，将下拉框的值进行初始化
	   * @param inputObjName 下拉框对应的input名称
	   * @param onSelectedFunc 值被选择的事件
	   * @param params json对象，可以包含如下的值：
	   * 1 valueField   下拉框value对应的值，默认为id
	   * 2 textField    下拉框text对应的值，默认为text
	   * 3 dest_url     下拉框data对应的url
	   * 4 t_col_name   下拉框对应的数据库表字段名称
	   * 5 p_input_name 上级下拉框对应的文本框名称，多个使用逗号,分隔
	   * 6 c_input_name 下级下拉框对应的文本框名称，多个使用逗号,分隔
	   * * 7 def_val      下拉框的默认值，如果为''或者null，则联动后值置为空，如果为'first'，联动后置为下拉框的第一个值
	   *                其他则直接选择值
	   * @returns
	   */
	  initTreeCombox:function(inputObjName,onSelectedFunc,params){
		  var that = this;
		  var manager = $("#"+inputObjName).ligerComboBox({
			    width: params.input_width || 135,
			    selectBoxWidth: 130,
			    selectBoxHeight: 150,
			    valueField: params.valueField || 'id',
			   	textField: params.textField || 'text',
			    valueFieldID: inputObjName+'_hidden',
			    dest_url:params.dest_url || '',
			   	t_col_name:params.t_col_name || '',
			   	p_input_name:params.p_input_name || '',
			   	c_input_name:params.c_input_name||'',
			   	def_val:params.def_val||'',
			    treeLeafOnly: false,
			    tree: {
			        data: [],
			        nodeWidth: 133,
			        checkbox: false,
			        parentIcon: null,
			        childIcon: null,
			        onBeforeCancelSelect: function() {
			            return false;
			        }
			    },
				onSelected: function(val){
					var options = this.options;
					if(options.is_linkage && $.trim(options.c_input_name)!=''){
						var c_input_name_arr = options.c_input_name.split(',');
						for(var i=0;i<c_input_name_arr.length;i++){
							that.asyncRequestInitComboxData(c_input_name_arr[i]);
						}
					}
					if(options.is_linkage && onSelectedFunc){
						onSelectedFunc.call(this,val);
					}
				}
			});
		  return manager;
	  },
	  /**
	   * 同步获取后台数据，第一个参数固定，后面的参数为不固定参数，可以为一个或多个
	   * param:页面上各下拉框对应的值，应为后台数据库查询获得，与下拉框的t_col_name相对应
	   * inputObjName一个或多个需要设置下拉框数据和值的下拉框对应的文本框ID
	   */
	  syncRequestInitComboxData:function(param,inputObjNames){
		  var n = arguments.length;
		  var that = this;
		  for (var i = 1; i < n; i++) {
	          var inputObjName = arguments[i];
	          var comboxManage = $("#"+inputObjName).ligerGetComboBoxManager();
	          comboxManage.setLinkage(false);//取消联动
	          var options_1 = comboxManage.options;
	          var url = options_1.dest_url;//请求数据URL
	          var t_col_name = options_1.t_col_name;//该下拉框对应的数据库表字段名称
	          var defaultVal;
	          if(param == null){
	        	  defaultVal = options_1.def_val;
	          }else{
	        	  defaultVal = param[t_col_name]; 
	          }
	          var data = {};
	          var p_input_name = options_1.p_input_name;//该下拉框上一级对应的数据库字段名称
	          if(p_input_name != null && $.trim(p_input_name)!=''){
	        	  var paramArr = p_input_name.split(',');
	        	  for(var j=0;j<paramArr.length;j++){
	        		  var param_name = paramArr[j];
	        		  var obj = $("#"+param_name).ligerGetComboBoxManager();
	        		  var options = obj.options;
	        		  var pn = options.t_col_name;
	        		  if(param == null){
	        			  data[pn] = $("#"+param_name+"_hidden").val();
	    	          }else{
	    	        	  data[pn] = param[pn];
	    	          }
	        		  
	        		  
	        	  }
	          }
	          $.ajax({
	              type: "GET",
	              url: globalUtil.getTimestampUrl(url),
	              data: data,
	              async: false,
	              dataType: 'json',
	              success: function(json) {
	            	  that.setComboxData(comboxManage,json,defaultVal);
	            	  comboxManage.setLinkage(true);//恢复联动
	              }
	          });
	      }
	  },
	  //异步获取后台数据
	  asyncRequestInitComboxData:function(inputObjName){
		  var that = this;
		  var comboxManage = $("#"+inputObjName).ligerGetComboBoxManager();
		  var options = comboxManage.options;
		  var url = options.dest_url;
		  var defaultVal = options.def_val;
		  var data ={};
		  var params = options.p_input_name;//该下拉框对应的上一级的下拉框文本框名称
	      if(params != undefined && params != null && $.trim(params) !=''){
	    	  var paramArr = params.split(',');
	    	  for(var j=0;j<paramArr.length;j++){
	    		  var param_name = paramArr[j];
	    		  var obj = $("#"+param_name).ligerGetComboBoxManager();
	    		  var options = obj.options;
	    		  var pn = options.t_col_name;
	    		  data[pn] = $("#"+param_name+"_hidden").val();
	    	  }
	      }
		  $.getJSON(globalUtil.getTimestampUrl(url),data,
			 function(json) {
				that.setComboxData(comboxManage, json, defaultVal);
			});
	  },
	  /**
	   * 设置下拉框（树、非树）值及初始值
	   * @param comBoxManager
	   * @param jsonData
	   * @param defaultVal
	   */
	  setComboxData:function(comBoxManager,jsonData,defaultVal){
		  var isTree = comBoxManager.options.tree;
		  var inputObjName = comBoxManager.textFieldID;
		  if(defaultVal == null){
			  defaultVal = '';
		  }
		  if(jsonData && jsonData.length>0){
			  comBoxManager.options.data_own = jsonData;
			  if(isTree){
				  comBoxManager.setTreeData(jsonData);
			  }else{
				  comBoxManager.setData(jsonData);
			  }
			  if(defaultVal == 'first'){
				  var valueField = comBoxManager.options.valueField;
				  comBoxManager.selectValue(jsonData[0][valueField]);
			  }else if(defaultVal === ''){
				  comBoxManager.selectValue('');
				  $("#"+inputObjName).val("");
			  }else{
				  comBoxManager.selectValue(defaultVal);
			  }
		  }else{
			  comBoxManager.options.data_own = null;
			  if(isTree){
				  comBoxManager.setTreeData([]);
			  }else{
				  comBoxManager.setData([]);
			  }
			  comBoxManager.selectValue('');
			  $("#"+inputObjName).val("");
		  }
	  },
	  /**
	   * 将下拉框的值进行初始化，首先将根级的下拉框重新初始化，根据联动其他的下拉框进行初始化
	   * @param inputObjNames 不定个数参数，根级的下拉框名称
	   */
	  initComboxDefVal:function(inputObjNames){
		  var n = arguments.length;
		  for (var i = 0; i < n; i++) {
	          var inputObjName = arguments[i];
	          this.asyncRequestInitComboxData(inputObjName);
	      }
	  },
	  
	  syncInitComboxDefVal:function(inputObjNames){
		  var n = arguments.length;
		  for (var i = 0; i < n; i++) {
			  var inputObjName = arguments[i];
			  this.syncRequestInitComboxData(null,inputObjName);
		  }
	  },
	  getSelectComboxData:function(inputObjName){
		  var comBoxManager = $("#"+inputObjName).ligerGetComboBoxManager();
		  var valueField = comBoxManager.options.valueField;
		  var comboxData =  comBoxManager.options.data_own;
		  var val = $("#"+inputObjName+"_hidden").val();
		  if(comboxData && comboxData.length>0){
			  for(var i=0; i<comboxData.length; i++){
				  if(comboxData[i][valueField] == val){
					  return comboxData[i];
				  }
			  }
		  }
		  return {};
	  },
	  getComboxData:function(inputObjName){
		  var comBoxManager = $("#"+inputObjName).ligerGetComboBoxManager();
		  var comboxData =  comBoxManager.options.data_own;
		  return comboxData;
	  },
	  getComboxVal:function(inputObjName){
		  return $("#"+inputObjName+"_hidden").val();
	  },
	  
	  setComboxVal:function(inputObjName,val){
		  var comBoxManager = $("#"+inputObjName).ligerGetComboBoxManager();
		  comBoxManager.selectValue(val);
	  },
	  
	  disabledCombox:function(inputObjName){
		  var comBoxManager = $("#"+inputObjName).ligerGetComboBoxManager();
		  comBoxManager.setDisabled();
	  },
	  
	  /**
	   * 清空树，不要调用manager.clear()方法，该方法性能很差
	   * @param treeInputId
	   */
	  clearTree:function(treeInputId){
		  var parentDiv = $('#'+treeInputId).parent();
		  var treeManage = $('#'+treeInputId).ligerGetTreeManager();
		  treeManage.destroy();
		  parentDiv.append("<div id="+treeInputId+"></div>");
	  },
	  
	  /**
	   * 递归对象，返回符合条件的的所有子孙节点指定的值的数值
	   * @param root 树或树的节点
	   * @param jsonComp {name:'1',age:'100'}则返回名称==1，age==100的所有子节点
	   * @param returnKey 返回对象的哪部分值 ps:'id',则取node的ID值,默认值为id
	   */
	  getTreeDescendantsList:function(root, jsonComp, returnKey){
		  	var idArr = [];
	    	var nodes = root.data;
	    	if(nodes instanceof Array == false){
	    		nodes = root.children;
	    	}
	    	if(nodes instanceof Array == false && root.data){
	    		nodes = root.data.children;
	    	}
	    	if(returnKey == null){
				returnKey = "id";
			}
	    	if(nodes){
	    		for(var i=0; i<nodes.length; i++){
	    			var isSame = true;
	    			if(jsonComp){
	    				for(var key in jsonComp){
	    					if($.trim(key) == ''){continue;}
	    					if(nodes[i][key]!=jsonComp[key]){
	    						isSame = false;
	    						break;
	    					}
	    				}
	    			}
	    			
	    			if(isSame){
	    				idArr.push(nodes[i][returnKey]);
	    			}
	    			
	    			var tmp = this.getTreeDescendantsList(nodes[i],jsonComp,returnKey);
	    			if(tmp.length>0){
	    				$.each(tmp, function(i, val){
	    					idArr.push(val);
	    				});
	    			}
	    		} 
	    	}
	    	return idArr;
	  },
	  
	  /**
	   * 只获取下级节点的数据
	   * @param root
	   * @param jsonComp
	   * @param returnKey
	   * @returns {Array}
	   */
	  getTreeChildrenList:function(root, jsonComp, returnKey){
		  var idArr = [];
		  var nodes = root.data;
		  if(nodes instanceof Array == false){
			  nodes = root.children;
		  }
		  if(nodes instanceof Array == false && root.data){
			  nodes = root.data.children;
		  }
		  if(returnKey == null){
			  returnKey = "id";
		  }
		  if(nodes){
			  for(var i=0; i<nodes.length; i++){
				  var isSame = true;
				  if(jsonComp){
					  for(var key in jsonComp){
						  if($.trim(key) == ''){continue;}
						  if(nodes[i][key]!=jsonComp[key]){
							  isSame = false;
							  break;
						  }
					  }
				  }
				  
				  if(isSame){
					  idArr.push(nodes[i][returnKey]);
				  }
			  } 
		  }
		  return idArr;
	  },
	
    /**
     * 表格-新增行数据,如果在表格前面增加index为0，如果增加到末尾index为空
     * @param gridObj 表格对象
     * @param rows 行数据
     * @param index 插入位置
     */
	addRows: function(gridObj, rows, index) {
		if (gridObj) {
			var grid_data = gridObj.getData();
			var append_data = [];
			var length = rows.length;
			if (length === undefined) {
				append_data.push(rows);
			} else if (length && length != 0) {
				append_data = rows;
			}
			if (typeof(index) === 'number') {
				for (var i = 0; i < append_data.length; i++) {
					grid_data.splice(index, 0, append_data[i]);
				}
			} else {
				grid_data = grid_data.concat(append_data);
			}
			gridObj.loadData({
				Rows: grid_data
			});
		}
	},
    /**
     * 表格-移除行数据
     * @param gridObj 表格对象
     */
	deleteSelectedRow: function(gridObj) {
		gridObj.deleteSelectedRow();
	},
    /**
     * 表格-查询
     * @param gridObj 表格对象
     * @param param 查询参数
     */
	search: function(gridObj, params) {
		gridObj.set('parms', params);
		gridObj.changePage('start');//查询类表格使用
		//gridObj.loadServerData(params);
		//$("#pageloading").hide();
	},
	
	/**
     * 表格-查询(保留当前页码)
     * @param gridObj 表格对象
     * @param param 查询参数
     */
	research: function(gridObj, params) {
		gridObj.set('parms', params);
		gridObj.changePage('nowpage');//查询类表格使用
	},
	
    /**
     * 表格-销毁grid对象
     * @param gridObj 表格对象
     */
	destroy: function(gridObj) {
		if (gridObj) {
			$.ligerui.remove(gridObj);
			$("#"+gridObj.id).removeAttr("ligeruiid");
		}
	},
    /**
     * 表格-单元格变色
     * @param gridObj 表格对象
     * @param rowindex 行索引
     * @param columnIndex 列索引
     * @param color 替换颜色
     */
	setCellBackgroundColor: function(gridObj, rowindex, columnIndex, color) {
		if (gridObj) {
			if (!$.trim(rowindex)||!$.trim(columnIndex)||!$.trim(color)) {
				return;
			}
			var g = gridObj;
			if (columnIndex > g.columns.length || rowindex > g.getData().length) {
				return;
			}
			var column, cellObj, rowdata;
			column = g.columns[columnIndex];
	        rowdata = g.getRowObj(rowindex);
	        cellObj = g.getCellObj(rowdata, column);
	        $(cellObj).css("background", color);
		}
	},
    /**
     * 更新表格中单元格的方法
     * @param gridObj 表格对象
     * @param currentObj 编辑表格的文本/下拉框对象
     * @param columnName 列name
     * @param value 更新值
     */
	updateCell: function(gridObj, currentObj, columnName, value) {
		if (!gridObj || !currentObj) return;
		var nowRowIndex = currentObj.options.nowRowindex;
		gridObj.updateCell(columnName, value, nowRowIndex);
	},
    /**
     * 表格中下拉框的render方法
     * @param rowdata 行数据
     * @param nowRowIndex 当前行索引
     * @param value 单元格的值
     * @param column 列对象
     */
	gridRenderSelectedValue: function(rowdata, nowRowIndex, value, column) {
		var columnEditor = column.editor;
		var comboList = columnEditor.data;
		if (!comboList || comboList.length==0) return;
		var valueField = columnEditor.valueField;
		var textField = columnEditor.textField;
		for (var i = 0, l = comboList.length; i < l; i++) {
            if (comboList[i][valueField] == rowdata[valueField])
                return comboList[i][textField];
        }
		return rowdata[textField];
	},
	/**
     * 省市联动下拉框（省）
     */
	gridRenderSelectedValue2: function(rowdata, value, column) {
		var columnEditor = column.editor;
		var comboList = columnEditor.data;
		if (!comboList || comboList.length==0) return;
		var valueField = columnEditor.valueField;
		var textField = columnEditor.textField;
		for (var i = 0, l = comboList.length; i < l; i++) {
            if (comboList[i][valueField] == value)
                return comboList[i][textField];
        }
		return "请选择";
	},
	/**
     * 省市联动下拉框（市）
     */
	gridRenderSelectedValue3: function(rowdata, value, column, data) {
		var columnEditor = column.editor;
		var comboList = data;
		if (!comboList || comboList.length==0) return;
		var valueField = columnEditor.valueField;
		var textField = columnEditor.textField;
		for (var i = 0, l = comboList.length; i < l; i++) {
            if (comboList[i][valueField] == value)
                return comboList[i][textField];
        }
		return "请选择";
	},
    /**
     * 获取表格中单元格值的方法
     * @param gridObj 表格对象
     * @param currentObj 编辑表格的文本/下拉框对象
     * @param columnName 列name
     */
	getCellValue: function(gridObj, currentObj, columnName) {
		if (!gridObj || !currentObj) return;
		var nowRowIndex = currentObj.options.nowRowindex;
		var a = gridObj.getData();
		return a[nowRowIndex][columnName];
	}
};