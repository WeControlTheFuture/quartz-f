/*
 * jQuery validation Penn mutual additions
 *
 * Copyright (c) 2008 Matthew Payne
 */
	jQuery.validator.addMethod("notEqualTo", function(value, element, param) {
		return $(param).val()?(value != $(param).val()):(value != param);
	}, "error");	