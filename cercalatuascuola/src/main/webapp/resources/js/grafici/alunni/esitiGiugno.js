$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/alunni/grafici/esiti-giugno', function(data) {
        var options = {
            chart: {
                type: "pie"
            },

            title: {
                text: ""
            },

            tooltip: {
                formatter: function () {
                    return 	this.point.name+ ": <strong>" +this.y+ "</strong>";
                }
            },

            plotOptions: {
                series: {
                    dataLabels: {
                        //format: "<strong>{point.name}</strong>: {y}%"
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
        	$('#chart-esiti-giugno-title').append(data.title);
        	$('#sc-pie-chart-esiti-giugno').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-pie-chart-esiti-giugno').highcharts(options);
        	$('#chart-esiti-giugno-note').append(data.note);
        	$('#chart-esiti-giugno-approfondisci').append(data.info);
        	$(".chart-explain").dotdot();
        }
    });
});