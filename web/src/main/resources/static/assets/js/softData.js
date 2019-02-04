
//加载首页数据表格
function loadIndexData() {
	 
    var t = $("#table").bootstrapTable({
        url: 'http://localhost:8080/data/ruanjian/list',
        method: 'get',
        dataType: "json",
        striped: true,//设置为 true 会有隔行变色效果
        undefinedText: "空",//当数据为 undefined 时显示的字符
        pagination: true, //分页
        // paginationLoop:true,//设置为 true 启用分页条无限循环的功能。
        showToggle: "true",//是否显示 切换试图（table/card）按钮
        showColumns: "true",//是否显示 内容列下拉框
        pageNumber: 1,//如果设置了分页，首页页码
        // showPaginationSwitch:true,//是否显示 数据条数选择框
        pageSize: 15,//如果设置了分页，页面数据条数
        pageList: [15, 30, 50, 100],	//如果设置了分页，设置可供选择的页面数据条数。设置为All 则显示所有记录。
        paginationPreText: '‹',//指定分页条中上一页按钮的图标或文字,这里是<
        paginationNextText: '›',//指定分页条中下一页按钮的图标或文字,这里是>
        // singleSelect: false,//设置True 将禁止多选
        search: false, //显示搜索框
        data_local: "zh-US",//表格汉化
        sidePagination: "server", //服务端处理分页
        queryParams: function (params) {//自定义参数，这里的参数是传给后台的，我这是是分页用的
            return {//这里的params是table提供的
            	startRow: params.offset,//从数据库第几条记录开始
                pageSize: params.limit//找多少条
            };
        },
        idField: "id",//指定主键列
        columns: [
            {
                title: 'id',//表的列名
                field: 'softId',//json数据中rows数组中的属性名
                align: 'center'//水平居中
            },
            {
                title: '软件名称',
                field: 'softName',
                align: 'center'
            },
            {
                title: '大小(MB)',
                field: 'softSize',
                align: 'center'
            },
            {
                title: '更新时间',
                field: 'softUpdateTime',
                align: 'center',
                formatter: function (value, row, index) {
                	return changeDateFormat(value);
                }
            },
            {
                title: '软件类别',
                field: 'softCategory',
                align: 'center'
            },
            {
                title: '软件类型',
                field: 'softType',
                align: 'center'
            },
            {
                title: '下载次数',
                field: 'softDownloadTimes',
                align: 'center'
            },
            {
                title: '评分',
                field: 'softRate',
                align: 'center'
            },
            {
                title: '语言',
                field: 'softLanguage',
                align: 'center'
            }
        ]
    });
 
 
    t.on('load-success.bs.table', function (data) {//table加载成功后的监听函数
        console.log("load success");
        $(".pull-right").css("display", "block");
    });
}

//转换日期格式(时间戳转换为datetime格式)
function changeDateFormat(cellval) {
    var dateVal = cellval + "";
    if (cellval != null) {
        var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        
        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
        
        return date.getFullYear() + "-" + month + "-" + currentDate
    }
}

