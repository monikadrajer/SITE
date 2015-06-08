$(function() {
	'use strict';
	$('#progress').hide();
	$('#CCDASuperFileupload').fileupload({
		url : urlCCDASuper,
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
		$('#CCDASuperFormSubmit').unbind("click");
		$('#CCDASuperFiles').empty();
		data.context = $('<div/>').appendTo('#CCDASuperFiles');
		$.each(data.files, function(index, file) {
			var node = $('<p/>').append($('<span/>').text(file.name));
			node.appendTo(data.context);
		});
		data.context = $('#CCDASuperFormSubmit').click(function(e) {			
				// unsubscribe callbacks from previous uploads
				$('#CCDASuperValidationForm').parsley(ccdaParsleyOptions.getOptions()).unsubscribe('parsley:form:validate');
				// calling the Parsley Validator.
				$('#CCDASuperValidationForm').parsley(ccdaParsleyOptions.getOptions()).subscribe('parsley:form:validate',function(formInstance){
					formInstance.submitEvent.preventDefault();
					if(formInstance.isValid()==true){
						var hideMsg3 = $("#CCDASuperFileupload").parsley();
						window.ParsleyUI.removeError(hideMsg3,'required');
						$("#ValidationResult [href='#tabs-1']").trigger( "click" );
						BlockPortletUI();
						var selectedValue = $("#CCDASuper_type_val").val();
						data.formData = { };
						if (selectedValue != undefined) {
							data.formData.ccda_type_val = selectedValue;
						}
						data.submit();
						window.lastFilesUploaded = data.files;
					}
				});
			});	
		})
		.prop('disabled', !$.support.fileInput).parent().addClass(
			$.support.fileInput ? undefined : 'disabled');

	$('#CCDASuperFileupload').bind('fileuploaddrop', function(e, data) {
		e.preventDefault();
	}).bind('fileuploaddragover', function(e) {
		e.preventDefault();
	});
	
	$('#CCDASuperFormSubmit').click(function(e) {
			// unsubscribe callbacks from previous uploads
			$('#CCDASuperValidationForm').parsley(ccdaParsleyOptions.getOptions()).unsubscribe('parsley:form:validate');
			// calling the Parsley Validator.
			$('#CCDASuperValidationForm').parsley(ccdaParsleyOptions.getOptions()).subscribe('parsley:form:validate',function(formInstance){
				formInstance.submitEvent.preventDefault();
				if(formInstance.isValid()==true){
					var hideMsg3 = $("#CCDASuperFileupload").parsley();
					window.ParsleyUI.removeError(hideMsg3,'required');
				}
			});
	});	
	
	$('#CCDASuperFileupload-btn').bind('click', function(e, data){
		var selectedText = $("#CCDASuper_type_val :selected").text();
		$("#CCDASuper_type_val option").each(function() {
			  if($(this).text() == selectedText) {
			    $(this).attr('selected', 'selected');
			  } else {
				$(this).removeAttr('selected');
			  }
			});
		$('#CCDASuperValidationForm').trigger('reset');
		$('#CCDASuperFormSubmit').unbind("click");
		$('#CCDASuperFiles').empty();
	});
});