# Epitek Dashboard
Antoine STEMPFER & Louis MALLEZ

## Lancement

docker-compose build
docker-compose up

Ouvrir le navigateur sur http://localhost

Le serveur API est démarré sur localhost:8080


## Token et clés d'API

Les clés d'API doivent etre renseignés dans la config de spring.

Concernant les clés de développement RIOT Games, elles doivent être renouvelés toutes les 24h 



## Liste des Services et des Widgets

#### Cat :

 - **Random Cat :** Affiche une image aléatoire de chat
 
*https://api.thecatapi.com/v1*

#### Country :

 - **Borders :** Affiche la liste les pays frontaliers d'un pays
 - **Flag :** Affiche le drapeau et la capitale d'un pays
 - **Informations:** Affiche des informations sur un pays
 
*https://restcountries.eu/rest/v2*


### Crypto

 - **ticker :** Affiche le cours d'une cryptomonnaie.
 
*https://api.binance.com*

### Riot

 - **Top Mastery :** Affiche la meilleure maitrise d'un joueur
 - **Last Match :** Affiche le dernier match d'un joueur
 - **Most Played Champion :** Affiche le champion le plus joué par un joueur

*https://euw1.api.riotgames.com*

### Spotify

*Demande un connection OAuth2*

 - **New Release :** Affiche une nouveautés musicale dans un pays
 - **Top Artiste :** Artiste le plus écouté par l'utilisateur
 - **Top Track :** Musique la plus écoutée par l'utilisateur
 - **User :** Affiche les informations de l'utilisateur

### Twitch

*Demande un connection OAuth2*

 - **Channel :** Affiche les informations sur la chaine de l'utilisateur
 - **Top Streamer :** Affiche le streamer avec le plus de viewers pour un jeu et une langue

### Weather

 - **Condition :** Affiche les conditions météorologique dans une ville
 - **Sun Info :** Affiche l'heure du lever et du coucher du soleil dans une vile
 - **Temperature :** Affiche la temperature dans une ville
 
*http://api.openweathermap.org/data/2.5/weather*
