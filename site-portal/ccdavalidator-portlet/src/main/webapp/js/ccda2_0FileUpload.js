$(function() {
	'use strict';
	$('#progress').hide();
	$('#CCDAR2_0ValidationForm').fileupload({
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
		$('#CCDAR2_0FormSubmit').unbind("click");
		$('#CCDAR2_0Files').empty();
		data.context = $('<div/>').appendTo('#CCDAR2_0Files');
		$.each(data.files, function(index, file) {
			var node = $('<p/>').append($('<span/>').text(file.name));
			node.appendTo(data.context);
		});
		data.context = $('#CCDAR2_0FormSubmit').click(function(e) {
				// unsubscribe callbacks from previous uploads
				$('#CCDAR2_0ValidationForm').parsley(ccdaParsleyOptions.getOptions()).unsubscribe('parsley:form:validate');
				// calling the Parsley Validator.
				$('#CCDAR2_0ValidationForm').parsley(ccdaParsleyOptions.getOptions()).subscribe('parsley:form:validate',function(formInstance){
					formInstance.submitEvent.preventDefault();
					if(formInstance.isValid()==true){
						var hideMsg3 = $("#CCDAR2_0Fileupload").parsley();
						window.ParsleyUI.removeError(hideMsg3,'required');
						$( "#ValidationResult [href='#tabs-1']").trigger( "click" );
						BlockPortletUI();
						var selectedValue = $("#CCDAR2_0_type_val").val();
						data.formData = { };
						if (selectedValue != undefined) {
							data.formData.CCDAR2_0_type_val = selectedValue;
						}
						data.submit();
						window.lastFilesUploaded = data.files;
					}
				});
			});
		})
		.prop('disabled', !$.support.fileInput).parent().addClass(
			$.support.fileInput ? undefined : 'disabled');

	$('#CCDAR2_0Fileupload').bind('fileuploaddrop', function(e, data) {
		e.preventDefault();
	}).bind('fileuploaddragover', function(e) {
		e.preventDefault();
	});
	
	$('#CCDAR2_0FormSubmit').click(function(e) {
			// unsubscribe callbacks from previous uploads
			$('#CCDAR2_0ValidationForm').parsley(ccdaParsleyOptions.getOptions()).unsubscribe('parsley:form:validate');
			// calling the Parsley Validator.
			$('#CCDAR2_0ValidationForm').parsley(ccdaParsleyOptions.getOptions()).subscribe('parsley:form:validate',function(formInstance){
				formInstance.submitEvent.preventDefault();
				if(formInstance.isValid()==true){
					var hideMsg3 = $("#CCDAR2_0Fileupload").parsley();
					window.ParsleyUI.removeError(hideMsg3,'required');
				}
			});
	});	
	
	$('#CCDA1fileupload-btn').bind('click', function(e, data){
		//$('#CCDA1ValidationForm .formError').hide(0);
		var selectedText = $("#CCDAR2_0_type_val :selected").text();
		$("#CCDAR2_0_type_val option").each(function() {
			  if($(this).text() == selectedText) {
			    $(this).attr('selected', 'selected');
			  } else {
				$(this).removeAttr('selected');
			  }
			});
		$('#CCDAR2_0ValidationForm').trigger('reset');
		$('#CCDAR2_0FormSubmit').unbind("click");
		$('#CCDAR2_0Files').empty();
	});
});
































//
//// Reference C-CDA Validator
//$(function() {
//	var formSelector = "#CCDAReferenceValidationForm";
//	var ajaximgpath = window.currentContextPath + "/css/ajax-loader.gif";
//	var jform = $(formSelector);
//	var parsleyForm = jform.parsley(ccdaParsleyOptions.getOptions());
//	// In the case we have access to the FormData interface:
//	if(window.FormData !== undefined){
//		$('#CCDAReferenceFormSubmit').click(function(e) {
//			e.preventDefault();
//			//if ((validateTestDataInput.isValid() === true) && (validateGeneratedCCDAInput.isValid() === true)){
//			if ((parsleyForm.validate() === true)) {
//					var hideMsg = $("#CCDAReferenceFileupload").parsley();
//					window.ParsleyUI.removeError(hideMsg,'required');
//					
//					var hideMsg1 = $("#CCDAReferenceCEHRTFileupload").parsley();
//					window.ParsleyUI.removeError(hideMsg1,'required');
//					$.blockUI({
//						css: {
//					        border: 'none', 
//					        padding: '15px', 
//					        backgroundColor: '#000', 
//					        '-webkit-border-radius': '10px', 
//					        '-moz-border-radius': '10px', 
//					        opacity: .5, 
//					        color: '#fff' 
//				    	},
//				    	message: '<div class="progressorpanel"><img src="'+ ajaximgpath + '" alt="loading">'+
//						          '<div class="lbl">Validating...</div></div>'
//					});
//					
//					var formData = jform.serializefiles();
//					var serviceUrl = jform.attr("action");
//					$.ajax({
//				        url: urlCCDA1_1,
//				        type: 'POST',
//				        success: function(data){
//				        	
//				        	alert("This has been a call to the Reference validator");
//				        	
//				        	var results = JSON.parse(data);
//				        	$.unblockUI();
//				        },
//				        error: errorHandler,
//				        // Form data
//				        data: formData,
//				        //Options to tell JQuery not to process data or worry about content-type
//				        cache: false,
//				        contentType: false,
//				        processData: false
//				    });
//				}
//			});
//	} else { // If no FormData, use a hidden Iframe to submit instead
//		$('#CCDAReferenceFormSubmit').click(function(e) {
//			e.preventDefault();
//			//var formInstance = jform.parsley(parsleyOptions);
//			if ((validateTestDataInput.isValid() === true) && (validateGeneratedCCDAInput.isValid() === true)){
//					var hideMsg = $("#CCDAReferenceFileupload").parsley();
//					window.ParsleyUI.removeError(hideMsg,'required');
//					var hideMsg1 = $("#CCDAReferenceCEHRTFileupload").parsley();
//					window.ParsleyUI.removeError(hideMsg1,'required');
//					$.blockUI({
//						css: {
//					        border: 'none', 
//					        padding: '15px', 
//					        backgroundColor: '#000', 
//					        '-webkit-border-radius': '10px', 
//					        '-moz-border-radius': '10px', 
//					        opacity: .5, 
//					        color: '#fff' 
//				    	},
//				    	message: '<div class="progressorpanel"><img src="'+ ajaximgpath + '" alt="loading">'+
//						          '<div class="lbl">Validating...</div></div>'
//					});
//				    //generate a random id
//				    var  iframeId = 'unique' + (new Date().getTime());
//				    //create an empty iframe
//				    var iframe = $('<iframe src="javascript:false;" name="'+iframeId+'" id="'+iframeId+'" />');
//				    //hide it
//				    iframe.hide();
//				    //set form target to iframe
//				    jform.attr('target',iframeId);
//				    //Add iframe to body
//				    iframe.appendTo('body');
//				    iframe.load(function(e) {
//				        var doc = getDoc(iframe[0]); //get iframe Document
//				        var node = doc.body ? doc.body : doc.documentElement;
//				        var data = (node.innerText || node.textContent);
//				        var results = JSON.parse(data);
//				        $.unblockUI();
//				        alert("This has been a call to the Reference validator from  an old browser");
//				    });
//				    jform.submit();
//				}
//			});
//	}
//	$('#CCDAReferenceFileupload').bind("change", function(){
//		$('#CCDAReferenceFiles').empty();
//		var filePath = $('#CCDAReferenceFileupload').val();
//		fileName = filePath.replace(/^.*[\\\/]/, '');
//		
//		context = $('<div/>').appendTo('#CCDAReferenceFiles');
//		var node = $('<p/>').append($('<span/>').text(fileName));
//		node.appendTo(context);
//	
//	});
//	$('#CCDAReferenceCEHRTFileupload').bind("change", function(){
//		$('#CCDACEHRTReferenceFiles').empty();
//		var filePath = $('#CCDAReferenceCEHRTFileupload').val();
//		fileName = filePath.replace(/^.*[\\\/]/, '');
//		context = $('<div/>').appendTo('#CCDACEHRTReferenceFiles');
//		var node = $('<p/>').append($('<span/>').text(fileName));
//		node.appendTo(context);
//	});
//});
//
//
