Phase 3
=======

Durant cette troisième et dernière phase de notre projet d'AMT, nous avons décidé d'implémenter une page présentant une interface dynamique permettant de gérer les règles. Cette page est une véritable petite "application web" utilisant Angular et présente un design simple et une interface inspirée d'un célèbre cartoon.

Cette page permet de:

- Ajouter de nouvelles règles
- Lister les règles existantes
- Modifier les règles existantes
- Supprimer des règles existantes
- Ajouter de nouveaux badges


Lors de la création de nouveaux badges, l'interface permet d'envoyer des fichiers images depuis le poste de l'utilisateur. En effet, les badges contiennent une image, mais seule l'URL est transmise à l'API de gamification du serveur Java EE. Cette image doit donc être hébergée au préalable sur le site utilisant le service.

Notre serveur express va donc se charger de stocker dynamiquement l'image envoyée par l'utilisateur et simplement retourner l'URL lors de la sélection d'une image. À noter que l'image est supprimée correctement lorsque la création du badge est annulée (changement d'action dans une liste, appuis sur le bouton "*Cancel*", etc.).

Le fichier Angular est disponible dans **public/js/app.js**. Il contient un filtre (pour n'afficher qu'une seule fois chaque règle dans la page principale), une directive permettant d'accéder à un objet représentant un fichier que l'utilisateur souhaite héberger, un service permettant d'uploader un fichier sur le serveur (à condition que ce soit une image, sinon une erreur est générée) et 4 fabriques (factorisation du code...), en plus des 5 contrôleurs. Tout le code est commenté, donc n'hésitez pas à le parcourir !
