package com.haocxx.framework.util.file;

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

    /**
     * Read a .json file and convert to String
     *
     * @param path The path of .json file
     * @return string content of .json file
     */
    public static String getJsonStringByFileName(String path) {
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
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                mInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Read a .json file and convert to JSONObject
     *
     * @param path The path of .json file
     * @return JSONObject content of .json file
     */
    public static JSONObject getJSONByFileName(String path) {
        try {
            return new JSONObject(getJsonStringByFileName(path));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Read a .json file and convert to JsonObject
     *
     * @param path The path of .json file
     * @return JsonObject content of .json file
     */
    public static JsonObject getJsonByFileName(String path) {
        JsonParser parser = new JsonParser();
        String jsonString = getJsonStringByFileName(path);
        if (jsonString != null) {
            return parser.parse(jsonString).getAsJsonObject();
        }
        return null;
    }

    /**
     * Write json string to .json file
     *
     * @param jsonContent string content to write
     * @param path absolute path of output file
     * @return true if success or false if failed
     */
    public static boolean writeJsonFile(String jsonContent, String path) {
        FileOutputStream fos = null;
        ObjectOutputStream os = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                if(!file.createNewFile()) {
                    return false;
                }
            }
            fos = new FileOutputStream(file, false);
            os = new ObjectOutputStream(fos);
            FileChannel fc = fos.getChannel();
            fc.truncate(fc.position() - 4);
            os.writeObject(jsonContent);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
        return true;
    }

}
