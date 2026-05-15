package com.gildedrose;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GildedRose {

    Item[] items;

    public void updateQuality() {

        for (Item item : items) {

            if(isSulfuras(item))
                continue;

            updateQualityValue(item);

            item.sellIn--;

            if(item.sellIn < 0)
                updateExpiredItem(item);
        }
    }

    // ======================================
    // UPDATE BEFORE EXPIRATION
    // ======================================
    private void updateQualityValue(Item item) {
        if(isAgedBrie(item))
            increaseQuality(item,1);
        else if(isBackstagePass(item)) {
            if (item.sellIn <= 5)
                increaseQuality(item, 3);
            else if (item.sellIn <= 10)
                increaseQuality(item, 2);
            else
                increaseQuality(item, 1);
        }
        else if(isConjured(item))
            decreaseQuality(item, 2);
        else
            decreaseQuality(item, 1);
    }

    // ======================================
    // UPDATE AFTER EXPIRATION
    // ======================================
    private void updateExpiredItem(Item item) {

        if (isAgedBrie(item)) //aged brie product
            increaseQuality(item, 1);
        else if (isBackstagePass(item)) //backstage product
            item.quality = 0;
        else if (isConjured(item)) // conjured product
            decreaseQuality(item, 2);
        else // normal product
            decreaseQuality(item, 1);
    }

    private boolean isConjured(Item item) {
        return "Conjured Mana Cake".equals(item.getName());
    }

    private void decreaseQuality(Item item, int amount) {
        item.setQuality(Math.max(0, item.quality - amount));
    }

    private void increaseQuality(Item item, int amount) {
        item.setQuality(Math.min(50, item.quality + amount));
    }

    private boolean isBackstagePass(Item item) {
        return "Backstage passes to a TAFKAL80ETC concert".equals(item.getName());
    }

    private boolean isAgedBrie(Item item) {
        return "Aged Brie".equals(item.getName());
    }


    private boolean isSulfuras(Item item) {
        return "Sulfuras, Hand of Ragnaros".equals(item.getName());
    }

}
