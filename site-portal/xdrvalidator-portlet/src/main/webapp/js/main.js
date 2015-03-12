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

function progressorHandlingFunction(e){
    if(e.lengthComputable){
    	//var progressorval = floorFigure(e.loaded/e.total*100,0);
    	/*
    	if(progressorval < 99)
    	{
    		$('.blockMsg .progressorpanel .lbl').text('Uploading...');
    		$('.blockMsg .progressorpanel .progressor').text( floorFigure(e.loaded/e.total*100,0).toString()+"%" );
    	}
    	else
    	{
    		$('.blockMsg .progressorpanel .lbl').text('Validating...');
    		$('.blockMsg .progressorpanel .progressor').text('');
    	}
    	*/
    }
}

function precannedRequired(field, rules, i, options){
	if($('#precannedfilepath').val()== '')
	{
		return "Please select a precanned C-CDA sample";
	}
}

function BlockPortletUI()
{
	var ajaximgpath = window.currentContextPath + "/images/ajax-loader.gif";
	window.xdrreceivewidget = $('#xdrreceivewidget .well');
	window.xdrreceivewidget.block({ 
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
				 '<div class="lbl">Sending...</div></div>',
	});
}

function BlockSendPortletUI()
{
	var ajaximgpath = window.currentContextPath + "/images/ajax-loader.gif";
	window.xdrsendwidget = $('#xdrsendwidget .well');
	window.xdrsendwidget.block({ 
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
				 '<div class="lbl">Searching...</div></div>',
	});
}


$(function() {
	
	
	$('#precannedOptionalOne').on('hidden.bs.collapse', function () {
		  $('#uploadOptionalHeadingOne a').addClass('collapsed');
		  $('#uploadOptionalOne').css("height", "0px");
		  $('#uploadOptionalOne').removeClass('in');
		  $('#uploadOptionalOne').addClass('collapse');
		  
		  //window.xdrReceiveDirectToAddress = $('#precannedToDirectAddress').val();
		  $('#precannedToDirectAddress').val('');
		  $('#toDirectAddress').val('');
		  //window.xdrReceiveDirectFromAddress = $('#precannedFromDirectAddress').val();
		  $('#precannedFromDirectAddress').val('');
		  $('#fromDirectAddress').val('');
		  

		  $('#messageType').val('minimal');
		  $('#precannedMessageType').val('minimal');
		  
		  $('#XDRPrecannedForm .formError').hide(0);
		  $('#XDRUploadForm .formError').hide(0);
		});
	
	$('#precannedOptionalOne').on('shown.bs.collapse', function () {
			$('#uploadOptionalHeadingOne a').removeClass('collapsed');
			$('#uploadOptionalOne').css("height", "auto");
			$('#uploadOptionalOne').addClass('in');
			$('#uploadOptionalOne').removeClass('collapse');

		});
	
	$('#precannedOptionalOne').on('show.bs.collapse', function () {
		$('#precannedToDirectAddress').val(window.xdrReceiveDirectToAddress);
		$('#precannedFromDirectAddress').val(window.xdrReceiveDirectFromAddress);
	});
	
	$('#uploadOptionalOne').on('hidden.bs.collapse', function () {
		 $('#precannedOptionalHeadingOne a').addClass('collapsed');
		 $('#precannedOptionalOne').css("height", "0px");
		  $('#precannedOptionalOne').removeClass('in');
		  $('#precannedOptionalOne').addClass('collapse');
		  

		  //window.xdrReceiveDirectToAddress = $('#toDirectAddress').val();
		  $('#toDirectAddress').val('');
		  $('#precannedToDirectAddress').val('');
		  //window.xdrReceiveDirectFromAddress = $('#fromDirectAddress').val();
		  $('#fromDirectAddress').val('');
		  $('#precannedFromDirectAddress').val('');
		  
		  $('#messageType').val('minimal');
		  $('#precannedMessageType').val('minimal');
		  
		  $('#XDRPrecannedForm .formError').hide(0);
		  $('#XDRUploadForm .formError').hide(0);

		});
	
	$('#uploadOptionalOne').on('shown.bs.collapse', function () {
		$('#precannedOptionalHeadingOne a').removeClass('collapsed');
		 $('#precannedOptionalOne').css("height", "auto");
		$('#precannedOptionalOne').addClass('in');
		  $('#precannedOptionalOne').removeClass('collapse');

		});
	
	$('#uploadOptionalOne').on('show.bs.collapse', function () {
		$('#toDirectAddress').val(window.xdrReceiveDirectToAddress);
		$('#fromDirectAddress').val(window.xdrReceiveDirectFromAddress);
	});
	
	$('.dropdown-menu').click(function (e) {
		e.stopPropagation();
	});
	
	
	$('#directMessageType a').click(function (e) {
		  e.preventDefault();
		  $(this).tab('show');

		    $('#precannedCCDAsubmit').validationEngine('hideAll');
		    $('#formsubmit').validationEngine('hideAll');
		});
	
	
	$('#precannedWsdlLocation').bind('change',function(){
	    $('#wsdlLocation').val($(this).val());
	 });
	
	$('#wsdlLocation').bind('change',function(){
	    $('#precannedWsdlLocation').val($(this).val());
	 });
	
	
	$('#precannedToDirectAddress').bind('change',function(){
	    $('#toDirectAddress').val($(this).val());
	 });
	
	$('#toDirectAddress').bind('change',function(){
	    $('#precannedToDirectAddress').val($(this).val());
	 });
	
	$('#precannedFromDirectAddress').bind('change',function(){
	    $('#fromDirectAddress').val($(this).val());
	 });
	
	$('#fromDirectAddress').bind('change',function(){
	    $('#precannedFromDirectAddress').val($(this).val());
	 });
	
	$('#precannedMessageType').bind('change',function(){
	    $('#messageType').val($(this).val());
	 });
	
	$('#messageType').bind('change',function(){
	    $('#precannedMessageType').val($(this).val());
	 });
	
	$("#ccdafiletreepanel").jstree({
		 "json_data" : {
			      "ajax" : {
				      "url" : sampleCCDATreeURL,
				      "type" : "post",
				      /*"data" : function (n) {
				    	 return { id : n.attr ? n.attr("id") : 0 };
				      }*/
				  }
	      },
	      
	      "types" : {
	    	  "valid_children" : [ "all" ],
	    	  "type_attr" : "ref",
	    	  "types" : {
	    		  "root" : {
		    	      "icon" : {
		    	    	  "image" : window.currentContextPath + "/images/root.png"
		    	      },
		    	      "valid_children" : [ "file","folder" ],
		    	      "max_depth" : 2,
		    	      "hover_node" : false,
		    	      "select_node" : function (e) {

		    	    	  this.toggle_node(e);
		    	    	  return false;
		    	      }
		    	      
		    	  	},
		    	  "file" : {
		    		  "icon" : {
		    	    	  "image" : window.currentContextPath + "/images/file.png"
		    	      },
		    		  "valid_children" : [ "none" ],
		    		  "deselect_node" : function (node,e) {
		    			  var jform = $('#XDRPrecannedForm');
		    			  $('#XDRPrecannedForm .formError').hide(0);
		    				
		    			  
		    			var textValue = $('#precannedemail').val();
		  				$('#XDRPrecannedForm').trigger('reset');
		  				$('#precannedCCDAsubmit').unbind("click");
		  				
		  				$('#precannedfilePathOutput').empty();
		  				$('#precannedfilepath').val('');
		  				
		  				$('#precannedemail').val(textValue);
		    			  
		    		  },
		    		  "select_node" : function (node,e) {
		    			  var jform = $('#XDRPrecannedForm');
		    			  //jform.validationEngine('hideAll');
		    			  $('#XDRPrecannedForm .formError').hide(0);
		    			  //populate the textbox
		    			  $("#precannedfilepath").val(node.data("serverpath"));
		    			  $("#precannedfilePathOutput").text($("#precannedfilepath").val());
		    	    	  //hide the drop down panel
		    			  $('[data-toggle="dropdown"]').parent().removeClass('open');
		    			  //hide all the errors
		    			  //$('#precannedCCDAsubmit').validationEngine('hideAll');
		    			   
		    			  $('#dLabel').focus();
		    			  $('#dLabel').dropdown("toggle");
		    			  
		    			  $("#precannedCCDAsubmit").click(function(e){
		    				    
		    					var jform = $('#XDRPrecannedForm');
		    					jform.validationEngine({promptPosition:"centerRight", validateNonVisibleFields: true, updatePromptsPosition:true});
		    					if(jform.validationEngine('validate'))
		    					{
		    						$('#XDRPrecannedForm .formError').hide(0);
		    						
		    						//block ui..
		    						BlockPortletUI();
		    						
		    						var formData = $('#XDRPrecannedForm').serializefiles();
		    					    
		    					    $.ajax({
		    					        url: precannedUrl,
		    					        
		    					        type: 'POST',
		    					        
		    					        xhr: function() {  // custom xhr
		    					            myXhr = $.ajaxSettings.xhr();
		    					            if(myXhr.upload){ // check if upload property exists
		    					                myXhr.upload.addEventListener('progressor', progressorHandlingFunction, false); // for handling the progressor of the upload
		    					            }
		    					            return myXhr;
		    					        },
		    					        
		    					        success: function(data){
		    					        	var results = JSON.parse(data);
		    					        	
		    					        	
		    					        	var iconurl = (results.body.IsSuccess == "true")? window.currentContextPath + "/images/icn_alert_success.png" :
		    					        									window.currentContextPath + "/images/icn_alert_error.png" ;
		    					        	

		    					        	$('#xdrreceivewidget .blockMsg .progressorpanel img').attr('src',iconurl);
		    					        	
		    					        	$('#xdrreceivewidget .blockMsg .progressorpanel .lbl').text(results.body.ErrorMessage);

		    					        	if(window.xdrreceivewidget)
		    					        	{
		    					        		window.xdrReceiveUploadTimeout = setTimeout(function(){
		    					        				window.xdrreceivewidget.unbind("click");
		    					        				window.xdrreceivewidget.unblock();
		    					        			},10000);
		    					        		
		    					        		
		    					        		window.xdrreceivewidget.bind("click", function() { 
		    					        			window.xdrreceivewidget.unbind("click");
		    					        			clearTimeout(window.xdrReceiveUploadTimeout);
		    					        			window.xdrreceivewidget.unblock(); 
		    					        			window.xdrreceivewidget.attr('title','Click to hide this message.').click($.unblockUI); 
		    						            });
		    					        		
		    					        	}
		    					        	
		    					        	Liferay.Portlet.refresh("#p_p_id_Statistics_WAR_siteportalstatisticsportlet_"); // refresh the counts
		    					        	
		    					        	
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
		    			        			    	    	
		    			        			$('#receiveaccordion .panel-body .well code').text(results.body.xdrResponse);
		    			        			  
		    			        			    	 
		    			        			  
		    			        			   
		    			        			
		    			        			$("#xdrReceiveModal").modal("show");
		    			        			
		    			        			
		    			        			//disable smart ccda result tab.
		    			        			$("#receiveModalTabs a[href='#receivetabs-1']").tab("show");
		    			        			
		    			        		    Liferay.Portlet.refresh("#p_p_id_Statistics_WAR_siteportalstatisticsportlet_"); // refresh the counts
		    			        		    
		    			        		    //clean up the links
		    			        		    /*$("#ValidationResult #tabs #tabs-1 b:first, #ValidationResult #tabs #tabs-1 a:first").remove();*/
		    			        		    $("#receiveValidationResult .tab-content #receivetabs-1 hr:lt(4)").remove();
		    			        		    
		    					        	
		    					        },
		    					        
		    					        error: function (request, status, error) {
		    					        	var iconurl = window.currentContextPath + "/images/icn_alert_error.png" ;
		    								
		    								$('#xdrreceivewidget .blockMsg .progressorpanel img').attr('src',iconurl);
		    					        	
		    					        	$('#xdrreceivewidget .blockMsg .progressorpanel .lbl').text('Error sending XDR message.');
		    								
		    								if(window.xdrreceivewidget)
		    					        	{
		    					        		window.xdrReceiveUploadTimeout = setTimeout(function(){
		    					        				window.xdrreceivewidget.unbind("click");
		    					        				window.xdrreceivewidget.unblock();
		    					        			},10000);
		    					        		
		    					        		
		    					        		window.xdrreceivewidget.bind("click", function() { 
		    					        			window.xdrreceivewidget.unbind("click");
		    					        			clearTimeout(window.xdrReceiveUploadTimeout);
		    					        			window.xdrreceivewidget.unblock(); 
		    					        			window.xdrreceivewidget.attr('title','Click to hide this message.').click($.unblockUI); 
		    						            });
		    					        		
		    					        	}
		    					        },
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
		    						$('#XDRPrecannedForm .formError').show(0);
		    						
		    						$('#XDRPrecannedForm .precannedfilepathformError').prependTo('#precannederrorlock');
		    					}
		    					return false;
		    				});
		    			  
		    		  }
		    	  },
		    	  "folder" : {
		    		  "icon" : {
		    	    	  "image" : window.currentContextPath + "/images/folder.png"
		    	      },
		    		  "valid_children" : [ "file" ],
		    		  "select_node" : function (e) {
		    	    	  e.find('a:first').focus();
		    			  this.toggle_node(e);
		    	    	  return false;
		    	      }
		    	  }
	    	 }
	    },
	    "plugins" : [ "themes", "json_data", "ui", "types" ]
	}).bind('loaded.jstree', function(e, data) {
		isfiletreeloaded = true;
		
		$('#ccdafiletreepanel').find('a').each(function() {
		    $(this).attr('tabindex', '1');
		});
	});
	
	
	
	$("#xdrSendSearchSubmit").click(function(e){
	    
		var jform = $('#XDRSendGetRequestList');
		jform.validationEngine({promptPosition:"centerRight", validateNonVisibleFields: true, updatePromptsPosition:true});
		if(jform.validationEngine('validate'))
		{
			$('#XDRSendGetRequestList .formError').hide(0);
			
			//block ui..
			BlockSendPortletUI();
			
			var formData = $('#XDRSendGetRequestList').serializefiles();
		    
		    $.ajax({
		        url: xdrSendGetRequestList,
		        
		        type: 'POST',
		        
		        xhr: function() {  // custom xhr
		            myXhr = $.ajaxSettings.xhr();
		            if(myXhr.upload){ // check if upload property exists
		                myXhr.upload.addEventListener('progressor', progressorHandlingFunction, false); // for handling the progressor of the upload
		            }
		            return myXhr;
		        },
		        
		        success: function(data){
		        	var results = JSON.parse(data);
		        	
		        	var iconurl;
		        	
		        	if (results.requestTimestamps.length > 0)
		        	{
		        		results.found = true;
		        		results.ErrorMessage = "Results Found.";
		        		var iconurl = window.currentContextPath + "/images/icn_alert_success.png" ;
		        		
		        	
		        	}
		        	else
		        	{
		        		results.found = false;
		        		results.ErrorMessage = "No Results Found.";
		        		iconurl = window.currentContextPath + "/images/icn_alert_error.png";
		        	}
		        
		        	$('#xdrsendwidget .blockMsg .progressorpanel img').attr('src',iconurl);
		        	
		        	$('#xdrsendwidget .blockMsg .progressorpanel .lbl').text(results.ErrorMessage);

		        	if(window.xdrsendwidget)
		        	{
		        		window.xdrSendUploadTimeout = setTimeout(function(){
		        				window.xdrsendwidget.unbind("click");
		        				window.xdrsendwidget.unblock();
		        			},10000);
		        		
		        		
		        		window.xdrsendwidget.bind("click", function() { 
		        			window.xdrsendwidget.unbind("click");
		        			clearTimeout(window.xdrSendUploadTimeout);
		        			window.xdrsendwidget.unblock(); 
		        			window.xdrsendwidget.attr('title','Click to hide this message.').click($.unblockUI); 
			            });
		        		
		        	}
		        	
		        	Liferay.Portlet.refresh("#p_p_id_Statistics_WAR_siteportalstatisticsportlet_"); // refresh the counts
		        	
		        	if (results.found)
		        	{
		        		clearTimeout(window.xdrSendUploadTimeout);
	        			window.xdrsendwidget.unblock();
	        			var tabHtml1 = ['<title>XDR Send Requests</title>',
	        									    '<h1 align="center">XDR Requests sent from ' + results.lookupCode + '</h1>',
	        									    '<hr/>',
	        									    '<hr/>',
	        									    '<br/>'].join('\n');
	        			
	        			tabHtml1 += '<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
	        			  
	        			var count = 0;
	        			for (var key in results.requestTimestamps)
	        			{
	        				var timestamp = results.requestTimestamps[key];
	        				var timestampDateUTC =  new Date(timestamp.slice(0, 4), timestamp.slice(4, 6) - 1, timestamp.slice(6, 8),
	        						timestamp.slice(8, 10), timestamp.slice(10, 12), timestamp.slice(12, 14));
	        				
	        				var timestampDateWithOffset = new Date(timestampDateUTC.getTime() - (timestampDateUTC.getTimezoneOffset() * 60000));
	        				
	        				var dateString = moment(timestampDateWithOffset).format("MMM DD, YYYY - HH:mm:ss");
	        				
	        				
	        				tabHtml1 += '<div class="panel panel-default" id="accordion'+count+'" data-lookup="' + results.lookupCode + '" data-timestamp="'+timestamp+'" data-loaded="false">';
	        				tabHtml1 += ' <div class="panel-heading" role="tab" id="heading'+count+'">';
	        				tabHtml1 += '  <h4 class="panel-title">';
	        				tabHtml1 += '    <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#accordion'+count+'collapse" aria-expanded="false" aria-controls="accordion'+count+'collapse" >';
	        				tabHtml1 += dateString;
	        				tabHtml1 += '    </a>';
	        				tabHtml1 += '  </h4>';
	        				tabHtml1 += ' </div>';
	        				tabHtml1 += ' <div id="accordion'+count+'collapse" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+count+'">';
	        				tabHtml1 += '	<div class="panel-body"></div>';
	        				tabHtml1 += ' </div>';
	        				tabHtml1 += '</div>';
	        			  
	        				count++;
	        				
	        			}
	        			
	        			tabHtml1 += "</div>";
	        			
	        			
	        			
	        			$("#ValidationResult .tab-content #tabs-1").html(tabHtml1);
	        			
	        			$('.panel').on('show.bs.collapse', function (e) {
	        			    
	        			    var accordionField = $('#' + e.currentTarget.id);
	        			    
	        			    var lookupCode = accordionField.data('lookup');
	        			    var timestamp = accordionField.data('timestamp');
	        			    var isLoaded = accordionField.data('loaded');
	        			    
	        			    if (isLoaded == false) {
	        			    
	        			    $('#accordion div[data-timestamp="'+timestamp+'"] .panel-body').html("Loading...");
			    	    	        			    
	        			    
	        			    	var formData = { lookup: lookupCode, timestamp: timestamp }; //Array 
	        			    	 
	        			    	$.ajax({
	        			    	    url : xdrSendGetRequest,
	        			    	    type: 'POST',
	        			    	    data : formData,
	        			    	    success: function(data, textStatus, jqXHR)
	        			    	    {
	        			    	    	var results = JSON.parse(data);
	        			    	    	
	        			    	    	var codeWrapper = document.createElement('div');
	        			    	    	var codeWrapper2 = document.createElement('div');
	        			    	    	
	        			    	    
	        			    	    	var codeEle = document.createElement("code");
	        			    	    	$('#accordion div[data-timestamp="'+results.timestamp+'"] .panel-body').html('<span style="font-weight:bold;">Request:</span>');
	        			    	    	$('#accordion div[data-timestamp="'+results.timestamp+'"] .panel-body').append(codeWrapper);
	        			    	    	
	        			    	    	if (results.responseContent) {
	        			    	    		var codeEle2 = document.createElement("code");
		        			    	    	
	        			    	    		$('#accordion div[data-timestamp="'+results.timestamp+'"] .panel-body').append('<br/><span style="font-weight:bold;">Response:</span>');
	        			    	    		$('#accordion div[data-timestamp="'+results.timestamp+'"] .panel-body').append(codeWrapper2);
	        			    	    		
	        			    	    		$(codeWrapper2).append(codeEle2);
		        			    	    	
		        			    	    	$(codeEle2).css("white-space", "pre");
		        			    	    	
		        			    	    	$(codeEle2).text(results.responseContent);
	        			    	    	}
	        			    	    		
	        			    	    	$('#accordion div[data-timestamp="'+results.timestamp+'"] .panel-body div').addClass("well");
	        			    	    	$('#accordion div[data-timestamp="'+results.timestamp+'"] .panel-body div').css("overflow-x", "scroll");
	        			    	    	
	        			    	    	
	        			    	    	$(codeWrapper).append(codeEle);
	        			    	    	
	        			    	    	$(codeEle).css("white-space", "pre");
	        			    	    	
	        			    	    	$(codeEle).text(results.requestContent);
	        			    	    	
	        			    	    	
	        			    	    	$('#accordion div[data-timestamp="'+results.timestamp+'"]').data('loaded', true);
	        			    	    	
	        			    	    },
	        			    	    error: function (jqXHR, textStatus, errorThrown)
	        			    	    {
	        			    	    	$('#accordion .in .panel-body').html("Error Retrieving XDR Request.")
	        			    	    }
	        			    	});
	        			    }
	        			    
	        			    
	        			    
	        			});
	        			
	        			$("#xdrSendModal").modal("show");
	        			
	        			
	        			//disable smart ccda result tab.
	        			$("#resultModalTabs a[href='#tabs-1']").tab("show");
	        			
	        		    Liferay.Portlet.refresh("#p_p_id_Statistics_WAR_siteportalstatisticsportlet_"); // refresh the counts
	        		    
	        		    //clean up the links
	        		    /*$("#ValidationResult #tabs #tabs-1 b:first, #ValidationResult #tabs #tabs-1 a:first").remove();*/
	        		    $("#ValidationResult .tab-content #tabs-1 hr:lt(4)").remove();
	        		    
		        	}
		        },
		        
		        error: function (request, status, error) {
		        	var iconurl = window.currentContextPath + "/images/icn_alert_error.png" ;
					
					$('#xdrsendwidget .blockMsg .progressorpanel img').attr('src',iconurl);
		        	
		        	$('#xdrsendwidget .blockMsg .progressorpanel .lbl').text('Error performing lookup.');
					
					if(window.xdrsendwidget)
		        	{
		        		window.xdrSendUploadTimeout = setTimeout(function(){
		        				window.xdrsendwidget.unbind("click");
		        				window.xdrsendwidget.unblock();
		        			},10000);
		        		
		        		
		        		window.xdrsendwidget.bind("click", function() { 
		        			window.xdrsendwidget.unbind("click");
		        			clearTimeout(window.xdrSendUploadTimeout);
		        			window.xdrsendwidget.unblock(); 
		        			window.xdrsendwidget.attr('title','Click to hide this message.').click($.unblockUI); 
			            });
		        		
		        	}
		        },
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
			$('#xdrSendSearchSubmit .formError').show(0);
			
			$('#xdrSendSearchSubmit .precannedfilepathformError').prependTo('#precannederrorlock');
		}
		return false;
	});
	

});