package com.unknown.delivery.domain.order.application;

import com.unknown.delivery.domain.menu.dao.MenuRepository;
import com.unknown.delivery.domain.menu.entity.Menu;
import com.unknown.delivery.domain.option.dao.OptionDetailRepository;
import com.unknown.delivery.domain.option.dao.OptionRepository;
import com.unknown.delivery.domain.option.entity.Option;
import com.unknown.delivery.domain.option.entity.OptionDetail;
import com.unknown.delivery.domain.order.dao.OrderRepository;
import com.unknown.delivery.domain.order.dto.OrderRequest;
import com.unknown.delivery.domain.order.dto.OrderResponse;
import com.unknown.delivery.domain.order.entity.Order;
import com.unknown.delivery.domain.restaurant.dao.RestaurantRepository;
import com.unknown.delivery.domain.restaurant.entity.Restaurant;
import com.unknown.delivery.global.exception.BusinessException;
import com.unknown.delivery.global.response.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final OptionRepository optionRepository;
    private final OptionDetailRepository optionDetailRepository;

    @Override
    public List<OrderResponse> getOrders(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findByRestaurantId(restaurantId)
                .orElseThrow(() -> new BusinessException(HttpResponse.Fail.NOT_FOUND_RESTAURANT));

        List<Order> orders = orderRepository.findOrdersByRestaurant(restaurant);

        return orders.stream()
                .map(OrderResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Map<String, Object> content = orderRequest.getContent();
        Restaurant restaurant = validateContent(content);
        BigDecimal totalPrice = getTotalPrice(content);

        Order order = OrderRequest.toEntity(orderRequest, restaurant, totalPrice);
        orderRepository.save(order);

        return OrderResponse.of(order);
    }

    private Restaurant validateContent(Map<String, Object> content) {
        Long restaurantId = Long.parseLong(content.get("restaurant_id").toString());
        String restaurantName = content.get("restaurant_name").toString();

        Restaurant restaurant = restaurantRepository.findByRestaurantId(restaurantId)
                .orElseThrow(() -> new BusinessException(HttpResponse.Fail.NOT_FOUND_RESTAURANT));

        if (!restaurantName.equals(restaurant.getName())) {
            throw new BusinessException(HttpResponse.Fail.RESTAURANT_MISMATCH);
        }

        return restaurant;
    }

    private BigDecimal getTotalPrice(Map<String, Object> content) {
        BigDecimal totalPrice = BigDecimal.valueOf(Long.parseLong(content.get("total_price").toString()));
        BigDecimal sumPrice = BigDecimal.valueOf(0L);

        // 메뉴 항목 유효성 검증
        List<Map<String, Object>> items = (List<Map<String, Object>>) content.get("items");
        for (Map<String, Object> item : items) {
            Long menuId = Long.parseLong(item.get("menu_id").toString());
            String menuName = item.get("name").toString();
            BigDecimal menuPrice = BigDecimal.valueOf(Long.parseLong(item.get("price").toString()));
            BigDecimal menuQuantity = BigDecimal.valueOf(Long.parseLong(item.get("quantity").toString()));

            menuPrice = menuPrice.multiply(menuQuantity);
            sumPrice = sumPrice.add(menuPrice);

            Menu menu = menuRepository.findById(menuId)
                    .orElseThrow(() -> new BusinessException(HttpResponse.Fail.NOT_FOUND_MENU));

            if (!menuName.equals(menu.getName())) {
                throw new BusinessException(HttpResponse.Fail.MENU_MISMATCH);
            }

            // 옵션 항목 유효성 검증
            List<Map<String, Object>> options = (List<Map<String, Object>>) item.get("options");
            for (Map<String, Object> option : options) {
                Long optionId = Long.parseLong(option.get("option_id").toString());
                String optionName = option.get("name").toString();
                Option optionEntity = optionRepository.findById(optionId)
                        .orElseThrow(() -> new BusinessException(HttpResponse.Fail.NOT_FOUND_OPTION));

                if (!optionName.equals(optionEntity.getName())) {
                    throw new BusinessException(HttpResponse.Fail.OPTION_MISMATCH);
                }

                // 옵션 상세 항목 유효성 검증
                List<Map<String, Object>> details = (List<Map<String, Object>>) option.get("details");
                for (Map<String, Object> detail : details) {
                    Long optionDetailId = Long.parseLong(detail.get("option_detail_id").toString());
                    String optionDetailName = detail.get("name").toString();
                    BigDecimal optionDetailPrice = BigDecimal.valueOf(Long.parseLong(detail.get("price").toString()));

                    sumPrice = sumPrice.add(optionDetailPrice);

                    OptionDetail optionDetail = optionDetailRepository.findById(optionDetailId)
                            .orElseThrow(() -> new BusinessException(HttpResponse.Fail.NOT_FOUND_OPTION_DETAIL));

                    if (!optionDetailName.equals(optionDetail.getName())) {
                        throw new BusinessException(HttpResponse.Fail.OPTION_DETAIL_MISMATCH);
                    }
                }
            }
        }

        if (!totalPrice.equals(sumPrice)) {
            throw new BusinessException(HttpResponse.Fail.TOTAL_PRICE_MISMATCH);
        }

        return totalPrice;
    }
}