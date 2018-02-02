$(document).ready(function() {
    $.getJSON('/poninchiaro/ponincifre/grafici/beneficiari/per-regione/', function(data) {
        var options = {
            chart: {
                type: "bar"
            },

            title: {
                text: ""
            },
            
			legend: {
				enabled: true,
				reversed: true,
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
				},
	            stackLabels: {
	                enabled: true,
	                style: {
	                    fontWeight: 'bold',
	                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                }
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
					 stacking: "normal",
					 dataLabels: {
						format: "{point.y:,.0f}"
					}
				}
			},
			
            series: [{}]
        };

        options.series = data.series;
        options.title.text = data.title;

        $("#chart-ben-2").highcharts(options);
    });
});