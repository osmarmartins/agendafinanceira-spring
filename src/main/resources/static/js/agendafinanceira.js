$(function() {
	
	var modalSetor = $('#cadastro-modal-setor');
	var btnSalvar = modalSetor.find('#salvar-setor');
	var form = modalSetor.find('form');
	var descricaoSetor = $('#setor-descricao');
	var mensagem = $('#mensagem');
	var url = form.attr('action');
	
	form.on('submit', function(event) { event.preventDefault() });
	modalSetor.on('shown.bs.modal', onModalShow);
	modalSetor.on('hide.bs.modal', onModalClose);
	btnSalvar.on('click', onSalvarClick);

	function onModalShow(){
		descricaoSetor.focus();
	}
	
	function onModalClose(){
		descricaoSetor.val('');
		mensagem.addClass('hidden');
		form.find('.form-group').removeClass('has-error');
	}
	
	function onSalvarClick(){
		console.log(url);
		$.ajax({
			url: url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ descricao: descricaoSetor }),
			error: onErrorModal,
			success: onSuccessModal
			
		})
	}
	
	function onErrorModal(obj){
		var mensagemErro = obj.responseText;
		mensagem.removeClass('hidden');
		mensagem.html('<span>' + mensagemErro + '</span>');
		form.find('.form-group').addClass('has-error');
	}
	
	function onSuccessModal(setorModal){
		modalSetor.modal('hide');
	}
	
	
})






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
//	var maskMoney = new AgendaFinanceira.MaskMoney();
//	maskMoney.enable();
});

