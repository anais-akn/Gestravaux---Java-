# 🏠 Bienvenue sur ImmoSync

> **La solution intelligente de gestion de chantiers et de mise en relation entrepreneurs.**

<p align="center">
  <img src="src/main/resources/images/logo.png" alt="Logo ImmoSync" width="200"/>
</p>

---

## 🚀 À propos du projet
**ImmoSync** est une plateforme JavaFX conçue pour simplifier la gestion immobilière. Elle permet de générer des devis types et de les proposer automatiquement aux entrepreneurs les plus pertinents grâce à un double algorithme :
1. **Expertise Métiers** : Filtrage automatique par catégories (Plomberie, Électricité, etc.).
2. **Proximité Géographique** : Calcul de distance via l'API Google Maps (rayon de 30 km maximum).

---

## 🔐 Accès Administration
Pour accéder à l'interface de gestion et tester les fonctionnalités, utilisez les identifiants par défaut :

* **📧 Adresse Email :** `admin@app.com`
* **🔑 Mot de passe :** `$2y$13$xyz`

---

## 📊 Configuration de la Base de Données

* **Script SQL :** Importez le fichier `app_db.sql` disponible à la racine du dépôt GitHub.
* **Nom de la base :** La base de données est nommée **`app_db`** dans le fichier `application.properties`.

### ⚙️ Paramètres de connexion
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/app_db
spring.datasource.username=votre_utilisateur
spring.datasource.password=votre_mot_de_passe