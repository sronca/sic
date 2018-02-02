$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/alunni/grafici/studenti-ripetenti', function(data) {
        var options = {
            chart: {
                type: "bar"
            },
            title: {
                text: ""
            },
            xAxis: {
                title: {
                    text: "Anno di corso"
                },
                categories: ["1°", "2°", "3°", "4°", "5°"]
            },
            yAxis: {
                title: {
                    text: ""
                },
                labels: {
                    enabled: false
                }
            },
            tooltip: {
                formatter: function() {
                    return default_tooltip(this.series.name, this.y);
                }
            },
            series: [{}]
        };

        options.series = data.series;
        options.title.text = data.title;

        
        
        if (data.series.length == 0){
        	$('#chart-studenti-ripetenti-title').append(data.title);
        	$('#sc-bar-chart-studenti-ripetenti').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-bar-chart-studenti-ripetenti').highcharts(options);
        	$('#chart-studenti-ripetenti-approfondisci').append(data.info);
        	$('#chart-studenti-ripetenti-note').append(data.note);
        	$(".chart-explain").dotdot();
        }
    });
});
