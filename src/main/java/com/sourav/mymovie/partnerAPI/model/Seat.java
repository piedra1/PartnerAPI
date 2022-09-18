package com.sourav.mymovie.partnerAPI.model;

import com.sourav.mymovie.partnerAPI.entity.Theatre;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;

@Data
@Slf4j
@EqualsAndHashCode
public class Seat {

    private Integer x;
    private Integer y;
    private ShowTime showTime;

}
