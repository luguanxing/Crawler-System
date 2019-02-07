
window.onload = function() {
	loadFileSizeChart();
	loadMusicLongChart()
}

function loadFileSizeChart() {
    // 后台获取数据
    $.ajax({
        url:"/data/yinpin/fileSize",
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


function loadMusicLongChart() {
    // 后台获取数据
    $.ajax({
        url:"/data/yinpin/musicLong",
        success:function(lineData){
        	lineData = JSON.parse(lineData);
		    //指定图标的配置和数据
		    var option = {
		            title:{
		                text:'音乐时长分布(秒)'
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
		                name:'音乐时长(秒)',
		                type:'bar',
		                data:lineData.seriesDatas,
		                itemStyle : { normal: {color:'#000000', label : {show: true}}}
		            }]
		        };
		    //初始化echarts实例
		    var musicLongChart = echarts.init(document.getElementById('musicLongChart'));
		    
		    // 自适应父容器大小
		    $("#musicLongChart").width($("#musicLongChart").parent().width());
		    musicLongChart.resize();
		
		    //使用制定的配置项和数据显示图表
		    musicLongChart.setOption(option);
        }
    });
}