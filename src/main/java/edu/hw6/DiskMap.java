package edu.hw6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

@SuppressWarnings("checkstyle:RegexpSinglelineJava")
public class DiskMap implements Map<String, String> {
    private Map<String, String> map = new HashMap<>();
    private File file;

    public DiskMap(String filename) {
        this.file = new File(filename);
        loadFromFile();
    }

    private void loadFromFile() {
        if (!file.exists()) {
            return; // Если файл не существует, пропускаем загрузку
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    map.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String put(String key, String value) {
        String oldValue = map.put(key, value);
        saveToFile();
        return oldValue;
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Override
    public String remove(Object key) {
        String value = map.remove(key);
        saveToFile();
        return value;
    }

    @Override
    public void clear() {
        map.clear();
        saveToFile();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<String> values() {
        return map.values();
    }

    @Override
    public Set<Map.Entry<String, String>> entrySet() {
        return map.entrySet();
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        for (Map.Entry<? extends String, ? extends String> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
        saveToFile();
    }

    @Override
    public void replaceAll(BiFunction<? super String, ? super String, ? extends String> function) {
        map.replaceAll(function);
        saveToFile();
    }

    @Override
    public String putIfAbsent(String key, String value) {
        String result = map.putIfAbsent(key, value);
        saveToFile();
        return result;
    }

    @Override
    public boolean remove(Object key, Object value) {
        boolean result = map.remove(key, value);
        if (result) {
            saveToFile();
        }
        return result;
    }

    @Override
    public boolean replace(String key, String oldValue, String newValue) {
        boolean result = map.replace(key, oldValue, newValue);
        if (result) {
            saveToFile();
        }
        return result;
    }

    @Override
    public String replace(String key, String value) {
        String result = map.replace(key, value);
        if (result != null) {
            saveToFile();
        }
        return result;
    }

    @Override
    public String computeIfAbsent(String key, Function<? super String, ? extends String> mappingFunction) {
        String result = map.computeIfAbsent(key, mappingFunction);
        if (result != null) {
            saveToFile();
        }
        return result;
    }

    @Override
    public String computeIfPresent(String key, BiFunction<? super String,
        ? super String, ? extends String> remappingFunction) {
        String result = map.computeIfPresent(key, remappingFunction);
        if (result != null) {
            saveToFile();
        }
        return result;
    }

    @Override
    public String compute(String key, BiFunction<? super String, ? super String, ? extends String> remappingFunction) {
        String result = map.compute(key, remappingFunction);
        if (result != null) {
            saveToFile();
        }
        return result;
    }

    @Override
    public String merge(String key, String value, BiFunction<? super String,
        ? super String, ? extends String> remappingFunction) {
        String result = map.merge(key, value, remappingFunction);
        if (result != null) {
            saveToFile();
        }
        return result;
    }
}
