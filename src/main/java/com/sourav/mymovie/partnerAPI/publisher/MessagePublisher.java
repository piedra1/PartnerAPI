package com.sourav.mymovie.partnerAPI.publisher;

import com.sourav.mymovie.partnerAPI.entity.Seat;
import com.sourav.mymovie.partnerAPI.model.BookingRequest;
import com.sourav.mymovie.partnerAPI.repository.SeatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class MessagePublisher {


    @Value("spring.rabbitmq.template.exchange")
    private String exchange;
    @Value("mq.movie.routing_key")
    private String movie_routing_key;
    @Value("mq.theatre.routing_key")
    private String theatre_routing_key;


    @Autowired
    AsyncRabbitTemplate template;
    SeatRepository seatRepository;

    public void publishMessage(BookingRequest object, Seat seat) {

        if(object != null) {
            template.convertSendAndReceive(exchange, theatre_routing_key, object)

                             .addCallback(new ListenableFutureCallback<Object>() {
                                @Override
                                public void onSuccess(Object result) {

                                    seat.setStatus("Booked");
                                    seatRepository.saveAndFlush(seat);
                                }

                                @Override
                                public void onFailure(Throwable ex) {
                                    seat.setStatus("Available");
                                    seatRepository.saveAndFlush(seat);

                                }
                            });
            log.info("Sent to movie queue");
        }
    }

}
