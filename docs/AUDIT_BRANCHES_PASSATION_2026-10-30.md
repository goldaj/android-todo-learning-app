# Audit GitHub et passation inter-agents — 30 octobre 2026

## Périmètre et niveau de preuve
Lecture de `README.md`, `AGENTS.md`, arborescence documentaire, références Git et historique des PR via GitHub. **Aucun test de compilation Android ni essai sur appareil exécuté pendant cette passe.** Ce document est un audit statique ; les consignes plus récentes de `AGENTS.md` et `docs/CHATGPT_PROJECT_HISTORY.md` priment.

## Mission et points à challenger
Application pédagogique Android/SQLite. Les tâches sont référencées par identifiant SQLite `_ID`, non par titre. Vérifier collisions de titres, édition après filtrage, recyclage des vues, migration non destructive, restauration et durée de vie Android. Le workflow `android-build.yml` doit **réellement** compiler un APK de débogage et publier l'artefact `android-todo-debug-apk` ; distinguer tests Java purs, build Android et essai réel. Vérifier SDK/Gradle avec l'outillage compatible et ne jamais exposer les réglages ou signatures locales.

## Branches relevées au moment de l'audit (base `main`)
| Branche | SHA | Écart Git depuis la base | Décision de reprise |
|---|---|---|---|
| `main` | `20a0d9842ae7` | Branche cible | Conserver |
| `chatgpt/review-20261024` | `11e730c76a39` | behind, ahead 0, behind 12 | Historique intégralement inclus ; candidate au nettoyage après vérification des usages externes. |
| `feat/search-filter-edit-safe-ids-20261029` | `c68e33ea5c7a` | behind, ahead 0, behind 2 | Historique intégralement inclus ; candidate au nettoyage après vérification des usages externes. |

## Pull requests
- [#2](https://github.com/goldaj/android-todo-learning-app/pull/2) : fusionnée ; `feat/search-filter-edit-safe-ids-20261029` vers `main`.
- [#1](https://github.com/goldaj/android-todo-learning-app/pull/1) : fusionnée ; `chatgpt/review-20261024` vers `main`.

## Prochain agent : ordre de travail
1. Lire `docs/CHATGPT_PROJECT_HISTORY.md`, `AGENTS.md` et `docs/TASK_MANAGEMENT_V2.md` ; comparer les règles historiques avec le code effectivement présent.
2. Exécuter les tests Java purs puis un vrai `gradlew.bat assembleDebug` en environnement Android SDK compatible. Contrôler les rapports et la présence de l'APK dans CI ; ne pas extrapoler.
3. Tester manuellement ajout/recherche/filtre/édition/suppression avec titres dupliqués, redémarrage et migrations SQLite.
4. Une branche entièrement ancêtre de `main` ne doit pas être fusionnée à nouveau. Vérifier les PR, les liens externes et les déploiements avant renommage `a_supprimer/` puis suppression. **Aucun renommage ni suppression effectué ici.**

## Documentation existante observée
- `docs/AUDIT_PASSATION_2026-10-30.md`
- `docs/CHATGPT_PROJECT_HISTORY.md`
- `docs/TASK_MANAGEMENT_V2.md`

## Traçabilité
Source : GitHub API, snapshot du 30/10/2026. Aucun commit produit modifié ; uniquement ce document. Tests non exécutés dans cette passe. Aucune validation de production implicite.
