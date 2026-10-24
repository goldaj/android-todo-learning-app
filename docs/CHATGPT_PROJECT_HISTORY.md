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

## 2026-10-24 — Corrections fonctionnelles sur une branche parallèle

**Utilisateur (résumé reconstruit, non verbatim, heure inconnue)** : demande de privilégier la correction des bugs et la poursuite des fonctionnalités incomplètes plutôt que la structure des dépôts.

**Assistant (résumé reconstruit, non verbatim)** : les lignes conservent désormais leur ID SQLite, les actions « terminée », « à faire » et « supprimer » utilisent cet ID, de sorte que deux tâches au titre identique restent indépendantes. Une mise à jour de base sans stratégie de migration explicite échoue plutôt que de détruire les tâches. Branche `chatgpt/review-20261024` seulement.

**Validation** : compilation Android soumise à GitHub Actions sur la PR ; aucun test manuel sur téléphone et aucune modification de la branche principale.
