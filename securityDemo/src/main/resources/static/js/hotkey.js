window.onhelp = function(){return false;}
document.onkeydown = function(){
	var target = event.target ;
    var tag = event.target.tagName.toUpperCase();
//	if ((event.keyCode == 112) || //shield F1
//	(event.keyCode == 113) || //shield F2
//	(event.keyCode == 114) || //shield F3
//	(event.keyCode == 115) || //shield F4
//	(event.keyCode == 116) || //shield F5
//	(event.keyCode == 117) || //shield F6
//	(event.keyCode == 118) || //shield F7
//	(event.keyCode == 119) || //shield F8
//	(event.keyCode == 120) || //shield F9
//	(event.keyCode == 121) || //shield F10
//	(event.keyCode == 122)  //shield F11 
//	//|| (event.keyCode == 8) //shield Backspace
//	)  
////	(event.keyCode == 123)) //shield F12
	if ((event.keyCode == 116) ||
	(event.ctrlKey && event.keyCode==82)) // Ctrl+R
	{
		event.keyCode = 0; 
		event.returnValue = false;
		return false;
	}
	
	if ((event.keyCode == 32))
	{
		if((tag == 'INPUT' && !$(target).attr("readonly"))||(tag == 'TEXTAREA' && !$(target).attr("readonly"))){
	   		if((target.type.toUpperCase() == "BUTTON") || (target.type.toUpperCase() == "RADIO") || (target.type.toUpperCase() == "CHECKBOX")){
	   			return false;
	   		}else{
	   			return true;
	   		}
	   	}else{
	   		return false;
	   	}
	}
	
}

document.oncontextmenu=function(){return false};

$(function() {
	  if (window.history && window.history.pushState) {
	  $(window).on('popstate', function () {
	  window.history.pushState('forward', null, '#');
	  window.history.forward(1);
	  });
	  }
	  window.history.pushState('forward', null, '#');
		  window.history.forward(1);
	  })

//key('enter', function(){
//    if (check()==1){
//        return false;
//    }
//	document.forms[0].action= "entr";
//	document.forms[0].submit();
//});
//key('f1', function(){
//	alert('f1');
//});
//key('f2', function(){
//	alert('f2');
//});
//key('f3', function(){
//	alert('f3');
//});
//key('f4', function(){
//	alert('f4');
//});
//key('f5', function(){
//	alert('f5');
//});
//key('f6', function(){
//	alert('f6');
//});
//key('f7', function(){
//	alert('f7');
//});
//key('f8', function(){
//	alert('f8');
//});
//key('f9', function(){
//	alert('f9');
//});
//key('f10', function(){
//	alert('f10');
//});
//key('f11', function(){
//	alert('f11');
//});
//key('f12', function(){
//	alert('f12');
//});