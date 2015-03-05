$(function() {
	'use strict';

	// Change this to the location of your server-side upload handler:
	$('#progress').hide();
	$('#fileupload').fileupload({
		url : url,
		dataType : 'json',
		autoUpload : false,
		type : 'POST',
		contenttype : false,
		replaceFileInput : false,
		error: function (e, data) {
			var iconurl = window.currentContextPath + "/images/icn_alert_error.png" ;
			
			$('.blockMsg .progressorpanel img').attr('src',iconurl);
        	
        	$('.blockMsg .progressorpanel .lbl').text('Error sending XDR message.');
			
			if(window.validationpanel)
        	{
        		window.xdrUploadTimeout = setTimeout(function(){
        				window.xdrreceivewidget.unbind("click");
        				window.xdrreceivewidget.unblock();
        			},10000);
        		
        		
        		window.xdrreceivewidget.bind("click", function() { 
        			window.xdrreceivewidget.unbind("click");
        			clearTimeout(window.xdrUploadTimeout);
        			window.xdrreceivewidget.unblock(); 
        			window.xdrreceivewidget.attr('title','Click to hide this message.').click($.unblockUI); 
	            });
        		
        	}
        },
		done : function(e, data) {
			$.each(data.result.files, function(index, file) {
				$('#files').empty();
				$('#files').text(file.name);
			});
			
			var results = data.result.body;
			
			var iconurl = (results.IsSuccess == "true")? window.currentContextPath + "/images/icn_alert_success.png" :
				window.currentContextPath + "/images/icn_alert_error.png" ;
			
			$('#xdrreceivewidget .blockMsg .progressorpanel img').attr('src',iconurl);
      
        	
        	$('#xdrreceivewidget .blockMsg .progressorpanel .lbl').text(results.ErrorMessage);
			
			if(window.xdrreceivewidget)
        	{
        		window.xdrUploadTimeout = setTimeout(function(){
        				window.xdrreceivewidget.unbind("click");
        				window.xdrreceivewidget.unblock();
        			},10000);
        		
        		
        		window.xdrreceivewidget.bind("click", function() { 
        			window.xdrreceivewidget.unbind("click");
        			clearTimeout(window.xdrUploadTimeout);
        			window.xdrreceivewidget.unblock(); 
        			window.xdrreceivewidget.attr('title','Click to hide this message.').click($.unblockUI); 
	            });
        		
        	}
			
		    Liferay.Portlet.refresh("#p_p_id_Statistics_WAR_siteportalstatisticsportlet_"); // refresh the counts
		    
		    
			window.setTimeout(function() {
				$('#progress').fadeOut(400, function() {
					$('#progress .progress-bar').css('width', '0%');
					
				});

			}, 1000);
			
			clearTimeout(window.xdrReceiveUploadTimeout);
			window.xdrreceivewidget.unblock();
			var tabHtml1 = ['<title>XDR Receive Response</title>',
									    '<h1 align="center">XDR Receive Response</h1>',
									    '<hr/>',
									    '<hr/>',
									    '<br/>'].join('\n');
			
			tabHtml1 += '<div class="panel-group" id="receiveaccordion" role="tablist" aria-multiselectable="true">';
			  
			
				
				
			tabHtml1 += '<div class="panel panel-default" id="xdrreceivepanel">';
			tabHtml1 += ' <div class="panel-heading" role="tab" id="xdrreceiveheading">';
			tabHtml1 += '  <h4 class="panel-title">';
			tabHtml1 += 'XDR Response';
			tabHtml1 += '  </h4>';
			tabHtml1 += ' </div>';
			tabHtml1 += '	<div class="panel-body"></div>';
			tabHtml1 += '</div>';
			  
			
			tabHtml1 += "</div>";
			
			
			
			$("#receiveValidationResult .tab-content #receivetabs-1").html(tabHtml1);
			
			
			    	    	
			var codeWrapper = document.createElement('div');
			    	    	
			var codeEle = document.createElement("code");
			$('#receiveaccordion .panel-body').html(codeWrapper);
			    	    	
			$('#receiveaccordion .panel-body div').addClass("well");
			$('#receiveaccordion .panel-body div').css("word-wrap", "break-word");
			    	    	
			    	    	
			$('#receiveaccordion .panel-body .well').html(codeEle);
			    	    	
			$('#receiveaccordion .panel-body .well code').css("white-space", "pre");
			    	    	
			$('#receiveaccordion .panel-body .well code').text(results.xdrResponse);
			  
			    	 
			  
			   
			
			$("#xdrReceiveModal").modal("show");
			
			
			//disable smart ccda result tab.
			$("#receiveModalTabs a[href='#receivetabs-1']").tab("show");
			
		    Liferay.Portlet.refresh("#p_p_id_Statistics_WAR_siteportalstatisticsportlet_"); // refresh the counts
		    
		    //clean up the links
		    /*$("#ValidationResult #tabs #tabs-1 b:first, #ValidationResult #tabs #tabs-1 a:first").remove();*/
		    $("#receiveValidationResult .tab-content #receivetabs-1 hr:lt(4)").remove();
			
		},
		progressall : function(e, data) {
			var progressval = parseInt(data.loaded / data.total * 100, 10);
			//$('#progress').fadeIn();
			//$('#progress .progress-bar').css('width', progress + '%');
			
			if(progressval < 99)
		    {
		    	$('.blockMsg .progressorpanel .lbl').text('Uploading...');
		   		$('.blockMsg .progressorpanel .progressor').text( floorFigure(data.loaded/data.total*100,0).toString()+"%" );
		    }
		    else
		    {
		    	$('.blockMsg .progressorpanel .lbl').text('Sending...');
		    	$('.blockMsg .progressorpanel .progressor').text('');
		    }
		}
	}).on('fileuploadadd', function(e, data) {
		
		$('#formSubmit').unbind("click");
		$('#files').empty();
		data.context = $('<div/>').appendTo('#files');
		$.each(data.files, function(index, file) {

			var node = $('<span/>').text(file.name);

			node.appendTo(data.context);
		});

		
		
		data.context = $('#formSubmit').click(function(e) {
			
			var jform = $('#XDRValidationForm');
			jform.validationEngine({promptPosition:"centerRight", validateNonVisibleFields: true, updatePromptsPosition:true});
			//jform.validationEngine('hideAll');
			
			if(jform.validationEngine('validate'))
			{
				$('#XDRValidationForm .formError').hide(0);
				
				BlockPortletUI();
				
				var selectedValue = $("#messageType").val();
				
				data.formData = { };
				
				data.formData.wsdlLocation = $("#wsdlLocation").val();
				data.formData.toDirectAddress = $('#toDirectAddress').val();
				data.formData.fromDirectAddress = $('#fromDirectAddress').val();
				
				if (selectedValue != undefined) {
					data.formData.messageType = selectedValue;
				}
				
				data.submit();
				

				window.lastFilesUploaded = data.files;
			}
			else
			{
				$('#XDRValidationForm .formError').show(0);
				
				$('#XDRValidationForm .fileuploadformError').prependTo('#ccdauploaderrorlock');
			}
			
			
			
		});
		
		
		
	}).prop('disabled', !$.support.fileInput).parent().addClass(
			$.support.fileInput ? undefined : 'disabled');

	$('#fileupload').bind('fileuploaddrop', function(e, data) {
		e.preventDefault();
	}).bind('fileuploaddragover', function(e) {
		e.preventDefault();
	});
		
	
	$('#fileupload-btn').bind('click', function(e, data)
	{
		$('#XDRValidationForm .formError').hide(0);
		var urlEndpoint = $('#wsdlLocation').val();
		var directFrom = $('#fromDirectAddress').val();
		var directTo = $('#toDirectAddress').val();
		var selectedText = $("#messageType :selected").text();
		$("#messageType option").each(function() {
			  if($(this).text() == selectedText) {
			    $(this).attr('selected', 'selected');            
			  } else {
				$(this).removeAttr('selected');
			  }                    
			});
		
		$('#XDRValidationForm').trigger('reset');
		$('#wsdlLocation').val(urlEndpoint);
		$('#fromDirectAddress').val(directFrom);
		$('#toDirectAddress').val(directTo);
		$('#formSubmit').unbind("click");
		
		$('#files').empty();
		
		
	});
	

});