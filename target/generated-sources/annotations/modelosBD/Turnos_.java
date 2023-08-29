package modelosBD;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelosBD.Odontologos;
import modelosBD.Pacientes;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-28T23:56:28")
@StaticMetamodel(Turnos.class)
public class Turnos_ { 

    public static volatile SingularAttribute<Turnos, Long> idTurnos;
    public static volatile SingularAttribute<Turnos, Date> fechaAtencion;
    public static volatile SingularAttribute<Turnos, Pacientes> rutPacienteFK;
    public static volatile SingularAttribute<Turnos, Odontologos> rutOdontologoFK;

}