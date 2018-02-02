'use strict';

// Declare app level module which depends on filters, and services
angular
		.module('ptof.controllers.convalida.cruscottoConvalidaFabbisogno',
				[ 'ptof.commons.services', 'ptof.directives', 'base64' ])
		.controller(
				'CruscottoConvalidaFabbisognoCtrl',
				function($scope, $rootScope, $window, $timeout, $location,
						$base64, ptofCommonsServicesFactory,
						gestioneConvalidaFabbisognoServicesFactory) {
					console.log('CruscottoConvalidaFabbisognoCtrl->');

					$scope.vm = {
						progressivoGestioneCatalogoDocumento : $location
								.search().progresivoGestioneCatalogoDocumento,
						attribForm : {
							regioniL : []
						},
						form : {},
						tabella : []
					}

					// 1)inizializzo il form
					var init = function() {
						console
								.log('Servizio -> Fabbisogno scuola posti comuni');

						var form = {
							progressivoGestioneCatalogoDocumento : $scope.vm.form.progressivoGestioneCatalogoDocumento
						};
						gestioneConvalidaFabbisognoServicesFactory
								.getFabbisognoScuolaPostiComuni(form)
								.success(
										function(data, status, config, headers) {
											$scope.vm.form.regione = data.attribForm.regione;
											$scope.vm.form.cruscottoFabbisogniPostiComuniDTO = data.attribForm.cruscottoFabbisogniPostiComuniDTO;
											$scope.vm.form.cruscottoFabbisogniPostiSostegnoDTO = data.attribForm.cruscottoFabbisogniPostiSostegnoDTO;
											$scope.vm.form.cruscottoFabbisogniPostiDiPotenziamentoDTO = data.attribForm.cruscottoFabbisogniPostiDiPotenziamentoDTO;
											console.log(data)
										})
					}

					this.convalidaSingola = function() {
						console.log('CruscottoConvalidaFabbisognoCtrl->convalidaSingola');
						console.log('---loadConvalidaSingola--');
						$window.location.href = APPGLOBALCONSTANT.contexPath+'/fn-cruscotto-convalida-singole-fabbisogno.do?progresivoGestioneCatalogoDocumento='+ $scope.vm.form.progressivoGestioneCatalogoDocumento;	
					}

					this.convalidaMassiva = function() {
						console
								.log('CruscottoConvalidaFabbisognoCtrl->convalidaMassiva');
						var form = {
							progressivoGestioneCatalogoDocumento : $scope.vm.form.progressivoGestioneCatalogoDocumento
						};
						var successRunFn = function() {
							gestioneConvalidaFabbisognoServicesFactory
									.convalidaMassiva(form).success(
											function(data, status, config,
													headers) {
												console.log(data);
											});
						}
						var sms = {
							title : "ATTENZIONE",
							text : "Si sta procedendo alla convalida massiva si vuole proseguire?",
							type : "CONFERMA",
							successRunFn : successRunFn
						};
						$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
					}

					this.rettificaMassiva = function() {
						console
								.log('CruscottoConvalidaFabbisognoCtrl->rettificaMassiva()');
						var form = {
							progressivoGestioneCatalogoDocumento : $scope.vm.form.progressivoGestioneCatalogoDocumento
						};
						var successRunFn = function() {
							gestioneConvalidaFabbisognoServicesFactory
									.rettificaMassiva(form).success(
											function(data, status, config,
													headers) {
												console.log(data);
											});
						}
						var sms = {
							title : "ATTENZIONE",
							text : "Si sta procedendo alla rettifica massiva si vuole proseguire?",
							type : "CONFERMA",
							successRunFn : successRunFn
						};
						$rootScope.$broadcast("EVENT_SENDMESSAGE", sms);
					}

					this.indietro = function() {
						window.history.back();
					}

					// 1)inizializzo il form
					$timeout(function() {
						init();
					});
				})