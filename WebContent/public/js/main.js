/** Colors genrators* */

function calculatePoint(i, intervalSize, colorRangeInfo) {
  var { colorStart, colorEnd, useEndAsStart } = colorRangeInfo;
  return (useEndAsStart
    ? (colorEnd - (i * intervalSize))
    : (colorStart + (i * intervalSize)));
}

function interpolateColors(dataLength, colorScale, colorRangeInfo) {
	  var { colorStart, colorEnd } = colorRangeInfo;
	  var colorRange = colorEnd - colorStart;
	  var intervalSize = colorRange / dataLength;
	  var i, colorPoint;
	  var colorArray = [];

	  for (i = 0; i < dataLength; i++) {
	    colorPoint = calculatePoint(i, intervalSize, colorRangeInfo);
	    colorArray.push(colorScale(colorPoint));
	  }

	  return colorArray;
}  

function getColors(dataLength){
	
	
	const colorScale = d3.interpolateYlGn;

	const colorRangeInfo = {
	  colorStart: 0.15,
	  colorEnd: 0.9,
	  useEndAsStart: false,
	}; 
	
	/* Create color array */
	return interpolateColors(dataLength, colorScale, colorRangeInfo);
}


/** Charts* */
function chart(type,id,data,options){
	
	

	var myPieChart = new Chart(id, {
	    type: type,
	    data: data,
	    options:options
	       
	});
	
	return myPieChart;
}

function pie(id,title,labels,values){
	
	var COLORS = getColors(values.length)
	  
	var data = {
		    datasets: [{
		        data: values,
		        backgroundColor: COLORS,
		        hoverBackgroundColor: COLORS,
		    }],
		    labels: labels,
	};
	
	var options =  {
	    	title: {
	             display: true,
	             text: title
	         },
	        responsive: true,
	        legend: {
	          display: true,
	        },
	        hover: {
	          onHover: function(e) {
	            var point = this.getElementAtEvent(e);
	            e.target.style.cursor = point.length ? 'pointer' : 'default';
	          },
	        },
	        tooltips: {
	            enabled: true
	        },
	        plugins: {
	            datalabels: {
	                formatter: (value, ctx) => {
	                    let sum = 0;
	                    let dataArr = ctx.chart.data.datasets[0].data;
	                    dataArr.map(data => {
	                        sum += data;
	                    });
	                    let percentage = (value*100 / sum).toFixed(2)+"%";
	                    return percentage;
	                },
	                color: '#000',
	            }
	        },
	        
	        
	    }
	return chart('pie',id,data,options)

}

function horizontalBar(id,title,labels,values,datasetlabels){
	
	var COLORS = getColors(values.length)
	  
	var data = {
		    datasets: [{
		    	label:datasetlabels,
		        data: values,
		        backgroundColor: COLORS,
		        hoverBackgroundColor: COLORS,
		    }],
		    labels: labels,
		    
	};
	
	var options =  {
	    	title: {
	             display: true,
	             text: title
	         },
	        responsive: true,
	        legend: {
	          display: true,
	        },
	        hover: {
	          onHover: function(e) {
	            var point = this.getElementAtEvent(e);
	            e.target.style.cursor = point.length ? 'pointer' : 'default';
	          },
	        },
	        tooltips: {
	            enabled: true
	        },
	        plugins: {
	            datalabels: {
	                formatter: (value, ctx) => {
	                   
	                   
	                  
	                    return value;
	                },
	                color: '#000',
	            }
	        },
	        scales: {
	            xAxes: [{
	                ticks: {
	                    beginAtZero: true,
	                    steps: 10,
	                    stepValue: 6,
	                    
	                    }
	                }
	            ]}
	        
	        
	    }
	
	return chart('horizontalBar',id,data,options)
}



function bar(id,title,labels,values,datasetlabels){
	
	var COLORS = getColors(values.length)
	  
	var data = {
		    datasets: [{
		    	label:datasetlabels,
		        data: values,
		        backgroundColor: COLORS,
		        hoverBackgroundColor: COLORS,
		    }],
		    labels: labels,
		    
	};
	
	var options =  {
	    	title: {
	             display: true,
	             text: title
	         },
	        responsive: true,
	        legend: {
	          display: true,
	        },
	        hover: {
	          onHover: function(e) {
	            var point = this.getElementAtEvent(e);
	            e.target.style.cursor = point.length ? 'pointer' : 'default';
	          },
	        },
	        tooltips: {
	            enabled: true
	        },
	        plugins: {
	            datalabels: {
	                formatter: (value, ctx) => {
	                   
	                   
	                  
	                    return value;
	                },
	                color: '#000',
	            }
	        },
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero: true
	                }
	            }]
	        }
	        
	        
	    }
	
	return chart('bar',id,data,options)
}

