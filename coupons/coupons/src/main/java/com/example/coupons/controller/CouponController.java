package com.example.coupons.controller;

import com.example.coupons.model.Cart;
import com.example.coupons.model.CouponEntity;
import com.example.coupons.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping
    public ResponseEntity<CouponEntity> createCoupon(@RequestBody CouponEntity couponEntity) {
        CouponEntity savedCoupon = couponService.createCoupon(couponEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCoupon);
    }

    @GetMapping
    public ResponseEntity<List<CouponEntity>> getAllCoupons() {
        List<CouponEntity> coupons = couponService.getAllCoupons();
        return ResponseEntity.ok(coupons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponEntity> getCoupon(@PathVariable Long id) {
        Optional<CouponEntity> coupon = couponService.getCoupon(id);
        return coupon.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CouponEntity> updateCoupon(@PathVariable Long id, @RequestBody CouponEntity updatedCoupon) {
        Optional<CouponEntity> coupon = couponService.updateCoupon(id, updatedCoupon);
        return coupon.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoupon(@PathVariable Long id) {
        boolean isDeleted = couponService.deleteCoupon(id);
        return isDeleted ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/applicable-coupons")
    public ResponseEntity<List<CouponEntity>> getApplicableCoupons(@RequestBody Cart cart) {
        List<CouponEntity> applicableCoupons = couponService.getApplicableCoupons(cart);
        return ResponseEntity.ok(applicableCoupons);
    }

    @PostMapping("/apply-coupon/{id}")
    public ResponseEntity<Cart> applyCoupon(@PathVariable Long id, @RequestBody Cart cart) {
        Cart updatedCart = couponService.applyCoupon(id, cart);
        return ResponseEntity.ok(updatedCart);
    }
}
