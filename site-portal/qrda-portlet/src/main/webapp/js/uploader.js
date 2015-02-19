$(function() {
	'use strict';

			 
	// Change this to the location of your server-side upload handler:
	$('#qrdauploadprogress').hide();
	$('#qrdauploadfile').fileupload({
		url : $( '#QRDAValidationForm' ).attr( 'action' ),
		dataType : 'json',
		autoUpload : false,
		type : 'POST',
		contenttype : false,
		replaceFileInput : false,
		done : function(e, data) {
			
			var result = data.result;
        	qrdaAjaxValidationResultHandler(result.body);
        	
        	Liferay.Portlet.refresh("#p_p_id_Statistics_WAR_siteportalstatisticsportlet_"); // refresh the counts
        	
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
		    	$('.blockMsg .progressorpanel .lbl').text('Validating...');
		    	$('.blockMsg .progressorpanel .progressor').text('');
		    }
		}
	}).on('fileuploadadd', function(e, data) {
		$('#qrdavalidate_btn').unbind("click");
		$('#qrdauploadfiles').empty();
		data.context = $('<div/>').appendTo('#qrdauploadfiles');
		$.each(data.files, function(index, file) {

			var node = $('<p/>').append($('<span/>').text(file.name));

			node.appendTo(data.context);
		});
		
		

		data.context = $('#qrdavalidate_btn').click(function(e) {
			
			// Setting up parsley options to have listed errors and to append the errors to parent element.
			var parsleyOptions ={
					errorsWrapper: '<ul></ul>',
					errorElem: '<li></li>'	,
					  errors: {
					  		classHandler: function(el){
					 			return el.parent();
			 					}
						}
				};
			// Parsley validator to validate xml extension.
			
			window.ParsleyValidator.addValidator('filetype',function(value,requirement){
				var ext=value.split('.').pop().toLowerCase();
				return ext === requirement;	
			},32).addMessage('en','filetype','The selected QRDA file must be an xml file(.xml)');
			
			// parsley Validator to validate the file size
			
			window.ParsleyValidator.addValidator('maxsize',function(value,requirement){
				var file_size=$('#qrdauploadfile')[0].files[0];
				return file_size.size < requirement*1024*1024;
			},32).addMessage('en','maxsize','The uploaded file size exceeds the maximum file size of 3 MB.');
			
			
			$('#QRDAValidationForm').parsley(parsleyOptions).unsubscribe('parsley:form:validate');
			$('#QRDAValidationForm').parsley(parsleyOptions).subscribe('parsley:form:validate',function(formInstance){
			
				formInstance.submitEvent.preventDefault();
				if(formInstance.isValid()==true){
					var hideMsg3 = $("#qrdauploadfile").parsley();
					window.ParsleyUI.removeError(hideMsg3,'required');
					
					BlockPortletUI('#qrdaWidget .well');
					data.submit();
				}else {
				$('#QRDAValidationForm .qrdauploadfileformError').prependTo('#qrdauploaderrorlock');
			}
				});
		});
		
		
	}).prop('disabled', !$.support.fileInput).parent().addClass(
			$.support.fileInput ? undefined : 'disabled');


	$('#qrdauploadfile').bind('fileuploaddrop', function(e, data) {
		e.preventDefault();
	}).bind('fileuploaddragover', function(e) {
		e.preventDefault();
	});
	
	// Validating the form when refreshing the page.
	$('#qrdavalidate_btn').click(function(e) {
		var parsleyOptions ={
				errorsWrapper: '<ul></ul>',
				errorElem: '<li></li>'	,
				  errors: {
				  		classHandler: function(el){
				 			return el.parent();
		 					}
					}
			};
		window.ParsleyValidator.addValidator('filetype',function(value,requirement){
			var ext=value.split('.').pop().toLowerCase();
			return ext === requirement;	
		},32).addMessage('en','filetype','The selected QRDA file must be an xml file(.xml)');
		
		window.ParsleyValidator.addValidator('maxsize',function(value,requirement){
			var file_size=$('#qrdauploadfile')[0].files[0];
			return file_size.size < requirement*1024*1024;
		},32).addMessage('en','maxsize','The uploaded file size exceeds the maximum file size of 3 MB.');
		
		$('#QRDAValidationForm').parsley(parsleyOptions).subscribe('parsley:form:validate',function(formInstance){
			formInstance.submitEvent.preventDefault();
			
			if(formInstance.isValid()==true){
				var hideMsg3 = $("#qrdauploadfile").parsley();
				window.ParsleyUI.removeError(hideMsg3,'required');
				
				BlockPortletUI('#qrdaWidget .well');
				//data.submit();
			} else {
			$('#QRDAValidationForm .qrdauploadfileformError').prependTo('#qrdauploaderrorlock');
		}
			});
	});
	
	$('#qrdauploadfile-btn').bind('click', function(e, data)
			{
				var dropdownvalue = $('#category').val();
				$('#QRDAValidationForm').trigger('reset');
				$('#qrdavalidate_btn').unbind("click");
				
				$('#qrdauploadfiles').empty();
				
				$('#category').val(dropdownvalue);
				
			}); 
	
});