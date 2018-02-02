$(document).ready(function() {
    $.getJSON('/poninchiaro/ponincifre/grafici/progetti/per-tipo-intervento/', function(data) {
        var options = {
            chart: {
                type: "pie"
            },

            title: {
                text: ""
            },

//            xAxis: {
//            	categories: data.categories
//            },
//
//            yAxis: {
//                title: {
//                	text: ""
//                }
//            },

			tooltip: {
				formatter: function () {
					return 	this.point.name+ ": <strong>" +this.y+ "%</strong>";
				}
			},

			plotOptions: {
				series: {
					dataLabels: {
						format: "<strong>{point.name}</strong>: {y}%"
					}
				}
			},

//            legend: {
//                enabled: false
//            },

            series: [{}]
        };

        options.series[0].data = data.series;
        options.title.text = data.title;

        $("#chart-proj-1").highcharts(options);
    });
});