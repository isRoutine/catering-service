# Catering Service

Realizzazione di un sistema informativo per una società che offre servizi di catering.<br>
Il sistema sarà progettato e realizzato usando SpringBoot e Postgres SQL.<br>

L'obbiettivo del sistema è quello di consentire almeno l'esecuzione di 4 casi d'uso:<br>
* almeno due che abbiano come attore principale <strong>l'amministratore del sistema</strong>, in particolare: <br>
>
>* inserimento di dati
>* modifica di dati 

* almeno due che abbiano come attore principale un <strong>utente generico</strong>


### ***Autenticazione e Registazione al sistema***
Il sistema prevede un diverso funzionamento in base al tipo di utente.<br>
> 1. Utente non autenticato:  funzionamento limitato, non è possibile visualizzare i dettagli di chef e buffet.<br>
> 2. Utente autenticato (generico):  funzionamento completo, è possibile visualizzare i dettagli di chef e buffet.<br>
> 3. Utente autenticato (admin):  funzionamento completo , con possibilità di apportare modifiche al sistema ed ai suoi dati.<br>

<img width="1101" alt="Screenshot 2022-06-16 at 17 00 36" src="https://user-images.githubusercontent.com/81380857/174099827-bf96a926-439a-4e10-8423-0080a7bf5237.png">


Ogni utente **potrà accedere** al sistema tramite:
> * Credenziali del proprio account, previa registrazione 
> * Account Google
