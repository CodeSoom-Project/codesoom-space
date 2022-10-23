package com.codesoom.myseat.db;

import com.codesoom.myseat.domain.*;
import com.codesoom.myseat.repositories.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("!mariadb")
public class DbInit {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final ReservationRepository reservationRepo;
    private final PlanRepository planRepo;
    private final RetrospectiveRepository retrospectiveRepo;

    User user1;
    User user2;
    
    public DbInit(
            UserRepository userRepo, 
            RoleRepository roleRepo, 
            ReservationRepository reservationRepo, 
            PlanRepository planRepo,
            RetrospectiveRepository retrospectiveRepo
    ) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.reservationRepo = reservationRepo;
        this.planRepo = planRepo;
        this.retrospectiveRepo = retrospectiveRepo;
    }

    @PostConstruct
    private void postConstruct() {
        addUsers();
        addReservations();
    }
    
    private void addUsers() {
        user1 = User.builder()
                .name("김철수")
                .email("soo@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();
        Role role1 = Role.builder()
                .userId(1L)
                .roleName("USER")
                .build();
        userRepo.save(user1);
        roleRepo.save(role1);
        
        user2 = User.builder()
                .name("김영희")
                .email("young@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();
        Role role2 = Role.builder()
                .userId(2L)
                .roleName("USER")
                .build();
        userRepo.save(user2);
        roleRepo.save(role2);
    }
    
    private void addReservations() {
        Plan plan1 = Plan.builder()
                .content("책읽기, 코테풀기")
                .build();
        Retrospective retrospective = Retrospective.builder()
                .content("잘했다.")
                .build();
        Reservation reservation1 = Reservation.builder()
                .date("2022-10-11")
                .user(user1)
                .plan(plan1)
                .retrospective(retrospective)
                .build();
        planRepo.save(plan1);
        reservationRepo.save(reservation1);
        retrospectiveRepo.save(retrospective);

        Plan plan2 = Plan.builder()
                .content("깃 공부")
                .build();
        Reservation reservation2 = Reservation.builder()
                .date("2022-10-19")
                .user(user1)
                .plan(plan2)
                .build();
        planRepo.save(plan2);
        reservationRepo.save(reservation2);
    }
}
