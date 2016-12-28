$(function() {
	
	var modalConta = $('#cadastro-modal-conta');
	var btnSalvar = modalConta.find('#salvar-conta');
	var form = modalConta.find('form');
	var descricaoConta = $('#conta-descricao');
	var mensagem = $('#mensagem');
	var url = form.attr('action');
	
	form.on('submit', function(event) { event.preventDefault() });
	modalConta.on('shown.bs.modal', onModalShow);
	modalConta.on('hide.bs.modal', onModalClose);
	btnSalvar.on('click', onSalvarClick);

	function onModalShow(){
		descricaoConta.focus();
	}
	
	function onModalClose(){
		descricaoConta.val('');
		mensagem.addClass('hidden');
		form.find('.form-group').removeClass('has-error');
	}
	

	function onSalvarClick(){
		var descricao = descricaoConta.val().trim();
		$.ajax({
			url: url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ descricao: descricao, ativo: 'ATIVO' }),
			error: onErrorModal,
			success: onSuccessModal
			
		});
		
	}
	
	
	function onErrorModal(obj){
		var mensagemErro = obj.responseText;
		mensagem.html('<span>' + mensagemErro + '</span>');
		mensagem.removeClass('hidden');
		form.find('.form-group').addClass('has-error');
	}
	
	function onSuccessModal(setorModal){
		window.location.reload(true);
		modalSetor.modal('hide');
	}
	
	
});


