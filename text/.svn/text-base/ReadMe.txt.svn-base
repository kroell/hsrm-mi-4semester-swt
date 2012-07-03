ReadMe

Beschreibung der Formate fuer die Dateien quest.dat, npc.dat und map.dat.

quest.dat:
	
	Jede Quest hat eine eigene, forlaufende ID gefolgt von einem Integerwert, 
	der das Semester angibt, in welcher die Quest spilbar ist. Getrennt 
	werden sie durch einen Doppelpunkt. Anschließend folgt der Questname.
	
	Beispiel:
	1:1 Questname
	
	Anschließend folgt zeilenweise der Dialog der Quest. Die ersten beiden Integerwerte werden 
	wie zuvor gesetzt. Es folgt aber jetzt noch eine ID fuer den Dialog. Der erste Part des Dialogs 
	bekommt immer die ID eins. 
	
	Beispiel:
	1:1:1 Guten Tag.
	
	Am Ende eines Dialogs kann eine Aktion folgen. Dies wird durch einen Tag 
	deutlich gemacht.
	
	verfuegbare Aktionen:
	#END%10						Questende, der Spieler erhaelt 10 CreditPoints
	#ITEM%Block/Stift   		Die Items mit dem jeweiligen Attribut name=="Block" und name=="Stift" muessen gefunden werden.
	#AREA%0/0/0					Begebe dich zum Feld mit den Koordinaten 0,0,0
	#SHOOT%20/2					Besiege in 20 Sekunden 2 Jawas.
	#BARTH						Besiege Barth.
	
	Beispiel:
	1:1:1 Guten Tag. Finde die Kaffemaschine. #ITEM%Kaffemaschine
	
	Die Beschreibung einer Quest endet mit einer Zeile die ausschließlich das 
	Zeichen '-' enthaelt. 
	
npc.dat:
	
	Ein NPC in 
	der Spielwelt entspricht einem Eintrag in der npc-dat-Datei. 
	Es gibt freundliche und feindlich gesinnte NonPlayerCreatures.  Erstere werden mit 
	P (pleasant) abgkuerzt, waehrend die zweite Gruppe mit H (hostile) abgekuerzt wird. 
	Anschließend folgen immer vier Attribute: Zuerst die int-Werte fuer die x- und die z- 
	Position, dann die Drehung gegenueber der Ausgangsposition (Float-Wert zwischen 0 und 1)
	und zuletzt die Pfadangabe zum Bild, mit welchem der NPC dargestellt wird. Getrennt werden 
	die Attribute durch '%'. 
	
	Beispiel: 
	 
	H%1%2%1.0f%image/jawa.png 					Ein hostile NPC mit dem Bild eines Jawas, der auf dem Feld 1,2 steht 
												und um 90 Grad gedreht ist. 
												
map.dat:
	
	Die Map ist ein dreidimensionales Array aus Areas. Ein Eintrag in der map.dat sieht zum 
	Beispiel folgendermaßen aus:

	
	wf%null%false%false
	wb%null%false%false
	wr%image/wall.jpg%false%true
	wl%image/wall.jpg%false%true
	wfloor%image/floor.jpg%false%true
	wsky%image/ceiling.jpg%false%true
	quest%null
	item%Block%image/block.png%0
	map%0%0%0
	
	Zuerst werden die sechs Waende wf, wb, wr, wl, wfloor und wsky eines Feldes angegeben. 
	Das erste Attribut steht fuer den Pfad zum Bild. Der Wert ist null, wenn keine Wand sichtbar ist.
	Danach folgen zwei boolsche Werte fuer die Transparenz und Festigkeit. 
	
	Danach kommt eine Zeile fuer die Quest. Startet keine Quest in dem Area wird 'null' 
	angegeben, ansonsten die ID der Quest.
	
	Dann folgt die Angabe des Items, welches zu Spielbeginn auf dem Feld platziert ist. 
	Die Attribute entsprechen in dieser Reihenfolge dem Namen, dem Pfad zum Bild und der 
	Drehung (Float zwischen 0 und 1). Ist kein Item platziert, reicht die Angebe 'item%'. 
	
	Zum Schluss werden noch die Koordinaten des gerade spezifierten Areas angegeben.  
	
	
	
	///////
image/gate.png%true%false

null%false%false

wfloor%image/floor2.jpg%false%false
wsky%image/ceiling.jpg%false%false

image/ceiling.jpg%false%false

image/wall.jpg%false%true

image/floorOut.jpg%false%false

wr%image/wall.jpg%false%true
wl%null%false%false

wr%image/window1.png%true%true
wl%null%false%false