document.addEventListener('DOMContentLoaded', function () {
        launch_toast();
    });

function launch_toast() {
    var x = document.getElementById("toast")
    if(x){
	    x.className = "show";
    	setTimeout(function(){ x.className = x.className.replace("show", ""); }, 5000);	
	}
}