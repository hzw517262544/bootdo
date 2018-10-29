$().ready(function() {
    validateRule();
});

/*$.validator.setDefaults({
    submitHandler : function() {
        save();
    }
});*/

function save() {
    /*if(!$('#signupForm').valid()){
        return false;
    }*/
    $.ajax({
        cache : true,
        type : "POST",
        url : "/rent/rentAdviseMessage/save",
        data : $('#signupForm').serialize(),// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("提交成功");
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
                maxlength:50
            },
            mobile : {
                required : true,
                isMobile : true
            },
            email : {
                required : false,
                maxlength : 50,
                email : true
            },
            advise : {
                required : true,
                maxlength:800
            }
        },
        messages : {
            username : {
                required : icon + "请输入用户名",
                maxlength : icon + "最多输入50个字符"
            },
            mobile : {
                required : icon + "请输入手机号码",
                isMobile : icon + "请输入正确的手机号码"
            },
            email : {
                required : icon + "请输入邮箱",
                maxlength : icon + "最多输入50个字符",
                email : icon + "请输入正确的邮箱"
            },
            advise : {
                required : icon + "请输入建议",
                maxlength:icon + "最多输入800个字符"
            }
        },
        submitHandler : function() {
            save();
        }
    })
}