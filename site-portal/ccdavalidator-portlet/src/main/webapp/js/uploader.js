




function buildCcdaErrorList(data){
	var errorList = ['<hr/>',
	                 '<ul>'];
	
	var errors = data.result.body.ccdaResults.errors;
	
	var nErrors = errors.length;
	for (var i=0; i < nErrors; i++){
		
		var error = errors[i];
		var message = error.message;
		var path = error.path;
		var lineNum = error.lineNumber;
		var source = error.source;
		
		var errorDescription = ['<li> ERROR '+(i+1).toString()+'',
		                    '<ul>',
		                    	'<li>Message: '+ message + '</li>',
		                    '</ul>',
		                    '<ul>',
	                    		'<li>Path: '+ path + '</li>',
	                    	'</ul>',
	                    	'<ul>',
	                    		'<li>Line Number (approximate): '+ lineNum + '</li>',
	                    	'</ul>',
	                    	'<ul>',
                    			'<li>Source: (approximate): '+ source + '</li>',
                    		'</ul>',
                    		'</li>'
		                    ];
		
		errorList = errorList.concat(errorDescription);
	}
	errorList.push('</ul>');
	
	return (errorList.join('\n'));
}


function buildExtendedCcdaErrorList(data){
	
	var errorList = ['<hr/>',
	                 '<ul>'];
	
	var errors = data.result.body.ccdaExtendedResults.errorList;
	
	
	var nErrors = errors.length;
	for (var i=0; i < nErrors; i++){
		
		var error = errors[i];
		var message = error.message;
		var codeSystemName = error.codeSystemName;
		var xpathExpression = error.xpathExpression;
		var codeSystem = error.codeSystem;
		var code = error.code;
		var displayName = error.displayName;
		var nodeIndex = error.nodeIndex;
		
		var errorDescription = ['<li> ERROR '+(i+1).toString()+'',
		                    '<ul>',
		                    	'<li>Message: '+ message + '</li>',
		                    '</ul>',
		                    '<ul>',
	                    		'<li>Code System Name: '+ codeSystemName + '</li>',
	                    	'</ul>',
	                    	'<ul>',
	                    		'<li>XPath Expression: '+ xpathExpression + '</li>',
	                    	'</ul>',
	                    	'<ul>',
                    			'<li>Code System: '+ codeSystem + '</li>',
                    		'</ul>',
	                    	'<ul>',
                				'<li>Code: '+ code + '</li>',
                			'</ul>',
                			'<ul>',
                				'<li>Display Name: '+ displayName + '</li>',
                			'</ul>',
                			'<ul>',
            					'<li>Node Index: '+ nodeIndex + '</li>',
            				'</ul>',
                    		'</li>'
		                    ];
		
		errorList = errorList.concat(errorDescription);
	}
	errorList.push('</ul>');
	
	return (errorList.join('\n'));
}


function buildCcdaWarningList(data){
	
		var warningList = ['<hr/>',
		                   '<ul>'];
		
		var warnings = data.result.body.ccdaResults.warnings;
		
		var nWarnings = warnings.length;
		for (var i=0; i < nWarnings; i++){
			
			var warning = warnings[i];
			var message = warning.message;
			var path = warning.path;
			var lineNum = warning.lineNumber;
			var source = warning.source;
			
			var warningDescription = ['<li> WARNING '+(i+1).toString()+'',
			                    '<ul>',
			                    	'<li>Message: '+ message + '</li>',
			                    '</ul>',
			                    '<ul>',
		                    		'<li>Path: '+ path + '</li>',
		                    	'</ul>',
		                    	'<ul>',
		                    		'<li>Line Number (approximate): '+ lineNum + '</li>',
		                    	'</ul>',
		                    	'<ul>',
	                    			'<li>Source: (approximate): '+ source + '</li>',
	                    		'</ul>',
	                    		'</li>'
			                    ];
			
			warningList = warningList.concat(warningDescription);
		}
		warningList.push('</ul>');

		return (warningList.join('\n'));
}



function buildExtendedCcdaWarningList(data){
	
	var warningList = ['<hr/>',
	                 '<ul>'];
	
	var warnings = data.result.body.ccdaExtendedResults.warningList;
	
	var nWarnings = warnings.length;
	for (var i=0; i < nWarnings; i++){
		
		var warning = warnings[i];
		var message = warning.message;
		var codeSystemName = warning.codeSystemName;
		var xpathExpression = warning.xpathExpression;
		var codeSystem = warning.codeSystem;
		var code = warning.code;
		var displayName = warning.displayName;
		var nodeIndex = warning.nodeIndex;
		
		var warningDescription = ['<li> WARNING '+(i+1).toString()+'',
		                    '<ul>',
		                    	'<li>Message: '+ message + '</li>',
		                    '</ul>',
		                    '<ul>',
	                    		'<li>Code System Name: '+ codeSystemName + '</li>',
	                    	'</ul>',
	                    	'<ul>',
	                    		'<li>XPath Expression: '+ xpathExpression + '</li>',
	                    	'</ul>',
	                    	'<ul>',
                    			'<li>Code System: '+ codeSystem + '</li>',
                    		'</ul>',
	                    	'<ul>',
                				'<li>Code: '+ code + '</li>',
                			'</ul>',
                			'<ul>',
                				'<li>Display Name: '+ displayName + '</li>',
                			'</ul>',
                			'<ul>',
            					'<li>Node Index: '+ nodeIndex + '</li>',
            				'</ul>',
                    		'</li>'
		                    ];
		
		warningList = warningList.concat(warningDescription);
	}
	warningList.push('</ul>');
	
	return (warningList.join('\n'));
}



function buildCcdaInfoList(data){
	
		var infoList = ['<hr/>',
		                '<ul>'];
		
		var infos = data.result.body.ccdaResults.info;
		
		var nInfos = infos.length;
		for (var i=0; i < nInfos; i++){
			
			var info = infos[i];
			var message = info.message;
			var path = info.path;
			var lineNum = info.lineNumber;
			var source = info.source;
			
			var infoDescription = ['<li> INFO '+(i+1).toString()+'',
			                    '<ul>',
			                    	'<li>Message: '+ message + '</li>',
			                    '</ul>',
			                    '<ul>',
		                    		'<li>Path: '+ path + '</li>',
		                    	'</ul>',
		                    	'<ul>',
		                    		'<li>Line Number (approximate): '+ lineNum + '</li>',
		                    	'</ul>',
		                    	'<ul>',
	                    			'<li>Source: (approximate): '+ source + '</li>',
	                    		'</ul>',
	                    		'</li>'
			                    ];
			
			infoList = infoList.concat(infoDescription);
		}
		infoList.push('</ul>');

		return (infoList.join('\n'));
}



function buildExtendedCcdaInfoList(data){
	
	var infoList = ['<hr/>',
	                 '<ul>'];
	
	var infos = data.result.body.ccdaExtendedResults.informationList;
	
	var nInfos = infos.length;
	for (var i=0; i < nInfos; i++){
		
		var info = infos[i];
		var message = info.message;
		var codeSystemName = info.codeSystemName;
		var xpathExpression = info.xpathExpression;
		var codeSystem = info.codeSystem;
		var code = info.code;
		var displayName = info.displayName;
		var nodeIndex = info.nodeIndex;
		
		var infoDescription = ['<li> INFO '+(i+1).toString()+'',
		                    '<ul>',
		                    	'<li>Message: '+ message + '</li>',
		                    '</ul>',
		                    '<ul>',
	                    		'<li>Code System Name: '+ codeSystemName + '</li>',
	                    	'</ul>',
	                    	'<ul>',
	                    		'<li>XPath Expression: '+ xpathExpression + '</li>',
	                    	'</ul>',
	                    	'<ul>',
                    			'<li>Code System: '+ codeSystem + '</li>',
                    		'</ul>',
	                    	'<ul>',
                				'<li>Code: '+ code + '</li>',
                			'</ul>',
                			'<ul>',
                				'<li>Display Name: '+ displayName + '</li>',
                			'</ul>',
                			'<ul>',
            					'<li>Node Index: '+ nodeIndex + '</li>',
            				'</ul>',
                    		'</li>'
		                    ];
		
		infoList = infoList.concat(infoDescription);
	}
	infoList.push('</ul>');
	
	return (infoList.join('\n'));
}




//C-CDA 1.1
$(function() {
	'use strict';

	// Change this to the location of your server-side upload handler:
	$('#progress').hide();
	$('#CCDA1fileupload').fileupload({
		url : urlCCDA1_1,
		dataType : 'json',
		autoUpload : false,
		type : 'POST',
		contenttype : false,
		replaceFileInput : false,
		error: function(jqXHR, textStatus, errorThrown) {
			
			var iconurl = window.currentContextPath + "/css/icn_alert_error.png" ;
			
			$('.blockMsg .progressorpanel img').attr('src',iconurl);
        	
        	$('.blockMsg .progressorpanel .lbl').text('Error uploading file.');
			
			if(window.validationpanel)
        	{
        		window.validationPanelTimeout = setTimeout(function(){
        				window.validationpanel.unbind("click");
        				window.validationpanel.unblock();
        			},10000);
        		
        		
        		window.validationpanel.bind("click", function() { 
        			window.validationpanel.unbind("click");
        			clearTimeout(window.validationPanelTimeout);
        			window.validationpanel.unblock();
        			window.validationpanel.attr('title','Click to hide this message.').click($.unblockUI); 
	            });
        		
        	}
        },
		done : function(e, data) {
			
			$.each(data.result.files, function(index, file) {
				$('#CCDA1files').empty();
				$('#CCDA1files').text(file.name);
			});
			
			
			//$( "#ValidationResult .tab-content #tabs-1" ).html(data.result.body);
			//$( "#ValidationResult .tab-content #tabs-1" ).html(window.JSON.stringify(data.result.body));
			var tabHtml1 = "";
			
			if (("error" in data.result.body.ccdaResults) || ("error" in data.result.body.ccdaExtendedResults))
			{
				
				tabHtml1 = ['<title>Validation Results</title>',
									    '<h1 align="center">Consolidated-CDA r1.1 Validation and Meaningful Use Stage 2 Certification Results</h1>',
									    '<font color="red">',
									    '<b>An error occurred during validation. Please try again. If this error persists, please contact the system administrator:</b>',
									    '</font>',
									    '<hr/>',
									    '<hr/>',
									    '<br/>'].join('\n');
			} else {
				
				
				
				var ccdaReport = data.result.body.ccdaResults.report;
				var extendedCcdaReport = data.result.body.ccdaExtendedResults.report;
				
				var uploadedFileName = data.result.files[0].name;
				var docTypeSelected = ccdaReport.docTypeSelected;
				
				var ccdaErrorCount = data.result.body.ccdaResults.errors.length;
				var ccdaWarningCount = data.result.body.ccdaResults.warnings.length;
				var ccdaInfoCount = data.result.body.ccdaResults.info.length;
				
				var extendedErrorCount = data.result.body.ccdaExtendedResults.errorList.length;
				var extendedWarningCount = data.result.body.ccdaExtendedResults.warningList.length;
				var extendedInfoCount = data.result.body.ccdaExtendedResults.informationList.length;
				
				
				var CCDARedOrGreen = '<font color="green">';
				
				if (ccdaErrorCount > 0) {
					CCDARedOrGreen = '<font color="red">';
				}
				
				var vocabRedOrGreen = '<font color="green">';
				
				if (extendedErrorCount > 0){
					vocabRedOrGreen = '<font color="red">';
				}
				
				
				
				var tabHtml1 = 
					   ['<title>Validation Results</title>',
					    '<h1 align="center">Consolidated-CDA r1.1 Validation and Meaningful Use Stage 2 Certification Results</h1>',
					    '<b>Upload Results:</b>',
					    '<br/>'+uploadedFileName+' was uploaded successfully.',
					    '<br/><br/>',
					    '<b>MU2 C-CDA Document Type Selected: </b>',
					    '<br/>'+docTypeSelected+'',
					    '<hr/>',
					    '<hr/>',
					    '<br/>',
					    '<br/>'+CCDARedOrGreen+'',
					    '<b>Validation Results: </b>',
					    '<br/>The file has encountered '+ccdaErrorCount+' error(s). The file has encountered '+ccdaWarningCount+' warning(s). The file has encountered '+ccdaInfoCount+' info message(s).',
					    '</font>',
					    '<hr/>',
					    '<hr/>',
					    '<br/>',
					    '<br/>',
					    '<br/>'+vocabRedOrGreen+'',
					    '<b>Vocabulary Validation Results: </b>',
					    '<br/>The file has encountered '+extendedErrorCount+' error(s). The file has encountered '+extendedWarningCount+' warning(s). The file has encountered '+extendedInfoCount+' info message(s).',
					    '</font>',
					    '<hr/>',
					    '<hr/>',
					    '<br/>',
					    '<br/>'
					   ].join('\n');
				
				tabHtml1 += '<font color="red">';
				
				tabHtml1 += '<hr/><b>Validation Results:</b>';
				
				tabHtml1 += buildCcdaErrorList(data);
				
				tabHtml1 += '<hr/><b>Vocabulary Validation Results:</b>';
				
				tabHtml1 += buildExtendedCcdaErrorList(data);
				
				tabHtml1 += '</font>';
				
				if (ccdaReport.hasWarnings || extendedCcdaReport.hasWarnings){
					tabHtml1 += '<font color="blue">';
				}
				
				tabHtml1 += '<hr/><b>Validation Results:</b>';
				
				tabHtml1 += buildCcdaWarningList(data);
				
				tabHtml1 += '<hr/><b>Vocabulary Validation Results:</b>';
				
				tabHtml1 += buildExtendedCcdaWarningList(data);
				
				tabHtml1 += '</font>';
				
				
				if (ccdaReport.hasInfo || extendedCcdaReport.hasInfo){
					tabHtml1 += '<font color="gray">';
				}
				
				tabHtml1 += '<hr/><b>Validation Results:</b>';
				
				tabHtml1 += buildCcdaInfoList(data);
				
				tabHtml1 += '<hr/><b>Vocabulary Validation Results:</b>';
				
				tabHtml1 += buildExtendedCcdaInfoList(data);
				
				tabHtml1 += '</font>';
			}
			
			$("#ValidationResult .tab-content #tabs-1").html(tabHtml1);
			
			$("#resultModal").modal("show");
			
			
			//disable smart ccda result tab.
			$("#resultModalTabs a[href='#tabs-1']").tab("show");
		    $("#resultModalTabs a[href='#tabs-2']").hide();
		    $("#resultModalTabs a[href='#tabs-3']").hide();
			
		    Liferay.Portlet.refresh("#p_p_id_Statistics_WAR_siteportalstatisticsportlet_"); // refresh the counts
		    
		    //clean up the links
		    /*$("#ValidationResult #tabs #tabs-1 b:first, #ValidationResult #tabs #tabs-1 a:first").remove();*/
		    $("#ValidationResult .tab-content #tabs-1 hr:lt(4)").remove();
		    
			if(typeof window.validationpanel != 'undefined')
				window.validationpanel.unblock();

			window.setTimeout(function() {
				$('#progress').fadeOut(400, function() {
					$('#progress .progress-bar').css('width', '0%');
					
				});

			}, 1000);
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
		$('#CCDA1formSubmit').unbind("click");
		$('#CCDA1files').empty();
		data.context = $('<div/>').appendTo('#CCDA1files');
		$.each(data.files, function(index, file) {

			var node = $('<p/>').append($('<span/>').text(file.name));

			node.appendTo(data.context);
		});

		
		data.context = $('#CCDA1formSubmit').click(function(e) {
				
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
				},32).addMessage('en','filetype','The selected C-CDA file must be an xml file(.xml)');
				
				// parsley Validator to validate the file size
				
				window.ParsleyValidator.addValidator('maxsize',function(value,requirement){
					var file_size=$('#CCDA1fileupload')[0].files[0];
					return file_size.size < requirement*1024*1024;
				},32).addMessage('en','maxsize','The uploaded file size exceeds the maximum file size of 3 MB.');
				
				// unsubscribe callbacks from previous uploads
				$('#CCDA1ValidationForm').parsley(parsleyOptions).unsubscribe('parsley:form:validate');
				// calling the Parsley Validator.
				$('#CCDA1ValidationForm').parsley(parsleyOptions).subscribe('parsley:form:validate',function(formInstance){
					
					formInstance.submitEvent.preventDefault();
					if(formInstance.isValid()==true){
						var hideMsg3 = $("#CCDA1fileupload").parsley();
						window.ParsleyUI.removeError(hideMsg3,'required');
						
						
						$( "#ValidationResult [href='#tabs-1']").trigger( "click" );
						
						BlockPortletUI();
						
						var selectedValue = $("#CCDA1_type_val").val();
						
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

	$('#CCDA1fileupload').bind('fileuploaddrop', function(e, data) {
		e.preventDefault();
	}).bind('fileuploaddragover', function(e) {
		e.preventDefault();
	});
	
	
	$('#CCDA1formSubmit').click(function(e) {
			
			// Setting up parsley options to have listed errors and to append the errors to parent element.
			var parsleyOptions ={
					errorsWrapper: '<ul></ul>',
					errorElem: '<li></li>',
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
			},32).addMessage('en','filetype','The selected C-CDA file must be an xml file(.xml)');
			
			// parsley Validator to validate the file size
			window.ParsleyValidator.addValidator('maxsize',function(value,requirement){
				var file_size=$('#CCDA1fileupload')[0].files[0];
				return file_size.size < requirement*1024*1024;
			},32).addMessage('en','maxsize','The uploaded file size exceeds the maximum file size of 3 MB.');
			
			// unsubscribe callbacks from previous uploads
			$('#CCDA1ValidationForm').parsley(parsleyOptions).unsubscribe('parsley:form:validate');
			// calling the Parsley Validator.
			$('#CCDA1ValidationForm').parsley(parsleyOptions).subscribe('parsley:form:validate',function(formInstance){
				
				formInstance.submitEvent.preventDefault();
				if(formInstance.isValid()==true){
					var hideMsg3 = $("#CCDA1fileupload").parsley();
					window.ParsleyUI.removeError(hideMsg3,'required');
				}
			});
	});	
	
	$('#CCDA1fileupload-btn').bind('click', function(e, data)
	{
		//$('#CCDA1ValidationForm .formError').hide(0);
		
		var selectedText = $("#CCDA1_type_val :selected").text();
		$("#CCDA1_type_val option").each(function() {
			  if($(this).text() == selectedText) {
			    $(this).attr('selected', 'selected');
			  } else {
				$(this).removeAttr('selected');
			  }
			});
		
		$('#CCDA1ValidationForm').trigger('reset');
		$('#CCDA1formSubmit').unbind("click");
		
		$('#CCDA1files').empty();
		
		
	});
});




// CCDA 2.0
$(function() {
	'use strict';

	// Change this to the location of your server-side upload handler:
	$('#progress').hide();
	$('#CCDA2fileupload').fileupload({
		url : urlCCDA2_0,
		dataType : 'json',
		autoUpload : false,
		type : 'POST',
		contenttype : false,
		replaceFileInput : false,
		error: function(jqXHR, textStatus, errorThrown) {
			
			var iconurl = window.currentContextPath + "/css/icn_alert_error.png" ;
			
			$('.blockMsg .progressorpanel img').attr('src',iconurl);
        	
        	$('.blockMsg .progressorpanel .lbl').text('Error uploading file.');
			
			if(window.validationpanel)
        	{
        		window.validationPanelTimeout = setTimeout(function(){
        				window.validationpanel.unbind("click");
        				window.validationpanel.unblock();
        			},10000);
        		
        		
        		window.validationpanel.bind("click", function() { 
        			window.validationpanel.unbind("click");
        			clearTimeout(window.validationPanelTimeout);
        			window.validationpanel.unblock();
        			window.validationpanel.attr('title','Click to hide this message.').click($.unblockUI); 
	            });
        		
        	}
        },
		done : function(e, data) {
			
			
			$.each(data.result.files, function(index, file) {
				$('#CCDA2files').empty();
				$('#CCDA2files').text(file.name);
			});
			
			
			if (("error" in data.result.body.ccdaResults) || ("error" in data.result.body.ccdaExtendedResults))
			{
				
				tabHtml1 = ['<title>Validation Results</title>',
									    '<h1 align="center">Consolidated-CDA R2.0 Validation Results</h1>',
									    '<font color="red">',
									    '<b>An error occurred during validation. Please try again. If this error persists, please contact the system administrator:</b>',
									    '</font>',
									    '<hr/>',
									    '<hr/>',
									    '<br/>'].join('\n');
			} else {
				
				
				
				var ccdaReport = data.result.body.ccdaResults.report;
				var extendedCcdaReport = data.result.body.ccdaExtendedResults.report;
				
				//var ccdaValResult = ccdaReport.validationResults1;
				//var ccdaValStatement = ccdaReport.validationResults2;
				var uploadedFileName = data.result.files[0].name;
				
				
				var ccdaErrorCount = data.result.body.ccdaResults.errors.length;
				var ccdaWarningCount = data.result.body.ccdaResults.warnings.length;
				var ccdaInfoCount = data.result.body.ccdaResults.info.length;
				
				var extendedErrorCount = data.result.body.ccdaExtendedResults.errorList.length;
				var extendedWarningCount = data.result.body.ccdaExtendedResults.warningList.length;
				var extendedInfoCount = data.result.body.ccdaExtendedResults.informationList.length;
				
				
				var CCDARedOrGreen = '<font color="green">';
				
				if (ccdaErrorCount > 0) {
					CCDARedOrGreen = '<font color="red">';
				}
				
				var vocabRedOrGreen = '<font color="green">';
				
				if (extendedErrorCount > 0){
					vocabRedOrGreen = '<font color="red">';
				}
				
				
				var tabHtml1 = 
					   ['<title>Validation Results</title>',
					    '<h1 align="center">Consolidated-CDA R2.0 Validation Results</h1>',
					    '<b>Upload Results:</b>',
					    '<br/>'+uploadedFileName+' was uploaded successfully.',
					    '<br/><br/>',
					    '<hr/>',
					    '<br/>',
					    '<br/>'+CCDARedOrGreen+'',
					    '<b>Validation Results: </b>',
					    '<br/>The file has encountered '+ccdaErrorCount+' error(s). The file has encountered '+ccdaWarningCount+' warning(s). The file has encountered '+ccdaInfoCount+' info message(s).',
					    '</font>',
					    '<hr/>',
					    '<hr/>',
					    '<br/>',
					    '<br/>',
					    '<br/>'+vocabRedOrGreen+'',
					    '<b>Vocabulary Validation Results: </b>',
					    '<br/>The file has encountered '+extendedErrorCount+' error(s). The file has encountered '+extendedWarningCount+' warning(s). The file has encountered '+extendedInfoCount+' info message(s).',
					    '</font>',
					    '<hr/>',
					    '<hr/>',
					    '<br/>',
					    '<br/>'
					   ].join('\n');
				
				tabHtml1 += '<font color="red">';
				
				tabHtml1 += '<hr/><b>Validation Results:</b>';
				
				tabHtml1 += buildCcdaErrorList(data);
				
				tabHtml1 += '<hr/><b>Vocabulary Validation Results:</b>';
				
				tabHtml1 += buildExtendedCcdaErrorList(data);
				
				tabHtml1 += '</font>';
				
				if (ccdaReport.hasWarnings || extendedCcdaReport.hasWarnings){
					tabHtml1 += '<font color="blue">';
				}
				
				tabHtml1 += '<hr/><b>Validation Results:</b>';
				
				tabHtml1 += buildCcdaWarningList(data);
				
				tabHtml1 += '<hr/><b>Vocabulary Validation Results:</b>';
				
				tabHtml1 += buildExtendedCcdaWarningList(data);
				
				tabHtml1 += '</font>';
				
				
				if (ccdaReport.hasInfo || extendedCcdaReport.hasInfo){
					tabHtml1 += '<font color="gray">';
				}
				
				tabHtml1 += '<hr/><b>Validation Results:</b>';
				
				tabHtml1 += buildCcdaInfoList(data);
				
				tabHtml1 += '<hr/><b>Vocabulary Validation Results:</b>';
				
				tabHtml1 += buildExtendedCcdaInfoList(data);
				
				tabHtml1 += '</font>';
			}
			
			$("#ValidationResult .tab-content #tabs-1").html(tabHtml1);
			
			$("#resultModal").modal("show");
			
			
			//disable smart ccda result tab.
			$("#resultModalTabs a[href='#tabs-1']").tab("show");
		    $("#resultModalTabs a[href='#tabs-2']").hide();
		    $("#resultModalTabs a[href='#tabs-3']").hide();
			
		    Liferay.Portlet.refresh("#p_p_id_Statistics_WAR_siteportalstatisticsportlet_"); // refresh the counts
		    
		    //clean up the links
		    /*$("#ValidationResult #tabs #tabs-1 b:first, #ValidationResult #tabs #tabs-1 a:first").remove();*/
		    $("#ValidationResult .tab-content #tabs-1 hr:lt(4)").remove();
		    
			if(typeof window.validationpanel != 'undefined')
				window.validationpanel.unblock();

			window.setTimeout(function() {
				$('#progress').fadeOut(400, function() {
					$('#progress .progress-bar').css('width', '0%');
					
				});

			}, 1000);
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
		$('#CCDA2formSubmit').unbind("click");
		$('#CCDA2files').empty();
		data.context = $('<div/>').appendTo('#CCDA2files');
		$.each(data.files, function(index, file) {

			var node = $('<p/>').append($('<span/>').text(file.name));

			node.appendTo(data.context);
		});

		
		data.context = $('#CCDA2formSubmit').click(function(e) {
			
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
			},32).addMessage('en','filetype','The selected C-CDA file must be an xml file(.xml)');
			
			// parsley Validator to validate the file size
			window.ParsleyValidator.addValidator('maxsize',function(value,requirement){
				var file_size=$('#CCDA2fileupload')[0].files[0];
				return file_size.size < requirement*1024*1024;
			},32).addMessage('en','maxsize','The uploaded file size exceeds the maximum file size of 3 MB.');
			
			// unsubscribe callbacks from previous uploads
			$('#CCDA2ValidationForm').parsley(parsleyOptions).unsubscribe('parsley:form:validate');
			
			// calling the Parsley Validator
			$('#CCDA2ValidationForm').parsley(parsleyOptions).subscribe('parsley:form:validate',function(formInstance){
				
				formInstance.submitEvent.preventDefault();
				if(formInstance.isValid()==true){
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
		
		// Setting up parsley options to have listed errors and to append the errors to parent element.
		var parsleyOptions ={
				errorsWrapper: '<ul></ul>',
				errorElem: '<li></li>',
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
		},32).addMessage('en','filetype','The selected C-CDA file must be an xml file(.xml)');
		
		// parsley Validator to validate the file size
		window.ParsleyValidator.addValidator('maxsize',function(value,requirement){
			var file_size=$('#CCDA2fileupload')[0].files[0];
			return file_size.size < requirement*1024*1024;
		},32).addMessage('en','maxsize','The uploaded file size exceeds the maximum file size of 3 MB.');
		
		// unsubscribe callbacks from previous uploads
		$('#CCDA2ValidationForm').parsley(parsleyOptions).unsubscribe('parsley:form:validate');
		// calling the Parsley Validator.
		$('#CCDA2ValidationForm').parsley(parsleyOptions).subscribe('parsley:form:validate',function(formInstance){
			
			formInstance.submitEvent.preventDefault();
			if(formInstance.isValid()==true){
				var hideMsg3 = $("#CCDA2fileupload").parsley();
				window.ParsleyUI.removeError(hideMsg3,'required');
			}
		});
	});	
	
	
	
	
	
	$('#CCDA2fileupload-btn').bind('click', function(e, data)
	{
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



//Super C-CDA
$(function() {
	'use strict';

	// Change this to the location of your server-side upload handler:
	$('#progress').hide();
	$('#CCDASuperFileupload').fileupload({
		url : urlCCDASuper,
		dataType : 'json',
		autoUpload : false,
		type : 'POST',
		contenttype : false,
		replaceFileInput : false,
		error: function(jqXHR, textStatus, errorThrown) {
			
			var iconurl = window.currentContextPath + "/css/icn_alert_error.png" ;
			
			$('.blockMsg .progressorpanel img').attr('src',iconurl);
        	
        	$('.blockMsg .progressorpanel .lbl').text('Error uploading file.');
			
			if(window.validationpanel)
        	{
        		window.validationPanelTimeout = setTimeout(function(){
        				window.validationpanel.unbind("click");
        				window.validationpanel.unblock();
        			},10000);
        		
        		
        		window.validationpanel.bind("click", function() { 
        			window.validationpanel.unbind("click");
        			clearTimeout(window.validationPanelTimeout);
        			window.validationpanel.unblock();
        			window.validationpanel.attr('title','Click to hide this message.').click($.unblockUI); 
	            });
        		
        	}
        },
		done : function(e, data) {
			
			$.each(data.result.files, function(index, file) {
				$('#CCDASuperFiles').empty();
				$('#CCDASuperFiles').text(file.name);
			});
			
			
			var tabHtml1 = "";
			
			if (("error" in data.result.body.ccdaResults) || ("error" in data.result.body.ccdaExtendedResults))
			{
				
				tabHtml1 = ['<title>Validation Results</title>',
									    '<h1 align="center">Super Compliant Consolidated-CDA Validation and Meaningful Use Stage 2 Certification Results</h1>',
									    '<font color="red">',
									    '<b>An error occurred during validation. Please try again. If this error persists, please contact the system administrator:</b>',
									    '</font>',
									    '<hr/>',
									    '<hr/>',
									    '<br/>'].join('\n');
			} else {
				
				
				
				var ccdaReport = data.result.body.ccdaResults.report;
				var extendedCcdaReport = data.result.body.ccdaExtendedResults.report;
				
				var uploadedFileName = data.result.files[0].name;
				var docTypeSelected = ccdaReport.docTypeSelected;
				
				var ccdaErrorCount = data.result.body.ccdaResults.errors.length;
				var ccdaWarningCount = data.result.body.ccdaResults.warnings.length;
				var ccdaInfoCount = data.result.body.ccdaResults.info.length;
				
				var extendedErrorCount = data.result.body.ccdaExtendedResults.errorList.length;
				var extendedWarningCount = data.result.body.ccdaExtendedResults.warningList.length;
				var extendedInfoCount = data.result.body.ccdaExtendedResults.informationList.length;
				
				
				var CCDARedOrGreen = '<font color="green">';
				
				if (ccdaErrorCount > 0) {
					CCDARedOrGreen = '<font color="red">';
				}
				
				var vocabRedOrGreen = '<font color="green">';
				
				if (extendedErrorCount > 0){
					vocabRedOrGreen = '<font color="red">';
				}
				
				
				
				var tabHtml1 = 
					   ['<title>Validation Results</title>',
					    '<h1 align="center">Super Compliant Consolidated-CDA Validation and Meaningful Use Stage 2 Certification Results</h1>',
					    '<b>Upload Results:</b>',
					    '<br/>'+uploadedFileName+' was uploaded successfully.',
					    '<br/><br/>',
					    '<b>MU2 C-CDA Document Type Selected: </b>',
					    '<br/>'+docTypeSelected+'',
					    '<hr/>',
					    '<hr/>',
					    '<br/>',
					    '<br/>'+CCDARedOrGreen+'',
					    '<b>Validation Results: </b>',
					    '<br/>The file has encountered '+ccdaErrorCount+' error(s). The file has encountered '+ccdaWarningCount+' warning(s). The file has encountered '+ccdaInfoCount+' info message(s).',
					    '</font>',
					    '<hr/>',
					    '<hr/>',
					    '<br/>',
					    '<br/>',
					    '<br/>'+vocabRedOrGreen+'',
					    '<b>Vocabulary Validation Results: </b>',
					    '<br/>The file has encountered '+extendedErrorCount+' error(s). The file has encountered '+extendedWarningCount+' warning(s). The file has encountered '+extendedInfoCount+' info message(s).',
					    '</font>',
					    '<hr/>',
					    '<hr/>',
					    '<br/>',
					    '<br/>'
					   ].join('\n');
				
				tabHtml1 += '<font color="red">';
				
				tabHtml1 += '<hr/><b>Validation Results:</b>';
				
				tabHtml1 += buildCcdaErrorList(data);
				
				tabHtml1 += '<hr/><b>Vocabulary Validation Results:</b>';
				
				tabHtml1 += buildExtendedCcdaErrorList(data);
				
				tabHtml1 += '</font>';
				
				if (ccdaReport.hasWarnings || extendedCcdaReport.hasWarnings){
					tabHtml1 += '<font color="blue">';
				}
				
				tabHtml1 += '<hr/><b>Validation Results:</b>';
				
				tabHtml1 += buildCcdaWarningList(data);
				
				tabHtml1 += '<hr/><b>Vocabulary Validation Results:</b>';
				
				tabHtml1 += buildExtendedCcdaWarningList(data);
				
				tabHtml1 += '</font>';
				
				
				if (ccdaReport.hasInfo || extendedCcdaReport.hasInfo){
					tabHtml1 += '<font color="gray">';
				}
				
				tabHtml1 += '<hr/><b>Validation Results:</b>';
				
				tabHtml1 += buildCcdaInfoList(data);
				
				tabHtml1 += '<hr/><b>Vocabulary Validation Results:</b>';
				
				tabHtml1 += buildExtendedCcdaInfoList(data);
				
				tabHtml1 += '</font>';
			}
			
			$("#ValidationResult .tab-content #tabs-1").html(tabHtml1);
			
			$("#resultModal").modal("show");
			
			
			//disable smart ccda result tab.
			$("#resultModalTabs a[href='#tabs-1']").tab("show");
		    $("#resultModalTabs a[href='#tabs-2']").hide();
		    $("#resultModalTabs a[href='#tabs-3']").hide();
			
		    Liferay.Portlet.refresh("#p_p_id_Statistics_WAR_siteportalstatisticsportlet_"); // refresh the counts
		    
		    //clean up the links
		    /*$("#ValidationResult #tabs #tabs-1 b:first, #ValidationResult #tabs #tabs-1 a:first").remove();*/
		    $("#ValidationResult .tab-content #tabs-1 hr:lt(4)").remove();
		    
			if(typeof window.validationpanel != 'undefined')
				window.validationpanel.unblock();

			window.setTimeout(function() {
				$('#progress').fadeOut(400, function() {
					$('#progress .progress-bar').css('width', '0%');
					
				});

			}, 1000);
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
		$('#CCDASuperFormSubmit').unbind("click");
		$('#CCDASuperFiles').empty();
		data.context = $('<div/>').appendTo('#CCDASuperFiles');
		$.each(data.files, function(index, file) {

			var node = $('<p/>').append($('<span/>').text(file.name));

			node.appendTo(data.context);
		});

		
		data.context = $('#CCDASuperFormSubmit').click(function(e) {
				
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
				},32).addMessage('en','filetype','The selected C-CDA file must be an xml file(.xml)');
				
				// Parsley Validator to validate the file size
				
				window.ParsleyValidator.addValidator('maxsize',function(value,requirement){
					var file_size=$('#CCDASuperFileupload')[0].files[0];
					return file_size.size < requirement*1024*1024;
				},32).addMessage('en','maxsize','The uploaded file size exceeds the maximum file size of 3 MB.');
				
				// unsubscribe callbacks from previous uploads
				$('#CCDASuperValidationForm').parsley(parsleyOptions).unsubscribe('parsley:form:validate');
				// calling the Parsley Validator.
				$('#CCDASuperValidationForm').parsley(parsleyOptions).subscribe('parsley:form:validate',function(formInstance){
					
					formInstance.submitEvent.preventDefault();
					if(formInstance.isValid()==true){
						var hideMsg3 = $("#CCDASuperFileupload").parsley();
						window.ParsleyUI.removeError(hideMsg3,'required');
						
						
						$( "#ValidationResult [href='#tabs-1']").trigger( "click" );
						
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
			
			// Setting up parsley options to have listed errors and to append the errors to parent element.
			var parsleyOptions = {
					errorsWrapper: '<ul></ul>',
					errorElem: '<li></li>',
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
			},32).addMessage('en','filetype','The selected C-CDA file must be an xml file(.xml)');
			
			// parsley Validator to validate the file size
			window.ParsleyValidator.addValidator('maxsize',function(value,requirement){
				var file_size=$('#CCDASuperFileupload')[0].files[0];
				return file_size.size < requirement*1024*1024;
			},32).addMessage('en','maxsize','The uploaded file size exceeds the maximum file size of 3 MB.');
			
			// unsubscribe callbacks from previous uploads
			$('#CCDASuperValidationForm').parsley(parsleyOptions).unsubscribe('parsley:form:validate');
			// calling the Parsley Validator.
			$('#CCDASuperValidationForm').parsley(parsleyOptions).subscribe('parsley:form:validate',function(formInstance){
				
				formInstance.submitEvent.preventDefault();
				if(formInstance.isValid()==true){
					var hideMsg3 = $("#CCDASuperFileupload").parsley();
					window.ParsleyUI.removeError(hideMsg3,'required');
				}
			});
	});
	
	$('#CCDASuperFileupload-btn').bind('click', function(e, data)
	{
		
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





(function($) {
$.fn.serializefiles = function() {
    var obj = $(this);
    /* ADD FILE TO PARAM AJAX */
    var formData = new FormData();
    $.each($(obj).find("input[type='file']"), function(i, tag) {
        $.each($(tag)[0].files, function(i, file) {
            formData.append(tag.name, file);
        });
    });
    var params = $(obj).serializeArray();
    $.each(params, function (i, val) {
        formData.append(val.name, val.value);
    });
    return formData;
};
})(jQuery);


function errorHandler (request, status, error) {
    alert("error:"+ error);
    if(window.validationpanel)
    	window.validationpanel.unblock();
    $.unblockUI();
}



function getDoc(frame) {
    var doc = null;

    // IE8 cascading access check
    try {
        if (frame.contentWindow) {
            doc = frame.contentWindow.document;
        }
    } catch(err) {
    }

    if (doc) { // successful getting content
        return doc;
    }

    try { // simply checking may throw in ie8 under ssl or mismatched protocol
        doc = frame.contentDocument ? frame.contentDocument : frame.document;
    } catch(err) {
        // last attempt
        doc = frame.document;
    }
    return doc;
}




$(function() {
	
	var formSelector = "#CCDAReconciledValidationForm";
	var ajaximgpath = window.currentContextPath + "/css/ajax-loader.gif";
	var jform = $(formSelector);
	
	
	// Parsley validator to validate xml extension.
	window.ParsleyValidator.addValidator('filetype',function(value,requirement){
		var ext=value.split('.').pop().toLowerCase();
		return ext === requirement;	
	},32).addMessage('en','filetype','The selected C-CDA file must be an xml file(.xml)');
	
	// parsley Validator to validate CIRI test data file size
	window.ParsleyValidator.addValidator('testdatamaxsize',function(value,requirement){
		var file_size=$('#CCDAReconciledTestDataFileupload')[0].files[0];
		return file_size.size < requirement*1024*1024;
	},32).addMessage('en','testdatamaxsize','The uploaded file size exceeds the maximum file size of 3 MB.');
	
	// parsley Validator to validate the reconciled file size
	window.ParsleyValidator.addValidator('reconciledmaxsize',function(value,requirement){
		var file_size=$('#CCDAReconciledReconciliationFileupload')[0].files[0];
		return file_size.size < requirement*1024*1024;
	},32).addMessage('en','reconciledmaxsize','The uploaded file size exceeds the maximum file size of 3 MB.');	
	
	
	// Setting up parsley options to have listed errors and to append the errors to parent element.
	var parsleyOptions = {
			errorsWrapper: '<ul></ul>',
			errorElem: '<li></li>',
			  errors: {
			  		classHandler: function(el){
			 			return el.parent();
	 					}
				}
		};
	
	var formInstance = jform.parsley(parsleyOptions);
	
	// In the case we have access to the FormData interface:
	if(window.FormData !== undefined){
		
		$('#CCDAReconciledFormSubmit').click(function(e) {
			
			e.preventDefault();
			
			if(formInstance.validate() === true){
					
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
			if(formInstance.validate() === true){
					
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
				    
				    iframe.load(function(e)
				    {
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



/*
function CCDAMultiFileValidationReconciledSubmitIFrame()
{
	
	var formSelector = "#CCDAReconciledValidationForm";
	var ajaximgpath = window.currentContextPath + "/css/ajax-loader.gif";
	
	
	var jform = $(formSelector);
	jform.validationEngine({promptPosition:"centerRight", validateNonVisibleFields: true, updatePromptsPosition:true});
	//jform.validationEngine('hideAll');
	
	if(jform.validationEngine('validate'))
	{
		$('#CCDAReconciledValidationForm .formError').hide(0);
	
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
	    
	    iframe.load(function(e)
	    {
	        var doc = getDoc(iframe[0]); //get iframe Document
	        var node = doc.body ? doc.body : doc.documentElement;
	        //var node = docRoot.innerHTML;
	        var data = (node.innerText || node.textContent);
	        var results = JSON.parse(data);
	        
	        $.unblockUI();
	        alert("This has been a call to the Reconciled validator from an old browser");
	        
	    });
		
	    jform.submit();
	    
	} else {
		
		nErrors = $('#CCDAReconciledValidationForm .formError').size();
		
		for (i = 0; i < nErrors; i++){
			$('#CCDAReconciledValidationForm .formError').show(i);
		}
		
		
		if ($('#CCDAReconciledValidationForm .CCDAReconciledFileuploadformError').size() > 0){
			$('#CCDAReconciledValidationForm .CCDAReconciledFileuploadformError').prependTo('#CCDAReconciledUploaderrorlock');
		}
		if ($('#CCDAReconciledValidationForm .CCDAReconciledCEHRTFileuploadformError').size() > 0){
			$('#CCDAReconciledValidationForm .CCDAReconciledCEHRTFileuploadformError').prependTo('#CCDAReconciledCEHRTUploadErrorLock');
		}
		if ($('#CCDAReconciledValidationForm .CCDAReconciledReconciliationFileuploadformError').size() > 0){
			$('#CCDAReconciledValidationForm .CCDAReconciledReconciliationFileuploadformError').prependTo('#CCDAReconciledReconciliationUploadErrorLock');
		}
	}
}
*/


/*
function CCDAMultiFileValidationReferenceSubmitIFrame()
{
	
	var formSelector = "#CCDAReferenceValidationForm";
	var ajaximgpath = window.currentContextPath + "/css/ajax-loader.gif";
	
	
	var jform = $(formSelector);
	jform.validationEngine({promptPosition:"centerRight", validateNonVisibleFields: true, updatePromptsPosition:true});
	
	if(jform.validationEngine('validate'))
	{
		$('#CCDAReferenceValidationForm .formError').hide(0);
	
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
	    
	    iframe.load(function(e)
	    {
	        var doc = getDoc(iframe[0]); //get iframe Document
	        var node = doc.body ? doc.body : doc.documentElement;
	        //var node = docRoot.innerHTML;
	        var data = (node.innerText || node.textContent);
	        var results = JSON.parse(data);
	        
	        $.unblockUI();
	        alert("This has been a call to the Reference validator from an old browser");
	        
	    });
		
	    jform.submit();
	    
	} else {
		
		nErrors = $('#CCDAReferenceValidationForm .formError').size();
		
		for (i = 0; i < nErrors; i++){
			$('#CCDAReferenceValidationForm .formError').show(i);
		}
		
		
		if ($('#CCDAReferenceValidationForm .CCDAReferenceFileuploadformError').size() > 0){
			$('#CCDAReferenceValidationForm .CCDAReferenceFileuploadformError').prependTo('#CCDAReferenceUploaderrorlock');
		}
		if ($('#CCDAReferenceValidationForm .CCDAReferenceCEHRTFileuploadformError').size() > 0){
			$('#CCDAReferenceValidationForm .CCDAReferenceCEHRTFileuploadformError').prependTo('#CCDAReferenceCEHRTUploaderrorlock');
		}
		
	}
}

*/

/*
function CCDAMultiFileValidationReconciledSubmit()
{
	
	var formSelector = "#CCDAReconciledValidationForm";
	var ajaximgpath = window.currentContextPath + "/css/ajax-loader.gif";
	
	
	var jform = $(formSelector);
	jform.validationEngine({promptPosition:"centerRight", validateNonVisibleFields: true, updatePromptsPosition:true});
	//jform.validationEngine('hideAll');
	
	if(jform.validationEngine('validate'))
	{
		$('#CCDAReconciledValidationForm .formError').hide(0);
	
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
		
		var formData = $(formSelector).serializefiles();
		var serviceUrl = $(formSelector).attr("action");
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
		
	} else {
		
		nErrors = $('#CCDAReconciledValidationForm .formError').size();
		
		for (i = 0; i < nErrors; i++){
			$('#CCDAReconciledValidationForm .formError').show(i);
		}
		
		
		if ($('#CCDAReconciledValidationForm .CCDAReconciledFileuploadformError').size() > 0){
			$('#CCDAReconciledValidationForm .CCDAReconciledFileuploadformError').prependTo('#CCDAReconciledUploaderrorlock');
		}
		if ($('#CCDAReconciledValidationForm .CCDAReconciledCEHRTFileuploadformError').size() > 0){
			$('#CCDAReconciledValidationForm .CCDAReconciledCEHRTFileuploadformError').prependTo('#CCDAReconciledCEHRTUploadErrorLock');
		}
		if ($('#CCDAReconciledValidationForm .CCDAReconciledReconciliationFileuploadformError').size() > 0){
			$('#CCDAReconciledValidationForm .CCDAReconciledReconciliationFileuploadformError').prependTo('#CCDAReconciledReconciliationUploadErrorLock');
		}
		
	}
}
*/


/*
function CCDAMultiFileValidationReferenceSubmit()
{
	
	
	var formSelector = "#CCDAReferenceValidationForm";
	var ajaximgpath = window.currentContextPath + "/css/ajax-loader.gif";
	
	
	var jform = $(formSelector);
	jform.validationEngine({promptPosition:"centerRight", validateNonVisibleFields: true, updatePromptsPosition:true});
	
	if(jform.validationEngine('validate'))
	{
		$('#CCDAReferenceValidationForm .formError').hide(0);
		
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
		
		var formData = $(formSelector).serializefiles();
		var serviceUrl = $(formSelector).attr("action");
		$.ajax({
	        url: serviceUrl,
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
	else
	{
		
		nErrors = $('#CCDAReferenceValidationForm .formError').size();
		
		for (i = 0; i < nErrors; i++){
			$('#CCDAReferenceValidationForm .formError').show(i);
		}
		
		
		if ($('#CCDAReferenceValidationForm .CCDAReferenceFileuploadformError').size() > 0){
			$('#CCDAReferenceValidationForm .CCDAReferenceFileuploadformError').prependTo('#CCDAReferenceUploaderrorlock');
		}
		if ($('#CCDAReferenceValidationForm .CCDAReferenceCEHRTFileuploadformError').size() > 0){
			$('#CCDAReferenceValidationForm .CCDAReferenceCEHRTFileuploadformError').prependTo('#CCDAReferenceCEHRTUploaderrorlock');
		}
		
	}
}
*/

/*
$(function() {
	
	
	if(window.FormData !== undefined){
		
		$('#CCDAReconciledFormSubmit').bind('click', function(e, data) {
			CCDAMultiFileValidationReconciledSubmit();			
		});
		
	} else {
		
		$('#CCDAReconciledFormSubmit').bind('click', function(e, data) {
			CCDAMultiFileValidationReconciledSubmitIFrame();
		});
		
	}
	
	
	$('#CCDAReconciledFileupload').bind("change", function(){
		
		$('#CCDAReconciledFiles').empty();
		var filePath = $('#CCDAReconciledFileupload').val();
		fileName = filePath.replace(/^.*[\\\/]/, '');
		
		context = $('<div/>').appendTo('#CCDAReconciledFiles');
		var node = $('<p/>').append($('<span/>').text(fileName));
		node.appendTo(context);
	
	});
	
	
	
	
	$('#CCDAReconciledCEHRTFileupload').bind("change", function(){
		
		$('#CCDACEHRTReconciledFiles').empty();
		var filePath = $('#CCDAReconciledCEHRTFileupload').val();
		fileName = filePath.replace(/^.*[\\\/]/, '');
		
		context = $('<div/>').appendTo('#CCDACEHRTReconciledFiles');
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



$(function() {
	
	
	if(window.FormData !== undefined){
		
		$('#CCDAReferenceFormSubmit').bind('click', function(e, data) {
				CCDAMultiFileValidationReferenceSubmit();
		});
		
	} else {
		
		$('#CCDAReferenceFormSubmit').bind('click', function(e, data) {
			CCDAMultiFileValidationReferenceSubmitIFrame();
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


*/


