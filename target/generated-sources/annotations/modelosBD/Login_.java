package modelosBD;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelosBD.Odontologos;
import modelosBD.Secretarios;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-28T23:56:28")
@StaticMetamodel(Login.class)
public class Login_ { 

    public static volatile SingularAttribute<Login, Date> fechaNacimiento;
    public static volatile SingularAttribute<Login, String> apellido;
    public static volatile ListAttribute<Login, Odontologos> odontologoLista;
    public static volatile SingularAttribute<Login, String> usuario;
    public static volatile SingularAttribute<Login, String> contrasenia;
    public static volatile ListAttribute<Login, Secretarios> secretarioLista;
    public static volatile SingularAttribute<Login, Long> idLogin;
    public static volatile SingularAttribute<Login, String> nombre;
    public static volatile SingularAttribute<Login, String> email;

}