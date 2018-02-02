$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/personale/grafici/assenze-docenti', function(data) {
        var options = {
            chart: {
                type: "column"
            },

            title: {
                text: ""
            },

            xAxis: {
                categories: ["Malattia", "Maternita'", "Altro"]
            },

            yAxis: {
                title: {
                    //text: "Giorni di assenza"
                	text: ""
                },
                labels: {
                    enabled: false
                }
            },

            tooltip: {
                formatter: function () {
                    return default_tooltip(this.series.name, this.y, "");
                }
            },

            plotOptions: {
                series: {
                    dataLabels: {
                        format: "{y}"
                    }
                }
            },

            series: [{}]
        };

        options.series = data.series;
        options.title.text = data.title;

        
        
        if (data.series.length == 0){
        	$('#chart-assenze-docenti-title').append(data.title);
        	$('#sc-bar-chart-assenze-docenti').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-bar-chart-assenze-docenti').highcharts(options);
        	$('#chart-assenze-docenti-approfondisci').append(data.info);
        	$('#chart-assenze-docenti-note').append(data.note);
        	$(".chart-explain").dotdot();
        }
        
    });
});