$(document).ready(function() {
    $.getJSON('/poninchiaro/ponincifre/grafici/beneficiari/per-ciclo-scolastico/', function(data) {
        var options = {
            chart: {
                type: "column"
            },

            title: {
                text: ""
            },
            
			legend: {
				enabled: true,
				//reversed: true,
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
					formatter: function () {
						return Highcharts.numberFormat(this.value,0);
					}
				}
			},

			tooltip: {
				useHTML: true,
				formatter: function () {
					return default_tooltip(this.series.name, Highcharts.numberFormat(this.point.y, 0), "", "");
				},
				backgroundColor: "rgba(255,255,255,1)"
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

        $("#chart-ben-3").highcharts(options);
    });
});