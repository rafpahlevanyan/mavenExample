package education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson implements Serializable {
    private String lessonName;
    private int duration;
    private String lecturerName;
    private int price;


}
