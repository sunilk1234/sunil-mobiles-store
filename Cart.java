package com.example.ecom.model;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private Map<Long, CartItem> items = new LinkedHashMap<>();

    public Map<Long, CartItem> getItems() { return items; }

    public void add(Product p, int qty) {
        CartItem existing = items.get(p.getId());
        if (existing == null) items.put(p.getId(), new CartItem(p, qty));
        else existing.setQuantity(existing.getQuantity() + qty);
    }

    public void update(Long productId, int qty) {
        CartItem item = items.get(productId);
        if (item != null) {
            if (qty <= 0) items.remove(productId);
            else item.setQuantity(qty);
        }
    }

    public void remove(Long productId) {
        items.remove(productId);
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem ci : items.values()) total = total.add(ci.getSubtotal());
        return total;
    }

    public int getCount() {
        int c = 0;
        for (CartItem ci : items.values()) c += ci.getQuantity();
        return c;
    }

    public void clear() {
        items.clear();
    }
}
