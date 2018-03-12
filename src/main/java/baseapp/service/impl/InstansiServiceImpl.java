package baseapp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baseapp.dao.InstansiMapper;
import baseapp.domain.Instansi;
import baseapp.domain.InstansiExample;
import baseapp.service.InstansiService;

@Service
public class InstansiServiceImpl implements InstansiService
{
    @Autowired
    private InstansiMapper instansiMapper;

    @Override
    public List<Instansi> getInstansiList ()
    {
        InstansiExample ex = new InstansiExample();
        
        return instansiMapper.selectByExample (ex);
    }

    @Override
    public Instansi getInstansiById (int id)
    {
        return instansiMapper.selectByPrimaryKey (id);
    }

    @Override
    public Instansi addInstansi (Instansi obj)
    {
        obj.setCreatedDate (new Date());
        instansiMapper.insert (obj);
        return obj;
    }

    @Override
    public Instansi editInstansi (Instansi obj)
    {
        obj.setCreatedDate (new Date());
        instansiMapper.updateByPrimaryKey (obj);
        return obj;
    }

    @Override
    public int deleteInstansiById (int id)
    {
        return instansiMapper.deleteByPrimaryKey (id);
    }

}
