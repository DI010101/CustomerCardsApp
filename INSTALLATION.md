# Installationsanleitung

## Voraussetzungen

- **Android Studio** (neueste Version empfohlen)
- **JDK 8 oder höher**
- **Android SDK** (API Level 24-34)

## Schritte zur Installation

### 1. Android Studio installieren

Falls noch nicht installiert:
- Lade Android Studio von https://developer.android.com/studio herunter
- Installiere Android Studio und den Android SDK Manager
- Stelle sicher, dass SDK API Level 34 installiert ist

### 2. Projekt öffnen

1. Starte Android Studio
2. Klicke auf **"Open"** oder **"File → Open"**
3. Navigiere zum **CustomerCardsApp** Ordner
4. Wähle den Ordner aus und klicke auf **"OK"**

### 3. Gradle Sync

1. Android Studio wird automatisch eine Gradle-Synchronisation starten
2. Warte, bis alle Abhängigkeiten heruntergeladen sind
3. Falls Fehler auftreten, klicke auf **"Sync Now"** im Banner

### 4. App ausführen

**Option A: Auf einem echten Android-Gerät**
1. Aktiviere **"Entwickleroptionen"** auf deinem Android-Gerät:
   - Gehe zu Einstellungen → Über das Telefon
   - Tippe 7x auf "Build-Nummer"
2. Aktiviere **"USB-Debugging"** in den Entwickleroptionen
3. Verbinde dein Gerät per USB mit dem Computer
4. Klicke in Android Studio auf den **"Run"** Button (grünes Dreieck)
5. Wähle dein Gerät aus der Liste

**Option B: Im Emulator**
1. Klicke auf **"Device Manager"** in Android Studio
2. Erstelle ein neues virtuelles Gerät (z.B. Pixel 6 mit API 34)
3. Starte den Emulator
4. Klicke auf den **"Run"** Button

### 5. Berechtigungen erteilen

Beim ersten Start der App:
1. Die App fragt nach **Kamera-Berechtigung**
2. Tippe auf **"Zulassen"**, um die Scanner-Funktion nutzen zu können

## Projekt-Struktur

```
CustomerCardsApp/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/customercards/
│   │       │   ├── MainActivity.kt
│   │       │   ├── AddCardActivity.kt
│   │       │   ├── ScannerActivity.kt
│   │       │   ├── CardDetailActivity.kt
│   │       │   ├── CustomerCard.kt
│   │       │   ├── CardAdapter.kt
│   │       │   └── CardDatabaseHelper.kt
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   ├── values/
│   │       │   └── xml/
│   │       └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
└── settings.gradle
```

## Problembehandlung

### Gradle Sync schlägt fehl
- Überprüfe deine Internetverbindung
- Klicke auf **"File → Invalidate Caches"** und starte neu

### App startet nicht auf dem Gerät
- Stelle sicher, dass USB-Debugging aktiviert ist
- Überprüfe, ob das Gerät in der Geräteliste erscheint
- Versuche, das USB-Kabel neu zu verbinden

### Scanner funktioniert nicht
- Stelle sicher, dass die Kamera-Berechtigung erteilt wurde
- Überprüfe, ob die Kamera auf deinem Gerät funktioniert
- Im Emulator funktioniert die Kamera möglicherweise nicht richtig

### Build-Fehler
- Stelle sicher, dass du JDK 8 oder höher verwendest
- Überprüfe, ob alle Gradle-Abhängigkeiten heruntergeladen wurden
- Führe **"Build → Clean Project"** und dann **"Build → Rebuild Project"** aus

## APK erstellen (für Installation ohne Android Studio)

1. Klicke auf **"Build → Build Bundle(s) / APK(s) → Build APK(s)"**
2. Warte bis der Build abgeschlossen ist
3. Klicke auf **"locate"** um die APK zu finden
4. Übertrage die APK auf dein Android-Gerät
5. Installiere die APK (ggf. "Installation aus unbekannten Quellen" aktivieren)

## Nächste Schritte

Nach erfolgreicher Installation:
1. Öffne die App
2. Tippe auf den **+** Button
3. Füge deine erste Kundenkarte hinzu
4. Teste die Scanner-Funktion oder gib Codes manuell ein

Viel Spaß mit deiner Kundenkarten-App! 🎉
