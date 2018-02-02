$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/alunni/grafici/distribuzione-votazioni-esame', function(data) {
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
                formatter: function () {
                    return default_tooltip("Voto " +this.point.name, this.y);
                }
            },

            plotOptions: {
                series: {
                    dataLabels: {
                        format: "{y}"
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
        	$('#chart-distribuzione-votazioni-esame-title').append(data.title);
        	$('#sc-pie-chart-distribuzione-votazioni-esame').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-pie-chart-distribuzione-votazioni-esame').highcharts(options);
        	$('#chart-distribuzione-votazioni-esame-approfondisci').append(data.info);
        	$('#chart-distribuzione-votazioni-esame-note').append(data.note);
        	$(".chart-explain").dotdot();
        }
    });
});