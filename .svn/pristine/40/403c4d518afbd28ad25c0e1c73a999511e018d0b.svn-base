function registerXlsFlashBtn(moviePath,fileName,titleJson,dataJson,obj,fnClick){
	if(moviePath){
		ZeroClipboard_TableTools.moviePath = moviePath;
	}
	var flash = new ZeroClipboard_TableTools.Client();
	flash.setHandCursor( true );
	flash.setAction( 'save' );
	flash.setCharSet('UTF16LE' );
	flash.setBomInc( true );
	flash.setFileName(fileName);
	flash.addEventListener('mouseDown', function(client) {
		
		if (typeof(fnClick) == 'function') {
			// actual function reference
			var ss = fnClick.call(this);
			if(typeof ss == "string"){
				flash.setText(ss);
			}else{
				var sNewline = navigator.userAgent.match(/Windows/) ? "\r\n" : "\n";
				//ss.title={'name':'姓名','age':'年龄'};
				//ss.data=[{'name':'张三','age':30},{'name':'张三','age':30}];
				var title = ss.title;
				var contents = ss.data;
				var aData=[];
				var titleKey=[];
				var aRow=[];
				for (var key in title) {            
					aRow.push(title[key]);
					titleKey.push(key);
				}
				aData.push( aRow.join("\t") );
				for(var i=0;i<contents.length;i++){
					aRow = [];
					for (var j=0;j<titleKey.length;j++) {
						var cellData = contents[i][titleKey[j]];
						if(cellData == null || cellData == 'undefined' || cellData == undefined){
							cellData = '';
						}
						if(titleKey[j] == 'id_card' || titleKey[j] == 'com_debtor_identity_id') {
							aRow.push('ID:'+cellData);  
						}else if(titleKey[j] == 'vehicle_evaluation_price') {
							aRow.push(' ' + cellData);
						}else {
							aRow.push(''+cellData);  
						}
						
					}
					aData.push( aRow.join("\t") );
				}
				var _sLastData = aData.join(sNewline);
				flash.setText(_sLastData);
			}
		}
	} );
	flash.glue(obj, "" );
	//fnGlue( flash, null, objId, "Save for Excel" );
}
function fnGlue ( clip, node, id, text )
{
	if ( document.getElementById(id) )
	{
		clip.glue( document.getElementById(id), text );
	}
	else
	{
		setTimeout( function () {
			fnGlue( clip, node, id, text );
		}, 100 );
	}
}
