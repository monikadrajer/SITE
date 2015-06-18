// Reference C-CDA Validator
$(function() {
	var formSelector = "#CCDAReferenceValidationForm";
	var ajaximgpath = window.currentContextPath + "/css/ajax-loader.gif";
	var jform = $(formSelector);
	var parsleyForm = jform.parsley(ccdaParsleyOptions.getOptions());
	// In the case we have access to the FormData interface:
	if(window.FormData !== undefined){
		$('#CCDAReferenceFormSubmit').click(function(e) {
			e.preventDefault();
			//if ((validateTestDataInput.isValid() === true) && (validateGeneratedCCDAInput.isValid() === true)){
			if ((parsleyForm.validate() === true)) {
					var hideMsg = $("#CCDAReferenceFileupload").parsley();
					window.ParsleyUI.removeError(hideMsg,'required');
					
					var hideMsg1 = $("#CCDAReferenceCEHRTFileupload").parsley();
					window.ParsleyUI.removeError(hideMsg1,'required');
					$.blockUI({
						css: {
					        border: 'none', 
					        padding: '15px', 
					        backgroundColor: '#000', 
					        '-webkit-border-radius': '10px', 
					        '-moz-border-radius': '10px', 
					        opacity: .5, 
					        color: '#fff' 
				    	},
				    	message: '<div class="progressorpanel"><img src="'+ ajaximgpath + '" alt="loading">'+
						          '<div class="lbl">Validating...</div></div>'
					});
					
					var formData = jform.serializefiles();
					var serviceUrl = jform.attr("action");
					$.ajax({
				        url: urlCCDA1_1,
				        type: 'POST',
				        success: function(data){
				        	
				        	alert("This has been a call to the Reference validator");
				        	
				        	var results = JSON.parse(data);
				        	$.unblockUI();
				        },
				        error: errorHandler,
				        // Form data
				        data: formData,
				        //Options to tell JQuery not to process data or worry about content-type
				        cache: false,
				        contentType: false,
				        processData: false
				    });
				}
			});
	} else { // If no FormData, use a hidden Iframe to submit instead
		$('#CCDAReferenceFormSubmit').click(function(e) {
			e.preventDefault();
			//var formInstance = jform.parsley(parsleyOptions);
			if ((validateTestDataInput.isValid() === true) && (validateGeneratedCCDAInput.isValid() === true)){
					var hideMsg = $("#CCDAReferenceFileupload").parsley();
					window.ParsleyUI.removeError(hideMsg,'required');
					var hideMsg1 = $("#CCDAReferenceCEHRTFileupload").parsley();
					window.ParsleyUI.removeError(hideMsg1,'required');
					$.blockUI({
						css: {
					        border: 'none', 
					        padding: '15px', 
					        backgroundColor: '#000', 
					        '-webkit-border-radius': '10px', 
					        '-moz-border-radius': '10px', 
					        opacity: .5, 
					        color: '#fff' 
				    	},
				    	message: '<div class="progressorpanel"><img src="'+ ajaximgpath + '" alt="loading">'+
						          '<div class="lbl">Validating...</div></div>'
					});
				    //generate a random id
				    var  iframeId = 'unique' + (new Date().getTime());
				    //create an empty iframe
				    var iframe = $('<iframe src="javascript:false;" name="'+iframeId+'" id="'+iframeId+'" />');
				    //hide it
				    iframe.hide();
				    //set form target to iframe
				    jform.attr('target',iframeId);
				    //Add iframe to body
				    iframe.appendTo('body');
				    iframe.load(function(e) {
				        var doc = getDoc(iframe[0]); //get iframe Document
				        var node = doc.body ? doc.body : doc.documentElement;
				        var data = (node.innerText || node.textContent);
				        var results = JSON.parse(data);
				        $.unblockUI();
				        alert("This has been a call to the Reference validator from  an old browser");
				    });
				    jform.submit();
				}
			});
	}
	$('#CCDAReferenceFileupload').bind("change", function(){
		$('#CCDAReferenceFiles').empty();
		var filePath = $('#CCDAReferenceFileupload').val();
		fileName = filePath.replace(/^.*[\\\/]/, '');
		
		context = $('<div/>').appendTo('#CCDAReferenceFiles');
		var node = $('<p/>').append($('<span/>').text(fileName));
		node.appendTo(context);
	
	});
	$('#CCDAReferenceCEHRTFileupload').bind("change", function(){
		$('#CCDACEHRTReferenceFiles').empty();
		var filePath = $('#CCDAReferenceCEHRTFileupload').val();
		fileName = filePath.replace(/^.*[\\\/]/, '');
		context = $('<div/>').appendTo('#CCDACEHRTReferenceFiles');
		var node = $('<p/>').append($('<span/>').text(fileName));
		node.appendTo(context);
	});
});


