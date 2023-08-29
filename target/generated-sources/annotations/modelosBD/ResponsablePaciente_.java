package modelosBD;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelosBD.Pacientes;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-28T23:56:28")
@StaticMetamodel(ResponsablePaciente.class)
public class ResponsablePaciente_ { 

    public static volatile SingularAttribute<ResponsablePaciente, Date> fechaNacimientoResponsable;
    public static volatile SingularAttribute<ResponsablePaciente, String> nombreResponsable;
    public static volatile SingularAttribute<ResponsablePaciente, String> apellidoResponsable;
    public static volatile SingularAttribute<ResponsablePaciente, String> rutResponsable;
    public static volatile SingularAttribute<ResponsablePaciente, Integer> edadResponsable;
    public static volatile ListAttribute<ResponsablePaciente, Pacientes> pacientesLista;

}