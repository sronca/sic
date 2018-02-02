$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/alunni/grafici/rav-24c5', function(data) {
        var options = {
                chart: {
                    type: "column"
                },
                title: {
                    text: ""
                },
                legend: {
                	reversed: true
                },
                xAxis: {
                    title: {
                        text: ""
                    },
                    categories: ["SCUOLA", "REGIONE", "ITALIA"]
                },
                yAxis: {
                    title: {
                        text: ""
                    }
                },
                tooltip: {
                    formatter: function() {
                        return default_tooltip(this.series.name, this.y, "");
                    }
                },
                plotOptions: {
                    series: {
                        dataLabels: {
                            format: "{y}"
                        }
                    },
                    column: {
                        stacking: 'percent'
                    }
                },
                series: [{}]
            };

            options.series = data.series;
            options.title.text = data.title;

        
        if (data.series.length == 0){
        	$('#chart-rav24c5-title').append(data.title);
        	$('#sc-bar-chart-rav24c5').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-bar-chart-rav24c5').highcharts(options);
        	$('#chart-rav24c5-approfondisci').append(data.info);
        	$('#chart-rav24c5-note').append(data.note);
        	$(".chart-explain").dotdot();
        }
    });
});