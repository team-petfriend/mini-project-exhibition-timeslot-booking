package org.example.exhibitiontimeslotbooking.common.constants;

public class ApiMappingPattern {
    private ApiMappingPattern() {}

    public static final String API = "/api";
    public static final String V1 = "/v1";
    public static final String BASE = API + V1;

    public static final class Auth {
        private Auth() {}

        public static final String ROOT = BASE + "/auth";

        public static final String LOGIN = ROOT + "/login";
        public static final String LOGOUT = ROOT + "/logout";
        public static final String REFRESH = ROOT + "/refresh";
        public static final String SIGNUP = ROOT + "/signup";
    }

    public static final class Users {
        private Users() {}

        public static final String ROOT = BASE + "/users";
        public static final String BY_ID = ROOT + "/{userId}";
        public static final String ME = ROOT + "/me";
        public static final String PROFILE = ME + "/profile";
    }

    public static final class Roles {
        private Roles() {}

        public static final String ROOT = BASE + "/roles";
        public static final String GRANT = Users.BY_ID + "/roles";
        public static final String COLLECT =  GRANT + "/{roleName}";
    }

    public static final class Venues {
        private Venues() {}
        public static final String ROOT = BASE + "/venues";
        public static final String ID_ONLY = "/{venueId}";
        public static final String BY_ID = ROOT + ID_ONLY;

        public static final String VENUE_FILE =  ID_ONLY + "/file";
        public static final String VENUE_FILE_ID = VENUE_FILE + "/{fileId}";
    }

    public static final class Exhibitions {
        private Exhibitions() {}
        public static final String ROOT = Venues.BY_ID + "/exhibitions";
        public static final String ID_ONLY = "/{exhibitionId}";
        public static final String BY_ID = ROOT + ID_ONLY;
        public static final String EXHIBITION_FILE = BY_ID + "/files";
        public static final String EXHIBITION_FILE_ID = "/{fileId}";

        public static final String SEARCH = "/search";
        public static final String STATUS = ID_ONLY + "/status";
    }

    public static final class Timeslots {
        public static final String ROOT =  Exhibitions.BY_ID + "/slots";
        public static final String ID_ONLY = "/{slotId}";
        public static final String BY_ID = ROOT + ID_ONLY;
        public static final String STATUS =  ID_ONLY + "/status";
        public static final String AUTO_STATUS = ID_ONLY + "/auto-status";
    }

    public static final class Bookings {
        private Bookings() {}

        public static final String ROOT = BASE + "/bookings";
        public static final String ID_ONLY = "/{bookingId}";
        public static final String BY_ID = BASE + ID_ONLY;
        public static final String BOOKING_CANCEL = BY_ID + "/cancel";
        public static final String BOOKING_REFUND = BY_ID + "/refund";

    }

    public static final class Tickets {
        private Tickets() {}

        public static final String TICKETS = Bookings.BY_ID + "/tickets";
        public static final String ROOT = BASE + "/tickets";
        public static final String ID_ONLY = "/{ticketId}";
        public static final String BY_ID = BASE+ID_ONLY;
        public static final String TICKET_USE = BY_ID + "/use";
        public static final String TICKET_VOID = BY_ID + "/void";
        public static final String TICKET_SCAN = BASE + "/scan";
    }

    public static final class Payments {
        private Payments() {}

        public static final String ROOT = BASE+"/payments";
        public static final String ID_ONLY = "/{paymentId}";
        public static final String BY_ID = ROOT + ID_ONLY;
        public static final String REFUND_PAY  = BY_ID + "/refund";
        public static final String WEBHOOK_PAY = ROOT + "/webhook";
    }

    public static final class Reviews {
        private Reviews() {}

        public static final String ROOT = BASE + "/exhibitions/{exhibitionId}/reviews";
        public static final String BY_ID = BASE + "/reviews/{reviewId}";
    }
}
