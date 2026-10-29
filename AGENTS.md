# Agent instructions — Android Todo

**Lire README.md puis docs/CHATGPT_PROJECT_HISTORY.md avant chaque changement.**

Projet : Application Android Java pédagogique avec tâches en SQLite, interfaces XML et Gradle ancien.

Règles : Ouvrir dans Android Studio, compiler par gradlew.bat assembleDebug ; SDK32 min API21. Protéger local.properties et signatures.

Préserver la provenance et ne jamais écraser les versions existantes. Documenter ce qui a réellement été testé. Après chaque modification, enrichir docs/CHATGPT_PROJECT_HISTORY.md si une instruction, décision ou nouveau dialogue pertinent est disponible ; séparer citations exactes des résumés et noter les dates seulement si certaines. Ne publier ni secrets ni données privées.

## Évolution 2026-10-29

Les tâches SQLite sont désormais identifiées par `_ID`, jamais par leur titre. Rechercher, filtrer et trier via `domain/TaskRules.java`, maintenir `TaskArrayAdapter` avec `row.setTag(id)` pour éviter les bugs avec titres dupliqués et recyclage des vues. Lire `docs/TASK_MANAGEMENT_V2.md`, exécuter les tests Java purs, puis valider build Android réellement avant fusion. Toute future migration SQLite doit être non destructive.

Le workflow `android-build.yml` doit créer un vrai APK Debug **et** publier l'artefact CI `android-todo-debug-apk`. Ne pas assimiler les tests Java purs au build Android ni l'APK compilé à un essai utilisateur réussi.
