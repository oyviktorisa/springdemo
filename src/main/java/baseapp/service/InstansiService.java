package baseapp.service;

import java.util.List;

import baseapp.domain.Instansi;

public interface InstansiService
{
    public List<Instansi> getInstansiList();
  
    public Instansi getInstansiById(int id);
    
    public Instansi addInstansi(Instansi obj);
    
    public Instansi editInstansi(Instansi obj);
    
    public int deleteInstansiById(int id);
    
}       
