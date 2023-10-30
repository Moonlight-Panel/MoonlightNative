package xyz.moonlightpanel.nativeapp.lang;

import java.util.HashMap;
import java.util.Objects;

public class Locale {
    public Locale(){
        translations = new HashMap<>();
    }
    private String name;
    private HashMap<String, String> translations;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void set(String key, String value){
        translations.put(key, value);
    }

    public String get(String key){
        return translations.get(key);
    }

    public String translate(String key){
        return get(key);
    }

    public String translate(String key, Object param1){
        String s = "{";
        String t = "}";
        return get(key)
                .replaceAll(s + 0 + t, param1.toString());
    }

    public String translate(String key, Object param1, Object param2){
        String s = "{";
        String t = "}";
        return get(key)
                .replaceAll(s + 0 + t, param1.toString())
                .replaceAll(s + 1 + t, param2.toString());
    }

    public String translate(String key, Object param1, Object param2, Object param3){
        String s = "{";
        String t = "}";
        return get(key)
                .replaceAll(s + 0 + t, param1.toString())
                .replaceAll(s + 1 + t, param2.toString())
                .replaceAll(s + 2 + t, param3.toString());
    }
    public String translate(String key, Object[] params){
        String s = "{";
        String t = "}";
        String v = get(key);
        int i = 0;
        for (Object o : params) {
            v = v.replaceAll(s + i + t, o.toString());
            i++;
        }

        return  v;
    }
}
