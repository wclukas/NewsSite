
package wad.domain;


import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormUutinen {
    
    @NotEmpty
    @Size(min = 7, max = 40)
    private String otsikko;
    
    @NotEmpty
    @Size(min = 20, max = 100)
    private String lead;
    
    @NotEmpty
    @Size(min = 20, max = 10000)
    private String text;
    
    @NotNull
    private File kuva;
    
    @NotNull
    private List<Long> kategoriatId;
    
    @NotNull
    private List<Long> kirjoittajatId;
}
