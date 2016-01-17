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

Notre serveur express va donc se charger de stocker l'image envoyée par l'utilisateur et simplement retourner l'URL à la création d'un badge. À noter que l'image est supprimée correctement lorsque la création du badge est annulée ou que le badge est supprimé.