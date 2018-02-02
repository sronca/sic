$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/finanza/grafici/entrate-fonti-finanziamento', function(data) {
        var options = {
            chart: {
                type: "pie",
                marginBottom: -common_opt.marginBottom,
                marginTop: common_opt.marginBottom
            },

            title: {
                text: ""
            },

            tooltip: {
                formatter: function() {
                    return default_tooltip(this.point.name, this.y);
                }
            },

            plotOptions: {
                series: {
                    dataLabels: {
                        distance: 10,
                        format: "{point.name}: {y}"
                    }
                },
                pie: {
                    startAngle: -common_opt.halfPieAngle,
                    endAngle: common_opt.halfPieAngle,
                    center: [common_opt.halfPieCenterX, common_opt.halfPieCenterY]
                }
            },

            series: [{
                innerSize: "55%",
                data: []
            }]
        };

        options.series[0].data = data.series;
        options.title.text = data.title;

        
        
        if (data.series.length == 0){
        	$('#chart-entrate-fonti-finanziamento-title').append(data.title);
        	$('#sc-pie-chart-entrate-fonti-finanziamento').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-pie-chart-entrate-fonti-finanziamento').highcharts(options);
        	$('#chart-entrate-fonti-finanziamento-approfondisci').append(data.info);
        	$('#chart-entrate-fonti-finanziamento-note').append(data.note);
        	$(".chart-explain").dotdot();
        }
        
    });
});