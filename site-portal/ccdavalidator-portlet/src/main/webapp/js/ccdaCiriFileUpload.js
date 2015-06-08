$(function() {
	var formSelector = "#CCDAReconciledValidationForm";
	var ajaximgpath = window.currentContextPath + "/css/ajax-loader.gif";
	var jform = $(formSelector);
	var parsleyForm = jform.parsley(ccdaParsleyOptions.getOptions());
	// In the case we have access to the FormData interface:
	if(window.FormData !== undefined){
		$('#CCDAReconciledFormSubmit').click(function(e) {
			e.preventDefault();
			if ((parsleyForm.validate() === true)) {
					var hideMsg = $("#CCDAReconciledTestDataFileupload").parsley();
					window.ParsleyUI.removeError(hideMsg,'required');
					var hideMsg1 = $("#CCDAReconciledReconciliationFileupload").parsley();
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
				        url: serviceUrl,
				        type: 'POST',
				        success: function(data){
				        	alert("This has been a call to the Reconciled validator");
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
		$('#CCDAReconciledFormSubmit').click(function(e) {
			e.preventDefault();
			//var formInstance = jform.parsley(parsleyOptions);
			if ((validateTestDataInput.isValid() === true) && (validateReconciledCCDAInput.isValid() === true)){
					var hideMsg = $("#CCDAReconciledTestDataFileupload").parsley();
					window.ParsleyUI.removeError(hideMsg,'required');
					var hideMsg1 = $("#CCDAReconciledReconciliationFileupload").parsley();
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
				    iframe.load(function(e){
				        var doc = getDoc(iframe[0]); //get iframe Document
				        var node = doc.body ? doc.body : doc.documentElement;
				        var data = (node.innerText || node.textContent);
				        var results = JSON.parse(data); 
				        $.unblockUI();
				        alert("This has been a call to the Reconciled validator from  an old browser");
				    });
				    jform.submit(); 
				}
			});
	}
	$('#CCDAReconciledTestDataFileupload').bind("change", function(){
		$('#CCDAReconciledTestDataFiles').empty();
		var filePath = $('#CCDAReconciledTestDataFileupload').val();
		fileName = filePath.replace(/^.*[\\\/]/, '');
		context = $('<div/>').appendTo('#CCDAReconciledTestDataFiles');
		var node = $('<p/>').append($('<span/>').text(fileName));
		node.appendTo(context);
	});
	$('#CCDAReconciledReconciliationFileupload').bind("change", function(){
		
		$('#CCDAReconciliationReconciledFiles').empty();
		var filePath = $('#CCDAReconciledReconciliationFileupload').val();
		fileName = filePath.replace(/^.*[\\\/]/, '');
		
		context = $('<div/>').appendTo('#CCDAReconciliationReconciledFiles');
		var node = $('<p/>').append($('<span/>').text(fileName));
		node.appendTo(context);
	});
});