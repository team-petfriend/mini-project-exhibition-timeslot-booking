package org.example.exhibitiontimeslotbooking.entity.file;


import jakarta.persistence.*;
import lombok.*;
import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;
import org.example.exhibitiontimeslotbooking.entity.venue.Venue;

@Entity
@Table(name = "exhibition_file_infos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ExhibitionFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "venue_id", nullable = false, foreignKey = @ForeignKey(name = "fk_exhibition_files_exhibition"))
    private Venue venue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "file_id", nullable = false, foreignKey = @ForeignKey(name = "fk_exhibition_files_file_info"))
    private FileInfo fileInfo;

    private Integer displayOrder;

    public void updateDisplayOrder(Integer newOrder) {
        this.displayOrder = newOrder;
    }

    @Builder
    public ExhibitionFile (Venue venue, FileInfo fileInfo, Integer displayOrder){
        this.venue = venue;
        this.fileInfo = fileInfo;
        this.displayOrder = displayOrder;
    }

}
