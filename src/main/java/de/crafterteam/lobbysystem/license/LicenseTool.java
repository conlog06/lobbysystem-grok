package de.crafterteam.lobbysystem.license;

import java.nio.file.Path;
import java.security.PrivateKey;

/**
 * Offline-Lizenzgenerator: java -jar LobbySystem.jar create "<Inhaber>" [Tage] [private.key]
 */
public final class LicenseTool {

    public static void main(String[] args) {
        if (args.length < 2 || !"create".equalsIgnoreCase(args[0])) {
            System.out.println("Usage: java -jar LobbySystem.jar create \"<Inhaber>\" [Tage] [license-private.key]");
            System.out.println("  Tage default = 365");
            System.out.println("  Key-Datei default = license-private.key");
            System.exit(1);
        }
        String holder = args[1];
        int days = 365;
        if (args.length >= 3) {
            try {
                days = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.err.println("Ungültige Tage-Angabe: " + args[2]);
                System.exit(1);
            }
        }
        Path keyFile = Path.of(args.length >= 4 ? args[3] : "license-private.key");
        try {
            PrivateKey key = LicenseCodec.readPrivateKeyFile(keyFile);
            var license = LicenseCodec.create(key, holder, days, LicenseCodec.OWNER);
            System.out.println("=== Lizenz erstellt ===");
            System.out.println("Inhaber : " + license.holder());
            System.out.println("Ablauf  : " + license.expiresText());
            System.out.println("Key     : " + license.key());
            System.out.println();
            System.out.println("Trage den Key in die config.json unter \"license\" ein.");
        } catch (Exception e) {
            System.err.println("Fehler: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
