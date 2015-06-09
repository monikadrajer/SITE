$(function() {
	'use strict';
	$('#progress').hide();
	$('#CCDA2fileupload').fileupload({
		url : urlCCDA2_0,
		dataType : 'json',
		autoUpload : false,
		type : 'POST',
		contenttype : false,
		replaceFileInput : false,
		error: function(jqXHR, textStatus, errorThrown) {
			handleFileUploadError();
        },
		done : function(e, data) {
			doCcdaValidation(data);
		},
		progressall : function(e, data) {
			showFileValidationProgress(data);
		}
	}).on('fileuploadadd', function(e, data) {
		$('#CCDA2formSubmit').unbind("click");
		$('#CCDA2files').empty();
		data.context = $('<div/>').appendTo('#CCDA2files');
		$.each(data.files, function(index, file) {
			var node = $('<p/>').append($('<span/>').text(file.name));
			node.appendTo(data.context);
		});
		data.context = $('#CCDA2formSubmit').click(function(e) {
			// unsubscribe callbacks from previous uploads
			$('#CCDA2ValidationForm').parsley(ccdaParsleyOptions.getOptions()).unsubscribe('parsley:form:validate');
			// calling the Parsley Validator
			$('#CCDA2ValidationForm').parsley(ccdaParsleyOptions.getOptions()).subscribe('parsley:form:validate',function(formInstance){
				formInstance.submitEvent.preventDefault();
				if(formInstance.isValid()===true){
					var hideMsg3 = $("#CCDA2fileupload").parsley();
					window.ParsleyUI.removeError(hideMsg3,'required');
					$( "#ValidationResult [href='#tabs-1']").trigger( "click" );
					BlockPortletUI();
					data.formData = { };
					data.submit();
					window.lastFilesUploaded = data.files;
				}
			});
		});	
	})
	.prop('disabled', !$.support.fileInput).parent().addClass(
			$.support.fileInput ? undefined : 'disabled');

	$('#CCDA2fileupload').bind('fileuploaddrop', function(e, data) {
		e.preventDefault();
	}).bind('fileuploaddragover', function(e) {
		e.preventDefault();
	});

	$('#CCDA2formSubmit').click(function(e) {
		// unsubscribe callbacks from previous uploads
		$('#CCDA2ValidationForm').parsley(ccdaParsleyOptions.getOptions()).unsubscribe('parsley:form:validate');
		// calling the Parsley Validator.
		$('#CCDA2ValidationForm').parsley(ccdaParsleyOptions.getOptions()).subscribe('parsley:form:validate',function(formInstance){
			formInstance.submitEvent.preventDefault();
			if(formInstance.isValid()==true){
				var hideMsg3 = $("#CCDA2fileupload").parsley();
				window.ParsleyUI.removeError(hideMsg3,'required');
			}
		});
	});	
	
	$('#CCDA2fileupload-btn').bind('click', function(e, data){
		$('#CCDA2ValidationForm .formError').hide(0);
		var selectedText = $("#CCDA2_type_val :selected").text();
		$("#CCDA2_type_val option").each(function() {
			  if($(this).text() == selectedText) {
			    $(this).attr('selected', 'selected');            
			  } else {
				$(this).removeAttr('selected');
			  }                    
			});
		$('#CCDA2ValidationForm').trigger('reset');
		$('#CCDA2formSubmit').unbind("click");
		$('#CCDA2files').empty();
	});
	
});