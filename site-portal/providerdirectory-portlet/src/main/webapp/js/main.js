$(function() {
	
	// Setting up parsley options to have listed errors and to append the errors to parent element.
	var parsleyOptions ={
			errorsWrapper: '<ul id="#providerDirectoryWidget"></ul>',
			errorElem: '<li></li>'	,
			  errors: {
			  		classHandler: function(el){
			 			return el.parent();
	 					}
				}
		};
	
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