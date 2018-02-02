$(function () {

	/**
	* opzioni comuni impostate come parametri
	**/
	common_opt = {
		"marginBottom": 40,
		"spacingBottom": 10,
		"titleMarginX": 14,
		"titleMarginY": 2,
		"chartTitleSize": "18px",
		"pieLabelSize": "14px",
		"chartTitleColor": "#333333",
		"chartTitleMargin": 20,
		"halfPieAngle": 90,
		"halfPieCenterX":"50%",
		"halfPieCenterY":"50%",
		"legendY": 28
	};

	/**
	* aggiungo controllo per sovrascrivere alcuni parametri in modalità mobile
	**/
	if ($(window).width() <= 768) {

		common_opt.marginBottom = 120;
		common_opt.legendY = 20;
		common_opt.pieLabelSize = "8px"
	}

	/**
	* formattazione tooltip default
	**/
	default_tooltip = function(key, val, prefix, suffix) {

		prefix = (typeof prefix === "undefined") ? "%" : prefix;
		suffix = (typeof suffix === "undefined") ? "%" : suffix;

		return key+ " : <strong>" +prefix+ " " +val+ " " +suffix+ "</strong>";
	}

	/**
	* settings comuni a tutti i grafici
	**/
	Highcharts.setOptions({

		// i18n per il download. lasciare vuoto pdf e png
		lang: {
			downloadJPEG: "Download in formato JPEG",
			downloadPDF: "Download in formato PDF",
			downloadPNG: "Download in formato PNG",
			downloadSVG: "Download in formato SVG",
			printChart: "Stampa il grafico",
			thousandsSep: ".",
			decimalPoint: ","
		},

		// colori di base
		colors:
		[
			"#69c3c4", "#d7ac80", "#3297bf", "#104d4f"
		],

		// grafico generale
		chart: {
			marginBottom: common_opt.marginBottom,
			spacingBottom: common_opt.spacingBottom,
			plotShadow: false,
			style: {
				fontFamily: 'ubuntulight, Verdana, Helvetica, sans-serif',
				fontSize: '14px'
			}
		},

		// asse ordinate
		xAxis: {
			title: {
				margin: common_opt.titleMarginX
			}
		},

		// asse ascisse. sono stati levati i valori
		yAxis: {
			title: {
				margin: common_opt.titleMarginY
			},
			labels: {
				enabled: true
			},
			allowDecimals: false,
			min: 0,
			max: null,
			minRange: 10
		},

		// abilito i tooltip di default
		tooltip: {
			enabled: true,
		},

		// titolo del grafico (se va sopra eliminare verticalAlign)
		title: {
			text: "",
			//~ verticalAlign: "top",
			margin: common_opt.chartTitleMargin,
			style: {
				fontSize: common_opt.chartTitleSize,
				fontWeight: "bold",
				color: common_opt.chartTitleColor
			}
		},

		// legenda da mettere in basso
		legend: {
			enabled: false,
			//~ verticalAlign: "bottom",
			borderWidth: 1,
			backgroundColor: "#f0f0f0",
			y: common_opt.legendY,
			shadow: false,
			labelFormatter: function() {
				return this.name;
			}
		},

		// mostra il valore del grafico in testa all'istogramma oppure la label nelle torte.
		// il formato di default è: [valore] [percentuale]
		plotOptions: {
			series: {
				pointPadding: 0.15,
				groupPadding: 0.1,
				dataLabels: {
					enabled: true,
					allowOverlap: true,
					zIndex: 0,
					format: "{y}%",
					style: {
                        textShadow: "0 0 3px white"
                    },
				}
			},
			pie: {
				allowPointSelect: true,
				cursor: "pointer",
				showInLegend: true,
				dataLabels: {
					style: {
                        fontSize: common_opt.pieLabelSize
                    }
				}
			}
		},

		// disabilita credits. licenza acquistata
		credits: {
			enabled: false
		}
	});
});