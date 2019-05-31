$(function(){

	$("#spannom").hide();
	$("#spancorreo").hide();
	$("#spantelefono").hide();
	$("#spanIntereses").hide();
	$("#btnRegresar").click(Limpiar);
	$("#btnGuardar").click(Verificar);
});

var Letras = /^[a-zA-Z ]+$/;
var Correo = /[a-zA-Z]+[@]{1}[a-z]+[.]{1}[c]{1}[o]{1}[m]{1}$/;
var Numero = /[0-9]{10}$/;

function Verificar(){
var Nombre = document.getElementById("IptNombre").value;
var Email = document.getElementById("IptCorreo").value;
var Telefono = document.getElementById("IptTelefono").value;

	//Verificar Nombre    
	if( Nombre === "" ){
		$("#divnom").attr("class","form-group has-error");
		$("#spannom").show().text('El campo esta vacio');
	}else if(!Nombre.match(Letras)){
        $("#divnom").attr("class","form-group has-error");
		$("#spannom").show().text('El nombre deben de ser solamente letras');
	}else{
		$("#divnom").attr("class","form-group has-success");
		$("#spannom").hide();
	}
	//VerificarCorreo
	if( Email === "" ){
		$("#divcorreo").attr("class","form-group has-error");
		$("#spancorreo").show().text('El campo esta vacio');
	}else if(!Email.match(Correo)){
        $("#divcorreo").attr("class","form-group has-error");
		$("#spancorreo").show().text('El correo es incorrecto');
	}else{
		$("#divcorreo").attr("class","form-group has-success");
		$("#spancorreo").hide();
	}
	//VerificarTelefono
	if( Telefono === "" ){
		$("#divtelefono").attr("class","form-group has-error");
		$("#spantelefono").show().text('El campo esta vacio');
	}else if(!Telefono.match(Numero)){
        $("#divtelefono").attr("class","form-group has-error");
		$("#spantelefono").show().text('El telefono es incorrecto');
	}else{
		$("#divtelefono").attr("class","form-group has-success");
		$("#spantelefono").hide();
	}
	//Validad los intereses
	if(!document.getElementById("CbxLibros").checked &&
       !document.getElementById("CbxMusica").checked &&
       !document.getElementById("CbxDeportes").checked &&
       !document.getElementById("CbxOtros").checked ){
    	$("#divIntereses").attr("class","form-group has-error");
        $("#spanIntereses").show().text('Seleccione un interes');
    }else{
    	$("#divIntereses").attr("class","form-group has-success");
    $("#spanIntereses").hide();;
    }
}
//Metodo para limpiar los campos
function Limpiar(){
	document.getElementById("IptNombre").value = "";
	$("#divnom").attr("class","form-group");
	$("#spannom").hide();
	document.getElementById("IptCorreo").value = "";
	$("#divcorreo").attr("class","form-group");
	$("#spancorreo").hide();
	document.getElementById("IptTelefono").value = "";
	$("#divtelefono").attr("class","form-group");
	$("#spantelefono").hide();
   var hijos=document.getElementsByName("hijos");
    hijos[0].checked=true;
    document.getElementById("SltEstadoCivil").value="Solter@";
    document.getElementById("CbxLibros").checked =false;
    document.getElementById("CbxMusica").checked =false;
    document.getElementById("CbxDeportes").checked =false;
    document.getElementById("CbxOtros").checked =false;
    $("#divIntereses").attr("class","form-group");
    $("#spanIntereses").hide();;
}