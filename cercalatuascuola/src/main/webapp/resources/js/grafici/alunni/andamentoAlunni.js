$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/alunni/grafici/andamento-alunni', function(data) {
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
                    //text: "Numero alunni"
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
        	$('#chart-andamento-alunni-title').append(data.title);
        	$('#sc-bar-chart-andamento-alunni').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-bar-chart-andamento-alunni').highcharts(options);
        	$('#chart-andamento-alunni-note').append(data.note);
        	$('#chart-andamento-alunni-approfondisci').append(data.info);
        	$(".chart-explain").dotdot();
        }
    });
});
