$(function() {

	var btnExcluir = $('.js-btn-excluir');
	var btnAtivarDesativar = $('.js-btn-ativar-desativar');
	var btnExcluirClicado;
	var btnAtivarDesativarClicado;
	var url;
	var descricao;
	var operacao
	
	$('[data-toggle="tooltip"]').tooltip()

	btnExcluir.on('click', onExcluir);
	btnAtivarDesativar.on('click', onAtivarDesativar);

	// Excluir
	function onExcluir(event) {
		btnExcluirClicado = $(event.currentTarget);
		url = btnExcluirClicado.data('url');
		descricao = btnExcluirClicado.data('descricao');

		swal({
			title : 'Confirma exclusão?',
			text : 'Excluir "' + descricao + '" ?',
			type : 'warning',
			showCancelButton : true,
			confirmButtonColor : '#DD6B55',
			confirmButtonText : 'Sim, exclua agora!',
			closeOnConfirm : false,
			cancelButtonText : "Cancelar exclusão.",
			closeOnCancel : true
		}, onExcluirConfirmado.bind(event));

	}

	function onExcluirConfirmado(event) {
		$.ajax({
			url : url,
			method : 'DELETE',
			success : onSucesso({ reload : true }),
			error : onErro
		});

	}

	function onSucesso(event) {
		var onResultado = (event.reload ? onSucessoExcluir : onSucessoAtivarDesativar);

		swal({
			title : 'Pronto!',
			text : 'Operação realizada com sucesso.',
			type : 'success'
		}, onResultado);
	}

	function onSucessoExcluir(event) {
		window.location.reload();
	}

	function onSucessoAtivarDesativar(event) {
		var iconStatus = btnAtivarDesativarClicado.find(".glyphicon");

		iconStatus.remove();
		
		if (btnAtivarDesativarClicado.data('status') === 'ATIVO'){
			btnAtivarDesativarClicado.data('status', 'INATIVO');
			btnAtivarDesativarClicado.append('<i class="glyphicon  glyphicon-remove  text-danger"></i>');
		} else {
			btnAtivarDesativarClicado.data('status', 'ATIVO');
			btnAtivarDesativarClicado.append('<i class="glyphicon  glyphicon-ok  text-success"></i>');
		}
	}

	function onErro(event) {
		swal('Oops!', event.responseText, 'error');

	}

	// Ativar-Desativar
	function onAtivarDesativar(event) {
		btnAtivarDesativarClicado = $(event.currentTarget);
		url = btnAtivarDesativarClicado.data('url');
		descricao = btnAtivarDesativarClicado.data('descricao');
		operacao = btnAtivarDesativarClicado.data('status') === 'ATIVO' ? 'Desativar: ' : 'Ativar: ';

		swal({
			title : 'Confirma operação?',
			text : operacao + descricao,
			type : 'info',
			showCancelButton : true,
			confirmButtonText : 'Sim',
			closeOnConfirm : false,
			cancelButtonText : "Não",
			closeOnCancel : true
		}, onAtivarDestivarConfirmado);

	}

	function onAtivarDestivarConfirmado(event) {
		$.ajax({
			url : url,
			method : 'PUT',
			success : onSucesso({ reload : false }),
			error : onErro
		});

	}

});
