$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/alunni/grafici/immatricolati-universita-area-didattica', function(data) {
        var options = {
            chart: {
                type: "bar"
            },

            title: {
                text: ""
            },

            xAxis: {
                //categories: ["CHIMICO-FARMACEUTICA", "GEO-BIOLOGICA", "LETTERARIA", "MEDICA", "POLITICO-SOCIALE", "PSICOLOGICA"]
            	categories: data.categories
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
                formatter: function () {
                    return default_tooltip(this.x, this.y);
                }
            },

            legend: {
                enabled: false
            },

            series: [{}]
        };

        options.series = data.series;
        options.title.text = data.title;

        
        
        if (data.series.length == 0){
        	$('#chart-immatricolazioni-universita-area-didattica-title').append(data.title);
        	$('#sc-bar-chart-immatricolazioni-universita-area-didattica').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-bar-chart-immatricolazioni-universita-area-didattica').highcharts(options);
        	$('#chart-immatricolazioni-universita-area-didattica-approfondisci').append(data.info);
        	$('#chart-immatricolazioni-universita-area-didattica-note').append(data.note);
        	$(".chart-explain").dotdot();
        }
    });
});