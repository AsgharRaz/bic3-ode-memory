# MEMORY

Ein einfaches Spiel implementiert mit JavaFX von

Thomas Halwax (ic14b050) und Thomas Zeitinger (ic14b033)

im Rahmen der LVA Objektorientierte Dienstentwicklung, WS2015, Technikum Wien

Memory ist eine Implementierung des bekannten Brettspiels "Memory", bei dem Kartenpaare mit dem selben Bild, Symbol
,... gefunden werden müssen.

Bei diesem Spiel können die Bilder für die Kartenpaare selbst gewählt werden. Beim Start muss der Benutzer einen Ordner
im Dateisystem öffnen, der die zu verwendenden Bilder (.jpg oder .png) enthält. Die Bilder werden in zufälliger
Reihenfolge gewählt, so dass bei jedem neuen Start andere Kartenpaare zu finden sind (sofern die Anzahl der verfügbaren
Bilder groß genug ist).

Der Benutzer kann weiterhin die Schwierigkeitsstufe bestimmen:
Leicht: es sind 4 Paare zu finden
Mittel: 6 Paare
Schwer: 8 Paare

Gefundene Paare bleiben aufgedeckt und werden nicht - so wie beim Brettspiel - vom Spielfeld entfernt.

