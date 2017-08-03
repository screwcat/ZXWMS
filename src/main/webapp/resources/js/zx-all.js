var global_param = {
		context_name: '/WMS',
		tab_number: 1, // 不控制显示标签数
        tab_limit_tip: true, // 标志标签提示是否已经给出，如果没有给出那么当标签数量超过指定数量的时候则给予提示，如已经提示过了那么直接自动关闭最早打开的标签
        tab_limit_tip_content: '主页默认最多同时打开7个标签页，请您关闭不用的标签页后再打开新页，默认会关闭最早打开的标签页，此提示不再出现。', // 关闭标签提示
        layout_bottomHeight:0, //标志主页面上最下面面板的高度
        layout_leftWidth: 130, //标志主页面上左侧菜单面板的宽度
        layout_titleHeight: 30, //面板标题高度，与样式“l-tab-links”中的height相关
        tree_menu_backcolor: '#e9e9eb', //树状菜单背景色
        tree_menu_nodeWidth: 177, //树状菜单选中宽度
		version: '?v=1.7.1',  // 解决静态缓存问题加入的版本信息
		session_check: true, // 是否检查session过期设置
		use_cookie:true, // 设置是否使用cookie 
  		upload_file_url: 'http://192.168.1.107:9080/upload',
		save_file_url: 'http://192.168.1.107:9080/WMS_UP',
		judge_return : '2014-08-19',//设置退件时间节点
		file_max_size : 60,//上传文件最大兆数
		timestamp_val : '2015-06-01',//判断短时间：作用作废单据用
		category_id1 : '115',//理财产品id 用于打印协议--有声明协议的
		category_id2 : '111',//理财产品id 用于打印协议--无声明协议的
		category_id3 : '112',//理财产品id 用于打印协议--无声明协议的
		dis_date : '2016-09-08'//赎回查询页面 此日期之前弹出老版本页面
};




document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/jquery-1.8.3.min.js"></script>');
document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/ajaxfileupload.js'+global_param.version+'"></script>');
document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/jquery.cookie.js"></script>');

document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/ligerui.all.js"></script>');
document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/date/WdatePicker.js"></script>');
document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/jquery.json-2.3.js"></script>');
document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/jquery.query-2.1.7.js"></script>');

document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/zxjs/error.message.js"></script>');
document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/zxjs/global.ligerui.extend.js"></script>');
document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/zxjs/global.util.js"></script>');
document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/zxjs/page.init.js"></script>');
document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/zxjs/menu.js"></script>');
document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/zxjs/tab.js"></script>');
document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/zxjs/form.js"></script>');
document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/zxjs/global.vali.js"></script>');
document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/zxjs/pagination.js'+global_param.version+'"></script>');
document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/zxjs/loading.js"></script>');
document.write('<script language="Javascript" src="'+global_param.context_name+'/resources/js/zxjs/framework.js"></script>');
