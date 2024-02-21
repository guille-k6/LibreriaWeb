document.addEventListener('DOMContentLoaded', ()=>{
	const mensajes = document.querySelectorAll('.mensajeInfo');
	
	console.log(mensajes);
	
	let innerHtmlNode = `<div class="d-flex"><i class="small material-icons home-icon">info</i> &ensp;`;
	
	if(mensajes.length > 0){
		innerHtmlNode += mensajes[0].innerHTML + `</div>`;
		let newNode = document.createElement("div");
		newNode.innerHTML = innerHtmlNode;
		Toastify({
		text: mensajes[0].innerHTML,
		duration: 3500,
		node: newNode,
		style: {
			padding: "0.5rem 1.5rem",
			border: "1px solid #f3f3f3",
			borderRadius: "4px",
			background: "#FF8C00",
			color: "white"
		},
		offset: {
		    x: 10,
		    y: 60
		  }
		}).showToast();
		
        mensajes.forEach(mensaje => {
            mensaje.remove();
        });
	}
});