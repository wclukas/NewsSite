
package wad.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FormKirjoittaja {
    @NotEmpty
    @Size(min = 6, max = 40)
    private String name;
}
