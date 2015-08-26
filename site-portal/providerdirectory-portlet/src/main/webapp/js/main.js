
$(function() {
	
	// Parsley validator to validate xml extension.
	window.ParsleyValidator.addValidator('wsdlUrl',function(value,requirement){
		var re = new RegExp(/^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i);	
		return re.test(value);
	},32).addMessage('en','wsdlUrl','The endpoint must be a properly formatted URL with protocol (e.g. http://sitenv.org/)');
	
	
	
});

$(function() {
	
	
	/*
	 * Parsley Options
	 */
	var parsleyOptions = {
	        trigger: 'change',
	        successClass: "has-success",
	        errorClass: "alert alert-danger",
	        classHandler: function (el) {
	        	return el.$element.closest(".form-group").children(".infoArea");
	        },
			errorsContainer: function (el) {
				return el.$element.closest(".form-group").children(".infoArea");
			},
			errorsWrapper: '<ul></ul>',
			errorElem: '<li></li>'
		};
	
	// unsubscribe callbacks from previous uploads
	$('#providerDirectoryTestForm').parsley(parsleyOptions).unsubscribe('parsley:form:validate');
	// Calling the Parsley Validator.
	$('#providerDirectoryTestForm').parsley(parsleyOptions).subscribe('parsley:form:validate',function(formInstance){     
		formInstance.submitEvent.preventDefault();
		if(formInstance.isValid()==true){
			var hideMsg1 = $("#endpointUrl").parsley();
			var hideMsg2 = $("#baseDn").parsley();
			window.ParsleyUI.removeError(hideMsg1,'required');
			window.ParsleyUI.removeError(hideMsg2,'required');
			
			
			blockProviderDirectoryWidget();
			  $.ajax({
		          type: "POST",
		          url: window.runTestsUrl,
		          data: $("#providerDirectoryTestForm").serialize(),
		          success: function(data) {
		        	  $("#PDResult").html(data);
		        	  
		        	  $("#resultsDialog").modal("show");
		        	  
		        	  Liferay.Portlet.refresh("#p_p_id_Statistics_WAR_siteportalstatisticsportlet_"); // refresh the counts
		        	  
		        	  unblockProviderDirectoryWidget();
		          },
		          error: function(jqXHR, textStatus, errorThrown) {
		        	  var iconurl = window.currentContextPath + "/images/icn_alert_error.png" ;
						
						$('#providerDirectoryWidget .blockMsg .progressorpanel img').attr('src',iconurl);
			        	
			        	$('#providerDirectoryWidget .blockMsg .progressorpanel .lbl').text('Error executing test cases.');
			        	
			        	
						
						if(window.directReceiveWdgt)
			        	{
			        		window.providerDirectoryTimeout = setTimeout(function(){
			        				window.providerDirectoryWidget.unbind("click");
			        				window.providerDirectoryWidget.unblock();
			        			},10000);
			        		
			        		
			        		window.providerDirectoryWidget.bind("click", function() { 
			        			window.providerDirectoryWidget.unbind("click");
			        			clearTimeout(window.providerDirectoryTimeout);
			        			window.providerDirectoryWidget.unblock(); 
			        			window.providerDirectoryWidget.attr('title','Click to hide this message.').click($.unblockUI); 
				            });
			        		
			        	}
		          }
		        });
		}});
});
	

function blockProviderDirectoryWidget()
{
	var ajaximgpath = window.currentContextPath + "/images/ajax-loader.gif";
	window.providerDirectoryWidget = $('#providerDirectoryWidget .well');
	window.providerDirectoryWidget.block({ 
		css: { 
	            border: 'none', 
	            padding: '15px', 
	            backgroundColor: '#000', 
	            '-webkit-border-radius': '10px', 
	            '-moz-border-radius': '10px', 
	            opacity: .5, 
	            color: '#fff' 
		},
		message: '<div class="progressorpanel">' +
				 '<img src="'+ ajaximgpath + '" alt="loading">'+
				 '<div class="lbl">Executing Test Cases...</div></div>',
	});
}

function unblockProviderDirectoryWidget()
{
	if(window.providerDirectoryWidget)
		window.providerDirectoryWidget.unblock();
}