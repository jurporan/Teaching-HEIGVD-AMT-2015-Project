# Documentation
Manuel d'installation
=====================

Base de données
---------------

Notre application utilise JPA pour la persistence des données et ce dernier enregistre ses données dans une base de données. L'application requiert donc une base de données fonctionnelle:

- Disposer d'un serveur de base de données fonctionnel (testé avec MySQL)
- Disposer d'un utilisateur ayant les droits d'accès et d'écriture sur ce serveur
- Créer la base de données ```Gary``` qui servira à contenir les données de l'application

La suite de la configuration s'effectue dans le serveur d'application utilisé, dans notre cas il s'agit de Glassfish. Sa console d'administration se situe à l'adresse: ```https://<IP du serveur Glassfish>:4848/```

Ensuite, dans Glassfish, créer un pool de connexion vers le serveur de base de données comme suit:

![Création d'un pool de connexion](img/glassfish_pool.png)

Où l'URL équivaut à : ```jdbc:mysql://<IP du serveur MySQL>:3306/Gary?zeroDateTimeBehavior=convertToNull```

Enfin, il faut créer une ressource JDBC liée à ce pool de connexion et portant le  nom ```jdbc/GaryDatasource```.