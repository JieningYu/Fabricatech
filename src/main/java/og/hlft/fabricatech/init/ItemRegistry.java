package og.hlft.fabricatech.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import og.hlft.fabricatech.Fabricatech;
import og.hlft.fabricatech.common.items.EnergyTiers;
import og.hlft.fabricatech.common.items.impl.BatteryItem;
import og.hlft.fabricatech.common.materials.RMaterial;
import og.hlft.fabricatech.common.materials.RMaterialPart;
import og.hlft.fabricatech.common.materials.RMaterials;

import static og.hlft.fabricatech.Fabricatech.TAB;
import static og.hlft.fabricatech.Fabricatech.asId;


public class ItemRegistry {
    public static void register() {
        item("battery", new BatteryItem(settings(), EnergyTiers.TEST));

        material(RMaterials.COPPER);
        material(RMaterials.GOLD);
        material(RMaterials.IRON);

        material(RMaterials.TIN);
        material(RMaterials.LEAD);
        material(RMaterials.NICKEL);
        material(RMaterials.SILVER);
    }

    protected static void material(RMaterial material) {
        for (RMaterialPart part: material.getItemParts()) {
            simpleItem(material.makeRID(part));
        }

        for (RMaterialPart part: material.getBlockParts()) {
            blockItem(material.makeRID(part));
        }
    }

    protected static void simpleItem(String id) {
        item(id, new Item(settings()));
    }

    protected static void blockItem(String id) {
        item(id, new BlockItem(Registry.BLOCK.get(asId(id)), settings()));
    }

    protected static void item(String id, Item item) {
        Registry.register(Registry.ITEM, Fabricatech.asId(id), item);
    }

    public static Item.Settings settings() {
        return new FabricItemSettings().group(TAB);
    }

    public static Item getItem(String id) {
        return Registry.ITEM.get(asId(id));
    }
}
