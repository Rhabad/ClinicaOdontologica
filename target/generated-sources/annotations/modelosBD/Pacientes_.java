package modelosBD;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelosBD.Descripcion;
import modelosBD.ResponsablePaciente;
import modelosBD.TipoPago;
import modelosBD.Turnos;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-28T23:56:28")
@StaticMetamodel(Pacientes.class)
public class Pacientes_ { 

    public static volatile SingularAttribute<Pacientes, TipoPago> idTipoPagoFK;
    public static volatile SingularAttribute<Pacientes, String> apellidoPaciente;
    public static volatile ListAttribute<Pacientes, Descripcion> descripcionLista;
    public static volatile SingularAttribute<Pacientes, Date> fechaNacimientoPaciente;
    public static volatile SingularAttribute<Pacientes, String> nombrePaciente;
    public static volatile SingularAttribute<Pacientes, ResponsablePaciente> rutResponsableFK;
    public static volatile SingularAttribute<Pacientes, String> rutPaciente;
    public static volatile SingularAttribute<Pacientes, Integer> edadPaciente;
    public static volatile ListAttribute<Pacientes, Turnos> turnosLista;

}