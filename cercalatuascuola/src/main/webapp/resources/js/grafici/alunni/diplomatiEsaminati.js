$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/alunni/grafici/diplomati-esaminati', function(data) {
        var options = {
            chart: {
                type: "column"
            },

            title: {
                text: ""
            },
            legend: {
                enabled: false
            },
            xAxis: {
                categories: ["SCUOLA", "REGIONE", "ITALIA"]
            },

            yAxis: {
                title: {
                    text: ""
                },
                max : 100
            },

            tooltip: {
                formatter: function () {
                    return default_tooltip(this.series.name, this.y);
                }
            },

            series: [{}]
        };

        options.series = data.series;
        options.title.text = data.title;

        
        
        if (data.series.length == 0){
        	$('#chart-diplomati-esaminati-title').append(data.title);
        	$('#sc-bar-chart-diplomati-esaminati').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-bar-chart-diplomati-esaminati').highcharts(options);
        	$('#chart-diplomati-esaminati-note').append(data.note);
        	$('#chart-diplomati-esaminati-approfondisci').append(data.info);
        	$(".chart-explain").dotdot();
        }
    });
});