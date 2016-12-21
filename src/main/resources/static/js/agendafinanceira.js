// Criação do Objeto (caso não exista, será criado um novo)
var AgendaFinanceira = AgendaFinanceira || {}

// Declaração da função (Equivalente a declaração da classe)
AgendaFinanceira.MaskMoney = (function(){
	
	// Construtor
	function MaskMoney(){
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
	}
	
	// Métodos 
	MaskMoney.prototype.enable = function(){
		this.decimal.maskMoney({decimal: ',', thousands: '.'});
		this.plain.maskMoney({precision: 0, thousands: '.'});
	}
	
}());


// Instância objeto maskMoney
$(function() {
	var maskMoney = new AgendaFinanceira.MaskMoney();
	maskMoney.enable();
});
