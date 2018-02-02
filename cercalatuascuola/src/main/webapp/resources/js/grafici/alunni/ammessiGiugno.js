$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/alunni/grafici/ammessi-giugno', function(data) {
        var options = {
            chart: {
                type: "bar"
            },

            title: {
                text: ""
            },
            legend: {
            	reversed: true
            },
            xAxis: {
                title: {
                    text: "Anno di corso"
                },
                categories: ["1°", "2°", "3°", "4°", "5°"]
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
        	$('#chart-ammessi-giugno-title').append(data.title);
        	$('#sc-bar-chart-ammessi-giugno').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-bar-chart-ammessi-giugno').highcharts(options);
        	$('#chart-ammessi-giugno-note').append(data.note);
        	$('#chart-ammessi-giugno-approfondisci').append(data.info);
        	$(".chart-explain").dotdot();
        }
    });
});