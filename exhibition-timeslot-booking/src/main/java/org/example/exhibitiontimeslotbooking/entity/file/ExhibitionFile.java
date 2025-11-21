package org.example.exhibitiontimeslotbooking.entity.file;


import jakarta.persistence.*;
import lombok.*;
import org.example.exhibitiontimeslotbooking.entity.exhibition.Exhibition;

@Entity
@Table(name = "exhibition_file_infos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class ExhibitionFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exhibition_id", nullable = false, foreignKey = @ForeignKey(name = "fk_exhibition_files_exhibition"))
    private Exhibition exhibition;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "file_id", nullable = false, foreignKey = @ForeignKey(name = "fk_exhibition_files_file_info"))
    private FileInfo fileInfo;

}
