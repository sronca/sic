$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/alunni/grafici/rav-24c1', function(data) {
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
        	$('#chart-rav24c1-title').append(data.title);
        	$('#sc-pie-chart-rav24c1').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-pie-chart-rav24c1').highcharts(options);
        	$('#chart-rav24c1-approfondisci').append(data.info);
        	$('#chart-rav24c1-note').append(data.note);
        	$(".chart-explain").dotdot();
        }
    });
});