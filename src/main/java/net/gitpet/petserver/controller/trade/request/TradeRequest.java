package net.gitpet.petserver.controller.trade.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public final class TradeRequest {

    @JsonProperty("user-id")
    private long sellerId;

    @JsonProperty("pet-id")
    private long petId;

    @NotEmpty
    private String title;

    @NotNull
    private int price;

}
