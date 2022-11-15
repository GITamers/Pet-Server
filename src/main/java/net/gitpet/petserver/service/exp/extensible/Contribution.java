package net.gitpet.petserver.service.exp.extensible;

@FunctionalInterface
public interface Contribution {

    int todayContributions(Contribution.Request request);

    final class Request{

        private final String name;
        private final String token;

        private Request(){
            throw new UnsupportedOperationException("Can not invoke constructor \"Request()\"");
        }

        private Request(Contribution.Request.Builder builder){
            this.name = builder.name;
            this.token = builder.token;
        }

        public static Contribution.Request.Builder getBuilder(){
            return new Builder();
        }

        public String getName(){
            return name;
        }

        public String getToken(){
            return this.token;
        }

        public final static class Builder{

            private String name;
            private String token;

            private Builder(){}

            public Builder name(String name){
                this.name = name;
                return this;
            }

            public Builder token(String token){
                this.token = token;
                return this;
            }

            public Request build(){
                return new Request(this);
            }

        }

    }

}
