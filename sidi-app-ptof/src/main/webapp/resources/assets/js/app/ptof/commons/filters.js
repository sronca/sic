'use strict';

/* Filters */

angular.module('ptof.filters', []).filter('interpolate',
	[ 'version', function(version) {
	    return function(text) {
		return String(text).replace(/\%VERSION\%/mg, version);
	    }
	} ])
.filter('addGiorniToDate', function() {
    return function(d1, giorni) {

	if ($.isNumeric(giorni)) {

	    var day = moment(d1, "DD/MM/YYYY");
	    if (day.isValid()) {
		day.add(giorni, 'days');
		return day.format("DD/MM/YYYY");
	    }
	}

	return "";
    }
})
.filter('objectToString', function() {
    return function(opts) {
    	var o = opts.o, s = opts.s;
        s = s.replace(/\[(\w+)\]/g, '.$1'); // convert indexes to properties
        s = s.replace(/^\./, '');           // strip a leading dot
        var a = s.split('.');
        for (var i = 0, n = a.length; i < n; ++i) {
            var k = a[i];
            if (o && k in o) {
                o = o[k];
            } else {
                return;
            }
        }
        return o;
    	}
 })
 .filter('replaceParamsMessage', function() {
    return function(message, params) {
    	for (var i = 1; i <= params.length; ++i) {
            message = message.replace("{"+i+"}",params[i-1]);
        }
		
		return message;
    }
 })
.filter('trunkString', function() {
    return function(opt) {
        var v = opt ;
    	if ( v &&  v.length > 80 ) {
        	return v.substring(0,80)+'...' ;
        }          
    	return v;
}})
 .filter('trunkString512', function() {
    return function(opt) {
        var v = opt ;
    	if (  v && v.length > 512 ) {
        	return v.substring(0,512)+'...' ;
        }          
    	return v;
}})
.filter('replace', function() {
    return function(opt) {
        var v = opt.v, c =opt.c , s = opt.s ;
    	return v.replace(c,s);
}});