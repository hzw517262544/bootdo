$().ready(function() {
	validateRule();
    $("#imgFile").change(function () {
        upLoadFile();
    });
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
		url : "/rent/recommend/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

};
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
            houseType : {
				required : true,
                maxlength : 100
			},
            houseAbstract : {
                required : true,
                maxlength : 200
            },
            location : {
                required : true,
                maxlength : 100
            },
            price : {
                required : true,
                digits:true
            }
		},
		messages : {
            houseType : {
				required : icon + "请输入户型",
                maxlength : icon + "最多输入100个字符"
            },
            houseAbstract : {
                required : icon + "请输入简介",
                maxlength : icon + "最多输入200个字符"
            },
            location : {
                required : icon + "请输入位置",
                maxlength : icon + "最多输入100个字符"
            },
            price : {
                required : icon + "请输入价格",
                digits : icon + "请输入整数"
            }
		}
	})
};

/**
 * 上传照片
 */
function upLoadFile() {
    var file = document.getElementById('imgFile').files[0];
    var size = file.size;
    if((size / 1024 / 1024) > 10) {
        alert("文件大小不能超过10M...");
        return false;
    }
    console.log("size="+size);
    var formData = new FormData();
    formData.append("file", file);
    $.ajax({
        data : formData,
        type : "POST",
        url : "/common/sysFile/upload",    // 图片上传出来的url，返回的是图片上传后的路径，http格式
        cache : false,
        contentType : false,
        processData : false,
        dataType : "json",
        success: function(data) {//data是返回的hash,key之类的值，key是定义的文件名
            layer.msg("上传成功！");
            $("#houseImg").attr("src", data.fileName);
            $("#imgUrl").val(data.fileName);
        },
        error:function(){
            alert("上传失败！");
        }
    });
}