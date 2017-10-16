
package com.infulene.valley.partytogo.data;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class Evento
{
  private String hora;
  private String objectId;
  private String local;
  private String titulo;
  private String preco;
  private String imagem_url;
  private java.util.Date updated;
  private String ownerId;
  private java.util.Date data_evento;
  private String detalhes_evento;
  private java.util.Date created;
  public String getHora()
  {
    return hora;
  }

  public void setHora( String hora )
  {
    this.hora = hora;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getLocal()
  {
    return local;
  }

  public void setLocal( String local )
  {
    this.local = local;
  }

  public String getTitulo()
  {
    return titulo;
  }

  public void setTitulo( String titulo )
  {
    this.titulo = titulo;
  }

  public String getPreco()
  {
    return preco;
  }

  public void setPreco( String preco )
  {
    this.preco = preco;
  }

  public String getImagem_url()
  {
    return imagem_url;
  }

  public void setImagem_url( String imagem_url )
  {
    this.imagem_url = imagem_url;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public java.util.Date getData_evento()
  {
    return data_evento;
  }

  public void setData_evento( java.util.Date data_evento )
  {
    this.data_evento = data_evento;
  }

  public String getDetalhes_evento()
  {
    return detalhes_evento;
  }

  public void setDetalhes_evento( String detalhes_evento )
  {
    this.detalhes_evento = detalhes_evento;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

                                                    
  public Evento save()
  {
    return Backendless.Data.of( Evento.class ).save( this );
  }

  public void saveAsync( AsyncCallback<Evento> callback )
  {
    Backendless.Data.of( Evento.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( Evento.class ).remove( this );
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( Evento.class ).remove( this, callback );
  }

  public static Evento findById( String id )
  {
    return Backendless.Data.of( Evento.class ).findById( id );
  }

  public static void findByIdAsync( String id, AsyncCallback<Evento> callback )
  {
    Backendless.Data.of( Evento.class ).findById( id, callback );
  }

  public static Evento findFirst()
  {
    return Backendless.Data.of( Evento.class ).findFirst();
  }

  public static void findFirstAsync( AsyncCallback<Evento> callback )
  {
    Backendless.Data.of( Evento.class ).findFirst( callback );
  }

  public static Evento findLast()
  {
    return Backendless.Data.of( Evento.class ).findLast();
  }

  public static void findLastAsync( AsyncCallback<Evento> callback )
  {
    Backendless.Data.of( Evento.class ).findLast( callback );
  }

  public static List<Evento> find( DataQueryBuilder queryBuilder )
  {
    return Backendless.Data.of( Evento.class ).find( queryBuilder );
  }

  public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<Evento>> callback )
  {
    Backendless.Data.of( Evento.class ).find( queryBuilder, callback );
  }
}