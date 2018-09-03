package com.haocxx.haocxxframework.util.file;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;

/**
 * Created by Haocxx
 * on 2018/9/3
 */
public class JSONUtil {
    public static String getJsonStrinByFileName(String path) {
        InputStream mInputStream = null;
        try {
            mInputStream = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (mInputStream == null) {
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(mInputStream));
        StringBuilder jsonString = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                // line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            return jsonString.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (mInputStream != null) {
                try {
                    mInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static JSONObject getJSONByFileName(String path) {
        try {
            return new JSONObject(getJsonStrinByFileName(path));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JsonObject getJsonByFileName(String path) {
        JsonParser parser = new JsonParser();
        String jsonString = getJsonStrinByFileName(path);
        if(jsonString != null) {
            JsonObject json = parser.parse(jsonString).getAsJsonObject();
            return json;
        }
        return null;
    }

    public static void writeJsonFile(String jsonContent, String path) {
        FileOutputStream fos = null;
        ObjectOutputStream os = null;
        try {
            File file = new File(path);
            if(!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file, false);
            os = new ObjectOutputStream(fos);
            FileChannel fc = fos.getChannel();
            fc.truncate(fc.position() - 4);
            os.writeObject(jsonContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
