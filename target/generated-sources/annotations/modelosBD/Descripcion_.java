package modelosBD;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelosBD.Pacientes;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-28T23:56:28")
@StaticMetamodel(Descripcion.class)
public class Descripcion_ { 

    public static volatile SingularAttribute<Descripcion, String> descripcion;
    public static volatile SingularAttribute<Descripcion, Long> idDescripcion;
    public static volatile SingularAttribute<Descripcion, Pacientes> rutPacienteFK;

}