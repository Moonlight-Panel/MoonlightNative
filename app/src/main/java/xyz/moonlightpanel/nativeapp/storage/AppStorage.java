package xyz.moonlightpanel.nativeapp.storage;

import android.content.Context;

import androidx.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class AppStorage {
    public static AppStorage INSTANCE = new AppStorage();
    public File contentDirectory;
    public <T extends Serializable> void save(String key, T instance) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(stream);
            out.writeObject(instance);
            out.close();

            byte[]data = stream.toByteArray();
            stream.close();
            save(key, data);
        }
        catch (Exception e){

        }
    }
    public void set(String key, String data) {
        save(key, data.getBytes(StandardCharsets.UTF_8));
    }
    public String get(String key){
        return new String(loadBytes(key), StandardCharsets.UTF_8);
    }

    @Nullable
    public <T extends Serializable> T load(String key) {
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(loadBytes(key));
            ObjectInputStream in = new ObjectInputStream(stream);
            T object = (T)in.readObject();
            in.close();
            stream.close();
            return object;
        }
        catch (Exception e){
            return null;
        }
    }

    public void remove(String key){
        String fn = hashName(key);
        String fullPath = contentDirectory.getAbsolutePath() + "/" + fn;

        File file = new File(fullPath);

        try {
            file.delete();
        }
        catch (Exception e){

        }
    }

    public void save(String key, byte[]data){
        String fn = hashName(key);
        String fullPath = contentDirectory.getAbsolutePath() + "/" + fn;

        File file = new File(fullPath);

        try {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileOutputStream stream = new FileOutputStream(file);
            stream.write(data);
            stream.close();
        }
        catch (Exception e){

        }
    }

    public byte[] loadBytes(String key){
        String fn = hashName(key);
        String fullPath = contentDirectory.getAbsolutePath() + "/" + fn;

        File file = new File(fullPath);
        if(!file.exists())
            return new byte[0];

        try {
            FileInputStream stream = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            stream.read(buffer);
            stream.close();
            return buffer;
        }
        catch (Exception e){
            return new byte[0];
        }
    }
    private static String hashName(String name){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    name.getBytes(StandardCharsets.UTF_8));

            return bytesToHex(encodedhash);
        }
        catch (Exception e){
            return "";
        }
    }
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
