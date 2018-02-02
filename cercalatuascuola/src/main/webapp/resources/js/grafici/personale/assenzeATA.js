$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/personale/grafici/assenze-ata', function(data) {
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
        	$('#chart-assenze-ata-title').append(data.title);
        	$('#sc-bar-chart-assenze-ata').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-bar-chart-assenze-ata').highcharts(options);
        	$('#chart-assenze-ata-approfondisci').append(data.info);
        	$('#chart-assenze-ata-note').append(data.note);
        	$(".chart-explain").dotdot();
        }
        
    });
});