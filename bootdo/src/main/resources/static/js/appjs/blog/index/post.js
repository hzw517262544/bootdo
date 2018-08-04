
function saveComment() {
    $.ajax({
        cache : true,
        type : "POST",
        url : "/blog/comment/save",
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
        }
    });
}
//点赞
function submitVote() {
    $.ajax({
        cache : true,
        type : "GET",
        url : "/blog/bContent/submitVote",
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
        url : "/blog/bContent/cancelVote",
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
