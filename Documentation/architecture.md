##Architecture

Notre application suit un modèle MVC.

Les servlets jouent le rôle des contrôleurs. Les servlets de l'applications  se trouvent tous dans le paquet ``ch.heigvd.amt.gary.controllers``. Chacun d'entre eux gère une partie différente de l'application.

Pour effectuer certaines tâches ils font appels à des EJBs qui se trouvent dans le paquet ``ch.heigvd.amt.gary.services``.

Les contrôleurs servent des pages JSP qui font fonctions de vues. Toutes les pages JSP se trouvent dans le dossier ``Web Pages/WEB-INF/views/`` du projet. Le dossier ``includes`` qui se trouve au même niveau que les JSP contient des entêtes et pieds-de-pages communs à toutes les pages de l'application.

Les modèles de notre applications sont représentés par les *Entities*, les classes présentent dans le paquet ``ch.heigvd.amt.gary.entities``. Celles-ci ne sont qu'une abstraction des tables contenues dans la base de données MySQL utilisée par l'application. Les *DAOs* présents dans le paquet ``ch.heigvd.amt.gary.services.dao`` permettent de faire le lien entre les entités utilisées par les services de l'application et la base de donnée.

Le paquet ``ch.heigvd.amt.gary.filters`` contient une unique classe utilisée pour filtrer les requêtes envoyées au serveur. Ce filtre sert à contrôler l'accès au différentes parties du site par les utilisateurs. Un utilisateur non-connecté ne pourra ainsi pas accéder à la page listant ses applications.

Finalement le paquet ``ch.heigvd.amt.gary.services.test`` contient un service utilisé pour généré des données de test.

Les tests ont été réalisé en partie avec JUnit et Selenium, dans un projet *GaryUserAcceptanceTest* et en partie à l'aide de JMeter. Des fichier contenant des plans de test JMeter se trouve dans le dossier */tests* de notre repo github.


