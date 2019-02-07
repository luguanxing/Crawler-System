
window.onload = function() {
	loadFileSizeChart();
    loadUpdateTimeChart();
    loadRateChart();
    loadRateAvgDownloadTimesChart();
    loadCategoryChart();
    loadTypeChart();
}

function loadFileSizeChart() {
    // 后台获取数据
    $.ajax({
        url:"/data/ruanjian/fileSize",
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

function loadUpdateTimeChart() {
	
    // 后台获取数据
    $.ajax({
        url:"/data/ruanjian/updateTime",
        success:function(lineData){
        	lineData = JSON.parse(lineData);
		    //指定图标的配置和数据
		    var option = {
		        title:{
		            text:'资源更新随时间分布'
		        },
		        tooltip:{},
		        xAxis:{
		            data:lineData.xAxisNames
		        },
		        yAxis:{
		
		        },
		        series:[{
		            name:'更新量',
		            type:'line',
		            data:lineData.seriesDatas,
		            itemStyle : {
		            	normal: {
		            		color:'#0000FF',
		            		label : {show: true}
		            	}
		            }
		        }]
		    };
		    //初始化echarts实例
		    var updateTimeChart = echarts.init(document.getElementById('updateTimeChart'));
		    
		    // 自适应父容器大小
		    $("#updateTimeChart").width($("#updateTimeChart").parent().width());
		    updateTimeChart.resize();
		
		    //使用制定的配置项和数据显示图表
		    updateTimeChart.setOption(option);
        }
    });
}

function loadRateChart() {
    
    // 后台获取数据
    $.ajax({
        url:"/data/ruanjian/rate",
        success:function(rateData){
            //指定图标的配置和数据
            var option = {
                    title:{
                        text:'评分分布'
                    },            
                    series:[{
                        name:'评分',
                        type:'pie',    
                        radius:'90%', 
                        data:JSON.parse(rateData),
                        itemStyle:{ 
                            normal:{ 
                                label:{ 
                                    show: true, 
                                    formatter: '评分{b}共{c}条(占比{d}%)' 
                                }, 
                                labelLine :{show:true} 
                           } 
                        } 
                    }]
                   
                };
            
            //初始化echarts实例
            var rateChart = echarts.init(document.getElementById('rateChart'));

            // 自适应父容器大小
            $("#rateChart").width($("#rateChart").parent().width());
            rateChart.resize();
            
            //使用制定的配置项和数据显示图表
            rateChart.setOption(option);
        }
    });
    
}

function loadRateAvgDownloadTimesChart() {
    // 后台获取数据
    $.ajax({
        url:"/data/ruanjian/rateAvgDownloadTimes",
        success:function(lineData){
        	lineData = JSON.parse(lineData);
		    //指定图标的配置和数据
		    var option = {
		            title:{
		                text:'每个评分对应平均下载量'
		            },
		            tooltip:{},
		            xAxis:{
		                data:lineData.xAxisNames
		            },
		            yAxis:{
		
		            },
		            series:[{
		                name:'平均下载量',
		                type:'bar',
		                data:lineData.seriesDatas,
		                itemStyle : { normal: {label : {show: true}}}
		            }]
		        };
		    //初始化echarts实例
		    var rateAvgDownloadTimesChart = echarts.init(document.getElementById('rateAvgDownloadTimesChart'));
		    
		    // 自适应父容器大小
		    $("#rateAvgDownloadTimesChart").width($("#rateAvgDownloadTimesChart").parent().width());
		    rateAvgDownloadTimesChart.resize();
		
		    //使用制定的配置项和数据显示图表
		    rateAvgDownloadTimesChart.setOption(option);
        }
    });
}

function loadCategoryChart() {
    
    // 后台获取数据
    $.ajax({
        url:"/data/ruanjian/category",
        success:function(categoryData){
            //指定图标的配置和数据
            var option = {
                    title:{
                        text:'软件类别分布'
                    },            
                    series:[{
                        name:'软件类别',
                        type:'pie',    
                        radius:'90%', 
                        data:JSON.parse(categoryData),
                        itemStyle:{ 
                            normal:{ 
                                label:{ 
                                    show: true, 
                                    formatter: '"{b}"{c}条' 
                                }, 
                                labelLine :{show:true} 
                           } 
                        } 
                    }]
                   
                };
            
            //初始化echarts实例
            var categoryChart = echarts.init(document.getElementById('categoryChart'));

            // 自适应父容器大小
            $("#categoryChart").width($("#categoryChart").parent().width());
            categoryChart.resize();
            
            //使用制定的配置项和数据显示图表
            categoryChart.setOption(option);
        }
    });
    
}


function loadTypeChart() {
    
    // 后台获取数据
    $.ajax({
        url:"/data/ruanjian/type",
        success:function(typeData){
            //指定图标的配置和数据
            var option = {
                    title:{
                        text:'软件类型分布'
                    },            
                    series:[{
                        name:'软件类型',
                        type:'pie',    
                        radius:'90%', 
                        data:JSON.parse(typeData),
                        itemStyle:{ 
                            normal:{ 
                                label:{ 
                                    show: true, 
                                    formatter: '"{b}"共{c}条(占比{d}%)' 
                                }, 
                                labelLine :{show:true} 
                           } 
                        } 
                    }]
                   
                };
            
            //初始化echarts实例
            var typeChart = echarts.init(document.getElementById('typeChart'));

            // 自适应父容器大小
            $("#typeChart").width($("#typeChart").parent().width());
            typeChart.resize();
            
            //使用制定的配置项和数据显示图表
            typeChart.setOption(option);
        }
    });
    
}