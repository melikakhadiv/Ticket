package ir.mft.ticket;

import ir.mft.ticket.model.TicketGroup;
import ir.mft.ticket.repository.TicketGroupRepository;
import org.hibernate.sql.ast.tree.from.TableJoin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketApplication {
    private static TicketGroupRepository ticketGroupRepository;

    public TicketApplication(TicketGroupRepository ticketGroupRepository){
        this.ticketGroupRepository=ticketGroupRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TicketApplication.class, args);

        TicketGroup ticketGroup1 = TicketGroup.builder().title("group1").build();
        ticketGroupRepository.save(ticketGroup1);

        TicketGroup ticketGroup2 = TicketGroup.builder().title("group2").parent(ticketGroup1).build();
        ticketGroupRepository.save(ticketGroup2);
    }

}
