# Castle Duel Challenge
Java castle duel written in Java 8 and Swing.

It does not use any external Java library. The whole game is ~700kb implemented on Swing. Works with Windows, Linux and Mac. **It requires the JDK installed to run because it uses the Java compiler to compile the students' bots during runtime.** 

The main pourpose is for students to compete with each other. They must implement a Java bot. There is samples in "/players" folder.

Just run the game "java -jar game.jar". It will open on GUI mode. There is an optional parameter "fps". Exemple: java -jar game.jar fps200.

To run on server mode (no GUI): java -jar game.jar server. This mode will load all bots to play with each other and will save a score TXT file.

