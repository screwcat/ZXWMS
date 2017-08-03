(function($) {

	$.fn.extend({
		initMultiSelect : function(params) {
			
			/**
			 * 参数 
			 * data : description : 数组
			 * 		  [{'id' : 1, 'name': '自来也'}, 
			 *         {'id' : 2, 'name': '纲手'}, 
			 *         {'id' : 3, 'name': '卡卡西'},
			 *         {'id' : 4, 'name': '佐助'}, 
			 *         {'id' : 5, 'name': '鸣人'}, 
			 *         {'id' : 6, 'name': '大蛇丸'},
			 *         {'id' : 7, 'name': '小樱'}, 
			 *         {'id' : 8, 'name': '雏田'}, 
			 *         {'id' : 9, 'name': '我爱罗'}],
			   value : 'id' description : input的id
			   show_value : description : 显示名称属性
			   default_value : '--请选择--', description : 默认值
			   width : 200,	description : 宽度(默认值与组件宽度相同)
			   height : 200 description : 高度(默认值200)
			 */
			
			/**
			 *  加载方式：var initMultiParams = {
			 *		         data : [{'id' : 1, 'name': '自来也'}, 
			 *	           		   {'id' : 2, 'name': '纲手'}, 
			 *	            	   {'id' : 3, 'name': '卡卡西'},
			 *	            	   {'id' : 4, 'name': '佐助'}, 
			 *	            	   {'id' : 5, 'name': '鸣人'}, 
			 *	            	   {'id' : 6, 'name': '大蛇丸'},
			 *	            	   {'id' : 7, 'name': '小樱'}, 
			 *	            	   {'id' : 8, 'name': '雏田'}, 
			 *	            	   {'id' : 9, 'name': '我爱罗'}],
			 *	             value : 'id',
			 *	             show_value : 'name',
			 *	             width : 200,
			 *	             height : 200
			 *         };
			 *         $('#customer_name').initMultiSelect(initMultiParams);
			 */
			
			var input_id = '';
			var is_checked_value = '';
			var val_arr = [];
			var show_val_arr = [];
			var left = getAbsoluteLeft(this) + 'px';
			var height =  (params.height | 200) + 'px';
			var width =  (params.width | $(this).css('width')) + 'px';
			var default_value = ('default_value' in params) ? params.default_value : '--请选择--';
			
			if($('#select_multiple_' + input_id).length == 0) {
				input_id = $(this).attr('id');
				
				var appendHTML =
						'<div id="select_multiple_' + input_id +
								'"' + 
								'style="display:none;position:absolute;border:1px solid black;z-index:9999;background-color:white;' +
								';left:' + left + 
								';margin-top:' + ($('#' + input_id + '_replace_div').css('height')) + 
								';height:' + height + 
								';width:' + width + ';">';
				
				
				appendHTML += '<div style="height:' + ((params.height | 200) - 35) + 'px;overflow:auto;"><ul style="padding:10px 5px 5px 10px;">';
				appendHTML += '<li style="font-weight:bold;"><input type="checkbox" class="radio3" id="select_multiple_all_' + input_id + '" />全选';
				
				$(params.data).each(function(i, o) {
					appendHTML += '<li>' + 
					    '<input type="checkbox" class="radio3" name="select_multiple_' + input_id + 
					    '" value="' + o[params.value] + '" />' + 
					    o[params.show_value] +
					'</li>';
				});

				appendHTML += '</ul></div>';
				appendHTML += '<div class="pop-footer6 clearboth" style="bottom: 1px; height: 35px">' +
					'<a id="confirm_multiple_' + input_id + '" class="btnType1 btnSize1">确定</a>' + 
					'<a id="cancel_multiple_' + input_id + '" class="btnType1 btnSize1">取消</a></div>';
				appendHTML += '</div>';
				
				$(this).hide();
				$(this).parent().append('<div id="' + input_id + '_replace_div" class="select3-container select3-container-multi select3"' +
						'style="min-width:' + ((parseInt($(this).css('width')) + 2) + 'px;line-height:20px;') + 
//						'width:' + ((parseInt($(this).css('width')) + 2) + 'px;') + 
						'border:1px #abadb3 solid;font-family:inherit;' +
						'font-size:inherit;' + 'font-weight:inherit;display:block;cursor:pointer;">' + default_value + 
						'<div class="l-trigger" style=""><div class="l-trigger-icon"></div></div></div>');
				
				$(this).parent().append(appendHTML);
					
				//全选
				$(document).on('click', '#select_multiple_all_' + input_id, function() {
					if($(this).is(':checked')) {
						$('input[name=select_multiple_' + input_id + ']').attr('checked', true);
					} else {
						$('input[name=select_multiple_' + input_id + ']').attr('checked', false);
					}
				});
				
				//确定
				$(document).on('click', '#confirm_multiple_' + input_id, function() {
					val_arr = [];
					show_val_arr = [];
					$('input[name=select_multiple_' + input_id + ']:checked').each(function(i, o) {
						val_arr.push($(o).val());
						show_val_arr.push($(o).parent().text());
					});
					$('#select_multiple_' + input_id).css('display', 'none');
					$('#' + input_id).val(val_arr.join(','));
					var addHTML = '<ul class="select3-choices" style="border:0px;">';
					$(show_val_arr).each(function(i, o) {
						addHTML += 
							'<li class="select3-search-choice" style="">' +
								'<div>' + o + '</div>' +   
								'<a href="#" class="select3-search-choice-close"></a>' +    
								'<input type="hidden" value="' + val_arr[i] + '" name="' + input_id + '_val"' + '/>' +
						   	'</li>';
					});
					
					addHTML += '</ul></div>';
					$('#' + input_id + '_replace_div').html(addHTML);
					
					//无选项显示默认值
					if(!val_arr.length) {
						$('#' + input_id + '_replace_div').html(default_value + 
							'<div class="l-trigger" style=""><div class="l-trigger-icon"></div></div>');
					}
				});
				
				//取消
				$(document).on('click', '#cancel_multiple_' + input_id, function() {
					$('#select_multiple_' + input_id).css('display', 'none');
				});
				
			} else {
				
			}
			
			//删除选项
			$(document).on('click', '#' + input_id + '_replace_div .select3-search-choice-close', function(event) {
				event.stopPropagation();
				$(this).parent().remove();
				val_arr = [];
				$('input[name=' + input_id + '_val]').each(function(i, o) {
					val_arr.push($(o).val());
				});
				$('#' + input_id).val(val_arr.join(','));
				//无选项显示默认值
				if(!val_arr.length) {
					$('#' + input_id + '_replace_div').html(default_value + 
						'<div class="l-trigger" style=""><div class="l-trigger-icon"></div></div>');
				}
			});
			
			//再次点击事件
			$(document).on('click', '#' + input_id + '_replace_div', function() {
				$('#select_multiple_' + input_id).css('display', 'block');
				is_checked_value = $('#' + input_id).val();
				
				if(is_checked_value != '') {
					var is_checked_value_arr = is_checked_value.split(',');
					var change_flag = false;
					$('input[name=select_multiple_' + input_id + ']').each(function(i, o) {
						change_flag = false;
						for(var i = 0; i < is_checked_value_arr.length; i++) {
							if($(o).val() == is_checked_value_arr[i]) {
								$(o).attr('checked', true);
								change_flag = true;
								break;
							}
						}
						if(!change_flag) {
						$(o).attr('checked', false);
						$('#select_multiple_all_' + input_id).attr('checked', false);
						}
					});
				} else {
					$('input[name=select_multiple_' + input_id + ']').attr('checked', false);
				}
			});
		}
	
		
	
	});
	

})(jQuery);

//  获取控件左绝对位置
function getAbsoluteLeft(o) {
	oLeft = o.offsetLeft;
	while (o.offsetParent != null) {
		oParent = o.offsetParent;
		oLeft += oParent.offsetLeft;
		o = oParent;
	}
	return oLeft;
}

// 获取控件上绝对位置
function getAbsoluteTop(o) {
	oTop = o.offsetTop;
	while (o.offsetParent != null) {
		oParent = o.offsetParent;
		oTop += oParent.offsetTop; // Add parent top position
		o = oParent;
	}
	return oTop;
}

