# Documentation

Interface REST
=============
L'interface REST de notre application permet aux concepteurs des applications d'utiliser le moteur de gamification. L'API est utilise le JSON comme format de transmission de données. Elle est disponible à l'adresse ```/api``` et reconnaît les chemins suivants:

Niveaux
--------

- **POST** ```/api/applications/<apikey>/levels``` : Crée un nouveau niveau pour l'application. Le JSON envoyé doit être de forme:

```
    {
        name : "<nom du niveau>",
        minPoints : <nombre de points au minimum pour accéder au niveau>
    }
```

- **GET** ```/api/applications/<apikey>/levels``` : Récupère la liste de tous les niveaux définis pour l'application sous la forme d'un tableau:

```
    [{level}, {level}, ..., {level}]
```

Où la structure ```{level}``` correspond à la définition du point ci-dessus.

Badges
--------

- **POST** ```/api/applications/<apikey>/badges``` : Crée un nouveau badge pour l'application. Le JSON envoyé doit être de forme:

```
    {
        name : "<nom du badge>",
        description : "<description du badge>",
        imageUrl : "<url vers l'image du badge>"
    }
```

- **GET** ```/api/applications/<apikey>/badges``` : Récupère la liste de tous les badges définis pour l'application sous la forme d'un tableau:

```
    [{badge}, {badge}, ..., {badge}]
```

Où la structure ```{badge}``` correspond à la définition du point ci-dessus.

Utilisateurs de l'application
-----------------------------

- **POST** ```/api/applications/<apikey>/users``` : Crée un nouvel utilisateur pour l'application. Le JSON envoyé doit être de forme:

```
    {
        id : <identifiant utilisé dans l'application pour l'utilisateur a gérer>
    }
```

L'identifiant correspond bel et bien à l'identifiant utilisé à l'interne de l'application cliente du service de gamification pour authentifier l'utilisateur. De cette manière, l'utilisation du service est transparente. À l'interne, l'utilisateur est identifié par le numéro fourni ET par l'application à laquelle il appartient.

- **GET** ```/api/applications/<apikey>/users``` : Récupère la liste de tous les utilisateurs définis pour l'application sous la forme d'un tableau:

```
    [{user}, {user}, ..., {user}]
```

Où la structure ```{user}``` correspond à la définition du point ci-dessus.

- **GET** ```/api/applications/<apikey>/users/<userid>``` : Récupère la réputation de l'utilisateur sous la forme:

```
    {
        points : <nombre de points actuellement acquis par l'utilisateur>,
        badges : [{badge}, {badge}, ..., {badge}]
    }
```

Les erreurs sont signalées par le code de status HTTP 400 (Bad Request) et contiennent un message indiquant l'erreur, par exemple ```This app doesn't seem to exist``` si la requêtee contient une API Key erronnée.