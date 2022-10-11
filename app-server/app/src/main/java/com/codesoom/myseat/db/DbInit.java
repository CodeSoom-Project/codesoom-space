package com.codesoom.myseat.db;

import com.codesoom.myseat.domain.*;
import com.codesoom.myseat.repositories.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class DbInit {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final SeatReservationRepository reservationRepo;
    private final PlanRepository planRepo;
    private final SeatRepository seatRepo;

    public DbInit(
            UserRepository userRepo, 
            RoleRepository roleRepo, 
            SeatReservationRepository reservationRepo, 
            PlanRepository planRepo,
            SeatRepository seatRepo
    ) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.reservationRepo = reservationRepo;
        this.planRepo = planRepo;
        this.seatRepo = seatRepo;
    }

    @PostConstruct
    private void postConstruct() {
        Seat seat1 = Seat.builder()
                .number(1)
                .build();
        Seat seat2 = Seat.builder()
                .number(2)
                .build();
        Seat seat3 = Seat.builder()
                .number(3)
                .build();
        Seat seat4 = Seat.builder()
                .number(4)
                .build();
        Seat seat5 = Seat.builder()
                .number(5)
                .build();
        Seat seat6 = Seat.builder()
                .number(6)
                .build();
        Seat seat7 = Seat.builder()
                .number(7)
                .build();
        Seat seat8 = Seat.builder()
                .number(8)
                .build();
        ArrayList<Seat> seats = new ArrayList<>();
        seats.add(seat1);
        seats.add(seat2);
        seats.add(seat3);
        seats.add(seat4);
        seats.add(seat5);
        seats.add(seat6);
        seats.add(seat7);
        seats.add(seat8);
        seatRepo.saveAll(seats);
        
        User user1 = User.builder()
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();
        Role role1 = Role.builder()
                .email("soo@email.com")
                .roleName("USER")
                .build();
        User user2 = User.builder()
                .name("김영희")
                .email("young@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();
        Role role2 = Role.builder()
                .email("young@email.com")
                .roleName("USER")
                .build();
        userRepo.save(user1);
        roleRepo.save(role1);
        userRepo.save(user2);
        roleRepo.save(role2);

        Plan plan1 = Plan.builder()
                .plan("책읽기, 코테풀기")
                .build();

        SeatReservation reservation1 = SeatReservation.builder()
                .date("2022-10-11")
                .user(user1)
                .plan(plan1)
                .build();

        plan1.addReservation(reservation1);

        planRepo.save(plan1);
        reservationRepo.save(reservation1);
    }
}
