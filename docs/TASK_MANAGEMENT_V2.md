# Android To-do — fonctionnalités 2026-10-29

## Apports réels
L'application Android Java permet maintenant de **rechercher les tâches**, filtrer toutes/à faire/faites, afficher les statistiques, trier d'abord les tâches à faire puis les plus récentes, **éditer** un titre et **confirmer** les suppressions.

**Correctif de données important :** l'ancienne application identifiait une tâche par son titre. Deux tâches homonymes pouvaient être modifiées/supprimées ensemble. Toutes les mutations utilisent désormais la clé SQLite `_ID` déjà présente depuis la version 1. Aucune migration destructrice du schéma n'est nécessaire. Les anciennes tâches restent conservées.

## Essayer
1. Ouvrir le dépôt dans Android Studio avec le SDK 32 et le JDK/Gradle compatibles de l'ancien projet.
2. Compiler avec `./gradlew assembleDebug` ou sur Windows `gradlew.bat assembleDebug`.
3. Créer deux tâches du même nom ; marquer une seule comme faite, modifier et supprimer une seule.
4. Chercher un mot, sélectionner filtres, vérifier le nombre de résultats.
5. Tourner le téléphone : la recherche est gérée par le widget Android et le filtre sauvegardé en état d'instance.

La CI ajoute des tests Java purs du moteur `TaskRules`. **Elle ne construit pas l'APK Android**, et aucune validation appareil n'est certifiée à ce stade.

## Architecture
`domain/TaskRules.java` : nettoyage des titres, recherche, filtrage et tri purs (sans SDK Android). `domain/Task.java` contient dorénavant l'ID immuable. `MainActivity.java` effectue les opérations SQLite avec clause `_ID = ?` et gère les contrôles. `TaskArrayAdapter.java` associe les boutons à l'ID de la ligne, même avec recyclage de la vue. Les layouts restent natifs.

## Suites pour Codex
- Créer un vrai test Android instrumentation avec base de données temporaire et migrations réalistes.
- Ajouter catégories et dates d'échéance par migration SQLite non destructive, avec test upgrade.
- Ajouter export/import local JSON et une sauvegarde avant migrations.
- Revoir les chaînes de texte, descriptions d'accessibilité et styles sur écrans compacts.
- Mettre à niveau Gradle/SDK/targetSdk prudemment, en validant un build et le cycle de vie Android.
