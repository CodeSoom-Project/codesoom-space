package com.codesoom.myseat.db;

import com.codesoom.myseat.domain.*;
import com.codesoom.myseat.enums.ReservationStatus;
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

        User adminUser = User.builder()
                .name("어드민")
                .email("admin@email.com")
                .password("$2a$10$hxqWrlGa7SQcCEGURjmuQup4J9kN6qnfr4n7j7R3LvzHEoEOUTWeW")
                .build();
        adminUser = userRepo.save(adminUser);
        Role role3 = Role.builder()
                .userId(adminUser.getId())
                .roleName("ADMIN")
                .build();
        roleRepo.save(role3);
    }

    private void addReservations() {
        Plan plan1 = Plan.builder()
                .content("책읽기, 코테풀기")
                .build();
        Retrospective retrospective = Retrospective.builder()
                .content("잘했다.")
                .build();
        Reservation reservation1 = Reservation.builder()
                .date(new Date("2022-10-11"))
                .user(user1)
                .plan(plan1)
                .status(ReservationStatus.RETROSPECTIVE_COMPLETE)
                .retrospective(retrospective)
                .build();
        planRepo.save(plan1);
        reservationRepo.save(reservation1);
        retrospectiveRepo.save(retrospective);

        Plan plan2 = Plan.builder()
                .content("깃 공부")
                .build();
        Reservation reservation2 = Reservation.builder()
                .date(new Date("2022-10-19"))
                .user(user1)
                .plan(plan2)
                .status(ReservationStatus.CANCELED)
                .build();
        planRepo.save(plan2);
        reservationRepo.save(reservation2);

        Plan plan3;
        Reservation reservation3;
        for (int i = 1; i <= 20; i++) {
            plan3 = Plan.builder()
                    .content("깃 공부")
                    .build();

            reservation3 = Reservation.builder()
                    .date(new Date("2022-11-" + i))
                    .user(user1)
                    .plan(plan2)
                    .status(ReservationStatus.RETROSPECTIVE_WAITING)
                    .build();
            planRepo.save(plan3);
            reservationRepo.save(reservation3);
        }

    }
}
