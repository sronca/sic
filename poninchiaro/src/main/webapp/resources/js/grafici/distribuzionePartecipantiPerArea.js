$(document).ready(function() {
    $.getJSON('/poninchiaro/ponincifre/grafici/beneficiari/per-area/', function(data) {
        var options = {
            chart: {
                type: "bar"
            },

            title: {
                text: ""
            },
            
			legend: {
				enabled: true,
				align: "center",
				verticalAlign: "top"
			},

            xAxis: {
            	categories: data.categories
            },

			yAxis: {
				title: {
					text: null,
				},
				labels: {
					enabled: false
				}
			},

			tooltip: {
				useHTML: true,
				formatter: function () {
					return default_tooltip(this.series.name, Highcharts.numberFormat(this.point.y, 0), "", "");
				}
			},

			plotOptions: {
				 series: {
					dataLabels: {
						format: "{point.y:,.0f}"
					}
				}
			},
			
            series: [{}]
        };

        options.series = data.series;
        options.title.text = data.title;

        $("#chart-ben-1").highcharts(options);
    });
});