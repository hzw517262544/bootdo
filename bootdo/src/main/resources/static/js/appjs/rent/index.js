$().ready(function() {
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        easyRentLogin();
    }
});

function easyRentLogin() {
    $.ajax({
        url : '/rent/login',
        type : "POST",
        data:$('#easyRentLoginForm').serialize(),
        success : function(data) {
            if (data.code == 0) {
                //登录成功，刷新主页
                parent.location.href = '/rent';
            }else {
                parent.layer.alert(data.msg);
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#easyRentLoginForm").validate({
        rules : {
            username : {
                required : true
            },
            password : {
                required : true,
            }
        },
        messages : {
            username : {
                required : icon + "请输入用户名"
            },
            password : {
                required : icon + "请输入密码"
            }
        }
    })
}