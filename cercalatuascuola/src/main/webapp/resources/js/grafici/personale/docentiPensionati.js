$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/personale/grafici/docenti-pensionati', function(data) {
        var options = {
            chart: {
                type: "column"
            },

            title: {
                text: ""
            },

            xAxis: {
                categories: ["Pensionamento Docenti"]
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
                formatter: function () {
                    return default_tooltip(this.series.name, this.y);
                }
            },

            series: [{}]
        };

        options.series = data.series;
        options.title.text = data.title;

        
        
        if (data.series.length == 0){
        	$('#chart-docenti-pensionati-title').append(data.title);
        	$('#sc-bar-chart-docenti-pensionati').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-bar-chart-docenti-pensionati').highcharts(options);
        	$('#chart-docenti-pensionati-approfondisci').append(data.info);
        	$('#chart-docenti-pensionati-note').append(data.note);
        	$(".chart-explain").dotdot();
        }
        
    });
});