$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

    //3.初始化表格事件
    var oEventInit = new EventInit();
    oEventInit.Init();
});


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        // 加载后台用户列表
        $('#id_table_blog').bootstrapTable({
            url: '/admin/wx_open_user_list.j',          //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                      //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100, 200, 500],  //可供选择的每页的行数（*）
             search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端
            strictSearch: false,                //是否进行完全匹配
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 650,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                  //是否显示父子表
            showHeader: true,
            columns: [{
                checkbox: true
            }, {
                field: 'id',
                title: 'ID',
                align: 'center',
                sortable: true,
            }, {
                field: 'avatar',
                title: '头像',
                align: 'center',
                formatter: function(value,row,index){
                    return "<img style='width: 50px;height: 50px;' src='"+value+"'class='img-rounded' >";
                }
            }, {
                field: 'nickname',
                title: '昵称',
                align: 'center',
            }, {
                field: 'gmtCreate',
                title: '创建时间',
                align: 'center',
                formatter: formatDateTime,
                sortable: true
            },{
                field: 'status',
                title: '状态',
                align: 'center',
                formatter: function(value, item, index) {
                    if(value === 0){
                        return "<button  onclick='updateStatus("+item.id+")' class='label label-success'>正常</button>";
                    }else{
                        return "<button  onclick='updateStatus("+item.id+")' class='label label-warning'>禁止</button>";
                    }
                }
            }
            ]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {
            //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            // limit: params.limit,   //页面大小
            // offset: params.offset,  //页码
            // title: $("#txt_search_departmentname").val(),
        };
        return temp;
    };

    return oTableInit;
};

/**
 * 重新加载表格
 */
function flushTable() {
    $('#id_table_blog').bootstrapTable('refresh');
}

function updateStatus(e) {

}