package one.digitalinnovation.gof.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import one.digitalinnovation.gof.model.Endereco;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {
    private Long id;
    private String nome;
    private Endereco endereco;
}
