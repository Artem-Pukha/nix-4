package com.spnsolo.until;

import com.spnsolo.Main;

import java.io.InputStream;
import java.net.URL;

public class FileResourcesUtil {

    public static String getPathToResourcesFile (String file){
        URL url = Main.class.getClassLoader().getResource("csv/input.csv");
        assert url != null;
        return url.getPath();
    }
}
