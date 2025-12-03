package org.example.exhibitiontimeslotbooking.common.utils.pageable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PageableUtils {
    public static Pageable buildPageable(int page, int size, String[] sortParms, Set<String> allowedSorts) {

        Sort sort = Sort.by("createdAt").descending();

        if (sortParms != null && sortParms.length > 0) {
            List<Sort.Order> orders = new ArrayList<>();

            for (int i = 0; i < sortParms.length; i++) {
                String value = sortParms[i];

                String property;
                String direction;

                // 다중 정렬
                if (value.contains(",")) {
                    String[] parts = value.split(",", 2);
                    property = parts[0].trim();
                    direction = parts.length > 1 ? parts[1].trim() : "desc";
                } else {
                    // 단일 정렬
                    property = value.trim();
                    // i+1 배열 요소가
                    String next = (i + 1 < sortParms.length) ? sortParms[i + 1].trim() : "";

                    if ("desc".equalsIgnoreCase(next) || "asc".equalsIgnoreCase(next)) {
                        direction = next;
                        i++;
                    } else {
                        direction = "desc";
                    }
                }
                if (!allowedSorts.contains(property)) continue;

                Sort.Direction dir = "desc".equalsIgnoreCase(direction)
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;

                orders.add(new Sort.Order(dir, property));
                }
                if (!orders.isEmpty()) sort = Sort.by(orders);
            }
        return PageRequest.of(page, size, sort);
    }
}

