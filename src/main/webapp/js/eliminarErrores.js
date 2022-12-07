const mensajes = document.querySelectorAll('.errorMensaje');

document.addEventListener('DOMContentLoaded', ()=>{
	
	setTimeout(function(){
	    borrarErrores();
	}, 5000);
});

function borrarErrores(){
	mensajes.forEach(e => e.remove());
}