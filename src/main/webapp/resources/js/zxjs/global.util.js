//重写toFixed方法，解决不同浏览器解析差异的BUG
Number.prototype.toFixed = function(exponent) {
	var j = Math.pow(10, exponent);
	var i = 0;
	if(this<0){
		i = this * j - 0.5;
	}else{
		i = this * j + 0.5;
	}
	
	var a = parseInt(i);
	var b = Math.pow(10, exponent);
    return (a/b).toString();
};
//为js的Date对象加入了格式化方法
Date.prototype.format = function(fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)){
         fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o){
         if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    }
    return fmt;
};
var globalUtil = {
    //全局颜色变量
    RED: '#FF6666',
    ORANGE: '#FF9933',
    BLUE: '#5C7DFE',
    GREEN: '#00FF66',

    _intervalIdArray: [],
    registInterval: function(intervalId) {
        this._intervalIdArray.push(intervalId);
    },
    closeAllInterval: function() {
        for (var i = 0; i < this._intervalIdArray.length; i++) {
            window.clearInterval(this._intervalIdArray[i]);
        }
        this._intervalIdArray = [];
    },
    
	_functionWrapperObj: {},
    /**
     * 方法计数包装器
     * @param varStr 计数包装器引用对象字符串
     * @param _fn 方法
     */
	setFunctionWrapper: function(varStr, _fn) {
		if (!$.trim(varStr) || typeof _fn != 'function') return;
		if (this._functionWrapperObj[varStr] == undefined) {
			this._functionWrapperObj[varStr] = 0;
		}
		this._functionWrapperObj[varStr]++;
		_fn();
		this._functionWrapperObj[varStr]--;
	},
    /**
     * 在varStr对应的方法计数包装器函数执行完成后执行_fn
     * @param varStr 计数包装器引用对象timer字符串
     * @param _fn 方法
     */
	functionRunOut: function(varStr, _fn) {
		if (!$.trim(varStr) || typeof _fn != 'function') return;
		if (this._functionWrapperObj[varStr] == undefined 
				|| this._functionWrapperObj[varStr] == 0) {
			if (this._functionWrapperObj[varStr+'timer']) {
				window.clearInterval(this._functionWrapperObj[varStr+'timer']);
				this._functionWrapperObj[varStr+'timer'] = null;
			}
			_fn();
		} else if (!this._functionWrapperObj[varStr+'timer']) {
			this._functionWrapperObj[varStr+'timer'] = window.setInterval(function() {
				this.functionRunOut(varStr, _fn);
			}, 200);
		}
	},
    
    getUrlParameter: function(strParame) {
        var args = new Object();
        var query = location.search.substring(1);
        var pairs = query.split("&"); // Break at ampersand 
        for (var i = 0; i < pairs.length; i++) {
            var pos = pairs[i].indexOf('=');
            if (pos == -1) continue;
            var argname = pairs[i].substring(0, pos);
            var value = pairs[i].substring(pos + 1);
            value = decodeURIComponent(value);
            args[argname] = value;
        }
        return args[strParame];
    },
    
    //获取父节点
    getTreeFatherNode: function(tree, node) {
        if (!node || !tree) {
            return null;
        }
        var treenode = tree.getNodeDom(node);
        var treeitem = $(treenode.target);
        var pnode = {};
        if (treeitem.parent().hasClass("l-tree")) {
            return null;
        }
        if (treeitem.parent().parent("li").length === 0) {
            return null;
        }
        pnode.target = treeitem.parent().parent("li")[0];
        pnode.data = tree.getDataByID($(pnode.target).attr("id"));
        return pnode;
    },
    
    // 得到树的最大层级
    getTreeMaxLevel: function(tree) {
        var maxLevel = 0;
        var setMaxLevel = function(node, level) {
            if (maxLevel < (level + 1)) {
                maxLevel = level + 1;
            }
            var childNodes = node && (node.data || node.children);
            if (childNodes) {
                for (var i = 0; i < childNodes.length; i++) {
                    setMaxLevel(childNodes[i], level + 1);
                }
            }
        };
        if (tree.length && tree.length > 0) {
            $.each(tree,
            function(index, node) {
                setMaxLevel(node, -1);
            });
        } else {
            setMaxLevel(tree, -1);
        }
        return maxLevel;
    },

    //树控件文字默认宽度
    defaultWidth: 50,
    //每个汉字的宽度
    singleWorldWidth: 12,

    // 得到树控件文字宽度
    getTreeMaxWidth: function(tree) {
        var sObj = this;
        var maxLevel = sObj.getTreeMaxLevel(tree);
        var maxWidth = sObj.defaultWidth;

        var setMaxWidth = function(node, level) {
            var nodeWidth = 0;
            if (node.text) {
                nodeWidth = node.text.length * sObj.singleWorldWidth - (maxLevel - level) * 22;
                if (maxWidth < nodeWidth) {
                    maxWidth = nodeWidth;
                }
            }

            var childNodes = node && (node.data || node.children);
            if (childNodes) {
                for (var i = 0; i < childNodes.length; i++) {
                    setMaxWidth(childNodes[i], level + 1);
                }
            }
        };

        setMaxWidth(tree, 0);
        return maxWidth + 22;
    },

    // 得到树控件整体宽度
    getTreeWholeWidth: function(tree, step) {
        var sObj = this;
        step = step || 22;
        var maxLevel = sObj.getTreeMaxLevel(tree);
        var maxWidth = sObj.getTreeMaxWidth(tree);

        return maxWidth + maxLevel * step;
    },
    //使用get方式，同步获取后台数据，返回数据结构为json
    syncGetJson: function(url, params,method) {
        var data = {};
        $.ajax({
            type: method || "GET",
            url: globalUtil.getTimestampUrl(url),
            data: params,
            async: false,
            dataType: 'json',
            success: function(json) {
                data = json;
            }
        });
        return data;
    },
    //使用post方式，同步获取后台数据，返回数据结构为json
    syncPostJson: function(url, params,method) {
        var data = {};
        $.ajax({
            type: method || "POST",
            url: globalUtil.getTimestampUrl(url),
            data: params,
            async: false,
            dataType: 'json',
            success: function(json) {
                data = json;
            }
        });
        return data;
    },
    // loading control with it's url, when some error happens, u need call this function
    errorHandle: function(data) {
        var msg = data && data.error;
        if (msg) {
            if (msg === '100091') {
                this.errorMsg(globalErrorMsg['100091']);
                return false;
            }
            if (msg === '100092') {
               this.confirmMsg(globalErrorMsg['100092'], '提示',
                function(yes) {
                    if (yes) {
                    	var obj = globalUtil._getMainpageObj(parent);
                    	obj.location.replace(global_param.context_name + "/login.html");
                        obj.location.hash = global_param.context_name + "/login.html";
                    } else {
                        // 此处删除掉弹出的多余确认框（当将地址粘贴到新的浏览器中时候会出现弹出2个确认框的问题，模态控制会混乱，此处避免此问题）
                    	globalUtil.clearAllDialog();
                    }
                },
                true);
                return false;
            }
        }
        return true;
    },
    reloginsys:function(){
    	$.post(this.getTimestampUrl("/login.do"),
				 {"cookieval_name": this.getCookieValue("cookie_username"), "cookieval_passwd": this.getCookieValue("cookie_passwd")}, 
				 function(data) {
				if (data.error) {
					if (data.error === '100100') {
						$.ligerDialog.error(globalErrorMsg['100100']);
					} else if (data.error === '100101') {
						$.ligerDialog.error(globalErrorMsg['100101']);
					} else if (data.error === '100109') {
						openDialog();
					}
				} else {
					this.resetCookieValue('cookie_username', account, 7);
					this.resetCookieValue('cookie_passwd', pwd, 7);
					colseDialog();
				}
			}
		);
    },
    _getMainpageObj: function(obj){
    	var tmp = obj.parent;
    	while(tmp!=obj){
    		obj = tmp;
    		tmp = obj.parent;
    	}
    	return tmp;
    },

    // 清除页面上的所有弹出框
    clearAllDialog: function() {
        $('.l-window-mask').remove();
        $('.l-dialog').remove();
    },

    //清除下拉框控件失去焦点后，下拉框不收缩的问题
    clearAllCombo: function() {
        $('.l-box-select').hide();
    },

    _mask: {},
    addMask: function(id) {
        this._mask[id] = $("<div class='l-window-mask' style='display: block;z-index: 9100' id=" + id + "></div>").appendTo('body');

    },
    delMask: function(id) {
        this._mask[id].remove();
    },

    showLoading: function() {
        $("<div class='l-loading-img' style='display:block;'></div>").appendTo('body');
        $("<div class='l-loading-text' style='display:block;'></div>").appendTo('body');
    },
    closeLoading: function() {
        $(".l-loading-img").remove();
        $(".l-loading-text").remove();
    },
    setLoadingText: function(str) {
        $(".l-loading-text").html(str);
    },

    //focusObj:dialog关闭后需要获取的焦点对象
    _alert: function(msg, type, callBackFunc, resetHeight, resetWidth) {
        var that = this;
        var obj;
        var timestamp = new Date().getTime();
        that.addMask(timestamp);

        if (type === 'success') {
            obj = $.ligerDialog.success(msg, null, null);
            var autoClose = setTimeout(function() { //2秒后自动关闭
                obj.close();
            },
            2000);
            obj.options.onHiddenOrClose = function() {
                that.delMask(timestamp);
                clearTimeout(autoClose); //取消自动关闭时间，避免手动关闭时重复调用onHiddenOrClose方法
                if (callBackFunc && typeof(callBackFunc) == 'function') {
                    callBackFunc();
                }
            };
            return;
        } else if (type === 'error') {
            obj = $.ligerDialog.error(msg, null, callBackFunc);
        } else if (type === 'warn') {
            obj = $.ligerDialog.warn(msg, null, callBackFunc);
        } else if (type === 'alert') {
            obj = $.ligerDialog.alert(msg, null, 'alert', callBackFunc);
            if (resetHeight) obj._setHeight(resetHeight);
            if (resetWidth) obj._setWidth(resetWidth);
        }
        obj.options.onHiddenOrClose = function() {      
            that.delMask(timestamp);
        };
    },
    successMsg: function(msg, callBackFunc) {
        this._alert(msg, 'success', callBackFunc);
    },
    warnMsg: function(msg, callBackFunc) {
        this._alert(msg, 'warn', callBackFunc);
    },
    errorMsg: function(msg, callBackFunc) {
        this._alert(msg, 'error', callBackFunc);
    },
    alertMsg: function(msg, callBackFunc,resetHeight, resetWidth) {
        this._alert(msg, 'alert', callBackFunc,resetHeight, resetWidth);
    },
    confirmMsg: function(content, title, callback, isClearAll, resetHeight, resetWidth,hiddenFun) {
        var that = this;
        var obj = $.ligerDialog.confirm(content, title, callback);
        that.addMask('confirm');
        if (resetHeight) obj._setHeight(resetHeight);
        if (resetWidth) obj._setWidth(resetWidth);
        if(hiddenFun){
        	obj.options.onHiddenOrClose = hiddenFun;
        }else{
        	obj.options.onHiddenOrClose = function() {
        		that.delMask('confirm');
        		if (isClearAll) {
        			that.clearAllDialog();
        		}
        	};
        }
    },
    setDialogHeight: function(height) {
        return document.body.offsetHeight < height ? document.body.offsetHeight: height;
    },

    checkSession: function() {
        $.getJSON(this.getTimestampUrl("/sysmanage/authorizedStub.do"),
        function(data) {
            globalUtil.errorHandle(data);
        });
    },
    openTab: function(tabId, tabName, url, removeAll) {
        var $query = window.parent.window.$;
        tab = $query("#framecenter").ligerGetTabManager();
        if (tab.isTabItemExist(tabId)) {
            tab.removeTabItem(tabId);
        }
        if (removeAll) {
            tab.removeAll();
        }

        if (url.indexOf('?') > -1) {
            url = url + '&timestamp=' + new Date().getTime();
        } else {
            url = url + '?timestamp=' + new Date().getTime();
        }

        tab.addTabItem_child({
            tabid: tabId,
            text: tabName,
            url: url
        });
    },
    removeTabItem: function(tabId)
    {
        var $query = window.parent.window.$;
        tab = $query("#framecenter").ligerGetTabManager();
        tab.removeTabItem(tabId);
    },
    removeOtherTab: function(tabId) {
        var $query = window.parent.window.$;
        tab = $query("#framecenter").ligerGetTabManager();
        tab.removeOther(tabId);
    },
    closeCurrentTab: function() {
        var $query = window.parent.window.$;
        tab = $query("#framecenter").ligerGetTabManager();
        tab.removeSelectedTabItem();
    },
    isEmail: function(value) {
        value = $.trim(value);
        return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
    },
    /**
     * 验证是否为邮政编码，验证为11位数字
     * @param value
     * @returns
     */
   isPostalCode: function(value) {
       value = $.trim(value);
       return /^[0-9]{6}$/.test(value);
   },
    /**
     * 验证是否为手机号码，验证为11位数字
     * @param value
     * @returns
     */
    isPhone: function(value) {
        value = $.trim(value);
        return /^[1][3-8]\d{9}$/.test(value);
    },
    /**
     * 验证是否为固定电话，格式为区号：前面一个0，后面跟2-3位数字 电话号码：7-8位数字
     * @param value
     * @returns
     */
    isFixedTelephone : function(value) {
    	value = $.trim(value);
    	return /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(value);
    },
    
    /**
     * 验证是否为固定电话，格式为区号：前面一个0，后面跟2-3位数字 电话号码：7-8位数字--可填分机号
     * @param value
     * @returns
     */
    isFixedTelephoneAll : function(value) {
    	value = $.trim(value);
    	return /^((0\d{2,3})-)(\d{7,20})(-(\d{3,}))?$/.test(value);
    },   
    /**
     * 验证是否为身份证号，只验证了位数和出生日期
     * @param value
     * @returns {Boolean}
     */
    isIdCard: function(value) {
        value = $.trim(value);
        value = value.toUpperCase(); //转为大写
        if (value.length == 15) {
            if (!/^[0-9]{15}$/.test(value)) {
                return false;
            }
            var year = value.substring(6, 8);
            var month = value.substring(8, 10);
            var day = value.substring(10, 12);
            var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
            // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
            if (temp_date.getYear() != parseFloat(year) || temp_date.getMonth() != parseFloat(month) - 1 || temp_date.getDate() != parseFloat(day)) {
                return false;
            } else {
                return true;
            }
        } else if (value.length == 18) {
            if (!/^[0-9]{17}([0-9]|X)$/.test(value)) {
                return false;
            }
            var year = value.substring(6, 10);
            var month = value.substring(10, 12);
            var day = value.substring(12, 14);
            var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
            // 这里用getFullYear()获取年份，避免千年虫问题   
            if (temp_date.getFullYear() != parseFloat(year) || temp_date.getMonth() != parseFloat(month) - 1 || temp_date.getDate() != parseFloat(day)) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    },
    getTimestampUrl: function(url) {
        url = $.trim(url);
        if (url.indexOf('?') > -1) {
            return global_param.context_name + url + '&timestamp=' + new Date().getTime();
        } else {
            return global_param.context_name + url + '?timestamp=' + new Date().getTime();
        }
    },

    /**
     * 验证是否为正整数
     * @param value
     * @returns
     */
    isNum: function(value) {
        value = $.trim(value);
        return /^[1-9]\d*|0$/.test(value);
    },
    //验证是否为实数，如果录入012，返回false
    isFloat: function(str) {
        var myreg = /^(-?((0|[1-9]+)|([1-9]\d*)))(\.\d+)?$/;
        return myreg.test(str);
    },
    isServerCode:function(value){
    	 value = $.trim(value);
         return  /\d{6}/.test(value);
    },
    /**
     * 验证是否为指定格式的实数(包括负数和整数和小数点)
     * @param value， intLen整数部分最长长度，floatLen小数部分最长长度
     * @returns
     */
    isReal: function(val1ue, intLen, floatLen) {
        value = $.trim(value);
        if (!this.isFloat(value)) { //首先验证是否为实数
            return false;
        }
        var pattern;
        if (!intLen) {
            return /^-?\d+(\.\d+)?$/.test(value);
        } else if (!floatLen) {
            pattern = new RegExp('^-?\\d{1,' + intLen + '}$', '');
        } else {
            pattern = new RegExp('^-?\\d{1,' + intLen + '}(\\.\\d{1,' + floatLen + '})?$', '');
        }

        return pattern.test(value);
    },
    
    /**
     * 验证金额
     * @param value 最多2位小数
     * @returns
     */
    isMoney: function(value) {
        value = $.trim(value);
        var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
        if (reg.test(value)) {
          return true;
          }else{
          return false;
     }
    },
    
    
    //验证正数
    isPositiveNum: function(num) {
    	var reg = /^\d+(?=\.{0,1}\d+$|$)/;
		if(reg.test(num)) {
			return true;
		}
		return false;  
    },
     
    //不进行四舍五入保留n位小数
    changeDecimal: function(x, n) {
        if (!this.isFloat(x)) {
            return x;
        }
        if ($.trim(x).length == 0) {
            return '';
        }
        var str = $.trim(x) + '';
        var pattern = new RegExp('\-{0,1}[0-9]+(\.[0-9]{1,' + n + '}){0,1}', '');
        str = str.match(pattern)[0];
        return parseFloat(str);
    },
    //不进行四舍五入保留n位小数
    changeDecimal2: function(x, n) {
        if (!this.isFloat(x)) {
            return x;
        }
        if ($.trim(x).length == 0) {
            return '';
        }
        var str = $.trim(x) + '';
        var pattern = new RegExp('\-{0,1}[0-9]+(\.[0-9]{1,' + n + '}){0,1}', '');
        str = str.match(pattern)[0];
        return str;
    },
    //四舍五入保留n位小数
    changeDecimalRound: function(x, n) {
        if (!this.isFloat(x)) {
            return x;
        }
        return parseFloat(x).toFixed(n);
    },
    //四舍五入保留n位小数
    changeDecimalRoundHalfEven: function(num, precision, addZeroFlag) {//添加自动补零标识(仅限整数，若有小数位需重写)
    	var result = 0;
    	if(num==undefined||num==""||num==null||num=="0"||num==0){
    		return result;
    	}
    	if (!this.isFloat(num)) {
    		return num;
    	}
    	var dnum = Math.pow(10, precision);
        if(Math.floor(num * dnum * 10) % 5 == 0 && Math.floor(num * dnum * 10) == num * dnum * 10 && Math.floor(num * dnum) % 2 == 0) {
            result =  Math.floor(num * dnum) / dnum;
        } else {
        	result = i.toFixed(precision);
        }
        if(addZeroFlag) {
    		var addZeroStr = '.';
    		if(this.isNum(num)) {
        		for(var i = 0; i < precision; i++) {
        			addZeroStr += '0';
        		}
        	}
    		result += addZeroStr;
    	}
        return result;
    },

    isEmpty: function(val) {
        if (typeof(val) == 'number') {
            val += '';
        }
        var str = val || '';
        return $.trim(str).length == 0;
    },

    //验证：大小写英文字符、数字、连字符、下划线四类字符
    checkSectCode: function(sectCode) {
        var sectCode = $.trim(sectCode);
        var a = /^([a-z]|[A-Z]|\-|_|\d){1,}$/;
        if (!a.test(sectCode)) {
            return false;
        } else return true;
    },
    
    /**
	   * 过滤对象或数组的属性
	   * @param obj 对象或数组
	   * @param value 过滤值
	   * @param deep 是否深层过滤
	   */
    deleteAttributes: function(obj, value, deep) {
    	if (jQuery.isPlainObject(obj)) {
        	for (var A in obj) {
        		if ((jQuery.isPlainObject(obj[A]) || jQuery.isArray(obj[A])) && deep) {
        			globalUtil.deleteAttributes(obj[A], value, deep);
        		} else if (obj[A] === value) {
    				delete obj[A];
        		}
        	}
    	} else if (jQuery.isArray(obj)) {
    		var temp = [];
    		jQuery.each(obj, function(i, n) {
        		if ((jQuery.isPlainObject(n) || jQuery.isArray(n)) && deep) {
        			globalUtil.deleteAttributes(n, value, deep);
        			if (n.length != 0) {
        				temp.push(n);
        			}
        		} else if (n !== value) {
        			temp.push(n);
        		}
    		});
    		obj.splice(0, obj.length);
    		if (temp.length != 0) {
    			for (var i = 0, l = temp.length; i < l; i++) {
    				obj.push(temp[i]);
    			}
    		}
    	}
    },
    //返回当前日期的字符串
    getCurrentDateStr: function() {
        var now = new Date();
        var year = now.getFullYear(); //年
        var month = now.getMonth() + 1; //月
        var day = now.getDate(); //日
        var clock = year + "-";
        if (month < 10) clock += "0";
        clock += month + "-";
        if (day < 10) clock += "0";
        clock += day;
        return (clock);
    },
    //返回当前时间的字符串，yyyy-MM-dd HH:mm:ss
    getCurrentDatetimeStr: function() {
        var now = new Date();
        return this._transToDateStr(now);
    },
    
    //返回当前日期的字符串，yyyy-MM-dd
    getCurrentDateStr: function() {
        var now = new Date();
        return this._transToDateStr1(now);
    },

    /**
	   * 根据format的格式对dateStr进行格式化，内部通过字符串的截取实现
	   * @param datestr
	   * @param format yyyy-MM-dd HH:mm:ss
	   */
    formatDateStr: function(datestr, format) {
        format = $.trim(format);
        var len = format.length;
        if (datestr.length < len) {
            return '';
        }
        return datestr.substring(0, len);
    },
    //获取和指定日期字符串差值diff日的日期
    getDiffDateStr: function(datestr, diff) {
        if (typeof(datestr) != 'date') {
            datestr = datestr.replace(/-/g, "/");
            datestr = new Date(datestr);
        }
        datestr.setDate(datestr.getDate() + (diff));
        var newDate = this.formatDateStr(this._transToDateStr(datestr), 'yyyy-MM-dd');
        return newDate;
    },
    //获取和指定日期字符串差值diff月的日期
    getDiffMonthStr: function(datestr, diff) {
        if (typeof(datestr) != 'date') {
            datestr = datestr.replace(/-/g, "/");
            datestr = new Date(datestr);
        }
        datestr.setMonth(datestr.getMonth() + (diff));
        var newDate = this.formatDateStr(this._transToDateStr(datestr), 'yyyy-MM-dd');
        return newDate;
    },
    /*
	 * 返回指定日期字符串增加指定时间，返回增加后的日期字符串
	 * param中对应的value应该为数值，不能写成字符串
	 * */

    getDiffDateFormatStr: function(datestr, param, format) {
        if (typeof(datestr) != 'date') {
            datestr = datestr.replace(/-/g, "/");
            datestr = new Date(datestr);
        }
        if (param.year) {
            datestr.setFullYear(datestr.getFullYear() + (param.year));
        }
        if (param.month) {
            datestr.setMonth(datestr.getMonth() + (param.month));
        }
        if (param.date) {
            datestr.setDate(datestr.getDate() + (param.date));
        }
        if (param.hour) {
            datestr.setHours(datestr.getHours() + (param.hour));
        }
        if (param.min) {
            datestr.setMinutes(datestr.getMinutes() + (param.min));
        }
        if (param.sec) {
            datestr.setSeconds(datestr.getSeconds() + (param.sec));
        }
        var newDate = this.formatDateStr(this._transToDateStr(datestr), format);
        return newDate;
    },
    //返回指定年月份的总天数
    searchDays: function(varYear, varMonth) {
        var lngDay;
        switch (varMonth) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            lngDay = 31;
            break;
        case 4:
        case 6:
        case 9:
        case 11:
            lngDay = 30;
            break;
        case 2:
            if ((varYear % 4 == 0 && varYear % 100 != 0 || (varYear % 400 == 0))) {
                lngDay = 29;
            } else {
                lngDay = 28;
            }
            break;
        }
        return lngDay;
    },
    getFirstDayOfCurrentMonth: function() {
        var now = new Date();
        var year = now.getFullYear(); //年
        var month = now.getMonth() + 1; //月
        var day = "01"; //日
        var clock = year + "-";
        if (month < 10) clock += "0";
        clock += month + "-";
        clock += day;
        return (clock);
    },
    getLastDayOfCurrentMonth: function() {
        var now = new Date();
        var year = now.getFullYear(); //年
        var month = now.getMonth() + 1; //月			
        var day = this.searchDays(year, month); //日
        var clock = year + "-";
        if (month < 10) clock += "0";
        clock += month + "-";
        clock += day;
        return (clock);
    },
    _transToDateStr: function(datetime) {
        var year = datetime.getFullYear(); //年
        var month = datetime.getMonth() + 1; //月
        var day = datetime.getDate(); //日
        var hour = datetime.getHours();
        var min = datetime.getMinutes();
        var sec = datetime.getSeconds();
        var clock = year + "-";
        if (month < 10) clock += "0";
        clock += month + "-";
        if (day < 10) clock += "0";
        clock += day;
        clock += " ";
        if (hour < 10) {
            clock += "0";
        }
        clock += hour;
        clock += ":";
        if (min < 10) {
            clock += "0";
        }
        clock += min;
        clock += ":";
        if (sec < 10) {
            clock += "0";
        }
        clock += sec;
        return clock;
    },
    
    _transToDateStr1: function(datetime) {
        var year = datetime.getFullYear(); //年
        var month = datetime.getMonth() + 1; //月
        var day = datetime.getDate(); //日
        var clock = year + "-";
        if (month < 10) clock += "0";
        clock += month + "-";
        if (day < 10) clock += "0";
        clock += day;
        clock += " ";
        return clock;
    },
    
    getCookieValue: function(cookieName){
    	var name = cookieName || 'cookie_username';
    	return $.cookie(name) || '';
    },
    setCookieValue: function(cookieName, cookieVal, expireDay){
    	if(global_param.use_cookie){
    		cookieName = cookieName || 'empty';
        	cookieVal = cookieVal || '';
        	expireDay = expireDay || 7;
        	$.cookie(cookieName, cookieVal, {expires: expireDay});
    	}
    },
    resetCookieValue: function(cookieName,cookieVal, expireDay){
    	$.removeCookie(cookieName);
    	globalUtil.setCookieValue(cookieName, cookieVal, expireDay);
    },
    
    isMobile: {
			Android: function() {
				return navigator.userAgent.match(/Android/i) ? true: false;
			},
			BlackBerry: function() {
				return navigator.userAgent.match(/BlackBerry/i) ? true: false;
			},
			iOS: function() {
				return navigator.userAgent.match(/iPhone|iPad|iPod/i) ? true: false;
			},
			Windows: function() {
				return navigator.userAgent.match(/IEMobile/i) ? true: false;
			},
			any: function() {
				return (this.Android() || this.BlackBerry() || this.iOS() || this.Windows());
			}
	},
    getHtml:function(url){
    	url = $.trim(url);
        if (url.indexOf('?') > -1) {
            return url + '&timestamp=' + (new Date()).getTime();
        } else {
            return url + '?timestamp=' + (new Date()).getTime();
        }
    	return url;
    },
    /*
     * 获取指定对象下的所有input、textarea值
     * divid：外层的id
     * jsonStr：如果无，新创建一个对象，如果有，直接在对象中添加
     * isCheck:是否进行必填项判断
     */
    getFormParam: function(divid,jsonStr,isCheck){
    	var inputlist = $("#"+divid+" input[type=text]");
    	var textarealist = $("#"+divid+" textarea");
    	var param = {};
    	if(jsonStr){
    		param = jsonStr;
    	}
    	
    	var iserror =false;
    	$.each(inputlist,function(i,obj){
		  var id = obj.id;
		  var name = obj.name;
		  if(!id && !name){
			  
		  }else{
			  var ligeruiid = obj.attributes.ligeruiid;
			  var val = $.trim(obj.value);
			  if(val == ''){
				  if(isCheck && $(obj).attr("isrequired") == '1'){
					  if(obj.style.display == 'none' || (obj.parentNode && obj.parentNode.style.display == 'none')){//父节点是否隐藏
						  
					  }else{
						  iserror =true;
						  obj.style.border = '1px dotted #FF0000';
						  //$(obj).attr({"style":"border: 1px dotted #FF0000;"});
					  }
				  }
			  }else{
				  obj.style.border = '';
			  }
			  if(ligeruiid){
				  val = $("#"+id+"_hidden").val();
			  }
			  if(name){
				  param[name] = val;
			  }else{
				  param[id] = val;
			  }
		  }
		});
    	$.each(textarealist,function(i,obj){
		  var id = obj.id;
		  var name = obj.name;
		  if(!id && !name){
			  
		  }else{
			  var val = $.trim(obj.value);
			  if(val == ''){
				  if(isCheck && $(obj).attr("isrequired") == '1'){
					  if(obj.parentNode && obj.parentNode.style.display == 'none'){//父节点是否隐藏
						  
					  }else{
						  iserror =true;
						  obj.style.border = '1px dotted #FF0000';
						  //$(obj).attr({"style":"border: 1px dotted #FF0000;"});
					  }
				  }
			  }else{
				  obj.style.border = '';
			  }
			  if(name){
				  param[name] = val;
			  }else{
				  param[id] = val;
			  }
		  }
		});
    	if(iserror){
    		return 'error';
    	}
    	return param;
    },
    /*
     * 获取指定对象下的所有input、textarea值
     * divid：外层的id
     * jsonStr：如果无，新创建一个对象，如果有，直接在对象中添加
     * isCheck:是否进行必填项判断
     */
    getFormParamByid: function(divid,jsonStr,isCheck){
    	var inputlist = $("#"+divid+" input[type=text]");
    	var textarealist = $("#"+divid+" textarea");
    	var param = {};
    	if(jsonStr){
    		param = jsonStr;
    	}
    	
    	var iserror =false;
    	$.each(inputlist,function(i,obj){
		  var id = obj.id;
		  if(!id){
			  
		  }else{
			  var ligeruiid = obj.attributes.ligeruiid;
			  var val = $.trim(obj.value);
			  if(val == ''){
				  if(isCheck && $(obj).attr("isrequired") == '1'){
					  if(obj.style.display == 'none' || (obj.parentNode && obj.parentNode.style.display == 'none')){//父节点是否隐藏
						  
					  }else{
						  iserror =true;
						  obj.style.border = '1px dotted #FF0000';
						  //$(obj).attr({"style":"border: 1px dotted #FF0000;"});
					  }
				  }
			  }else{
				  obj.style.border = '';
			  }
			  if(ligeruiid){
				  val = $("#"+id+"_hidden").val();
			  }
			   param[id] = val;
		  }
		});
    	$.each(textarealist,function(i,obj){
		  var id = obj.id;
		  if(!id){
			  
		  }else{
			  var val = $.trim(obj.value);
			  if(val == ''){
				  if(isCheck && $(obj).attr("isrequired") == '1'){
					  if(obj.parentNode && obj.parentNode.style.display == 'none'){//父节点是否隐藏
						  
					  }else{
						  iserror =true;
						  obj.style.border = '1px dotted #FF0000';
						  //$(obj).attr({"style":"border: 1px dotted #FF0000;"});
					  }
				  }
			  }else{
				  obj.style.border = '';
			  }
			  param[id] = val;
		  }
		});
    	if(iserror){
    		return 'error';
    	}
    	return param;
    },
    /*
     * 指定对象下的所有input、textarea值(除下拉框，下拉框需要单独赋值)
     */
    setFormVal: function(divid,param){
    	if(param == null || param == ''){
    		return;
    	}
    	var inputlist = $("#"+divid+" input[type=text]");
    	var textarealist = $("#"+divid+" textarea");
    	$.each(inputlist,function(i,obj){
    		var id = obj.id;
    		var name = obj.name;
    		var ligeruiid = obj.attributes.ligeruiid;
    		var val = name ? param[name] : param[id];
 			if(!ligeruiid){//判断param中是否包含文本域所定义的id或name的属性
    			$(obj).val(val);
    		}
    	});
    	$.each(textarealist,function(i,obj){
    		var id = obj.id;
    		var name = obj.name;
    		if(name){
    			$(obj).val(param[name]);
	  		}else{
	  			$(obj).val(param[id]);
	  		}
    	});
    },
    /*
     * 指定对象下的所有input、textarea值(除下拉框，下拉框需要单独赋值)
     */
    setFormValByid: function(divid,param){
    	if(param == null || param == ''){
    		return;
    	}
    	var inputlist = $("#"+divid+" input[type=text]");
    	var textarealist = $("#"+divid+" textarea");
    	$.each(inputlist,function(i,obj){
    		var id = obj.id;
    		var ligeruiid = obj.attributes.ligeruiid;
    		var val =  param[id];
    		if(!ligeruiid){
    			$(obj).val(val);
    		}
    	});
    	$.each(textarealist,function(i,obj){
    		var id = obj.id;
	  		$(obj).val(param[id]);
    	});
    },
    /*
     * 指定对象下的所有input、textarea值(除下拉框，下拉框需要单独赋值)
     */
    setFormValByname: function(divid,param){
    	if(param == null || param == ''){
    		return;
    	}
    	var inputlist = $("#"+divid+" input[type=text]");
    	var textarealist = $("#"+divid+" textarea");
    	$.each(inputlist,function(i,obj){
    		var name = obj.name;
    		var ligeruiid = obj.attributes.ligeruiid;
    		var val =  param[name];
    		if(!ligeruiid){
    			$(obj).val(val);
    		}
    	});
    	$.each(textarealist,function(i,obj){
    		var name = obj.name;
	  		$(obj).val(param[name]);
    	});
    },
    haveRepeated: function(arr){
    	for(i=0;i<arr.length;i++){
    		var v = arr[i];
    		//2016-4-5 baisong 如果数据没空则不校验重复--房贷联系人电话
    		if(v.value!=null&&v.value!=''){
    			for(j=i+1;j<arr.length;j++){
        			if(arr[j].value==v.value){
        				return true;
        			}
        		}	
    		}
    		
    	}   
    	return false;
    },
	//清理指定DIV下的所有LigerTip控件
	clearLigerTip : function (divId){
		var elementArr = $("#"+divId+" *");
		jQuery.each(elementArr.get(), function(i, element) {
			$(element).ligerHideTip();
		});
	},
	//当滚动条滚动时，清除带有滚动条的Div中的所有ligerTip组件以及下拉列表框的下部分，其中，scrollDivId是带有滚动条的Div的ID值
	clearLigerWhenScroll : function (scrollDivId){
		$('#'+scrollDivId).scroll(function(){ 
			globalUtil.clearLigerTip(scrollDivId);
			globalUtil.clearAllCombo();
		});
	},
	//小写金额转大写金额
	convertCurrency : function(currencyDigits) {  
		var MAXIMUM_NUMBER = 99999999999.99;   
		var CN_ZERO = "零";  
		var CN_ONE = "壹";  
		var CN_TWO = "贰";  
		var CN_THREE = "叁";  
		var CN_FOUR = "肆";  
		var CN_FIVE = "伍";  
		var CN_SIX = "陆";  
		var CN_SEVEN = "柒";  
		var CN_EIGHT = "捌";  
		var CN_NINE = "玖";  
		var CN_TEN = "拾";  
		var CN_HUNDRED = "佰";  
		var CN_THOUSAND = "仟";  
		var CN_TEN_THOUSAND = "万";  
		var CN_HUNDRED_MILLION = "亿";  
		var CN_SYMBOL = "";  
		var CN_DOLLAR = "元";  
		var CN_TEN_CENT = "角";  
		var CN_CENT = "分";  
		var CN_INTEGER = "整";  
		    
		var integral;   
		var decimal; 
		var outputCharacters; 
		var parts;  
		var digits, radices, bigRadices, decimals;  
		var zeroCount;  
		var i, p, d;  
		var quotient, modulus;  
		  
		currencyDigits = currencyDigits.toString();   
		  
		currencyDigits = currencyDigits.replace(/,/g, ""); 
		currencyDigits = currencyDigits.replace(/^0+/, ""); 
		
		if (Number(currencyDigits) > MAXIMUM_NUMBER) {  
		  globalUtil.errorMsg(globalErrorMsg['100404']);  
		  return "";  
		}		
		parts = currencyDigits.split(".");  
		if (parts.length > 1) {  
		  integral = parts[0];  
		  decimal = parts[1];  
		  decimal = decimal.substr(0, 2);  
		}  
		else {  
		  integral = parts[0];  
		  decimal = "";  
		}  
		digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE);  
		radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND);  
		bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION);  
		decimals = new Array(CN_TEN_CENT, CN_CENT);  
		outputCharacters = "";  
		if (Number(integral) > 0) {  
		  zeroCount = 0;  
		  for (i = 0; i < integral.length; i++) {  
		   p = integral.length - i - 1;  
		   d = integral.substr(i, 1);  
		   quotient = p / 4;  
		   modulus = p % 4;  
		   if (d == "0") {  
		    zeroCount++;  
		   }  
		   else {  
		    if (zeroCount > 0)  
		    {  
		     outputCharacters += digits[0];  
		    }  
		    zeroCount = 0;  
		    outputCharacters += digits[Number(d)] + radices[modulus];  
		  }  
		   if (modulus == 0 && zeroCount < 4) {  
		    outputCharacters += bigRadices[quotient];  
		  }  
		  }  
		  outputCharacters += CN_DOLLAR;  
		}  
		if (decimal != "") {  
		  for (i = 0; i < decimal.length; i++) {  
		   d = decimal.substr(i, 1);  
		   if (d != "0") {  
		    outputCharacters += digits[Number(d)] + decimals[i];  
		   }  
		  }  
		}  
		if (outputCharacters == "") {  
		  outputCharacters = CN_ZERO + CN_DOLLAR;  
		}  
		if (decimal == "") {  
		  outputCharacters += CN_INTEGER;  
		}  
		outputCharacters = CN_SYMBOL + outputCharacters;  
		return outputCharacters;  
	},/* 数字千分符 */
	rendererZhMoney : function(v) {
		if (v == 0 || v == '0') {
			return '0.00';
		}
		if (isNaN(v)) {
			return v;
		}
		v = (Math.round((v - 0) * 100)) / 100;
		v = (v == Math.floor(v)) ? v + ".00"
				: ((v * 10 == Math.floor(v * 10)) ? v + "0" : v);
		vv = String(v);
		var ps = vv.split('.');
		var whole = ps[0];
		var sub = ps[1] ? '.' + ps[1] : '.00';
		var r = /(\d+)(\d{3})/;
		while (r.test(whole)) {
			whole = whole.replace(r, '$1' + ',' + '$2');
		}
		vv = whole + sub;

		return vv;
	},

    borrowProtocolInfo: function(wms_cre_credit_head_id){
     	var row={};
     	//如果存在grid则正常
     	try{
     		row=grid.getSelectedRow();
     	}catch (e){//如果grid不存在则获取cre_type&protocol_type的值 并赋值给row  放款申请-合同清单-电子版打印合同housingMakeLoansAppro.html
     		if(cre_type!=null &&protocol_type!=null){
	     		row.cre_type=cre_type;
	     		row.protocol_type=protocol_type;
	     		row.wms_cre_credit_head_id=wms_cre_credit_head_id;
	     	}
     	}
    	if(row==null){
            return;
    	}else{
			if(row.cre_type=='2'&&(row.protocol_type==""||row.protocol_type==undefined)){
				globalUtil.warnMsg(globalErrorMsg['600112']);
        		return;
			}
			 globalUtil.confirmMsg("请用360极速浏览器打印，否则出现格式问题！",
			    function(yes) { //确认删除
			    	if(yes){
				    	if(row.cre_type=='1'){//信贷
				    		var json=globalUtil.syncGetJson('/cremanage/wmscrecreditheadinfobypk.do',{
				        		"wms_cre_credit_head_id": wms_cre_credit_head_id
				    	    },'GET');
				    		if(json.cre_loan_type==284){
				    			window.open("../loanappro/loanApproBorrowProtocolTSDSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id); 	
				    		}else{
				    			window.open("../loanappro/showloanApproBorrowProtocol.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);  
				    		}
				    	}else if(row.cre_type=='2'){//房贷
					   			if(row.protocol_type == '1'){
					   				window.open("../loanappro/houseLoanOneNoSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);
					   			}else if(row.protocol_type == '2'){
					   				window.open("../loanappro/houseLoanOneSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);
					   			}else if(row.protocol_type == '3'){
					   				window.open("../loanappro/houseLoanTwoNoSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);
					   			}else if(row.protocol_type == '4'){
					   				window.open("../loanappro/houseLoanTwoSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);   				
					   			}else if(row.protocol_type == '6'){
					   				window.open("../loanappro/returnBorrowProtocolSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);   				
								}else if(row.protocol_type == '7'){
									window.open("../loanappro/combineBorrowProtocolSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);   				
								}else if(row.protocol_type == '11'){
					   				window.open("../loanappro/houseLoanThreeSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);   				
								}else if(row.protocol_type == '12'){
									window.open("../loanappro/houseLoanThreeNoSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);   				
								}else if(row.protocol_type == '9'){
					   				window.open("../loanappro/houseLoanFourSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);   				
								}else if(row.protocol_type == '10'){
									window.open("../loanappro/houseLoanFourNoSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);   				
								}else if(row.protocol_type == '21'){//var protocol_type="21";//合同编号   先息后本 新房1号 保人担责    zxMortgageOne.html
					   				window.open("../loanappro/zxMortgageOneSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);   				
								}else if(row.protocol_type == '22'){//var protocol_type="22";//合同编号   先息后本 新房1号 保人不担责  zxMortgageOneNo.html 
									window.open("../loanappro/zxMortgageOneNoSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);   				
								}else if(row.protocol_type == '23'){//var protocol_type="23";//合同编号   等额本息 新房2号 保人担责    zxMortgageTwoEq.html
					   				window.open("../loanappro/zxMortgageTwoEqSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);   				
								}else if(row.protocol_type == '24'){//var protocol_type="24";//合同编号   等额本息 新房2号 保人不担责  zxMortgageTwoEqNo.html
									window.open("../loanappro/zxMortgageTwoEqNoSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);   				
								}else if(row.protocol_type == '25'){//var protocol_type="23";//合同编号   等额本息 新房2号 保人担责   zxMortgageOneSearch0431.html 长春 
					   				window.open("../loanappro/zxMortgageOneSearch0431.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);   				
								}else if(row.protocol_type == '26'){//var protocol_type="24";//合同编号   等额本息 新房2号 保人不担责  zxMortgageTwoEq0431.html 长春
									window.open("../loanappro/zxMortgageTwoEqSearch0431.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);   				
								}else if(row.protocol_type == '27'){//var protocol_type="25";//合同编号   等额本息 新房2号 保人担责    zxMortgageTwoEq.html 长春
					   				window.open("../loanappro/zxMortgageOneNoSearch0431.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);   				
								}else if(row.protocol_type == '28'){//var protocol_type="26";//合同编号   等额本息 新房2号 保人不担责  zxMortgageTwoEqNo.html 长春
									window.open("../loanappro/zxMortgageTwoEqNoSearch0431.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);   				
								}else if(globalUtil.getCreType(row.protocol_type)){//新版本合同ireport模板
									window.open("../showPDF.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id); 
								}
				    	  }else if(row.cre_type=='3'){//车贷
				    		   if(row.protocol_type == '31'){
					   				window.open("../loanappro/carLoanApproBorrowProtocolEqSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);
					   			}else if(row.protocol_type == '32'){
					   				window.open("../loanappro/carLoanApproBorrowProtocolNoSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);
					   			}else if(row.protocol_type == '33'){
					   				window.open("../loanappro/carLoanApproBorrowProtocolSearch.html?wms_cre_credit_head_id="+row.wms_cre_credit_head_id);
					   			}
				    	  }
			    	}
			    });
    		}
    },
	
    //校验房产证号格式："XXX房权证XXX字第XXX号"
	validHouseNo: function(house_no) {
//		if(house_no == '') {
			return true;
//		}
//		var validValue1Index = house_no.indexOf('房权证');
//		var validValue2Index = house_no.indexOf('字第');
//		var validValue3Index = house_no.indexOf('号');
//		
//		var validValue4Index = house_no.indexOf('不动产权第');
//		var validValue5Index = house_no.indexOf('号');
//		if(validValue1Index > -1 && 
//				validValue2Index >-1 && 
//				validValue3Index >-1 &&
//				validValue1Index < validValue2Index && 
//				validValue2Index < validValue3Index) {
//			return true;
//		} else if(validValue4Index >-1 && 
//			validValue5Index >-1 &&
//			validValue4Index < validValue5Index) {
//			return true;
//		} else {
//			return false;
//		}
	},
    
    getDayCountByYearAndMonth: function(year, month) {
    	var dayCount = 30;
    	switch (month) { 
        case 1: 
        case 3: 
        case 5: 
        case 7: 
        case 8: 
        case 10: 
        case 12: 
             dayCount = 31; 
             break; 
        case 4: 
        case 6: 
        case 9: 
        case 11: 
             dayCount = 30; 
             break; 
        case 2: 
             dayCount = 28; 
             if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
                 dayCount = 29; 
             } 
             break; 
        default: 
             break;
    	} 
    	return dayCount;
    },
    /**
     * 根据传递过来的产品类型判断是否是需要处理的产品--贷款申请- 客户信息完善- 终审审批- 放款申请
     * @param cre_loan_type
     * @returns {Boolean}
     */
    getCreType: function(cre_loan_type) {
    	/*if(cre_loan_type == "785" || cre_loan_type == "786" || cre_loan_type == "970"){
    		return true;
    	}
    	return false;*/
    	if(cre_loan_type == "116" || cre_loan_type == "117" || cre_loan_type == "319" || cre_loan_type == "320"){
    		return false;
    	}
    	return true;
    },
    
 // 加法函数，用来得到精确的加法结果
	// 说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
	// 调用：accAdd(arg1,arg2)
	// 返回值：arg1加上arg2的精确结果
	accAdd : function(arg1, arg2) {
		var r1, r2, m;
		try {
			r1 = arg1.toString().split(".")[1].length;
		} catch (e) {
			r1 = 0;
		}
		try {
			r2 = arg2.toString().split(".")[1].length;
		} catch (e) {
			r2 = 0;
		}
		m = Math.pow(10, Math.max(r1, r2));
		return (this.accMul(arg1,m) + this.accMul(arg2,m)) / m;
	},

	// 减法函数，用来得到精确的减法结果
	// 说明：javascript的加法结果会有误差，在两个浮点数相减的时候会比较明显。这个函数返回较为精确的减法结果。
	// 调用：accSub(arg1,arg2)
	// 返回值：arg1减arg2的精确结果
	accSub : function(arg1, arg2) {
		var r1, r2, m, n;
		try {
			r1 = arg1.toString().split(".")[1].length;
		} catch (e) {
			r1 = 0;
		}
		try {
			r2 = arg2.toString().split(".")[1].length;
		} catch (e) {
			r2 = 0;
		}
		m = Math.pow(10, Math.max(r1, r2));
		// last modify by deeka
		// 动态控制精度长度
		n = (r1 >= r2) ? r1 : r2;
		return ((arg1 * m - arg2 * m) / m).toFixed(n);
	},

	// 乘法函数，用来得到精确的乘法结果
	// 说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
	// 调用：accMul(arg1,arg2)
	// 返回值：arg1乘以arg2的精确结果
	accMul : function(arg1, arg2) {
		if(arg1 == null || arg2 == null){
			return null;
		}
		var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
		try {
			m += s1.split(".")[1].length;
		} catch (e) {
		}
		try {
			m += s2.split(".")[1].length;
		} catch (e) {
		}
		return Number(s1.replace(".", "")) * Number(s2.replace(".", ""))
				/ Math.pow(10, m);
	},
	
	// 除法函数，用来得到精确的除法结果
	// 说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
	// 调用：accDiv(arg1,arg2)
	// 返回值：arg1除以arg2的精确结果
	accDiv : function(arg1, arg2) {
		var t1 = 0, t2 = 0, r1, r2;
		try {
			t1 = arg1.toString().split(".")[1].length;
		} catch (e) {
		}
		try {
			t2 = arg2.toString().split(".")[1].length;
		} catch (e) {
		}
		with (Math) {
			r1 = Number(arg1.toString().replace(".", ""));
			r2 = Number(arg2.toString().replace(".", ""));
			return (r1 / r2) * pow(10, t2 - t1);
		}
	},
	
	//获取数据字典表记录（用于某些下拉列表或单选按钮等组件参数不写死）
	getSysDictData : function(wms_sys_dict_id) {
		var jsonData = {};
		//查询归属公司下拉列表值(基础数据)
	    $.ajax({ 
	        type: "POST", 
	        url: global_param.context_name + "/sysmanage/wmssysdictdatabydictid.do",
	        async: false,
	        dataType: "json",
	        data: { 
	        	wms_sys_dict_id : wms_sys_dict_id 
	        }, 
	        success: function(data) {
	        	jsonData = data;
	        }
	    });
	    return jsonData;
	},
	//获取图片下载信息
	downloadAllImg :function(jqueryViewJson){
    	var jquerydownloadJson=[];
    	for(var i=0;i<jqueryViewJson.length;i++){
    		var downloadJson={};
    		downloadJson.attachment_address=jqueryViewJson[i].attachment_address;
    		downloadJson.attachment_old_name=jqueryViewJson[i].attachment_old_name;
    		jquerydownloadJson.push(downloadJson);
    	}
    	var url=global_param.save_file_url + "/wms/getFileBatchZIP.do?file_address=";
    	window.location.href =url+JSON.stringify(jquerydownloadJson);	
	},
	//显示全部图片
	openShowAllImg :function(fileArryf){
		jqueryViewJson=fileArryf;
    	window.open("../jqueryView.html");
	},
	//初始化下拉框 select_name字段名称 select_value 下拉框的value  下拉框的描述select_meaning 是否添加请选择isEmpty=true 字典表idwms_sys_dict_id
	init_select_value :function(select_name,select_value,select_meaning,isEmpty,wms_sys_dict_id){
		$.ajax({
	        url: global_param.context_name + '/sysmanage/wmssysdictdatabydictidemptyall.do?isEmpty='+isEmpty+'&wms_sys_dict_id='+wms_sys_dict_id,
	        type: 'GET',
	        data: {},
	        async: false,
	        success: function(data) {
	        	$(data).each(function(i, o) {
		        	$('#'+select_name).append('<option value="' + o[select_value] + '">' +  o[select_meaning] + '</option>');
	        	});
	        }
	    });
	},
	//初始化下拉框 select_name字段名称 select_value 下拉框的value  下拉框的描述select_meaning 是否添加请选择isEmpty=true 字典表idwms_sys_dict_id
	init_select_value_name :function(select_name,select_value,select_meaning,isEmpty,wms_sys_dict_id){
		$.ajax({
	        url: global_param.context_name + '/sysmanage/wmssysdictdatabydictidemptyall.do?isEmpty='+isEmpty+'&wms_sys_dict_id='+wms_sys_dict_id,
	        type: 'GET',
	        data: {},
	        async: false,
	        success: function(data) {
	        	$(data).each(function(i, o) {
		        	$("select[name="+select_name+"]").append('<option value="' + o[select_value] + '">' +  o[select_meaning] + '</option>');
	        	});
	        }
	    });
	},
	unique : function(arrayList){
		 var res = [];
		 var json = {};
		 for(var i = 0; i < arrayList.length; i++){
		  if(!json[arrayList[i]]){
		   res.push(arrayList[i]);
		   json[arrayList[i]] = 1;
		  }
		 }
		 return res;
	 },
	//获取当前日期
	showDate:function (){
	   var mydate = new Date();
	   var str = "" + mydate.getFullYear() + "-";
	   str += (mydate.getMonth()+1) + "-";
	   str += mydate.getDate();
	   return str;
    },
	//获取当前日期--只有月日
	showDateMD:function (){
	   var mydate = new Date();
	   var str = "";
	   str += (mydate.getMonth()+1) + "-";
	   str += mydate.getDate();
	   return str;
    },
    deepCopy : function(source) { 
        var result = {};
		for (var key in source) {
		       result[key] = typeof source[key] === 'object' ? deepCopy(source[key]): source[key];
	    } 
		return result; 
	},
    //获取单据taskid 
    getTaskInfo: function(businessId,debtkey){
  	  var taskInfo=globalUtil.syncGetJson('/sysmanage/getTaskInfo.do',{
  		  businessId: businessId, // 贷款id'
  		  debtkey: debtkey, // 节点名称
	        },'POST');
  	  return taskInfo;
    },
	
	//判断单据在当前查询页(放款申请 放款审批 放款确认)弹出页是否为组合贷页面
	isCombineLoan: function(wms_cre_credit_group_id, bill_status) {
		if(wms_cre_credit_group_id == null || wms_cre_credit_group_id == '' || typeof(wms_cre_credit_group_id) == 'undefined') {
			return false; 
		}
		var flag = false;
		$.ajax({ 
	        type: "POST", 
	        url: global_param.context_name + "/loanfina/searchCombineLoanList.do",
	        async: false,
	        dataType: "json",
	        data: { 
	        	wms_cre_credit_group_id : wms_cre_credit_group_id, 
	        	bill_status : bill_status
	        }, 
	        success: function(data) {
	        	if('combineLoanList' in data && data.combineLoanList != null && data.combineLoanList.length > 1) {
	        		flag = true;
	        	}
	        }
	    });
		return flag;
	},
    
    //获取当前登录人信息
    getLoginUserInfo: function() {
    	var user = {};
    	$.ajax({ 
	        type: "POST", 
	        url: global_param.context_name + "/cremanage/getLoginUserInfo.do",
	        async: false,
	        dataType: "json",
	        data: { 
	        	
	        }, 
	        success: function(data) {
	        	user = data;
	        }
	    });
		return user;
    },
    
    getNextMonth: function (date) {  
         var arr = date.split('-');  
         var year = arr[0]; //获取当前日期的年份  
         var month = arr[1]; //获取当前日期的月份  
         var day = arr[2]; //获取当前日期的日  
         var days = new Date(year, month, 0);  
         days = days.getDate(); //获取当前日期中的月的天数  
         var year2 = year;  
         var month2 = parseInt(month) + 1;  
         if (month2 == 13) {  
             year2 = parseInt(year2) + 1;  
             month2 = 1;  
         }  
         var day2 = day;  
         var days2 = new Date(year2, month2, 0);  
         days2 = days2.getDate();  
         if (day2 > days2) {  
             day2 = days2;  
         }  
         if (month2 < 10) {  
             month2 = '0' + month2;  
         }  
       
         var t2 = year2 + '-' + month2 + '-' + day2;  
         return t2;  
     },  
	
     //验证是否为大于等于0的正整数 
	 isPositiveNumber : function(s){
		 var re = /^\d+$/;
		  return re.test(s) 
	} 
};