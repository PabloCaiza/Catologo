/**
 * 
 */


let img = document.getElementById("formDetalle:productImage");
const result= document.getElementById("result");
const resultParent= result.parentNode;
const pnlProducto = document.getElementById("formDetalle:pnlProduct");
const pnlProductoParent = pnlProducto.parentNode;
let lens;


resultParent.style.display= 'none';
result.style.display= 'none';
pnlProducto.style.display= 'block';
pnlProductoParent.style.display="block";



function imageZoom() {

	lens = document.createElement("DIV");
	lens.classList.add("img-zoom-lens");
	img.parentElement.insertBefore(lens, img);
	resultParent.style.display= 'block';
result.style.display= 'block';
	console.log(result.offsetWidth ,result.offsetHeight)
	let cx = result.offsetWidth/lens.offsetWidth;
	let cy = result.offsetHeight/lens.offsetHeight;
		resultParent.style.display= 'none';
result.style.display= 'none';
	
	result.style.background= "url('" + img.src + "')";
	result.style.backgroundSize = (img.width * cx) + "px " + (img.height * cy) + "px";
	
	lens.addEventListener("mousemove", moveLens);
	img.addEventListener("mousemove", moveLens);

	lens.addEventListener("touchmove", moveLens);
	img.addEventListener("touchmove", moveLens);

	img.addEventListener("mouseout", (ev  => {

		result.style.display = 'none';
		resultParent.style.display='none';
		pnlProductoParent.style.display='block';
		pnlProductoParent.style.display = 'block';


	}));



	function moveLens(e) {
		e.preventDefault();
		result.style.display='block';
		resultParent.style.display= 'block';
		pnlProductoParent.style.display = 'none';
		let pos, x, y;


		pos = getCursorPos(e);
		x = pos.x - (lens.offsetWidth / 2);
		y = pos.y - (lens.offsetHeight / 2);
		if (x > img.width - lens.offsetWidth) { x = img.width - lens.offsetWidth; }
		if (x < 0) { x = 0; }
		if (y > img.height - lens.offsetHeight) { y = img.height - lens.offsetHeight; }
		if (y < 0) { y = 0; }
		/* Set the position of the lens: */
		lens.style.left = x + "px";
		lens.style.top = y + "px";
		/* Display what the lens "sees": */
		result.style.backgroundPosition = "-" + (x * cx) + "px -" + (y * cy) + "px";


	}

	function getCursorPos(e) {
		let a, x = 0, y = 0;
		e = e || window.event;
		a = img.getBoundingClientRect();
		x = e.pageX - a.left;
		y = e.pageY - a.top;

		x = x - window.pageXOffset;
		y = y - window.pageYOffset;
		console.log(x );
		console.log(y);

		return { x: x, y: y }
	}





}
imageZoom();

