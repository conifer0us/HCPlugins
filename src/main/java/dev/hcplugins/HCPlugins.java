package dev.hcplugins;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class HCPlugins extends JavaPlugin implements Listener {

    public static final String FLESH_ITEM_NAME = "Fresh Flesh :)";

    // Plugin startup logic
    @Override
    public void onEnable() {
        getLogger().info("HC Plugins Enabled");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void OnMobDeath(EntityDeathEvent event) {
        LivingEntity villager = event.getEntity();

        if (villager.getType() != EntityType.VILLAGER) {
            return;
        }

        ItemStack drop = new ItemStack(Material.BEEF, 1);
        ItemMeta itemData = drop.getItemMeta();
        itemData.setDisplayName(FLESH_ITEM_NAME);
        drop.setItemMeta(itemData);

        Location villagerPos = villager.getLocation();
        villagerPos.getWorld().dropItemNaturally(villagerPos, drop);
    }

    // Plugin shutdown logic
    @Override
    public void onDisable() {
        getLogger().info("HC Plugins Disabled");
    }
}
