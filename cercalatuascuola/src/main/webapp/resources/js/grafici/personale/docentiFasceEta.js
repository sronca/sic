$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/personale/grafici/docenti-fasce-eta', function(data) {
        var options = {
            chart: {
                type: "column"
            },

            title: {
                text: ""
            },

            xAxis: {
                title: {
                    text: "Fascia di eta'"
                },
                categories: ["<35", "35-44", "45-54", "55+"]
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
        	$('#chart-docenti-fasce-eta-title').append(data.title);
        	$('#sc-bar-chart-docenti-fasce-eta').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-bar-chart-docenti-fasce-eta').highcharts(options);
        	$('#chart-docenti-fasce-eta-approfondisci').append(data.info);
        	$('#chart-docenti-fasce-eta-note').append(data.note);
        	$(".chart-explain").dotdot();
        }
        
    });
});