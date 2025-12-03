package org.example.exhibitiontimeslotbooking.common.utils.pageable;

import java.util.Set;

public final class SortFields {
    private SortFields() {}
    public static final Set<String> VENUE_SORT = Set.of("id", "name", "createdAt", "updatedAt");
    public static final Set<String> EXHIBITION_SORTS = Set.of("id", "title", "createdAt", "updatedAt");
}
