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

- **GET** ```/api/applications/<apikey>/users/<userid>/level``` : Récupère le niveau actuel de l'utilisateur. Le JSON reçu sera sous cette forme :

```
    {
        name : "<nom du niveau>",
        minPoints : <nombre de points au minimum pour accéder au niveau>
    }
```

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

- **GET** ```/api/applications/<apikey>/users/<userid>/reputation``` : Récupère la réputation de l'utilisateur sous la forme:

```
    {
        points : <nombre de points actuellement acquis par l'utilisateur>,
        badges : [{badge}, {badge}, ..., {badge}]
    }
```

Règles
------

- **POST** ```/api/applications/<apikey>/rules``` : Crée une nouvelle règle pour l'application. Le JSON envoyé doit être de forme:

```
   {
        typeOfEvent : <nom de l'événement concerner>,
        ruleParameter : <le nombre de point ou l'id du badge concerné>,
        penalty : [false|true],
        minValue : <valeur minimum>,
        maxValue : <valeur maximum>,
        rewardType : [1|2]
   }
```

Le champ *typeOfEvent* sert à indiquer le nom des événements qui seront concernés par la règle

Le champ *ruleParameter* sert à indiquer, soit le nombre de points, soit le badge qui seront ajoutés ou enlever à l'utilisateur.

Le champ *penalty* indique s'il s'agit d'une punition ou non. Les points (ou le badge) seront enlevés respectivement ajoutés lors d'un événement.

Les champs *minValue* et *maxValue* servent à indiquer des éventuels  bornes conditionnant l'obtention de la récompense (comparé avec un paramètre de l'évent)

Le champ *rewardType* indique le type de récompense : 1 pour des points, 2 pour des badges. Toute autre valeur lévera une erreur.

## Événements

- **POST** ```/api/applications/<apikey>/users/<userId>/events``` : L'event sera traité. Le JSON doit être envoyé sous la forme :

```
   {
        type : <nom de l'événement>,
        parameter : <valeur du paramètre>
   }
```

Le champ *type* permet d'identifier l'événement. Si une règle de la même application pour ce type d'événement, cette dernière sera appliquée. Si aucune règle n'existe, rien ne se produira.

Le champ *parameter* est optionnel. S'il est présent il doit s'agir d'un entier. Sinon il doit prendre la valeur *null*. Si *parameter* vaut *null* la règle s'appliquera dans tous les cas. Si au contraire *parameter* n'est pas nul, la règle ne s'appliquera uniquement si *parameter* est plus grand ou égal à l'attribut *minValue* de la règle et plus petit ou égal à l'attribut *maxValue* de la règle.

## Erreurs

Les erreurs sont signalées par le code de status HTTP 400 (Bad Request) et contiennent un message indiquant l'erreur, par exemple ```This app doesn't seem to exist``` si la requêtee contient une API Key erronnée.
