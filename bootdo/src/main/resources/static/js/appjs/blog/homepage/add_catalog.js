var prefix = "/blog/catalog"
$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : prefix + "/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				// parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);
				//重新加载分类列表
                parent.getCatalogs();
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
            name : {
				required : true,
				maxlength:50
			}
		},
		messages : {
            name : {
				required : icon + "请输入分类名称",
				maxlength: icon + "分类名称最多50个字符"
			}
		}
	})
}
//删除
function remove() {
    layer.confirm('确定要删除选中的记录？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : prefix + "/remove",
            type : "post",
            data : {
                'id' : id
            },
            success : function(r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    // reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}
