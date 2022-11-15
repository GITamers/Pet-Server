package net.gitpet.petserver.service.exp;

public enum DayOfExp {

    LOW(5);

    private final int expLimit;

    DayOfExp(int expLimit){
        this.expLimit = expLimit;
    }

    int getExpLimit() {
        return expLimit;
    }
    
}
