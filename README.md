![immagine](https://user-images.githubusercontent.com/94211283/213875031-c1fd0b0e-ad22-4e32-a631-e7410da76028.png)

# YTclone Backend

Questo progetto è stato sviluppato utilizzando:

- Java 17

- Spring Boot 2.7.5

- MongoDB

- Tomcat 9.0.68

# Configurazione pre-run BE

1. Config MongoDB

Il DB deve prevedere una collezione denominata "roles" all'interno della quale troviamo i ruoli previsti per i vari utenti:

(P.S. La collezione può essere creata manualmente)

ROLE_USER

ROLE_MODERATOR

ROLE_ADMIN

ed un'altra denominata "Users" all'interno della quale troviamo almeno un utente con ROLE_ADMIN per il primo utilizzo.
(Inserimento utente da valuare se automatica o tramite script da eseguire sul DB).

###################################################################################################

2. Config BE

Il file "src/main/resources/application.properties" contiene le variabili da modificare 
per la corretta esecuzione del BE sul server host.

##################### MongoDB ######################

spring.data.mongodb.host= -> IP del DB

spring.data.mongodb.port=27017(default di MongoDB)

spring.data.mongodb.database= -> Nome del DB

############## JWT #################

jwt.secret= -> Password per l'utilizzo del Token 

jwt.expirationDateInMs=3600000 (Durata in millisecondi del Token)

jwt.refreshExpirationDateInMs=3600000 (Durata in millisecondi del Refresh Token)

############## Multipart file size ##################

#spring.servlet.multipart.enabled=true -> Può essere decommentato in caso di problemi con l'upload

spring.servlet.multipart.max-file-size=250MB -> Dimensioni max del file

spring.servlet.multipart.max-request-size=250MB -> Dimensioni max del file per richiesta

########## Upload directory ########################

MultimediaDirectoryFolder.upload-without-prefix= -> Nome della directory di destinazione per l'upload dei file

MultimediaDirectoryFolder.upload= -> EX. "C:/Users/xyz/Video/${MultimediaDirectoryFolder.upload-without-prefix}"
