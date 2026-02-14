# Kundenkarten App

Eine Android-App zum Verwalten digitaler Kundenkarten.

## Features

✅ **Karten erstellen**: Erstelle digitale Versionen deiner Kundenkarten
✅ **Manuelle Eingabe**: Gib Barcode- oder QR-Code-Nummern manuell ein
✅ **Scanner-Funktion**: Scanne bestehende Karten mit der Handy-Kamera
✅ **Mehrere Formate**: Unterstützt Barcodes (EAN-13, CODE-128, etc.) und QR-Codes
✅ **Code-Anzeige**: Zeigt scanbare Barcodes/QR-Codes zum Vorzeigen an der Kasse
✅ **Speicherverwaltung**: SQLite-Datenbank für lokale Speicherung

## Unterstützte Barcode-Formate

- QR-Code
- EAN-13 (Standard-Barcodes wie bei DM, Rossmann)
- EAN-8
- CODE-128
- CODE-39
- UPC-A
- UPC-E
- ITF
- CODABAR

## Installation

1. Projekt in Android Studio öffnen
2. Gradle Sync durchführen
3. App auf einem Gerät oder Emulator starten

## Anforderungen

- Android 7.0 (API Level 24) oder höher
- Kamera-Berechtigung für Scanner-Funktion

## Verwendung

### Neue Karte hinzufügen

1. Tippe auf den **+** Button
2. Gib einen Namen ein (z.B. "DM", "REWE")
3. Wähle den Code-Typ (Barcode oder QR-Code)
4. Entweder:
   - Gib die Nummer manuell ein, oder
   - Tippe auf "Code scannen" und scanne die Karte

### Karte verwenden

1. Tippe auf eine Karte in der Liste
2. Der Barcode/QR-Code wird angezeigt
3. Halte das Display an das Lesegerät an der Kasse

### Karte löschen

1. Öffne die Karte
2. Scrolle nach unten
3. Tippe auf "Karte löschen"

## Technologie-Stack

- **Sprache**: Kotlin
- **UI**: Material Design Components
- **Datenbank**: SQLite
- **Barcode-Scanner**: ZXing (Zebra Crossing)
- **Barcode-Generierung**: ZXing Core

## Berechtigungen

- `CAMERA`: Zum Scannen von Barcodes und QR-Codes

## Lizenz

Dieses Projekt ist für private Nutzung gedacht.

## Haftungsausschluss

Die App speichert Kundenkartendaten lokal auf dem Gerät. Stelle sicher, dass du berechtigt bist, die Daten deiner Kundenkarten zu digitalisieren.
