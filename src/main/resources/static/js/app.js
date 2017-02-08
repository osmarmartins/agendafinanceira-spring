$(function() {

	var btnExcluir = $('.js-btn-excluir');
	var btnAtivarDesativar = $('.js-btn-ativar-desativar');
	var btnExcluirClicado;
	var url;
	var descricao;
	
	btnExcluir.on('click', onExcluir);
	btnAtivarDesativar.on('click', onAtivarDesativar);
	
	
	// Excluir 
	function onExcluir(event){
		btnExcluirClicado = $(event.currentTarget);
		url = btnExcluirClicado.data('url');
		descricao = btnExcluirClicado.data('descricao');
		
		swal({
			title: 'Confirma exclusão?',
			text: 'Excluir "' + descricao + '" ?',
			type: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#DD6B55',
			confirmButtonText: 'Sim, exclua agora!',
			closeOnConfirm: false,
			cancelButtonText: "Cancelar exclusão.",
			closeOnCancel: true
		}, onExcluirConfirmado.bind(event));		
		
		
	}
	
	function onExcluirConfirmado(event){
		
		$.ajax({
			url: url,
			method: 'DELETE',
			success: onSucesso,
			error: onErro
		});
		
	}

	function onSucesso(event){
		swal({
			title: 'Pronto!',
			text: 'Operação realizada com sucesso.',
			type: 'success'
		}, onSucessoRecarregar);			
	}
	
	function onSucessoRecarregar(event){
		window.location.reload();
	}

	function onErro(event){
		swal('Oops!', event.responseText, 'error');
		
	}
	
	
	
	// Ativar-Desativar
	function onAtivarDesativar(event){
		btnAtivarDesativarClicado = $(event.currentTarget);
		url = btnAtivarDesativarClicado.data('url');
		descricao = btnAtivarDesativarClicado.data('descricao');
		var operacao = btnAtivarDesativarClicado.data('status')=='ATIVO'? 'Desativar: ' : 'Ativar: '; 
		
		swal({
			title: 'Confirma operação?',
			text: operacao + descricao,
			type: 'info',
			showCancelButton: true,
			confirmButtonText: 'Sim',
			closeOnConfirm: false,
			cancelButtonText: "Não",
			closeOnCancel: true
		}, onAtivarDestivarConfirmado);		
		
	}
	
	
	function onAtivarDestivarConfirmado(event){
		
		console.log('Ativar/Desativar - '+status+' - '+descricao+' - '+url);
		
		$.ajax({
			url: url,
			method: 'PUT',
			success: onSucesso,
			error: onErro
		});		

	}
	
});
