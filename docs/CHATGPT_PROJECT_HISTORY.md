# Historique de projet ChatGPT — Android Todo

Archive de transmission à Codex créée le 2026-10-24, **non exhaustive**. Aucun export intégral des anciens échanges n'est disponible dans cette session.

## Périmètre vérifié dans README.md
Application Android Java pédagogique avec tâches en SQLite, interfaces XML et Gradle ancien.

## Règles de reprise
Ouvrir dans Android Studio, compiler par gradlew.bat assembleDebug ; SDK32 min API21. Protéger local.properties et signatures.

## Échanges attestés
- **2026-10-18** — Assistant (résumé reconstruit, non verbatim) : regroupement d'archives d'apprentissage/prototypes dans des dépôts distincts, conservation de la provenance et documentation honnête du niveau de validation.
- **2026-10-18 13:24:13 UTC** — Utilisateur (extrait verbatim) : « N'écrase aucun repo existant. »
- **2026-10-24** — Utilisateur (résumé reconstruit, non verbatim) : demande un AGENTS.md propre au dépôt, un historique lisible par Codex avec messages utilisateur/assistant et horodatages disponibles, ainsi qu'une mise à jour récurrente de la documentation.

## Limites et maintenance
Les transcriptions exactes des échanges précédents et leurs horaires ne sont pas disponibles ici ; aucune réponse de l'assistant n'a été inventée. Lorsque le propriétaire fournit des exports authentiques, conserver la distinction rôle/date/texte et vérifier les informations sensibles avant commit. Ajouter les nouveaux échanges sourcés en fin de document, sans réécrire le passé.

## 2026-10-29 : ergonomie et identité des tâches

Utilisateur (verbatim, heure précise indisponible) : « Je ne te demande pas de créer une tâche planifiée qui continue chaque jour. Je te demande de continuer maintenant en boucle et de ne pas t'arrêter sur juste 3 améliorations alors qu'il y a plus de 40 repo et plusieurs améliorations possibles par repo ».

Assistant (résumé non verbatim) : évolution de l'app Android native sans migration destructive, recherche/filtre/édition/suppression confirmée ; correction des mutations sur titre ambigu en ciblage SQLite `_ID`. Ajout de règles pures testables en JDK et doc dans `docs/TASK_MANAGEMENT_V2.md`. Le build et les essais appareil restent à confirmer.
