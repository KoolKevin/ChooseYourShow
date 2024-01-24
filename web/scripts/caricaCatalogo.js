

function generaHtml(jsonObj) {
	var righe = ""
	
	for(var i=0; i<jsonObj.prodotti.length; i++) {
		
		righe+="<tr id='"+jsonObj.prodotti[i].idProdotto+"'>"	//identifico ogni prodotto tramite il suo id
		    righe+="<td>" + jsonObj.prodotti[i].idProdotto + "</td>";
		    righe+="<td>" + jsonObj.prodotti[i].descrizione + "</td>";
		    righe+="<td>" + jsonObj.prodotti[i].prezzo + "</td>";
		    righe+="<td>" + jsonObj.prodotti[i].quantita + "</td>";
		    //piuttosto che fare una richiesta ajax decido di ricaricare direttamente la pagina per fare prima
		    righe+="<td><button>aggiungi al carrello di gruppo</button></td>"
		 righe+="</tr>"
	}

	var table = "<table class='formdata'><th>idProdotto</th><th>descrizione</th><th>prezzo$</th><th>quantita</th><th>aggiungi</th>"+ righe +"</table>";
	
	return table;
}

function caricaCatalogo_callback( theXhr, holder ) {


	if ( theXhr.readyState === 2 ) {
    	// non faccio niente
    	// theElement.innerHTML = "Richiesta inviata...";
	}
	else if ( theXhr.readyState === 3 ) {
    	// non faccio niente
		// theElement.innerHTML = "Ricezione della risposta...";
	}
	else if ( theXhr.readyState === 4 ) {
        if ( theXhr.status === 200 ) {
        	// operazione avvenuta con successo
	        if ( theXhr.responseText && theXhr.responseText !== "" ) {
					console.log( JSON.parse(theXhr.responseText) ); 
					//console.log( generaHtml( JSON.parse(theXhr.responseText) ) );
					var jsonObj = JSON.parse(theXhr.responseText);
			        holder.innerHTML = generaHtml( jsonObj );
			        //NB: solo una volta caricati gli elementi nella pagina posso accederci
			        //NB2: aggiungo l'evento di onclick ai bottoni tramite addEventListener dato che non mi bastano " e '
			        for( i in jsonObj.prodotti ) {
						//ATTENZIONE MOLTO IMPORTANTE QUA USARE LET(ma non ho capito bene perchè?)!!!
						let idProdotto = jsonObj.prodotti[i].idProdotto;
						//costruisco l'url con i parametri necessari per aggiungere l'elemento desisderato al carrello di gruppo			     
						let destination = location.protocol + '//' + location.host + location.pathname+"?addProdotto="+idProdotto;
			 			document.getElementById(idProdotto)	
				 				.getElementsByTagName("button")[0]
				 				.addEventListener("click", () => window.location.replace(destination) );
					}
			}
			else {
		    	// non faccio niente
			}
        }

        else {
        	// errore di caricamento
        	// non faccio niente nemmeno qui
        }

	}

}



function caricaCatalogoIframe(theUri, holder) {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	holder.innerHTML = '<iframe src="' + theUri + '" width="50%" height="50px">Il tuo browser non supporta gli iframe</iframe>';
}



function caricaCatalogoAJAX(theUri, theXhr, holder) {
    
	theXhr.onreadystatechange = function() { caricaCatalogo_callback(theXhr, holder); };

	try {
		theXhr.open("get", theUri, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}

	theXhr.send(null);

}


function caricaCatalogo(uri, holder) {

	// assegnazione oggetto XMLHttpRequest
	var xhr = myGetXmlHttpRequest();

	if ( xhr ) 
		caricaCatalogoAJAX(uri, xhr, holder); 
	else 
		caricaCatalogoIframe(uri, holder); 

}
