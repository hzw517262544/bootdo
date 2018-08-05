
function saveComment() {
    if($("#commentContent").val().length == 0){
        layer.msg("请输入评论内容！");
        return;
    }
    $.ajax({
        cache : true,
        type : "POST",
        url : "/blog/open/saveComment",
        data : {
        	'comment':$("#commentContent").val(),
			'blogId':$("#blogId").text()
		},// 你的formid
        async : false,
        error : function(request) {
            layer.msg("评论失败！");
        },
        success : function(data) {
            layer.msg("评论成功！");
            //重新加载评论信息
            var html = "<div class=\"row\">"
            html = "<h2 class=\"card-title col-lg-1 col-md-2\">";
            html += "<span>";
            html += "<a href=\"#\" title=\""+data.createUserName+"\">";
            html += "<img src=\""+data.picUrl+"\"  style=\"border-radius: 50%;height: 50px;width: 50px;\">";
            html += "</a>";
            html += "</span>";
            html += "</h2>";
            html += "<div class=\"card-text col-lg-11 col-md-10\">";
            html += "<a>"+data.rownum+"</a>楼  ";
            html += "<a class=\"card-link\">"+data.createUserName+"</a>";
            html += "<a>"+data.createTime+"</a>";
            html += "<!--<a href=\"javascript:void(0)\" class=\"blog-delete-comment\" th:if=\"${commentOwner} eq *{comment.createUserName}\" th:attr=\"commentId=*{id}\"><i class=\"fa fa-trash-o\" aria-hidden=\"true\"></i></a>-->";
            html += "<p>"+data.comment+"</p>";
            html += "</div>";
            html += "</div>";
            $("#mainContainer").append(html);
        }
    });
}
//点赞
function submitVote() {
    $.ajax({
        cache : true,
        type : "GET",
        url : "/blog/open/submitVote",
        data : {
            'cid':$("#blogId").text()
        },
        async : false,
        error : function(request) {
            layer.msg("点赞失败！");
        },
        success : function(data) {
            layer.msg("点赞成功！");
            //隐藏点赞按钮，显示取消点赞按钮
            $("#submitVote").hide();
            $("#cancelVote").show();
        }
    });
}
//取消点赞
function cancelVote() {
    $.ajax({
        cache : true,
        type : "GET",
        url : "/blog/open/cancelVote",
        data : {
            'cid':$("#blogId").text()
        },
        async : false,
        error : function(request) {
            layer.msg("取消点赞失败！");
        },
        success : function(data) {
            layer.msg("取消点赞成功！");
            //隐藏取消点赞按钮，显示点赞按钮
            $("#cancelVote").hide();
            $("#submitVote").show();
        }
    });
}
