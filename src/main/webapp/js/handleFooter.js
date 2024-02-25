document.addEventListener("DOMContentLoaded", function(){
	console.log("hola")
	var viewportHeight = window.innerHeight;
	const bodyHeight = document.querySelector('body').offsetHeight;
	console.log(viewportHeight);
	console.log(bodyHeight);
	
	if(bodyHeight<viewportHeight) {
		console.log("houston tenemos un porblema");
	}
});
