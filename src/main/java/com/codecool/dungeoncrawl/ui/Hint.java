package com.codecool.dungeoncrawl.ui;

public class Hint {

    String howToPlayTitle = "How to play?";
    String getHowToPlayHint = "Simple as that:\n\n" +
            "- Move your hero with ArrowKeys or AWSD keys\n" +
            "- To pick up item, just stand on it and press P\n" +
            "- To heal with potion just press H\n" +
            "- To change Hero's name just click 'Player Name' field or press N\n\n" +
            "Have Fun!";

    String welcomeTitle = "Welcome to the Game";
    String welcomeHint = "Your objective is to find a Graal.\n\n" +
            "In order to do that, you should explore the caves, fight with monsters and get supplies.\n" +
            "You can move with ArrowKey or AWSD - its up to You\n" +
            "To pick up items, just stand on it and click P\n" +
            "After standing on heart and picking it, you will heal instantly,\n" +
            "with potion you can heal yourself later by pressing H key.\n\n" +
            "Have Fun!";

    String title1 = "Quest for Graal";
    String hint1 = "Hello, Adventure hunter!\n\n" +
            "In order to find a Graal, You should look out for a Stairs Keeper.\n" +
            "But beware, in order to destroy him, you should get Mighty Axe, hidden\n" +
            "in the north.\n\n" +
            "Good Luck!";

    String title2 = "Quest for Graal";
    String hint2 = "You made it here!\n\n" +
            "Now, find a boneyard, guarded by Skeleton/Ghost Guards.\n" +
            "But in order to defeat Graal Keeper You should find Holy Sword \n" +
            "and get a proper armor, because even with Mighty Axe you will lose!\n\n" +
            "This is Your Time!";

    String endGameTitle = "End credits";
    String endMessage = "You did it! You were able to find the Graal.\n\n" +
            "It feels like you were trapped here for days\n" +
            "but you finally made it here in one piece.\n\n" +
            "Hope you had fun playing the game!";


    public String getHowToPlayTitle() { return howToPlayTitle; }

    public String getGetHowToPlayHint() { return getHowToPlayHint; }

    public String getWelcomeTitle() { return welcomeTitle; }

    public String getTitle1() { return title1; }

    public String getTitle2() { return title2; }

    public String getWelcomeHint() { return welcomeHint; }

    public String getHint1() { return hint1; }

    public String getHint2() { return hint2; }

    public String getEndGameTitle() {
        return endGameTitle;
    }

    public String getEndMessage() {
        return endMessage;
    }
}
