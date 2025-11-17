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
        public static final String ID_ONLY = "/{userId}";
        public static final String BY_ID = ROOT + ID_ONLY;
        public static final String ME = ROOT + "/me";
        public static final String PASSWORD = ID_ONLY + "/password";
    }

    public static final class Roles {
        private Roles() {}

        public static final String ROOT = BASE + "/roles";
        public static final String GRANT = Users.BY_ID + "/roles";
        public static final String COLLECT = Users.BY_ID + GRANT + "/{roleName}";
    }

    public static final class Venues {
        private Venues() {}

        public static final String ROOT = BASE + "/venues";
        public static final String ID_ONLY = "/{venueId}";
        public static final String BY_ID = ROOT + ID_ONLY;
        public static final String COUNT = ROOT + "/count";
        public static final String SEARCH = ROOT + "/search";
        public static final String PAGE = ROOT + "/page";
        public static final String LIKE = BY_ID + "/like";
        public static final String LIKE_CANCEL = BY_ID + "/like/cancel";
        public static final String LIKE_COUNT = BY_ID + "/like/count";
    }

    public static final class Exhibitions {
        private Exhibitions() {}

        public static final String ROOT = BASE + "/exhibitions";
        public static final String ID_ONLY = "/{id}";
        public static final String BY_ID = ROOT + ID_ONLY;
        public static final String STATUS =  BY_ID + "/status";
        public static final String SLOTS =  BY_ID + "/slots";
    }

    public static final class Timeslots {
        public static final String ROOT =  BASE + "/slots";
        public static final String ID_ONLY = "/{slotId}";
        public static final String BY_ID = ROOT + ID_ONLY;
        public static final String STATUS =  BY_ID + "/status";
    }
}
