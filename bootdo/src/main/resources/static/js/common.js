$().ready(function() {
    /*加载select*/
    $("select").each(function (index,element) {
        var selectType = element.name;
        var id = element.id;
        if(selectType != undefined&&selectType != ''){
            getSelectType(id,selectType,null);
        }
    });
});

function getSelectType(elementId,selectType,parentSelectTypeId) {
    $.ajax({
        url : '/common/dict/list',
        data:{
            limit: 10000,
            offset:0,
            'type':selectType,
            'parentId':parentSelectTypeId
        },
        success : function(data) {
            //加载数据
            var html = "";
            var rows = data.rows;
            for (var i = 0; i < rows.length; i++) {
                html += '<option value="' + rows[i].value + '">' + rows[i].name + '</option>'
            }
            $("#"+elementId).append(html);
            $("#"+elementId).chosen({
                maxHeight : 200
            });
        }
    });
}