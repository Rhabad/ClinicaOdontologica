package modelosBD;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelosBD.Pacientes;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-28T23:56:28")
@StaticMetamodel(TipoPago.class)
public class TipoPago_ { 

    public static volatile SingularAttribute<TipoPago, Long> idTipoPago;
    public static volatile SingularAttribute<TipoPago, String> tipoPago;
    public static volatile ListAttribute<TipoPago, Pacientes> pacientesLista;

}