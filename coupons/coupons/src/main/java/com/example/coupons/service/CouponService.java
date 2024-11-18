package com.example.coupons.service;

import com.example.coupons.model.CouponEntity;
import com.example.coupons.model.Cart;
import com.example.coupons.model.Product;
import com.example.coupons.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    public CouponEntity createCoupon(CouponEntity couponEntity) {
        return couponRepository.save(couponEntity);
    }

    public List<CouponEntity> getAllCoupons() {
        return couponRepository.findAll();
    }

    public Optional<CouponEntity> getCoupon(Long id) {
        return couponRepository.findById(id);
    }

    public Optional<CouponEntity> updateCoupon(Long id, CouponEntity updatedCoupon) {
        return couponRepository.update(id, updatedCoupon);
    }

    public boolean deleteCoupon(Long id) {
        return couponRepository.delete(id);
    }

    public List<CouponEntity> getApplicableCoupons(Cart cart) {
        return couponRepository.findAll();
    }

    public Cart applyCoupon(Long id, Cart cart) {
        Optional<CouponEntity> couponOpt = couponRepository.findById(id);
        if (couponOpt.isPresent()) {
            CouponEntity coupon = couponOpt.get();
            switch (coupon.getType()) {
                case "CART_WISE":
                    if (cart.getTotalAmount() >= coupon.getDiscountValue()) {
                        cart.setTotalAmount(cart.getTotalAmount() - coupon.getDiscountValue());
                    }
                    break;
                case "PRODUCT_WISE":
                    applyProductWiseCoupon(cart, coupon);
                    break;
                case "BX_GY":
                    applyBxGyCoupon(cart, coupon);
                    break;
                default:
                    break;
            }
        }
        return cart;
    }

    private void applyCartWiseCoupon(Cart cart, CouponEntity coupon) {
        if (cart.getTotalAmount() > coupon.getMinCartValue()) {
            double discount = cart.getTotalAmount() * (coupon.getDiscountValue() / 100);
            cart.setTotalAmount(cart.getTotalAmount() - discount);
        }
    }

    private void applyProductWiseCoupon(Cart cart, CouponEntity coupon) {
        for (Product product : cart.getProducts()) {
            if (product.getName().equals(coupon.getApplicableProductName())) {
                double discount = product.getPrice() * (coupon.getDiscountValue() / 100);
                cart.setTotalAmount(cart.getTotalAmount() - (discount * product.getQuantity()));
                break;
            }
        }
    }

    private void applyBxGyCoupon(Cart cart, CouponEntity coupon) {
        List<String> buyProducts = coupon.getBuyProducts();
        List<String> getProducts = coupon.getGetProducts();
        int totalBuyCount = 0;

        for (Product product : cart.getProducts()) {
            if (buyProducts.contains(product.getName())) {
                totalBuyCount += product.getQuantity();
            }
        }

        int freeCount = (totalBuyCount / coupon.getBuyCount()) * coupon.getGetCount();

        for (String getProduct : getProducts) {
            addFreeProductToCart(cart, getProduct, freeCount);
        }
    }

    private void addFreeProductToCart(Cart cart, String productName, int quantity) {
        for (Product product : cart.getProducts()) {
            if (product.getName().equals(productName)) {
                product.setQuantity(product.getQuantity() + quantity);
                return;
            }
        }

        Product freeProduct = new Product();
        freeProduct.setName(productName);
        freeProduct.setQuantity(quantity);
        freeProduct.setPrice(0.0);
        cart.getProducts().add(freeProduct);
    }
}
