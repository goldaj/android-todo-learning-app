# Audit indépendant et passation — goldaj/android-todo-learning-app

Date 2026-10-30. Audit des références et PR GitHub ; tests applicatifs non exécutés.

## Branches et différence par rapport à main
Branche cible : `main` à `0c38a3254e84d1879ec4cbeaefb89d22d2690e35`.
- `chatgpt/review-20261024` (`11e730c76a393393d66572fefb6bf3cbdd8c938d`) : **behind**, 0 commit(s) exclusifs, 11 commit(s) derrière la cible. Historique absorbé : candidate au retrait après contrôle des références et de la PR.
- `feat/search-filter-edit-safe-ids-20261029` (`c68e33ea5c7a9a7b9747b84c33af656692331101`) : **behind**, 0 commit(s) exclusifs, 1 commit(s) derrière la cible. Historique absorbé : candidate au retrait après contrôle des références et de la PR.

## Pull requests
- [#2](https://github.com/goldaj/android-todo-learning-app/pull/2) — fusionnée, closed : feat: searchable Android to-do with safe SQLite IDs, editing and downloadable debug APK
- [#1](https://github.com/goldaj/android-todo-learning-app/pull/1) — fusionnée, closed : [ChatGPT] Corriger les tâches dupliquées et protéger SQLite

## Périmètre consulté
Racine : `.github`, `.gitignore`, `AGENTS.md`, `README.md`, `app`, `build.gradle`, `docs`, `gradle.properties`, `gradle`, `gradlew`, `gradlew.bat`, `settings.gradle`, `tests`.

README (extrait) : # Android To-do — application Java pédagogique

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

## Nouvelles capacités (2026-10-29)

Recherche, filtres Tout/À faire/Faites, compteur de tâches, tri par état et récence, modification de titre, confirmation de suppression. Les opérations SQLite ciblent l'identifiant `_ID` et n'affectent plus plusieurs tâches homonymes. Aucune migration de schéma. Lire [docs/TASK_MANAGEMENT_V2.md](docs/TASK_MANAG

AGENTS (extrait) : # Agent instructions — Android Todo

**Lire README.md puis docs/CHATGPT_PROJECT_HISTORY.md avant chaque changement.**

Projet : Application Android Java pédagogique avec tâches en SQLite, interfaces XML et Gradle ancien.

Règles : Ouvrir dans Android Studio, compiler par gradlew.bat assembleDebug ; SDK32 min API21. Protéger local.properties et signatures.

Préserver la provenance et ne jamais écraser les versions existantes. Documenter ce qui a réellement été testé. Après chaque modification, enrichir docs/CHATGPT_PROJECT_HISTORY.md si une instruction, décision ou nouveau dialogue pertinent est disponible ; séparer citations exactes des résumés et noter les dates seulement si certaines. Ne publier ni secrets ni données privées.

## Évolution 2026-10-29

Les tâches SQLite sont désormais identifiées par `_ID`, jamais par leur titre. Rechercher, filtrer et trier via `domain/TaskRules.java`, maintenir `TaskArrayAdapter` avec `row.setTag(id)` pour éviter les bugs avec titres dupliqués et recyclage des vues. Lire `docs/TASK_MANAGEMENT_V2.md`, exécuter les tests Java purs, puis valider buil

## Challenge concret pour prochain agent
1. Reproduire la recherche, les filtres et la modification de tâches sur le poste Android réel ; vérifier rotation, relance, migrations SQLite, entrées vides et identifiants persistants.
2. Vérifier génération APK, tests unitaires et logs CI, en distinguant exécution réelle et tests simulés.
3. En cas de retrait des deux branches historiques, vérifier avant suppression qu'aucune PR ouverte ni automatisation n'en dépend.
4. Aucune modification de code ni merge effectuée dans cet audit ; ne pas déduire une qualité de production du seul graphe Git.
