$(document).ready(function() {
    $.getJSON('/poninchiaro/ponincifre/grafici/finanziamenti/importi-programmazione/', function(data) {
        var options = {
            chart: {
                type: "pie"
            },

            title: {
                text: ""
            },
            
			tooltip: {
				useHTML: true,
				formatter: function () {
					return default_tooltip(this.point.name, Highcharts.numberFormat(this.point.secondata, 2), "&euro;", "");
				}
			},            
			
			plotOptions: {
				series: {
					dataLabels: {
						format: "<strong>{point.name}</strong>: {y}%"
					}
				}
			},			

            series: [{}]
        };

        options.series[0].data = data.series;
        options.title.text = data.title;

        $("#chart-finanz-1").highcharts(options);
    });
});