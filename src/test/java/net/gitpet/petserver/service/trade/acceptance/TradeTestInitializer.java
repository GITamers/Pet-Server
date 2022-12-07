package net.gitpet.petserver.service.trade.acceptance;


import net.gitpet.petserver.domain.Pet;
import net.gitpet.petserver.domain.Trade;
import net.gitpet.petserver.domain.User;
import net.gitpet.petserver.domain.UserPet;
import net.gitpet.petserver.repository.PetRepository;
import net.gitpet.petserver.repository.TradeRepository;
import net.gitpet.petserver.repository.UserRepository;
import net.gitpet.petserver.repository.UserPetRepository;
import net.gitpet.petserver.testannotation.TestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@TestBean
@Transactional
public class TradeTestInitializer {

    private final UserRepository USER_REPOSITORY;
    private final UserPetRepository USER_PET_REPOSITORY;
    private final TradeRepository TRADE_REPOSITORY;
    private final PetRepository PET_REPOSITORY;

    public Response initializeAllInOne(Request request){
        long petTypeId = initializePetType();
        long sellerId = initializeUser(request.user.point, request.user.commitCount);
        long itemId = initializeUserPet(request.userPet.isMainPet,
                                        request.userPet.level,
                                        request.userPet.commitCount,
                                        sellerId,
                                        petTypeId);
        long tradeId = initializeTrade(request.trade.title, request.trade.price, sellerId, itemId, request.trade.createdAt);
        return Response.builder()
                .sellerId(sellerId)
                .sellerPoint(request.user.point)
                .userPetId(itemId)
                .isMainPet(request.userPet.isMainPet)
                .petLevel(request.userPet.level)
                .petTypeId(petTypeId)
                .tradeId(tradeId)
                .tradePrice(request.trade.price)
                .tradeTitle(request.trade.title)
                .tradeCreatedAt(request.trade.createdAt)
                .build();
    }

    public void deprecateAll(){
        PET_REPOSITORY.deleteAll();
        TRADE_REPOSITORY.deleteAll();
        USER_PET_REPOSITORY.deleteAll();
        USER_REPOSITORY.deleteAll();
    }

    public static class Request{
        private User user;
        private UserPet userPet;
        private Trade trade;

        private Request(){}

        private Request(Request.Builder builder){
            this.user = builder.user;
            this.userPet = builder.userPet;
            this.trade = builder.trade;
        }

        public static Request.Builder builder(){
            return new Request.Builder();
        }

        public static class Builder{
            private User user;
            private UserPet userPet;
            private Trade trade;

            private Builder(){}

            public Request.Builder user(User user){
                this.user = user;
                return this;
            }

            public Request.Builder userPet(UserPet userPet){
                this.userPet = userPet;
                return this;
            }

            public Request.Builder trade(Trade trade){
                this.trade = trade;
                return this;
            }

            public Request build(){
                return new Request(this);
            }
        }

        public static class User{
            private long point;
            private long commitCount;

            private User(Request.User.Builder builder){
                this.point = builder.point;
                this.commitCount = builder.commitCount;
            }

            public static Request.User.Builder builder(){
                return new Builder();
            }

            public static class Builder{
                private long point;
                private long commitCount;

                private Builder(){}

                public Request.User.Builder point(long point){
                    this.point = point;
                    return this;
                }

                public Request.User.Builder commitCount(long commitCount){
                    this.commitCount = commitCount;
                    return this;
                }

                public Request.User build(){
                    return new Request.User(this);
                }

            }

        }

        public static class UserPet{
            private boolean isMainPet;
            private long level;
            private long commitCount;

            private UserPet(Request.UserPet.Builder builder){
                this.isMainPet = builder.isMainPet;
                this.level = builder.level;
                this.commitCount = builder.commitCount;
            }

            public static Request.UserPet.Builder builder(){
                return new Builder();
            }

            public static class Builder{
                private boolean isMainPet;
                private long level;
                private long commitCount;

                private Builder(){}

                public Request.UserPet.Builder isMainPet(boolean isMainPet){
                    this.isMainPet = isMainPet;
                    return this;
                }

                public Request.UserPet.Builder level(long level){
                    this.level = level;
                    return this;
                }

                public Request.UserPet.Builder commitCount(long commitCount){
                    this.commitCount = commitCount;
                    return this;
                }

                public Request.UserPet build(){
                    return new Request.UserPet(this);
                }

            }

        }

        public static class Trade{
            private String title;
            private int price;
            private LocalDateTime createdAt;

            private Trade(Request.Trade.Builder builder){
                this.title = builder.title;
                this.price = builder.price;
                this.createdAt = builder.createdAt;
            }

            public static Request.Trade.Builder builder(){
                return new Builder();
            }

            public static class Builder{
                private String title;
                private int price;
                private LocalDateTime createdAt;

                private Builder(){}

                public Request.Trade.Builder title(String title){
                    this.title = title;
                    return this;
                }

                public Request.Trade.Builder price(int price){
                    this.price = price;
                    return this;
                }

                public Request.Trade.Builder createdAt(LocalDateTime createdAt){
                    this.createdAt = createdAt;
                    return this;
                }

                public Request.Trade build(){
                    return new Request.Trade(this);
                }
            }
        }
    }

    public static class Response{
        private final long SELLER_ID;
        private final long SELLER_POINT;
        private final long USER_PET_ID;
        private final boolean IS_MAIN_PET;
        private final long PET_LEVEL;
        private final long PET_TYPE_ID;
        private final long TRADE_ID;
        private final int TRADE_PRICE;
        private final String TRADE_TITLE;
        private final LocalDateTime TRADE_CREATED_AT;

        private Response(Response.Builder builder){
            SELLER_ID = builder.sellerId;
            SELLER_POINT = builder.sellerPoint;

            USER_PET_ID = builder.userPetId;
            IS_MAIN_PET = builder.isMainPet;
            PET_LEVEL = builder.petLevel;
            PET_TYPE_ID = builder.petTypeId;

            TRADE_ID = builder.tradeId;
            TRADE_PRICE = builder.tradePrice;
            TRADE_TITLE = builder.tradeTitle;
            TRADE_CREATED_AT = builder.tradeCreatedAt;
        }

        public static Response.Builder builder(){
            return new Builder();
        }

        public long getSellerId(){
            return SELLER_ID;
        }

        public long getSellerPoint(){
            return SELLER_POINT;
        }

        public long getUserPetId(){
            return USER_PET_ID;
        }

        public boolean getIsMainPet(){
            return IS_MAIN_PET;
        }

        public long getPetLevel(){
            return PET_LEVEL;
        }

        public long getPetTypeId(){
            return PET_TYPE_ID;
        }

        public long getTradeId(){
            return TRADE_ID;
        }

        public int getTradePrice(){
            return TRADE_PRICE;
        }

        public String getTradeTitle(){
            return TRADE_TITLE;
        }

        public LocalDateTime getTradeCreatedAt(){
            return TRADE_CREATED_AT;
        }

        public static class Builder{
            private long sellerId;
            private long sellerPoint;
            private long userPetId;
            private boolean isMainPet;
            private long petLevel;
            private long petTypeId;
            private long tradeId;
            private int tradePrice;
            private String tradeTitle;
            private LocalDateTime tradeCreatedAt;


            private Builder(){}

            public Response.Builder sellerId(long sellerId){
                this.sellerId = sellerId;
                return this;
            }

            public Response.Builder sellerPoint(long sellerPoint){
                this.sellerPoint = sellerPoint;
                return this;
            }

            public Response.Builder userPetId(long userPetId){
                this.userPetId = userPetId;
                return this;
            }

            public Response.Builder isMainPet(boolean isMainPet){
                this.isMainPet = isMainPet;
                return this;
            }

            public Response.Builder petLevel(long petLevel){
                this.petLevel = petLevel;
                return this;
            }

            public Response.Builder petTypeId(long petTypeId){
                this.petTypeId = petTypeId;
                return this;
            }

            public Response.Builder tradeId(long tradeId){
                this.tradeId = tradeId;
                return this;
            }

            public Response.Builder tradePrice(int tradePrice){
                this.tradePrice = tradePrice;
                return this;
            }

            public Response.Builder tradeTitle(String tradeTitle){
                this.tradeTitle = tradeTitle;
                return this;
            }

            public Response.Builder tradeCreatedAt(LocalDateTime tradeCreatedAt){
                this.tradeCreatedAt = tradeCreatedAt;
                return this;
            }

            public Response build(){
                return new Response(this);
            }

        }

    }

    private long initializeUser(long point, long commitCount){
        User user = new User();
        user.setPoint(point);
        user.setCreatedAt(LocalDateTime.now());
        user.setCommitUpdatedAt(LocalDateTime.now());
        user.setCommitCnt(commitCount);
        return USER_REPOSITORY.saveAndFlush(user).getUid();
    }

    private long initializeUserPet(boolean isMainPet, long level, long commitCount, long ownerId, long petTypeId) {
        UserPet userPet = new UserPet();
        userPet.setMainPet(isMainPet);
        userPet.setOwner(USER_REPOSITORY.findById(ownerId).orElseThrow());
        userPet.setAdoptedAt(LocalDateTime.now());
        userPet.setLevel(level);
        userPet.setCommitCnt(commitCount);
        userPet.setType(PET_REPOSITORY.findById(petTypeId).orElseThrow());
        return USER_PET_REPOSITORY.saveAndFlush(userPet).getUpid();
    }

    private long initializeTrade(String title, int price, long sellerId, long itemId, LocalDateTime createdAt){
        Trade trade = new Trade();
        trade.setPrice(price);
        trade.setTitle(title);
        trade.setSeller(USER_REPOSITORY.findById(sellerId).orElseThrow());
        trade.setCreatedAt(createdAt);
        trade.setPet(USER_PET_REPOSITORY.findById(itemId).orElseThrow());
        return TRADE_REPOSITORY.saveAndFlush(trade).getTid();
    }

    private long initializePetType(){
        Pet pet = new Pet();
        pet.setImgSrc("it's mock pet");
        return PET_REPOSITORY.saveAndFlush(pet).getPid();
    }

    @Autowired
    TradeTestInitializer(UserRepository userRepository,
                         UserPetRepository userPetRepository,
                         TradeRepository tradeRepository,
                         PetRepository petRepository){
        USER_REPOSITORY = userRepository;
        USER_PET_REPOSITORY = userPetRepository;
        TRADE_REPOSITORY = tradeRepository;
        PET_REPOSITORY = petRepository;
    }

}
