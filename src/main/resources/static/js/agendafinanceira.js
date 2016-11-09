$(function() {
	var decimal = $('.js-decimal');
	decimal.maskMoney();
	
	var plain = $('.js-plain');
	plain.maskMoney({ precision: 0});
			
});

$("[name='my-checkbox']").bootstrapSwitch();
