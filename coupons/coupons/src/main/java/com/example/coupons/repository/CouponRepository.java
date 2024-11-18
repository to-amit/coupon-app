package com.example.coupons.repository;

import com.example.coupons.model.CouponEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CouponRepository {
    private final List<CouponEntity> coupons = new ArrayList<>();
    private long currentId = 1;

    public List<CouponEntity> findAll() {
        return new ArrayList<>(coupons);
    }

    public Optional<CouponEntity> findById(Long id) {
        return coupons.stream().filter(coupon -> coupon.getId().equals(id)).findFirst();
    }

    public CouponEntity save(CouponEntity couponEntity) {
        couponEntity.setId(currentId++);
        coupons.add(couponEntity);
        return couponEntity;
    }

    public Optional<CouponEntity> update(Long id, CouponEntity updatedCoupon) {
        return findById(id).map(coupon -> {
            coupon.setType(updatedCoupon.getType());
            coupon.setDiscountValue(updatedCoupon.getDiscountValue());
            coupon.setConditions(updatedCoupon.getConditions());
            return coupon;
        });
    }

    public boolean delete(Long id) {
        return coupons.removeIf(coupon -> coupon.getId().equals(id));
    }
}
