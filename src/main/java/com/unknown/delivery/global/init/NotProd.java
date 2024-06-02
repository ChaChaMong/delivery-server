package com.unknown.delivery.global.init;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unknown.delivery.domain.menu.dao.MenuRepository;
import com.unknown.delivery.domain.menu.entity.Menu;
import com.unknown.delivery.domain.option.dao.OptionDetailRepository;
import com.unknown.delivery.domain.option.dao.OptionRepository;
import com.unknown.delivery.domain.option.entity.Option;
import com.unknown.delivery.domain.option.entity.OptionDetail;
import com.unknown.delivery.domain.option.enumerated.OptionType;
import com.unknown.delivery.domain.order.dao.OrderRepository;
import com.unknown.delivery.domain.order.entity.Order;
import com.unknown.delivery.domain.order.enumerated.OrderStatus;
import com.unknown.delivery.domain.restaurant.dao.RestaurantRepository;
import com.unknown.delivery.domain.restaurant.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Profile("!test")
@Configuration
@RequiredArgsConstructor
public class NotProd {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final OptionRepository optionRepository;
    private final OptionDetailRepository optionDetailRepository;
    private final OrderRepository orderRepository;

    @Bean
    public ApplicationRunner initNotProd() {
        return args -> {
            if (!restaurantRepository.findAll().isEmpty()) {
                return;
            }

            initRestaurant();
            initMenu();
            initOption();
            initOptionDetail();
            initOrder();
        };
    }

    private void initRestaurant() {
        restaurantRepository.save(Restaurant.builder().name("피자매장").build());
        restaurantRepository.save(Restaurant.builder().name("한식매장").build());
        restaurantRepository.save(Restaurant.builder().name("일식매장").build());
    }

    private void initMenu() {
        Optional<Restaurant> optionalRestaurant1 = restaurantRepository.findById(1L);
        if (optionalRestaurant1.isPresent()) {
            Restaurant restaurant = optionalRestaurant1.get();
            menuRepository.save(Menu.builder().restaurant(restaurant).name("마르게리타 피자").price(BigDecimal.valueOf(12000)).build());
            menuRepository.save(Menu.builder().restaurant(restaurant).name("미트볼 스파게티").price(BigDecimal.valueOf(9000)).build());
            menuRepository.save(Menu.builder().restaurant(restaurant).name("콜라").price(BigDecimal.valueOf(2000)).build());
            menuRepository.save(Menu.builder().restaurant(restaurant).name("사이다").price(BigDecimal.valueOf(2000)).build());
        }

        Optional<Restaurant> optionalRestaurant2 = restaurantRepository.findById(2L);
        if (optionalRestaurant2.isPresent()) {
            Restaurant restaurant = optionalRestaurant2.get();
            menuRepository.save(Menu.builder().restaurant(restaurant).name("불고기").price(BigDecimal.valueOf(20000)).build());
            menuRepository.save(Menu.builder().restaurant(restaurant).name("제육").price(BigDecimal.valueOf(15000)).build());
        }
    }

    private void initOption() {
        // 마르게리타 피자
        Optional<Menu> optionalMenu1 = menuRepository.findById(1L);
        if (optionalMenu1.isPresent()) {
            Menu menu = optionalMenu1.get();
            optionRepository.save(Option.builder().menu(menu).name("사이즈").type(OptionType.RADIO).build());
            optionRepository.save(Option.builder().menu(menu).name("추가 토핑").type(OptionType.CHECK).build());
            optionRepository.save(Option.builder().menu(menu).name("추가 음료").type(OptionType.CHECK).build());
        }

        // 미트볼 스파게티
        Optional<Menu> optionalMenu2 = menuRepository.findById(2L);
        if (optionalMenu2.isPresent()) {
            Menu menu = optionalMenu2.get();
            optionRepository.save(Option.builder().menu(menu).name("추가 음료").type(OptionType.CHECK).build());
        }

        // 불고기
        Optional<Menu> optionalMenu5 = menuRepository.findById(5L);
        if (optionalMenu5.isPresent()) {
            Menu menu = optionalMenu5.get();
            optionRepository.save(Option.builder().menu(menu).name("사이즈").type(OptionType.RADIO).build());
        }

        // 제육
        Optional<Menu> optionalMenu6 = menuRepository.findById(6L);
        if (optionalMenu6.isPresent()) {
            Menu menu = optionalMenu6.get();
            optionRepository.save(Option.builder().menu(menu).name("사이즈").type(OptionType.RADIO).build());
        }
    }

    private void initOptionDetail() {
        Optional<Option> optionalOption1 = optionRepository.findById(1L);
        if (optionalOption1.isPresent()) {
            Option option = optionalOption1.get();
            optionDetailRepository.save(OptionDetail.builder().option(option).name("스몰 사이즈").price(BigDecimal.valueOf(0)).build());
            optionDetailRepository.save(OptionDetail.builder().option(option).name("미디엄 사이즈").price(BigDecimal.valueOf(5000)).build());
            optionDetailRepository.save(OptionDetail.builder().option(option).name("라지 사이즈").price(BigDecimal.valueOf(10000)).build());
        }

        Optional<Option> optionalOption2 = optionRepository.findById(2L);
        if (optionalOption2.isPresent()) {
            Option option = optionalOption2.get();
            optionDetailRepository.save(OptionDetail.builder().option(option).name("페퍼로니").price(BigDecimal.valueOf(2000)).build());
            optionDetailRepository.save(OptionDetail.builder().option(option).name("베이컨").price(BigDecimal.valueOf(1500)).build());
            optionDetailRepository.save(OptionDetail.builder().option(option).name("치즈").price(BigDecimal.valueOf(2000)).build());
        }

        Optional<Option> optionalOption3 = optionRepository.findById(3L);
        if (optionalOption3.isPresent()) {
            Option option = optionalOption3.get();
            optionDetailRepository.save(OptionDetail.builder().option(option).name("콜라").price(BigDecimal.valueOf(2000)).build());
            optionDetailRepository.save(OptionDetail.builder().option(option).name("사이다").price(BigDecimal.valueOf(2000)).build());
        }

        Optional<Option> optionalOption4 = optionRepository.findById(4L);
        if (optionalOption4.isPresent()) {
            Option option = optionalOption4.get();
            optionDetailRepository.save(OptionDetail.builder().option(option).name("콜라").price(BigDecimal.valueOf(2000)).build());
            optionDetailRepository.save(OptionDetail.builder().option(option).name("사이다").price(BigDecimal.valueOf(2000)).build());
        }

        Optional<Option> optionalOption5 = optionRepository.findById(5L);
        if (optionalOption5.isPresent()) {
            Option option = optionalOption5.get();
            optionDetailRepository.save(OptionDetail.builder().option(option).name("500g").price(BigDecimal.valueOf(0)).build());
            optionDetailRepository.save(OptionDetail.builder().option(option).name("700g").price(BigDecimal.valueOf(5000)).build());
        }

        Optional<Option> optionalOption6 = optionRepository.findById(6L);
        if (optionalOption6.isPresent()) {
            Option option = optionalOption6.get();
            optionDetailRepository.save(OptionDetail.builder().option(option).name("500g").price(BigDecimal.valueOf(0)).build());
            optionDetailRepository.save(OptionDetail.builder().option(option).name("700g").price(BigDecimal.valueOf(4000)).build());
        }
    }

    private void initOrder() throws JsonProcessingException {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findByRestaurantId(1L);
        if (optionalRestaurant.isPresent()) {
            String jsonContent = "{\n" +
                    "  \"restaurant_id\": 1,\n" +
                    "  \"restaurant_name\": \"피자매장\",\n" +
                    "  \"items\": [\n" +
                    "    {\n" +
                    "      \"menu_id\": 1,\n" +
                    "      \"name\": \"마르게리타 피자\",\n" +
                    "      \"price\": 12000,\n" +
                    "\t  \"quantity\": 1,\n" +
                    "      \"options\": [\n" +
                    "        {\n" +
                    "          \"option_id\": 1,\n" +
                    "          \"name\": \"사이즈\",\n" +
                    "          \"type\": \"RADIO\",\n" +
                    "          \"details\": [\n" +
                    "            {\n" +
                    "\t\t\t  \"option_detail_id\": 3,\n" +
                    "              \"name\": \"라지 사이즈\",\n" +
                    "              \"price\": 10000\n" +
                    "            }\n" +
                    "          ]\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"option_id\": 2,\n" +
                    "          \"name\": \"추가 토핑\",\n" +
                    "          \"type\": \"CHECK\",\n" +
                    "          \"details\": [\n" +
                    "            {\n" +
                    "\t\t\t  \"option_detail_id\": 4,\n" +
                    "              \"name\": \"페퍼로니\",\n" +
                    "              \"price\": 2000\n" +
                    "            },\n" +
                    "            {\n" +
                    "\t\t\t  \"option_detail_id\": 5,\n" +
                    "              \"name\": \"베이컨\",\n" +
                    "              \"price\": 1500\n" +
                    "            }\n" +
                    "          ]\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"menu_id\": 2,\n" +
                    "      \"name\": \"미트볼 스파게티\",\n" +
                    "      \"price\": 9000,\n" +
                    "\t  \"quantity\": 2,\n" +
                    "      \"options\": []\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"total_price\": 43500\n" +
                    "}";
            
            Map<String,Object> content = new ObjectMapper().readValue(jsonContent, HashMap.class);

            Restaurant restaurant = optionalRestaurant.get();

            for (int i = 1; i <= 1_000; i++) {
                orderRepository.save(Order.builder().restaurant(restaurant).content(content).price(BigDecimal.valueOf(43500)).status(OrderStatus.COMPLETED).address("서울시").addressDetail("강남구").postcode("01234").phone(1012345678L).ordererName("TEST" + i).build());
            }
        }

        Optional<Restaurant> optionalRestaurant2 = restaurantRepository.findByRestaurantId(2L);
        if (optionalRestaurant2.isPresent()) {
            String jsonContent = "{\n" +
                    "  \"restaurant_id\": 2,\n" +
                    "  \"restaurant_name\": \"한식매장\",\n" +
                    "  \"items\": [\n" +
                    "    {\n" +
                    "      \"menu_id\": 5,\n" +
                    "      \"name\": \"불고기\",\n" +
                    "      \"price\": 20000,\n" +
                    "\t  \"quantity\": 1,\n" +
                    "      \"options\": [\n" +
                    "        {\n" +
                    "          \"option_id\": 5,\n" +
                    "          \"name\": \"사이즈\",\n" +
                    "          \"type\": \"RADIO\",\n" +
                    "          \"details\": [\n" +
                    "            {\n" +
                    "\t\t\t  \"option_detail_id\": 12,\n" +
                    "              \"name\": \"700g\",\n" +
                    "              \"price\": 5000\n" +
                    "            }\n" +
                    "          ]\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"menu_id\": 6,\n" +
                    "      \"name\": \"제육\",\n" +
                    "      \"price\": 15000,\n" +
                    "\t  \"quantity\": 2,\n" +
                    "      \"options\": [\n" +
                    "        {\n" +
                    "          \"option_id\": 6,\n" +
                    "          \"name\": \"사이즈\",\n" +
                    "          \"type\": \"RADIO\",\n" +
                    "          \"details\": [\n" +
                    "            {\n" +
                    "\t\t\t  \"option_detail_id\": 13,\n" +
                    "              \"name\": \"500g\",\n" +
                    "              \"price\": 0\n" +
                    "            }\n" +
                    "          ]\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"total_price\": 55000\n" +
                    "}";

            Map<String,Object> content = new ObjectMapper().readValue(jsonContent, HashMap.class);

            Restaurant restaurant = optionalRestaurant2.get();

            for (int i = 1; i <= 1_000; i++) {
                orderRepository.save(Order.builder().restaurant(restaurant).content(content).price(BigDecimal.valueOf(55000)).status(OrderStatus.COMPLETED).address("서울시").addressDetail("강남구").postcode("01234").phone(1012345678L).ordererName("TEST" + i).build());
            }
        }
    }
}
