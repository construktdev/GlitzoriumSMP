# GlitzoriumSMP Plugin

> [!IMPORTANT]
> Dieses Plugin kann nicht ohne Änderungen auf deinem Server genutzt werden!
> Deswegen steht es nicht als Binary zur Verfügung

## Key Features:
- HelpOP System für leichtere Moderation
  - Discord Logging Implementation
    - In der config.yml muss dafür ein token festgelegt werden, und in der Main-Klasse der Logging-Kanal
  - warn, mute, kick und ban command
  - AutoMod für einen sauberen Chat
- Short Commands
  - Commands die oft eingegeben werden müssen sind verkürzt
  - Zurzeit implementiert: /day, /night, /sun, /gm 0|1|2|3
  - Außerdem gibt es den /lobby bzw. /hub command, um in die Lobby zu wechseln
- CraftAttack Ähnlich
  - Es gibt das gleiche /status System wie in CraftAttack
  - Vom Spawn aus kann man mit einer Einweg-Elytra und einem Boost fliegen, um das travelling vom Spawn zu vereinfachen
  - In der TabList werden hinter dem Namen die entsprechenden Tode angezeigt
- Kompatibilität
  - Dieses Plugin ist darauf ausgelegt das Spielerlebnis auch für Bedrock Spieler so gut wie möglich ist
  - Falls der Server unsigned chatting nicht zulässt, gibt es einen /chat Command, um trotzdem zu chatten
  - Es gibt eine Namen-basierte Whitelist, um auch Bedrock Spieler whitelisten zu können
 

## Aufsetzen
0. Repo clonen
1. In der Klasse GlitzoriumSMP.java die Kanal-ID zum logging Kanal setzen (am Ende der onEnable())
2. In der Klasse Prefix.java (package: utils) nötige Prefixes ändern
3. In der Klasse SpawnBoostListener.java den SpawnRadius und Boost Faktor für die Spawn Elytra ändern
4. Gegebenenfalls in BadWords.java den AutoMod Filter ändern (getBadWords())
5. Das Plugin kompillieren <br>
   5.1 Mit IntelliJ: <br>
     5.1.1 Auf das Maven Symbol (m) Rechts in der Navigationsleiste klicken <br>
     5.1.2 Lifecycle erweitern <br>
     5.1.3 Doppelclick auf 'package' <br>
     5.1.4 Aus dem Ordner /target die Datei 'GlitzoriumSMP-1.0-SNAPSHOT-shaded.jar' kopieren

## Bei Fragen
https://glitzorium.de/support <br>
https://glitzorium.de/discord
