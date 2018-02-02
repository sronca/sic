$(document).ready(function() {
    $.getJSON('/cercalatuascuola/istituti/' + $("body").attr("data-codscuut") + '/' + $("body").attr("data-desnomscu") + '/alunni/grafici/rav-24c3?i=I', function(data) {
        var options = {
                chart: {
                    type: "column"
                },
                title: {
                    text: ""
                },
                xAxis: {
                    title: {
                        text: ""
                    },
                    categories: ["SCUOLA", "REGIONE", "ITALIA"]
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
        	$('#chart-rav24c3-title').append(data.title);
        	$('#sc-bar-chart-rav24c3').html('<h3 class="sc-no-graph">Dati non disponibili per la scuola</h3>');
        }else{
        	$('#sc-bar-chart-rav24c3').highcharts(options);
        	$('#chart-rav24c3-note').append(data.note);
        	$('#chart-rav24c3-approfondisci').append(data.info);
        	$(".chart-explain").dotdot();
        }
    });
});