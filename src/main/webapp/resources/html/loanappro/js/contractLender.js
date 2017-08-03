//合同出借人信息自动带出
jQuery(function($) {
	$(document).on('blur', "#creditor_name,#creditor_name11,input[name=creditor_name],input[name=creditor_name11]", function(){
		var lender_name = $(this).val();
		if(lender_name != '') {
			$.ajax({ 
		        type: "POST", 
		        url: global_param.context_name + "/loanappro/searchLenderInfo.do",
		        async: false,
		        dataType: "json",
		        data: { 
		        	lender_name : lender_name
		        }, 
		        success: function(data) {
		        	if(data.lenderList != null && data.lenderList.length > 0) {
		        		var lenderInfo = data.lenderList[0];
		        		//根据出借人信息自动带出身份证号、通讯地址、联系电话、甲方放款账户（户名，账号，开户行）
		        		$('#creditor_name').val(lenderInfo.lender_name);
		        		$('#creditor_identity_id').val(lenderInfo.id_card);
		        		$('#creditor_address').val(lenderInfo.contact_address);
		        		$('#creditor_tel').val(lenderInfo.mobile_phone);
		        		$('#creditor_loan_name').val(lenderInfo.lender_name);
		        		$('#creditor_loan_number').val(lenderInfo.card_no);
		        		$('#creditor_loan_bank').val(lenderInfo.bank_of_deposit_name);
		        		
		        		$('input[name=creditor_name]').val(lender_name);
		        		$('input[name=creditor_identity_id]').val(lenderInfo.id_card);
		        		$('input[name=creditor_address]').val(lenderInfo.contact_address);
		        		$('input[name=creditor_tel]').val(lenderInfo.mobile_phone);
		        		$('input[name=creditor_loan_name]').val(lenderInfo.lender_name);
		        		$('input[name=creditor_loan_number]').val(lenderInfo.card_no);
		        		$('input[name=creditor_loan_bank]').val(lenderInfo.bank_of_deposit_name);
		        		
		        		$('#creditor_name11').val(lender_name);
		        		$('#creditor_identity_id11').val(lenderInfo.id_card);
		        		$('#creditor_address11').val(lenderInfo.contact_address);
		        		$('#creditor_tel11').val(lenderInfo.mobile_phone);
		        		$('#creditor_loan_name11').val(lenderInfo.lender_name);
		        		$('#creditor_loan_number11').val(lenderInfo.card_no);
		        		$('#creditor_loan_bank11').val(lenderInfo.bank_of_deposit_name);
		        		
		        		$('input[name=creditor_name11]').val(lender_name);
		        		$('input[name=creditor_identity_id11]').val(lenderInfo.id_card);
		        		$('input[name=creditor_address11]').val(lenderInfo.contact_address);
		        		$('input[name=creditor_tel11]').val(lenderInfo.mobile_phone);
		        		$('input[name=creditor_loan_name11]').val(lenderInfo.lender_name);
		        		$('input[name=creditor_loan_number11]').val(lenderInfo.card_no);
		        		$('input[name=creditor_loan_bank11]').val(lenderInfo.bank_of_deposit_name);
		        	}
		        }
		    });
		}
	});
	
});
function initCreditorName(){
	var creditor_name = $("#creditor_name").val();
	if(creditor_name != ''){
		$.ajax({ 
	        type: "POST", 
	        url: global_param.context_name + "/loanappro/searchLenderInfo.do",
	        async: false,
	        dataType: "json",
	        data: { 
	        	lender_name : creditor_name
	        }, 
	        success: function(data) {
	        	if(data.lenderList != null && data.lenderList.length > 0) {
	        		var lenderInfo = data.lenderList[0];
	        		//根据出借人信息自动带出身份证号、通讯地址、联系电话、甲方放款账户（户名，账号，开户行）
	        		$('#creditor_name').val(lenderInfo.lender_name);
	        		$('#creditor_identity_id').val(lenderInfo.id_card);
	        		$('#creditor_address').val(lenderInfo.contact_address);
	        		$('#creditor_tel').val(lenderInfo.mobile_phone);
	        		$('#creditor_loan_name').val(lenderInfo.lender_name);
	        		$('#creditor_loan_number').val(lenderInfo.card_no);
	        		$('#creditor_loan_bank').val(lenderInfo.bank_of_deposit_name);
	        		
	        		$('input[name=creditor_name]').val(lender_name);
	        		$('input[name=creditor_identity_id]').val(lenderInfo.id_card);
	        		$('input[name=creditor_address]').val(lenderInfo.contact_address);
	        		$('input[name=creditor_tel]').val(lenderInfo.mobile_phone);
	        		$('input[name=creditor_loan_name]').val(lenderInfo.lender_name);
	        		$('input[name=creditor_loan_number]').val(lenderInfo.card_no);
	        		$('input[name=creditor_loan_bank]').val(lenderInfo.bank_of_deposit_name);
	        		
	        		$('#creditor_name11').val(lender_name);
	        		$('#creditor_identity_id11').val(lenderInfo.id_card);
	        		$('#creditor_address11').val(lenderInfo.contact_address);
	        		$('#creditor_tel11').val(lenderInfo.mobile_phone);
	        		$('#creditor_loan_name11').val(lenderInfo.lender_name);
	        		$('#creditor_loan_number11').val(lenderInfo.card_no);
	        		$('#creditor_loan_bank11').val(lenderInfo.bank_of_deposit_name);
	        		
	        		$('input[name=creditor_name11]').val(lender_name);
	        		$('input[name=creditor_identity_id11]').val(lenderInfo.id_card);
	        		$('input[name=creditor_address11]').val(lenderInfo.contact_address);
	        		$('input[name=creditor_tel11]').val(lenderInfo.mobile_phone);
	        		$('input[name=creditor_loan_name11]').val(lenderInfo.lender_name);
	        		$('input[name=creditor_loan_number11]').val(lenderInfo.card_no);
	        		$('input[name=creditor_loan_bank11]').val(lenderInfo.bank_of_deposit_name);
	        	}
	        }
	    });
	}
}
