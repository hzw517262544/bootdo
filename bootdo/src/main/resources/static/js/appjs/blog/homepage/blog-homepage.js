var prefix = "/blog/homepage";
$(function() {
    // 最新\最热切换事件
    $(".nav-item .nav-link").click(function() {
        var url = $(this).attr("url");
        // 先移除其他的点击样式，再添加当前的点击样式
        $(".nav-item .nav-link").removeClass("active");
        $(this).addClass("active");
        // 加载其他模块的页面到右侧工作区
        $.ajax({
            url: url+'&async=true',
            success: function(data){
                $("#mainContainer").html(data);
            },
            error : function() {
                toastr.error("error!");
            }
        })

        // 清空搜索框内容
        $("#keyword").val('');
    });
});

//删除分类
function remove_catalog(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : "/blog/catalog/remove",
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
//新增分类
function add_catalog(){
    layer.open({
        type : 2,
        title : '新建分类',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '450px', '300px' ],
        content : prefix + '/add_catalog' // iframe的url
    });
}

//编辑分类
function edit_catalog(id) {
    layer.open({
        type : 2,
        title : '编辑分类',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '450px', '300px' ],
        content : prefix + '/edit_catalog/' + id // iframe的url
    });
}

// 获取分类列表
function getCatalogs() {
    $.ajax({
        url: '/blog/catalog/list',
        type: 'GET',
        data:{},
        success: function(data){
            $("#catalogMain").html(data);
        },
        error : function() {
            layer.msg("error!");
        }
    });
}
