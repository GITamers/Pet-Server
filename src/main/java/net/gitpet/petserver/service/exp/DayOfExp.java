package net.gitpet.petserver.service.exp;

public enum DayOfExp {

    LOW(5);

    private final int EXP_LIMIT;

    DayOfExp(int expLimit){
        EXP_LIMIT = expLimit;
    }

    int getExpLimit() {
        return EXP_LIMIT;
    }
    
}
