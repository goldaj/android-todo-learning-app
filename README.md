# Première application Android : liste de tâches

Application pédagogique Android écrite en Java. Elle permet d'ajouter une tâche, de la marquer comme faite ou à faire et de la supprimer. Les tâches et leur état sont conservés localement avec SQLite.

## Ouvrir le projet

Ouvrir ce dossier dans Android Studio, installer les composants Android SDK demandés et laisser Gradle synchroniser les dépendances. La configuration historique cible Android SDK 32, un minimum API 21 et le plugin Android Gradle 7.3.1.

Pour construire depuis Windows avec le SDK et le JDK compatibles configurés :

```powershell
.\gradlew.bat assembleDebug
```

La compilation et l'exécution sur un appareil restent à vérifier avec un environnement Android configuré.

## Points à examiner

- `MainActivity.java` : interactions et accès à la base.
- `TaskArrayAdapter.java` : affichage des éléments de la liste.
- `db/` : contrat et création de la base SQLite.
- `domain/` : tâche et état.
- `app/src/main/res/` : interface XML et ressources.

`local.properties`, les clés de signature, les paramètres IDE et les fichiers de construction restent locaux et sont ignorés par Git.
