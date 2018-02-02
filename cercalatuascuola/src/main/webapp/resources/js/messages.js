function getErrorMessage(xhr) {
	var errorMessage = 'errore generico';
	if (xhr) {
		if (xhr.responseJSON && xhr.responseJSON.message) {
			errorMessage =  xhr.responseJSON.message;
		} else {
			errorMessage = xhr.status+': '+xhr.statusText;
		}
	}
	return errorMessage;
}

function showErrorMessage(message) {
	noty({
		layout : 'bottomRight',
		type : 'error',
		timeout : 3000,
		text : message
	});
}

function showErrorBox(xhr){
	if(xhr.responseText){
		var content = $(xhr.responseText).find('#content');
		if(content.length==0){
			content = getErrorMessage(xhr);
		}
		$('#errorContainer').empty().append(content).dialog({
			width:'70%',
			height: 'auto'
		});
	}else{
		showErrorMessage(getErrorMessage(xhr));
	}
}

function showInfoMessage(message) {
	noty({
		layout : 'bottomRight',
		type : 'info',
		timeout : 1000,
		text : message
	});
}

function showSuccessMessage(message) {
	return noty({
		layout : 'bottomRight',
		type : 'success',
		timeout : 3000,
		text : message
	});
}

function showWarnMessage(message) {
	return noty({
		layout : 'bottomRight',
		type : 'warning',
		timeout : 3000,
		text : message
	});
}

function showAlertMessage(message) {
	return noty({
		layout : 'topCenter',
		type : 'alert',
		text : message,
		animation : {
			open : {
				opacity : 'toggle'
			},
			close : {
				opacity : 'toggle'
			}
		}
	});
}

function showConfirmMessage(message,callback) {
	var popup = $('<div id="confirmPopup" title="Richiesta di conferma" class="hide">'+message+'</div>');
	$('document').append(popup);
	popup.dialog({
		autoOpen : true,
		modal : true,
		close: function(event, ui){ 
            $(this).dialog('destroy');
        },
		buttons : {
			Conferma : function(){
				callback();
				$(this).dialog('destroy').remove();
			},
			Annulla : function() {
				$(this).dialog('destroy').remove();
			}
		}
	});
}

function showBlockedMessage(message) {
	return noty({
		layout : 'bottomRight',
		type : 'warning',
		text : message,
		closeWith: []
	});
}

function alert(message){
	return noty({
		layout : 'center',
		type : 'warning',
		text : message,
		closeWith:['button']
	});
}

function alertError(message){
	return noty({
		layout : 'center',
		type : 'error',
		text : message,
		closeWith:['button']
	});
}

function closeMessageById(messageId) {
	$.noty.close(messageId);
}

function closeMessage(message) {
	if(message){
		message.close();
	}
}