function validateFormRegistrazione() //Controlla se tutti i campi della registrazione sono corretti
{
	var nome = document.Registrazione.nome;
	var cognome = document.Registrazione.cognome;
	var username = document.Registrazione.username;
	var email = document.Registrazione.email;
	var password = document.Registrazione.password;
	var confermapassword = document.Registrazione.confermapassword;
	var nazionalita = document.Registrazione.nazionalita;
	var f = document.forms['Registrazione'];
	
	var passid_len;
		
	if(checknome(nome))
	{
		if(checkcognome(cognome))
		{
			if(checkusername(username))
			{
				if(checkmail(email))
				{
					if(checkpassword(password))
					{
						if(checkconfermapassword(confermapassword,password))
						{
							if(checknazionalita(f))
							{
								
							}
							else
							{
								return false;
							}
						}
						else
						{
							return false;
						}
					}
					else
					{
						return false;
					}
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}	
	else
	{
		return false;
	}
}

function validateFormAccedi() //Controlla se tutti i campi del login sono corretti
{
	var username = document.Accedi.username;
	var password = document.Accedi.password;
	
	if(checkusername(username))
	{
		if(checkpassword1(password))
		{
			
		}
		else
		{
			return false;
		}
	}
	else
	{
		return false;
	}
}

function validateFormPassowordDimenticata() //Controlla se tutti i campi della password password dimenticata sono corretti
{
	var username = document.Dimenticata.username;
	var password = document.Dimenticata.password;
	
	if(checkusername(username))
	{
		if(checkmail(email))
		{
			
		}
		else
		{
			return false;
		}
	}
	else
	{
		return false;
	}
}

function validateFormModificaPassword()
{
	var passwordattuale = document.ModificaPassword.password;
	var nuovapassword = document.ModificaPassword.nuovapassword;
	var ripetinuovapassword = document.ModificaPassword.ripetinuovapassword;
	
	if(checkpassword(passwordattuale))
	{
		if(checkpassword(nuovapassword))
		{
			if(checkmodificapassword(nuovapassword,ripetinuovapassword))
			{
				
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	else
	{
		return false;
	}
}

function checknome(nome)
{
	if(nome.value.length == 0)
	{
		alert("Inserire il nome");
		nome.focus();
		return false;
	}
	else if(nome.value.length>20)
	{
		alert("Il nome non deve essere superiore a 20 caratteri");
		nome.focus();
		return false;
	}
	return true;
}

function checkcognome(cognome)
{
	if(cognome.value.length == 0)
	{
		alert("Inserire il cognome");
		cognome.focus();
		return false;
	}
	else if(cognome.value.length>20)
	{
		alert("Il cognome non deve essere superiore a 20 caratteri");
		cognome.focus();
		return false;
	}
	return true;
}

function checkusername(username)
{
	if(username.value.length == 0)
	{
		alert("Inserire l'username");
		username.focus();
		return false;
	}
	else if(username.value.length>20)
	{
		alert("L'username non deve essere superiore a 20 caratteri");
		username.focus();
		return false;
	}
	return true;
}

function checkmail(email)
{
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(email.value.match(mailformat))
	{
		return true;
	}
	else
	{
		alert("E-mail errata o non inserita");
		email.focus();
		return false;
	}
}

function checkpassword(password)
{
	passid_len = password.value.length;
	if (passid_len == 0)
	{
		alert("Inserire una password");
		password.focus();
		return false;
	}
	else if (passid_len > 15)
	{
		alert("La password non deve essere superiore a 15 caratteri");
		password.focus();
		return false;
	}
	return true;
}

function checkpassword1(password)
{
	var passid_len2 = password.value.length;
	if (passid_len2 == 0)
	{
		alert("Inserire una password");
		password.focus();
		return false;
	}
	else if (passid_len2 > 15)
	{
		alert("La password non deve essere superiore a 15 caratteri");
		password.focus();
		return false;
	}
	return true;
}

function checkconfermapassword(confermapassword,password)
{
	var passid_len1 = confermapassword.value.length;
	
	if (passid_len1 == 0)
	{
		alert("Inserire nuovamente la password");
		confermapassword.focus();
		return false;
	}
	else if (passid_len1 != passid_len)
	{
		alert("La due passord non coincidono");
		confermapassword.focus();
		return false;
	}
	return true;
}

function checkmodificapassword(nuovapassword,ripetinuovapassword)
{
	var passid_len1 = nuovapassword.value.length;
	var passid_len2 = ripetinuovapassword.value.length;
	
	if (passid_len2 == 0)
	{
		alert("Inserire nuovamente la password");
		nuovapassword.focus();
		return false;
	}
	if (passid_len2 != passid_len1)
	{
		alert("La due passord non coincidono");
		nuovapassword.focus();
		return false;
	}
	return true;
}

function checknazionalita(f)
{
	if(f.nazionalita.options[f.nazionalita.selectedIndex].value == "Seleziona")
	{
		alert("Inserire la nazionalita");
		f.focus();
		return false;
	}
	return true;
}


