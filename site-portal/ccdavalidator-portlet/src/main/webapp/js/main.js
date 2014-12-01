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

function fileSelected()
{
	$(".fakefile input").val($('#ccdafileChooser').val());
}

function BlockPortletUI()
{
	window.validationpanel = $('#CCDAvalidator .well');
	
	var ajaximgpath = window.currentContextPath + "/css/ajax-loader.gif";
	
	window.validationpanel.block({ 
		css: { 
	            border: 'none', 
	            padding: '15px', 
	            backgroundColor: '#000', 
	            '-webkit-border-radius': '10px', 
	            '-moz-border-radius': '10px', 
	            opacity: .5, 
	            color: '#fff',
	            width: '90%' 
		},
		message: '<div class="progressorpanel"><img src="'+ ajaximgpath + '" alt="loading">'+
				 '<div class="lbl">Uploading...</div>' +
				 '<div class="progressor">0%</div></div>',
	});
}

function BlockResultUI()
{
	window.resultPanel = $('#ValidationResult').closest('div[class="ui-dialog"]');
	
	var ajaximgpath = window.currentContextPath + "/css/ajax-loader.gif";
	
	window.resultPanel.block({ 
		css: { 
	            border: 'none', 
	            padding: '15px', 
	            backgroundColor: '#000', 
	            '-webkit-border-radius': '10px', 
	            '-moz-border-radius': '10px', 
	            opacity: .5, 
	            color: '#fff',
	            width: '90%'
		},
		message: '<div class="progressorpanel"><img src="'+ ajaximgpath + '" alt="loading">'+
				 '<div class="lbl">Uploading...</div>' +
				 '<div class="progressor">0%</div></div>',
	});
}



function errorHandler (request, status, error) {
    alert("error:"+ error);
    if(window.validationpanel)
    	window.validationpanel.unblock();
    $.unblockUI();
}

function progressHandlingFunction(e){
    if(e.lengthComputable){
    	var progressval = floorFigure(e.loaded/e.total*100,0);
    	if(progressval < 99)
    	{
    		$('.blockMsg .progressorpanel .lbl').text('Uploading...');
    		$('.blockMsg .progressorpanel .progressor').text( floorFigure(e.loaded/e.total*100,0).toString()+"%" );
    	}
    	else
    	{
    		$('.blockMsg .progressorpanel .lbl').text('Validating...');
    		$('.blockMsg .progressorpanel .progressor').text('');
    	}
    }
}

function floorFigure(figure, decimals){
    if (!decimals) decimals = 2;
    var d = Math.pow(10,decimals);
    return (parseInt(figure*d)/d).toFixed(decimals);
};





function writeSmartCCDAResultHTML(data){
	var results = JSON.parse(data);
	if(results.IsSuccess)
	{
		try{
    		var tablehtml = [];
    		var rubricLookup = results.RubricLookup;
    		var rowtmp = '<tr><td>{label}</td><td>{score}</td><td>{scoreexplain}</td><td>{detail}</td></tr>';
    		
    		tablehtml.push('<table class="bordered">');
    		tablehtml.push('<colgroup>');
    		tablehtml.push('<col span="1" style="width: 15%;">');
    		tablehtml.push('<col span="1" style="width: 50px;">');
    		tablehtml.push('<col span="1" style="width: 15%;">');
    		tablehtml.push('<col span="1" style="width: 67%;">');
    		tablehtml.push('</colgroup>');
    		
    		tablehtml.push('<thead><tr>');
    		tablehtml.push('<th>Rubric</th>');
    		tablehtml.push('<th>Score</th>');
    		tablehtml.push('<th>Comment</th>');
    		tablehtml.push('<th>Details</th>');
    		tablehtml.push('</tr></thead>');
    		
    		tablehtml.push('<tbody>');

    		$.each(results.Results, function(i, result) {
    			//look up the label
    			var rowcache = rowtmp;
    			var label = rubricLookup[result.rubric].description;
    			rowcache = rowcache.replace(/{label}/g, label?label:'N/A');
    			rowcache = rowcache.replace(/{score}/g, result.score?result.score:'N/A');
    			var scoreexplaination = (rubricLookup[result.rubric])?(rubricLookup[result.rubric].points)?rubricLookup[result.rubric].points[result.score]:'N/A':'N/A';
    			rowcache = rowcache.replace(/{scoreexplain}/g, scoreexplaination?scoreexplaination:'N/A');
    			rowcache = rowcache.replace(/{detail}/g, result.detail?result.detail:'');
    			tablehtml.push(rowcache);
            });
    		
    		tablehtml.push('</tbody></table>');
    		
    		$("#resultModalTabs a[href='#tabs-3']").show();
    		
    		$(".modal-body").scrollTop(0);
    		
    		$("#resultModalTabs a[href='#tabs-3']").tab("show");
    		
    		$("#ValidationResult .tab-content #tabs-3" ).html(tablehtml.join(""));
		
		}
		catch(exp)
		{
			alert('javascript crashed, please report this issue:'+ err.message);
		}
		$.unblockUI();
	}
	else
	{
		alert(results.Message);
		$.unblockUI();
	}
}











function smartCCDAValidation()
{
	var ajaximgpath = window.currentContextPath + "/css/ajax-loader.gif";
	
	var selector = null;
	
	//TODO: Make one of these for each C-CDA Validator 
	if ($('#collapseCCDA1_1').hasClass('in')){
		selector = '#CCDA1ValidationForm';
	} else if ($('#collapseCCDA2_0').hasClass('in')){
		selector = '#CCDA2ValidationForm';
	} else if ($('#collapseReconciledValidator').hasClass('in')){
		selector = '#CCDAReconciledValidationForm';
	} else if ($('#collapseReferenceValidator').hasClass('in')){
		selector = '#CCDAReferenceValidationForm';
	} else if ($('#collapseSuperValidator').hasClass('in')){
		selector = '#CCDASuperValidationForm';
	} else {
		
	}
	
	
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
	
	
	
	if (bowser.msie && bowser.version <= 9) {
		
		var jform = $(selector);
		
	    var  iframeId = 'unique' + (new Date().getTime());
	    var action = jform.attr("action");
	    var relay = jform.attr("relay");
	    // Set the action on the form to the value of the relay
	    jform.attr("action", relay);
	    
	    var iframe = $('<iframe src="javascript:false;" name="'+iframeId+'" id="'+iframeId+'" />');
	    
	    iframe.hide();
	    jform.attr('target',iframeId);
	    iframe.appendTo('body');
	    
	    iframe.load(function(e)
	    {
	        var doc = getDoc(iframe[0]); //get iframe Document
	        var node = doc.body ? doc.body : doc.documentElement;
	        var data = (node.innerText || node.textContent);
	        writeSmartCCDAResultHTML(data);
	        
	    });
		
	    jform.submit();
	    // Set the action on the form from the relay 
	    // back to the original action
	    jform.attr("action", action);
	
	} else {
		
		var formData = $(selector).serializefiles();
		var serviceUrl = $(selector).attr("relay");
		$.ajax({
	        url: serviceUrl,
	        type: 'POST',
	        
	        success: function(data){
	        	writeSmartCCDAResultHTML(data);
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
}


function incorpRequired(field, rules, i, options){
	if($('#incorpfilepath').val()== '')
	{
		return "Please select a C-CDA sample";
	}
}


$(function(){
	
	
	
	
	
	
	
	
	
	$('#smartCCDAValidationBtn').bind('click', function(e, data) {
		smartCCDAValidation();
	});
	
	$('#reportSaveAsQuestion button').button();
	
	
	
	$('#saveResultsBtn').on('click', function(e){
		e.preventDefault();
		
		var ajaximgpath = window.currentContextPath + "/css/ajax-loader.gif";
		
		$.blockUI({ css: { 
	        border: 'none', 
	        padding: '15px', 
	        backgroundColor: '#000', 
	        '-webkit-border-radius': '10px', 
	        '-moz-border-radius': '10px', 
	        opacity: .5, 
	        color: '#fff' 
    	},
    	message: '<div class="progressorpanel"><img src="'+ ajaximgpath + '" alt="loading">'+
		          '<div class="lbl">Preparing your report...</div></div>' });
		
		
		//set the value of the result and post back to server.
		
		var $tab = $('#resultTabContent'), $active = $tab.find('.tab-pane.active');
		
		$('#downloadtest textarea').val($active.html());
		//submit the form.
		
		$.fileDownload($('#downloadtest').attr('action'), {
			
			successCallback: function (url) {
				$.unblockUI(); 
            },
            failCallback: function (responseHtml, url) {
            	alert("Server error:" + responseHtml);
            	$.unblockUI(); 
            },
	        httpMethod: "POST",
	        data: $('#downloadtest').serialize()
	    });
		
	});
	
	//ccdavalidator_callAjax();
	/*
	$("#ccdavalidate_btn").click(function(e){
	    
		//switch back to tab1.
		$( "#ValidationResult [href='#tabs-1']").trigger( "click" );
		
		//block the UI.
		//find the cloest panel content
		
		BlockPortletUI();
		
		var formData = $('#CCDAValidationForm').serializefiles();
	    
	    $.ajax({
	        url: $('#CCDAValidationForm').attr('action'),
	        type: 'POST',
	        
	        xhr: function() {  // custom xhr
	            myXhr = $.ajaxSettings.xhr();
	            if(myXhr.upload){ // check if upload property exists
	                myXhr.upload.addEventListener('progress',progressHandlingFunction, false); // for handling the progress of the upload
	            }
	            return myXhr;
	        },
	        
	        //Ajax events
	        //beforeSend: beforeSendHandler,
	        success: completeHandler,
	        error: errorHandler,
	        // Form data
	        data: formData,
	        //Options to tell JQuery not to process data or worry about content-type
	        cache: false,
	        contentType: false,
	        processData: false
	    });
	    return false;
	});
	*/
	
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
		    			  var jform = $('#incorpForm');
		    			  $('#incorpForm .formError').hide(0);
		    				
		    			  
		    			var textValue = $('#incorpemail').val();
		  				$('#incorpForm').trigger('reset');
		  				$('#incorpCCDAsubmit').unbind("click");
		  				
		  				$('#incorpfilePathOutput').empty();
		  				$('#incorpfilepath').val('');
		  				
		  				$('#incorpemail').val(textValue);
		    			  
		    		  },
		    		  "select_node" : function (node,e) {
		    			  //var jform = $('#incorpForm');
		    			  //jform.validationEngine('hideAll');
		    			  $('#incorpForm .formError').hide(0);
		    			  //populate the textbox
		    			  $("#incorpfilepath").val(node.data("serverpath"));
		    			
		    			  
		    			  $("#incorpfilePathOutput").text($("#incorpfilepath").val());
		    			 
		    			  
		    	    	  //hide the drop down panel
		    			  $('[data-toggle="dropdown"]').parent().removeClass('open');
		    			  //hide all the errors
		    			  //$('#incorpCCDAsubmit').validationEngine('hideAll');
		    			  
		    			  $('#dLabel').focus();
		    			  $('#dLabel').dropdown("toggle");
		    			  
		    			  $("#incorpCCDAsubmit").click(function(e){
		    				    
		    				  	var ajaximgpath = window.currentContextPath + "/css/ajax-loader.gif";
		    				  	$.blockUI({ css: { 
		    				        border: 'none', 
		    				        padding: '15px', 
		    				        backgroundColor: '#000', 
		    				        '-webkit-border-radius': '10px', 
		    				        '-moz-border-radius': '10px', 
		    				        opacity: .5, 
		    				        color: '#fff' 
		    			    	},
		    			    	message: '<div class="progressorpanel"><img src="'+ ajaximgpath + '" alt="loading">'+
		    					          '<div class="lbl">Preparing your download...</div></div>' });
		    				  	
		    				  
		    					var jform = $('#incorpForm');
		    					jform.validationEngine({promptPosition:"centerRight", validateNonVisibleFields: true, updatePromptsPosition:true});
		    					if(jform.validationEngine('validate'))
		    					{
		    						$('#incorpForm .formError').hide(0);
		    						
		    					    
		    						$.fileDownload($('#incorpForm').attr('action'), {
		    							
		    							successCallback: function (url) {
		    								$.unblockUI(); 
		    				            },
		    				            failCallback: function (responseHtml, url) {
		    				            	alert("Server error:" + responseHtml);
		    				            	alert(url);
		    				            	$.unblockUI(); 
		    				            },
		    					        httpMethod: "POST",
		    					        data: $('#incorpForm').serialize()
		    					    });
		    						
		    						
		    						/*
		    						 * 
		    						var formData = $('#incorpForm').serialize();
		    					    $.ajax({
		    					        url: $('#incorpForm').attr('action'),
		    					        
		    					        type: 'POST',
		    					        
		    					        //xhr: function() {  // custom xhr
		    					         //   myXhr = $.ajaxSettings.xhr();
		    					         //   if(myXhr.upload){ // check if upload property exists
		    					         //      myXhr.upload.addEventListener('progressor', progressorHandlingFunction, false); 
		    					         //   }
		    					         //   return myXhr;
		    					        //},
		    					        
		    					        success: function(data){
		    					        	
		    					        },
		    					        
		    					        error: function (request, status, error) {
		    					        	alert(status);
		    					        	alert(error);
		    					        },
		    					        // Form data
		    					        data: formData,
		    					        //Options to tell JQuery not to process data or worry about content-type
		    					        cache: false,
		    					        contentType: false,
		    					        processData: false
		    					    });
		    					    */
		    					}
		    					else
		    					{
		    						$('#incorpForm .formError').show(0);
		    						
		    						$('#incorpform .incorpfilepathformError').prependTo('#incorperrorlock');
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
	
	
});