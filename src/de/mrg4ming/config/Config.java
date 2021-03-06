package de.mrg4ming.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Config {

    private File file;
    private YamlConfiguration cfg;

    private String name;

    public Config(String _name) {
        this.name = _name;
        load(_name);
    }

    public void load(String _name) {
        File dir = new File("./plugins/MaintenanceE/");

        if(!dir.exists()) {
            dir.mkdirs();
        }

        file = new File(dir, Objects.requireNonNull(_name) + ".yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        cfg = YamlConfiguration.loadConfiguration(file);
    }

    public void reload() {
        load(this.name);
    }

    public boolean contains(String _path) {
        return cfg.contains(_path);
    }

    public void set(String _path, Object _value) throws IOException {
        cfg.set(_path, _value);
        cfg.save(file);
    }

    public Object get(String _path) {
        if(!contains(_path)) return null;
        return cfg.get(_path);
    }

    public List getList(String _path) {
        if(!contains(_path)) return null;
        return cfg.getList(_path);
    }

    public ItemStack getItemStack(String _path) {
        if(!contains(_path)) return null;
        return new ItemStack(cfg.getItemStack(_path));
    }

    public String getName() {
        return name;
    }
}
