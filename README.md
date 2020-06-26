# MAP Invaders
## Presentazione dell'architettura del sistema

Quando il prof. ha chiesto un progetto interamente sviluppato in java ho subito pensato ad un videogioco. Space Invaders è un classico videogioco semplice da realizzare, ma che facilmente riesce a contenere tutti i concetti base di Metodi Avanzati di Programmazione.

Dall'utilizzo della classe astratta Sprite, per mettere a fattor comune alcune caratteristiche (attributi) e modi d'uso (metodi) all'override del metodo move() per far muovere in maniera differente ogni oggetto istanziato (a seconda poi del ruolo nel videogioco)

Anche se non è molto didattico, da quando alle superiori studiavo i fondamenti dell'informatica (variabili, cicli, iterazioni semplici) ho sempre associato il tutto ad un videogioco, e anche studiando la teoria di MAP, negli esempi che ripetendo facevo a me stesso ritrovavo tante nozioni importanti, come quelle inerenti all'ereditarietà rispetto alle superclassi o in generale sulle nozioni di progettazione del software.

## Iniziamo - i Package e le Classi del gioco

Proverò a fornire direttamente qui (per il prof o per chiunque ne abbia bisogno in futuro) alcune informazioni rispetto a quanto sviluppato, cosa dovete conoscere per realizzarlo e qualche esempio reale (screen / gif) ogni tanto per rendere tangibile ciò di cui parlo.
###### App
App contiene il main, quindi è da qui che avviamo la nostra applicazione, semplicemente istanzia l'oggetto GameMainFrame, che come vedremo dopo, serve ad inizializzare l'istanza di gioco.

###### Constants
Come già detto all'inizio, ho immaginato delle figure dinamiche all'interno del videogame, in realtà l'unica cosa "statica" è lo sfondo...

Tuttavia, anche se non si direbbe, molte componenti sono assegnate in maniera statica nella classe 'Constants' appunto, e sono tutti valori inerenti a velocità di gioco, messaggi che appaiono durante quest'ultimo (vittoria o game over) e alcuni pre-settaggi rispetto alle dimensioni di ciò che accade nel 'canvas' (<-- tavolo di gioco, per intenderci)

In alcuni casi, come nel caso dei messaggi che provengono dal sistema, mi è sembrata senz'altro una scelta adatta, poichè gli unici momenti in cui l'istanza li prevede sono a "fine partita", e ciò accade più o meno sempre allo stesso modo.

In altri casi si è trattato di mantenere una "solidità" di progettazione, istanziare nuove variabili in maniera dinamica può generare esponenzialmente errori o problemi, e in questo caso ho preferito andare sul sicuro e focalizzarmi su altri aspetti. (anche perchè risulta piuttosto semplice rendere la velocità incrementale piuttosto che cambiare il numero dei nemici)

###### Callbacks
L'unica classe di questo package è l'EventListener, ovvero un estensione della superclasse 'KeyAdapter'.
Ereditandone in questo modo i metodi, EventListener riesce ad "ascoltare" e monitorare quindi il gioco una volta avviato. Istanzia il GamePanel e grazie ai due metodi keyReleased e keyPressed riesce a gestire i tasti che inseriamo (ovviamente al netto di una buona sintassi quando ci serviranno)

###### Image
Image riguarda la parte "grafica" del videogioco, infatti nella classe **Image**semplicemente dichiariamo un nuovo tipo (ovvero Image) che prende le proprietà da enum, inserendo in maniera "statica" gli elementi che poi ci ritroveremo sul canvas, ovvero UFO,BOMB,SPACESHIP etc.
**ImageFactory** invece contiene il metodo per "creare" sul pannello le immagini, con uno switch che assegna le .png varie (create sempre da me :) vi piacciono?) ai vari elementi del tipo Image dichiarato in Image.java

###### Model
Siamo ad una delle due parti più importanti dello sviluppo del videogioco (assieme alla successiva, la UI).
Innanzitutto ogni classe indica un tipo di elemento che ritroveremo nel canvas. 
Ognuna di loro ha delle caratteristiche e dei metodi precisi, che in alcuni casi sono "nuovi", privati (come nel caso di initialize() per inizializzare la nave del giocatore) ed interni alla classe.
In altri casi (come in move() ovvero il metodo che permette ad un elemento di muoversi) vengono ereditati dalla classe astratta **Sprite**
Le classi astratte, come ho imparato dal corso di MAP, sono classi che servono come "repository" di metodi e di informazioni che ci serve ereditare su molteplici sottoclassi che istanziano tramite i loro costruttori oggetti con alcune caratteristiche simili. **Sprite**on può istanziare oggetti, e non viene mai utilizzata nell'applicazione, se non per questo motivo (ereditarietà).
Un altra serie di metodi importantissimo ereditati da Sprite sono getX e getY (oltre che setX e setY) che servono a gestire le coordinate dei vari elementi! Tutto il gioco si basa su questo ed è stata la parte di sviluppo più complicata.

###### UI (User Interface)
Punto focale del progetto, ricco di codice e di ore perse per errori vari, la UI contiene tra le altre classi il GamePanel, che scoprirete presto essere il vostro "campo dove smanettare" per rendere tutti i package costruiti prima utili ed utilizzabili.
Prima di approfondire il **GamePanel** però, con ordine, descriverei il **GameLoop**, la classe che ci permette di avere un gioco "animato" in tempo reale.
Implementando ActionListener ci permette di monitorare ciò che succede, ovvero le azioni dell'utente.
Utilizza un @Override, ovvero un tipo di ri-definizione del metodo actionPerformed, effettuando così dei Loop (vedremo tra poco cosa viene looppato) sul GamePanel.

**GameMainFrame**, altra classe appartenente al package ui, inizializza il layout di gioco (importando la libreria 'swing.JFrame' e settando il titolo del gioco (costante pre-definita) sull'oggetto GamePanel appena istanziato in layout.
Inoltre qui si stabiliscono alcune caratteristiche dell'ambiente di gioco, ovvero come viene chiuso ed il fatto che non possa essere ridimensionato.

**GamePanel** è la piattaforma di gioco, estensione di JPanel, è colma di dichiarazioni per istanziare successivamente la nave del giocatore, quelle nemiche, gli scudi, i messaggi di vittoria e game over, il timer per il loop etc.
Sostanzialmente inizializza 3 cose: le variabili, il board ed il game all'interno.
InitializeVariables è costruttore di tutti gli elementi dinamici: dall'arraylist per il gruppo di nemici, al laser, alla nave del player.
InitializeBoard è costruttore di tutti gli elementi relativi al canvas di gioco, e grazie ad un KeyListener rende il gioco "in ascolto" rispetto alla nostra pressione dei tasti (vedremo dopo come)
Istanzia inoltre il timer, passando il valore della game_speed
InitializeGame istanzia le spaseship nemiche, inserendole nell'Arraylist in maniera inerente a quanto dichiarato in Constants (anche questa cosa poteva facilmente essere selezionata dall'utente, ma inserirlo in Constants evita diversi potenziali errori) 
Dopo questa prima fase di "inizializzazione" c'è la fase di "drawing", ovvero tutti i metodi a cui passiamo le coordinate relative all'elemento e che ne lo outputtano sul canvas (prendendolo dalle png/risorse i quali url sono già stati assegnati in Constants a varie stringhe)
dodrawing si occupa di controllare se siamo in sessione di gioco (disegnando gli elementi) e di richiamare gameOver che si occuperà di definire font e caratteristiche della scritta di fine sessione.
Il metodo update controlla se la nostra nave è viva o meno (richiamando isDead, che returna un booleano per indicarcelo) e se non dovesse esserlo inserisce in message (che è poi utilizzato in gameover) il messaggio di sconfitta :(
Allo stesso modo controlla lo stato delle navi nemiche controllando deaths rispetto alla dimensione di enemyShip (ricordo che è una Lista), anche qui se dovessero essere uguali si inserisce in message, che andrà poi in gameOver, il messaggio di vittoria :)
Oltre queste due funzioni fondamentali, il metodo update contiene il controllo sul laser (non puoi instanziarne uno se l'ultimo non è "morto" e quindi fuori dal canvas) e l'incremento di deaths e score, cioè delle morti che poi confrontiamo per finire il gioco, e del punteggio che viene incrementato di 20 ogni volta che abbattiamo un nemico. Quando ciò avviene richiamiamo anche soundFactory che evito di descrivere come classe per non dilungarmi (semplicemente ci fà sentire suoni qui in update e quando il laser viene istanziato)
Inoltre update si occupa di far muovere i nemici e di generare bombe da loro (la probabilità che una bomba si venga a creare in seguito ad un movimento è gestita dalla costante BOMB_DROPPING_PROBABILITY)
Quando una bomba arriva al livello della spaceship, se ne controllano le coordinate, se coincidono con quelle della nave, allora (oltre a richiamare il suono dell'accaduto sempre da SoundFactory) rimuove uno shields (vita).
doOneLoop è il pivot di aggiornamento del videogioco, grazie ad update() e repaint() ci permette di tenere aggiornato il canvas di gioco.
i due metodi keyReleased e keyPressed gestiscono gli input che immettiamo da tastiera.


```
Schermate di esempio
```

## Diagramma delle Classi

![Diagramma Classi](/uml.png)








## Struttura Algebrica



###### Istruzioni (?)




## Fatto con

* [Netbeans](https://netbeans.apache.org) 
* [Adobe Photoshop](http://www.adobe.com) - Grafiche (png)

## Versione
1.0 (Magari per altri esami la arricchisco di funzioni addizionali, chissà)

## Autore

**William Vesnaver** - *Per esame di Metodi Avanzati di Programmazione* 


