package modelosBD;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelosBD.Login;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-28T23:56:28")
@StaticMetamodel(Secretarios.class)
public class Secretarios_ { 

    public static volatile SingularAttribute<Secretarios, Integer> edadSecretario;
    public static volatile SingularAttribute<Secretarios, String> nombreSecretario;
    public static volatile SingularAttribute<Secretarios, String> apellidoSecretario;
    public static volatile SingularAttribute<Secretarios, Login> idLoginFK;
    public static volatile SingularAttribute<Secretarios, String> rutSecretario;
    public static volatile SingularAttribute<Secretarios, Date> fechaNacimientoSecretario;

}