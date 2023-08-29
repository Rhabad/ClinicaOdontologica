package modelosBD;

import java.sql.Date;
import java.sql.Time;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelosBD.Login;
import modelosBD.Turnos;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-28T23:56:28")
@StaticMetamodel(Odontologos.class)
public class Odontologos_ { 

    public static volatile SingularAttribute<Odontologos, String> apellidoOdontologo;
    public static volatile SingularAttribute<Odontologos, Login> idLoginFK;
    public static volatile SingularAttribute<Odontologos, String> horarioDias;
    public static volatile SingularAttribute<Odontologos, Integer> edadOdontologo;
    public static volatile SingularAttribute<Odontologos, String> nombreOdontologo;
    public static volatile SingularAttribute<Odontologos, String> rutOdontologo;
    public static volatile SingularAttribute<Odontologos, Time> horarioHoras;
    public static volatile SingularAttribute<Odontologos, String> especialidad;
    public static volatile SingularAttribute<Odontologos, Date> fechaNacimientoOdontologo;
    public static volatile ListAttribute<Odontologos, Turnos> turnosLista;

}