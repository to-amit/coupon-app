# Coupon Management API

## Overview

The Coupon Management API is a RESTful service designed to manage and apply various types of discount coupons for an e-commerce platform. The API supports cart-wise, product-wise, and Buy X Get Y (BxGy) coupons, allowing for flexible discount strategies. The design is modular, enabling the easy addition of new coupon types in the future.

## Use Cases

### Implemented Use Cases

1. **Cart-wise Coupons**:
   - Apply a discount to the total cart value.
   - Minimum cart value required to apply the coupon.
   - Expiration date for the coupon.

2. **Product-wise Coupons**:
   - Apply a discount to specific products in the cart.
   - Limit the number of times a coupon can be used on a product.
   - Conditions based on product categories.

3. **Buy X Get Y (BxGy) Coupons**:
   - Buy a certain quantity of a product to receive another product for free.
   - Apply to specific products or categories.
   - Limitations on the number of times the coupon can be used.

### Future Use Cases (Not Implemented)

1. **Tiered Discounts**:
   - Discounts based on total cart value (e.g., 10% off for $50, 20% off for $100).

2. **Stackable Coupons**:
   - Allow multiple coupons to be applied to a single transaction.

3. **Time-limited Coupons**:
   - Coupons that are only valid during specific time frames (e.g., holiday sales).

4. **Customer-specific Coupons**:
   - Coupons that are personalized for specific customers based on their purchase history.

5. **Referral Coupons**:
   - Coupons given for referring new customers.

### Edge Cases Considered

- Invalid coupon codes.
- Coupons that have expired.
- Coupons that do not meet the minimum requirements.
- Overlapping coupon conditions (e.g., a product being eligible for both product-wise and cart-wise coupons).
- Handling of concurrent requests that may apply coupons to the same cart.

## Assumptions

- The API assumes that the input data (cart details, coupon codes) is properly formatted and validated before reaching the service layer.
- Coupon codes are unique and case-sensitive.
- The API does not handle user authentication; it is assumed that the client application manages user sessions.

## Limitations

- The current implementation does not support stackable coupons or tiered discounts.
- The API does not persist coupon usage history; once a coupon is applied, it cannot be tracked for future use.
- The system does not handle coupon expiration based on specific user actions (e.g., only valid for first-time users).
- No comprehensive logging or monitoring is implemented.

## API Endpoints

