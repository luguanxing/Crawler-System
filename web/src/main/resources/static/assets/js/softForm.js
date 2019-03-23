$(function () {
	$("#zdySelect").on("change", function(){
		var aggSize = $("#zdySelect").val();
		if (aggSize < 0) {
			$('#zdyInput').attr("disabled", false);
		} else {
			$('#zdyInput').attr("disabled", true);
		}
	});
});

function submit() {
	try {
		var aggType = $("#aggType").val();
		var aggSize = $("#zdySelect").val();
		var aggSizesArray = $('#zdyInput').val();
		if (aggSize == -1) {
			var aggSizes = aggSizesArray.split(",");
			if (aggSizes.length == 0) {
				alert("未输入自定义大小粒度");
				return;
			}
			var size = eval(aggSizes[0]);
			for (var i = 1; i < aggSizes.length; i++) {
				if (size < eval(aggSizes[i])) {
					size = eval(aggSizes[i]);
				} else {
					alert("非递增数组");
					return;
				}
			}
			console.log("check ok");
		}
		loadFileSizeChart(aggType, aggSize, aggSizesArray);
	} catch (err) {
		alert("出现错误：" + err);
		return;
	}
}

function loadFileSizeChart(aggType, aggSize, aggSizesArray) {
    // 后台获取数据
    $.ajax({
        url:"/data/ruanjian/query?aggType=" + aggType + "&aggSize=" + aggSize + "&aggSizesArray=" + aggSizesArray,
        success:function(lineData){
        	lineData = JSON.parse(lineData);
		    //指定图标的配置和数据
		    var option = {
		            title:{
		                text:'文件大小分布(MB)'
		            },
		            tooltip:{},
		            xAxis:{
		                data:lineData.xAxisNames,
		                axisLabel:{
			                interval:0,
			                rotate:15
			            }
		            },
		            yAxis:{
		
		            },
		            series:[{
		                name:'文件大小(MB)',
		                type:'bar',
		                data:lineData.seriesDatas,
		                itemStyle : { normal: {color:'#000000', label : {show: true}}}
		            }]
		        };
		    //初始化echarts实例
		    var fileSizeChart = echarts.init(document.getElementById('fileSizeChart'));
		    
		    // 自适应父容器大小
		    $("#fileSizeChart").width($("#fileSizeChart").parent().width());
		    fileSizeChart.resize();
		
		    //使用制定的配置项和数据显示图表
		    fileSizeChart.setOption(option);
        }
    });
}