$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/alunni/grafici/trasferimenti', function(data) {
        var options = {
            chart: {
                type: "column"
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
                    //text: "Percentuale studenti"
                	text: ""
                },
                labels: {
                    enabled: false
                }
            },

            tooltip: {
                formatter: function() {
                    return default_tooltip(this.series.name, this.y, "");
                }
            },

            series: [{}]
        };

        options.series = data.series;
        options.title.text = data.title;
        
        
        
        if (data.series.length == 0){
        	$('#chart-trasferimenti-title').append(data.title);
        	$('#sc-bar-chart-trasferimenti').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-bar-chart-trasferimenti').highcharts(options);
        	$('#chart-trasferimenti-approfondisci').append(data.info);
        	$('#chart-trasferimenti-note').append(data.note);
        	$(".chart-explain").dotdot();
        }
        
        
    });
});