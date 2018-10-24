$ = jQuery;
$(function(){

    //El calendario
    $(".calendar").click(function(){
        $(".ui-datepicker-trigger").click();
    });
    setLocate();
});

function setLocate()
{
    PrimeFaces.locales['es'] = {
        closeText: 'Cerrar',
        prevText: 'Anterior',
        nextText: 'Siguiente',
        currentText: 'Inicio',
        monthNames: ['Enero','Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
        monthNamesShort: ['Enero','Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
        dayNames: ['Domingo','Lunes','Martes','Miércoles','Jueves','Viernes','Sábado'],
        dayNamesShort: ['Dom','Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
        dayNamesMin: ['D','L','M','M','J','V','S'],
        weekHeader: 'Semana',
        firstDay: 1,
        isRTL: false,
        showMonthAfterYear: false,
        yearSuffix: '',
        timeOnlyTitle: 'Sólo hora',
        timeText: 'Tempo',
        hourText: 'Hora',
        minuteText: 'Minuto',
        secondText: 'Segundo',
        ampm: false,
        month: 'Mes',
        week: 'Semana',
        day: 'Día',
        allDayText : 'Todo el día'
    };
}


function handleRequest(xhr, status, args) {
    if(!args.validationFailed && args.exito) {
        var elem = getElementsByAttr('tabindex');
        for(var i = 0; i < elem.length; i++) {
            elem[i].value="";
        }
        dlg.hide();
    }
}


function getElementsByAttr(attr) {
    var all = document.getElementsByTagName('*');
    var elements = [];
    for ( var i = 0; i < all.length; i++) {
        if (all[i].getAttribute(attr))
            elements.push(all[i]);
    }
    return elements;
}

    
////////////////////////////////////////////////////////////////
function showAceptMessage2(xhr, status, args) {
    if(!args.validationFailed && args.exito) {
        avisoExitoLogOut.show();
    } else {
        avisoExitoLogOut.hide();
    }
}

function confirmaRegistroMessage2(xhr, status, args) {
    if(!args.validationFailed && args.exito) {
    	confirmaRegistro.show();
    } else {
    	confirmaRegistro.hide();
    }
}

function timeoutMessage2(xhr, status, args) {
    if(!args.validationFailed && args.exito) {
        timeout.show();
    } else {
        timeout.hide();
    }
}

function genericMessage(xhr, status, args, obj) {
    if(!args.validationFailed && args.exito) {
        obj.show();
    } else {
        obj.hide();
    }
}

function navegate(xhr, status, args, obj) {
    if(!args.validationFailed && args.exito) {
        location = obj;
    }
}
function navegate2(xhr, status, args, obj) {
    if(!args.validationFailed && args.exito) {
        location = obj;
    }
}
////////////////////////////////////////////////////////////////////
function timeoutMessage(xhr, status, args) {
	genericMessage(xhr, status, args, timeout);
}
function showAceptMessage(xhr, status, args) {
	genericMessage(xhr, status, args, avisoExitoLogOut);
}

function confirmaRegistroMessage(xhr, status, args) {
	genericMessage(xhr, status, args, confirmaRegistro);
}

function redirect(xhr, status, args, url) {
    if(!args.validationFailed && args.exito) {
        location=url;
    }
}

function redirectConsulta(xhr, status, args, url) {
    if(!args.validationFailed && args.exito) {
    	statusDialog.show();
        location=url;
    }
}
function decide(args, status, obj) {  
    if(!args.validationFailed && args.exito) {    
        obj.hide();  
    }
}
function toUpperCase(field)
{
    var cursor = -1;
    var tb = field;
    if(tb != undefined){
        cursor=tb.selectionStart;
        tb.value=tb.value.toUpperCase();
        tb.setSelectionRange(cursor, cursor);
    }
}

function toUpperCase(e,field) {
    tecla = (document.all) ? e.keyCode : e.which;
    
    //teclas(flechas de navegacion, inicio, fin y shif)
    if (e.which >= 60 && e.which <= 90 || e.which >= 96 && e.which <= 105 || e.which >= 48 && e.which <= 57) {
        var cursor = -1;
        var tb = field;
        if(tb != undefined){
            cursor=tb.selectionStart;
            tb.value=tb.value.toUpperCase();
            tb.setSelectionRange(cursor, cursor);
        }
    }
}
function upperCase(element){
    var oldValue = element.value;
    var newValue = oldValue.toUpperCase();
    element.value = newValue;
}

function handleRequest(xhr, status, args) {
    if (args.ok) {
        alert('Proceso generado correctamente');
    }else{
        alert('Ha ocurrido un error en la ejecución del proceso');
    }
}
function disableCharacters(e) {
    tecla = (document.all) ? e.keyCode : e.which;    
    //teclas(flechas de navegacion, inicio, fin y shif)
    if (tecla >= 48 && tecla <= 57 || tecla == 8 || tecla == 0) {
        return true;
    }else{
        return false;
    }
}

function disableEnterKey(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    //teclas(flechas de navegacion, inicio, fin y shif)
    if(tecla!=13){
        return true;
    } else{
        return false;
    }
}

function nobackbutton(){
   window.location.hash="no-back-button";
   window.location.hash="Again-No-back-button" //chrome
   window.onhashchange=function(){window.location.hash="no-back-button";}
}
 

 

 
 
 

















/*



 function handleEjemploNoUsado(xhr, status, args) {  
     alert(status); // pinta: success, fail, ...
     alert(xhr); // no se... pero creo que es el ajax http request
     if(args.validationFailed || !args.loggedIn) {  
         //jQuery('#loginScreenX').parent().effect("shake", { times:3 }, 100);  
     } else {  
         loginScreen.hide();  
         //jQuery('#loginLink').fadeOut();  
     }  
 }
function showModal() {
     try {    
            otro = document.getElementById("modalLayer");
            otro.style.height= vsize()+"px";
            otro.style.width= hsize()+"px";
            otro.style.display = "block";
            
            img = document.getElementById("waitImage");
            img.style.top = middleVScreen()+"px";
            img.style.left = middleHScreen()+"px";
     } catch(e) {
         alert(e);
     }
     return false;
 }


function getInternetExplorerVersion()
// Returns the version of Windows Internet Explorer or a -1
// (indicating the use of another browser).
{
   var rv = -1; // Return value assumes failure.
   if (navigator.appName == 'Microsoft Internet Explorer')
   {
      var ua = navigator.userAgent;
      var re  = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
      if (re.exec(ua) != null)
         rv = parseFloat( RegExp.$1 );
   }
   return rv;
}
function checkIEVersion()
{
   var msg = "You're not using Windows Internet Explorer.";
   var ver = getInternetExplorerVersion();
   if ( ver> -1 )
   {
      if ( ver>= 8.0 )
         msg = "You're using Windows Internet Explorer 8.";
      else if ( ver == 7.0 )
          msg = "You're using Windows Internet Explorer 7.";
      else if ( ver == 6.0 )
          msg = "You're using Windows Internet Explorer 6.";
      else
          msg = "You should upgrade your copy of Windows Internet Explorer";
    }
   alert( msg );
}
 function hideModal() {
     try {    
            otro = document.getElementById("modalLayer");
            otro.style.display = "none";
     } catch(e) {
         alert(e);
     }
     return false;
 }
 function vsize() {
     return document.body.offsetHeight + 86;
 }
 
 function hsize() {
     return document.body.offsetWidth + 16;
 }
 
 function middleVScreen() {
     return (window.screen.height/2)-(50);
 }
 
 function middleHScreen() {
     return (window.screen.width/2)-(25);
 }
 


*/