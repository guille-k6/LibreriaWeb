const mensajes = document.querySelectorAll('.mensajeInfo');

document.addEventListener('DOMContentLoaded', ()=>{
	
	setTimeout(function(){
	    borrarMensajes();
	}, 3000);
});

function borrarMensajes(){
	mensajes.forEach(e => e.remove());
}