$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/alunni/grafici/esiti-giugno-settembre', function(data) {
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
                        //format: "{point.name}: {y}%",
                        //distance: 10
                    	format: "{y}",
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
        	$('#chart-esiti-giugno-settembre-title').append(data.title);
        	$('#sc-pie-chart-esiti-giugno-settembre').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-pie-chart-esiti-giugno-settembre').highcharts(options);
        	$('#chart-esiti-giugno-settembre-approfondisci').append(data.info);
        	$('#chart-esiti-giugno-settembre-note').append(data.note);
        	$(".chart-explain").dotdot();
        }
    });
});