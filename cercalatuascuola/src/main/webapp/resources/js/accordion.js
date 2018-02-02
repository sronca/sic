jQuery(function() {
	
	jQuery('#accordion .sc-address').on('click',function() {
		jQuery('.sc-ediliziasubmenu').slideUp('fast');
		jQuery(this).next('.sc-ediliziasubmenu').slideDown('fast');
	});
	
	jQuery('#accordion .sc-list-accordion-text').hide();
	
    jQuery("#accordion .sc-list-accordion-title").click(function () {
		jQuery(this).toggleClass("current").next(".sc-list-accordion-text").slideToggle("fast");
		jQuery(this).parent().siblings().find(".sc-list-accordion-title.current").removeClass("current").next(".sc-list-accordion-text").slideUp("fast");
    });
	
	// inserito per accordion menù semplice //
    jQuery("#simple-accordion .sc-simple-accordion-item").click(function () {
		jQuery(this).toggleClass("sc-accordion-openitem");
		jQuery(this).toggleClass("current").next(".sc-simple-accordion-submenu").slideToggle("fast");
		jQuery(this).parent().siblings().find(".sc-simple-accordion-item.current").removeClass("current").next(".sc-simple-accordion-submenu").slideUp("fast");
    });	
	

});