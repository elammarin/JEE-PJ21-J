# [Projet de SRA : Retrait des commandes](https://gdufrene.github.io/mooc_jee_spring/GH1hlSRQMBsS_2022.html#retrait)

Par :

* EL AMMARI NORDINE
* HAMANI    RAYANE
* HALLIL    RAZINE
* AMARA     CYRIA

## __Pré-requis : Installation du projet__

* Clonez le [projet](https://github.com/elammarin/JEE-PJ21-J)
* Compilez le avec la commande

```bash
mvn package
```

## __Pré-requis : Installation de Tomcat__

* Téléchargez [Tomcat 9](https://tomcat.apache.org/download-90.cgi)
* Extrayez l'archive
* _(Optionnel)_ Donnez vous toutes les autorisations sur le dossier extrait si vous rencontrez des problèmes d'autorisations

```bash
chmod -R +777 tomcat
```

* Ajoutez le contexte dans la balise `Host` du fichier `conf/server.xml` du dossier extrait

```xml
<Context    docBase="{chemin du projet}/JEE-PJ21-J/target/sra1-1.0-SNAPSHOT"
            path="/JEE-PJ21-J"
            reloadable="true"
/>
```

## __Lancement de l'application__

* Lancez Tomcat depuis son dossier `bin`

```bash
./catalina.sh run
```

* Créez des quais à la [page de création des quais](http://localhost:8080/JEE-PJ21-J/docks/admin/list.html)
* Initialisez les données __(dans l'ordre)__
  * [page d'initialisation des employés](http://localhost:8080/JEE-PJ21-J/employee/init.html)
  * [page d'initialisation des commandes](http://localhost:8080/JEE-PJ21-J/order/init.html)
* Renseignez à la [page d'accueil du client](http://localhost:8080/JEE-PJ21-J/order/welcome.html) `123` (identifiant d'un client mocké) dans le champ et appuyez sur le bouton `Valider`
* Appuyez ensuite sur le bouton `Récupérer` si une commande est prête à être délivrée
* Choisissez un nom à la [page de connexion des employés](http://localhost:8080/JEE-PJ21-J/employee/connect.html) et appuyez sur le bouton `Connect !`
* Choisissez une commande délivrable et appuyez sur le bouton `Delivered` pour notifer qu'elle a été délivrée ou `Disconnect` si vous souhaitez changer d'employé
* Allez ensuite à la [page de vue des statistiques de l'admin](http://localhost:8080/JEE-PJ21-J/order/welcomeAdmin.html), choisissez une date et appuyez sur le bouton `Valider`
* Admirez les statistiques de livraison
  
## __User Stories réalisées__

* En tant qu'admin du drive, je peux gérer des quais de livraison pour organiser le retrait des commandes

```txt
L'admin pourra voir, ajouter, modifier ou supprimer des quais de livraison.
Un quai de livraison sera simplement identifié par son numéro (1 .. 30).
Afin de les représenter graphiquement sur un plan, nous leur associerons également une coordonnée (x, y) et une taille (longueur, largeur).
Pour simplifier la représentation, les emplacements seront forcément alignés avec les axes x et y (pas de rotation).
Une page permettra de visualiser ce plan des quais.
```

* En tant que client, je peux me présenter à une borne pour retirer ma commande

```txt
Le client arrive devant une borne qui lui propose de saisir son numéro de client.
Le système recherche la commande, et, si elle est prête à être livrée, assigne un numéro de quai disponible au client.
Vous réaliserez les écrans proposant cette interface d'accueil et d'orientation des clients.
Vous mettrez évidemment à jour les données nécessaires au traitement et à la livraison de la commande et des quais.
```

* En tant qu'employé, je peux visualiser la liste des commandes dont la livraison peut être effectuée afin de remettre les articles à un client

```txt
Si ce n'est pas déjà fait, l'employé pourra sélectionner son nom parmi une liste déroulante (issue d'une table des employés).
Cette information pourra être conservée en session et l'employé pourra se "déconnecter" quand il le souhaite.
L'employé choisit l'une des commandes pour laquelle il peut livrer les produits à un client en attente.
```

* En tant qu'admin, je peux avoir des statistiques sur les commandes retirées

```txt
L'admin doit pouvoir visualiser le nombre de commandes livrées par heure, pour une journée choisie.
Un compteur reprendra aussi le nombre total de commandes livrées et le chiffre d'affaire de la journée (toutes les commande livrées).
Une autre partie de la page montrera le nombre de commandes livrées par employé et par heure (par tranche d'une heure).
```

## __Ce que nous aurions fait avec plus de temps__

* Nous aurions réalisé la dernière user story
* Nous aurions ajouté du css à nos pages pour les rendre plus jolies
* Nous aurions laissé le soin aux clients de créer les commandes
* Nous aurions départagé les différents rôles (admin, client, employé)
