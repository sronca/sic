$(function () {

	/**
	* opzioni comuni impostate come parametri
	**/
	common_opt = {
		"marginBottom": 100,
		"spacingBottom": 32,
		"titleMarginX": 14,
		"titleMarginY": 2,
		"chartTitleSize": "13px",
		"chartTitleColor": "#333333",
		"chartTitleMargin": 30,
		"halfPieAngle": 90,
		"halfPieCenterX":"50%",
		"halfPieCenterY":"50%",
		"legendY": 28
	};

	/**
	* aggiungo controllo per sovrascrivere alcuni parametri in modalità mobile
	**/
	if ($(window).width() < 768) {

		common_opt.marginBottom = 120;
		common_opt.legendY = 20;
	}

	/**
	* formattazione tooltip default
	**/
	default_tooltip = function(key, val, suffix) {

		suffix = (typeof suffix === "undefined") ? "" : suffix;

		return key+ ": <strong>" +val + suffix+ "</strong>";
	}

	/**
	* settings comuni a tutti i grafici
	**/
	Highcharts.setOptions({

		// i18n per il download. lasciare vuoto pdf e png
		lang: {
			downloadJPEG: "Download in formato JPEG",
			downloadPDF: "",
			downloadPNG: "",
			downloadSVG: "Download in formato SVG",
			printChart: "Stampa il grafico"
		},

		// colori di base
		colors:
		[
			"#5EC5ED","#E64427","#FFC719","#53CE4D","#D6D6D6","#E6CE93","#818991","#DE7AFF","#3D9806","#DCDB6D"
		],

		// grafico generale
		chart: {
			marginBottom: common_opt.marginBottom,
			spacingBottom: common_opt.spacingBottom,
			plotShadow: false,
			style: {
				fontFamily: '"Open Sans", Verdana, Helvetica, sans-serif',
				fontSize: '13px'
			}
		},

		// asse ascisse
		xAxis: {
			title: {
				margin: common_opt.titleMarginX
			}
		},

		// asse ordinate. sono stati levati i valori
		yAxis: {
			title: {
				margin: common_opt.titleMarginY
			},
			labels: {
				enabled: true
			},
			allowDecimals: false,
			min: 0,
			minRange: 1
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
					format: "{y}",
					style: {
                        textShadow: '0 0 3px white',
                    },
				}
			},
			pie: {
				allowPointSelect: true,
				cursor: "pointer",
				showInLegend: true
			}
		},

		// disabilita credits. licenza acquistata
		credits: {
			enabled: false
		}
	});
});
