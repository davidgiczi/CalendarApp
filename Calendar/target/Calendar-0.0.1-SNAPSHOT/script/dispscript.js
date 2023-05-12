document.getElementById("prev").addEventListener("click", getPreviousMonth);
document.getElementById("next").addEventListener("click", getNextMonth);
document.getElementById("close").addEventListener("click", closeCalendar);
document.getElementById("print").addEventListener("click", printPage);
document.getElementById("search").addEventListener("click", searchNameDay);
document.getElementById("getyear").addEventListener("click", getYear);
document.getElementById("color").addEventListener("change", selectColor);
document.getElementById("del").addEventListener("click", deleteNote);
var actualyear = parseInt(document.getElementById("actyear").value);
var actualmonth = document.getElementById("actmonth").value;
var monthsize = parseInt(document.getElementById("monthsize").value);
var selectColor = document.getElementById("color");
var selectedColor;
var month;
	
for(var dayID = 1; dayID <= monthsize; dayID++){
	
	document.getElementById(dayID).addEventListener("click", addNote);
}

	
	selectedColor = selectColor.options[selectColor.selectedIndex].value;
	selectColor.style.color = selectedColor;
	document.getElementById("actcolor").value = selectedColor;

function selectColor() {
	
	selectColor = document.getElementById("color");
	selectedColor = selectColor.options[selectColor.selectedIndex].value;
	selectColor.style.color = selectedColor;
	document.getElementById("actcolor").value = selectedColor;
}


function addNote() {
	
	
	for(var day = 1; day <= monthsize; day++){
		
		if(this.id == document.getElementById(day).id){
			
			var dd = day > 9 ? day : "0" + day;
			var msg = document.getElementById(day).innerText.trim();
				
			if(msg.endsWith(";")){
				
				sendDataForNoteRegistration(actualyear, getMonthNumberByName(), day, msg, selectColor.selectedIndex);
				return;
			}
			
				
			msg = msg == "registration" ? "" : msg;
			var reg = window.prompt("Bejegyzés hozzáadása a naphoz (" + actualyear + ". " + actualmonth + " " + dd + ".)", msg);
			
			if(reg !== null && reg !== ""){
				
			
				sendDataForNoteRegistration(actualyear, getMonthNumberByName(), day, reg, selectColor.selectedIndex);
			}
							
		}
		
}
	
}

function getYear() {
	
	 var year = prompt("Évszám megadása (A naptár 1582. évtől érvényes):");
	 	 
	 
	 if( year !== null  && !isNaN(parseInt(year))) {
		
		 month = getMonthNumberByName();
		 actualyear = parseInt(year);
		 sendData();
		 
	 }
	 else if( year !== null && isNaN(parseInt(year))) {
		 
		 alert("Csak szám lehet az évszám érték.");
	 }
		
	 
}


function searchNameDay() {
	
	 var name = prompt("Keresendő névnap megadása:");
	 
	 
	    if (name !== null) {
	    	
	    	sendDataForNameSearching(name, actualyear);
	    	
	    }
	
}

function sendDataForNoteRegistration(year, month, day, msg, color) {

    var xmlHTTP = new XMLHttpRequest();

    xmlHTTP.onreadystatechange = function () {

        if (xmlHTTP.readyState === 4 && xmlHTTP.status === 200) {

            var response = xmlHTTP.responseText;
           
            displayResponse(response);

        }

    };

    var url = document.location.protocol + "//" + document.location.host +
             "/Calendar/addNote?year=" + year + "&month=" + month + "&day=" + day + "&msg=" + msg + "&color=" + color;


    xmlHTTP.open("POST", url, true);
    xmlHTTP.send();


}

function displayResponse(resp) {
	
	
	if(resp === "opened"){
			
		alert("A bejegyzés módosításához az oldal frissítése szükséges.");
		
	}	
	else if(resp !== "ok")	{
		
		alert(resp);
	}
	
		month = getMonthNumberByName();
		
		sendData();
		
}


function sendDataForNameSearching(name, year) {

    var xmlHTTP = new XMLHttpRequest();

    xmlHTTP.onreadystatechange = function () {

        if (xmlHTTP.readyState === 4 && xmlHTTP.status === 200) {

            var response = xmlHTTP.responseText;

           alert(response);


        }

    };

    var url = document.location.protocol + "//" + document.location.host +
             "/Calendar/searchName?name=" + name + "&actualyear=" + year;


    xmlHTTP.open("GET", url, true);
    xmlHTTP.send();


}




function closeCalendar() {

	
    window.close();
}


function getMonthNumberByName() {

    switch (actualmonth) {

        case "Január" :

            return 0;

        case "Február" :

            return 1;

        case "Március" :

            return 2;

        case "Április" :

            return 3;

        case "Május" :

            return 4;

        case "Június" :

            return 5;

        case "Július" :

            return 6;

        case "Augusztus" :

            return 7;

        case "Szeptember" :

            return 8;

        case "Október" :

            return 9;

        case "November" :

            return 10;

        case "December" :

            return 11;

    }

}

function getNextMonth() {
	
    month = getMonthNumberByName();
    
    if(month < 11){
    	
    	month++;
    }
    else{
    	month = 0;
    	actualyear++;
    }
   
    sendData();
}

function getPreviousMonth() {

    month = getMonthNumberByName();
    
    if(month > 0){
    month--;
    }
    else {
    	month = 11;
    	actualyear--;
    }
    
    sendData();
}

function sendData() {

    document.getElementById("actyear").value = actualyear;
    document.getElementById("actmonth").value = month;

    document.getElementById("stepper").submit();

}

function printPage() {

    window.print();


}

function deleteNote() {
	
	var inputData = prompt("A törlendő bejegyzés napjának megadása (0 < a nap száma <=" +  monthsize + "):");
	
	if(inputData !== null && isNaN(parseInt(inputData))){
		
		alert("Csak szám lehet a nap száma.");
	}
	else if(inputData === "0" || 0 > parseInt(inputData) || monthsize < parseInt(inputData)){
		
		alert("Nem megfelelő érték: 0 < a nap száma <=" + monthsize + ".");
	}
	else{
		
		sendDataForNoteDelete(actualyear, getMonthNumberByName(), inputData);
	}
}

function sendDataForNoteDelete(year, month, day) {

    var xmlHTTP = new XMLHttpRequest();

    xmlHTTP.onreadystatechange = function () {

        if (xmlHTTP.readyState === 4 && xmlHTTP.status === 200) {

            var response = xmlHTTP.responseText;
           
          displayResponse(response);

        }

    };

    var url = document.location.protocol + "//" + document.location.host +
             "/Calendar/delete?year=" + year + "&month=" + month + "&day=" + day;


    xmlHTTP.open("GET", url, true);
    xmlHTTP.send();


}


