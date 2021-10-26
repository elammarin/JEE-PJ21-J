package univ.lille.gl.sra1.repository;


import org.springframework.data.repository.CrudRepository;

import univ.lille.gl.sra1.model.Dock;


public interface DockRepository
        extends CrudRepository<Dock, Integer>
{

    boolean existsById(Integer id );

    boolean existsByNumDocker(Integer nbrDock);


}
