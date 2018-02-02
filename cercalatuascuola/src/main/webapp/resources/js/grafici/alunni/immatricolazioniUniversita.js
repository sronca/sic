$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/alunni/grafici/immatricolati-universita', function(data) {
        var options = {
            chart: {
                type: "pie"
            },

            title: {
                text: ""
            },

            tooltip: {
                formatter: function () {
                    return default_tooltip(this.point.name, this.y);
                }
            },

            plotOptions: {
                series: {
                    dataLabels: {
                        //format: "{point.name}: {y}%"
                    	format: "{y}"
                    }
                }
            },

            series: [{
                data: [{}]
            }]
        };

        options.series[0].data = data.series;
        options.title.text = data.title;

        
        
        if (data.series.length == 0){
        	$('#chart-immatricolazioni-universita-title').append(data.title);
        	$('#sc-pie-chart-immatricolazioni-universita').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-pie-chart-immatricolazioni-universita').highcharts(options);
        	$('#chart-immatricolazioni-universita-approfondisci').append(data.info);
        	$('#chart-immatricolazioni-universita-note').append(data.note);
        	$(".chart-explain").dotdot();
        }
    });
});