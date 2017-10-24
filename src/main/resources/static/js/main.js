$(function(){
	$(document).on("click", ".drawer_button", function(){//控制左侧滑的菜单
		// console.log("点击了");
		$(".layout_drawer").toggleClass("is-visible");
		$(".layout_obfuscator").toggleClass("is-visible");
	})
	$(document).on("click", ".layout_obfuscator", function(){//点击蒙板隐藏侧滑和蒙板
		// console.log("点击了");
		$(".layout_drawer").toggleClass("is-visible");
		$(".layout_obfuscator").toggleClass("is-visible");
	})
    $(document).on('click', '#top-search', function(e){//header搜索   
        e.preventDefault();
        $('.header').addClass('search-toggled');
        $('#top-search-wrap input').focus();
        $('#top-search-wrap input').on('click', function(e){e.stopPropagation();});
        $('html').one('click', function(){
            $('.header').removeClass('search-toggled');
            e.preventDefault();
        });
    });
    $('body').on('click', '.profile-menu > a', function(e){//小屏幕左侧滑用户banner展开收起
        e.preventDefault();
        $(this).parent().toggleClass('toggled');
        $(this).next().slideToggle(200);
    });
    //评论切换
    $(document).on("click", ".Jinput", function() {
        $(this).hide().next(".Jcommentform").fadeIn("fast");
        $(".textarea[name='content']").focus();
        //console.log($(this).closest(".commentBox").find(".star"));
        //$(this).closest(".commentBox").find(".star").raty('score', 0);//加载后将分重置
        $(this).closest(".commentBox").find("[name='confirm']").val("");//加载后将变量重置
        $(this).closest(".commentBox").find("[name='content']").val("");//加载后将变量重置
    });
    //隐藏评论表单
    $(document).on("click", ".Jformhide", function() {
        $(this).parents(".Jcommentform").hide().prev(".Jinput").show();
    });
    //分享新鲜事
    $(document).on('click', ".placeholder", function(){$(".large-box").slideDown();})
    $(document).on('click', ".remove", function(){$(".large-box").slideUp();})
})