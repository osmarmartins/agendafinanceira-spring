$(function() {
	
	var tabelaSetores = $('#tabela-setores');
	var modalSetor = $('#cadastro-modal-setor');
	var btnSalvar = modalSetor.find('#salvar-setor');
	var btnAtivarDesativar = tabelaSetores.find('#ativar-desativar')
	var form = modalSetor.find('form');
	var descricaoSetor = $('#setor-descricao');
	var mensagem = $('#mensagem');
	var url = form.attr('action');
	
	btnAtivarDesativar.on('click', onAtivarDesativarClick);
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
	
	function onAtivarDesativarClick(){
		var status = 'ATIVO';
		
		if (btnAtivarDesativar.hasClass("glyphicon-remove")){
			status = 'INATIVO';
		}
	
	}
	

	function onSalvarClick(){
		var descricao = descricaoSetor.val().trim();
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


