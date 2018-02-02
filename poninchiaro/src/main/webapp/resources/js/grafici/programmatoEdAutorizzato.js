$(document).ready(function() {
    $.getJSON('/poninchiaro/ponincifre/grafici/progetti/programmato-autorizzato/', function(data) {
        var options = {
            chart: {
                type: "bar"
            },

            title: {
                text: ""
            },

            xAxis: {
            	categories: data.categories
            },

			yAxis: {
				title: {
					text: "Piano Finanziario del PON",
				},
				labels: {
					enabled: false
				}
			},

			tooltip: {
				useHTML: true,
				formatter: function () {
					return default_tooltip(this.series.name, Highcharts.numberFormat(this.point.y, 2), "&euro;", "");
				}
			},

			plotOptions: {
				 series: {
					dataLabels: {
						format: "€ {point.y:,.2f}"
					}
				}
			},

            legend: {
                enabled: false
            },

            series: [{}]
        };

        options.series = data.series;
        options.title.text = data.title;

        $("#chart-finanz-2").highcharts(options);
    });
});