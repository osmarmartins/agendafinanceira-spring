	var textStatus = $('#text-status');
	var toggleStatus = $('#toggle-status');
	
	// Novos registros serão inicializados como ATIVO
	if (textStatus.val()==''){
		textStatus.val('ATIVO');
	}
	
	// Inicializa o toggle com o valor inicial na aplicação (back-end)
	toggleStatus.bootstrapToggle((textStatus.val()=='ATIVO')? 'on' : 'off');
	
	// Alterar valor  
	toggleStatus.change(function() {
		
		console.log('Status: ' + toggleStatus.prop('checked'));
		if (toggleStatus.prop('checked')){
			textStatus.val('ATIVO');
		}else{
			textStatus.val('INATIVO');
		}
		
	})
	      