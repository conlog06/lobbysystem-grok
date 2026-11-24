# LobbySystem – Paper 1.21.10 bis 26.3 (Grok Edition)

Modernes LobbySystem mit Navigator, Cosmetics, Daily-Reward-Hexe, Einstellungs-Dorfbewohner,
Lotterie, Scoreboard, Tablist, Bossbar, Actionbar und Lizenzsystem.

**Download:** [Releases → latest](https://github.com/conlog06/lobbysystem-grok/releases/tag/latest) (wird bei jedem Push automatisch gebaut)

**Author / Owner-Lizenz:** SujucDerBreite

## Was ist neu gegenüber der 1.16.5 – 1.18.1 Version?

| Bereich | Alt | Neu (1.21.10 – 26.3) |
|---|---|---|
| Schrift / Farben | `§`-Codes | **MiniMessage**: Gradients, Hex-Farben, Hover/Klick, `ꜱᴍᴀʟʟ ᴄᴀᴘꜱ`-Schrift (alte `§`/`&`-Codes funktionieren weiterhin) |
| Prefix | `§7» §2…` | `<gradient:#ff6b00:#ffb347><b>ɢʀᴏᴋ</b></gradient> »` – komplett in der config.json |
| Scoreboard | rote Zahlen rechts | **ohne Zahlen** (NumberFormat.blank) |
| Hologramme | Armorstands | **TextDisplay** mit Hintergrund & Billboard |
| Ballone | Armorstand + Leine | **BlockDisplay** mit flüssiger Interpolation |
| Item-Glanz | Fake-Verzauberung | Enchantment-Glint-Override |
| Gadgets | – | u. a. **Windstoß** (Wind Charge, 1.21) |
| Doppelsprung | Wolke | Breeze-Sound + Gust-Partikel |
| Speicher | nur MySQL | MySQL **oder** lokal SQLite; Coins werden als Differenz gespeichert (mehrere Lobbys gleichzeitig sicher) |

## Commands

| Befehl | Beschreibung |
|---|---|
| `/build [Spieler]` | De-/aktiviert den Baumodus |
| `/lobbycoins add\|remove\|set\|get <Spieler> [Coins]` | Coins vergeben, entfernen, setzen oder anzeigen (auch offline) |
| `/setreward [remove]` | Setzt die Daily-Reward Hexe oder entfernt sie |
| `/setsettings [remove]` | Setzt den Einstellungs-Dorfbewohner oder entfernt ihn |
| `/setspawn` | Setzt den Spawn-Punkt |
| `/setwarp <Name>` · `/setwarp remove <Name>` · `/setwarp list` | Warp-Punkte für den Navigator |
| `/warp <Name>` · `/spawn` | Teleport zu Warp / Spawn |
| `/tpcoord <x> <y> <z> [Welt] [Yaw] [Pitch]` | Teleport zu Koordinaten (`~` relativ möglich) |
| `/lobbysystem reload\|info` | Konfiguration neu laden / Infos |
| `/license create\|list\|verify` | **Nur SujucDerBreite** – Lizenzen verwalten |

## Permissions

Die Permissions sind frei in der `config.json` einstellbar. Die gesamte Permission setzt sich aus
`prefix` + `.` + jeweiligem Suffix zusammen:

```json
"permissions": {
  "prefix": "lobbysystem",
  "suffix_build": "build",
  "suffix_coins": "coins",
  "suffix_bypass": "bypass"
}
```

→ Baumodus = `lobbysystem.build`. Ein **leerer** Suffix bedeutet: jeder darf es (Standard beim Doppelsprung).
Ränge werden in der `ranks.json` über die dort eingetragene Permission vergeben (z. B. `lobbysystem.rank.admin`).

## Installation (Paper / Spigot-Fork)

1. JAR aus den Releases herunterladen.
2. In den `plugins`-Ordner der Lobby-Server legen.
3. Server starten → `plugins/LobbySystem/config.json` wird erstellt.
   * `license` eintragen (Lizenz gibt es nur von **SujucDerBreite**).
   * MySQL-Daten unter `mysql` eintragen und `enabled: true` setzen (gleiche DB wie die anderen Server). Ohne MySQL wird lokal SQLite genutzt.
4. Server neu starten.
5. Optional: Ränge in `ranks.json` anpassen, Nachrichten in der `config.json` ändern.

### CloudNet (v3/v4)

1. JAR nach `local/templates/Lobby/default/plugins/` (v4) bzw. `local/templates/Lobby/plugins/` (v3).
2. Gruppe starten → Config in `temp/Lobby-X/plugins/LobbySystem/` bearbeiten (Lizenz + MySQL).
3. Geänderte Config in den Template-Ordner kopieren und Gruppe neu starten.

## Navigator konfigurieren

In der `config.json` unter `navigatorItems`, eine Zeile pro Item:

```
Slot;MATERIAL;Ziel;Name;Lore
```

- **Ziel:** `warp:<Name>` · `connect:<Server>` (BungeeCord/Velocity) · `spawn` · `command:<Befehl>`
- Mehrere Lore-Zeilen mit `\n` trennen.

Beispiel: `"12;IRON_AXE;connect:CityBuild-1;<dark_gray>»</dark_gray> <dark_green>ᴄɪᴛʏʙᴜɪʟᴅ;<gray>Klicke zum Joinen"`
(alte Schreibweise `§7» §2CityBuild` funktioniert auch).

## Lizenzsystem

- Lizenzschlüssel sind mit **Ed25519** signiert. Im Plugin steckt nur der öffentliche Schlüssel –
  **nur wer die `license-private.key` besitzt (SujucDerBreite), kann Lizenzen erstellen.**
- Owner-Server: `license-private.key` nach `plugins/LobbySystem/` legen → Plugin läuft ohne Lizenz
  und `/license create <Inhaber> [Tage]` ist für den Spieler **SujucDerBreite** freigeschaltet.
- Offline ohne Server: `java -jar LobbySystem-2.0.0.jar create "<Inhaber>" [Tage] license-private.key`
- Die `license-private.key` **niemals** hochladen oder weitergeben (ist per `.gitignore` ausgeschlossen).

## Build

```bash
mvn package
```

Java 21, kompiliert gegen `paper-api 1.21.10` – läuft auf allen Paper-Versionen bis 26.3.

## Grok-Anpassungen

- Prefix & Gradients auf **Grok-Orange** (`#ff6b00` → `#ffb347`) umgestellt
- Server-Name & Tablist/Scoreboard-Titel auf „ɢʀᴏᴋ ʟᴏʙʙʏ“
- MiniMessage + Small-Caps durchgängig
- Alle modernen 1.21.10–26.3 Features (TextDisplay, BlockDisplay, Wind Charge, Breeze-Partikel, NumberFormat.blank) beibehalten
