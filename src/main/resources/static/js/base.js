$(function(){
	/** Messenger消息提示插件设置 **/
	Messenger.options = {
	        extraClasses: 'messenger-fixed messenger-on-top',
	        theme: 'air'
	}
	
    /** 异步表单提交 **/
    $(document).on("click", "[name='submit']", function(e) {
        var form = $(this).parents("form");
        var url = form.attr("action");
        var data = form.serialize();
        $("[data-error]").html("");
        $.post(url, data, function(json) {
        	if (json.code == 200) {
	       		Messenger().post({
	       			message: json.msg,
	                type: 'success',
	                hideAfter: 3,
	                showCloseButton: true
	            });
	       		if(json.tourl == "reload") {
	       			window.location.reload();
	       		} else {
	       			window.location.href = json.tourl;
	       		}
        	} else if(json.tourl != null) {
        		swal({
    			  title: "错误	",
    			  text: json.message,
    			  icon: "error",
    			  button: "前往登录",
    			  closeOnClickOutside: false,
    			})
    			.then((value) => {
    				window.location.href = json.tourl;
				});
        	} else {
                 $.each(json, function(field, message) {
                     var name = form.find("[data-error='" + field + "']");
                     if (name.length > 0) {
                         name.html(message);
                     } else {
                         Messenger().post({
                             message: message,
                             type: 'error',
                             hideAfter: 2,
                             showCloseButton: true
                         });
                     }
                 });
        	}
        }, "json");
    });
	
	$(document).on("click", "[data-delete]", function() {
		$.ajax({
			url: $(this).data("delete"),
			type: "DELETE",
			dataType: "json",
			success: function(json) {
				console.log("success");
				if (json.code == 200) {
					Messenger().post({
		       			message: json.msg,
		                type: 'success',
		                hideAfter: 3,
		                showCloseButton: true
		            });
		       		if(json.tourl == "reload") {
		       			window.location.reload();
		       		} else {
		       			window.location.href = json.tourl;
		       		}
				}
			},
			error: function(json) {
				console.log("error");
			}
		});
	})
})