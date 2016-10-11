/*
 * This is an indispensable class for Java programmers who target their programs for mutli-platform use.
 * With this class, you are able to get anything about the OS, the user, and more!
 * Essentially, this is a wrapper for the os.* and user.*.
 * I have included Mac-specific methods, such as setting the menubar title or the dock icon!
 *
 * created by theawesomecoder61, 10/11/2016
 * GitHub repo: https://github.com/theawesomecoder61/OSUtils
 *
 * PLEASE NOTE: THE MAC METHODS ONLY WORK WITH JDK 8!
 *
 */

import java.awt.Image;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author theawesomecoder61
 * @version 1.0
*/

public class OSUtils {
    
    private static final String OS = sysprop("os.name").toLowerCase();

    // PLATFORM DETECTION
    public static boolean isWindows() {
        return OS.contains("win");
    }
    public static boolean isMac() {
        return OS.contains("mac");
    }
    public static boolean isLinux() {
        return OS.contains("Linux");
    }

    // GET
    /**
     *
     * @param t Use the enum java, os, or user. (e.g. OSUtils.user)
     * @param o Use a enum that relates to the type.
     * @return Returns a dictionary/map of java, os, or user properties.
     */
    public static String get(type t, Object o) {
        String r = "";
        Map<String, String> m = new HashMap<>();
        if(null != t) switch (t) {
            case java:
                m.put("home", sysprop("java.home"));
                m.put("ven", sysprop("java.vendor"));
                m.put("venURL", sysprop("java.vendor.url"));
                m.put("ver", sysprop("java.version"));
                r = m.get(((java)o).name());
                break;
            case os:
                m.put("arch", sysprop("os.arch"));
                m.put("name", sysprop("os.name"));
                m.put("ver", sysprop("os.version"));
                r = m.get(((os)o).name());
                break;
            case user:
                m.put("country", sysprop("user.country")==null?sysprop("user.region"):sysprop("user.country"));
                m.put("dir", sysprop("user.dir"));
                m.put("home", sysprop("user.home"));
                m.put("lang", sysprop("user.language"));
                m.put("name", sysprop("user.name"));
                r = m.get(((user)o).name());
                break;
            default:
                break;
        }
        return r;
    }
    
    // MAC
    public static void setMenubarTitle(String str) {        
        System.setProperty("apple.awt.application.name", str);
    }
    public static void setDockIconImage(Image img) {
        try {
            Class util = Class.forName("com.apple.eawt.Application");
            Method getApplication = util.getMethod("getApplication", new Class[0]);
            Object application = getApplication.invoke(util);
            Class params[] = new Class[1];
            params[0] = Image.class;
            Method setDockIconImage = util.getMethod("setDockIconImage", params);
            setDockIconImage.invoke(application, img);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            // log exception
        }
    }
    
    // HELPERS
    private static String sysprop(String p) {
        try {
            return System.getProperty(p);
        } catch (SecurityException ex) {
            System.err.println("Caught a SecurityException reading the system property '" + p + "'; the SystemUtils property value will default to null.");
            return null;
        }
    }
    
    // ENUMS
    public enum type {
        java, os, user
    }
    public enum java {
        home, ven, venURL, ver
    }
    public enum os {
        arch, name, ver
    }
    public enum user {
        country, dir, home, lang, name
    }
}
