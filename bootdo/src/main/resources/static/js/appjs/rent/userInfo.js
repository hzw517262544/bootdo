$().ready(function() {
    validateRule();
    $("#easyRentUserImgFile").change(function () {
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
        url : "/rent/register/add",
        data : $('#signupForm').serialize(),// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("注册成功");
                //注册成功调用易租登录接口
                easyRentLogin($("#username").val(),$("#password").val(),null);
            } else {
                parent.layer.alert(data.msg);
            }

        }
    });

}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            username : {
                required : true,
                maxlength:50,
                remote: {
                    url: "/rent/register/validateUsernameExit",
                    type: "post",
                    dataType: "json",
                    data: {
                        pwd: function () {
                            return $("#username").val();　　　　//这个是取要验证的密码
                        }
                    },
                    dataFilter: function (data) {　　　　//判断控制器返回的内容
                        if (data == "true") {
                            return false;
                        }
                        else {
                            return true;
                        }
                    }
                }
            },
            birth : {
                required : false
            },
            sex : {
                required : false
            },
            mobile : {
                required : true,
                isMobile : true,
                remote: {
                    url: "/rent/register/validateMobileExit",
                    type: "post",
                    dataType: "json",
                    data: {
                        pwd: function () {
                            return $("#mobile").val();　　　　//这个是取要验证的密码
                        }
                    },
                    dataFilter: function (data) {　　　　//判断控制器返回的内容
                        if (data == "true") {
                            return false;
                        }
                        else {
                            return true;
                        }
                    }
                }
            },
            email : {
                required : false,
                maxlength:100,
                email : true,
                remote: {
                    url: "/rent/register/validateEmailExit",
                    type: "post",
                    dataType: "json",
                    data: {
                        pwd: function () {
                            return $("#email").val();　　　　//这个是取要验证的密码
                        }
                    },
                    dataFilter: function (data) {　　　　//判断控制器返回的内容
                        if (data == "true") {
                            return false;
                        }
                        else {
                            return true;
                        }
                    }
                }
            },
            province : {
                required : false
            },
            city : {
                required : false
            },
            district : {
                required : false
            },
            liveAddress : {
                required : false,
                maxlength:200

            },
            hobby : {
                required : false,
                maxlength:200
            },
            password : {
                required : true,
                rangelength:[6,20]
            },
            confirmPassword : {
                equalTo: "#password"
            }
        },
        messages : {
            username : {
                required : icon + "请输入用户名",
                maxlength : icon + "最多输入50个字符",
                remote : '用户名已被注册'
            },
            mobile : {
                required : icon + "请输入手机号码",
                isMobile : icon + "请输入正确的手机号码",
                remote : icon + "该手机号码已被注册"
            },
            email : {
                required : icon + "请输入邮箱",
                email : icon + "请输入正确的邮箱格式",
                maxlength : icon + "最多输入100个字符",
                remote : icon + "该邮箱已被注册"

            },
            liveAddress : {
                required : icon + "请输入地址",
                rangelength:icon + "最多输入200个字符"
            },
            hobby : {
                required : icon + "请输入爱好",
                rangelength:icon + "最多输入200个字符"
            },
            password : {
                required : icon + "请输入密码",
                rangelength:icon + "密码长度为6-20"
            },
            confirmPassword : {
                equalTo : icon + "与密码不一致"
            }
        }
    })
}

/**
 * 上传照片
 */
function upLoadFile() {
    var file = document.getElementById('easyRentUserImgFile').files[0];
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
            debugger;
            $("#easyRentUserImg").attr("src", data.fileName);
            $("#easyRentUserImg").val(data.fileName);
            $("#picId").val(data.fileId);
        },
        error:function(){
            alert("上传失败！");
        }
    });
}