/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.stu.fiit;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;


import sk.stu.fiit.GUI.MainView;



/**
 *
 * @author Adam
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainView mainView = new MainView();
        gatherAndSetData(mainView);
        mainView.setVisible(true);
    }
    
    
    
    private static void gatherAndSetData(MainView view){
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String architecture = System.getProperty("os.arch");
        String userName = System.getProperty("user.name");
        String javaVersion = System.getProperty("java.version");
        String currentDir = System.getProperty("user.dir");
        String currentDirSize = formatSize(getFolderSize(new File(currentDir)));
        String heapSize = formatSize(Runtime.getRuntime().totalMemory());
        String freeHeapSpace = formatSize(Runtime.getRuntime().freeMemory());
        String maxHeapSize = formatSize(Runtime.getRuntime().maxMemory());
        
        view.setFields(osName, osVersion, architecture, userName, javaVersion,
                currentDir, currentDirSize, heapSize, freeHeapSpace, maxHeapSize);
    }
    
    
    public static String formatSize(long size){
        DecimalFormat df = new DecimalFormat("0.00");
        
        float kBsize = (float)size / (float)1024;
        float MBsize = (float)kBsize / (float)1024;
        float GBsize = (float)MBsize / (float)1024;
        if(GBsize > 1){
            return df.format(GBsize) + "GB";
        } else if(MBsize > 1){
            return df.format(MBsize) + "MB";
        } else{
            return df.format(kBsize) + "kB";
        }
        
    }
    
    
    public static long getFolderSize(File directory) {
        long length = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile())
                length += file.length();
            else
                length += getFolderSize(file);
        }
        return length;
    }
    

    
}
