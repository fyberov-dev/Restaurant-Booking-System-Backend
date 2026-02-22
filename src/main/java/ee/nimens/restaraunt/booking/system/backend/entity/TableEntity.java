package ee.nimens.restaraunt.booking.system.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tables")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int guests;

    @Column(name = "x", nullable = false)
    private float posX;

    @Column(name = "y", nullable = false)
    private float posY;

    @ManyToMany
    @JoinTable(
        name = "tables_table_types",
        joinColumns = @JoinColumn(name = "table_id"),
        inverseJoinColumns = @JoinColumn(name = "table_type_id")
    )
    private Set<TableTypeEntity> type;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

}
