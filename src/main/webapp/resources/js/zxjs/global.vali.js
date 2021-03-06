var globalVali = {
		checkForm: function (divId, isValiRequired){
			var result=false;	
			//验证文本输入框
			var inputElementArr = $("#"+divId+" :input");	
			jQuery.each(inputElementArr.get(), function(i, inputElement) {
			//for(i=0;i<inputElementArr.get().length;i++){
				//var inputElement = inputElementArr.get(i);
				var inputVal = inputElement.value;
				//验证是否为空
				if($(inputElement).attr('isRequired')!=null && $(inputElement).parent().css('display')!='none' && globalUtil.isEmpty(inputVal) && isValiRequired){				
			        result = true;	 
			        $(inputElement).parent().addClass("position-re");
			        var lf=$(inputElement).parent().width();
			        $(inputElement).ligerTip({isabsolute:true,absleft:(lf-90),width:80,content: globalUtil.isEmpty(inputElement.title)? "此项必填！": inputElement.title});	
			        $(inputElement).bind('click',function(){
						$(inputElement).ligerHideTip();
					});
			        return true;	        
				}
				//验证是否为非负整数,scope值为l时为左闭右开区间，为r时为左开右闭区间，为a时为左闭右闭区间，默认为左开右开区间
				var positiveIntegerCondition = $(inputElement).attr('isPositiveInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(inputVal)||inputVal==0) && inputVal<parseInt($(inputElement).attr('maxVal')) &&inputVal>parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				if($(inputElement).attr('scope')=='l'){
					positiveIntegerCondition = $(inputElement).attr('isPositiveInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(inputVal)||inputVal==0) && inputVal<parseInt($(inputElement).attr('maxVal')) &&inputVal>=parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}else if($(inputElement).attr('scope')=='r'){
					positiveIntegerCondition = $(inputElement).attr('isPositiveInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(inputVal)||inputVal==0) && inputVal<=parseInt($(inputElement).attr('maxVal')) &&inputVal>parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}else if($(inputElement).attr('scope')=='a'){
					positiveIntegerCondition = $(inputElement).attr('isPositiveInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(inputVal)||inputVal==0) && inputVal<=parseInt($(inputElement).attr('maxVal')) &&inputVal>=parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}
				if(positiveIntegerCondition){
						result=true;
						$(inputElement).parent().addClass("position-re");
						var lf=$(inputElement).parent().width();
						$(inputElement).ligerTip({isabsolute:true,absleft:(lf-90),width:160,content: globalUtil.isEmpty(inputElement.title)? "此项为"+$(inputElement).attr('minVal')+"～"+$(inputElement).attr('maxVal')+"的整数！": inputElement.title});	
						$(inputElement).bind('click',function(){
							$(inputElement).ligerHideTip();
						});
				}
				//验证是否为整数,scope值为l时为左闭右开区间，为r时为左开右闭区间，为a时为左闭右闭区间，默认为左开右开区间
				var integerCondition = $(inputElement).attr('isInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(Math.abs(inputVal))||inputVal==0) && inputVal<parseInt($(inputElement).attr('maxVal')) &&inputVal>parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				if($(inputElement).attr('scope')=='l'){
					integerCondition = $(inputElement).attr('isInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(Math.abs(inputVal))||inputVal==0) && inputVal<parseInt($(inputElement).attr('maxVal')) &&inputVal>=parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}else if($(inputElement).attr('scope')=='r'){
					integerCondition = $(inputElement).attr('isInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(Math.abs(inputVal))||inputVal==0) && inputVal<=parseInt($(inputElement).attr('maxVal')) &&inputVal>parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}else if($(inputElement).attr('scope')=='a'){
					integerCondition = $(inputElement).attr('isInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(Math.abs(inputVal))||inputVal==0) && inputVal<=parseInt($(inputElement).attr('maxVal')) &&inputVal>=parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}
				if(integerCondition){
						result=true;
						$(inputElement).parent().addClass("position-re");
						var lf=$(inputElement).parent().width();
						$(inputElement).ligerTip({isabsolute:true,absleft:(lf-90),width:150,content: globalUtil.isEmpty(inputElement.title)? "此项为"+$(inputElement).attr('minVal')+"～"+$(inputElement).attr('maxVal')+"的整数！": inputElement.title});	
						$(inputElement).bind('click',function(){
							$(inputElement).ligerHideTip();
						});
				}
				//验证是否为小数
				var floatCondition = $(inputElement).attr('isFloat')!=null && $(inputElement).parent().css('display')!='none' && !(globalUtil.isFloat(inputVal) && inputVal<parseFloat($(inputElement).attr('maxVal')) && inputVal>parseFloat($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				if($(inputElement).attr('scope')=='l'){
					floatCondition = $(inputElement).attr('isFloat')!=null && $(inputElement).parent().css('display')!='none' && !(globalUtil.isFloat(inputVal) && inputVal<parseFloat($(inputElement).attr('maxVal')) && inputVal>=parseFloat($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}else if($(inputElement).attr('scope')=='r'){
					floatCondition = $(inputElement).attr('isFloat')!=null && $(inputElement).parent().css('display')!='none' && !(globalUtil.isFloat(inputVal) && inputVal<=parseFloat($(inputElement).attr('maxVal')) && inputVal>parseFloat($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}else if($(inputElement).attr('scope')=='a'){
					floatCondition = $(inputElement).attr('isFloat')!=null && $(inputElement).parent().css('display')!='none' && !(globalUtil.isFloat(inputVal) && inputVal<=parseFloat($(inputElement).attr('maxVal')) && inputVal>=parseFloat($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}
				if(floatCondition){
						result=true;
						$(inputElement).parent().addClass("position-re");
						var lf=$(inputElement).parent().width();
						$(inputElement).ligerTip({isabsolute:true,absleft:(lf-90),width:155,content: globalUtil.isEmpty(inputElement.title)? "此项为"+$(inputElement).attr('minVal')+"～"+$(inputElement).attr('maxVal')+"的数值": inputElement.title });
						$(inputElement).bind('click',function(){
							$(inputElement).ligerHideTip();
						});
				}
				//验证是否为邮箱格式
				if($(inputElement).attr('isEmail')!=null && $(inputElement).parent().css('display')!='none' && !globalUtil.isEmail(inputVal) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal))){
						result=true;
						$(inputElement).parent().addClass("position-re");
						var lf=$(inputElement).parent().width();
						$(inputElement).ligerTip({isabsolute:true,absleft:(lf-90),width:110,content: globalUtil.isEmpty(inputElement.title)? "邮箱格式不正确！": inputElement.title });				
						$(inputElement).bind('click',function(){
							$(inputElement).ligerHideTip();
						});
				}
				//验证是否为邮政编码
				if($(inputElement).attr('isPostalCode')!=null && $(inputElement).parent().css('display')!='none' && !globalUtil.isPostalCode(inputVal) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal))){
						result=true;
						$(inputElement).parent().addClass("position-re");
						var lf=$(inputElement).parent().width();
						$(inputElement).ligerTip({isabsolute:true,absleft:(lf-90),width:110,content: globalUtil.isEmpty(inputElement.title)? "邮编格式不正确！": inputElement.title });				
						$(inputElement).bind('click',function(){
							$(inputElement).ligerHideTip();
						});
				}
				//验证服务密码是否正确
				if($(inputElement).attr('isServerCode')!=null && $(inputElement).parent().css('display')!='none' && !globalUtil.isServerCode(inputVal) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal))){
						result=true;
						$(inputElement).parent().addClass("position-re");
						var lf=$(inputElement).parent().width();
						$(inputElement).ligerTip({isabsolute:true,absleft:(lf-90),width:110,content: globalUtil.isEmpty(inputElement.title)? "服务密码不正确！": inputElement.title });				
						$(inputElement).bind('click',function(){
							$(inputElement).ligerHideTip();
						});
				}
				if ($(inputElement).attr('isPositiveNumber') != null && !globalUtil.isPositiveNumber(inputVal)) {
					result=true;
					$(inputElement).parent().addClass("position-re");
					var lf=$(inputElement).parent().width();
					$(inputElement).ligerTip({isabsolute:true,absleft:(lf-90),width:110,content: globalUtil.isEmpty(inputElement.title)? "此项为正整数！": inputElement.title });				
					$(inputElement).bind('click',function(){
						$(inputElement).ligerHideTip();
					});
				}
				if ($(inputElement).attr('isMoney') != null && !globalUtil.isMoney(inputVal)) {
					result=true;
					$(inputElement).parent().addClass("position-re");
					var lf=$(inputElement).parent().width();
					$(inputElement).ligerTip({isabsolute:true,absleft:(lf-90),width:110,content: globalUtil.isEmpty(inputElement.title)? "此项为最多2位小数！": inputElement.title });				
					$(inputElement).bind('click',function(){
						$(inputElement).ligerHideTip();
					});
				}
			//}
			});	
			
			//验证文本域
			var textareaElementArr = $("#"+divId+" textarea");	
			jQuery.each(textareaElementArr.get(), function(i, textareaElement) {
				var textareaVal = textareaElement.value;
				//验证必填
				if($(textareaElement).attr('isRequired')!=null && $(textareaElement).parent().css('display')!='none' && globalUtil.isEmpty(textareaVal) && isValiRequired){				
					result = true;	
					$(textareaElement).parent().addClass("position-re");
					var lf=$(textareaElement).parent().width();
				    $(textareaElement).ligerTip({isabsolute:true,absleft:(lf-90),width:80,content: globalUtil.isEmpty(textareaElement.title)? "此项为必填！": textareaElement.title});	
				    $(textareaElement).bind('click',function(){
				    	$(textareaElement).ligerHideTip();
					});
				    return true;
				}
				//验证是否超过最大长度
				if($(textareaElement).attr('maxLen')!=null && $(textareaElement).parent().css('display')!='none' && $.trim(textareaVal).length>parseInt($(textareaElement).attr('maxLen'))){
						result=true;
						$(textareaElement).parent().addClass("position-re");
						var lf=$(textareaElement).parent().width();
						$(textareaElement).ligerTip({isabsolute:true,absleft:(lf-90),width:110,content: globalUtil.isEmpty(textareaElement.title)? "此项已超过最大长度："+$(textareaElement).attr('maxLen') : textareaElement.title});	
						$(textareaElement).bind('click',function(){
							$(textareaElement).ligerHideTip();
						});
				}
			});
			if(isValiRequired){
				//验证下拉框必选
				var selectElementArr = $("#"+divId+" select");
				jQuery.each(selectElementArr.get(), function(i, selectElement) {
					var selectVal = selectElement.value;	
					if($(selectElement).attr('isRequired')!=null && $(selectElement).parent().css('display')!='none' && selectVal==0){				
						result = true;	 
						$(selectElement).parent().addClass("position-re");
						var lf=$(selectElement).parent().width();
				        $(selectElement).ligerTip({isabsolute:true,absleft:(lf-90),width:80,content: globalUtil.isEmpty(selectElement.title)? "此项必选！": selectElement.title});	
				        $(selectElement).bind('click',function(){
							$(selectElement).ligerHideTip();
						});
				        $(selectElement).bind('change',function(){
							$(selectElement).ligerHideTip();
						});
					}
				});
				//验证单选钮必填
				var radioDivElementArr = $("#"+divId+" div[radioIsRequired],"+"#"+divId+" td[radioIsRequired]");
				jQuery.each(radioDivElementArr.get(), function(i, radioDivElement) {
					if($(radioDivElement).css('display')!='none'){
						var radioElementArr = $(radioDivElement).children();
						var flag=false;
						jQuery.each(radioElementArr.get(), function(j, radioElement) {
							var status = $(radioElement).attr('checked');
							if(status=='checked'){				
								flag = true;					                
							}
						});
						if(!flag){
							result = true;	 
							$(radioDivElement).parent().addClass("position-re");
							var lf=$(radioDivElement).parent().width();
							$(radioDivElement).ligerTip({absleft:(lf-20),isabsolute:true,width:80,content: globalUtil.isEmpty(radioDivElement.title)? "此项必选！": radioDivElement.title});	
					        $(radioDivElement).bind('click',function(){
								$(radioDivElement).ligerHideTip();
							});
						}
					}		
				});
				//验证多选钮至少选一项
				var checkboxDivElementArr = $("#"+divId+" div[checkboxIsRequired],"+"#"+divId+" td[checkboxIsRequired]");
				jQuery.each(checkboxDivElementArr.get(), function(i, checkboxDivElement) {
					if($(checkboxDivElement).css('display')!='none'){
						var checkboxElementArr = $(checkboxDivElement).children();
						var flag=false;
						jQuery.each(checkboxElementArr.get(), function(j, checkboxElement) {
							var status = $(checkboxElement).attr('checked');
							if(status=='checked'){				
								flag = true;					                
							}
						});
						if(!flag){
							result = true;	
							$(checkboxDivElement).parent().addClass("position-re");
							var lf=$(checkboxDivElement).width();
							$(checkboxDivElement).ligerTip({absleft:(lf+100),isabsolute:true,width:120,content: globalUtil.isEmpty(checkboxDivElement.title)? "此项至少选择一项！": checkboxDivElement.title});	
					        $(checkboxDivElement).bind('click',function(){
					        	$(checkboxDivElement).ligerHideTip();
							});
						}
					}		
				});
				//验证ligerui下拉框必选
				var ligeruiSelectElementList = $("#"+divId+" input[type=text][ligeruiSelectIsRequired]");
				$.each(ligeruiSelectElementList,function(i,ligeruiSelectElement){
					var id = ligeruiSelectElement.id;
					var ligeruiId = ligeruiSelectElement.attributes.ligeruiid;
					if(ligeruiId){
						if($('#'+id).val()=='请选择'){
							result = true;	
							$(ligeruiSelectElement).parent().addClass("position-re");
							var lf=$(ligeruiSelectElement).parent().width();
							$(ligeruiSelectElement).ligerTip({isabsolute:true,absleft:(lf-90),width:80,content: globalUtil.isEmpty(ligeruiSelectElement.title)? "此项必选！": ligeruiSelectElement.title});	
					        $(ligeruiSelectElement).bind('click',function(){
								$(ligeruiSelectElement).ligerHideTip();
							});
					        $(ligeruiSelectElement).bind('change',function(){
								$(ligeruiSelectElement).ligerHideTip();
							});
						}
					}
				});			
			}
			return result;		
		},
		
		checkFormFormat: function (divId, isValiRequired){
			var result = false;	
			//验证文本输入框
			var inputElementArr = $("#"+divId+" :input");	
			jQuery.each(inputElementArr.get(), function(i, inputElement) {
			//for(i=0;i<inputElementArr.get().length;i++){
				//var inputElement = inputElementArr.get(i);
				var inputVal = inputElement.value;
				//验证是否为空
				if($(inputElement).attr('isRequired')!=null && $(inputElement).parent().css('display')!='none' && globalUtil.isEmpty(inputVal) && isValiRequired){				
			        result = true;	 
			        $(inputElement).parent().addClass("position-re");
			        var lf=$(inputElement).parent().width();
			        $(inputElement).ligerTip({isabsolute:true,absleft:(lf-90),width:80,content: globalUtil.isEmpty(inputElement.title)? "此项必填！": inputElement.title});	
			        $(inputElement).bind('click',function(){
						$(inputElement).ligerHideTip();
					});
			        return true;	        
				}
				//验证是否为非负整数,scope值为l时为左闭右开区间，为r时为左开右闭区间，为a时为左闭右闭区间，默认为左开右开区间
				var positiveIntegerCondition = $(inputElement).attr('isPositiveInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(inputVal)||inputVal==0) && inputVal<parseInt($(inputElement).attr('maxVal')) &&inputVal>parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				if($(inputElement).attr('scope')=='l'){
					positiveIntegerCondition = $(inputElement).attr('isPositiveInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(inputVal)||inputVal==0) && inputVal<parseInt($(inputElement).attr('maxVal')) &&inputVal>=parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}else if($(inputElement).attr('scope')=='r'){
					positiveIntegerCondition = $(inputElement).attr('isPositiveInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(inputVal)||inputVal==0) && inputVal<=parseInt($(inputElement).attr('maxVal')) &&inputVal>parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}else if($(inputElement).attr('scope')=='a'){
					positiveIntegerCondition = $(inputElement).attr('isPositiveInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(inputVal)||inputVal==0) && inputVal<=parseInt($(inputElement).attr('maxVal')) &&inputVal>=parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}
				if(positiveIntegerCondition){
						result=true;
						$(inputElement).parent().addClass("position-re");
						var lf=$(inputElement).parent().width();
						$(inputElement).ligerTip({isabsolute:true,absleft:(lf-90),width:160,content: globalUtil.isEmpty(inputElement.title)? "此项为"+$(inputElement).attr('minVal')+"～"+$(inputElement).attr('maxVal')+"的整数！": inputElement.title});	
						$(inputElement).bind('click',function(){
							$(inputElement).ligerHideTip();
						});
				}
				//验证是否为整数,scope值为l时为左闭右开区间，为r时为左开右闭区间，为a时为左闭右闭区间，默认为左开右开区间
				var integerCondition = $(inputElement).attr('isInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(Math.abs(inputVal))||inputVal==0) && inputVal<parseInt($(inputElement).attr('maxVal')) &&inputVal>parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				if($(inputElement).attr('scope')=='l'){
					integerCondition = $(inputElement).attr('isInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(Math.abs(inputVal))||inputVal==0) && inputVal<parseInt($(inputElement).attr('maxVal')) &&inputVal>=parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}else if($(inputElement).attr('scope')=='r'){
					integerCondition = $(inputElement).attr('isInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(Math.abs(inputVal))||inputVal==0) && inputVal<=parseInt($(inputElement).attr('maxVal')) &&inputVal>parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}else if($(inputElement).attr('scope')=='a'){
					integerCondition = $(inputElement).attr('isInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(Math.abs(inputVal))||inputVal==0) && inputVal<=parseInt($(inputElement).attr('maxVal')) &&inputVal>=parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}
				if(integerCondition){
						result=true;
						$(inputElement).parent().addClass("position-re");
						var lf=$(inputElement).parent().width();
						$(inputElement).ligerTip({isabsolute:true,absleft:(lf-90),width:150,content: globalUtil.isEmpty(inputElement.title)? "此项为"+$(inputElement).attr('minVal')+"～"+$(inputElement).attr('maxVal')+"的整数！": inputElement.title});	
						$(inputElement).bind('click',function(){
							$(inputElement).ligerHideTip();
						});
				}
				//验证是否为小数
				var floatCondition = $(inputElement).attr('isFloat')!=null && $(inputElement).parent().css('display')!='none' && !(globalUtil.isFloat(inputVal) && inputVal<parseFloat($(inputElement).attr('maxVal')) && inputVal>parseFloat($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				if($(inputElement).attr('scope')=='l'){
					floatCondition = $(inputElement).attr('isFloat')!=null && $(inputElement).parent().css('display')!='none' && !(globalUtil.isFloat(inputVal) && inputVal<parseFloat($(inputElement).attr('maxVal')) && inputVal>=parseFloat($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}else if($(inputElement).attr('scope')=='r'){
					floatCondition = $(inputElement).attr('isFloat')!=null && $(inputElement).parent().css('display')!='none' && !(globalUtil.isFloat(inputVal) && inputVal<=parseFloat($(inputElement).attr('maxVal')) && inputVal>parseFloat($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}else if($(inputElement).attr('scope')=='a'){
					floatCondition = $(inputElement).attr('isFloat')!=null && $(inputElement).parent().css('display')!='none' && !(globalUtil.isFloat(inputVal) && inputVal<=parseFloat($(inputElement).attr('maxVal')) && inputVal>=parseFloat($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
				}
				if(floatCondition){
						result=true;
						$(inputElement).parent().addClass("position-re");
						var lf=$(inputElement).parent().width();
						$(inputElement).ligerTip({isabsolute:true,absleft:(lf-90),width:155,content: globalUtil.isEmpty(inputElement.title)? "此项为"+$(inputElement).attr('minVal')+"～"+$(inputElement).attr('maxVal')+"的数值": inputElement.title });
						$(inputElement).bind('click',function(){
							$(inputElement).ligerHideTip();
						});
				}
				//验证是否为邮箱格式
				if($(inputElement).attr('isEmail')!=null && $(inputElement).parent().css('display')!='none' && !globalUtil.isEmail(inputVal) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal))){
						result=true;
						$(inputElement).parent().addClass("position-re");
						var lf=$(inputElement).parent().width();
						$(inputElement).ligerTip({isabsolute:true,absleft:(lf-90),width:110,content: globalUtil.isEmpty(inputElement.title)? "邮箱格式不正确！": inputElement.title });				
						$(inputElement).bind('click',function(){
							$(inputElement).ligerHideTip();
						});
				}
				//验证是否为邮政编码
				if($(inputElement).attr('isPostalCode')!=null && $(inputElement).parent().css('display')!='none' && !globalUtil.isPostalCode(inputVal) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal))){
						result=true;
						$(inputElement).parent().addClass("position-re");
						var lf=$(inputElement).parent().width();
						$(inputElement).ligerTip({isabsolute:true,absleft:(lf-90),width:110,content: globalUtil.isEmpty(inputElement.title)? "邮编格式不正确！": inputElement.title });				
						$(inputElement).bind('click',function(){
							$(inputElement).ligerHideTip();
						});
				}
				//验证服务密码是否正确
				if($(inputElement).attr('isServerCode')!=null && $(inputElement).parent().css('display')!='none' && !globalUtil.isServerCode(inputVal) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal))){
						result=true;
						$(inputElement).parent().addClass("position-re");
						var lf=$(inputElement).parent().width();
						$(inputElement).ligerTip({isabsolute:true,absleft:(lf-90),width:110,content: globalUtil.isEmpty(inputElement.title)? "服务密码不正确！": inputElement.title });				
						$(inputElement).bind('click',function(){
							$(inputElement).ligerHideTip();
						});
				}
			//}
			});	
			
			//验证文本域
			var textareaElementArr = $("#"+divId+" textarea");	
			jQuery.each(textareaElementArr.get(), function(i, textareaElement) {
				var textareaVal = textareaElement.value;
				//验证必填
				if($(textareaElement).attr('isRequired')!=null && $(textareaElement).parent().css('display')!='none' && globalUtil.isEmpty(textareaVal) && isValiRequired){				
					result = true;	
					$(textareaElement).parent().addClass("position-re");
					var lf=$(textareaElement).parent().width();
				    $(textareaElement).ligerTip({isabsolute:true,absleft:(lf-90),width:80,content: globalUtil.isEmpty(textareaElement.title)? "此项为必填！": textareaElement.title});	
				    $(textareaElement).bind('click',function(){
				    	$(textareaElement).ligerHideTip();
					});
				    return true;
				}
				//验证是否超过最大长度
				if($(textareaElement).attr('maxLen')!=null && $(textareaElement).parent().css('display')!='none' && $.trim(textareaVal).length>parseInt($(textareaElement).attr('maxLen'))){
						result=true;
						$(textareaElement).parent().addClass("position-re");
						var lf=$(textareaElement).parent().width();
						$(textareaElement).ligerTip({isabsolute:true,absleft:(lf-90),width:110,content: globalUtil.isEmpty(textareaElement.title)? "此项已超过最大长度："+$(textareaElement).attr('maxLen') : textareaElement.title});	
						$(textareaElement).bind('click',function(){
							$(textareaElement).ligerHideTip();
						});
				}
			});
			return result;		
		},	
		
	checkFormalert: function (divId, isValiRequired){
		var result=false;	
		//验证文本输入框
		var inputElementArr = $("#"+divId+" :input");	
		jQuery.each(inputElementArr.get(), function(i, inputElement) {
			var inputVal = inputElement.value;
			//验证是否为空
			if($(inputElement).attr('isRequired')!=null && $(inputElement).parent().css('display')!='none' && globalUtil.isEmpty(inputVal) && isValiRequired){				
		        result = true;	
		        globalUtil.alertMsg(globalUtil.isEmpty(inputElement.title)? "此项必填！": inputElement.title,function(){
		        	$(inputElement).focus();
				});		        
		        return false;	        
			}
			//验证是否为非负整数,scope值为l时为左闭右开区间，为r时为左开右闭区间，为a时为左闭右闭区间，默认为左开右开区间
			var positiveIntegerCondition = $(inputElement).attr('isPositiveInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(inputVal)||inputVal==0) && inputVal<parseInt($(inputElement).attr('maxVal')) &&inputVal>parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
			if($(inputElement).attr('scope')=='l'){
				positiveIntegerCondition = $(inputElement).attr('isPositiveInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(inputVal)||inputVal==0) && inputVal<parseInt($(inputElement).attr('maxVal')) &&inputVal>=parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
			}else if($(inputElement).attr('scope')=='r'){
				positiveIntegerCondition = $(inputElement).attr('isPositiveInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(inputVal)||inputVal==0) && inputVal<=parseInt($(inputElement).attr('maxVal')) &&inputVal>parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
			}else if($(inputElement).attr('scope')=='a'){
				positiveIntegerCondition = $(inputElement).attr('isPositiveInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(inputVal)||inputVal==0) && inputVal<=parseInt($(inputElement).attr('maxVal')) &&inputVal>=parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
			}
			if(positiveIntegerCondition){
				result=true;
				globalUtil.alertMsg(globalUtil.isEmpty(inputElement.title)? "此项为"+$(inputElement).attr('minVal')+"～"+$(inputElement).attr('maxVal')+"的整数！": inputElement.title,function(){
				    $(inputElement).focus();
				});		        
				return false;
			}
			//验证是否为整数,scope值为l时为左闭右开区间，为r时为左开右闭区间，为a时为左闭右闭区间，默认为左开右开区间
			var integerCondition = $(inputElement).attr('isInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(Math.abs(inputVal))||inputVal==0) && inputVal<parseInt($(inputElement).attr('maxVal')) &&inputVal>parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
			if($(inputElement).attr('scope')=='l'){
				integerCondition = $(inputElement).attr('isInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(Math.abs(inputVal))||inputVal==0) && inputVal<parseInt($(inputElement).attr('maxVal')) &&inputVal>=parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
			}else if($(inputElement).attr('scope')=='r'){
				integerCondition = $(inputElement).attr('isInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(Math.abs(inputVal))||inputVal==0) && inputVal<=parseInt($(inputElement).attr('maxVal')) &&inputVal>parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
			}else if($(inputElement).attr('scope')=='a'){
				integerCondition = $(inputElement).attr('isInteger')!=null && $(inputElement).parent().css('display')!='none' && !((globalUtil.isNum(Math.abs(inputVal))||inputVal==0) && inputVal<=parseInt($(inputElement).attr('maxVal')) &&inputVal>=parseInt($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
			}
			if(integerCondition){
				result=true;
				globalUtil.alertMsg(globalUtil.isEmpty(inputElement.title)? "此项为"+$(inputElement).attr('minVal')+"～"+$(inputElement).attr('maxVal')+"的整数！": inputElement.title,function(){
				    $(inputElement).focus();
				});		        
				return false;
			}
			//验证是否为小数
			var floatCondition = $(inputElement).attr('isFloat')!=null && $(inputElement).parent().css('display')!='none' && !(globalUtil.isFloat(inputVal) && inputVal<parseFloat($(inputElement).attr('maxVal')) && inputVal>parseFloat($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
			if($(inputElement).attr('scope')=='l'){
				floatCondition = $(inputElement).attr('isFloat')!=null && $(inputElement).parent().css('display')!='none' && !(globalUtil.isFloat(inputVal) && inputVal<parseFloat($(inputElement).attr('maxVal')) && inputVal>=parseFloat($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
			}else if($(inputElement).attr('scope')=='r'){
				floatCondition = $(inputElement).attr('isFloat')!=null && $(inputElement).parent().css('display')!='none' && !(globalUtil.isFloat(inputVal) && inputVal<=parseFloat($(inputElement).attr('maxVal')) && inputVal>parseFloat($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
			}else if($(inputElement).attr('scope')=='a'){
				floatCondition = $(inputElement).attr('isFloat')!=null && $(inputElement).parent().css('display')!='none' && !(globalUtil.isFloat(inputVal) && inputVal<=parseFloat($(inputElement).attr('maxVal')) && inputVal>=parseFloat($(inputElement).attr('minVal'))) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal));
			}
			if(floatCondition){
				result=true;
				globalUtil.alertMsg(globalUtil.isEmpty(inputElement.title)? "此项为"+$(inputElement).attr('minVal')+"～"+$(inputElement).attr('maxVal')+"的数值": inputElement.title,function(){
				    $(inputElement).focus();
				});		        
				return false;
			}
			//验证是否为邮箱格式
			if($(inputElement).attr('isEmail')!=null && $(inputElement).parent().css('display')!='none' && !globalUtil.isEmail(inputVal) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal))){
				result=true;
				globalUtil.alertMsg(globalUtil.isEmpty(inputElement.title)? "邮箱格式不正确！": inputElement.title,function(){
				    $(inputElement).focus();
				});		        
				return false;
			}
			//验证是否为邮政编码
			if($(inputElement).attr('isPostalCode')!=null && $(inputElement).parent().css('display')!='none' && !globalUtil.isPostalCode(inputVal) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal))){
					result=true;
					globalUtil.alertMsg(globalUtil.isEmpty(inputElement.title)? "邮编格式不正确！": inputElement.title,function(){
					    $(inputElement).focus();
					});		        
					return false;
			}
			//验证服务密码是否正确
			if($(inputElement).attr('isServerCode')!=null && $(inputElement).parent().css('display')!='none' && !globalUtil.isServerCode(inputVal) && !((!isValiRequired || $(inputElement).attr('isRequired')==null) && globalUtil.isEmpty(inputVal))){
					result=true;
					globalUtil.alertMsg(globalUtil.isEmpty(inputElement.title)? "服务密码不正确！": inputElement.title,function(){
					    $(inputElement).focus();
					});		        
					return false;
			}
		//}
		});	

				return result;
	}
}