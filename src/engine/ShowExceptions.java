package engine;

import org.lwjgl.Sys;

public class ShowExceptions {
    public static void showException(Exception e){
        Sys.alert("An exception has occurred", e.toString());
    }
}
