package com.shoppingapp.universal_shopping_app.services.customer.cart;


import com.shoppingapp.universal_shopping_app.dto.*;
import com.shoppingapp.universal_shopping_app.entity.*;
import com.shoppingapp.universal_shopping_app.enums.OrderStatus;
import com.shoppingapp.universal_shopping_app.exceptions.ValidationException;
import com.shoppingapp.universal_shopping_app.repositry.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private OrderRepositry orderRepositry;

    @Autowired
    private UserRepositry userRepositry;

    @Autowired
    private CartItemsRepositry cartItemsRepositry;

    @Autowired
    private ProductRepositry productRepositry;

    @Autowired
    private CouponRepositry couponRepositry;


    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto){
        Order activeOrder=orderRepositry.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
        System.out.println("hi");
        System.out.println("Order ID: " );
        Optional<CartItems> optionalCartItems=cartItemsRepositry.findByProductIdAndOrderIdAndUserId(addProductInCartDto.getProductId(),activeOrder.getId(),addProductInCartDto.getUserId());
        System.out.println("1"+optionalCartItems.isPresent());
        if(optionalCartItems.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }else{
            Optional<Product> optionalProduct=productRepositry.findById(addProductInCartDto.getProductId());
            Optional<User> optionalUser=userRepositry.findById(addProductInCartDto.getUserId());
            System.out.println("else");
            if(optionalProduct.isPresent() && optionalUser.isPresent()){
                System.out.println("if");
             CartItems cart= new CartItems();
             cart.setProduct(optionalProduct.get());
             cart.setPrice(optionalProduct.get().getPrice());
             cart.setQuantity(1L);
             cart.setUser(optionalUser.get());
             cart.setOrder(activeOrder);
             System.out.println("till");
                System.out.println("Order ID: " + activeOrder.getId());
                System.out.println("Product ID: " + addProductInCartDto.getProductId());
                System.out.println("User ID: " + addProductInCartDto.getUserId());
             CartItems updateCart= cartItemsRepositry.save(cart);
                System.out.println("till1");
                System.out.println("here1"+cart.getPrice());
                System.out.println("here "+activeOrder.getTotalAmount() );
                activeOrder.setTotalAmount(activeOrder.getTotalAmount()+cart.getPrice());
                System.out.println("till2");
                activeOrder.setAmount(activeOrder.getAmount()+cart.getPrice());
                System.out.println("till3");

                activeOrder.getCartItems().add(cart);

              orderRepositry.save(activeOrder);

              return ResponseEntity.status(HttpStatus.CREATED).body(cart.getId());

            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
            }

        }
    }

    public boolean removeProduct(RemoveProductInCartDto removeProductInCartDto){
        Order activeOrder=orderRepositry.findByUserIdAndOrderStatus(removeProductInCartDto.getUserId(), OrderStatus.Pending);
        Optional<Product> optionalProduct=productRepositry.findById(removeProductInCartDto.getProductId());

        Optional<CartItems> optionalCartItem =cartItemsRepositry.findByProductIdAndOrderIdAndUserId(
                removeProductInCartDto.getProductId(), activeOrder.getId(),  removeProductInCartDto.getUserId()
        );
        if(optionalProduct.isPresent() && optionalCartItem.isPresent()){
            CartItems cartItem =optionalCartItem.get();
            Product product=optionalProduct.get();
            activeOrder.setAmount(activeOrder.getAmount() -(cartItem.getQuantity() *product.getPrice()) );
            activeOrder.setTotalAmount(activeOrder.getTotalAmount()-(cartItem.getQuantity() *product.getPrice()));

            cartItem.setQuantity(0L);

            if(activeOrder.getCoupon() !=null){
                double discountAmount= ((activeOrder.getCoupon().getDiscount() / 100.0) * activeOrder.getTotalAmount());
                double netAmount = activeOrder.getTotalAmount() -discountAmount;

                activeOrder.setAmount((long)netAmount);
                activeOrder.setDiscount((long)discountAmount);
            }

            cartItemsRepositry.delete(cartItem);
            orderRepositry.save(activeOrder);
            return true;

        }
        return false;
    }


    public OrderDto getCartByUserId(Long userId){
        Order activeOrder=orderRepositry.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        List<CartItemsDto> cartItemsDtoList =activeOrder.getCartItems().stream().map(CartItems::getCartDto).collect(Collectors.toList());
        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(activeOrder.getAmount());
        orderDto.setId(activeOrder.getId());
        orderDto.setOrderStatus(activeOrder.getOrderStatus());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setTotalAmount(activeOrder.getTotalAmount());
        orderDto.setCartItems(cartItemsDtoList);
        if(activeOrder.getCoupon() !=null){
            orderDto.setCouponName(activeOrder.getCoupon().getName());
        }

        return orderDto;
    }

    public OrderDto applyCoupon(Long userId, String code){
        Order activeOrder=orderRepositry.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        Coupon coupon=couponRepositry.findByCode(code).orElseThrow(()-> new ValidationException("Coupon not found."));

        if(couponIsExpired(coupon)){
            throw new ValidationException("Coupon is expired.");
        }

        double discountAmount= ((coupon.getDiscount() / 100.0) * activeOrder.getTotalAmount());
        double netAmount = activeOrder.getTotalAmount() -discountAmount;

        activeOrder.setAmount((long)netAmount);
        activeOrder.setDiscount((long)discountAmount);
        activeOrder.setCoupon(coupon);
        System.out.println("coupon created");
        orderRepositry.save(activeOrder);
        System.out.println("coupon saved");
        return activeOrder.getOrderDto();

    }

    private boolean couponIsExpired(Coupon coupon){
        LocalDate today = LocalDate.now();
        LocalDate expirationDate= coupon.getExpirationDate();
         return expirationDate != null && today.isAfter(expirationDate);
    }

    public OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto){
        Order activeOrder=orderRepositry.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
        Optional<Product> optionalProduct=productRepositry.findById(addProductInCartDto.getProductId());

        Optional<CartItems> optionalCartItem =cartItemsRepositry.findByProductIdAndOrderIdAndUserId(
                addProductInCartDto.getProductId(), activeOrder.getId(),  addProductInCartDto.getUserId()
        );
        if(optionalProduct.isPresent() && optionalCartItem.isPresent()){
            CartItems cartItem =optionalCartItem.get();
            Product product=optionalProduct.get();

            activeOrder.setAmount(activeOrder.getAmount()+ product.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount()+ product.getPrice());

            cartItem.setQuantity(cartItem.getQuantity() +1);

            if(activeOrder.getCoupon() !=null){
                double discountAmount= ((activeOrder.getCoupon().getDiscount() / 100.0) * activeOrder.getTotalAmount());
                double netAmount = activeOrder.getTotalAmount() -discountAmount;

                activeOrder.setAmount((long)netAmount);
                activeOrder.setDiscount((long)discountAmount);
            }

            cartItemsRepositry.save(cartItem);
            orderRepositry.save(activeOrder);
            return activeOrder.getOrderDto();

        }
        return null;
    }


    public OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto){
        System.out.println(addProductInCartDto.getUserId());
        Order activeOrder=orderRepositry.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
        Optional<Product> optionalProduct=productRepositry.findById(addProductInCartDto.getProductId());

        Optional<CartItems> optionalCartItem =cartItemsRepositry.findByProductIdAndOrderIdAndUserId(
                addProductInCartDto.getProductId(), activeOrder.getId(),  addProductInCartDto.getUserId()
        );
        if(optionalProduct.isPresent() && optionalCartItem.isPresent()){
            CartItems cartItem =optionalCartItem.get();
            Product product=optionalProduct.get();

            activeOrder.setAmount(activeOrder.getAmount() - product.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() - product.getPrice());

            cartItem.setQuantity(cartItem.getQuantity() - 1);

            if(activeOrder.getCoupon() !=null){
                double discountAmount= ((activeOrder.getCoupon().getDiscount() / 100.0) * activeOrder.getTotalAmount());
                double netAmount = activeOrder.getTotalAmount() -discountAmount;

                activeOrder.setAmount((long)netAmount);
                activeOrder.setDiscount((long)discountAmount);
            }

            cartItemsRepositry.save(cartItem);
            orderRepositry.save(activeOrder);
            return activeOrder.getOrderDto();

        }
        return null;
    }

    public OrderDto placeOrder(PlaceOrderDto placeOrderDto){
        Order activeOrder=orderRepositry.findByUserIdAndOrderStatus(placeOrderDto.getUserId(), OrderStatus.Pending);
        Optional<User> optionalUser =userRepositry.findById(placeOrderDto.getUserId());

        if(optionalUser.isPresent()){
            activeOrder.setOrderDescription(placeOrderDto.getOrderDescription());
            activeOrder.setAddress(placeOrderDto.getAddress());
            activeOrder.setDate(LocalDate.now());
            activeOrder.setOrderStatus(OrderStatus.Placed);
            activeOrder.setTrackingId(UUID.randomUUID());

            orderRepositry.save(activeOrder);

            Order order=new Order();
            order.setAmount(0L);
            order.setTotalAmount(0L);
            order.setDiscount(0L);
            order.setUser(optionalUser.get());
            order.setOrderStatus(OrderStatus.Pending);
            orderRepositry.save(order);

            return activeOrder.getOrderDto();
        }
        return null;
    }

    public List<OrderDto> getMyPlacedOrders(Long userId){
        List<OrderStatus> orderStatusList=List.of(OrderStatus.Placed, OrderStatus.Delivered, OrderStatus.Shipped);

        return orderRepositry.findAllByUserIdAndOrderStatusIn(userId,orderStatusList).stream().map(Order::getOrderDto).collect(Collectors.toList());
    }

}
