package rocketseat.com.passin.domain.attendee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rocketseat.com.passin.domain.event.Event;

@Entity
@Table(name = "attendees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendee {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @ManyToOne
  @JoinColumn(name = "event_id", nullable = false)
  private Event event;

  @Column(name = "created_at")
  private LocalDateTime createdAt;
}
