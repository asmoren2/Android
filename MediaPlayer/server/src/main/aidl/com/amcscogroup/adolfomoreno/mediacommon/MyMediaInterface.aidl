// MyMediaInterface.aidl
package com.amcscogroup.adolfomoreno.mediacommon;

// Declare any non-default types here with import statements
interface MyMediaInterface {

    //Play the audio file
    void play();
    // Play the current song
    void playSong(int x);
    // Pause the audio file
    void pause();
    // Resume the audio file
    void resume();
    // Stop the audio file
    void stop();
    // Get the record
    String parseCursor();
    // get the file path
    int []getFilePath();
    // Get the names of the songs
    String []getSongNames();
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);
}
