package Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Entities.Cuotas;
import Entities.Socio;
import Logic.SocioLogic;

public class DataCuotas {

	public LinkedList<Cuotas> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		LinkedList<Cuotas> cuotas= new LinkedList<>();
		
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("select * from cuotas");
			if(rs!=null) {
				while(rs.next()) {
					Cuotas c=new Cuotas();
					c.setIdCuota(rs.getInt("idcuotas")); //La clase se llama en plural el get es en singular pero la columna en la bd es en plural
					c.setFechaPago(rs.getDate("fechaPago"));
					c.setFechaDesde(rs.getDate("fechaDesde"));
					c.setFechaHasta(rs.getDate("fechaHasta"));
					// Busco el objeto autor para el libro
					int idSocio = rs.getInt("idSocio");
					SocioLogic soclog = new SocioLogic();
					Socio elSocio = new Socio();
					elSocio.setIdSocio(idSocio);
					elSocio = soclog.getOneById(elSocio);
					// Le agrego el autor
					c.setSocio(elSocio);
					c.setEstado(rs.getString("estado"));
					// Añado el libro con autor incluido a la LinkedList.
					cuotas.add(c);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cuotas;
	} // fin metodo GetAll
	
	public Cuotas getById(Cuotas cuotasToSearch) {
		Cuotas c=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from cuotas where idcuotas=?"
					);
			stmt.setInt(1, cuotasToSearch.getIdCuota());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				c=new Cuotas();
				c.setIdCuota(rs.getInt("idcuotas")); //La clase se llama en plural el get es en singular pero la columna en la bd es en plural
				c.setFechaPago(rs.getDate("fechaPago"));
				c.setFechaDesde(rs.getDate("fechaDesde"));
				c.setFechaHasta(rs.getDate("fechaHasta"));
				// Busco el objeto autor para el libro
				int idSocio = rs.getInt("idSocio");
				SocioLogic soclog = new SocioLogic();
				Socio elSocio = new Socio();
				elSocio.setIdSocio(idSocio);
				elSocio = soclog.getOneById(elSocio);
				// Le agrego el autor
				c.setSocio(elSocio);	
				c.setEstado(rs.getString("estado"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return c;
	} // Fin Metodo GetById

	public void add(Cuotas cuotas) {
		PreparedStatement stmt= null;
		ResultSet keyResultSet=null;
		//SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"insert into cuotas(fechaPago, fechaDesde, fechaHasta, idSocio, estado) values(?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
			stmt.setString(1, cuotas.getFechaPago().toString());
			stmt.setString(2, cuotas.getFechaDesde().toString());
			stmt.setString(3, cuotas.getFechaHasta().toString());
			stmt.setInt(4, cuotas.getSocio().getIdSocio());
			stmt.setString(5, cuotas.getEstado());
			stmt.executeUpdate();
			
			keyResultSet=stmt.getGeneratedKeys();
            if(keyResultSet!=null && keyResultSet.next()){
                cuotas.setIdCuota(keyResultSet.getInt(1));
            }

			
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(keyResultSet!=null)keyResultSet.close();
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}

	} // FIN METODO ADD
	
	public void update(Cuotas cuotas) {
		PreparedStatement stmt= null;
		String fechaPago;
		if(cuotas.getFechaPago()==null) {
			fechaPago=null;
		}else {
			fechaPago=cuotas.getFechaPago().toString();
		}

		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"update cuotas set fechaPago=?, fechaDesde=?, fechaHasta=?, idSocio=?, estado=? where idcuotas = ?");
			stmt.setString(1, fechaPago);
			stmt.setString(2, cuotas.getFechaDesde().toString());
			stmt.setString(3, cuotas.getFechaHasta().toString());
			stmt.setInt(4, cuotas.getSocio().getIdSocio());
			stmt.setString(5, cuotas.getEstado());
			stmt.setInt(6, cuotas.getIdCuota());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	} // FIN METODO UPDATE
	
	public void remove(Cuotas cuotas) {
		PreparedStatement stmt= null;
		try {
			stmt=DbConnector.getInstancia().getConn().
					prepareStatement(
							"delete from cuotas where idcuotas=?");
			stmt.setInt(1, cuotas.getIdCuota());
			stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
		} finally {
            try {
                if(stmt!=null)stmt.close();
                DbConnector.getInstancia().releaseConn();
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}	// FIN METODO REMOVE

	public LinkedList<Cuotas> getCuotasByUser(Socio socio) { // GUARDA CON ESTE NO SE SI ANDA BIEN
		LinkedList<Cuotas> c= new LinkedList<Cuotas>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from cuotas where idSocio=?"
					);
			stmt.setInt(1, socio.getIdSocio());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()) {
				Cuotas q=new Cuotas();
				q.setIdCuota(rs.getInt("idcuotas")); //La clase se llama en plural el get es en singular pero la columna en la bd es en plural
				q.setFechaPago(rs.getDate("fechaPago"));
				q.setFechaDesde(rs.getDate("fechaDesde"));
				q.setFechaHasta(rs.getDate("fechaHasta"));
				// Busco el objeto autor para el libro
				int idSocio = rs.getInt("idSocio");
				SocioLogic soclog = new SocioLogic();
				Socio elSocio = new Socio();
				elSocio.setIdSocio(idSocio);
				elSocio = soclog.getOneById(elSocio);
				// Le agrego el autor
				q.setSocio(elSocio);	
				q.setEstado(rs.getString("estado"));
				// Añado la cuota a la lista de cuotas
				c.add(q);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return c;
	// Fin Metodo getCuotasByUser
	}

	public LinkedList<Cuotas> getCuotasImpagasByUser(Socio socio) {
		LinkedList<Cuotas> lasCuotas = new LinkedList<Cuotas>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from cuotas where idSocio=? and fechaPago is null"
					);
			stmt.setInt(1, socio.getIdSocio());
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				Cuotas c=new Cuotas();
				c.setIdCuota(rs.getInt("idcuotas")); //La clase se llama en plural el get es en singular pero la columna en la bd es en plural
				c.setFechaPago(rs.getDate("fechaPago"));
				c.setFechaDesde(rs.getDate("fechaDesde"));
				c.setFechaHasta(rs.getDate("fechaHasta"));
				// Busco el objeto autor para el libro
				int idSocio = rs.getInt("idSocio");
				SocioLogic soclog = new SocioLogic();
				Socio elSocio = new Socio();
				elSocio.setIdSocio(idSocio);
				elSocio = soclog.getOneById(elSocio);
				// Le agrego el autor
				c.setSocio(elSocio);
				c.setEstado(rs.getString("estado"));
				
				lasCuotas.add(c);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return lasCuotas;
	} // Fin Metodo GetById
	
	public LinkedList<Cuotas> getCuotasAConfirmarByUser(Socio socio) {
		LinkedList<Cuotas> lasCuotas = new LinkedList<Cuotas>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select * from cuotas where idSocio=? and estado =?"
					);
			stmt.setInt(1, socio.getIdSocio());
			stmt.setString(2, "A_Confirmar");
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				Cuotas c=new Cuotas();
				c.setIdCuota(rs.getInt("idcuotas")); //La clase se llama en plural el get es en singular pero la columna en la bd es en plural
				c.setFechaPago(rs.getDate("fechaPago"));
				c.setFechaDesde(rs.getDate("fechaDesde"));
				c.setFechaHasta(rs.getDate("fechaHasta"));
				// Busco el objeto autor para el libro
				int idSocio = rs.getInt("idSocio");
				SocioLogic soclog = new SocioLogic();
				Socio elSocio = new Socio();
				elSocio.setIdSocio(idSocio);
				elSocio = soclog.getOneById(elSocio);
				// Le agrego el autor
				c.setSocio(elSocio);
				c.setEstado(rs.getString("estado"));
				
				lasCuotas.add(c);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return lasCuotas;
	} // Fin Metodo GetById
	
	public LinkedList<Socio> getUsuariosAConfirmar() {
		LinkedList<Socio> losSocios = new LinkedList<Socio>();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT distinct idSocio FROM cuotas where estado =?"
					);
			stmt.setString(1, "A_Confirmar");
			rs=stmt.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
				Socio s=new Socio();
				int idSocio = rs.getInt("idSocio");
				SocioLogic soclog = new SocioLogic();
				s.setIdSocio(idSocio);
				s = soclog.getOneById(s);				
				losSocios.add(s);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return losSocios;
	} // Fin Metodo GetById
	
	
}
